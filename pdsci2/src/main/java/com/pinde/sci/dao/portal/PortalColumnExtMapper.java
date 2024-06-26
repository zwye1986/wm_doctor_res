package com.pinde.sci.dao.portal;

import com.pinde.sci.model.mo.PortalColumn;

import java.util.List;
import java.util.Map;


public interface PortalColumnExtMapper {
    List<PortalColumn> searchInxColumnList(Map<String, Object> paramMap);
}