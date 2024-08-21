package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchExamArrangementExtMapper;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchExamCfgBizImpl implements ISchExamCfgBiz {
	@Autowired
	private  SchExamArrangementMapper schExamArrangementMapper;
	@Autowired
	private  SchExamDoctorArrangementMapper doctorArrangementMapper;
	@Autowired
	private  ResDoctorGraduationExamMapper graduationExamMapper;
	@Autowired
	private SchExamArrangementExtMapper schExamArrangementExtMapper;
	@Autowired
	private  SchExamStandardDeptMapper deptMapper;
	@Autowired
	private  SysDeptMapper sysDeptMapper;
	@Autowired
	private  SchDeptMapper schDeptMapper;
	@Override
	public List<SchExamArrangement> searchList(SchExamArrangement seam) {
		SchExamArrangementExample example=new SchExamArrangementExample();
		SchExamArrangementExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(seam.getArrangeFlow()))
		{
			criteria.andArrangeFlowEqualTo(seam.getArrangeFlow());
		}
		if(StringUtil.isNotBlank(seam.getOrgFlow()))
		{
			criteria.andOrgFlowEqualTo(seam.getOrgFlow());
		}
		if(StringUtil.isNotBlank(seam.getTrainingSpeId()))
		{
			criteria.andTrainingSpeIdEqualTo(seam.getTrainingSpeId());
		}
		if(StringUtil.isNotBlank(seam.getTrainingTypeId()))
		{
			criteria.andTrainingTypeIdEqualTo(seam.getTrainingTypeId());
		}
		if(StringUtil.isNotBlank(seam.getSessionNumber()))
		{
			criteria.andSessionNumberEqualTo(seam.getSessionNumber());
		}
		if(StringUtil.isNotBlank(seam.getAssessmentYear()))
		{
			criteria.andAssessmentYearEqualTo(seam.getAssessmentYear());
		}
		example.setOrderByClause("ASSESSMENT_YEAR,TRAINING_TYPE_ID");
		return schExamArrangementMapper.selectByExample(example);
	}

	@Override
	public int updateCfg(SchExamArrangement schExamArrangement) {
		if(StringUtil.isNotBlank(schExamArrangement.getArrangeFlow()))
		{
			GeneralMethod.setRecordInfo(schExamArrangement,false);
			return schExamArrangementMapper.updateByPrimaryKeySelective(schExamArrangement);
		}else{
			schExamArrangement.setArrangeFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(schExamArrangement,true);
			return schExamArrangementMapper.insertSelective(schExamArrangement);
		}
	}

	@Override
	public SchExamArrangement readByFlow(String arrangeFlow) {
		return schExamArrangementMapper.selectByPrimaryKey(arrangeFlow);
	}

	@Override
	public List<SchExamStandardDept> readStandardDeptsByFlow(String arrangeFlow) {
		if(StringUtil.isNotBlank(arrangeFlow))
		{
			SchExamStandardDeptExample example=new SchExamStandardDeptExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andArrangeFlowEqualTo(arrangeFlow);
			return deptMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public int updateArrangement(SchExamArrangement schExamArrangement, String[] standardDeptId,String isProduct) {
		String uuid = PkUtil.getUUID();
		int result = 0;
		if(StringUtil.isBlank(schExamArrangement.getIsOpen())){
			schExamArrangement.setIsOpen(GlobalConstant.RECORD_STATUS_N);
		}
		if(StringUtil.isNotBlank(schExamArrangement.getArrangeFlow()))
		{
			GeneralMethod.setRecordInfo(schExamArrangement,false);
			result = schExamArrangementMapper.updateByPrimaryKeySelective(schExamArrangement);
		}
		if(StringUtil.isBlank(schExamArrangement.getArrangeFlow())){
			schExamArrangement.setArrangeFlow(uuid);
			GeneralMethod.setRecordInfo(schExamArrangement,true);
			result = schExamArrangementMapper.insertSelective(schExamArrangement);
		}
		//设置考试范围
		if(standardDeptId != null && standardDeptId.length > 0 && "2".equals(schExamArrangement.getExampaperType())){
			String arrangeFlow =  schExamArrangement.getArrangeFlow();
			//根据考核配置Flow查询考核范围的科室并将record_status设为N；
			SchExamStandardDeptExample example = new SchExamStandardDeptExample();
			example.createCriteria().andArrangeFlowEqualTo(arrangeFlow);
			List<SchExamStandardDept> deptsForStatus = deptMapper.selectByExample(example);
			if(deptsForStatus != null && deptsForStatus.size() > 0){
				for(SchExamStandardDept tempDept : deptsForStatus){
					tempDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
					GeneralMethod.setRecordInfo(tempDept,false);
					deptMapper.updateByPrimaryKey(tempDept);
				}
			}
			//对考试范围循环
			for(String tempDeptId : standardDeptId){
				SchExamStandardDept schExamStandardDept = null;
				example.clear();
				//查询数据库中已有的考试范围科室Id（不区分状态）若有则改状态为Y
				example.createCriteria().andArrangeFlowEqualTo(schExamArrangement.getArrangeFlow()).andStandardDeptIdEqualTo(tempDeptId);
				List<SchExamStandardDept> depts = deptMapper.selectByExample(example);
				if(depts != null && depts.size() > 0){
					schExamStandardDept = depts.get(0);
					schExamStandardDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					GeneralMethod.setRecordInfo(schExamStandardDept,false);
					deptMapper.updateByPrimaryKey(schExamStandardDept);
				}else {
					schExamStandardDept = new SchExamStandardDept();
					schExamStandardDept.setRecordFlow(PkUtil.getUUID());
					schExamStandardDept.setArrangeFlow(arrangeFlow);
					schExamStandardDept.setOrgFlow(schExamArrangement.getOrgFlow());
					schExamStandardDept.setOrgName(schExamArrangement.getOrgName());
					if(GlobalConstant.FLAG_Y.equals(isProduct))
					{
						SchDept dept=schDeptMapper.selectByPrimaryKey(tempDeptId);
						if(dept!=null) {
							schExamStandardDept.setStandardDeptId(tempDeptId);
							schExamStandardDept.setStandardDeptName(dept.getSchDeptName());
							GeneralMethod.setRecordInfo(schExamStandardDept, true);
							deptMapper.insert(schExamStandardDept);
						}
					}else {
						SysDept dept=sysDeptMapper.selectByPrimaryKey(tempDeptId);
						if(dept!=null) {
							schExamStandardDept.setStandardDeptId(tempDeptId);
							schExamStandardDept.setStandardDeptName(dept.getDeptName());
							GeneralMethod.setRecordInfo(schExamStandardDept, true);
							deptMapper.insert(schExamStandardDept);
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public int findDocExamCount(String userFlow, String arrangeFlow) {
		return schExamArrangementExtMapper.findDocExamCount(userFlow,arrangeFlow);
	}

	@Override
	public int save(SchExamDoctorArrangement result) {
		GeneralMethod.setRecordInfo(result,true);
		return doctorArrangementMapper.insertSelective(result);
	}
	@Override
	public int update(SchExamDoctorArrangement result) {
		GeneralMethod.setRecordInfo(result,false);
		return doctorArrangementMapper.updateByPrimaryKeySelective(result);
	}

	@Override
	public Map<String, Object> getSuiJiConfig(SchExamArrangement ment,String userFlow) {
		return schExamArrangementExtMapper.getSuiJiConfig(ment, userFlow);
	}

	@Override
	public Map<String, Object> getGuDingConfig(SchExamArrangement ment) {
		return schExamArrangementExtMapper.getGuDingConfig(ment);
	}

	@Override
	public List<Map<String,String>> searchExamLogByItems(Map<String, String> paramMap) {
		return schExamArrangementExtMapper.searchExamLogByItems(paramMap);
	}

	@Override
	public void deleteSchExamStandardDeptByDeptId(String deptFlow) {
		schExamArrangementExtMapper.deleteSchExamStandardDeptByDeptId(deptFlow);
	}

	@Override
	public int checkHaveExam(String arrangeFlow) {
		return schExamArrangementExtMapper.checkHaveExam(arrangeFlow);
	}

	@Override
	public List<SchArrangeResult> getSuiJiConfigs(SchExamArrangement ment, String userFlow) {

		return schExamArrangementExtMapper.getSuiJiConfigs(ment, userFlow);
	}
	@Override
	public List<SchArrangeResult> getGuDingConfigs(SchExamArrangement ment,String userFlow) {

		return schExamArrangementExtMapper.getGuDingConfigs(ment,userFlow);
	}

	@Override
	public int checkExists(SchExamArrangement ment) {
		return schExamArrangementExtMapper.checkExists(ment);
	}

	@Override
	public int checkExists(SchExamArrangement ment, List<String> speIds, List<String> sessinNumbers) {
		return schExamArrangementExtMapper.checkExistsByIds(ment,speIds,sessinNumbers);
	}

	@Override
	public int updateArrangements(SchExamArrangement schExamArrangement, String[] standardDeptId, List<String> speIds, List<String> sessinNumbers) {
		int c=0;
		DictTypeEnum e=null;
		if(TrainCategoryEnum.AssiGeneral.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
			e=DictTypeEnum.AssiGeneral;

			schExamArrangement.setTrainingTypeName(TrainCategoryEnum.AssiGeneral.getName());
		}
		if(TrainCategoryEnum.DoctorTrainingSpe.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
			e=DictTypeEnum.DoctorTrainingSpe;
			schExamArrangement.setTrainingTypeName(TrainCategoryEnum.DoctorTrainingSpe.getName());
		}
		if(TrainCategoryEnum.WMFirst.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
			e=DictTypeEnum.WMFirst;
			schExamArrangement.setTrainingTypeName(TrainCategoryEnum.WMFirst.getName());
		}
		if(TrainCategoryEnum.WMSecond.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
			e=DictTypeEnum.WMSecond;
			schExamArrangement.setTrainingTypeName(TrainCategoryEnum.WMSecond.getName());
		}
		for(String speId:speIds)
		{
			for(String sessionNumber:sessinNumbers)
			{
				schExamArrangement.setArrangeFlow("");
				schExamArrangement.setSessionNumber(sessionNumber);
				schExamArrangement.setTrainingSpeId(speId);
				schExamArrangement.setTrainingSpeName(DictTypeEnum.getDictName(e,speId));
				c+=updateArrangement(schExamArrangement,standardDeptId,"N");
			}
		}
		return c;
	}

	@Override
	public SchExamDoctorArrangement readExamResult(String processFlow) {
		return doctorArrangementMapper.selectByPrimaryKey(processFlow);
	}

	@Override
	public int saveGraduationExam(ResDoctorGraduationExam result) {
		GeneralMethod.setRecordInfo(result,true);
		return graduationExamMapper.insertSelective(result);
	}
}
