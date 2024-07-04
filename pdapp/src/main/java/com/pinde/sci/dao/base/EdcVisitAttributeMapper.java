package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisitAttribute;
import com.pinde.sci.model.mo.EdcVisitAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitAttributeMapper {
    int countByExample(EdcVisitAttributeExample example);

    int deleteByExample(EdcVisitAttributeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcVisitAttribute record);

    int insertSelective(EdcVisitAttribute record);

    List<EdcVisitAttribute> selectByExample(EdcVisitAttributeExample example);

    EdcVisitAttribute selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcVisitAttribute record, @Param("example") EdcVisitAttributeExample example);

    int updateByExample(@Param("record") EdcVisitAttribute record, @Param("example") EdcVisitAttributeExample example);

    int updateByPrimaryKeySelective(EdcVisitAttribute record);

    int updateByPrimaryKey(EdcVisitAttribute record);
}