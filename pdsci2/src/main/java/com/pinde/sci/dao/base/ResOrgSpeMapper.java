package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.ResOrgSpeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgSpeMapper {
    int countByExample(ResOrgSpeExample example);

    int deleteByExample(ResOrgSpeExample example);

    int deleteByPrimaryKey(String orgSpeFlow);

    int insert(ResOrgSpe record);

    int insertSelective(ResOrgSpe record);

    List<ResOrgSpe> selectByExample(ResOrgSpeExample example);

    ResOrgSpe selectByPrimaryKey(String orgSpeFlow);

    int updateByExampleSelective(@Param("record") ResOrgSpe record, @Param("example") ResOrgSpeExample example);

    int updateByExample(@Param("record") ResOrgSpe record, @Param("example") ResOrgSpeExample example);

    int updateByPrimaryKeySelective(ResOrgSpe record);

    int updateByPrimaryKey(ResOrgSpe record);

    int batchInsert(@Param("resOrgSpeList") List<ResOrgSpe> resOrgSpeList);

    int batchUpdateSelective(@Param("resOrgSpeList") List<ResOrgSpe> resOrgSpeList);
}