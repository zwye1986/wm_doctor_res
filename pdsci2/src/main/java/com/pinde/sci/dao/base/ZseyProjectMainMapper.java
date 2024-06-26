package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseyProjectMain;
import com.pinde.sci.model.mo.ZseyProjectMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseyProjectMainMapper {
    int countByExample(ZseyProjectMainExample example);

    int deleteByExample(ZseyProjectMainExample example);

    int deleteByPrimaryKey(String projectFlow);

    int insert(ZseyProjectMain record);

    int insertSelective(ZseyProjectMain record);

    List<ZseyProjectMain> selectByExample(ZseyProjectMainExample example);

    ZseyProjectMain selectByPrimaryKey(String projectFlow);

    int updateByExampleSelective(@Param("record") ZseyProjectMain record, @Param("example") ZseyProjectMainExample example);

    int updateByExample(@Param("record") ZseyProjectMain record, @Param("example") ZseyProjectMainExample example);

    int updateByPrimaryKeySelective(ZseyProjectMain record);

    int updateByPrimaryKey(ZseyProjectMain record);
}