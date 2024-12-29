package com.pinde.sci.biz.osca.impl;


import com.pinde.core.common.sci.dao.OscaOrgSpeMapper;
import com.pinde.core.common.sci.dao.SysOrgMapper;
import com.pinde.core.model.OscaOrgSpe;
import com.pinde.core.model.OscaOrgSpeExample;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysOrgExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.ISiteInformationBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.osca.OscaSysOrgExtMapper;
import com.pinde.sci.model.osca.OscaOrgSpeExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class SiteInformationBizImpl implements ISiteInformationBiz{

    @Autowired
    private OscaOrgSpeMapper oscaOrgSpeMapper;
    @Autowired
    private OscaSysOrgExtMapper oscaSysOrgExtMapper;
    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Override
    public int saveSiteInformation(OscaOrgSpe oscaOrgSpe) {
        String recordFlow = oscaOrgSpe.getRecordFlow();
        if(StringUtil.isBlank(recordFlow)){
            oscaOrgSpe.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(oscaOrgSpe,true);
            return oscaOrgSpeMapper.insert(oscaOrgSpe);
        }else{
            GeneralMethod.setRecordInfo(oscaOrgSpe,false);
            return oscaOrgSpeMapper.updateByPrimaryKeySelective(oscaOrgSpe);
        }
    }

    @Override
    public int updateSiteInformation(OscaOrgSpe oscaOrgSpe) {
        OscaOrgSpeExample example=new OscaOrgSpeExample();
        OscaOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaOrgSpe.getRecordFlow())){
            criteria.andRecordFlowEqualTo(oscaOrgSpe.getRecordFlow());
        }
        if(StringUtil.isNotBlank(oscaOrgSpe.getOrgFlow())){
            criteria.andOrgFlowEqualTo(oscaOrgSpe.getOrgFlow());
        }
//        if(StringUtil.isNotBlank(oscaOrgSpe.getModifyTime())){
//            criteria.andModifyTimeEqualTo(oscaOrgSpe.getModifyTime());
//        }
//        if(StringUtil.isNotBlank(oscaOrgSpe.getModifyUserFlow())){
//            criteria.andModifyUserFlowEqualTo(oscaOrgSpe.getModifyUserFlow());
//        }
        return oscaOrgSpeMapper.updateByExample(oscaOrgSpe,example);
    }

    @Override
    public List<OscaOrgSpe> searchOscaOrgSpeList(OscaOrgSpe oscaOrgSpe) {
        OscaOrgSpeExample example=new OscaOrgSpeExample();
        example.setDistinct(true);
        OscaOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaOrgSpe.getOrgFlow())){
            criteria.andOrgFlowEqualTo(oscaOrgSpe.getOrgFlow());
        }
        return oscaOrgSpeMapper.selectByExample(example);
    }

    @Override
    public List<SysOrg> searchOrgList(SysOrg sysOrg) {
        SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sysOrg.getOrgProvId())){
            criteria.andOrgProvIdEqualTo(sysOrg.getOrgProvId());
        }
        if(StringUtil.isNotBlank(sysOrg.getOrgCityId())){
            criteria.andOrgCityIdEqualTo(sysOrg.getOrgCityId());
        }
        if(StringUtil.isNotBlank(sysOrg.getOrgName())){
            criteria.andOrgNameLike(sysOrg.getOrgName());
        }
        return sysOrgMapper.selectByExample(example);
    }

    @Override
    public List<SysOrg> serachOrgCity(SysOrg sysOrg) {
        Map<String, Object> map=new HashMap<>();
        map.put("orgProvId",sysOrg.getOrgProvId());
        return oscaSysOrgExtMapper.searchOrgCitys(map);
    }

    @Override
    public List<OscaOrgSpeExt> searchAllOrg(Map<String, Object> map) {
        return oscaSysOrgExtMapper.searchAllOrg(map);
    }

    @Override
    public List<OscaOrgSpeExt> searchAllSites(Map<String, Object> map) {
        return oscaSysOrgExtMapper.searchAllSites(map);
    }

    @Override
    public void removeOscaOrgSpe(Map<String, Object> map) {
        oscaSysOrgExtMapper.updateOscaOrgSpe(map);
    }

    @Override
    public List<Map<String, Object>> searchManagerList(Map<String, Object> paramMap) {
        return oscaSysOrgExtMapper.searchManagerList(paramMap);
    }
}
