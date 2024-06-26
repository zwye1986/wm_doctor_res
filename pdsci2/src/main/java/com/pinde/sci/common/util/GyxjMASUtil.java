package com.pinde.sci.common.util;

import com.pinde.sci.wsld.gyxjgl.SMsgService;
import com.pinde.sci.wsld.gyxjgl.SMsgServiceLocator;
import com.pinde.sci.wsld.gyxjgl.SMsgSoapBindingStub;
import com.pinde.sci.wsld.gyxjgl.SMsg_PortType;

public class GyxjMASUtil {
    //广医大数据库ip
    private String dbIp = "10.158.211.35";
    //数据库名
    private String dbName = "mas";
    //数据库端口
    private String dbPort = "3306";
    //登录用户
    private String loginName = "pdyjs";
    //登录密码
    private String loginPwd = "pdyjs123321";
    //接口编码
    private  String apiCode = "pdyjs";
    //接收短信手机号
    private String [] mobiles;
    //短信内容
    private String content;
    //短信id,缺省值0
    private Long smId = Long.valueOf(0);

    public GyxjMASUtil(){};
    public GyxjMASUtil(String [] mobiles, String content){
        this.mobiles = mobiles;
        this.content = content;
    };
    public GyxjMASUtil(String apiCode, String loginName, String loginPwd, String [] mobiles, String content, Long smId){
        this.apiCode = apiCode;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.mobiles = mobiles;
        this.content = content;
        this.smId = smId;
    }

    /**
     * MT发送短信返回值
     * @return 0成功，其他失败
     * @throws Exception
     */
    public int sendSms() throws Exception{
        SMsgService service = new SMsgServiceLocator();
        SMsg_PortType client = service.getSMsg();
        SMsgSoapBindingStub stub = (SMsgSoapBindingStub)client;
        stub.init(dbIp,dbName,dbPort,loginName,loginPwd);
        return client.sendSM(apiCode,loginName,loginPwd,mobiles,content,smId);
    }

    public static void main(String[] args) throws Exception{
        String mobiles [] = {"13270708846"};
        String content = "您好，广州医科大学招生目录已经发布，贵单位共有拟招学术学位10人，拟招专业学位20人";
        GyxjMASUtil mas = new GyxjMASUtil(mobiles,content);
        System.out.println(mas.sendSms());
    }
}