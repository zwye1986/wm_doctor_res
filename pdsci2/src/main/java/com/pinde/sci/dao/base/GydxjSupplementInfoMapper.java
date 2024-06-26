package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GydxjSupplementInfo;
import com.pinde.sci.model.mo.GydxjSupplementInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GydxjSupplementInfoMapper {
    int countByExample(GydxjSupplementInfoExample example);

    int deleteByExample(GydxjSupplementInfoExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(GydxjSupplementInfo record);

    int insertSelective(GydxjSupplementInfo record);

    List<GydxjSupplementInfo> selectByExample(GydxjSupplementInfoExample example);

    GydxjSupplementInfo selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") GydxjSupplementInfo record, @Param("example") GydxjSupplementInfoExample example);

    int updateByExample(@Param("record") GydxjSupplementInfo record, @Param("example") GydxjSupplementInfoExample example);

    int updateByPrimaryKeySelective(GydxjSupplementInfo record);

    int updateByPrimaryKey(GydxjSupplementInfo record);
}