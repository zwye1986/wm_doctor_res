package com.pinde.sci.biz.pub;

import com.pinde.core.model.PubMsg;
import com.pinde.core.model.PubMsgExample;

import java.util.List;


public interface IMsgBiz {

//	public void addEdcInviteMsg(SysUser user);

    int addEmailMsg(String receiver, String title, String content);

	//发送邮件,flag区别spring定时任务发送邮件
	int addEmailMsg(String receiver, String title, String content,String flag);

    void addSmsMsg(String receiver, String content);

    void addSysMsg(String receiver, String title, String content);

    void addWeixinMsg(String receiver, String title, String content);

    Integer countErrorMsg(String msgType);

//	public List<PubMsg> searchMessage(PubMsgExample example);

//	public PubMsg readMsg(String msgFlow);

	List<PubMsg> searchMessageWithBLOBs(PubMsgExample example);

	int updateMsg(PubMsg msg); 

	int saveMsg(PubMsg msg);

	/**
	 * 调用短信接口
	 * @param mobiles 手机号
	 * @param smsTempFlow 模板流水号
	 * @param params 参数
	 * @param relId 业务流水号
	 * @param relType 业务表
     * @return
     */
	String sendSMS(String mobiles,String smsTempFlow, String params,String relId, String relType);
}
