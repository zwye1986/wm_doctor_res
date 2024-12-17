package com.pinde.res.biz.jswjw;

import com.pinde.core.model.ResDoctor;

/**
 * @author tiger
 *
 */
public interface IResDoctorBiz {
	ResDoctor readDoctor(String recordFlow);

	int editDoctor(ResDoctor doctor);
}
