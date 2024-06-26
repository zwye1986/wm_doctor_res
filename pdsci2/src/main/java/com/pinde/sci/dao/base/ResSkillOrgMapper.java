package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSkillOrg;
import com.pinde.sci.model.mo.ResSkillOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResSkillOrgMapper {
    int countByExample(ResSkillOrgExample example);

    int deleteByExample(ResSkillOrgExample example);

    int deleteByPrimaryKey(String skillOrgFlow);

    int insert(ResSkillOrg record);

    int insertSelective(ResSkillOrg record);

    List<ResSkillOrg> selectByExample(ResSkillOrgExample example);

    ResSkillOrg selectByPrimaryKey(String skillOrgFlow);

    int updateByExampleSelective(@Param("record") ResSkillOrg record, @Param("example") ResSkillOrgExample example);

    int updateByExample(@Param("record") ResSkillOrg record, @Param("example") ResSkillOrgExample example);

    int updateByPrimaryKeySelective(ResSkillOrg record);

    int updateByPrimaryKey(ResSkillOrg record);
}