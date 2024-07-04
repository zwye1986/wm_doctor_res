package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduMajorCredit;
import com.pinde.sci.model.mo.EduMajorCreditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduMajorCreditMapper {
    int countByExample(EduMajorCreditExample example);

    int deleteByExample(EduMajorCreditExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduMajorCredit record);

    int insertSelective(EduMajorCredit record);

    List<EduMajorCredit> selectByExample(EduMajorCreditExample example);

    EduMajorCredit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduMajorCredit record, @Param("example") EduMajorCreditExample example);

    int updateByExample(@Param("record") EduMajorCredit record, @Param("example") EduMajorCreditExample example);

    int updateByPrimaryKeySelective(EduMajorCredit record);

    int updateByPrimaryKey(EduMajorCredit record);
}