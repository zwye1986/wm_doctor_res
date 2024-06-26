package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DgdhyyActivity;
import com.pinde.sci.model.mo.DgdhyyActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgdhyyActivityMapper {
    int countByExample(DgdhyyActivityExample example);

    int deleteByExample(DgdhyyActivityExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DgdhyyActivity record);

    int insertSelective(DgdhyyActivity record);

    List<DgdhyyActivity> selectByExample(DgdhyyActivityExample example);

    DgdhyyActivity selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DgdhyyActivity record, @Param("example") DgdhyyActivityExample example);

    int updateByExample(@Param("record") DgdhyyActivity record, @Param("example") DgdhyyActivityExample example);

    int updateByPrimaryKeySelective(DgdhyyActivity record);

    int updateByPrimaryKey(DgdhyyActivity record);
}