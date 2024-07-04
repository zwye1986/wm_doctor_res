package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ScresGraduationTicket;
import com.pinde.sci.model.mo.ScresGraduationTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScresGraduationTicketMapper {
    int countByExample(ScresGraduationTicketExample example);

    int deleteByExample(ScresGraduationTicketExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ScresGraduationTicket record);

    int insertSelective(ScresGraduationTicket record);

    List<ScresGraduationTicket> selectByExample(ScresGraduationTicketExample example);

    ScresGraduationTicket selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ScresGraduationTicket record, @Param("example") ScresGraduationTicketExample example);

    int updateByExample(@Param("record") ScresGraduationTicket record, @Param("example") ScresGraduationTicketExample example);

    int updateByPrimaryKeySelective(ScresGraduationTicket record);

    int updateByPrimaryKey(ScresGraduationTicket record);
}