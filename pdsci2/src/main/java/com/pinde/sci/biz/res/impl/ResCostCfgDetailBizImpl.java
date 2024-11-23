package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.res.IResCostCfgDetailBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.ResCostCfgDetailMapper;
import com.pinde.sci.model.mo.ResCostCfgDetail;
import com.pinde.sci.model.mo.ResCostCfgDetailExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResCostCfgDetailBizImpl implements IResCostCfgDetailBiz {

    @Autowired
    private ResCostCfgDetailMapper costCfgDetailMapper;

    @Override
    public List<ResCostCfgDetail> search(ResCostCfgDetail cfgDetail) {
        ResCostCfgDetailExample example = new ResCostCfgDetailExample();
        ResCostCfgDetailExample.Criteria criteria = example.createCriteria();
        if(cfgDetail!=null){
            if(StringUtil.isNotBlank(cfgDetail.getMainFlow())){
                criteria.andMainFlowEqualTo(cfgDetail.getMainFlow());
            }
            if(StringUtil.isNotBlank(cfgDetail.getItemId())){
                criteria.andItemIdEqualTo(cfgDetail.getItemId());
            }
            return costCfgDetailMapper.selectByExample(example);

        }
        return null;
    }

    @Override
    public int saveDetail(ResCostCfgDetail detail) {
        if(StringUtil.isNotBlank(detail.getDetailFlow())){
            GeneralMethod.setRecordInfo(detail,false);
            return costCfgDetailMapper.updateByPrimaryKeySelective(detail);
        }else{
            detail.setDetailFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(detail,true);
            return costCfgDetailMapper.insert(detail);
        }
    }
}
