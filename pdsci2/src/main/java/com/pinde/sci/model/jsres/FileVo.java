package com.pinde.sci.model.jsres;

import java.io.Serializable;

/**
 * @创建人 zsq
 * @创建时间 2021/6/2
 * 描述
 */
public class FileVo implements java.io.Serializable {
    private String fileResult;

    private String filePath;

    public String getFileResult() {
        return fileResult;
    }

    public void setFileResult(String fileResult) {
        this.fileResult = fileResult;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
