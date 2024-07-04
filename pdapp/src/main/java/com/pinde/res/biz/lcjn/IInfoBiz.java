package com.pinde.res.biz.lcjn;

import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserRole;

import java.util.List;

public interface IInfoBiz {

	List<InxInfo> getDocNoReadInfos(String userFlow);

	int saveDocNoReadInfos(String userFlow);

	List<InxInfo> getInfos(String userFlow);

	InxInfo readNoticeByFlow(String infoFlow);
}
  