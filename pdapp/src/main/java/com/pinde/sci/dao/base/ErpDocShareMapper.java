package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpDocShare;
import com.pinde.sci.model.mo.ErpDocShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpDocShareMapper {
    int countByExample(ErpDocShareExample example);

    int deleteByExample(ErpDocShareExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpDocShare record);

    int insertSelective(ErpDocShare record);

    List<ErpDocShare> selectByExample(ErpDocShareExample example);

    ErpDocShare selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpDocShare record, @Param("example") ErpDocShareExample example);

    int updateByExample(@Param("record") ErpDocShare record, @Param("example") ErpDocShareExample example);

    int updateByPrimaryKeySelective(ErpDocShare record);

    int updateByPrimaryKey(ErpDocShare record);
}