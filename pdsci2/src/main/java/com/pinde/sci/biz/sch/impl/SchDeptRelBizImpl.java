package com.pinde.sci.biz.sch.impl;

import com.pinde.core.model.SchDeptRel;
import com.pinde.core.model.SchDeptRelExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchDeptRelBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchDeptRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchDeptRelBizImpl implements ISchDeptRelBiz {
	@Autowired
	private SchDeptRelMapper deptRelMapper;
	
	@Override
	public int editDeptRel(SchDeptRel deptRel){
		if(deptRel!=null){
			if(StringUtil.isNotBlank(deptRel.getRecordFlow())){
				GeneralMethod.setRecordInfo(deptRel, false);
				return deptRelMapper.updateByPrimaryKeySelective(deptRel);
			}else{
				deptRel.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(deptRel, true);
				return deptRelMapper.insertSelective(deptRel);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int depSchDeptRel(String schDeptFlow){
		SchDeptRelExample example = new SchDeptRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowEqualTo(schDeptFlow);
		SchDeptRel deptRel = new SchDeptRel();
        deptRel.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		return deptRelMapper.updateByExampleSelective(deptRel, example);
	}

//	@Override
//	public SchDeptRel readDeptRel(String recordFlow){
//		return deptRelMapper.selectByPrimaryKey(recordFlow);
//	}
	
	@Override
	public List<SchDeptRel> searchRelByStandard(String orgFlow,String standardDeptId){
		SchDeptRelExample example = new SchDeptRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andStandardDeptIdEqualTo(standardDeptId);
		return deptRelMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDeptRel> searchRelBySchDept(String schDeptFlow){
		SchDeptRelExample example = new SchDeptRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowEqualTo(schDeptFlow);
		return deptRelMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDeptRel> searchRelBySchDepts(List<String> schDeptFlows){
		SchDeptRelExample example = new SchDeptRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowIn(schDeptFlows);
		return deptRelMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDeptRel> searchRelByOrg(String orgFlow){
		SchDeptRelExample example = new SchDeptRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		return deptRelMapper.selectByExample(example);
	}
}
