package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjLeaveBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.NyqjLeaveMainMapper;
import com.pinde.sci.dao.xjgl.XjNyqjLeaveMainExtMapper;
import com.pinde.sci.model.mo.NyqjLeaveMain;
import com.pinde.sci.model.mo.NyqjLeaveMainExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjLeaveBizImpl implements IXjLeaveBiz {
    @Autowired
    private NyqjLeaveMainMapper nyqjLeaveMainMapper;
    @Autowired
    private XjNyqjLeaveMainExtMapper nyqjLeaveMainExtMapper;

    @Override
    public List<NyqjLeaveMain> searchNyqjLeaveList(NyqjLeaveMain nyqjLeaveMain) {
        NyqjLeaveMainExample example=new NyqjLeaveMainExample();
        NyqjLeaveMainExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(nyqjLeaveMain.getUserName())){
            criteria.andUserNameLike("%"+nyqjLeaveMain.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(nyqjLeaveMain.getUserFlow())){
            criteria.andUserFlowEqualTo(nyqjLeaveMain.getUserFlow());
        }
        if(StringUtil.isNotBlank(nyqjLeaveMain.getApplyTypeId())){
            criteria.andApplyTypeIdEqualTo(nyqjLeaveMain.getApplyTypeId());
        }
        example.setOrderByClause("APPLY_TIME DESC,CREATE_TIME DESC");
        return nyqjLeaveMainMapper.selectByExample(example);
    }

    @Override
    public int saveNyqjLeaveMain(NyqjLeaveMain nyqjLeaveMain) {
        if(StringUtil.isNotBlank(nyqjLeaveMain.getRecordFlow())){
            GeneralMethod.setRecordInfo(nyqjLeaveMain, false);
            return nyqjLeaveMainMapper.updateByPrimaryKeySelective(nyqjLeaveMain);
        }else{
            nyqjLeaveMain.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(nyqjLeaveMain, true);
            return nyqjLeaveMainMapper.insertSelective(nyqjLeaveMain);
        }
    }

    @Override
    public NyqjLeaveMain searchNyqjLeaveByFlow(String recordFlow) {
        return nyqjLeaveMainMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<NyqjLeaveMain> searchLeaveApplyList(NyqjLeaveMain leaveMain) {
        NyqjLeaveMainExample example=new NyqjLeaveMainExample();
        NyqjLeaveMainExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        NyqjLeaveMainExample.Criteria criteria2=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(leaveMain.getUserName())){
            criteria.andUserNameLike("%"+leaveMain.getUserName()+"%");
            criteria2.andUserNameLike("%"+leaveMain.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(leaveMain.getUserFlow())){
            criteria.andUserFlowEqualTo(leaveMain.getUserFlow());
            criteria2.andUserFlowEqualTo(leaveMain.getUserFlow());
        }
        if(StringUtil.isNotBlank(leaveMain.getApplyTypeId())){
            criteria.andApplyTypeIdEqualTo(leaveMain.getApplyTypeId());
            criteria2.andApplyTypeIdEqualTo(leaveMain.getApplyTypeId());
        }
        if(StringUtil.isNotBlank(leaveMain.getStudentId())){
            criteria.andStudentIdEqualTo(leaveMain.getStudentId());
            criteria2.andStudentIdEqualTo(leaveMain.getStudentId());
        }
        //申请时间筛选
        if(StringUtil.isNotBlank(leaveMain.getCreateTime())){
            criteria.andApplyTimeGreaterThanOrEqualTo(leaveMain.getCreateTime());
            criteria2.andApplyTimeGreaterThanOrEqualTo(leaveMain.getCreateTime());
        }
        //申请时间筛选
        if(StringUtil.isNotBlank(leaveMain.getModifyTime())){
            criteria.andApplyTimeLessThanOrEqualTo(leaveMain.getModifyTime());
            criteria2.andApplyTimeLessThanOrEqualTo(leaveMain.getModifyTime());
        }
        //导师一
        if(StringUtil.isNotBlank(leaveMain.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(leaveMain.getDoctorFlow());
        }
        //导师二
        if(StringUtil.isNotBlank(leaveMain.getSecondDoctorFlow())){
            criteria2.andSecondDoctorFlowEqualTo(leaveMain.getSecondDoctorFlow());
            //导师可作为导师一或导师二形式
            example.or(criteria2);
        }
        if(StringUtil.isNotBlank(leaveMain.getDoctorAuditStatusId())){
            criteria.andDoctorAuditStatusIdEqualTo(leaveMain.getDoctorAuditStatusId());
            criteria2.andSecondAuditStatusIdEqualTo(leaveMain.getDoctorAuditStatusId());
            example.or(criteria2);
        }
        //培养单位
        if(StringUtil.isNotBlank(leaveMain.getPydwOrgFlow())){
            criteria.andPydwOrgFlowEqualTo(leaveMain.getPydwOrgFlow());
            criteria2.andPydwOrgFlowEqualTo(leaveMain.getPydwOrgFlow());
        }
        if(StringUtil.isNotBlank(leaveMain.getPydwAuditStatusId())){
            criteria.andPydwAuditStatusIdEqualTo(leaveMain.getPydwAuditStatusId());
            criteria2.andPydwAuditStatusIdEqualTo(leaveMain.getPydwAuditStatusId());
        }
        //研究生部思政科
        if(StringUtil.isNotBlank(leaveMain.getSzkOrgFlow())){
            criteria.andSzkOrgFlowEqualTo(leaveMain.getSzkOrgFlow());
            criteria2.andSzkOrgFlowEqualTo(leaveMain.getSzkOrgFlow());
        }
        if(StringUtil.isNotBlank(leaveMain.getSzkAuditStatusId())){
            criteria.andSzkAuditStatusIdEqualTo(leaveMain.getSzkAuditStatusId());
            criteria2.andSzkAuditStatusIdEqualTo(leaveMain.getSzkAuditStatusId());
        }
        //研究生部领导
        if(StringUtil.isNotBlank(leaveMain.getLeaderOrgFlow())){
            criteria.andLeaderOrgFlowEqualTo(leaveMain.getLeaderOrgFlow());
            criteria2.andLeaderOrgFlowEqualTo(leaveMain.getLeaderOrgFlow());
        }
        if(StringUtil.isNotBlank(leaveMain.getLeaderAuditStatusId())){
            criteria.andLeaderAuditStatusIdEqualTo(leaveMain.getLeaderAuditStatusId());
            criteria2.andLeaderAuditStatusIdEqualTo(leaveMain.getLeaderAuditStatusId());
        }
        if(StringUtil.isNotBlank(leaveMain.getYjsbmAuditFlag())){
            criteria.andYjsbmAuditFlagEqualTo(leaveMain.getYjsbmAuditFlag());
            criteria2.andYjsbmAuditFlagEqualTo(leaveMain.getYjsbmAuditFlag());
        }
        example.setOrderByClause("APPLY_TIME DESC,CREATE_TIME DESC");
        return nyqjLeaveMainMapper.selectByExample(example);
    }

    @Override
    public List<NyqjLeaveMain> searchNyqjListByTutor(Map<String, Object> paramMap) {
        return nyqjLeaveMainExtMapper.queryNyqjListByTutor(paramMap);
    }

    @Override
    public List<NyqjLeaveMain> searchNyqjListByPydw(Map<String, Object> paramMap) {
        return nyqjLeaveMainExtMapper.queryNyqjListByPydw(paramMap);
    }

    @Override
    public List<NyqjLeaveMain> searchNyqjListBySzk(Map<String, Object> paramMap) {
        return nyqjLeaveMainExtMapper.queryNyqjListBySzk(paramMap);
    }

    @Override
    public int delLeave(String recordFlow) {
        return nyqjLeaveMainMapper.deleteByPrimaryKey(recordFlow);
    }
}
