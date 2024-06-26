package com.pinde.sci.model.fstu;

import com.pinde.sci.model.mo.FstuPatent;
import com.pinde.sci.model.mo.FstuPatentAuthor;
import com.pinde.sci.model.mo.PubFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public class FstuPatentAuthorList implements Serializable {
    private List<FstuPatentAuthor> authorList;
    private List<PubFile> pubFileList;
    private FstuPatent patent;

    public List<FstuPatentAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<FstuPatentAuthor> authorList) {
        this.authorList = authorList;
    }

    public List<PubFile> getPubFileList() {
        return pubFileList;
    }

    public void setPubFileList(List<PubFile> pubFileList) {
        this.pubFileList = pubFileList;
    }

    public FstuPatent getPatent() {
        return patent;
    }

    public void setPatent(FstuPatent patent) {
        this.patent = patent;
    }
}
