package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.common.enums.OrgLevelEnum;
import com.pinde.core.common.enums.OrgTypeEnum;
import com.pinde.core.common.enums.TrainCategoryEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IGraduationDoctorStatisticsBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.jsres.GraduationDoctorStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/graduationStatistics")
public class GraduationDoctorStatisticsController extends GeneralController {

    @Autowired
    private IGraduationDoctorStatisticsBiz graduationDoctorStatisticsBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;
    @Autowired
    private IOrgBiz orgBiz;


    /**
     * @Department：研发部
     * @Description 结业学员统计
     * @Author fengxf
     * @Date 2025/2/13
     */
    @RequestMapping("/graduationDoctorStatisticsMain")
    public String graduationDoctorStatisticsMain(String roleFlag, String catSpeId, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        // 非基地管理员
        if(!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
            SysOrg sysOrg = new SysOrg();
            sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            sysOrg.setOrgProvId(org.getOrgProvId());
            sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
            // 市局
            if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                sysOrg.setOrgCityId(org.getOrgCityId());
            }
            List<SysOrg> orgList = orgBiz.searchOrgList(sysOrg);
            model.addAttribute("orgList", orgList);
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("catSpeId", catSpeId);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findTestConfigByCurrYear(DateUtil.getYear());
        model.addAttribute("resTestConfigList", resTestConfigList);
        return "jsres/asse/graduationStatisticsMain";
    }

    /**
     * @Department：研发部
     * @Description 结业学员统计
     * @Author fengxf
     * @Date 2025/2/14
     */
    @RequestMapping(value = "/graduationDoctorStatistics", method = {RequestMethod.POST})
    public String graduationDoctorStatistics(String queryType, String roleFlag, String sessionNumber, String doctorTypeId,
                                             String examType, String orgFlow, String catSpeId, String speIdArr, String testId, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("queryType",queryType);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("doctorTypeId", doctorTypeId);
        paramMap.put("examType", examType);
        paramMap.put("catSpeId", catSpeId);
        paramMap.put("testId", testId);
        if(StringUtil.isNotBlank(speIdArr)){
            paramMap.put("speIdArr", speIdArr.split(","));
        }
        // 基地管理员查自己基地的数据
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
            paramMap.put("orgFlow", currUser.getOrgFlow());
        }else{
            // 市局角色
            if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
                paramMap.put("orgCityId", org.getOrgCityId());
            }
            paramMap.put("orgFlow", orgFlow);
        }
        paramMap.put("applyYear", DateUtil.getYear());
        List<GraduationDoctorStatisticsInfo> graduationDoctorList = graduationDoctorStatisticsBiz.searchGraduationDoctorStatistics(paramMap);
        model.addAttribute("graduationDoctorList", graduationDoctorList);
        // 统计汇总信息
        Map<String, Object> statisticsMap = new HashMap<>();
        // 应结业人数
        statisticsMap.put("graduationNum", graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getGraduationNum()).sum());
        // 已申请人数
        statisticsMap.put("applyNum", graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getApplyNum()).sum());
        // 基地审核通过人数
        statisticsMap.put("localAuditNum", graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getLocalAuditNum()).sum());
        // 市局审核通过人数
        statisticsMap.put("cityAuditNum", graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getCityAuditNum()).sum());
        // 省厅审核通过人数
        statisticsMap.put("globalAuditNum", graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getGlobalAuditNum()).sum());
        model.addAttribute("statisticsMap", statisticsMap);
        model.addAttribute("catSpeId", catSpeId);
        // 培训专业列表
        if("orgStatistics".equals(queryType)){
            if(TrainCategoryEnum.DoctorTrainingSpe.getId().equals(catSpeId)){
                SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
                SysOrg sysOrg = new SysOrg();
                sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysOrg.setOrgProvId(org.getOrgProvId());
                sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                // 市局
                if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    sysOrg.setOrgCityId(org.getOrgCityId());
                }
                if(StringUtil.isNotBlank(orgFlow)){
                    sysOrg.setOrgFlow(orgFlow);
                }
                List<SysOrg> orgInfoList = orgBiz.searchOrgList(sysOrg);
                model.addAttribute("orgInfoList", orgInfoList);
            }
            return "jsres/asse/graduationStatisticsByOrg";
        }
        // 住院医师
        if(TrainCategoryEnum.DoctorTrainingSpe.getId().equals(catSpeId)){
            List<SysDict> dictList = DictTypeEnum.DoctorTrainingSpe.getSysDictList();
            if(StringUtil.isNotBlank(speIdArr)){
                dictList = dictList.stream().filter(dict -> speIdArr.contains(dict.getDictId())).collect(Collectors.toList());
            }
            model.addAttribute("dictList", dictList);
        }else{
            List<SysDict> dictList = DictTypeEnum.AssiGeneral.getSysDictList();
            if(StringUtil.isNotBlank(speIdArr)){
                dictList = dictList.stream().filter(dict -> speIdArr.contains(dict.getDictId())).collect(Collectors.toList());
            }
            model.addAttribute("dictList", dictList);
        }
        return "jsres/asse/graduationStatisticsBySpe";
    }

    /**
     * @Department：研发部
     * @Description 导出结业人员统计信息
     * @Author fengxf
     * @Date 2025/2/15
     */
    @RequestMapping(value = "/exportGraduationStatistics", method = {RequestMethod.POST})
    public void exportGraduationStatistics(String queryType, String roleFlag, String sessionNumber, String doctorTypeId,
                                             String examType, String orgFlow, String catSpeId, String speIdArr, String testId, HttpServletResponse response){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("queryType",queryType);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("doctorTypeId", doctorTypeId);
        paramMap.put("examType", examType);
        paramMap.put("catSpeId", catSpeId);
        paramMap.put("testId", testId);
        if(StringUtil.isNotBlank(speIdArr)){
            paramMap.put("speIdArr", speIdArr.split(","));
        }
        // 基地管理员查自己基地的数据
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
            paramMap.put("orgFlow", currUser.getOrgFlow());
        }else{
            // 市局角色
            if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
                paramMap.put("orgCityId", org.getOrgCityId());
            }
            paramMap.put("orgFlow", orgFlow);
        }
        paramMap.put("applyYear", DateUtil.getYear());
        List<GraduationDoctorStatisticsInfo> graduationDoctorList = graduationDoctorStatisticsBiz.searchGraduationDoctorStatistics(paramMap);
        // 按基地统计
        if("orgStatistics".equals(queryType)) {
            List<SysOrg> orgInfoList = null;
            if (TrainCategoryEnum.DoctorTrainingSpe.getId().equals(catSpeId)){
                SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
                SysOrg sysOrg = new SysOrg();
                sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysOrg.setOrgProvId(org.getOrgProvId());
                sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                // 市局
                if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    sysOrg.setOrgCityId(org.getOrgCityId());
                }
                if (StringUtil.isNotBlank(orgFlow)) {
                    sysOrg.setOrgFlow(orgFlow);
                }
                orgInfoList = orgBiz.searchOrgList(sysOrg);
            }
            exportGraduationStatisticsByOrg(orgInfoList, graduationDoctorList, response);
        }else{
            // 住院医师
            if(TrainCategoryEnum.DoctorTrainingSpe.getId().equals(catSpeId)){
                List<SysDict> dictList = DictTypeEnum.DoctorTrainingSpe.getSysDictList();
                if(StringUtil.isNotBlank(speIdArr)){
                    dictList = dictList.stream().filter(dict -> speIdArr.contains(dict.getDictId())).collect(Collectors.toList());
                }
                exportGraduationStatisticsBySpe(dictList, graduationDoctorList, response);
            }else{
                List<SysDict> dictList = DictTypeEnum.AssiGeneral.getSysDictList();
                if(StringUtil.isNotBlank(speIdArr)){
                    dictList = dictList.stream().filter(dict -> speIdArr.contains(dict.getDictId())).collect(Collectors.toList());
                }
                exportGraduationStatisticsBySpe(dictList, graduationDoctorList, response);
            }
        }
    }

    /**
     * @Department：研发部
     * @Description 按基地导出
     * @Author fengxf
     * @Date 2025/2/15
     */
    private void exportGraduationStatisticsByOrg(List<SysOrg> orgInfoList, List<GraduationDoctorStatisticsInfo> graduationDoctorList, HttpServletResponse response){
        try {
            // 存放数据列表
            List<GraduationDoctorStatisticsInfo> orgStatisticsList = new ArrayList<>();
            // 总计信息
            // 应结业人数
            int graduationNum = 0;
            // 已申请人数
            int applyNum = 0;
            // 基地审核通过人数
            int localAuditNum = 0;
            // 市局审核通过人数
            int cityAuditNum = 0;
            // 省厅审核通过人数
            int globalAuditNum = 0;
            if(orgInfoList != null){
                // 处理数据
                for (SysOrg orgInfo : orgInfoList) {
                    // 数据是否存在
                    String existsFlag = GlobalConstant.FLAG_N;
                    for (GraduationDoctorStatisticsInfo graduationDoctorInfo : graduationDoctorList) {
                        if (orgInfo.getOrgCode().equals(graduationDoctorInfo.getOrgCode())) {
                            existsFlag = GlobalConstant.FLAG_Y;
                            orgStatisticsList.add(graduationDoctorInfo);
                            graduationNum += graduationDoctorInfo.getGraduationNum();
                            applyNum += graduationDoctorInfo.getApplyNum();
                            localAuditNum += graduationDoctorInfo.getLocalAuditNum();
                            cityAuditNum += graduationDoctorInfo.getCityAuditNum();
                            globalAuditNum += graduationDoctorInfo.getGlobalAuditNum();
                        }
                    }
                    // 不存在
                    if (GlobalConstant.FLAG_N.equals(existsFlag)) {
                        GraduationDoctorStatisticsInfo tempInfo = new GraduationDoctorStatisticsInfo();
                        tempInfo.setOrgCode(orgInfo.getOrgCode());
                        tempInfo.setOrgName(orgInfo.getOrgName());
                        tempInfo.setGraduationNum(0);
                        tempInfo.setApplyNum(0);
                        tempInfo.setLocalAuditNum(0);
                        tempInfo.setCityAuditNum(0);
                        tempInfo.setGlobalAuditNum(0);
                        orgStatisticsList.add(tempInfo);
                    }
                }
            }else{
                // 应结业人数
                graduationNum = graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getGraduationNum()).sum();
                // 已申请人数
                applyNum = graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getApplyNum()).sum();
                // 基地审核通过人数
                localAuditNum = graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getLocalAuditNum()).sum();
                // 市局审核通过人数
                cityAuditNum = graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getCityAuditNum()).sum();
                // 省厅审核通过人数
                globalAuditNum = graduationDoctorList.stream().mapToInt(graduationInfo -> graduationInfo.getGlobalAuditNum()).sum();
                orgStatisticsList = graduationDoctorList;
            }

            GraduationDoctorStatisticsInfo statisticsInfo = new GraduationDoctorStatisticsInfo();
            statisticsInfo.setOrgCode("总计");
            statisticsInfo.setOrgName("");
            statisticsInfo.setGraduationNum(graduationNum);
            statisticsInfo.setApplyNum(applyNum);
            statisticsInfo.setLocalAuditNum(localAuditNum);
            statisticsInfo.setCityAuditNum(cityAuditNum);
            statisticsInfo.setGlobalAuditNum(globalAuditNum);
            orgStatisticsList.add(statisticsInfo);
            String[] titles = new String[]{
                "orgCode:基地编号",
                "orgName:基地名称",
                "graduationNum:应结业学员",
                "applyNum:已申请学员",
                "localAuditNum:基地审核通过",
                "cityAuditNum:市局审核通过",
                "globalAuditNum:省厅审核通过"
            };
            String fileName = "结业学员统计信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjsAllString(titles, orgStatisticsList, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Department：研发部
     * @Description 按基地导出
     * @Author fengxf
     * @Date 2025/2/15
     */
    private void exportGraduationStatisticsBySpe(List<SysDict> dictList, List<GraduationDoctorStatisticsInfo> graduationDoctorList, HttpServletResponse response){
        try {
            // 存放数据列表
            List<GraduationDoctorStatisticsInfo> speStatisticsList = new ArrayList<>();
            // 总计信息
            // 应结业人数
            int graduationNum = 0;
            // 已申请人数
            int applyNum = 0;
            // 基地审核通过人数
            int localAuditNum = 0;
            // 市局审核通过人数
            int cityAuditNum = 0;
            // 省厅审核通过人数
            int globalAuditNum = 0;
            // 处理数据
            for (SysDict dict : dictList) {
                // 数据是否存在
                String existsFlag = GlobalConstant.FLAG_N;
                for (GraduationDoctorStatisticsInfo graduationDoctorInfo : graduationDoctorList) {
                    if (dict.getDictId().equals(graduationDoctorInfo.getSpeId())) {
                        existsFlag = GlobalConstant.FLAG_Y;
                        speStatisticsList.add(graduationDoctorInfo);
                        graduationNum += graduationDoctorInfo.getGraduationNum();
                        applyNum += graduationDoctorInfo.getApplyNum();
                        localAuditNum += graduationDoctorInfo.getLocalAuditNum();
                        cityAuditNum += graduationDoctorInfo.getCityAuditNum();
                        globalAuditNum += graduationDoctorInfo.getGlobalAuditNum();
                    }
                }
                // 不存在
                if (GlobalConstant.FLAG_N.equals(existsFlag)) {
                    GraduationDoctorStatisticsInfo tempInfo = new GraduationDoctorStatisticsInfo();
                    tempInfo.setSpeId(dict.getDictId());
                    tempInfo.setSpeName(dict.getDictName());
                    tempInfo.setGraduationNum(0);
                    tempInfo.setApplyNum(0);
                    tempInfo.setLocalAuditNum(0);
                    tempInfo.setCityAuditNum(0);
                    tempInfo.setGlobalAuditNum(0);
                    speStatisticsList.add(tempInfo);
                }
            }
            GraduationDoctorStatisticsInfo statisticsInfo = new GraduationDoctorStatisticsInfo();
            statisticsInfo.setSpeId("总计");
            statisticsInfo.setSpeName("");
            statisticsInfo.setGraduationNum(graduationNum);
            statisticsInfo.setApplyNum(applyNum);
            statisticsInfo.setLocalAuditNum(localAuditNum);
            statisticsInfo.setCityAuditNum(cityAuditNum);
            statisticsInfo.setGlobalAuditNum(globalAuditNum);
            speStatisticsList.add(statisticsInfo);
            String[] titles = new String[]{
                    "speId:专业编码",
                    "speName:专业名称",
                    "graduationNum:应结业学员",
                    "applyNum:已申请学员",
                    "localAuditNum:基地审核通过",
                    "cityAuditNum:市局审核通过",
                    "globalAuditNum:省厅审核通过"
            };
            String fileName = "结业学员统计信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjsAllString(titles, speStatisticsList, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Department：研发部
     * @Description 应结业学员查询
     * @Author fengxf
     * @Date 2025/2/15
     */
    @RequestMapping("/searchGraduationDoctorList")
    public String searchGraduationDoctorList(String roleFlag, String sessionNumber, String doctorTypeId, String examType, String orgFlow,
                                             String catSpeId, String speIdArr, Integer currentPage, HttpServletRequest request, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("doctorTypeId", doctorTypeId);
        paramMap.put("examType", examType);
        paramMap.put("catSpeId", catSpeId);
        if(StringUtil.isNotBlank(speIdArr)){
            paramMap.put("speIdArr", speIdArr.split(","));
        }
        // 基地管理员查自己基地的数据
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
            paramMap.put("orgFlow", currUser.getOrgFlow());
        }else{
            paramMap.put("orgFlow", orgFlow);
            SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
            SysOrg sysOrg = new SysOrg();
            sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            sysOrg.setOrgProvId(org.getOrgProvId());
            sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
            // 市局
            if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                sysOrg.setOrgCityId(org.getOrgCityId());
                paramMap.put("orgCityId", org.getOrgCityId());
            }
            List<SysOrg> orgList = orgBiz.searchOrgList(sysOrg);
            model.addAttribute("orgList", orgList);
        }
        paramMap.put("applyYear", DateUtil.getYear());
        if(currentPage == null){
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> graduationDoctorList = graduationDoctorStatisticsBiz.searchGraduationDoctorList(paramMap);
        model.addAttribute("graduationDoctorList", graduationDoctorList);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("catSpeId", catSpeId);
        model.addAttribute("speIdArr", speIdArr);
        return "jsres/asse/graduationDoctorList";
    }

    /**
     * @Department：研发部
     * @Description 导出应结业学员信息
     * @Author fengxf
     * @Date 2025/2/15
     */
    @RequestMapping("/exportGraduationDoctorList")
    public void exportGraduationDoctorList(String roleFlag, String sessionNumber, String doctorTypeId, String examType, String orgFlow,
                                             String catSpeId, String speIdArr, HttpServletResponse response){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("doctorTypeId", doctorTypeId);
        paramMap.put("examType", examType);
        paramMap.put("catSpeId", catSpeId);
        if(StringUtil.isNotBlank(speIdArr)){
            paramMap.put("speIdArr", speIdArr.split(","));
        }
        // 基地管理员查自己基地的数据
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
            paramMap.put("orgFlow", currUser.getOrgFlow());
        }else{
            paramMap.put("orgFlow", orgFlow);
            // 市局
            if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
                paramMap.put("orgCityId", org.getOrgCityId());
            }
        }
        paramMap.put("applyYear", DateUtil.getYear());
        List<Map<String, String>> graduationDoctorList = graduationDoctorStatisticsBiz.searchGraduationDoctorList(paramMap);
        try{
            String[] titles = new String[]{
                "userName:姓名",
                "sexName:性别",
                "cretTypeName:证件类型",
                "idNo:证件号码",
                "workOrgName:派送单位/学校",
                "speName:培训专业",
                "sessionNumber:年级",
                "trainingDate:培训起止时间"
            };
            // 非基地角色增加基地名称列
            if(!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
                titles = new String[]{
                    "userName:姓名",
                    "sexName:性别",
                    "cretTypeName:证件类型",
                    "idNo:证件号码",
                    "orgName:基地名称",
                    "workOrgName:派送单位/学校",
                    "speName:培训专业",
                    "sessionNumber:年级",
                    "trainingDate:培训起止时间"
                };
            }
            String fileName = "应结业学员信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjsAllString(titles, graduationDoctorList, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
