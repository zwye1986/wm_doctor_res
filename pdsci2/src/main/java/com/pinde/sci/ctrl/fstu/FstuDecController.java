package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.FileUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.fstu.StudyStatusEnum;
import com.pinde.sci.form.fstu.FstuProjAndFileForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fstu/dec")
public class FstuDecController extends GeneralController{
	@Autowired
	private IFstuProjBiz fstuProjBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IFstuStudyBiz fstuStudyBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IFstuCreditBiz fstuCreditBiz;
	@Autowired
	private IFstuFileBiz fstuFileBiz;
	/**
	 * FstuProj查询
	 * @param model
	 * @param proj
	 */
	@RequestMapping(value="/projList/{role}")
	public String projList(@PathVariable String role,Model model,FstuProj proj,Integer currentPage,HttpServletRequest request){
		PageHelper.startPage(currentPage,getPageSize(request));
		model.addAttribute("roleFlag", role);
		List<FstuProj> projList=null;
			projList=fstuProjBiz.search(proj);
		model.addAttribute("projList", projList);
		return "/fstu/dec/proj/projList";
	}
	/**
	 * FstuProj编辑
	 * @param flow
	 * @param model
	 */
	@RequestMapping("/add/{role}")
	public String add(@PathVariable String role,String flow,String viewFlag,Model model){
		model.addAttribute("roleFlag", role);
		if(StringUtil.isNotBlank(viewFlag)){
			model.addAttribute("viewFlag",viewFlag);
		}
		List<PubFile> fileList = null;
		if(StringUtil.isNotBlank(flow)){
			FstuProj proj=fstuProjBiz.findByFlow(flow);
			//判断是否是负责人
			String userCode = GlobalContext.getCurrentUser().getUserCode();
			if(userCode.equals(proj.getApplyUserFlow())){
				model.addAttribute("canDown","canDown");
			}
			model.addAttribute("proj", proj);
			fileList = fileBiz.searchByProductFlow(flow);

		}
		model.addAttribute("fileList", fileList);
		//加载所有人员LIST
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		List<SysUser> users = userBiz.getUserByOrg(orgFlow);
		Map<String,String> userMap = new HashMap<>();
		if(users!=null&&users.size()>0){
			for(SysUser user:users){
				userMap.put(user.getUserName(),user.getUserCode());
			}
		}
		model.addAttribute("userMap", JSONObject.toJSONString(userMap));
		return "/fstu/dec/proj/edit";
	}
	@RequestMapping("/save")
	@ResponseBody
	public String save(String jsondata,HttpServletRequest request) throws IOException{
		//FstuProjAndFileForm
		FstuProjAndFileForm fstuProjAndFileForm = JSON.parseObject(jsondata, FstuProjAndFileForm.class);
		FstuProj proj = fstuProjAndFileForm.getFstuProj();
		String success = GlobalConstant.SAVE_SUCCESSED;
		String fail = GlobalConstant.SAVE_FAIL;
		if(GlobalConstant.RECORD_STATUS_N.equals(proj.getRecordStatus())){
			success = GlobalConstant.DELETE_SUCCESSED;
			fail = GlobalConstant.DELETE_FAIL;
		}
		//保存附件
		List<PubFile> pubFileList = fstuProjAndFileForm.getFileList();
		String resultPath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		for (int i = 0; i < fileList.size(); i++) {
			PubFile pubFile = pubFileList.get(i);//aList.getPubFileList().get(i);
			if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
				//保存附件
				String flow = pubFile.getFileFlow();
				if (StringUtil.isNotBlank(flow)) {
					pubFile.setFileFlow(flow);
				}
				resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuImages", flow);
				pubFile.setFileName(fileList.get(i).getOriginalFilename());
				pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
				pubFile.setFileUpType("1");
			}
			pubFileList.add(pubFile);
		}
		int result=fstuProjBiz.saveProjAndFile(proj,pubFileList);
		if(result==GlobalConstant.ZERO_LINE){
			return fail;
		}else{
			return success;
		}
	}

	//下载附件
	@RequestMapping(value = {"/fileDown" }, method = {RequestMethod.POST,RequestMethod.GET})
	public void fileDown(String fileFlow,HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		FileUtil.downLoad(file.getFilePath(),file.getFileName(),response);
	}
	@RequestMapping("/delPro")
	@ResponseBody
	public String delPro(FstuProj proj){
		int result=fstuProjBiz.save(proj);
		if(result==GlobalConstant.ONE_LINE){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * fstuStudy查询
	 * @param roleFlag
	 * @param user
	 * @param model
	 */
	@RequestMapping(value="/fstuStudy/{roleFlag}")
	public String fstuStudy(@PathVariable String roleFlag,SysUser user,Model model,Integer currentPage,HttpServletRequest request){
		
		model.addAttribute("roleFlag", roleFlag);
		List<SysUser> userList=null; 
		List<String> auditList=null;
		//判断是否是卫生局
		if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
			PageHelper.startPage(currentPage,getPageSize(request));
			userList=userBiz.searchUser(user);
			auditList=new ArrayList<String>();
			auditList.add(StudyStatusEnum.Submit.getId());
			auditList.add(StudyStatusEnum.FirstAudit.getId());
		}else{
			SysUser sysUser = GlobalContext.getCurrentUser();
			String userOrgFlow= sysUser.getOrgFlow();
			user.setOrgFlow(userOrgFlow);
			PageHelper.startPage(currentPage,getPageSize(request));
			userList=userBiz.searchUser(user);
		}
		if(userList!=null&& !userList.isEmpty()){
			Map<String, SysUser> sysUserMap = new HashMap<String, SysUser>();
			List<String> flowList=new ArrayList<String>();
			for(SysUser s:userList){
				String userFlow=s.getUserFlow();
				sysUserMap.put(userFlow, s);
				flowList.add(userFlow);
			}
			if(currentPage==null){
				currentPage=1;
			}
			
			model.addAttribute("userList", userList);
			model.addAttribute("sysUserMap", sysUserMap);
			List<FstuStudy> studyList=fstuStudyBiz.searchByUserFlows(flowList,auditList);
			model.addAttribute("studyList", studyList);
		}
		return "/fstu/dec/study/list";
	}
	/**
	 * 编辑
	 * @param flow
	 * @param model
	 */
	@RequestMapping("/edit")
	public String edit(String flow,Model model){
		String userFlow = "";
		if(StringUtil.isNotBlank(flow)){
			FstuStudy study=fstuStudyBiz.findByFlow(flow);
			if(study!=null){
				userFlow = study.getUserFlow();
				model.addAttribute("study", study);
			}
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		List<FstuStudy> studyList = fstuStudyBiz.searchStudys();
		List<String> userFlows = new ArrayList<String>();
		if(studyList!=null && studyList.size()>0){
			for(FstuStudy stu : studyList){
				String studyUserFlow = stu.getUserFlow();
				if(!studyUserFlow.equals(userFlow)){
					userFlows.add(studyUserFlow);
				}
			}
		}
		List<SysUser> userList=userBiz.searchUserNotInUserFlows(currUser.getOrgFlow(),userFlows);
		model.addAttribute("userList", userList);
		return "/fstu/dec/study/edit";
	}
	/**
	 * 
	 * @param study
	 */
	@RequestMapping("/saveStudy")
	@ResponseBody
	public String save(FstuStudy study){
		
		String success = GlobalConstant.SAVE_SUCCESSED;
		String fail = GlobalConstant.SAVE_FAIL;
		if(GlobalConstant.RECORD_STATUS_N.equals(study.getRecordStatus())){
			success = GlobalConstant.DELETE_SUCCESSED;
			fail = GlobalConstant.DELETE_FAIL;
		}
		if(StringUtil.isNotBlank(study.getAuditStatusId())){
			success = GlobalConstant.OPRE_SUCCESSED;
			fail = GlobalConstant.OPRE_FAIL;
		}
		if(StringUtil.isNotBlank(study.getAuditStatusId())){
			study.setAuditStatusName(StudyStatusEnum.getNameById(study.getAuditStatusId()));
		}
		String userFlow = study.getUserFlow();
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				study.setUserName(user.getUserName());
				study.setOrgFlow(user.getOrgFlow());
				study.setOrgName(user.getOrgName());
			}
		}
		int studyFlow=fstuStudyBiz.saveOrEdit(study);
		if(studyFlow==GlobalConstant.ZERO_LINE){
			return fail;
		}else{
			return success;
		}
	}
	/**
	 * 自动读值
	 * @param userFlow
	 * @return
	 */
	@RequestMapping(value="/readUser",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public SysUser readUser(String userFlow){
		SysUser user = userBiz.readSysUser(userFlow);
		return user;
	}
	/**
	 * SysUser查询
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/creditList/{roleFlag}")
	public String userList(@PathVariable String roleFlag,SysUser user,Model model,Integer currentPage,HttpServletRequest request){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		model.addAttribute("roleFlag", roleFlag);
		List<SysUser> userList=null;
		if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
			userList=userBiz.searchUser(user);
		}else{
			SysUser currsysUser = GlobalContext.getCurrentUser();
			String userOrgFlow= currsysUser.getOrgFlow();
			user.setOrgFlow(userOrgFlow);
			userList=userBiz.searchUser(user);
		}
		model.addAttribute("userList", userList);
		return "/fstu/dec/credit/list";
	}
	
	@RequestMapping("/editCredit")
	public String editCredit(String userFlow,Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user=userBiz.readSysUser(userFlow);
			model.addAttribute("user", user);
			List<FstuCredit> credit=fstuCreditBiz.searchCreditByUserFlow(userFlow);
			model.addAttribute("credit", credit);
		}
		return "/fstu/dec/credit/edit";
	}
	@RequestMapping("/saveCredit")
	@ResponseBody
	public String saveCredit(@RequestBody List<FstuCredit> credits){
		
		int result=fstuCreditBiz.saveCreditList(credits);
		if(result==GlobalConstant.ONE_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	@RequestMapping("/delCredit")
	@ResponseBody
	public String delCredit(FstuCredit credit){
		int result=fstuCreditBiz.edit(credit);
		if(result==GlobalConstant.ONE_LINE){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/******************************余杭定制独立项目**********************************/
	//项目登记
	@RequestMapping(value="/project/register")
	public String projList(String flag,FstuProj proj,Integer currentPage,Model model){
		model.addAttribute("flag", flag);
		PageHelper.startPage(currentPage,10);
		List<FstuProj> projList = fstuProjBiz.search(proj);
		model.addAttribute("projList", projList);
		return "/fstu/dec/proj/register";
	}
	//项目登记编辑页
	@RequestMapping("/editProject")
	public String add(String flow,String viewFlag,Model model){
		model.addAttribute("viewFlag",viewFlag);
		if(StringUtil.isNotBlank(flow)){
			FstuProj proj = fstuProjBiz.findByFlow(flow);
			//判断是否是负责人
			String userCode = GlobalContext.getCurrentUser().getUserCode();
			if(userCode.equals(proj.getApplyUserFlow())){
				model.addAttribute("canDown","canDown");
			}
			model.addAttribute("proj", proj);
			List<PubFile> fileList = fileBiz.searchByProductFlow(flow);
			model.addAttribute("fileList", fileList);
		}
		return "/fstu/dec/proj/editProj";
	}
	/******************************余杭定制独立项目**********************************/
}