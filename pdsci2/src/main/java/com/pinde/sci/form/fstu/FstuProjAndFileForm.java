package com.pinde.sci.form.fstu;

import com.pinde.sci.model.mo.FstuProj;
import com.pinde.sci.model.mo.PubFile;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-10.
 */
public class FstuProjAndFileForm {
    private FstuProj fstuProj;
    private List<PubFile> fileList;

    public List<PubFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PubFile> fileList) {
        this.fileList = fileList;
    }

    public FstuProj getFstuProj() {
        return fstuProj;
    }

    public void setFstuProj(FstuProj fstuProj) {
        this.fstuProj = fstuProj;
    }
}
