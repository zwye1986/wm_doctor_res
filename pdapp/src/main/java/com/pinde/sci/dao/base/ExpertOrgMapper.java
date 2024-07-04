package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertOrg;
import com.pinde.sci.model.mo.ExpertOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpertOrgMapper {
    int countByExample(ExpertOrgExample example);

    int deleteByExample(ExpertOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertOrg record);

    int insertSelective(ExpertOrg record);

    List<ExpertOrg> selectByExample(ExpertOrgExample example);

    ExpertOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertOrg record, @Param("example") ExpertOrgExample example);

    int updateByExample(@Param("record") ExpertOrg record, @Param("example") ExpertOrgExample example);

    int updateByPrimaryKeySelective(ExpertOrg record);

    int updateByPrimaryKey(ExpertOrg record);
}