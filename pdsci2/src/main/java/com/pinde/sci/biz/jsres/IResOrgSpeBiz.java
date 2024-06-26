package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;

public interface IResOrgSpeBiz {
    /**
     * 查询
     *
     * @param resOrgSpe
     * @param trainCategoryTypeId
     * @return
     */
    List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe, String trainCategoryTypeId);

    List<ResOrgSpe> searchResOrgSpeListNew(ResOrgSpe resOrgSpe, String trainCategoryTypeId,String speFlag);

    List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe);

    /**
     * 保存基地专业
     *
     * @param resOrgSpe
     * @return
     */
    int saveResOrgSpe(ResOrgSpe resOrgSpe);

    int saveOrgSpeManageAll(ResOrgSpe resOrgSpe, List<SysOrg> orgList) throws Exception;
    /**
     * 保存基地专业管理
     *
     * @param orgSpe
     * @return
     */
    int saveOrgSpeManage(ResOrgSpe orgSpe);

    List<ResOrgSpe> searchSpeByCondition(ResOrgSpe resOrgSpe, String flag);

    List<ResOrgSpe> searchResOrgSpeListByFlows(List<String> jointOrgFlowList);
}
