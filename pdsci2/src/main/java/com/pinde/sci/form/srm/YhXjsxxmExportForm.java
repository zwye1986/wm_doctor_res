package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.PubProj;

import java.io.Serializable;

public class YhXjsxxmExportForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961012350772935758L;	
	
	private PubProj proj;
	private YhXjsxxmForm projInfoForm;
	
	public PubProj getProj() {
		return proj;
	}
	public void setProj(PubProj proj) {
		this.proj = proj;
	}
	public YhXjsxxmForm getProjInfoForm() {
		return projInfoForm;
	}
	public void setProjInfoForm(YhXjsxxmForm projInfoForm) {
		this.projInfoForm = projInfoForm;
	}

	
}
