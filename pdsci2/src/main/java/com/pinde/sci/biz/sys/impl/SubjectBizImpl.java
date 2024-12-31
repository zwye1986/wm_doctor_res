package com.pinde.sci.biz.sys.impl;

import com.pinde.core.common.sci.dao.SysSubjCodeMapper;
import com.pinde.core.model.SysSubjCode;
import com.pinde.core.model.SysSubjCodeExample;
import com.pinde.sci.biz.sys.ISubjectBiz;
import com.pinde.sci.dao.sys.SysSubjCodeExtMapper;
import com.pinde.sci.form.sys.SubjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SubjectBizImpl implements ISubjectBiz {
	@Autowired
	private SysSubjCodeMapper sysSubjCodeMapper;
	@Autowired
	private SysSubjCodeExtMapper sysSubjCodeExtMapper;


	@Override
	public List<SysSubjCode> getAll(String recordStatus) {
		SysSubjCodeExample example = new SysSubjCodeExample();
		example.createCriteria().andRecordStatusEqualTo(recordStatus);
		example.setOrderByClause("ordinal asc");
		return this.sysSubjCodeMapper.selectByExample(example);
	}

	@Override
	public int save(SysSubjCode subject) {
		return this.sysSubjCodeMapper.insertSelective(subject);
	}

	@Override
	public int updateByIds(List<String> ids) {
		SubjectForm form = new SubjectForm();
		form.setIds(ids);
		return this.sysSubjCodeExtMapper.updateByIds(form);
	}

	@Override
	public int update(SysSubjCode subject) {
		return this.sysSubjCodeMapper.updateByPrimaryKeySelective(subject);
	}

	@Override
	public SysSubjCode getByFlow(String flow) {
		return this.sysSubjCodeMapper.selectByPrimaryKey(flow);
	}

	@Override
	public SysSubjCode getById(String id) {
		SysSubjCodeExample example = new SysSubjCodeExample();
        example.createCriteria().andSubjIdEqualTo(id).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysSubjCode> list = this.sysSubjCodeMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateParentId(String id, String parentId) {
		SubjectForm form = new SubjectForm();
		form.setId(id);
		form.setParentId(parentId);
		return this.sysSubjCodeExtMapper.updateParentId(form);
	}
	

}
