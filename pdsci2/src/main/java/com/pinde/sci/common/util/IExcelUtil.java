package com.pinde.sci.common.util;

import java.util.Map;


public interface IExcelUtil<T> {
	void operExcelData(ExcelUtile eu);
	String checkExcelData(T t, ExcelUtile eu);

}
