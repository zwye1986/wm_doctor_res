package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchArrangeResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int updateAfterPbRequiest(@Param("resultFlow")String resultFlow);
    int updateAfterPbGroup(@Param("resultFlow")String resultFlow);

    Double schMon(@Param("doctorFlow")String doctorFlow,
                   @Param("rotationFlow")String rotationFlow,
                   @Param("standDeptId")String standDeptId,
                   @Param("groupFlow")String groupFlow);
}