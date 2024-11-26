package com.pinde.res.biz.common.impl;

import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysDictExample;
import com.pinde.core.model.SysDictExample.Criteria;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IDictBiz;
import com.pinde.sci.dao.base.SysDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class DictBizImpl implements IDictBiz {
	
	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public SysDict readDict(String dictFlow) {
		return this.sysDictMapper.selectByPrimaryKey(dictFlow);
	}

	@Override
	public SysDict readDict(String dictTypeId,String dictId){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		criteria.andDictIdEqualTo(dictId);
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

	@Override
	public SysDict readAllSecondLevelDict(String dictTypeId, String dictId){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictIdEqualTo(dictId);
		criteria.andDictTypeIdLike(dictTypeId+"%");
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

	@Override
	public SysDict readDict(String dictTypeId,String dictId, String orgFlow){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		criteria.andDictIdEqualTo(dictId);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

    @Override
    public List<SysDict> searchDictListByDictName(SysDict sysDict) {
        SysDictExample example = new SysDictExample();
        Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
            criteria.andDictTypeIdEqualTo(sysDict.getDictTypeId());
        }
        if(StringUtil.isNotBlank(sysDict.getDictName())){
            criteria.andDictNameEqualTo(sysDict.getDictName());
        }
        return this.sysDictMapper.selectByExample(example);
    }

    @Override
	public List<SysDict> searchDictList(SysDict sysDict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			criteria.andDictTypeIdEqualTo(sysDict.getDictTypeId());
		}
		if(StringUtil.isNotBlank(sysDict.getDictId())){
			criteria.andDictIdLike("%"+sysDict.getDictId()+"%");
		}
		if(StringUtil.isNotBlank(sysDict.getDictName())){
			criteria.andDictNameLike("%"+sysDict.getDictName()+"%");
		}
		if(StringUtil.isNotBlank(sysDict.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysDict.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysDict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysDict.getOrgFlow());
		}
		//example.setOrderByClause(" RECORD_STATUS DESC,CREATE_TIME");
		example.setOrderByClause(" ORDINAL");
		return this.sysDictMapper.selectByExample(example);
	}

	@Override
	public List<SysDict> searchDictListAllByDictTypeId(String dictTypeId , boolean isShowAll) {
		
		SysDictExample example = new SysDictExample();
		Criteria criteria  = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		if(!isShowAll){
            criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		}
		example.setOrderByClause(" ORDINAL");
		return this.sysDictMapper.selectByExample(example);
		
	}

	@Override
	public List<SysDict> searchDictListByDictTypeId(String dictTypeId) {
		SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		example.setOrderByClause("ORDINAL");
		return this.sysDictMapper.selectByExample(example);
		
	}

    @Override
    public List<SysDict> searchDictListByDictTypeIdAndDictName(String dictTypeId, String dictName) {
        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andDictNameEqualTo(dictName)
                .andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        example.setOrderByClause("ORDINAL");
        return this.sysDictMapper.selectByExample(example);

    }

    @Override
	public List<SysDict> searchDictListByDictTypeIdAndDictId(String dictTypeId,String dictId) {
		SysDictExample example = new SysDictExample();
        Criteria criteria = example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(dictId))
		{
			criteria.andDictIdEqualTo(dictId);
		}
		example.setOrderByClause("ORDINAL");
		return this.sysDictMapper.selectByExample(example);

	}

	@Override
	public List<SysDict> searchDictListByDictTypeIdNotIncludeSelf(SysDict dict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(dict.getDictTypeId())){
			criteria.andDictTypeIdEqualTo(dict.getDictTypeId());
		}
		if(StringUtil.isNotBlank(dict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(dict.getOrgFlow());
		}
		if(StringUtil.isNotBlank(dict.getDictFlow())){
			criteria.andDictFlowNotEqualTo(dict.getDictFlow());
		}
		return this.sysDictMapper.selectByExample(example);
	}

	@Override
	public List<SysDict> searchAllSecondLevelDictListByDictTypeIdNotIncludeSelf(String dictTypeId ,SysDict dict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(dict.getDictId())){
			criteria.andDictIdEqualTo(dict.getDictId());
		}
		if(StringUtil.isNotBlank(dictTypeId)){
			criteria.andDictTypeIdLike(dictTypeId+"%");
		}
		if(StringUtil.isNotBlank(dict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(dict.getOrgFlow());
		}
		if(StringUtil.isNotBlank(dict.getDictFlow())){
			criteria.andDictFlowNotEqualTo(dict.getDictFlow());
		}
		return this.sysDictMapper.selectByExample(example);
	}
}
