package com.pinde.sci.dao.base;

import com.pinde.core.model.ResBaseevalFormCfg;
import com.pinde.core.model.ResBaseevalFormCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResBaseevalFormCfgMapper {
    int countByExample(ResBaseevalFormCfgExample example);

    int deleteByExample(ResBaseevalFormCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseevalFormCfg record);

    int insertSelective(ResBaseevalFormCfg record);

    List<ResBaseevalFormCfg> selectByExample(ResBaseevalFormCfgExample example);

    ResBaseevalFormCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseevalFormCfg record, @Param("example") ResBaseevalFormCfgExample example);

    int updateByExample(@Param("record") ResBaseevalFormCfg record, @Param("example") ResBaseevalFormCfgExample example);

    int updateByPrimaryKeySelective(ResBaseevalFormCfg record);

    int updateByPrimaryKey(ResBaseevalFormCfg record);
}