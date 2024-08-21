package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResInprocessInfoMember;
import com.pinde.sci.model.mo.ResInprocessInfoMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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