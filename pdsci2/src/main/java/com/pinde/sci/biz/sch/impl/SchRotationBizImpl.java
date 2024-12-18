package com.pinde.sci.biz.sch.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.core.common.enums.sch.SchSelTypeEnum;
import com.pinde.core.common.enums.sch.SchStageEnum;
import com.pinde.core.model.*;
import com.pinde.core.model.SchRotationExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResRotationOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchRotationExtMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchRotationBizImpl implements ISchRotationBiz {
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private SchRotationExtMapper rotationExtMapper;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IResRotationOrgBiz iResRotationOrgBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private SchRotationGroupMapper rotationGroupMapper;
	@Autowired
	private ISchRotationGroupBiz rotationGroupBiz;
	@Autowired
	private ISchDeptBiz deptBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private SchRotationDeptMapper schRotationDeptMapper;
	@Autowired
	private SchRotationDeptReqMapper schRotationDeptReqMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ResOrgRotationCfgMapper rotationCfgMapper;


	@Override
	public List<SchRotation> searchSchRotation() {
		SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL");
		return rotationMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<SchRotation> searchSchRotationByIsPublish() {
		SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("ORDINAL");
		return rotationMapper.selectByExample(example);
	}



//	@Override
//	public List<SchRotation> searchRotation(String orgFlow) {
//		SchRotationExample example = new SchRotationExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andRotationTypeIdEqualTo(SchRotationTypeEnum.Local.getId());
//		example.setOrderByClause("ORDINAL");
//		return rotationMapper.selectByExample(example);
//	}

//	@Override
//	public SchRotation readRotationByStandardAndOrg(String orgFlow,String rotationFlow) {
//		SchRotation rotation = null;
//		SchRotationExample example = new SchRotationExample();
//		example.createCriteria()
//		.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//		.andOrgFlowEqualTo(orgFlow)
//		.andRotationTypeIdEqualTo(SchRotationTypeEnum.Local.getId())
//		.andRotationFlowEqualTo(rotationFlow);
//		List<SchRotation> rotationList = rotationMapper.selectByExample(example);
//		if(rotationList!=null && rotationList.size()>0){
//			rotation = rotationList.get(0);
//		}
//		return rotation;
//	}

//	@Override
//	public List<SchRotation> searchSchRotationIsPublish(String publishFlag) {
//		SchRotationExample example = new SchRotationExample();
//		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRotationTypeIdEqualTo(SchRotationTypeEnum.Standard.getId());
//		if(StringUtil.isNotBlank(publishFlag)){
//			criteria.andPublishFlagEqualTo(publishFlag);
//		}
//		example.setOrderByClause("ORDINAL");
//		return rotationMapper.selectByExample(example);
//	}

	@Override
	public List<SchRotation> searchSchRotationForPlatform(String doctorCateGoryId) {
		SchRotationExample example = new SchRotationExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(doctorCateGoryId)){
			criteria.andDoctorCategoryIdEqualTo(doctorCateGoryId);
		}
		example.setOrderByClause("ORDINAL");
		return rotationMapper.selectByExample(example);
	}

//	@Override
//	public List<SchRotation> searchRotationByRole(String roleFlag,SchRotation rotation){
//		return rotationExtMapper.searchRotationByRole(roleFlag,rotation);
//	}

	@Override
	public List<SchRotation> searchOrgStandardRotation(SchRotation rotation){
		SchRotationExample example = new SchRotationExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

		if(StringUtil.isNotBlank(rotation.getDoctorCategoryId())){
			criteria.andDoctorCategoryIdEqualTo(rotation.getDoctorCategoryId());
		}
		if(StringUtil.isNotBlank(rotation.getSpeId())){
			criteria.andSpeIdEqualTo(rotation.getSpeId());
		}
		if(StringUtil.isNotBlank(rotation.getPublishFlag())){
			criteria.andPublishFlagEqualTo(rotation.getPublishFlag());
		}
		if(StringUtil.isNotBlank(rotation.getWorkOrgId())){
			criteria.andWorkOrgIdEqualTo(rotation.getWorkOrgId());
		}
		if(StringUtil.isNotBlank(rotation.getRotationName())) {
			criteria.andRotationNameLike("%" + rotation.getRotationName() + "%");
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return rotationMapper.selectByExample(example);
	}

	@Override
	public List<SchRotation> searchSchRotation(String speId) {
		SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSpeIdEqualTo(speId)
                .andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("CREATE_TIME DESC");
		return rotationMapper.selectByExample(example);
	}

	@Override
	public SchRotation readSchRotation(String rotationFlow) {
		return rotationMapper.selectByPrimaryKey(rotationFlow);
	}

	@Override
	public List<SchRotation> readSchRotationByPartitionList(List<List<String>> rotationFlowListList) {
		if(CollectionUtils.isEmpty(rotationFlowListList)) {
			return Collections.emptyList();
		}

		return rotationMapper.readSchRotationByPartitionList(rotationFlowListList);
	}

	@Override
	public List<SchRotation> readSchRotationByExample(SchRotationExample example) {
		return rotationMapper.selectByExample(example);
	}

	@Override
	public int saveSchRotation(SchRotation rotation) {
		if(rotation != null){
			if(StringUtil.isNotBlank(rotation.getRotationFlow())){
				GeneralMethod.setRecordInfo(rotation, false);
				return rotationMapper.updateByPrimaryKeySelective(rotation);
			}else{
				rotation.setRotationFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(rotation, true);
				return rotationMapper.insertSelective(rotation);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<SchRotation> searchRotationByrotationFlows(List<String> rotationFlows){
		SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRotationFlowIn(rotationFlows);
		example.setOrderByClause("ORDINAL");
		return rotationMapper.selectByExample(example);
	}

//	@Override
//	public List<SchRotation> searchNotExistRotation(String orgFlow){
//		return rotationExtMapper.searchNotExistRotation(orgFlow);
//	}

	@Override
	public int saveLocalRotation(List<SchRotation> rotationList,String orgFlow){
		String orgName = null;
		Map<String,List<SchDeptRel>> deptRelListMap = new HashMap<String, List<SchDeptRel>>();
		if(StringUtil.isNotBlank(orgFlow)){
			//根据 orgflow 查询 Sch_dept_rel（轮转科室关联标准科室）
			List<SchDeptRel> deptRelList = deptRelBiz.searchRelByOrg(orgFlow);
			//解析标准科室轮转科室关系( 把 标准科室id 一一对应 轮转科室关联标准科室实体 )
			if(deptRelList!=null && deptRelList.size()>0){
				for(SchDeptRel deptRel : deptRelList){
					String key = deptRel.getStandardDeptId();//标准科室id
					if(deptRelListMap.get(key)==null){
						List<SchDeptRel> deptRelTempRelList = new ArrayList<SchDeptRel>();
						deptRelTempRelList.add(deptRel);
						deptRelListMap.put(key,deptRelTempRelList);
					}else{
						deptRelListMap.get(key).add(deptRel);
					}
				}
			}

			SysOrg org = orgBiz.readSysOrg(orgFlow);
			if(org!=null){
				orgName = org.getOrgName();
			}
		}

		//保存本地方案
		if(rotationList!=null && rotationList.size()>0){
			List<String> stabdardRotationFlows = new ArrayList<String>();
			for(SchRotation rotation : rotationList){
				stabdardRotationFlows.add(rotation.getRotationFlow());
			}
			//通过标准方案获取标准规则
			List<SchRotationGroup> groupList = groupBiz.searchGroupByRotations(stabdardRotationFlows);
			List<SchRotationDept> rotationDeptList = rotationDeptBiz.searchDeptByRotations(stabdardRotationFlows);

			//解析标准轮转规则
			Map<String,List<SchRotationDept>> rotationDeptListMap = null;
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
				for(SchRotationDept rotationDept : rotationDeptList){
					String key = rotationDept.getGroupFlow();
					if(rotationDeptListMap.get(key)==null){
						List<SchRotationDept> rotationDeptTempList = new ArrayList<SchRotationDept>();
						rotationDeptTempList.add(rotationDept);
						rotationDeptListMap.put(key,rotationDeptTempList);
					}else{
						rotationDeptListMap.get(key).add(rotationDept);
					}
				}
			}
			//解析标准轮转规则分组
			Map<String,List<SchRotationGroup>> groupListMap = null;
			if(groupList!=null && groupList.size()>0){
				groupListMap = new HashMap<String, List<SchRotationGroup>>();
				for(SchRotationGroup group : groupList){
					String key = group.getRotationFlow();
					if(groupListMap.get(key)==null){
						List<SchRotationGroup> groupTempList = new ArrayList<SchRotationGroup>();
						groupTempList.add(group);
						groupListMap.put(key,groupTempList);
					}else{
						groupListMap.get(key).add(group);
					}
				}
			}

			//解析保存本地规则
			for(SchRotation rotation : rotationList){
				String rotationFlow = rotation.getRotationFlow();
				//获取本方案必轮科室列表
//				List<SchRotationDept> mustRotationDeptList = null;
//				if(rotationDeptListMap!=null){
//					mustRotationDeptList = rotationDeptListMap.get(rotationFlow);
//				}
//				if(mustRotationDeptList!=null && mustRotationDeptList.size()>0){
//					for(SchRotationDept rotationDept : mustRotationDeptList){
//						rotationDept.setRotationFlow(rotationFlow);
//						rotationDept.setOrgFlow(orgFlow);
//						rotationDept.setOrgName(orgName);
//						parseStandardDept(deptRelListMap,rotationDept,rotation);
//					}
//				}
				//获取本方案组合列表
				if(groupListMap!=null){
					List<SchRotationGroup> standardGroupList = groupListMap.get(rotationFlow);
					if(standardGroupList!=null && standardGroupList.size()>0){
						for(SchRotationGroup group : standardGroupList){
							Integer deptNum = group.getDeptNum();
							Integer maxDeptNum = group.getMaxDeptNum();
							//获取组合内科室
							List<SchRotationDept> groupRotationDeptList = null;
							if(rotationDeptListMap!=null){
								groupRotationDeptList = rotationDeptListMap.get(group.getGroupFlow());
							}
							group.setStandardGroupFlow(group.getGroupFlow());
							group.setGroupFlow(PkUtil.getUUID());
							if(groupRotationDeptList!=null && groupRotationDeptList.size()>0){
								for(SchRotationDept rotationDept : groupRotationDeptList){
									rotationDept.setRotationFlow(rotationFlow);
									rotationDept.setGroupFlow(group.getGroupFlow());
									rotationDept.setOrgFlow(orgFlow);
									rotationDept.setOrgName(orgName);

									int upCount = parseStandardDept(deptRelListMap,rotationDept,rotation);
                                    if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(group.getIsRequired())) {
										if(SchSelTypeEnum.Free.getId().equals(group.getSelTypeId())){
											if(maxDeptNum!=null){
												maxDeptNum+=upCount;
											}
										}else{
											if(deptNum!=null){
												deptNum+=upCount;
											}
										}
									}
								}
							}

							group.setRotationFlow(rotationFlow);
							if(SchSelTypeEnum.Free.getId().equals(group.getSelTypeId())){
								group.setMaxDeptNum(maxDeptNum);
							}else{
								group.setDeptNum(deptNum);
							}
							group.setOrgFlow(orgFlow);
							group.setOrgName(orgName);
							GeneralMethod.setRecordInfo(group,true);
							rotationGroupMapper.insertSelective(group);
						}
					}
				}

				//获取本方案组合
				List<SchRotationGroup> confirmGroupList = rotationGroupBiz.searchOrgGroupOrAll(rotationFlow,orgFlow,null);
				//获取本方案规则
				List<SchRotationDept> confirmDeptList = rotationDeptBiz.searchOrgSchRotationDept(rotationFlow,orgFlow);
				Map<String,List<SchRotationDept>> confirmDeptMap = null;
				if(confirmDeptList!=null && confirmDeptList.size()>0){
					confirmDeptMap = new HashMap<String,List<SchRotationDept>>();
					for(SchRotationDept srd : confirmDeptList){
						String key = srd.getGroupFlow();
						if(confirmDeptMap.get(key)==null){
							List<SchRotationDept> srds = new ArrayList<SchRotationDept>();
							srds.add(srd);
							confirmDeptMap.put(key,srds);
						}else{
							confirmDeptMap.get(key).add(srd);
						}
					}
				}

				//产生本地顺序
				if(confirmDeptMap!=null && confirmDeptMap.size()>0){
					int ord = 0;
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(rotation.getIsStage())) {
						if(confirmGroupList!=null && confirmGroupList.size()>0){
							Map<String,List<SchRotationGroup>> confirmGroupMap = new HashMap<String,List<SchRotationGroup>>();

							for(SchRotationGroup srg : confirmGroupList){
								String key = srg.getSchStageId();
								if(confirmGroupMap.get(key)==null){
									List<SchRotationGroup> srgs = new ArrayList<SchRotationGroup>();
									srgs.add(srg);
									confirmGroupMap.put(key,srgs);
								}else{
									confirmGroupMap.get(key).add(srg);
								}
							}

							for(SchStageEnum stage : SchStageEnum.values()){
								String groupKey = stage.getId();
								List<SchRotationGroup> sortGroupList = confirmGroupMap.get(groupKey);
								if(sortGroupList!=null && sortGroupList.size()>0){
									for(SchRotationGroup srg : sortGroupList){
										String deptKey = srg.getGroupFlow();
										List<SchRotationDept> sortDeptList = confirmDeptMap.get(deptKey);
										if(sortDeptList!=null&&sortDeptList.size()>0) {
											for (SchRotationDept srd : sortDeptList) {
												srd.setOrdinal(ord++);
												rotationDeptBiz.saveSchRotationDept(srd);
											}
										}
									}
								}
							}
						}
					}else{
						if(confirmGroupList!=null && confirmGroupList.size()>0){
							for(SchRotationGroup srg : confirmGroupList){
								String deptKey = srg.getGroupFlow();
								List<SchRotationDept> sortDeptList = confirmDeptMap.get(deptKey);
								if(sortDeptList!=null && sortDeptList.size()>0){
									for(SchRotationDept srd : sortDeptList){
										srd.setOrdinal(ord++);
										rotationDeptBiz.saveSchRotationDept(srd);
									}
								}
							}
						}
					}
				}
			}
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	private int parseStandardDept(Map<String,List<SchDeptRel>> deptRelListMap,SchRotationDept rotationDept,SchRotation rotation){
		int overFlowNum = 0;
		if(rotationDept!=null){
			List<SchDeptRel> deptRelList = deptRelListMap.get(rotationDept.getStandardDeptId());
			if(deptRelList!=null && deptRelList.size()>0){
				boolean first = true;
				for(SchDeptRel deptRel : deptRelList){
					SchDept dept = deptBiz.readSchDept(deptRel.getSchDeptFlow());

					rotationDept.setRecordFlow(null);
					if(dept!=null){
						rotationDept.setSchDeptFlow(dept.getSchDeptFlow());
						rotationDept.setSchDeptName(dept.getSchDeptName());
						rotationDept.setDeptFlow(dept.getDeptFlow());
						rotationDept.setDeptName(dept.getDeptName());
					}

					if(!first){
						rotationDept.setSchMonth("0");
					}
					rotationDeptBiz.saveSchRotationDept(rotationDept);
					first = false;
				}
				overFlowNum = deptRelList.size()-1;
			}else{
				rotationDept.setRecordFlow(null);
				rotationDeptBiz.saveSchRotationDept(rotationDept);
			}
		}
		return overFlowNum;
	}

//	@Override
//	public int editRotations(List<SchRotation> rotationList){
//		if(rotationList!=null && rotationList.size()>0){
//			for(SchRotation rotation : rotationList){
//				saveSchRotation(rotation);
//			}
//			return com.pinde.core.common.GlobalConstant.ONE_LINE;
//		}
//		return com.pinde.core.common.GlobalConstant.ZERO_LINE;
//	}

	@Override
	public List<SchRotation> searchNotExistRotation(String orgFlow) {
		return rotationExtMapper.searchNotExistRotation(orgFlow);
	}

	@Override
	public int rotationClone(String rotationFlow,String rotationYear){
		if(StringUtil.isNotBlank(rotationFlow)){
			SchRotation rotation = readSchRotation(rotationFlow);

			if(rotation!=null){
				//保存新方案
				rotation.setRotationFlow(null);
                rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
				//rotation.setRotationYear(rotationYear);
				rotation.setRotationName("复制-" + rotation.getRotationName());
                rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
				saveSchRotation(rotation);

				//保存方案的表单配置
				SysCfg cfg = cfgBiz.read("res_form_category_"+rotationFlow);
				if(cfg!=null){
					cfg.setCfgCode("res_form_category_"+rotation.getRotationFlow());
					cfgBiz.add(cfg);
				}

				//查询老方案必轮科室
//				List<SchRotationDept> oldRotationDeptList = rotationDeptBiz.searchSchRotationDeptMustWithBLOBs(rotationFlow);
//				if(oldRotationDeptList!=null && oldRotationDeptList.size()>0){
//					for(SchRotationDept rotationDept : oldRotationDeptList){
//						String relFlow = rotationDept.getRecordFlow();
//
//						rotationDept.setRecordFlow(null);
//						rotationDept.setRotationFlow(rotation.getRotationFlow());
//						rotationDeptBiz.saveSchRotationDept(rotationDept);
//
//						//保存关联的要求
//						saveReqByRel(relFlow,rotationDept.getRecordFlow(),rotation);
//					}
//				}

				//查询老方案科室组
				List<SchRotationGroup> oldGroupList = groupBiz.searchSchRotationGroup(rotationFlow);
				if(oldGroupList!=null && oldGroupList.size()>0){
					for(SchRotationGroup group : oldGroupList){
						String oldGroupFlow = group.getGroupFlow();

						group.setGroupFlow(null);
						group.setRotationFlow(rotation.getRotationFlow());
						groupBiz.saveSchRotationGroup(group);

						List<SchRotationDept> oldGroupDeptList = rotationDeptBiz.searchRotationDeptByGroupFlowWithBLOBs(oldGroupFlow);
						if(oldGroupDeptList!=null && oldGroupDeptList.size()>0){
							for(SchRotationDept rotationDept : oldGroupDeptList){
								String relFlow = rotationDept.getRecordFlow();

								rotationDept.setRecordFlow(null);
								rotationDept.setRotationFlow(rotation.getRotationFlow());
								rotationDept.setGroupFlow(group.getGroupFlow());
								rotationDeptBiz.saveSchRotationDept(rotationDept);
								//保存关联的要求
								saveReqByRel(relFlow,rotationDept.getRecordFlow(),rotation);
							}
						}
					}
				}
			}
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	private void saveReqByRel(String oldRelFlow,String newRelFlow,SchRotation rotation){
		String rotationFlow = rotation.getRotationFlow();
		if(StringUtil.isNotBlank(oldRelFlow)){
			List<SchRotationDeptReq> reqList = rotationDeptBiz.searchDeptReqByRel(oldRelFlow);
			if(reqList!=null && reqList.size()>0){
				for(SchRotationDeptReq req : reqList){
					req.setReqFlow(null);
					req.setRelRecordFlow(newRelFlow);
					req.setRotationFlow(rotationFlow);
					rotationDeptBiz.editDeptReq(req);
				}
			}
		}
	}

	@Override
	public List<SchRotation> searchRotationByName(String rotationName){
		SchRotationExample example = new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andRotationNameEqualTo(rotationName);
		return rotationMapper.selectByExample(example);
	}

	/**
	 * 为医师根据专业自动更新方案
	 * @param
	 */
	@Override
	public ResDoctor updateDocRotation(ResDoctor doctor){
		//20180420
		if(doctor!=null){
			SchRotation rotation = null;

			String trainingType = doctor.getTrainingTypeId();
            if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
				trainingType = RecDocCategoryEnum.Doctor.getId();
			}

			String speId = doctor.getTrainingSpeId();
			String secondspeId = doctor.getSecondSpeId();

			if(StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(speId)){
				SchRotationExample example = new SchRotationExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSpeIdEqualTo(speId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andDoctorCategoryIdEqualTo(trainingType);
				example.setOrderByClause("create_time desc");

				List<SchRotation> list = rotationMapper.selectByExample(example);
				if(list!=null && list.size()>0){
				    // 判断如果是全科 使用2020年新培训方案 需求1453
                    if("50".equals(speId)){
                        for (SchRotation schRotation: list ) {
                            if(schRotation.getRotationName().contains("2020西医助理")){
                                rotation = schRotation;
                                break;
                            }
                        }
                    } else {
//                        rotation = list.get(0);
						ResOrgRotationCfg rotationCfg = getRotationCfg(doctor.getOrgFlow(), speId, doctor.getSessionNumber());
						rotation = readSchRotation(rotationCfg.getRotationFlow());
                    }
				}
			}
			SysUser sysUser=new SysUser();
			sysUser.setUserFlow(doctor.getDoctorFlow());
			if(rotation!=null){
				doctor.setRotationFlow(rotation.getRotationFlow());
				doctor.setRotationName(rotation.getRotationName());
				sysUser.setMedicineTypeId(rotation.getRotationTypeId());
				sysUser.setMedicineTypeName(rotation.getRotationTypeName());
			}else{
				doctor.setRotationFlow("");
				doctor.setRotationName("");
				sysUser.setMedicineTypeId("");
				sysUser.setMedicineTypeName("");
			}

			userBiz.updateUser(sysUser);
			SchRotation rotation2 = null;
			if(StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(secondspeId)){
				SchRotationExample example = new SchRotationExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSpeIdEqualTo(secondspeId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				example.setOrderByClause("create_time desc");

				List<SchRotation> list = rotationMapper.selectByExample(example);
				if(list!=null && list.size()>0){
					rotation2 = list.get(0);
				}
			}
			if(rotation2!=null){
				doctor.setSecondRotationFlow(rotation2.getRotationFlow());
				doctor.setSecondRotationName(rotation2.getRotationName());
			}else{
				doctor.setSecondRotationFlow("");
				doctor.setSecondRotationName("");
			}
		}
		return doctor;
	}

	/**
	 *  加载轮转方案（如果是对指定机构展示，则只显示本机构的）
	 * @param rotationList 所有轮转方案列表
	 * @param orgFlow 当前机构流水
	 * @return
	 */
	@Override
	public List<SchRotation> schRotations(List<SchRotation> rotationList,String orgFlow) {
		List<SchRotation> rotations=new ArrayList<SchRotation>();
		for (SchRotation schRotation : rotationList) {
			//如果是对指定机构展示，则只显示本机构的；否则直接显示
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(schRotation.getIsOrgView())) {
				//根据轮转方案流水和机构流水查询方案机构关联表
				List<ResRotationOrg> resRotationOrgs=iResRotationOrgBiz.searchByRotationFlowOrg(schRotation.getRotationFlow(),orgFlow);
				if (resRotationOrgs!=null&&resRotationOrgs.size()>0) {
					rotations.add(schRotation);
				}
			}else{
				rotations.add(schRotation);
			}
		}
		return rotations;
	}

	@Override
    public SchRotation getRotationByRecruit(com.pinde.core.model.ResDoctorRecruit recruit) {
		if(recruit!=null){
			SchRotation rotation = null;

			String trainingType = recruit.getCatSpeId();
            if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
				trainingType = RecDocCategoryEnum.Doctor.getId();
			}

			String speId = recruit.getSpeId();

			if(StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(speId)){
				SchRotationExample example = new SchRotationExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSpeIdEqualTo(speId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andDoctorCategoryIdEqualTo(trainingType);
				example.setOrderByClause("create_time desc");

				List<SchRotation> list = rotationMapper.selectByExample(example);
				if(list!=null && list.size()>0){
					rotation = list.get(0);
				}
			}

            return rotation;
		}
		return null;
	}

	@Override
	public int updateResDoctorSchProcessStatus(String doctorFlow) {
		return rotationMapper.updateResDoctorSchProcessStatus(doctorFlow);
	}

    @Override
    public int updateSchArrangeResultStatus(String doctorFlow) {
        return rotationMapper.updateSchArrangeResultStatus(doctorFlow);
    }

    @Override
    public int updateResrecStatus(String doctorFlow) {
        return rotationMapper.updateResrecStatus(doctorFlow);
    }

    @Override
    public int updateResSchProcessExpressStatus(String doctorFlow) {
        return rotationMapper.updateResSchProcessExpressStatus(doctorFlow);
    }

	@Override
    public SchRotation getRotationByRecruitNew(com.pinde.core.model.ResDoctorRecruit recruit) {
		if(recruit!=null){
			SchRotation rotation = null;

			String trainingType = recruit.getCatSpeId();
            if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
				trainingType = RecDocCategoryEnum.Doctor.getId();
			}

			String speId = recruit.getSpeId();

			if(StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(speId)){
				SchRotationExample example = new SchRotationExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSpeIdEqualTo(speId).andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andDoctorCategoryIdEqualTo(trainingType).andCreateTimeLessThan("2019");
				example.setOrderByClause("create_time desc");

				List<SchRotation> list = rotationMapper.selectByExample(example);
				if(list!=null && list.size()>0){
					rotation = list.get(0);
				}
			}

            return rotation;
		}
		return null;
	}

	@Override
	public SchRotation searchDoctorBySpeId(String speId) {
		SchRotationExample example = new SchRotationExample();
		Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorCategoryIdEqualTo("Doctor")
                .andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(speId)){
			criteria.andSpeIdEqualTo(speId);
		}
		example.setOrderByClause("CREATE_TIME DESC");
		List<SchRotation> list = rotationMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ResOrgRotationCfg getRotationCfg(String orgFlow, String speId, String sessionNumber) {
		ResOrgRotationCfgExample example = new ResOrgRotationCfgExample();
        example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andSpeIdEqualTo(speId).andSessionYearEqualTo(sessionNumber);
		List<ResOrgRotationCfg> rotationCfgList = rotationCfgMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(rotationCfgList)) {
			return rotationCfgList.get(0);
		}
		return new ResOrgRotationCfg();
	}

	@Override
	public int saveRotationCfg(ResOrgRotationCfg rotationCfg) {

		if (StringUtil.isNotBlank(rotationCfg.getRotationCfgFlow())) {
			GeneralMethod.setRecordInfo(rotationCfg, false);
			return rotationCfgMapper.updateByPrimaryKeySelective(rotationCfg);
		} else {
			rotationCfg.setRotationCfgFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(rotationCfg, true);
			return rotationCfgMapper.insert(rotationCfg);
		}
	}

	@Override
	public List<String> getBzDeptNameBySpe(String speId) {
		List<String> result = new ArrayList<>();
		SchRotation schRotation = searchDoctorBySpeId(speId);
		if (ObjectUtil.isEmpty(schRotation)) {
			return result;
		}
		List<SchRotationDept> list = rotationDeptBiz.searchSchRotationDept(schRotation.getRotationFlow());
		if (CollectionUtil.isEmpty(list)) {
			return result;
		}
		result = list.stream().map(SchRotationDept::getStandardDeptName).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<SchRotationDept> getAllBzDeptListBySpeId(String speId) {
		List<SchRotationDept> result = new ArrayList<>();
		SchRotation schRotation = searchDoctorBySpeId(speId);
		if (ObjectUtil.isEmpty(schRotation)) {
			return result;
		}
		result = rotationDeptBiz.searchSchRotationDept(schRotation.getRotationFlow());
		return result;
	}
}
