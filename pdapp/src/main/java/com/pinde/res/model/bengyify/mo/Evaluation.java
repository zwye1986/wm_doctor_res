package com.pinde.res.model.bengyify.mo;

import java.io.Serializable;

/**
 * 自我评价
 * @author Administrator
 *
 */
public class Evaluation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int dataFlow;
	
	private String userFlow;
	
	private String schDeptFlow;
	
	//出科状态 1:已出科 0:未出科
	private String checkStatus;
	
	//自我评价内容
	private String briefRequrie;
	
	//带教审核意见
	private String secAppraise;
	
	//轮转Id
	private String cySecId;
	
	//审核状态默认为0
	private String verifyState;

	public int getDataFlow() {
		return dataFlow;
	}

	public void setDataFlow(int dataFlow) {
		this.dataFlow = dataFlow;
	}

	public String getUserFlow() {
		return userFlow;
	}

	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}

	public String getSchDeptFlow() {
		return schDeptFlow;
	}

	public void setSchDeptFlow(String schDeptFlow) {
		this.schDeptFlow = schDeptFlow;
	}

	public String getBriefRequrie() {
		return briefRequrie;
	}

	public void setBriefRequrie(String briefRequrie) {
		this.briefRequrie = briefRequrie;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getSecAppraise() {
		return secAppraise;
	}

	public void setSecAppraise(String secAppraise) {
		this.secAppraise = secAppraise;
	}

	public String getCySecId() {
		return cySecId;
	}

	public void setCySecId(String cySecId) {
		this.cySecId = cySecId;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	
	

}
