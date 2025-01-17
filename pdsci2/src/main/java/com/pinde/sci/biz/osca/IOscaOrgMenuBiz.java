package com.pinde.sci.biz.osca;

import com.pinde.core.model.OscaOrgMenu;
import com.pinde.core.model.OscaOrgMenuExt;

import java.util.List;

public interface IOscaOrgMenuBiz {

    int saveOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    int updateOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    List<OscaOrgMenuExt> searchOscaOrgMenuList(OscaOrgMenuExt oscaOrgMenuExt);

    void deleteOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    List<OscaOrgMenu> searchAllOrgMenu(OscaOrgMenu oscaOrgMenu);
}
