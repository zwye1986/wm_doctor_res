package com.pinde.sci.biz.srm.impl;

import com.pinde.sci.biz.srm.IFundFormBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmProjFundFormMapper;
import com.pinde.sci.model.mo.SrmProjFundForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class FundFormBizImpl implements IFundFormBiz {
    @Autowired
    private SrmProjFundFormMapper fundFormMapper;

    @Override
    public int deleteFundForm(String formFlow) {
        SrmProjFundForm fundForm = new SrmProjFundForm();
        fundForm.setFundFormFlow(formFlow);
        fundForm.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return this.fundFormMapper.updateByPrimaryKeySelective(fundForm);
    }
}
