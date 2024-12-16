package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.model.PubFile;
import com.pinde.core.model.ResAnnualAssessment;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.ResDiscipleInfo;
import com.pinde.sci.model.mo.ResDiscipleTeacherInfo;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResGraduationAssessmentWithBLOBs;
import com.pinde.sci.model.res.AnnualAssessmentExt;
import com.pinde.sci.model.res.ResGraduationAssessmentExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/disciple")
public class ResDiscipleController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResDiscipleController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResDiscipleInfoBiz discipleInfoBiz;
	@Autowired
	private IResDiscipleTeacherInfoBiz teacherInfoBiz;

    @Autowired
    private IDiscipleBiz discipleBiz;
	@Autowired
	private IResGraduationAssessmentBiz graduationAssessmentBiz;
	@Autowired
	private IFileBiz pubFileBiz;
    @Autowired
    private IAnnualAssessmentBiz assessmentBiz;
	@Autowired
	private IUserBiz userBiz;
	/**
	 * 学员跟师管理九宫格主页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(Model model) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null ){
			ResDoctor resDoctor=doctorBiz.readDoctor(currUser.getUserFlow());
			model.addAttribute("resDoctor",resDoctor);
			ResGraduationAssessmentExt ext=graduationAssessmentBiz.getDocGraduationAssessment(currUser.getUserFlow());
			model.addAttribute("ext",ext);
			PubFile search=new PubFile();
			search.setCreateUserFlow(currUser.getUserFlow());
			search.setProductType("GRADUATION_FILE");
			PubFile pf=pubFileBiz.readDocGraduationFile(search);
			model.addAttribute("file",pf);
		}
		return "res/disciple/main";
	}
	/**
	 * 学员跟师学习手册封面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/discipleIndex")
	public String discipleIndex(Model model) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("currUser",currUser);
		if(currUser != null ){
			ResDoctor resDoctor=doctorBiz.readDoctor(currUser.getUserFlow());
			model.addAttribute("resDoctor",resDoctor);
			ResDiscipleInfo info=discipleInfoBiz.readResDiscipleInfo(currUser.getUserFlow());
			model.addAttribute("resDiscipleInfo",info);
			String teacherNames=discipleInfoBiz.getTeacherName(currUser.getUserFlow());
			model.addAttribute("teacherNames",teacherNames);
		}
		return "res/disciple/discipleIndex";
	}
	@RequestMapping(value="showFollowTeacherRecord")
	public String showFollowTeacherRecord(){
		return "res/disciple/followTeacherRecord";
	}
	/**
	 * 保存手册封面
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/saveDiscipleInfo")
	@ResponseBody
	public String saveDiscipleInfo(ResDiscipleInfo bean, Model model) throws Exception{
		int count=discipleInfoBiz.savaResDiscipleInfo(bean);
		if(count==1)
		{
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 保存手册封面
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/saveDiscipleTeacherInfo")
	@ResponseBody
	public String saveDiscipleTeacherInfo(ResDiscipleTeacherInfo bean, Model model) throws Exception{
		if(StringUtil.isNotBlank(bean.getSexId()))
		bean.setSexName(UserSexEnum.getNameById(bean.getSexId()));
		int count=teacherInfoBiz.savaResDiscipleTeacherInfo(bean);
		if(count==1)
		{
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 跟师指导老师简况
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/discipleTeacherIndex")
	public String discipleTeacherIndex(Model model,String infoFlow,String role) throws Exception{
		if(StringUtil.isBlank(role)) {
			SysUser currUser = GlobalContext.getCurrentUser();
			ResDiscipleTeacherInfo info = teacherInfoBiz.readResDiscipleTeacherInfo(currUser.getUserFlow());
			model.addAttribute("teacherInfo", info);
			model.addAttribute("user", currUser);
		}else if(role.equals("teacher")||role.equals("admin")||role.equals("global")||role.equals("university")){
			ResDiscipleTeacherInfo info = teacherInfoBiz.readResDiscipleTeacherInfoByFlow(infoFlow);
			model.addAttribute("teacherInfo", info);
		}
		model.addAttribute("role", role);
		return "res/disciple/discipleTeacherIndex";
	}

	//查询师承老师列表
	@RequestMapping(value = "/searchTeachers")
	@ResponseBody
	public List<SysUser> searchTeachers(String orgFlow,String userName, HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		List<SysUser> sysUserList=null;//初始化查询结果
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("roleFlow",sysCfgMap.get("res_disciple_role_flow"));
		if(StringUtil.isBlank(sysCfgMap.get("res_disciple_role_flow"))){
			return null;
		}
		paramMap.put("wsId",wsId);
		paramMap.put("userName",userName);
		paramMap.put("orgFlow",orgFlow);
		sysUserList = userBiz.searchUserWithRole(paramMap);
		return sysUserList;
	}
	//查找单个老师信息
	@RequestMapping(value = "/searchSingleTeacher")
	@ResponseBody
	public SysUser searchSingleTeacher(String userFlow) {
		SysUser user = userBiz.readSysUser(userFlow);
		return user;
	}
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/showUploadFile",method={RequestMethod.POST,RequestMethod.GET})
    public String showUploadFile( String fileFlow,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        PubFile file=pubFileBiz.readFile(fileFlow);
		model.addAttribute("file",file);
		return  "res/disciple/uploadFile";
    }
	/**
	 * 保存上传论文附件
	 * @param uploadFile
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	@ResponseBody
	public String checkUploadFile(String productType, String fileFlow, MultipartFile uploadFile, Model model) throws UnsupportedEncodingException {
		SysUser user = GlobalContext.getCurrentUser();
		if(uploadFile!=null && !uploadFile.isEmpty()){
			String fileResult = graduationAssessmentBiz.checkFile(uploadFile);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(fileResult)) {
				return fileResult;
			}else{
				return graduationAssessmentBiz.saveFileToDirs(fileFlow,productType, uploadFile,user);
			}
		}
		return "上传附件不能为空";
	}
	@RequestMapping(value = {"/down" }, method = RequestMethod.GET)
	public void down (String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile	file  =  pubFileBiz.readFile(fileFlow);
		graduationAssessmentBiz.down(file,response);
	}
	@RequestMapping(value = {"/downImg" }, method = RequestMethod.GET)
	public void downImg (String recordFlow, final HttpServletResponse response) throws Exception{
		ResGraduationAssessmentWithBLOBs ext=graduationAssessmentBiz.getGraduationAssessmentWithBlobByFlow(recordFlow);
		graduationAssessmentBiz.downImg(ext,response);
	}

    /**
     * 初始化年度考核列表
     * @param doctorFlow
     * @param model
     * @return
     */
    @RequestMapping("/initAnnualAssessment/{roleScope}")
    public String initAnnualAssessment(@PathVariable String roleScope, String doctorFlow,String operaFlag, Model model) {
        model.addAttribute("roleScope",roleScope);
        model.addAttribute("operaFlag",operaFlag);
        SysUser currUser = GlobalContext.getCurrentUser();
        List<String> statusIdList = new ArrayList<>();
        //如果是学员
        if (currUser != null && roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            ResDoctor resDoctor=doctorBiz.readDoctor(currUser.getUserFlow());
            model.addAttribute("resDoctor",resDoctor);
        }
        ResAnnualAssessment assessment = new ResAnnualAssessment();
        assessment.setDoctorFlow(doctorFlow);
        List<ResAnnualAssessment> assessmentList ;

        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleScope)) {
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(operaFlag)) {
                statusIdList.add(DiscipleStatusEnum.DiscipleAudit.getId());
                statusIdList.add(DiscipleStatusEnum.DiscipleBack.getId());
                statusIdList.add(DiscipleStatusEnum.AdminAudit.getId());
                statusIdList.add(DiscipleStatusEnum.AdminBack.getId());
            }
//			statusIdList.add(DiscipleStatusEnum.Submit.getId());
            statusIdList.add(DiscipleStatusEnum.DiscipleAudit.getId());
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleScope)) {
			statusIdList.add(DiscipleStatusEnum.AdminAudit.getId());
        }
        assessmentList = assessmentBiz.findAnnualAssessmentList(assessment,statusIdList);
        model.addAttribute("assessmentList",assessmentList);
        return "res/disciple/doctor/initAnnualAssessment";
    }
    @RequestMapping("/initAnnualAssessmentDetail/{roleScope}")
    public String initAnnualAssessmentDetail(@PathVariable String roleScope,String doctorFlow,String recordFlow,
											 String operaFlag, Model model, String recordYear,String discipleStartDate,
											 String discipleEndDate){
        model.addAttribute("roleScope",roleScope);
        model.addAttribute("operaFlag",operaFlag);
        if(StringUtil.isNotBlank(recordFlow)){
			//查询附件
			List<PubFile> discipleFiles = pubFileBiz.searchByProductFlow(recordFlow);
			model.addAttribute("discipleFiles",discipleFiles);
        }
		Map<String,Object> paramMap = new HashMap<>();
//		if(StringUtil.isNotBlank(discipleStartDate) && StringUtil.isNotBlank(discipleEndDate)){
//			String startDate = discipleStartDate.replace("-","") + "000000";
//			String endDate = discipleEndDate.replace("-","") + "000000";
//			paramMap.put("startDate",startDate);
//			paramMap.put("endDate",endDate);
//		}
		paramMap.put("roleScope",roleScope);
		paramMap.put("startDate",discipleStartDate);
		paramMap.put("endDate",discipleEndDate);
		paramMap.put("recordFlow",recordFlow);
		paramMap.put("doctorFlow",doctorFlow);
		paramMap.put("recordYear",recordYear);

        AnnualAssessmentExt annualAssessmentExt = assessmentBiz.initAnnualAssessmentExt(paramMap);
		if (annualAssessmentExt != null	&& StringUtil.isNotBlank(discipleStartDate)
				&& StringUtil.isNotBlank(discipleEndDate)) {
			annualAssessmentExt.setDiscipleStartDate(discipleStartDate);
			annualAssessmentExt.setDiscipleEndDate(discipleEndDate);
			annualAssessmentExt.setRecordYear(recordYear);
		}
		if(StringUtil.isNotBlank(recordFlow) && annualAssessmentExt != null){
			annualAssessmentExt.setDiscipleStartDate(annualAssessmentExt.getStudyStartDate());
			annualAssessmentExt.setDiscipleEndDate(annualAssessmentExt.getStudyEndDate());
		}
		model.addAttribute("annualAssessmentExt",annualAssessmentExt);
        model.addAttribute("recordYear",recordYear);
        model.addAttribute("discipleStartDate",discipleStartDate);
        model.addAttribute("discipleEndDate",discipleEndDate);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleScope)) {
			return "res/disciple/doctor/annualAssessmentDetail4Global";
		}
		return "res/disciple/doctor/annualAssessmentDetail";
    }

    @RequestMapping("/annualAssessmentOneKeyAudit")
    @ResponseBody
    public String annualAssessmentOneKeyAudit(String teacherFlow){
        if(StringUtil.isBlank(teacherFlow)){
            teacherFlow =  GlobalContext.getCurrentUser().getUserFlow();
        }
        int result = assessmentBiz.oneKeyAudit(teacherFlow);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
    }

    @RequestMapping("/isDateOverlapped")
    @ResponseBody
    public String isDateOverlapped(String startDate,String endDate,String doctorFlow){
        if(StringUtil.isBlank(doctorFlow)){
            doctorFlow =  GlobalContext.getCurrentUser().getUserFlow();
        }
        boolean flag = false;
        List<ResAnnualAssessment> assessmentList =  assessmentBiz.findAnnualAssessmentByDocFlow(doctorFlow);
        for(ResAnnualAssessment assessment :assessmentList){
            flag =    DateUtil.isDateOverlapped(startDate,endDate,assessment.getStudyStartDate(),assessment.getStudyEndDate());
            if (flag){
                break;
            }
        }

        if(flag){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
        }else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
    }


}
