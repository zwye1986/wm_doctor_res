package com.pinde.sci.ctrl.inx;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TjDocinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 南京住院医师技能考核准考证
 */
@Controller
@RequestMapping("/inx/njresexam")
public class InxNjResExamController extends GeneralController{
    @Autowired
    private INoticeBiz noticeBiz;

    @Autowired
    private IInxBiz inxBiz;

    /**
     * 显示登陆界面
     * @return
     */
    @RequestMapping("")
    public String showLogin(Model model){
        InxInfo info = new InxInfo();
        PageHelper.startPage(1,2);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos",infos);
        return "inx/njresexam/login";
    }

    /**
     * 登陆
     * @return
     */
    @RequestMapping("/login")
    public String login(String userCode, String userPasswd,String verifyCode , Model model , HttpServletRequest request){
        //验证码输入不能为空，不区分大小写
        String loginErrorMessage = "";
        String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
        if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            loginErrorMessage = "验证码不正确";
            model.addAttribute("loginErrorMessage" , loginErrorMessage);
            InxInfo info = new InxInfo();
            PageHelper.startPage(1,2);
            List<InxInfo> infos = this.noticeBiz.findNotice(info);
            model.addAttribute("infos",infos);
            removeValidateCodeAttribute();
            return "inx/njresexam/login";
        }
        removeValidateCodeAttribute();
        try{
            TjDocinfo docinfo = inxBiz.loginInfo(userCode, userPasswd);
            SysUser sysUser = new SysUser();
            sysUser.setUserName(docinfo.getUserName());
            sysUser.setOrgName(docinfo.getOrgName());
            sysUser.setIdNo(docinfo.getIdNo());
            setSessionAttribute(GlobalConstant.CURRENT_USER, sysUser);
            setSessionAttribute("docinfo", docinfo);
            return "redirect:/res/njExam/index";
        }catch(RuntimeException re){
            loginErrorMessage = re.getMessage();
        }
        model.addAttribute("loginErrorMessage" , loginErrorMessage);
        InxInfo info = new InxInfo();
        PageHelper.startPage(1,2);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos",infos);
        return "inx/njresexam/login";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/inx/njresexam";
    }

}
