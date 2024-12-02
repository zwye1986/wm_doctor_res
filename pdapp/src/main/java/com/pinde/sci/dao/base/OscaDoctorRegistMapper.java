package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaDoctorRegist;
import com.pinde.core.model.OscaDoctorRegistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaDoctorRegistMapper {
    int countByExample(OscaDoctorRegistExample example);

    int deleteByExample(OscaDoctorRegistExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaDoctorRegist record);

    int insertSelective(OscaDoctorRegist record);

    List<OscaDoctorRegist> selectByExample(OscaDoctorRegistExample example);

    OscaDoctorRegist selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaDoctorRegist record, @Param("example") OscaDoctorRegistExample example);

    int updateByExample(@Param("record") OscaDoctorRegist record, @Param("example") OscaDoctorRegistExample example);

    int updateByPrimaryKeySelective(OscaDoctorRegist record);

    int updateByPrimaryKey(OscaDoctorRegist record);
}