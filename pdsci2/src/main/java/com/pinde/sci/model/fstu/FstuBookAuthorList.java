package com.pinde.sci.model.fstu;


import com.pinde.sci.model.mo.FstuBook;
import com.pinde.sci.model.mo.FstuBookAuthor;
import com.pinde.sci.model.mo.PubFile;

import java.io.Serializable;
import java.util.List;

public class FstuBookAuthorList implements Serializable {

    private List<FstuBookAuthor> authorList;
    private List<PubFile> fileList;
    private FstuBook book;

    public List<FstuBookAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<FstuBookAuthor> authorList) {
        this.authorList = authorList;
    }

    public List<PubFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PubFile> fileList) {
        this.fileList = fileList;
    }

    public FstuBook getBook() {
        return book;
    }

    public void setBook(FstuBook book) {
        this.book = book;
    }
}