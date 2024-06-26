package com.pinde.sci.job;

import com.pinde.sci.dao.base.PubMsgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor=Exception.class)
public class ResAuditRemindSendJobBizImpl {
	
//	private static Logger logger = LoggerFactory.getLogger(ResAuditRemindSendJobBizImpl.class);
//	@Autowired
//	private PubMsgMapper pubMsgMapper;
//
//	@Value("#{configProperties['thread.switch']}")
//	private String threadSwitch;
	
//	@Scheduled(cron="*/10 * * * * *")
//	public void doJob() {
//
//	}
}
