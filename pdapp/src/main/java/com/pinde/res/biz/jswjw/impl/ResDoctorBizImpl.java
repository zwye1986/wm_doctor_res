package com.pinde.res.biz.jswjw.impl;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IResDoctorBiz;
import com.pinde.core.common.sci.dao.ResDoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorBizImpl implements IResDoctorBiz {
	@Autowired
	private ResDoctorMapper doctorMapper;

	@Override
	public ResDoctor readDoctor(String recordFlow){
		return doctorMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int editDoctor(ResDoctor doctor) {
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				return doctorMapper.updateByPrimaryKeySelective(doctor);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

}
 