package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResReg;

import java.util.List;

public interface IResRegBiz {

//	List<ResReg> searchResReg();

	ResReg searchResReg(String userFlow, String regYear);

	int editResReg(ResReg reg);
	
	/**
	 * 查询学员最新的注册记录
	 * @param userFlow
	 * @return
	 */
	ResReg searchRecentYearResReg(String userFlow);

	/**
	 * 查询学员所有注册记录
	 * @param userFlow
	 * @return
     */
	List<ResReg> searchAllResReg(String userFlow);
}
