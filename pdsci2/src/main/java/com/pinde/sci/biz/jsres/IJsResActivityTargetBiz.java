package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.TeachingActivityEval;
import com.pinde.sci.model.mo.TeachingActivityFormValue;
import com.pinde.sci.model.mo.TeachingActivityInfoTarget;
import com.pinde.sci.model.mo.TeachingActivityTarget;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResActivityTargetBiz {

    List<TeachingActivityTarget> list(Map<String, String> param);

    List<TeachingActivityTarget> listNew(Map<String, String> param);

    int delTarget(String targetFlow);

    TeachingActivityTarget readByName(String orgFlow, String targetName);

    List<TeachingActivityTarget> readByOrg(String orgFlow);

    List<TeachingActivityTarget> readByOrgNew(String activityTypeId,String orgFlow);

    int saveTarget(TeachingActivityTarget add);

    int saveTargetNew(TeachingActivityTarget add);

    List<TeachingActivityInfoTarget> readActivityTargets(String activityFlow);

    List<Map<String,Object>> readActivityTargetEvals(String activityFlow);

    List<TeachingActivityEval> readTeachingEvals(String activityFlow);

    TeachingActivityInfoTarget readActivityTarget(String activityFlow, String targetFlow);

    int saveInfoTarget(TeachingActivityInfoTarget infoTarget);

    TeachingActivityTarget readByFlow(String targetFlow);

    int editFrom(TeachingActivityFormValue formValue);

    List<TeachingActivityFormValue> searchFormValue(TeachingActivityFormValue formValue);

    TeachingActivityTarget readByNameNew(String orgFlow, String trim, String targetType);

    TeachingActivityTarget readByNameNew2(String orgFlow, String trim, String activityTypeId);

    int saveTargetNew2(TeachingActivityTarget activityTarget);

}
