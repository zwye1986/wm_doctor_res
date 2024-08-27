package com.pinde.sci.common.util;

import com.pinde.sci.common.util.mo.EmailAccount;
import com.pinde.sci.common.util.mo.MessageInfo;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static boolean sslSend(MessageInfo msg1, EmailAccount emailAccount)
            throws AddressException, MessagingException, IOException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", emailAccount.getPlace());
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.connectiontimeout", "10 * 1000");
        props.setProperty("mail.smtp.timeout", "10 * 1000");
        props.put("mail.smtp.auth", "true");

        final String username = emailAccount.getUsername();
        final String password = emailAccount.getPassword();
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }});
        Message msg = new MimeMessage(session);

        if(msg1.getTo() == null || msg1.getTo().size() < 1){
            throw new AddressException("收信人为空！");
        }

        MimeMessageHelper messageHelper = new MimeMessageHelper((MimeMessage) msg,true,"UTF-8");
        messageHelper.setFrom(emailAccount.getUsername());// 发送人名片
        messageHelper.setTo(msg1.getTo().get(0));// 收件人邮箱
        messageHelper.setSubject(msg1.getSubject());// 邮件主题
        messageHelper.setSentDate(new Date());// 邮件发送时间
//        String context = "<html><head>" +
//                "<meta http-equiv='description' content=''><meta http-equiv='content-type' content='text/html; charset=UTF-8'>" +
//                "</head><body><div>"+msg1.getMsg()+"</div></body></html>";
        messageHelper.setText(msg1.getMsg(),true);
        Transport.send(msg);

        // 设置发件人和收件人
//        msg.setFrom(new InternetAddress(emailAccount.getUsername()));
//        List<String> tos = msg1.getTo();
//        Address to[] = new InternetAddress[tos.size()];
//        for(int i=0;i<tos.size();i++){
//            to[i] = new InternetAddress(tos.get(i));
//        }
//        // 多个收件人地址
//        msg.setRecipients(Message.RecipientType.TO, to);
//        msg.setSubject(msg1.getSubject()); // 标题
//        msg.setText(msg1.getMsg());// 内容
//        msg.setSentDate(new Date());
//        Transport.send(msg);
        System.out.println("EmailUtil ssl协议邮件发送打印" +msg.toString());
        return true;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("270503984@qq.com");
//
//        MessageInfo messageInfo = new MessageInfo();
//        messageInfo.setTo(list);
//        messageInfo.setSubject("8888");
//        messageInfo.setMsg("test888888");
//
//        EmailAccount emailAccount = new EmailAccount();
//        emailAccount.setPlace("smtp.qq.com");
//        emailAccount.setUsername("admin@njpdkj.com");
//        emailAccount.setPassword("tcakbqhcglaudejf");
//        try {
//            sslSend(messageInfo, emailAccount);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
