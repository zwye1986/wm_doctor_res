package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpProductManageUser;
import com.pinde.sci.model.mo.ErpProductManageUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpProductManageUserMapper {
    int countByExample(ErpProductManageUserExample example);

    int deleteByExample(ErpProductManageUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpProductManageUser record);

    int insertSelective(ErpProductManageUser record);

    List<ErpProductManageUser> selectByExample(ErpProductManageUserExample example);

    ErpProductManageUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpProductManageUser record, @Param("example") ErpProductManageUserExample example);

    int updateByExample(@Param("record") ErpProductManageUser record, @Param("example") ErpProductManageUserExample example);

    int updateByPrimaryKeySelective(ErpProductManageUser record);

    int updateByPrimaryKey(ErpProductManageUser record);
}