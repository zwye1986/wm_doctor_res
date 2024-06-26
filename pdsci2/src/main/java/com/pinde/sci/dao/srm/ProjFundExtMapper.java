package com.pinde.sci.dao.srm;

import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.srm.*;

import java.util.List;
import java.util.Map;

/**
 * 经费扩展接口
 *
 * @author Administrator
 */
public interface ProjFundExtMapper {

    List<ProjFundDetailExt> selectProjFundDetailList(ProjFundDetailExt projFundDetailExt);

    List<PubProjExt> selectProjFundInfoList(PubProjExt projExt);

    ProjFundDetailExt selectProjFundDetailExt(String fundDetailFlow);

    List<ProjFundFormExt> selectProjFundFormList(ProjFundFormExt projFundFormExt);

    ProjFundFormExt selectProjFundFormExt(String fundFormFlow);

    List<PubProjExt> selectProjFundInfoExtList(PubProjExt projExt);

    List<PubProjExt> selectReimburseInfoExtList(PubProjExt projExt);

    List<FundDetailAndSchemeExt> selectFundDetailExtList(Map<String,Object> map);

    List<SrmProjFundDetail> selectFundDetailList(Map<String,Object> map);

    List<SrmProjFundInfo> selectFundInfoList(Map<String,Object> map);
}
