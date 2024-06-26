package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResCostCfgMain;
import com.pinde.sci.model.mo.ResCostCfgMainExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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