package com.pinde.sci.biz.sch.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.HttpClientUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.vo.sch.SchExamArrangementVO;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchExamArrangementExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
@Slf4j
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

	private static final String HTTP_STATUS_OK = "200";

	private static final String HTTP_RESP_CODE = "code";

	private static final String HTTP_RESP_MSG = "msg";

	/***
	 * key和iv值可以随机生成,确保与前端的key,iv对应
	 */
	private static String KEY = "abcdefgh12345678";
	private static String IV = "12345678abcdefgh";

	/**
	 * 接专业和年级生成试卷
	 * @param schExamArrangement
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @return
	 */
	@Override
	public String generateExam(SchExamArrangement schExamArrangement, String trainingSpeId, String sessionNumber, String accessToken) {
		String testTypeFlow = "15";
		if(StringUtils.isNotEmpty(InitConfig.getSysCfg("jsres_yearly_test_type_flow"))) {
			testTypeFlow = InitConfig.getSysCfg("jsres_yearly_test_type_flow");
		}
		// 构建请求体
		Map<String, String> map = new HashMap<>();
		// 考试端url
		String yearlyTestUrl = "";
		if(StringUtils.isEmpty(schExamArrangement.getArrangeFlow())) {
			yearlyTestUrl = InitConfig.getSysCfg("jsres_yearly_test_url");
		}else {
			yearlyTestUrl = InitConfig.getSysCfg("jsres_yearly_test_update_url");
			map.put("paperFlow", schExamArrangement.getPaperFlow());
		}
		String paperName = schExamArrangement.getOrgName() + schExamArrangement.getAssessmentYear() + "年度" + sessionNumber + "级"
				+ DictTypeEnum.DoctorTrainingSpe.getDictNameById(trainingSpeId) + "住院医师年度考核";
		map.put("paperName", paperName);
		map.put("paperTime", schExamArrangement.getExamDuration());
		map.put("paperPassScore", "60");
		map.put("sumSore", "100");
		map.put("soluType", "1");
		map.put("isDepartmentalExa", "0");
		map.put("paperType", "5");
		map.put("startDate", schExamArrangement.getExamStartTime());
		map.put("endDate", schExamArrangement.getExamEndTime());
		map.put("testUsedNum", schExamArrangement.getExamNumber());
		map.put("isSingle", "false");
		map.put("isDisplayResult", "false");
		map.put("isDownload", "false");
		map.put("testTypeFlow", testTypeFlow);

		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("testTypeFlow", testTypeFlow);
		jsonObject.put("subjectName", "单选题");
		jsonObject.put("num", "100");
		jsonObject.put("score", 1);
		jsonObject.put("totalScore", 100);
		array.add(jsonObject);
		map.put("paperTopicStr", array.toJSONString());
		map.put("subjectFlowStr", null);
		map.put("isLimited", "Y");
		map.put("singleExam", "0");
		map.put("paperExamType", "3");
		map.put("isRollBack", "0");
		map.put("testNum", "100");
		map.put("weChat", "1");
		map.put("web", "0");
		map.put("pointExtractInfo", "[]");

		Header[] headers = new Header[]{new BasicHeader("authorization", accessToken),
			new BasicHeader("applicationname", InitConfig.getSysCfg("jsres_yearly_test_applicationname")),
			new BasicHeader("projectname", InitConfig.getSysCfg("jsres_yearly_test_projectname")),
		};
		HttpClientUtil.HttpResult httpResult = HttpClientUtil.sendPostForm(yearlyTestUrl, map, headers, null, "UTF-8");

		String resultContent = httpResult.getStringContent();
		JSONObject resultJson = JSONObject.parseObject(resultContent);
		if(!HTTP_STATUS_OK.equals(resultJson.get(HTTP_RESP_CODE))) {
			throw new RuntimeException("生成年度试卷失败，失败信息：" + resultJson.get(HTTP_RESP_MSG));
		}

		String paperFlow = resultJson.getJSONObject("data").getString("paperFlow");

		// 生成试卷后开启试卷（允许使用）
		String switchOnTestUrl = InitConfig.getSysCfg("jsres_yearly_test_switch_status_url");
		Map<String, String> switchOnParams = new HashMap<>();
		switchOnParams.put("paperFlowStr", paperFlow);
		switchOnParams.put("isStop", "true");
		// 暂时不看结果，简单处理
		httpResult = HttpClientUtil.sendPostForm(switchOnTestUrl, switchOnParams, headers, null, "UTF-8");
		String switchOnContent = httpResult.getStringContent();
		JSONObject switchOnJson = JSONObject.parseObject(switchOnContent);
		if(!HTTP_STATUS_OK.equals(switchOnJson.get(HTTP_RESP_CODE))) {
			throw new RuntimeException("开启年度试卷失败，失败信息：" + switchOnJson.get(HTTP_RESP_MSG));
		}

		return paperFlow;
	}

	@Override
	public void generateDoctorExam(SchExamArrangement schExamArrangement, String paperFlow, String trainingSpeId, String sessionNumber, String accessToken) {
		// 先查出数据
		SchExamArrangementVO queryVO = new SchExamArrangementVO();
		queryVO.setTrainingSpeId(trainingSpeId);
		queryVO.setSessionNumber(sessionNumber);
		queryVO.setSchStartDate(schExamArrangement.getAssessmentStartTime());
		queryVO.setSchEndDate(schExamArrangement.getAssessmentEndTime());
		queryVO.setOrgFlow(schExamArrangement.getOrgFlow());
		List<SchExamArrangementVO> userDataList = schExamArrangementMapper.selectExamUserList(queryVO);

		if(CollectionUtils.isEmpty(userDataList)) {
			log.info("generateDoctorExam warn, empty user list, org: {}, speId: {}, sessionNumber: {}", schExamArrangement.getOrgName(), trainingSpeId, sessionNumber);
			return;
		}

		// 处理一下数据 userDataList
		for (SchExamArrangementVO schExamArrangementVO : userDataList) {
			String standardDeptName = schExamArrangementVO.getDeptName();
			String[] stdDeptArr = standardDeptName.split(",");
			List<String> stdDeptList = Arrays.stream(stdDeptArr).distinct().collect(Collectors.toList());
			for(int i = 0; i < stdDeptList.size(); i++) {
				switch (i) {
					case 0: schExamArrangementVO.setSchMonth01StdDept(stdDeptList.get(i));break;
					case 1: schExamArrangementVO.setSchMonth02StdDept(stdDeptList.get(i));break;
					case 2: schExamArrangementVO.setSchMonth03StdDept(stdDeptList.get(i));break;
					case 3: schExamArrangementVO.setSchMonth04StdDept(stdDeptList.get(i));break;
					case 4: schExamArrangementVO.setSchMonth05StdDept(stdDeptList.get(i));break;
					case 5: schExamArrangementVO.setSchMonth06StdDept(stdDeptList.get(i));break;
					case 6: schExamArrangementVO.setSchMonth07StdDept(stdDeptList.get(i));break;
					case 7: schExamArrangementVO.setSchMonth08StdDept(stdDeptList.get(i));break;
					case 8: schExamArrangementVO.setSchMonth09StdDept(stdDeptList.get(i));break;
					case 9: schExamArrangementVO.setSchMonth10StdDept(stdDeptList.get(i));break;
					case 10: schExamArrangementVO.setSchMonth11StdDept(stdDeptList.get(i));break;
					case 11: schExamArrangementVO.setSchMonth12StdDept(stdDeptList.get(i));break;
					default:
				}
			}
		}

		String[] titles = new String[] {
				"userName:姓名",
				"trainingSpeId:专业",
				"userCode:用户名",
				"userPhone:手机号",
				"idNo:身份证号",
				"schMonth01StdDept:2024-01",
				"schMonth02StdDept:2024-02",
				"schMonth03StdDept:2024-03",
				"schMonth04StdDept:2024-04",
				"schMonth05StdDept:2024-05",
				"schMonth06StdDept:2024-06",
				"schMonth07StdDept:2024-07",
				"schMonth08StdDept:2024-08",
				"schMonth09StdDept:2024-09",
				"schMonth10StdDept:2024-10",
				"schMonth11StdDept:2024-11",
				"schMonth12StdDept:2024-12",

		};
		byte[] stream = null;
        try {
			stream = com.pinde.core.util.ExcleUtile.exportExcelFileByObjs(titles, userDataList);
        } catch (Exception e) {
            log.info("generateDoctorExam warn, generate excel error, org: {}, speId: {}, sessionNumber: {}", schExamArrangement.getOrgName(), trainingSpeId, sessionNumber);
			return;
        }

		// 将生成的excel文件传给考试
		String yearlyTestImportPlanUrl = InitConfig.getSysCfg("jsres_yearly_test_import_plan_url");
		HttpClient httpClient = HttpClientUtil.getClient();
		HttpPost httpPost = new HttpPost(yearlyTestImportPlanUrl);

		// 添加表单字段
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("paperFlow", paperFlow);

		// 添加文件
		String tempDir = System.getProperty("java.io.tmpdir");
		String uid = PkUtil.getUUID();
		File file = new File(tempDir + File.separator + uid + "_导入排班.xls"); // 创建File对象指定文件路径

		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(stream); // 将byte数组写入文件
			builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
		} catch (IOException e) {
			log.info("generateDoctorExam warn, generate file error, org: {}, speId: {}, sessionNumber: {}", schExamArrangement.getOrgName(), trainingSpeId, sessionNumber);
			return;
		}

		// 构建实体
		HttpEntity multipart = builder.build();
		httpPost.setEntity(multipart);
		httpPost.addHeader(new BasicHeader("authorization", accessToken));
		httpPost.addHeader(new BasicHeader("applicationname", InitConfig.getSysCfg("jsres_yearly_test_applicationname")));
		httpPost.addHeader(new BasicHeader("projectname", InitConfig.getSysCfg("jsres_yearly_test_projectname")));
		HttpResponse response = null;
		String failReason = "";
        try {
			response = httpClient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			HttpEntity httpEntity = response.getEntity();

			if(statusLine.getStatusCode() != 200) {
				throw new RuntimeException("生成学员年度试卷失败，请求失败, 状态码：{}" + statusLine.getStatusCode());
			}

			if (statusLine.getStatusCode() == 200) {
				String resp = EntityUtils.toString(httpEntity);
				JSONObject respJson = JSONObject.parseObject(resp);
				if(!HTTP_STATUS_OK.equals(respJson.get(HTTP_RESP_CODE))) {
					throw new RuntimeException("生成学员年度试卷失败，信息：" + respJson.get(HTTP_RESP_MSG));
				}
			}
        } catch (Exception e) {
			log.error("generateDoctorExam warn, send post error", e);
			throw new RuntimeException(e);
		}finally {
			if(file != null && file.exists()) {
				file.delete();
			}
		}
    }

	@Override
	public boolean deleteExam(String paperFlow, String accessToken) {
		if(StringUtils.isEmpty(paperFlow) || StringUtils.isEmpty(accessToken)) {
			log.warn("deleteExam fail, paper flow or access token is empty");
		}

		Header[] headers = new Header[]{new BasicHeader("authorization", accessToken),
				new BasicHeader("applicationname", InitConfig.getSysCfg("jsres_yearly_test_applicationname")),
				new BasicHeader("projectname", InitConfig.getSysCfg("jsres_yearly_test_projectname")),
		};
		String statusChangeUrl = InitConfig.getSysCfg("jsres_yearly_test_switch_status_url");
		Map<String, String> map = new HashMap<>();
		map.put("paperFlowStr", paperFlow);
		map.put("recordStatus", "N");

		// 暂时不看结果，简单处理
		HttpClientUtil.HttpResult httpResult = HttpClientUtil.sendPostForm(statusChangeUrl, map, headers, null, "UTF-8");

		return true;
	}

	/**
	 * 加密方法
	 */
	private static String encrypt(String data, String key, String iv) {
		try {
			// "算法/模式/补码方式"NoPadding PkcsPadding
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);

			return new Base64().encodeToString(encrypted);
		} catch (Exception e) {
			log.info("encrypt error.", e);
		}

		return null;
	}

	@Override
	public List<SchExamArrangement> searchList(SchExamArrangement seam) {
		SchExamArrangementExample example=new SchExamArrangementExample();
		SchExamArrangementExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
            schExamArrangement.setIsOpen(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
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
                    tempDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
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
                    schExamStandardDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					GeneralMethod.setRecordInfo(schExamStandardDept,false);
					deptMapper.updateByPrimaryKey(schExamStandardDept);
				}else {
					schExamStandardDept = new SchExamStandardDept();
					schExamStandardDept.setRecordFlow(PkUtil.getUUID());
					schExamStandardDept.setArrangeFlow(arrangeFlow);
					schExamStandardDept.setOrgFlow(schExamArrangement.getOrgFlow());
					schExamStandardDept.setOrgName(schExamArrangement.getOrgName());
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isProduct))
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
	public int updateArrangements(SchExamArrangement schExamArrangement, String[] standardDeptId, List<String> speIds, List<String> sessinNumbers, Map<String, String> speSessionToPaperFlowMap) {
		int c=0;
        com.pinde.core.common.enums.DictTypeEnum e = null;
        if (com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
            e = com.pinde.core.common.enums.DictTypeEnum.AssiGeneral;

            schExamArrangement.setTrainingTypeName(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getName());
		}
        if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
            e = com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe;
            schExamArrangement.setTrainingTypeName(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getName());
		}
        if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
            e = com.pinde.core.common.enums.DictTypeEnum.WMFirst;
            schExamArrangement.setTrainingTypeName(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getName());
		}
        if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(schExamArrangement.getTrainingTypeId()))
		{
            e = com.pinde.core.common.enums.DictTypeEnum.WMSecond;
            schExamArrangement.setTrainingTypeName(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getName());
		}
		for(String speId:speIds)
		{
			for(String sessionNumber:sessinNumbers)
			{
				schExamArrangement.setArrangeFlow("");
				schExamArrangement.setSessionNumber(sessionNumber);
				schExamArrangement.setTrainingSpeId(speId);
				schExamArrangement.setPaperFlow(speSessionToPaperFlowMap.get(speId + sessionNumber));
                schExamArrangement.setTrainingSpeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(e, speId));
                c += updateArrangement(schExamArrangement, standardDeptId, com.pinde.core.common.GlobalConstant.FLAG_N);
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
