package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjEduCourseScoreRefBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduCourseScoreRefMapper;
import com.pinde.sci.model.mo.EduCourseScoreRef;
import com.pinde.sci.model.mo.EduCourseScoreRefExample;
import com.pinde.sci.model.mo.EduCourseScoreRefExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjEduCourseScoreRefBizImpl implements IXjEduCourseScoreRefBiz {

    @Autowired
    private EduCourseScoreRefMapper scoreRefMapper;

    @Override
    public int saveScoreRef(EduCourseScoreRef ref) {
        if (StringUtil.isNotBlank(ref.getRecordFlow())) {
            GeneralMethod.setRecordInfo(ref, false);
            return scoreRefMapper.updateByPrimaryKeySelective(ref);
        } else {
            ref.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(ref, true);
            return scoreRefMapper.insert(ref);
        }
    }

    @Override
    public String saveScoreRefs(List<EduCourseScoreRef> refList,
                                String courseFlow, String refTypeId) {
        //先删除该课程下计分人员范围记录
        EduCourseScoreRef scoreRef = new EduCourseScoreRef();
        scoreRef.setCourseFlow(courseFlow);
        scoreRef.setRefTypeId(refTypeId);
        List<EduCourseScoreRef> refs = searchScoreRefs(scoreRef);
        deleteScoreRefs(refs);
        //保存计分人员范围记录
        if (refList != null && !refList.isEmpty()) {
            for (EduCourseScoreRef ref : refList) {
                ref.setCourseFlow(courseFlow);
                saveScoreRef(ref);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @Override
    public List<EduCourseScoreRef> searchScoreRefs(EduCourseScoreRef ref) {
        EduCourseScoreRefExample example = new EduCourseScoreRefExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(ref.getCourseFlow())) {
            criteria.andCourseFlowEqualTo(ref.getCourseFlow());
        }
        if (StringUtil.isNotBlank(ref.getRefTypeId())) {
            criteria.andRefTypeIdEqualTo(ref.getRefTypeId());
        }
        return scoreRefMapper.selectByExample(example);
    }

//	@Override
//	public EduCourseScoreRef readScoreRef(String recordFlow) {
//		return scoreRefMapper.selectByPrimaryKey(recordFlow);
//	}

    @Override
    public String deleteScoreRef(EduCourseScoreRef ref) {
        if (ref != null) {
            ref.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(ref, false);
            scoreRefMapper.updateByPrimaryKeySelective(ref);
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @Override
    public String deleteScoreRefs(List<EduCourseScoreRef> refList) {
        if (refList != null && !refList.isEmpty()) {
            for (EduCourseScoreRef ref : refList) {
                deleteScoreRef(ref);
            }
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }

}
