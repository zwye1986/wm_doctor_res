package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.model.gyxjgl.XjEduUserExt;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/2/6
 */
public interface GyXjPartyBranchExtMapper {
    //查询党员信息
    List<XjEduUserExt> querypartyMembers(Map<String, String> map);
}
