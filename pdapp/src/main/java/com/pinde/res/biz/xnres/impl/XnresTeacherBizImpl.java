package com.pinde.res.biz.xnres.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.xnres.IXnresTeacherBiz;
import com.pinde.res.dao.xnres.ext.XnresDoctorExtMapper;
import com.pinde.res.enums.stdp.RecStatusEnum;
import com.pinde.sci.dao.base.ResRecMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.model.mo.ResRec;
import com.pinde.sci.model.mo.ResRecExample;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class XnresTeacherBizImpl implements IXnresTeacherBiz {
	
	@Autowired
	private XnresDoctorExtMapper docExtMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private ResRecMapper recMapper;
	
	//获取带过的学员列表
	@Override
	public List<Map<String,Object>> getDocListByTeacher(Map<String,Object> paramMap){
		return docExtMapper.getDocListByTeacher(paramMap);
	}
	
	//审核一条登记数据//默认审核通过
    @Override
    public void auditDate(String userFlow,String dataFlow){
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(dataFlow)){
            ResRec rec = new ResRec();
            rec.setRecFlow(dataFlow);
            
            rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
            rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
            rec.setAuditUserFlow(userFlow);
            SysUser user = userMapper.selectByPrimaryKey(userFlow);
            if(user!=null){
                rec.setAuditUserName(user.getUserName());
            }
            rec.setAuditTime(DateUtil.getCurrDateTime());
            
            recMapper.updateByPrimaryKeySelective(rec);
        }
    }//审核一条登记数据//默认审核通过
    @Override
    public int auditRecDate(String userFlow,String dataFlow,String statusId){
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(dataFlow)){
            ResRec rec = new ResRec();
            rec.setRecFlow(dataFlow);
            if(statusId.equals("Y")) {
                rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
                rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
            }else{
                rec.setAuditStatusId(RecStatusEnum.TeacherAuditN.getId());
                rec.setAuditStatusName(RecStatusEnum.TeacherAuditN.getName());
            }
            rec.setAuditUserFlow(userFlow);
            SysUser user = userMapper.selectByPrimaryKey(userFlow);
            if(user!=null){
                rec.setAuditUserName(user.getUserName());
            }
            rec.setAuditTime(DateUtil.getCurrDateTime());

            return recMapper.updateByPrimaryKeySelective(rec);
        }
        return 0;
    }

    @Override
    public List<Map<String, String>> schDoctorSchProcessQuery(Map<String, Object> schArrangeResultMap) {
        return docExtMapper.schDoctorSchProcessQuery(schArrangeResultMap);
    }

    @Override
    public List<Map<String, String>> schDoctorSchProcessInfoQuery(Map<String, Object> schArrangeResultMap) {
        return docExtMapper.schDoctorSchProcessInfoQuery(schArrangeResultMap);
    }

    @Override
    public List<Map<String, Object>> findDataNoAudit(Map<String, Object> param) {
        return docExtMapper.findDataNoAudit(param);
    }

    @Override
    public List<ResRec> getDocRecs(String processFlow, String userFlow, List<String> typeId) {
        ResRecExample example=new  ResRecExample();
        ResRecExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(userFlow);
        if(typeId!=null&&typeId.size()>0)
                criteria.andRecTypeIdIn(typeId);
        return recMapper.selectByExample(example);
    }
    @Override
    public List<ResRec> searchRecByProcessAndRecType(String processFlow,String doctorFlow,String recType,String biaoJi) {
        ResRecExample example = new ResRecExample();
        ResRecExample.Criteria criteria=    example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow)
                .andRecTypeIdEqualTo(recType);
        if(StringUtil.isNotBlank(biaoJi))
            criteria.andAuditStatusIdIsNull();
        example.setOrderByClause("OPER_TIME");
        return recMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public ResRec readResRec(String recFlow) {
        return recMapper.selectByPrimaryKey(recFlow);
    }

    @Override
    public List<Map<String, Object>> getDocCapDatas(String id, String processFlow) {
        return docExtMapper.getDocCapDatas(id,processFlow);
    }
}
