package com.pinde.res.biz.jswjw.impl;


import com.pinde.res.biz.jswjw.ISchExamScoreQueryBiz;
import com.pinde.sci.dao.base.JsResSchExamScoreQueryMapper;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchExamScoreQueryBizImpl implements ISchExamScoreQueryBiz {
    @Autowired
    private JsResSchExamScoreQueryMapper scoreQueryMapper;
    @Override
    public List<Map<String, Object>> userList(Map<String, Object> param) {
        return scoreQueryMapper.userList(param);
    }

    @Override
    public List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param) {
        return scoreQueryMapper.getDoctorArrangements(param);
    }


}
