package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AchTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.ach.AchSatExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmAchSatAuthorList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
/**
 *
 * @author tiger
 *
 */
@Controller
@RequestMapping("/srm/ach/sat")
public class AchSatController extends GeneralController{


	@Autowired
	private ISatBiz satBiz;
	@Autowired
	private IAchSatAuthorBiz satAuthorBiz;
	@Autowired
	private ISrmAchFileBiz srmAchFileBiz;
	@Autowired
	private ISrmAchProcessBiz srmAchProcessBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IAchScoreBiz achScoreBiz;

	/**
	 * 保存论文及作者
	 * @param aList
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value={"/save/{roleScope}"})
	@ResponseBody
	public String save(@PathVariable String roleScope,String jsondata, MultipartFile file, HttpServletRequest request) throws IOException{

		SrmAchSatAuthorList aList = JSON.parseObject(jsondata, SrmAchSatAuthorList.class);

		SrmAchSat sat = aList.getSat();
		List<SrmAchSatAuthor> authorList = aList.getAuthorList();
		//-----枚举：根据相关的ID获得name-----
		sat.setAchTypeName(DictTypeEnum.AchType.getDictNameById(sat.getAchTypeId()));
		sat.setPrizedGradeName(DictTypeEnum.PrizedGrade.getDictNameById(sat.getPrizedGradeId()));
		sat.setPrizedLevelName(DictTypeEnum.PrizedLevel.getDictNameById(sat.getPrizedLevelId()));
		sat.setCategoryName(DictTypeEnum.SubjectType.getDictNameById(sat.getCategoryId()));
		sat.setProjSourceName(DictTypeEnum.ProjTypeSource.getDictNameById(sat.getProjSourceId()));
		//所属单位
		sat.setOrgBelongName(DictTypeEnum.OrgBelong.getDictNameById(sat.getOrgBelongId()));
		sat.setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(sat.getBranchId()));
        if(GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            sat.setOperStatusId(AchStatusEnum.Apply.getId());
            sat.setOperStatusName(AchStatusEnum.Apply.getName());
        }
		//获取申请单位信息
		SysUser currUser = GlobalContext.getCurrentUser();
   	     /*sat.setApplyOrgFlow(currUser.getOrgFlow());
   	     sat.setApplyOrgName(currUser.getOrgName());
   	     sat.setApplyUserFlow(currUser.getUserFlow());
   	     sat.setApplyUserName(currUser.getUserName());*/

		//根据科技作者相关的ID枚举获得相对应的Name（性别、学历、职称）
		for(int i = 0; i<authorList.size();i++){
			// authorList.get(i).setTypeName(DictTypeEnum.AuthorType.getDictNameById(authorList.get(i).getTypeId()));
			authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
			authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
			authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
			authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
			authorList.get(i).setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(authorList.get(i).getBranchId()));
			if(StringUtil.isNotBlank(authorList.get(i).getScoreFlow())){
				SrmAchScore score=new SrmAchScore();
				score.setScoreFlow(authorList.get(i).getScoreFlow());
				List<SrmAchScore> list=achScoreBiz.searchScoreSetList(score);
				if(list!=null&&list.size()>0&&list.get(0)!=null){
					authorList.get(i).setScoreFlow(list.get(0).getScoreFlow());
					authorList.get(i).setScoreName(list.get(0).getScoreName());
					authorList.get(i).setAchScore(list.get(0).getScorePersonalValue().add(new BigDecimal(0)));
					authorList.get(i).setAchScoreDept(list.get(0).getScoreDeptValue().add(new BigDecimal(0)));
				}
			}
		}

		SrmAchFile srmAchFile = null;
		if(file != null && StringUtil.isNotBlank(file.getOriginalFilename())){
			//封装附件对象
			srmAchFile = new SrmAchFile();
			byte[] bytes = new byte[(int)file.getSize()];
			file.getInputStream().read(bytes);

			srmAchFile.setFileFlow(aList.getSrmAchFile().getFileFlow());
			srmAchFile.setFileContent(bytes);
			srmAchFile.setFileName(file.getOriginalFilename());

			srmAchFile.setTypeId(AchTypeEnum.Sat.getId());
			srmAchFile.setTypeName(AchTypeEnum.Sat.getName());

			String[] nameArray = file.getOriginalFilename().split("\\.");
			srmAchFile.setFileSuffix(nameArray[nameArray.length-1]);
			srmAchFile.setFileType(nameArray[nameArray.length-1]);
		}
		//封装成果过程对象
		SrmAchProcess srmAchProcess=new SrmAchProcess();

		srmAchProcess.setTypeId(AchTypeEnum.Sat.getId());
		srmAchProcess.setTypeName(AchTypeEnum.Sat.getName());

		srmAchProcess.setOperateUserFlow(currUser.getUserFlow());
		srmAchProcess.setOperateUserName(currUser.getUserName());
        if(GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            srmAchProcess.setOperStatusId(AchStatusEnum.Apply.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.Apply.getName());
        }
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleScope)){
            srmAchProcess.setOperStatusId(AchStatusEnum.LocalEdit.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.LocalEdit.getName());
        }
		int reslut = satBiz.save(sat, authorList, srmAchFile, srmAchProcess);
		if(reslut == GlobalConstant.ONE_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}


	@RequestMapping(value="/submitAudit",method={RequestMethod.GET})
	@ResponseBody
	public String submitAudit(@RequestParam(value="satFlow", required = true)String satFlow, Model model){
		if(StringUtil.isNotBlank(satFlow)){
			SrmAchSat sat = satBiz.readSat(satFlow);
			sat.setOperStatusId(AchStatusEnum.Submit.getId());
			sat.setOperStatusName(AchStatusEnum.Submit.getName());

			SrmAchProcess process=srmAchProcessBiz.searchAchProcess(satFlow, AchStatusEnum.Apply.getId()).get(0);
			process.setProcessFlow(PkUtil.getUUID());
			process.setOperStatusId(AchStatusEnum.Submit.getId());
			process.setOperStatusName(AchStatusEnum.Submit.getName());
			GeneralMethod.setRecordInfo(process, true);
			process.setOperateTime(process.getCreateTime());

			SysUser currUser = GlobalContext.getCurrentUser();
			process.setOperateUserFlow(currUser.getUserFlow());
			process.setOperateUserName(currUser.getUserName());

			int result = satBiz.updateSatStatus(sat, process);
			if(result == GlobalConstant.ONE_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}


	//删除科技作者
	@RequestMapping(value="/deleteAuthor",method={RequestMethod.GET})
	@ResponseBody
	public String deleteAuthor(String authorFlow){
		if(StringUtil.isNotBlank(authorFlow)){
			SrmAchSatAuthor author = new SrmAchSatAuthor();
			author.setAuthorFlow(authorFlow);
			author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = satAuthorBiz.editSatAuthor(author);
			if(result == GlobalConstant.ONE_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}


	/**
	 * 加载科技列表
	 * @param achSat
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list/{scope}",method={RequestMethod.GET,RequestMethod.POST})
	public String list(@PathVariable String scope,SrmAchSat sat,Integer currentPage,HttpServletRequest request, Model model){

		SysUser currUser = GlobalContext.getCurrentUser();
		sat.setApplyUserFlow(currUser.getUserFlow());
		if("wxdermyy".equals(scope)&&GlobalConstant.FLAG_Y.equals(sat.getOperStatusId())){
			sat.setOperStatusId("");
		}
		PageHelper.startPage(currentPage, getPageSize(request));

		List<SrmAchSat> satList=satBiz.search(sat, null);

		Map<String,List<SrmAchSatAuthor>> allAuthorMap = new LinkedHashMap<String, List<SrmAchSatAuthor>>();
		List<SrmAchSatAuthor> allAuthorList  = satAuthorBiz.searchAuthorList(new SrmAchSatAuthor());
		for(SrmAchSatAuthor a : allAuthorList){
			List<SrmAchSatAuthor> list =  allAuthorMap.get(a.getSatFlow());
			if(list == null){
				list = new ArrayList<SrmAchSatAuthor>();
			}
			list.add(a);
			allAuthorMap.put(a.getSatFlow(), list);
		}
		model.addAttribute("satList",satList);
		model.addAttribute("allAuthorMap", allAuthorMap);
		if("xzzxyy".equals(scope)){
			return "srm/ach_xzzxyy/sat/list";
		}
		if("wxdermyy".equals(scope)){
			return "srm/ach_wxdermyy/sat/list";
		}
		if("common".equals(scope)){
			return "srm/ach_common/sat/list";
		}
		return "srm/ach/sat/list";
	}
	/**
	 * 奖励批准号是否已存在
	 * @return
	 */
	@RequestMapping("/satIsExist")
	@ResponseBody
	public SrmAchSat satIsExist(String approvalCode){
		SrmAchSat sat = null;
		if(StringUtil.isNotBlank(approvalCode)) {
			approvalCode = approvalCode.trim();
			sat = satBiz.satIsExist(approvalCode);
		}
		return sat;
	}
	/**
	 * 编辑科技信息
	 * @param satFlow
	 * @param author
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{scope}",method=RequestMethod.GET)
	public String edit(@PathVariable String scope,String satFlow, Model model){
		model.addAttribute("scope",scope);
		SrmAchFile file = null;
		List<SrmAchFile> fileList = null;
		if(StringUtil.isNotBlank(satFlow)){
			SrmAchSat sat=satBiz.readSat(satFlow);
			model.addAttribute("sat", sat);
			//查询科技作者信息
			SrmAchSatAuthor author = new SrmAchSatAuthor();
			author.setSatFlow(satFlow);
			List<SrmAchSatAuthor> satAuthorList=satAuthorBiz.searchAuthorList(author);
			model.addAttribute("satAuthorList",satAuthorList);
			fileList = srmAchFileBiz.searchSrmAchFile(satFlow);
			if(null != fileList && !fileList.isEmpty()){
				file = fileList.get(0);
			}
		}
        model.addAttribute("roleScope",GlobalConstant.USER_LIST_PERSONAL);
		model.addAttribute("file", file);
		if("xzzxyy".equals(scope)){
			return "srm/ach_xzzxyy/sat/edit";
		}
		if("wxdermyy".equals(scope)){
			return "srm/ach_wxdermyy/sat/edit";
		}
		if("common".equals(scope)){
			return "srm/ach_common/sat/edit";
		}
		return "srm/ach/sat/edit";
	}


	@RequestMapping(value="/auditList/{scope}",method={RequestMethod.POST,RequestMethod.GET})
	public String auditList(@PathVariable String scope, Integer currentPage, SrmAchSat sat, SrmAchSatAuthor author, SysOrg org, Model model, HttpServletRequest request){
		model.addAttribute("scope",scope);
		SysUser currUser = GlobalContext.getCurrentUser();
		List<SrmAchSat> searchList=null;
		//查询当前机构下属所有级别子机构包含自身
		List<SysOrg> currOrgChildList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
		//根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
		Map<String,List<SysOrg>> resultMap=orgBiz.searchChargeAndApply(org,scope);
		//获取当前机构下属一级的机构
		List<SysOrg> firstGradeOrgList=(List<SysOrg>) resultMap.get("firstGradeOrgList");
		model.addAttribute("firstGradeOrgList", firstGradeOrgList);
        List<String> satFlows=null;
        if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
            SrmAchSatAuthor b=new SrmAchSatAuthor();
            b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(author.getAuthorName())){
                b.setAuthorName(author.getAuthorName());
            }
            if(StringUtil.isNotBlank(author.getWorkCode())){
                b.setWorkCode(author.getWorkCode());
            }
            List<SrmAchSatAuthor> autorsList=satAuthorBiz.searchAuthorList(b);
            if(autorsList!=null&&autorsList.size()>0){
                satFlows=new ArrayList<>();
                for(SrmAchSatAuthor sa:autorsList){
                    if(!satFlows.contains(sa.getSatFlow()))
                    {
                        satFlows.add(sa.getSatFlow());
                    }
                }

            }
            if(null == satFlows || satFlows.size()==0){
                satFlows=new ArrayList<>();
                satFlows.add("");
            }
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                sat.setDeptFlow(currUser.getDeptFlow());
            }else{
				if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
					return "srm/ach_wxdermyy/sat/auditListlocal";
				}
				return "srm/ach_common/sat/auditListlocal";
            }
        }
		if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
			//设置查询条件：科技处审核通过的成果
			sat.setOperStatusId(AchStatusEnum.FirstAudit.getId());
			//如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
			List<SysOrg> secondGradeOrgList=(List<SysOrg>) resultMap.get("secondGradeOrgList");
			if(null!=secondGradeOrgList && !secondGradeOrgList.isEmpty()){
				model.addAttribute("secondGradeOrgList",secondGradeOrgList);
			}
			//如果查询条件orgFlow为空且chargeOrgFlow不为空，则查询该主管部门下所有子机构的所有成果
			if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())){
				if(null == secondGradeOrgList || secondGradeOrgList.isEmpty()){
					SysOrg sysOrg=orgBiz.readSysOrg(org.getChargeOrgFlow());
					List<SysOrg> selfOrgList=new ArrayList<SysOrg>();
					selfOrgList.add(sysOrg);
                    PageHelper.startPage(currentPage, getPageSize(request));
					searchList = satBiz.search(sat,selfOrgList,satFlows);
				}else{
                    PageHelper.startPage(currentPage, getPageSize(request));
					searchList = satBiz.search(sat,secondGradeOrgList,satFlows);
				}
			}

		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
			sat.setOperStatusId(AchStatusEnum.FirstAudit.getId());
		}else{
            org.setOrgFlow(currUser.getOrgFlow());//当前登陆者是医院 只查询本医院下的成果
			//审核状态： 审核通过、待审核、全部
			if(StringUtil.isBlank(sat.getOperStatusId())){
				sat.setOperStatusId(AchStatusEnum.Submit.getId());
			}
			if(GlobalConstant.FLAG_Y.equals(sat.getOperStatusId())){
				sat.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
			}
		}
		//如果查询条件orgFlow不为空，则查询该org下所有成果
		if(StringUtil.isNotBlank(org.getOrgFlow())){
			sat.setApplyOrgFlow(org.getOrgFlow());
            PageHelper.startPage(currentPage, getPageSize(request));
			searchList = satBiz.search(sat, null,satFlows);
		}
		//如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
		if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())){
            PageHelper.startPage(currentPage, getPageSize(request));
			searchList = satBiz.search(sat, currOrgChildList,satFlows);
		}

		Map<String, List<SrmAchSatAuthor>> allAuthorMap = new HashMap<String, List<SrmAchSatAuthor>>();
		List<SrmAchSatAuthor> satAuthorList = satAuthorBiz.searchAuthorList(new SrmAchSatAuthor());
		if(satAuthorList != null && !satAuthorList.isEmpty()){
			for(SrmAchSatAuthor a : satAuthorList){
				List<SrmAchSatAuthor> authorList = allAuthorMap.get(a.getSatFlow());
				if(authorList == null){
					authorList = new ArrayList<SrmAchSatAuthor>();
				}
				authorList.add(a);
				allAuthorMap.put(a.getSatFlow(), authorList);
			}
		}


		model.addAttribute("satList", searchList);
		model.addAttribute("allAuthorMap", allAuthorMap);
		if("xzzxyy".equals(scope)){
			return "srm/ach_xzzxyy/sat/auditListlocal";
		}
		if("wxdermyy".equals(scope)){
			return "srm/ach_wxdermyy/sat/auditListlocal";
		}
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
			if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
				return "srm/ach_wxdermyy/sat/auditListlocal";
			}
			return "srm/ach_common/sat/auditListlocal";
        }
		if("common".equals(scope)){
			return "srm/ach_common/sat/auditListlocal";
		}
		return "srm/ach/sat/auditList"+scope;
	}


	/**
	 * 跳转到审核界面
	 * @param satFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/audit")
	public String audit(@RequestParam(value="satFlow", required=true)String satFlow, Model model){
		SrmAchScore score=new SrmAchScore();
		score.setScoreStatusId(GlobalConstant.RECORD_STATUS_Y);
		List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(score);
		model.addAttribute("srmAchScoreList",srmAchScoreList);
		//根据成果流水号查询相关信息，用于加载审核页面
		SrmAchFile file = null;
		List<SrmAchFile> fileList=null;
		if(StringUtil.isNotBlank(satFlow)){
			//查询成果本身
			SrmAchSat sat=satBiz.readSat(satFlow);
			model.addAttribute("sat", sat);
			//查询成果作者
			SrmAchSatAuthor author = new SrmAchSatAuthor();
			author.setSatFlow(satFlow);
			List<SrmAchSatAuthor> satAuthorList=satAuthorBiz.searchAuthorList(author);
			model.addAttribute("satAuthorList", satAuthorList);
			//查询成果附件
			fileList=srmAchFileBiz.searchSrmAchFile(satFlow);
			if(fileList!=null && !fileList.isEmpty()){
				file=fileList.get(0);
				model.addAttribute("file", file);
			}
		}
		SrmAchSat sat=satBiz.readSat(satFlow);
		model.addAttribute("sat", sat);
        model.addAttribute("roleScope",GlobalConstant.USER_LIST_LOCAL);
		model.addAttribute("company","Y");
		//徐州中心医院
		if("Y".equals(InitConfig.getSysCfg("srm_local_type"))){
			return "srm/ach_xzzxyy/sat/audit";
		}
		//无锡第二人民医院
		if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
			return "srm/ach_wxdermyy/sat/audit";
		}
		if("common".equals(InitConfig.getSysCfg("srm_local_type"))){
			return "srm/ach_common/sat/audit";
		}
		return "srm/ach/sat/audit";
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
	public String saveAudit(@RequestParam(value="agreeFlag", required=true)String agreeFlag,String auditContent,
							@RequestParam(value="satFlow", required=true)String satFlow,Model model){
		SrmAchSat sat=satBiz.readSat(satFlow);
		SrmAchProcess process=srmAchProcessBiz.searchAchProcess(satFlow, AchStatusEnum.Apply.getId()).get(0);

		if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
			sat.setOperStatusId(AchStatusEnum.FirstAudit.getId());
			sat.setOperStatusName(AchStatusEnum.FirstAudit.getName());
			process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
			process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
		} else{
			sat.setOperStatusId(AchStatusEnum.RollBack.getId());
			sat.setOperStatusName(AchStatusEnum.RollBack.getName());
			process.setOperStatusId(AchStatusEnum.RollBack.getId());
			process.setOperStatusName(AchStatusEnum.RollBack.getName());
		}
		process.setProcessFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(process, true);
		process.setOperateTime(process.getCreateTime());
		SysUser currUser = GlobalContext.getCurrentUser();
		process.setOperateUserFlow(currUser.getUserFlow());
		process.setOperateUserName(currUser.getUserName());
		process.setContent(auditContent);
		satBiz.updateSatStatus(sat, process);
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 删除科技信息
	 * @param achSat
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(String satFlow){
		if(StringUtil.isNotBlank(satFlow)){
			SrmAchSat sat = new SrmAchSat();
			sat.setSatFlow(satFlow);
			sat.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

			SrmAchSatAuthor temp = new SrmAchSatAuthor();
			temp.setSatFlow(satFlow);
			List<SrmAchSatAuthor> authorList = satAuthorBiz.searchAuthorList(temp);
			for(SrmAchSatAuthor author : authorList){
				author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			}

			SrmAchFile file = null;
			List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(satFlow);
			if(fileList != null && !fileList.isEmpty()){
				file = fileList.get(0);
				file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			}

			int result = satBiz.edit(sat, authorList, file);
			if(result == GlobalConstant.ONE_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 导出科技信息
	 * @param scope
	 * @param sat
	 * @param author
	 * @param org
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportSatExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
	public void exportSatExcel(@PathVariable String scope,SrmAchSat sat, SrmAchSatAuthor author, SysOrg org, HttpServletResponse response) throws Exception {
        if(scope.equals("wxdermyy") || "common".equals(scope)){
            scope = GlobalConstant.PROJ_STATUS_SCOPE_LOCAL;
        }
        String[] titles;//导出列表头信息
		SysUser currUser = GlobalContext.getCurrentUser();
		List<AchSatExportForm> searchList=new ArrayList<AchSatExportForm>();
		List<SrmAchSat> achSatList = null;
		//查询当前机构下属所有级别子机构包含自身
		List<SysOrg> currOrgChildList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
		//根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
		Map<String,List<SysOrg>> resultMap=orgBiz.searchChargeAndApply(org,scope);

		List<String> satFlows=null;
		if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
			SrmAchSatAuthor b=new SrmAchSatAuthor();
			b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			if(StringUtil.isNotBlank(author.getAuthorName())){
				b.setAuthorName(author.getAuthorName());
			}
			if(StringUtil.isNotBlank(author.getWorkCode())){
				b.setWorkCode(author.getWorkCode());
			}
			List<SrmAchSatAuthor> autorsList=satAuthorBiz.searchAuthorList(b);
			if(autorsList!=null&&autorsList.size()>0){
				satFlows=new ArrayList<>();
				for(SrmAchSatAuthor sa:autorsList){
					if(!satFlows.contains(sa.getSatFlow()))
					{
						satFlows.add(sa.getSatFlow());
					}
				}

			}
			if(null == satFlows || satFlows.size()==0){
				satFlows=new ArrayList<>();
				satFlows.add("");
			}
		}
		if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
			//设置查询条件：科技处审核通过的成果
			sat.setOperStatusId(AchStatusEnum.FirstAudit.getId());
			//如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
			List<SysOrg> secondGradeOrgList=(List<SysOrg>) resultMap.get("secondGradeOrgList");
			titles = new String[]{
					"srmAchSat.satName:科技名称",
					"authorName:科技作者",
					"srmAchSat.prizedGradeName:获奖级别",
					"srmAchSat.prizedLevelName:获奖等级",
					"srmAchSat.prizedDate:获奖日期",
					"srmAchSat.applyOrgName:申报单位"
			};
			//如果查询条件orgFlow为空且chargeOrgFlow不为空，则查询该主管部门下所有子机构的所有成果
			if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())){
				if(null == secondGradeOrgList || secondGradeOrgList.isEmpty()){
					SysOrg sysOrg=orgBiz.readSysOrg(org.getChargeOrgFlow());
					List<SysOrg> selfOrgList=new ArrayList<SysOrg>();
					selfOrgList.add(sysOrg);
					achSatList = satBiz.search(sat,selfOrgList,satFlows);
				}else{
					achSatList = satBiz.search(sat,secondGradeOrgList,satFlows);
				}
			}

		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
			titles = new String[]{
					"srmAchSat.satName:科技名称",
					"authorName:科技作者",
					"srmAchSat.prizedGradeName:获奖级别",
					"srmAchSat.prizedLevelName:获奖等级",
					"srmAchSat.prizedDate:获奖日期"
			};
			sat.setOperStatusId(AchStatusEnum.FirstAudit.getId());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)){
			titles = new String[]{
					"srmAchSat.prizedDate:年度",
					"srmAchSat.prizedGradeName:奖项类别",
					"srmAchSat.deptName:科室",
					"authorName:完成者",
                    "srmAchSat.satName:项目名称",
                    "srmAchSat.prizedGradeName:奖励等级"
			};
			org.setOrgFlow(currUser.getOrgFlow());
			//审核状态： 审核通过、待审核、全部
			if(StringUtil.isBlank(sat.getOperStatusId())){
				sat.setOperStatusId(AchStatusEnum.Submit.getId());
			}
			if(GlobalConstant.FLAG_Y.equals(sat.getOperStatusId())){
				sat.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
			}
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
            titles = new String[]{
                    "srmAchSat.prizedDate:年度",
                    "srmAchSat.prizedGradeName:奖项类别",
                    "srmAchSat.deptName:科室",
                    "authorName:完成者",
                    "srmAchSat.satName:项目名称",
                    "srmAchSat.prizedGradeName:奖励等级"
            };
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                sat.setDeptFlow(currUser.getDeptFlow());
            }
            if(StringUtil.isBlank(sat.getOperStatusId())){
                sat.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if(GlobalConstant.FLAG_Y.equals(sat.getOperStatusId())){
                sat.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
            }
        } else {
			titles = new String[]{
                    "srmAchSat.prizedDate:年度",
                    "srmAchSat.prizedGradeName:奖项类别",
                    "srmAchSat.deptName:科室",
                    "authorName:完成者",
                    "srmAchSat.satName:项目名称",
                    "srmAchSat.prizedGradeName:奖励等级"
			};
			sat.setApplyUserFlow(currUser.getUserFlow());
            if (GlobalConstant.FLAG_Y.equals(sat.getOperStatusId())) {
                sat.setOperStatusId("");
            }
			achSatList = satBiz.search(sat, null);
		}
        if (!scope.equals(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL)) {
            //如果查询条件orgFlow不为空，则查询该org下所有成果
            if (StringUtil.isNotBlank(org.getOrgFlow())) {
                sat.setApplyOrgFlow(org.getOrgFlow());
                achSatList = satBiz.search(sat, null,satFlows);
            }
            //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
                achSatList = satBiz.search(sat, currOrgChildList,satFlows);
            }
            if (GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope) && StringUtil.isBlank(sat.getDeptFlow())) {
                achSatList = null;
            }
        }
		List<SrmAchSatAuthor> satAuthorList = satAuthorBiz.searchAuthorList(new SrmAchSatAuthor());
		if (satAuthorList != null && !satAuthorList.isEmpty() && null != achSatList) {
			for (SrmAchSat achSat : achSatList) {
				//组织关联作者
				String authorName = "";
				AchSatExportForm satExportForm = new AchSatExportForm();
				for (SrmAchSatAuthor achSatAuthor : satAuthorList) {
					if (achSat.getSatFlow().equals(achSatAuthor.getSatFlow())) {
						//authorName = authorName + "\n" + achSatAuthor.getAuthorName();
						if(authorName.equals("")) {
							authorName = achSatAuthor.getAuthorName();
						}else{
							authorName = authorName + "," + achSatAuthor.getAuthorName();
						}
					}
				}
				satExportForm.setSrmAchSat(achSat);
				satExportForm.setAuthorName(authorName);
				searchList.add(satExportForm);
			}
		}
		ExcleUtile.exportSimpleExcleByObjs(titles, searchList, response.getOutputStream());
		String fileName = "科研成果（奖项）.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}


}
