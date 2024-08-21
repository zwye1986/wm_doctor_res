package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResInprocessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface ResInprocessInfoMemberExtMapper {


    int deleteMemberNotInFlow(@Param(value = "recordFlow") String recordFlow, @Param(value = "memberFlows")List<String> memberFlows);

    List<ResInprocessInfo> readInfoList(@Param(value = "orgFlow") String orgFlow);

    List<ResInprocessInfo> readInfoList4Global(Map<String ,Object> map);
}
