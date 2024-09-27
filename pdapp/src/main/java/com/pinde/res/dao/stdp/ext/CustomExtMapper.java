package com.pinde.res.dao.stdp.ext;

import com.pinde.sci.model.mo.ResDoctor;
import org.apache.ibatis.annotations.Param;

public interface CustomExtMapper {
	/**
	 * 获取该教师的所有学员
	 * @param doctorFlow
	 * @return
	 */
	ResDoctor getDoctorByFlow(@Param(value="doctorFlow") String doctorFlow);
}
