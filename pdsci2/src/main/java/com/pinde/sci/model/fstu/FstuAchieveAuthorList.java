package com.pinde.sci.model.fstu;

import com.pinde.sci.model.mo.FstuAchieve;
import com.pinde.sci.model.mo.FstuAchieveAuthor;
import com.pinde.sci.model.mo.PubFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public class FstuAchieveAuthorList implements Serializable {
    private List<FstuAchieveAuthor> authorList;
    private List<PubFile> pubFileList;
    private FstuAchieve achieve;

    public List<FstuAchieveAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<FstuAchieveAuthor> authorList) {
        this.authorList = authorList;
    }

    public List<PubFile> getPubFileList() {
        return pubFileList;
    }

    public void setPubFileList(List<PubFile> pubFileList) {
        this.pubFileList = pubFileList;
    }

    public FstuAchieve getAchieve() {
        return achieve;
    }

    public void setAchieve(FstuAchieve achieve) {
        this.achieve = achieve;
    }
}
