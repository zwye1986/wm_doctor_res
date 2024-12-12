package com.pinde.sci.ctrl.osca;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.osca.IOscaOrgMenuBiz;
import com.pinde.sci.biz.osca.ISiteInformationBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.OscaOrgMenu;
import com.pinde.sci.model.mo.OscaOrgSpe;
import com.pinde.sci.model.osca.OscaOrgSpeExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/osca/orgSpeGlobal")
public class OscaOrgSpeGlobalController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private ISiteInformationBiz siteInformationBiz;
    @Autowired
    private IOscaOrgMenuBiz oscaOrgMenuBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IInxBiz iInxBiz;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String oscaOrgSpeList(Integer currentPage, OscaOrgSpeExt oscaOrgSpeExt, SysOrg sysOrg, HttpServletRequest request, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(currUser.getOrgFlow())){
            sysOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
        }
        List<SysOrg> orgCityList=siteInformationBiz.serachOrgCity(sysOrg);
        String orgName="";
        String orgProvId="";
        String orgCityId="";
        orgProvId=sysOrg.getOrgProvId();
        if(oscaOrgSpeExt!=null){
            sysOrg=oscaOrgSpeExt.getSysOrg();
            if(sysOrg==null){
                sysOrg =new SysOrg();
                sysOrg.setOrgProvId(orgProvId);
            }else if("All".equals(sysOrg.getOrgCityId())){
                orgName=oscaOrgSpeExt.getSysOrg().getOrgName();
                model.addAttribute("orgName",orgName);
            } else
            {
                orgName=oscaOrgSpeExt.getSysOrg().getOrgName();
                model.addAttribute("orgName",orgName);
                model.addAttribute("orgCityId",oscaOrgSpeExt.getSysOrg().getOrgCityId());
                orgProvId=sysOrg.getOrgProvId();
                orgCityId=sysOrg.getOrgCityId();
            }
        }
        List<OscaOrgSpeExt> oscaOrgSpeExtList=new ArrayList<>();
        Map<String, Object> map=new HashMap<>();
        map.put("orgName",orgName);
        map.put("orgProvId",orgProvId);
        map.put("orgCityId",orgCityId);
        map.put("withN", com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(oscaOrgSpeExt!=null&&oscaOrgSpeExt.isSearchSite()){
            map.put("isExamOrg", com.pinde.core.common.GlobalConstant.IS_EXAM_TEA_Y);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        oscaOrgSpeExtList=siteInformationBiz.searchAllOrg(map);
        if (oscaOrgSpeExtList!=null&&oscaOrgSpeExtList.size()>0){
            for (OscaOrgSpeExt oose:oscaOrgSpeExtList) {
                OscaOrgSpe os=new OscaOrgSpe();
                os.setOrgFlow(oose.getOrgFlow());
                List<OscaOrgSpe> listTemp=siteInformationBiz.searchOscaOrgSpeList(os);
                if (listTemp!=null&&listTemp.size()>0){
                    oose.setToSite(com.pinde.core.common.GlobalConstant.FLAG_Y);
                }else {
                    oose.setToSite(com.pinde.core.common.GlobalConstant.FLAG_N);
                }
            }
        }
        List<SysOrg> sysOrgList=siteInformationBiz.searchOrgList(sysOrg);
        List<OscaOrgSpe> oscaOrgSpeList=siteInformationBiz.searchOscaOrgSpeList(new OscaOrgSpe());
        model.addAttribute("orgCityList", orgCityList);
        model.addAttribute("sysOrgList", sysOrgList);
        model.addAttribute("oscaOrgSpeList", oscaOrgSpeList);
        model.addAttribute("oscaOrgSpeExtList",oscaOrgSpeExtList);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("orgProvId",orgProvId);
        return "osca/global/oscaOrgSpeList";
    }


    /**
     * 停用 启用 基地
     * @param orgFlow
     * @param recordStatus
     * @return
     */
    @RequestMapping(value="/oprOrg")
    @ResponseBody
    public String oprOrg(String orgFlow, String recordStatus) {
        SysOrg org  = new SysOrg();
        org.setOrgFlow(orgFlow);
        org.setRecordStatus(recordStatus);
        int num = orgBiz.update(org);
        if (num == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
    }


    @RequestMapping(value = {"/saveOrgSpe"})
    @ResponseBody
    public String saveOrgSpe(String orgFlow,Boolean checked,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        SysOrg sysOrg=orgBiz.readSysOrg(orgFlow);
        if(checked){
            sysOrg.setIsExamOrg(com.pinde.core.common.GlobalConstant.IS_EXAM_TEA_Y);
        }else {
            sysOrg.setIsExamOrg(com.pinde.core.common.GlobalConstant.IS_EXAM_TEA_N);
            Map<String, Object> map=new HashMap<>();
            map.put("orgFlow",orgFlow);
            map.put("modifyUserFlow",GlobalContext.getCurrentUser().getUserFlow());
            map.put("modifyTime",DateUtil.getCurrentTime());
            siteInformationBiz.removeOscaOrgSpe(map);
            //删除考点权限配置
            OscaOrgMenu oscaOrgMenu=new OscaOrgMenu();
            oscaOrgMenu.setOrgFlow(orgFlow);
            List<OscaOrgMenu> oscaOrgMenuTemp=oscaOrgMenuBiz.searchAllOrgMenu(oscaOrgMenu);
            if(oscaOrgMenuTemp!=null&&oscaOrgMenuTemp.size()>0){
                for(int i=0;i<oscaOrgMenuTemp.size();i++){
                    oscaOrgMenu=oscaOrgMenuTemp.get(i);
                    if(oscaOrgMenu!=null){
                        oscaOrgMenu.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                        oscaOrgMenuBiz.updateOscaOrgMenu(oscaOrgMenu);
                    }
                }
            }
        }
        sysOrg .setModifyUserFlow(currUser.getOrgFlow());
        sysOrg.setModifyTime(DateUtil.getCurrentTime());
        orgBiz.update(sysOrg);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("/editInfo")
    public String editInfo(String orgFlow,String orgName,Model model){
        OscaOrgSpe oscaOrgSpe=new OscaOrgSpe();
        oscaOrgSpe.setOrgFlow(orgFlow);
        List<OscaOrgSpe> oscaOrgSpeList=siteInformationBiz.searchOscaOrgSpeList(oscaOrgSpe);
        List<OscaOrgSpeExt> oscaOrgSpeExtList=new ArrayList<>();
        List<SysDict> speList = com.pinde.core.common.enums.DictTypeEnum.CheckSpe.getSysDictList();
            for (SysDict sd:speList) {
                OscaOrgSpeExt oscaOrgSpeExt=new OscaOrgSpeExt();
                if(oscaOrgSpeList!=null&&oscaOrgSpeList.size()>0){
                    for (OscaOrgSpe oos:oscaOrgSpeList) {
                        if(sd.getDictId().equals(oos.getSpeId())){
                            oscaOrgSpeExt.setAsMajor(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        }
                        oscaOrgSpeExt.setSpeId(sd.getDictId());
                        oscaOrgSpeExt.setSpeName(sd.getDictName());
                    }
                }else {
                    oscaOrgSpeExt.setSpeId(sd.getDictId());
                    oscaOrgSpeExt.setSpeName(sd.getDictName());
                }
                oscaOrgSpeExtList.add(oscaOrgSpeExt);
            }
        model.addAttribute("oscaOrgSpeList",oscaOrgSpeList);
        model.addAttribute("oscaOrgSpeExtList",oscaOrgSpeExtList);
        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("orgName",orgName);
        return "osca/global/editInfo";
    }

    @RequestMapping("/editInfo2")
    public String editInfo2(String orgFlow,Model model){
        OscaOrgSpe oscaOrgSpe=new OscaOrgSpe();
        oscaOrgSpe.setOrgFlow(orgFlow);
        List<OscaOrgSpe> oscaOrgSpeList=siteInformationBiz.searchOscaOrgSpeList(oscaOrgSpe);//已选类型和专业数据
        model.addAttribute("oscaOrgSpeList",oscaOrgSpeList);
        model.addAttribute("orgFlow",orgFlow);
        return "osca/global/editInfo2";
    }

    @RequestMapping(value = {"/saveEditInfo"})
    @ResponseBody
    public String saveEditInfo(String orgFlow,String data){
        //删除旧数据
        Map<String, Object> map=new HashMap<>();
        map.put("orgFlow",orgFlow);
        map.put("modifyUserFlow",GlobalContext.getCurrentUser().getUserFlow());
        map.put("modifyTime",DateUtil.getCurrentTime());
        siteInformationBiz.removeOscaOrgSpe(map);
        String[] deptCords={};
        OscaOrgSpe oscaOrgSpe=new OscaOrgSpe();
        String orgName="";
        SysOrg newSysOrg=orgBiz.readSysOrg(orgFlow);
        if (newSysOrg!=null){
            orgName=newSysOrg.getOrgName();
        }
        if(data!=null){
            deptCords=data.split(",");
            for(int i=0;i<deptCords.length;i++){
                    oscaOrgSpe.setOrgFlow(orgFlow);
                    oscaOrgSpe.setOrgName(orgName);
                    oscaOrgSpe.setSpeId(deptCords[i]);
                oscaOrgSpe.setSpeName(com.pinde.core.common.enums.DictTypeEnum.CheckSpe.getDictNameById(deptCords[i]));
                oscaOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    oscaOrgSpe.setCreateTime(DateUtil.getCurrentTime());
                    oscaOrgSpe.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                    siteInformationBiz.saveSiteInformation(oscaOrgSpe);
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/saveEditInfo2"})
    @ResponseBody
    public String saveEditInfo2(String jsonData){
        Map<String, Object> dataMap = JSON.parseObject(jsonData);
        List<Map<String, String>> mapList = (List<Map<String, String>>)dataMap.get("jsonData");
        String orgFlow = (String)dataMap.get("orgFlow");
        String orgName="";
        SysOrg newSysOrg=orgBiz.readSysOrg(orgFlow);
        if (newSysOrg!=null){
            orgName=newSysOrg.getOrgName();
        }
        OscaOrgSpe searchSpe = new OscaOrgSpe();
        searchSpe.setOrgFlow(orgFlow);
        List<OscaOrgSpe> oldOrgSpeList = siteInformationBiz.searchOscaOrgSpeList(searchSpe);//旧的配置数据

        boolean deleteFlag ;
        if(oldOrgSpeList!=null&&oldOrgSpeList.size()>0) {
            for (OscaOrgSpe oscaOrgSpe : oldOrgSpeList) {
                deleteFlag = true;
                for(Map<String, String> map:mapList){
                    String typeId = map.get("typeId");
                    String speId = map.get("speId");
                    if(oscaOrgSpe.getTypeId().equals(typeId)&&oscaOrgSpe.getSpeId().equals(speId)){
                        deleteFlag = false;
                        continue;
                    }
                }
                if(deleteFlag){
                    oscaOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                    siteInformationBiz.saveSiteInformation(oscaOrgSpe);
                }
            }
        }

        boolean saveFlag;
        if(mapList!=null&&mapList.size()>0){
            for(Map<String, String> map:mapList){
                saveFlag = true;
                String typeId = map.get("typeId");
                String typeName = map.get("typeName");
                String speId = map.get("speId");
                String speName = map.get("speName");
                if(oldOrgSpeList!=null&&oldOrgSpeList.size()>0){
                    for(OscaOrgSpe oscaOrgSpe:oldOrgSpeList){
                        if(oscaOrgSpe.getTypeId().equals(typeId)&&oscaOrgSpe.getSpeId().equals(speId)){
                            saveFlag = false;
                            continue;
                        }
                    }
                }
                if(saveFlag){
                    OscaOrgSpe save = new OscaOrgSpe();
                    save.setOrgFlow(orgFlow);
                    save.setOrgName(orgName);
                    save.setTypeId(typeId);
                    save.setTypeName(typeName);
                    save.setSpeId(speId);
                    save.setSpeName(speName);
                    siteInformationBiz.saveSiteInformation(save);
                }
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("/goAddOrg")
    public String goAddOrg(String orgProvId,Model model){
        SysOrg simple = new SysOrg();
        simple.setOrgProvId(orgProvId);
        model.addAttribute("sysOrg",simple);
        return "osca/global/addOrg";
    }

    @RequestMapping("/addOrg")
    public String addOrg(String orgFlow,String page,Model model){
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        model.addAttribute("page",page);
        model.addAttribute("sysOrg",sysOrg);
        return "osca/global/addOrg";
    }

    //考点管理员信息列表
    @RequestMapping("/managerlist")
    public String managerList(String orgCityId,String orgName,String userCode,Model model,Integer currentPage,
                              HttpServletRequest request){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgCityId",orgCityId);
        paramMap.put("orgName",orgName);
        paramMap.put("userCode",userCode);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> resultMapList = siteInformationBiz.searchManagerList(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(currUser.getOrgFlow())){
            SysOrg sysOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
            List<SysOrg> orgCityList=siteInformationBiz.serachOrgCity(sysOrg);
            model.addAttribute("orgCityList",orgCityList);
        }
        return "osca/global/oscaManagerList";
    }

    @RequestMapping("/editManager")
    public String editManager(String userFlow,Model model){
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        SysUser currentUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(currentUser.getOrgFlow())){
            SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            String provId = sysOrg.getOrgProvId();
            SysOrg search = new SysOrg();
            search.setIsExamOrg(com.pinde.core.common.GlobalConstant.FLAG_Y);
            search.setOrgProvId(provId);
            search.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<SysOrg> sysOrgList = orgBiz.searchOrg(search);
            model.addAttribute("sysOrgList",sysOrgList);
        }
        return "osca/global/editManager";
    }

    @RequestMapping("/saveManager")
    @ResponseBody
    public String saveManager(SysUser sysUser,String newFlag){
        SysUser user = null;
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(newFlag)) {
            user = userBiz.findByUserCode(sysUser.getUserCode());
            if(user != null){
                return "用户名重复！";
            }
            iInxBiz.saveOsceManager(sysUser);
        }else{
            user = userBiz.findByUserCodeNotSelf(sysUser.getUserFlow(),sysUser.getUserCode());
            if(user != null){
                return "用户名重复！";
            }
            userBiz.edit(sysUser);
        }
        return "1";
    }

    /**
     * 控制考点管理员角色 学员信息管理、考官信息管理菜单的按钮显示
     * @param orgFlow
     * @param checked
     * @param model
     * @return
     */
    @RequestMapping(value = {"/setShowFlg"})
    @ResponseBody
    public String setShowFlg(String role,String orgFlow,String isShow,Model model){

        SysOrg sysOrg=orgBiz.readSysOrg(orgFlow);
        if("doctor".equals(role)){
            sysOrg.setOsceDoctorShow(isShow);
        }else if("teacher".equals(role)){
            sysOrg.setOsceTeacherShow(isShow);
        }
        orgBiz.update(sysOrg);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

}
