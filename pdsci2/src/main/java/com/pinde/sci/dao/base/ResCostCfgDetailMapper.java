package com.pinde.sci.dao.base;

import com.pinde.core.model.ResCostCfgDetail;
import com.pinde.core.model.ResCostCfgDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResCostCfgDetailMapper {
    int countByExample(ResCostCfgDetailExample example);

    int deleteByExample(ResCostCfgDetailExample example);

    int deleteByPrimaryKey(String detailFlow);

    int insert(ResCostCfgDetail record);

    int insertSelective(ResCostCfgDetail record);

    List<ResCostCfgDetail> selectByExample(ResCostCfgDetailExample example);

    ResCostCfgDetail selectByPrimaryKey(String detailFlow);

    int updateByExampleSelective(@Param("record") ResCostCfgDetail record, @Param("example") ResCostCfgDetailExample example);

    int updateByExample(@Param("record") ResCostCfgDetail record, @Param("example") ResCostCfgDetailExample example);

    int updateByPrimaryKeySelective(ResCostCfgDetail record);

    int updateByPrimaryKey(ResCostCfgDetail record);
}