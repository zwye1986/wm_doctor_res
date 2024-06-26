package com.pinde.sci.biz.gyxjgl.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduStudentCourseBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjImportRecordBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.PubImportRecordMapper;
import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.PubImportRecord;
import com.pinde.sci.model.mo.PubImportRecordExample;
import com.pinde.sci.model.mo.PubImportRecordExample.Criteria;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjImportRecordBiz implements IGyXjImportRecordBiz {
    @Autowired
    private PubImportRecordMapper importRecordMapper;
    @Autowired
    private IGyXjEduStudentCourseBiz studentCourseBiz;

    @Override
    public List<PubImportRecord> searchImportRecordList(PubImportRecord importRecord) {
        PubImportRecordExample example = new PubImportRecordExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(importRecord.getImpTypeId())) {
            criteria.andImpTypeIdEqualTo(importRecord.getImpTypeId());
        }
        if (StringUtil.isNotBlank(importRecord.getRoleFlag())) {
            criteria.andRoleFlagEqualTo(importRecord.getRoleFlag());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return importRecordMapper.selectByExample(example);
    }

    @Override
    public int addRecord(PubImportRecord importRecord) {
        SysUser currUser = GlobalContext.getCurrentUser();
        importRecord.setImpUserFlow(currUser.getUserFlow());
        importRecord.setImpUserName(currUser.getUserName());
        GeneralMethod.setRecordInfo(importRecord, true);
        return importRecordMapper.insert(importRecord);
    }

    @Override
    public int delImpRecord(String impFlow) {
        if (StringUtil.isNotBlank(impFlow)) {
            EduStudentCourse studentCourse = new EduStudentCourse();
            studentCourse.setImpFlow(impFlow);
            List<EduStudentCourse> studentCourseList = studentCourseBiz.searchStudentCourseList(studentCourse);
            if (studentCourseList != null && !studentCourseList.isEmpty()) {
                for (EduStudentCourse sc : studentCourseList) {
                    sc.setRecordStatus(GlobalConstant.FLAG_N);
                    studentCourseBiz.save(sc);
                }
            }
            PubImportRecord importRecord = new PubImportRecord();
            importRecord.setImpFlow(impFlow);
            importRecord.setRecordStatus(GlobalConstant.FLAG_N);
            return update(importRecord);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int update(PubImportRecord importRecord) {
        if (StringUtil.isNotBlank(importRecord.getImpFlow())) {
            GeneralMethod.setRecordInfo(importRecord, false);
            return importRecordMapper.updateByPrimaryKeySelective(importRecord);
        }
        return GlobalConstant.ZERO_LINE;
    }

}
