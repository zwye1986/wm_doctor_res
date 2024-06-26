package com.pinde.sci.model.edu;

import com.pinde.sci.model.mo.EduAnswer;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.SysUser;

public class EduAnswerExt extends EduAnswer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1424284595479833659L;
	private SysUser user;
	private EduUser eduUser;
	private EduQuestionExt questionExt;
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public EduUser getEduUser() {
		return eduUser;
	}
	public void setEduUser(EduUser eduUser) {
		this.eduUser = eduUser;
	}
	public EduQuestionExt getQuestionExt() {
		return questionExt;
	}
	public void setQuestionExt(EduQuestionExt questionExt) {
		this.questionExt = questionExt;
	}
}
