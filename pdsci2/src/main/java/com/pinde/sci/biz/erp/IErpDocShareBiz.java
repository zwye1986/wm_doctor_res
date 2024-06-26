package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpDocShare;

import java.util.List;


public interface IErpDocShareBiz {

    /**
     * 保存ErpDocShare
     *
     * @param erpDocShare
     * @return
     */
    int saveErpDocShare(ErpDocShare erpDocShare);

    /**
     * 查询
     *
     * @param docShare
     * @return
     */
    List<ErpDocShare> searchErpDocShareList(ErpDocShare docShare);


    /**
     * 批量共享
     *
     * @param docFlow
     * @param shareTypeId
     * @param shareRecordFlow
     * @param shareRecordName
     * @param recordStatus
     * @return
     */
    String batchShare(String[] docFlow, String shareTypeId, String shareRecordFlow, String shareRecordName, String recordStatus);

    /**
     * 查询共享文档
     * @param paramMap
     * @return
     */
//	List<ErpDocShareExt> searchErpDocShareExtList(Map<String, Object> paramMap);

}
