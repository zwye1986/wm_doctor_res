package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.RegisProjFundExt;

import java.util.List;
import java.util.Map;

/**
 * Created by MyPC on 2016/12/21.
 */
public interface IRegisProjBiz {
    /**
     * 保存（更新）项目信息（无锡二院项目登记）
     * @param proj
     */
    void saveRegisProjInfo(PubProj proj, PubProjProcess process, SrmProjFundInfo fundInfo, List<SrmProjFundDetail> fundDetailList, boolean projIsExist,PubProjAuthor projAuthor);

    List<RegisProjFundExt> searchRegisProjFundExt(Map<String,String> map);

    void updateDetailStatus(SrmProjFundDetail fundDetail, SrmFundProcess fundProcess);

    void saveRealityAmount(SrmProjFundInfo fundInfo,PubProjAuthor projAuthor);
}
