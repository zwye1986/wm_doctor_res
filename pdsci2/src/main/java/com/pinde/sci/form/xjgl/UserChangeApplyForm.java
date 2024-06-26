package com.pinde.sci.form.xjgl;

import com.pinde.sci.model.mo.EduUserChangeApply;

import java.io.Serializable;

public class UserChangeApplyForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9008721416716877199L;
	private EduUserChangeApply apply;
	
	public EduUserChangeApply getApply() {
		return apply;
	}
	public void setApply(EduUserChangeApply apply) {
		this.apply = apply;
	}	
}
