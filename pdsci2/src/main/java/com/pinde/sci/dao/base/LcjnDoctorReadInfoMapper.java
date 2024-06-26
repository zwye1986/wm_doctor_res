package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnDoctorReadInfo;
import com.pinde.sci.model.mo.LcjnDoctorReadInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnDoctorReadInfoMapper {
    int countByExample(LcjnDoctorReadInfoExample example);

    int deleteByExample(LcjnDoctorReadInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnDoctorReadInfo record);

    int insertSelective(LcjnDoctorReadInfo record);

    List<LcjnDoctorReadInfo> selectByExample(LcjnDoctorReadInfoExample example);

    LcjnDoctorReadInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnDoctorReadInfo record, @Param("example") LcjnDoctorReadInfoExample example);

    int updateByExample(@Param("record") LcjnDoctorReadInfo record, @Param("example") LcjnDoctorReadInfoExample example);

    int updateByPrimaryKeySelective(LcjnDoctorReadInfo record);

    int updateByPrimaryKey(LcjnDoctorReadInfo record);
}