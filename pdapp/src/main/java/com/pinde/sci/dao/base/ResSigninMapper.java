package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSignin;
import com.pinde.sci.model.mo.ResSigninExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResSigninMapper {
    int countByExample(ResSigninExample example);

    int deleteByExample(ResSigninExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResSignin record);

    int insertSelective(ResSignin record);

    List<ResSignin> selectByExample(ResSigninExample example);

    ResSignin selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResSignin record, @Param("example") ResSigninExample example);

    int updateByExample(@Param("record") ResSignin record, @Param("example") ResSigninExample example);

    int updateByPrimaryKeySelective(ResSignin record);

    int updateByPrimaryKey(ResSignin record);
}