package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GydxjInsertDetail;
import com.pinde.sci.model.mo.GydxjInsertDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GydxjInsertDetailMapper {
    int countByExample(GydxjInsertDetailExample example);

    int deleteByExample(GydxjInsertDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GydxjInsertDetail record);

    int insertSelective(GydxjInsertDetail record);

    List<GydxjInsertDetail> selectByExample(GydxjInsertDetailExample example);

    GydxjInsertDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GydxjInsertDetail record, @Param("example") GydxjInsertDetailExample example);

    int updateByExample(@Param("record") GydxjInsertDetail record, @Param("example") GydxjInsertDetailExample example);

    int updateByPrimaryKeySelective(GydxjInsertDetail record);

    int updateByPrimaryKey(GydxjInsertDetail record);
}