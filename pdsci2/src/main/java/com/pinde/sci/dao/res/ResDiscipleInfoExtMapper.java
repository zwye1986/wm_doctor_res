package com.pinde.sci.dao.res;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface ResDiscipleInfoExtMapper {
    Map<String,String> getTeacherNames(String userFlow);

    List<Map<String,String>> searchDiscipleInfoFinbyDocFlow(@Param("discipleDocFlows") List<String> discipleDocFlows);

    List<Map<String,String>> findEveDiscipleInfoReq();
}
