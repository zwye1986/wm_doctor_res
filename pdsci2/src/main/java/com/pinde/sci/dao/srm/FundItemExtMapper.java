package com.pinde.sci.dao.srm;

import com.pinde.sci.model.mo.SrmFundItem;

import java.util.List;

public interface FundItemExtMapper {
    List<SrmFundItem> searchSrmFundItemNotInBySchemeFlow(String schemeFlow);
}
