package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResCostCfgDetail;
import com.pinde.sci.model.mo.ResCostCfgDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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