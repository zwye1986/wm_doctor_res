package com.pinde.sci.form.sch;

import java.io.Serializable;

public class RosterTime implements Serializable{

	private String index;
	private String schMonth;

	private int si;
	private int ei;

	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public int getEi() {
		return ei;
	}

	public void setEi(int ei) {
		this.ei = ei;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSchMonth() {
		return schMonth;
	}

	public void setSchMonth(String schMonth) {
		this.schMonth = schMonth;
	}
}
