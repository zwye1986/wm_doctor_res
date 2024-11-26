package com.pinde.res.biz.jswjw.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IResDoctorBiz;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorExample;
import com.pinde.core.model.ResDoctorExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorBizImpl implements IResDoctorBiz {
	@Autowired
	private ResDoctorMapper doctorMapper;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public	List<ResDoctor> searchDoctor(){
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public ResDoctor readDoctor(String recordFlow){
		return doctorMapper.selectByPrimaryKey(recordFlow);
	}


	@Override
	public List<ResDoctor> searchByDoc(ResDoctor doctor){
		ResDoctorExample example = new ResDoctorExample();
		Criteria criteria = example.createCriteria();
		setCriteria(doctor,criteria);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public int editDoctor(ResDoctor doctor) {
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				return doctorMapper.updateByPrimaryKeySelective(doctor);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	private Criteria setCriteria(ResDoctor doctor, Criteria criteria){
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getSessionNumber())){
				criteria.andSessionNumberEqualTo(doctor.getSessionNumber());
			}
			if(StringUtil.isNotBlank(doctor.getTrainingSpeId())){
				criteria.andTrainingSpeIdEqualTo(doctor.getTrainingSpeId());
			}
			if(StringUtil.isNotBlank(doctor.getGraduatedId())){
				criteria.andGraduatedIdEqualTo(doctor.getGraduatedId());
			}
			if(StringUtil.isNotBlank(doctor.getRecordStatus())){
				criteria.andRecordStatusEqualTo(doctor.getRecordStatus());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				criteria.andDoctorCategoryIdEqualTo(doctor.getDoctorCategoryId());
			}
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				criteria.andOrgFlowEqualTo(doctor.getOrgFlow());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorName())){
				criteria.andDoctorNameEqualTo(doctor.getDoctorName());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(doctor.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(doctor.getSchStatusId())){
				criteria.andSchStatusIdEqualTo(doctor.getSchStatusId());
			}
			if(StringUtil.isNotBlank(doctor.getDeptFlow())){
				criteria.andDeptFlowEqualTo(doctor.getDeptFlow());
			}
			if(StringUtil.isNotBlank(doctor.getGroupId())){
				criteria.andGroupIdEqualTo(doctor.getGroupId());
			}
			if(StringUtil.isNotBlank(doctor.getSelDeptFlag())){
				criteria.andSelDeptFlagEqualTo(doctor.getSelDeptFlag());
			}
			if(StringUtil.isNotBlank(doctor.getSchFlag())){
				criteria.andSchFlagEqualTo(doctor.getSchFlag());
			}
		}
		return criteria;
	}
	

}
 