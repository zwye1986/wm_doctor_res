package com.pinde.sci.biz.sch.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchArrangePeriodRelBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchArrangePeriodRelMapper;
import com.pinde.sci.model.mo.SchArrangePeriodRel;
import com.pinde.sci.model.mo.SchArrangePeriodRelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchArrangePeriodRelBizImpl implements ISchArrangePeriodRelBiz {
	@Autowired
	private SchArrangePeriodRelMapper periodRelMapper;

	@Override
	public List<SchArrangePeriodRel> searchPeriodRelByDoctorFlow(String doctorFlow) {
		SchArrangePeriodRelExample example = new SchArrangePeriodRelExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow);
		return periodRelMapper.selectByExample(example);
	}

	@Override
	public int insert(SchArrangePeriodRel periodRel,String doctorFlow) {
		int result = 0;
		if(StringUtil.isBlank(periodRel.getRecordFlow())){//添加
			periodRel.setDoctorFlow(doctorFlow);
			periodRel.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(periodRel,true);
			result = periodRelMapper.insertSelective(periodRel);
		}else{
			GeneralMethod.setRecordInfo(periodRel,false);
			result = periodRelMapper.updateByPrimaryKeySelective(periodRel);
		}

		return result;
	}

	@Override
	public SchArrangePeriodRel searchPeriodRel(String recordFlow) {
		return periodRelMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int delete(String recordFlow) {
		int result = 0;
		result = periodRelMapper.deleteByPrimaryKey(recordFlow);
		return result;
	}

	@Override
	public int deleteByDoctorFlow(String doctorFlow) {
		int result = 0;
		SchArrangePeriodRelExample example = new SchArrangePeriodRelExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow);
		result = periodRelMapper.deleteByExample(example);
		return result;
	}
}
