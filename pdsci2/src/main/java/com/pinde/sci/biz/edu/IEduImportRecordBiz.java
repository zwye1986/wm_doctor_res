package com.pinde.sci.biz.edu;

import com.pinde.sci.model.mo.PubImportRecord;

import java.util.List;


public interface IEduImportRecordBiz {

    /**
     * 新增
     *
     * @param importRecord
     */
    int addRecord(PubImportRecord importRecord);

    /**
     * 查询
     *
     * @param importRecord
     * @return
     */
    List<PubImportRecord> searchImportRecordList(PubImportRecord importRecord);

    /**
     * 删除该批次导入的成绩记录
     *
     * @param impFlow
     * @return
     */
    int delImpRecord(String impFlow);

    /**
     * 修改
     *
     * @param importRecord
     * @return
     */
    int update(PubImportRecord importRecord);
} 
 