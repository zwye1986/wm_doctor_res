package com.pinde.sci.dao.base;

import com.pinde.core.model.ResQualifiedPlanMsg;
import com.pinde.core.model.ResQualifiedPlanMsgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResQualifiedPlanMsgMapper {
    int countByExample(ResQualifiedPlanMsgExample example);

    int deleteByExample(ResQualifiedPlanMsgExample example);

    int deleteByPrimaryKey(String msgFlow);

    int insert(ResQualifiedPlanMsg record);

    int insertSelective(ResQualifiedPlanMsg record);

    List<ResQualifiedPlanMsg> selectByExample(ResQualifiedPlanMsgExample example);

    ResQualifiedPlanMsg selectByPrimaryKey(String msgFlow);

    int updateByExampleSelective(@Param("record") ResQualifiedPlanMsg record, @Param("example") ResQualifiedPlanMsgExample example);

    int updateByExample(@Param("record") ResQualifiedPlanMsg record, @Param("example") ResQualifiedPlanMsgExample example);

    int updateByPrimaryKeySelective(ResQualifiedPlanMsg record);

    int updateByPrimaryKey(ResQualifiedPlanMsg record);
}