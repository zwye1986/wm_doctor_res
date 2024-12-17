package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchDeptExternalRelBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchDeptExternalRelMapper;
import com.pinde.sci.dao.sch.SchDeptExtMapper;
import com.pinde.core.model.SchDeptExternalRel;
import com.pinde.core.model.SchDeptExternalRelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchDeptExternalRelBizImpl implements ISchDeptExternalRelBiz {
	@Autowired
	private SchDeptExternalRelMapper deptExtRelMapper;
	@Autowired
	private SchDeptExtMapper deptExtMapper;

	@Override
	public int editSchDeptExtRel(SchDeptExternalRel schDeptExtRel) {
		if(schDeptExtRel !=null){
			if(StringUtil.isNotBlank(schDeptExtRel.getRecordFlow())){
				GeneralMethod.setRecordInfo(schDeptExtRel, false);
				return deptExtRelMapper.updateByPrimaryKeySelective(schDeptExtRel);
			}else{
				schDeptExtRel.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(schDeptExtRel, true);
				return deptExtRelMapper.insertSelective(schDeptExtRel);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

//	@Override
//	public SchDeptExternalRel readSchDeptExtRel(String recordFlow) {
//		return deptExtRelMapper.selectByPrimaryKey(recordFlow);
//	}

	@Override
	public SchDeptExternalRel readSchDeptExtRelBySchDept(String schDeptFlow) {
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowEqualTo(schDeptFlow);
		SchDeptExternalRel schDeptExtRel = null;
		List<SchDeptExternalRel> deptExtRelList = deptExtRelMapper.selectByExample(example);
		if(deptExtRelList!=null && deptExtRelList.size()>0){
			schDeptExtRel = deptExtRelList.get(0);
		}
		return schDeptExtRel;
	}
	
	@Override
	public SchDeptExternalRel readSchDeptExtRelByDeptAndRelOrgFlow(String deptFlow,String relOrgFlow) {
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
		example.createCriteria().andDeptFlowEqualTo(deptFlow).andRelOrgFlowEqualTo(relOrgFlow);
		SchDeptExternalRel schDeptExtRel = null;
		List<SchDeptExternalRel> deptExtRelList = deptExtRelMapper.selectByExample(example);
		if(deptExtRelList!=null && deptExtRelList.size()>0){
			schDeptExtRel = deptExtRelList.get(0);
		}
		return schDeptExtRel;
	}

	@Override
	public List<SchDeptExternalRel> readSchDeptExtRelByRelOrgFlow(String relOrgFlow) {
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
		example.createCriteria().andRelOrgFlowEqualTo(relOrgFlow);
		return deptExtRelMapper.selectByExample(example);
	}

	@Override
	public List<SchDeptExternalRel> readSchDeptExtRelByDept(String deptFlow) {
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDeptFlowEqualTo(deptFlow);
		return deptExtRelMapper.selectByExample(example);
	}

	@Override
	public List<SchDeptExternalRel> searchSchDeptExtRel(String orgFlow) {
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		return deptExtRelMapper.selectByExample(example);
	}
	
	@Override
	public int delSchDeptRel(String schDeptFlow){
		SchDeptExternalRel deptExtRel = new SchDeptExternalRel();
        deptExtRel.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowEqualTo(schDeptFlow);
		return deptExtRelMapper.updateByExampleSelective(deptExtRel,example);
	}

//	@Override
//	public int getExternalOrgNum(String deptFlow){
//		SchDeptExternalRelExample example = new SchDeptExternalRelExample();
//
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//		.andDeptFlowEqualTo(deptFlow);
//
//		return deptExtRelMapper.countByExample(example);
//	}
	
	@Override
	public int getExternalNum(String userFlow){
		if(!StringUtil.isNotBlank(userFlow)){
			return 0;
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow",userFlow);
		return deptExtMapper.getExternalNum(paramMap);
	}
}
