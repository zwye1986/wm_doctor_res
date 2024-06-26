package com.pinde.sci.dao.hbzy;

import org.apache.ibatis.annotations.Param;

/**
 * Created by pdkj on 2017/10/9.
 */
public interface HbzyTempMapper {
    void backups(@Param("archiveTime")String archiveTime, @Param("sessionNumber")String sessionNumber);

    boolean selectBackupTable(@Param("tableName")String tableName);
}
