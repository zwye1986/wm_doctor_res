package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PersonnelStatisticsByName;

import java.util.List;

public interface PersonnelCollaborativeBaseMapper {

    /**
     * 获取到协同基地的orgFlow
     * @param orgName
     * @return
     */
    List<PersonnelStatisticsByName> selectCollaborativeBaseFlow(String orgName);
}
