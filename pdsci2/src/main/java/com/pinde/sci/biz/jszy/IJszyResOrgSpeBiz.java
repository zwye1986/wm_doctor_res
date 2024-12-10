package com.pinde.sci.biz.jszy;

import com.pinde.core.model.ResOrgSpe;

import java.util.List;

public interface IJszyResOrgSpeBiz {
    /**
     * 查询
     *
     * @param resOrgSpe
     * @param trainCategoryTypeId
     * @return
     */
    List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe, String trainCategoryTypeId);

    List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe);

    /**
     * 保存基地专业
     *
     * @param resOrgSpe
     * @return
     */
    int saveResOrgSpe(ResOrgSpe resOrgSpe);

    /**
     * 保存基地专业管理
     *
     * @param orgSpe
     * @return
     */
    int saveOrgSpeManage(ResOrgSpe orgSpe);

    List<ResOrgSpe> searchSpeByCondition(ResOrgSpe resOrgSpe, String flag);

}
