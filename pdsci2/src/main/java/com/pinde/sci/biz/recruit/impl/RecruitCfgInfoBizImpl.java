package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitCfgInfoMapper;
import com.pinde.sci.dao.recruit.RecruitCfgInfoExtMapper;
import com.pinde.sci.model.mo.RecruitCfgInfo;
import com.pinde.sci.model.mo.RecruitCfgInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitCfgInfoBizImpl implements IRecruitCfgInfoBiz {

    @Autowired
    private RecruitCfgInfoMapper recruitCfgInfoMapper;
    @Autowired
    private RecruitCfgInfoExtMapper recruitCfgInfoExtMapper;

    /**
     * 根据年份查询配置
     * @param year
     * @return
     */
    @Override
    public RecruitCfgInfo searchCfgInfoByYear(String year,String orgFlow) {
        RecruitCfgInfoExample example = new RecruitCfgInfoExample();
        RecruitCfgInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecruitYearEqualTo(year);
        criteria.andOrgFlowEqualTo(orgFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<RecruitCfgInfo> recruitCfgInfos = recruitCfgInfoMapper.selectByExample(example);
        if (recruitCfgInfos != null && !recruitCfgInfos.isEmpty() && recruitCfgInfos.size()>0){
            return recruitCfgInfos.get(0);
        }
        return null;
    }

    /**
     * 新增配置
     * @param recruitCfgInfo
     * @return
     */
    @Override
    public Integer addRecCfgInfo(RecruitCfgInfo recruitCfgInfo) {
        recruitCfgInfo.setCfgFlow(PkUtil.getUUID());
        recruitCfgInfo.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        recruitCfgInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitCfgInfo.setCreateTime(DateUtil.getCurrDateTime());
        recruitCfgInfo.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        recruitCfgInfo.setModifyTime(DateUtil.getCurrDateTime());
        recruitCfgInfo.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitCfgInfoMapper.insertSelective(recruitCfgInfo);
    }

    /**
     * 根据报考年份更新配置
     * @param recruitCfgInfo
     * @return
     */
    @Override
    public Integer updateRecCfgInfo(RecruitCfgInfo recruitCfgInfo) {
        GeneralMethod.setRecordInfo(recruitCfgInfo,false);
        return recruitCfgInfoMapper.updateByPrimaryKeySelective(recruitCfgInfo);
    }

    /**
     * 按照修改时间倒序搜索所有未删除配置
     * @return
     */
    @Override
    public List<String> searchAllRecruitYear(String orgFlow) {
        List<String> recruitYear = recruitCfgInfoExtMapper.searchAllRecruitYear(orgFlow);
        if (recruitYear != null && recruitYear.size() > 0){
            return recruitYear;
        }else {
            return null;
        }
    }

    @Override
    public List<RecruitCfgInfo> cfgs(String orgFlow) {
        RecruitCfgInfoExample example= new RecruitCfgInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("RECRUIT_YEAR desc");
        return recruitCfgInfoMapper.selectByExample(example);
    }

    @Override
    public RecruitCfgInfo readByFlow(String cfgFlow) {
        return recruitCfgInfoMapper.selectByPrimaryKey(cfgFlow);
    }

    @Override
    public Integer RecruitCfgInfo(String cfgFlow,String orgFlow) {
        recruitCfgInfoExtMapper.changeIsRecruit(orgFlow);
        RecruitCfgInfo recruitCfgInfo = new RecruitCfgInfo();
        recruitCfgInfo.setCfgFlow(cfgFlow);
        recruitCfgInfo.setIsRecruit(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        GeneralMethod.setRecordInfo(recruitCfgInfo,false);
        return recruitCfgInfoMapper.updateByPrimaryKeySelective(recruitCfgInfo);
    }

    /**
     * 获取当前设置的配置信息
     * @return
     */
    @Override
    public RecruitCfgInfo getCurrYearCfgInfo(String orgFlow) {
        RecruitCfgInfoExample example = new RecruitCfgInfoExample();
        example.createCriteria().andIsRecruitEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        List<RecruitCfgInfo> recruitCfgInfos = recruitCfgInfoMapper.selectByExample(example);
        if (recruitCfgInfos != null && recruitCfgInfos.size() > 0){
            return recruitCfgInfos.get(0);
        }else {
            return null;
        }
    }


}
