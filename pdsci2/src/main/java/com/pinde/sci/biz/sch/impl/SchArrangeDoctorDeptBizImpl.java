package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.sci.dao.SchArrangeDoctorDeptMapper;
import com.pinde.core.model.SchArrangeDoctorDept;
import com.pinde.core.model.SchArrangeDoctorDeptExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchArrangeDoctorDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchArrangeDoctorDeptBizImpl implements ISchArrangeDoctorDeptBiz {
	@Autowired
	private SchArrangeDoctorDeptMapper arrangeDoctorDeptMapper;
	
	@Override
	public List<SchArrangeDoctorDept> searchSchArrangeDoctorDept() {
		SchArrangeDoctorDeptExample example = new SchArrangeDoctorDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return arrangeDoctorDeptMapper.selectByExample(example);
	}

	@Override
	public SchArrangeDoctorDept readySchArrangeDoctorDept(String arrDocDeptFlow) {
		return arrangeDoctorDeptMapper.selectByPrimaryKey(arrDocDeptFlow);
	}

	@Override
	public int saveSchArrangeDoctorDept(SchArrangeDoctorDept arrangeDoctorDept) {
		if(arrangeDoctorDept != null){
			if(StringUtil.isNotBlank(arrangeDoctorDept.getArrDocDeptFlow())){
				GeneralMethod.setRecordInfo(arrangeDoctorDept, false);
				return arrangeDoctorDeptMapper.updateByPrimaryKeySelective(arrangeDoctorDept);
			}else{
				arrangeDoctorDept.setArrDocDeptFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(arrangeDoctorDept, true);
				return arrangeDoctorDeptMapper.insertSelective(arrangeDoctorDept);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
}
