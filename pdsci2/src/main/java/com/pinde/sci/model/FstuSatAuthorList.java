package com.pinde.sci.model;

import com.pinde.sci.model.mo.*;

import java.io.Serializable;
import java.util.List;

public class FstuSatAuthorList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2615577822625938959L;
	private List<FstuSatAuthor> authorList;
	private List<PubFile> fileList;
	private FstuSat sat;

	public List<FstuSatAuthor> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<FstuSatAuthor> authorList) {
		this.authorList = authorList;
	}

	public List<PubFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<PubFile> fileList) {
		this.fileList = fileList;
	}

	public FstuSat getSat() {
		return sat;
	}

	public void setSat(FstuSat sat) {
		this.sat = sat;
	}
}
