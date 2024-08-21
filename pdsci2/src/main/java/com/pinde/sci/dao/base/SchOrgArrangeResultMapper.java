package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchOrgArrangeResult;
import com.pinde.sci.model.mo.SchOrgArrangeResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchOrgArrangeResultMapper {
    int countByExample(SchOrgArrangeResultExample example);

    int deleteByExample(SchOrgArrangeResultExample example);

    int deleteByPrimaryKey(String arrangeFlow);

    int insert(SchOrgArrangeResult record);

    int insertSelective(SchOrgArrangeResult record);

    List<SchOrgArrangeResult> selectByExample(SchOrgArrangeResultExample example);

    SchOrgArrangeResult selectByPrimaryKey(String arrangeFlow);

    int updateByExampleSelective(@Param("record") SchOrgArrangeResult record, @Param("example") SchOrgArrangeResultExample example);

    int updateByExample(@Param("record") SchOrgArrangeResult record, @Param("example") SchOrgArrangeResultExample example);

    int updateByPrimaryKeySelective(SchOrgArrangeResult record);

    int updateByPrimaryKey(SchOrgArrangeResult record);
}