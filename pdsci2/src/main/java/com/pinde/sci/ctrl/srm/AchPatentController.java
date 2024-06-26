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
import com.pinde.sci.form.ach.AchPatentExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmAchPatentAuthorList;
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

//控制器标记
@Controller
//项目路径
@RequestMapping("/srm/ach/patent")
public class AchPatentController  extends GeneralController{

	//自动装配
    @Autowired
    private IPatentBiz patentBiz;
    @Autowired
    private IPatentAuthorBiz patentAuthorBiz;
    @Autowired
 	private ISrmAchFileBiz srmAchFileBiz;
 	@Autowired
 	private ISrmAchProcessBiz srmAchProcessBiz;
 	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IAchScoreBiz achScoreBiz;

	 /**
	  * 执行保存和修改操作业务
	  * @param srmAchPatent 专利实体对象
	  * @return
	  * @throws IOException
	  */
    @RequestMapping(value="/save/{roleScope}" ,method=RequestMethod.POST)
    @ResponseBody
    public String save(@PathVariable String roleScope,String jsondata , @RequestParam(value="file" , required=false) MultipartFile file ,HttpServletRequest request) throws IOException{
    	 SrmAchPatentAuthorList aList=JSON.parseObject(jsondata,SrmAchPatentAuthorList.class);
    	 SrmAchPatent patent=aList.getPatent();
    	 List<SrmAchPatentAuthor> authorList=aList.getAchPatentList();
    	 //枚举：根据专利相关ID枚举获得name
    	 patent.setCooperTypeName(DictTypeEnum.PatentCooperType.getDictNameById(patent.getCooperTypeId()));
    	 patent.setProjSourceName(DictTypeEnum.ProjTypeSource.getDictNameById(patent.getProjSourceId()));
    	 patent.setOrgBelongName(DictTypeEnum.OrgBelong.getDictNameById( patent.getOrgBelongId()));
    	 patent.setRangeName(DictTypeEnum.PatentRange.getDictNameById(patent.getRangeId()));
    	 patent.setStatusName(DictTypeEnum.PatentStatus.getDictNameById(patent.getStatusId()));
    	 patent.setTypeName(DictTypeEnum.PatentType.getDictNameById(patent.getTypeId()));
		 patent.setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(patent.getBranchId()));
        if(GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            patent.setOperStatusId(AchStatusEnum.Apply.getId());
            patent.setOperStatusName(AchStatusEnum.Apply.getName());
        }
         //获取申请单位信息
         SysUser currUser = GlobalContext.getCurrentUser();
         /*patent.setApplyOrgFlow(currUser.getOrgFlow());
         patent.setApplyOrgName(currUser.getOrgName());
         patent.setApplyUserFlow(currUser.getUserFlow());
         patent.setApplyUserName(currUser.getUserName());*/

		//枚举：根据专利作者相关ID枚举获得name
		for(int i = 0; i < authorList.size(); i++){
     		//authorList.get(i).setTypeName(DictTypeEnum.AuthorType.getDictNameById(authorList.get(i).getTypeId()));
  			authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
  			authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
  			authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
  			authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
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

		//封装成果过程对象
		SrmAchProcess srmAchProcess=new SrmAchProcess();
		srmAchProcess.setTypeId(AchTypeEnum.Patent.getId());
		srmAchProcess.setTypeName(AchTypeEnum.Patent.getName());
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
       	SrmAchFile srmAchFile = null;
		if(file != null && StringUtil.isNotBlank(file.getOriginalFilename())){
			//封装附件对象
			srmAchFile =new SrmAchFile();
			byte[] b = new byte[(int) file.getSize()];
			file.getInputStream().read(b);
			srmAchFile.setFileFlow(aList.getSrmAchFile().getFileFlow());
			srmAchFile.setFileContent(b);
			srmAchFile.setFileName(file.getOriginalFilename());
			srmAchFile.setTypeId(AchTypeEnum.Patent.getId());
			srmAchFile.setTypeName(AchTypeEnum.Patent.getName());
			String[] nameArray=file.getOriginalFilename().split("\\.");
			srmAchFile.setFileSuffix(nameArray[nameArray.length-1]);
			srmAchFile.setFileType(nameArray[nameArray.length-1]);
		}
		patentBiz.save(patent, authorList,srmAchFile,srmAchProcess);
		return GlobalConstant.SAVE_SUCCESSED;
     }


     /**
      * 执行查询操作业务
      * @param srmAchPatent 专利实体对象
      * @param model
      * @return
      */
     @RequestMapping(value="/list/{scope}",method={RequestMethod.GET,RequestMethod.POST})
     public String list(@PathVariable String scope,Integer currentPage, SrmAchPatent srmAchPatent,SrmAchPatentAuthor author,HttpServletRequest request, Model model){
    	 SysUser currUser = GlobalContext.getCurrentUser();
    	 srmAchPatent.setApplyUserFlow(currUser.getUserFlow());
		 if("wxdermyy".equals(scope)&&GlobalConstant.FLAG_Y.equals(srmAchPatent.getOperStatusId())){
			 srmAchPatent.setOperStatusId("");
		 }
		 PageHelper.startPage(currentPage, getPageSize(request));
		 List<SrmAchPatent> patentList = patentBiz.search(srmAchPatent, null);

		 Map<String,List<SrmAchPatentAuthor>> allAuthorMap = new LinkedHashMap<String,List<SrmAchPatentAuthor>>();

		 List<SrmAchPatentAuthor> patentAuthorList = patentAuthorBiz.searchAuthorList(new SrmAchPatentAuthor());
		 if(patentList != null && !patentList.isEmpty()){
			 for(SrmAchPatentAuthor a : patentAuthorList){
				 List<SrmAchPatentAuthor> list = allAuthorMap.get(a.getPatentFlow());
				 if(list == null){
					 list = new ArrayList<SrmAchPatentAuthor>();
				 }
				 list.add(a);
				 allAuthorMap.put(a.getPatentFlow(), list);
			 }
		 }
		 model.addAttribute("allAuthorMap", allAuthorMap);
    	 model.addAttribute("patentList",patentList);
         if("xzzxyy".equals(scope)){
             return "srm/ach_xzzxyy/patent/list";
         }
         if("wxdermyy".equals(scope)){
             return "srm/ach_wxdermyy/patent/list";
         }
		 if("common".equals(scope)){
			 return "srm/ach_common/patent/list";
		 }
    	 return "srm/ach/patent/list";
     }

	/**
	 * 专利号是否已存在
	 * @return
	 */
	@RequestMapping("/patentIsExist")
	@ResponseBody
	public SrmAchPatent patentIsExist(String authorizeCode){
		SrmAchPatent patent = null;
		if(StringUtil.isNotBlank(authorizeCode)) {
			authorizeCode = authorizeCode.trim();
			patent = patentBiz.patentIsExist(authorizeCode);
		}
		return patent;
	}

     /**
      * 专利审核列表
      * @param thesis
      * @param model
      * @return
      */
     @RequestMapping(value="/auditList/{scope}",method={RequestMethod.POST,RequestMethod.GET})
     public String auditList(@PathVariable String scope, Integer currentPage, SrmAchPatent patent, SrmAchPatentAuthor author, SysOrg org,Model model,HttpServletRequest request){
    	 model.addAttribute("scope",scope);
         List<SrmAchPatent> searchList=null;
    	 //查询当前机构下属所有级别子机构包含自身
    	 SysUser currUser = GlobalContext.getCurrentUser();
    	 List<SysOrg> currOrgChildList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
    	 //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
    	 Map<String,List<SysOrg>> resultMap=orgBiz.searchChargeAndApply(org,scope);
    	 //获取当前机构下属一级的机构
    	 List<SysOrg> firstGradeOrgList=(List<SysOrg>) resultMap.get("firstGradeOrgList");
    	 model.addAttribute("firstGradeOrgList", firstGradeOrgList);
         List<String> patentFlows=null;
         if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
             SrmAchPatentAuthor b=new SrmAchPatentAuthor();
             b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
             if(StringUtil.isNotBlank(author.getAuthorName())){
                 b.setAuthorName(author.getAuthorName());
             }
             if(StringUtil.isNotBlank(author.getWorkCode())){
                 b.setWorkCode(author.getWorkCode());
             }
             b.setAuthorName(author.getAuthorName());
             List<SrmAchPatentAuthor> autorsList=patentAuthorBiz.searchAuthorList(b);
             if(autorsList!=null&&autorsList.size()>0){
                 patentFlows=new ArrayList<>();
                 for(SrmAchPatentAuthor sa:autorsList){
                     if(!patentFlows.contains(sa.getPatentFlow()))
                     {
                         patentFlows.add(sa.getPatentFlow());
                     }
                 }

             }
             if(null == patentFlows || patentFlows.size()==0){
                 patentFlows=new ArrayList<>();
                 patentFlows.add("");
             }
         }
         if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
             if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                 patent.setDeptFlow(currUser.getDeptFlow());
             }else{
				 if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
					 return "srm/ach_wxdermyy/patent/auditListlocal";
				 }
				 return "srm/ach_common/patent/auditListlocal";
             }
         }
		 //当前登录者是卫生局
    	 if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
    		 //设置查询条件：科技处审核通过的成果
     		 patent.setOperStatusId(AchStatusEnum.FirstAudit.getId());
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
		   		    searchList = patentBiz.search(patent, selfOrgList,patentFlows);
	   		    }else{
                    PageHelper.startPage(currentPage, getPageSize(request));
		   		    searchList = patentBiz.search(patent, secondGradeOrgList,patentFlows);
	   		    }
 			}

    	 }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
    		 patent.setOperStatusId(AchStatusEnum.FirstAudit.getId());
    	 }else{
             org.setOrgFlow(currUser.getOrgFlow());//当前登陆者是医院 只查询本医院下的成果
    		 if(StringUtil.isBlank(patent.getOperStatusId())){
    			 patent.setOperStatusId(AchStatusEnum.Submit.getId());
     		 }
    		 if(GlobalConstant.FLAG_Y.equals(patent.getOperStatusId())){
    			patent.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
    		 }
    	 }
    	//如果查询条件orgFlow不为空，则查询该org下所有成果
    	 if(StringUtil.isNotBlank(org.getOrgFlow())){
    		 patent.setApplyOrgFlow(org.getOrgFlow());
             PageHelper.startPage(currentPage, getPageSize(request));
	   		 searchList = patentBiz.search(patent, null,patentFlows);
	     }
    	 //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
    	 if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())){
             PageHelper.startPage(currentPage, getPageSize(request));
             PageHelper.startPage(currentPage, getPageSize(request));
	   		 searchList = patentBiz.search(patent, currOrgChildList,patentFlows);
    	 }

    	//组织关联作者的Map
    	Map<String, List<SrmAchPatentAuthor>> allAuthorMap = new HashMap<String, List<SrmAchPatentAuthor>>();
  		List<SrmAchPatentAuthor> patentAuthorList = patentAuthorBiz.searchAuthorList(new SrmAchPatentAuthor());
  		if(patentAuthorList != null && !patentAuthorList.isEmpty()){
  			for(SrmAchPatentAuthor a : patentAuthorList){
  				List<SrmAchPatentAuthor> authorList = allAuthorMap.get(a.getPatentFlow());
  				if(authorList == null){
  					authorList = new ArrayList<SrmAchPatentAuthor>();
  				}
  				authorList.add(a);
  				allAuthorMap.put(a.getPatentFlow(), authorList);
  			}
  		}
   		 model.addAttribute("patentList", searchList);
		 model.addAttribute("allAuthorMap", allAuthorMap);
         if("xzzxyy".equals(scope)){
             return "srm/ach_xzzxyy/patent/auditListlocal";
         }
         if("wxdermyy".equals(scope)){
             return "srm/ach_wxdermyy/patent/auditListlocal";
         }
		 if("common".equals(scope)){
			 return "srm/ach_common/patent/auditListlocal";
		 }
         if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//科主任
			 if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
				 return "srm/ach_wxdermyy/patent/auditListlocal";
			 }
			 return "srm/ach_common/patent/auditListlocal";
         }
		 return "srm/ach/patent/auditList"+scope;
 	}



     /**
      * 根据专利流水号查询该专利的详细信息
      * @param srmAchPatent  专利实体对象
      * @param model
      * @return
      */
     @RequestMapping(value="/edit/{scope}",method=RequestMethod.GET)
     public String edit(@PathVariable String scope,String patentFlow, Model model){
         model.addAttribute("scope",scope);
    	  if(StringUtil.isNotBlank(patentFlow)){
    		  SrmAchPatent patent=patentBiz.readPatent(patentFlow);
    		  model.addAttribute("patent", patent);
    		  //作者
    		  SrmAchPatentAuthor tempAuthor = new SrmAchPatentAuthor();
    		  tempAuthor.setPatentFlow(patentFlow);
	    	  List<SrmAchPatentAuthor> authorList=patentAuthorBiz.searchAuthorList(tempAuthor);
	    	  model.addAttribute("authorList", authorList);
	    	  //附件
	    	  List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(patentFlow);
	    	  if(fileList != null && !fileList.isEmpty()){
	    		  SrmAchFile file = fileList.get(0);
	    		  model.addAttribute("file", file);
	    	  }
    	  }
         model.addAttribute("roleScope",GlobalConstant.USER_LIST_PERSONAL);
         if("xzzxyy".equals(scope)){
             return "srm/ach_xzzxyy/patent/edit";
         }
         if("wxdermyy".equals(scope)){
             return "srm/ach_wxdermyy/patent/edit";
         }
		 if("common".equals(scope)){
			 return "srm/ach_common/patent/edit";
		 }
    	  return "srm/ach/patent/edit";
     }

     /**
      * 根据专利流水号删除专利
      * @param srmAchPatent  专利实体对象
      * @return
      */
     @RequestMapping(value="/delete",method=RequestMethod.GET)
     @ResponseBody
     public  String delete(String patentFlow){
    	 if(StringUtil.isNotBlank(patentFlow)){
    		 SrmAchPatent patent = new SrmAchPatent();
    		 patent.setPatentFlow(patentFlow);
    		 patent.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
    		 //作者
    		 SrmAchPatentAuthor tempAuthor = new SrmAchPatentAuthor();
    		 tempAuthor.setPatentFlow(patentFlow);
			 List<SrmAchPatentAuthor> authorList = patentAuthorBiz.searchAuthorList(tempAuthor);
			 for(SrmAchPatentAuthor author : authorList){
				 author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			 }
			 //附件
			 List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(patentFlow);
			 SrmAchFile file = null;
	    	 if(fileList != null && !fileList.isEmpty()){
	    		 file = fileList.get(0);
	    		 file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
	    	 }
			 int result = patentBiz.edit(patent, authorList, file);

			 if(result == GlobalConstant.ONE_LINE){
				 return GlobalConstant.DELETE_SUCCESSED;
			 }
		 }
		 return GlobalConstant.DELETE_FAIL;
     }



	@RequestMapping(value = "/deleteAuthor",method={RequestMethod.GET})
	@ResponseBody
	public String deleteAuthor(String authorFlow){
		if(StringUtil.isNotBlank(authorFlow)){
			SrmAchPatentAuthor author = new SrmAchPatentAuthor();
			author.setAuthorFlow(authorFlow);
			author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			patentAuthorBiz.editAuthor(author);
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}


    @RequestMapping("/audit")
 	public String audit(@RequestParam(value="patentFlow", required=true)String patentFlow,Model model){
		SrmAchScore score=new SrmAchScore();
		score.setScoreStatusId(GlobalConstant.RECORD_STATUS_Y);
		List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(score);
		model.addAttribute("srmAchScoreList",srmAchScoreList);
		//根据成果流水号查询相关信息，用于加载审核页面
		 SrmAchFile file = null;
		 List<SrmAchFile> fileList=null;
		if(StringUtil.isNotBlank(patentFlow)){
			//查询成果本身
			SrmAchPatent patent=patentBiz.readPatent(patentFlow);
			model.addAttribute("patent", patent);
			//查询成果作者
			SrmAchPatentAuthor author = new SrmAchPatentAuthor();
	    	author.setPatentFlow(patentFlow);
		    List<SrmAchPatentAuthor> authorList=patentAuthorBiz.searchAuthorList(author);
			model.addAttribute("authorList", authorList);
			//查询成果附件
		    fileList=srmAchFileBiz.searchSrmAchFile(patentFlow);
		  if(fileList!=null && !fileList.isEmpty()){
		    file=fileList.get(0);
		    model.addAttribute("file", file);
		   }
		}
        model.addAttribute("roleScope",GlobalConstant.USER_LIST_LOCAL);
		model.addAttribute("company","Y");//是否显示积分项
        //徐州中心医院
        if("Y".equals(InitConfig.getSysCfg("srm_local_type"))){
            return "srm/ach_xzzxyy/patent/audit";
        }
       //无锡第二人民医院
        if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
            return "srm/ach_wxdermyy/patent/audit";
        }
		if("common".equals(InitConfig.getSysCfg("srm_local_type"))){
			return "srm/ach_common/patent/audit";
		}
 		return "srm/ach/patent/audit";
 	}


    @RequestMapping(value="/saveAudit",method={RequestMethod.POST})
 	@ResponseBody
 	public String saveAudit(@RequestParam(value="agreeFlag" , required=true)String agreeFlag,String auditContent,
 			                @RequestParam(value="patentFlow" , required=true)String patentFlow,Model model){
 		SrmAchPatent patent=patentBiz.readPatent(patentFlow);
 		SrmAchProcess process=srmAchProcessBiz.searchAchProcess(patentFlow, AchStatusEnum.Apply.getId()).get(0);

 		if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
 			 patent.setOperStatusId(AchStatusEnum.FirstAudit.getId());
 			 patent.setOperStatusName(AchStatusEnum.FirstAudit.getName());
 			 process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
 			 process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
 		}else{
 			 patent.setOperStatusId(AchStatusEnum.RollBack.getId());
 			 patent.setOperStatusName(AchStatusEnum.RollBack.getName());
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
		 patentBiz.updatePatentStatus(patent, process);
		 return GlobalConstant.OPERATE_SUCCESSED;
 	}



 	@RequestMapping(value="/submitAudit",method={RequestMethod.GET})
 	@ResponseBody
 	public String submitAudit(@RequestParam(value="patentFlow" , required=true)String patentFlow,Model model){
		SrmAchPatent patent=patentBiz.readPatent(patentFlow);
		patent.setOperStatusId(AchStatusEnum.Submit.getId());
		patent.setOperStatusName(AchStatusEnum.Submit.getName());

		 SrmAchProcess process=srmAchProcessBiz.searchAchProcess(patentFlow, AchStatusEnum.Apply.getId()).get(0);
		 process.setProcessFlow(PkUtil.getUUID());
		 process.setOperStatusId(AchStatusEnum.Submit.getId());
		 process.setOperStatusName(AchStatusEnum.Submit.getName());
		 GeneralMethod.setRecordInfo(process, true);
		 process.setOperateTime(process.getCreateTime());
		 SysUser currUser = GlobalContext.getCurrentUser();
		 process.setOperateUserFlow(currUser.getUserFlow());
		 process.setOperateUserName(currUser.getUserName());
		 patentBiz.updatePatentStatus(patent, process);
		 return GlobalConstant.OPRE_SUCCESSED_FLAG;
 	}

    /**
     * 专利信息导出
     *
     * @param scope
     * @param patent
     * @param author
     * @param org
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportPatentExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportPatentExcel(@PathVariable String scope,SrmAchPatent patent, SrmAchPatentAuthor author, SysOrg org, HttpServletResponse response) throws Exception {
		if(scope.equals("wxdermyy") || "common".equals(scope)){
			scope = GlobalConstant.PROJ_STATUS_SCOPE_LOCAL;
		}
        String[] titles;//导出列表头信息
        SysUser currUser = GlobalContext.getCurrentUser();
        /*著作权导出信息列表*/
        List<AchPatentExportForm> searchList = new ArrayList<AchPatentExportForm>();
        /*查询著作权信息*/
        List<SrmAchPatent> patentList=null;
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);
		List<String> patentFlows=null;
		if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
			SrmAchPatentAuthor b=new SrmAchPatentAuthor();
			b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			if(StringUtil.isNotBlank(author.getAuthorName())){
				b.setAuthorName(author.getAuthorName());
			}
			if(StringUtil.isNotBlank(author.getWorkCode())){
				b.setWorkCode(author.getWorkCode());
			}
			b.setAuthorName(author.getAuthorName());
			List<SrmAchPatentAuthor> autorsList=patentAuthorBiz.searchAuthorList(b);
			if(autorsList!=null&&autorsList.size()>0){
				patentFlows=new ArrayList<>();
				for(SrmAchPatentAuthor sa:autorsList){
					if(!patentFlows.contains(sa.getPatentFlow()))
					{
						patentFlows.add(sa.getPatentFlow());
					}
				}

			}
			if(null == patentFlows || patentFlows.size()==0){
				patentFlows=new ArrayList<>();
				patentFlows.add("");
			}
		}
        //卫计委
        if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
            patent.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            titles = new String[]{
                    "srmAchPatent.patentName:专利名称",
                    "srmAchPatent.applyOrgName:申报单位",
                    "authorName:专利发明（设计）人",
                    "srmAchPatent.typeName:专利类型",
                    "srmAchPatent.statusName:专利状态",
                    "srmAchPatent.applyDate:申请日期"
            };
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())) {
                //设置查询条件：科技处审核通过的成果
                //如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
                List<SysOrg> secondGradeOrgList = (List<SysOrg>) resultMap.get("secondGradeOrgList");
                if (null == secondGradeOrgList || secondGradeOrgList.isEmpty()) {
                    SysOrg sysOrg = orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList = new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    patentList = patentBiz.search(patent, selfOrgList,patentFlows);
                } else {
                    patentList = patentBiz.search(patent, secondGradeOrgList,patentFlows);
                }
            }

            //主管部门
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
            titles = new String[]{
                    "srmAchPatent.patentName:专利名称",
                    "srmAchPatent.applyOrgName:申报单位",
                    "authorName:专利发明（设计）人",
                    "srmAchPatent.typeName:专利类型",
                    "srmAchPatent.statusName:专利状态",
                    "srmAchPatent.applyDate:申请日期"
            };
            patent.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //申报单位
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)){
            titles = new String[]{
                    "serial:序号",
                    "srmAchPatent.deptName:科室",
                    "srmAchPatent.patentName:专利名称",
                    "authorName:发明人",
                    "srmAchPatent.authorizeCode:授权号",
                    "srmAchPatent.typeName:专利类型",
                    "srmAchPatent.authorizeDate:授权日期"
            };
			org.setOrgFlow(currUser.getOrgFlow());
            if (StringUtil.isBlank(patent.getOperStatusId())) {
                patent.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(patent.getOperStatusId())) {
                patent.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }

        }else if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
            titles = new String[]{
                    "serial:序号",
                    "srmAchPatent.deptName:科室",
                    "srmAchPatent.patentName:专利名称",
                    "authorName:发明人",
                    "srmAchPatent.authorizeCode:授权号",
                    "srmAchPatent.typeName:专利类型",
                    "srmAchPatent.authorizeDate:授权日期"
            };
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                patent.setDeptFlow(currUser.getDeptFlow());
            }
            if (StringUtil.isBlank(patent.getOperStatusId())) {
                patent.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(patent.getOperStatusId())) {
                patent.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
            //负责人
        } else {
            titles = new String[]{
                    "serial:序号",
                    "srmAchPatent.deptName:科室",
                    "srmAchPatent.patentName:专利名称",
                    "authorName:发明人",
                    "srmAchPatent.authorizeCode:授权号",
                    "srmAchPatent.typeName:专利类型",
                    "srmAchPatent.authorizeDate:授权日期"
            };
            if (GlobalConstant.FLAG_Y.equals(patent.getOperStatusId())) {
                patent.setOperStatusId("");
            }
            patent.setApplyUserFlow(currUser.getUserFlow());
            patentList = patentBiz.search(patent, null);
        }
        if(!scope.equals(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL)) {
            //如果查询条件orgFlow不为空，则查询该org下所有成果
            if (StringUtil.isNotBlank(org.getOrgFlow())) {
                patent.setApplyOrgFlow(org.getOrgFlow());
                patentList = patentBiz.search(patent, null,patentFlows);
            }
            //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
                patentList = patentBiz.search(patent, currOrgChildList,patentFlows);
            }
            if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope) && StringUtil.isBlank(patent.getDeptFlow())){
                patentList = null;
            }
        }
        List<SrmAchPatentAuthor> patentAuthorList = patentAuthorBiz.searchAuthorList(new SrmAchPatentAuthor());
        if (patentAuthorList != null && !patentAuthorList.isEmpty() && patentList != null) {
            Integer serial = 1;//序号
            for (SrmAchPatent achPatent : patentList) {
                //组织关联作者
                String authorName = "";
                AchPatentExportForm patentExportForm = new AchPatentExportForm();
                for (SrmAchPatentAuthor patentAuthor : patentAuthorList) {
                    if (achPatent.getPatentFlow().equals(patentAuthor.getPatentFlow())) {
						if(authorName.equals("")) {
							authorName = patentAuthor.getAuthorName();
						}else{
							authorName = authorName + "," + patentAuthor.getAuthorName();
						}
                    }
                }
                patentExportForm.setSrmAchPatent(achPatent);
                patentExportForm.setAuthorName(authorName);
                patentExportForm.setSerial(serial.toString());
                serial++;
                searchList.add(patentExportForm);
            }
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, searchList, response.getOutputStream());
        String fileName = "科研成果（专利）.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
