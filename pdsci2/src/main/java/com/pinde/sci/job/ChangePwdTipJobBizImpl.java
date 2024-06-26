package com.pinde.sci.job;

import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjNoticeBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.GyxjMASUtil;
import com.pinde.sci.dao.base.NyjzScholarshipMainMapper;
import com.pinde.sci.dao.gzykdx.GzykdxSchoolAdminExtMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.sci.enums.xjgl.ScholarshipTypeEnum;
import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.model.mo.NyjzScholarshipMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component 
@Transactional(rollbackFor=Exception.class)

public class ChangePwdTipJobBizImpl {
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Autowired
	private IGyXjNoticeBiz noticeBiz;
	@Autowired
	private IXjEduUserBiz eduUserBiz;
	@Autowired
	private GzykdxSchoolAdminExtMapper schoolExtMapper;
	@Autowired
	private NyjzScholarshipMainMapper scholarshipMapper;

	@Value("#{configProperties['change.password.tip.switch']}")
	private String changePwdSwitch;

	//每隔3月进行密码更新提示（广医大研究生系统）
	//@Scheduled(cron="0 0 0 1 */3 *")
	public void doJob() throws Exception {
		if(GlobalConstant.FLAG_Y.equals(changePwdSwitch)){
			userExtMapper.updateTipPassword();
		}
	}
	//每隔1小时待办事项短信通知（广医大研究生系统）
//	@Scheduled(cron="0 0 * * * *")
//	public void doJob2() throws Exception {
//		String schoolRole = InitConfig.getSysCfg("xjgl_school_role_flow");
//		if(StringUtil.isNotBlank(schoolRole)){
//			//代办事项查询
//			Map<String,Object> dataMap = noticeBiz.searchAgencyThing();
//			if(null != dataMap && (Integer.valueOf(((BigDecimal) dataMap.get("CHANGE_NUM")).toString()) > 0 ||
//					Integer.valueOf(((BigDecimal) dataMap.get("GRADE_NUM")).toString()) > 0 || Integer.valueOf(((BigDecimal) dataMap.get("DOCTOR_NUM")).toString()) > 0)){
//				//学校管理员手机号查询
//				List<Map<String,String>> schoolPhones = schoolExtMapper.querySchoolPhones(schoolRole);
//				if(null != schoolPhones && !schoolPhones.isEmpty()){
//					String phones = "";
//					String content = "您好，您有新的待办事项需要处理，请及时审核。";
//					for(int i=0;i<schoolPhones.size();i++){
//						phones += schoolPhones.get(i).get("USER_PHONE");
//						if(i<schoolPhones.size()-1){
//							phones+=",";
//						}
//					}
//					GyxjMASUtil mas = new GyxjMASUtil(phones.split(","),content);
//					mas.sendSms();
//				}
//			}
//		}
//	}
	//每年4月1号生成奖学金/助学金申请记录（南医大研究生系统）
	//@Scheduled(cron="0 0 0 20 3,9 *")
	public void doJob3() throws Exception {
		String stuRole = InitConfig.getSysCfg("xjgl_student_role_flow");
		if(StringUtil.isNotBlank(stuRole)){
			//生成国家助学金 名单
			List<Map<String,String>> gjzxjLst = new ArrayList<>();
			//生成学业助学金 名单
			List<Map<String,String>> xyzxjLst = new ArrayList<>();
			List<Map<String,Object>> eduUserList = userExtMapper.queryEduUserList(stuRole);
			for(Map<String,Object> obj : eduUserList){
				String content = CodeUtil.clobToString((Clob)obj.get("content"));
				XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
				if(StringUtil.isBlank(extInfoForm.getDropOutSchoolDate()) && GlobalConstant.FLAG_Y.equals(extInfoForm.getIsArchivesComplete())){
					Map<String,String> info = new HashMap<>();
					info.put("userFlow",(String)obj.get("userFlow"));
					info.put("userName",(String)obj.get("userName"));
					info.put("studentId",(String)obj.get("studentId"));
					gjzxjLst.add(info);
					if(GlobalConstant.FLAG_Y.equals(extInfoForm.getIsFeeComplete())){
						xyzxjLst.add(info);
					}
				}
			}
			if(gjzxjLst.size()>0){
				for(Map<String,String> mp : gjzxjLst){
					NyjzScholarshipMain record = new NyjzScholarshipMain();
					record.setRecordFlow(PkUtil.getUUID());
					record.setUserFlow(mp.get("userFlow"));
					record.setUserName(mp.get("userName"));
					record.setStudentId(mp.get("studentId"));
					record.setSessionNumber(DateUtil.getYear());
					record.setApplyFlag(GlobalConstant.FLAG_Y);
					record.setApplyTime(DateUtil.getCurrDate());
					record.setApplyTypeId(ScholarshipTypeEnum.Gjzxj.getId());
					record.setApplyTypeName(ScholarshipTypeEnum.Gjzxj.getName());
					GeneralMethod.setRecordInfo(record,true);
					scholarshipMapper.insertSelective(record);
				}
			}
			if(xyzxjLst.size()>0){
				for(Map<String,String> mp : gjzxjLst){
					NyjzScholarshipMain record = new NyjzScholarshipMain();
					record.setRecordFlow(PkUtil.getUUID());
					record.setUserFlow(mp.get("userFlow"));
					record.setUserName(mp.get("userName"));
					record.setStudentId(mp.get("studentId"));
					record.setSessionNumber(DateUtil.getYear());
					record.setApplyFlag(GlobalConstant.FLAG_Y);
					record.setApplyTime(DateUtil.getCurrDate());
					record.setApplyTypeId(ScholarshipTypeEnum.Xyzxj.getId());
					record.setApplyTypeName(ScholarshipTypeEnum.Xyzxj.getName());
					GeneralMethod.setRecordInfo(record,true);
					scholarshipMapper.insertSelective(record);
				}
			}
		}
	}
}
