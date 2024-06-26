package com.pinde.sci.biz.res;

import com.pinde.sci.model.res.ResAllowancePayment;
import com.pinde.sci.model.res.ResAllowanceUserExt;

import java.util.List;
import java.util.Map;

public interface IResPerformanceManageBiz {

    List<ResAllowanceUserExt> searchUser(Map<String, Object> param);

    List<ResAllowancePayment> searchAllowancePaymentTea(Map<String, Object> param2);

    List<ResAllowancePayment> searchAllowancePayment(Map<String, Object> param2);

    List<ResAllowancePayment> searchAllowancePaymentSpe(Map<String, Object> param2);

    Map<String, String> searchActivityTea(Map<String, Object> param2);

    Map<String, String> searchActivity(Map<String, Object> param2);

    Map<String, String> searchActivitySpe(Map<String, Object> param2);

    Map<String, String> searchLecture(Map<String, Object> param2);

}
