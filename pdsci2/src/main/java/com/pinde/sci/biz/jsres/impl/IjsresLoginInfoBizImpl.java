package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IjsresLoginInfoBiz;
import com.pinde.sci.dao.base.JsresLoginInfoMapper;
import com.pinde.sci.model.mo.JsresLoginInfo;
import com.pinde.sci.model.mo.JsresLoginInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class IjsresLoginInfoBizImpl implements IjsresLoginInfoBiz{
	@Autowired
	private JsresLoginInfoMapper jsresLoginInfoMapper;

	@Override
	public List<JsresLoginInfo> search(JsresLoginInfo jsresLoginInfo) {
		JsresLoginInfoExample example = new JsresLoginInfoExample();
		JsresLoginInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(jsresLoginInfo.getLoginDomain())){
			criteria.andLoginDomainEqualTo(jsresLoginInfo.getLoginDomain());
		}
		if(StringUtil.isNotBlank(jsresLoginInfo.getLoginTitle())){
			criteria.andLoginTitleEqualTo(jsresLoginInfo.getLoginTitle());
		}
		if(StringUtil.isNotBlank(jsresLoginInfo.getLoginUrl())){
			criteria.andLoginUrlEqualTo(jsresLoginInfo.getLoginUrl());
		}
		if(StringUtil.isNotBlank(jsresLoginInfo.getLoginDomainSuffix())){
			criteria.andLoginDomainSuffixEqualTo(jsresLoginInfo.getLoginDomainSuffix());
		}
		return jsresLoginInfoMapper.selectByExample(example);
	}
}
