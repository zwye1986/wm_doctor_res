package com.pinde.sci.form.sch;


import java.io.Serializable;
import java.util.List;

public class RosterForm implements Serializable{
	private static final long serialVersionUID = 8256273931760685022L;
	private String startDate;
	private List<RosterTime> months;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<RosterTime> getMonths() {
		return months;
	}

	public void setMonths(List<RosterTime> months) {
		this.months = months;
	}
}
