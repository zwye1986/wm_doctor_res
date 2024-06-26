package com.pinde.sci.dao.lcjn;


import java.util.List;
import java.util.Map;

public interface LcjnSuppliesAndAssetsExtMapper {
    /**
     * 固定资产列表
     * @param map
     * @return
     */
    List<Map<String,Object>> queryFixedAssetsList(Map<String, String> map);

    /**
     * 耗材列表
     * @param map
     * @return
     */
    List<Map<String,Object>> querySuppliesList(Map<String, String> map);
}
