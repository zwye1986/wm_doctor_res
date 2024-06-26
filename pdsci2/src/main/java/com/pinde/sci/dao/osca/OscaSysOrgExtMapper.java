package com.pinde.sci.dao.osca;

import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.osca.OscaOrgMenuExt;
import com.pinde.sci.model.osca.OscaOrgSpeExt;

import java.util.List;
import java.util.Map;

public interface OscaSysOrgExtMapper {
    /**
     * 地市查询
     * @param map
     * @return
     */
    List<SysOrg> searchOrgCitys(Map<String, Object> map);

    List<OscaOrgSpeExt> searchAllSites(Map<String, Object> map);

    List<OscaOrgSpeExt> searchAllOrg(Map<String, Object> map);

    void updateOscaOrgSpe(Map<String, Object> map);

    List<OscaOrgMenuExt> searchAllOrgSpes(Map<String, Object> map);

    //根据条件查询考点管理员
    List<Map<String,Object>> searchManagerList(Map<String,Object> paramMap);
}
