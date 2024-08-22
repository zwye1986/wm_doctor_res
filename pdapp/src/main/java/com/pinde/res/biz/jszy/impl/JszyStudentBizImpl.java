package com.pinde.res.biz.jszy.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.core.commom.enums.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jszy.IJszyAppBiz;
import com.pinde.res.biz.jszy.IJszyStudentBiz;
import com.pinde.res.dao.jszy.ext.*;
import com.pinde.res.model.jszy.mo.AnnualAssessmentExt;
import com.pinde.res.model.jszy.mo.ResGraduationAssessmentExt;
import com.pinde.res.model.jszy.mo.UploadFileForm;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class JszyStudentBizImpl implements IJszyStudentBiz {
	
	@Resource
	private JszySchArrangeResultExtMapper resultExtMapper;
	@Resource
	private SchArrangeResultMapper resultMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Resource
	private SchRotationDeptReqMapper reqMapper;
	@Resource
	private ResRecMapper recMapper;
	@Resource
	private SchRotationDeptMapper rotationDeptMapper;
	@Resource
	private IJszyAppBiz jszyAppBiz;
	@Resource
	private JszySchRotationDeptExtMapper rotationDeptExtMapper;
	@Resource
	private SchRotationGroupMapper rotationGroupMapper;
	@Resource
	private SchRotationMapper rotationMapper;
	@Autowired
	private ResDoctorSchProcessMapper  resDoctorProcessMapper;
	//读取学员的排班数据
	@Override
	public List<Map<String,Object>> searchResult(Map<String,Object> paramMap){
		return resultExtMapper.searchResult(paramMap);
	}
	
	//根据result获取peocess
	@Override
	public ResDoctorSchProcess getProcessByResult(String resultFlow){
		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			ResDoctorSchProcessExample.Criteria criteria = example.createCriteria()
					.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> processes = processMapper.selectByExample(example);
			if(processes!=null && !processes.isEmpty()){
				return processes.get(0);
			}
		}
		return null;
	}

	@Override
	public SchRotationDept readRotationDept(String rotationDeptFlow){
		return rotationDeptMapper.selectByPrimaryKey(rotationDeptFlow);
	}

	@Override
	public SchRotationGroup readRotationGroup(String groupFlow){
		return rotationGroupMapper.selectByPrimaryKey(groupFlow);
	}

	@Override
	public SchRotation readRotation(String rotationFlow){
		return rotationMapper.selectByPrimaryKey(rotationFlow);
	}
	/************************************** 计算登记百分比START****************************************************/
	//要求标识
	final static private String REQ_NUM = "ReqNum";
	//完成数标识
	final static private String FINISHED = "Finished";
	//审核数标识
	final static private String AUDIT = "Audit";
	//子项列表标识
	final static private String IREM_IDS = "itemIds";

	//get方法实现
	private <T> T getMethod(String prop,Object obj) throws Exception{
		Class clazz = obj.getClass();
		T value = null;
		String firstWord = prop.substring(0,1);
		firstWord = firstWord.toUpperCase();
		String surplusWords = "";
		if(prop.length()>1){
			surplusWords = prop.substring(1);
		}
		String methodName = "get"+firstWord+surplusWords;
		Method method = clazz.getMethod(methodName);
		value = (T)method.invoke(obj);
		return value;
	}

	//组织key
	private String groupTheKey(Object obj,String[] keys){
		String key = "";
		for(String s : keys){
			String val = "";
			try {
				val = getMethod(s,obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			key+=val;
		}
		return key;
	}

	//以对象本身的属性作为key组织一个map 1-1/N
	private <T,E> Map<String,T> transListObjToMapsSoM(boolean isMany,List<E> objs,String ...keys){
		if(objs!=null && !objs.isEmpty() && keys!=null && keys.length>0){
			Map<String,T> map = new HashMap<String, T>();
			for(E e : objs){
				String key = groupTheKey(e,keys);
				if(isMany){
					List<E> es = (ArrayList<E>)map.get(key);
					if(es==null){
						es = new ArrayList<E>();
					}
					es.add(e);
					map.put(key,(T)es);
				}else{
					map.put(key,(T)e);
				}
			}
			return map;
		}
		return null;
	}

	//两数相除且不能大于1
	private float getPerResult(float a,float b){
		if(a<=0){
			return 0;
		}
		if(b<=0){
			return 0;
		}
		float result = a/b;
		if(result>1){
			result = 1;
		}
		return result;
	}

	//为一个Map<String,Float> 类型的map设置
	private <T> void setVal4MapStrFloat(Map<String,T> numMap,String key,Float num){
		if(numMap!=null){
			if(numMap.get(key)==null){
				numMap.put(key,(T)num);
			}else{
				Float v = (Float)numMap.get(key) + num;
				numMap.put(key,(T)v);
			}
		}
	}

	//取出map内的数字默认0
	private Float getFdefaultZero(Map<String,Float> map,String key){
		Float result = 0f;
		if(map!=null){
			Float v = map.get(key);
			if(v!=null){
				result = v;
			}
		}
		return result;
	}

	//把值塞进map
	private void setVal4PerMap(Map<String,Object> perMap,String key,Float val,int format){
		if(perMap!=null){
			BigDecimal b = new BigDecimal(val).setScale(format,BigDecimal.ROUND_HALF_UP);
			perMap.put(key,b.toString());
		}
	}

	//将要求绑定给result
	private Map<String,Object> bind4RotationDept(Map<String, SchRotationDept> rotationDeptMap, List<SchRotationDeptReq> reqs, Map<String, SchDoctorDept> doctorDeptMap, boolean isReduction, List<SchArrangeResult> results){
		if(rotationDeptMap!=null && !rotationDeptMap.isEmpty() && reqs!=null && !reqs.isEmpty()){

			Map<String,List<SchRotationDeptReq>> reqsMap = transListObjToMapsSoM(true,reqs,"relRecordFlow");

			Map<String,Object> reqMap = new HashMap<String, Object>();
			for(String key : rotationDeptMap.keySet()){
				SchRotationDept srd = rotationDeptMap.get(key);
				double per=1;
				if(isReduction)
				{
					SchDoctorDept sdd=doctorDeptMap.get(key);
					if(sdd!=null&&srd!=null)
					{
						if(GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
							per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(srd.getSchMonth());
							srd.setSchMonth(sdd.getSchMonth());
						}
					}
				}
				if(srd!=null){
					String relRecordFlow = srd.getRecordFlow();
					List<SchRotationDeptReq> rs = reqsMap.get(relRecordFlow);

					if(rs!=null && !rs.isEmpty()){
						for(SchRotationDeptReq srdr : rs){
							if(srdr!=null){
//								setVal4MapStrFloat(reqMap,"all",srdr.getReqNum().floatValue());

								float num= (float)Math.ceil(srdr.getReqNum().floatValue()*per);
								srdr.setReqNum(new BigDecimal(num));
								setVal4MapStrFloat(reqMap,key,num);

								String recTypeId = srdr.getRecTypeId();
								setVal4MapStrFloat(reqMap,key+recTypeId,num);

								String itemId = srdr.getItemId();
								if(StringUtil.isNotBlank(itemId)){
									setVal4MapStrFloat(reqMap,key+recTypeId+itemId,num);

									List<String> itemIds = (List<String>)reqMap.get(key+recTypeId+IREM_IDS);
									if(itemIds==null){
										itemIds = new ArrayList<String>();
										reqMap.put(key+recTypeId+IREM_IDS,itemIds);
									}
									itemIds.add(itemId);
								}
							}
						}
					}
				}
			}
			if(results!=null)
			{
				for(SchArrangeResult sar:results)
				{
					String groupFlow = sar.getStandardGroupFlow();
					String standardDeptId = sar.getStandardDeptId();
					String globalUpKey = groupFlow+standardDeptId;
					String key = groupFlow+standardDeptId+sar.getResultFlow();
					SchRotationDept srd = rotationDeptMap.get(globalUpKey);
					SchRotation rotation=readRotation(sar.getRotationFlow());

					if(srd!=null){
						double per=1.0;
						//是否翻倍
						if(rotation!=null&&GlobalConstant.FLAG_Y.equals(rotation.getIsDouble())&&StringUtil.isNotBlank(srd.getSchMonth()))
						{
							if(StringUtil.isNotBlank(sar.getSchMonth()))
							{
								per=Double.valueOf(sar.getSchMonth())/Double.valueOf(srd.getSchMonth())*per;
							}
						}
						String relRecordFlow = srd.getRecordFlow();
						List<SchRotationDeptReq> rs = reqsMap.get(relRecordFlow);

						if(rs!=null && !rs.isEmpty()){
							for(SchRotationDeptReq srdr : rs){
								if(srdr!=null){
									float num= (float)Math.ceil(srdr.getReqNum().floatValue()*per);
									setVal4MapStrFloat(reqMap,key,num);

									String recTypeId = srdr.getRecTypeId();
									setVal4MapStrFloat(reqMap,key+recTypeId,num);

									String itemId = srdr.getItemId();
									if(StringUtil.isNotBlank(itemId)){
										setVal4MapStrFloat(reqMap,key+recTypeId+itemId,num);

										List<String> itemIds = (List<String>)reqMap.get(key+recTypeId+IREM_IDS);
										if(itemIds==null){
											itemIds = new ArrayList<String>();
											reqMap.put(key+recTypeId+IREM_IDS,itemIds);
										}
										itemIds.add(itemId);
									}
								}
							}
						}
					}

				}
			}
			return reqMap;
		}
		return null;
	}

	//将登记数据绑定给result
	private Map<String,Float> bindRec4Process(List<ResRec> recs){
		if(recs!=null && !recs.isEmpty()){
			Map<String,Float> recMap = new HashMap<String, Float>();
			for(ResRec rr : recs){
				if(rr!=null){
//					setVal4MapStrFloat(recMap,"all",1f);
					String processFlow = rr.getProcessFlow();
					setVal4MapStrFloat(recMap,processFlow,1f);

//					ResDoctorSchProcess process=resDoctorProcessMapper.selectByPrimaryKey(processFlow);
//					if(process!=null){
//						SchArrangeResult result=resultMapper.selectByPrimaryKey(process.getSchResultFlow());
//						if(result!=null){
//
//						}
//					}
					String recTypeId = rr.getRecTypeId();
					setVal4MapStrFloat(recMap,processFlow+recTypeId,1f);

					String itemId = rr.getItemId();
					if(StringUtil.isNotBlank(itemId)){
						setVal4MapStrFloat(recMap,processFlow+recTypeId+itemId,1f);
					}

					String auditStatus = rr.getAuditStatusId();
					if(StringUtil.isNotBlank(auditStatus)){
						setVal4MapStrFloat(recMap,processFlow+AUDIT,1f);

						setVal4MapStrFloat(recMap,processFlow+recTypeId+AUDIT,1f);

						if(StringUtil.isNotBlank(itemId)){
							setVal4MapStrFloat(recMap,processFlow+recTypeId+itemId+AUDIT,1f);
						}
					}
				}
			}
			return recMap;
		}
		return null;
	}

	//获取医师轮转计划
	@Override
	public <T> T searcheDocResult(String doctorFlow,String resultFlow){
		if(StringUtil.isNotBlank(resultFlow)){
			return (T)resultMapper.selectByPrimaryKey(resultFlow);
		}
		if(StringUtil.isNotBlank(doctorFlow)){
			SchArrangeResultExample example = new SchArrangeResultExample();
			SchArrangeResultExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andDoctorFlowEqualTo(doctorFlow);
			return (T)resultMapper.selectByExample(example);
		}
		return null;
	}

	//获取医师的所有轮转信息
	private <T> T searchDocProcess(String doctorFlow,String processFlow){
		if(StringUtil.isNotBlank(processFlow)){
			return (T)processMapper.selectByPrimaryKey(processFlow);
		}
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			ResDoctorSchProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andUserFlowEqualTo(doctorFlow);
			return (T)processMapper.selectByExample(example);
		}
		return null;
	}

	//获取该医师的要求
	private List<SchRotationDeptReq> searchDocReq(String rotationFlow,String recordFlow,List<String> recTypeId,String itemId){
		SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
		SchRotationDeptReqExample.Criteria criteria = reqExample.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(rotationFlow)){
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		if(StringUtil.isNotBlank(recordFlow)){
			criteria.andRelRecordFlowEqualTo(recordFlow);
		}
		if(recTypeId!=null&&recTypeId.size()>0){
			criteria.andRecTypeIdIn(recTypeId);
		}
		if(StringUtil.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}

		return reqMapper.selectByExample(reqExample);
	}

	//获取该医师的登记信息
	private List<ResRec> searchDocRec(String operUserFlow, String processFlow, List<String> recTypeId, String itemId){
		if(!StringUtil.isNotBlank(operUserFlow)){
			return null;
		}
		ResRecExample example=new ResRecExample();
		ResRecExample.Criteria criteria = example
				.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOperUserFlowEqualTo(operUserFlow);
		if(StringUtil.isNotBlank(processFlow)){
			criteria.andProcessFlowEqualTo(processFlow);
		}
		if(recTypeId!=null&&recTypeId.size()>0){
			criteria.andRecTypeIdIn(recTypeId);
		}
		if(StringUtil.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}
		return recMapper.selectByExample(example);
	}
	
	//获取标准科室
	private <T> T getStandardDept(String recordFlow,String standardGroupFlow,String standardDeptId,String rotationFlow){
		if(StringUtil.isNotBlank(recordFlow)){
			return (T)rotationDeptMapper.selectByPrimaryKey(recordFlow);
		}
		
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		
		if(StringUtil.isNotBlank(standardGroupFlow)){
			criteria.andGroupFlowEqualTo(standardGroupFlow);
		}
		
		if(StringUtil.isNotBlank(standardDeptId)){
			criteria.andStandardDeptIdEqualTo(standardDeptId);
		}
		
		if(StringUtil.isNotBlank(rotationFlow)){
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		
		return (T)rotationDeptMapper.selectByExample(example);
	}
	
	/**获取该学员的轮转登记比例,完成数和要求
	 * f:float,i:int
	 * Map<doctorFlow,f> 整个轮转登记进度
	 * Map<doctorFlow+'reqNum',i> 整个轮转要求数
	 * Map<doctorFlow+'finished',i> 整个轮转完成数
	 * Map<processFlow/resultFlow,f> 当前科室登记进度
	 * Map<processFlow/resultFlow+'ReqNum',i> 当前科室要求数
	 * Map<processFlow/resultFlow+'Finished',i> 当前科室完成数
	 * Map<processFlow/resultFlow+recTypeId,f> 该科室某一登记类型登记进度
	 * Map<processFlow/resultFlow+recTypeId+'ReqNum',i> 该科室某一登记类型要求数
	 * Map<processFlow/resultFlow+recTypeId+'Finished',i> 该科室某一登记类型完成数
	 * Map<processFlow/resultFlow+recTypeId+itemId,f> 该科室某一登记类型某一子项登记进度
	 * Map<processFlow/resultFlow+recTypeId+itemId+'ReqNum',i> 该科室某一登记类型某一子项要求数
	 * Map<processFlow/resultFlow+recTypeId+itemId+'Finished',i> 该科室某一登记类型某一子项完成数
	 * */
	
	/**
	 * 获取这个人的所有科室类型等细分比例和完成数等
	 * @param format
	 * @param userFlow
	 * @return
	 */
	@Override
	public Map<String,Object> getRegPer(int format,String userFlow){
		return getRegPer(format,userFlow,null);
	}
	
	/**
	 * 获取该科室的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @return
	 */
	@Override
	public Map<String,Object> getRegPer(int format,String userFlow,String resultFlow){
		return getRegPer(format,userFlow,resultFlow,null);
	}
	
	/**
	 * 获取科室下某类型的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @return
	 */
	@Override
	public Map<String,Object> getRegPer(int format,String userFlow,String resultFlow,String recTypeId){
		return getRegPer(format,userFlow,resultFlow,recTypeId,null,true);
	}
	
	/**
	 * 获取可是下某子项的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @param itemId
	 * @param overMethod
	 * @return
	 */
	@Override
	public Map<String,Object> getRegPer(
			int format,
			String userFlow,
			String resultFlow,
			String recTypeId,
			String itemId,
			boolean overMethod
			){
		//获取学员信息
		ResDoctor doctor = null;
		SysUser user = null;
		if(StringUtil.isNotBlank(userFlow)){
			doctor = jszyAppBiz.readResDoctor(userFlow);
			if(doctor==null){
				return null;
			}
			user = jszyAppBiz.readSysUser(userFlow);
			if(user==null){
				return null;
			}
		}
		
		//轮转方案
		String rotationFlow = doctor.getRotationFlow();
		String secondRotationFlow = doctor.getSecondRotationFlow();
		
		//是否查询某一科室下的数据,在某一科室下recTypeId,itemId等参数才有效
		boolean onlyOneDept = StringUtil.isNotBlank(resultFlow);
		
		//轮转科室
		List<SchArrangeResult> results = null;
		List<ResDoctorSchProcess> processes = null;
		List<SchRotationDept> rotationDeptList = new ArrayList<>();
		String relRecordFlow = null;
		String processFlow = null;
		if(onlyOneDept){
			SchArrangeResult result = searcheDocResult(null,resultFlow);
			results = new ArrayList<SchArrangeResult>();
			results.add(result);
			
			ResDoctorSchProcess process = getProcessByResult(resultFlow);
			processes = new ArrayList<ResDoctorSchProcess>();
			processes.add(process);
			if(process!=null){
				processFlow = process.getProcessFlow();
			}
			
			if(result!=null){
				String grouoFlow = result.getStandardGroupFlow();
				String standardDeptId = result.getStandardDeptId();
				rotationDeptList = getStandardDept(null,grouoFlow, standardDeptId,null);
				
				if(rotationDeptList!=null && !rotationDeptList.isEmpty()){
					SchRotationDept rotationDept = rotationDeptList.get(0);
					if(rotationDept!=null){
						relRecordFlow = rotationDept.getRecordFlow();
					}
				}
			}
		}else{
			//非固定科室不定位带类型或子项,可取消
			recTypeId = null;
			itemId = null;
			
			results = searcheDocResult(userFlow,null);
			
			processes = searchDocProcess(userFlow,null);

			List<SchRotationDept> rotationDeptList1 = getStandardDept(null,null,null,rotationFlow);
			rotationDeptList.addAll(rotationDeptList1);
			if(StringUtil.isNotBlank(secondRotationFlow)) {
				List<SchRotationDept> rotationDeptList2 = getStandardDept(null, null, null, secondRotationFlow);
				rotationDeptList.addAll(rotationDeptList2);
			}
		}
		
		//过程数据,跟排班相关联
		Map<String,ResDoctorSchProcess> processMap = transListObjToMapsSoM(false,processes,"schResultFlow");
		
		//获取标准科室列表以绑定要求数据
		Map<String,SchRotationDept> rotationDeptMap = transListObjToMapsSoM(false,rotationDeptList,"groupFlow","standardDeptId");

		//判断学员是否需要减免
		String trainingType = doctor.getTrainingTypeId();
		String sessionNumber = doctor.getSessionNumber();
		String trainingYears = doctor.getTrainingYears();
		boolean isReduction = JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
		isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
		isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));
		Map<String,SchDoctorDept> doctorDeptMap=new HashMap<>();
		if(isReduction)
		{
			//获取所有缩减的调整科室
			List<SchDoctorDept> doctorDeptList = jszyAppBiz.searchReductionDept(userFlow,rotationFlow,secondRotationFlow);
			if(doctorDeptList!=null&&doctorDeptList.size()>0)
			{
				//获取标准科室列表以绑定要求数据
				doctorDeptMap = transListObjToMapsSoM(false,doctorDeptList,"groupFlow","standardDeptId");
			}
		}

		//通过登记数据获取所有登记过的类型
		List<String> recTypeIds = new ArrayList<String>();
		if(StringUtil.isNotBlank(recTypeId)){
			recTypeIds.add(recTypeId);
		}else{
			for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(GlobalUtil.getSysCfg("res_registry_type_"+regType.getId()))){
					if(GlobalUtil.findChineseOrWestern(user.getMedicineTypeId(),regType.getId())) {
						recTypeIds.add(regType.getId());
					}
				}
			}
		}

		//要求

		List<SchRotationDeptReq> reqs=new ArrayList<>();
		List<SchRotationDeptReq> reqs1 = searchDocReq(rotationFlow, relRecordFlow, recTypeIds, itemId);
		reqs.addAll(reqs1);
		if(StringUtil.isNotBlank(secondRotationFlow)) {
			List<SchRotationDeptReq> reqs2 = searchDocReq(secondRotationFlow, relRecordFlow, recTypeIds, itemId);
			reqs.addAll(reqs2);
		}
		
		//通过标准科室将要求绑定至result 并按减免比例减少
		Map<String,Object> reqAndItemsMap = bind4RotationDept(rotationDeptMap,reqs,doctorDeptMap,isReduction,results);
		Map<String,Float> reqMap = null;
		Map<String,List<String>> itemIdsMap = null;
		if(reqAndItemsMap!=null && !reqAndItemsMap.isEmpty()){
			reqMap = new HashMap<String, Float>();
			itemIdsMap = new HashMap<String, List<String>>();
			for(String key : reqAndItemsMap.keySet()){
				Object o = reqAndItemsMap.get(key);
				if(o instanceof List){
					itemIdsMap.put(key,(List<String>)o);
				}else{
					reqMap.put(key,(Float)o);
				}
			}
		}
		
		
		//登记的数据列表
		List<ResRec> recs = searchDocRec(userFlow,processFlow,recTypeIds,itemId);
		
		//登记数据统计
		Map<String,Float> recMap = bindRec4Process(recs);

		return getRegPer(format,overMethod,results,processMap,reqMap,itemIdsMap,recMap,recTypeIds);
	}
	
	/**
	 * 获取可是下某子项的比例及完成数等计算
	 * @param format
	 * @param overMethod
	 * @param results
	 * @param processMap
	 * @param reqMap
	 * @param itemIdsMap
	 * @param finishedMap
	 * @param resTypes
	 * @return
	 */
	@Override
	public Map<String,Object> getRegPer(
			int format,
			boolean overMethod,
			List<SchArrangeResult> results,
			Map<String,ResDoctorSchProcess> processMap,
			Map<String,Float> reqMap,
			Map<String,List<String>> itemIdsMap,
			Map<String,Float> finishedMap,
			List<String> resTypes
			){
		//省略位数不能小于0
		format = format<0?0:format;

		//验证必要参数的可用性
		if(results==null || results.isEmpty()){
			return null;
		}
		if(processMap==null || processMap.isEmpty()){
			return null;
		}
		if(resTypes==null || resTypes.isEmpty()){
			return null;
		}
		
		//开始计算百分比
		Map<String,Object> progressMap = new HashMap<String, Object>();
		for(SchArrangeResult sar : results){
			if(sar!=null){
				String resultFlow = sar.getResultFlow();
				String groupFlow = sar.getStandardGroupFlow();
				String standardDeptId = sar.getStandardDeptId();
				String globalUpKey = groupFlow+standardDeptId;
				SchRotation rotation=readRotation(sar.getRotationFlow());
				//是否翻倍
				if(rotation!=null&&GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()))
				{
					globalUpKey=globalUpKey+sar.getResultFlow();
				}
				ResDoctorSchProcess rdsp = processMap.get(resultFlow);
				if(rdsp!=null){
					String processFlow = rdsp.getProcessFlow();
					
					//获取审核情况
					Float deptAudit = getFdefaultZero(finishedMap,processFlow+AUDIT);
					setVal4PerMap(progressMap,processFlow+AUDIT,deptAudit,0);
					setVal4PerMap(progressMap,resultFlow+AUDIT,deptAudit,0);
					
					//为当前科室绑定数据
					Float deptFinish = getFdefaultZero(finishedMap,processFlow);
					setVal4PerMap(progressMap,processFlow+FINISHED,deptFinish,0);
					setVal4PerMap(progressMap,resultFlow+FINISHED,deptFinish,0);
					
					//当前科室要求数
					Float deptReq = getFdefaultZero(reqMap,globalUpKey);
					setVal4PerMap(progressMap,processFlow+REQ_NUM,deptReq,0);
					setVal4PerMap(progressMap,resultFlow+REQ_NUM,deptReq,0);
					
					//类型总比
					Float deptPer = 0f;
					
					//计算类型下的比例
					for(String typeId : resTypes){
						String typeKeyp = processFlow+typeId;
						String typeKeyr = resultFlow+typeId;
						String typeKeygs = globalUpKey+typeId;
						
						//获取审核情况
						Float typeAudit = getFdefaultZero(finishedMap,typeKeyp+AUDIT);
						setVal4PerMap(progressMap,typeKeyp+AUDIT,typeAudit,0);
						setVal4PerMap(progressMap,typeKeyr+AUDIT,typeAudit,0);
						
						//该科室下该类型的完成数
						Float typeFinish = getFdefaultZero(finishedMap,typeKeyp);
						setVal4PerMap(progressMap,typeKeyp+FINISHED,typeFinish,0);
						setVal4PerMap(progressMap,typeKeyr+FINISHED,typeFinish,0);
						
						//该科室下该类型的要求数
						Float typeReq = getFdefaultZero(reqMap,typeKeygs);
						setVal4PerMap(progressMap,typeKeyp+REQ_NUM,typeReq,0);
						setVal4PerMap(progressMap,typeKeyr+REQ_NUM,typeReq,0);
						
						//该类型占总要求的比例
						Float typeReqPer = getPerResult(typeReq,deptReq);
						
						//子项总比
						Float typePer = 0f;
						
						//子项列表
						List<String> itemIds = null;
						if(itemIdsMap!=null){
							itemIds = itemIdsMap.get(typeKeygs+IREM_IDS);
						}
						if(itemIds!=null && !itemIds.isEmpty()){
							Float itemTypePer = 0f;
							Float allItemReq=0f;
							Float allItemFin=0f;
							for(String itemId : itemIds){
								String itemKeyp = typeKeyp+itemId;
								String itemKeyr = typeKeyr+itemId;
								String itemKeygs = typeKeygs+itemId;
								
								//获取审核情况
								Float itemAudit = getFdefaultZero(finishedMap,itemKeyp+AUDIT);
								setVal4PerMap(progressMap,itemKeyp+AUDIT,itemAudit,0);
								setVal4PerMap(progressMap,itemKeyr+AUDIT,itemAudit,0);
								
								//该科室下该类型下该子项的完成数
								Float itemFinish = getFdefaultZero(finishedMap,itemKeyp);
								setVal4PerMap(progressMap,itemKeyp+FINISHED,itemFinish,0);
								setVal4PerMap(progressMap,itemKeyr+FINISHED,itemFinish,0);
								allItemFin+=itemFinish;

								//该科室下该类型下该子项的要求数
								Float itemReq = getFdefaultZero(reqMap,itemKeygs);
								setVal4PerMap(progressMap,itemKeyp+REQ_NUM,itemReq,0);
								setVal4PerMap(progressMap,itemKeyr+REQ_NUM,itemReq,0);
								allItemReq+=itemReq;
								
								//该子项占类型的比例
								Float iremReqPerInTypePer = getPerResult(itemReq,typeReq)*typeReqPer;
								
								//该子项的比例
								Float iremReqPer = getPerResult(itemReq,typeReq);
								
								//该子项的完成比例
								Float itemPer = getPerResult(itemFinish,itemReq);
								
								//无要求处理
								if(overMethod){
									if(itemReq==0){
										itemPer = 1f;
									}
								}
								//该子项占比
								setVal4PerMap(progressMap,itemKeyp,itemPer*100,format);
								setVal4PerMap(progressMap,itemKeyr,itemPer*100,format);
								
								//当前类型总比
								typePer+=(itemPer*iremReqPerInTypePer);
								
								//当前类型下比例
								itemTypePer+=(itemPer*iremReqPer);
							}
//							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
//							if(allItemReq==0&&allItemFin>0)
//							{
//								typePer = 1f;
//							}
							//该类型的占比
							setVal4PerMap(progressMap,typeKeyp,itemTypePer*100,format);
							setVal4PerMap(progressMap,typeKeyr,itemTypePer*100,format);
							
							//如果总要求数是0那么设置类型比例为100
							if(overMethod){
								if(typeReq==0){
									setVal4PerMap(progressMap,typeKeyp,1f*100,format);
									setVal4PerMap(progressMap,typeKeyr,1f*100,format);
								}
							}
						}else{
							//无子项单独计算
							Float typePerNoItem = getPerResult(typeFinish,typeReq);
							
							//无要求处理
							if(overMethod){
								if(typeReq==0){
									typePerNoItem = 1f;
								}
							}
							//该类型的占比
							setVal4PerMap(progressMap,typeKeyp,typePerNoItem*100,format);
							setVal4PerMap(progressMap,typeKeyr,typePerNoItem*100,format);
							
							//当前类型总比
							typePer+=(typePerNoItem*typeReqPer);
						}
						//当前类型总比
						deptPer+=typePer;
						
					}
					
					//无要求处理
					if(overMethod){
						if(deptReq==0){
							deptPer = 1f;
						}
					}
					//科室总比
					setVal4PerMap(progressMap,resultFlow,deptPer*100,format);
					setVal4PerMap(progressMap,processFlow,deptPer*100,format);
				}
			}
		}
		return progressMap;
	}
	/************************************** 计算登记百分比END****************************************************/

	@Override
	public List<Map<String,Object>> getDoctorRotationDept(Map<String,Object> paramMap){
		return rotationDeptExtMapper.getDoctorRotationDept(paramMap);
	}

	@Override
	public List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String resultFlow, String rotationFlow, String secondRotationFlow){
		List<SchArrangeResult> result = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			paramMap.put("startDate",startDate);
			paramMap.put("endDate",endDate);
			paramMap.put("resultFlow",resultFlow);
			paramMap.put("rotationFlow",rotationFlow);
			paramMap.put("secondRotationFlow",secondRotationFlow);
			result = resultExtMapper.checkResultDate(paramMap);
		}
		return result;
	}

	@Override
	public int editDoctorResult(
			String userFlow,
			String standardDeptFlow,
			String schDeptFlow,
			String schStartDate,
			String schEndDate,
			String teacherUserFlow,
			String headUserFlow,
			String deptFlow
	) throws ParseException {
		boolean isNew = deptFlow==null;
		SchArrangeResult result ;
		ResDoctorSchProcess process ;
		ResDoctor doctor = jszyAppBiz.readResDoctor(userFlow);
		if(isNew){
			result = new SchArrangeResult();
			process = new ResDoctorSchProcess();


			if(doctor!=null){
				result.setResultFlow(PkUtil.getUUID());
				result.setArrangeFlow(PkUtil.getUUID());
				result.setDoctorFlow(userFlow);
				result.setDoctorName(doctor.getDoctorName());
				result.setSessionNumber(doctor.getSessionNumber());
				result.setOrgFlow(doctor.getOrgFlow());
				result.setOrgName(doctor.getOrgName());
				result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				result.setCreateUserFlow(userFlow);
				result.setCreateTime(DateUtil.getCurrentTime());
				result.setModifyUserFlow(userFlow);
				result.setModifyTime(DateUtil.getCurrentTime());

				process.setProcessFlow(PkUtil.getUUID());
				process.setUserFlow(userFlow);
				process.setOrgFlow(doctor.getOrgFlow());
				process.setOrgName(doctor.getOrgName());
				process.setSchResultFlow(result.getResultFlow());
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_N);
				process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				process.setCreateUserFlow(userFlow);
				process.setCreateTime(DateUtil.getCurrentTime());
				process.setModifyUserFlow(userFlow);
				process.setModifyTime(DateUtil.getCurrentTime());

			}
		}else{
			result = searcheDocResult(null,deptFlow);
			result.setModifyUserFlow(userFlow);
			result.setModifyTime(DateUtil.getCurrentTime());

			process = getProcessByResult(deptFlow);
			if(process==null)
			{
				process=new ResDoctorSchProcess();
				process.setUserFlow(userFlow);
				process.setOrgFlow(doctor.getOrgFlow());
				process.setOrgName(doctor.getOrgName());
				process.setSchResultFlow(result.getResultFlow());
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_N);
				process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				process.setCreateUserFlow(userFlow);
				process.setCreateTime(DateUtil.getCurrentTime());
			}
			process.setModifyUserFlow(userFlow);
			process.setModifyTime(DateUtil.getCurrentTime());
		}

		SchRotationDept rotationDept = readRotationDept(standardDeptFlow);
		if(rotationDept!=null){
			Integer ordi = rotationDept.getOrdinal();
			BigDecimal ord = null;
			if(ordi!=null){
				ord = BigDecimal.valueOf(ordi);
			}
			result.setSchDeptOrder(ord);
			//result.setSchMonth(rotationDept.getSchMonth());
			result.setIsRequired(rotationDept.getIsRequired());
			result.setStandardDeptId(rotationDept.getStandardDeptId());
			result.setStandardDeptName(rotationDept.getStandardDeptName());
			result.setStandardGroupFlow(rotationDept.getGroupFlow());

			String rotationFlow = rotationDept.getRotationFlow();
			if(StringUtil.isNotBlank(rotationFlow)){
				SchRotation rotation = readRotation(rotationFlow);
				result.setRotationFlow(rotation.getRotationFlow());
				result.setRotationName(rotation.getRotationName());
				result.setSchYear(rotation.getRotationYear());
			}
		}

		SchDept schDept = jszyAppBiz.readSchDept(schDeptFlow);
		if(schDept!=null){
			result.setDeptFlow(schDept.getDeptFlow());
			result.setDeptName(schDept.getDeptName());
			result.setSchDeptFlow(schDeptFlow);
			result.setSchDeptName(schDept.getSchDeptName());

			process.setDeptFlow(schDept.getDeptFlow());
			process.setDeptName(schDept.getDeptName());
			process.setSchDeptFlow(schDeptFlow);
			process.setSchDeptName(schDept.getSchDeptName());
		}

		SysUser teacher = jszyAppBiz.readSysUser(teacherUserFlow);
		if(teacher!=null){
			process.setTeacherUserFlow(teacherUserFlow);
			process.setTeacherUserName(teacher.getUserName());
		}

		SysUser head = jszyAppBiz.readSysUser(headUserFlow);
		if(head!=null){
			process.setHeadUserFlow(headUserFlow);
			process.setHeadUserName(head.getUserName());
		}

		result.setSchStartDate(schStartDate);
		result.setSchEndDate(schEndDate);
		//读取是否开启自主增加轮转计划开关 res_custom_result_flag
		String isManualFlag = jszyAppBiz.getCfgByCode("res_custom_result_flag");
		//读取是否允许学员自己入科开关 res_custom_result_flag
		String isInBySelfFlag = jszyAppBiz.getCfgByCode("res_doc_in_by_self");
		if(GlobalConstant.FLAG_Y.equals(isInBySelfFlag)&&!GlobalConstant.FLAG_Y.equals(isManualFlag)){
			if(DateUtil.getCurrDate().compareTo(schStartDate)>=0) {
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
			}
		}
		String startDate = result.getSchStartDate();
		String endDate = result.getSchEndDate();
		process.setSchStartDate(startDate);
		process.setSchEndDate(endDate);
		//轮转计划单位
		String unit = jszyAppBiz.getCfgByCode("res_rotation_unit");
		//默认按月计算
		int step = 30;
		if (SchUnitEnum.Week.getId().equals(unit)) {
			//如果是周按7天算/没配置或者选择月按30天
			step = 7;
			BigDecimal realMonth = BigDecimal.valueOf(0);
			long realDays = DateUtil.signDaysBetweenTowDate(result.getSchEndDate(), result.getSchStartDate())+1;
			if (realDays != 0) {
				//计算实际轮转的月/周数
				double realMonthF = (realDays / (step * 1.0));
				realMonth = BigDecimal.valueOf(realMonthF);
				realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
			}
			String schMonth = String.valueOf(realMonth.doubleValue());
			result.setSchMonth(schMonth);
		}else{
			Map<String,String> map= new HashMap<>();
			map.put("startDate",result.getSchStartDate());
			map.put("endDate",result.getSchEndDate());
			Double month = TimeUtil.getMonthsBetween(map);
			String schMonth = String.valueOf(Double.parseDouble(month + ""));
			result.setSchMonth(schMonth);
		}

		process.setSchStartDate(schStartDate);
		process.setSchEndDate(schEndDate);
		process.setStartDate(schStartDate);
		process.setEndDate(schEndDate);

		if(isNew){
			resultMapper.insertSelective(result);
			processMapper.insertSelective(process);
		}else{
			resultMapper.updateByPrimaryKeySelective(result);
			if(StringUtil.isNotBlank(process.getProcessFlow())) {
				processMapper.updateByPrimaryKeySelective(process);
			}else{
				process.setProcessFlow(PkUtil.getUUID());
				processMapper.insertSelective(process);
			}
		}
		if(!GlobalConstant.FLAG_Y.equals(doctor.getSchFlag())||!GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag()))
		{
			doctor.setSchFlag(GlobalConstant.FLAG_Y);
			doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
			jszyAppBiz.editResDoctor(doctor);
		}
		return 1;
	}

	@Override
	public int delDoctorResult(String deptFlow){
		SchArrangeResult result = searcheDocResult(null,deptFlow);
		result.setModifyTime(DateUtil.getCurrentTime());
		result.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		resultMapper.updateByPrimaryKeySelective(result);

		ResDoctorSchProcess process = getProcessByResult(deptFlow);
		process.setModifyTime(DateUtil.getCurrentTime());
		process.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		processMapper.updateByPrimaryKeySelective(process);

		return 1;
	}

	@Override
	public int updateProcess(ResDoctorSchProcess process){
		if(process!=null && StringUtil.isNotBlank(process.getProcessFlow())){
			process.setModifyTime(DateUtil.getCurrentTime());
			return processMapper.updateByPrimaryKeySelective(process);
		}
		return 0;
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow);
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Autowired
	private ResDiscipleInfoMapper resDiscipleInfoMapper;
	@Autowired
	private ResDiscipleInfoExtMapper resDiscipleInfoExtMapper;
	@Autowired
	private ResDiscipleTeacherInfoMapper teacherInfoMapper;
	@Autowired
	private ResStudentDiscipleTeacherMapper resStudentDiscipleTeacherMapper;
	@Autowired
	private ResDiscipleRecordInfoMapper resDiscipleRecordInfoMapper;
	@Autowired
	private ResFollowTeacherRecordExtMapper resFollowTeacherRecordExtMapper;
	@Autowired
	private ResDiscipleNoteInfoMapper noteInfoMapper;
	@Autowired
	private ResGraduationAssessmentMapper graduationAssessmentMapper;
	@Autowired
	private ResAnnualAssessmentMapper annualAssessmentMapper;
	@Autowired
	private ResTypicalCasesMapper typicalCasesMapper;
	@Override
	public ResDiscipleInfo readResDiscipleInfo(String userFlow) {
		ResDiscipleInfoExample example=new ResDiscipleInfoExample();
		example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<ResDiscipleInfo>list= resDiscipleInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public String getTeacherName(String userFlow) {
		Map<String,String> map=resDiscipleInfoExtMapper.getTeacherNames(userFlow);
		if(map!=null)
		{
			return map.get("TEACHER_NAMES");
		}
		return null;
	}

	@Override
	public int savaResDiscipleInfo(ResDiscipleInfo bean,SysUser user) {

		if(StringUtil.isBlank(bean.getDiscipleFlow())) {
			bean.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			bean.setCreateUserFlow(user.getUserFlow());
			bean.setCreateTime(DateUtil.getCurrDateTime());
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			bean.setDiscipleFlow(PkUtil.getUUID());
			return resDiscipleInfoMapper.insert(bean);
		}else{
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			return resDiscipleInfoMapper.updateByPrimaryKeySelective(bean);
		}
	}
	@Override
	public ResDiscipleTeacherInfo readResDiscipleTeacherInfo(String userFlow) {
		ResDiscipleTeacherInfoExample example=new ResDiscipleTeacherInfoExample();
		example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<ResDiscipleTeacherInfo> list= teacherInfoMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int savaResDiscipleTeacherInfo(ResDiscipleTeacherInfo bean,SysUser user) {
		if(StringUtil.isBlank(bean.getRecordFlow())) {
			bean.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			bean.setCreateUserFlow(user.getUserFlow());
			bean.setCreateTime(DateUtil.getCurrDateTime());
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			bean.setRecordFlow(PkUtil.getUUID());
			return teacherInfoMapper.insert(bean);
		}else{
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			return teacherInfoMapper.updateByPrimaryKeySelective(bean);
		}
	}

	@Override
	public ResDiscipleTeacherInfo readResDiscipleTeacherInfoByFlow(String infoFlow) {
		return teacherInfoMapper.selectByPrimaryKey(infoFlow);
	}

	@Override
	public List<ResStudentDiscipleTeacher> searchResStudentDiscipleTeacher(ResStudentDiscipleTeacher resStudentDiscipleTeacher) {
		ResStudentDiscipleTeacherExample example = new ResStudentDiscipleTeacherExample();
		ResStudentDiscipleTeacherExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(resStudentDiscipleTeacher!=null){
			if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(resStudentDiscipleTeacher.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getDoctorName())){
				criteria.andDoctorNameLike("%"+resStudentDiscipleTeacher.getDoctorName()+"%");
			}
			if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getTeacherFlow())){
				criteria.andTeacherFlowEqualTo(resStudentDiscipleTeacher.getTeacherFlow());
			}

		}
		example.setOrderByClause("DOCTOR_NAME desc");
		return resStudentDiscipleTeacherMapper.selectByExample(example);
	}

	@Override
	public int saveResDiscipleRecordInfo(ResDiscipleRecordInfo resDiscipleRecordInfo,SysUser user) {
		if(resDiscipleRecordInfo!=null){
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getRecordFlow())){
				resDiscipleRecordInfo.setModifyUserFlow(user.getUserFlow());
				resDiscipleRecordInfo.setModifyTime(DateUtil.getCurrDateTime());
				return resDiscipleRecordInfoMapper.updateByPrimaryKeySelective(resDiscipleRecordInfo);
			}else{
				resDiscipleRecordInfo.setRecordFlow(PkUtil.getUUID());
				resDiscipleRecordInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				resDiscipleRecordInfo.setCreateUserFlow(user.getUserFlow());
				resDiscipleRecordInfo.setCreateTime(DateUtil.getCurrDateTime());
				resDiscipleRecordInfo.setModifyUserFlow(user.getUserFlow());
				resDiscipleRecordInfo.setModifyTime(DateUtil.getCurrDateTime());
				return resDiscipleRecordInfoMapper.insert(resDiscipleRecordInfo);
			}
		}
		return 0;
	}

	@Override
	public List<ResDiscipleRecordInfo> searchFolowTeacherRecord(ResDiscipleRecordInfo resDiscipleRecordInfo) {
		ResDiscipleRecordInfoExample example = new ResDiscipleRecordInfoExample();
		ResDiscipleRecordInfoExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(resDiscipleRecordInfo!=null){
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(resDiscipleRecordInfo.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getTeacherFlow())){
				criteria.andTeacherFlowEqualTo(resDiscipleRecordInfo.getTeacherFlow());
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getRecordFlow())){
				criteria.andRecordFlowEqualTo(resDiscipleRecordInfo.getRecordFlow());
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusId())&& DiscipleStatusEnum.Submit.getId().equals(resDiscipleRecordInfo.getAuditStatusId())){
				List<String> values= new ArrayList<>();
				values.add(DiscipleStatusEnum.Submit.getId());
				values.add(DiscipleStatusEnum.DiscipleAudit.getId());
				values.add(DiscipleStatusEnum.DiscipleBack.getId());
				values.add(DiscipleStatusEnum.AdminAudit.getId());
				values.add(DiscipleStatusEnum.AdminBack.getId());
				values.add(DiscipleStatusEnum.PendingAudit.getId());
				values.add(DiscipleStatusEnum.Qualified.getId());
				values.add(DiscipleStatusEnum.UnQualified.getId());
				criteria.andAuditStatusIdIn(values);
				resDiscipleRecordInfo.setAuditStatusId("");
				resDiscipleRecordInfo.setAuditStatusName("");
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusId())){
				criteria.andAuditStatusIdEqualTo(resDiscipleRecordInfo.getAuditStatusId());
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusName())){
				criteria.andAuditStatusNameEqualTo(resDiscipleRecordInfo.getAuditStatusName());
			}
			if(StringUtil.isNotBlank(resDiscipleRecordInfo.getDoctorName())){
				criteria.andDoctorNameLike("%"+resDiscipleRecordInfo.getDoctorName()+"%");
			}
		}
		example.setOrderByClause("CREATE_TIME desc");
		return resDiscipleRecordInfoMapper.selectByExample(example);
	}

	@Override
	public List<ResDiscipleRecordInfo> searchFolowTeacherRecordByType(String userFlow, String typeId) {
		return resFollowTeacherRecordExtMapper.searchFolowTeacherRecordByType(userFlow,typeId);
	}
	@Override
	public Map<String,Object> folowTeacherRecordFinishMap(String userFlow) {
		return resFollowTeacherRecordExtMapper.folowTeacherRecordFinishMap(userFlow);
	}
	@Override
	public Map<String,Object> discipleNoteInfoFinishMap(String userFlow,String noteTypeId) {
		return resFollowTeacherRecordExtMapper.discipleNoteInfoFinishMap(userFlow,noteTypeId);
	}

	@Override
	public Map<String,Object> typicalCasesFinishMap(String userFlow) {
		return resFollowTeacherRecordExtMapper.typicalCasesFinishMap(userFlow);
	}

	@Override
	public ResDiscipleRecordInfo readFollowTeacherRecord(String recordFlow) {
		return resDiscipleRecordInfoMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResDiscipleNoteInfo> findResDiscipleNoteInfo(ResDiscipleNoteInfo discipleNoteInfo,List<String> auditStatusList) {
		ResDiscipleNoteInfoExample example = new ResDiscipleNoteInfoExample();
		ResDiscipleNoteInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(discipleNoteInfo.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(discipleNoteInfo.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(discipleNoteInfo.getTeacherFlow())){
			criteria.andTeacherFlowEqualTo(discipleNoteInfo.getTeacherFlow());
		}
		if(StringUtil.isNotBlank(discipleNoteInfo.getStudyTimeId())){
			criteria.andStudyTimeIdEqualTo(discipleNoteInfo.getStudyTimeId());
		}
		if(StringUtil.isNotBlank(discipleNoteInfo.getNoteTypeId())){
			criteria.andNoteTypeIdEqualTo(discipleNoteInfo.getNoteTypeId());
		}
		if(StringUtil.isNotBlank(discipleNoteInfo.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(discipleNoteInfo.getAuditStatusId());
		}
		if(StringUtil.isNotBlank(discipleNoteInfo.getStudyStartDate())){
			criteria.andStudyStartDateEqualTo(discipleNoteInfo.getStudyStartDate());
		}
		if(null != auditStatusList && auditStatusList.size()>0){
			criteria.andAuditStatusIdIn(auditStatusList);
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return noteInfoMapper.selectByExample(example);
	}

	@Override
	public ResDiscipleNoteInfoWithBLOBs findResDiscipleNoteInfoWithBLOBs(String recordFlow) {
		return noteInfoMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int updateResDiscipleNoteInfoWithBLOBs(ResDiscipleNoteInfoWithBLOBs discipleNoteInfo, SysUser user, ResDiscipleNoteInfoWithBLOBs old, List<String> fileFlows, List<UploadFileForm> members) {
		int i=0;
		if(old!=null&&old.getRecordFlow().equals(discipleNoteInfo.getRecordFlow())){
			discipleNoteInfo.setModifyUserFlow(user.getUserFlow());
			discipleNoteInfo.setModifyTime(DateUtil.getCurrDateTime());
			i= noteInfoMapper.updateByPrimaryKeySelective(discipleNoteInfo);
		}else {
			discipleNoteInfo.setRecordFlow(PkUtil.getUUID());
			discipleNoteInfo.setCreateUserFlow(user.getUserFlow());
			discipleNoteInfo.setCreateTime(DateUtil.getCurrDateTime());
			discipleNoteInfo.setModifyUserFlow(user.getUserFlow());
			discipleNoteInfo.setModifyTime(DateUtil.getCurrDateTime());
			discipleNoteInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			i= noteInfoMapper.insertSelective(discipleNoteInfo);
		}
		if(i==1)
		{
			uploadFile(discipleNoteInfo.getRecordFlow(),discipleNoteInfo.getNoteTypeId(),fileFlows,members);
		}
		return i;
	}

	@Override
	public int delResDiscipleNoteInfo(String recordFlow) {
		ResDiscipleNoteInfo resDiscipleNoteInfo = new ResDiscipleNoteInfo();
		resDiscipleNoteInfo.setRecordFlow(recordFlow);
		resDiscipleNoteInfo.setRecordStatus(GlobalConstant.FLAG_N);
		return noteInfoMapper.updateByPrimaryKey(resDiscipleNoteInfo);
	}

	@Autowired
	private ResBookStudyRecordMapper bookStudyRecordMapper;

	@Override
	public List<ResBookStudyRecord> getBookStudyRecords(String doctorFlow,String teacherFlow) {
		ResBookStudyRecordExample example=new ResBookStudyRecordExample();
		ResBookStudyRecordExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
		if(StringUtil.isNotBlank(teacherFlow))
		{
			criteria.andTeacherFlowEqualTo(teacherFlow);
		}
		return bookStudyRecordMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public ResBookStudyRecord getBookStudyRecord(String recordFlow) {
		return bookStudyRecordMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int savaRecord(ResBookStudyRecord record,SysUser user) {
		ResBookStudyRecord old=getBookStudyRecord(record.getRecordFlow());
		if(old==null){
			record.setRecordFlow(PkUtil.getUUID());
			record.setCreateUserFlow(user.getUserFlow());
			record.setCreateTime(DateUtil.getCurrDateTime());
			record.setModifyUserFlow(user.getUserFlow());
			record.setModifyTime(DateUtil.getCurrDateTime());
			record.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return bookStudyRecordMapper.insert(record);
		}else{
			record.setModifyUserFlow(user.getUserFlow());
			record.setModifyTime(DateUtil.getCurrDateTime());
			return bookStudyRecordMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public ResGraduationAssessment findResGraduationAssessment(String recordFlow) {
		return graduationAssessmentMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public ResAnnualAssessment findResAnnualAssessment(String recordFlow) {
		return annualAssessmentMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public ResTypicalCases findTypicalCases(String recordFlow) {
		return typicalCasesMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResTypicalCases> searchTypicalCases(ResTypicalCases resTypicalCases) {
		ResTypicalCasesExample example = new ResTypicalCasesExample();
		ResTypicalCasesExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(resTypicalCases!=null){
			if(StringUtil.isNotBlank(resTypicalCases.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(resTypicalCases.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getTeacherFlow())){
				criteria.andTeacherFlowEqualTo(resTypicalCases.getTeacherFlow());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getRecordFlow())){
				criteria.andRecordFlowEqualTo(resTypicalCases.getRecordFlow());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusId())&& DiscipleStatusEnum.Submit.getId().equals(resTypicalCases.getAuditStatusId())){
				List<String> values= new ArrayList<>();
				values.add(DiscipleStatusEnum.Submit.getId());
				values.add(DiscipleStatusEnum.DiscipleAudit.getId());
				values.add(DiscipleStatusEnum.DiscipleBack.getId());
				values.add(DiscipleStatusEnum.AdminAudit.getId());
				values.add(DiscipleStatusEnum.AdminBack.getId());
				values.add(DiscipleStatusEnum.PendingAudit.getId());
				values.add(DiscipleStatusEnum.Qualified.getId());
				values.add(DiscipleStatusEnum.UnQualified.getId());
				criteria.andAuditStatusIdIn(values);
				resTypicalCases.setAuditStatusId("");
				resTypicalCases.setAuditStatusName("");
			}
			if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusId())){
				criteria.andAuditStatusIdEqualTo(resTypicalCases.getAuditStatusId());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusName())){
				criteria.andAuditStatusNameEqualTo(resTypicalCases.getAuditStatusName());
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return typicalCasesMapper.selectByExample(example);
	}
	@Override
	public List<ResTypicalCases> searchTypicalCases(ResTypicalCases resTypicalCases, List<String> auditStatusList) {
		ResTypicalCasesExample example = new ResTypicalCasesExample();
		ResTypicalCasesExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(resTypicalCases!=null){
			if(StringUtil.isNotBlank(resTypicalCases.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(resTypicalCases.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getTeacherFlow())){
				criteria.andTeacherFlowEqualTo(resTypicalCases.getTeacherFlow());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getRecordFlow())){
				criteria.andRecordFlowEqualTo(resTypicalCases.getRecordFlow());
			}
			if(auditStatusList!=null&&auditStatusList.size()>0){
				criteria.andAuditStatusIdIn(auditStatusList);
			}
			if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusId())){
				criteria.andAuditStatusIdEqualTo(resTypicalCases.getAuditStatusId());
			}
			if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusName())){
				criteria.andAuditStatusNameEqualTo(resTypicalCases.getAuditStatusName());
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return typicalCasesMapper.selectByExample(example);
	}

	@Override
	public int saveResTypicalCases(ResTypicalCases resTypicalCases, SysUser user, ResTypicalCases old, List<String> fileFlows, List<UploadFileForm> members) {
		if(resTypicalCases!=null){
			int i=0;
			if(old!=null){
				resTypicalCases.setModifyUserFlow(user.getUserFlow());
				resTypicalCases.setModifyTime(DateUtil.getCurrDateTime());
				i= typicalCasesMapper.updateByPrimaryKeyWithBLOBs(resTypicalCases);
			}else {
				resTypicalCases.setRecordFlow(PkUtil.getUUID());
				resTypicalCases.setCreateUserFlow(user.getUserFlow());
				resTypicalCases.setCreateTime(DateUtil.getCurrDateTime());
				resTypicalCases.setModifyUserFlow(user.getUserFlow());
				resTypicalCases.setModifyTime(DateUtil.getCurrDateTime());
				resTypicalCases.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				i= typicalCasesMapper.insert(resTypicalCases);
			}
			if(i==1)
			{
				uploadFile(resTypicalCases.getRecordFlow(),"TypicalCases",fileFlows,members);
			}
			return i;
		}
		return 0;
	}
	@Override
	public int delResTypicalCases(ResTypicalCases resTypicalCases, SysUser user, ResTypicalCases old) {
		if(resTypicalCases!=null){
			if(old!=null){
				resTypicalCases.setModifyUserFlow(user.getUserFlow());
				resTypicalCases.setModifyTime(DateUtil.getCurrDateTime());
				return typicalCasesMapper.updateByPrimaryKeyWithBLOBs(resTypicalCases);
			}else {
				resTypicalCases.setRecordFlow(PkUtil.getUUID());
				resTypicalCases.setCreateUserFlow(user.getUserFlow());
				resTypicalCases.setCreateTime(DateUtil.getCurrDateTime());
				resTypicalCases.setModifyUserFlow(user.getUserFlow());
				resTypicalCases.setModifyTime(DateUtil.getCurrDateTime());
				resTypicalCases.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				return typicalCasesMapper.insert(resTypicalCases);
			}

		}
		return 0;
	}

	@Override
	public ResTypicalCases findResTypicalCases(String caseFlow) {
		return typicalCasesMapper.selectByPrimaryKey(caseFlow);
	}

	@Autowired
	private AnnualAssessmentExtMapper assessmentExtMapper;

	@Override
	public AnnualAssessmentExt initAnnualAssessmentExt(ResAnnualAssessment assessment) {
		return assessmentExtMapper.initAnnualAssessmentExt(assessment);
	}

	@Override
	public List<ResAnnualAssessment> findAnnualAssessmentList(ResAnnualAssessment assessment,List<String> statusIdList) {
		ResAnnualAssessmentExample example = new ResAnnualAssessmentExample();
		ResAnnualAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(assessment.getDoctorFlow())) {
			criteria.andDoctorFlowEqualTo(assessment.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(assessment.getTeacherFlow())) {
			criteria.andTeacherFlowEqualTo(assessment.getTeacherFlow());
		}
		if(StringUtil.isNotBlank(assessment.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(assessment.getAuditStatusId());
		}
		if(statusIdList != null && statusIdList.size()>0){
			criteria.andAuditStatusIdIn(statusIdList);
		}
		if(StringUtil.isNotBlank(assessment.getRecordYear())){
			criteria.andRecordYearEqualTo(assessment.getRecordYear());
		}
		example.setOrderByClause("DOCTOR_NAME,CREATE_TIME DESC");
		return annualAssessmentMapper.selectByExample(example);
	}

	@Override
	public int editAnnualAssessment(ResAnnualAssessmentWithBLOBs assessmentWithBLOBs, SysUser user, ResAnnualAssessment old, List<String> fileFlows, List<UploadFileForm> members) {
		int i=0;
		if (old!=null) {
			assessmentWithBLOBs.setModifyUserFlow(user.getUserFlow());
			assessmentWithBLOBs.setModifyTime(DateUtil.getCurrDateTime());
			i= annualAssessmentMapper.updateByPrimaryKeySelective(assessmentWithBLOBs);

		} else {
			assessmentWithBLOBs.setCompleleSignTime(DateUtil.getCurrDateTime());
			assessmentWithBLOBs.setExperienceSignTime(DateUtil.getCurrDateTime());
			assessmentWithBLOBs.setRecordFlow(PkUtil.getUUID());
			assessmentWithBLOBs.setCreateUserFlow(user.getUserFlow());
			assessmentWithBLOBs.setCreateTime(DateUtil.getCurrDateTime());
			assessmentWithBLOBs.setModifyUserFlow(user.getUserFlow());
			assessmentWithBLOBs.setModifyTime(DateUtil.getCurrDateTime());
			assessmentWithBLOBs.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			i= annualAssessmentMapper.insertSelective(assessmentWithBLOBs);
		}
		if(1==1) {
			uploadFile(assessmentWithBLOBs.getRecordFlow(), "AnnualAssessment", fileFlows, members);
		}
		return i;
	}

	@Override
	public int delAnnualAssessment(String recordFlow) {
		ResAnnualAssessmentWithBLOBs assessment = new ResAnnualAssessmentWithBLOBs();
		assessment.setRecordFlow(recordFlow);
		assessment.setRecordStatus("N");
		return annualAssessmentMapper.updateByPrimaryKeySelective(assessment);
	}
	@Autowired
	private ResGraduationAssessmentExtMapper graduationAssessmentExtMapper;

	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Override
	public ResGraduationAssessmentExt getDocGraduationAssessment(String userFlow) {
		return graduationAssessmentExtMapper.getDocGraduationAssessment(userFlow);
	}

	@Override
	public int save(ResGraduationAssessmentWithBLOBs bean, SysUser user, List<String> fileFlows, List<UploadFileForm> members) {
		int i=0;
		if(StringUtil.isBlank(bean.getRecordFlow())) {
			bean.setRecordFlow(PkUtil.getUUID());
			bean.setCreateUserFlow(user.getUserFlow());
			bean.setCreateTime(DateUtil.getCurrDateTime());
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			bean.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			i= graduationAssessmentMapper.insert(bean);
		}else{
			bean.setModifyUserFlow(user.getUserFlow());
			bean.setModifyTime(DateUtil.getCurrDateTime());
			i= graduationAssessmentMapper.updateByPrimaryKeySelective(bean);
		}
		if(i==1)
		{
			uploadFile(bean.getRecordFlow(),"GraduatAssess",fileFlows,members);
		}
		return i;
	}

	//base64字符串转化成图片
	public static boolean generateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) //图像数据为空
		{
			return false;
		}
		int lastIndex=savePath.lastIndexOf("/");
		if(lastIndex<=0)
			lastIndex=savePath.lastIndexOf("\\");
		String newDir = savePath.substring(0,lastIndex);
		File fileDir = new File(newDir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片//新生成的图片
			File imageFile = new File(savePath);
			OutputStream out = new FileOutputStream(imageFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	private void uploadFile(String recordFlow, String noteTypeId, List<String> fileFlows, List<UploadFileForm> members) {
		if(StringUtil.isNotBlank(recordFlow)&&StringUtil.isNotBlank(noteTypeId))
		{
			String baseDir=jszyAppBiz.getCfgByCode("upload_base_dir");
			upadteFileInfo(recordFlow,fileFlows);
			for(UploadFileForm fileForm:members)
			{
				if(StringUtil.isBlank(fileForm.getFileFlow())){
					String fileName=fileForm.getFileName();
					//定义上传路径
					String dateString = DateUtil.getCurrDate2();
					String newDir = baseDir+ File.separator + "discipleFiles" + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
					File fileDir = new File(newDir);
					if (!fileDir.exists()) {
						fileDir.mkdirs();
					}
					//重命名上传后的文件名
					String originalFilename = "";
					originalFilename = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
					try {
						generateImage(fileForm.getContent(),fileDir+  File.separator +originalFilename);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("保存文件失败！");
					}
					String filePath = File.separator + "discipleFiles" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;

					PubFile pubFile = new PubFile();
					pubFile.setFileFlow(fileForm.getFileFlow());
					pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					pubFile.setFilePath(filePath);
					pubFile.setFileName(fileName);
					pubFile.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
					pubFile.setProductType(noteTypeId);
					pubFile.setProductFlow(recordFlow);
					pubFileBiz.editFile(pubFile);
				}else{
					PubFile pubFile = new PubFile();
					pubFile.setFileFlow(fileForm.getFileFlow());
					pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					pubFile.setProductType(noteTypeId);
					pubFile.setProductFlow(recordFlow);
					pubFileBiz.editFile(pubFile);
				}

			}
		}

	}

	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
		PubFile pubFile=new PubFile();
		pubFile.setRecordStatus("N");
		PubFileExample example=new PubFileExample();
		PubFileExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProductFlowEqualTo(recordFlow);
		if(fileFlows!=null&&fileFlows.size()>0)
		{
			criteria.andFileFlowNotIn(fileFlows);
		}
		pubFileMapper.updateByExampleSelective(pubFile,example);
	}

	@Override
	public ResGraduationAssessmentWithBLOBs getGraduationAssessmentWithBlobByFlow(String recordFlow) {
		return graduationAssessmentMapper.selectByPrimaryKey(recordFlow);
	}
	@Override
	public ResGraduationAssessmentWithBLOBs findResGraduationAssessmentByDoctorFlow(String doctorFlow) {
		ResGraduationAssessmentExample example=new ResGraduationAssessmentExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		List<ResGraduationAssessmentWithBLOBs>list= graduationAssessmentMapper.selectByExampleWithBLOBs(example);
		if (list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int checkAnnualDate(String userFlow, String startTime, String endTime) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		paramMap.put("startDate",startTime);
		paramMap.put("endDate",endTime);
		return assessmentExtMapper.checkAnnualDate(paramMap);
	}

}
