package com.pinde.sci.dao.res;

import com.pinde.core.model.ResMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResMessageExtMapper {

    List<Map<String,Object>> findMessageOrgAndCount(@Param("sessionNumber") String sessionNumber);

    List<Map<String,Object>> findMessageOrgAndTime(@Param("time") String time);

    List<ResMessage> findMessageByOrgFlow(@Param("orgFlow")String orgFlow,@Param("sessionNumber")String sessionNumber);

}
