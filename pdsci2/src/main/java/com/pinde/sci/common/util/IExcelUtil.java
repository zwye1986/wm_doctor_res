package com.pinde.sci.common.util;

public interface IExcelUtil<T> {
	void operExcelData(ExcelUtile eu);
	String checkExcelData(T t, ExcelUtile eu);

}
