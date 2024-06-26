package com.pinde.sci.biz.lcjn;

import com.pinde.sci.model.mo.LcjnFixedAssets;
import com.pinde.sci.model.mo.LcjnSupplies;
import com.pinde.sci.model.mo.LcjnSuppliesBatch;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ILcjnSuppliesAndAssetsBiz {

    /**
     * 固定资产列表
     * @param dictName
     * @param statusId
     * @return
     */
    List<Map<String,Object>> selectFixedAssetsList(String dictName,String statusId);

    /**
     * 新增或修改固定资产
     * @param lcjnFixedAssets
     * @return
     */
    int editFixedAsset(LcjnFixedAssets lcjnFixedAssets);

    /**
     * 根据fixedFlow查询一条记录
     * @param fixedFlow
     * @return
     */
    LcjnFixedAssets selectByFixedFlow(String fixedFlow);

    /**
     * 根据fixedFlow删除一条数据
     * @param fixedFlow
     * @return
     */
    int deleteByFixedFlow(String fixedFlow);

    /**
     * 耗材列表
     * @param dictName
     * @return
     */
    List<Map<String,Object>> selectSuppliesList(String dictName,String suppliesFlow);

    /**
     * 查询某个耗材信息
     * @param DictId
     * @return
     */
    List<LcjnSupplies> selectByDictId(String DictId);

    /**
     * 新增耗材
     * @param supplies
     * @return
     */
    int addSupplies(LcjnSupplies supplies);

    /**
     * 新增耗材批次
     * @param suppliesBatch
     * @return
     */
    int addSupliesBatch(LcjnSuppliesBatch suppliesBatch);

    /**
     * 删除耗材信息
     * @param suppliesFlow
     * @return
     */
    int deleteSuppliesInfo(String suppliesFlow);

    /**
     * 查询维护批次
     * @param suppliesFlow
     * @return
     */
    List<LcjnSuppliesBatch> selectSuppliesBatch(String suppliesFlow);

    void importAssetsFromExcel(MultipartFile file);

    void importSuppliesFromExcel(MultipartFile file);
}
