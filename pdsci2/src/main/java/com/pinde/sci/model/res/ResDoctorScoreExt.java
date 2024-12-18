package com.pinde.sci.model.res;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.SysUser;

import java.math.BigDecimal;

public class ResDoctorScoreExt extends ResDoctor {
	private static final long serialVersionUID = -438010526424360596L;
	
	private SysUser sysUser;
	
	private ResScore resScore;

    private BigDecimal avgScore;

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }

    public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ResScore getResScore() {
		return resScore;
	}

	public void setResScore(ResScore resScore) {
		this.resScore = resScore;
	}
}
