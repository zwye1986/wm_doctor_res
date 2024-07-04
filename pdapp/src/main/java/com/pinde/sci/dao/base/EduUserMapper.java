package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.EduUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduUserMapper {
    int countByExample(EduUserExample example);

    int deleteByExample(EduUserExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(EduUser record);

    int insertSelective(EduUser record);

    List<EduUser> selectByExampleWithBLOBs(EduUserExample example);

    List<EduUser> selectByExample(EduUserExample example);

    EduUser selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") EduUser record, @Param("example") EduUserExample example);

    int updateByExampleWithBLOBs(@Param("record") EduUser record, @Param("example") EduUserExample example);

    int updateByExample(@Param("record") EduUser record, @Param("example") EduUserExample example);

    int updateByPrimaryKeySelective(EduUser record);

    int updateByPrimaryKeyWithBLOBs(EduUser record);

    int updateByPrimaryKey(EduUser record);
}