package com.pinde.sci.biz.sch;

import com.pinde.sci.form.sch.SchRotationOrgGroupForm;
import com.pinde.core.model.SchRotationOrgCfg;
import com.pinde.core.model.SchRotationOrgDept;
import com.pinde.core.model.SchRotationOrgGroup;

import java.util.List;
import java.util.Map;

public interface ISchRotationCfgBiz {


    List<SchRotationOrgGroup> searchSchRotationOrgGroup(Map<String, String> tempMap);

    int saveSchRotationOrgGroup(SchRotationOrgGroup newGroup);

    List<SchRotationOrgDept> searchSchRotationOrgDept(Map<String, String> paramMap2);

    int saveSchRotationOrgDept(SchRotationOrgDept newDept);

    int checkRotationCfg(String orgFlow, String selectYear, String sessionNumber, String rotationFlow);

    SchRotationOrgCfg readOrgRotationCycleType(String rotationFlow, String orgFlow, String sessionNumber);

    int checkSelNum(String orgFlow, String sessionNumber, String rotationFlow);

    int checkSchNum(String orgFlow, String sessionNumber, String rotationFlow);

    void saveCycleCfg(String orgFlow, String sessionNumber, String rotationFlow, String cycleTypeId);

    void saveCfg(List<SchRotationOrgGroupForm> groupForms, String orgFlow, String selectYear, String sessionNumber, String rotationFlow) throws Exception;
}
