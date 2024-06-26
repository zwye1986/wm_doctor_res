package com.pinde.sci.dao.pub;

import com.pinde.sci.model.pub.PubTrainUserExt;

import java.util.List;

public interface PubTrainUserExtMapper {
	List<PubTrainUserExt> selectGcpList(List<String> userFlows);
}
