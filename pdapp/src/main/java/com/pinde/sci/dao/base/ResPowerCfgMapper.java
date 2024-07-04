package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResPowerCfg;
import com.pinde.sci.model.mo.ResPowerCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResPowerCfgMapper {
    int countByExample(ResPowerCfgExample example);

    int deleteByExample(ResPowerCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(ResPowerCfg record);

    int insertSelective(ResPowerCfg record);

    List<ResPowerCfg> selectByExample(ResPowerCfgExample example);

    ResPowerCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") ResPowerCfg record, @Param("example") ResPowerCfgExample example);

    int updateByExample(@Param("record") ResPowerCfg record, @Param("example") ResPowerCfgExample example);

    int updateByPrimaryKeySelective(ResPowerCfg record);

    int updateByPrimaryKey(ResPowerCfg record);
}