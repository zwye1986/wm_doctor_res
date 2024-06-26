package com.pinde.sci.model.res;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GradeDetail4ShiYan implements Serializable{
	
	/**
	 * 试验室太和医院OSCE考试成绩详情
	 */
	private static final long serialVersionUID = 2018071301L;

	private String station1;

	private String station2;

	private String station3;

	private String station4;

	private String station5;

	private String station6;

	private String station7;

	private String station8;

	private String station9;

	private String total;

	private String result;

	public String getStation1() {
		return station1;
	}

	public void setStation1(String station1) {
		this.station1 = station1;
	}

	public String getStation2() {
		return station2;
	}

	public void setStation2(String station2) {
		this.station2 = station2;
	}

	public String getStation3() {
		return station3;
	}

	public void setStation3(String station3) {
		this.station3 = station3;
	}

	public String getStation4() {
		return station4;
	}

	public void setStation4(String station4) {
		this.station4 = station4;
	}

	public String getStation5() {
		return station5;
	}

	public void setStation5(String station5) {
		this.station5 = station5;
	}

	public String getStation6() {
		return station6;
	}

	public void setStation6(String station6) {
		this.station6 = station6;
	}

	public String getStation7() {
		return station7;
	}

	public void setStation7(String station7) {
		this.station7 = station7;
	}

	public String getStation8() {
		return station8;
	}

	public void setStation8(String station8) {
		this.station8 = station8;
	}

	public String getStation9() {
		return station9;
	}

	public void setStation9(String station9) {
		this.station9 = station9;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
