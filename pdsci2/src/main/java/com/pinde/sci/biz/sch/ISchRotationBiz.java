package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface ISchRotationBiz {
	List<SchRotation> searchSchRotation();

	List<SchRotation> searchSchRotation(String speId);
	
	SchRotation readSchRotation(String rotationFlow);

	List<SchRotation> readSchRotationByPartitionList(List<List<String>> rotationFlowListList);

	List<SchRotation> readSchRotationByExample(SchRotationExample example);

	int saveSchRotation(SchRotation rotation);

	List<SchRotation> searchRotationByrotationFlows(List<String> rotationFlows);

	/**
	 * 检测本基地未同步的方案数量
	 * @param orgFlow
	 * @return
	 */
	List<SchRotation> searchNotExistRotation(String orgFlow);

//	int editRotations(List<SchRotation> rotationList);

	int rotationClone(String rotationFlow, String rotationYear);

	List<SchRotation> searchOrgStandardRotation(SchRotation rotation);

	List<SchRotation> searchSchRotationByIsPublish();

	int saveLocalRotation(List<SchRotation> rotationList, String orgFlow);

	List<SchRotation> searchSchRotationForPlatform(String doctorCateGoryId);
	Map<String, Object> importRoationFromExcel(MultipartFile file);

	List<SchRotation> searchRotationByName(String rotationName);

	/**
	 * 为医师根据专业自动更新方案
	 * @param doctor
	 * @return
	 */
	ResDoctor updateDocRotation(ResDoctor doctor);

	/**
	 * 加载轮转方案（如果是对指定机构展示，则只显示本机构的）
	 * @param rotationList 所有轮转方案列表
	 * @param orgFlow 当前机构流水
     * @return
     */
	List<SchRotation> schRotations(List<SchRotation> rotationList ,String orgFlow);

	SchRotation getRotationByRecruit(ResDoctorRecruit recruit);

	/**
	 * 审核通过更新状态
	 * @param doctorFlow
	 * @return
	 */
	int updateResDoctorSchProcessStatus(String doctorFlow);

	int updateSchArrangeResultStatus(String doctorFlow);

	int updateResrecStatus(String doctorFlow);

	int updateResSchProcessExpressStatus(String doctorFlow);

	SchRotation getRotationByRecruitNew(ResDoctorRecruit recruit);

	SchRotation searchDoctorBySpeId(String speId);
}
