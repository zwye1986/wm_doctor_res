package com.pinde.sci.biz.irb;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

public interface IIrbProjBiz {


    /**
     * 根据项目流水号读取项目信息
     *
     * @param projFlow
     * @return
     */
    PubProj readProject(String projFlow);

    /**
     * 查询全部  (irb 和 ResumeController 用到了 SRM没用到)
     *
     * @return
     */
    List<PubProj> queryProjList(PubProj proj);


}
