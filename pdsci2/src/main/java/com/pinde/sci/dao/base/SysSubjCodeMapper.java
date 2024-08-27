package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysSubjCode;
import com.pinde.sci.model.mo.SysSubjCodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSubjCodeMapper {
    int countByExample(SysSubjCodeExample example);

    int deleteByExample(SysSubjCodeExample example);

    int deleteByPrimaryKey(String subjFlow);

    int insert(SysSubjCode record);

    int insertSelective(SysSubjCode record);

    List<SysSubjCode> selectByExample(SysSubjCodeExample example);

    SysSubjCode selectByPrimaryKey(String subjFlow);

    int updateByExampleSelective(@Param("record") SysSubjCode record, @Param("example") SysSubjCodeExample example);

    int updateByExample(@Param("record") SysSubjCode record, @Param("example") SysSubjCodeExample example);

    int updateByPrimaryKeySelective(SysSubjCode record);

    int updateByPrimaryKey(SysSubjCode record);
}