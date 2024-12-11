package com.pinde.res.biz.lcjn.impl;


import com.pinde.core.model.InxInfo;
import com.pinde.res.biz.lcjn.IInfoBiz;
import com.pinde.res.dao.lcjn.ext.InxInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class InfoBizImpl implements IInfoBiz {
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;

	@Override
	public List<InxInfo> getDocNoReadInfos(String userFlow) {
		return inxInfoExtMapper.getDocNoReadInfos(userFlow);
	}

}
  