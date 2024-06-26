package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.StuBatch;

import java.util.List;

public interface IStuBatchBiz {

    //查询所有进修报名批次
    List<StuBatch> getStuBatchLst();
    //查询报名中
    List<StuBatch> getSingUpStuBatchLst();
    //根据报名批次流水号查询信息
    StuBatch searchStuBatch(String batchFlow);
    //保存进修报名批次设置
    int saveBatchSetting(String batchFlow,String registerTime,String fee);
    int saveBatchSetting(String batchFlow,String registerTime,String fee,String batchDate,String batchStatus);
    //删除报名批次设置
    int delBatchSetting(String batchFlow);
    //变更系统默认批次
    void changeDefaultSetting(String batchFlow);

    StuBatch searchStuBatchByDate(String batchDate);

    StuBatch getDefualBatch();

    List<StuBatch> getDefualStuBatchAndStuSelect(String userFlow/*, String batchDate*/);

    int getStuNumByFlow(String batchFlow);
}
