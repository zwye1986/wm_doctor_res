package com.pinde.sci.biz.osca;

import com.pinde.core.model.SysOrg;
import com.pinde.sci.model.mo.OscaOrgSpe;
import com.pinde.sci.model.osca.OscaOrgSpeExt;

import java.util.List;
import java.util.Map;

public interface ISiteInformationBiz {

    /**
     * 保存考点信息
     * @param oscaOrgSpe
     * @return
     */
    int saveSiteInformation(OscaOrgSpe oscaOrgSpe);

    /**
     * 修改考点信息
     * @param oscaOrgSpe
     * @return
     */
    int updateSiteInformation(OscaOrgSpe oscaOrgSpe);

    /**
     * 查找所有考点
     * @param oscaOrgSpe
     * @return
     */
    List<OscaOrgSpe> searchOscaOrgSpeList(OscaOrgSpe oscaOrgSpe);

    /**
     * 查找所有基地
     * @param sysOrg
     * @return
     */
    List<SysOrg> searchOrgList(SysOrg sysOrg);

    /**
     * 地市列表
     * @param sysOrg
     * @return
     */
    List<SysOrg> serachOrgCity(SysOrg sysOrg);

    /**
     * 查找所有基地
     * @param map
     * @return
     */
    List<OscaOrgSpeExt> searchAllOrg(Map<String, Object> map);

    /**
     * 查找所有考点
     * @param map
     * @return
     */
    List<OscaOrgSpeExt> searchAllSites(Map<String, Object> map);

    /**
     * 根据orgFlow伪删除数据
     * @param map
     */
    void removeOscaOrgSpe(Map<String, Object> map);

    //根据条件查找考点管理员
    List<Map<String,Object>> searchManagerList(Map<String, Object> paramMap);
}
