package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseyAppointMain;
import com.pinde.sci.model.mo.ZseyAppointMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseyAppointMainMapper {
    int countByExample(ZseyAppointMainExample example);

    int deleteByExample(ZseyAppointMainExample example);

    int deleteByPrimaryKey(String appointFlow);

    int insert(ZseyAppointMain record);

    int insertSelective(ZseyAppointMain record);

    List<ZseyAppointMain> selectByExample(ZseyAppointMainExample example);

    ZseyAppointMain selectByPrimaryKey(String appointFlow);

    int updateByExampleSelective(@Param("record") ZseyAppointMain record, @Param("example") ZseyAppointMainExample example);

    int updateByExample(@Param("record") ZseyAppointMain record, @Param("example") ZseyAppointMainExample example);

    int updateByPrimaryKeySelective(ZseyAppointMain record);

    int updateByPrimaryKey(ZseyAppointMain record);
}