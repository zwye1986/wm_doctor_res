package com.pinde.sci.ctrl.jszy;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyResUserBlackBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author chenzuozhou
 *
 */
@Controller
@RequestMapping("/jszy/blackList")
public class JszyResUserBlackListController extends GeneralController {
    @Autowired
    private IJszyResUserBlackBiz blackBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    public List<String> searchJointOrgList(String flag,String Flow) {
        List<String> jointOrgFlowList=new ArrayList<String>();
        if(GlobalConstant.FLAG_Y.equals(flag)){
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }
    /**
     * 获取黑名单信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/blackListInfo")
    public String blackListInfo(JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request, Integer currentPage,String seeFlag,String jointOrg,String[] datas) throws DocumentException {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(sessionNumber)){
            jsresUserBalcklist.setSessionNumber(sessionNumber);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)&&StringUtil.isBlank(seeFlag)) {
            jsresUserBalcklist.setOrgFlow(user.getOrgFlow());
        }
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org=new SysOrg();
            SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs=orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())&&getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
            if(orgs!=null&&!orgs.isEmpty()){
                for(SysOrg org:orgs){
                    orgFlowList.add(org.getOrgFlow());
                }
            }
        }
        SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("countryOrgFlag","Y");
            if(null != jointOrg && jointOrg.equals("checked")){
                orgFlowList.add(jsresUserBalcklist.getOrgFlow());
                jsresUserBalcklist.setOrgFlow("");
                List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
                if(null != jointOrgs && jointOrgs.size() > 0){
                    for(ResJointOrg so : jointOrgs){
                        orgFlowList.add(so.getJointOrgFlow());
                    }
                }
            }
        }
        Map<String,Object> param = new HashMap<>();
        param.put("jsresUserBalcklist",jsresUserBalcklist);
        param.put("userFlowList",userFlowList);
        param.put("orgFlowList",orgFlowList);
        param.put("docTypeList",docTypeList);
        PageHelper.startPage(currentPage, getPageSize(request));
//        List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList);
        List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(param);
        model.addAttribute("blackLists", blackLists);
        return "jszy/blackListInfo";
    }
    /**
     * 删除黑名单
     *
     * @param jsresUserBalcklist
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/removeBlack")
    public @ResponseBody String removeBlack(JsresUserBalcklist jsresUserBalcklist) throws DocumentException {
        blackBiz.saveBlack(jsresUserBalcklist);
        if(jsresUserBalcklist.getRecordStatus().equals(GlobalConstant.FLAG_N))
        {
            String userFlow=jsresUserBalcklist.getUserFlow();
            if(StringUtil.isNotBlank(userFlow))
            {
                SysUser sysUser=userBiz.readSysUser(userFlow);
                if(sysUser!=null)
                {
                    sysUser.setRecordStatus(GlobalConstant.FLAG_Y);
                    userBiz.edit(sysUser);
                }
                ResDoctor resDoctor=doctorBiz.searchByUserFlow(userFlow);
                if(resDoctor!=null)
                {
                    resDoctor.setRecordStatus(GlobalConstant.FLAG_Y);
                    doctorBiz.editDoctor(resDoctor);
                }
            }
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }
    /**
     * 跳转添加黑名单界面
     *
     * @param
     * @return mav
     * @throws
     */
    @RequestMapping(value={"/addBlackList"},method= RequestMethod.GET)
    public ModelAndView addBlackList(){
        ModelAndView mav=new ModelAndView("jszy/doctor/addBlackList");
        return mav;
    }
    /**
     * 保存黑名单
     *
     * @param
     * @return
     * @throws
     */
    @RequestMapping(value={"/saveBlackList"})
    public @ResponseBody String saveBlackList(String userIdNo, String reason,String userName){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysUser sysUser = new SysUser();
        sysUser = userBiz.findByIdNo(userIdNo);
        JsresUserBalcklist blackUser = blackBiz.searchInfoByIdNo(userIdNo);
        if(blackUser==null)
            blackUser=new JsresUserBalcklist();
        if(sysUser==null)
        {
            sysUser=new SysUser();
            sysUser.setIdNo(userIdNo);
            sysUser.setUserName(userName);
        }
        int count=doSave( sysUser,reason,blackUser);
        if(sysUser!=null&&count==1)
        {
            String userFlow=sysUser.getUserFlow();
            if(StringUtil.isNotBlank(userFlow))
            {
                sysUser.setRecordStatus(GlobalConstant.FLAG_N);
                userBiz.edit(sysUser);
                ResDoctor resDoctor=doctorBiz.searchByUserFlow(userFlow);
                if(resDoctor!=null)
                {
                    resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
                    doctorBiz.editDoctor(resDoctor);
                }
            }
        }
        if(count==1)
        {
            return GlobalConstant.OPERATE_SUCCESSED;
        }else{
            return GlobalConstant.OPRE_FAIL;
        }
    }
    private int doSave(SysUser sysUser,String reason,JsresUserBalcklist black){
        ResDoctor doctor = new ResDoctor();
        doctor = doctorBiz.findByFlow(sysUser.getUserFlow());
        black.setUserFlow(sysUser.getUserFlow());
        black.setUserCode(sysUser.getUserCode());
        black.setUserName(sysUser.getUserName());
        black.setUserPhone(sysUser.getUserPhone());
        black.setUserEmail(sysUser.getUserEmail());
        if(doctor!=null)
        {
            black.setOrgFlow(doctor.getOrgFlow());
            black.setOrgName(doctor.getOrgName());
            black.setSessionNumber(doctor.getSessionNumber());
            black.setTrainingSpeId(doctor.getTrainingSpeId());
            black.setTrainingSpeName(doctor.getTrainingSpeName());
        }
        black.setIdNo(sysUser.getIdNo());
        black.setReason(reason);
        black.setOperTypeId("2");
        black.setOperTypeName("手动");
        black.setReasonYj("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
        black.setRecordStatus("Y");
       return blackBiz.saveBlack(black);
    }

}