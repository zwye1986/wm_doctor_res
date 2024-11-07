package com.pinde.res.biz.stdp;

import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeachingActivityFormValue;
import com.pinde.sci.model.mo.TeachingActivityInfoTarget;
import com.pinde.sci.model.mo.TeachingActivityTarget;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IResActivityTargetBiz {

    List<TeachingActivityTarget> list(Map<String, String> param);

    int delTarget(String targetFlow);

    TeachingActivityTarget readByName(String orgFlow, String targetName);
    List<TeachingActivityTarget> readByOrg(String orgFlow);

    List<TeachingActivityInfoTarget> readActivityTargets(String activityFlow);

    List<Map<String,Object>> readActivityTargetEvals(String activityFlow);

    TeachingActivityInfoTarget readActivityTarget(String activityFlow, String targetFlow);

    int saveInfoTarget(TeachingActivityInfoTarget infoTarget, SysUser user);

    TeachingActivityTarget readByFlow(String targetFlow);

    List<TeachingActivityFormValue> activityFormValues(String activityFlow);

    List<TeachingActivityTarget> readByOrgNew(String activityTypeId, String orgFlow);

    List<String> selectJointOrgFlow(String userFlow);
}
