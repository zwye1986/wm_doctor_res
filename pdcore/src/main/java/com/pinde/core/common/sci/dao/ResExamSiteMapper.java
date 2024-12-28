package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResExamSite;
import com.pinde.core.model.ResExamSiteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResExamSiteMapper {
    int countByExample(ResExamSiteExample example);

    int deleteByExample(ResExamSiteExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResExamSite record);

    int insertSelective(ResExamSite record);

    List<ResExamSite> selectByExample(ResExamSiteExample example);

    ResExamSite selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResExamSite record, @Param("example") ResExamSiteExample example);

    int updateByExample(@Param("record") ResExamSite record, @Param("example") ResExamSiteExample example);

    int updateByPrimaryKeySelective(ResExamSite record);

    int updateByPrimaryKey(ResExamSite record);
}