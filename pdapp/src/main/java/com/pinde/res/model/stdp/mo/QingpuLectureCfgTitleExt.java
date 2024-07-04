package com.pinde.res.model.stdp.mo;

import java.io.Serializable;
import java.util.List;

public class QingpuLectureCfgTitleExt implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5631934699990814967L;

	private String name;
	private String id;
	private String score;
	private String order;
	private List<QingpuLectureCfgItemExt> itemList;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<QingpuLectureCfgItemExt> getItemList() {
		return itemList;
	}

	public void setItemList(List<QingpuLectureCfgItemExt> itemList) {
		this.itemList = itemList;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
