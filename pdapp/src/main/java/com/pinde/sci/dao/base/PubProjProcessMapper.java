package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubProjProcess;
import com.pinde.sci.model.mo.PubProjProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubProjProcessMapper {
    int countByExample(PubProjProcessExample example);

    int deleteByExample(PubProjProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(PubProjProcess record);

    int insertSelective(PubProjProcess record);

    List<PubProjProcess> selectByExample(PubProjProcessExample example);

    PubProjProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") PubProjProcess record, @Param("example") PubProjProcessExample example);

    int updateByExample(@Param("record") PubProjProcess record, @Param("example") PubProjProcessExample example);

    int updateByPrimaryKeySelective(PubProjProcess record);

    int updateByPrimaryKey(PubProjProcess record);
}