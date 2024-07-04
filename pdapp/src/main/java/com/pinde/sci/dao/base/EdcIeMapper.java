package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcIe;
import com.pinde.sci.model.mo.EdcIeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcIeMapper {
    int countByExample(EdcIeExample example);

    int deleteByExample(EdcIeExample example);

    int deleteByPrimaryKey(String ieFlow);

    int insert(EdcIe record);

    int insertSelective(EdcIe record);

    List<EdcIe> selectByExample(EdcIeExample example);

    EdcIe selectByPrimaryKey(String ieFlow);

    int updateByExampleSelective(@Param("record") EdcIe record, @Param("example") EdcIeExample example);

    int updateByExample(@Param("record") EdcIe record, @Param("example") EdcIeExample example);

    int updateByPrimaryKeySelective(EdcIe record);

    int updateByPrimaryKey(EdcIe record);
}