package com.pinde.sci.webservice;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.*;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.webservice.IShzsWebServiceBiz;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.webservice.bean.shzs.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: litao
 * Date: 2018-11-09
 * Version: 1.0
 */
@WebService
@Component
public class ShzsArrangeService {
    private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);


    @Resource
    private WebServiceContext wsContext;
    @Autowired
    private ISchRotationDeptBiz  rotationDeptBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private  IShzsWebServiceBiz shzsWebServiceBiz;

    private void getClientInfo() {
        try {
            MessageContext mc = wsContext.getMessageContext();

            HttpExchange exchange = (HttpExchange) mc
                    .get(JAXWSProperties.HTTP_EXCHANGE);
            InetSocketAddress isa = exchange.getRemoteAddress();
            logger.debug("InetSocketAddress : " + isa);
            logger.debug("Hostname : "
                    + isa.getAddress().getHostAddress() + " address: "
                    + isa.getAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 接收科室数据
     *
     * @param deptData {"actionType":"Add","deptInfo":{"deptId":"123456","deptName":"呼吸内科"},"token":"F5A4634CBC8240478E5952D273D007B4"}
     * @return
     */
    public synchronized String synDeptInfo(String deptData) {
        try {
            getClientInfo();
            logger.debug("deptData======="+deptData);
            if (StringUtil.isBlank(deptData)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            DeptInfoForm form=null;
            try {
                form=JSON.parseObject(deptData, DeptInfoForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            if(StringUtil.isBlank(form.getActionType()))
            {
                logger.debug("操作失败，ActionType为空！");
                return _getResMsg("000500", "操作失败，ActionType为空！", "");
            }
            if(!"Add".equals(form.getActionType())&&!"Edit".equals(form.getActionType())
                    &&!"Stop".equals(form.getActionType())&&!"Enable".equals(form.getActionType()))
            {
                logger.debug("操作失败，ActionType错误！");
                return _getResMsg("000700", "操作失败，ActionType错误！", "");
            }
            DeptInfo deptInfo=form.getDeptInfo();
            if(deptInfo==null)
            {
                logger.debug("操作失败，科室信息为空！");
                return _getResMsg("000700", "操作失败，科室信息为空！", "");
            }
            if(StringUtil.isBlank(deptInfo.getDeptId()))
            {
                logger.debug("操作失败，科室标识符为空！");
                return _getResMsg("000800", "操作失败，科室标识符为空！", "");
            }
            //新增
            if("Add".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(deptInfo.getDeptName()))
                {
                    logger.debug("操作失败，科室名称为空！");
                    return _getResMsg("000900", "操作失败，科室名称为空！", "");
                }
                if(deptInfo.getDeptName().length()>25)
                {
                    logger.debug("操作失败，科室名称过长，不超过25个字符！");
                    return _getResMsg("001000", "操作失败，科室名称过长，不超过25个字符！", "");
                }
                SysDept old=shzsWebServiceBiz.readDeptByName(deptInfo.getDeptName(),ShzsGlobalContent.SHZS_ORG_FLOW);
                if(old!=null)
                {
                    logger.debug("操作失败，科室名称已存在！");
                    return _getResMsg("001100", "操作失败，科室名称已存在！", "");
                }
                old=shzsWebServiceBiz.readDeptById(deptInfo.getDeptId());
                if(old!=null)
                {
                    logger.debug("操作失败，科室标识符已存在！且科室名称为【"+old.getDeptName()+"】");
                    return _getResMsg("001200", "操作失败，科室标识符已存在！且科室名称为【"+old.getDeptName()+"】", "");
                }
                int c=shzsWebServiceBiz.saveDept(deptInfo);
                if(c==0)
                {
                    logger.debug("操作失败");
                    return _getResMsg("001300", "操作失败", "");
                }
            }else
            //修改
            if("Edit".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(deptInfo.getDeptName()))
                {
                    logger.debug("操作失败，科室名称为空！");
                    return _getResMsg("000900", "操作失败，科室名称为空！", "");
                }
                if(deptInfo.getDeptName().length()>25)
                {
                    logger.debug("操作失败，科室名称过长，不超过25个字符！");
                    return _getResMsg("001000", "操作失败，科室名称过长，不超过25个字符！", "");
                }
                SysDept old=shzsWebServiceBiz.readDeptById(deptInfo.getDeptId());
                if(old==null)
                {
                    logger.debug("操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！");
                    return _getResMsg("001400", "操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！", "");
                }
                 old=shzsWebServiceBiz.readDeptByName(deptInfo.getDeptName(), ShzsGlobalContent.SHZS_ORG_FLOW);

                if(old!=null&&!deptInfo.getDeptId().equals(old.getDeptFlow()))
                {
                    logger.debug("操作失败，科室名称已存在！");
                    return _getResMsg("001100", "操作失败，科室名称已存在！", "");
                }
                int c=shzsWebServiceBiz.saveEditDept(deptInfo);
                if(c==0)
                {
                    logger.debug( "操作失败");
                    return _getResMsg("001300", "操作失败", "");
                }
            }else   if("Stop".equals(form.getActionType()))
            {
                SchDept schDept=shzsWebServiceBiz.readSchDeptById(deptInfo.getDeptId());
                if(schDept==null)
                {
                    logger.debug("操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！");
                    return _getResMsg("001400", "操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！", "");
                }
                if(ShzsGlobalContent.RECORD_STATUS_N.equals(schDept.getRecordStatus()))
                {
                    logger.debug("操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室已停用，请勿重复停用！");
                    return _getResMsg("001700", "操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室已停用，请勿重复停用！", "");
                }
                int c=shzsWebServiceBiz.stopDept(deptInfo);
                if(c==0)
                {
                    logger.debug("001800", "停用失败", "");
                    return _getResMsg("001800", "停用失败", "");
                }
            }else   if("Enable".equals(form.getActionType()))
            {
                SchDept schDept=shzsWebServiceBiz.readSchDeptById(deptInfo.getDeptId());
                if(schDept==null)
                {
                    logger.debug("操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！");
                    return _getResMsg("001400", "操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室在系统中不存在！", "");
                }
                if(ShzsGlobalContent.RECORD_STATUS_Y.equals(schDept.getRecordStatus()))
                {
                    logger.debug("操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室已启用，请勿重复启用！");
                    return _getResMsg("001500", "操作失败，科室标识符为【"+deptInfo.getDeptId()+"】科室已启用，请勿重复启用！", "");
                }
                int c=shzsWebServiceBiz.enableDept(deptInfo);
                if(c==0)
                {
                    logger.debug("001600", "启用失败", "");
                    return _getResMsg("001600", "启用失败", "");
                }
            }
            return _getResMsg("000000", "操作成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }

    /**
     * 接收师资信息
     *{"actionType":"Add","deptIds":["123456"],"roles":["Teacher","Head","Admin"],"token":"F5A4634CBC8240478E5952D273D007B4","userInfo":{"idNo":"320684199010129382","userCode":"123456","userName":"带教老师","userPhone":"15601582206"}}
     * @param userData
     * @return
     */
    public synchronized String synUserInfo(String userData) {
        try {
            getClientInfo();
            logger.debug("userData======="+userData);
            if (StringUtil.isBlank(userData)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            UserInfoForm form=null;
            try {
                form=JSON.parseObject(userData, UserInfoForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            if(StringUtil.isBlank(form.getActionType()))
            {
                logger.debug("操作失败，ActionType为空！");
                return _getResMsg("000500", "操作失败，ActionType为空！", "");
            }
            if(!"Add".equals(form.getActionType())&&!"Edit".equals(form.getActionType())
                    &&!"Stop".equals(form.getActionType())&&!"Enable".equals(form.getActionType()))
            {
                logger.debug("操作失败，ActionType错误！");
                return _getResMsg("000700", "操作失败，ActionType错误！", "");
            }
            UserInfo userInfo=form.getUserInfo();
            if(userInfo==null)
            {
                logger.debug("操作失败，人员信息为空！");
                return _getResMsg("000800", "操作失败，人员信息为空！", "");
            }
            if("Add".equals(form.getActionType())) {
                if(StringUtil.isBlank(userInfo.getUserCode()))
                {
                    logger.debug("操作失败，用户名为空！");
                    return _getResMsg("002800", "操作失败，用户名为空！", ""); 
                }
                Boolean b = userInfo.getUserCode().matches("^[a-zA-Z]\\w{2,17}$");
                if(!b)
                {
                    logger.debug("操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！");
                    return _getResMsg("002600", "操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！", "");
                }
                if(StringUtil.isBlank(userInfo.getPassWord()))
                {
                    logger.debug("操作失败，密码为空！");
                    return _getResMsg("001400", "操作失败，密码为空！", "");
                }
                Boolean b1 = userInfo.getPassWord().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
                if(!b1)
                {
                    logger.debug("操作失败，密码必须包括数字、字母，长度8－20！");
                    return _getResMsg("001300", "操作失败，密码必须包括数字、字母，长度8－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getUserName()))
                {
                    logger.debug("操作失败，用户姓名为空！");
                    return _getResMsg("002900", "操作失败，用户姓名为空！", "");
                }
                Boolean b2 = userInfo.getUserName().matches("^[\\u4E00-\\u9FA5]+$");
                if(!b2||userInfo.getUserName().length()>20||userInfo.getUserName().length()<2)
                {
                    logger.debug("操作失败，姓名只能是中文，长度2－20！");
                    return _getResMsg("002300", "操作失败，姓名只能是中文，长度2－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getIdNo()))
                {
                    logger.debug("操作失败，证件号为空！");
                    return _getResMsg("003100", "操作失败，证件号为空！", "");
                }
                Boolean b3 = userInfo.getIdNo().matches("^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$");
                if(!b3)
                {
                    logger.debug("操作失败，无效的身份证号码！");
                    return _getResMsg("002100", "操作失败，无效的身份证号码！", ""); 
                }
                if(StringUtil.isBlank(userInfo.getUserPhone()))
                {
                    logger.debug("操作失败，手机号为空！");
                    return _getResMsg("002000", "操作失败，手机号为空！", ""); 
                }
                Boolean b4 = userInfo.getUserPhone().matches("^[1][3456789]\\d{9}$");
                if(!b4)
                {
                    logger.debug("操作失败，无效的手机号！");
                    return _getResMsg("002200", "操作失败，无效的手机号！", ""); 
                }
                //查是否存在此用户
                SysUser user = userBiz.findByUserCode(userInfo.getUserCode());
                if (user != null) {
                    logger.debug("操作失败，用户名为【" + userInfo.getUserCode() + "】的用户，在系统中已存在！");
                   return _getResMsg("002700", "操作失败，用户名为【"+userInfo.getUserCode()+"】的用户，在系统中已存在！", "");
                }
                user = userBiz.findByUserPhone(userInfo.getUserPhone());
                if (user != null) {
                    logger.debug("操作失败，手机号为【" + userInfo.getUserPhone() + "】的用户，在系统中已存在！");
                   return _getResMsg("001900", "操作失败，手机号为【"+userInfo.getUserPhone()+"】的用户，在系统中已存在！", "");
                }
                user = userBiz.findByIdNo(userInfo.getIdNo());
                if (user != null) {
                    logger.debug("操作失败，证件号为【" + userInfo.getIdNo() + "】的用户，在系统中已存在！");
                    return _getResMsg("003000", "操作失败，证件号为【"+userInfo.getIdNo()+"】的用户，在系统中已存在！", ""); 
                }
                if(form.getRoles()==null)
                {
                    logger.debug("操作失败，请为当前师资人员添加角色！");
                    return _getResMsg("001500", "操作失败，请为当前师资人员添加角色！", "");
                }
                if(form.getRoles().size()==0)
                {
                    logger.debug("操作失败，请为当前师资人员添加角色！");
                    return _getResMsg("001500", "操作失败，请为当前师资人员添加角色！", "");
                }
                if(form.getRoles().size()>2)
                {
                    logger.debug("操作失败，师资人员最多2个角色，带教与主任！");
                    return _getResMsg("001800", "操作失败，师资人员最多2个角色，带教与主任！", "");
                }
                boolean f=false;
                boolean isTeacher=false;
                boolean isHead=false;
                for(String role:form.getRoles())
                {
                    if(!"Head".equals(role)&&!"Teacher".equals(role))
                    {
                        f=true;
                    }
                    if("Teacher".equals(role))
                    {
                        isTeacher=true;
                    }
                    if("Head".equals(role))
                    {
                        isHead=true;
                    }
                }
                if(f)
                {
                    logger.debug("操作失败，师资人员存在不是带教或主任的角色！");
                    return _getResMsg("001700", "操作失败，师资人员存在不是带教或主任的角色！", "");
                }
                //原来是否是带教或主任

                boolean isTeacherOld=false;
                boolean isHeadOld=false;
                List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(form.getUserInfo().getUserFlow());
                for(SysUserRole userRole:userRoles)
                {
                    if(ShzsGlobalContent.SHZS_TEACHER_ROLE_FLOW.equals(userRole.getRoleFlow()))
                    {
                        isTeacherOld=true;
                    }
                    if(ShzsGlobalContent.SHZS_HEAD_ROLE_FLOW.equals(userRole.getRoleFlow()))
                    {
                        isHeadOld=true;
                    }
                }
                //消除原来带教角色，判断是否有学员已经选其为带教老师
                if(isTeacherOld&&!isTeacher)
                {
                    int count=shzsWebServiceBiz.readDocCountByTea(userInfo.getUserFlow());
                    if(count>0)
                    {
                        logger.debug("操作失败，此师资人员信息，已有学员选择其为带教老师，无法去除带教角色！");
                        return _getResMsg("000900", "操作失败，此师资人员信息，已有学员选择其为带教老师，无法去除带教角色！", "");
                    }
                }
                //消除原来带教角色，判断是否有学员已经选其为主任
                if(isHeadOld&&!isHead)
                {
                    int count=shzsWebServiceBiz.readDocCountByHead(userInfo.getUserFlow());
                    if(count>0)
                    {
                        logger.debug("操作失败，此师资人员信息，已有学员选择其为科主任，无法去除主任角色！");
                        return _getResMsg("001000", "操作失败，此师资人员信息，已有学员选择其为科主任，无法去除主任角色！", "");
                    }
                }
                if(form.getDeptIds()==null)
                {
                    logger.debug("操作失败，请为当前师资人员添加所在科室！");
                    return _getResMsg("001600", "操作失败，请为当前师资人员添加所在科室！", "");
                }
                if(form.getDeptIds().size()==0)
                {
                    logger.debug("操作失败，请为当前师资人员添加所在科室！");
                    return _getResMsg("001600", "操作失败，请为当前师资人员添加所在科室！", "");
                }
                List<SysDept> depts=new ArrayList<>();
                for(String deptId:form.getDeptIds())
                {
                    SysDept dept=shzsWebServiceBiz.readDeptById(deptId);
                    if(dept==null)
                    {
                        logger.debug("操作失败，科室标识符为【"+deptId+"】科室在系统中不存在！");
                        return _getResMsg("003300", "操作失败，科室标识符为【"+deptId+"】科室在系统中不存在！", "");
                    }
                    if(!dept.getOrgFlow().equals(ShzsGlobalContent.SHZS_ORG_FLOW))
                    {
                        logger.debug("操作失败，科室标识符为【"+deptId+"】科室不是当前基地科室！");
                        return _getResMsg("003200", "操作失败，科室标识符为【"+deptId+"】科室不是当前基地科室！", "");
                    }
                    depts.add(dept);
                }
                String userFlow=shzsWebServiceBiz.saveUserInfo(form,depts);
                if(StringUtil.isBlank(userFlow))
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", userFlow);
            }else if("Edit".equals(form.getActionType()))
            {

                if(StringUtil.isBlank(userInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", ""); 
                }
                SysUser user=userBiz.readSysUser(userInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                if(StringUtil.isBlank(userInfo.getUserCode()))
                {
                    logger.debug("操作失败，用户名为空！");
                    return _getResMsg("002800", "操作失败，用户名为空！", ""); 
                }
                Boolean b = userInfo.getUserCode().matches("^[a-zA-Z]\\w{2,17}$");
                if(!b)
                {
                    logger.debug("操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！");
                    return _getResMsg("002600", "操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！", "");
                }
                if(StringUtil.isBlank(userInfo.getPassWord()))
                {
                    logger.debug("操作失败，密码为空！");
                    return _getResMsg("001400", "操作失败，密码为空！", "");
                }
                Boolean b1 = userInfo.getPassWord().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
                if(!b1)
                {
                    logger.debug("操作失败，密码必须包括数字、字母，长度8－20！");
                    return _getResMsg("001300", "操作失败，密码必须包括数字、字母，长度8－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getUserName()))
                {
                    logger.debug("操作失败，用户姓名为空！");
                    return _getResMsg("002900", "操作失败，用户姓名为空！", "");
                }
                Boolean b2 = userInfo.getUserName().matches("^[\\u4E00-\\u9FA5]+$");
                if(!b2||userInfo.getUserName().length()>20||userInfo.getUserName().length()<2)
                {
                    logger.debug("操作失败，姓名只能是中文，长度2－20！");
                    return _getResMsg("002300", "操作失败，姓名只能是中文，长度2－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getIdNo()))
                {
                    logger.debug("操作失败，证件号为空！");
                    return _getResMsg("003100", "操作失败，证件号为空！", "");
                }
                Boolean b3 = userInfo.getIdNo().matches("^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$");
                if(!b3)
                {
                    logger.debug("操作失败，无效的身份证号码！");
                    return _getResMsg("002100", "操作失败，无效的身份证号码！", ""); 
                }
                if(StringUtil.isBlank(userInfo.getUserPhone()))
                {
                    logger.debug("操作失败，手机号为空！");
                    return _getResMsg("002000", "操作失败，手机号为空！", ""); 
                }
                Boolean b4 = userInfo.getUserPhone().matches("^[1][3456789]\\d{9}$");
                if(!b4)
                {
                    logger.debug("操作失败，无效的手机号！");
                    return _getResMsg("002200", "操作失败，无效的手机号！", ""); 
                }
                //查是否存在此用户
                SysUser user1 = userBiz.findByUserCode(userInfo.getUserCode());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，用户名为【" + userInfo.getUserCode() + "】的用户，在系统中已存在！");
                   return _getResMsg("002700", "操作失败，用户名为【"+userInfo.getUserCode()+"】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByUserPhone(userInfo.getUserPhone());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，手机号为【" + userInfo.getUserPhone() + "】的用户，在系统中已存在！");
                   return _getResMsg("001900", "操作失败，手机号为【"+userInfo.getUserPhone()+"】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByIdNo(userInfo.getIdNo());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，证件号为【" + userInfo.getIdNo() + "】的用户，在系统中已存在！");
                    return _getResMsg("003000", "操作失败，证件号为【"+userInfo.getIdNo()+"】的用户，在系统中已存在！", ""); 
                }
                if(form.getRoles()==null)
                {
                    logger.debug("操作失败，请为当前师资人员添加角色！");
                    return _getResMsg("001500", "操作失败，请为当前师资人员添加角色！", "");
                }
                if(form.getRoles().size()==0)
                {
                    logger.debug("操作失败，请为当前师资人员添加角色！");
                    return _getResMsg("001500", "操作失败，请为当前师资人员添加角色！", "");
                }
                if(form.getRoles().size()>2)
                {
                    logger.debug("操作失败，师资人员最多2个角色，带教与主任！");
                    return _getResMsg("001800", "操作失败，师资人员最多2个角色，带教与主任！", "");
                }
                boolean f=false;
                for(String role:form.getRoles())
                {
                    if(!"Head".equals(role)&&!"Teacher".equals(role))
                    {
                        f=true;
                    }
                }
                if(f)
                {
                    logger.debug("操作失败，师资人员存在不是带教或主任的角色！");
                    return _getResMsg("001700", "操作失败，师资人员存在不是带教或主任的角色！", "");
                }
                if(form.getDeptIds()==null)
                {
                    logger.debug("操作失败，请为当前师资人员添加所在科室！");
                    return _getResMsg("001600", "操作失败，请为当前师资人员添加所在科室！", "");
                }
                if(form.getDeptIds().size()==0)
                {
                    logger.debug("操作失败，请为当前师资人员添加所在科室！");
                    return _getResMsg("001600", "操作失败，请为当前师资人员添加所在科室！", "");
                }
                List<SysDept> depts=new ArrayList<>();
                for(String deptId:form.getDeptIds())
                {
                    SysDept dept=shzsWebServiceBiz.readDeptById(deptId);
                    if(dept==null)
                    {
                        logger.debug("操作失败，科室标识符为【"+deptId+"】科室在系统中不存在！");
                        return _getResMsg("003300", "操作失败，科室标识符为【"+deptId+"】科室在系统中不存在！", "");
                    }
                    if(!dept.getOrgFlow().equals(ShzsGlobalContent.SHZS_ORG_FLOW))
                    {
                        logger.debug("操作失败，科室标识符为【"+deptId+"】科室不是当前基地科室！");
                        return _getResMsg("003200", "操作失败，科室标识符为【"+deptId+"】科室不是当前基地科室！", "");
                    }
                    depts.add(dept);
                }
                String userFlow=shzsWebServiceBiz.saveUserInfo(form,depts);
                if(StringUtil.isBlank(userFlow))
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", ""); 
            }else if("Stop".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(userInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", ""); 
                }
                SysUser user=userBiz.readSysUser(userInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                if("Locked".equals(user.getStatusId()))
                {
                    logger.debug("操作失败，此用户已被停用，请勿重复操作！");
                    return _getResMsg("001200", "操作失败，此用户已被停用，请勿重复操作！", "");
                }
                user.setStatusId("Locked");
                user.setStatusDesc("锁定");
                int c=userBiz.updateUser(user);
                if(c==0)
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", ""); 
            }else if("Enable".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(userInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", ""); 
                }
                SysUser user=userBiz.readSysUser(userInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                if("Activated".equals(user.getStatusId()))
                {
                    logger.debug("操作失败，此用户已被启用，请勿重复操作！");
                    return _getResMsg("001100", "操作失败，此用户已被启用，请勿重复操作！", "");
                }
                user.setStatusId("Activated");
                user.setStatusDesc("已激活");
                int c=userBiz.updateUser(user);
                if(c==0)
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
            }

            return _getResMsg("000000", "操作成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }

    /**
     * 接收 修改个人基本信息
     *{"token":"F5A4634CBC8240478E5952D273D007B4","userInfo":{"idNo":"320684199010129382","userCode":"123456","userName":"带教老师","userPhone":"15601582206","userFlow":"15601582206"}}
     * @param userData
     * @return
     */
    public synchronized String synUserBaseInfo(String userData) {
        try {
            getClientInfo();
            logger.debug("userData======="+userData);
            if (StringUtil.isBlank(userData)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            UserInfoForm form=null;
            try {
                form=JSON.parseObject(userData, UserInfoForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            UserInfo userInfo=form.getUserInfo();
            if(userInfo==null)
            {
                logger.debug("操作失败，人员信息为空！");
                return _getResMsg("000800", "操作失败，人员信息为空！", "");
            }
                if(StringUtil.isBlank(userInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", "");
                }
                SysUser user=userBiz.readSysUser(userInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+userInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                if(StringUtil.isBlank(userInfo.getUserCode()))
                {
                    logger.debug("操作失败，用户名为空！");
                    return _getResMsg("002800", "操作失败，用户名为空！", ""); 
                }
                Boolean b = userInfo.getUserCode().matches("^[a-zA-Z]\\w{2,17}$");
                if(!b)
                {
                    logger.debug("操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！");
                    return _getResMsg("002600", "操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！", "");
                }
                if(StringUtil.isBlank(userInfo.getPassWord()))
                {
                    logger.debug("操作失败，密码为空！");
                    return _getResMsg("001400", "操作失败，密码为空！", "");
                }
                Boolean b1 = userInfo.getPassWord().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
                if(!b1)
                {
                    logger.debug("操作失败，密码必须包括数字、字母，长度8－20！");
                    return _getResMsg("001300", "操作失败，密码必须包括数字、字母，长度8－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getUserName()))
                {
                    logger.debug("操作失败，用户姓名为空！");
                    return _getResMsg("002900", "操作失败，用户姓名为空！", "");
                }
                Boolean b2 = userInfo.getUserName().matches("^[\\u4E00-\\u9FA5]+$");
                if(!b2||userInfo.getUserName().length()>20||userInfo.getUserName().length()<2)
                {
                    logger.debug("操作失败，姓名只能是中文，长度2－20！");
                    return _getResMsg("002300", "操作失败，姓名只能是中文，长度2－20！", "");
                }
                if(StringUtil.isBlank(userInfo.getIdNo()))
                {
                    logger.debug("操作失败，证件号为空！");
                    return _getResMsg("003100", "操作失败，证件号为空！", "");
                }
                Boolean b3 = userInfo.getIdNo().matches("^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$");
                if(!b3)
                {
                    logger.debug("操作失败，无效的身份证号码！");
                    return _getResMsg("002100", "操作失败，无效的身份证号码！", ""); 
                }
                if(StringUtil.isBlank(userInfo.getUserPhone()))
                {
                    logger.debug("操作失败，手机号为空！");
                    return _getResMsg("002000", "操作失败，手机号为空！", ""); 
                }
                Boolean b4 = userInfo.getUserPhone().matches("^[1][3456789]\\d{9}$");
                if(!b4)
                {
                    logger.debug("操作失败，无效的手机号！");
                    return _getResMsg("002200", "操作失败，无效的手机号！", ""); 
                }
                //查是否存在此用户
                SysUser user1 = userBiz.findByUserCode(userInfo.getUserCode());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，用户名为【" + userInfo.getUserCode() + "】的用户，在系统中已存在！");
                   return _getResMsg("002700", "操作失败，用户名为【"+userInfo.getUserCode()+"】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByUserPhone(userInfo.getUserPhone());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，手机号为【" + userInfo.getUserPhone() + "】的用户，在系统中已存在！");
                   return _getResMsg("001900", "操作失败，手机号为【"+userInfo.getUserPhone()+"】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByIdNo(userInfo.getIdNo());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，证件号为【" + userInfo.getIdNo() + "】的用户，在系统中已存在！");
                    return _getResMsg("003000", "操作失败，证件号为【"+userInfo.getIdNo()+"】的用户，在系统中已存在！", ""); 
                }
                String userFlow=shzsWebServiceBiz.saveUserBaseInfo(form);
                if(StringUtil.isBlank(userFlow))
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", ""); 
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }

    /**
     *接收学员个人信息
     *
     *{"actionType":"Add","token":"F5A4634CBC8240478E5952D273D007B4","userInfo":{"doctorTypeId":"Company","idNo":"320684199010129382","sessionNumber":"2018","trainingSpeId":"d","trainingYears":"d","userCode":"123456","userName":"带教老师","userPhone":"15601582206"}}
     * @param docData
     * @return
     */
    public synchronized String synDocInfo(String docData){
        try {
            getClientInfo();
            logger.debug("docData======="+docData);
            if (StringUtil.isBlank(docData)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            DocInfoForm form=null;
            try {
                form=JSON.parseObject(docData, DocInfoForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            if(StringUtil.isBlank(form.getActionType()))
            {
                logger.debug("操作失败，ActionType为空！");
                return _getResMsg("000500", "操作失败，ActionType为空！", "");
            }
            if(!"Add".equals(form.getActionType())&&!"Edit".equals(form.getActionType())
                    &&!"Delete".equals(form.getActionType()))
            {
                logger.debug("操作失败，ActionType错误！");
                return _getResMsg("000700", "操作失败，ActionType错误！", "");
            }
            DocInfo docInfo=form.getUserInfo();
            if(docInfo==null)
            {
                logger.debug("操作失败，人员信息为空！");
                return _getResMsg("000800", "操作失败，人员信息为空！", "");
            }
            if("Add".equals(form.getActionType())) {
                if(StringUtil.isBlank(docInfo.getUserCode()))
                {
                    logger.debug("操作失败，用户名为空！");
                    return _getResMsg("002800", "操作失败，用户名为空！", ""); 
                }
                Boolean b = docInfo.getUserCode().matches("^[a-zA-Z]\\w{2,17}$");
                if(!b)
                {
                    logger.debug("操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！");
                    return _getResMsg("002600", "操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！", "");
                }
                if(StringUtil.isBlank(docInfo.getPassWord()))
                {
                    logger.debug("操作失败，密码为空！");
                    return _getResMsg("001400", "操作失败，密码为空！", "");
                }
                Boolean b1 = docInfo.getPassWord().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
                if(!b1)
                {
                    logger.debug("操作失败，密码必须包括数字、字母，长度8－20！");
                    return _getResMsg("001300", "操作失败，密码必须包括数字、字母，长度8－20！", "");
                }
                if(StringUtil.isBlank(docInfo.getUserName()))
                {
                    logger.debug("操作失败，用户姓名为空！");
                    return _getResMsg("002900", "操作失败，用户姓名为空！", "");
                }
                Boolean b2 = docInfo.getUserName().matches("^[\\u4E00-\\u9FA5]+$");
                if(!b2||docInfo.getUserName().length()>20||docInfo.getUserName().length()<2)
                {
                    logger.debug("操作失败，姓名只能是中文，长度2－20！");
                    return _getResMsg("002300", "操作失败，姓名只能是中文，长度2－20！", "");
                }
                if(StringUtil.isBlank(docInfo.getIdNo()))
                {
                    logger.debug("操作失败，证件号为空！");
                    return _getResMsg("003100", "操作失败，证件号为空！", "");
                }
                Boolean b3 = docInfo.getIdNo().matches("^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$");
                if(!b3)
                {
                    logger.debug("操作失败，无效的身份证号码！");
                    return _getResMsg("002100", "操作失败，无效的身份证号码！", ""); 
                }
                if(StringUtil.isBlank(docInfo.getUserPhone()))
                {
                    logger.debug("操作失败，手机号为空！");
                    return _getResMsg("002000", "操作失败，手机号为空！", ""); 
                }
                Boolean b4 = docInfo.getUserPhone().matches("^[1][3456789]\\d{9}$");
                if(!b4)
                {
                    logger.debug("操作失败，无效的手机号！");
                    return _getResMsg("002200", "操作失败，无效的手机号！", ""); 
                }
                //查是否存在此用户
                SysUser user = userBiz.findByUserCode(docInfo.getUserCode());
                if (user != null) {
                    logger.debug("操作失败，用户名为【" + docInfo.getUserCode() + "】的用户，在系统中已存在！");
                    return _getResMsg("002700", "操作失败，用户名为【" + docInfo.getUserCode() + "】的用户，在系统中已存在！", "");
                }
                user = userBiz.findByUserPhone(docInfo.getUserPhone());
                if (user != null) {
                    logger.debug("操作失败，手机号为【" + docInfo.getUserPhone() + "】的用户，在系统中已存在！");
                    return _getResMsg("001900", "操作失败，手机号为【" + docInfo.getUserPhone() + "】的用户，在系统中已存在！", "");
                }
                user = userBiz.findByIdNo(docInfo.getIdNo());
                if (user != null) {
                    logger.debug("操作失败，证件号为【" + docInfo.getIdNo() + "】的用户，在系统中已存在！");
                    return _getResMsg("003000", "操作失败，证件号为【" + docInfo.getIdNo() + "】的用户，在系统中已存在！", "");
                }
                if(StringUtil.isBlank(docInfo.getSessionNumber()))
                {
                    logger.debug("操作失败，年级为空！");
                    return _getResMsg("000900", "操作失败，年级为空！", ""); 
                }
                Boolean b5 = docInfo.getSessionNumber().matches("^(19\\d{2}|[2-9]\\d{3})$");
                if (!b5){
                    logger.debug("操作失败，年级有误，只能是1900至9999之间的年份！");
                    return _getResMsg("001000", "操作失败，年级有误，只能是1900至9999之间的年份！", "");
                }
                if(StringUtil.isBlank(docInfo.getTrainingSpeId()))
                {
                    logger.debug("操作失败，培训专业代码为空！");
                    return _getResMsg("001200", "操作失败，培训专业代码为空！", "");
                }
                if(!ShzsGlobalContent.speIds.contains(docInfo.getTrainingSpeId()))
                {
                    logger.debug("操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！");
                    return _getResMsg("001100", "操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！", "");
                }
                if(StringUtil.isBlank(docInfo.getDoctorTypeId()))
                {
                    logger.debug("操作失败，人员类型代码为空！");
                    return _getResMsg("001800", "操作失败，人员类型代码为空！", "");
                }
                if(!ShzsGlobalContent.doctorTypeIds.contains(docInfo.getDoctorTypeId()))
                {
                    logger.debug("操作失败，人员类型代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.doctorTypeIds)+"】！");
                    return _getResMsg("001700", "操作失败，人员类型代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.doctorTypeIds)+"】！", "");
                }
                if(StringUtil.isBlank(docInfo.getTrainingYears()))
                {
                    logger.debug("操作失败，培养年限为空！");
                    return _getResMsg("001600", "操作失败，培养年限为空！", "");
                }
                Boolean b6 = docInfo.getTrainingYears().matches("^(1|2|3)$");
                if (!b6){
                    logger.debug("操作失败，培养年限，只能是1或2或3！");
                    return _getResMsg("001500", "操作失败，培养年限，只能是1或2或3！", "");
                }
                SchRotation rotation=shzsWebServiceBiz.readRotationBySpeId(docInfo.getTrainingSpeId(),"Doctor");
                if(rotation==null)
                {
                    logger.debug("操作失败，此专业未配置轮转方案，请联系系统管理员！");
                    return _getResMsg("003500", "操作失败，此专业未配置轮转方案，请联系系统管理员！", "");
                }
                String userFlow=shzsWebServiceBiz.saveDocInfo(form,rotation);
                if(StringUtil.isBlank(userFlow))
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", userFlow);
            }else if("Edit".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(docInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", ""); 
                }
                SysUser user=userBiz.readSysUser(docInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+docInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+docInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                int count=shzsWebServiceBiz.readDocProcess(docInfo.getUserFlow());
                if(count>0)
                {
                    logger.debug("操作失败，学员已开始进行轮转，无法修改信息，如需要修改个人信息，请使用synUserBaseInfo()！");
                    return _getResMsg("003700", "操作失败，学员已开始进行轮转，无法修改信息，如需要修改个人信息，请使用synUserBaseInfo()！", ""); 
                }
                if(StringUtil.isBlank(docInfo.getUserCode()))
                {
                    logger.debug("操作失败，用户名为空！");
                    return _getResMsg("002800", "操作失败，用户名为空！", ""); 
                }
                Boolean b = docInfo.getUserCode().matches("^[a-zA-Z]\\w{2,17}$");
                if(!b)
                {
                    logger.debug("操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！");
                    return _getResMsg("002600", "操作失败，用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线！", "");
                }
                if(StringUtil.isBlank(docInfo.getPassWord()))
                {
                    logger.debug("操作失败，密码为空！");
                    return _getResMsg("001400", "操作失败，密码为空！", "");
                }
                Boolean b1 = docInfo.getPassWord().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
                if(!b1)
                {
                    logger.debug("操作失败，密码必须包括数字、字母，长度8－20！");
                    return _getResMsg("001300", "操作失败，密码必须包括数字、字母，长度8－20！", "");
                }
                if(StringUtil.isBlank(docInfo.getUserName()))
                {
                    logger.debug("操作失败，用户姓名为空！");
                    return _getResMsg("002900", "操作失败，用户姓名为空！", "");
                }
                Boolean b2 = docInfo.getUserName().matches("^[\\u4E00-\\u9FA5]+$");
                if(!b2||docInfo.getUserName().length()>20||docInfo.getUserName().length()<2)
                {
                    logger.debug("操作失败，姓名只能是中文，长度2－20！");
                    return _getResMsg("002300", "操作失败，姓名只能是中文，长度2－20！", "");
                }
                if(StringUtil.isBlank(docInfo.getIdNo()))
                {
                    logger.debug("操作失败，证件号为空！");
                    return _getResMsg("003100", "操作失败，证件号为空！", "");
                }
                Boolean b3 = docInfo.getIdNo().matches("^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$");
                if(!b3)
                {
                    logger.debug("操作失败，无效的身份证号码！");
                    return _getResMsg("002100", "操作失败，无效的身份证号码！", ""); 
                }
                if(StringUtil.isBlank(docInfo.getUserPhone()))
                {
                    logger.debug("操作失败，手机号为空！");
                    return _getResMsg("002000", "操作失败，手机号为空！", ""); 
                }
                Boolean b4 = docInfo.getUserPhone().matches("^[1][3456789]\\d{9}$");
                if(!b4)
                {
                    logger.debug("操作失败，无效的手机号！");
                    return _getResMsg("002200", "操作失败，无效的手机号！", ""); 
                }
                //查是否存在此用户
                SysUser user1 = userBiz.findByUserCode(docInfo.getUserCode());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，用户名为【" + docInfo.getUserCode() + "】的用户，在系统中已存在！");
                    return _getResMsg("002700", "操作失败，用户名为【" + docInfo.getUserCode() + "】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByUserPhone(docInfo.getUserPhone());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，手机号为【" + docInfo.getUserPhone() + "】的用户，在系统中已存在！");
                    return _getResMsg("001900", "操作失败，手机号为【" + docInfo.getUserPhone() + "】的用户，在系统中已存在！", "");
                }
                user1 = userBiz.findByIdNo(docInfo.getIdNo());
                if (user1 != null&&!user.getUserFlow().equals(user1.getUserFlow())) {
                    logger.debug("操作失败，证件号为【" + docInfo.getIdNo() + "】的用户，在系统中已存在！");
                    return _getResMsg("003000", "操作失败，证件号为【" + docInfo.getIdNo() + "】的用户，在系统中已存在！", "");
                }
                if(StringUtil.isBlank(docInfo.getSessionNumber()))
                {
                    logger.debug("操作失败，年级为空！");
                    return _getResMsg("000900", "操作失败，年级为空！", ""); 
                }
                Boolean b5 = docInfo.getSessionNumber().matches("^(19\\d{2}|[2-9]\\d{3})$");
                if (!b5){
                    logger.debug("操作失败，年级有误，只能是1900至9999之间的年份！");
                    return _getResMsg("001000", "操作失败，年级有误，只能是1900至9999之间的年份！", "");
                }
                if(StringUtil.isBlank(docInfo.getTrainingSpeId()))
                {
                    logger.debug("操作失败，培训专业代码为空！");
                    return _getResMsg("001200", "操作失败，培训专业代码为空！", "");
                }
                if(!ShzsGlobalContent.speIds.contains(docInfo.getTrainingSpeId()))
                {
                    logger.debug("操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！");
                    return _getResMsg("001100", "操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！", "");
                }
                if(StringUtil.isBlank(docInfo.getDoctorTypeId()))
                {
                    logger.debug("操作失败，人员类型代码为空！");
                    return _getResMsg("001800", "操作失败，人员类型代码为空！", "");
                }
                if(!ShzsGlobalContent.doctorTypeIds.contains(docInfo.getDoctorTypeId()))
                {
                    logger.debug("操作失败，人员类型代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.doctorTypeIds)+"】！");
                    return _getResMsg("001700", "操作失败，人员类型代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.doctorTypeIds)+"】！", "");
                }
                if(StringUtil.isBlank(docInfo.getTrainingYears()))
                {
                    logger.debug("操作失败，培养年限为空！");
                    return _getResMsg("001600", "操作失败，培养年限为空！", "");
                }
                Boolean b6 = docInfo.getTrainingYears().matches("^(1|2|3)$");
                if (!b6){
                    logger.debug("操作失败，培养年限，只能是1或2或3！");
                    return _getResMsg("001500", "操作失败，培养年限，只能是1或2或3！", "");
                }
                SchRotation rotation=shzsWebServiceBiz.readRotationBySpeId(docInfo.getTrainingSpeId(),"Doctor");
                if(rotation==null)
                {
                    logger.debug("操作失败，此专业未配置轮转方案，请联系系统管理员！");
                    return _getResMsg("003500", "操作失败，此专业未配置轮转方案，请联系系统管理员！", "");
                }

                String userFlow=shzsWebServiceBiz.saveDocInfo(form,rotation);
                if(StringUtil.isBlank(userFlow))
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", ""); 
            }else if("Delete".equals(form.getActionType()))
            {
                if(StringUtil.isBlank(docInfo.getUserFlow()))
                {
                    logger.debug("操作失败，用户标识符为空！");
                    return _getResMsg("002500", "操作失败，用户标识符为空！", ""); 
                }
                SysUser user=userBiz.readSysUser(docInfo.getUserFlow());
                if(user==null)
                {
                    logger.debug("操作失败，用户标识符为【"+docInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                    return _getResMsg("002400", "操作失败，用户标识符为【"+docInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
                }
                int count=shzsWebServiceBiz.readDocProcess(docInfo.getUserFlow());
                if(count>0)
                {
                    logger.debug("操作失败，学员已开始进行轮转，无法删除！");
                    return _getResMsg("003600", "操作失败，学员已开始进行轮转，无法删除！", "");
                }
                int c=shzsWebServiceBiz.delDocInfo(docInfo);
                if(c==0)
                {
                    logger.debug("操作失败！");
                    return _getResMsg("003400", "操作失败！", "");
                }
                logger.debug("操作成功！");
                return _getResMsg("000000", "操作成功！", ""); 
            }

            return _getResMsg("000000", "操作成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }

    /**
     * 获取专业对应方案的标准科室 用来同步学员排班记录
     * {"token":"F5A4634CBC8240478E5952D273D007B4","trainingSpeId":"0700"}
     * @param data
     * @return
     */
    public synchronized String synRotationDeptBySpe(String data)
    {
        try{
            getClientInfo();
            logger.debug("data======="+data);
            if (StringUtil.isBlank(data)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            RotationDeptForm form=null;
            try {
                form=JSON.parseObject(data, RotationDeptForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            if(StringUtil.isBlank(form.getTrainingSpeId()))
            {
                logger.debug("操作失败，培训专业代码为空！");
                return _getResMsg("001200", "操作失败，培训专业代码为空！", "");
            }
            if(!ShzsGlobalContent.speIds.contains(form.getTrainingSpeId()))
            {
                logger.debug("操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！");
                return _getResMsg("001100", "操作失败，培训专业代码错误,只能是【"+JSON.toJSONString(ShzsGlobalContent.speIds)+"】！", "");
            }
            SchRotation rotation=shzsWebServiceBiz.readRotationBySpeId(form.getTrainingSpeId(),"Doctor");
            if(rotation==null)
            {
                logger.debug("操作失败，此专业未配置轮转方案，请联系系统管理员！");
                return _getResMsg("003500", "操作失败，此专业未配置轮转方案，请联系系统管理员！", "");
            }
            List<Map<String,String>> depts=shzsWebServiceBiz.readRotationDeptByFlow(rotation.getRotationFlow());

            logger.debug("操作成功！");
            return _getResMsg("000000", "操作成功！", depts);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }

    /**
     * 同步学员排班信息，不支持修改与删除
     * {"actionType":"Add","arrangeInfo":{"deptCode":"11111","deptId":"11111","headUserFlow":"11111","schEndDate":"","schStartDate":"","teacherUserFlow":"11111","userFlow":"11111"},"token":"F5A4634CBC8240478E5952D273D007B4"}
     * @param processData
     * @return
     */
    public synchronized String synDocProcess(String processData)
    {
        try {
            getClientInfo();
            logger.debug("processData======="+processData);
            if (StringUtil.isBlank(processData)) {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            DocArrangeForm form=null;
            try {
                form=JSON.parseObject(processData, DocArrangeForm.class);
            }catch (Exception e){
                logger.debug("操作失败，提交数据格式不正确！");
                return _getResMsg("000200", "操作失败，提交数据格式不正确！", "");
            }
            if(form==null){
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            if(StringUtil.isBlank(form.getToken()))
            {
                logger.debug("操作失败，TOKEN为空！");
                return _getResMsg("000300", "操作失败，TOKEN为空！", "");
            }
            if(!ShzsGlobalContent.SHZS_WEBSERVICE_KEY.equals(form.getToken()))
            {
                logger.debug("操作失败，TOKEN错误！");
                return _getResMsg("000400", "操作失败，TOKEN错误！", "");
            }
            if(StringUtil.isBlank(form.getActionType()))
            {
                logger.debug("操作失败，ActionType为空！");
                return _getResMsg("000500", "操作失败，ActionType为空！", "");
            }
            if(!"Add".equals(form.getActionType()))
            {
                logger.debug("操作失败，ActionType错误！");
                return _getResMsg("000700", "操作失败，ActionType错误！", "");
            }
            if(form.getArrangeInfo()==null)
            {
                logger.debug("操作失败，数据为空！");
                return _getResMsg("000100", "操作失败，数据为空！", "");
            }
            ArrangeInfo arrangeInfo=form.getArrangeInfo();
            if(StringUtil.isBlank(arrangeInfo.getUserFlow()))
            {
                logger.debug("操作失败，用户标识符为空！");
                return _getResMsg("002500", "操作失败，用户标识符为空！", "");
            }
            SysUser user=userBiz.readSysUser(arrangeInfo.getUserFlow());
            if(user==null)
            {
                logger.debug("操作失败，用户标识符为【"+arrangeInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！");
                return _getResMsg("002400", "操作失败，用户标识符为【"+arrangeInfo.getUserFlow()+"】的用户在系统中不存在，请确认用户标识符！", "");
            }
            ResDoctor doctor=doctorBiz.readDoctor(arrangeInfo.getUserFlow());
            if(doctor==null)
            {
                logger.debug("操作失败，用户标识符为【"+arrangeInfo.getUserFlow()+"】的用户在系统中不存在学员信息，请确认其是否为学员！");
                return _getResMsg("005300", "操作失败，用户标识符为【"+arrangeInfo.getUserFlow()+"】的用户在系统中不存在，请确认其是否为学员！", "");
            }
            if(StringUtil.isBlank(doctor.getRotationFlow()))
            {
                logger.debug("操作失败，此学员未分配轮转方案，请联系管理员进行调整！");
                return _getResMsg("004500", "操作失败，此学员未分配轮转方案，请联系管理员进行调整！", "");
            }
            if(StringUtil.isBlank(arrangeInfo.getDeptCode()))
            {
                logger.debug("操作失败，标准科室代码为空！");
                return _getResMsg("004400", "操作失败，标准科室代码为空！", ""); 
            }
            SchRotationDept rotationDept=rotationDeptBiz.readSchRotationDept(arrangeInfo.getDeptCode());
            if(rotationDept==null)
            {
                logger.debug("操作失败，标准科室代码为【"+arrangeInfo.getDeptCode()+"】的标准科室在系统中不存在，请确认后提交！");
                return _getResMsg("005600","操作失败，标准科室代码为【"+arrangeInfo.getDeptCode()+"】的标准科室在系统中不存在，请确认后提交！", "");
            }
            if(!rotationDept.getRotationFlow().equals(doctor.getRotationFlow()))
            {
                logger.debug("操作失败，标准科室代码为【"+arrangeInfo.getDeptCode()+"】的标准科室所在的培训方案与学员的培训方案不一致，请确认后提交！");
                return _getResMsg("005500","操作失败，标准科室代码为【"+arrangeInfo.getDeptCode()+"】的标准科室所在的培训方案与学员的培训方案不一致，请确认后提交！", "");
            }
            if(StringUtil.isBlank(arrangeInfo.getDeptId()))
            {
                logger.debug("操作失败，科室标识符为空！");
                return _getResMsg("000800", "操作失败，科室标识符为空！", "");
            }
            SysDept sysDept=shzsWebServiceBiz.readDeptById(arrangeInfo.getDeptId());
            if(sysDept==null)
            {
                logger.debug("操作失败，科室标识符为【"+arrangeInfo.getDeptId()+"】科室在系统中不存在！");
                return _getResMsg("001400", "操作失败，科室标识符为【"+arrangeInfo.getDeptId()+"】科室在系统中不存在！", "");
            }
            if(!sysDept.getOrgFlow().equals(ShzsGlobalContent.SHZS_ORG_FLOW))
            {
                logger.debug("操作失败，科室标识符为【"+arrangeInfo.getDeptId()+"】科室不是当前基地科室！");
                return _getResMsg("003200", "操作失败，科室标识符为【"+arrangeInfo.getDeptId()+"】科室不是当前基地科室！", "");
            }

            SysUser tea=userBiz.readSysUser(arrangeInfo.getTeacherUserFlow());
            if(StringUtil.isBlank(arrangeInfo.getTeacherUserFlow()))
            {
                logger.debug("操作失败，带教标识符为空！");
                return _getResMsg("004600", "操作失败，带教标识符为空！", ""); 
            }
            if(tea==null)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教在系统中不存在，请确认带教标识符！");
                return _getResMsg("004200", "操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教在系统中不存在，请确认带教标识符！", "");
            }
            SysUserRole teaRole=userRoleBiz.readUserRole(arrangeInfo.getTeacherUserFlow(), ShzsGlobalContent.SHZS_TEACHER_ROLE_FLOW);
            if(teaRole==null)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教在系统中不存在带教角色，请确认带教标识符！");
                return _getResMsg("004300", "操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教在系统中不存在带教角色，请确认带教标识符！", "");
            }
            int teaDept=shzsWebServiceBiz.checkUserDept(tea.getUserFlow(),sysDept.getDeptFlow());
            if(teaDept<=0)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教不在科室代码为【"+arrangeInfo.getDeptId()+"】的科室中，请确认带教所在科室！");
                return _getResMsg("004100", "操作失败，标识符为【"+arrangeInfo.getTeacherUserFlow()+"】的带教不在科室代码为【"+arrangeInfo.getDeptId()+"】的科室中，请确认带教所在科室！", "");
            }
            SysUser head=userBiz.readSysUser(arrangeInfo.getHeadUserFlow());
            if(StringUtil.isBlank(arrangeInfo.getHeadUserFlow()))
            {
                logger.debug("操作失败，主任标识符为空！");
                return _getResMsg("005400", "操作失败，主任标识符为空！", "");
            }
            if(head==null)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任在系统中不存在，请确认主任标识符！");
                return _getResMsg("003900", "操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任在系统中不存在，请确认主任标识符！", "");
            }
            SysUserRole headRole=userRoleBiz.readUserRole(arrangeInfo.getHeadUserFlow(), ShzsGlobalContent.SHZS_HEAD_ROLE_FLOW);
            if(headRole==null)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任在系统中不存在带教角色，请确认主任标识符！");
                return _getResMsg("004000", "操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任在系统中不存在带教角色，请确认主任标识符！", ""); 
            }
            int headDept=shzsWebServiceBiz.checkUserDept(head.getUserFlow(),sysDept.getDeptFlow());
            if(headDept<=0)
            {
                logger.debug("操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任不在科室代码为【"+arrangeInfo.getDeptId()+"】的科室中，请确认主任所在科室！");
                return _getResMsg("003800", "操作失败，标识符为【"+arrangeInfo.getHeadUserFlow()+"】的主任不在科室代码为【"+arrangeInfo.getDeptId()+"】的科室中，请确认主任所在科室！", ""); 
            }
            String schStartDate=arrangeInfo.getSchStartDate();
            String schEndDate=arrangeInfo.getSchEndDate();
            if(StringUtil.isBlank(schStartDate))
            {
                logger.debug("操作失败，轮转开始时间为空！");
                return _getResMsg("005100", "操作失败，轮转开始时间为空！", "");
            }
            try
            {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat2.parse(schStartDate);
            }
            catch (Exception e)
            {
                logger.debug("操作失败，轮转开始时间格式错误，正确格式为yyyy-MM-dd！");
                return _getResMsg("005000", "操作失败，轮转开始时间格式错误，正确格式为yyyy-MM-dd！", "");
            }
            if(StringUtil.isBlank(schEndDate))
            {
                logger.debug("操作失败，轮转结束时间为空！");
                return _getResMsg("004900", "操作失败，轮转结束时间为空！", ""); 
            }
            try
            {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat2.parse(schEndDate);
            }
            catch (Exception e)
            {
                logger.debug("操作失败，轮转结束时间格式错误，正确格式为yyyy-MM-dd！");
                return _getResMsg("004800", "操作失败，轮转结束时间格式错误，正确格式为yyyy-MM-dd！", "");
            }
            if(schStartDate.compareTo(schEndDate)>0)
            {
                logger.debug("操作失败，开始时间不得大于结束时间！");
                return _getResMsg("004700", "操作失败，开始时间不得大于结束时间！", "");
            }
            List<SchArrangeResult> resultList = shzsWebServiceBiz.checkResultDate(arrangeInfo.getUserFlow(),schStartDate,schEndDate,doctor.getRotationFlow());
            if(resultList!=null && !resultList.isEmpty()){
                logger.debug("操作失败，轮转时间与其他科室轮转时间重叠！");
                return _getResMsg("005200", "操作失败，轮转时间与其他科室轮转时间重叠！", "");
            }

           int c= shzsWebServiceBiz.editDoctorResult(user,rotationDept,sysDept,schStartDate,schEndDate,tea,head);
            if(c==0)
            {
                logger.debug("操作失败！");
                return _getResMsg("003400", "操作失败！", "");
            }


            return _getResMsg("000000", "操作成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000600", "操作失败，出现异常！"+e.getMessage(),null);
        }
    }
    private String _getResMsg(String resultCode, String resultMsg, Object qty){
        ResultInfo info=new ResultInfo();
        info.setResultCode(resultCode);
        info.setResultMsg(resultMsg);
        info.setResponse(qty);
        logger.error("操作返回结果："+JSON.toJSONString(info));
        return JSON.toJSONString(info);
    }


    public static void main(String[] args) {
        DeptInfoForm deptInfoForm=new DeptInfoForm();
        deptInfoForm.setActionType("Add");
        deptInfoForm.setToken(ShzsGlobalContent.SHZS_WEBSERVICE_KEY);
        DeptInfo deptInfo=new DeptInfo();
        deptInfo.setDeptId("123456");
        deptInfo.setDeptName("呼吸内科");
        deptInfoForm.setDeptInfo(deptInfo);

        UserInfoForm userInfoForm=new UserInfoForm();
        userInfoForm.setActionType("Add");
        userInfoForm.setToken(ShzsGlobalContent.SHZS_WEBSERVICE_KEY);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserCode("123456");
        userInfo.setUserName("带教老师");
        userInfo.setUserPhone("15601582206");
        userInfo.setIdNo("320684199010129382");
        userInfoForm.setUserInfo(userInfo);
        List<String> roles=new ArrayList();
        roles.add("Teacher");
        roles.add("Head");
        roles.add("Admin");
        userInfoForm.setRoles(roles);
        List<String> deptIds=new ArrayList();
        deptIds.add("123456");
        userInfoForm.setDeptIds(deptIds);

        DocInfoForm docInfoForm=new DocInfoForm();
        docInfoForm.setActionType("Add");
        docInfoForm.setToken(ShzsGlobalContent.SHZS_WEBSERVICE_KEY);
        DocInfo docInfo=new DocInfo();
        docInfo.setUserCode("123456");
        docInfo.setUserName("带教老师");
        docInfo.setUserPhone("15601582206");
        docInfo.setIdNo("320684199010129382");
        docInfo.setSessionNumber("2018");
        docInfo.setTrainingYears("d");
        docInfo.setTrainingSpeId("d");
        docInfo.setDoctorTypeId("Company");
        docInfoForm.setUserInfo(docInfo);
        DocArrangeForm docArrangeForm=new DocArrangeForm();
        docArrangeForm.setActionType("Add");
        docArrangeForm.setToken(ShzsGlobalContent.SHZS_WEBSERVICE_KEY);
        ArrangeInfo arrangeInfo=new ArrangeInfo();
        arrangeInfo.setUserFlow("11111");
        arrangeInfo.setTeacherUserFlow("11111");
        arrangeInfo.setHeadUserFlow("11111");
        arrangeInfo.setDeptId("11111");
        arrangeInfo.setDeptCode("11111");
        arrangeInfo.setSchStartDate("");
        arrangeInfo.setSchEndDate("");
        docArrangeForm.setArrangeInfo(arrangeInfo);

        RotationDeptForm deptForm=new RotationDeptForm();
        deptForm.setToken(ShzsGlobalContent.SHZS_WEBSERVICE_KEY);
        deptForm.setTrainingSpeId("0700");
        System.out.println(JSON.toJSONString(docArrangeForm));

    }

}