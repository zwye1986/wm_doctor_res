package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.ILearningSocietyBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmLearningSocietyMapper;
import com.pinde.sci.model.mo.SrmLearningSociety;
import com.pinde.sci.model.mo.SrmLearningSocietyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class LearningSocietyBizImpl implements ILearningSocietyBiz {
    @Autowired
    private SrmLearningSocietyMapper societyMapper;

    @Override
    public List<SrmLearningSociety> search(SrmLearningSociety srmLearningSociety) {
        SrmLearningSocietyExample example=new SrmLearningSocietyExample();
        SrmLearningSocietyExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(srmLearningSociety.getDeptFlow())){
            criteria.andDeptFlowEqualTo(srmLearningSociety.getDeptFlow());
        }
        if(StringUtil.isNotBlank(srmLearningSociety.getUserFlow())){
            criteria.andUserFlowEqualTo(srmLearningSociety.getUserFlow());
        }
        if(StringUtil.isNotBlank(srmLearningSociety.getSocietyName())){
            criteria.andSocietyNameLike("%"+srmLearningSociety.getSocietyName()+"%");
        }
        if(StringUtil.isNotBlank(srmLearningSociety.getSocietyPosition())){
            criteria.andSocietyPositionEqualTo(srmLearningSociety.getSocietyPosition());
        }
        if(StringUtil.isNotBlank(srmLearningSociety.getOrgFlow())){
            criteria.andOrgFlowEqualTo(srmLearningSociety.getOrgFlow());
        }
        return societyMapper.selectByExample(example);
    }

    @Override
    public boolean update(SrmLearningSociety society) {

        return false;
    }

    @Override
    public int save(SrmLearningSociety society) {
        int isSuccess = 0;
        if(null != society){
            if(StringUtil.isNotBlank(society.getSocietyFlow())){
                GeneralMethod.setRecordInfo(society, false);
                isSuccess = societyMapper.updateByPrimaryKeySelective(society);
            }else{
                GeneralMethod.setRecordInfo(society, true);
                society.setSocietyFlow(PkUtil.getUUID());
                isSuccess = societyMapper.insertSelective(society);
            }
        }
        return isSuccess;
    }

    @Override
    public void deleteByPrimaryKeys(List<String> societyFlowList) {
        for(String societyFlow : societyFlowList){
            if(StringUtil.isNotBlank(societyFlow)){
                SrmLearningSociety society = new SrmLearningSociety();
                society.setSocietyFlow(societyFlow);
                society.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                societyMapper.updateByPrimaryKeySelective(society);
            }
        }
    }
}
