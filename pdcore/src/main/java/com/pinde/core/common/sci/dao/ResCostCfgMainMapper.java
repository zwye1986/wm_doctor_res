package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResCostCfgMain;
import com.pinde.core.model.ResCostCfgMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResCostCfgMainMapper {
    int countByExample(ResCostCfgMainExample example);

    int deleteByExample(ResCostCfgMainExample example);

    int deleteByPrimaryKey(String mainFlow);

    int insert(ResCostCfgMain record);

    int insertSelective(ResCostCfgMain record);

    List<ResCostCfgMain> selectByExample(ResCostCfgMainExample example);

    ResCostCfgMain selectByPrimaryKey(String mainFlow);

    int updateByExampleSelective(@Param("record") ResCostCfgMain record, @Param("example") ResCostCfgMainExample example);

    int updateByExample(@Param("record") ResCostCfgMain record, @Param("example") ResCostCfgMainExample example);

    int updateByPrimaryKeySelective(ResCostCfgMain record);

    int updateByPrimaryKey(ResCostCfgMain record);

}