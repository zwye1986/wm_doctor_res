package com.pinde.res.biz.stdp;

import com.pinde.res.model.stdp.mo.InxInfoForm;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.ResReadInfo;

import java.util.List;

public interface IInxInfoBiz {

    /**
     * 根据infoFlow查询
     *
     * @param infoFlow
     * @return
     */
    InxInfo getByInfoFlow(String infoFlow);

    /**
     * 获取资讯列表
     *
     * @param form
     * @return
     */
    List<InxInfo> getList(InxInfoForm form);

    /**
     * 查询分类的资讯列表
     *
     * @param form
     * @return
     */
    List<InxInfo> queryClassifyList(InxInfoForm form);

    ResReadInfo getReadInfoByFlow(String infoFlow,  String userFlow);

    int saveReadInfo(String userFlow, ResReadInfo resReadInfo);
}
