package com.pinde.sci.dao.base;

import com.pinde.core.model.SysAppUserInfo;
import com.pinde.core.model.SysAppUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAppUserInfoMapper {
    int countByExample(SysAppUserInfoExample example);

    int deleteByExample(SysAppUserInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(SysAppUserInfo record);

    int insertSelective(SysAppUserInfo record);

    List<SysAppUserInfo> selectByExample(SysAppUserInfoExample example);

    SysAppUserInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") SysAppUserInfo record, @Param("example") SysAppUserInfoExample example);

    int updateByExample(@Param("record") SysAppUserInfo record, @Param("example") SysAppUserInfoExample example);

    int updateByPrimaryKeySelective(SysAppUserInfo record);

    int updateByPrimaryKey(SysAppUserInfo record);
}