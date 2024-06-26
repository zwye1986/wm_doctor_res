package com.pinde.sci.biz.pub.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IModuleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PubModuleMapper;
import com.pinde.sci.dao.edc.PubModuleExtMapper;
import com.pinde.sci.enums.edc.ModuleStyleEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.PubModule;
import com.pinde.sci.model.mo.PubModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor=Exception.class)
public class ModuleBizImpl implements IModuleBiz{

	@Autowired
	private PubModuleMapper pubModuleMapper;
	@Autowired
	private PubModuleExtMapper pubModuleExtMapper;

	@Override
	public List<PubModule> searchModuleList(PubModuleExample example) {
		return pubModuleMapper.selectByExample(example);
	}

	@Override
	public PubModule readPubModule(String flow) {
		return pubModuleMapper.selectByPrimaryKey(flow);
	}

	@Override
	public PubModule readPubModuleByCode(String code) {
		PubModuleExample example = new PubModuleExample();
		 example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andModuleCodeEqualTo(code);
		List<PubModule> result =  searchModuleList(example);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<PubModule> moduleSearch(String keyWord) {
		return pubModuleExtMapper.moduleSearch(keyWord) ;
	}

	@Override
	public int addPubModule(PubModule pm) {
		pm.setModuleFlow(PkUtil.getUUID());
		pm.setModuleCode(PkUtil.getUUID());
		pm.setModuleTypeName(DictTypeEnum.ModuleType.getDictNameById(pm.getModuleTypeId()));
		if (StringUtil.isNotBlank(pm.getModuleStyleId())) {
			pm.setModuleStyleName(ModuleStyleEnum.getNameById(pm.getModuleStyleId()));
		}
		GeneralMethod.setRecordInfo(pm, true);
		return this.pubModuleMapper.insert(pm);
	}

	public int updatePubModule(PubModule pm) {
		pm.setModuleTypeName(DictTypeEnum.ModuleType.getDictNameById(pm.getModuleTypeId()));
		if (StringUtil.isNotBlank(pm.getModuleStyleId())) {
			pm.setModuleStyleName(ModuleStyleEnum.getNameById(pm.getModuleStyleId()));
		}
		GeneralMethod.setRecordInfo(pm, false);
		return this.pubModuleMapper.updateByPrimaryKeySelective(pm);
	}

	@Override
	public List<PubModule> searchModuleListByDomain(String domain) {
		PubModuleExample example = new PubModuleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andModuleTypeIdEqualTo(domain);
		example.setOrderByClause("ORDINAL");
		return searchModuleList(example);
	}
} 
 