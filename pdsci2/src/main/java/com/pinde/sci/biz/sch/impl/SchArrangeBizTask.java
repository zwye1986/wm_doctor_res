package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.enums.sch.SchArrangeStatusEnum;
import com.pinde.core.common.enums.sch.SchStageEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StatisticsUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.SchArrangeDoctorDeptMapper;
import com.pinde.sci.dao.base.SchArrangeMapper;
import com.pinde.sci.dao.base.SchArrangeResultMapper;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class SchArrangeBizTask {
	public int MaxSchDeptNums = 7;
	@Autowired
	private SchArrangeMapper arrangeMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SchArrangeDoctorDeptMapper arrangeDoctorDeptMapper;
	@Autowired
	private SchArrangeResultMapper schArrangeResultMapper;
	
	@Async
    public void processDoctor(SchArrange arrange, List<ResDoctor> doctorList, SysUser currUser, boolean exact) {
		_processDoctor(arrange, doctorList, currUser, exact);
	}
	
	//@Transactional(rollbackFor=Exception.class)
	private void _processDoctor(SchArrange arrange,List<ResDoctor> doctorList,SysUser currUser,boolean exact){
		for(int d=0;d<doctorList.size();d++){
			ResDoctor doctor = doctorList.get(d);

			//删除已经存在的排班
			SchArrangeResultExample example2 = new SchArrangeResultExample();
			example2.createCriteria().andDoctorFlowEqualTo(doctor.getDoctorFlow());//.andArrangeFlowNotEqualTo(arrangeFlow);
			
			SchArrangeResult resultUpdate = new SchArrangeResult();
            resultUpdate.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			schArrangeResultMapper.updateByExampleSelective(resultUpdate, example2);
			
			SchArrangeDoctorDeptExample exampleSdd = new SchArrangeDoctorDeptExample();
			exampleSdd.createCriteria().andArrangeFlowEqualTo(arrange.getArrangeFlow()).andDoctorFlowEqualTo(doctor.getDoctorFlow())
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			
			List<SchArrangeDoctorDept> arrangeDoctorDeptList = arrangeDoctorDeptMapper.selectByExample(exampleSdd);
			List<String> allSchDeptList = new LinkedList<String>();
			List<String> allSchDateList = new LinkedList<String>();
			double [][] old = _arrageDept(arrange, doctor,arrangeDoctorDeptList,allSchDeptList,allSchDateList, currUser,exact);
			if(d==0){
				SchArrange update = new SchArrange();
				update.setArrangeFlow(arrange.getArrangeFlow());
				update.setBeginIndex(StatisticsUtil.arrange(old)+"");
				arrangeMapper.updateByPrimaryKeySelective(update);
			}
			//获得医生所有的科室（必选+可选）
			List<SchArrangeDoctorDept> arrDocDeptList1 = new ArrayList<SchArrangeDoctorDept>();
			List<SchArrangeDoctorDept> arrDocDeptList2 = new ArrayList<SchArrangeDoctorDept>();
			List<SchArrangeDoctorDept> arrDocDeptList3 = new ArrayList<SchArrangeDoctorDept>();
			for(SchArrangeDoctorDept arraryDoctorDept : arrangeDoctorDeptList){
				//第一阶段
				if(StringUtil.isBlank(arraryDoctorDept.getSchStageId())){
					if(StringUtil.isNotBlank(arraryDoctorDept.getSchMonth())){
						arrDocDeptList1.add(arraryDoctorDept);
					}
				}
				if(SchStageEnum.FirstStage.getId().equals(arraryDoctorDept.getSchStageId())){
					if(StringUtil.isNotBlank(arraryDoctorDept.getSchMonth())){
						arrDocDeptList1.add(arraryDoctorDept);
					}
				}
				//第二阶段
				if(SchStageEnum.SecondStage.getId().equals(arraryDoctorDept.getSchStageId())){
					if(StringUtil.isNotBlank(arraryDoctorDept.getSchMonth())){
						arrDocDeptList2.add(arraryDoctorDept);
					}
				}
				//第三阶段
				if(SchStageEnum.ThirdStage.getId().equals(arraryDoctorDept.getSchStageId())){
					if(StringUtil.isNotBlank(arraryDoctorDept.getSchMonth())){
						arrDocDeptList3.add(arraryDoctorDept);
					}
				}
			}
			
			List<List<SchArrangeDoctorDept>> allArrDocDeptListList  =new ArrayList<List<SchArrangeDoctorDept>>();
			allArrDocDeptListList.addAll(_splitArrDept(arrDocDeptList1));
			allArrDocDeptListList.addAll(_splitArrDept(arrDocDeptList2));
			allArrDocDeptListList.addAll(_splitArrDept(arrDocDeptList3));

			String schStartDate = arrange.getBeginDate();
			int startInterval = 0;
			for(Iterator<List<SchArrangeDoctorDept>> it = allArrDocDeptListList.iterator();it.hasNext();){
				List<SchArrangeDoctorDept> arrDocDeptList = it.next();
				String schEndDate = _processDoctorDept(arrange, doctor,schStartDate,startInterval,arrDocDeptList,allSchDeptList,allSchDateList,currUser,old,exact);
				schStartDate = DateUtil.addDate(schEndDate,1);
				startInterval +=arrDocDeptList.size();
			}
			if(d==doctorList.size()-1){
				SchArrange update = new SchArrange();
				update.setArrangeFlow(arrange.getArrangeFlow());
				update.setEndIndex(StatisticsUtil.arrange(old)+"");
				arrangeMapper.updateByPrimaryKeySelective(update);
			}
			ResDoctor update = new ResDoctor();
			update.setDoctorFlow(doctor.getDoctorFlow());
            update.setSchFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			doctorMapper.updateByPrimaryKeySelective(update);

			//被强制终止了
			SchArrange last = arrangeMapper.selectByPrimaryKey(arrange.getArrangeFlow());
			if(SchArrangeStatusEnum.Aborting.getId().equals(last.getArrangeStatusId())){
				SchArrange update2 = new SchArrange();
				update2.setArrangeFlow(arrange.getArrangeFlow());
				update2.setDoctorNum(d+1);
				update2.setArrangeStatusId(SchArrangeStatusEnum.Aborted.getId());
				update2.setArrangeStatusName(SchArrangeStatusEnum.Aborted.getName());
				arrangeMapper.updateByPrimaryKeySelective(update2);
				return;
			}
		}
		
		SchArrange update = new SchArrange();
		update.setArrangeFlow(arrange.getArrangeFlow());
		update.setArrangeStatusId(SchArrangeStatusEnum.Finish.getId());
		update.setArrangeStatusName(SchArrangeStatusEnum.Finish.getName());
		arrangeMapper.updateByPrimaryKeySelective(update);
	}
	

	//@Transactional(rollbackFor=Exception.class)
	private double [][] _arrageDept(SchArrange arrange,ResDoctor doctor,
			List<SchArrangeDoctorDept> arrangeDoctorDeptList,List<String> allSchDeptList,
			List<String> allSchDateList,
			SysUser currUser,boolean exact){
        String resRotationUnit = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_rotation_unit"), com.pinde.core.common.enums.SchUnitEnum.Month.getId());
		String firthStartYear = DateUtil.transDateTime(arrange.getBeginDate(), DateUtil.defDtPtn04, "yyyy");
		
		SchArrangeResultExample example = new SchArrangeResultExample();
        SchArrangeResultExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(currUser.getOrgFlow());
		criteria.andSchYearGreaterThanOrEqualTo(firthStartYear);
		List<SchArrangeResult> schArrangeResultList = schArrangeResultMapper.selectByExample(example);

		Map<String,Integer> schDeptCountMap = new HashMap<String,Integer>();
		for(SchArrangeResult schArrangeResult : schArrangeResultList){
			String schDeptFlow = schArrangeResult.getSchDeptFlow();
			String schStartDate = schArrangeResult.getSchStartDate();
			String schEndDate = schArrangeResult.getSchEndDate();
			String processDate = schStartDate;
			do{
				String interval = schDeptFlow+"_"+processDate;
				Integer deptCount  = schDeptCountMap.get(interval);
				if(deptCount==null){
					deptCount = new Integer(0);
				}
				deptCount = deptCount+1;
				schDeptCountMap.put(interval,deptCount);
				int signDays = (int) DateUtil.signDaysBetweenTowDate(schEndDate,processDate);
				if(signDays<=0){
					break;
				}
				processDate = DateUtil.addDate(processDate, 1);	
			}while(true);
		}
		
		float doctorTotalSchMonth  = 0;
		for(SchArrangeDoctorDept arrangeDocDept : arrangeDoctorDeptList){
			float tmp = Float.parseFloat(arrangeDocDept.getSchMonth());
			doctorTotalSchMonth += tmp;
			if(!allSchDeptList.contains(arrangeDocDept.getSchDeptFlow())){
				allSchDeptList.add(arrangeDocDept.getSchDeptFlow());
			}
		}
		
		String startDate = arrange.getBeginDate();
		String endDate = arrange.getBeginDate();

        if (com.pinde.core.common.enums.SchUnitEnum.Month.getId().equals(resRotationUnit)) {
			endDate = DateUtil.addMonthForArrange(arrange.getBeginDate(), doctorTotalSchMonth+"",exact);
		}
        if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(resRotationUnit)) {
			endDate = DateUtil.addDate(arrange.getBeginDate(), (int)doctorTotalSchMonth*7-1);
		}
		int totalDays = (int) DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
		
		double [][] old = new double[allSchDeptList.size()][totalDays];
		
		{
			String processDate = startDate;
			do{
				allSchDateList.add(processDate);
				int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate,processDate);
				if(signDays<=0){
					break;
				}
				processDate = DateUtil.addDate(processDate, 1);	
			}while(true);
		}
		
		int i=0;
		
		for(String schDeptFlow : allSchDeptList){
			int j = 0;

			String processDate = startDate;
			do{
				String interval = schDeptFlow+"_"+processDate;
				Integer deptCount  = schDeptCountMap.get(interval);
				if(deptCount==null){
					deptCount = new Integer(0);
				}
				old[i][j++] = deptCount.doubleValue();
				int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate,processDate);
				if(signDays<=0){
					break;
				}
				processDate = DateUtil.addDate(processDate, 1);	
			}while(true);
			i++;
		}
		_printArray(old);
		return old;
	}

	private List<List<SchArrangeDoctorDept>> _splitArrDept(List<SchArrangeDoctorDept> arrDocDeptList){
		ArrayList<List<SchArrangeDoctorDept>> result=new ArrayList<List<SchArrangeDoctorDept>>();
		List<SchArrangeDoctorDept> temp = new ArrayList<SchArrangeDoctorDept>();
		while(arrDocDeptList.size()>0){
			int r=(int)(Math.random()*arrDocDeptList.size());
			temp.add(arrDocDeptList.remove(r));
			if(temp.size()==MaxSchDeptNums){
				result.add(temp);
				temp = new ArrayList<SchArrangeDoctorDept>();
			}
		}
		if(temp.size()>0){
			result.add(temp);
		}
		return result;
	}

	//@Transactional(rollbackFor=Exception.class)
	private String _processDoctorDept(SchArrange arrange,ResDoctor doctor,String schStartDate,int startInterval,
			List<SchArrangeDoctorDept> arrDocDeptList,List<String> allSchDeptList,List<String> allSchDateList,
			SysUser currUser,double [][] old,boolean exact){
        String resRotationUnit = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_rotation_unit"), com.pinde.core.common.enums.SchUnitEnum.Month.getId());
		//医生最大的排班数量
		List<String> arrDocDeptFlowList = new LinkedList<String>();
		Map<String,SchArrangeDoctorDept> arrangeDoctorDeptMap = new HashMap<String, SchArrangeDoctorDept>();
		for(SchArrangeDoctorDept arrangeDoctorDept : arrDocDeptList){
			arrDocDeptFlowList.add(arrangeDoctorDept.getArrDocDeptFlow());
			arrangeDoctorDeptMap.put(arrangeDoctorDept.getArrDocDeptFlow(), arrangeDoctorDept);
		}

		double doctorIndex = Double.MAX_VALUE;
		List<String> bestArrDocDeptFlowArray = null;
		int order = 0;
		Iterator<List<String>> it = new PermutationIterator<String>(arrDocDeptFlowList);
		while (it.hasNext()) {
			List<String> arrDocDeptFlowArray = it.next();
			
			double[][] newArray = _copyArray(old);
			
			String startDate = schStartDate;
			String endDate  =schStartDate;
			for(int i=0;i<arrDocDeptFlowArray.size();i++){
				String arrDocDeptFlow = arrDocDeptFlowArray.get(i);

				String schDeptFlow = arrangeDoctorDeptMap.get(arrDocDeptFlow).getSchDeptFlow();
				float schMonth = Float.parseFloat(StringUtil.defaultIfEmpty(arrangeDoctorDeptMap.get(arrDocDeptFlow).getSchMonth(), "0.0"));
				int oriDeptIndex = allSchDeptList.indexOf(schDeptFlow);
                if (com.pinde.core.common.enums.SchUnitEnum.Month.getId().equals(resRotationUnit)) {
					endDate = DateUtil.addMonthForArrange(startDate, schMonth+"",exact);
				}
                if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(resRotationUnit)) {
					endDate = DateUtil.addDate(startDate, (int)schMonth*7-1);
				}
				String processDate = startDate;
				do{
					int oriDateIndex = allSchDateList.indexOf(processDate);
					newArray[oriDeptIndex][oriDateIndex] += 1;
					int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate,processDate);
					if(signDays<=0){
						break;
					}
					processDate = DateUtil.addDate(processDate, 1);	
				}while(true);
				startDate = DateUtil.addDate(processDate, 1);
			}
//			_printArray(newArray);
			double index = StatisticsUtil.arrange(newArray);
			if(index<doctorIndex){
				bestArrDocDeptFlowArray = arrDocDeptFlowArray;
				doctorIndex = index;
			}
			double oldIndex = StatisticsUtil.arrange(old);
			if(oldIndex==0d){
				break;
			}
			order++;
		}
		
		String schYear = DateUtil.transDateTime(schStartDate, DateUtil.defDtPtn04, "yyyy");
		//最佳方案
		//记录最佳方案后的old值
		String startDate = schStartDate;
		String endDate  =schStartDate;
		for(int i=0;i<bestArrDocDeptFlowArray.size();i++){
			String arrDocDeptFlow = bestArrDocDeptFlowArray.get(i);
			SchArrangeDoctorDept arrangeDoctorDept = arrangeDoctorDeptMap.get(arrDocDeptFlow);
			
			String schDeptFlow = arrangeDoctorDept.getSchDeptFlow();
			float schMonth = Float.parseFloat(arrangeDoctorDept.getSchMonth());
			int oriDeptIndex = allSchDeptList.indexOf(schDeptFlow);

            if (com.pinde.core.common.enums.SchUnitEnum.Month.getId().equals(resRotationUnit)) {
				endDate = DateUtil.addMonthForArrange(startDate, schMonth+"",exact);
			}
            if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(resRotationUnit)) {
				endDate = DateUtil.addDate(startDate, (int)schMonth*7-1);
			}
			String processDate = startDate;
			do{
				int oriDateIndex = allSchDateList.indexOf(processDate);
				old[oriDeptIndex][oriDateIndex] += 1;
				int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate,processDate);
				if(signDays<=0){
					break;
				}
				processDate = DateUtil.addDate(processDate, 1);	
			}while(true);

			//记录排班结构
			SchArrangeResult sar = new SchArrangeResult();
			sar.setResultFlow(PkUtil.getUUID());
			sar.setArrangeFlow(arrange.getArrangeFlow());
			sar.setDoctorFlow(doctor.getDoctorFlow());
			sar.setDoctorName(doctor.getDoctorName());
			sar.setSessionNumber(doctor.getSessionNumber());
			sar.setRotationFlow(doctor.getRotationFlow());
			sar.setRotationName(doctor.getRotationName());
			sar.setSchYear(schYear);
			sar.setSchDeptOrder(new BigDecimal((startInterval+i+1)));
			sar.setSchStartDate(startDate);
			sar.setSchEndDate(endDate);
			sar.setDeptFlow(arrangeDoctorDept.getDeptFlow());
			sar.setDeptName(arrangeDoctorDept.getDeptName());
			sar.setSchDeptFlow(arrangeDoctorDept.getSchDeptFlow());
			sar.setSchDeptName(arrangeDoctorDept.getSchDeptName());
			sar.setIsRequired(arrangeDoctorDept.getIsRequired());
			sar.setGroupFlow(arrangeDoctorDept.getGroupFlow());
			sar.setStandardGroupFlow(arrangeDoctorDept.getStandardGroupFlow());
			sar.setStandardDeptId(arrangeDoctorDept.getStandardDeptId());
			sar.setStandardDeptName(arrangeDoctorDept.getStandardDeptName());
			
			GeneralMethod.setRecordInfo(sar, true);
			
			sar.setOrgFlow(arrangeDoctorDept.getOrgFlow());
			sar.setOrgName(arrangeDoctorDept.getOrgName());
			
			sar.setSchMonth(schMonth+"");
			schArrangeResultMapper.insert(sar);
			
			startDate = DateUtil.addDate(processDate, 1);
		}
//		_printArray(old);
		return endDate;
	}
	
	private double[][] _copyArray(double[][] strArray){
		double[][] copyArray=new double[strArray.length][];
		for(int i=0;i<strArray.length;i++){
			copyArray[i]=new double[strArray[i].length];
			System.arraycopy(strArray[i], 0, copyArray[i], 0, strArray[i].length);
		}
		return copyArray;
	}
	
	private void _printArray(double[][] intArray){
//		for(double ttt [] : intArray){
//			for(double t : ttt){
//				System.err.print(t+" ");
//			}
//			System.err.println("");
//		}
		System.err.println(StatisticsUtil.arrange(intArray));
	}

}
