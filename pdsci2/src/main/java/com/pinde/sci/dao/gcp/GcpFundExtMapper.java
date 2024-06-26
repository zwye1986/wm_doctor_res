package com.pinde.sci.dao.gcp;

import com.pinde.sci.model.mo.GcpFund;

import java.math.BigDecimal;

public interface GcpFundExtMapper {
	BigDecimal selectSum(GcpFund fund);
}
