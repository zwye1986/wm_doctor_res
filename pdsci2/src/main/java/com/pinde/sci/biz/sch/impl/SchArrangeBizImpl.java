package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.enums.sch.SchArrangeStatusEnum;
import com.pinde.core.common.enums.sch.SchArrangeTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.sch.ISchArrangeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchArrangeResultExtMapper;
import com.pinde.sci.form.sch.SchGradeFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchArrangeBizImpl implements ISchArrangeBiz {

	@Autowired
	private SchArrangeResultExtMapper resultExtMapper;
	@Autowired
	private SchArrangeMapper arrangeMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SchArrangeDoctorMapper arrangeDoctorMapper;
	@Autowired
	private SchDoctorDeptMapper doctorDeptMapper;
	@Autowired
	private SchArrangeDoctorDeptMapper arrangeDoctorDeptMapper;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationGroupMapper rotationGroupMapper;
	@Autowired
	private SchArrangeBizTask schArrangeBizTask;

	@Override
	public List<SchArrange> searchSchArrange(String orgFLow) {
		SchArrangeExample example = new SchArrangeExample();
		example.createCriteria()
		.andOrgFlowEqualTo(orgFLow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("OPER_TIME DESC");
		return arrangeMapper.selectByExample(example);
	}

	@Override
	public void rostering(String beginDate,final boolean exact) {
		final SysUser currUser = GlobalContext.getCurrentUser();
		//待排班医师-选科结束未排班
		final List<ResDoctor> doctorList = searchCouldSchDoctor(currUser.getOrgFlow());
		//排班记录
		final SchArrange arrange = new SchArrange();
		arrange.setArrangeFlow(PkUtil.getUUID());
		arrange.setBeginDate(beginDate);
		arrange.setDoctorNum(doctorList.size());
		arrange.setOperTime(DateUtil.getCurrDateTime());
		arrange.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		arrange.setOperUserName(GlobalContext.getCurrentUser().getUserName());
		arrange.setArrangeTypeId(SchArrangeTypeEnum.Auto.getId());
		arrange.setArrangeTypeName(SchArrangeTypeEnum.Auto.getName());
		arrange.setArrangeStatusId(SchArrangeStatusEnum.Process.getId());
		arrange.setArrangeStatusName(SchArrangeStatusEnum.Process.getName());
		arrange.setOrgFlow(currUser.getOrgFlow());
		arrange.setOrgName(currUser.getOrgName());
		GeneralMethod.setRecordInfo(arrange, true);
		arrangeMapper.insert(arrange);
		//记录人员及人员排班的信息
		_arrangeDoctorAndDept(arrange, doctorList,currUser);
		//智能排班
		schArrangeBizTask.processDoctor(arrange, doctorList,currUser,exact);	
	}
	
	private void _arrangeDoctorAndDept(SchArrange arrange,List<ResDoctor> doctorList,SysUser currUser){
		SchRotationGroupExample exampleSRG = new SchRotationGroupExample();
        exampleSRG.createCriteria().andOrgFlowEqualTo(currUser.getOrgFlow());//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SchRotationGroup> schRotationGroupList = rotationGroupMapper.selectByExample(exampleSRG);
		Map<String,SchRotationGroup>schRotationGroupMap = new HashMap<String,SchRotationGroup>();
		for(SchRotationGroup schRotationGroup : schRotationGroupList){
			schRotationGroupMap.put(schRotationGroup.getGroupFlow(), schRotationGroup);
		}
		//记录医师留痕
		for(ResDoctor doctor : doctorList){
			SchArrangeDoctor arrangeDoctor = new SchArrangeDoctor();
			arrangeDoctor.setArrDocFlow(PkUtil.getUUID());
			arrangeDoctor.setArrangeFlow(arrange.getArrangeFlow());
			arrangeDoctor.setDoctorFlow(doctor.getDoctorFlow());
			arrangeDoctor.setDoctorName(doctor.getDoctorName());
			arrangeDoctor.setRotationFlow(doctor.getRotationFlow());
			arrangeDoctor.setRotationName(doctor.getRotationName());
			GeneralMethod.setRecordInfo(arrangeDoctor, true);
			arrangeDoctor.setOrgFlow(currUser.getOrgFlow());
			arrangeDoctor.setOrgName(currUser.getOrgName());
			arrangeDoctorMapper.insert(arrangeDoctor);
			
			List<SchArrangeDoctorDept> arrangeDoctorDeptList =  new ArrayList<SchArrangeDoctorDept>();

			//记录必轮科室留痕
			String rotationFlow = doctor.getRotationFlow();
			SchRotationDeptExample srdExample = new SchRotationDeptExample();
            srdExample.createCriteria().andOrgFlowEqualTo(currUser.getOrgFlow()).andRotationFlowEqualTo(rotationFlow).andIsRequiredEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			
			List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(srdExample);
			for(SchRotationDept rotationDept :rotationDeptList){
				SchArrangeDoctorDept arrangeDocDept = new SchArrangeDoctorDept();
				arrangeDocDept.setArrDocDeptFlow(PkUtil.getUUID());
				arrangeDocDept.setArrangeFlow(arrange.getArrangeFlow());
				arrangeDocDept.setDoctorFlow(doctor.getDoctorFlow());
				arrangeDocDept.setDoctorName(doctor.getDoctorName());
				arrangeDocDept.setDeptFlow(rotationDept.getDeptFlow()); 
				arrangeDocDept.setDeptName(rotationDept.getDeptName());
				
				SchRotationGroup schRotationGroup = schRotationGroupMap.get(rotationDept.getGroupFlow());
				arrangeDocDept.setGroupFlow(rotationDept.getGroupFlow());
				arrangeDocDept.setGroupName(schRotationGroup.getGroupName());
				arrangeDocDept.setSchStageId(schRotationGroup.getSchStageId());
				arrangeDocDept.setSchStageName(schRotationGroup.getSchStageName());
				
				arrangeDocDept.setSchDeptFlow(rotationDept.getSchDeptFlow());
				arrangeDocDept.setSchDeptName(rotationDept.getSchDeptName());
				arrangeDocDept.setSchMonth(rotationDept.getSchMonth());
				arrangeDocDept.setOrgFlow(currUser.getOrgFlow());
				arrangeDocDept.setOrgName(currUser.getOrgName());
				arrangeDocDept.setIsRequired(rotationDept.getIsRequired());
				arrangeDocDept.setStandardGroupFlow(schRotationGroup.getStandardGroupFlow());
				arrangeDocDept.setStandardDeptId(rotationDept.getStandardDeptId());
				arrangeDocDept.setStandardDeptName(rotationDept.getStandardDeptName());
				GeneralMethod.setRecordInfo(arrangeDocDept, true);
				arrangeDoctorDeptMapper.insert(arrangeDocDept);
				arrangeDoctorDeptList.add(arrangeDocDept);
			}

			//记录医师选科留痕
			//医师已选科记录
			SchDoctorDeptExample example = new SchDoctorDeptExample();
			example.createCriteria()
			.andDoctorFlowEqualTo(doctor.getDoctorFlow())
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			
			List<SchDoctorDept> doctorDeptList = doctorDeptMapper.selectByExample(example);
			if(doctorDeptList!=null){
				for(SchDoctorDept schDoctorDept : doctorDeptList){
					SchArrangeDoctorDept arrangeDocDept = new SchArrangeDoctorDept();
					arrangeDocDept.setArrDocDeptFlow(PkUtil.getUUID());
					arrangeDocDept.setArrangeFlow(arrange.getArrangeFlow());
					arrangeDocDept.setDoctorFlow(doctor.getDoctorFlow());
					arrangeDocDept.setDoctorName(doctor.getDoctorName());
					arrangeDocDept.setDeptFlow(schDoctorDept.getDeptFlow()); 
					arrangeDocDept.setDeptName(schDoctorDept.getDeptName());
					
					SchRotationGroup schRotationGroup = schRotationGroupMap.get(schDoctorDept.getGroupFlow());
					arrangeDocDept.setGroupFlow(schDoctorDept.getGroupFlow());
					arrangeDocDept.setGroupName(schRotationGroup.getGroupName());
					arrangeDocDept.setSchStageId(schRotationGroup.getSchStageId());
					arrangeDocDept.setSchStageName(schRotationGroup.getSchStageName());
					
					arrangeDocDept.setSchDeptFlow(schDoctorDept.getSchDeptFlow());
					arrangeDocDept.setSchDeptName(schDoctorDept.getSchDeptName());
					arrangeDocDept.setSchMonth(schDoctorDept.getSchMonth());
					arrangeDocDept.setOrgFlow(currUser.getOrgFlow());
					arrangeDocDept.setOrgName(currUser.getOrgName());

					arrangeDocDept.setIsRequired(schDoctorDept.getIsRequired());
					arrangeDocDept.setStandardGroupFlow(schRotationGroup.getStandardGroupFlow());
					arrangeDocDept.setStandardDeptId(schDoctorDept.getStandardDeptId());
					arrangeDocDept.setStandardDeptName(schDoctorDept.getStandardDeptName());
					
					GeneralMethod.setRecordInfo(arrangeDocDept, true);
					arrangeDoctorDeptMapper.insert(arrangeDocDept);
					arrangeDoctorDeptList.add(arrangeDocDept);
				}
			}
		}
	}

	@Override
	public List<ResDoctor> searchUnSchDoctor(String orgFlow) {
		ResDoctorExample example = new ResDoctorExample();
        ResDoctorExample.Criteria creater = example.createCriteria();
        creater.andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_N);
		return doctorMapper.selectByExample(example); 
	}
	@Override
	public List<ResDoctor> searchCouldSchDoctor(String orgFlow) {
		ResDoctorExample example = new ResDoctorExample();
        example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_N).andSelDeptFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public SchArrange readArrange(String arrangeFlow) {
		return arrangeMapper.selectByPrimaryKey(arrangeFlow);
	}
	
	@Override
	public void modifyArrange(SchArrange arrange) {
		arrangeMapper.updateByPrimaryKeySelective(arrange);
	} 
	
	@Override
	public int saveArrange(SchArrange arrange){
		GeneralMethod.setRecordInfo(arrange, true);
		return arrangeMapper.insertSelective(arrange);
	}

	@Override
	public List<ResDoctor> searchFinishSchDoctor(String arrangeFlow) {
		SchArrangeDoctorExample example = new SchArrangeDoctorExample();
		example.createCriteria().andArrangeFlowEqualTo(arrangeFlow);
		List<SchArrangeDoctor> schArrangeDoctorList = arrangeDoctorMapper.selectByExample(example);
		List<ResDoctor> result = new ArrayList<ResDoctor>();
		for(SchArrangeDoctor arrangeDoctor : schArrangeDoctorList){
			String doctorFlow = arrangeDoctor.getDoctorFlow();
			ResDoctor resDoctor = doctorMapper.selectByPrimaryKey(doctorFlow);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resDoctor.getSchFlag())) {
				result.add(resDoctor);
			}
		}
		return result;
	}

	@Override
	public void abort(String arrangeFlow) {
		SchArrange update = new SchArrange();
		update.setArrangeFlow(arrangeFlow);
		update.setArrangeStatusId(SchArrangeStatusEnum.Aborting.getId());
		update.setArrangeStatusName(SchArrangeStatusEnum.Aborting.getName());
		arrangeMapper.updateByPrimaryKeySelective(update);
	}

	@Override
	public List<SchGradeFrom> searchGradeByDoctorFlow(String schDeptFlow,String userFlow,String processFlow,String doctorFlow,String schStartDate,String schEndDate,String resultFlow) {
		return resultExtMapper.searchGradeByDoctorFlow(schDeptFlow,userFlow,processFlow,doctorFlow,schStartDate,schEndDate,resultFlow);
	}

	@Override
	public List<SchGradeFrom> searchGradeByOrgFlow(String doctorName,String processFlow,String orgFlow, String schStartDate, String schEndDate) {
		return resultExtMapper.searchGradeByOrgFlow(doctorName,processFlow,orgFlow,schStartDate,schEndDate);
	}
}
