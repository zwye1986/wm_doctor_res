package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.jsres.JsResUserBalckListExtMapper;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author chenzuozhou
 *
 */
@Controller
@RequestMapping("/jsres/blackList")
@Slf4j
public class JsResUserBlackListController extends GeneralController {
    @Autowired
    private IJsResUserBlackBiz blackBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private JsResUserBalckListExtMapper jsResUserBalckListExtMapper;

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
     * 上传附件
     * @return
     */
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(){
        return "jsres/blackUploadFile";
    }
    /**
     * 上传附件
     * @return
     */
    @RequestMapping(value = "/checkUploadFile")
    public String checkUploadFile(MultipartFile uploadFile,Model model){
        SysUser user = GlobalContext.getCurrentUser();
        String resultPath = "";
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = blackBiz.checkImg(uploadFile);
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", fileResult);
            } else {
                resultPath = blackBiz.saveFileToDirs("", uploadFile, "blackListFile", "2022");
            }
            model.addAttribute("result", fileResult);
            model.addAttribute("resultPath", resultPath);
            model.addAttribute("fileName",uploadFile.getOriginalFilename());
        }
        return "jsres/blackUploadFile";
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, String recordFlow) throws Exception {
        JsresUserBalcklist jsresUserBalcklist = blackBiz.readUserBalcklist(recordFlow);
        String fileName = jsresUserBalcklist.getFileName();
        String fileUrl =jsresUserBalcklist.getAttachmentPath();
        String fileDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + fileUrl;
        File file = new File(fileDir);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileInputStream is = null;

        OutputStream os = null;
        try {
            is = new FileInputStream(file);
            os = new BufferedOutputStream(response.getOutputStream());
            byte[] b = new byte[(int) file.length()];
            int n;
            while ((n = is.read(b)) != -1) {
                os.write(b, 0, n);
            }
            os.flush();
        } catch (IOException e) {
            log.error("",e);
        } finally {
            if(is != null) is.close();
            if(os != null) os.close();
        }

    }
    /**
     * 获取黑名单信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/blackListInfo")
    public String blackListInfo(JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request,
                                Integer currentPage,String seeFlag,String orgFlow0) throws DocumentException {
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
                jsresUserBalcklist.setOrgFlow(user.getOrgFlow());
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs=orgBiz.searchAllSysOrg(org);
            orgs.add(s);
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
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount",orgList.size());
        SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org);
//        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//            model.addAttribute("countryOrgFlag","Y");
//            if(null != jointOrg && jointOrg.equals("checked")){
//                orgFlowList.add(jsresUserBalcklist.getOrgFlow());
//                jsresUserBalcklist.setOrgFlow("");
//                List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//                if(null != jointOrgs && jointOrgs.size() > 0){
//                    for(ResJointOrg so : jointOrgs){
//                        orgFlowList.add(so.getJointOrgFlow());
//                    }
//                }
//            }
//        }
        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow",orgFlow0);
        model.addAttribute("orgList",orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
//        List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList);
        List<JsresUserBalcklist> blackLists = jsResUserBalckListExtMapper.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList,"DoctorTrainingSpe");
        model.addAttribute("blackLists", blackLists);
        return "jsres/blackListInfo";
    }

    @RequestMapping(value = "/blackListInfoAcc")
    public String blackListInfoAcc(JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request,
                                Integer currentPage,String seeFlag,String orgFlow0) throws DocumentException {
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
                jsresUserBalcklist.setOrgFlow(user.getOrgFlow());
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs=orgBiz.searchAllSysOrg(org);
            orgs.add(s);
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
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount",orgList.size());
        SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org);
//        if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//            model.addAttribute("countryOrgFlag","Y");
//            if(null != jointOrg && jointOrg.equals("checked")){
//                orgFlowList.add(jsresUserBalcklist.getOrgFlow());
//                jsresUserBalcklist.setOrgFlow("");
//                List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//                if(null != jointOrgs && jointOrgs.size() > 0){
//                    for(ResJointOrg so : jointOrgs){
//                        orgFlowList.add(so.getJointOrgFlow());
//                    }
//                }
//            }
//        }
        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow",orgFlow0);
        model.addAttribute("orgList",orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
//        List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList);
        List<JsresUserBalcklist> blackLists = jsResUserBalckListExtMapper.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList,"AssiGeneral");
        model.addAttribute("blackLists", blackLists);
        return "jsres/blackListInfoAcc";
    }

    @RequestMapping(value="/blackListInfoMain")
    public String blackListInfoMain(Model model) {
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchOrgListNew(org);
        model.addAttribute("orgs", orgs);
        return "jsres/blackListInfoMain";
    }

    @RequestMapping(value="/blackListInfoMainAcc")
    public String blackListInfoMainAcc(Model model) {
        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchOrgListNew(org);
        model.addAttribute("orgs", orgs);
        return "jsres/blackListInfoMainAcc";
    }

    @RequestMapping(value = "/blackListInfoList")
    public String blackListInfoList(JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request,
                                Integer currentPage,String seeFlag,String orgFlow0,String jointOrgFlag,String cityId,String auditStatusId) {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
//        if(StringUtil.isNotBlank(sessionNumber)){
//            jsresUserBalcklist.setSessionNumber(sessionNumber);
//        }
        String roleFlag = getRoleFlag();
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(sessionNumber)) {
            String[] numbers = sessionNumber.split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                jsresUserBalcklist.setSessionNumber("");
            }
        }

        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if(StringUtil.isNotBlank(cityId)){
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);

        List<String> orgFlowList=new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if("Y".equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            if("Y".equals(jointOrgFlag)){
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(jsresUserBalcklist.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount",orgList.size());
        SysOrg org2 =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org2);

        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow",orgFlow0);
        model.addAttribute("orgList",orgList);
        if(StringUtil.isNotEmpty(roleFlag) && roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)){
            orgFlowList.add(user.getOrgFlow());
            orgFlowList = null;
        }


        PageHelper.startPage(currentPage, getPageSize(request));
        List<JsresUserBalcklist> blackLists = jsResUserBalckListExtMapper.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList, sessionNumbers, auditStatusId, "DoctorTrainingSpe");
        //List<JsresUserBalcklist> blackLists = blackBiz.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId);
        model.addAttribute("blackLists", blackLists);
        return "jsres/blackListInfoList";
    }


    @RequestMapping(value = "/blackListInfoListAcc")
    public String blackListInfoListAcc(JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request,
                                    Integer currentPage,String seeFlag,String orgFlow0,String jointOrgFlag,String cityId,String auditStatusId) {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
//        if(StringUtil.isNotBlank(sessionNumber)){
//            jsresUserBalcklist.setSessionNumber(sessionNumber);
//        }
        String roleFlag = getRoleFlag();
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(sessionNumber)) {
            String[] numbers = sessionNumber.split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                jsresUserBalcklist.setSessionNumber("");
            }
        }

        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if(StringUtil.isNotBlank(cityId)){
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);

        List<String> orgFlowList=new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if("Y".equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            if("Y".equals(jointOrgFlag)){
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(jsresUserBalcklist.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount",orgList.size());
        SysOrg org2 =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org2);

        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow",orgFlow0);
        model.addAttribute("orgList",orgList);
        if(StringUtil.isNotEmpty(roleFlag) && roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)){
            orgFlowList.add(user.getOrgFlow());
            orgFlowList = null;
        }


        PageHelper.startPage(currentPage, getPageSize(request));
        List<JsresUserBalcklist> blackLists = jsResUserBalckListExtMapper.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList, sessionNumbers, auditStatusId, "AssiGeneral");
        //List<JsresUserBalcklist> blackLists = blackBiz.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId);
        model.addAttribute("blackLists", blackLists);
        return "jsres/blackListInfoListAcc";
    }

    @RequestMapping(value = "/exportBlackList")
    public void exportBlackList(JsresUserBalcklist jsresUserBalcklist, String sessionNumber,HttpServletResponse response,HttpServletRequest request,
                                    Integer currentPage,String seeFlag,String orgFlow0,String jointOrgFlag,String cityId,String auditStatusId) throws Exception {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(sessionNumber)) {
            String[] numbers = sessionNumber.split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                jsresUserBalcklist.setSessionNumber("");
            }
        }

        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if(StringUtil.isNotBlank(cityId)){
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);

        List<String> orgFlowList=new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if("Y".equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            if("Y".equals(jointOrgFlag)){
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(jsresUserBalcklist.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());

        SysOrg org2 =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org2);

        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }

//        PageHelper.startPage(currentPage, getPageSize(request));
        //List<JsresUserBalcklist> blackLists = blackBiz.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId);
        List<JsresUserBalcklist> blackLists =jsResUserBalckListExtMapper.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId,"DoctorTrainingSpe");
        String[] titles = new String[]{
            "userName:姓名",
            "idNo:证件号",
            "orgName:原培训基地",
            "trainingSpeName:原培训专业",
            "sessionNumber:原培训届别"
        };
        String fileName = "黑名单信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, blackLists, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");

    }

    @RequestMapping(value = "/exportBlackListAcc")
    public void exportBlackListAcc(JsresUserBalcklist jsresUserBalcklist, String sessionNumber,HttpServletResponse response,HttpServletRequest request,
                                Integer currentPage,String seeFlag,String orgFlow0,String jointOrgFlag,String cityId,String auditStatusId) throws Exception {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(sessionNumber)) {
            String[] numbers = sessionNumber.split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                jsresUserBalcklist.setSessionNumber("");
            }
        }

        List<SysOrg> orgs=new ArrayList<SysOrg>();
        SysOrg org=new SysOrg();
        SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if(StringUtil.isNotBlank(cityId)){
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs=orgBiz.searchAllSysOrg(org);

        List<String> orgFlowList=new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if("Y".equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            if("Y".equals(jointOrgFlag)){
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(jsresUserBalcklist.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<SysOrg> orgList=new ArrayList<>();
        orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());

        SysOrg org2 =orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0,org2);

        if(StringUtil.isBlank(orgFlow0)){
            orgFlow0 = org.getOrgFlow();
        }else if("all".equals(orgFlow0)){
            orgFlowList.add(jsresUserBalcklist.getOrgFlow());
            jsresUserBalcklist.setOrgFlow("");
            if(orgList!=null&&orgList.size()>0){
                for (SysOrg so:orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        }else{
            jsresUserBalcklist.setOrgFlow(orgFlow0);
        }

//        PageHelper.startPage(currentPage, getPageSize(request));
        //List<JsresUserBalcklist> blackLists = blackBiz.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId);
        List<JsresUserBalcklist> blackLists =jsResUserBalckListExtMapper.searchInfo2(jsresUserBalcklist, userFlowList, orgFlowList,sessionNumbers,auditStatusId,"AssiGeneral");

        String[] titles = new String[]{
                "userName:姓名",
                "idNo:证件号",
                "orgName:原培训基地",
                "trainingSpeName:原培训专业",
                "sessionNumber:原培训届别"
        };
        String fileName = "黑名单信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, blackLists, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");

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
        if(jsresUserBalcklist.getRecordStatus().equals(GlobalConstant.FLAG_N)) {
            String userFlow=jsresUserBalcklist.getUserFlow();
            if(StringUtil.isNotBlank(userFlow)) {
                SysUser sysUser=userBiz.readSysUser(userFlow);
                if(sysUser!=null) {
                    sysUser.setRecordStatus(GlobalConstant.FLAG_Y);
                    userBiz.edit(sysUser);
                }
                ResDoctor resDoctor=doctorBiz.searchByUserFlow(userFlow);
                if(resDoctor!=null) {
                    resDoctor.setRecordStatus(GlobalConstant.FLAG_Y);
                    doctorBiz.editDoctor(resDoctor);
                }
            }
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 删除黑名单
     *
     * @param jsresUserBalcklist
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/removeBlackNew")
    public @ResponseBody String removeBlackNew(JsresUserBalcklist jsresUserBalcklist){
        blackBiz.saveBlack(jsresUserBalcklist);
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
        ModelAndView mav=new ModelAndView("jsres/doctor/addBlackList");
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
    public @ResponseBody String saveBlackList(String userIdNo, String reason,String userName,String attachmentPath,
            String fileName,String cretTypeId,String roleFlag){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysUser sysUser = new SysUser();
        sysUser = userBiz.findByIdNo(userIdNo);
//        if (sysUser == null) {
//            return "不能添加系统中不存在学员";
//        }
//        if (StringUtil.isBlank(userName) || !userName.equals(sysUser.getUserName())) {
//            return "学员姓名不正确";
//        }
        if("city".equals(roleFlag)){
            //市局不能添加系统中不存在学员
       /*     if(sysUser==null){
                return "不能添加系统中不存在学员";
            }*/
            //市局不能添加非本市学员
            String orgFlow = currentUser.getOrgFlow();
            if(StringUtil.isNotBlank(orgFlow)){
                SysOrg currentOrg = orgBiz.readSysOrg(orgFlow);
                String currentCityId = currentOrg.getOrgCityId();
                SysOrg userOrg = orgBiz.readSysOrg(sysUser.getOrgFlow());
                if(userOrg!=null){
                    String userCityId = userOrg.getOrgCityId();
                    if(StringUtil.isNotBlank(userCityId)){
                        if(!userCityId.equals(currentCityId)){
                            return "不能添加非本市学员";
                        }
                    }else {
                        return "不能添加非本市学员";
                    }
                }else {
                    return "不能添加非本市学员";
                }
            }else{
                return GlobalConstant.OPRE_FAIL;
            }
        }
        JsresUserBalcklist blackUser = blackBiz.searchInfoByIdNo(userIdNo);
        if(blackUser!=null){
            return "不能重复添加学员";
        }
        blackUser=new JsresUserBalcklist();
        blackUser.setAttachmentPath(attachmentPath);
        blackUser.setFileName(fileName);
        blackUser.setCretTypeId(cretTypeId);
        if(sysUser==null) {
            sysUser=new SysUser();
            sysUser.setIdNo(userIdNo);
            sysUser.setUserName(userName);
        }
        int count=doSave( sysUser,reason,blackUser,roleFlag);
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
    private int doSave(SysUser sysUser,String reason,JsresUserBalcklist black,String roleFlag){
        ResDoctor doctor = null;
        if(StringUtil.isNotBlank(sysUser.getUserFlow())) {
            doctor = doctorBiz.findByFlow(sysUser.getUserFlow());
            black.setUserFlow(sysUser.getUserFlow());
            black.setUserCode(sysUser.getUserCode());
            black.setUserPhone(sysUser.getUserPhone());
            black.setUserEmail(sysUser.getUserEmail());
            black.setIsSystem("Y");
        }else{
            black.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            black.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            black.setIsSystem("N");
        }
        if(doctor!=null) {
            black.setOrgFlow(doctor.getOrgFlow());
            black.setOrgName(doctor.getOrgName());
            black.setSessionNumber(doctor.getSessionNumber());
            black.setTrainingSpeId(doctor.getTrainingSpeId());
            black.setTrainingSpeName(doctor.getTrainingSpeName());
        }
        black.setUserName(sysUser.getUserName());
        black.setIdNo(sysUser.getIdNo());
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            black.setAuditStatusId("Passed");
            black.setAuditStatusName("审核通过");
        }else{
            black.setAuditStatusId("Auditing");
            black.setAuditStatusName("待审核");
        }
        black.setReason(reason);
        black.setOperTypeId("2");
        black.setOperTypeName("手动");
        black.setReasonYj("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
        black.setRecordStatus("Y");
       return blackBiz.saveBlack(black);
    }

    /**
     * 审核黑名单
     */
    @RequestMapping(value = "/auditBlack")
    public @ResponseBody String auditBlack(String recordFlow,String auditStatusId,String type) {
        JsresUserBalcklist jsresUserBalcklist = blackBiz.readUserBalcklist(recordFlow);
        jsresUserBalcklist.setAuditStatusId(auditStatusId);
        if("Remove".equals(type)){
            //是否移除黑名单
            if("Passed".equals(auditStatusId)){
                jsresUserBalcklist.setRecordStatus("N");
            }else if("NotPassed".equals(auditStatusId)){
                //审核不通过  状态改为移除前的审核通过状态
                jsresUserBalcklist.setAuditStatusId("Passed");
                jsresUserBalcklist.setAuditStatusName("审核通过");
            }
        }else {
            if ("Passed".equals(auditStatusId)) {
                jsresUserBalcklist.setAuditStatusName("审核通过");
            } else if ("NotPassed".equals(auditStatusId)) {
                jsresUserBalcklist.setRecordStatus("N");
            }
        }
        blackBiz.saveBlack(jsresUserBalcklist);
        if(jsresUserBalcklist.getRecordStatus().equals(GlobalConstant.FLAG_N)) {
            String userFlow=jsresUserBalcklist.getUserFlow();
            if(StringUtil.isNotBlank(userFlow)) {
                SysUser sysUser=userBiz.readSysUser(userFlow);
                if(sysUser!=null) {
                    sysUser.setRecordStatus(GlobalConstant.FLAG_Y);
                    userBiz.edit(sysUser);
                }
                ResDoctor resDoctor=doctorBiz.searchByUserFlow(userFlow);
                if(resDoctor!=null) {
                    resDoctor.setRecordStatus(GlobalConstant.FLAG_Y);
                    doctorBiz.editDoctor(resDoctor);
                }
            }
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }
    /***
     * 获取角色标识（如：global charge local 等）
     * @return
     */
    public String getRoleFlag(){
        List<Map<String,String>>  rolelistInfo=getRoles();
        List<String> roleUrlList=new ArrayList<>();
        for(Map<String,String> map:rolelistInfo){
            String roleurl = map.get("roleIndex");
            if(StringUtil.isNotBlank(roleurl)){
                //省级 主管部门  高校
                if("/jsres/manage/global".equals(roleurl) || "/jsres/manage/charge".equals(roleurl) || "/jsres/manage/university".equals(roleurl)){
                    String flag =roleurl.split("/")[3];
                    roleUrlList.add(flag);
                }
            }
        }
        if(roleUrlList.size()>0){
            if(roleUrlList.contains("global") && roleUrlList.contains("charge") ){
                throw new RuntimeException("请联系管理员，角色不能既是省级部门又是主管部门");
            }else if(roleUrlList.contains("global")  && roleUrlList.contains("university")){
                throw new RuntimeException("请联系管理员，角色不能既是省级部门又是高校");
            }else if(roleUrlList.contains("charge")  && roleUrlList.contains("university")){
                throw new RuntimeException("请联系管理员，角色不能既是主管部门又是高校");
            }else if(roleUrlList.contains("global") && roleUrlList.contains("charge")  && roleUrlList.contains("university")){
                throw new RuntimeException("请联系管理员，角色不能同时省级部门、主管部门、高校");
            }else{
                return roleUrlList.get(0);
            }
        }
        return "";
    }
    public List<Map<String,String>> getRoles(){
        List<Map<String,String>> roles=new ArrayList<>();
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
        for(String roleFlow : currRoleList) {
            Map<String, String> role = getRoleUrl(roleFlow);
            if (role != null) {
                roles.add(role);
            }
        }
        return roles;
    }
    public Map<String,String> getRoleUrl(String roleFlow){
        if (StringUtil.isNotBlank(roleFlow)){
            Map<String,String> role = new HashMap<String, String>();
            if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
                role.put("roleName",InitConfig.getSysCfgDesc("res_global_role_flow"));
                role.put("roleIndex","/jsres/manage/global");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
                role.put("roleName",InitConfig.getSysCfgDesc("res_qkzx_role_flow"));
                role.put("roleIndex","/jsres/manage/province");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
                role.put("roleName",InitConfig.getSysCfgDesc("res_bjw_role_flow"));
                role.put("roleIndex","/jsres/manage/province");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
                role.put("roleName",InitConfig.getSysCfgDesc("res_zyglj_role_flow"));
                role.put("roleIndex","/jsres/manage/province");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
                role.put("roleName",InitConfig.getSysCfgDesc("res_school_role_flow"));
                role.put("roleIndex","/jsres/manage/school");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门
                role.put("roleName",InitConfig.getSysCfgDesc("res_charge_role_flow"));
                role.put("roleIndex","/jsres/manage/charge");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                role.put("roleName",InitConfig.getSysCfgDesc("res_admin_role_flow"));
                role.put("roleIndex","/jsres/manage/local");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
                role.put("roleName",InitConfig.getSysCfgDesc("res_head_role_flow"));
                role.put("roleIndex","/jsres/kzr/index");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_secretary_role_flow"))) {//科秘
                role.put("roleName",InitConfig.getSysCfgDesc("res_secretary_role_flow"));
                role.put("roleIndex","/jsres/km/index");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
                role.put("roleName",InitConfig.getSysCfgDesc("res_teacher_role_flow"));
                role.put("roleIndex","/jsres/teacher/index");
            }else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                role.put("roleName",InitConfig.getSysCfgDesc("res_doctor_role_flow"));
                role.put("roleIndex","/jsres/doctor/index");
            }
            else if (roleFlow.equals(InitConfig.getSysCfg("res_university_role_flow"))) {//高校管理员角色
                role.put("roleName",InitConfig.getSysCfgDesc("res_university_role_flow"));
                role.put("roleIndex","/jsres/manage/university");
            }
            else if (roleFlow.equals(InitConfig.getSysCfg("res_university_manager_role_flow"))) {//高校管理员角色
                role.put("roleName",InitConfig.getSysCfgDesc("res_university_manager_role_flow"));
                role.put("roleIndex","/jsres/manage/university");
            }
            return role;
        }
        return null;
    }

}