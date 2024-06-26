package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResAnnualAssessment;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.res.AnnualAssessmentExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface StuBatchExtMapper {

    List<StuBatch> getDefualStuBatchAndStuSelect(@Param("userFlow") String userFlow/*, @Param("batchDate") String batchDate*/);

    int getStuNumByFlow(@Param("batchFlow") String batchFlow);
}
