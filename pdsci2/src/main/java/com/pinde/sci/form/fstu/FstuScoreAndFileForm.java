package com.pinde.sci.form.fstu;

import com.pinde.sci.model.mo.FstuScoreMain;
import com.pinde.sci.model.mo.PubFile;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-12.
 */
public class FstuScoreAndFileForm {
    //我的学分
    private FstuScoreMain score;
    //我的附件
    private List<PubFile> fileList;

    public FstuScoreMain getScore() {
        return score;
    }

    public void setScore(FstuScoreMain score) {
        this.score = score;
    }

    public List<PubFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PubFile> fileList) {
        this.fileList = fileList;
    }
}
