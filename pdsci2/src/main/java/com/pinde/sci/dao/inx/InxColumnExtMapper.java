package com.pinde.sci.dao.inx;

import com.pinde.core.model.InxColumn;

import java.util.List;
import java.util.Map;


public interface InxColumnExtMapper {
    List<InxColumn> searchInxColumnList(Map<String, Object> paramMap);
}