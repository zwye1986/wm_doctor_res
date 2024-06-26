package com.pinde.sci.biz.eval;

import com.pinde.sci.model.mo.ExpertOrgSpe;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IExpertOrgSpeBiz {

    List<ExpertOrgSpe> readByOrgAndYear(String orgFlow, String evalYear, String recordStatus);

    ExpertOrgSpe readByFlow(String recordFlow);

    ExpertOrgSpe getByOrgAndYear(String orgFlow, String evalYear, String speId,String recordStatus);

    int save(ExpertOrgSpe old);
}
