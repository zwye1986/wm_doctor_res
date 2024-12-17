package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResEnterOpenCfgMapper;
import com.pinde.sci.dao.base.SchArrangeTimeMapper;
import com.pinde.core.model.ResEnterOpenCfg;
import com.pinde.core.model.ResEnterOpenCfgExample;
import com.pinde.core.model.SchArrangeTime;
import com.pinde.core.model.SchArrangeTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResEnterOpenCfgBizImpl implements IResEnterOpenCfgBiz{
    @Autowired
    private ResEnterOpenCfgMapper enterOpenCfgMapper;
    @Autowired
    private SchArrangeTimeMapper arrangeTimeMapper;

    @Override
    public ResEnterOpenCfg readResEnterOpenCfg(String orgFlow){
        if(StringUtil.isNotBlank(orgFlow)){
            ResEnterOpenCfgExample example = new ResEnterOpenCfgExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow);
            List<ResEnterOpenCfg> list = enterOpenCfgMapper.selectByExample(example);
            if(list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }
    @Override
    public int saveEnterOpenCfg(ResEnterOpenCfg enterOpenCfg){
        int result=0;
        if(enterOpenCfg!=null){
            if(StringUtil.isBlank(enterOpenCfg.getCfgFlow())){//新增
                enterOpenCfg.setCfgFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(enterOpenCfg,true);
                result = enterOpenCfgMapper.insert(enterOpenCfg);
            }else{//修改
                GeneralMethod.setRecordInfo(enterOpenCfg,false);
                result = enterOpenCfgMapper.updateByPrimaryKeySelective(enterOpenCfg);
            }
        }
        return result;
    }

    @Override
    public List<SchArrangeTime> getArrangeTimes(String orgFlow) {

        if(StringUtil.isNotBlank(orgFlow)){
            SchArrangeTimeExample example = new SchArrangeTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow);
            List<SchArrangeTime> list = arrangeTimeMapper.selectByExample(example);
            return list;
        }
        return null;
    }

    @Override
    public SchArrangeTime getArrangeTime(String recordFlow) {
        return arrangeTimeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public SchArrangeTime getArrangeTimeByOrgYear(String sessionNumber, String orgFlow) {

        if(StringUtil.isNotBlank(orgFlow)){
            SchArrangeTimeExample example = new SchArrangeTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber);
            List<SchArrangeTime> list = arrangeTimeMapper.selectByExample(example);
            if(list!=null&&list.size()>0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveSchArrangeTime(SchArrangeTime time) {
        int result=0;
        if(time!=null){
            if(StringUtil.isBlank(time.getRecordFlow())){//新增
                time.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(time,true);
                result = arrangeTimeMapper.insert(time);
            }else{//修改
                GeneralMethod.setRecordInfo(time,false);
                result = arrangeTimeMapper.updateByPrimaryKeySelective(time);
            }
        }
        return result;
    }
}
