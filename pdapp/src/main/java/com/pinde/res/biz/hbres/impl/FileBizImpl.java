package com.pinde.res.biz.hbres.impl;


import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.PubFileExample;
import com.pinde.sci.model.mo.PubFileExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FileBizImpl implements IFileBiz {

	@Autowired
	private PubFileMapper pubFileMapper;


	@Override
	public List<PubFile> searchByProductFlow(String productFlow) {
		if(StringUtil.isBlank(productFlow))
			return  null;
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(productFlow)){
			criteria.andProductFlowEqualTo(productFlow);
		}
		return pubFileMapper.selectByExample(example);
	}

	@Override
	public void addFile(PubFile file) {
		if (file.getFileContent() != null) {
			file.setFileSize(new BigDecimal(file.getFileContent().length));
		}
		this.pubFileMapper.insert(file);
	}

	@Override
	public PubFile readFile(String fileFlow) {
		return pubFileMapper.selectByPrimaryKey(fileFlow);
	}

	@Override
	public void editFile(PubFile pubFile) {
		if(StringUtil.isNotBlank(pubFile.getFileFlow()))
		{
			pubFileMapper.updateByPrimaryKeySelective(pubFile);
		}else{
			pubFile.setFileFlow(PkUtil.getUUID());
			pubFileMapper.insertSelective(pubFile);
		}
	}

	@Override
	public PubFile readDocGraduationFile(PubFile search) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(search.getProductType())){
			criteria.andProductTypeEqualTo(search.getProductType());
		}
		if(StringUtil.isNotBlank(search.getCreateUserFlow())){
			criteria.andCreateUserFlowEqualTo(search.getCreateUserFlow());
		}
		List<PubFile> list=pubFileMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public PubFile searchByProductFlowAndType(String productFlow, String productType) {
		if(StringUtil.isBlank(productFlow)||StringUtil.isBlank(productType))
			return  null;
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(productFlow)){
			criteria.andProductFlowEqualTo(productFlow);
		}
		if(StringUtil.isNotBlank(productType)){
			criteria.andProductTypeEqualTo(productType);
		}
		List<PubFile> files= pubFileMapper.selectByExample(example);
		if(files!=null&&!files.isEmpty())
		{
			return files.get(0);
		}
		return null;
	}

	@Override
	public List<PubFile> findFileByTypeFlow(String productType, String productFlow) {

		if(StringUtil.isBlank(productFlow))
			return  null;
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(productFlow)){
			criteria.andProductFlowEqualTo(productFlow);
		}
		if(StringUtil.isNotBlank(productType)){
			criteria.andProductTypeEqualTo(productType);
		}
		return pubFileMapper.selectByExample(example);
	}

	@Override
	public PubFile readProductFile(String resultFlow, String fileType) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andProductTypeEqualTo(fileType);
		criteria.andProductFlowEqualTo(resultFlow);
		List<PubFile> list=pubFileMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
}
