package com.pinde.res.biz.stdp;

import com.pinde.sci.model.mo.SchAndStandardDeptCfg;

import java.util.List;


public interface ISchAndStandardDeptCfgBiz {

	SchAndStandardDeptCfg readByFlow(String recordFlow);

	List<SchAndStandardDeptCfg> getListByOrgFlow(String orgFlow);

	SchAndStandardDeptCfg readBySchDeptFlow(String schDeptFlow);
}
