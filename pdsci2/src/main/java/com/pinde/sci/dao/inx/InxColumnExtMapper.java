package com.pinde.sci.dao.inx;

import com.pinde.sci.model.mo.InxColumn;

import java.util.List;
import java.util.Map;


public interface InxColumnExtMapper {
    //	public List<InxColumnExt> selectByForm(InxColumnForm form);
//	public long selectCountByForm(InxColumnForm form);
//	public InxColumnExt selectOneByForm(InxColumnForm form);
    List<InxColumn> searchInxColumnList(Map<String, Object> paramMap);
}