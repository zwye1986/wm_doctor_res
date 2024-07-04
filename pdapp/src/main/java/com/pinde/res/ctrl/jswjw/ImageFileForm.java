package com.pinde.res.ctrl.jswjw;

import org.springframework.web.multipart.MultipartFile;

public class ImageFileForm {
	private String userFlow;
	private String deptFlow;
	private String fileName;
	private String uploadFileData;
	private String imageContent;
	private MultipartFile uploadFile;

	private String recordFlow;
	
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
	public String getDeptFlow() {
		return deptFlow;
	}
	public void setDeptFlow(String deptFlow) {
		this.deptFlow = deptFlow;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageContent() {
		return imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}

	public String getRecordFlow() {
		return recordFlow;
	}

	public void setRecordFlow(String recordFlow) {
		this.recordFlow = recordFlow;
	}
}
