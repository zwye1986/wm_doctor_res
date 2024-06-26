package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpDoc;
import com.pinde.sci.model.mo.ErpDocExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpDocMapper {
    int countByExample(ErpDocExample example);

    int deleteByExample(ErpDocExample example);

    int deleteByPrimaryKey(String docFlow);

    int insert(ErpDoc record);

    int insertSelective(ErpDoc record);

    List<ErpDoc> selectByExample(ErpDocExample example);

    ErpDoc selectByPrimaryKey(String docFlow);

    int updateByExampleSelective(@Param("record") ErpDoc record, @Param("example") ErpDocExample example);

    int updateByExample(@Param("record") ErpDoc record, @Param("example") ErpDocExample example);

    int updateByPrimaryKeySelective(ErpDoc record);

    int updateByPrimaryKey(ErpDoc record);
}