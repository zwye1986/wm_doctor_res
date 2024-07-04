package com.pinde.res.ctrl.jswjw;

import org.springframework.web.multipart.MultipartFile;

public class ActivityImageFileForm {
	private String userFlow;
	private String activityFlow;
	private String fileName;
	private String uploadFileData;
	private MultipartFile uploadFile;

	private String imageContent;
	
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

	public String getActivityFlow() {
		return activityFlow;
	}

	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}

	public String getImageContent() {
		return imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}
}
