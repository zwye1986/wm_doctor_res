package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcQuery;
import com.pinde.sci.model.mo.EdcQueryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcQueryMapper {
    int countByExample(EdcQueryExample example);

    int deleteByExample(EdcQueryExample example);

    int deleteByPrimaryKey(String queryFlow);

    int insert(EdcQuery record);

    int insertSelective(EdcQuery record);

    List<EdcQuery> selectByExample(EdcQueryExample example);

    EdcQuery selectByPrimaryKey(String queryFlow);

    int updateByExampleSelective(@Param("record") EdcQuery record, @Param("example") EdcQueryExample example);

    int updateByExample(@Param("record") EdcQuery record, @Param("example") EdcQueryExample example);

    int updateByPrimaryKeySelective(EdcQuery record);

    int updateByPrimaryKey(EdcQuery record);
}