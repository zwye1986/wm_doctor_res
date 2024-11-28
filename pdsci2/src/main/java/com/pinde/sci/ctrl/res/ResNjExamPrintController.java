package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.OrgTypeEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.INjResExamBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.jsres.SysUserJsresMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.NjDocinfoExt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/res/njExam")
public class ResNjExamPrintController extends GeneralController{
    @Autowired
    private INjResExamBiz resExamBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private SysUserJsresMapper sysUserMapper;
    @Autowired
    private SysOrgMapper orgMapper;

    @RequestMapping("/index")
    public String showIndex(HttpServletRequest request, TjDocinfo searchDocinfo,Model model,Integer currentPage){
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        searchDocinfo.setDocrole("1");
        if(docinfo.getDocrole().equals("1")){
            if(StringUtil.isNotBlank(docinfo.getUserId())){
                searchDocinfo.setUserId(docinfo.getUserId());
                List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
                //用户头像不存在,使用sys_user表中的头像
                NjDocinfoExt docinfo1 = extList.get(0);
                SysUser sysUser = sysUserMapper.selectByIdNO(docinfo.getIdNo());
                if (sysUser != null) {
                    docinfo1.setPhonePath(sysUser.getUserHeadImg());
                }
                model.addAttribute("docinfo",docinfo1);
            }
            return "/njresexam/index";
        }
        if(currentPage==null){
            currentPage=1;
        }
        if(docinfo.getDocrole().equals("2")){
            if(StringUtil.isNotBlank(docinfo.getOrgCode())) {
                searchDocinfo.setOrgCode(docinfo.getOrgCode());
                List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
                model.addAttribute("extList",extList);
            }
            return "/njresexam/adminIndex";
        }
        if(docinfo.getDocrole().equals("3")){
            PageHelper.startPage(currentPage,10);
            List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
            model.addAttribute("extList",extList);
            return "/njresexam/adminIndex";
        }
        if(docinfo.getDocrole().equals("4")){//市级
            SysOrgExample sysOrgExample = new SysOrgExample();
            sysOrgExample.createCriteria().andOrgCityIdEqualTo(docinfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> sysOrgs = orgMapper.selectByExample(sysOrgExample);
            if(CollectionUtils.isEmpty(sysOrgs)){
                model.addAttribute("extList",new ArrayList<>());
                return "/njresexam/cityIndex";
            }
            List<String> orgs = sysOrgs.stream().map(SysOrg::getOrgName).collect(Collectors.toList());
            PageHelper.startPage(currentPage,10);
            List<NjDocinfoExt> extList = resExamBiz.readNjDocinfo(searchDocinfo,orgs);
            model.addAttribute("extList",extList);
            return "/njresexam/cityIndex";
        }
        return "";
    }

    @RequestMapping("/docInfo")
    public String docInfo(){
         return "/njresexam/docInfo";
    }

    @RequestMapping("/deleteRecord")
    @ResponseBody
    public String deleteRecord(String userFlow,String idNo){
        int i = resExamBiz.delDocInfo(userFlow, idNo);
        if(i > 0){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/docInfoList")
    public String docInfoList(HttpServletRequest request, TjDocinfo searchDocinfo,TjExamInfo tjExamInfo,Model model,Integer currentPage){
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        searchDocinfo.setDocrole("1");
        if(currentPage==null){
            currentPage=1;
        }
        if(docinfo.getDocrole().equals("4")){//市级
            List<SysOrg> sysOrgs = new ArrayList<>();
            String orgFlow = docinfo.getOrgFlow();
            if (StringUtil.isEmpty(orgFlow)) {
                orgFlow = "";
            }
            SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
            if (null == sysOrg) {
                SysOrgExample sysOrgExample = new SysOrgExample();
                sysOrgExample.createCriteria().andOrgCityIdEqualTo(docinfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                sysOrgs = orgMapper.selectByExample(sysOrgExample);
            } else {
                searchDocinfo.setCreateUserFlow(docinfo.getUserFlow());
            }
            List<String> orgs = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
            PageHelper.startPage(currentPage,10);
            List<NjDocinfoExt> extList = resExamBiz.readNjDocinfo(searchDocinfo,orgs);
            model.addAttribute("extList",extList);
            return "/njresexam/docInfoList";
        }
        return "";
    }

    @RequestMapping("/exportDocInfoList")
    public void exportDocInfoList(TjDocinfo searchDocinfo,HttpServletResponse response) throws IOException {
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        searchDocinfo.setDocrole("1");
        List<NjDocinfoExt> extList = new ArrayList<>();
        if(docinfo.getDocrole().equals("4")){//市级
            List<SysOrg> sysOrgs = new ArrayList<>();
            String orgFlow = docinfo.getOrgFlow();
            if (StringUtil.isEmpty(orgFlow)) {
                orgFlow = "";
            }
            SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
            if (null == sysOrg) {
                SysOrgExample sysOrgExample = new SysOrgExample();
                sysOrgExample.createCriteria().andOrgCityIdEqualTo(docinfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                sysOrgs = orgMapper.selectByExample(sysOrgExample);
            } else {
                searchDocinfo.setCreateUserFlow(docinfo.getUserFlow());
            }
            List<String> orgs = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
            extList = resExamBiz.readNjDocinfo(searchDocinfo,orgs);
        }
        resExamBiz.chargeExportList2(extList,response);
    }

    @RequestMapping("/examInfoList")
    public String examInfoList(HttpServletRequest request, TjExamInfo tjExamInfo,Model model,Integer currentPage){
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        String orgFlow = "";
        if(StringUtils.isNotEmpty(docinfo.getOrgFlow())){
            orgFlow = docinfo.getOrgFlow();
        }
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        if(null != sysOrg){
            tjExamInfo.setCreateUserFlow(docinfo.getUserFlow());
        }else {
            tjExamInfo.setCityCode(docinfo.getCityCode());
        }
        PageHelper.startPage(currentPage,10);
        List<TjExamInfo> tjExamInfos = resExamBiz.selTjExamInfos(tjExamInfo);
        model.addAttribute("tjExamInfos",tjExamInfos);
        return "/njresexam/examInfolList";
    }

    @RequestMapping("/examInfo")
    public String examInfo(){
        return "/njresexam/examInfo";
    }

    @RequestMapping("/editExamInfo")
    public String editExamInfo(String recordFlow,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            List<TjExamInfo> tjExamInfos = resExamBiz.selTjExamInfoById(recordFlow);
            if(tjExamInfos !=null && tjExamInfos.size()>0){
                model.addAttribute("examInfo",tjExamInfos.get(0));
            }
        }
        return "/njresexam/editExamInfo";
    }

    @RequestMapping(value="/saveExamInfo")
    @ResponseBody
    public String saveExamInfo(HttpServletRequest request, TjExamInfo examInfo){
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        if(examInfo==null){
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        int i = resExamBiz.editExamInfo(examInfo);
        if(i > 0){
            if (StringUtil.isNotEmpty(examInfo.getSpeName())) {
                TjDocinfo tjDocinfo = new TjDocinfo();
                List<SysOrg> sysOrgs = new ArrayList<>();
                String orgFlow = docinfo.getOrgFlow() == null ? "" : docinfo.getOrgFlow();
                SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
                if (sysOrg == null) {
                    SysOrgExample sysOrgExample = new SysOrgExample();
                    sysOrgExample.createCriteria().andOrgCityIdEqualTo(docinfo.getCityCode())
                            .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    sysOrgs = orgMapper.selectByExample(sysOrgExample);
                } else {
                    tjDocinfo.setCreateUserFlow(docinfo.getUserFlow());
                }
                tjDocinfo.setSpeName(examInfo.getSpeName());
                List<String> orgs;
                List<NjDocinfoExt> extList;
                if (CollectionUtils.isNotEmpty(sysOrgs) && StringUtils.isEmpty(tjDocinfo.getCreateUserFlow())) {
                    orgs = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
                    extList = resExamBiz.readNjDocinfo(tjDocinfo, orgs);
                } else {
                    extList = resExamBiz.readNjDocinfo(tjDocinfo, new ArrayList<>());
                }
                if (CollectionUtils.isNotEmpty(extList)) {
                    resExamBiz.editNjDocinfo(extList, examInfo);
                }
            }
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;

    }

    @RequestMapping("/searchDocInfos")
    public String searchDocInfos(TjDocinfo docinfo,Model model,Integer currentPage,String recordFlow){
        TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        SysOrgExample sysOrgExample = new SysOrgExample();
        sysOrgExample.createCriteria().andOrgCityIdEqualTo(currInfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg> sysOrgs = orgMapper.selectByExample(sysOrgExample);
        List<String> orgFlows = new ArrayList<>();
        docinfo.setDocrole("1");
        String orgFlow = "";
        if(StringUtils.isNotEmpty(currInfo.getOrgFlow())){
            orgFlow = currInfo.getOrgFlow();
            model.addAttribute("currOrgFlow",orgFlow);
        }
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        if(null != sysOrg){
            docinfo.setCreateUserFlow(currInfo.getUserFlow());
        }
        if(StringUtil.isEmpty(docinfo.getOrgFlow()) && null == sysOrg){
            orgFlows = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
        }
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,10);
        List<NjDocinfoExt> extList = resExamBiz.readNjDocinfo(docinfo,orgFlows);
        model.addAttribute("extList",extList);
        model.addAttribute("sysOrgs",sysOrgs);
        model.addAttribute("docinfo",docinfo);
        model.addAttribute("recordFlow",recordFlow);
        return "/njresexam/selDocInfo";
    }

    @RequestMapping("/searchDocInfoBySpe")
    public String searchDocInfoBySpe(TjDocinfo docinfo,Model model,Integer currentPage,String speName){
        TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        docinfo.setDocrole("1");
        if(currentPage==null){
            currentPage=1;
        }
        if(currInfo.getDocrole().equals("4")){//市级
            List<SysOrg> sysOrgs = new ArrayList<>();
            String orgFlow = currInfo.getOrgFlow();
            if (StringUtil.isEmpty(orgFlow)) {
                orgFlow = "";
            }
            SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
            if (null == sysOrg) {
                SysOrgExample sysOrgExample = new SysOrgExample();
                sysOrgExample.createCriteria().andOrgCityIdEqualTo(currInfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                sysOrgs = orgMapper.selectByExample(sysOrgExample);
            } else {
                docinfo.setCreateUserFlow(currInfo.getUserFlow());
            }
            List<String> orgs = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
            PageHelper.startPage(currentPage,10);
            List<NjDocinfoExt> extList = resExamBiz.readNjDocinfo(docinfo,orgs);
            model.addAttribute("extList",extList);
            model.addAttribute("sysOrgs",sysOrgs);
            model.addAttribute("docinfo",docinfo);
            model.addAttribute("speName",speName);
            return "/njresexam/showDocInfo";
        }
        return "";
    }

    @RequestMapping("/exportInfo")
    public void exportInfo(TjDocinfo docinfo, HttpServletResponse response) throws IOException {
        TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        docinfo.setDocrole("1");
        List<NjDocinfoExt> extList = new ArrayList<>();
        if(currInfo.getDocrole().equals("4")){//市级
            List<SysOrg> sysOrgs = new ArrayList<>();
            String orgFlow = currInfo.getOrgFlow();
            if (StringUtil.isEmpty(orgFlow)) {
                orgFlow = "";
            }
            SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
            if (null == sysOrg) {
                SysOrgExample sysOrgExample = new SysOrgExample();
                sysOrgExample.createCriteria().andOrgCityIdEqualTo(currInfo.getCityCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                sysOrgs = orgMapper.selectByExample(sysOrgExample);
            } else {
                docinfo.setCreateUserFlow(currInfo.getCreateUserFlow());
            }
            List<String> orgs = sysOrgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
            extList = resExamBiz.readNjDocinfo(docinfo,orgs);
        }
        resExamBiz.chargeExportList(extList,response);
    }

    @RequestMapping("/examCardImportTwo")
    public String examCardImportTwo(){
        return "/njresexam/examCardImportTwo";
    }

    @RequestMapping("/examUserImport")
    public String examUserImport(String examFlow,Model model){
        model.addAttribute("examFlow",examFlow);
        return "/njresexam/examUserImport";
    }

    @RequestMapping(value = {"/setExamInfo" })
    @ResponseBody
    public synchronized String setExamInfo(String resultFlows,String examFlow){
        int i = resExamBiz.setExamInfo(resultFlows,examFlow);
        if(i>0){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = {"/delExamInfo" })
    @ResponseBody
    public String delExamInfo(String examFlow){
        int i = resExamBiz.delExamInfo(examFlow);
        if(i>0){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/printCard")
    public String printCard(String userId,String flag,Model model){
        TjDocinfo searchDocinfo = new TjDocinfo();
        if(StringUtil.isNotBlank(userId)){
            searchDocinfo.setUserId(userId);
            List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
            //用户头像不存在,使用sys_user表中的头像
            NjDocinfoExt docinfo = extList.get(0);
            SysUser sysUser = sysUserMapper.selectByIdNO(docinfo.getIdNo());
            if (sysUser != null) {
                docinfo.setPhonePath(sysUser.getUserHeadImg());
            }

            model.addAttribute("docinfo",docinfo);
        }
        model.addAttribute("flag",flag);
        return "njresexam/printExamCard";
    }
    @RequestMapping("/editCard")
    public String editCard(String userFlow,String idNo,Model model){
        TjDocinfo searchDocinfo = new TjDocinfo();
        if(StringUtil.isNotBlank(userFlow)){
            searchDocinfo.setUserId(userFlow);
        }
        if(StringUtil.isNotBlank(idNo)){
            searchDocinfo.setIdNo(idNo);
        }

        List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
        if(extList !=null && extList.size()>0){
            model.addAttribute("docinfo",extList.get(0));
        }
        return "/njresexam/editExamCard";
    }
    @RequestMapping("/printCertificate")
    public String printCertificate(String userId,Model model){
        TjDocinfo searchDocinfo = new TjDocinfo();
        if(StringUtil.isNotBlank(userId)){
            searchDocinfo.setUserId(userId);
            List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(searchDocinfo);
            if(extList !=null && extList.size()>0){
                model.addAttribute("docinfo",extList.get(0));
            }
        }
        return "/njresexam/printCertificate";
    }

    @RequestMapping("/searchDocInfo")
    public String searchDocInfo(TjDocinfo docinfo,Model model){
        docinfo.setDocrole("1");
        List<NjDocinfoExt> extList = resExamBiz.searchNjDocinfoExt(docinfo);
        model.addAttribute("extList",extList);
        return "/njresexam/adminIndex";
    }

     @RequestMapping("/importDocInfoExcel")
     @ResponseBody
    public String importDocInfoExcel(MultipartFile file){
         if(file.getSize() > 0){
             try{
                 ExcelUtile result = resExamBiz.importDocInfoExcel(file);
                 if(null!=result)
                 {
                     String code= (String) result.get("code");
                     int count=(Integer) result.get("count");
                     String msg= (String) result.get("msg");
                     if("1".equals(code))
                     {
                         return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
                     }else{
                         if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                             return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                         }else{
                             return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                         }
                     }
                 }else {
                     return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                 }
             }catch(RuntimeException re){
                 re.printStackTrace();
                 return re.getMessage();
             }
         }
         return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;

     }


    @RequestMapping("/importExamUserExcel")
    @ResponseBody
    public String importExamUserExcel(MultipartFile file,String examFlow){
        if(file.getSize() > 0){
            try{
                ExcelUtile result = resExamBiz.importExamUserExcel(file,examFlow);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;

    }

     @RequestMapping(value="/saveDocInfo")
     @ResponseBody
    public String saveDocInfo(HttpServletRequest request, TjDocinfo docInfo){
         if(docInfo==null){
             return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
         }
         TjDocinfo tjDocinfo = resExamBiz.readDocInfo(docInfo.getIdNo());
         if (tjDocinfo != null
                 && !docInfo.getUserFlow().equalsIgnoreCase( tjDocinfo.getUserFlow())){
             return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
         }

         int i = resExamBiz.editDocInfo(docInfo);
         if(i == 0){
             return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
         }
         return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;

     }
    @RequestMapping("/examCardImport")
    public String examCardImport(){
         return "/njresexam/examCardImport";
     }


}
