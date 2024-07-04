package com.pinde.res.ctrl.qpres;

import org.springframework.web.multipart.MultipartFile;

public class LectureImageFileForm {
	private String userFlow;
	private String registFlow;
	private String fileName;
	private String uploadFileData;
	private MultipartFile uploadFile;
	
	public String getUploadFileData() {
		return uploadFileData;
	}
	public void setUploadFileData(String uploadFileData) {
		this.uploadFileData = uploadFileData;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUserFlow() {
		return userFlow;
	}
	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRegistFlow() {
		return registFlow;
	}

	public void setRegistFlow(String registFlow) {
		this.registFlow = registFlow;
	}
}
