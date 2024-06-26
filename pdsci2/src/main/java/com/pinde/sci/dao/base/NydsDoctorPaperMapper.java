package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydsDoctorPaper;
import com.pinde.sci.model.mo.NydsDoctorPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydsDoctorPaperMapper {
    int countByExample(NydsDoctorPaperExample example);

    int deleteByExample(NydsDoctorPaperExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydsDoctorPaper record);

    int insertSelective(NydsDoctorPaper record);

    List<NydsDoctorPaper> selectByExample(NydsDoctorPaperExample example);

    NydsDoctorPaper selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydsDoctorPaper record, @Param("example") NydsDoctorPaperExample example);

    int updateByExample(@Param("record") NydsDoctorPaper record, @Param("example") NydsDoctorPaperExample example);

    int updateByPrimaryKeySelective(NydsDoctorPaper record);

    int updateByPrimaryKey(NydsDoctorPaper record);
}