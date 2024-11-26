package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchDoctorDeptMapper;
import com.pinde.sci.dao.sch.SchDoctorDeptExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchDoctorDeptBizImpl implements ISchDoctorDeptBiz {
	@Autowired
	private SchDoctorDeptMapper doctorDeptMapper;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDoctorBiz schDoctortBiz;
	@Autowired
	private SchDoctorDeptExtMapper doctorDeptExtMapper;

//	@Override
//	public List<SchDoctorDept> searchSchDoctorDept() {
//		SchDoctorDeptExample example = new SchDoctorDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		example.setOrderByClause("ORDINAL");
//		return doctorDeptMapper.selectByExample(example);
//	}
	
	@Override
	public List<SchDoctorDept> searchDoctorDeptByDoctorFlows(List<String> doctorFlows){
		SchDoctorDeptExample example = new SchDoctorDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(doctorFlows);
		example.setOrderByClause("ORDINAL");
		return doctorDeptMapper.selectByExample(example);
	}

	@Override
	public List<SchDoctorDept> searchSchDoctorDept(String doctorFlow) {
		SchDoctorDeptExample example = new SchDoctorDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("ORDINAL");
		return doctorDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchDoctorDept> searchSchDoctorDeptIgnoreStatus(String doctorFlow,String orgFlow) {
		SchDoctorDeptExample example = new SchDoctorDeptExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return doctorDeptMapper.selectByExample(example);
	}
	
	@Override
	public int countSchDoctorDeptIgnoreStatus(String doctorFlow,String rotationFlow,String orgFlow) {
		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)){
			SchDoctorDeptExample example = new SchDoctorDeptExample();
			example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow).andOrgFlowEqualTo(orgFlow);
			example.setOrderByClause("ORDINAL");
			return doctorDeptMapper.countByExample(example);
		}
		return 0;
	}

//	@Override
//	public List<SchDoctorDept> searchDoctorDeptForReduction(String doctorFlow,String rotationFlow) {
//		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)){
//			SchDoctorDeptExample example = new SchDoctorDeptExample();
//			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
//			.andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
//			example.setOrderByClause("ORDINAL");
//			return doctorDeptMapper.selectByExample(example);
//		}
//		return null;
//	}
	
	@Override
	public List<SchDoctorDept> searchDoctorDeptForReductionIgnoreStatus(String doctorFlow,String rotationFlow) {
		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)){
			SchDoctorDeptExample example = new SchDoctorDeptExample();
			example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
			example.setOrderByClause("ORDINAL");
			return doctorDeptMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public SchDoctorDept readSchDoctorDept(String recordFlow) {
		return doctorDeptMapper.selectByPrimaryKey(recordFlow);
	}
	
	@Override
	public SchDoctorDept readSchDoctorDeptByObj(String doctorFlow,String schDeptFlow,String groupFlow,String standardDeptId) {
		SchDoctorDept doctorDept = null;
		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(schDeptFlow)){
			SchDoctorDeptExample example = new SchDoctorDeptExample();
			example.createCriteria().andDoctorFlowEqualTo(doctorFlow)
			.andSchDeptFlowEqualTo(schDeptFlow)
			.andGroupFlowEqualTo(groupFlow)
			.andStandardDeptIdEqualTo(standardDeptId);
			List<SchDoctorDept> doctorDeptList = doctorDeptMapper.selectByExample(example);
			if(doctorDeptList != null && doctorDeptList.size()>0){
				doctorDept = doctorDeptList.get(0);
			}
		}
		return doctorDept;
	}

	@Override
	public int editDoctorDept(SchDoctorDept doctorDept){
		if(doctorDept!=null){
			if(StringUtil.isNotBlank(doctorDept.getRecordFlow())){
				GeneralMethod.setRecordInfo(doctorDept,false);
				return doctorDeptMapper.updateByPrimaryKeySelective(doctorDept);
			}else{
				doctorDept.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(doctorDept,true);
				return doctorDeptMapper.insertSelective(doctorDept);
			}
			//return saveSelDeptFlag(doctorDept);
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveSchDoctorDept(SchDoctorDept doctorDept) {
		if(doctorDept != null){
			if(StringUtil.isNotBlank(doctorDept.getRecordFlow())){
				return update(doctorDept);
			}else{
				return save(doctorDept);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int save(SchDoctorDept doctorDept){
		if(doctorDept!=null){
			SchDept schDept = schDeptBiz.readSchDept(doctorDept.getSchDeptFlow());
			if(schDept!=null){
				doctorDept.setDeptFlow(schDept.getDeptFlow());
				doctorDept.setDeptName(schDept.getDeptName());
			}
			doctorDept.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(doctorDept, true);
			return doctorDeptMapper.insertSelective(doctorDept);
			//return saveSelDeptFlag(doctorDept);
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int update(SchDoctorDept doctorDept){
		GeneralMethod.setRecordInfo(doctorDept, false);
		return doctorDeptMapper.updateByPrimaryKeySelective(doctorDept);
		//return saveSelDeptFlag(doctorDept);
	}
	
	private int saveSelDeptFlag(SchDoctorDept doctorDept){
		doctorDept = readSchDoctorDept(doctorDept.getRecordFlow());
		int deptNumCount = schRotationtGroupBiz.sumDeptNumByRotation(doctorDept.getRotationFlow());
		
		List<SchDoctorDept> doctorDeptList = searchSchDoctorDept(doctorDept.getDoctorFlow());
		
		ResDoctor doctor = new ResDoctor();
		doctor.setDoctorFlow(doctorDept.getDoctorFlow());
		doctor.setSelDeptFlag(GlobalConstant.FLAG_N);
		
		if(doctorDeptList != null && !doctorDeptList.isEmpty() && doctorDeptList.size() == deptNumCount){
			doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
		}
		return schDoctortBiz.saveResDoctor(doctor);
	}
	
	@Override
	public int countRotationUse(String rotationFlow){
		return doctorDeptExtMapper.countRotationUse(rotationFlow);
	}
	
	@Override
	public int cleanInvalidSelDept(Map<String,Object> paramMap){
		return doctorDeptExtMapper.cleanInvalidSelDept(paramMap);
	}

	@Override
	public int editDoctorDeptList(List<SchDoctorDept> doctorDeptList){
		if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
			for(SchDoctorDept sdd : doctorDeptList){
				if(sdd!=null){
					if(StringUtil.isNotBlank(sdd.getRecordFlow())){
						GeneralMethod.setRecordInfo(sdd,false);
						doctorDeptMapper.updateByPrimaryKeySelective(sdd);
					}else{
						String recordStatus = sdd.getRecordStatus();
						sdd.setRecordFlow(PkUtil.getUUID());
						GeneralMethod.setRecordInfo(sdd,true);
						if(StringUtil.isNotBlank(recordStatus)){
							sdd.setRecordStatus(recordStatus);
						}
						doctorDeptMapper.insertSelective(sdd);
					}
				}
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int countReducation(String orgFlow){
		return doctorDeptExtMapper.countReducation(orgFlow);
	}
	@Override
	public int jszyCountReducation(String orgFlow){
		return doctorDeptExtMapper.jszyCountReducation(orgFlow);
	}


}
