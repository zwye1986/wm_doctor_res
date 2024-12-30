package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResPaper;
import com.pinde.core.model.ResPaperExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPaperMapper {
    int countByExample(ResPaperExample example);

    int deleteByExample(ResPaperExample example);

    int deleteByPrimaryKey(String paperFlow);

    int insert(ResPaper record);

    int insertSelective(ResPaper record);

    List<ResPaper> selectByExample(ResPaperExample example);

    ResPaper selectByPrimaryKey(String paperFlow);

    int updateByExampleSelective(@Param("record") ResPaper record, @Param("example") ResPaperExample example);

    int updateByExample(@Param("record") ResPaper record, @Param("example") ResPaperExample example);

    int updateByPrimaryKeySelective(ResPaper record);

    int updateByPrimaryKey(ResPaper record);
}