package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IAcademicBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.FstuAcademicActivityMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.enums.fstu.AcademicAndScoreEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor=Exception.class)
public class AcademicBizImpl implements IAcademicBiz {

	@Autowired
	private FstuAcademicActivityMapper academicMapper;
	@Autowired
	private PubFileMapper fileMapper;

	@Override
	public int saveAcademicActivity(FstuAcademicActivity activity,String filePath) {
		Map<String,String> map = new HashMap<>();
		map.put("filePath",filePath);
		if (StringUtil.isNotBlank(activity.getAcademicFlow())) {
			GeneralMethod.setRecordInfo(activity, false);
			if(StringUtil.isNotBlank(filePath)){
				map.put("academicFlow",activity.getAcademicFlow());
				saveFileInfo(map);
			}
			return academicMapper.updateByPrimaryKeySelective(activity);
		} else {
			activity.setAcademicFlow(PkUtil.getUUID());
			activity.setAuditStatusId(AcademicAndScoreEnum.AcaAtyAdd.getId());
			activity.setAuditStatusName(AcademicAndScoreEnum.AcaAtyAdd.getName());
			activity.setExpenseStatusId(AcademicAndScoreEnum.AcaExpAdd.getId());
			activity.setExpenseStatusName(AcademicAndScoreEnum.AcaExpAdd.getName());
			GeneralMethod.setRecordInfo(activity, true);
			if(StringUtil.isNotBlank(filePath)){
				map.put("academicFlow",activity.getAcademicFlow());
				saveFileInfo(map);
			}
			return academicMapper.insert(activity);
		}
	}

	@Override
	public String saveFileToDirs(MultipartFile file, String folderName) {
		String path = GlobalConstant.FLAG_N;
		if (file.getSize() > 0) {
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			path = folderName + "/" + dateString + "/" + originalFilename;
		}
		return path;
	}

	public int saveFileInfo(Map<String, String> map) {
		String academicFlow = map.get("academicFlow");
		String filePath = map.get("filePath");
		PubFileExample example = new PubFileExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProductTypeEqualTo("Academic").andProductFlowEqualTo(academicFlow);
		List<PubFile> fileLst = fileMapper.selectByExample(example);
		int count = 0;
		if(null != fileLst && fileLst.size() > 0){
			PubFile pf = fileLst.get(0);
			pf.setFileName(filePath.substring(filePath.lastIndexOf('/')+1));
			pf.setFilePath(filePath);
			count = fileMapper.updateByPrimaryKeySelective(pf);
		}else{
			PubFile pf = new PubFile();
			pf.setFileFlow(PkUtil.getUUID());
			pf.setFileName(filePath.substring(filePath.lastIndexOf('/')+1));
			pf.setFilePath(filePath);
			pf.setProductType("Academic");
			pf.setProductFlow(academicFlow);
			GeneralMethod.setRecordInfo(pf,true);
			count = fileMapper.insertSelective(pf);
		}
		return count;
	}

	@Override
	public List<FstuAcademicActivity> search(FstuAcademicActivity activity,String startingTime,String endingTime,List<String> deptFlows) {
		FstuAcademicActivityExample example = new FstuAcademicActivityExample();
		FstuAcademicActivityExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(activity!=null){
			if(deptFlows!=null&&deptFlows.size()>0){
				criteria.andApplyDeptFlowIn(deptFlows);
			}
			if(StringUtil.isNotBlank(activity.getAcademicName())){
				criteria.andAcademicNameLike("%"+activity.getAcademicName()+"%");
			}
			if(StringUtil.isNotBlank(activity.getAcademicTypeId())){
				criteria.andAcademicTypeIdEqualTo(activity.getAcademicTypeId());
			}
			if(StringUtil.isNotBlank(activity.getPlaceCityId())){
				criteria.andPlaceCityIdEqualTo(activity.getPlaceCityId());
			}
			if(StringUtil.isNotBlank(activity.getPlaceProvinceId())){
				criteria.andPlaceProvinceIdEqualTo(activity.getPlaceProvinceId());
			}
			if(StringUtil.isNotBlank(activity.getPlaceArea())){
				criteria.andPlaceAreaLike("%"+activity.getPlaceArea()+"%");
			}
			if(StringUtil.isNotBlank(activity.getHoldUnit())){
				criteria.andHoldUnitLike("%"+activity.getHoldUnit()+"%");
			}
			if(StringUtil.isNotBlank(activity.getBeginTime())){
				criteria.andBeginTimeGreaterThan(activity.getBeginTime());
			}
			if(StringUtil.isNotBlank(activity.getEndTime())){
				criteria.andEndTimeLessThan(activity.getEndTime());
			}
			if(StringUtil.isNotBlank(activity.getApplyYear())){
				criteria.andApplyYearEqualTo(activity.getApplyYear());
			}
			if(StringUtil.isNotBlank(activity.getTakeDay())){
				criteria.andTakeDayEqualTo(activity.getTakeDay());
			}
			if(StringUtil.isNotBlank(activity.getApplyUserName())){
				criteria.andApplyUserNameLike("%"+activity.getApplyUserName()+"%");
			}
			if(StringUtil.isNotBlank(activity.getApplyDeptName())){
				criteria.andApplyDeptNameLike("%"+activity.getApplyDeptName()+"%");
			}
			if(StringUtil.isNotBlank(startingTime)){
				criteria.andApplyTimeGreaterThan(startingTime);
			}
			if(StringUtil.isNotBlank(endingTime)){
				criteria.andApplyTimeLessThan(endingTime);
			}
			if(StringUtil.isNotBlank(activity.getAuditStatusId())){
				criteria.andAuditStatusIdEqualTo(activity.getAuditStatusId());
			}
			example.setOrderByClause("BEGIN_TIME DESC");
			return academicMapper.selectByExample(example);

		}
		return null;
	}

	@Override
	public List<FstuAcademicActivity> searchAcademicActivity(FstuAcademicActivity activity,String typeFlag) {
		FstuAcademicActivityExample example = new FstuAcademicActivityExample();
		FstuAcademicActivityExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (activity != null) {
			if (StringUtil.isNotBlank(activity.getApplyUserFlow())) {//// TODO: 2017-03-28
				criteria.andApplyUserFlowEqualTo(activity.getApplyUserFlow());
			}
			if (StringUtil.isNotBlank(activity.getAcademicName())) {
				criteria.andAcademicNameLike("%" + activity.getAcademicName() + "%");
			}
			if (StringUtil.isNotBlank(activity.getAcademicTypeId())) {
				criteria.andAcademicTypeIdEqualTo(activity.getAcademicTypeId());
			}
			if (StringUtil.isNotBlank(activity.getPlaceCity())) {
				criteria.andPlaceCityLike("%" + activity.getPlaceCity() + "%");
			}
			if (StringUtil.isNotBlank(activity.getTakeDay())) {
				criteria.andTakeDayEqualTo(activity.getTakeDay());
			}
			if (StringUtil.isNotBlank(activity.getBeginTime())) {
				criteria.andBeginTimeGreaterThanOrEqualTo(activity.getBeginTime());
			}
			if (StringUtil.isNotBlank(activity.getEndTime())) {
				criteria.andEndTimeLessThanOrEqualTo(activity.getEndTime());
			}
			List<String> arg = new ArrayList<String>();
			if (StringUtil.isNotBlank(activity.getAuditStatusId())) {//学术申请送审后
				if("ALL".equals(activity.getAuditStatusId())){//全部记录
					arg.add(AcademicAndScoreEnum.AcaAtyPassing.getId());//待审核
					arg.add(AcademicAndScoreEnum.AcaAtyPassed.getId());//审核通过
					arg.add(AcademicAndScoreEnum.AcaAtyBacked.getId());//退回
					criteria.andAuditStatusIdIn(arg);
				}else if("Y".equals(activity.getAuditStatusId())){//已审核
					arg.add(AcademicAndScoreEnum.AcaAtyPassed.getId());
					arg.add(AcademicAndScoreEnum.AcaAtyBacked.getId());
					criteria.andAuditStatusIdIn(arg);
				}else if("N".equals(activity.getAuditStatusId())){//未审核
					arg.add(AcademicAndScoreEnum.AcaAtyPassing.getId());
					criteria.andAuditStatusIdIn(arg);
				}else{//学术报销审核流程
					criteria.andAuditStatusIdEqualTo(activity.getAuditStatusId());
				}
			}
			List<String> arg2 = new ArrayList<String>();
			if (StringUtil.isNotBlank(activity.getExpenseStatusId())) {//学术申请送审后
				if("ALL".equals(activity.getExpenseStatusId())){//全部记录
					arg2.add(AcademicAndScoreEnum.AcaExpPassing.getId());//待审核
					arg2.add(AcademicAndScoreEnum.AcaExpPassed.getId());//审核通过
					arg2.add(AcademicAndScoreEnum.AcaExpBacked.getId());//退回
					criteria.andExpenseStatusIdIn(arg2);
				}else if("Y".equals(activity.getExpenseStatusId())){//已审核
					arg2.add(AcademicAndScoreEnum.AcaExpPassed.getId());
					arg2.add(AcademicAndScoreEnum.AcaExpBacked.getId());
					criteria.andExpenseStatusIdIn(arg2);
				}else if("N".equals(activity.getExpenseStatusId())){//未审核
					arg2.add(AcademicAndScoreEnum.AcaExpPassing.getId());
					criteria.andExpenseStatusIdIn(arg2);
				}
			}
		}
		if("expense".equals(typeFlag)){//学术报销申请（审核）
			example.setOrderByClause("DECODE(EXPENSE_STATUS_ID,'Backed',1,'Passing',2,'Passed',3,4),AUDIT_TIME DESC,CREATE_TIME DESC");
		}
		if("academic".equals(typeFlag)){//学术申请（审核）
			example.setOrderByClause("DECODE(AUDIT_STATUS_ID,'Backed',1,'Passing',2,'Passed',3,4),AUDIT_TIME DESC,CREATE_TIME DESC");
		}
		return academicMapper.selectByExample(example);
	}

	@Override
	public FstuAcademicActivity searchAcademicByFlow(String academicFlow) {
		return academicMapper.selectByPrimaryKey(academicFlow);
	}

	@Override
	public PubFile seachUpFile(String academicFlow) {
		PubFileExample example = new PubFileExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProductFlowEqualTo(academicFlow).andProductTypeEqualTo("Academic");
		List<PubFile> pfLst = fileMapper.selectByExample(example);
		if(null != pfLst && pfLst.size() > 0){
			return pfLst.get(0);
		}
		return null;
	}

	@Override
	public int delAcademicByFlow(String academicFlow) {
		FstuAcademicActivity activity = new FstuAcademicActivity();
		activity.setAcademicFlow(academicFlow);
		activity.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return academicMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int updateAcademicByFlow(String academicFlow) {
		FstuAcademicActivity activity = new FstuAcademicActivity();
		activity.setAcademicFlow(academicFlow);
		activity.setAuditStatusId(AcademicAndScoreEnum.AcaAtyPassing.getId());
		activity.setAuditStatusName(AcademicAndScoreEnum.AcaAtyPassing.getName());
		return academicMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int updateAcademicActivity(FstuAcademicActivity activity) {
		return academicMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int updateExpenseByFlow(String academicFlow,String academicSummary) {
		FstuAcademicActivity activity = new FstuAcademicActivity();
		activity.setAcademicFlow(academicFlow);
		activity.setAcademicSummary(academicSummary);
		activity.setExpenseStatusId(AcademicAndScoreEnum.AcaExpPassing.getId());
		activity.setExpenseStatusName(AcademicAndScoreEnum.AcaExpPassing.getName());
		return academicMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public Map<String,Integer> calculation(String year,List<String> deptFlows) {
		Map<String, Integer> resultMap = new HashMap<>();
		FstuAcademicActivityExample example = new FstuAcademicActivityExample();
		FstuAcademicActivityExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andExpenseStatusIdEqualTo("Passed");
		if(StringUtil.isNotBlank(year)){
			criteria.andApplyYearEqualTo(year);
		}
		if(deptFlows!=null&&deptFlows.size()>0){
			criteria.andApplyDeptFlowIn(deptFlows);
		}
		List<FstuAcademicActivity> academicActivities = academicMapper.selectByExample(example);
		int meetingFeeSum = 0;
		int materialFeeSum = 0;
		int trafficFeeSum = 0;
		int costumeFeeSum = 0;
		int foodFeeSum = 0;
		int subsidyFeeSum = 0;
		int otherFeeSum = 0;
		int sumFeeSum = 0;
		if(academicActivities!=null&&academicActivities.size()>0){
			for(FstuAcademicActivity academicActivity:academicActivities){
				int meetingFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getMeetingFee())?"0":academicActivity.getMeetingFee());
				int materialFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getMaterialFee())?"0":academicActivity.getMaterialFee());
				int trafficFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getTrafficFee())?"0":academicActivity.getTrafficFee());
				int costumeFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getCostumeFee())?"0":academicActivity.getCostumeFee());
				int foodFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getFoodFee())?"0":academicActivity.getFoodFee());
				int subsidyFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getSubsidyFee())?"0":academicActivity.getSubsidyFee());
				int otherFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getOtherFee())?"0":academicActivity.getOtherFee());
				int sumFee = Integer.parseInt(StringUtil.isBlank(academicActivity.getSumFee())?"0":academicActivity.getSumFee());
				meetingFeeSum += meetingFee;
				materialFeeSum += materialFee;
				trafficFeeSum += trafficFee;
				costumeFeeSum += costumeFee;
				foodFeeSum += foodFee;
				subsidyFeeSum += subsidyFee;
				otherFeeSum += otherFee;
				sumFeeSum += sumFee;
			}
		}
		resultMap.put("meetingFeeSum",meetingFeeSum);
		resultMap.put("materialFeeSum",materialFeeSum);
		resultMap.put("trafficFeeSum",trafficFeeSum);
		resultMap.put("costumeFeeSum",costumeFeeSum);
		resultMap.put("foodFeeSum",foodFeeSum);
		resultMap.put("subsidyFeeSum",subsidyFeeSum);
		resultMap.put("otherFeeSum",otherFeeSum);
		resultMap.put("sumFeeSum",sumFeeSum);
		return resultMap;
	}

	@Override
	public int saveAcademicAndFiles(FstuAcademicActivity activity, List<PubFile> fileList) {
		int result = 0;
		if (StringUtil.isNotBlank(activity.getAcademicFlow())) {
			GeneralMethod.setRecordInfo(activity, false);
			result = academicMapper.updateByPrimaryKeySelective(activity);
		} else {
			activity.setAcademicFlow(PkUtil.getUUID());
			activity.setAuditStatusId(AcademicAndScoreEnum.AcaAtyAdd.getId());
			activity.setAuditStatusName(AcademicAndScoreEnum.AcaAtyAdd.getName());
			activity.setExpenseStatusId(AcademicAndScoreEnum.AcaExpAdd.getId());
			activity.setExpenseStatusName(AcademicAndScoreEnum.AcaExpAdd.getName());
			GeneralMethod.setRecordInfo(activity, true);
			result = academicMapper.insert(activity);
		}
		//操作附件
		for(PubFile pubFile : fileList) {
			//操作附件
			if (null != pubFile) {
				pubFile.setProductFlow(activity.getAcademicFlow());
				if (StringUtil.isNotBlank(pubFile.getFileFlow())) {
					GeneralMethod.setRecordInfo(pubFile, false);
					fileMapper.updateByPrimaryKeySelective(pubFile);
				} else {
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile, true);
					fileMapper.insertSelective(pubFile);
				}
			}
		}
		return result;
	}
}
