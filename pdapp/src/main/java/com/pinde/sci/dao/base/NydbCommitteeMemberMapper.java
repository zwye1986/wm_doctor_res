package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydbCommitteeMember;
import com.pinde.sci.model.mo.NydbCommitteeMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydbCommitteeMemberMapper {
    int countByExample(NydbCommitteeMemberExample example);

    int deleteByExample(NydbCommitteeMemberExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydbCommitteeMember record);

    int insertSelective(NydbCommitteeMember record);

    List<NydbCommitteeMember> selectByExampleWithBLOBs(NydbCommitteeMemberExample example);

    List<NydbCommitteeMember> selectByExample(NydbCommitteeMemberExample example);

    NydbCommitteeMember selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydbCommitteeMember record, @Param("example") NydbCommitteeMemberExample example);

    int updateByExampleWithBLOBs(@Param("record") NydbCommitteeMember record, @Param("example") NydbCommitteeMemberExample example);

    int updateByExample(@Param("record") NydbCommitteeMember record, @Param("example") NydbCommitteeMemberExample example);

    int updateByPrimaryKeySelective(NydbCommitteeMember record);

    int updateByPrimaryKeyWithBLOBs(NydbCommitteeMember record);

    int updateByPrimaryKey(NydbCommitteeMember record);
}