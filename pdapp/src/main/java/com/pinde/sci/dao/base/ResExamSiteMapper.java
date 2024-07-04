package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResExamSite;
import com.pinde.sci.model.mo.ResExamSiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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