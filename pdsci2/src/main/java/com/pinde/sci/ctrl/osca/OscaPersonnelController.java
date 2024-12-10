package com.pinde.sci.ctrl.osca;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.sci.model.mo.OscaDoctorRegist;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.core.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/osca/personnel")
public class OscaPersonnelController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IOscaDoctorRegistBiz doctorRegistBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IOscaDoctorRegistBiz oscaDoctorRegistBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IInxBiz iInxBiz;
    @Autowired
    private IOscaBaseBiz oscaBaseBiz;

    //打开审核列表
    @RequestMapping("/studentAudit")
    public String studentAudit(String userName,String sessionNumber,String idNo,Model model,Integer currentPage,
                  String trainingTypeId,String trainingSpeId,HttpServletRequest request,String statusId){
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userName",userName);
        paramMap.put("trainingTypeId",trainingTypeId);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("idNo",idNo);
        paramMap.put("orgFlow",currentUser.getOrgFlow());
        paramMap.put("statusId",statusId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> resultMapList = doctorRegistBiz.search(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(null);
        model.addAttribute("orgList",orgList);
        return "osca/base/studentAudit";
    }

    //打开单个审核页面
    @RequestMapping("/audit")
    public String audit(String userFlow,Model model){
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor",doctor);
        OscaDoctorRegist search = new OscaDoctorRegist();
        search.setDoctorFlow(user.getUserFlow());
        List<OscaDoctorRegist> regists = oscaDoctorRegistBiz.searchRegist(search);
        if(regists!=null&&regists.size()>0){
            model.addAttribute("regist",regists.get(0));
        }
        List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(null);
        model.addAttribute("orgList",orgList);
        model.addAttribute("auditFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        return "osca/base/audit";
    }

    //审核
    @RequestMapping("/editAudit")
    @ResponseBody
    public int editAudit(OscaDoctorRegist oscaDoctorRegist){
        String statusName = AuditStatusEnum.getNameById(oscaDoctorRegist.getStatusId());
        oscaDoctorRegist.setStatusName(statusName);
        oscaDoctorRegistBiz.editAudit(oscaDoctorRegist);
        return 1;
    }

    //打新增或编辑页面
    @RequestMapping("/editStudent")
    public String editStudent(String userFlow,Model model){
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        if(user!=null){
            ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
            model.addAttribute("doctor",doctor);
            OscaDoctorRegist search = new OscaDoctorRegist();
            search.setDoctorFlow(user.getUserFlow());
            List<OscaDoctorRegist> regists = oscaDoctorRegistBiz.searchRegist(search);
            if(regists!=null&&regists.size()>0){
                model.addAttribute("regist",regists.get(0));
            }
        }
        List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(null);
        model.addAttribute("orgList",orgList);
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
        return "osca/base/audit";
    }

    /**
     * 管理员--学员信息管理查询
     * @param userName
     * @param sessionNumber
     * @param idNo
     * @param model
     * @param currentPage
     * @param trainingTypeId
     * @param trainingSpeId
     * @param statusId
     * @param request
     * @return
     */
    @RequestMapping("/studentList")
    public String studentList(String userName,String sessionNumber,String idNo,Model model,Integer currentPage,
                   String trainingTypeId,String trainingSpeId, String statusId,HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusId",statusId);
        paramMap.put("userName",userName);
        paramMap.put("trainingTypeId",trainingTypeId);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("idNo",idNo);
        paramMap.put("orgFlow",currentUser.getOrgFlow());
        String flow= InitConfig.getSysCfg("osca_doctor_role_flow");
        if(StringUtil.isNotBlank(flow)) {
            paramMap.put("flow",flow);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String, Object>> resultMapList = doctorRegistBiz.searchStudents(paramMap);

            //查询 省厅控制考点管理员按钮是否显示
            String orgFlow = currentUser.getOrgFlow();
            SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
            if (StringUtil.isNotBlank(sysOrg.getOsceDoctorShow())) {
                model.addAttribute("isShow", sysOrg.getOsceDoctorShow());
            }
            model.addAttribute("resultMapList", resultMapList);
        }
        return "osca/base/studentList";
    }

    //编辑学员信息
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(SysUser user,ResDoctor doctor){
        if(StringUtil.isNotBlank(user.getUserFlow())){
            String userFlow = user.getUserFlow();
            //是否已注册
            String userEmail = user.getUserEmail().trim();
            SysUser sysUser = null;
            sysUser = userBiz.findByUserEmailNotSelf(userFlow,userEmail);
            if(sysUser != null){
                return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
            }
            //判断用户身份证号是否重复
            sysUser = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
            if (sysUser != null) {
                return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
            }
            //判断用户手机号是否重复
            sysUser = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
            if (sysUser != null) {
                return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
            }
            userBiz.edit(user);
            doctor.setDoctorFlow(user.getUserFlow());
            doctorBiz.editDoctor(doctor);
            return "操作成功";
        }else{
            //是否已注册
            String userEmail = user.getUserEmail().trim();
            SysUser sysUser = null;
            sysUser = userBiz.findByUserEmail(userEmail);
            if(sysUser != null){
                return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
            }
            sysUser = userBiz.findByUserCode(userEmail);
            if(sysUser != null){
                return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
            }
            //判断用户身份证号是否重复
            sysUser = userBiz.findByIdNo(user.getIdNo());
            if (sysUser != null) {
                return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
            }
            //判断用户手机号是否重复
            sysUser = userBiz.findByUserPhone(user.getUserPhone());
            if (sysUser != null) {
                return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
            }
            iInxBiz.saveOsceRegistUser2(user,doctor);
        }
        return "操作成功";
    }

    /**
     * 人员导入
     * @param file
     * @return
     */
    @RequestMapping(value="/importUsersFromExcel")
    @ResponseBody
    public String importUsersFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = oscaBaseBiz.importUserFromExcel(file);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
                }else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    //导出
    //打开学员管理列表
    @RequestMapping("/exportStudents")
    public void exportStudents(String userName,String sessionNumber,String idNo,Model model,HttpServletResponse response,
                              String trainingTypeId,String trainingSpeId, String statusId,HttpServletRequest request) throws Exception {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusId",statusId);
        paramMap.put("userName",userName);
        paramMap.put("trainingTypeId",trainingTypeId);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("idNo",idNo);
        paramMap.put("orgFlow",currentUser.getOrgFlow());
        List<Map<String, Object>> resultMapList =new ArrayList<>();
        String flow= InitConfig.getSysCfg("osca_doctor_role_flow");
        if(StringUtil.isNotBlank(flow)) {
            paramMap.put("flow", flow);
            resultMapList = doctorRegistBiz.searchStudents(paramMap);
        }
        String[] titles = new String[]{
                "USER_NAME:姓名",
                "ORG_NAME:培训基地",
                "TRAINING_TYPE_NAME:培训类型",
                "TRAINING_SPE_NAME:培训专业",
                "SESSION_NUMBER:培训年级",
                "ID_NO:证件号码",
                "USER_EMAIL:邮箱",
                "USER_PHONE:手机号码",
                "WORK_ORG_NAME:所在单位"
        };
        String fileName = "学员信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
    }

    private static Logger logger = LoggerFactory.getLogger(OscaPersonnelController.class);

}
