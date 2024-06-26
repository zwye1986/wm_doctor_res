package com.pinde.sci.job;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IRecruitCfgBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.EmailUtil;
import com.pinde.sci.common.util.mo.EmailAccount;
import com.pinde.sci.common.util.mo.MessageInfo;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.PubMsgMapper;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component 
@Transactional(rollbackFor=Exception.class)
public class ResGiveUpRecruitJobBizImpl {

	private static Logger logger = LoggerFactory.getLogger(ResGiveUpRecruitJobBizImpl.class);

	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private DoctorRecruitBiz doctorRecruitBiz;

	@Value("#{configProperties['sczy.giveupauto']}")
	private String giveupauto;

//	@Scheduled(cron="* * 3 * * ?") // todo empty method
	public void doJob() {
//		if(!"Y".equals(giveupauto)){
//			return;
//		}
//		String currDate = DateUtil.getCurrDate();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
//		DateCfgMsg admitDateCfgMsg = new DateCfgMsg(recruitCfg);
//		admitDateCfgMsg.setAdmitDateMsg(currDate);
//		String code = admitDateCfgMsg.getCode();
//		//确认日期截止时，把所有未确认学员统一做放弃录取处理
//		if("2".equals(code)){
//			Map<String,Object> paramMap = new HashMap<String,Object>();
//			paramMap.put("recruitYear", regYear);
//			paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
//			paramMap.put("recruitFlag", "Y");
//			paramMap.put("confirmFlagIsNull", "Y");
//			List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
//			if(doctorRecruitExts!=null&&doctorRecruitExts.size()>0){
//				for(ResDoctorRecruitExt recruitExt:doctorRecruitExts){
//					ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
//					recruit.setRecruitFlow(recruitExt.getRecruitFlow());
//					recruit.setConfirmFlag("N");
//					doctorRecruitBiz.giveUpDoctorRecruit(recruit);
//				}
//			}
//		}
	}

}
