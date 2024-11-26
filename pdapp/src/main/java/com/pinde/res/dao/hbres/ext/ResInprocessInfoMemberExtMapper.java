package com.pinde.res.dao.hbres.ext;

import com.pinde.core.model.ResInprocessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface ResInprocessInfoMemberExtMapper {


    int deleteMemberNotInFlow(@Param(value = "recordFlow") String recordFlow, @Param(value = "memberFlows") List<String> memberFlows);

    List<ResInprocessInfo> readInfoList(@Param(value = "orgFlow") String orgFlow);
}
