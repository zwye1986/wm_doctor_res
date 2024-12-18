package com.pinde.sci.biz.res;

import com.pinde.core.model.ResDiscipleInfo;
import com.pinde.core.model.ResDiscipleReq;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface IResDiscipleInfoBiz {

    ResDiscipleInfo readResDiscipleInfo(String userFlow);

    List<ResDiscipleInfo> readResDiscipleInfos(List<String> userFlows);

    String getTeacherName(String userFlow);

    int savaResDiscipleInfo(ResDiscipleInfo bean);

    List<ResDiscipleReq> findResDiscipleReqList(ResDiscipleReq resDiscipleReq);

    ResDiscipleReq findResDiscipleReqByFlow(String recordFlow);

    int updateDiscipleReq(ResDiscipleReq resDiscipleReq);

    List<Map<String,String>> findDiscipleInfoFinbyDocFlow(List<String> discipleDocFlows);

    List<Map<String,String>> findEveDiscipleInfoReq();
}
