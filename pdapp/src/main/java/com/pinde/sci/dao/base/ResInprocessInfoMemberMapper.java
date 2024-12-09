package com.pinde.sci.dao.base;

import com.pinde.core.model.ResInprocessInfoMember;
import com.pinde.core.model.ResInprocessInfoMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResInprocessInfoMemberMapper {
    int countByExample(ResInprocessInfoMemberExample example);

    int deleteByExample(ResInprocessInfoMemberExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResInprocessInfoMember record);

    int insertSelective(ResInprocessInfoMember record);

    List<ResInprocessInfoMember> selectByExample(ResInprocessInfoMemberExample example);

    ResInprocessInfoMember selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResInprocessInfoMember record, @Param("example") ResInprocessInfoMemberExample example);

    int updateByExample(@Param("record") ResInprocessInfoMember record, @Param("example") ResInprocessInfoMemberExample example);

    int updateByPrimaryKeySelective(ResInprocessInfoMember record);

    int updateByPrimaryKey(ResInprocessInfoMember record);
}