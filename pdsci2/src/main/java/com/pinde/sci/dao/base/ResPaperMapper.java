package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResPaper;
import com.pinde.sci.model.mo.ResPaperExample;
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