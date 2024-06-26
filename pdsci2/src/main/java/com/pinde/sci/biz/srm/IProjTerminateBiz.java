package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

public interface IProjTerminateBiz {
    /**
     * 查询承担单位项目列表
     *
     * @param proj
     * @param recTypeId
     * @return
     */
    List<PubProj> searchLocalProj(PubProj proj, String recTypeId);

    /**
     * 查询主管单位项目列表
     *
     * @param proj
     * @param recTypeId
     * @return
     */
    List<PubProj> searchChargeProj(PubProj proj, String recTypeId);

    /**
     * 查询所有项目列表
     *
     * @param proj
     * @param recTypeId
     * @return
     */
    List<PubProj> searchGlobalProj(PubProj proj, String recTypeId);
}
