package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchArrangeDoctorBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchArrangeDoctorMapper;
import com.pinde.sci.model.mo.SchArrangeDoctor;
import com.pinde.sci.model.mo.SchArrangeDoctorExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchArrangeDoctorBizImpl implements ISchArrangeDoctorBiz {
	@Autowired
	private SchArrangeDoctorMapper arrangeDoctorMapper;
	
	@Override
	public List<SchArrangeDoctor> searchSchArrangeDoctor() {
		SchArrangeDoctorExample example = new SchArrangeDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return arrangeDoctorMapper.selectByExample(example);
	}

	@Override
	public SchArrangeDoctor readSchArrangeDoctor(String arrDocFlow) {
		return arrangeDoctorMapper.selectByPrimaryKey(arrDocFlow);
	}

	@Override
	public int saveSchArrangeDoctor(SchArrangeDoctor arrangeDoctor) {
		if(arrangeDoctor != null){
			if(StringUtil.isNotBlank(arrangeDoctor.getArrDocFlow())){
				GeneralMethod.setRecordInfo(arrangeDoctor, false);
				return arrangeDoctorMapper.updateByPrimaryKeySelective(arrangeDoctor);
			}else{
				arrangeDoctor.setArrDocFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(arrangeDoctor, true);
				return arrangeDoctorMapper.insertSelective(arrangeDoctor);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
}
