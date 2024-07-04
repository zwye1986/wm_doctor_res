package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseevalForm;
import com.pinde.sci.model.mo.ResBaseevalFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResBaseevalFormMapper {
    int countByExample(ResBaseevalFormExample example);

    int deleteByExample(ResBaseevalFormExample example);

    int deleteByPrimaryKey(String formFlow);

    int insert(ResBaseevalForm record);

    int insertSelective(ResBaseevalForm record);

    List<ResBaseevalForm> selectByExample(ResBaseevalFormExample example);

    ResBaseevalForm selectByPrimaryKey(String formFlow);

    int updateByExampleSelective(@Param("record") ResBaseevalForm record, @Param("example") ResBaseevalFormExample example);

    int updateByExample(@Param("record") ResBaseevalForm record, @Param("example") ResBaseevalFormExample example);

    int updateByPrimaryKeySelective(ResBaseevalForm record);

    int updateByPrimaryKey(ResBaseevalForm record);
}