package com.pinde.sci.dao.edc;

import com.pinde.sci.model.edc.RandomDrugGroupForm;

import java.util.List;
import java.util.Map;


public interface EdcRandomRecExtMapper {
    List<RandomDrugGroupForm> searchDurgGroups(String projFlow);

    void updateBlock(Map<String, Object> map);

    void resetBlock(Map<String, Object> map);

    Integer searchMaxVisit(String projFlow);

    Integer searchMaxVisitFollow(Map<String, Object> map);
}