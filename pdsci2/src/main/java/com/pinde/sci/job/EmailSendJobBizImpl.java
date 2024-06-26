package com.pinde.sci.job;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.EmailUtil;
import com.pinde.sci.common.util.mo.EmailAccount;
import com.pinde.sci.common.util.mo.MessageInfo;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.PubMsgMapper;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.PubMsg;
import com.pinde.sci.model.mo.PubMsgExample;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component 
@Transactional(rollbackFor=Exception.class)
public class EmailSendJobBizImpl {
	
	private static Logger logger = LoggerFactory.getLogger(EmailSendJobBizImpl.class);
	@Autowired
	private PubMsgMapper pubMsgMapper;
	@Autowired
	private PubFileMapper pubFileMapper;

	@Value("#{configProperties['thread.switch']}") 
	private String threadSwitch;
	
	private JavaMailSenderImpl mailSender = null;
	
	public EmailSendJobBizImpl(){
		_init();
	}
	
	private void _init(){
		if(mailSender==null){
			try {
				mailSender = new JavaMailSenderImpl();
				mailSender.setHost(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_host")));
				int port = 25;
				port = Integer.parseInt(StringUtil.defaultIfEmpty(InitConfig.getSysCfg("sys_mail_port"),"25"));
				mailSender.setPort(port);
				Properties prop = new Properties();
				prop.setProperty("mail.smtp.auth", "true");
				mailSender.setJavaMailProperties(prop);
				mailSender.setUsername(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_username")));
				mailSender.setPassword(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_password")));
			} catch (Throwable e) {
				e.printStackTrace();
				mailSender = null;
			}
		}
	}
	
//	@Scheduled(cron="*/10 * * * * *") // todo moved
	public void doJob() {
//		logger.debug("starting EmailSendJobBizImpl doJob.....");
		if(!GlobalConstant.FLAG_Y.equals(threadSwitch)  || !StringUtil.isEquals(InitConfig.getSysCfg("sys_sysEmailSendJob"), GlobalConstant.FLAG_Y)){
			return;
		}	
		String maxErrorTimes = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("sys_mail_max_error_times"),"5").trim();
		Integer iMaxErrorTimes = Integer.valueOf(maxErrorTimes);
		PubMsgExample example = new PubMsgExample();
		PubMsgExample.Criteria criteria = example.createCriteria();
		criteria.andMsgTypeIdEqualTo(MsgTypeEnum.Email.getId());
		criteria.andReceiverIsNotNull();
		criteria.andSendFlagEqualTo(GlobalConstant.FLAG_N).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("SEND_ERROR_TIMES,CREATE_TIME");
		
		PageHelper.startPage(1, 10);
		
		List<PubMsg> pubMsgList = pubMsgMapper.selectByExample(example);
		for(PubMsg pubMsg : pubMsgList){
			pubMsg = pubMsgMapper.selectByPrimaryKey(pubMsg.getMsgFlow());
			try {
			    if(StringUtil.isBlank(pubMsg.getReceiver())){
                    pubMsg.setSendErrorTimes(100);
                    throw new Exception("收信人邮箱为空！");
                }
                if("2017年湖北省住院医师规范化培训招录个人信息审查结果通知".equals(pubMsg.getMsgTitle().trim()) ||
                   "2018年湖北省住院医师规范化培训招录个人信息审查结果通知".equals(pubMsg.getMsgTitle().trim())){
                    pubMsg.setSendErrorTimes(100);
                    throw new Exception("审查结果通知null");
                }


                String context = "<html><head>" +
                        "<meta http-equiv='description' content=''><meta http-equiv='content-type' content='text/html; charset=UTF-8'>" +
                        "</head><body><div>" + pubMsg.getMsgContent() + "</div></body></html>";

			    if("465".equals(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_port")))){
                    List<String> list = new ArrayList<>();
                    list.add(pubMsg.getReceiver());

                    MessageInfo messageInfo = new MessageInfo();
                    messageInfo.setTo(list);
                    messageInfo.setSubject(pubMsg.getMsgTitle());
                    messageInfo.setMsg(context);

                    EmailAccount emailAccount = new EmailAccount();
                    emailAccount.setPlace(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_host")));
                    emailAccount.setUsername(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_username")));
                    emailAccount.setPassword(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_password")));

                    EmailUtil.sslSend(messageInfo, emailAccount);
                }else {
                    _init();
                    MimeMessage mail = mailSender.createMimeMessage();
//				SimpleMailMessage mail = new SimpleMailMessage();
                    //multipart模式
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true, "UTF-8");

                    messageHelper.setFrom(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));// 发送人名片
                    messageHelper.setTo(pubMsg.getReceiver());// 收件人邮箱
                    messageHelper.setSubject(pubMsg.getMsgTitle());// 邮件主题
                    messageHelper.setSentDate(new Date());// 邮件发送时间
                    messageHelper.setText(context, true);

                    if (StringUtil.isNotBlank(pubMsg.getFileFlow())) {

                        final PubFile attachment = pubFileMapper.selectByPrimaryKey(pubMsg.getFileFlow());
                        InputStreamSource iss = new InputStreamSource() {
                            @Override
                            public InputStream getInputStream() throws IOException {
                                return new ByteArrayInputStream(attachment.getFileContent());
                            }
                        };
                        messageHelper.addAttachment(MimeUtility.encodeWord(attachment.getFileName() + "." + attachment.getFileSuffix()), iss);
                    }
                    mailSender.send(mail);
                }
				
				pubMsg.setSendResult("发送成功");
				pubMsg.setSendFlag(GlobalConstant.FLAG_Y);	
				pubMsg.setSender(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));
				pubMsg.setSendTime(DateUtil.getCurrDateTime());
				
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
				mailSender = null;
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
		
	}

}
