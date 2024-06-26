package com.pinde.sci.biz.irb.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.irb.IIrbProjBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.PubProjExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class IrbProjBizImpl implements IIrbProjBiz {


    @Autowired
    private PubProjMapper projMapper;
    @Autowired
    private IFileBiz fileBiz;


    @Override
    public PubProj readProject(String projFlow) {
        return projMapper.selectByPrimaryKey(projFlow);
    }


    @Override
    public List<PubProj> queryProjList(PubProj proj) {
        PubProjExample example = new PubProjExample();
        PubProjExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(proj.getProjName())) {
            criteria.andProjNameLike("%" + proj.getProjName() + "%");
        }
        if (StringUtil.isNotBlank(proj.getProjShortName())) {
            criteria.andProjShortNameLike("%" + proj.getProjShortName() + "%");
        }
        if (StringUtil.isNotBlank(proj.getProjNo())) {
            criteria.andProjNoLike("%" + proj.getProjNo() + "%");
        }
        if (StringUtil.isNotBlank(proj.getApplyUserFlow())) {
            criteria.andApplyUserFlowEqualTo(proj.getApplyUserFlow());
        }
        if (StringUtil.isNotBlank(proj.getApplyUserName())) {
            criteria.andApplyUserNameLike("%" + proj.getApplyUserName() + "%");
        }
        if (StringUtil.isNotBlank(proj.getApplyDeptName())) {
            criteria.andApplyDeptNameLike("%" + proj.getApplyDeptName() + "%");
        }

        if (StringUtil.isNotBlank(proj.getProjStageId())) {//阶段
            List<String> projStageIdList = new ArrayList<String>();
            String[] projStageIds = proj.getProjStageId().split(",");
            projStageIdList = Arrays.asList(projStageIds);
            criteria.andProjStageIdIn(projStageIdList);
        }
        if (StringUtil.isNotBlank(proj.getProjCategoryId())) {//类别
            List<String> projCategoryIdList = new ArrayList<String>();
            String[] projCategoryIds = proj.getProjCategoryId().split(",");
            projCategoryIdList = Arrays.asList(projCategoryIds);
            criteria.andProjCategoryIdIn(projCategoryIdList);
        }
        if (StringUtil.isNotBlank(proj.getProjSubTypeId())) {//期类别
            criteria.andProjSubTypeIdEqualTo(proj.getProjSubTypeId());
        }
        if (StringUtil.isNotBlank(proj.getApplyOrgFlow())) {//机构
            criteria.andApplyOrgFlowEqualTo(proj.getApplyOrgFlow());
        }
        if (StringUtil.isNotBlank(proj.getApplyDeptFlow())) {//承担科室
            criteria.andApplyDeptFlowEqualTo(proj.getApplyDeptFlow());
        }
        if (StringUtil.isNotBlank(proj.getProjStatusId())) {//状态
            criteria.andProjStatusIdEqualTo(proj.getProjStatusId());
        }
        if (StringUtil.isNotBlank(proj.getProjTypeId())) {
            criteria.andProjTypeIdEqualTo(proj.getProjTypeId());
        }
        example.setOrderByClause("CREATE_TIME DESC, PROJ_START_TIME DESC, PROJ_END_TIME");
        return projMapper.selectByExample(example);
    }


}
