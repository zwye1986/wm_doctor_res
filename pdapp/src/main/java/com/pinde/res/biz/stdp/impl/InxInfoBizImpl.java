package com.pinde.res.biz.stdp.impl;

import com.pinde.core.common.sci.dao.InxInfoMapper;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.ResReadInfo;
import com.pinde.core.model.ResReadInfoExample;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IInxInfoBiz;
import com.pinde.sci.dao.base.ResReadInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class InxInfoBizImpl implements IInxInfoBiz {
	@Autowired
	private InxInfoMapper inxInfoMapper;
	@Autowired
	private ResReadInfoMapper resReadInfoMapper;

	
	@Override
	public InxInfo getByInfoFlow(String infoFlow) {
		return inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

	@Override
	public ResReadInfo getReadInfoByFlow(String infoFlow, String userFlow) {
		ResReadInfoExample example=new ResReadInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andInfoFlowEqualTo(infoFlow).andUserFlowEqualTo(userFlow);
		List<ResReadInfo> list=resReadInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveReadInfo(String userFlow, ResReadInfo d) {
		if(StringUtil.isNotBlank(d.getRecordFlow())){
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			return resReadInfoMapper.updateByPrimaryKeySelective(d);
		}else {
			d.setRecordFlow(PkUtil.getUUID());
			d.setCreateTime(DateUtil.getCurrDateTime());
			d.setCreateUserFlow(userFlow);
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
            d.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			return resReadInfoMapper.insertSelective(d);
		}
	}
}
