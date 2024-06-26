package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpProductManage;
import com.pinde.sci.model.mo.ErpProductManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpProductManageMapper {
    int countByExample(ErpProductManageExample example);

    int deleteByExample(ErpProductManageExample example);

    int deleteByPrimaryKey(String manageFlow);

    int insert(ErpProductManage record);

    int insertSelective(ErpProductManage record);

    List<ErpProductManage> selectByExample(ErpProductManageExample example);

    ErpProductManage selectByPrimaryKey(String manageFlow);

    int updateByExampleSelective(@Param("record") ErpProductManage record, @Param("example") ErpProductManageExample example);

    int updateByExample(@Param("record") ErpProductManage record, @Param("example") ErpProductManageExample example);

    int updateByPrimaryKeySelective(ErpProductManage record);

    int updateByPrimaryKey(ErpProductManage record);
}