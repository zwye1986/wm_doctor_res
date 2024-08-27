package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRingLetterUser;
import com.pinde.sci.model.mo.ResRingLetterUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRingLetterUserMapper {
    int countByExample(ResRingLetterUserExample example);

    int deleteByExample(ResRingLetterUserExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(ResRingLetterUser record);

    int insertSelective(ResRingLetterUser record);

    List<ResRingLetterUser> selectByExample(ResRingLetterUserExample example);

    ResRingLetterUser selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") ResRingLetterUser record, @Param("example") ResRingLetterUserExample example);

    int updateByExample(@Param("record") ResRingLetterUser record, @Param("example") ResRingLetterUserExample example);

    int updateByPrimaryKeySelective(ResRingLetterUser record);

    int updateByPrimaryKey(ResRingLetterUser record);
}