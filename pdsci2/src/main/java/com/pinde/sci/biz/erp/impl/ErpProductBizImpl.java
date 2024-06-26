package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractPayPlanBiz;
import com.pinde.sci.biz.erp.IErpProductBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.ILogBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ErpCrmContractPayPlanMapper;
import com.pinde.sci.dao.base.ErpProductManageMapper;
import com.pinde.sci.dao.base.ErpProductManageProcessMapper;
import com.pinde.sci.dao.base.ErpProductManageUserMapper;
import com.pinde.sci.dao.erp.ErpCrmContractPayPlanExtMapper;
import com.pinde.sci.dao.erp.ErpProductManageExtMapper;
import com.pinde.sci.enums.erp.PayPlanStatusEnum;
import com.pinde.sci.model.erp.ErpProductManageUserExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ErpCrmContractPayPlanExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpProductBizImpl implements IErpProductBiz {

    @Autowired
    private ErpProductManageMapper manageMapper;
    @Autowired
    private ErpProductManageUserMapper manageUserMapper;
    @Autowired
    private ErpProductManageExtMapper manageUserExtMapper;
    @Autowired
    private ErpProductManageProcessMapper manageProcessMapper;
    @Autowired
    private ErpProductManageExtMapper manageExtMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IMsgBiz msgBiz;

    @Override
    public List<ErpProductManage> ownerProducts(Map<String, Object> paramMap) {
        return manageExtMapper.ownerProducts(paramMap);
    }

    @Override
    public List<ErpProductManage> process(Map<String, Object> paramMap) {
        return manageExtMapper.process(paramMap);
    }

    @Override
    public List<ErpProductManage> query(Map<String, Object> paramMap) {
        return manageExtMapper.query(paramMap);
    }

    @Override
    public List<ErpProductManageUser> getProductManageUsers(String manageFlow) {
        if (StringUtil.isNotBlank(manageFlow)) {
            ErpProductManageUserExample example = new ErpProductManageUserExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andManageFlowEqualTo(manageFlow);
            return manageUserMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<ErpProductManageProcess> getProductProcessList(String manageFlow) {
        if (StringUtil.isNotBlank(manageFlow)) {
            ErpProductManageProcessExample example = new ErpProductManageProcessExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andManageFlowEqualTo(manageFlow);
            example.setOrderByClause("PROCESS_TYPE_ID DESC,PROCESS_TIME ASC");
            return manageProcessMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public ErpProductManage readByFlow(String manageFlow) {
        return manageMapper.selectByPrimaryKey(manageFlow);
    }

    /**
     * 编辑项目信息，根据类型是否发送邮件提醒
     *
     * @param manage
     * @param type        1：项目启动提醒  2：项目跟进提醒  3：项目完成提醒
     * @param isSendEmail 是否发送邮件  true:是
     * @return
     */
    @Override
    public int updateManage(ErpProductManage manage, String type, boolean isSendEmail, String processMsg) {
        if (StringUtil.isNotBlank(manage.getManageFlow())) {
            //邮件提醒
            if(isSendEmail && StringUtil.isEquals(type, "1", "2", "3")){
                sendEmail(manage, type, processMsg);
            }
            GeneralMethod.setRecordInfo(manage, false);
            return manageMapper.updateByPrimaryKeySelective(manage);
        } else {
            manage.setManageFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(manage, true);
            return manageMapper.insertSelective(manage);
        }
    }

    /**
     * 发送邮件
     * @param manage
     * @param type
     * @param processMsg
     */
    private void sendEmail(ErpProductManage manage, String type, String processMsg) {
        if(manage != null) {
            //获取发送人列表
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("manageFlow", manage.getManageFlow());
            List<ErpProductManageUserExt> users = manageUserExtMapper.selectProductUserInfo(paramMap);
            //发送
            String title;
            String content;
            for (ErpProductManageUserExt user : users) {
                if (StringUtil.isNotBlank(user.getSysUser().getUserEmail())) {
                    switch (type) {
                        case "1":
                            title = "项目启动提醒【" + manage.getProductName() + "】";
                            content = user.getSysUser().getUserName() + "您好，" +
                                    "项目（" + manage.getProductName() + "）已启动！<br>客户名称：" + manage.getConsumerName() + "，请及时跟进！";
                            break;
                        case "2":
                            title = "项目跟进提醒【" + manage.getProductName() + "】";
                            content = user.getSysUser().getUserName() + "您好，" +
                                    "项目（" + manage.getProductName() + "）跟进中！<br>客户名称：" + manage.getConsumerName() + "！<br>跟进信息：" + processMsg + "<br>详情请登录ERP查看！";
                            break;
                        case "3":
                            title = "项目完成提醒【" + manage.getProductName() + "】";
                            content = user.getSysUser().getUserName() + "您好，" +
                                    "项目（" + manage.getProductName() + "）已完成！<br>客户名称：" + manage.getConsumerName() + "！<br>完成说明：" + processMsg + "<br>详情请登录ERP查看！";
                            break;
                        default:
                            throw new RuntimeException("ISSENDEMAIL TYPE IS UNDEFINED");
                    }
                    msgBiz.addEmailMsg(user.getSysUser().getUserEmail(), title, content);
                }
            }
            //跟进信息发给项目发起人
            if(StringUtil.isEquals(type, "2")){
                SysUser user = userBiz.readSysUser(manage.getApprovalUserFlow());
                if(user != null && StringUtil.isNotBlank(user.getUserEmail())) {
                    title = "项目跟进提醒【" + manage.getProductName() + "】";
                    content = manage.getApprovalUserName() + "您好，" +
                            "项目（" + manage.getProductName() + "）跟进中！<br>客户名称：" + manage.getConsumerName() + "！<br>跟进信息：" + processMsg + "<br>详情请登录ERP查看！";
                    msgBiz.addEmailMsg(user.getUserEmail(), title, content);
                }
            }
        }
    }

    @Override
    public int saveManage(ErpProductManage manage, List<String> userFlow) {
        //删除以前选择的参与人
        deleteJoinUserByManageFlow(manage.getManageFlow());
        int result = updateManage(manage, null, false, null);
        if (result == 1) {
            if (userFlow != null && userFlow.size() > 0) {
                for (String flow : userFlow) {
                    ErpProductManageUser manageUser = getProductManageUser(manage.getManageFlow(), flow);
                    if (manageUser == null)
                        manageUser = new ErpProductManageUser();
                    manageUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    manageUser.setManageFlow(manage.getManageFlow());
                    SysUser user = userBiz.readSysUser(flow);
                    manageUser.setUserFlow(flow);
                    if (user != null) {
                        manageUser.setUserName(user.getUserName());
                    }
                    saveManageUser(manageUser);
                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int saveManageUser(ErpProductManageUser manageUser) {
        if (StringUtil.isNotBlank(manageUser.getRecordFlow())) {
            GeneralMethod.setRecordInfo(manageUser, false);
            return manageUserMapper.updateByPrimaryKeySelective(manageUser);
        } else {
            manageUser.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(manageUser, true);
            return manageUserMapper.insertSelective(manageUser);
        }
    }

    @Override
    public int saveManageProcess(ErpProductManageProcess manageProcess) {
        ErpProductManage manage = readByFlow(manageProcess.getManageFlow());
        if (manage != null) {
            if ("Complete".equals(manageProcess.getProcessTypeId())) {
                    manage.setStatusId("Complete");
                    manage.setStatusName("完成");
                    manage.setCompleteTime(DateUtil.getCurrDate());
                    updateManage(manage, "3", true, manageProcess.getProcessContent());
            }else{
                sendEmail(manage, "2", manageProcess.getProcessContent());
            }
            if (StringUtil.isNotBlank(manageProcess.getProcessFlow())) {
                GeneralMethod.setRecordInfo(manageProcess, false);
                return manageProcessMapper.updateByPrimaryKeySelective(manageProcess);
            } else {
                manageProcess.setProcessFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(manageProcess, true);
                return manageProcessMapper.insertSelective(manageProcess);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    private ErpProductManageUser getProductManageUser(String manageFlow, String flow) {
        ErpProductManageUserExample example = new ErpProductManageUserExample();
        example.createCriteria().andManageFlowEqualTo(manageFlow).andUserFlowEqualTo(flow);
        List<ErpProductManageUser> list = manageUserMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private int deleteJoinUserByManageFlow(String manageFlow) {
        if (StringUtil.isNotBlank(manageFlow)) {
            return manageExtMapper.deleteJoinUserByManageFlow(manageFlow);
        }
        return 0;
    }
}
