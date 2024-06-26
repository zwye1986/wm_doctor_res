package com.pinde.sci.dao.gzzyjxres;

import org.apache.ibatis.annotations.Param;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/7/10
 */
public interface GzStuUserExtMapper {
    //统计当年已报到学员人数
    int countAdmitedStuUsers(@Param("year")String year);
}
