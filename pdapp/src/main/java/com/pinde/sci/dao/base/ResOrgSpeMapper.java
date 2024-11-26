package com.pinde.sci.dao.base;

import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.ResOrgSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}