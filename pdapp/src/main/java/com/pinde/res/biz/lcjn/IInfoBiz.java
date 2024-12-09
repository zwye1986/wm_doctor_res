package com.pinde.res.biz.lcjn;

import com.pinde.core.model.InxInfo;

import java.util.List;

public interface IInfoBiz {

	List<InxInfo> getDocNoReadInfos(String userFlow);

	int saveDocNoReadInfos(String userFlow);

	List<InxInfo> getInfos(String userFlow);

	InxInfo readNoticeByFlow(String infoFlow);
}
  