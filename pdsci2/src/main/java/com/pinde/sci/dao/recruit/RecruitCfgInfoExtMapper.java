package com.pinde.sci.dao.recruit;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: 18svn
 * @BelongsPackage: com.pinde.sci.dao.recruit
 * @Author: yex
 * @CreateTime: 2019-04-26 15:18
 * @Description: ${Description}
 */
public interface RecruitCfgInfoExtMapper {
    List<String> searchAllRecruitYear(@Param("orgFlow") String orgFlow);

    void changeIsRecruit(@Param("orgFlow") String orgFlow);
}
