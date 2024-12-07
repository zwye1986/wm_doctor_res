package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.res.IResCostCfgMainBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.dao.base.ResCostCfgMainMapper;
import com.pinde.sci.dao.res.ResCostCfgExtMapper;
import com.pinde.sci.model.mo.ResCostCfgMain;
import com.pinde.sci.model.mo.ResCostCfgMainExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResCostCfgMainBizImpl implements IResCostCfgMainBiz {

    @Autowired
    private ResCostCfgMainMapper costCfgMainMapper;
    @Autowired
    private ResCostCfgExtMapper costCfgExtMapper;

    @Override
    public List<ResCostCfgMain> search(ResCostCfgMain costCfgMain) {
        ResCostCfgMainExample example = new ResCostCfgMainExample();
        ResCostCfgMainExample.Criteria criteria = example.createCriteria();
        if(costCfgMain!=null){
            if(StringUtil.isNotBlank(costCfgMain.getStartDate()) && StringUtil.isBlank(costCfgMain.getEndDate())){
                criteria.andStartDateGreaterThanOrEqualTo(costCfgMain.getStartDate());
            }
            if(StringUtil.isNotBlank(costCfgMain.getEndDate()) && StringUtil.isBlank(costCfgMain.getStartDate())){
                criteria.andEndDateLessThanOrEqualTo(costCfgMain.getEndDate());
            }
            if(StringUtil.isNotBlank(costCfgMain.getStartDate()) && StringUtil.isNotBlank(costCfgMain.getEndDate())){
                criteria.andStartDateGreaterThanOrEqualTo(costCfgMain.getStartDate())
                        .andEndDateLessThanOrEqualTo(costCfgMain.getEndDate());
            }
            if(StringUtil.isNotBlank(costCfgMain.getOrgFlow())){
                criteria.andOrgFlowEqualTo(costCfgMain.getOrgFlow());
            }
            if(StringUtil.isNotBlank(costCfgMain.getTypeId())){
                criteria.andTypeIdEqualTo(costCfgMain.getTypeId());
            }
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return costCfgMainMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public ResCostCfgMain readCostCfgMain(String mainFlow) {
        return costCfgMainMapper.selectByPrimaryKey(mainFlow);
    }

    @Override
    public int saveMain(ResCostCfgMain main) {
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",main.getOrgFlow());
        param.put("typeId",main.getTypeId());
        param.put("startDate",main.getStartDate());
        param.put("endDate",main.getEndDate());
        List<Map<String,Object>> map = costCfgExtMapper.checkDate(param);
        if(map != null && map.size()>0){
            return 0;
        }
        main.setMainFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(main,true);
        return costCfgMainMapper.insert(main);
    }

    @Override
    public int updateMain(ResCostCfgMain main) {
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",main.getOrgFlow());
        param.put("typeId",main.getTypeId());
        param.put("mainFlow",main.getMainFlow());
        param.put("startDate",main.getStartDate());
        param.put("endDate",main.getEndDate());
        List<Map<String,Object>> map = costCfgExtMapper.checkDate(param);
        if(map != null && map.size()>0){
            return 0;
        }
        GeneralMethod.setRecordInfo(main, false);
        return costCfgMainMapper.updateByPrimaryKeySelective(main);
    }
}
