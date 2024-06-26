package com.pinde.sci.biz.gyxjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduCoursePeriodRefBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduCoursePeriodRefMapper;
import com.pinde.sci.model.mo.EduCoursePeriodRef;
import com.pinde.sci.model.mo.EduCoursePeriodRefExample;
import com.pinde.sci.model.mo.EduCoursePeriodRefExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjEduCoursePeriodRefBizImpl implements IGyXjEduCoursePeriodRefBiz {

    @Autowired
    private EduCoursePeriodRefMapper periodRefMapper;

    @Override
    public int savePeriodRef(EduCoursePeriodRef ref) {
        if (StringUtil.isNotBlank(ref.getRecordFlow())) {
            GeneralMethod.setRecordInfo(ref, false);
            return periodRefMapper.updateByPrimaryKeySelective(ref);
        } else {
            ref.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(ref, true);
            return periodRefMapper.insert(ref);
        }
    }

    @Override
    public String savePeriodRefs(List<EduCoursePeriodRef> refList,
                                 String courseFlow, String refTypeId) {
        //先删除该课程下计学时人员范围记录
        EduCoursePeriodRef periodRef = new EduCoursePeriodRef();
        periodRef.setCourseFlow(courseFlow);
        periodRef.setRefTypeId(refTypeId);
        List<EduCoursePeriodRef> refs = searchPeriodRefs(periodRef);
        deletePeriodRefs(refs);
        //保存计学时人员范围记录
        if (refList != null && !refList.isEmpty()) {
            for (EduCoursePeriodRef ref : refList) {
                ref.setCourseFlow(courseFlow);
                savePeriodRef(ref);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @Override
    public List<EduCoursePeriodRef> searchPeriodRefs(EduCoursePeriodRef ref) {
        EduCoursePeriodRefExample example = new EduCoursePeriodRefExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(ref.getCourseFlow())) {
            criteria.andCourseFlowEqualTo(ref.getCourseFlow());
        }
        if (StringUtil.isNotBlank(ref.getRefTypeId())) {
            criteria.andRefTypeIdEqualTo(ref.getRefTypeId());
        }
        return periodRefMapper.selectByExample(example);
    }

    @Override
    public EduCoursePeriodRef readPeriodRef(String recordFlow) {
        return periodRefMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public String deletePeriodRef(EduCoursePeriodRef ref) {
        if (ref != null) {
            ref.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(ref, false);
            periodRefMapper.updateByPrimaryKeySelective(ref);
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @Override
    public String deletePeriodRefs(List<EduCoursePeriodRef> refList) {
        if (refList != null && !refList.isEmpty()) {
            for (EduCoursePeriodRef ref : refList) {
                deletePeriodRef(ref);
            }
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }
}
