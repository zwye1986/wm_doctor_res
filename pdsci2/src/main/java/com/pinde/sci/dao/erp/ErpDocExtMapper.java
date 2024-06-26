package com.pinde.sci.dao.erp;

import com.pinde.sci.model.mo.ErpDoc;

import java.util.List;
import java.util.Map;

public interface ErpDocExtMapper {

    /**
     * 查询文档类型
     *
     * @return
     */
    List<String> searchFileTypeList();

    /**
     * 查询
     *
     * @param paramMap
     * @return
     */
    List<ErpDoc> searchErpDocList(Map<String, Object> paramMap);

    /**
     * 批量删除
     *
     * @param docFlowList
     * @return
     */
    int batchDelByDocFlowList(List<String> docFlowList);
}
