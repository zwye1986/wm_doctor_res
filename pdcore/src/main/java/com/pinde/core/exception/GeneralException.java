package com.pinde.core.exception;

/**
 * 框架服务层异常
 * 
 * @author shijian
 * @create 
 */
public class GeneralException extends Exception {

	public GeneralException(Exception e) {
		super(e);
	}

	public GeneralException(String msg) {
		super(msg);
	}

	public GeneralException(String msg, Throwable t) {
		super(msg, t);
	}

}
