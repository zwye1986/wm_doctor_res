package com.pinde.sci.biz.irb.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.irb.IIrbRegulationBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.IrbRegulationMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.enums.irb.IrbRegulationTypeEnum;
import com.pinde.sci.model.mo.IrbRegulation;
import com.pinde.sci.model.mo.IrbRegulationExample;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
/**
 * 
 * @author tiger
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IrbRegulationBizImpl implements IIrbRegulationBiz {
	@Autowired
	private IrbRegulationMapper regulationMapper;
	@Autowired
	private PubFileMapper fileMapper;
	@Autowired
	private IFileBiz fileBiz;

	@Override
	public List<IrbRegulation> queryRegulationList(IrbRegulation regulation) {
		IrbRegulationExample example = new IrbRegulationExample();
		com.pinde.sci.model.mo.IrbRegulationExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(regulation.getRegTypeId())){
			criteria.andRegTypeIdEqualTo(regulation.getRegTypeId());
		}
		example.setOrderByClause("ORDINAL");
		return regulationMapper.selectByExample(example);
	}

	@Override
	public int editRegulation(PubFile file, IrbRegulation regulation) {
		if (file.getFileContent() != null) {
			file.setFileSize(new BigDecimal(file.getFileContent().length));
		}
		if(StringUtil.isNotBlank(file.getFileFlow())){//修改文件
			GeneralMethod.setRecordInfo(file, false);
			fileMapper.updateByPrimaryKeyWithBLOBs(file);
		}else{//新增
			file.setFileFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(file, true);
			fileMapper.insert(file);
		}
		
		if(StringUtil.isNotBlank(regulation.getRegFlow())){ //修改
			regulation.setFileFlow(file.getFileFlow());//关联文件(文件可能删除)
			GeneralMethod.setRecordInfo(regulation, false);
			//regulation.setRegTypeName(IrbRegulationTypeEnum.getNameById(regulation.getRegTypeId()));
			regulationMapper.updateByPrimaryKeySelective(regulation);
		}else{//新增
			regulation.setRegFlow(PkUtil.getUUID());
			regulation.setFileFlow(file.getFileFlow());//关联文件
			regulation.setRegTypeName(IrbRegulationTypeEnum.getNameById(regulation.getRegTypeId()));
			GeneralMethod.setRecordInfo(regulation, true);
			regulationMapper.insert(regulation);
		}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public IrbRegulation getRegulationByFlow(String regFlow) {
		if(StringUtil.isNotBlank(regFlow)){
			return regulationMapper.selectByPrimaryKey(regFlow);
		}
		return null;
	}

}
