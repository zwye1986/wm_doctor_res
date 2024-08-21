package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResLectureRandomScan;
import com.pinde.sci.model.mo.ResLectureRandomScanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResLectureRandomScanMapper {
    int countByExample(ResLectureRandomScanExample example);

    int deleteByExample(ResLectureRandomScanExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResLectureRandomScan record);

    int insertSelective(ResLectureRandomScan record);

    List<ResLectureRandomScan> selectByExample(ResLectureRandomScanExample example);

    ResLectureRandomScan selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResLectureRandomScan record, @Param("example") ResLectureRandomScanExample example);

    int updateByExample(@Param("record") ResLectureRandomScan record, @Param("example") ResLectureRandomScanExample example);

    int updateByPrimaryKeySelective(ResLectureRandomScan record);

    int updateByPrimaryKey(ResLectureRandomScan record);
}