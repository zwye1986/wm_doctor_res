package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResRegMapper;
import com.pinde.core.model.ResReg;
import com.pinde.core.model.ResRegExample;
import com.pinde.core.model.ResRegExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResRegBiz;
import com.pinde.sci.common.GeneralMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResRegBizImpl implements IResRegBiz{
	@Autowired
	private ResRegMapper regMapper;

//	@Override
//	public	List<ResReg> searchResReg(){
//		ResRegExample example = new ResRegExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		return regMapper.selectByExample(example);
//	}

	@Override
	public ResReg searchResReg(String userFlow,String regYear){
		ResReg reg = null;
		ResRegExample example = new ResRegExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserFlowEqualTo(userFlow).andRegYearEqualTo(regYear);
		List<ResReg> list = regMapper.selectByExample(example);
		if (list != null && list.size() >0) {
			reg = list.get(0);
		}
		return reg;
	}
	
	@Override
	public	int editResReg(ResReg reg){
		if(reg!=null){
			if(StringUtil.isNotBlank(reg.getRegFlow())){
				GeneralMethod.setRecordInfo(reg, false);
				return regMapper.updateByPrimaryKeySelective(reg);
			}else{
				reg.setRegFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(reg, true);
				return regMapper.insertSelective(reg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResReg searchRecentYearResReg(String userFlow) {
		ResRegExample example = new ResRegExample();
		example.setOrderByClause("REG_YEAR DESC");
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andUserFlowEqualTo(userFlow);
		List<ResReg> regs = this.regMapper.selectByExample(example);
		if(!regs.isEmpty()){
			return regs.get(0);
		}
		return null;
	}

	@Override
	public List<ResReg> searchAllResReg(String userFlow) {
		ResRegExample example = new ResRegExample();
		example.setOrderByClause("REG_YEAR DESC");
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andUserFlowEqualTo(userFlow);
		return this.regMapper.selectByExample(example);
	}
}
 