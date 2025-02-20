package com.pinde.core.common.sci.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchArrangeResultExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SchArrangeResultMapper extends BaseMapper<SchArrangeResult> {
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

    int updateAfterPbRequiest(@Param("resultFlow") String resultFlow);

    int updateAfterPbGroup(@Param("resultFlow") String resultFlow);

    Double schMon(@Param("doctorFlow") String doctorFlow,
                  @Param("rotationFlow") String rotationFlow,
                  @Param("standDeptId") String standDeptId,
                  @Param("groupFlow") String groupFlow);

    SchArrangeResult infoByDeptFlowSchMon(@Param("schDeptFlow") String schDeptFlow,
                                          @Param("schStartDate") String schStartDate,
                                          @Param("schEndDate") String schEndDate,
                                          @Param("doctorFlow") String doctorFlow);
    List<SchArrangeResult> searchArrangeResultByDateAndOrgByMapNew(Map<String, Object> map);

    @Select("SELECT R.DOCTOR_FLOW, R.ROTATION_FLOW, D.RECORD_FLOW" +
            " FROM SCH_ARRANGE_RESULT R LEFT JOIN SCH_ROTATION_DEPT D " +
            " ON R.STANDARD_DEPT_ID = D.STANDARD_DEPT_ID " +
            " AND D.GROUP_FLOW = R.STANDARD_GROUP_FLOW" +
            " AND R.RECORD_STATUS = 'Y'  " +
            " AND D.RECORD_STATUS = 'Y' " +
            " ${ew.customSqlSegment} ")
    List<Map<String, String>> selectArrangeResultWithRotationDept(@Param(Constants.WRAPPER) LambdaQueryWrapper<SchArrangeResult> queryWrapper);
}