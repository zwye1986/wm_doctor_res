package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.LectureInfoTarget;
import com.pinde.core.model.LectureInfoTargetExample;
import com.pinde.core.model.ResLectureEvaDetail;
import com.pinde.core.model.ResLectureEvaDetailExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResLectureEvaDetailBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.LectureInfoTargetMapper;
import com.pinde.sci.dao.base.ResLectureEvaDetailMapper;
import com.pinde.sci.dao.res.ResLectureEvaDetailExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResLectureEvaDetailBizImpl implements IResLectureEvaDetailBiz {
    @Autowired
    private ResLectureEvaDetailMapper lectureEvaDetailMapper;
    @Autowired
    private ResLectureEvaDetailExtMapper lectureEvaDetailExtMapper;
    @Autowired
    private LectureInfoTargetMapper lectureInfoTargetMapper;
   @Override
    public List<ResLectureEvaDetail> SearchByLectureFlow(String lectureFlow){
       ResLectureEvaDetailExample example = new ResLectureEvaDetailExample();
       if(StringUtil.isNotBlank(lectureFlow)) {
           example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow);
       }
       return lectureEvaDetailMapper.selectByExample(example);
   }

    @Override
    public List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow, String lectureFlow) {
        ResLectureEvaDetailExample example = new ResLectureEvaDetailExample();
        if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(lectureFlow)){
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow)
                    .andOperUserFlowEqualTo(userFlow);
        }
        example.setOrderByClause("ORDINAL");
        return lectureEvaDetailMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail) {
        if(resLectureEvaDetail!=null){
            if(StringUtil.isNotBlank(resLectureEvaDetail.getRecordFlow())){
               GeneralMethod.setRecordInfo(resLectureEvaDetail, false);
               return lectureEvaDetailMapper.updateByPrimaryKeySelective(resLectureEvaDetail);
            }else{
                resLectureEvaDetail.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(resLectureEvaDetail, true);
                return lectureEvaDetailMapper.insertSelective(resLectureEvaDetail);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }
    @Override
    public List<ResLectureEvaDetail> searchByLectureFlow(String lectureFlow){
        ResLectureEvaDetailExample example = new ResLectureEvaDetailExample();
        example.createCriteria().andLectureFlowEqualTo(lectureFlow);
        example.setOrderByClause("CREATE_TIME DESC");
        return lectureEvaDetailMapper.selectByExample(example);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description 查询讲座活动用户评价信息
     * @Author fengxf
     * @Date 2020/2/14
     */
    @Override
    public List<ResLectureEvaDetail> searchUserEvalList(Map<String, String> param) {
        return lectureEvaDetailExtMapper.searchUserEvalList(param);
    }

    /**
     * @param lectureFlow
     * @Department：研发部
     * @Description 查找指定讲座的评价指标
     * @Author Zjie
     * @Date 0005, 2020年11月5日
     */
    @Override
    public List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow) {
        LectureInfoTargetExample example = new LectureInfoTargetExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow);
        example.setOrderByClause("ORDINAL");
        return lectureInfoTargetMapper.selectByExample(example);
    }


}
