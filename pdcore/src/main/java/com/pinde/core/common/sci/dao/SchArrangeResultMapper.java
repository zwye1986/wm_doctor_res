package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchArrangeResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchArrangeResultMapper {
    int countByExample(SchArrangeResultExample example);

    int deleteByExample(SchArrangeResultExample example);

    int deleteByPrimaryKey(String resultFlow);

    int insert(SchArrangeResult record);

    int insertSelective(SchArrangeResult record);

    List<SchArrangeResult> selectByExample(SchArrangeResultExample example);

    SchArrangeResult selectByPrimaryKey(String resultFlow);

    int updateByExampleSelective(@Param("record") SchArrangeResult record, @Param("example") SchArrangeResultExample example);

    int updateByExample(@Param("record") SchArrangeResult record, @Param("example") SchArrangeResultExample example);

    int updateByPrimaryKeySelective(SchArrangeResult record);

    int updateByPrimaryKey(SchArrangeResult record);

    List<SchArrangeResult> searchArrangeResultByDateAndOrgByMapNew(Map<String, Object> map);
}