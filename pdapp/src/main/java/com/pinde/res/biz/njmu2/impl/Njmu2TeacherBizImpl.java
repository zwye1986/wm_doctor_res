package com.pinde.res.biz.njmu2.impl;

import java.util.List;
import java.util.Map;

import com.pinde.app.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.INjmu2TeacherBiz;
import com.pinde.res.dao.stdp.ext.StdpResDoctorExtMapper;
import com.pinde.res.enums.stdp.RecStatusEnum;

@Service
@Transactional(rollbackFor=Exception.class)
public class Njmu2TeacherBizImpl implements INjmu2TeacherBiz{
	
	@Autowired
	private StdpResDoctorExtMapper docExtMapper;
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
	}

}
