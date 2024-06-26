package com.pinde.sci.form.fstu;

import com.pinde.sci.model.mo.FstuAcademicActivity;
import com.pinde.sci.model.mo.PubFile;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-12.
 */
public class FstuAcademicActivityAndFileForm {

    private FstuAcademicActivity academicActivity;
    private List<PubFile> fileList;

    public FstuAcademicActivity getAcademicActivity() {
        return academicActivity;
    }

    public void setAcademicActivity(FstuAcademicActivity academicActivity) {
        this.academicActivity = academicActivity;
    }

    public List<PubFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PubFile> fileList) {
        this.fileList = fileList;
    }
}
