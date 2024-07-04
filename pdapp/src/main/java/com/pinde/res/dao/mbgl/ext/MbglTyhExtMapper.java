package com.pinde.res.dao.mbgl.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016/7/18.
 */
public interface MbglTyhExtMapper {
    List<Map<String,Object>> userList();

    void updateRingInfo(@Param("yhm") String yhm, @Param("s") String s);

    Map<String,Object> selectByCode(@Param("userCode") String userCode);

    List<Map<String,Object>> readOtherUsers(@Param("userCode") String userCode);
}
