package com.pinde.sci.biz.res;

import com.pinde.core.model.ResBookStudyRecord;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface IResBookStudyRecordBiz {

    List<ResBookStudyRecord> getBookStudyRecords(String doctorFlow,String teacherFlow);

    ResBookStudyRecord getBookStudyRecord(String recordFlow);

    int savaRecord(ResBookStudyRecord record);
}
