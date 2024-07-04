package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchRotationDeptReq;
import com.pinde.sci.model.mo.SchRotationDeptReqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchRotationDeptReqMapper {
    int countByExample(SchRotationDeptReqExample example);

    int deleteByExample(SchRotationDeptReqExample example);

    int deleteByPrimaryKey(String reqFlow);

    int insert(SchRotationDeptReq record);

    int insertSelective(SchRotationDeptReq record);

    List<SchRotationDeptReq> selectByExampleWithBLOBs(SchRotationDeptReqExample example);

    List<SchRotationDeptReq> selectByExample(SchRotationDeptReqExample example);

    SchRotationDeptReq selectByPrimaryKey(String reqFlow);

    int updateByExampleSelective(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByExample(@Param("record") SchRotationDeptReq record, @Param("example") SchRotationDeptReqExample example);

    int updateByPrimaryKeySelective(SchRotationDeptReq record);

    int updateByPrimaryKeyWithBLOBs(SchRotationDeptReq record);

    int updateByPrimaryKey(SchRotationDeptReq record);
}