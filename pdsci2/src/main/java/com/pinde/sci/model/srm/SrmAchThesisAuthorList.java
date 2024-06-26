package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmAchFile;
import com.pinde.sci.model.mo.SrmAchThesis;
import com.pinde.sci.model.mo.SrmAchThesisAuthor;

import java.io.Serializable;
import java.util.List;

public class SrmAchThesisAuthorList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4915304753740456768L;

	private List<SrmAchThesisAuthor> authorList;
    
	private SrmAchThesis thesis;
	
	private List<SrmAchFile> srmAchFileList;
	
	public List<SrmAchThesisAuthor> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<SrmAchThesisAuthor> authorList) {
		this.authorList = authorList;
	}

	public SrmAchThesis getThesis() {
		return thesis;
	}

	public void setThesis(SrmAchThesis thesis) {
		this.thesis = thesis;
	}

	public List<SrmAchFile> getSrmAchFileList() {
		return srmAchFileList;
	}

	public void setSrmAchFileList(List<SrmAchFile> srmAchFileList) {
		this.srmAchFileList = srmAchFileList;
	}
}
