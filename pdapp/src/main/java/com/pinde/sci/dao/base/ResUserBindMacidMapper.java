package com.pinde.sci.dao.base;

import com.pinde.core.model.ResUserBindMacid;
import com.pinde.core.model.ResUserBindMacidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResUserBindMacidMapper {
    int countByExample(ResUserBindMacidExample example);

    int deleteByExample(ResUserBindMacidExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(ResUserBindMacid record);

    int insertSelective(ResUserBindMacid record);

    List<ResUserBindMacid> selectByExample(ResUserBindMacidExample example);

    ResUserBindMacid selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") ResUserBindMacid record, @Param("example") ResUserBindMacidExample example);

    int updateByExample(@Param("record") ResUserBindMacid record, @Param("example") ResUserBindMacidExample example);

    int updateByPrimaryKeySelective(ResUserBindMacid record);

    int updateByPrimaryKey(ResUserBindMacid record);
}