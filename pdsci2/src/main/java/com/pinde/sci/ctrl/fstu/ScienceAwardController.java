package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.fstu.IFstuFileBiz;
import com.pinde.sci.biz.fstu.IFstuSatAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuSatBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.enums.fstu.RelTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.FstuSatAuthorList;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/fstu/aisr/sat")
public class ScienceAwardController extends GeneralController{
	@Autowired
	private IFstuSatBiz satBiz;
	@Autowired
	private IFstuSatAuthorBiz satAuthorBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IFstuAuditProcessBiz auditProcessBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IFstuFileBiz fstuFileBiz;
	/**
	 * 加载科技报奖列表
	 * @return
     */
	@RequestMapping(value = "/getList")
	public String list(FstuSat sat,Integer currentPage,HttpServletRequest request, Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		sat.setApplyUserFlow(currUser.getUserFlow());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<FstuSat> satList=satBiz.search(sat, null);
		Map<String,List<FstuSatAuthor>> allAuthorMap = new LinkedHashMap<String, List<FstuSatAuthor>>();
		List<FstuSatAuthor> allAuthorList  = satAuthorBiz.searchAuthorList(new FstuSatAuthor());
		for(FstuSatAuthor a : allAuthorList){
			List<FstuSatAuthor> list =  allAuthorMap.get(a.getSatFlow());
			if(list == null){
				list = new ArrayList<FstuSatAuthor>();
			}
			list.add(a);
			allAuthorMap.put(a.getSatFlow(), list);
		}
		model.addAttribute("satList",satList);
		model.addAttribute("allAuthorMap", allAuthorMap);
		return "/fstu/aisr/sat/scienceAward";
	}
	/**
	 * 编辑科技信息
	 * @param satFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(String satFlow, Model model){
		List<PubFile> fileList = null;
		if(StringUtil.isNotBlank(satFlow)){
			FstuSat sat=satBiz.readSat(satFlow);
			model.addAttribute("sat", sat);
			//查询科技作者信息
			FstuSatAuthor author = new FstuSatAuthor();
			author.setSatFlow(satFlow);
			List<FstuSatAuthor> satAuthorList=satAuthorBiz.searchAuthorList(author);
			model.addAttribute("satAuthorList",satAuthorList);
			fileList = fileBiz.searchByProductFlow(satFlow);
		}
		model.addAttribute("fileList", fileList);
		return "/fstu/aisr/sat/edit";
	}

	//下载附件
	@RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		satBiz.down(file,response);
	}

	/**
	 * 保存科技报奖及作者
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value={"/save"})
	@ResponseBody
	public String save(String jsondata, HttpServletRequest request) throws IOException{
		FstuSatAuthorList aList = JSON.parseObject(jsondata, FstuSatAuthorList.class);

		FstuSat sat = aList.getSat();
		List<FstuSatAuthor> authorList = aList.getAuthorList();
		//-----枚举：根据相关的ID获得name-----
		sat.setAchTypeName(DictTypeEnum.SatType.getDictNameById(sat.getAchTypeId()));
		sat.setPrizedGradeName(DictTypeEnum.SatGrade.getDictNameById(sat.getPrizedGradeId()));
		sat.setPrizedLevelName(DictTypeEnum.SatLevel.getDictNameById(sat.getPrizedLevelId()));
		sat.setCategoryName(DictTypeEnum.SubjectCategories.getDictNameById(sat.getCategoryId()));
		sat.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(sat.getProjSourceId()));
		//所属单位
		sat.setOrgBelongName(DictTypeEnum.SatOrg.getDictNameById(sat.getOrgBelongId()));
		//状态
		sat.setOperStatusId(ProStatusEnum.Draft.getId());
		sat.setOperStatusName(ProStatusEnum.Draft.getName());
		//获取申请单位信息
		SysUser currUser = GlobalContext.getCurrentUser();
		sat.setApplyOrgFlow(currUser.getOrgFlow());
		sat.setApplyOrgName(currUser.getOrgName());
		//申请人
		sat.setApplyUserFlow(currUser.getUserFlow());
		sat.setApplyUserName(currUser.getUserName());
		//科室信息
		sat.setDeptFlow(currUser.getDeptFlow());
		sat.setDeptName(currUser.getDeptName());

		//根据科技作者相关的ID枚举获得相对应的Name（性别、学历、职称）
		for(int i = 0; i<authorList.size();i++){
			authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
			authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
			authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
			authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
		}
		//保存附件
		List<PubFile> pubFileList = new ArrayList<>();
		String resultPath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		for (int i = 0; i < fileList.size(); i++) {
			PubFile pubFile = aList.getFileList().get(i);
			if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
				//保存附件
				String flow = pubFile.getFileFlow();
				if (StringUtil.isNotBlank(flow)) {
					pubFile.setFileFlow(flow);
				}
				resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuSat", flow);
				pubFile.setFileName(fileList.get(i).getOriginalFilename());
				pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
				pubFile.setFileUpType("1");
			}
			pubFileList.add(pubFile);
		}
		int reslut = satBiz.save(sat, authorList, pubFileList, null);
		if(reslut == GlobalConstant.ONE_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;

	}

	@RequestMapping(value = "/deleteAuthor",method={RequestMethod.GET})
	@ResponseBody
	public  String deleteAuthor(String authorFlow){
		if(StringUtil.isNotBlank(authorFlow)){
			FstuSatAuthor author = new FstuSatAuthor();
			author.setAuthorFlow(authorFlow);
			satAuthorBiz.deleteOneAuthor(author);
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(String satFlow){
		if(StringUtil.isNotBlank(satFlow)){
			FstuSat sat = new FstuSat();
			sat.setSatFlow(satFlow);
			sat.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

			FstuSatAuthor author = new FstuSatAuthor();
			author.setSatFlow(satFlow);
			List<FstuSatAuthor> authorList = satAuthorBiz.searchAuthorList(author);
			for(FstuSatAuthor a : authorList){
				a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			}
			//附件
			List<PubFile> fileList = fileBiz.searchByProductFlow(satFlow);
			if(fileList != null && !fileList.isEmpty()){
				for(PubFile file:fileList){
					file.setRecordStatus("N");
				}
			}
			int result = satBiz.save(sat, authorList, fileList,null);
			if(result == GlobalConstant.ONE_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping(value="/submitAudit",method={RequestMethod.GET})
	@ResponseBody
	public String submitAudit(String satFlow,Model model){
		FstuSat sat=satBiz.readSat(satFlow);
		sat.setOperStatusId(ProStatusEnum.Apply.getId());
		sat.setOperStatusName(ProStatusEnum.Apply.getName());

		FstuAuditProcess auditProcess=new FstuAuditProcess();
		auditProcess.setRelFlow(satFlow);
		auditProcess.setRelTypeId(RelTypeEnum.Award.getId());
		auditProcess.setProStatusId(ProStatusEnum.Apply.getId());
		auditProcess.setProStatusName(ProStatusEnum.Apply.getName());
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		String userName = currUser.getUserName();
		auditProcess.setOperUserFlow(userFlow);
		auditProcess.setOperUserName(userName);
		String orgFlow = currUser.getOrgFlow();
		String orgName = currUser.getOrgName();
		auditProcess.setOperOrgFlow(orgFlow);
		auditProcess.setOperOrgName(orgName);
		String currTime = DateUtil.getCurrDateTime();
		auditProcess.setOperTime(currTime);
		auditProcess.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		int result = auditProcessBiz.add(auditProcess,sat);
		if(result == GlobalConstant.ONE_LINE){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value="/auditList/{role}",method={RequestMethod.POST,RequestMethod.GET})
	public String auditList(@PathVariable String role, Integer currentPage, FstuSat sat, FstuSatAuthor author, SysOrg org, Model model, HttpServletRequest request){
		PageHelper.startPage(currentPage, getPageSize(request));
		model.addAttribute("role",role);
		SysUser currUser = GlobalContext.getCurrentUser();
		if("adminSat".equals(role)) {
			if (StringUtil.isBlank(sat.getOperStatusId())) {
				sat.setOperStatusId(ProStatusEnum.Apply.getId());
			}
		}
		if("admin".equals(role)) {
			if (StringUtil.isBlank(sat.getOperStatusId())) {
				sat.setOperStatusId("all");
			}
		}
		List<FstuSat> sats = new ArrayList<>();
		List<FstuSat> satList = new ArrayList<>();
		if("deptMaster".equals(role)){
			//科主任角色查询
			String userFlow = currUser.getUserFlow();
			String dept = currUser.getDeptFlow();
			List<String> deptFlowList = new ArrayList<>();
			if(StringUtil.isNotBlank(dept)){
				deptFlowList.add(dept);
			}
			List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
			if(userDepts!=null&&userDepts.size()>0){
				for(SysUserDept userDept:userDepts){
					String deptFlow = userDept.getDeptFlow();
					deptFlowList.add(deptFlow);
				}
			}
			if(deptFlowList.size()==0){
				PageHelper.startPage(currentPage, getPageSize(request));
				sat.setApplyUserFlow(userFlow);
				sats = satBiz.search(sat,null);
			}else {
				PageHelper.startPage(currentPage, getPageSize(request));
				sats = satBiz.search(sat, deptFlowList);
			}
		}else{
				PageHelper.startPage(currentPage, getPageSize(request));
				sats = satBiz.search(sat,null);
		}

		Map<String, List<FstuSatAuthor>> allAuthorMap = new HashMap<String, List<FstuSatAuthor>>();
		List<FstuSatAuthor> satAuthorList = satAuthorBiz.searchAuthorList(new FstuSatAuthor());
		if(satAuthorList != null && !satAuthorList.isEmpty()){
			for(FstuSatAuthor a : satAuthorList){
				List<FstuSatAuthor> authorList = allAuthorMap.get(a.getSatFlow());
				if(authorList == null){
					authorList = new ArrayList<FstuSatAuthor>();
				}
				authorList.add(a);
				allAuthorMap.put(a.getSatFlow(), authorList);
			}
		}
		//作者查询
		String pageFlag = "Y";
		if(StringUtil.isNotBlank(author.getAuthorName())){
			pageFlag = "N";
			for(FstuSat s:sats){
				boolean addFlag = false;
				List<FstuSatAuthor> authorByNameList = allAuthorMap.get(s.getSatFlow());
				if(authorByNameList != null){
					for(FstuSatAuthor na : authorByNameList){
						if(na.getAuthorName().equals(author.getAuthorName())){
							addFlag = true;
							break;
						}
					}
				}
				if(addFlag){
					satList.add(s);
				}
			}
		}else {
			satList = sats;
		}
		model.addAttribute("pageFlag",pageFlag);
		model.addAttribute("satList",satList);
		model.addAttribute("allAuthorMap", allAuthorMap);
		return "fstu/aisr/sat/auditList";
	}

	/**
	 * 跳转到审核界面
	 * @param satFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/audit")
	public String audit(String satFlow, Model model){
		//根据成果流水号查询相关信息，用于加载审核页面
		PubFile file = null;
		List<PubFile> fileList=null;
		if(StringUtil.isNotBlank(satFlow)){
			//查询成果本身
			FstuSat sat=satBiz.readSat(satFlow);
			model.addAttribute("sat", sat);
			//查询成果作者
			FstuSatAuthor author = new FstuSatAuthor();
			author.setSatFlow(satFlow);
			List<FstuSatAuthor> satAuthorList=satAuthorBiz.searchAuthorList(author);
			model.addAttribute("satAuthorList", satAuthorList);
			//查询成果附件
			fileList=fileBiz.searchByProductFlow(satFlow);
			if(fileList!=null && !fileList.isEmpty()){
				model.addAttribute("fileList", fileList);
			}
		}
		FstuSat sat=satBiz.readSat(satFlow);
		model.addAttribute("sat", sat);
		return "fstu/aisr/sat/audit";
	}

	/**
	 * 保存审核结果
	 * @param agreeFlag
	 * @param auditContent
	 * @param satFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveAudit",method={RequestMethod.POST})
	@ResponseBody
	public String saveAudit(String agreeFlag,String auditContent,FstuSat sat,Model model){
		FstuAuditProcess process=auditProcessBiz.search(sat.getSatFlow()).get(0);
//-----枚举：根据相关的ID获得name-----
		sat.setAchTypeName(DictTypeEnum.SatType.getDictNameById(sat.getAchTypeId()));
		sat.setPrizedGradeName(DictTypeEnum.SatGrade.getDictNameById(sat.getPrizedGradeId()));
		sat.setPrizedLevelName(DictTypeEnum.SatLevel.getDictNameById(sat.getPrizedLevelId()));
		sat.setCategoryName(DictTypeEnum.SubjectCategories.getDictNameById(sat.getCategoryId()));
		sat.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(sat.getProjSourceId()));
		if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
			sat.setOperStatusId(ProStatusEnum.Passed.getId());
			sat.setOperStatusName(ProStatusEnum.Passed.getName());
			process.setProStatusId(ProStatusEnum.Passed.getId());
			process.setProStatusName(ProStatusEnum.Passed.getName());
		}
		else{
			sat.setOperStatusId(ProStatusEnum.UnPassed.getId());
			sat.setOperStatusName(ProStatusEnum.UnPassed.getName());
			process.setProStatusId(ProStatusEnum.UnPassed.getId());
			process.setProStatusName(ProStatusEnum.UnPassed.getName());
		}
//		process.setOperTime(process.getCreateTime());
//		SysUser currUser = GlobalContext.getCurrentUser();
//		process.setOperateUserFlow(currUser.getUserFlow());
//		process.setOperateUserName(currUser.getUserName());

		process.setAuditContent(auditContent);
//		satBiz.updateSatStatus(sat, process);
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		String userName = currUser.getUserName();
		process.setOperUserFlow(userFlow);
		process.setOperUserName(userName);
		String orgFlow = currUser.getOrgFlow();
		String orgName = currUser.getOrgName();
		process.setOperOrgFlow(orgFlow);
		process.setOperOrgName(orgName);
		String currTime = DateUtil.getCurrDateTime();
		process.setOperTime(currTime);
		sat.setLastAuditAdvice(auditContent);
		sat.setLastAuditTime(currTime);
		int result = auditProcessBiz.add(process,sat);
		if(result == GlobalConstant.ONE_LINE){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 审核记录查看
	 * @return
	 */
	@RequestMapping("/auditProcess")
	public String auditProcess(String flow , Model model){
		List<FstuAuditProcess> processList = this.auditProcessBiz.search(flow);
		model.addAttribute("processList" , processList);
		return "fstu/aisr/sat/auditProcess";
	}
}