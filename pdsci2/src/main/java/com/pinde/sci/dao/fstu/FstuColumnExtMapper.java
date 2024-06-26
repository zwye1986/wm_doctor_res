package com.pinde.sci.dao.fstu;

import com.pinde.sci.model.mo.InxColumn;

import java.util.List;
import java.util.Map;


public interface FstuColumnExtMapper {
    List<InxColumn> searchInxColumnList(Map<String, Object> paramMap);
}
