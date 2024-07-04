package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydsOfficialDoctor;
import com.pinde.sci.model.mo.NydsOfficialDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydsOfficialDoctorMapper {
    int countByExample(NydsOfficialDoctorExample example);

    int deleteByExample(NydsOfficialDoctorExample example);

    int deleteByPrimaryKey(String doctorFlow);

    int insert(NydsOfficialDoctor record);

    int insertSelective(NydsOfficialDoctor record);

    List<NydsOfficialDoctor> selectByExample(NydsOfficialDoctorExample example);

    NydsOfficialDoctor selectByPrimaryKey(String doctorFlow);

    int updateByExampleSelective(@Param("record") NydsOfficialDoctor record, @Param("example") NydsOfficialDoctorExample example);

    int updateByExample(@Param("record") NydsOfficialDoctor record, @Param("example") NydsOfficialDoctorExample example);

    int updateByPrimaryKeySelective(NydsOfficialDoctor record);

    int updateByPrimaryKey(NydsOfficialDoctor record);
}