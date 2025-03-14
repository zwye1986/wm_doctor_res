package com.pinde.core.common.sci.dao;

import java.util.List;

import com.pinde.core.model.ResProfessionalInfo;
import com.pinde.core.model.ResProfessionalInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ResProfessionalInfoMapper {
    int countByExample(ResProfessionalInfoExample example);

    int deleteByExample(ResProfessionalInfoExample example);

    int deleteByPrimaryKey(String professionalFlow);

    int insert(ResProfessionalInfo record);

    int insertSelective(ResProfessionalInfo record);

    List<ResProfessionalInfo> selectByExample(ResProfessionalInfoExample example);

    ResProfessionalInfo selectByPrimaryKey(String professionalFlow);

    int updateByExampleSelective(@Param("record") ResProfessionalInfo record, @Param("example") ResProfessionalInfoExample example);

    int updateByExample(@Param("record") ResProfessionalInfo record, @Param("example") ResProfessionalInfoExample example);

    int updateByPrimaryKeySelective(ResProfessionalInfo record);

    int updateByPrimaryKey(ResProfessionalInfo record);
}