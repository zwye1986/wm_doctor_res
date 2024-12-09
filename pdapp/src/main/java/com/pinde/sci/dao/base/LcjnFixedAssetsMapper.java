package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnFixedAssets;
import com.pinde.core.model.LcjnFixedAssetsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnFixedAssetsMapper {
    int countByExample(LcjnFixedAssetsExample example);

    int deleteByExample(LcjnFixedAssetsExample example);

    int deleteByPrimaryKey(String fixedFlow);

    int insert(LcjnFixedAssets record);

    int insertSelective(LcjnFixedAssets record);

    List<LcjnFixedAssets> selectByExample(LcjnFixedAssetsExample example);

    LcjnFixedAssets selectByPrimaryKey(String fixedFlow);

    int updateByExampleSelective(@Param("record") LcjnFixedAssets record, @Param("example") LcjnFixedAssetsExample example);

    int updateByExample(@Param("record") LcjnFixedAssets record, @Param("example") LcjnFixedAssetsExample example);

    int updateByPrimaryKeySelective(LcjnFixedAssets record);

    int updateByPrimaryKey(LcjnFixedAssets record);
}