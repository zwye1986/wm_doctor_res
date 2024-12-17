package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.model.ResBaseSpeDept;
import com.pinde.core.model.ResBaseSpeDeptExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResBaseSpeDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResBaseSpeDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResBaseSpeDeptBizImpl implements IResBaseSpeDeptBiz {

    @Autowired
    private ResBaseSpeDeptMapper speDeptMapper;

    @Override
    public ResBaseSpeDept readBaseByDeptFlow(String deptFlow) {
        if (StringUtil.isNotBlank(deptFlow)) {
            ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDeptFlowEqualTo(deptFlow).andTypeEqualTo("dept");
            List<ResBaseSpeDept> list = speDeptMapper.selectByExampleWithBLOBs(example);
            if (null != list && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public ResBaseSpeDept readBaseBySpeFlow(String speFlow) {
        if (StringUtil.isNotBlank(speFlow)) {
            ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andSpeFlowEqualTo(speFlow).andTypeEqualTo("spe");
            List<ResBaseSpeDept> list = speDeptMapper.selectByExampleWithBLOBs(example);
            if (null != list && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveInfo(ResBaseSpeDept resBaseSpeDept) {
        if (null==resBaseSpeDept){
            return 0;
        }
        if (StringUtil.isBlank(resBaseSpeDept.getRecordFlow())){
            resBaseSpeDept.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resBaseSpeDept, true);
            return  speDeptMapper.insertSelective(resBaseSpeDept);
        }else {
            GeneralMethod.setRecordInfo(resBaseSpeDept, false);
            return  speDeptMapper.updateByPrimaryKeyWithBLOBs(resBaseSpeDept);
        }
    }
}
