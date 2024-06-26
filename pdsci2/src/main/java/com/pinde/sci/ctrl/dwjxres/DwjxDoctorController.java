package com.pinde.sci.ctrl.dwjxres;

import com.alibaba.druid.support.json.JSONUtils;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.form.dwjxres.SingUpForm;
import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dwjxres/doctor")
public class DwjxDoctorController extends GeneralController {

    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IDocSingupBiz docSingupBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IOrgBiz orgBiz;

    @RequestMapping("/home")
    public String home(Integer currentPage, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        String batchFlow = "";//进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();//取报名中数据
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {
                    batchFlow = obj.getBatchFlow();    //取报名中默认批次的数据的批次号
                    break;
                }
            }
            //若报名中默认批次的数据的批次号为空，则取报名中数据的第一个数据的批次号
            if (StringUtil.isBlank(batchFlow))
                batchFlow = batchLst.get(0).getBatchFlow();
        }
        //如果批次号为空，则查询全表
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);
        StuUserResume stuUser = null;
        //取第一条数据，并设置当前批次号为第一条数据的批次号
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            if (StringUtil.isBlank(batchFlow))
                batchFlow = stuUser.getStuBatId();
            model.addAttribute("stuUser", stuUser);//当前系统设置批次下的医师
        }
        model.addAttribute("batchFlow", batchFlow);
        //查询该学员是否有已报名记录且记录状态为（Admited，Graduation，DelayGraduation）；
        List<String> paramList = new ArrayList<>();
        paramList.add(StuStatusEnum.Admited.getId());
        paramList.add(StuStatusEnum.Graduation.getId());
        paramList.add(StuStatusEnum.DelayGraduation.getId());
        List<StuUserResume> showGraduationLst = stuUserResumeBiz.getShowGraduationLst(user.getUserFlow(), paramList);
        if (null != showGraduationLst && showGraduationLst.size() > 0) {
            model.addAttribute("showGraduation", "Y");//当前系统展示结业管理菜单
        }

        //读取系统消息
        PubMsgExample example = new PubMsgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
        example.setOrderByClause("MSG_TIME desc");
        PageHelper.startPage(currentPage, 10);
        List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
        if (msgList != null && msgList.size() > 0) {
            int newMsg = 0;
            for (PubMsg msg : msgList) {
                if (!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())) {
                    newMsg++;
                }
            }
            model.addAttribute("msgList", msgList);
            model.addAttribute("newMsg", newMsg);
        }
        return "dwjxres/doctor/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        if (!userRoleList.isEmpty()) {
            userRole = userRoleList.get(0);
            return "redirect:" + this.getRoleUrl(userRole.getRoleFlow());
        } else {
            if (StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().contains("2")) {
                return "redirect:/dwjxres/secretaries/home";
            }
        }
        String loginErrorMessage = "未赋权";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return "inx/dwjxres/login";
    }

    @RequestMapping("/checkFile")
    @ResponseBody
    public String checkFile(String fileFlow) {
        PubFile file = fileBiz.readFile(fileFlow);
        List<String> sufs = new ArrayList<>();
        //bmp,jpg,png,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF
        sufs.add("png");
        sufs.add("jpg");
        sufs.add("bmp");
        sufs.add("gif");
        if (sufs.contains(file.getFileSuffix().toLowerCase())) {
            return file.getFilePath();
        } else {
            return "1";
        }


    }

    /**
     * 学员上传成绩结业鉴定
     *
     * @param file
     * @param productFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadFile"})
    @ResponseBody
    public String uploadApplicationFile(MultipartFile file, String productFlow) throws Exception {
        if (file != null && !file.isEmpty()) {
            stuUserResumeBiz.saveStuFile(file, productFlow);
            return GlobalConstant.UPLOAD_SUCCESSED;
        }
        return "上传附件内容不能为空！";
    }

    //下载附件
    @RequestMapping(value = {"/fileDown"}, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
        PubFile file = this.fileBiz.readFile(fileFlow);
        fileBiz.downPubFile(file, response);
    }

    public String getRoleUrl(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
            if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                return "/dwjxres/hospital/home";
//			} else if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//系统管理员即省级管理员
//				return "/dwjxres/manage/home";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/dwjxres/doctor/home";
            }
        }
        return "";
    }

    @RequestMapping("/singup")
    public String singup(String batchFlow, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        user = this.userBiz.readSysUser(user.getUserFlow());
        model.addAttribute("user", user);
        String batchDate = "";
        //当前系统默认 进修批次流水号
        StuBatch sb = stuBatchBiz.getDefualBatch();
        if (sb == null) {
            List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();
            if (null != batchLst && batchLst.size() > 0) {
                sb = batchLst.get(0);
            }
        }
        if (sb != null) {
            if (StringUtil.isBlank(batchFlow)) {
                batchFlow = sb.getBatchFlow();
            }
            batchDate = sb.getBatchDate();
        }

        //获取比默认批次大的所有批次并且学员提交过审核的批次
        //只有报名中
        List<StuBatch> batchLst = stuBatchBiz.getDefualStuBatchAndStuSelect(user.getUserFlow()/*,batchDate*/);
        model.addAttribute("batchLst", batchLst);
        //选中的批次
        StuBatch selectBatch = stuBatchBiz.searchStuBatch(batchFlow);

        model.addAttribute("recordLst", stuUserResumeBiz.getStuUserLst(user.getUserFlow(), null));
        String bid = batchFlow;
        model.addAttribute("batchFlow", bid);
        model.addAttribute("batchNo", selectBatch.getBatchNo());
        model.addAttribute("batchId", bid);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), bid);
        StuUserResume stuUser = null;
        ExtInfoForm extInfo = null;
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师
            extInfo = docSingupBiz.parseExtInfoXml(doctorLst.get(0).getResumeInfo());
            model.addAttribute("extInfo", extInfo);
            if (StuStatusEnum.Passed.getId().equals(doctorLst.get(0).getStuStatusId())
                    || StuStatusEnum.Admited.getId().equals(doctorLst.get(0).getStuStatusId())) {
                return "dwjxres/doctor/singupinfo";//查看
            }
        } else {
            List<StuUserResume> lst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);//默认批次
            if (null != lst && lst.size() > 0) {
                stuUser = lst.get(0);
                model.addAttribute("stuUser", lst.get(0));//当前系统设置批次下的医师
                extInfo = docSingupBiz.parseExtInfoXml(lst.get(0).getResumeInfo());
                extInfo.setStudyAim("");
                extInfo.setVocationalLevel("");
                model.addAttribute("extInfo", extInfo);
            }
        }
        Boolean b = true;//用于判断第一页是否提交extInfo为null则未提交
        if (extInfo == null && user != null) {
            extInfo = new ExtInfoForm();
            //新注册的学员StuUserResume为空,页面中的学员基本信息是从StuUserResume中的resumeInfo（对应ExtInfoForm）即extInfo取的
            //页面要默认注册时的信息下一步是将SysUser中基本信息赋到extInfo
            updateExtInfoFromUser(user, extInfo);
            model.addAttribute("extInfo", extInfo);
            b = false;
        }
        /*if(sb==null||StringUtil.isBlank(sb.getBatchDate()) *//*|| selectBatch.getBatchDate().compareTo(sb.getBatchDate())>=0*//*	){*/
        if ((stuUser == null || !RegStatusEnum.Passed.getId().equals(stuUser.getStuStatusId()))) {
            int bigStep = 0;//四大步中第几步
            String step = "1";//
            if (extInfo != null) {
//					if(StringUtil.isNotBlank(extInfo.getUserEmail()) && b){
//						model.addAttribute("formHaveSubmit1" ,"Y");
//					}
                //改成身份证
                if (StringUtil.isNotBlank(extInfo.getIdNo()) && b) {
                    model.addAttribute("formHaveSubmit1", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getCertifiedNo())) {
                    model.addAttribute("formHaveSubmit2", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getVocationalLevel())) {
                    model.addAttribute("formHaveSubmit3", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getRegisterFormUri())) {
                    model.addAttribute("registerFormUri", "Y");
                }
                if (stuUser != null) {
                    if (StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                            || GlobalConstant.FLAG_Y.equals(stuUser.getIsBack())
                            || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                        model.addAttribute("stuBatId", "Y");
                        List<StuAuditProcess> processes = stuUserResumeBiz.searchProcessByResumeFlow(stuUser.getResumeFlow());
                        if (processes != null && processes.size() > 0) {
                            StuAuditProcess process = processes.get(0);
                            if (StringUtil.isNotBlank(process.getAuditContent())) {
                                model.addAttribute("auditAgree", process.getAuditContent());
                            }
                        }
                    }
                }
            }
            if (stuUser != null) {
                if (StuStatusEnum.UnSubmit.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                    return "dwjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
                }
            }
            if (stuUser == null) {
                return "dwjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
            }
        }
//		/*}*/
        return "dwjxres/doctor/singupinfo";//查看
    }

    @RequestMapping("/saveSingupInfo")
    @ResponseBody
    public String saveSingupInfo(SingUpForm singUpForm, String flag, String stuBatId, String registerFormUriValue) {

        StuBatch bachinfo = docSingupBiz.findBatchByBatchFlow(stuBatId);

        SysUser currentUser = GlobalContext.getCurrentUser();
        currentUser = userBiz.readSysUser(currentUser.getUserFlow());
        String data = "";
        Map<String, String> dataMap = new HashMap<>();

        if ("未开始".equals(bachinfo.getBatchStatus())) {
            dataMap.put("operResult", "该批次进修报名暂未开始，无法申请！");
            data = JSONUtils.toJSONString(dataMap);
            return data;

        }
        if ("已结束".equals(bachinfo.getBatchStatus())) {
            dataMap.put("operResult", "该批次进修报名已结束，无法申请！");
            data = JSONUtils.toJSONString(dataMap);
            return data;
        }

        if ("true".equals(flag)) {
            List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), stuBatId);
            if (doctorLst != null && doctorLst.size() > 0) {
                StuUserResume resume = doctorLst.get(0);
                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resume.getResumeInfo());
                extInfo.setRegisterFormUri(registerFormUriValue);
                extInfo.setTitleTypeName(DictTypeEnum.TitleGenre.getDictNameById(extInfo.getTitleTypeId()));
                resume.setResumeInfo(docSingupBiz.getXmlFromExtInfo(extInfo));
                resume.setStuStatusId(StuStatusEnum.Passing.getId());
                resume.setStuStatusName(StuStatusEnum.Passing.getName());
                
                if(extInfo.getTitleTypeName().equals("护士")){//需先护理管理员审批
                    resume.setNursingStatusId(StuStatusEnum.Passing.getId());
                    resume.setNursingStatusName(StuStatusEnum.Passing.getName());
                }

                int result = stuUserResumeBiz.save(resume);
                return result + "";
            }
            return GlobalConstant.OPRE_FAIL;
        }
        if ("reApply".equals(flag)) {
            List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), stuBatId);
            if (doctorLst != null && doctorLst.size() > 0) {
                StuUserResume resume = doctorLst.get(0);
                resume.setIsBack(GlobalConstant.FLAG_N);
                resume.setStuStatusId(StuStatusEnum.UnSubmit.getId());
                resume.setStuStatusName(StuStatusEnum.UnSubmit.getName());
                int result = stuUserResumeBiz.save(resume);
                return result + "";
            }
            return GlobalConstant.OPRE_FAIL;
        }
        SysUser user = singUpForm.getUser();
        if (user == null) {
            user = currentUser;
            singUpForm.setUser(user);
        }
        user.setUserFlow(currentUser.getUserFlow());//用户流水号
        user.setCretTypeId("Shenfenzheng");//只能为身份证类型
        if (StringUtil.isNotBlank(user.getIdNo())) {//验证身份证是否注册过
            SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
            if (null != existUser) {
                dataMap.put("operResult", "证件号码已注册！");
                data = JSONUtils.toJSONString(dataMap);
                return data;
            }
        }
        user.setSexName(UserSexEnum.getNameById(user.getSexId()));//性别名称
        user.setNationName(UserNationEnum.getNameById(user.getNationId()));//名族名称

        StuUserResume stuUser = singUpForm.getStuUser();
        stuUser.setStuBatId(stuBatId);
        stuUser.setUserFlow(user.getUserFlow());//用户流水号
        if (StringUtil.isNotBlank(stuUser.getSpeId())) {
            stuUser.setSpeName(DictTypeEnum.DwjxSpe.getDictNameById(stuUser.getSpeId()));//进修专业名称
        }
        if (StringUtil.isNotBlank(stuUser.getStuTimeId())) {
            if(stuUser.getStuTimeId().equals("短期培训")){
                stuUser.setStuTimeName("3个月");
            }else {
                stuUser.setStuTimeName(stuUser.getStuTimeId() + "个月");//进修时间名称
            }
        }
        StuBatch bach = docSingupBiz.findBatchByBatchFlow(stuUser.getStuBatId());
        stuUser.setStuBatName(bach.getBatchNo());//进修批次名称

        if (StringUtil.isNotBlank(stuUser.getClotherSizeId())) {
            stuUser.setClotherSizeName(stuUser.getClotherSizeId() + "号");//工作尺寸名称
        }
        stuUser.setMaxEduName(DictTypeEnum.UserEducation.getDictNameById(stuUser.getMaxEduId()));//最高学历
        stuUser.setTitleName(DictTypeEnum.UserTitle.getDictNameById(stuUser.getTitleId()));//职称
        stuUser.setCertifiedTypeName(DictTypeEnum.PracticeGenre.getDictNameById(stuUser.getCertifiedTypeId()));//执业类别
        stuUser.setHospitalLevelName(DictTypeEnum.HospitalRank.getDictNameById(stuUser.getHospitalLevelId()));//医院等级
        stuUser.setStuStatusId(RegStatusEnum.UnSubmit.getId());
        stuUser.setStuStatusName("未提交");
        singUpForm.setStuUser(stuUser);
        ExtInfoForm extInfo = singUpForm.getExtInfo();
        try {
            SysUser tempUser = singUpForm.getUser();
            ExtInfoForm tempInfo = singUpForm.getExtInfo();
            if (tempUser != null) {
                //因为刚注册的学员基本信息要先存入SYS_USER表，页面将基本信息（userName/sexId/sexName/userBirthday/
                // idNo/nationId/nationName/userPhone/userEmail/userHeadImg）映射到SysUser类中
                //然而报考的每一批次的记录要可编辑故基本信息要存入ExtInfoForm中即StuUserResume的resumeInfo（大字段）中
                //以下做的就是把SysUser中的基本信息更新到ExtInfoForm中
                if (tempInfo == null) {
                    tempInfo = new ExtInfoForm();
                }
                updateExtInfoFromUser(tempUser, tempInfo);
                singUpForm.setExtInfo(tempInfo);
            }
            docSingupBiz.saveSingupByPage(singUpForm);

            dataMap.put("operResult", GlobalConstant.OPRE_SUCCESSED);
            dataMap.put("formHaveSubmit1", "Y");
            if (extInfo != null) {
                if (extInfo.getCertifiedUriList()!=null&&!extInfo.getCertifiedUriList().isEmpty()) {
                    dataMap.put("formHaveSubmit2", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getVocationalLevel())) {
                    dataMap.put("formHaveSubmit3", "Y");
                    dataMap.put("resumeFlow", stuUser.getResumeFlow());
                }
            }
            data = JSONUtils.toJSONString(dataMap);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("operResult", GlobalConstant.OPRE_FAIL);
            data = JSONUtils.toJSONString(dataMap);
            return data;
        }
    }

    private void updateExtInfoFromUser(SysUser tempUser, ExtInfoForm tempInfo) {
        if (StringUtil.isNotBlank(tempUser.getUserName())) {
            tempInfo.setUserName(tempUser.getUserName());//姓名
        }
        if (StringUtil.isNotBlank(tempUser.getSexId())) {
            tempInfo.setSexId(tempUser.getSexId());//性别Id
        }
        if (StringUtil.isNotBlank(tempUser.getSexName())) {
            tempInfo.setSexName(tempUser.getSexName());//性别Name
        }
        if (StringUtil.isNotBlank(tempUser.getUserBirthday())) {
            tempInfo.setUserBirthday(tempUser.getUserBirthday());//生日
        }
        if (StringUtil.isNotBlank(tempUser.getIdNo())) {
            tempInfo.setIdNo(tempUser.getIdNo());//身份证号
        }
        if (StringUtil.isNotBlank(tempUser.getNationId())) {
            tempInfo.setNationId(tempUser.getNationId());//民族Id
        }
        if (StringUtil.isNotBlank(tempUser.getNationName())) {
            tempInfo.setNationName(tempUser.getNationName());//民族Name
        }
        if (StringUtil.isNotBlank(tempUser.getUserPhone())) {
            tempInfo.setUserPhone(tempUser.getUserPhone());//联系电话
        }
        if (StringUtil.isNotBlank(tempUser.getUserEmail())) {
            tempInfo.setUserEmail(tempUser.getUserEmail());//邮箱
        }
        if (StringUtil.isNotBlank(tempUser.getUserHeadImg())) {
            tempInfo.setUserHeadImg(tempUser.getUserHeadImg());//证件照地址
        }
    }

    /**
     * 学员信息
     */
    @RequestMapping("/getsingupinfoaudit")
    public String getSingUpInfoForAudit(String userFlow, String batchId, String flag, Model model) {
        SysUser user = this.userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(userFlow, batchId);
        if (null != doctorLst && doctorLst.size() > 0) {
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师
            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(doctorLst.get(0).getResumeInfo());
            model.addAttribute("extInfo", extInfo);
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        return "dwjxres/doctor/singupinfoforaudit";
    }

    @RequestMapping("/submitSingup")
    @ResponseBody
    public String submitSingup(SingUpForm form, String flag) {
        SysUser user = form.getUser();
        user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());//用户流水号
        user.setCretTypeId("Shenfenzheng");//只能为身份证类型
        if (StringUtil.isNotBlank(user.getIdNo())) {//验证身份证是否注册过
            SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
            if (null != existUser) {
                return "证件号码已注册！";
            }
        }
        user.setSexName(UserSexEnum.getNameById(user.getSexId()));//性别名称
        user.setNationName(UserNationEnum.getNameById(user.getNationId()));//名族名称

        StuUserResume stuUser = form.getStuUser();
        stuUser.setUserFlow(user.getUserFlow());//用户流水号
        stuUser.setSpeName(DictTypeEnum.DwjxSpe.getDictNameById(stuUser.getSpeId()));//进修专业名称
        String stuTimeId = stuUser.getStuTimeId();
        if(stuTimeId.equals("短期培训")){
            stuTimeId = "3";
        }
        stuUser.setStuTimeName(stuTimeId + "个月");//进修时间名称
        if (StringUtil.isNotBlank(stuUser.getStuBatId())) {
            StuBatch selectBatch = stuBatchBiz.searchStuBatch(stuUser.getStuBatId());
            if (selectBatch != null)
                stuUser.setStuBatName(selectBatch.getBatchNo());//进修批次名称
        }
        stuUser.setClotherSizeName(stuUser.getClotherSizeId() + "号");//工作尺寸名称
        stuUser.setMaxEduName(DictTypeEnum.UserEducation.getDictNameById(stuUser.getMaxEduId()));//最高学历
        stuUser.setTitleName(DictTypeEnum.UserTitle.getDictNameById(stuUser.getTitleId()));//职称
        stuUser.setCertifiedTypeName(DictTypeEnum.PracticeGenre.getDictNameById(stuUser.getCertifiedTypeId()));//执业类别
        stuUser.setHospitalLevelName(DictTypeEnum.HospitalRank.getDictNameById(stuUser.getHospitalLevelId()));//医院等级
        if ("false".equals(flag)) {
            stuUser.setStuStatusId(RegStatusEnum.UnSubmit.getId());
            stuUser.setStuStatusName("未提交");
        } else {
            stuUser.setStuStatusId(RegStatusEnum.Passing.getId());
            stuUser.setStuStatusName("待审核");
        }
        this.docSingupBiz.submitSingup(form);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 验证当前批次的网上报名是否暂存或者提交
     *
     * @return
     */
    @RequestMapping("/valideExist")
    @ResponseBody
    public String valideExist(String batchFlow) {
        SysUser user = GlobalContext.getCurrentUser();
        List<StuUserResume> stuUserLst = this.stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);
        if (null != stuUserLst && stuUserLst.size() > 0) {
            return stuUserLst.get(0).getResumeFlow();
        }
        return null;
    }

    /**
     * 打印录取通知书
     */
    @RequestMapping(value = "/recruitNotice")
    public void recruitNotice(String resumeFlow, String templeteName, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName());
            dataMap.put("speName", stuUser.getSpeName());//进修专业
            dataMap.put("stuBatName", stuUser.getStuBatName());//进修批次
            dataMap.put("stuTimeName", stuUser.getStuTimeName());//进修时间
            String stuTimeId = stuUser.getStuTimeId();
            if(stuTimeId.equals("短期培训")){
                stuTimeId = "3";
            }
            String date = DateUtil.addMonth(stuUser.getStuBatName().replace("年", "-").replace("月", ""), Integer.valueOf(stuTimeId));
            dataMap.put("stuBatEndName", formate(date));//进修批次+进修时间
            dataMap.put("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            dataMap.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            dataMap.put("trainFee", (stuUser.getStuBatch().getMonthFee()).intValue() * Integer.valueOf(stuTimeId) + "");//进修培养费
        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "";
        if ("cd".equals(templeteName)) {
            path = "/jsp/dwjxres/print/admissionNoticeTemeplete.docx";//进修申请表模板
        } else if ("xz".equals(templeteName)) {
            path = "/jsp/dwjxres/print/admissionNoticeXZTemeplete.docx";//进修申请表模板
        }
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "录取通知书.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    @RequestMapping(value = "/showCertificate")
    public String showCertificate(String resumeFlow, String flag,Model model,HttpServletRequest request) {

        StuUserResume stuUserResume = stuUserResumeBiz.getStuUserByKey(resumeFlow);
        model.addAttribute("stuUserResume",stuUserResume);
        String userFlow= stuUserResume.getUserFlow();
        SysUser sysUser = userBiz.readSysUser(userFlow);
        model.addAttribute("sysUser",sysUser);
        SysUser currentUser = GlobalContext.getCurrentUser();
        ServletContext application = request.getServletContext();
        Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        String orgName = sysCfgMap.get("jx_certificate_name");
        model.addAttribute("orgName",orgName);
        String batchFlow = stuUserResume.getStuBatId();
        String stutyTime = stuUserResume.getStuTimeId();
        if(stutyTime.equals("短期培训")){
            stutyTime = "3";
        }
        StuBatch stuBatch = stuBatchBiz.searchStuBatch(batchFlow);
        //进修开始时间
        String regDate = stuBatch.getBatchRegDate();
        //进修结束时间
        String finishDate = DateUtil.newDateOfAddMonths(regDate,Integer.parseInt(stutyTime));
        model.addAttribute("regDate",regDate);
        model.addAttribute("flag",flag);
        model.addAttribute("finishDate",finishDate);
        model.addAttribute("thisTime",stuUserResume.getModifyTime());
        model.addAttribute("completeNo","furtherEducation");
        return "dwjxres/doctor/showCertificate/info";
    }

    public String formate(String date) {
        String[] arry = date.split("-");
        if (null != arry) {
            if (arry.length == 2) {
                return arry[0] + "年" + arry[1] + "月";
            } else if (arry.length == 3) {
                return arry[0] + "年" + arry[1] + "月" + arry[2] + "日";
            }
        }
        return "";
    }

}
