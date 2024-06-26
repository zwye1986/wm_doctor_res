package com.pinde.sci.biz.res.impl;

import com.pinde.sci.biz.res.IResPerformanceManageBiz;
import com.pinde.sci.dao.res.ResAllowanceUserExtMapper;
import com.pinde.sci.model.res.ResAllowancePayment;
import com.pinde.sci.model.res.ResAllowanceUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResPerformanceManageBizImpl implements IResPerformanceManageBiz {
    @Autowired
    private ResAllowanceUserExtMapper allowanceUserExtMapper;

    @Override
    public List<ResAllowanceUserExt> searchUser(Map<String, Object> param) {
        return allowanceUserExtMapper.searchUser(param);
    }

    @Override
    public List<ResAllowancePayment> searchAllowancePaymentTea(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchAllowancePaymentTea(param2);
    }

    @Override
    public List<ResAllowancePayment> searchAllowancePayment(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchAllowancePayment(param2);
    }

    @Override
    public List<ResAllowancePayment> searchAllowancePaymentSpe(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchAllowancePaymentSpe(param2);
    }

    @Override
    public Map<String, String> searchActivityTea(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchActivityTea(param2);
    }

    @Override
    public Map<String, String> searchActivity(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchActivity(param2);
    }

    @Override
    public Map<String, String> searchActivitySpe(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchActivitySpe(param2);
    }

    @Override
    public Map<String, String> searchLecture(Map<String, Object> param2) {
        return allowanceUserExtMapper.searchLecture(param2);
    }

}
