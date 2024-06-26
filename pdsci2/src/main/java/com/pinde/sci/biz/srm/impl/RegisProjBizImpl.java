package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.PubProjAuthorMapper;
import com.pinde.sci.dao.base.SrmFundProcessMapper;
import com.pinde.sci.dao.base.SrmProjFundDetailMapper;
import com.pinde.sci.dao.srm.RegisProjFundExtMapper;
import com.pinde.sci.enums.jsres.GraduationStatusEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AidProjStatusEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.RegisProjFundExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by MyPC on 2016/12/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegisProjBizImpl implements IRegisProjBiz {
    @Autowired
    private IProjProcessBiz processBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private RegisProjFundExtMapper fundExtMapper;
    @Autowired
    private SrmProjFundDetailMapper fundDetailMapper;
    @Autowired
    private SrmFundProcessMapper fundProcessMapper;
    @Autowired
    private PubProjAuthorMapper projAuthorMapper;

    @Override
    public void saveRegisProjInfo(PubProj proj, PubProjProcess process, SrmProjFundInfo fundInfo, List<SrmProjFundDetail> fundDetailList, boolean projIsExist, PubProjAuthor projAuthor) {
        if (!projIsExist) {
            projBiz.savePubProj(proj);
            processBiz.addProcess(process);
        } else {
            projBiz.modProject(proj);
        }
        SrmProjFundDetail delDetail = new SrmProjFundDetail();
        delDetail.setRecordStatus("N");
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        SrmProjFundDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andFundTypeIdEqualTo(ProjFundTypeEnum.Budget.getId());
        if (StringUtil.isNotBlank(fundInfo.getFundFlow())) {
            criteria.andFundFlowEqualTo(fundInfo.getFundFlow());
            fundDetailMapper.updateByExampleSelective(delDetail, example);
        }
        SrmFundProcess fundProcess = new SrmFundProcess();
        fundProcess.setOperateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        fundProcess.setOperateUserName(GlobalContext.getCurrentUser().getUserName());
        fundProcess.setOperStatusId("regisProjFund");
        fundProcess.setOperStatusName("无锡二院项目经费");
        fundInfoBiz.saveFundInfo(fundInfo, fundDetailList, fundProcess);
        //保存项目登记作者
        if(StringUtil.isNotBlank(projAuthor.getScoreFlow()) && StringUtil.isNotBlank(projAuthor.getProjFlow())) {
            GeneralMethod.setRecordInfo(projAuthor, true);
            projAuthor.setAuthorFlow(PkUtil.getUUID());
            projAuthorMapper.insertSelective(projAuthor);
        }
    }

    @Override
    public List<RegisProjFundExt> searchRegisProjFundExt(Map<String,String> map) {
        return fundExtMapper.searchProjFundExt(map);
    }

    public void updateDetailStatus(SrmProjFundDetail fundDetail, SrmFundProcess fundProcess) {
        if (StringUtil.isNotBlank(fundDetail.getFundDetailFlow())) {
            GeneralMethod.setRecordInfo(fundDetail, false);
            if (AchStatusEnum.Pass.getId().equals(fundDetail.getOperStatusId())) {
                //报销时间
                fundDetail.setProvideDateTime(DateUtil.getCurrDateTime());
            }
            fundDetailMapper.updateByPrimaryKeySelective(fundDetail);
            if (StringUtil.isNotBlank(fundDetail.getFundDetailFlow())) {
                fundProcess.setFundDetailFlow(fundDetail.getFundDetailFlow());
            }
            fundProcessMapper.insert(fundProcess);

        }
    }

    @Override
    public void saveRealityAmount(SrmProjFundInfo fundInfo, PubProjAuthor projAuthor) {
        //保存项目登记作者
        if(StringUtil.isNotBlank(projAuthor.getScoreFlow()) && StringUtil.isNotBlank(projAuthor.getProjFlow())) {
            GeneralMethod.setRecordInfo(projAuthor, true);
            projAuthor.setAuthorFlow(PkUtil.getUUID());
            projAuthorMapper.insertSelective(projAuthor);
        }
        fundInfoBiz.updateFundInfo(fundInfo);
    }
}
