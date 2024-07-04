package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpDocLog;
import com.pinde.sci.model.mo.ErpDocLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpDocLogMapper {
    int countByExample(ErpDocLogExample example);

    int deleteByExample(ErpDocLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(ErpDocLog record);

    int insertSelective(ErpDocLog record);

    List<ErpDocLog> selectByExample(ErpDocLogExample example);

    ErpDocLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") ErpDocLog record, @Param("example") ErpDocLogExample example);

    int updateByExample(@Param("record") ErpDocLog record, @Param("example") ErpDocLogExample example);

    int updateByPrimaryKeySelective(ErpDocLog record);

    int updateByPrimaryKey(ErpDocLog record);
}