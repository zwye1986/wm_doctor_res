package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmProductBuildInfo;
import com.pinde.sci.model.mo.ErpCrmProductBuildInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmProductBuildInfoMapper {
    int countByExample(ErpCrmProductBuildInfoExample example);

    int deleteByExample(ErpCrmProductBuildInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(ErpCrmProductBuildInfo record);

    int insertSelective(ErpCrmProductBuildInfo record);

    List<ErpCrmProductBuildInfo> selectByExample(ErpCrmProductBuildInfoExample example);

    ErpCrmProductBuildInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") ErpCrmProductBuildInfo record, @Param("example") ErpCrmProductBuildInfoExample example);

    int updateByExample(@Param("record") ErpCrmProductBuildInfo record, @Param("example") ErpCrmProductBuildInfoExample example);

    int updateByPrimaryKeySelective(ErpCrmProductBuildInfo record);

    int updateByPrimaryKey(ErpCrmProductBuildInfo record);
}