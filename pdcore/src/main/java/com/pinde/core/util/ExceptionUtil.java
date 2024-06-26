package com.pinde.core.util;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ExceptionUtil {
	
	public static String getStackTrace(Throwable throwable) {
		return ExceptionUtils.getFullStackTrace(throwable);
	}

}
