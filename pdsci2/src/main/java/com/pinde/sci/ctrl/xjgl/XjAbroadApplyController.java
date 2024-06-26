package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.IEduUserBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IAbroadApplyBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.xjgl.AbroadCategoryEnum;
import com.pinde.sci.form.xjgl.XjAbroadFamilyForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjAbroadApplyExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Copyright njpdxx.com
 * @since 2018/4/13
 */
@Controller
@RequestMapping("/xjgl/abroadApply")
public class XjAbroadApplyController extends GeneralController {

    @Autowired
    private IAbroadApplyBiz abroadApplyBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IEduUserBiz eduUserBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @RequestMapping("/abroadApplyList")
    public String abroadApplyList(String formType,String roleFlag,NygoAbroadApply abroadApply, Model model,Integer currentPage, HttpServletRequest request ){
        if("student".equals(roleFlag)){
            abroadApply.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if("tutor".equals(roleFlag)){
            abroadApply.setTutorFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if("fwh".equals(roleFlag)){
            abroadApply.setFwhOrgFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if("pydw".equals(roleFlag)){
            abroadApply.setPydwOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<NygoAbroadApply> dataList=abroadApplyBiz.searchApplyList(abroadApply);
        model.addAttribute("dataList",dataList);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        if("student".equals(roleFlag)){
            if("recordSheet".equals(formType)){ //备案登记表
                return "xjgl/internationalEdu/recordSheetList";
            }
            return "xjgl/internationalEdu/stuApplyLIst";
        }
        return "xjgl/internationalEdu/abroadStuList";
    }
    @RequestMapping("/editAbroadApply")
    public String editAbroadApply(String recordFlow,String roleFlag,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            NygoAbroadApply abroadApply=abroadApplyBiz.searchApplyByFlow(recordFlow);
            if(abroadApply!=null&&StringUtil.isNotBlank(abroadApply.getUserFlow())){
                EduUser eduUser=eduUserBiz.readEduUser(abroadApply.getUserFlow());
                SysUser sysUser=userBiz.readSysUser(abroadApply.getUserFlow());
                ResDoctor resDoctor=resDoctorBiz.readDoctor(abroadApply.getUserFlow());
                model.addAttribute("eduUser",eduUser);
                model.addAttribute("resDoctor",resDoctor);
                model.addAttribute("sysUser",sysUser);
            }
            model.addAttribute("abroadApply",abroadApply);
        }else{
            EduUser eduUser=eduUserBiz.readEduUser(GlobalContext.getCurrentUser().getUserFlow());
            ResDoctor resDoctor=resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("eduUser",eduUser);
            model.addAttribute("resDoctor",resDoctor);
            model.addAttribute("sysUser",GlobalContext.getCurrentUser());
        }
        model.addAttribute("roleFlag",roleFlag);
        return "xjgl/internationalEdu/abroadApplication";
    }
    @RequestMapping("/saveAbroadApply")
    @ResponseBody
    public String saveAbroadApply(String roleFlag,String formType,NygoAbroadApply abroadApply){
        if("tutor".equals(roleFlag)){
            if("return".equals(formType)){
                abroadApply.setTutorBackAuditDate(DateUtil.getCurrDate());
            }else{
                abroadApply.setTutorAuditDate(DateUtil.getCurrDate());
            }
        }
        if(StringUtil.isNotBlank(abroadApply.getStuLevelId())){
            if("master".equals(abroadApply.getStuLevelId())){
                abroadApply.setStuLevelName("硕士研究生");
            }
            if("doctor".equals(abroadApply.getStuLevelId())){
                abroadApply.setStuLevelName("博士研究生");
            }
        }
        if(StringUtil.isNotBlank(abroadApply.getGoAbroadId())){
            abroadApply.setGoAbroadName(AbroadCategoryEnum.getNameById(abroadApply.getGoAbroadId()));
        }
        int num=0;
        num=abroadApplyBiz.saveAbroadApply(abroadApply);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping("/delAbroadApply")
    @ResponseBody
    public String delAbroadApply(String recordFlow){
        int num=0;
        if(StringUtil.isNotBlank(recordFlow)){
            num=abroadApplyBiz.delAbroadApply(recordFlow);
        }
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    @RequestMapping("/showInfo")
    public String showInfo(String recordFlow,String roleFlag,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            NygoAbroadApply abroadApply=abroadApplyBiz.searchApplyByFlow(recordFlow);
            model.addAttribute("abroadApply",abroadApply);
        }
        model.addAttribute("recordFlow",recordFlow);
        model.addAttribute("roleFlag",roleFlag);
        return "xjgl/internationalEdu/showInfo";
    }

    /**
     * 编辑各项登记表
     * @return
     */
    @RequestMapping("/showEditSheet")
    public String showEditSheet(String recordFlow,String roleFlag,String formType,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            NygoAbroadApply abroadApply=abroadApplyBiz.searchApplyByFlow(recordFlow);
            model.addAttribute("abroadApply",abroadApply);
            if(abroadApply!=null&&StringUtil.isNotBlank(abroadApply.getFamilyMember())){
                XjAbroadApplyExt abroadApplyExt=abroadApplyBiz.parseAbroadFamilyXml(abroadApply.getFamilyMember());
                if(abroadApplyExt!=null){
                    model.addAttribute("familyFormList",abroadApplyExt.getFamilyFormList());
                }
            }
        }
        model.addAttribute("recordFlow",recordFlow);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("formType",formType);
        if("recordSheet".equals(formType)){
            return "xjgl/internationalEdu/recordSheet";
        }
        if("cost".equals(formType)){
            return "xjgl/internationalEdu/costSheet";
        }
        if("trip".equals(formType)){
            return "xjgl/internationalEdu/tripSheet";
        }
        if("return".equals(formType)){
            return "xjgl/internationalEdu/returnApplication";
        }
        return "xjgl/internationalEdu/recordSheet";
    }
    @RequestMapping("/saveSheet")
    @ResponseBody
    public String saveSheet(String roleFlag, String formType, XjAbroadApplyExt abroadApplyExt){
        int num=0;
        NygoAbroadApply abroadApply=abroadApplyExt.getAbroadApply();
        List<XjAbroadFamilyForm> formList=abroadApplyExt.getFamilyFormList();
        num=abroadApplyBiz.saveSheet(abroadApply,formList);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value="/showUploadFile")
    public String showUploadFile(String roleFlag, String recordFlow,Model model){
        NygoAbroadApply abroadApply=abroadApplyBiz.searchApplyByFlow(recordFlow);
        model.addAttribute("abroadApply",abroadApply);
        model.addAttribute("recordFlow",recordFlow);
        model.addAttribute("roleFlag",roleFlag);
        return "xjgl/internationalEdu/uploadFiles";
    }
    @RequestMapping(value="/uploadInvitationFile")
    @ResponseBody
    public String uploadInvitationFile(String recordFlow,String invitationType,MultipartFile file){
        if(file!=null && file.getSize() > 0){
            return abroadApplyBiz.uploadInvitationFile(recordFlow,invitationType,file);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping("/showTripInfo")
    public String showTripInfo(String recordFlow,String roleFlag,Model model){
        List<NygoAbroadSchedule> dataList=abroadApplyBiz.searchScheduleList(recordFlow);
        model.addAttribute("dataList",dataList);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("applyFlow",recordFlow);
        return "xjgl/internationalEdu/tripSheet";
    }
    @RequestMapping("/saveTripInfo")
    @ResponseBody
    public String saveTripInfo(@RequestBody List<NygoAbroadSchedule> list) throws Exception{
        int num=0;
        if(null!=list && !list.isEmpty()){
            for(NygoAbroadSchedule nas : list){
                num += abroadApplyBiz.saveAbroadSchedule(nas);
            }
        }
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping("/delTrip")
    @ResponseBody
    public String delTrip(String recordFlow){
        int num=0;
        if(StringUtil.isNotBlank(recordFlow)){
            num=abroadApplyBiz.delAbroadSchedule(recordFlow);
        }
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    @RequestMapping("/saveCost")
    @ResponseBody
    public String saveCost(NygoAbroadApply abroadApply){
        int num=0;
        num=abroadApplyBiz.saveAbroadApply(abroadApply);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 各项登记表删除操作
     * @param recordFlow
     * @param sheetType
     * @return
     */
    @RequestMapping("/delSheetInfo")
    @ResponseBody
    public String delSheetInfo(String recordFlow,String sheetType){
        int num=0;
        if(StringUtil.isNotBlank(recordFlow)){
            NygoAbroadApply abroadApply=new NygoAbroadApply();
            abroadApply.setRecordFlow(recordFlow);
            if("recordSheet".equals(sheetType)){//备案登记表
                abroadApply.setPoliticalStatus("");
                abroadApply.setPostDesc("");
                abroadApply.setHealthySituation("");
                abroadApply.setFamilyMember("");
                abroadApply.setGoAbroadDesc("");
            }
            if("costSheet".equals(sheetType)){//费用登记表
                abroadApply.setGjlfStandard("");
                abroadApply.setGjlfDay("");
                abroadApply.setGjlfPeople("");
                abroadApply.setGjlfSubtotal("");
                abroadApply.setGwcsjtfStandard("");
                abroadApply.setGwcsjtfDay("");
                abroadApply.setGwcsjtfPeople("");
                abroadApply.setGwcsjtfSubtotal("");
                abroadApply.setZsfStandard("");
                abroadApply.setZsfDay("");
                abroadApply.setZsfPeople("");
                abroadApply.setZsfSubtotal("");
                abroadApply.setHsfStandard("");
                abroadApply.setHsfDay("");
                abroadApply.setHsfPeople("");
                abroadApply.setHsfSubtotal("");
                abroadApply.setGzfStandard("");
                abroadApply.setGzfDay("");
                abroadApply.setGzfPeople("");
                abroadApply.setGzfSubtotal("");
                abroadApply.setOtherStandard("");
                abroadApply.setOtherDay("");
                abroadApply.setOtherPeople("");
                abroadApply.setOtherSubtotal("");
                abroadApply.setFeeDesc("");
            }
            if("returnSheet".equals(sheetType)){//回国登记表
                abroadApply.setCscCertificateNo("");
                abroadApply.setCultureSummary("");
                abroadApply.setBackTutorAdvice("");
                abroadApply.setTutorBackAuditDate("");
            }
            num=abroadApplyBiz.saveAbroadApply(abroadApply);
            if(num>0){
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping("/exportInfos")
    public void exportInfos(String formType,String roleFlag,NygoAbroadApply abroadApply,HttpServletResponse response)throws Exception {
        if("student".equals(roleFlag)){
            abroadApply.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if("tutor".equals(roleFlag)){
            abroadApply.setTutorFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if("fwh".equals(roleFlag)){
            abroadApply.setFwhOrgFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if("pydw".equals(roleFlag)){
            abroadApply.setPydwOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NygoAbroadApply> dataList=abroadApplyBiz.searchApplyList(abroadApply);
        String []titles = new String[]{
                "period:入学年级",
                "stuLevelName:培养层次",
                "stuNo:学号",
                "userName:姓名",
                "sexName:性别",
                "beginDate:出国开始时间",
                "endDate:出国结束时间",
                "majorName:专业名称",
                "pydwOrgName:培养单位",
                "tutorName:导师"
        };
        String fileName = "申请信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList, response.getOutputStream(),new String[]{"2"});
    }
}
