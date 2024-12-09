package com.pinde.res.biz.jswjw.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.dao.jswjw.ext.ResDoctorSchProcessExtMapper;
import com.pinde.sci.dao.base.ResDoctorSchProcessMapper;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResDoctorSchProcessExample;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorProcessBizImpl implements IResDoctorProcessBiz {

	@Autowired
	private ResDoctorSchProcessMapper  resDoctorProcessMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;

	@Override
	public int edit(ResDoctorSchProcess process,SysUser user) {
		if(process!=null){
			if(StringUtil.isNotBlank(process.getProcessFlow())){//修改
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				process.setModifyUserFlow(user.getUserFlow());
				process.setModifyTime(DateUtil.getCurrDateTime());
				return this.resDoctorProcessMapper.updateByPrimaryKeySelective(process);
			}else{//新增
				process.setProcessFlow(PkUtil.getUUID());
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				process.setCreateUserFlow(user.getUserFlow());
				process.setCreateTime(DateUtil.getCurrDateTime());
				process.setModifyUserFlow(user.getUserFlow());
				process.setModifyTime(DateUtil.getCurrDateTime());
				return this.resDoctorProcessMapper.insertSelective(process);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorSchProcess read(String processFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
		}
		return process;
	}
	
	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow);
		return resDoctorProcessMapper.selectByExample(example);
	}
	
	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctor doctor,ResDoctorSchProcess process,Map<String,String> roleFlagMap){
		return resDoctorProcessExtMapper.searchProcessByDoctor(doctor, process,roleFlagMap);
	}

	@Override
	public List<Map<String,String>>  schDoctorSchProcessQuery(
			Map<String, String> map) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessQuery(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public int schProcessStudentDistinctQuery(String deptFlow, String userFlow, String isSch) {
		return resDoctorProcessExtMapper.schProcessStudentDistinctQuery(deptFlow,userFlow,isSch);
	}

	@Override
	public int checkProcessEval(String processFlow) {
		return resDoctorProcessExtMapper.checkProcessEval(processFlow);
	}
}
