package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpContract;
import com.pinde.sci.model.mo.GcpContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpContractMapper {
    int countByExample(GcpContractExample example);

    int deleteByExample(GcpContractExample example);

    int deleteByPrimaryKey(String contractFlow);

    int insert(GcpContract record);

    int insertSelective(GcpContract record);

    List<GcpContract> selectByExample(GcpContractExample example);

    GcpContract selectByPrimaryKey(String contractFlow);

    int updateByExampleSelective(@Param("record") GcpContract record, @Param("example") GcpContractExample example);

    int updateByExample(@Param("record") GcpContract record, @Param("example") GcpContractExample example);

    int updateByPrimaryKeySelective(GcpContract record);

    int updateByPrimaryKey(GcpContract record);
}