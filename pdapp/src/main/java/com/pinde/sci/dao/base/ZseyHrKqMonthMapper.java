package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseyHrKqMonth;
import com.pinde.sci.model.mo.ZseyHrKqMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseyHrKqMonthMapper {
    int countByExample(ZseyHrKqMonthExample example);

    int deleteByExample(ZseyHrKqMonthExample example);

    int deleteByPrimaryKey(String monthFlow);

    int insert(ZseyHrKqMonth record);

    int insertSelective(ZseyHrKqMonth record);

    List<ZseyHrKqMonth> selectByExample(ZseyHrKqMonthExample example);

    ZseyHrKqMonth selectByPrimaryKey(String monthFlow);

    int updateByExampleSelective(@Param("record") ZseyHrKqMonth record, @Param("example") ZseyHrKqMonthExample example);

    int updateByExample(@Param("record") ZseyHrKqMonth record, @Param("example") ZseyHrKqMonthExample example);

    int updateByPrimaryKeySelective(ZseyHrKqMonth record);

    int updateByPrimaryKey(ZseyHrKqMonth record);
}