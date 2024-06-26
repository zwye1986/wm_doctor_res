package com.pinde.sci.model.fstu;

import com.pinde.sci.model.mo.FstuThesis;
import com.pinde.sci.model.mo.FstuThesisAuthor;
import com.pinde.sci.model.mo.PubFile;

import java.io.Serializable;
import java.util.List;

public class FstuThesisAuthorList implements Serializable{
    private List<FstuThesisAuthor> authorList;
    private List<PubFile> fileList;
    private FstuThesis thesis;

    public FstuThesis getThesis() {
        return thesis;
    }

    public void setThesis(FstuThesis thesis) {
        this.thesis = thesis;
    }

    public List<PubFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PubFile> fileList) {
        this.fileList = fileList;
    }

    public List<FstuThesisAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<FstuThesisAuthor> authorList) {
        this.authorList = authorList;
    }
}
