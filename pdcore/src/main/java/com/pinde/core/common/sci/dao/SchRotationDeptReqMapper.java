package com.pinde.core.common.sci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pinde.core.model.SchRotationDeptReq;
import com.pinde.core.model.SchRotationDeptReqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationDeptReqMapper extends BaseMapper<SchRotationDeptReq> {
    int countByExample(SchRotationDeptReqExample example);

    int deleteByExample(SchRotationDeptReqExample example);

    int deleteByPrimaryKey(String reqFlow);

    int insert(SchRotationDeptReq record);

    int insertSelective(SchRotationDeptReq record);

    List<SchRotationDeptReq> selectByExampleWithBLOBs(SchRotationDeptReqExample example);

    List<SchRotationDeptReq> selectByExample(SchRotationDeptReqExample example);

    List<SchRotationDeptReq> selectListByPartitionList(@Param("relRecordListList") List<List<String>> recordListList);

    SchRotationDeptReq selectByPrimaryKey(String reqFlow);

    int updateByExampleSelective(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByExample(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByPrimaryKeySelective(SchRotationDeptReq record);

    int updateByPrimaryKeyWithBLOBs(SchRotationDeptReq record);

    int updateByPrimaryKey(SchRotationDeptReq record);
}