package com.pinde.sci.dao.base;

import com.pinde.core.model.SysSubjCode;
import com.pinde.core.model.SysSubjCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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