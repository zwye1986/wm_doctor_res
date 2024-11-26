package com.pinde.sci.biz.osca.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaOrgMenuBiz;
import com.pinde.sci.dao.base.OscaOrgMenuMapper;
import com.pinde.sci.dao.osca.OscaOrgMenuExtMapper;
import com.pinde.sci.dao.osca.OscaSysOrgExtMapper;
import com.pinde.sci.model.mo.OscaOrgMenu;
import com.pinde.sci.model.mo.OscaOrgMenuExample;
import com.pinde.sci.model.osca.OscaOrgMenuExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaOrgMenuBizImpl implements IOscaOrgMenuBiz{
    @Autowired
    private OscaSysOrgExtMapper oscaSysOrgExtMapper;
    @Autowired
    private OscaOrgMenuMapper oscaOrgMenuMapper;
    @Autowired
    private OscaOrgMenuExtMapper oscaOrgMenuExtMapper;

    @Override
    public int saveOscaOrgMenu(OscaOrgMenu oscaOrgMenu) {
        return oscaOrgMenuMapper.insertSelective(oscaOrgMenu);
    }

    @Override
    public List<OscaOrgMenuExt> searchOscaOrgMenuList(OscaOrgMenuExt oscaOrgMenuExt) {
        String orgName="";
        String orgProvId="";
        if (oscaOrgMenuExt!=null){
            orgName=oscaOrgMenuExt.getOrgName();
            orgProvId=oscaOrgMenuExt.getOrgProvId();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("orgName",orgName);
        map.put("orgProvId",orgProvId);
        return oscaOrgMenuExtMapper.searchSpeOrgs(map);
    }

    @Override
    public int updateOscaOrgMenu(OscaOrgMenu oscaOrgMenu) {
        return oscaOrgMenuMapper.updateByPrimaryKeySelective(oscaOrgMenu);
    }

    @Override
    public void deleteOscaOrgMenu(OscaOrgMenu oscaOrgMenu) {
        OscaOrgMenuExample example=new OscaOrgMenuExample();
        OscaOrgMenuExample.Criteria criteria=example.createCriteria();
        if (StringUtil.isNotBlank(oscaOrgMenu.getOrgFlow())){
            criteria.andOrgFlowEqualTo(oscaOrgMenu.getOrgFlow());
        }
        if (StringUtil.isNotBlank(oscaOrgMenu.getMenuId())){
            criteria.andMenuIdEqualTo(oscaOrgMenu.getMenuId());
        }
        oscaOrgMenuMapper.deleteByExample(example);
    }

    @Override
    public List<OscaOrgMenu> searchAllOrgMenu(OscaOrgMenu oscaOrgMenu) {
        OscaOrgMenuExample example=new OscaOrgMenuExample();
        OscaOrgMenuExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(oscaOrgMenu.getOrgFlow())){
            criteria.andOrgFlowEqualTo(oscaOrgMenu.getOrgFlow());
        }
        if (StringUtil.isNotBlank(oscaOrgMenu.getMenuId())){
            criteria.andMenuIdEqualTo(oscaOrgMenu.getMenuId());
        }
        return oscaOrgMenuMapper.selectByExample(example);
    }
}
