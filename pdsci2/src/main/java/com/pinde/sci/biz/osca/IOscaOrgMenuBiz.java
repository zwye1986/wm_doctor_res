package com.pinde.sci.biz.osca;

import com.pinde.sci.model.mo.OscaOrgMenu;
import com.pinde.sci.model.osca.OscaOrgMenuExt;

import java.util.List;

public interface IOscaOrgMenuBiz {

    int saveOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    int updateOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    List<OscaOrgMenuExt> searchOscaOrgMenuList(OscaOrgMenuExt oscaOrgMenuExt);

    void deleteOscaOrgMenu(OscaOrgMenu oscaOrgMenu);

    List<OscaOrgMenu> searchAllOrgMenu(OscaOrgMenu oscaOrgMenu);
}
