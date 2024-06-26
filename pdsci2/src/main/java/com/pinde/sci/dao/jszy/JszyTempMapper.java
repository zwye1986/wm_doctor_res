package com.pinde.sci.dao.jszy;

import org.apache.ibatis.annotations.Param;

/**
 * Created by pdkj on 2017/10/9.
 */
public interface JszyTempMapper {
    void backups(@Param("archiveTime")String archiveTime, @Param("sessionNumber")String sessionNumber);

    boolean selectBackupTable(@Param("tableName")String tableName);
}
