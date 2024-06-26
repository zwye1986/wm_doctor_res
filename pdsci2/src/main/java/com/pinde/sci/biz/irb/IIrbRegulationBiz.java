package com.pinde.sci.biz.irb;

import com.pinde.sci.model.mo.IrbRegulation;
import com.pinde.sci.model.mo.PubFile;

import java.util.List;

public interface IIrbRegulationBiz {
    /**
     * 查询
     *
     * @param regulation
     * @return
     */
    List<IrbRegulation> queryRegulationList(IrbRegulation regulation);

    /**
     * 新增或修改
     *
     * @param file
     * @param regulation
     * @return
     */
    int editRegulation(PubFile file, IrbRegulation regulation);

    /**
     * 根据流水号查找
     *
     * @param regFlow
     * @return
     */
    IrbRegulation getRegulationByFlow(String regFlow);

}
