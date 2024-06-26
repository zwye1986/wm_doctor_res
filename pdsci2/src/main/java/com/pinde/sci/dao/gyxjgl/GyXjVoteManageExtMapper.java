package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.model.mo.NydwVoteMain;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/2/7
 */
public interface GyXjVoteManageExtMapper {
    //被投票人详情查询
    List<Map<String,String>> queryElectorList(Map<String, String> map);
    //投票人详情查询
    List<Map<String,String>> queryVoterList(Map<String, String> map);
    //投票管理查询-党员
    List<NydwVoteMain> queryVoteMainList(Map<String, String> map);
}
