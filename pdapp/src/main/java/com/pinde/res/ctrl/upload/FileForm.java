package com.pinde.res.ctrl.upload;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
