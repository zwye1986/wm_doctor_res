package com.pinde.res.biz.nydyjs.impl;

import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu.INjmuBiz;
import com.pinde.res.biz.nydyjs.INydyjsAppBiz;
import com.pinde.res.dao.nfyy.ext.AppMapper;
import com.pinde.res.dao.nydyjs.ext.NydyjsAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class NydyjsAppBizImpl implements INydyjsAppBiz {

	@Resource
	private NydyjsAppMapper nydyjsAppMapper;

	private String _md5Dnet(String pTocrypt, String pKey) {
		String ret = "";
		byte[] data3 = new byte[80];
		try {
			pTocrypt = StringUtil.defaultString(pTocrypt).toUpperCase();
			pKey = StringUtil.defaultString(pKey).toUpperCase();
			byte[] data1 = pTocrypt.getBytes("UTF-8");
			byte[] data2 = pKey.getBytes("UTF-8");
			int d1len = 0;
			int d2len = 0;
			for (int i = 0; i < 80; i++) {
				if ((i % 2) == 0 && d1len < data1.length) {
					data3[i] = data1[d1len++];
				} else if ((i % 2) == 1 && d2len < data2.length) {
					data3[i] = data2[d2len++];
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		try {
			ret = CodeUtil.md5(new String(data3,"UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}


	@Override
	public int getNotAuditNum(String userFlow) {
		return nydyjsAppMapper.getNotAuditNum(userFlow);
	}

	@Override
	public int getIsCurrentNum(String userFlow) {
		return nydyjsAppMapper.getIsCurrentNum(userFlow);
	}

	@Override
	public int getIsSchNum(String userFlow) {
		return nydyjsAppMapper.getIsSchNum(userFlow);
	}

	@Override
	public Map<String, String> readUser(String userFlow) {
		return nydyjsAppMapper.readUser(userFlow);
	}

	@Override
	public List<Map<String, String>> getStudentList(Map<String, Object> param) {
		return nydyjsAppMapper.getStudentList(param);
	}
	@Override
	public List<Map<String, String>> getDataStudentList(Map<String, Object> param) {
		return nydyjsAppMapper.getDataStudentList(param);
	}
	@Override
	public List<Map<String, String>> getCaseRegistryList(Map<String, Object> param) {
		return nydyjsAppMapper.getCaseRegistryList(param);
	}
	@Override
	public List<Map<String, String>> getDiseaseRegistryList(Map<String, Object> param) {
		return nydyjsAppMapper.getDiseaseRegistryList(param);
	}
	@Override
	public List<Map<String, String>> getOperateSkillList(Map<String, Object> param) {
		return nydyjsAppMapper.getOperateSkillList(param);
	}
	@Override
	public List<Map<String, String>> getPossSkillList(Map<String, Object> param) {
		return nydyjsAppMapper.getPossSkillList(param);
	}
	@Override
	public List<Map<String, String>> getActivityList(Map<String, Object> param) {
		return nydyjsAppMapper.getActivityList(param);
	}

	@Override
	public List<Map<String, String>> appealDataList(Map<String, Object> param) {
		return nydyjsAppMapper.appealDataList(param);
	}

	@Override
	public Map<String, String> getAfterSummDetail(Map<String, Object> param) {
		return nydyjsAppMapper.getAfterSummDetail(param);
	}

	@Override
	public Map<String, String> getCaseRegistryProcess(Map<String, Object> param) {
		return nydyjsAppMapper.getCaseRegistryProcess(param);
	}
	@Override
	public Map<String, String> getDiseaseProcess(Map<String, Object> param) {
		return nydyjsAppMapper.getDiseaseProcess(param);
	}
	@Override
	public Map<String, String> getSkillProcess(Map<String, Object> param) {
		return nydyjsAppMapper.getSkillProcess(param);
	}
	@Override
	public Map<String, String> getPossSkillProcess(Map<String, Object> param) {
		return nydyjsAppMapper.getPossSkillProcess(param);
	}

	@Override
	public int dataAudit(Map<String, Object> param) {
		String classId= (String) param.get("classId");
		param.put("auditTime", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss"));
		int count=0;
		if(StringUtil.isNotBlank(classId)){
			count+=nydyjsAppMapper.insertIntoReviewInfo(param);
			count+=nydyjsAppMapper.insertIntoReviewInfoItem(param);
			if(classId.equals("1")){//病历
				count+=nydyjsAppMapper.updateRecBigHistory(param);
			}else if(classId.equals("2")){//病种
				count+=nydyjsAppMapper.updateRecCaseClass(param);
			}else if(classId.equals("3")){//操作
				count+=nydyjsAppMapper.updateRecOperateSkill(param);
			}else if(classId.equals("4")){//手术
				count+=nydyjsAppMapper.updateRecPOSSkill(param);
			}
		}
		return count;
	}

	@Override
	public int activityAudit(Map<String, Object> param) {

		param.put("auditTime", DateUtil.getCurrDateTime("yyyy-MM-dd"));
		int count=0;
		count+=nydyjsAppMapper.insertCaseDiscussCheck(param);
		count+=nydyjsAppMapper.updateCaseDiscuss(param);
		return count;
	}

	@Override
	public int appealAudit(Map<String, Object> param) {
		param.put("auditTime", DateUtil.getCurrDateTime("yyyy-MM-dd  HH:mm:ss"));
		int count=0;
		count+=nydyjsAppMapper.updateExplan(param);
		return count;
	}
	@Override
	public int afterAudit(Map<String, Object> param) {
		param.put("auditTime", DateUtil.getCurrDateTime("yyyy-MM-dd  HH:mm:ss"));
		int count=0;
		count+=nydyjsAppMapper.updateOutSecBrief(param);
		count+=nydyjsAppMapper.deleteWFWORK(param);
		return count;
	}

	@Override
	public int getDiseaseRegistryNum(String explanId) {
		return nydyjsAppMapper.getDiseaseRegistryNum(explanId);
	}

	@Override
	public int getOperateSkillNum(String explanId) {
		return nydyjsAppMapper.getOperateSkillNum(explanId);
	}

	@Override
	public int getPossSkillNum(String explanId) {
		return nydyjsAppMapper.getPossSkillNum(explanId);
	}
	@Override
	public int getCaRequireNum(String hosCySecId) {
		return nydyjsAppMapper.getCaRequireNum(hosCySecId);
	}
	@Override
	public int getDisRequireNum(String hosCySecId) {
		return nydyjsAppMapper.getDisRequireNum(hosCySecId);
	}

	@Override
	public int getOpRequireNum(String hosCySecId) {
		return nydyjsAppMapper.getOpRequireNum(hosCySecId);
	}
	@Override
	public int getOpsRequireNum(String hosCySecId) {
		return nydyjsAppMapper.getOpsRequireNum(hosCySecId);
	}

	@Override
	public int getCaRequireFinishNum(Map<String, Object> param) {
		return nydyjsAppMapper.getCaRequireFinishNum(param);
	}
	@Override
	public int getDisRequireFinishNum(Map<String, Object> param) {
		return nydyjsAppMapper.getDisRequireFinishNum(param);
	}

	@Override
	public int getOpRequireFinishNum(Map<String, Object> param) {
		return nydyjsAppMapper.getOpRequireFinishNum(param);
	}

	@Override
	public int getOpsRequireFinishNum(Map<String, Object> param) {
		return nydyjsAppMapper.getOpsRequireFinishNum(param);
	}

	@Override
	public Map<String, Object> getDayLogCount(Map<String, Object> param) {
		return nydyjsAppMapper.getDayLogCount(param);
	}

	@Override
	public Map<String, Object> getCycDayCount(Map<String, Object> param) {
		return nydyjsAppMapper.getCycDayCount(param);
	}

	@Override
	public int getActivityNum(Map<String, Object> param) {
		return nydyjsAppMapper.getActivityNum(param);
	}

	@Override
	public Map<String, Object> getScoreMap(Map<String, Object> param) {
		return nydyjsAppMapper.getScoreMap(param);
	}

	@Override
	public int getCheckStatus(Map<String, Object> param) {
		return nydyjsAppMapper.getCheckStatus(param);
	}

	@Override
	public int updateAfterEva(Map<String, Object> param) {
		return nydyjsAppMapper.updateAfterEva(param);
	}

	@Override
	public int insertAfterEva(Map<String, Object> param) {
		return nydyjsAppMapper.insertAfterEva(param);
	}

	@Override
	public Map<String, Object> getTheroyScore(String afterEvaId) {
		return nydyjsAppMapper.getTheroyScore(afterEvaId);
	}

	@Override
	public int deleteWfwork(String workId) {
		return nydyjsAppMapper.deleteWfwork(workId);
	}

	@Override
	public int insertWFWORK(Map<String, Object> param2) {
		return nydyjsAppMapper.insertWFWORK(param2);
	}

	@Override
	public List<Map<String, Object>> getDayTimeList(Map<String, Object> param2) {
		return nydyjsAppMapper.getDayTimeList(param2);
	}

	@Override
	public int havaAppealNum(Map<String, Object> paramMap) {
		return nydyjsAppMapper.havaAppealNum(paramMap);
	}

	@Override
	public int getMonths(Map<String, Object> param) {
		return nydyjsAppMapper.getMonths(param);
	}

	@Override
	public List<Map<String, String>> appealStudentList(Map<String, Object> param) {

		return nydyjsAppMapper.appealStudentList(param);
	}
	@Override
	public List<Map<String, String>> afterSummeryStudentList(Map<String, Object> param) {

		return nydyjsAppMapper.afterSummeryStudentList(param);
	}
	@Override
	public List<Map<String, String>> afterEvaStudentList(Map<String, Object> param) {

		return nydyjsAppMapper.afterEvaStudentList(param);
	}
	@Override
	public int getAppealNotAuditNum(String userFlow) {
		return nydyjsAppMapper.getAppealNotAuditNum(userFlow);
	}
	@Override
	public int getAfterSummNotAuditNum(String userFlow) {
		return nydyjsAppMapper.getAfterSummNotAuditNum(userFlow);
	}
	@Override
	public int getAfterEvaNotAuditNum(String userFlow) {
		return nydyjsAppMapper.getAfterEvaNotAuditNum(userFlow);
	}
}
