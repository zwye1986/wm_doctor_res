package com.pinde.sci.dao.base;

import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.ResSchProcessExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResSchProcessExpressMapper {
    int countByExample(ResSchProcessExpressExample example);

    int deleteByExample(ResSchProcessExpressExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResSchProcessExpress record);

    int insertSelective(ResSchProcessExpress record);

    List<ResSchProcessExpress> selectByExampleWithBLOBs(ResSchProcessExpressExample example);

    List<ResSchProcessExpress> selectByExample(ResSchProcessExpressExample example);

    ResSchProcessExpress selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByExampleWithBLOBs(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByExample(@Param("record") ResSchProcessExpress record, @Param("example") ResSchProcessExpressExample example);

    int updateByPrimaryKeySelective(ResSchProcessExpress record);

    int updateByPrimaryKeyWithBLOBs(ResSchProcessExpress record);

    int updateByPrimaryKey(ResSchProcessExpress record);
}