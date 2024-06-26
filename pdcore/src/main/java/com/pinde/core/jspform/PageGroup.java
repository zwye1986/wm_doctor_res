package com.pinde.core.jspform;

import java.util.Map;

public class PageGroup {

	private String firstPage;
	
	private String name;
	
	private String view;
	
	private String printTemeplete;
	
	private Map<String, Page> pageMap;

	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getFirstPageJsp() {
		return pageMap.get(firstPage).getJsp();
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public Map<String, Page> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, Page> pageMap) {
		this.pageMap = pageMap;
	}

	public String getPrintTemeplete() {
		return printTemeplete;
	}

	public void setPrintTemeplete(String printTemeplete) {
		this.printTemeplete = printTemeplete;
	}
	
	

}