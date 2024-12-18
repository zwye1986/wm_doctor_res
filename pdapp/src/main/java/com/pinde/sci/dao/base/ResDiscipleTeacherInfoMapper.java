package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDiscipleTeacherInfo;
import com.pinde.core.model.ResDiscipleTeacherInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDiscipleTeacherInfoMapper {
    int countByExample(ResDiscipleTeacherInfoExample example);

    int deleteByExample(ResDiscipleTeacherInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDiscipleTeacherInfo record);

    int insertSelective(ResDiscipleTeacherInfo record);

    List<ResDiscipleTeacherInfo> selectByExampleWithBLOBs(ResDiscipleTeacherInfoExample example);

    List<ResDiscipleTeacherInfo> selectByExample(ResDiscipleTeacherInfoExample example);

    ResDiscipleTeacherInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDiscipleTeacherInfo record, @Param("example") ResDiscipleTeacherInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDiscipleTeacherInfo record, @Param("example") ResDiscipleTeacherInfoExample example);

    int updateByExample(@Param("record") ResDiscipleTeacherInfo record, @Param("example") ResDiscipleTeacherInfoExample example);

    int updateByPrimaryKeySelective(ResDiscipleTeacherInfo record);

    int updateByPrimaryKeyWithBLOBs(ResDiscipleTeacherInfo record);

    int updateByPrimaryKey(ResDiscipleTeacherInfo record);
}