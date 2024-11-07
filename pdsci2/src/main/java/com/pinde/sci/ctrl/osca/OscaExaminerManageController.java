package com.pinde.sci.ctrl.osca;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaExaminerManageBiz;
import com.pinde.sci.biz.osca.ISiteInformationBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaExaminerExt;
import com.pinde.sci.model.osca.OscaOrgSpeExt;
import com.pinde.sci.model.osca.OscaTypeSpeExt;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/osca/oscaExaminerManage")
public class OscaExaminerManageController extends GeneralController{

    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private ISiteInformationBiz siteInformationBiz;
    @Autowired
    private IOscaExaminerManageBiz oscaExaminerManageBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;

    /**
     * 省厅-考官信息管理 查询
     * @param sysUser
     * @param currentPage
     * @param request
     * @param exmpel
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String oscaExaminerList(SysUser sysUser, Integer currentPage, HttpServletRequest request, String exmpel,Model model){
        List<String> orgFlowList=new ArrayList<>();
        SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        Map<String, Object> map=new HashMap<>();
        map.put("orgProvId",sysOrg.getOrgProvId());
        map.put("isExamOrg",GlobalConstant.RECORD_STATUS_Y);
        List<OscaOrgSpeExt> orgSpeList=new ArrayList();
        orgSpeList=siteInformationBiz.searchAllOrg(map);
        Map<String, Object> examMap=new HashMap<>();
        if(sysUser!=null){
            examMap.put("userName",sysUser.getUserName());
            examMap.put("titleName",sysUser.getTitleName());
            examMap.put("recordStatus",sysUser.getRecordStatus());
            examMap.put("userCode",sysUser.getUserCode());
            if("All".equals(sysUser.getOrgFlow())||"All".equals(exmpel)){
                orgFlowList=null;
            }else {
                orgFlowList.add(sysUser.getOrgFlow());
            }
            examMap.put("orgFlowList",orgFlowList);
        }
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        examMap.put("examTeaRole",examTeaRole);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<OscaExaminerExt> examinerList=new ArrayList();
        //考核人员类型和考核专业map
        Map<String,String> typeSpeMap = new HashMap<>();
        examinerList=oscaExaminerManageBiz.searchAllExam(examMap);
        if(examinerList!=null&&examinerList.size()>0){
            for (int i = 0, length = examinerList.size(); i < length; i++){

                //查询考官表中 考核人员类型、考核专业
                List<OscaTeaInfo> oscaTeaInfoList = oscaExaminerManageBiz.getOscaTeaInfo(examinerList.get(i).getUserFlow());

                //拼接考核人员类型和考核专业
                StringBuilder typeSpeList = new StringBuilder();
                if(null!=oscaTeaInfoList && !oscaTeaInfoList.isEmpty()){
                    for(OscaTeaInfo teaInfo:oscaTeaInfoList){
                        typeSpeList.append(teaInfo.getTrainingTypeName()+"("+teaInfo.getTrainingSpeName()+")"+"<br/>");
                        typeSpeMap.put(examinerList.get(i).getUserFlow(),typeSpeList.toString());
                    }
                }

                ResDoctor resDoctor=new ResDoctor();
                for (OscaExaminerExt ose:examinerList) {
                    String doctorFlow=ose.getUserFlow();
                    resDoctor=resDoctorBiz.findByFlow(doctorFlow);
                    ose.setResDoctor(resDoctor);
                }
            }
        }
        model.addAttribute("typeSpeMap",typeSpeMap);
        model.addAttribute("orgSpeList",orgSpeList);
        model.addAttribute("examinerList",examinerList);
        model.addAttribute("currentPage",currentPage);
        return "osca/global/examinerList";
    }

    /**
     * 管理员角色考官管理--查询
     * @param sysUser
     * @param currentPage
     * @param request
     * @param exmpel
     * @param model
     * @return
     */
    @RequestMapping(value = "/managerList", method = {RequestMethod.GET, RequestMethod.POST})
    public String oscaExamManagerList(String manage,OscaTeaInfo oscaTeaInfo,SysUser sysUser, Integer currentPage, HttpServletRequest request, String exmpel,Model model){
        List<String> orgFlowList=new ArrayList<>();
        SysUser currentUser = GlobalContext.getCurrentUser();
        orgFlowList.add(currentUser.getOrgFlow());
//        SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
//        Map<String, Object> map=new HashMap<>();
//        map.put("orgProvId",sysOrg.getOrgProvId());
//        map.put("isExamOrg",GlobalConstant.RECORD_STATUS_Y);
//        List<OscaOrgSpeExt> orgSpeList=siteInformationBiz.searchAllOrg(map);
//        model.addAttribute("orgSpeList",orgSpeList);
//        model.addAttribute("manage",manage);
//        if("manage".equals(manage)){
            model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
            model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
//        }

        //查询 省厅控制考点管理员 按钮是否显示(考官)
        String orgFlow =currentUser.getOrgFlow();
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        if(StringUtil.isNotBlank(sysOrg.getOsceTeacherShow())){
            model.addAttribute("isShow",sysOrg.getOsceTeacherShow());
        }

        Map<String, Object> examMap=new HashMap<>();
        if(sysUser!=null){
            examMap.put("userName",sysUser.getUserName());
            examMap.put("titleName",sysUser.getTitleName());
            examMap.put("statusId",sysUser.getStatusId());
            examMap.put("userCode",sysUser.getUserCode());
            examMap.put("orgFlowList",orgFlowList);
            examMap.put("workOrgName",sysUser.getWorkOrgName());
            examMap.put("orgFlow",sysUser.getOrgFlow());
        }
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        examMap.put("examTeaRole",examTeaRole);
        if(null!=oscaTeaInfo){
            examMap.put("trainingTypeId",oscaTeaInfo.getTrainingTypeId());
            examMap.put("trainingSpeId",oscaTeaInfo.getTrainingSpeId());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<OscaExaminerExt> examinerList=new ArrayList();
        examinerList=oscaExaminerManageBiz.searchAllExam(examMap);

        //考核人员类型和考核专业map
        Map<String,String> typeSpeMap = new HashMap<>();

        if(examinerList!=null&&examinerList.size()>0){

            for (int i = 0, length = examinerList.size(); i < length; i++){

            //查询考官表中 考核人员类型、考核专业
            List<OscaTeaInfo> oscaTeaInfoList = oscaExaminerManageBiz.getOscaTeaInfo(examinerList.get(i).getUserFlow());

           //拼接考核人员类型和考核专业
            StringBuilder typeSpeList = new StringBuilder();
            if(null!=oscaTeaInfoList && !oscaTeaInfoList.isEmpty()){
                for(OscaTeaInfo teaInfo:oscaTeaInfoList){
                    typeSpeList.append(teaInfo.getTrainingTypeName()+"("+teaInfo.getTrainingSpeName()+")"+"<br/>");
                    typeSpeMap.put(examinerList.get(i).getUserFlow(),typeSpeList.toString());
                }
            }

            }
            ResDoctor resDoctor=new ResDoctor();
            for (OscaExaminerExt ose:examinerList) {
                String doctorFlow=ose.getUserFlow();
                //resDoctor=resDoctorBiz.findByFlow(doctorFlow);
                ose.setResDoctor(resDoctor);
            }
        }
        model.addAttribute("typeSpeMap",typeSpeMap);
        model.addAttribute("examinerList",examinerList);
        model.addAttribute("currentPage",currentPage);
        return "osca/examInfoManager/examInfoManagerList";
    }

    /**
     * 显示编辑页面
     * @param model
     * @param manage
     * @return
     */
    @RequestMapping("/showEditInfo")
    public String showEditInfo(Model model,String manage){
        SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        Map<String, Object> map=new HashMap<>();
        map.put("orgProvId",sysOrg.getOrgProvId());
        map.put("isExamOrg",GlobalConstant.RECORD_STATUS_Y);
        List<OscaOrgSpeExt> orgSpeList=siteInformationBiz.searchAllOrg(map);
        model.addAttribute("orgSpeList",orgSpeList);
        model.addAttribute("manage",manage);
        if("manage".equals(manage)){
            model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
        }
        return "osca/global/editExaminer";
    }

    /**
     * 新增考官
     * @param model
     * @param oscaExaminerExt
     * @param workOrgName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/editExam"})
    @ResponseBody
    public String editExam(Model model,OscaTeaInfo oscaTeaInfo,OscaExaminerExt oscaExaminerExt,String workOrgName,String manage)throws Exception{
//        workOrgName=java.net.URLDecoder.decode(workOrgName,"UTF-8");
        if (oscaExaminerExt!=null){
            SysUser sysUser=new SysUser();
            String sexName="女";
            String orgName="";
            String orgFlow="";
            if("Man".equals(oscaExaminerExt.getSexId())){
                sexName="男";
            }
            if("manage".equals(manage)){
                orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
            }else {
                orgFlow=oscaExaminerExt.getOrgFlow();
            }
            SysOrg sysOrg=orgBiz.readSysOrg(orgFlow);
            if (sysOrg!=null){
                orgName=sysOrg.getOrgName();
            }
            sysUser.setUserCode(oscaExaminerExt.getUserCode());
            sysUser.setUserName(oscaExaminerExt.getUserName());
            sysUser.setSexId(oscaExaminerExt.getSexId());
            sysUser.setSexName(sexName);
            sysUser.setTitleId(oscaExaminerExt.getTitleId());
            String titleName = DictTypeEnum.OscaUserTitle.getDictNameById(oscaExaminerExt.getTitleId());
            sysUser.setTitleName(titleName);
            sysUser.setUserPhone(oscaExaminerExt.getUserPhone());
            sysUser.setOrgFlow(orgFlow);
            sysUser.setOrgName(orgName);
            sysUser.setWorkOrgName(oscaExaminerExt.getWorkOrgName());

            String userFlow=oscaExaminerExt.getUserFlow();
            //人员类型和专业List
            List<OscaTypeSpeExt> typeSpeList=null;

            if(null!=oscaExaminerExt && null!=oscaExaminerExt.getTypeSpeList()) {
                 typeSpeList = oscaExaminerExt.getTypeSpeList();
            }

            if(StringUtil.isBlank(sysUser.getUserCode()))
            {
                return "请输入用户名";
            }

            //判断用户名是否已存在
            SysUser existsUser=userBiz.findByUserCode(sysUser.getUserCode());
            if(existsUser!=null&&!existsUser.getUserFlow().equals(userFlow))
            {
                return GlobalConstant.USER_CODE_REPETE;
            }
            String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
            if(StringUtil.isBlank(userFlow)){
                //判断用户名是否已存在
//                List<SysUser> userList=oscaExaminerManageBiz.sysUserList(sysUser);
//                if(userList!=null&&userList.size()>0&&userList.get(0)!=null) {
//                    return GlobalConstant.USER_CODE_REPETE;
//
//                }
//                    SysUserRole sysUserRole=new SysUserRole();
//                    sysUserRole.setUserFlow(userList.get(0).getUserFlow());
//                    sysUserRole.setWsId("res");
//                    List<SysUserRole> sysUserRoleList=userRoleBiz.searchUserRole(sysUserRole);
//                    boolean roleFlag=true;
//                    //判断是否为带教老师
//                    if(sysUserRoleList!=null&&sysUserRoleList.size()>0){
//                        for (SysUserRole userRole:sysUserRoleList) {
//                            SysRole role=roleBiz.read(userRole.getRoleFlow());
//                            if(role!=null&&"带教老师".equals(role.getRoleName())){
//                                //如果为当前考点，则判断其重复
//                                if(StringUtil.isNotBlank(userList.get(0).getOrgFlow())&&!userList.get(0).getOrgFlow().equals(orgFlow)){
//                                    return GlobalConstant.USER_CODE_REPETE;
//                                }
//                                //同时更新 user表和 osca_tea_info
//                                sysUser.setUserFlow(userList.get(0).getUserFlow());
//                                //设置其为 是考官的标志
//                                sysUser.setIsExamTea(GlobalConstant.IS_EXAM_TEA_Y);
//                                //考官信息表
//                                oscaTeaInfo.setUserFlow(userList.get(0).getUserFlow());
//
//                                oscaExaminerManageBiz.updateExamAndUser(sysUser,oscaTeaInfo,typeSpeList);
//
//                                roleFlag=false;
//                                return GlobalConstant.SAVE_SUCCESSED;
//                            }
//                        }
//                        if(roleFlag){
//                            return GlobalConstant.USER_CODE_REPETE;
//                        }
//                    }
                //设置其为 是考官的标志

                SysUserRole userRole=new SysUserRole();
                userRole.setRoleFlow(examTeaRole);
                userRole.setWsId("osca");
                oscaExaminerManageBiz.updateExamAndUser(sysUser,oscaTeaInfo,typeSpeList,userRole);
            }else {
                SysUserRole userRole=userRoleBiz.readUserRole(userFlow,examTeaRole);
                if(userRole==null)
                {
                    userRole=new SysUserRole();
                    userRole.setRoleFlow(examTeaRole);
                    userRole.setWsId("osca");
                }
                sysUser.setUserFlow(userFlow);
                oscaExaminerManageBiz.updateExamAndUser(sysUser,oscaTeaInfo,typeSpeList,userRole);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 编辑状态
     * @param userFlow
     * @param recordStatus
     * @return
     */
    @RequestMapping(value = {"/modifyRecordStatus"})
    @ResponseBody
    public String modifyRecordStatus(String userFlow,String recordStatus){
        SysUser sysUser=new SysUser();
        sysUser.setUserFlow(userFlow);
        sysUser.setRecordStatus(recordStatus);
        sysUser.setModifyTime(DateUtil.getCurrDateTime());
        sysUser.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        userBiz.edit(sysUser);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 一键停用
     * @param data
     * @return
     */
    @RequestMapping(value = {"/modifyAllRecordStatus"})
    @ResponseBody
    public String modifyAllRecordStatus(String data){
        String[] userFlows={};
        if(data!=null){
            userFlows=data.split("/pdnsp/");
            for(int i=1;i<userFlows.length;i++){
                SysUser sysUser=new SysUser();
                sysUser.setUserFlow(userFlows[i]);
                sysUser.setStatusId(UserStatusEnum.Locked.getId());
                sysUser.setStatusDesc(UserStatusEnum.Locked.getName());
                sysUser.setModifyTime(DateUtil.getCurrDateTime());
                sysUser.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                userBiz.edit(sysUser);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/showAssignsExam"})
    public String showAssignsExam(Model model,String userFlow){
        SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        Map<String, Object> map=new HashMap<>();
        map.put("orgProvId",sysOrg.getOrgProvId());
        map.put("isExamOrg",GlobalConstant.RECORD_STATUS_Y);
        List<OscaOrgSpeExt> orgSpeList=new ArrayList();
        orgSpeList=siteInformationBiz.searchAllOrg(map);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("orgSpeList",orgSpeList);
        return "osca/global/assignsExam";
    }

    /**
     * 分配考点
     * @param orgFlow
     * @param userFlow
     * @return
     */
    @RequestMapping(value = {"/editExamTestCenter"})
    @ResponseBody
    public String editExamTestCenter(String orgFlow,String userFlow){
        SysOrg sysOrg=new SysOrg();
        sysOrg=orgBiz.readSysOrg(orgFlow);
        SysUser sysUser=new SysUser();
        sysUser.setUserFlow(userFlow);
        sysUser.setOrgFlow(orgFlow);
        if (sysOrg!=null){
            sysUser.setOrgName(sysOrg.getOrgName());
        }
       userBiz.edit(sysUser);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 考官信息管理-编辑
     * @param model
     * @param userFlow
     * @param manage
     * @return
     */

    @RequestMapping("/showEditInfoBeforAdd")
    public String showEditInfoBeforAdd(Model model,String userFlow,String manage){
        SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        Map<String, Object> map=new HashMap<>();
        map.put("orgProvId",sysOrg.getOrgProvId());
        map.put("isExamOrg",GlobalConstant.RECORD_STATUS_Y);
        List<OscaOrgSpeExt> orgSpeList=siteInformationBiz.searchAllOrg(map);
        SysUser sysUser=userBiz.findByFlow(userFlow);
        ResDoctor resDoctor=new ResDoctor();
        if(sysUser!=null){
            resDoctor=resDoctorBiz.findByFlow(userFlow);
        }
        //查询考官表
        List<OscaTeaInfo> teaInfoList = oscaExaminerManageBiz.getOscaTeaInfo(userFlow);
        if(null!=teaInfoList && teaInfoList.size()>0){
            model.addAttribute("typeSpeList",teaInfoList);
        }

        model.addAttribute("orgSpeList",orgSpeList);
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("manage",manage);
        if("manage".equals(manage)){
            model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
        }
        return "osca/global/editExaminer";
    }

    /**
     *管理员--导出考官excel
     *
     * @throws Exception
     */
    @RequestMapping("/exportManageExamList")
    public void exportManageExamList(String manage,OscaTeaInfo oscaTeaInfo,SysUser sysUser,HttpServletResponse response) throws Exception {
        List<String> orgFlowList=new ArrayList<>();
        SysUser currentUser = GlobalContext.getCurrentUser();
        orgFlowList.add(currentUser.getOrgFlow());
        Map<String, Object> examMap=new HashMap<>();
        if(sysUser!=null){
            examMap.put("userName",sysUser.getUserName());
            examMap.put("titleName",sysUser.getTitleName());
            examMap.put("statusId",sysUser.getStatusId());
            examMap.put("userCode",sysUser.getUserCode());
            examMap.put("orgFlowList",orgFlowList);
            examMap.put("workOrgName",sysUser.getWorkOrgName());
        }
        if(null!=oscaTeaInfo){
            examMap.put("trainingTypeId",oscaTeaInfo.getTrainingTypeId());
            examMap.put("trainingSpeId",oscaTeaInfo.getTrainingSpeId());
        }
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        examMap.put("examTeaRole",examTeaRole);
        List<OscaExaminerExt> examinerList=new ArrayList();
        examinerList=oscaExaminerManageBiz.searchAllExam(examMap);
        if(examinerList!=null&&examinerList.size()>0){
            ResDoctor resDoctor=new ResDoctor();
            for (OscaExaminerExt ose:examinerList) {
                String doctorFlow=ose.getUserFlow();
                resDoctor=resDoctorBiz.findByFlow(doctorFlow);
                ose.setResDoctor(resDoctor);
            }
        }
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("考官信息列表");
        //列宽自适应
        sheet.setDefaultColumnWidth((short)100);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //第一行
        HSSFRow rowDep = sheet.createRow(0);
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("考官信息列表");
        //第二行
        HSSFRow rowOne= sheet.createRow(1);
        String[] titles = new String[]{
                "序号",
                "用户名",
                "姓名",
                "性别",
                "职称",
                "专业（类型）",
                "联系方式",
                "所在单位",
                "所在考点"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        String[] resultList = null;
        int rowNum = 2;
        if (examinerList != null && examinerList.size()>0) {
            String workOrgName="";
            for (int i = 0; i < examinerList.size(); i++, rowNum++) {
                //查询考官表中 考核人员类型、考核专业
                List<OscaTeaInfo> oscaTeaInfoList = oscaExaminerManageBiz.getOscaTeaInfo(examinerList.get(i).getUserFlow());
                //拼接考核人员类型和考核专业
                StringBuilder typeSpeList = new StringBuilder();
                if(null!=oscaTeaInfoList && !oscaTeaInfoList.isEmpty()){
                    for(OscaTeaInfo teaInfo:oscaTeaInfoList){
                        typeSpeList.append(teaInfo.getTrainingSpeName()+"("+teaInfo.getTrainingTypeName()+")"+"\r\n");
                    }
                }
                //第二行
                HSSFRow rowFour = sheet.createRow(rowNum);
                if(examinerList.get(i).getResDoctor()!=null){
                    workOrgName=examinerList.get(i).getResDoctor().getWorkOrgName();
                }
                resultList = new String[]{
                        i+1+"",
                        examinerList.get(i).getUserCode(),
                        examinerList.get(i).getUserName(),
                        examinerList.get(i).getSexName(),
                        examinerList.get(i).getTitleName(),
                        typeSpeList.toString(),
                        examinerList.get(i).getUserPhone(),
                        examinerList.get(i).getWorkOrgName(),
                        examinerList.get(i).getOrgName()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
            }
        }
        String fileName ="考官信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    /**
     * 省厅---导出考官信息
     * @param orgFlow
     * @param titleName
     * @param userName
     * @param recordStatus
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportExamList")
    public void exportExamList(String orgFlow,String titleName,String userCode,String userName,String recordStatus, HttpServletResponse response) throws Exception {
        Map<String, Object> examMap=new HashMap<>();
        List<String> orgFlowList=new ArrayList<>();
        examMap.put("userName",userName);
        examMap.put("titleName",titleName);
        examMap.put("userCode",userCode);
        examMap.put("recordStatus",recordStatus);
        if("All".equals(orgFlow)){
            orgFlowList=null;
        }else {
            orgFlowList.add(orgFlow);
        }
        examMap.put("orgFlowList",orgFlowList);
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        examMap.put("examTeaRole",examTeaRole);
        List<OscaExaminerExt> examinerList=new ArrayList();
        examinerList=oscaExaminerManageBiz.searchAllExam(examMap);
        if(examinerList!=null&&examinerList.size()>0){
            ResDoctor resDoctor=new ResDoctor();
            for (OscaExaminerExt ose:examinerList) {
                String doctorFlow=ose.getUserFlow();
                resDoctor=resDoctorBiz.findByFlow(doctorFlow);
                ose.setResDoctor(resDoctor);
            }
        }
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        //居中
        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        //靠左垂直居中
        HSSFCellStyle styleLeft = wb.createCellStyle();
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        //居中
        HSSFCellStyle stylevwc = wb.createCellStyle();
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("考官信息列表");
        //列宽自适应
        sheet.setDefaultColumnWidth((short)50);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //第一行
        HSSFRow rowDep = sheet.createRow(0);
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("考官信息列表");
        //第二行
        HSSFRow rowOne= sheet.createRow(1);
        String[] titles = new String[]{
                "序号",
                "用户名",
                "姓名",
                "性别",
                "职称",
                "类型（专业）",
                "联系方式",
                "所在单位",
                "所在考点"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        String[] resultList = null;
        int rowNum = 2;
        if (examinerList != null && examinerList.size()>0) {
            String workOrgName="";
            for (int i = 0; i < examinerList.size(); i++, rowNum++) {
                //查询考官表中 考核人员类型、考核专业
                List<OscaTeaInfo> oscaTeaInfoList = oscaExaminerManageBiz.getOscaTeaInfo(examinerList.get(i).getUserFlow());
                //拼接考核人员类型和考核专业
                StringBuilder typeSpeList = new StringBuilder();
                if(null!=oscaTeaInfoList && !oscaTeaInfoList.isEmpty()){
                    for(OscaTeaInfo teaInfo:oscaTeaInfoList){
                        typeSpeList.append(teaInfo.getTrainingTypeName()+"("+teaInfo.getTrainingSpeName()+")"+"\r\n");
                    }
                }
                //第二行
                HSSFRow rowFour = sheet.createRow(rowNum);
                if(examinerList.get(i).getResDoctor()!=null){
                    workOrgName=examinerList.get(i).getResDoctor().getWorkOrgName();
                }
                resultList = new String[]{
                        i+1+"",
                        examinerList.get(i).getUserCode(),
                        examinerList.get(i).getUserName(),
                        examinerList.get(i).getSexName(),
                        examinerList.get(i).getTitleName(),
                        typeSpeList.toString(),
                        examinerList.get(i).getUserPhone(),
                        examinerList.get(i).getWorkOrgName(),
                        examinerList.get(i).getOrgName()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
            }
        }
        String fileName ="考官信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value="/showImport")
    public String showImport(){return "osca/global/importExam";}

    @RequestMapping(value="/showManageImport")
    public String showManageImport(){return "osca/examInfoManager/importManageExam";}

    /**
     * 省厅--考官信息管理-导入
     * @param file
     * @return
     */
    @RequestMapping(value="importExam")
    public @ResponseBody String importExam(MultipartFile file){
        if(file.getSize() > 0){
            try{
                oscaExaminerManageBiz.importExamFromExcel(file);
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return "导入成功！";
    }

    /**
     * 管理员--考官信息管理-导入
     * @param file
     * @return
     */
    @RequestMapping(value="importManagerExam")
    public @ResponseBody String importManagerExam(MultipartFile file){
        if(file.getSize() > 0){
            try{
                oscaExaminerManageBiz.importExamFromExcel1(file);
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return "导入成功！";
    }
}
