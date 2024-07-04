package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResDoctor;

import java.util.List;

/**
 * @author tiger
 *
 */
public interface IResDoctorBiz {
	List<ResDoctor> searchDoctor();
	ResDoctor readDoctor(String recordFlow);
	List<ResDoctor> searchByDoc(ResDoctor doctor);

	int editDoctor(ResDoctor doctor);
}
