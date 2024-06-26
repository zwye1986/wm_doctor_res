package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.TjDocinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 天津住院医师结业考核准考证及结业证书在线打登录
 */
@Controller
@RequestMapping("/inx/tjResExam")
public class InxTjResExamController extends GeneralController{
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
        return "inx/tjresexam/login";
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
            return "inx/tjresexam/login";
        }
        removeValidateCodeAttribute();
        try{
            TjDocinfo docinfo = inxBiz.loginInfoTj(userCode, userPasswd);
            setSessionAttribute(GlobalConstant.CURRENT_USER, docinfo);
            return "redirect:/res/tjExam/index";
        }catch(RuntimeException re){
            loginErrorMessage = re.getMessage();
        }
        model.addAttribute("loginErrorMessage" , loginErrorMessage);
        InxInfo info = new InxInfo();
        PageHelper.startPage(1,2);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos",infos);
        return "inx/tjresexam/login";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/inx/tjResExam";
    }

}
