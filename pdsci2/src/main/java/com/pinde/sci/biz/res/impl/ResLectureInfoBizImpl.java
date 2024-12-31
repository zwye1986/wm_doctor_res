package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResLectureEvaDetailExtMapper;
import com.pinde.core.common.sci.dao.ResLectureInfoMapper;
import com.pinde.core.common.sci.dao.ResLectureInfoRoleMapper;
import com.pinde.core.common.sci.dao.ResLectureScanRegistMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResLectureInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.res.LectureInfoTargetExtMapper;
import com.pinde.sci.dao.res.ResLectureInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResLectureInfoBizImpl implements IResLectureInfoBiz {
    @Autowired
    private ResLectureInfoMapper lectureInfoMapper;
    @Autowired
    private ResLectureInfoExtMapper lectureInfoExtMapper;

    @Autowired
    private ResLectureInfoRoleMapper resLectureRoleMapper;
    @Autowired
    private LectureInfoTargetExtMapper lectureInfoTargetExtMapper;
    @Autowired
    private ResLectureEvaDetailExtMapper lectureEvaDetailExtMapper;
    @Autowired
    private ResLectureScanRegistMapper lectureScanRegistMapper;

    @Override
    public List<ResLectureInfo> SearchByDateContentTeacherNameType(String orgFlow, String lectureTrainDate, String content, String lectureTeacherName, String lectureTypeId, String place) {
        if(StringUtil.isBlank(orgFlow))
            return null;
        ResLectureInfoExample resLectureInfoExample = new ResLectureInfoExample();
        ResLectureInfoExample.Criteria criteria = resLectureInfoExample.createCriteria().andOrgFlowEqualTo(orgFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(lectureTrainDate)) {
            criteria.andLectureTrainDateEqualTo(lectureTrainDate);
        }
        if (StringUtil.isNotBlank(lectureTeacherName)) {
            criteria.andLectureTeacherNameLike("%"+lectureTeacherName+"%");
        }
        if (StringUtil.isNotBlank(content)) {
            criteria.andLectureContentLike("%"+content+"%");
        }
        if(StringUtil.isNotBlank(lectureTypeId)){
            criteria.andLectureTypeIdEqualTo(lectureTypeId);
        }
        if (StringUtil.isNotBlank(place)) {
            criteria.andLectureTrainPlaceLike("%"+place+"%");
        }
        resLectureInfoExample.setOrderByClause("LECTURE_TRAIN_DATE DESC,LECTURE_START_TIME,CREATE_TIME");
        return lectureInfoMapper.selectByExample(resLectureInfoExample);
    }

    @Override
    public List<ResLectureInfo> SearchByDateContentTeacherNameTypeTwo(String orgFlow,String lectureTrainStartDate,String lectureTrainEndDate, String content, String lectureTeacherName,String lectureTypeId,String place) {
        if(StringUtil.isBlank(orgFlow))
            return null;
        ResLectureInfoExample resLectureInfoExample = new ResLectureInfoExample();
        ResLectureInfoExample.Criteria criteria = resLectureInfoExample.createCriteria().andOrgFlowEqualTo(orgFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(lectureTrainStartDate) && StringUtil.isNotBlank(lectureTrainEndDate)) {
//            criteria.andLectureTrainDateEqualTo(lectureTrainDate);
            criteria.andLectureTrainDateGreaterThanOrEqualTo(lectureTrainStartDate);
            criteria.andLectureTrainDateLessThanOrEqualTo(lectureTrainEndDate);
        }
        if (StringUtil.isNotBlank(lectureTeacherName)) {
            criteria.andLectureTeacherNameLike("%"+lectureTeacherName+"%");
        }
        if (StringUtil.isNotBlank(content)) {
            criteria.andLectureContentLike("%"+content+"%");
        }
        if(StringUtil.isNotBlank(lectureTypeId)){
            criteria.andLectureTypeIdEqualTo(lectureTypeId);
        }
        if (StringUtil.isNotBlank(place)) {
            criteria.andLectureTrainPlaceLike("%"+place+"%");
        }
        resLectureInfoExample.setOrderByClause("LECTURE_TRAIN_DATE DESC,LECTURE_START_TIME,CREATE_TIME");
        return lectureInfoMapper.selectByExample(resLectureInfoExample);
    }

    @Override
    public int editLectureInfo(ResLectureInfo resLectureInfo) {
        if (resLectureInfo != null) {
            String lectureFlow = resLectureInfo.getLectureFlow();
            ResLectureInfo lectureInfo = read(lectureFlow);
            if (lectureInfo!=null) {
                resLectureInfo.setLectureFlow(lectureInfo.getLectureFlow());
                GeneralMethod.setRecordInfo(resLectureInfo, false);
                int count = lectureInfoMapper.updateByPrimaryKeySelective(resLectureInfo);
                if(count != 0){
                    // 如果讲座活动状态为删除 则删除对应的讲座活动评价表
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(resLectureInfo.getRecordStatus())) {
                        lectureInfoTargetExtMapper.delLectureInfoTarget(resLectureInfo);
                    }
                }
                return count;
            } else {
                GeneralMethod.setRecordInfo(resLectureInfo, true);
                int count = lectureInfoMapper.insertSelective(resLectureInfo);
                if(count != 0){
                    // 讲座信息新增成功后保存讲座和讲座评价指标关联信息表
                    lectureInfoTargetExtMapper.saveLectureInfoTarget(resLectureInfo);
                }
                return count;
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResLectureInfo read(String lectureFlow) {
       ResLectureInfo lectureInfo = null;
       if(StringUtil.isNotBlank(lectureFlow)) {
           lectureInfo = lectureInfoMapper.selectByPrimaryKey(lectureFlow);
       }
       return  lectureInfo;
    }

    @Override
    public List<ResLectureInfo> searchNewLectures(String orgFlow, String roleId, String roleFlow,ResLectureInfo resLectureInfo){
        List<ResLectureInfo> lectureInfos = lectureInfoExtMapper.searchLecturesList(orgFlow,roleId,roleFlow,resLectureInfo);
        return lectureInfos;
    }
    @Override
    public int addLectureInfo(ResLectureInfo resLectureInfo, String[] roleFlows) {
        int c=0;
        if (resLectureInfo != null) {
            String lectureFlow = resLectureInfo.getLectureFlow();
            ResLectureInfo lectureInfo = read(lectureFlow);
            if (lectureInfo!=null) {
                resLectureInfo.setLectureFlow(lectureInfo.getLectureFlow());
                GeneralMethod.setRecordInfo(resLectureInfo, false);
                c= lectureInfoMapper.updateByPrimaryKeySelective(resLectureInfo);
            } else {
                GeneralMethod.setRecordInfo(resLectureInfo, true);
                c= lectureInfoMapper.insertSelective(resLectureInfo);
                if(c != 0){
                    // 讲座信息新增成功后保存讲座和讲座评价指标关联信息表
                    lectureInfoTargetExtMapper.saveLectureInfoTarget(resLectureInfo);
                }
            }
        }
        if(c==1) {
            List<String> roleFlowList=null;

            if(roleFlows!=null) {
                roleFlowList = Arrays.asList(roleFlows);
            }
            lectureInfoExtMapper.deleteLectureRole(resLectureInfo,roleFlowList);

            if(roleFlows!=null) {
                for(String roleFlow:roleFlows) {
                    ResLectureInfoRole infoRole=null;
                    if(StringUtil.isNotBlank(resLectureInfo.getLectureFlow())) {
                        infoRole=readLectureRole(resLectureInfo.getLectureFlow(), roleFlow);
                    }
                    if(infoRole==null){
                        infoRole=new ResLectureInfoRole();
                    }
                    infoRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    infoRole.setLectureFlow(resLectureInfo.getLectureFlow());
                    infoRole.setLectureRole(roleFlow);
                    saveLectureRole(infoRole);
                }
            }
        }
        return c;
    }

    @Override
    public List<ResLectureInfoRole> readLectureRoleList(String lectureFlow) {
        if(StringUtil.isNotBlank(lectureFlow)) {
            ResLectureInfoRoleExample example = new ResLectureInfoRoleExample();
            example.createCriteria().andLectureFlowEqualTo(lectureFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResLectureInfoRole> list = resLectureRoleMapper.selectByExample(example);
            return list;
        }
        return null;
    }



    private int saveLectureRole(ResLectureInfoRole infoRole) {

        if(infoRole != null){
            if(StringUtil.isNotBlank(infoRole.getRecordFlow())){
                GeneralMethod.setRecordInfo(infoRole, false);
                return resLectureRoleMapper.updateByPrimaryKeySelective(infoRole);
            }else{
                infoRole.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(infoRole, true);
                return resLectureRoleMapper.insertSelective(infoRole);
            }
        }
        return 0;
    }

    private ResLectureInfoRole readLectureRole(String lectureFlow, String roleFlow) {
        ResLectureInfoRoleExample example=new ResLectureInfoRoleExample();
        example.createCriteria().andLectureFlowEqualTo(lectureFlow).andLectureRoleEqualTo(roleFlow);
        List<ResLectureInfoRole> list=resLectureRoleMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryNotification(String orgFlow) {

        return lectureInfoMapper.queryNotification(orgFlow);
    }

    @Override
    public List<ParticipateInfoExt> queryParticipateList(String orgFlow) {

        return lectureInfoMapper.queryParticipateList(orgFlow);
    }

    @Override
    public List<Map<String, Object>> queryAssessScoreList(String orgFlow) {

        return lectureInfoMapper.queryAssessScoreList(orgFlow);
    }

    /**
     * @param scanRegist
     * @param paramMap
     * @Department：研发部
     * @Description 保存讲座活动评价信息
     * @Author fengxf
     * @Date 2020/2/14
     */
    @Override
    public String saveLectureEval(ResLectureScanRegist scanRegist, Map<String, Object> paramMap) {
        // 更新讲座活动报名签到表评分信息
        GeneralMethod.setRecordInfo(scanRegist, false);
        int count = lectureScanRegistMapper.updateByPrimaryKeySelective(scanRegist);
        if(count == 0){
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        // 保存讲座活动评分信息
        count = lectureEvaDetailExtMapper.saveLectureEval(paramMap);
        if(count == 0){
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * @param lectureFlow
     * @Department：研发部
     * @Description 判断讲座信息是否能删除
     * @Author fengxf
     * @Date 2019/12/17
     */
    @Override
    public boolean getDelLectureInfoFlag(String lectureFlow) {
        // 签到人数
        int signCount = lectureInfoExtMapper.countLectureSign(lectureFlow);
        // 评价人数
        int evelCount = lectureInfoExtMapper.countLectureEvel(lectureFlow);
        // 有人签到或评价的讲座信息则不能删除
        return signCount > 0 || evelCount > 0;
    }
}