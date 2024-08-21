package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSchProcessExpress;
import com.pinde.sci.model.mo.ResSchProcessExpressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSchProcessExpressMapper {
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
}