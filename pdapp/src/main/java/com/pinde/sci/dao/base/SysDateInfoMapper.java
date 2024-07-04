package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysDateInfo;
import com.pinde.sci.model.mo.SysDateInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDateInfoMapper {
    int countByExample(SysDateInfoExample example);

    int deleteByExample(SysDateInfoExample example);

    int deleteByPrimaryKey(String dateFlow);

    int insert(SysDateInfo record);

    int insertSelective(SysDateInfo record);

    List<SysDateInfo> selectByExample(SysDateInfoExample example);

    SysDateInfo selectByPrimaryKey(String dateFlow);

    int updateByExampleSelective(@Param("record") SysDateInfo record, @Param("example") SysDateInfoExample example);

    int updateByExample(@Param("record") SysDateInfo record, @Param("example") SysDateInfoExample example);

    int updateByPrimaryKeySelective(SysDateInfo record);

    int updateByPrimaryKey(SysDateInfo record);
}