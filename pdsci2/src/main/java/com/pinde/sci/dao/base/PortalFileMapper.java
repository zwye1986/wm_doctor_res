package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PortalFile;
import com.pinde.sci.model.mo.PortalFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalFileMapper {
    int countByExample(PortalFileExample example);

    int deleteByExample(PortalFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PortalFile record);

    int insertSelective(PortalFile record);

    List<PortalFile> selectByExample(PortalFileExample example);

    PortalFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PortalFile record, @Param("example") PortalFileExample example);

    int updateByExample(@Param("record") PortalFile record, @Param("example") PortalFileExample example);

    int updateByPrimaryKeySelective(PortalFile record);

    int updateByPrimaryKey(PortalFile record);
}