package com.pinde.res.biz.jswjw.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJsResBaseBiz;
import com.pinde.sci.dao.base.ResPassScoreCfgMapper;
import com.pinde.sci.model.mo.ResPassScoreCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class JsResBaseBizImpl implements IJsResBaseBiz {
	@Autowired
	private ResPassScoreCfgMapper resPassScoreCfgMapper;
	@Override
	public ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg){
		ResPassScoreCfg passScoreCfg=null;
		 if(StringUtil.isNotBlank(resPassScoreCfg.getCfgYear())){
			 passScoreCfg = resPassScoreCfgMapper.selectByPrimaryKey(resPassScoreCfg.getCfgYear());
		 }
		return passScoreCfg;
	}

}
