package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmLearningSociety;

import java.util.List;

/**
 * 学会任职登记
 */
public interface ILearningSocietyBiz {
    /**
     * 查询学会任职信息
     * @param srmLearningSociety
     * @return
     */
    public List<SrmLearningSociety> search(SrmLearningSociety srmLearningSociety);

    /**
     * 修改任职信息
     * @param society
     * @return
     */
    boolean update(SrmLearningSociety society);

    int save(SrmLearningSociety society);

    void deleteByPrimaryKeys(List<String> societyFlowList);
}
