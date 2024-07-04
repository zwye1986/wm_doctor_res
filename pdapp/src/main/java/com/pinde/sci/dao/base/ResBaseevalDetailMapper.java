package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseevalDetail;
import com.pinde.sci.model.mo.ResBaseevalDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResBaseevalDetailMapper {
    int countByExample(ResBaseevalDetailExample example);

    int deleteByExample(ResBaseevalDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseevalDetail record);

    int insertSelective(ResBaseevalDetail record);

    List<ResBaseevalDetail> selectByExample(ResBaseevalDetailExample example);

    ResBaseevalDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseevalDetail record, @Param("example") ResBaseevalDetailExample example);

    int updateByExample(@Param("record") ResBaseevalDetail record, @Param("example") ResBaseevalDetailExample example);

    int updateByPrimaryKeySelective(ResBaseevalDetail record);

    int updateByPrimaryKey(ResBaseevalDetail record);
}