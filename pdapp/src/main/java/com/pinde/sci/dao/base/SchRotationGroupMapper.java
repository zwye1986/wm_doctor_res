package com.pinde.sci.dao.base;

import com.pinde.core.model.SchRotationGroup;
import com.pinde.core.model.SchRotationGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchRotationGroupMapper {
    int countByExample(SchRotationGroupExample example);

    int deleteByExample(SchRotationGroupExample example);

    int deleteByPrimaryKey(String groupFlow);

    int insert(SchRotationGroup record);

    int insertSelective(SchRotationGroup record);

    List<SchRotationGroup> selectByExample(SchRotationGroupExample example);

    SchRotationGroup selectByPrimaryKey(String groupFlow);

    int updateByExampleSelective(@Param("record") SchRotationGroup record, @Param("example") SchRotationGroupExample example);

    int updateByExample(@Param("record") SchRotationGroup record, @Param("example") SchRotationGroupExample example);

    int updateByPrimaryKeySelective(SchRotationGroup record);

    int updateByPrimaryKey(SchRotationGroup record);
}