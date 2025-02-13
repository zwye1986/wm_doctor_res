package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.*;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IGraduationDoctorStatisticsBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.jsres.GraduationDoctorStatisticsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/jsres/graduationStatistics")
public class GraduationDoctorStatisticsController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(GraduationDoctorStatisticsController.class);

    @Autowired
    private IGraduationDoctorStatisticsBiz graduationDoctorStatisticsBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;


    /**
     * @Department：研发部
     * @Description 按专业统计应结业学员信息
     * @Author fengxf
     * @Date 2025/2/13
     */
    @RequestMapping("/graduationDoctorStatisticsBySpe")
    public String graduationDoctorStatisticsBySpe(String roleFlag, String sessionNumber, String doctorTypeId, String examType, String orgFlow, Model model){
        Map<String,Object> paramMap = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("doctorTypeId", doctorTypeId);
        paramMap.put("examType", examType);
        // 基地管理员查自己基地的数据
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
            paramMap.put("orgFlow", currUser.getOrgFlow());
        }else{
            paramMap.put("orgFlow", orgFlow);
        }
        List<GraduationDoctorStatisticsInfo> graduationDoctorList = graduationDoctorStatisticsBiz.searchGraduationDoctorStatisticsBySpe(paramMap);
        model.addAttribute("graduationDoctorList", graduationDoctorList);
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigList", resTestConfigList);
        return "jsres/asse/graduationStatisticsBySpe";
    }


    /**
     * @Description // 带教、科主任、管理员请假导出 助理全科
     **/
//    @RequestMapping(value={"/exportLeaveListAcc/{roleFlag}"},method={RequestMethod.GET, RequestMethod.POST})
//    public void exportLeaveListAcc(@PathVariable String roleFlag, ResDoctorKq kq, HttpServletResponse response,
//                                String schDeptFlow) throws Exception{
//        Map<String,Object> paramMap = new HashMap<>();
//        SysUser currUser = GlobalContext.getCurrentUser();
//        paramMap.put("doctorName",kq.getDoctorName());
//        paramMap.put("typeId",kq.getTypeId());
//        paramMap.put("startDate",kq.getStartDate());
//        paramMap.put("endDate",kq.getEndDate());
//        paramMap.put("schDeptFlow",schDeptFlow);
//        paramMap.put("roleFlag",roleFlag);
//        String auditStatusId = kq.getAuditStatusId();
//        paramMap.put("auditStatusId",auditStatusId);
//        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
//        if("teacher".equals(roleFlag)){//带教
//            paramMap.put("teacherFlow",currUser.getUserFlow());
//            if(auditRoleFlag){
//                //待带教审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
//            }
//        }
//        if("head".equals(roleFlag)){
//            paramMap.put("headFlow",currUser.getUserFlow());
//            if(auditRoleFlag){
//                //待科主任审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
//            }
//        }
//        if("manager".equals(roleFlag)){
//            paramMap.put("orgFlow",currUser.getOrgFlow());
//            paramMap.put("managerFlow",currUser.getUserFlow());
//            if(auditRoleFlag){
//                //待管理员审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
//            }
//        }
//        List<String> notStatus=new ArrayList<>();
//        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
//        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
//        paramMap.put("notStatus",notStatus);
//        paramMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
//        List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
//        String[] titles = new String[]{
//                "doctorName:姓名",
//                "typeName:请假类型",
//                "schDeptName:轮转科室",
//                "doctorRemarks:请假事由",
//                "startDate:请假开始时间",
//                "endDate:请假结束时间",
//                "intervalDays:请假天数",
//                "auditStatusName:请假状态"
//        };
//        String fileName = "请假信息.xls";
//        fileName = URLEncoder.encode(fileName, "UTF-8");
//        ExcleUtile.exportSimpleExcleByObjs(titles, kqList, response.getOutputStream());
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//        response.setContentType("application/octet-stream;charset=UTF-8");
//    }

}
