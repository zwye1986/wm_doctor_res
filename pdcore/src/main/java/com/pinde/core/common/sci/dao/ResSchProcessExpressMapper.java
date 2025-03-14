package com.pinde.core.common.sci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.ResSchProcessExpressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSchProcessExpressMapper extends BaseMapper<ResSchProcessExpress> {
    int countByExample(ResSchProcessExpressExample example);

    int deleteByExample(ResSchProcessExpressExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResSchProcessExpress record);

    int insertSelective(ResSchProcessExpress record);

    List<ResSchProcessExpress> selectByExampleWithBLOBs(ResSchProcessExpressExample example);

    List<ResSchProcessExpress> selectByExample(ResSchProcessExpressExample example);

    ResSchProcessExpress selectByPrimaryKey(String recFlow);

    List<ResSchProcessExpress> queryResRecList(@Param("processFlowList") List<String> processFlowList, @Param("operUserFlowList") List<String> operUserFlowList,@Param("recTypeId") String recTypeId);

    int updateByExampleSelective(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByExampleWithBLOBs(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByExample(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByPrimaryKeySelective(ResSchProcessExpress record);

    int updateByPrimaryKeyWithBLOBs(ResSchProcessExpress record);

    int updateByPrimaryKey(ResSchProcessExpress record);

    List<ResSchProcessExpress> listByDoctorList(@Param("doctorFlowList")List<String> doctorFlowList, @Param("schStartDate")String schStartDate, @Param("schEndDate")String schEndDate);
}