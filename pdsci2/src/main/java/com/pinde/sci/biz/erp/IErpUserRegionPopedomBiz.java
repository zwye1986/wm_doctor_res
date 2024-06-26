package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpUserRegionPopedom;

import java.util.List;
import java.util.Map;

public interface IErpUserRegionPopedomBiz {

    /**
     * 查询人员地区权限
     *
     * @param regPop
     * @return
     */
    List<ErpUserRegionPopedom> searchRegionPopList(ErpUserRegionPopedom regPop);

    void saveRegion(String userFlow, String[] provIds, String[] provNames);

    List<ErpUserRegionPopedom> getErpUserRegionByUserFlow(String userFlow);

    void delErpUserRegion(String recordFlow);

    List<ErpUserRegionPopedom> getUserRegionArea(String currUserFlow);
}
