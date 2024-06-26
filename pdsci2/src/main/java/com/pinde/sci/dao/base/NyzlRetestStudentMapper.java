package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlRetestStudent;
import com.pinde.sci.model.mo.NyzlRetestStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlRetestStudentMapper {
    int countByExample(NyzlRetestStudentExample example);

    int deleteByExample(NyzlRetestStudentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlRetestStudent record);

    int insertSelective(NyzlRetestStudent record);

    List<NyzlRetestStudent> selectByExample(NyzlRetestStudentExample example);

    NyzlRetestStudent selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlRetestStudent record, @Param("example") NyzlRetestStudentExample example);

    int updateByExample(@Param("record") NyzlRetestStudent record, @Param("example") NyzlRetestStudentExample example);

    int updateByPrimaryKeySelective(NyzlRetestStudent record);

    int updateByPrimaryKey(NyzlRetestStudent record);
}