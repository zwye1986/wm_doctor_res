package com.pinde.sci.common.util.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.util.Date;
import java.util.List;

public class MessageInfo extends TeachingActivitySpeakerExample.MybatisObject {

    //发件人地址
    private String  from;

    //收件人地址
    private List<String> to;

    //发送时间
    private Date sendDate;

    //邮件主题
    private String subject;

    //消息正文
    private String msg;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
