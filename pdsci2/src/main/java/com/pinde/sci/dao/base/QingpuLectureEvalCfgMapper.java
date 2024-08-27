package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.QingpuLectureEvalCfg;
import com.pinde.sci.model.mo.QingpuLectureEvalCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QingpuLectureEvalCfgMapper {
    int countByExample(QingpuLectureEvalCfgExample example);

    int deleteByExample(QingpuLectureEvalCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(QingpuLectureEvalCfg record);

    int insertSelective(QingpuLectureEvalCfg record);

    List<QingpuLectureEvalCfg> selectByExampleWithBLOBs(QingpuLectureEvalCfgExample example);

    List<QingpuLectureEvalCfg> selectByExample(QingpuLectureEvalCfgExample example);

    QingpuLectureEvalCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") QingpuLectureEvalCfg record, @Param("example") QingpuLectureEvalCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") QingpuLectureEvalCfg record, @Param("example") QingpuLectureEvalCfgExample example);

    int updateByExample(@Param("record") QingpuLectureEvalCfg record, @Param("example") QingpuLectureEvalCfgExample example);

    int updateByPrimaryKeySelective(QingpuLectureEvalCfg record);

    int updateByPrimaryKeyWithBLOBs(QingpuLectureEvalCfg record);

    int updateByPrimaryKey(QingpuLectureEvalCfg record);
}