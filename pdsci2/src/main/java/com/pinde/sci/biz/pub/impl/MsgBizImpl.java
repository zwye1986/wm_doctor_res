package com.pinde.sci.biz.pub.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.SMSUtil;
import com.pinde.sci.dao.base.PubMsgMapper;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.model.mo.PubMsg;
import com.pinde.sci.model.mo.PubMsgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
@Transactional(rollbackFor=Exception.class)
public class MsgBizImpl implements IMsgBiz {

	@Autowired
	private PubMsgMapper pubMsgMapper;

	@Override
	public int addEmailMsg(String receiver,String title,String content){
		PubMsg msg = new PubMsg();
		String msgFlow = PkUtil.getUUID();
		msg.setMsgFlow(msgFlow);
		msg.setMsgCode(msgFlow);
		msg.setMsgTypeId(MsgTypeEnum.Email.getId());
		msg.setMsgTypeName(MsgTypeEnum.Email.getName());
		msg.setMsgTime(DateUtil.getCurrDateTime());
		msg.setMsgTitle(StringUtil.defaultString(title));
		msg.setMsgContent(StringUtil.defaultString(content));
		msg.setSender(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));
		//msg.setSendTime(DateUtil.getCurrDateTime());
		msg.setReceiver(receiver);
		msg.setSendFlag(GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(msg, true);
		int count = pubMsgMapper.insert(msg);
		if(count > 0){
			doJob(msgFlow);
		}
		return count;
	}

	@Override
	public int addEmailMsg(String receiver,String title,String content,String flag){
		PubMsg msg = new PubMsg();
		String msgFlow = PkUtil.getUUID();
		msg.setMsgFlow(msgFlow);
		msg.setMsgCode(msgFlow);
		msg.setMsgTypeId(MsgTypeEnum.Email.getId());
		msg.setMsgTypeName(MsgTypeEnum.Email.getName());
		msg.setMsgTime(DateUtil.getCurrDateTime());
		msg.setMsgTitle(StringUtil.defaultString(title));
		msg.setMsgContent(StringUtil.defaultString(content));
		msg.setSender(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));
		msg.setReceiver(receiver);
		msg.setSendFlag(GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(msg, true);
		int count = pubMsgMapper.insert(msg);
		if(count > 0){
			doJob(msgFlow);
		}
		return count;
	}
	private void doJob(String msgFlow){//发送邮件
		String maxErrorTimes = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("sys_mail_max_error_times"),"5").trim();
		Integer iMaxErrorTimes = Integer.valueOf(maxErrorTimes);
		PubMsg pubMsg = pubMsgMapper.selectByPrimaryKey(msgFlow);
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_host")));
			int port = Integer.parseInt(StringUtil.defaultIfEmpty(InitConfig.getSysCfg("sys_mail_port"),"25"));
			mailSender.setPort(port);
			Properties prop = new Properties();
			prop.setProperty("mail.smtp.auth", "true");
			mailSender.setJavaMailProperties(prop);
			mailSender.setUsername(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_username")));
			mailSender.setPassword(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_password")));
			MimeMessage mail = mailSender.createMimeMessage();
			//multipart模式
			MimeMessageHelper messageHelper = new MimeMessageHelper(mail,true,"UTF-8");
			messageHelper.setFrom(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));// 发送人名片
			messageHelper.setTo(pubMsg.getReceiver());// 收件人邮箱
			messageHelper.setSubject(pubMsg.getMsgTitle());// 邮件主题
			messageHelper.setSentDate(new Date());// 邮件发送时间
			String context = "<html><head>" +
					"<meta http-equiv='description' content=''><meta http-equiv='content-type' content='text/html; charset=UTF-8'>" +
					"</head><body><div>"+pubMsg.getMsgContent()+"</div></body></html>";
			messageHelper.setText(context,true);
			mailSender.send(mail);
			pubMsg.setSendResult("发送成功");
			pubMsg.setSendFlag(GlobalConstant.FLAG_Y);
			pubMsg.setSender(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));
			pubMsg.setSendTime(DateUtil.getCurrDateTime());
		} catch (Exception e) {
			e.printStackTrace();
			Integer errorTimes = pubMsg.getSendErrorTimes();
			if(errorTimes==null){
				errorTimes = new Integer(0);
			}
			errorTimes++;
			pubMsg.setSendErrorTimes(errorTimes);
			if(errorTimes>iMaxErrorTimes){
				pubMsg.setSendFlag(GlobalConstant.FLAG_F);
			}else{
				pubMsg.setSendFlag(GlobalConstant.FLAG_N);
			}
			pubMsg.setSendResult("发送失败:"+e.getMessage());
		}
		pubMsgMapper.updateByPrimaryKeySelective(pubMsg);
	}

	@Override
	public void addSmsMsg(String receiver,String content){
		PubMsg msg = new PubMsg();
		String msgFlow = PkUtil.getUUID();
		msg.setMsgFlow(msgFlow);
		msg.setMsgCode(msgFlow);
		msg.setMsgTypeId(MsgTypeEnum.Sms.getId());
		msg.setMsgTypeName(MsgTypeEnum.Sms.getName());
		msg.setMsgTime(DateUtil.getCurrDateTime());
		msg.setMsgTitle("");
		msg.setMsgContent(StringUtil.defaultString(content));
		msg.setReceiver(receiver);
		msg.setSendFlag(GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(msg, true);		
		pubMsgMapper.insert(msg);
	}

	@Override
	public void addSysMsg(String receiver,String title,String content){
		PubMsg msg = new PubMsg();
		String msgFlow = PkUtil.getUUID();
		msg.setMsgFlow(msgFlow);
		msg.setMsgCode(msgFlow);
		msg.setMsgTypeId(MsgTypeEnum.Sys.getId());
		msg.setMsgTypeName(MsgTypeEnum.Sys.getName());
		msg.setMsgTime(DateUtil.getCurrDateTime());
		msg.setMsgTitle(StringUtil.defaultString(title));
		msg.setMsgContent(StringUtil.defaultString(content));
		msg.setReceiver(receiver);
		msg.setSendFlag(GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(msg, true);		
		pubMsgMapper.insert(msg);
	}

	@Override
	public void addWeixinMsg(String receiver, String title, String content) {
		PubMsg msg = new PubMsg();
		String msgFlow = PkUtil.getUUID();
		msg.setMsgFlow(msgFlow);
		msg.setMsgCode(msgFlow);
		msg.setMsgTypeId(MsgTypeEnum.Weixin.getId());
		msg.setMsgTypeName(MsgTypeEnum.Weixin.getName());
		msg.setMsgTime(DateUtil.getCurrDateTime());
		msg.setMsgTitle(StringUtil.defaultString(title));
		msg.setMsgContent(StringUtil.defaultString(content));
		msg.setReceiver(receiver);
		msg.setSendFlag(GlobalConstant.FLAG_N);
		GeneralMethod.setRecordInfo(msg, true);		
		pubMsgMapper.insert(msg);
		
	}

	@Override
	public Integer countErrorMsg(String msgType) {
		PubMsgExample example = new PubMsgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andMsgTypeIdEqualTo(msgType).andSendFlagEqualTo(GlobalConstant.FLAG_N);
		return pubMsgMapper.countByExample(example);
	}

//	@Override
//	public List<PubMsg> searchMessage(PubMsgExample example) {
//		return pubMsgMapper.selectByExample(example);
//	}

	@Override
	public List<PubMsg> searchMessageWithBLOBs(PubMsgExample example) {
		return pubMsgMapper.selectByExampleWithBLOBs(example);
	}

//	@Override
//	public PubMsg readMsg(String msgFlow) {
//		return pubMsgMapper.selectByPrimaryKey(msgFlow);
//	}
	
	@Override
	public int updateMsg(PubMsg msg){
		GeneralMethod.setRecordInfo(msg, false);
		return pubMsgMapper.updateByPrimaryKeySelective(msg);
	}

	@Override
	public int saveMsg(PubMsg msg) {
		if(StringUtil.isNotBlank(msg.getMsgFlow())){
			GeneralMethod.setRecordInfo(msg, false);
			return pubMsgMapper.updateByPrimaryKey(msg);
		}else{
			msg.setMsgFlow(PkUtil.getUUID());
			msg.setMsgCode(msg.getMsgFlow());
			GeneralMethod.setRecordInfo(msg, true);
			return pubMsgMapper.insert(msg);
		}
	}

	@Override
	public String sendSMS(String mobiles, String smsTempFlow, String params, String relId, String relType) {
		SMSUtil util = new SMSUtil(mobiles);
		return util.sendSMS(smsTempFlow,params,relId,relType);
	}
}
