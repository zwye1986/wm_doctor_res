package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcAttribute;
import com.pinde.sci.model.mo.EdcAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcAttributeMapper {
    int countByExample(EdcAttributeExample example);

    int deleteByExample(EdcAttributeExample example);

    int deleteByPrimaryKey(String attrFlow);

    int insert(EdcAttribute record);

    int insertSelective(EdcAttribute record);

    List<EdcAttribute> selectByExample(EdcAttributeExample example);

    EdcAttribute selectByPrimaryKey(String attrFlow);

    int updateByExampleSelective(@Param("record") EdcAttribute record, @Param("example") EdcAttributeExample example);

    int updateByExample(@Param("record") EdcAttribute record, @Param("example") EdcAttributeExample example);

    int updateByPrimaryKeySelective(EdcAttribute record);

    int updateByPrimaryKey(EdcAttribute record);
}