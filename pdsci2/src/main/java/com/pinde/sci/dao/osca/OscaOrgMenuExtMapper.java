package com.pinde.sci.dao.osca;

import com.pinde.sci.model.osca.OscaOrgMenuExt;

import java.util.List;
import java.util.Map;

public interface OscaOrgMenuExtMapper {
    List<OscaOrgMenuExt> searchAllOrgSpes(Map<String, Object> map);

    List<OscaOrgMenuExt> searchSpeOrgs(Map<String, Object> map);
}
