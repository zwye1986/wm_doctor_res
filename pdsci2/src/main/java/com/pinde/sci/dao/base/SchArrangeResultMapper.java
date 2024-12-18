package com.pinde.sci.dao.base;

import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchArrangeResultExample;
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

    SchArrangeResult infoByDeptFlowSchMon(@Param("schDeptFlow")String schDeptFlow,
                                          @Param("schStartDate")String schStartDate,
                                          @Param("schEndDate")String schEndDate,
                                          @Param("doctorFlow")String doctorFlow);
}