package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DegreeInfoMain;
import com.pinde.sci.model.mo.DegreeInfoMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DegreeInfoMainMapper {
    int countByExample(DegreeInfoMainExample example);

    int deleteByExample(DegreeInfoMainExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(DegreeInfoMain record);

    int insertSelective(DegreeInfoMain record);

    List<DegreeInfoMain> selectByExample(DegreeInfoMainExample example);

    DegreeInfoMain selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") DegreeInfoMain record, @Param("example") DegreeInfoMainExample example);

    int updateByExample(@Param("record") DegreeInfoMain record, @Param("example") DegreeInfoMainExample example);

    int updateByPrimaryKeySelective(DegreeInfoMain record);

    int updateByPrimaryKey(DegreeInfoMain record);
}