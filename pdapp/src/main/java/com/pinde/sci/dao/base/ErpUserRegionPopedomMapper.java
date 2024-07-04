package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpUserRegionPopedom;
import com.pinde.sci.model.mo.ErpUserRegionPopedomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpUserRegionPopedomMapper {
    int countByExample(ErpUserRegionPopedomExample example);

    int deleteByExample(ErpUserRegionPopedomExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpUserRegionPopedom record);

    int insertSelective(ErpUserRegionPopedom record);

    List<ErpUserRegionPopedom> selectByExample(ErpUserRegionPopedomExample example);

    ErpUserRegionPopedom selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpUserRegionPopedom record, @Param("example") ErpUserRegionPopedomExample example);

    int updateByExample(@Param("record") ErpUserRegionPopedom record, @Param("example") ErpUserRegionPopedomExample example);

    int updateByPrimaryKeySelective(ErpUserRegionPopedom record);

    int updateByPrimaryKey(ErpUserRegionPopedom record);
}