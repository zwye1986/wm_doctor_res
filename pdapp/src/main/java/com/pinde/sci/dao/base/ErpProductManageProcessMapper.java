package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpProductManageProcess;
import com.pinde.sci.model.mo.ErpProductManageProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpProductManageProcessMapper {
    int countByExample(ErpProductManageProcessExample example);

    int deleteByExample(ErpProductManageProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(ErpProductManageProcess record);

    int insertSelective(ErpProductManageProcess record);

    List<ErpProductManageProcess> selectByExample(ErpProductManageProcessExample example);

    ErpProductManageProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") ErpProductManageProcess record, @Param("example") ErpProductManageProcessExample example);

    int updateByExample(@Param("record") ErpProductManageProcess record, @Param("example") ErpProductManageProcessExample example);

    int updateByPrimaryKeySelective(ErpProductManageProcess record);

    int updateByPrimaryKey(ErpProductManageProcess record);
}