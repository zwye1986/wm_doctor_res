package com.pinde.sci.dao.res;

import com.pinde.core.model.StuBatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface StuBatchExtMapper {

    List<StuBatch> getDefualStuBatchAndStuSelect(@Param("userFlow") String userFlow/*, @Param("batchDate") String batchDate*/);

    int getStuNumByFlow(@Param("batchFlow") String batchFlow);
}
