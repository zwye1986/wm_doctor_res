package com.pinde.sci.biz.sch.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.DateUtil;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchRotationDeptExtMapper;
import com.pinde.sci.dao.sch.SchRotationDeptReqExtMapper;
import com.pinde.sci.dao.sch.SchRotationExtendMapper;
import com.pinde.sci.enums.res.RegistryTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sch.SchSelTypeEnum;
import com.pinde.sci.enums.sch.SchStageEnum;
import com.pinde.sci.form.sch.SchRotationDeptForm;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchRotationDeptBizImpl implements ISchRotationDeptBiz {
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private SchRotationGroupMapper rotationGroupMapper;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private SchRotationDeptReqMapper deptReqMapper;
	@Autowired
	private SchRotationDeptReqExtMapper deptReqExtMapper;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private SchRotationDeptExtMapper rotationDeptExtMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ResRecMapper resRecMapper;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private SchRotationExtendMapper rotationExtendMapper;

	//	@Override
//	public List<SchRotationDept> searchSchRotationDept() {
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		example.setOrderByClause("ORDINAL");
//		return rotationDeptMapper.selectByExample(example);
//	}
//	@Override
//	public List<SchRotationDept> searchSchRotationDeptByCurrFlow(String currRelRecordFlow) {
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecordFlowEqualTo(currRelRecordFlow);
//		example.setOrderByClause("ORDINAL");
//		return rotationDeptMapper.selectByExample(example);
//	}
	@Override
	public List<SchRotationDept> searchAllSchRotationDept() {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowIsNull();
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	@Override
	public List<SchRotationDept> searchSchRotationDept(String rotationFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowIsNull();
		if(StringUtil.isNotEmpty(rotationFlow)){
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDept> searchOrgSchRotationDept(String rotationFlow,String orgFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
		.andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}

	@Override
	public List<SchRotationDept> searchSchRotationDeptByItems(Map<String,String> paramMap) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(paramMap.get("rotationFlow"))){
			criteria.andRotationFlowEqualTo(paramMap.get("rotationFlow"));
		}
		if(StringUtil.isNotBlank(paramMap.get("orgFlow"))){
			criteria.andOrgFlowEqualTo(paramMap.get("orgFlow"));
		}
		if(StringUtil.isNotBlank(paramMap.get("sessionNumber"))){
			criteria.andSessionNumberEqualTo(paramMap.get("sessionNumber"));
		}
		if("schDeptFlowIsNull".equals(paramMap.get("schDeptFlowIsNull"))){
			criteria.andSchDeptFlowIsNull();
		}
		if("schDeptFlowIsNotNull".equals(paramMap.get("schDeptFlowIsNotNull"))){
			criteria.andSchDeptFlowIsNotNull();
		}
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDept> searchSchRotationDeptGroup(String rotationFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsRequiredEqualTo(GlobalConstant.FLAG_N)
		.andRotationFlowEqualTo(rotationFlow).andOrgFlowIsNull();
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDept> searchOrgSchRotationDeptGroup(String rotationFlow,String orgFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsRequiredEqualTo(GlobalConstant.FLAG_N)
		.andRotationFlowEqualTo(rotationFlow).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDept> searchRotationDeptByFlows(List<String> recordFlows) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andRecordFlowIn(recordFlows);
		return rotationDeptMapper.selectByExample(example);
	}

//	@Override
//	public List<SchRotationDept> searchSchRotationDeptMust(String rotationFlow) {
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsRequiredEqualTo(GlobalConstant.FLAG_Y)
//			.andRotationFlowEqualTo(rotationFlow).andOrgFlowIsNull();
//		example.setOrderByClause("ORDINAL");
//		return rotationDeptMapper.selectByExample(example);
//	}

//	@Override
//	public List<SchRotationDept> searchSchRotationDeptMustWithBLOBs(String rotationFlow) {
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsRequiredEqualTo(GlobalConstant.FLAG_Y)
//		.andRotationFlowEqualTo(rotationFlow).andOrgFlowIsNull();
//		example.setOrderByClause("ORDINAL");
//		return rotationDeptMapper.selectByExampleWithBLOBs(example);
//	}
	
	@Override
	public List<SchRotationDept> searchOrgSchRotationDeptMust(String rotationFlow,String orgFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsRequiredEqualTo(GlobalConstant.FLAG_Y)
		.andRotationFlowEqualTo(rotationFlow).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDept> searchRotationDeptByRecordFlows(List<String> recordFlows) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecordFlowIn(recordFlows);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public SchRotationDept readSchRotationDept(String recordFlow) {
		return rotationDeptMapper.selectByPrimaryKey(recordFlow);
	}
	
	@Override
	public List<SchRotationDept> searchDeptByStandard(String rotationFlow,String groupFlow,String standardDeptId,String orgFlow){
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(standardDeptId)){
			criteria.andStandardDeptIdEqualTo(standardDeptId);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if(StringUtil.isNotBlank(rotationFlow)){
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		if(StringUtil.isNotBlank(groupFlow)){
			criteria.andGroupFlowEqualTo(groupFlow);
		}else{
			criteria.andIsRequiredEqualTo(GlobalConstant.FLAG_Y);
		}
		return rotationDeptMapper.selectByExample(example);
	}
	@Override
	public List<SchRotationDept> searchDeptByMap(Map<String,String> parameMap){
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(parameMap.get("rotationFlow"))){
			criteria.andRotationFlowEqualTo(parameMap.get("rotationFlow"));
		}
		if(StringUtil.isNotBlank(parameMap.get("groupFlow"))){
			criteria.andGroupFlowEqualTo(parameMap.get("groupFlow"));
		}
		if(StringUtil.isNotBlank(parameMap.get("orgFlow"))){
			criteria.andOrgFlowEqualTo(parameMap.get("orgFlow"));
		}
		if(StringUtil.isNotBlank(parameMap.get("sessionNumber"))){
			criteria.andSessionNumberEqualTo(parameMap.get("sessionNumber"));
		}
		return rotationDeptMapper.selectByExample(example);
	}

//	@Override
//	public SchRotationDept readSchRotationDept(String schDeptFlow,String rotationFlow){
//		SchRotationDept rotationDept = null;
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowEqualTo(schDeptFlow).andRotationFlowEqualTo(rotationFlow);
//		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
//		if(rotationDeptList!=null && rotationDeptList.size()>0){
//			rotationDept = rotationDeptList.get(0);
//		}
//		return rotationDept;
//	}

	@Override
	public int saveSchRotationDept(SchRotationDept rotationDept) {
		if(rotationDept != null){
			if(StringUtil.isNotBlank(rotationDept.getRecordFlow())){
				GeneralMethod.setRecordInfo(rotationDept, false);
				return rotationDeptMapper.updateByPrimaryKeySelective(rotationDept);
			}else{
				rotationDept.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(rotationDept, true);
				return rotationDeptMapper.insertSelective(rotationDept);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveRotationDeptOrd(String[] recordFlows){
		if(recordFlows != null && recordFlows.length>0){
			int i = 0 ;
			SchRotationDept rotationDept = new SchRotationDept();
			for(String recordFlow : recordFlows){
				if(StringUtil.isNotBlank(recordFlow)){
					rotationDept.setRecordFlow(recordFlow);
					rotationDept.setOrdinal(i++);
					saveSchRotationDept(rotationDept);
				}
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveSchRotationDeptForm(SchRotationDeptForm rotationDeptForm,String rotationFlow){
		//如果方案已发布则重置所用培训机构关联信息
		//delRelRotationDept(rotationFlow);
		
		return saveSchRotationDeptForm(rotationDeptForm);
	}
	
	private void delRelRotationDept(String rotationFlow){
		if(StringUtil.isNotBlank(rotationFlow)){
			SchRotation rotation = rotationBiz.readSchRotation(rotationFlow);
			if(GlobalConstant.FLAG_Y.equals(rotation.getPublishFlag())){
				SchRotationDept rotationDept = new SchRotationDept();
				rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				SchRotationDeptExample example = new SchRotationDeptExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIsNotNull().andRotationFlowEqualTo(rotationFlow);
				rotationDeptMapper.updateByExampleSelective(rotationDept,example);
				
				SchRotationGroup group = new SchRotationGroup();
				group.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				SchRotationGroupExample gExample = new SchRotationGroupExample();
				gExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIsNotNull().andRotationFlowEqualTo(rotationFlow);
				rotationGroupMapper.updateByExampleSelective(group,gExample);
			}
		}
	}
	
	@Override
	public int saveSchRotationDeptForm(SchRotationDeptForm rotationDeptForm){
		//String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		//String orgName = GlobalContext.getCurrentUser().getOrgName();

		//获取页面选择的组合科室
		List<SchRotationDept> rotationDeptList = rotationDeptForm.getRotationDeptList();

		//从页面选择的组合科室中，获取已存在于数据库的记录（根据主键判断，如果为空就表示不存在于数据库中）
		Map<String,SchRotationDept> rotationDeptMap = new HashMap<String, SchRotationDept>();
		for(SchRotationDept rotationDept : rotationDeptList){
			String key = rotationDept.getRecordFlow();
			if(StringUtil.isNotBlank(key)){
				rotationDeptMap.put(key,rotationDept);
			}
		}

		//获取当前页面编辑或新增的这个组合
		SchRotationGroup rotationGroup = rotationDeptForm.getRotationGroup();
		if(StringUtil.isNotBlank(rotationGroup.getSchStageId())){
			rotationGroup.setSchStageName(SchStageEnum.getNameById(rotationGroup.getSchStageId()));
		}

		//获取当前这个方案
		String rotationFlow = rotationGroup.getRotationFlow();

		//获取已同步该方案的机构
		List<String> isUpdateOrgFlows = rotationDeptExtMapper.isUpdateOrg(rotationFlow);

		//获取当前组合下的所有已存在规则
		List<SchRotationDept> rotationDeptDbList = rotationGroupBiz.readSchRotationDept(rotationGroup.getGroupFlow());
		if(rotationDeptDbList != null && rotationDeptDbList.size()>0){
			for(SchRotationDept rotationDeptDb : rotationDeptDbList){
				String key = rotationDeptDb.getRecordFlow();
				SchRotationDept srd = rotationDeptMap.get(key);//页面中已经存在于
				//判断已保存的规则是否被取消,如果取消则禁用该记录
				if(srd==null){
					rotationDeptDb.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
					saveSchRotationDept(rotationDeptDb);
					//同步至各机构
					delOrgGroupOrRotationDept(rotationDeptDb.getRecordFlow(),null);
				}else{
					String pageSrd = srd.getSchMonth();
					String dbSrd = rotationDeptDb.getSchMonth();
					pageSrd = StringUtil.defaultString(pageSrd);
					if(!pageSrd.equals(dbSrd)){
						String standardRecFlow = rotationDeptDb.getRecordFlow();
						for(String orgFlow : isUpdateOrgFlows){
							List<SchRotationDept> srds = searchRotationDeptByStanard(standardRecFlow,orgFlow);
							if(srds!=null){
								String schMonth = srds.size()==1?pageSrd:"0";
								for(SchRotationDept rsrd : srds){
									rsrd.setSchMonth(schMonth);
									saveSchRotationDept(rsrd);
								}
							}
						}
					}
				}
			}
		}
		
		int result = GlobalConstant.ZERO_LINE;
		if(StringUtil.isNotBlank(rotationGroup.getGroupFlow())){
			//先更新标准组合
			result = rotationGroupBiz.update(rotationGroup);

			//将标准组合属性同步至各机构
			SchRotationGroup tempGroup = new SchRotationGroup();
			tempGroup.setGroupName(rotationGroup.getGroupName());
			tempGroup.setSchMonth(rotationGroup.getSchMonth());
			tempGroup.setIsRequired(rotationGroup.getIsRequired());
			tempGroup.setSchStageId(rotationGroup.getSchStageId());
			tempGroup.setSchStageName(rotationGroup.getSchStageName());
			tempGroup.setOrdinal(rotationGroup.getOrdinal());
			if(SchSelTypeEnum.Free.getId().equals(rotationGroup.getSelTypeId())){
				tempGroup.setDeptNum(rotationGroup.getDeptNum());
			}

			//将标准组合为该组合的机构组合更新
			SchRotationGroupExample groupExample = new SchRotationGroupExample();
			groupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andStandardGroupFlowEqualTo(rotationGroup.getGroupFlow());
			rotationGroupMapper.updateByExampleSelective(tempGroup,groupExample);

			//将所有同步的规则的必轮状态更新
			rotationDeptExtMapper.setRotationDeptRequired(rotationGroup.getIsRequired(),rotationGroup.getGroupFlow());
		}else{
			//rotationGroup.setGroupFlow(PkUtil.getUUID());
			//rotationGroup.setOrgFlow(orgFlow);
			//rotationGroup.setOrgName(orgName);

			//先新增一个组合
			result = rotationGroupBiz.save(rotationGroup);

			//判断是否存在已同步的机构
			if(isUpdateOrgFlows!=null && isUpdateOrgFlows.size()>0){
				SchRotationGroup group = new SchRotationGroup();
				group.setRotationFlow(rotationFlow);
				group.setGroupName(rotationGroup.getGroupName());
				group.setDeptNum(rotationGroup.getDeptNum());
				group.setSchMonth(rotationGroup.getSchMonth());
				group.setGroupNote(rotationGroup.getGroupNote());
				group.setSelTypeId(rotationGroup.getSelTypeId());
				group.setSelTypeName(rotationGroup.getSelTypeName());
				group.setMaxDeptNum(rotationGroup.getMaxDeptNum());
				group.setOrdinal(rotationGroup.getOrdinal());
				group.setIsRequired(rotationGroup.getIsRequired());
				group.setSchStageId(rotationGroup.getSchStageId());
				group.setSchStageName(rotationGroup.getSchStageName());
				group.setStandardGroupFlow(rotationGroup.getGroupFlow());
				
				for(String orgFlow : isUpdateOrgFlows){
					//获取一个同步的机构
					SysOrg org = orgBiz.readSysOrg(orgFlow);
					
					group.setOrgFlow(orgFlow);
					group.setOrgName(org.getOrgName());
					rotationGroupBiz.save(group);
				}
			}
		}
		if(result == GlobalConstant.ZERO_LINE){
			return  GlobalConstant.ZERO_LINE;
		}

		//编辑当前配置的规则
		for(SchRotationDept rotationDept : rotationDeptList){
			//rotationDept.setOrgFlow(orgFlow);
			//rotationDept.setOrgName(orgName);
			
			rotationDept.setGroupFlow(rotationGroup.getGroupFlow());
			rotationDept.setRecordStatus(rotationGroup.getRecordStatus());

			//轮转科室不为空则获取这个科室
			if(StringUtil.isNotBlank(rotationDept.getSchDeptFlow())){
				SchDept schDept = schDeptBiz.readSchDept(rotationDept.getSchDeptFlow());
				if(schDept != null){
					rotationDept.setDeptFlow(schDept.getDeptFlow());
					rotationDept.setDeptName(schDept.getDeptName());
				}else{
					return GlobalConstant.ZERO_LINE; 
				}
			}

			//轮转时间为空则默认为0
			rotationDept.setSchMonth(StringUtil.defaultIfEmpty(rotationDept.getSchMonth(),"0"));

			//先保存该规则的操作形式是新增还是更新
			boolean isBlank = !StringUtil.isNotBlank(rotationDept.getRecordFlow());

			//操作该条规则
			saveSchRotationDept(rotationDept);

			//使用状态判断同步形式
			if(isBlank){
				if(isUpdateOrgFlows!=null && isUpdateOrgFlows.size()>0){
					for(String orgFlow : isUpdateOrgFlows){
						//读取当前这个机构
						SysOrg org = orgBiz.readSysOrg(orgFlow);

						//读取当前标准科室与该机构的轮转科室关系
						List<SchDeptRel> deptRelList = deptRelBiz.searchRelByStandard(orgFlow,rotationDept.getStandardDeptId());

						//通过标准组合读取该机构的组合记录
						SchRotationGroup group = rotationGroupBiz.readGroupByStandard(orgFlow,rotationDept.getGroupFlow());

						//为机构同步数据
						SchRotationDept rotationDeptOrg = new SchRotationDept();
						rotationDeptOrg.setOrgFlow(org.getOrgFlow());
						rotationDeptOrg.setOrgName(org.getOrgName());
						rotationDeptOrg.setSchMonth(rotationDept.getSchMonth());
						rotationDeptOrg.setIsRequired(rotationDept.getIsRequired());
						rotationDeptOrg.setDeptNote(rotationDept.getDeptNote());
						rotationDeptOrg.setOrdinal(rotationDept.getOrdinal());
						rotationDeptOrg.setStandardDeptId(rotationDept.getStandardDeptId());
						rotationDeptOrg.setStandardDeptName(rotationDept.getStandardDeptName());
						rotationDeptOrg.setGroupFlow(group.getGroupFlow());
						rotationDeptOrg.setRotationFlow(rotationFlow);
						
						if(deptRelList!=null && deptRelList.size()>0){
							if(group!=null && GlobalConstant.FLAG_N.equals(group.getIsRequired())){
								if(SchSelTypeEnum.Free.getId().equals(group.getSelTypeId())){
									Integer standardMax = rotationGroup.getMaxDeptNum();
									standardMax+=(deptRelList.size()-1);
									group.setMaxDeptNum(standardMax);
								}else{
									Integer standardMax = rotationGroup.getDeptNum();
									standardMax+=(deptRelList.size()-1);
									group.setDeptNum(standardMax);
								}
								rotationGroupBiz.update(group);
							}
							
							boolean first = true;
							for(SchDeptRel deptRel : deptRelList){
								rotationDeptOrg.setRecordFlow(null);
								rotationDeptOrg.setSchDeptFlow(deptRel.getSchDeptFlow());
								rotationDeptOrg.setSchDeptName(deptRel.getSchDeptName());
								SchDept dept = schDeptBiz.readSchDept(deptRel.getSchDeptFlow());
								if(dept!=null){
									rotationDeptOrg.setDeptFlow(dept.getDeptFlow());
									rotationDeptOrg.setDeptName(dept.getDeptName());
								}
								
								if(!first){
									rotationDeptOrg.setSchMonth("0");
								}
								
								saveSchRotationDept(rotationDeptOrg);
								
								first = false;
							}
						}else{
							rotationDeptOrg.setRecordFlow(null);
							saveSchRotationDept(rotationDeptOrg);
						}
					}
				}
			}
		}
		return GlobalConstant.ONE_LINE;
	}

//	@Override
//	public int delSchRotationDeptForm(SchRotationDeptForm rotationDeptForm){
//		SchRotationGroup group = rotationDeptForm.getRotationGroup();
//		rotationGroupBiz.update(group);
//
//		List<SchRotationDept> rotationDeptList = rotationDeptForm.getRotationDeptList();
//		if(rotationDeptList!=null && rotationDeptList.size()>0){
//			for(SchRotationDept rotationDept : rotationDeptList){
//				saveSchRotationDept(rotationDept);
//			}
//		}
//		List<String> recordFlows = new ArrayList<String>();
//		for(SchRotationDept rotationDept : rotationDeptList){
//			if(StringUtil.isNotBlank(rotationDept.getRecordFlow())){
//				rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//				recordFlows.add(rotationDept.getRecordFlow());
//			}
//		}
//		return GlobalConstant.ONE_LINE;
//	}
	
	@Override
	public int saveSelDepts(List<String> recordFlows,Map<String,String> schMonthMap,ResDoctor doctor){
		if(recordFlows!=null && recordFlows.size()>0){
			List<SchRotationDept> rotationDeptList = searchRotationDeptByFlows(recordFlows);
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				
				SchDoctorDept doctorDept = new SchDoctorDept();
				doctorDept.setDoctorFlow(doctor.getDoctorFlow());
				doctorDept.setDoctorName(doctor.getDoctorName());
				doctorDept.setOrgFlow(doctor.getOrgFlow());
				doctorDept.setOrgName(doctor.getOrgName());
				doctorDept.setRotationFlow(doctor.getRotationFlow());
				doctorDept.setRotationName(doctor.getRotationName());
				doctorDept.setSessionNumber(doctor.getSessionNumber());
				
				for(SchRotationDept rotationDept : rotationDeptList){
					doctorDept.setRecordFlow(null);
					doctorDept.setGroupFlow(rotationDept.getGroupFlow());
					doctorDept.setSchMonth(schMonthMap.get(rotationDept.getRecordFlow()));
					doctorDept.setSchDeptFlow(rotationDept.getSchDeptFlow());
					doctorDept.setSchDeptName(rotationDept.getSchDeptName());
					doctorDept.setDeptFlow(rotationDept.getDeptFlow());
					doctorDept.setDeptName(rotationDept.getDeptName());
					doctorDept.setStandardDeptId(rotationDept.getStandardDeptId());
					doctorDept.setStandardDeptName(rotationDept.getStandardDeptName());
					doctorDept.setIsRequired(rotationDept.getIsRequired());
					doctorDept.setOrdinal(rotationDept.getOrdinal());
					doctorDeptBiz.editDoctorDept(doctorDept);
				}
				
				doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
				doctorBiz.editDoctor(doctor);
				return GlobalConstant.ONE_LINE;
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveSelDeptsAndResult(List<String> recordFlows,Map<String,String> schMonthMap,ResDoctor doctor){
		saveSelDepts(recordFlows,schMonthMap,doctor);
		//doctor.setSchFlag(GlobalConstant.FLAG_Y);
		return schArrangeResultBiz.saveResultByDoctor(doctor);
	}
	
	@Override
	public List<SchRotationDept> searchDeptByRotations(List<String> rotationFlows){
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andRotationFlowIn(rotationFlows)
		.andOrgFlowIsNull();
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}

//	@Override
//	public List<SchRotationDept> searchSelDeptByRotations(List<String> rotationFlows){
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowIn(rotationFlows)
//		.andIsRequiredEqualTo(GlobalConstant.FLAG_N).andOrgFlowIsNull();
//		return rotationDeptMapper.selectByExample(example);
//	}
	
	@Override
	public List<SchRotationDept> searchOrgSelDeptByRotations(List<String> rotationFlows,String orgFlow){
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowIn(rotationFlows)
		.andIsRequiredEqualTo(GlobalConstant.FLAG_N).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}
	
	@Override
	public int saveRotationDeptList(List<SchRotationDept> rotationDeptList,SchRotationGroup group){
		for(SchRotationDept rotationDept : rotationDeptList){
			saveSchRotationDept(rotationDept);
		}
		if(group!=null){
			rotationGroupBiz.saveSchRotationGroup(group);
		}
		return GlobalConstant.ONE_LINE;
	}
	
	@Override
	public SchRotationDept readStandardRotationDeptByLocal(String rotationFlow,String groupFlow,String standardDeptId){
		SchRotationDeptExample example = new SchRotationDeptExample();
		com.pinde.sci.model.mo.SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIsNull();
		if(StringUtil.isNotBlank(groupFlow)){
			SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(groupFlow);
			if(StringUtil.isNotBlank(group.getStandardGroupFlow())){
				groupFlow = group.getStandardGroupFlow();
			}
			criteria.andGroupFlowEqualTo(groupFlow).andStandardDeptIdEqualTo(standardDeptId);
		}else{
			criteria.andIsRequiredEqualTo(GlobalConstant.FLAG_Y).andRotationFlowEqualTo(rotationFlow).andStandardDeptIdEqualTo(standardDeptId);
		}
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(example);
		SchRotationDept rotationDept = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			rotationDept = rotationDeptList.get(0);
		}
		return rotationDept;
	}

//	@Override
//	public List<SchRotationDept> searchRotationDeptByGroupFlow(String groupFlow){
//		SchRotationDeptExample example = new SchRotationDeptExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andGroupFlowEqualTo(groupFlow);
//		return rotationDeptMapper.selectByExample(example);
//	}
	
	@Override
	public List<SchRotationDept> searchRotationDeptByGroupFlowWithBLOBs(String groupFlow){
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(groupFlow)){
			criteria.andGroupFlowEqualTo(groupFlow);
		}
		return rotationDeptMapper.selectByExampleWithBLOBs(example);
	}
	
	/**
	 * 更新指定区域规则
	 */
	@Override
	public int updateAreaRule(String rotationFlow,String standardDeptId,String groupFlow,SysOrg org){
		String oldRotationFlow = rotationFlow;
		String oldGroupFlow = groupFlow;
		//删除原区域数据
		SchRotationDept rotationDept = new SchRotationDept();
		rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		SchRotationDeptExample example = new SchRotationDeptExample();
		com.pinde.sci.model.mo.SchRotationDeptExample.Criteria criteria = example.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowEqualTo(org.getOrgFlow());
		if(StringUtil.isNotBlank(groupFlow)){
			criteria.andGroupFlowEqualTo(groupFlow).andStandardDeptIdEqualTo(standardDeptId);
		}else{
			criteria.andIsRequiredEqualTo(GlobalConstant.FLAG_Y).andRotationFlowEqualTo(rotationFlow).andStandardDeptIdEqualTo(standardDeptId);
		}
		int oldDeptNum = rotationDeptMapper.updateByExampleSelective(rotationDept,example);

		//找到原区域标准数据
		example.clear();
		criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIsNull();
		SchRotationGroup group = null;
		if(StringUtil.isNotBlank(groupFlow)){
			group = rotationGroupBiz.readSchRotationGroup(groupFlow);
			if(StringUtil.isNotBlank(group.getStandardGroupFlow())){
				groupFlow = group.getStandardGroupFlow();
			}
			criteria.andGroupFlowEqualTo(groupFlow).andStandardDeptIdEqualTo(standardDeptId);
		}else{
			criteria.andIsRequiredEqualTo(GlobalConstant.FLAG_Y).andRotationFlowEqualTo(rotationFlow).andStandardDeptIdEqualTo(standardDeptId);
		}
		List<SchRotationDept> standardRotationDept = rotationDeptMapper.selectByExample(example);
		if(standardRotationDept!=null && standardRotationDept.size()>0){
			rotationDept = standardRotationDept.get(0);
			if(rotationDept!=null && org!=null){
				List<SchDeptRel> deptRelList = deptRelBiz.searchRelByStandard(org.getOrgFlow(),standardDeptId);
				if(deptRelList!=null && deptRelList.size()>0){
					if(group!=null && GlobalConstant.FLAG_N.equals(group.getIsRequired())){
						int newDeptNum = deptRelList.size();
						if(SchSelTypeEnum.Free.getId().equals(group.getSelTypeId())){
							int currNum = group.getMaxDeptNum();
							group.setMaxDeptNum(currNum+(newDeptNum-oldDeptNum));
						}else{
							int currNum = group.getDeptNum();
							group.setDeptNum(currNum+(newDeptNum-oldDeptNum));
						}
						rotationGroupBiz.update(group);
					}
					
					rotationDept.setOrgFlow(org.getOrgFlow());
					rotationDept.setOrgName(org.getOrgName());
					rotationDept.setGroupFlow(oldGroupFlow);
					rotationDept.setRotationFlow(oldRotationFlow);
					
					boolean first = true;
					for(SchDeptRel deptRel : deptRelList){
						rotationDept.setRecordFlow(null);
						rotationDept.setSchDeptFlow(deptRel.getSchDeptFlow());
						rotationDept.setSchDeptName(deptRel.getSchDeptName());
						SchDept dept = schDeptBiz.readSchDept(deptRel.getSchDeptFlow());
						if(dept!=null){
							rotationDept.setDeptFlow(dept.getDeptFlow());
							rotationDept.setDeptName(dept.getDeptName());
						}
						
						if(!first){
							rotationDept.setSchMonth("0");
						}
						
						saveSchRotationDept(rotationDept);
						
						first = false;
					}
				}
			}
		}
		
		return GlobalConstant.ONE_LINE;
	}

	/*******************************************************
	 * 要求
	 *****************************************************/

//	@Override
//	public List<SchRotationDeptReq> searchDeptReq(String rotationFlow,String schDeptFlow,String recTypeId){
//		if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(schDeptFlow) && StringUtil.isNotBlank(recTypeId)){
//			SchRotationDeptReqExample example = new SchRotationDeptReqExample();
//			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
//			.andRecTypeIdEqualTo(recTypeId)
//			.andStandardDeptIdEqualTo(schDeptFlow);
//			return deptReqMapper.selectByExample(example);
//		}
//		return null;
//	}
	
	@Override
	public List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow,String recTypeId){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andRecTypeIdEqualTo(recTypeId)
		.andRelRecordFlowEqualTo(relRecordFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		return deptReqMapper.selectByExample(example);
	}
	
	@Override
	public int defaultOtherItem(String relRecordFlow,String recTypeId){
		if(StringUtil.isNotBlank(relRecordFlow) && StringUtil.isNotBlank(recTypeId)){
			RegistryTypeEnum regType = null;
			for(RegistryTypeEnum rte : RegistryTypeEnum.values()){
				if(rte.getId().equals(recTypeId)){
					regType = rte;
					break;
				}
			}
			if(regType!=null && regType.getHaveItem().equals(GlobalConstant.FLAG_Y)){
				List<SchRotationDeptReq> reqList = searchDeptReqByRel(relRecordFlow,recTypeId,GlobalConstant.RES_REQ_OTHER_ITEM_ID);
				if(reqList==null || reqList.size()<=0){
					SchRotationDept rotationDept = readSchRotationDept(relRecordFlow);
					if(rotationDept!=null){
						SchRotationDeptReq req = new SchRotationDeptReq();
						req.setRotationFlow(rotationDept.getRotationFlow());
						req.setStandardDeptId(rotationDept.getStandardDeptId());
						req.setStandardDeptName(rotationDept.getStandardDeptName());
						req.setRelRecordFlow(relRecordFlow);
						req.setRecTypeId(regType.getId());
						req.setRecTypeName(regType.getName());
						req.setItemId(GlobalConstant.RES_REQ_OTHER_ITEM_ID);
						req.setItemName(GlobalConstant.RES_REQ_OTHER_ITEM_NAME);
						req.setOrgFlow(rotationDept.getOrgFlow());
						req.setOrgName(rotationDept.getOrgName());
						req.setReqNum(BigDecimal.ZERO);
						return editDeptReq(req);
					}
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andRelRecordFlowEqualTo(relRecordFlow);
		return deptReqMapper.selectByExample(example);
	}

	@Override
	public List<SchRotationDeptReq> searchDeptReqByRel2(List<List<String>> relRecordFlowListList){
		if(org.apache.commons.collections4.CollectionUtils.isEmpty(relRecordFlowListList)) {
			return Collections.emptyList();
		}
		return deptReqMapper.selectListByPartitionList(relRecordFlowListList);
	}

	@Override
	public List<SchRotationDeptReq> searchDeptReqByExample(SchRotationDeptReqExample example){
		return deptReqMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow,String recTypeId,String itemId){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andRelRecordFlowEqualTo(relRecordFlow);
		if(StringUtil.isNotBlank(recTypeId)){
			criteria.andRecTypeIdEqualTo(recTypeId);
		}
		if(StringUtil.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return deptReqMapper.selectByExample(example);
	}
	
	@Override
	public List<SchRotationDeptReq> searchDeptReq(String rotationFlow,String schDeptFlow){
		if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(schDeptFlow)){
			SchRotationDeptReqExample example = new SchRotationDeptReqExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
			.andStandardDeptIdEqualTo(schDeptFlow);
			return deptReqMapper.selectByExample(example);
		}
		return null;
	}
	
	@Override
	public List<SchRotationDeptReq> searchDeptReqByItemId(String rotationFlow,String schDeptFlow,String itemId){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria criteria = example.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
				.andStandardDeptIdEqualTo(schDeptFlow);
		if(StringUtil.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return deptReqMapper.selectByExample(example);
	}
	
	@Override
	public SchRotationDeptReq readDeptReq(String reqFlow){
		return deptReqMapper.selectByPrimaryKey(reqFlow);
	}
	
	@Override
	public int editDeptReq(SchRotationDeptReq deptReq){
		if(deptReq!=null){
			if(StringUtil.isNotBlank(deptReq.getReqFlow())){
				GeneralMethod.setRecordInfo(deptReq, false);
				return deptReqMapper.updateByPrimaryKeySelective(deptReq);
			}else{
				deptReq.setReqFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(deptReq, true);
				return deptReqMapper.insertSelective(deptReq);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public List<SchRotationDeptReq> searchReqByRotationAndSchDept(List<String> rotationFlows,List<String> schDeptFlows,String recTypeId,String itemName){
		return deptReqExtMapper.searchReqByRotationAndSchDept(rotationFlows, schDeptFlows, recTypeId, itemName);
	}
	
	@Override
	public List<Map<String,Object>> countReqWithSchDept(List<String> rotationFlows,List<String> standardDeptIds){
		return deptReqExtMapper.countReqWithSchDept(rotationFlows,standardDeptIds);
	}

//	@Override
//	public List<SchRotationDeptReq> searchStandardReqByMust(String rotationFlow,String standardDeptId){
//		SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
//		rotationDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
//		.andIsRequiredEqualTo(GlobalConstant.FLAG_Y)
//		.andRotationFlowEqualTo(rotationFlow)
//		.andStandardDeptIdEqualTo(standardDeptId)
//		.andOrgFlowIsNull();
//		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);
//
//		List<SchRotationDeptReq> reqList = null;
//		if(rotationDeptList!=null && rotationDeptList.size()>0){
//			String relRecordFlow = rotationDeptList.get(0).getRecordFlow();
//			reqList = searchDeptReqByRel(relRecordFlow);
//		}
//		return reqList;
//	}
	
	@Override
	public List<SchRotationDeptReq> searchStandardReqByGroup(String standardGroupFlow,String standardDeptId){
		if(!StringUtil.isNotBlank(standardDeptId) || !StringUtil.isNotBlank(standardGroupFlow)){
			return null;
		}
		SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
		rotationDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andGroupFlowEqualTo(standardGroupFlow)
		.andStandardDeptIdEqualTo(standardDeptId)
		.andOrgFlowIsNull();
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);
		
		List<SchRotationDeptReq> reqList = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			String relRecordFlow = rotationDeptList.get(0).getRecordFlow();
			reqList = searchDeptReqByRel(relRecordFlow);
		}
		return reqList;
	}

	@Override
	public Pair<List<SchRotationDeptReq>, List<SchRotationDept>> searchStandardReqByGroup2(List<String> standardGroupFlowList,List<String> standardDeptIdList){
		List<String> filterStandardGroupFlowList = new ArrayList<>();
		List<String> filterStandardDeptIdList = new ArrayList<>();
		for(int i = 0; i < standardGroupFlowList.size(); i++) {
			if(!StringUtils.isEmpty(standardGroupFlowList.get(i)) && !StringUtils.isEmpty(standardDeptIdList.get(i))) {
				filterStandardGroupFlowList.add(standardGroupFlowList.get(i));
				filterStandardDeptIdList.add(standardDeptIdList.get(i));
			}
		}

		if(org.apache.commons.collections4.CollectionUtils.isEmpty(filterStandardGroupFlowList)) {
			return Pair.of(new ArrayList<>(), new ArrayList<>());
		}
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectListByGroupAndDept(filterStandardDeptIdList, filterStandardGroupFlowList);
		Map<String, SchRotationDept> keyToEntityMap = rotationDeptList.stream().collect(Collectors.toMap(vo -> vo.getGroupFlow() + vo.getStandardDeptId(), Function.identity(), (vo1, vo2) -> vo1));
		List<String> recordFlowList = keyToEntityMap.values().stream().map(vo -> vo.getRecordFlow()).collect(Collectors.toList());
		if(org.apache.commons.collections4.CollectionUtils.isEmpty(recordFlowList)) {
			return Pair.of(new ArrayList<>(), new ArrayList<>());
		}

		List<List<String>> recordFlowListList = Lists.partition(recordFlowList, 1000);
		List<SchRotationDeptReq> schRotationDeptReqList = searchDeptReqByRel2(recordFlowListList);
		List<SchRotationDept> schRotationDeptList = new ArrayList(keyToEntityMap.values());
		return Pair.of(schRotationDeptReqList, schRotationDeptList);
	}

	@Override
	public List<SchRotationDept> searchSchRotationDeptByPartitionList(List<List<String>> rotationFlowListList) {
		if(org.apache.commons.collections4.CollectionUtils.isEmpty(rotationFlowListList)) {
			return Collections.emptyList();
		}

		return rotationDeptMapper.searchSchRotationDeptByPartitionList(rotationFlowListList);
	}

	@Override
	public List<SchRotationDeptReq> searchStandardReqByStandard(
			String standardGroupFlow,
			String standardDeptId,
			String recTypeId,
			String itemId
			){
		List<SchRotationDeptReq> reqList = null;
		if(StringUtil.isNotBlank(standardDeptId)){
			SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
			SchRotationDeptExample.Criteria criteria = rotationDeptExample.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andStandardDeptIdEqualTo(standardDeptId).andOrgFlowIsNull();
				if(StringUtil.isNotBlank(standardGroupFlow)){
					criteria.andGroupFlowEqualTo(standardGroupFlow);
				}
			List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);
			
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				String relRecordFlow = rotationDeptList.get(0).getRecordFlow();
				reqList = searchDeptReqByRel(relRecordFlow,recTypeId,itemId);
			}
		}
		
		return reqList;
	}
	
	
	@Override
	public List<SchRotationDeptReq> searchStandardReqByResult(SchArrangeResult result,ResDoctor doctor){
		List<SchRotationDeptReq> reqList = null;
		if (result != null) {
			reqList = searchStandardReqByGroup(result.getStandardGroupFlow(), result.getStandardDeptId());
		}
		return reqList;
	}

	@Override
	public List<SchRotationDeptReq> searchStandardReqByGroupAndOrgSessionNumber(String standardGroupFlow, String standardDeptId,
																				String orgFlow, String sessionNumber) {

		if (!StringUtil.isNotBlank(standardDeptId) || !StringUtil.isNotBlank(standardGroupFlow)||
				!StringUtil.isNotBlank(orgFlow) || !StringUtil.isNotBlank(sessionNumber)) {
			return null;
		}
		SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
		rotationDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andGroupFlowEqualTo(standardGroupFlow)
				.andStandardDeptIdEqualTo(standardDeptId)
				.andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber);
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);

		List<SchRotationDeptReq> reqList = null;
		if (rotationDeptList != null && rotationDeptList.size() > 0) {
			String relRecordFlow = rotationDeptList.get(0).getRecordFlow();
			reqList = searchDeptReqByRel(relRecordFlow);
		}
		return reqList;
	}

	@Override
	public List<SchRotationDeptReq> searchStandardReqByResult(SchArrangeResult result, String recTypeId) {
		List<SchRotationDeptReq> reqList = null;
		if (StringUtil.isNotBlank(result.getStandardGroupFlow())) {
			String standardGroupFlow = result.getStandardGroupFlow();
			reqList = searchStandardReqByStandard(standardGroupFlow, result.getStandardDeptId(), recTypeId, null);
		}
		return reqList;
	}
	
	@Override
	public List<SchRotationDeptReq> searchStandardReqByRelFlows(List<String> relRecordFlows){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowIn(relRecordFlows);
		return deptReqMapper.selectByExample(example);
	}
	@Override
	public List<SchRotationDeptReq> searchSchRotationDeptReq(String relRecordFlow){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowEqualTo(relRecordFlow);
		return deptReqMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<SchRotationDeptReq> searchRotationDeptReq(String relRecordFlow, String recTypeId) {
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowEqualTo(relRecordFlow).andRecTypeIdEqualTo(recTypeId);
		return deptReqMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public void initSchRotationDeptReq(List<SchRotationDeptReq> schRotationDeptReqList){
		if (schRotationDeptReqList != null && schRotationDeptReqList.size() > 0) {
			for(SchRotationDeptReq temp : schRotationDeptReqList){
				temp.setReqFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(temp, true);
				deptReqMapper.insertSelective(temp);
			}
		}
	}

	@Override
	public List<SchRotationDept> doctorGetNotSchDept(String rotationFlow, String doctorFlow) {
		return rotationDeptExtMapper.doctorGetNotSchDept(rotationFlow,doctorFlow);
	}

	@Override
	public int synchronizeReq(String relRecordFlow,String currRelRecordFlow,String recTypeId){
		List<SchRotationDeptReq> schRotationDeptReqList = searchSchRotationDeptReq(relRecordFlow);
		SchRotationDept dept = readSchRotationDept(currRelRecordFlow);
		for(SchRotationDeptReq req: schRotationDeptReqList){
			if(StringUtil.isNotBlank(req.getItemId())){
				if(! GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(req.getItemId())){
					req.setRotationFlow(dept.getRotationFlow());
					req.setRelRecordFlow(currRelRecordFlow);
					req.setStandardDeptId(dept.getStandardDeptId());
					req.setStandardDeptName(dept.getStandardDeptName());
					req.setReqFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(req, true);
				    deptReqMapper.insertSelective(req);
				}
			}else{
				List<SchRotationDeptReq> rotationDeptReqList = searchRotationDeptReq(relRecordFlow,recTypeId);
				if(rotationDeptReqList!=null && !rotationDeptReqList.isEmpty()){
					SchRotationDeptReq schRotationDeptReq = rotationDeptReqList.get(0);
					if(schRotationDeptReq !=null){
						req.setReqFlow(req.getReqFlow());
						GeneralMethod.setRecordInfo(req, false);
					    deptReqMapper.updateByPrimaryKeySelective(req);
					}
				}else{
					req.setRotationFlow(dept.getRotationFlow());
					req.setRelRecordFlow(currRelRecordFlow);
					req.setStandardDeptId(dept.getStandardDeptId());
					req.setStandardDeptName(dept.getStandardDeptName());
					req.setReqFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(req, true);
				    deptReqMapper.insertSelective(req);
				}
			}
		}
			return GlobalConstant.ONE_LINE ;
	}
	/**=====================================================================================================*/
	
	@Override
	public int delGroupOrRotationDept(String recordFlow,String groupFlow,String rotationFlow){
		//delRelRotationDept(rotationFlow);
		delOrgGroupOrRotationDept(recordFlow,groupFlow);
		int result = 0;
		if(StringUtil.isNotBlank(recordFlow)){
			SchRotationDept rotationDept = new SchRotationDept();
			rotationDept.setRecordFlow(recordFlow);
			rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			result = saveSchRotationDept(rotationDept);
		}else if(StringUtil.isNotBlank(groupFlow)){
			SchRotationGroup rotationGroup = new SchRotationGroup();
			rotationGroup.setGroupFlow(groupFlow);
			rotationGroup.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			rotationGroupBiz.saveSchRotationGroup(rotationGroup);
			
			SchRotationDept rotationDept = new SchRotationDept();
			rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			SchRotationDeptExample example = new SchRotationDeptExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andGroupFlowEqualTo(groupFlow);
			result = rotationDeptMapper.updateByExampleSelective(rotationDept,example);
		}
		return result;
	}
	
	private int delOrgGroupOrRotationDept(String recordFlow,String groupFlow){
		int result = 0;
		
		SchRotationDeptExample deptExample = new SchRotationDeptExample();
		SchRotationGroupExample groupExample = new SchRotationGroupExample();
		
		if(StringUtil.isNotBlank(recordFlow)){
			SchRotationDept standardDept = readSchRotationDept(recordFlow);//查询标准的轮转科室
			String standardGroupFlow = standardDept.getGroupFlow();
			groupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andStandardGroupFlowEqualTo(standardGroupFlow);
			List<SchRotationGroup> localGroupList = rotationGroupMapper.selectByExample(groupExample);//查询科室所属组合的信息
			if(localGroupList!=null && localGroupList.size()>0){
				List<String> groupFlows = new ArrayList<String>();
				for(SchRotationGroup srg : localGroupList){
					groupFlows.add(srg.getGroupFlow());
				}
				
				SchRotationDept rotationDept = new SchRotationDept();
				rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				deptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andGroupFlowIn(groupFlows).andStandardDeptIdEqualTo(standardDept.getStandardDeptId())
				.andOrgFlowIsNotNull();
				result = rotationDeptMapper.updateByExampleSelective(rotationDept,deptExample);//同步已经本地化机构
			}
		}else if(StringUtil.isNotBlank(groupFlow)){
			SchRotationGroup group = new SchRotationGroup();
			group.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			
			groupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andStandardGroupFlowEqualTo(groupFlow);
			List<SchRotationGroup> localGroupList = rotationGroupMapper.selectByExample(groupExample);
			if(localGroupList!=null && localGroupList.size()>0){
				rotationGroupMapper.updateByExampleSelective(group,groupExample);
				
				List<String> groupFlows = new ArrayList<String>();
				for(SchRotationGroup srg : localGroupList){
					groupFlows.add(srg.getGroupFlow());
				}
				
				SchRotationDept rotationDept = new SchRotationDept();
				rotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				deptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andGroupFlowIn(groupFlows).andOrgFlowIsNotNull();
				result = rotationDeptMapper.updateByExampleSelective(rotationDept,deptExample);
			}
		}
		return result;
	}
	
	@Override
	public List<Map<String,String>> readStandardRotationDept(List<String> resultFlows){
		return rotationDeptExtMapper.readStandardRotationDeptByresultFlows(resultFlows);
	}

	@Override
	public SchRotationDept readStandardRotationDept(String resultFlow){
		SchRotationDept rotationDept = null;
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				if(StringUtil.isNotBlank(result.getStandardGroupFlow())){
					SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
					if(group!=null){
						if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
							SchRotationDeptExample example = new SchRotationDeptExample();
							SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
									.andRotationFlowEqualTo(rotationFlow)
									.andStandardDeptIdEqualTo(standardDeptId)
									.andOrgFlowIsNull()
									.andGroupFlowEqualTo(result.getStandardGroupFlow());
							List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
							if(rotationDeptList!=null && rotationDeptList.size()>0){
								rotationDept = rotationDeptList.get(0);
							}
						}
					}
				}
			}
		}
		return rotationDept;
	}

	@Override
	public List<SchRotationDept> readStandardRotationDeptByExample(SchRotationDeptExample example) {
		return rotationDeptMapper.selectByExample(example);
	}

	@Override
	public int getUnrelCount(String orgFlow,String rotationFlow){
		return rotationDeptExtMapper.getUnrelCount(orgFlow,rotationFlow);
	}
	
	@Override
	public int getSchInUsedCount(String schDeptFlow,String deptFlow){
		return rotationDeptExtMapper.getSchInUsedCount(schDeptFlow,deptFlow);
	}
	
	@Override
	public List<Map<String,Object>> getRotationMustSum(String orgFlow){
		return rotationDeptExtMapper.getRotationMustSum(orgFlow);
	}
	
	@Override
	public List<Map<String,Object>> getRotationGroupSum(String orgFlow){
		return rotationDeptExtMapper.getRotationGroupSum(orgFlow);
	}
	
	@Override
	public List<SchRotationDept> searchRotationDeptByStanard(String standardDeptRecFlow,String orgFlow){
		return rotationDeptExtMapper.searchRotationDeptByStanard(standardDeptRecFlow,orgFlow);
	}

	@Override
	public List<SchRotationDept> searchRotationDeptByFlows() {
		SchRotationDeptExample example = new SchRotationDeptExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andOrgFlowIsNull();
		return rotationDeptMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<SchRotationDeptReq> searchSchRotationDeptReqOrgNull() {
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return 	deptReqMapper.selectByExampleWithBLOBs(example);
	
	}
	@Override
	public SchRotationDept searchGroupFlowAndStandardDeptIdQuery(
			String groupFlow, String standardDeptId) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criter=example.createCriteria();
		criter.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(groupFlow)) {
			criter.andGroupFlowEqualTo(groupFlow);
		}
		if (StringUtil.isNotBlank(standardDeptId)) {
			criter.andStandardDeptIdEqualTo(standardDeptId);
		}
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(example);
		SchRotationDept rotationDept = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			rotationDept = rotationDeptList.get(0);
		}
		return rotationDept;
	}
	@Override
	public SchRotationDept searchGroupFlowAndStandardDeptIdQueryTwo(
			String groupFlow, String standardDeptId) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criter=example.createCriteria();
		criter.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(groupFlow)) {
			criter.andGroupFlowEqualTo(groupFlow);
		}
		if (StringUtil.isNotBlank(standardDeptId)) {
			criter.andStandardDeptIdEqualTo(standardDeptId);
		}
		List<SchRotationDept> rotationDeptList = rotationDeptExtMapper.selectByExampleTwo(example);
		SchRotationDept rotationDept = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			rotationDept = rotationDeptList.get(0);
		}
		return rotationDept;
	}

	@Override
	public List<SchRotationDept> searchOrgSchRotationDeptBySessionNumber(String rotationFlow, String orgFlow, String sessionNumber) {
		if(StringUtil.isBlank(rotationFlow)||StringUtil.isBlank(orgFlow)||StringUtil.isBlank(sessionNumber))
		{
			return null;
		}
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criter=example.createCriteria();
		criter.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criter.andRotationFlowEqualTo(rotationFlow);
		criter.andOrgFlowEqualTo(orgFlow);
		criter.andSessionNumberEqualTo(sessionNumber);
		criter.andSchDeptFlowIsNotNull();
		example.setOrderByClause("ORDINAL");
		return  rotationDeptMapper.selectByExample(example);
	}

	@Override
	public ExcelUtile importDeptReqExcel(MultipartFile file, Map<String,Object> map) {
		InputStream is = null;
		try{
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			final String[] keys = {
					"itemName",
					"reqNum",
					"reqNote"
			};
			String relRecordFlow = (String)map.get("relRecordFlow");
			String recTypeId = (String)map.get("recTypeId");
			boolean boolFlag = false;
			String reqFlow = "";
			List<String> itemNameList = new ArrayList<>();
			SchRotationDept rotationDept = readSchRotationDept(relRecordFlow);
			RegistryTypeEnum rte = EnumUtil.getById(recTypeId,RegistryTypeEnum.class);
			List<SchRotationDeptReq> deptReqList = searchDeptReqByRel(relRecordFlow,recTypeId);
			if(rte!=null && GlobalConstant.FLAG_Y.equals(rte.getHaveItem())){
				boolFlag = true;
				if(null != deptReqList && !deptReqList.isEmpty()){
					for(SchRotationDeptReq req : deptReqList){
						if(StringUtil.isNotBlank(req.getItemName())){
							itemNameList.add(req.getItemName());
						}
					}
				}
			}else if(null != deptReqList && !deptReqList.isEmpty()){
				reqFlow = deptReqList.get(0).getReqFlow();
			}
			boolean finalBoolFlag = boolFlag;
			String finalReqFlow = reqFlow;
			return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>(){
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<HashMap> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++){
						HashMap mp = datas.get(i);
						SchRotationDeptReq deptReq = new SchRotationDeptReq();
						deptReq.setRecTypeId(recTypeId);
						deptReq.setRecTypeName((String)map.get("recTypeName"));
						deptReq.setRelRecordFlow(relRecordFlow);
						deptReq.setRotationFlow((String)map.get("rotationFlow"));
						deptReq.setRotationFlow((String)map.get("rotationFlow"));
						if(rotationDept!=null){
							deptReq.setOrgFlow(rotationDept.getOrgFlow());
							deptReq.setOrgName(rotationDept.getOrgName());
							deptReq.setStandardDeptId(rotationDept.getStandardDeptId());
							deptReq.setStandardDeptName(rotationDept.getStandardDeptName());
						}
						if(finalBoolFlag){
							deptReq.setItemId(PkUtil.getUUID());
							deptReq.setItemName((String)mp.get("itemName"));
						}else{
							//没有子项名称
							deptReq.setReqFlow(finalReqFlow);
						}
						deptReq.setReqNum(new BigDecimal(String.valueOf(mp.get("reqNum"))));
						deptReq.setReqNote((String)mp.get("reqNote"));
						editDeptReq(deptReq);
						count++;
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(HashMap mp,ExcelUtile eu) {
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					if(finalBoolFlag && StringUtil.isBlank((String)mp.get("itemName"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【子项名称】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isNotBlank((String)mp.get("itemName")) && itemNameList.contains(mp.get("itemName"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【子项名称】已存在，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					if(StringUtil.isBlank((String)mp.get("reqNum"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【要求例数】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public ExcelUtile importDeptDataExcel(MultipartFile file, Map<String, Object> map) {
		InputStream is = null;
		try{
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			String recTypeId = (String)map.get("recTypeId");
			String schDeptFlow = (String)map.get("schDeptFlow");
			String rotationFlow = (String)map.get("rotationFlow");
			String processFlow = (String)map.get("processFlow");
			SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
			ResDoctor doc = doctorBiz.readDoctor(user.getUserFlow());
			SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
			//读取过程数据
			String str = "";
			ResDoctorSchProcess process = processBiz.read(processFlow);
			if(process!=null && StringUtil.isNotBlank(process.getSchResultFlow())) {
				SchRotationDept dept = readStandardRotationDept(process.getSchResultFlow());
				if(dept!=null) {
					str=dept.getRecordFlow();
				}
			}
			String recordFlow = str;
			String[] keys = null;
			if(RegistryTypeEnum.CaseRegistry.getId().equals(recTypeId)){
				keys = new String[]{"mr_no", "disease_pName", "mr_diagType"};
			}else if(RegistryTypeEnum.DiseaseRegistry.getId().equals(recTypeId)){
				keys = new String[]{"disease_diagName", "regItem", "disease_mrNo","disease_diagType","disease_isCharge","disease_isRescue","disease_treatStep"};
			}else if(RegistryTypeEnum.SkillRegistry.getId().equals(recTypeId)){
				keys = new String[]{"mr_no", "disease_pName", "mr_diagType"};
			}else if(RegistryTypeEnum.OperationRegistry.getId().equals(recTypeId)){
				keys = new String[]{"mr_no", "disease_pName", "mr_diagType"};
			}else if(RegistryTypeEnum.CampaignNoItemRegistry.getId().equals(recTypeId)){
				keys = new String[]{"mr_no", "disease_pName", "mr_diagType"};
			}
			return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>(){
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<HashMap> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++){
						HashMap mp = datas.get(i);
						mp.put("formFileName",recTypeId);
						mp.put("rotationFlow",rotationFlow);
						mp.put("schDeptFlow",schDeptFlow);
						mp.put("processFlow",processFlow);
						mp.put("recordFlow",recordFlow);
						mp.put("user",user);
						mp.put("doc",doc);
						mp.put("schDept",schDept);
						if(RegistryTypeEnum.CaseRegistry.getId().equals(recTypeId)){
							mp.put("mr_no",mp.get("mr_no"));
							mp.put("disease_pName",mp.get("disease_pName"));
							mp.put("mr_diagType",mp.get("mr_diagType"));
						}else if(RegistryTypeEnum.DiseaseRegistry.getId().equals(recTypeId)){
							mp.put("disease_diagName",mp.get("disease_diagName"));
							mp.put("regItem",mp.get("regItem"));
							mp.put("disease_mrNo",mp.get("disease_mrNo"));
							mp.put("disease_diagType",mp.get("disease_diagType"));
							mp.put("disease_isCharge",mp.get("disease_isCharge"));
							mp.put("disease_isRescue",mp.get("disease_isRescue"));
							mp.put("disease_treatStep",mp.get("disease_treatStep"));
						}else if(RegistryTypeEnum.SkillRegistry.getId().equals(recTypeId)){
						}else if(RegistryTypeEnum.OperationRegistry.getId().equals(recTypeId)){
						}else if(RegistryTypeEnum.CampaignNoItemRegistry.getId().equals(recTypeId)){
						}
						saveRegistryForm(mp);
						count++;
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(HashMap mp,ExcelUtile eu) {
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					if(StringUtil.isBlank((String)mp.get("mr_no"))){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行【病历号】为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}
	public int saveRegistryForm(Map<String,Object> map){
		String formFileName = (String)map.get("formFileName");
		String rotationFlow = (String)map.get("rotationFlow");
		String processFlow = (String)map.get("processFlow");
		String schDeptFlow = (String)map.get("schDeptFlow");
		String recordFlow = (String)map.get("recordFlow");
		SysUser user = (SysUser)map.get("user");
		ResDoctor doc = (ResDoctor)map.get("doc");
		SchDept schDept = (SchDept)map.get("schDept");
		if(StringUtil.isNotBlank(formFileName)){
			String productType = getRecFormByCfg(rotationFlow,recordFlow,formFileName);
			String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+formFileName);
			if(StringUtil.isBlank(currVer)){
				currVer = InitConfig.resFormRequestUtil.getVersionMap().get(GlobalConstant.RES_FORM_PRODUCT+"_"+formFileName);
			}
			if(StringUtil.isBlank(currVer)){
				currVer = GlobalConstant.RES_FORM_PRODUCT_VER;
			}
			Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(formFileName);
			IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
			if(singleForm == null){
				singleForm = singleFormMap.get(GlobalConstant.RES_FORM_PRODUCT+"_"+currVer);
			}
			if(singleForm == null){
				throw new RuntimeException("未发现表单 模版类型:"+productType+",表单类型:"+ResRecTypeEnum.getNameById(formFileName)+",版本号:"+currVer);
			}
			ResRec rec = new ResRec();
			//轮转科室信息
			rec.setSchDeptFlow(schDeptFlow);
			if(schDept!=null){
				rec.setSchDeptName(schDept.getSchDeptName());
				rec.setOrgFlow(schDept.getOrgFlow());
				rec.setOrgName(schDept.getOrgName());
				rec.setDeptFlow(schDept.getDeptFlow());
				rec.setDeptName(schDept.getDeptName());
			}
			rec.setMedicineType(user.getMedicineTypeId());
			//表单类型
			rec.setRecTypeId(formFileName);
			rec.setRecTypeName(ResRecTypeEnum.valueOf(formFileName).getTypeName());
			//表单版本
			rec.setRecVersion(currVer);
			rec.setRecForm(productType);
			//表单学员 设置表单的用户和机构信息
			rec.setOperUserFlow(doc.getDoctorFlow());
			rec.setOperUserName(doc.getDoctorName());
			rec.setOrgFlow(doc.getOrgFlow());
			rec.setOrgName(doc.getOrgName());
			//记录该表单在哪个过程中产生的
			rec.setProcessFlow(processFlow);
			//根据表单类型组织大字段
			String recContent = getRecContent(formFileName, singleForm.getItemList(), map);
			//更新大字段内容
			rec.setRecContent(recContent);
			edit(rec);
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}
	public int edit(ResRec rec) {
		if(rec!=null){
			if(StringUtil.isNotBlank(rec.getRecFlow())){//修改
				GeneralMethod.setRecordInfo(rec, false);
				return resRecMapper.updateByPrimaryKeySelective(rec);
			}else{//新增
				rec.setRecFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(rec, true);
				return resRecMapper.insertSelective(rec);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	private String getRecContent(String formName, List<Element> list,Map<String,Object> map){
		Element rootEle = DocumentHelper.createElement(formName);
		rootEle = getContentEle(list,map,rootEle);
		return rootEle.asXML();
	}
	private Element getContentEle(List<Element> list,Map<String,Object> map,Element rootEle){
		if(list !=null && list.size()>0 && rootEle!=null){
			for(Element itemEle : list){
				String eleName=itemEle.getName();
				if("item".equals(eleName)){
					func(itemEle,rootEle,map);
				}
			}
		}
		return rootEle;
	}
	private void func(Element itemEle,Element rootEle,Map<String,Object> map) {
		String multiple = itemEle.attributeValue("multiple");
		if (GlobalConstant.FLAG_N.equals(multiple)) {
			String name = itemEle.attributeValue("name");
			String isSelect = itemEle.attributeValue("select");
			String value = "";
			Element element = DocumentHelper.createElement(name);
			if (GlobalConstant.FLAG_Y.equals(isSelect)) {
				value = (String) map.get(name);
				String recTypeId = (String)map.get("formFileName");
				if(RegistryTypeEnum.CaseRegistry.getId().equals(recTypeId)){
					if("主要诊断".equals(value)){
						element.addAttribute("id", "1");
					}else if("次要诊断".equals(value)){
						element.addAttribute("id", "2");
					}else if("并行诊断".equals(value)){
						element.addAttribute("id", "3");
					}
				}else if(RegistryTypeEnum.DiseaseRegistry.getId().equals(recTypeId)){

				}
			} else {
				value = (String)map.get(name);
			}
			if (StringUtil.isNotBlank(value)){
				element.setText(value);
			}
			rootEle.add(element);
		}
	}
	private String getRecFormByCfg(String rotationFlow, String recordFlow, String recTypeId){
		String recForm = InitConfig.getSysCfg("res_form_category_"+rotationFlow+"_"+recordFlow+"_"+recTypeId);

		if(!StringUtil.isNotBlank(recForm)){
			recForm = InitConfig.getSysCfg("res_form_category_"+rotationFlow);
		}
		if(!StringUtil.isNotBlank(recForm)){
			recForm = InitConfig.getSysCfg("res_form_category");
		}

		if(!StringUtil.isNotBlank(recForm)){
			recForm = GlobalConstant.RES_FORM_PRODUCT;
		}

		return recForm;
	}

	@Override
	public Map importRotationFromExcel(MultipartFile file) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			Map map = parseExcel(wb);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private Map parseExcel(Workbook wb) {
		List<SchRotation> schRotationList = new ArrayList<>();
		List<SchRotationDept> schRotationDeptList = new ArrayList<>();
		List<SchRotationGroup> schRotationGroupList = new ArrayList<>();
		Map<String, Object> importResult = new HashMap<>();
		List<String> hintList = new ArrayList<>();
		String defaultMajor = null;
		String defaultTemplateProjectName = null;
		String defaultTerm = null;
		String defaultRotationDepartmentName = null;
		String defaultRotationDuration = null;
		String defaultStage = null;
		String defaulIfRotation = null;
		String defaulToChoose = null;
		String defaulGroupName = null;
		String defaulRotationTime = null;
		Integer defaulDeptNum = 0;
		Integer defaulMaxDeptNum = 0;
		SysUser user = GlobalContext.getCurrentUser();
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try {
				sheet = wb.getSheetAt(0);
			} catch (Exception e) {
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR = sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for (int i = 0; i < cell_num; i++) {
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for (int i = 1; i <= row_num; i++) {
				String hint = "";
				String major = "";
				String templateProjectName = "";
				String term = "";
				String rotationDepartmentName = "";
				String rotationDuration = "";
				String stage = "";
				String ifRotation = "";
				String deptNote = "";
				String groupName = "";
				String toChoose = "";
				String rotationTime = "";
				Integer deptNnum = 0;
				Integer maxDeptNnum = 0;
				Row r = sheet.getRow(i);
				boolean rowEmpty = isRowEmpty(r);
				if (rowEmpty == false) {
					SchRotationDept schRotationDept = new SchRotationDept();
					SchRotation schRotation = new SchRotation();
					SchRotationGroup schRotationGroupNew = new SchRotationGroup();
					for (int j = 0; j < colnames.size(); j++) {
						String value = "";
						Cell cell = r.getCell(j);
						if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
							if (cell.getCellType() == CellType.STRING) {
								value = cell.getStringCellValue().trim();
							} else {
								value = _doubleTrans(cell.getNumericCellValue()).trim();
							}
						}
						if (null != cell) {
							if ("专业".equals(colnames.get(j))) {
								major = value;
								if (StringUtil.isNotEmpty(major)) {
									defaultMajor = major;
								} else if (StringUtil.isEmpty(major) && StringUtil.isNotEmpty(defaultMajor)) {
									major = defaultMajor;
								}
								if (StringUtil.isNotEmpty(major)) {
									schRotation.setSpeName(major);
								}
							} else if ("方案名称".equals(colnames.get(j))) {
								templateProjectName = value;
								if (StringUtil.isNotEmpty(templateProjectName)) {
									defaultTemplateProjectName = templateProjectName;
								} else if (StringUtil.isEmpty(templateProjectName) && StringUtil.isNotEmpty(defaultTemplateProjectName)) {
									templateProjectName = defaultTemplateProjectName;
								}
								if (StringUtil.isNotEmpty(templateProjectName)) {
									schRotation.setRotationName(templateProjectName);
									schRotationDept.setRotationFlow(templateProjectName);
									schRotationGroupNew.setRotationFlow(templateProjectName);
								}
							} else if ("培养年限".equals(colnames.get(j))) {
								term = value;
								if (StringUtil.isNotEmpty(term)) {
									defaultTerm = term;
								} else if (StringUtil.isEmpty(term) && StringUtil.isNotEmpty(defaultTerm)) {
									term = defaultTerm;
								}
								if (StringUtil.isNotEmpty(term)) {
									schRotation.setRotationYear(term);
								}

							} else if ("轮转科室名称".equals(colnames.get(j))) {
								rotationDepartmentName = value;
								if (StringUtil.isNotEmpty(rotationDepartmentName)) {
									defaultRotationDepartmentName = rotationDepartmentName;
								} else if (StringUtil.isEmpty(rotationDepartmentName) && StringUtil.isNotEmpty(defaultRotationDepartmentName)) {
									rotationDepartmentName = defaultRotationDepartmentName;
								}
								if (StringUtil.isNotEmpty(rotationDepartmentName)) {
									schRotationDept.setStandardDeptName(rotationDepartmentName);
								}

							} else if ("轮转时长（月）".equals(colnames.get(j))) {
								rotationDuration = value;
								if (StringUtil.isNotEmpty(rotationDuration)) {
									defaultRotationDuration = rotationDuration;
								} else if (StringUtil.isEmpty(rotationDuration) && StringUtil.isNotEmpty(defaultRotationDuration)) {
									rotationDuration = defaultRotationDuration;
								}
								if (StringUtil.isNotEmpty(rotationDuration)) {
									schRotationDept.setSchMonth(rotationDuration);
								}
							} else if ("阶段".equals(colnames.get(j))) {
								stage = value;
								if (StringUtil.isNotEmpty(stage)) {
									defaultStage = stage;
								} else if (StringUtil.isEmpty(stage) && StringUtil.isNotEmpty(defaultStage)) {
									stage = defaultStage;
								}
								if (StringUtil.isNotEmpty(stage)) {
									schRotationGroupNew.setSchStageName(stage);
									schRotationGroupNew.setGroupName(stage);
								}
								schRotationDept.setGroupFlow(schRotationGroupNew.getGroupName());
							} else if ("是否选轮".equals(colnames.get(j))) {
								ifRotation = value;
								if (StringUtil.isNotEmpty(ifRotation)) {
									defaulIfRotation = ifRotation;
								} else if (StringUtil.isEmpty(ifRotation) && StringUtil.isNotEmpty(defaulIfRotation)) {
									ifRotation = defaulIfRotation;
								}
								if (StringUtil.isNotEmpty(ifRotation)) {
									schRotationGroupNew.setIsRequired(ifRotation);
									schRotationDept.setIsRequired(ifRotation);
								}
							} else if ("轮转说明".equals(colnames.get(j))) {
								deptNote = value;
								if (StringUtil.isNotEmpty(deptNote)) {
									schRotationDept.setDeptNote(deptNote);
								}
							} else if ("科室选择类型".equals(colnames.get(j)) && StringUtil.isNotEmpty(defaulIfRotation) && defaulIfRotation.equals("是")) {
								toChoose = value;
								if (StringUtil.isNotEmpty(toChoose)) {
									defaulToChoose = toChoose;
								} else if (StringUtil.isEmpty(toChoose) && StringUtil.isNotEmpty(defaulToChoose)) {
									toChoose = defaulToChoose;
								}
								if (StringUtil.isNotEmpty(toChoose)) {
									schRotationGroupNew.setSelTypeName(toChoose);
								}
							} else if ("轮转时间".equals(colnames.get(j)) && StringUtil.isNotEmpty(defaulIfRotation) && defaulIfRotation.equals("是")) {
								rotationTime = value;
								if (StringUtil.isNotEmpty(rotationTime)) {
									defaulRotationTime = rotationTime;
								} else if (StringUtil.isEmpty(rotationTime) && StringUtil.isNotEmpty(defaulRotationTime)) {
									rotationTime = defaulRotationTime;
								}
								if (StringUtil.isNotEmpty(rotationTime)) {
									schRotationGroupNew.setSchMonth(rotationTime);
								}
							} else if ("最小科室选择数".equals(colnames.get(j)) && StringUtil.isNotEmpty(defaulIfRotation) && defaulIfRotation.equals("是")) {
								if (StringUtil.isNotEmpty(value)) {
									deptNnum = Integer.parseInt(value);
								}
								if (0 != deptNnum) {
									defaulDeptNum = deptNnum;
								} else if (deptNnum != 0.0 && 0.0 != defaulDeptNum) {
									deptNnum = defaulDeptNum;
								}
								if (0 != deptNnum) {
									schRotationGroupNew.setDeptNum(deptNnum);
								}
							} else if ("最大科室选择数".equals(colnames.get(j)) && StringUtil.isNotEmpty(defaulIfRotation) && defaulIfRotation.equals("是")
									&& StringUtil.isNotEmpty(defaulToChoose) && defaulToChoose.equals("自由选科")) {
								if (StringUtil.isNotEmpty(value)) {
									maxDeptNnum = Integer.parseInt(value);
								}
								if (0 != maxDeptNnum) {
									defaulMaxDeptNum = maxDeptNnum;
								} else if (0 != maxDeptNnum && 0 != defaulMaxDeptNum) {
									maxDeptNnum = defaulMaxDeptNum;
								}
								if (0 != maxDeptNnum) {
									schRotationGroupNew.setMaxDeptNum(maxDeptNnum);
								}
							}
						}
					}
					schRotationList.add(schRotation);
					schRotationDeptList.add(schRotationDept);
					schRotationGroupList.add(schRotationGroupNew);
					if (StringUtil.isNotEmpty(hint)) {
						hintList.add(hint);
					}
				}
			}
		}
		if (StringUtil.isEmpty(defaultMajor) || StringUtil.isEmpty(defaultTemplateProjectName) || StringUtil.isEmpty(defaultRotationDepartmentName)) {
			hintList.add("数据填写错误！上传失败！");
		} else if (CollectionUtils.isEmpty(schRotationList) || CollectionUtils.isEmpty(schRotationDeptList) || CollectionUtils.isEmpty(schRotationGroupList)) {
			hintList.add("数据填写错误！上传失败！");
		} else {
			importRotation(schRotationList, schRotationDeptList, schRotationGroupList, hintList);
		}
		importResult.put("count", count);
		importResult.put("hintList", hintList);
		return importResult;
	}

	@Transactional
	public List<String> importRotation(List<SchRotation> schRotations, List<SchRotationDept> schRotationDepts, List<SchRotationGroup> schRotationGroups, List<String> hintList) {
		SysUser user = GlobalContext.getCurrentUser();
		//标准科室
		Map<String, SysDict> sysStandardDeptMap = new HashMap<>();
		//培训专业
		Map<String, SysDict> sysDoctorTrainingSpeMap = new HashMap<>();
		List<SysDict> sysStandardDepts = rotationExtendMapper.selectDictStandardDept();
		//名称去重
		if (CollectionUtils.isNotEmpty(sysStandardDepts)) {
			List<SysDict> sysStandardDeptList = sysStandardDepts.stream().collect(
					Collectors.collectingAndThen(
							Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysDict::getDictName))), ArrayList::new)
			);
			sysStandardDeptMap = Maps.uniqueIndex(sysStandardDeptList, SysDict::getDictName);
		}

		List<SysDict> sysDoctorTrainingSpes = rotationExtendMapper.selectDictDoctorTrainingSpe();
		//名称去重
		if (CollectionUtils.isNotEmpty(sysDoctorTrainingSpes)) {
			List<SysDict> sysDoctorTrainingSpeList = sysDoctorTrainingSpes.stream().collect(
					Collectors.collectingAndThen(
							Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysDict::getDictName))), ArrayList::new)
			);
			sysDoctorTrainingSpeMap = Maps.uniqueIndex(sysDoctorTrainingSpeList, SysDict::getDictName);
		}
		// 根据方案名称去重
		List<SchRotation> schRotationList = schRotations.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SchRotation::getRotationName))), ArrayList::new)
		);
		// 根据组合名称去重
		List<SchRotationGroup> schRotationGroupNewList = schRotationGroups.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SchRotationGroup::getSchStageName))), ArrayList::new)
		);
		// 根据科室名称去重
		List<SchRotationDept> schRotationDeptList = schRotationDepts.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SchRotationDept::getStandardDeptName))), ArrayList::new)
		);
		//保存方案
		for (SchRotation schRotationNew : schRotationList) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			schRotationNew.setRotationFlow(uuid);
			schRotationNew.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			schRotationNew.setCreateUserFlow(user.getUserFlow());
			schRotationNew.setCreateTime(DateUtil.getCurrDateTime());
			if(null == sysDoctorTrainingSpeMap.get(schRotationNew.getSpeName())){
				hintList.add("未找到此专业: "+schRotationNew.getSpeName()+" 请先在系统内添加！");
				return hintList;
			}
			schRotationNew.setSpeId(sysDoctorTrainingSpeMap.get(schRotationNew.getSpeName()).getDictId());
			schRotationNew.setDoctorCategoryId("Doctor");
			schRotationNew.setDoctorCategoryName("住院医师");
			schRotationNew.setPublishFlag(GlobalConstant.FLAG_Y);
			rotationMapper.insertSelective(schRotationNew);
			schRotationNew.setRotationFlow(uuid);
		}
		//方案Map
		Map<String, SchRotation> schRotationNewMap = Maps.uniqueIndex(schRotationList, SchRotation::getRotationName);
		//保存组合
		for (SchRotationGroup rotationGroupNew : schRotationGroupNewList) {
			int sumMonth = 0;
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			rotationGroupNew.setGroupFlow(uuid);
			rotationGroupNew.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			rotationGroupNew.setCreateUserFlow(user.getUserFlow());
			switch (rotationGroupNew.getSchStageName()) {
				case "第一阶段":
					rotationGroupNew.setSchStageId("FirstStage");
					break;
				case "第二阶段":
					rotationGroupNew.setSchStageId("SecondStage");
					break;
				case "第三阶段":
					rotationGroupNew.setSchStageId("ThirdStage");
					break;
			}

			for (SchRotationDept schRotationDept : schRotationDeptList) {
				if (StringUtil.isNotEmpty(schRotationDept.getGroupFlow())) {
					if (schRotationDept.getGroupFlow().equals(rotationGroupNew.getGroupName())) {
						sumMonth += Integer.parseInt(schRotationDept.getSchMonth());
						if ("是".equals(rotationGroupNew.getIsRequired())) {
							break;
						}
					}
				}
			}
			if (sumMonth != 0 && ("否").equals(rotationGroupNew.getIsRequired())) {
				rotationGroupNew.setSchMonth(sumMonth + "");
			}
			if (StringUtil.isNotEmpty(rotationGroupNew.getSelTypeName())) {
				if ("自由选科".equals(rotationGroupNew.getSelTypeName())) {
					rotationGroupNew.setSelTypeId("Free");
					if(null == rotationGroupNew.getDeptNum() || null == rotationGroupNew.getMaxDeptNum()){
						hintList.add("自由选科需填写最大科室选择和最小科室选择！");
						return hintList;
					}
				} else {
					rotationGroupNew.setSelTypeId("Fixed");
					if(null == rotationGroupNew.getDeptNum()){
						hintList.add("固定选科需填写最小科室选择数！");
						return hintList;
					}
				}
			}
			rotationGroupNew.setCreateTime(DateUtil.getCurrDateTime());
			if (StringUtil.isNotEmpty(rotationGroupNew.getIsRequired())) {
				if ("是".equals(rotationGroupNew.getIsRequired())) {
					rotationGroupNew.setIsRequired(GlobalConstant.FLAG_N);
					if(StringUtil.isEmpty(rotationGroupNew.getSchMonth()) || StringUtil.isEmpty(rotationGroupNew.getSelTypeName())){
						hintList.add("轮转时间,科室选择类型未填写！");
						return hintList;
					}
				} else {
					rotationGroupNew.setIsRequired(GlobalConstant.FLAG_Y);
				}
				if (null != schRotationNewMap.get(rotationGroupNew.getRotationFlow())) {
					rotationGroupNew.setRotationFlow(schRotationNewMap.get(rotationGroupNew.getRotationFlow()).getRotationFlow());
				}
			}
			SchRotationGroup schRotationGroup = new SchRotationGroup();
			BeanUtils.copyProperties(rotationGroupNew, schRotationGroup);
			rotationGroupMapper.insertSelective(schRotationGroup);
			rotationGroupNew.setGroupFlow(uuid);
		}
		//组合Map
		Map<String, SchRotationGroup> schRotationGroupNewMap = Maps.uniqueIndex(schRotationGroupNewList, SchRotationGroup::getGroupName);
		//保存科室
		for (SchRotationDept schRotationDept : schRotationDeptList) {
			schRotationDept.setRecordFlow(PkUtil.getUUID());
			schRotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			schRotationDept.setCreateTime(DateUtil.getCurrDateTime());
			schRotationDept.setCreateUserFlow(user.getUserFlow());
			if (null != sysStandardDeptMap.get(schRotationDept.getStandardDeptName())) {
				schRotationDept.setStandardDeptId(sysStandardDeptMap.get(schRotationDept.getStandardDeptName()).getDictId());
			}else{
				hintList.add("未找到此科室: "+schRotationDept.getStandardDeptName()+" 请先在系统内添加！");
				return hintList;
			}
			if (null != schRotationGroupNewMap.get(schRotationDept.getGroupFlow())) {
				schRotationDept.setRecordStatus(schRotationGroupNewMap.get(schRotationDept.getGroupFlow()).getRecordStatus());
				schRotationDept.setGroupFlow(schRotationGroupNewMap.get(schRotationDept.getGroupFlow()).getGroupFlow());
			}
			if (null != schRotationNewMap.get(schRotationDept.getRotationFlow())) {
				schRotationDept.setRotationFlow(schRotationNewMap.get(schRotationDept.getRotationFlow()).getRotationFlow());
			}

			//轮转科室不为空则获取这个科室
			if (StringUtil.isNotBlank(schRotationDept.getSchDeptFlow())) {
				SchDept schDept = schDeptBiz.readSchDept(schRotationDept.getSchDeptFlow());
				if (null != schDept) {
					schRotationDept.setDeptFlow(schDept.getDeptFlow());
					schRotationDept.setDeptName(schDept.getDeptName());
				}
			}
			if (StringUtil.isNotEmpty(schRotationDept.getIsRequired())) {
				if ("是".equals(schRotationDept.getIsRequired())) {
					schRotationDept.setIsRequired(GlobalConstant.FLAG_N);
				} else {
					schRotationDept.setIsRequired(GlobalConstant.FLAG_Y);
				}
				//轮转时间为空则默认为0
				schRotationDept.setSchMonth(StringUtil.defaultIfEmpty(schRotationDept.getSchMonth(), "0"));
				rotationDeptMapper.insertSelective(schRotationDept);
			}
		}
		return hintList;
	}

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return false;
			}
		}
		return true;
	}
}
