package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyqjLeaveMain;
import com.pinde.sci.model.mo.NyqjLeaveMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyqjLeaveMainMapper {
    int countByExample(NyqjLeaveMainExample example);

    int deleteByExample(NyqjLeaveMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyqjLeaveMain record);

    int insertSelective(NyqjLeaveMain record);

    List<NyqjLeaveMain> selectByExample(NyqjLeaveMainExample example);

    NyqjLeaveMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyqjLeaveMain record, @Param("example") NyqjLeaveMainExample example);

    int updateByExample(@Param("record") NyqjLeaveMain record, @Param("example") NyqjLeaveMainExample example);

    int updateByPrimaryKeySelective(NyqjLeaveMain record);

    int updateByPrimaryKey(NyqjLeaveMain record);
}