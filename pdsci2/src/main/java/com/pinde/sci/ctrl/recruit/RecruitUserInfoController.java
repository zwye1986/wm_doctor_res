package com.pinde.sci.ctrl.recruit;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recruit/user")
public class RecruitUserInfoController extends GeneralController {

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IUserBiz userBiz;

    @RequestMapping("/ownerInfo")
    public String ownerInfo(Model model){
        //获取当前登录用户信息
        SysUser currentUser = GlobalContext.getCurrentUser();
         SysUser user=userBiz.readSysUser(currentUser.getUserFlow());
         model.addAttribute("user",user);
        return "recruit/user/ownerInfo";
    }

    @RequestMapping("/saveOwnerInfo")
    @ResponseBody
    public String saveOwnerInfo(SysUser registerUser ,  Model model){
        SysUser currentUser=GlobalContext.getCurrentUser();
        //是否已注册
        String userCode = registerUser.getUserCode();
        String userPhone = registerUser.getUserPhone();
        String idNo = registerUser.getIdNo();
        SysUser user = userBiz.findByUserCode(userCode.trim());
        if(user != null&&!user.getUserFlow().equals(registerUser.getUserFlow())){
            model.addAttribute("errorMsg" , GlobalConstant.USER_CODE_REPETE);
            return GlobalConstant.USER_CODE_REPETE;
        }
        user = userBiz.findByUserPhone(userPhone.trim());
        if(user != null&&!user.getUserFlow().equals(registerUser.getUserFlow())){
            model.addAttribute("errorMsg" , GlobalConstant.USER_PHONE_REPETE);
            return GlobalConstant.USER_PHONE_REPETE;
        }
        user = userBiz.findByIdNo(idNo.trim());
        if(user != null&&!user.getUserFlow().equals(registerUser.getUserFlow())){
            model.addAttribute("errorMsg" , GlobalConstant.USER_ID_NO_REPETE);
            return GlobalConstant.USER_ID_NO_REPETE;
        }
        this.userBiz.saveUser(registerUser);
        user = userBiz.readSysUser(registerUser.getUserFlow());
        setSessionAttribute(GlobalConstant.CURRENT_USER, user);
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/security"})
    public String security(Model model){
        //更新session中user信息
        SysUser user = (SysUser)getSessionAttribute(GlobalConstant.CURRENT_USER);
        user = userBiz.readSysUser(user.getUserFlow());
        setSessionAttribute(GlobalConstant.CURRENT_USER, user);
        return "recruit/user/security";
    }

    @RequestMapping(value = "/managers")
    public String managers(Integer currentPage, SysUser user,  String orgFlow, HttpServletRequest request, Model model) {
        if (currentPage == null) {
            currentPage = 1;
        }
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        List<SysUser> sysUserList = null;

        List<String> roleList = new ArrayList<String>();
        String tutorRoleFlow = InitConfig.getSysCfg("recruit_audit_role_flow");
        if(StringUtil.isNotBlank(tutorRoleFlow)){
            roleList.add(tutorRoleFlow);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUserList = userBiz.searchRecruitManagers(user, roleList);
        model.addAttribute("sysUserList",sysUserList);
        return "/recruit/user/managers";
    }
    @RequestMapping(value = "/doctors")
    public String doctors(Integer currentPage, SysUser user,  String orgFlow, HttpServletRequest request, Model model) {
        if (currentPage == null) {
            currentPage = 1;
        }
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        List<SysUser> sysUserList = null;

        List<String> roleList = new ArrayList<String>();
        String tutorRoleFlow = InitConfig.getSysCfg("recruit_doctor_role_flow");
        if(StringUtil.isNotBlank(tutorRoleFlow)){
            roleList.add(tutorRoleFlow);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUserList = userBiz.searchRecruitManagers(user, roleList);
        model.addAttribute("sysUserList",sysUserList);
        return "/recruit/user/doctors";
    }

    @RequestMapping(value={"/edit"})
    public String edit( String userFlow, String currentPage, Model model){
        model.addAttribute("currentPage",currentPage);
        SysUser logiunUser=GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(userFlow)){
            SysUser sysUser=userBiz.readSysUser(userFlow);
            model.addAttribute("sysUser",sysUser);
        }
        return "recruit/user/edit";
    }
    @RequestMapping(value={"/showDoctor"})
    public String showDoctor( String userFlow, String currentPage, Model model){
        SysUser sysUser=userBiz.readSysUser(userFlow);
        model.addAttribute("sysUser",sysUser);
        return "recruit/user/showDoctor";
    }

    @RequestMapping(value={"/save"},method= RequestMethod.POST)
    public @ResponseBody String save(SysUser user, String[] mulDeptFlow, String roleFlow, MultipartFile file, String isRe){
        //新增用户是判断
        if(StringUtil.isBlank(user.getUserFlow())){
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCode(user.getUserCode());
            if(old!=null){
                return GlobalConstant.USER_CODE_REPETE;
            }

            if(StringUtil.isNotBlank(user.getIdNo())){
                old = userBiz.findByIdNo(user.getIdNo());
                if(old!=null){
                    return GlobalConstant.USER_ID_NO_REPETE;
                }
            }

            if(StringUtil.isNotBlank(user.getUserPhone())){
                old = userBiz.findByUserPhone(user.getUserPhone());
                if(old!=null){
                    return GlobalConstant.USER_PHONE_REPETE;
                }
            }

            if(StringUtil.isNotBlank(user.getUserEmail())){
                old = userBiz.findByUserEmail(user.getUserEmail());
                if(old!=null){
                    return GlobalConstant.USER_EMAIL_REPETE;
                }
            }
        }else{
            String userFlow = user.getUserFlow();
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
            if(old!=null){
                return GlobalConstant.USER_CODE_REPETE;
            }

            if(StringUtil.isNotBlank(user.getIdNo())){
                old = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
                if(old!=null){
                    return GlobalConstant.USER_ID_NO_REPETE;
                }
            }

            if(StringUtil.isNotBlank(user.getUserPhone())){
                old = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
                if(old!=null){
                    return GlobalConstant.USER_PHONE_REPETE;
                }
            }
            if(StringUtil.isNotBlank(user.getUserEmail())){
                old = userBiz.findByUserEmailNotSelf(userFlow,user.getUserEmail());
                if(old!=null){
                    return GlobalConstant.USER_EMAIL_REPETE;
                }
            }
        }
        if(StringUtil.isNotBlank(user.getSexId())){
            user.setSexName(UserSexEnum.getNameById(user.getSexId()));
        }
        user.setTitleName(DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
        user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
        user.setCertificateLevelName(DictTypeEnum.Certificatelevel.getDictNameById(user.getCertificateLevelId()));
        user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
        user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));
        user.setTeacherTypeName(DictTypeEnum.TeachersType.getDictNameById(user.getTeacherTypeId()));

        if(StringUtil.isNotBlank(roleFlow)){
            userBiz.saveUser(user,roleFlow);
        }else{
            userBiz.saveUser(user);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

}
