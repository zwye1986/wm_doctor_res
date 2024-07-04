package com.pinde.res.model.jszy.mo;

/**
 * Created by Administrator on 2017/12/22.
 */
public class UploadFileForm {
    private String fileFlow;
    private String fileName;
    private String filePath;
    private String content;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
