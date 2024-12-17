package com.pinde.sci.biz.inx;

import com.pinde.core.model.SysUser;
import com.pinde.core.model.TjDocinfo;
import com.pinde.core.model.ResDoctor;

public interface IInxBiz {
    SysUser login(String userCode, String passwd);
    SysUser gzzyjxresLogin(String userCode, String passwd);

    void regist(SysUser user);

    int sendEmail(String userFlow, String userEmail);
    //发送邮件,flag区别spring定时任务发送邮件
    int sendEmail(String userFlow, String userEmail,String flag);

    void activateUser(String userFlow);

    void saveSczyRegistUser(SysUser user);

    void saveRegistUser(SysUser user);

    void saveOsceRegistUser(SysUser user);

    void saveOsceRegistUser2(SysUser user,ResDoctor doctor);

    //新建一个OSCE考点管理员
    void saveOsceManager(SysUser user);

    //完善OSCE注册学员信息并提交申请记录
    void completeOsceRegistUser(SysUser user, ResDoctor doctor);

    void saveJXRegistUser(SysUser user);

    void saveJXRegistUserEN(SysUser user);

    void sendResetPassEmail(String userEmail, String userFlow);



    /**
     * 南京准考证打印登录
     * @param userCode
     * @param passwd
     * @return
     */
    TjDocinfo loginInfo(String userCode, String passwd);


    /**
     * 南京准考证打印
     * @param userCode
     * @param passwd
     * @return
     */
    TjDocinfo loginInfoNj(String userCode, String passwd);

    /**
     * @Department：研发部
     * @Description 弱密码替换成强密码
     * @Author Zjie
     * @Date 2020/9/16
     */
    String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd);
    //验证短信发送间隔
    boolean checkVerificationTime(String phone);
    //记录短信发送时间
    void saveVerificationCodeRecord(String phone);
}
