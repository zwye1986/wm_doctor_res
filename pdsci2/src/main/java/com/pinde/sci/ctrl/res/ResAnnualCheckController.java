package com.pinde.sci.ctrl.res;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAnnualCheckBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.jszy.JszyResDocTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.ResAnnualAssessmentRecord;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserDept;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author william.li
 * @date 2017/12/29
 */
@Controller
@RequestMapping("/res/annualCheck")
public class ResAnnualCheckController extends GeneralController{

    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @Autowired
    private IResAnnualCheckBiz resAnnualCheckBiz;

    /**
     * 显示 填写须知
     * @param isShow
     * @param model
     * @return
     */
    @RequestMapping("/showGuide")
    public String showReminder(String isShow,Model model){
        model.addAttribute("isShow",isShow);
        return "res/professionalBase/guide";
    }

    /**
     * 不再显示 session赋值
     * @param isShow
     */
    @RequestMapping("/changeShow")
    @ResponseBody
    public String changeShow(String isShow){
        setSessionAttribute("isShow", isShow);
        return GlobalConstant.OPERATE_SUCCESSED;
    }


    /**
     * 年度考核成绩录入界面
     * @param roleFlag
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/getAnnualCheck")
    public String getAnnualCheck(String roleFlag, String deptName,HttpServletRequest request,Integer currentPage, String[] doctorTypeList,ResDoctorExt doctor, Model model){

        SysUser sysUser = GlobalContext.getCurrentUser();
        String userFlow = sysUser.getUserFlow();

        model.addAttribute("role",roleFlag);
        if("professionalBase".equals(roleFlag)){
            //从session中获取isShow的值
            String isShow = (String) getSessionAttribute("isShow");
            model.addAttribute("isShow",isShow);
        }

        if("doctor".equals(roleFlag)){
            doctor.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        }

        if("head".equals(roleFlag) || "secretary".equals(roleFlag)){
            //本科室
            List<String> deptFlows = new ArrayList<>();
            List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
            for(SysUserDept sud:userDepts){
                deptFlows.add(sud.getDeptFlow());
            }
            doctor.setDeptFlows(deptFlows);
        }

        String assessmentYear = "";
        if(null!=doctor.getAssessmentRecord() && StringUtil.isNotBlank(doctor.getAssessmentRecord().getAssessmentYear())){
            assessmentYear = doctor.getAssessmentRecord().getAssessmentYear();
        }else{
//            assessmentYear = DateUtil.getYear();
            assessmentYear="2017";
        }
        model.addAttribute("assessmentYear",assessmentYear);

        if(doctorTypeList!=null&&doctorTypeList.length>0){
            doctor.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
            model.addAttribute("doctorTypeIdList",Arrays.asList(doctorTypeList));
        }
        else{
            List<String> paramList = new ArrayList<>();
            List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
            for(SysDict s:dictList){
                if("ExternalEntrust".equals(s.getDictId())){
                    continue;
                }
                paramList.add(s.getDictId());
            }
            doctor.setDoctorTypeIdList(paramList);
        }
        //所在单位科室字段取值说明：
        // 本单位人取个人信息中的“所在科室”数据；委培单位人取“工作单位”数据；在校专硕取“在读院校；社会人为空”
        if(null!=doctorTypeList && doctorTypeList.length==1){
            //本单位人
            if(JszyResDocTypeEnum.Company.getId().equals(doctorTypeList[0])){
                doctor.setDepartMentName(deptName);
            }
            //委培单位人
            if(JszyResDocTypeEnum.CompanyEntrust.getId().equals(doctorTypeList[0])){
                doctor.setWorkOrgName(deptName);
            }
            //在校专硕
            if(JszyResDocTypeEnum.Graduate.getId().equals(doctorTypeList[0])){
                doctor.setWorkOrgName(deptName);
            }
        }
        ResAnnualAssessmentRecord record = new ResAnnualAssessmentRecord();
        record.setAssessmentYear("2017");
        doctor.setAssessmentRecord(record);

        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> resDoctorList = resDoctorBiz.searchDocUserForAnnualCheck(doctor);

        model.addAttribute("resDoctorList",resDoctorList);

        return "res/professionalBase/annualCheckRecord";
    }

    /**
     * 保存年度考核录入
     * @param assessmentRecord
     * @return
     */
    @RequestMapping("/saveAnnualCheck")
    @ResponseBody
    public String saveAnnualCheck(ResAnnualAssessmentRecord assessmentRecord){
        //考核年度 默认取当前年
//        String currentYear = DateUtil.getYear();
        //暂时默认取2017
        assessmentRecord.setAssessmentYear("2017");
        int count=resAnnualCheckBiz.saveAnnualCheck(assessmentRecord);
        if(count>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }


    /**
     * 上传文件至指定目录
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/uploadFile"})
    @ResponseBody
    public String uploadFile(String userFlow,MultipartFile file) throws Exception{
        if(file!=null && !file.isEmpty()){
            String resultPath = "";
            resultPath = resAnnualCheckBiz.saveFileToDirs("", file, "zseyFlies");

            //存入 年度考核成绩录入表
            ResAnnualAssessmentRecord record = new ResAnnualAssessmentRecord();
            record.setUserFlow(userFlow);
            record.setMaterialUrl(resultPath);
            record.setMaterialName(file.getOriginalFilename());
            record.setAssessmentYear(DateUtil.getYear());
            resAnnualCheckBiz.updateByUserFlowAndYear(record);
            return resultPath;
        }
        return GlobalConstant.UPLOAD_FAIL;
    }


    /**
     * 删除目录中上传文件
     * @param assessmentRecord
     * @return
     */
    @RequestMapping(value = {"/delFile"})
    @ResponseBody
    public String delFile(ResAnnualAssessmentRecord assessmentRecord){

        int count= resAnnualCheckBiz.deleteMatertialUrlAndFile(assessmentRecord);
        if(count>0){
            return GlobalConstant.DELETE_SUCCESSED;
        }

        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 导出年度考核成绩excel
     * @param response
     * @param doctorExt
     * @throws Exception
     */
    @RequestMapping("/recordExport")
    public void recordExport(HttpServletResponse response, String[] doctorTypeList, ResDoctorExt doctorExt,String role) throws Exception {

        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        //判断当前登录角色
        if("doctor".equals(role)){
            doctorExt.setDoctorFlow(userFlow);
        }else if("secretary".equals(role) || "head".equals(role)){
            List<String> deptFlows = new ArrayList<>();
            List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
            for(SysUserDept sud:userDepts){
                deptFlows.add(sud.getDeptFlow());
            }
            doctorExt.setDeptFlows(deptFlows);

            if(doctorTypeList!=null&&doctorTypeList.length>0){
                doctorExt.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
            }
        }else{
            if(doctorTypeList!=null&&doctorTypeList.length>0){
                doctorExt.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
            }
        else{
            List<String> paramList = new ArrayList<>();
            List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
            for(SysDict s:dictList){
                paramList.add(s.getDictId());
            }
            doctorExt.setDoctorTypeIdList(paramList);
        }
    }

        String[] titles = new String[]{
                "sysUser.userName:姓名",
                "sysUser.idNo:身份证号",
                "sessionNumber:入培时间",
                "graduationYear:结业时间",
                "assessmentRecord.dailyFinishScore:日常考核及出科考核",
                "assessmentRecord.trainingManualScore:培训手册填写",
                "assessmentRecord.professionalTheoryScore:专业理论成绩",
                "assessmentRecord.skillName:技能考核名称",
                "assessmentRecord.skillScore:技能成绩",
                "assessmentRecord.publicSkillsScore:公共技能成绩",
                "assessmentRecord.approvedTotalScore:核定总成绩",
                "assessmentRecord.memo:备注"
        };

        List<ResDoctorExt> resDoctorList = resDoctorBiz.searchDocUserForAnnualCheck(doctorExt);
        String fileName = "年度考核成绩.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, resDoctorList, response.getOutputStream());

    }


    /**
     * 打印年度考核成绩excel
     * @param response
     * @param doctorExt
     * @throws Exception
     */
    @RequestMapping("/printRecord")
    public void printRecord(HttpServletResponse response, String[] doctorTypeList, ResDoctorExt doctorExt) throws Exception {
        String[] titles = new String[]{
                "sysUser.userName:姓名",
                "sysUser.idNo:身份证号",
                "sessionNumber:入培时间",
                "graduationYear:结业时间",
                "assessmentRecord.dailyFinishScore:日常考核及出科考核",
                "assessmentRecord.trainingManualScore:培训手册填写",
                "assessmentRecord.professionalTheoryScore:专业理论成绩",
                "assessmentRecord.skillName:技能考核名称",
                "assessmentRecord.skillScore:技能成绩",
                "assessmentRecord.publicSkillsScore:公共技能成绩",
                "assessmentRecord.approvedTotalScore:核定总成绩",
                "assessmentRecord.memo:备注"
        };
        if(doctorTypeList!=null&&doctorTypeList.length>0){
            doctorExt.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
        }
//        else{
//            List<String> paramList = new ArrayList<>();
//            List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
//            for(SysDict s:dictList){
//                paramList.add(s.getDictId());
//            }
//            doctorExt.setDoctorTypeIdList(paramList);
//        }



        List<ResDoctorExt> resDoctorList = resDoctorBiz.searchDocUserForAnnualCheck(doctorExt);
        String fileName = doctorExt.getAssessmentRecord().getAssessmentYear()+doctorExt.getTrainingSpeName()+"专业基地年度考核成绩表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAndCustom(titles, resDoctorList, response.getOutputStream());



    }


    /**
     * 导入年度考核成绩excel
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/importCheckExcel")
    @ResponseBody
    public String importCheckExcel(MultipartFile file) throws Exception{
        if(file.getSize() > 0){
            try{
                int result = resAnnualCheckBiz.importCheckExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
}
