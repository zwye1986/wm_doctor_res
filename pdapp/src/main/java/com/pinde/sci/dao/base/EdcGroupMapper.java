package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcGroup;
import com.pinde.sci.model.mo.EdcGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcGroupMapper {
    int countByExample(EdcGroupExample example);

    int deleteByExample(EdcGroupExample example);

    int deleteByPrimaryKey(String groupFlow);

    int insert(EdcGroup record);

    int insertSelective(EdcGroup record);

    List<EdcGroup> selectByExample(EdcGroupExample example);

    EdcGroup selectByPrimaryKey(String groupFlow);

    int updateByExampleSelective(@Param("record") EdcGroup record, @Param("example") EdcGroupExample example);

    int updateByExample(@Param("record") EdcGroup record, @Param("example") EdcGroupExample example);

    int updateByPrimaryKeySelective(EdcGroup record);

    int updateByPrimaryKey(EdcGroup record);
}