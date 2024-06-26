package com.pinde.sci.job;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Component 
@Transactional(rollbackFor=Exception.class)

public class ResJobBizImpl {
	private static Logger logger = LoggerFactory.getLogger(ResJobBizImpl.class);
	
	@Autowired
	private SysUserExtMapper userExtMapper;
	
	@Value("#{configProperties['thread.switch']}") 
	private String threadSwitch;
	@Value("#{configProperties['jswjw.res.haveNotRegOverMonth.job.switch']}") 
	private String haveNotRegSwitch;

//	@Scheduled(cron="0 0 1 1 * ?") // todo moved
	public void doJob() throws Exception {
		if(!GlobalConstant.FLAG_Y.equals(threadSwitch) || !GlobalConstant.FLAG_Y.equals(haveNotRegSwitch)){
			return;
		}	
		String currTime = DateUtil.getCurrDateTime();DateUtil.getCurrDate();
		
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date = sdf.parse(currTime);
	    
	    c.setTime(date);
	    c.add(Calendar.MONTH,-1);
	    
	    String beforeMonth = sdf.format(c.getTime());
	    beforeMonth = beforeMonth.substring(0,6);
	    
	    userExtMapper.lockHaveNotRegOverMonth(beforeMonth);
	}
}
