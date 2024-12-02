package com.pinde.sci.common.util;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.SysSmsLog;
import com.pinde.core.model.TestPaperExample;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysSmsLogMapper;
import com.pinde.sci.dao.base.SysSmsTemplateMapper;
import com.pinde.sci.model.mo.SysSmsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 短信发送服务
 *
 */
public class SMSUtil {
    /** 用户名常量 */
    public static final String UID = "njpdxx-test";
    /** 用户密码常量 */
    public static final String PWD = "e5cb510dd5beb4af0278164b0e5111aa";  //已经加密后的密码，接口里不需要再MD5加密！登录密码修改后接口密码也要同时修改

    /** 用户名 */
    private String uid;
    /** 用户密码 */
    private String pwd;
    /** 群发号码组 */
    private String mobile;
    /** 短信内容 */
    private String content;
    /** 短信最大长度 */
    private Integer length;
    /** 群发短信记录管理 */

    /**
     * 完全构造函数：进行密码的MD5加密，内容的URL编码
     *
     * @param mobile
     *            手机号码字符串（用","隔开）
     * @param content
     *            短信内容(未指定长度，默认长度参数为0)
     */
    public SMSUtil(String mobile, String content) {
        this.uid = UID;
        this.pwd = PWD;//PasswordHelper.encrypt32(PWD+UID);
        this.mobile = mobile;
        this.length = 70;
        if (content.length() > this.length) {
            content = content.substring(0, this.length - 1);
        }
        this.content = content;
    }

    /**
     * 完全构造函数：进行密码的MD5加密，内容的URL编码
     *
     * @param uid
     *            用户名
     * @param pwd
     *            用户密码
     * @param mobile
     *            手机号码字符串（用","隔开）
     * @param content
     *            短信内容(未指定长度，默认长度参数为0)
     */
    public SMSUtil(String uid, String pwd, String mobile, String content) {
        this.uid = uid;
        this.pwd = PWD;//PasswordHelper.encrypt32(pwd+uid);
        this.mobile = mobile;
        this.length = 70;
        if (content.length() > this.length) {
            content = content.substring(0, this.length - 1);
        }
        this.content = content;
    }

    /**
     * 完全构造函数：进行密码的MD5加密，内容的URL编码
     *
     * @param uid
     *            用户名
     * @param pwd
     *            用户密码
     * @param mobile
     *            手机号码字符串（用","隔开）
     * @param content
     *            短信内容
     * @param length
     *            短信内容最大长度
     */
    public SMSUtil(String uid, String pwd, String mobile, String content,
                   int length) {
        this.uid = uid;
        this.pwd = PWD;//PasswordHelper.encrypt32(pwd+uid);
        this.mobile = mobile;
        this.length = length < 76 ? 76 : length;
        if (content.length() > this.length) {
            content = content.substring(0, this.length - 1);
        }
        this.content = content;
    }


    /**
     * 发送信息的接口方法
     * @param smsLogFlow
     * @param smsTempFlow
     * @param relId
     * @param relType
     * @return
     */
    public SysSmsLog send(String smsLogFlow, String smsTempFlow, String relId, String relType) {
        return sendMessage(smsLogFlow, smsTempFlow, relId, relType);
    }

    /**
     * 发送信息的接口方法
     * @param smsLogFlow
     * @param smsTempFlow
     * @param relId
     * @return
     */
    public SysSmsLog send(String smsLogFlow, String smsTempFlow, String relId) {
        return sendMessage(smsLogFlow, smsTempFlow, relId, null);
    }

    /**
     * 发送信息的接口方法
     * @param smsLogFlow
     * @param smsTempFlow
     * @param relId
     * @return
     */
    public SysSmsLog send(String smsLogFlow, String smsTempFlow, String relId, Integer code) {
        return sendMessage(smsLogFlow, smsTempFlow, relId, null,code);
    }


    /**
     * 使用http协议发送信息
     * @param smsLogFlow
     * @param smsTempFlow
     * @param relId
     * @return
     */
    @SuppressWarnings("finally")
    private SysSmsLog sendMessage(String smsLogFlow, String smsTempFlow, String relId, String relType) {
        Log logger = LogFactory.getLog(SMSUtil.class);
        String result = "";
        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://api.sms.cn/sms/?");
        sb.append("ac=").append("send");
        // 向StringBuffer追加用户名
        sb.append("&uid=").append(this.getUid());
        // 向StringBuffer追加密码（密码采用MD5 32位 小写）
        sb.append("&pwd=").append(this.getPwd());
        // 向StringBuffer追加手机号码
        sb.append("&mobile=").append(this.getMobile());
        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content=").append(URLEncoder.encode(this.getContent()));
        sb.append("&encode=utf8");
//        System.out.println(sb.toString());
        try {
            // 创建url对象
            URL url = new URL(sb.toString());
            // 打开url连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置url请求方式 ‘get’ 或者 ‘post’
            connection.setRequestMethod("POST");
            // 发送
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            // 返回发送结果
            result = in.readLine().toString();
            System.out.println("result================");
            System.out.println(result);
            System.out.println("result================");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.info("编码转换异常");
        } catch (ProtocolException e) {
            e.printStackTrace();
            logger.info("协议使用异常");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("返回结果异常");
        } finally {
            Map<String, String> map = (Map<String, String>)JSON.parse(result);

            SysSmsLog sysSmsLog = new SysSmsLog();
            if(StringUtil.isNotBlank(smsLogFlow)){
                sysSmsLog.setSmsLogFlow(smsLogFlow);
            }
            sysSmsLog.setSmsUserName(this.getUid());
            sysSmsLog.setSmsSendTime(DateUtil.getCurrDateTime());
            sysSmsLog.setSmsContent(this.getContent());
            sysSmsLog.setSmsMobile(this.getMobile());
            sysSmsLog.setSmsReceiverCount(new BigDecimal(this.getMobile().split(",").length * (this.getContent().length() / 70 + 1)));
            sysSmsLog.setSmsTempFlow(smsTempFlow);
            sysSmsLog.setStatusCode(map.get("stat"));
            sysSmsLog.setStatusName(map.get("message"));
            sysSmsLog.setRelId(relId);
            if(StringUtil.isNotBlank(relType)) {
                sysSmsLog.setRelType(relType);
            }
            sysSmsLog.setSmsResponseMsg(result);
            return sysSmsLog;
        }
    }



    /**
     * 使用http协议发送信息(自定义验证码模板)
     * @param smsLogFlow
     * @param smsTempFlow
     * @param relId
     * @return
     */
    @SuppressWarnings("finally")
    private SysSmsLog sendMessage(String smsLogFlow, String smsTempFlow, String relId, String relType, Integer code) {
        Log logger = LogFactory.getLog(SMSUtil.class);
        String result = "";
        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://api.sms.cn/sms/?");
        sb.append("ac=").append("send");
        // 向StringBuffer追加用户名
        sb.append("&uid=").append(this.getUid());
        // 向StringBuffer追加密码（密码采用MD5 32位 小写）
        sb.append("&pwd=").append(this.getPwd());
        sb.append("&template=").append(smsTempFlow);
        // 向StringBuffer追加手机号码
        sb.append("&mobile=").append(this.getMobile());
        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content=").append(URLEncoder.encode("{\"code\":\""+code+"\"}"));

        sb.append("&encode=utf8");
//        System.out.println(sb.toString());
        try {
            // 创建url对象
            URL url = new URL(sb.toString());
            // 打开url连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置url请求方式 ‘get’ 或者 ‘post’
            connection.setRequestMethod("POST");
            // 发送
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            // 返回发送结果
            result = in.readLine().toString();
            System.out.println("result================");
            System.out.println(result);
            System.out.println("result================");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.info("编码转换异常");
        } catch (ProtocolException e) {
            e.printStackTrace();
            logger.info("协议使用异常");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("返回结果异常");
        } finally {
            Map<String, String> map = (Map<String, String>)JSON.parse(result);

            SysSmsLog sysSmsLog = new SysSmsLog();
            if(StringUtil.isNotBlank(smsLogFlow)){
                sysSmsLog.setSmsLogFlow(smsLogFlow);
            }
            sysSmsLog.setSmsUserName(this.getUid());
            sysSmsLog.setSmsSendTime(DateUtil.getCurrDateTime());
            sysSmsLog.setSmsContent(this.getContent());
            sysSmsLog.setSmsMobile(this.getMobile());
            sysSmsLog.setSmsTempFlow(smsTempFlow);
            sysSmsLog.setStatusCode(map.get("stat"));
            sysSmsLog.setStatusName(map.get("message"));
            sysSmsLog.setRelId(relId);
            if(StringUtil.isNotBlank(relType)) {
                sysSmsLog.setRelType(relType);
            }
            sysSmsLog.setSmsResponseMsg(result);
            return sysSmsLog;
        }
    }

    /**
     * 将返回状态编码转化为描述结果
     *
     * @param result
     *            状态编码
     * @return 描述结果
     */
    private String getResponse(String result) {
        Log logger = LogFactory.getLog(SMSUtil.class);
        if (result.equals("100")) {
            logger.info("发送成功");
            return "发送成功";
        }
        if (result.equals("101")) {
            logger.info("验证失败");
            return "验证失败";
        }
        if (result.equals("102")) {
            logger.info("短信不足");
            return "短信不足";
        }
        if (result.equals("103")) {
            logger.info("操作失败");
            return "操作失败";
        }
        if (result.equals("104")) {
            logger.info("非法字符");
            return "非法字符";
        }
        if (result.equals("105")) {
            logger.info("内容过多");
            return "内容过多";
        }
        if (result.equals("106")) {
            logger.info("号码过多");
            return "号码过多";
        }
        if (result.equals("107")) {
            logger.info("频率过快");
            return "频率过快";
        }
        if (result.equals("108")) {
            logger.info("号码内容空");
            return "号码内容空";
        }
        if (result.equals("109")) {
            logger.info("账号冻结");
            return "账号冻结";
        }
        if (result.equals("112")) {
            logger.info("号码不正确");
            return "号码不正确";
        }
        if (result.equals("116")) {
            logger.info("禁止接口发送");
            return "禁止接口发送";
        }
        if (result.equals("117")) {
            logger.info("绑定IP不正确");
            return "绑定IP不正确";
        }
        if (result.equals("161")) {
            logger.info("未添加短信模板");
            return "未添加短信模板";
        }
        if (result.equals("162")) {
            logger.info("模板格式不正确");
            return "模板格式不正确";
        }
        if (result.equals("163")) {
            logger.info("模板ID不正确");
            return "模板ID不正确";
        }
        if (result.equals("164")) {
            logger.info("全文模板不匹配");
            return "全文模板不匹配";
        }
        return result;
    }

    /**
     * 查询默认帐户剩余短信数量
     *
     * @return
     */
    public static String queryRemainingCount() {
        return queryRemainingCount(UID, PWD);
    }

    /**
     * 查询指定帐户剩余短信数量
     *
     * @param uid
     *            用户名
     * @param password
     *            用户密码
     * @return
     */
    @SuppressWarnings("finally")
    public static String queryRemainingCount(String uid, String password) {
        Log logger = LogFactory.getLog(SMSUtil.class);
        String result = "";
        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://api.sms.cn/sms/?");
        sb.append("ac=").append("number");
        // 向StringBuffer追加用户名
        sb.append("&uid=").append(uid);
        // 向StringBuffer追加密码（密码采用MD5 32位 小写）
//        sb.append("&pwd=").append(PasswordHelper.encrypt32(password+uid));
        sb.append("&pwd=").append(PWD);
//        System.out.println(sb.toString());
        try {
            // 创建url对象
            URL url = new URL(sb.toString());
            // 打开url连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            // 设置url请求方式 ‘get’ 或者 ‘post’
            connection.setRequestMethod("POST");

            // 发送
            BufferedReader in = new BufferedReader(new InputStreamReader(url
                    .openStream()));

            // 返回发送结果
            result = in.readLine().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.info("编码转换异常");
        } catch (ProtocolException e) {
            e.printStackTrace();
            logger.info("协议使用异常");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("返回结果异常");
        } finally {
            if (result.startsWith("{\"stat\":\"100")) {
                logger.info(result);
                logger.info("查询短信剩余数量成功，共" + result.substring(5));
            } else {
                logger.info("查询短信验证失败");
            }
            return result;
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    //短信接口实例化
    public SMSUtil(String mobile) {
        this.uid = UID;
        this.pwd = PWD;//PasswordHelper.encrypt32(PWD+UID);
        this.mobile = mobile;
        this.length = 70;
    }


    /**
     * 调用发送短信接口
     * @param smsTempFlow 模板流水号
     * @param params 多个参数用,拼接
     * @param relId 关联业务流水号
     * @param relType 关联业务表
     * @return
     */
    public String sendSMS(String smsTempFlow, String params,String relId, String relType) {
        String powerFlag = InitConfig.getSysCfg("sys_smsSendJob");
        String uid = InitConfig.getSysCfg("sys_sms_username");
        String pwd = InitConfig.getSysCfg("sys_sms_password");
        if (StringUtil.isBlank(powerFlag) || powerFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            return "短信开关未开启";
        }
        if(StringUtil.isBlank(uid) || StringUtil.isBlank(pwd)){
            return "短信账号或密码未配置";
        }
        this.uid = uid;
        this.pwd = PasswordHelper.encrypt32(pwd+uid);
        SysSmsTemplate temp = SpringUtil.getBean(SysSmsTemplateMapper.class).selectByPrimaryKey(smsTempFlow);
        if(null != temp && "1".equals(temp.getIsEnabled())){//1启用 2停用
            int smsCount = smsResidualCapacity();//用户可发送短信的剩余容量
            if(smsCount > 0){
                return sendMessage(temp,params,relId,relType);
            }else if(smsCount == -1){
                return "短信账号或密码配置错误";
            }else if(smsCount == -2){
                return "返回结果异常";
            }else{
                return "短信发送失败";
            }
        }else{
            return "该短信模板已停用";
        }
    }
    //查询用户可发送短信的剩余容量
    private int smsResidualCapacity() {
        String result = "";
        StringBuffer sb = new StringBuffer("http://api.sms.cn/sms/?");
        sb.append("ac=").append("number");
        sb.append("&uid=").append(this.uid);
        sb.append("&pwd=").append(this.pwd);
        try {
            URL url = new URL(sb.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开url链接
            connection.setRequestMethod("POST");//请求方式
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));//构建缓存阅读器
            result = in.readLine();//返回信息
        } catch (IOException e) {
            e.printStackTrace();
            return -2;//返回结果异常
        } finally {
            if (result.startsWith("{\"stat\":\"100")) {
                return Integer.valueOf(((Map<String, String>)JSON.parse(result)).get("number"));
            } else {
                return -1;//账号密码错误
            }
        }
    }
    //短信发送动作
    private String sendMessage(SysSmsTemplate temp, String params, String relId, String relType) {
        String tempContent = temp.getTempContent();
        int count = 0,start = 0;
        while (tempContent.indexOf("{**}",start) >= 0 && start < tempContent.length()) {
            count++;
            start = tempContent.indexOf("{**}",start) + "{**}".length();
        }
        if(params.split(",").length != count){
            return "该短信模板所需参数："+count;
        }
        if(count > 0){
            int i = 0;
            String pattern = "\\{\\*\\*\\}";
            Matcher m= Pattern.compile(pattern).matcher(tempContent);
            while(m.find()){
                tempContent=tempContent.replace(m.group(), params.split(",")[i]);
                i++;
            }
        }
        this.content = tempContent;
        String result = "";
        StringBuffer sb = new StringBuffer("http://api.sms.cn/sms/?");
        sb.append("ac=").append("send");
        sb.append("&uid=").append(this.uid);
        sb.append("&pwd=").append(this.pwd);
        sb.append("&mobile=").append(this.mobile);
        sb.append("&content=").append(URLEncoder.encode(this.content));
        sb.append("&encode=utf8");
        try {
            URL url = new URL(sb.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "返回结果异常";
        } finally {
            Map<String, String> map = (Map<String, String>)JSON.parse(result);//默认json格式
            SysSmsLog sysSmsLog = new SysSmsLog();
            sysSmsLog.setSmsLogFlow(PkUtil.getUUID());//发送日志流水号
            sysSmsLog.setSmsUserName(this.uid);
            sysSmsLog.setSmsSendTime(DateUtil.getCurrDateTime());
            sysSmsLog.setSmsMobile(this.mobile);
            sysSmsLog.setSmsContent(this.content);
            sysSmsLog.setSmsReceiverCount(new BigDecimal(this.mobile.split(",").length * (this.content.length() / 70 + 1)));
            sysSmsLog.setSmsTempFlow(temp.getSmsTempFlow());
            sysSmsLog.setStatusCode(map.get("stat"));
            sysSmsLog.setStatusName(map.get("message"));
            sysSmsLog.setRelId(relId);
            sysSmsLog.setRelType(relType);
            sysSmsLog.setSmsResponseMsg(result);
            GeneralMethod.setRecordInfo(sysSmsLog,true);
            SpringUtil.getBean(SysSmsLogMapper.class).insertSelective(sysSmsLog);
            return "短信发送成功";
        }
    }
}