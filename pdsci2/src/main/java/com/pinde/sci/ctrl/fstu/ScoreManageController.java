package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.form.fstu.FstuScoreAndFileForm;
import com.pinde.sci.form.fstu.ScoreManageForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/fstu/score")
public class ScoreManageController extends GeneralController{
	@Autowired
	private IAcaScoreBiz scoreBiz;
	@Autowired
	private IAcademicBiz academicBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IFstuBookBiz bookBiz;
	@Autowired
	private IFstuThesisBiz thesisBiz;
	@Autowired
	private IFstuSatBiz satBiz;
	@Autowired
	private IFstuFileBiz fstuFileBiz;
	@Autowired
	private IFstuPatentBiz patentBiz;
	@Autowired
	private IUserBiz userBiz;
	/**
	 * 评分层级维护及学分配置查询
	 */
	@RequestMapping(value="/scoreConfig")
	public String scoreConfig(Integer currentPage,HttpServletRequest request,FstuScoreConfig cfg,Model model){
		if(StringUtil.isNotBlank(cfg.getProjTypeId())){
			String val = cfg.getProjTypeId().split(",")[1];
			cfg.setProjTypeId(val);
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<FstuScoreConfig> configList = scoreBiz.searchScoreCfgList(cfg);
		model.addAttribute("configList",configList);
		return "fstu/scom/scoreConfig";
	}

	/**
	 * 学分配置新增编辑界面
     */
	@RequestMapping("/addConfig")
	public String addConfig(String recordFlow, Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			FstuScoreConfig scoreCfg = scoreBiz.searchScoreCfgByFlow(recordFlow);
			model.addAttribute("scoreCfg",scoreCfg);
		}
		return "fstu/scom/addConfig";
	}

	/**
	 * 新增学分配置操作
	 */
	@RequestMapping("/saveConfig")
	@ResponseBody
	public String saveConfig(FstuScoreConfig scoreCfg){
		int num = scoreBiz.saveScoreCfg(scoreCfg);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "此层级关系已维护过！";
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping("/delConfig")
	@ResponseBody
	public String delConfig(String recordFlow){
		int num = scoreBiz.delConfigByFlow(recordFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}
	/**
	 * 学分配置查看
	 */
	@RequestMapping("/queryConfig")
	public String queryConfig(String recordFlow, Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			FstuScoreConfig scoreCfg = scoreBiz.searchScoreCfgByFlow(recordFlow);
			model.addAttribute("scoreCfg",scoreCfg);
		}
		return "fstu/scom/queryConfig";
	}

	/**
	 * 我的学分查询
	 */
	@RequestMapping(value="/myScore/{roleFlag}")
	public String myScore(@PathVariable String roleFlag,Integer currentPage, HttpServletRequest request, FstuScoreMain score, Model model){
		model.addAttribute("roleFlag",roleFlag);
		if(StringUtil.isBlank(score.getSessionNumber())){
			score.setSessionNumber(DateUtil.getYear());
			model.addAttribute("currentYear",score.getSessionNumber());
		}
		if("all".equals(score.getSessionNumber())){
			score.setSessionNumber("");
		}
		if(StringUtil.isNotBlank(score.getFirstProjTypeId())){
			String val = score.getFirstProjTypeId().split(",")[1];
			score.setFirstProjTypeId(val);
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		if(StringUtil.isNotBlank(roleFlag)){
			score.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		}
		List<FstuScoreMain> scoreList = scoreBiz.searchScoreList(score,null);
		model.addAttribute("scoreList",scoreList);
		double sum= 0;
		if(scoreList!=null&&scoreList.size()>0){
			for(FstuScoreMain scoreMain:scoreList){
				String score1 = scoreMain.getMyScore();
				sum += Double.parseDouble(score1);
			}
		}
		model.addAttribute("sum",sum);
		return "fstu/scom/myScore";
	}
	/**
	 * 我的学分新增编辑界面
	 */
	@RequestMapping("/addScore")
	public String addScore(String recordFlow, Model model){
		SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("user", user);
		if(StringUtil.isNotBlank(recordFlow)){
			FstuScoreMain score = scoreBiz.searchScoreByFlow(recordFlow);
			model.addAttribute("score",score);
			List<PubFile> pubFileList = scoreBiz.queryFileList(recordFlow);
			model.addAttribute("pubFileList",pubFileList);
		}
		return "fstu/scom/addScore";
	}

	/**
	 * 新增我的学分前读取学分配置信息
	 */
	@RequestMapping("/qryCfg")
	@ResponseBody
	public Object qryCfg(FstuScoreMain score){
		Map<String,Object> resultMap=new HashMap<>();
		FstuScoreConfig cfg = new FstuScoreConfig();
		cfg.setScoreTypeId(score.getFirstScoreTypeId());
		cfg.setProjTypeId(score.getFirstProjTypeId());
		cfg.setScoreItemId(score.getFirstScoreItemId());
		cfg.setExecutionId(score.getFirstExecutionId());
		List<FstuScoreConfig> cfgList = scoreBiz.searchScoreCfgList(cfg);
		if (null != cfgList && cfgList.size() > 0) {
			FstuScoreConfig config = cfgList.get(0);
			resultMap.put("config",config);

			SysUser currUser = GlobalContext.getCurrentUser();
			String userFlow = currUser.getUserFlow();
			//查询之前申请过学分的论文
			FstuScoreMain searchScore = new FstuScoreMain();
			searchScore.setUserFlow(userFlow);
			searchScore.setRecordStatus("Y");
			List<FstuScoreMain> scoreMainList = scoreBiz.searchScoreList(searchScore,null);
			List<String> names = new ArrayList();
			if(scoreMainList!=null&&scoreMainList.size()>0){
				for(FstuScoreMain scoreMain:scoreMainList){
					String name = scoreMain.getScoreName();
					names.add(name);
				}
			}
			if(StringUtil.isNotBlank(score.getScoreName())){
				names.remove(score.getScoreName());
			}
			if("book".equals(config.getReltiveType())){
				FstuBook book = new FstuBook();
				book.setApplyUserFlow(userFlow);
				book.setOperStatusId(ProStatusEnum.Passed.getId());
				List<FstuBook> achBookList = bookBiz.search(book, null);
				List<Map<String,String>> list = new ArrayList<>();
				for(FstuBook fstuBook:achBookList){
					String name = fstuBook.getBookName();
					if(!names.contains(name)){
						String scoreValue = fstuBook.getScoreValue();
						Map<String,String> map = new HashMap<>();
						map.put("name",name);
						map.put("scoreValue",scoreValue);
						list.add(map);
					}
				}
				resultMap.put("list",list);
			}else if("thesis".equals(config.getReltiveType())){
				FstuThesis thesis = new FstuThesis();
				thesis.setApplyUserFlow(userFlow);
				thesis.setOperStatusId(ProStatusEnum.Passed.getId());
				List<FstuThesis> thesisList =thesisBiz.search(thesis, null);
				List<Map<String,String>> list = new ArrayList<>();
				for(FstuThesis fstuThesis:thesisList){
					String name = fstuThesis.getThesisName();
					if(!names.contains(name)) {
						String scoreValue = fstuThesis.getScoreValue();
						Map<String, String> map = new HashMap<>();
						map.put("name", name);
						map.put("scoreValue", scoreValue);
						list.add(map);
					}
				}
				resultMap.put("list",list);
			}else if("award".equals(config.getReltiveType())){
				FstuSat sat = new FstuSat();
				sat.setApplyUserFlow(userFlow);
				sat.setOperStatusId(ProStatusEnum.Passed.getId());
				List<FstuSat> satList=satBiz.search(sat, null);
				List<Map<String,String>> list = new ArrayList<>();
				for(FstuSat fstuSat:satList){
					String name = fstuSat.getSatName();
					if(!names.contains(name)) {
						String scoreValue = fstuSat.getScoreValue();
						Map<String, String> map = new HashMap<>();
						map.put("name", name);
						map.put("scoreValue", scoreValue);
						list.add(map);
					}
				}
				resultMap.put("list",list);
			}else if("patent".equals(config.getReltiveType())){
				FstuPatent patent = new FstuPatent();
				patent.setApplyUserFlow(userFlow);
				patent.setOperStatusId(ProStatusEnum.Passed.getId());
				List<FstuPatent> patentList=patentBiz.search(patent, null);
				List<Map<String,String>> list = new ArrayList<>();
				for(FstuPatent patent1:patentList){
					String name = patent1.getPatentName();
					if(!names.contains(name)) {
						String scoreValue = patent1.getScoreValue();
						Map<String, String> map = new HashMap<>();
						map.put("name", name);
						map.put("scoreValue", scoreValue);
						list.add(map);
					}
				}
				resultMap.put("list",list);
			}
			return resultMap;
		}
		return null;
	}

	/**
	 * 新增我的学分操作
	 */
	@RequestMapping("/saveScore")
	@ResponseBody
	public String saveScore(ScoreManageForm form){
		if(null != form.getScoreApplyUrlList() && form.getScoreApplyUrlList().size() > 0){
			Iterator<String> it = form.getScoreApplyUrlList().iterator();
			while(it.hasNext()) {
				if(StringUtil.isBlank(it.next())) {
					it.remove();//去除页面初始上传附件模板为空的情况
				}
			}
		}
		int num = scoreBiz.saveScore(form.getScore(),form.getScoreApplyUrlList());
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 新增我的学分
	 * @return
     */
	@RequestMapping("/saveScore2")
	@ResponseBody
	public String saveScore2(String jsondata,HttpServletRequest request){
		FstuScoreAndFileForm fstuScoreAndFileForm = JSON.parseObject(jsondata, FstuScoreAndFileForm.class);
		FstuScoreMain scoreMain = fstuScoreAndFileForm.getScore();
		String success = GlobalConstant.OPRE_SUCCESSED;
		String fail = GlobalConstant.OPRE_FAIL;
		//保存附件
		List<PubFile> pubFileList = fstuScoreAndFileForm.getFileList();
		String resultPath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		for (int i = 0; i < fileList.size(); i++) {
			PubFile pubFile = pubFileList.get(i);
			if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
				//保存附件
				resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuScom", pubFile.getFileFlow());
				pubFile.setFileName(fileList.get(i).getOriginalFilename());
				pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
				pubFile.setFileUpType("1");
			}
//			pubFileList.add(pubFile);
		}
		int result=scoreBiz.saveScoreAndFile(scoreMain,pubFileList);
		if(result==GlobalConstant.ZERO_LINE){
			return fail;
		}else{
			return success;
		}
	}
	/**
	 * 删除我的学分操作
	 */
	@RequestMapping("/delScore")
	@ResponseBody
	public String delScore(String recordFlow){
		int num = scoreBiz.delScoreByFlow(recordFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 我的学分提交及管理员取消审核操作
     */
	@RequestMapping("/submitScore")
	@ResponseBody
	public String submitScore(String recordFlow,String roleFlag){
		int num = scoreBiz.submitScoreByFlow(recordFlow,roleFlag);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 我的学分查看
	 */
	@RequestMapping("/queryScore")
	public String queryScore(String recordFlow, Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			FstuScoreMain score = scoreBiz.searchScoreByFlow(recordFlow);
			model.addAttribute("score",score);
			List<PubFile> pubFileList = scoreBiz.queryFileList(recordFlow);
			model.addAttribute("pubFileList",pubFileList);
		}
		return "fstu/scom/queryScore";
	}

	/**
	 * 我的学分查询
	 */
	@RequestMapping(value="/scoreManage/{roleFlag}")
	public String scoreManage(@PathVariable String roleFlag,Integer currentPage, HttpServletRequest request, FstuScoreMain score,String flag, Model model){
		model.addAttribute("roleFlag",roleFlag);
		if(StringUtil.isNotBlank(score.getFirstProjTypeId())){
			String val = score.getFirstProjTypeId().split(",")[1];
			score.setFirstProjTypeId(val);
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		//初次加载我的学分管理的所有记录
		if(StringUtil.isNotBlank(flag)){
			score.setAuditStatusId(flag);
		}
		List<FstuScoreMain> scoreList = scoreBiz.searchScoreList(score,roleFlag);
		model.addAttribute("scoreList",scoreList);
		return "fstu/scom/scoreManage";
	}

	/**
	 * 我的学分审核操作
	 */
	@RequestMapping("/auditScore")
	@ResponseBody
	public String auditScore(String jsonData){
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
		List<String> recordLst = (List<String>)mp.get("recordLst");
		String auditStatusId = (String)mp.get("auditStatusId");
		String roleFlag = (String)mp.get("roleFlag");
		int num = 0;
		if(null != recordLst && recordLst.size() > 0){
			for (int i = 0; i < recordLst.size(); i++) {
				num += scoreBiz.auditScore(recordLst.get(i),auditStatusId,roleFlag);
			}
		}
		if (num > 0) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 我的学分审核弹框
	 */
	@RequestMapping("/auditScoreInfo")
	public String auditScoreInfo(String recordFlow, Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			FstuScoreMain score = scoreBiz.searchScoreByFlow(recordFlow);
			model.addAttribute("score",score);
		}
		return "fstu/scom/auditScoreInfo";
	}

	/**
	 * 上传文件
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadFile"})
	@ResponseBody
	public String uploadFile(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			return academicBiz.saveFileToDirs(file, "fstuScom");
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 学分导入
	 * @param
	 * @return
	 */
	@RequestMapping(value="/importScore")
	public String importScores(){
		return "fstu/scom/importScore";
	}

	/**
	 * 学分导入
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importScoresFromExcel")
	@ResponseBody
	public String importScoresFromExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = scoreBiz.importScoreFromExcel(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/******************************余杭定制独立项目**********************************/
	//学分登记
	@RequestMapping(value="/register")
	public String register(String flag,FstuUserScore score, Integer currentPage, HttpServletRequest request, Model model){
		model.addAttribute("flag",flag);
		PageHelper.startPage(currentPage,getPageSize(request));
		if(flag.equals("local")){
			score.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<FstuUserScore> scoreList = scoreBiz.searchScoreList(score);
		model.addAttribute("scoreList",scoreList);
		return "fstu/scom/register";
	}
	@RequestMapping("/editScore")
	public String editScore(String userFlow,Model model){
		if(StringUtil.isBlank(userFlow)){
			SysUser currUser = GlobalContext.getCurrentUser();
			//医院下未登记学分的学员
			List<SysUser> sysUserList = scoreBiz.searchUserNotInScore(currUser.getOrgFlow(),"score");
			model.addAttribute("sysUserList", sysUserList);
		}else{
			FstuUserScore userScore = scoreBiz.searchUserScoreByFlow(userFlow);
			model.addAttribute("userScore",userScore);
		}
		return "fstu/scom/editScore";
	}
	@RequestMapping("/saveUserScore")
	public @ResponseBody String saveUserScore(@RequestBody List<FstuUserScore> user){
		if (user.size() > 0) {
			SysUser currUser = GlobalContext.getCurrentUser();
			for (FstuUserScore score : user) {
				score.setOrgFlow(currUser.getOrgFlow());
				score.setOrgName(currUser.getOrgName());
				scoreBiz.saveUserScore(score);
			}
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	//进修申请
	@RequestMapping(value="/studyApply")
	public String studyApply(String flag, FstuUserStudy study, Integer currentPage, HttpServletRequest request, Model model){
		model.addAttribute("flag",flag);
		SysUser sysUser = GlobalContext.getCurrentUser();
		if(flag.equals("local")){
			study.setOrgFlow(sysUser.getOrgFlow());
			SysDept sysDept = new SysDept();
			sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(sysUser.getOrgFlow());
			List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
			model.addAttribute("sysDeptList",sysDeptList);
		}else{//卫计局（省厅）
			study.setAuditStatusId("stFlag");//用来过滤未提交数据的标识
			List<SysOrg> sysOrgList = orgBiz.searchSysOrg();
			model.addAttribute("sysOrgList",sysOrgList);
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<FstuUserStudy> studyList = scoreBiz.searchStudyList(study);
		model.addAttribute("studyList",studyList);
		return "fstu/scom/studyReq";
	}
	@RequestMapping("/editStudy")
	public String editStudy(Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		//医院下未申请进修的学员
		List<SysUser> sysUserList = scoreBiz.searchUserNotInScore(currUser.getOrgFlow(),"study");
		model.addAttribute("sysUserList", sysUserList);
		return "fstu/scom/editStudy";
	}
	@RequestMapping("/saveUserStudy")
	public @ResponseBody String saveUserStudy(@RequestBody List<FstuUserStudy> user,String submitFlag){
		if (user.size() > 0) {
			SysUser currUser = GlobalContext.getCurrentUser();
			for (FstuUserStudy study : user) {
				study.setOrgFlow(currUser.getOrgFlow());
				study.setOrgName(currUser.getOrgName());
				if("submit".equals(submitFlag)){//提交操作
					study.setAuditStatusId("Passing");
					study.setAuditStatusName("待审核");
				}
				scoreBiz.saveUserStudy(study);
			}
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	@RequestMapping("/auditStudy")
	public String auditStudy(String userFlow,Model model){
		if(StringUtil.isNotBlank(userFlow)){
			FstuUserStudy userStudy = scoreBiz.searchUserStudyByFlow(userFlow);
			model.addAttribute("userStudy",userStudy);
		}
		return "fstu/scom/auditStudy";
	}
	@RequestMapping("/auditStudyOpt")
	@ResponseBody
	public String auditStudyOpt(FstuUserStudy study){
		if(StringUtil.isNotBlank(study.getUserFlow())){
			SysUser user = GlobalContext.getCurrentUser();
			study.setAuditFlow(user.getUserFlow());
			study.setAuditTime(DateUtil.getCurrentTime());
			scoreBiz.saveUserStudy(study);
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	/******************************余杭定制独立项目**********************************/
}