package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAppraisalAuthorBiz;
import com.pinde.sci.biz.srm.IAppraisalBiz;
import com.pinde.sci.biz.srm.ISrmAchFileBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AchTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.ach.AchAppraisalExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmAchAppraisalAuthorList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/srm/ach/appraisal")
public class AchAppraisalController extends GeneralController{
	
	 @Autowired
	 private IAppraisalBiz appraisalBiz;
	 @Autowired
	 private IAppraisalAuthorBiz appraisalAuthorBiz;
	 @Autowired
	 private ISrmAchFileBiz srmAchFileBiz;
	 @Autowired
	 private ISrmAchProcessBiz srmAchProcessBiz;
	 @Autowired
	 private IOrgBiz orgBiz;

	/**
	 * 删除鉴定作者方法
	 * @param authorFlow
	 * @return
     */
	@RequestMapping(value = "/deleteAuthor",method={RequestMethod.GET})
	@ResponseBody
	public  String deleteAuthor(String authorFlow){
		if(StringUtil.isNotBlank(authorFlow)){
			SrmAchAppraisalAuthor author = new SrmAchAppraisalAuthor();
			author.setAuthorFlow(authorFlow);
			author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			appraisalAuthorBiz.editAppraisalAuthor(author);
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}
		
	 
	 @RequestMapping(value={"/save"})
	 @ResponseBody
	 public String saveAppraisalAndAuthor(String jsondata ,MultipartFile file,HttpServletRequest request) throws IOException{
    	 
		 SrmAchAppraisalAuthorList aList=JSON.parseObject(jsondata,SrmAchAppraisalAuthorList.class);
         SrmAchAppraisal appraisal = aList.getAppraisal();
         List<SrmAchAppraisalAuthor> authorList=aList.getAuthorList();
         
		 appraisal.setAppraisalResultName(DictTypeEnum.AppraisalResultName.getDictNameById(appraisal.getAppraisalResultId()));
		 appraisal.setAppraisalTypeName(DictTypeEnum.AppraisalTypeName.getDictNameById(appraisal.getAppraisalTypeId()));
		 appraisal.setFinishTypeName(DictTypeEnum.FinishTypeName.getDictNameById(appraisal.getFinishTypeId()));
	     appraisal.setProjSourceName(DictTypeEnum.ProjSource.getDictNameById(appraisal.getProjSourceId()));

		 appraisal.setOperStatusId(AchStatusEnum.Apply.getId());
		 appraisal.setOperStatusName(AchStatusEnum.Apply.getName());
   	     //获取申请单位信息
   	     SysUser currUser = GlobalContext.getCurrentUser();
   	     appraisal.setApplyOrgFlow(currUser.getOrgFlow());
   	     appraisal.setApplyOrgName(currUser.getOrgName());
   	     appraisal.setApplyUserFlow(currUser.getUserFlow());
   	     appraisal.setApplyUserName(currUser.getUserName());
   	     
		 //根据鉴定人相关的ID枚举获取相对应的Name（性别、学历、职称）
		 for(int i = 0; i < authorList.size(); i++){
			 authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
			 authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
			 authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
			 authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
		 }
		 SrmAchFile srmAchFile = null;
    	 if(file!=null && StringUtil.isNotBlank(file.getOriginalFilename())){
			  //封装附件对象
		       srmAchFile=new SrmAchFile();
			   byte[] b = new byte[(int) file.getSize()]; 
			   file.getInputStream().read(b);
	  	       srmAchFile.setFileFlow(aList.getSrmAchFile().getFileFlow());
			   srmAchFile.setFileContent(b);
			   srmAchFile.setFileName(file.getOriginalFilename());
		       srmAchFile.setTypeId(AchTypeEnum.Appraisal.getId());
		       srmAchFile.setTypeName(AchTypeEnum.Appraisal.getName());
	           String[] nameArray=file.getOriginalFilename().split("\\.");
		       srmAchFile.setFileSuffix(nameArray[nameArray.length-1]);
		       srmAchFile.setFileType(nameArray[nameArray.length-1]);
    	 }
		   //封装成果过程对象
    	 SrmAchProcess srmAchProcess=new SrmAchProcess();
    	 srmAchProcess.setTypeId(AchTypeEnum.Appraisal.getId());
    	 srmAchProcess.setTypeName(AchTypeEnum.Appraisal.getName());
    	 srmAchProcess.setOperateUserFlow(currUser.getUserFlow());
    	 srmAchProcess.setOperateUserName(currUser.getUserName());
    	 srmAchProcess.setOperStatusId(AchStatusEnum.Apply.getId());
    	 srmAchProcess.setOperStatusName(AchStatusEnum.Apply.getName());
    	 appraisalBiz.save(appraisal, authorList, srmAchFile, srmAchProcess);
    	 return GlobalConstant.SAVE_SUCCESSED;
	 }
	 
	 
	 @RequestMapping(value="/edit",method=RequestMethod.GET)
	 public String edit(String appraisalFlow, Model model){
		 if(StringUtil.isNotBlank(appraisalFlow)){
   	    	 SrmAchAppraisal appraisal=appraisalBiz.readAppraisal(appraisalFlow);
   	    	 model.addAttribute("appraisal", appraisal);
		 	 //鉴定作者显示
   	    	 SrmAchAppraisalAuthor author = new SrmAchAppraisalAuthor();
   	    	 author.setAppraisalFlow(appraisalFlow);
			 List<SrmAchAppraisalAuthor> appraisalAuthorList=appraisalAuthorBiz.searchAuthorList(author);
			 model.addAttribute("appraisalAuthorList",appraisalAuthorList);
			 //附件
			 List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(appraisalFlow);  
			 if(fileList != null && !fileList.isEmpty()){
				 SrmAchFile file =  fileList.get(0);
				 model.addAttribute("file", file);
			 }
   	     }
   	     return "srm/ach/appraisal/edit";
	 }
	
	 
	 @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	 public String list(Integer currentPage, SrmAchAppraisal achAppraisal ,SrmAchAppraisalAuthor author,HttpServletRequest request, Model model){
		 SysUser currUser = GlobalContext.getCurrentUser();
		 achAppraisal.setApplyUserFlow(currUser.getUserFlow());

		 PageHelper.startPage(currentPage, getPageSize(request));
		 List<SrmAchAppraisal> appraisalList = appraisalBiz.search(achAppraisal,null,null);
		 
		 Map<String,List<SrmAchAppraisalAuthor>> allAuthorMap = new LinkedHashMap<String,List<SrmAchAppraisalAuthor>>();
		 List<SrmAchAppraisalAuthor> allAppraisalAuthorList = appraisalAuthorBiz.searchAuthorList(new SrmAchAppraisalAuthor());
		 for(SrmAchAppraisalAuthor a : allAppraisalAuthorList){
			 List<SrmAchAppraisalAuthor> list = allAuthorMap.get(a.getAppraisalFlow());
			 if(list == null){
				 list = new ArrayList<SrmAchAppraisalAuthor>();
			 }
			 list.add(a);
			 allAuthorMap.put(a.getAppraisalFlow(), list);
		 }
		 model.addAttribute("allAuthorMap", allAuthorMap);
		 model.addAttribute("appraisalList", appraisalList);
		 return "srm/ach/appraisal/list";
	 }
	 
	 @RequestMapping(value="/delete",method=RequestMethod.GET)
	 @ResponseBody
	 public String delete(String appraisalFlow){
		 if(StringUtil.isNotBlank(appraisalFlow)){
		 	SrmAchAppraisal appraisal = new SrmAchAppraisal();
 			appraisal.setAppraisalFlow(appraisalFlow);
 			appraisal.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			 
 			SrmAchAppraisalAuthor author = new SrmAchAppraisalAuthor();
 			author.setAppraisalFlow(appraisalFlow);
 			List<SrmAchAppraisalAuthor> authorList = appraisalAuthorBiz.searchAuthorList(author);
 			for(SrmAchAppraisalAuthor a : authorList){
 				a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
 			}
			//附件
			SrmAchFile file = null;
			List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(appraisalFlow); 
	    	if(fileList != null && !fileList.isEmpty()){
	    		 file = fileList.get(0);
	    		 file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
	    	}
	    	int result = appraisalBiz.edit(appraisal, authorList, file);
	    	if(result == GlobalConstant.ONE_LINE){
	    		return GlobalConstant.DELETE_SUCCESSED;
	    	}
		}
		 return GlobalConstant.DELETE_FAIL;
	 }
	 
		@RequestMapping(value="/submitAudit",method={RequestMethod.GET})
	 	@ResponseBody
	 	public String submitAudit(@RequestParam(value="appraisalFlow" , required=true)String appraisalFlow,Model model){
	 		SrmAchAppraisal appraisal=appraisalBiz.readAppraisal(appraisalFlow);
	 		appraisal.setOperStatusId(AchStatusEnum.Submit.getId());
	 		appraisal.setOperStatusName(AchStatusEnum.Submit.getName());
	 		 
	 		SrmAchProcess process=srmAchProcessBiz.searchAchProcess(appraisalFlow, AchStatusEnum.Apply.getId()).get(0);
	 		process.setProcessFlow(PkUtil.getUUID());
	 		process.setOperStatusId(AchStatusEnum.Submit.getId());
	 		process.setOperStatusName(AchStatusEnum.Submit.getName());
	 		GeneralMethod.setRecordInfo(process, true);
	 		process.setOperateTime(process.getCreateTime());
	 		SysUser currUser = GlobalContext.getCurrentUser();
	 		process.setOperateUserFlow(currUser.getUserFlow());
	 		process.setOperateUserName(currUser.getUserName());
	 		appraisalBiz.updateAppraisalStatus(appraisal, process);
	 		 
	 		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	 	}
	
		@RequestMapping(value="/saveAudit",method={RequestMethod.POST})
		@ResponseBody
		public String saveAudit(@RequestParam(value="agreeFlag" , required=true)String agreeFlag,String auditContent,
	 			                @RequestParam(value="appraisalFlow" , required=true)String appraisalFlow,Model model){
		    SrmAchAppraisal appraisal=appraisalBiz.readAppraisal(appraisalFlow);
	 		SrmAchProcess process=srmAchProcessBiz.searchAchProcess(appraisalFlow, AchStatusEnum.Apply.getId()).get(0);

	 		if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
	 			appraisal.setOperStatusId(AchStatusEnum.FirstAudit.getId());
	 			appraisal.setOperStatusName(AchStatusEnum.FirstAudit.getName());
	 			 process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
	 			 process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
	 		}
	 		else{
	 			appraisal.setOperStatusId(AchStatusEnum.RollBack.getId());
	 			appraisal.setOperStatusName(AchStatusEnum.RollBack.getName());
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
	 	    appraisalBiz.updateAppraisalStatus(appraisal, process);
	 		return GlobalConstant.OPERATE_SUCCESSED;
	 	}
		  
		@RequestMapping("/audit")
		public String audit(@RequestParam(value="appraisalFlow" , required=true)String appraisalFlow,Model model){
			//根据成果流水号查询相关信息，用于加载审核页面 
			 SrmAchFile file = null;
			 List<SrmAchFile> fileList=null;
			if(StringUtil.isNotBlank(appraisalFlow)){
				//查询成果本身
				SrmAchAppraisal appraisal=appraisalBiz.readAppraisal(appraisalFlow);
				model.addAttribute("appraisal", appraisal);
				//查询成果作者
				SrmAchAppraisalAuthor author = new SrmAchAppraisalAuthor();
		    	author.setAppraisalFlow(appraisalFlow);
			    List<SrmAchAppraisalAuthor> appraisalAuthorList = appraisalAuthorBiz.searchAuthorList(author);
				model.addAttribute("appraisalAuthorList", appraisalAuthorList);
				//查询成果附件
			    fileList=srmAchFileBiz.searchSrmAchFile(appraisalFlow);  
			  if(fileList!=null && !fileList.isEmpty()){
			    file=fileList.get(0);
			    model.addAttribute("file", file);
			   }
			}
	 		return "srm/ach/appraisal/audit";
		}
		   
		/**
		 * 专利审核列表
		 * @param appraisal
		 * @param model
		 * @return
		 */
		 @RequestMapping(value="/auditList/{scope}",method={RequestMethod.POST,RequestMethod.GET})
		 public String auditList(@PathVariable String scope,Integer currentPage, SrmAchAppraisal appraisal, SrmAchAppraisalAuthor author, SysOrg org,Model model,HttpServletRequest request){
                 model.addAttribute("scope",scope);
                 SysUser currUser = GlobalContext.getCurrentUser();
		    	 List<SrmAchAppraisal> searchList=null;
		    	 //查询当前机构下属所有级别子机构包含自身
		    	 List<SysOrg> currOrgChildList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
		    	 //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
		    	 Map<String,List<SysOrg>> resultMap=orgBiz.searchChargeAndApply(org,scope);
		    	 //获取当前机构下属一级的机构
		    	 List<SysOrg> firstGradeOrgList=(List<SysOrg>) resultMap.get("firstGradeOrgList");
		    	 model.addAttribute("firstGradeOrgList", firstGradeOrgList);
		 		 List<String> appraisalFlowList = appraisalAuthorBiz.getAppraisalFlowByAuthor(author);
		    	 if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL .equals(scope)){
		    		//设置查询条件：科技处审核通过的成果
		      		appraisal.setOperStatusId(AchStatusEnum.FirstAudit.getId()); 
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
		 		   		    searchList = appraisalBiz.search(appraisal,selfOrgList,appraisalFlowList);
		 	   		    }else{
                            PageHelper.startPage(currentPage, getPageSize(request));
		 		   		    searchList = appraisalBiz.search(appraisal,secondGradeOrgList,appraisalFlowList);
		 	   		    }
		  			}
		  			
		    	 }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
		    		appraisal.setOperStatusId(AchStatusEnum.FirstAudit.getId()); 
		    	 }else{
		    		 if(StringUtil.isBlank(appraisal.getOperStatusId())){
		    			 appraisal.setOperStatusId(AchStatusEnum.Submit.getId());
		     		 }
		    		 if(GlobalConstant.FLAG_Y.equals(appraisal.getOperStatusId())){
		    			appraisal.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
		    		 }
		    	 }
		    	//如果查询条件orgFlow不为空，则查询该org下所有成果
		    	 if(StringUtil.isNotBlank(org.getOrgFlow())){
		    		 appraisal.setApplyOrgFlow(org.getOrgFlow());
                     PageHelper.startPage(currentPage, getPageSize(request));
			   		 searchList = appraisalBiz.search(appraisal, null,appraisalFlowList);
			     }
		    	 //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
		    	 if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())){
                     PageHelper.startPage(currentPage, getPageSize(request));
			   		 searchList = appraisalBiz.search(appraisal, currOrgChildList,appraisalFlowList);
		    	 }
		    	 
		    	 
		    	 Map<String, List<SrmAchAppraisalAuthor>> allAuthorMap = new HashMap<String, List<SrmAchAppraisalAuthor>>();
	 	    	 List<SrmAchAppraisalAuthor> appraisalAuthorList = appraisalAuthorBiz.searchAuthorList(new SrmAchAppraisalAuthor());
	 	    	 if(appraisalAuthorList != null && !appraisalAuthorList.isEmpty()){
	 	    		 for(SrmAchAppraisalAuthor a : appraisalAuthorList){
	 	    			 List<SrmAchAppraisalAuthor> authorList = allAuthorMap.get(a.getAppraisalFlow());
	 	    			 if(authorList == null){
	 	    				 authorList = new ArrayList<SrmAchAppraisalAuthor>();
	 	    			 }
	 	    			 authorList.add(a);
	 	    			 allAuthorMap.put(a.getAppraisalFlow(), authorList);
	 	    		 }
	 	    	 }

	    		 model.addAttribute("allAuthorMap", allAuthorMap);
	    		 model.addAttribute("appraisalList", searchList);
	     		 return "srm/ach/appraisal/auditList"+scope;
		     }

    /**
     * 导出科研成果（鉴定）
     *
     * @param appraisal
     * @param response
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/exportAppraisalExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportAppraisalExcel(@PathVariable String scope,SrmAchAppraisal appraisal, SrmAchAppraisalAuthor author, SysOrg org, HttpServletResponse response) throws Exception {

        String[] titles;//导出列表头信息
        List<AchAppraisalExportForm>  searchList= new ArrayList<AchAppraisalExportForm>() ;
        List<SrmAchAppraisal> achAppraisalList=null;
        List<SrmAchAppraisal> appraisalList=null;
        SysUser currUser = GlobalContext.getCurrentUser();
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);
        //获取当前机构下属一级的机构
        List<SysOrg> firstGradeOrgList = (List<SysOrg>) resultMap.get("firstGradeOrgList");
        List<String> appraisalFlowList = appraisalAuthorBiz.getAppraisalFlowByAuthor(author);
        //卫计委
        if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
            appraisal.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            titles = new String[]{
                    "srmAchAppraisal.appraisalName:成果名称",
                    "authorName:成果完成人",
                    "srmAchAppraisal.applyOrgName:申报单位",
                    "srmAchAppraisal.appraisalDate:鉴定日期",
                    "srmAchAppraisal.appraisalResultName:鉴定结论",
                    "srmAchAppraisal.projSourceName:项目来源",
                    "srmAchAppraisal.appraisalTypeName:鉴定形式"
            };
            List<SysOrg> secondGradeOrgList=(List<SysOrg>) resultMap.get("secondGradeOrgList");

            if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())){
                if(null == secondGradeOrgList || secondGradeOrgList.isEmpty()){
                    SysOrg sysOrg=orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList=new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    achAppraisalList = appraisalBiz.search(appraisal,selfOrgList,appraisalFlowList);
                }else{
                    achAppraisalList = appraisalBiz.search(appraisal,secondGradeOrgList,appraisalFlowList);
                }
            }
            //主管部门
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
            titles = new String[]{
                    "srmAchAppraisal.appraisalName:成果名称",
                    "authorName:成果完成人",
                    "srmAchAppraisal.appraisalDate:鉴定日期",
                    "srmAchAppraisal.appraisalResultName:鉴定结论",
                    "srmAchAppraisal.projSourceName:项目来源",
                    "srmAchAppraisal.appraisalTypeName:鉴定形式"
            };
            appraisal.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //申报单位
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)){
            titles = new String[]{
                    "srmAchAppraisal.appraisalName:成果名称",
                    "authorName:成果完成人",
                    "srmAchAppraisal.appraisalDate:鉴定日期",
                    "srmAchAppraisal.appraisalResultName:鉴定结论",
                    "srmAchAppraisal.projSourceName:项目来源",
                    "srmAchAppraisal.operStatusName:审核状态"
            };

            if (StringUtil.isBlank(appraisal.getOperStatusId())) {
                appraisal.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(appraisal.getOperStatusId())) {
                appraisal.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
            //负责人
        }else {
            titles = new String[]{
                    "srmAchAppraisal.appraisalName:成果名称",
                    "authorName:成果完成人",
                    "srmAchAppraisal.appraisalDate:鉴定日期",
                    "srmAchAppraisal.appraisalResultName:鉴定结论",
                    "srmAchAppraisal.projSourceName:项目来源",
                    "srmAchAppraisal.operStatusName:审核状态"
            };
            appraisal.setApplyUserFlow(currUser.getUserFlow());
            achAppraisalList = appraisalBiz.search(appraisal, null,appraisalFlowList);
        }
        if(!scope.equals(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL)) {
            //如果查询条件orgFlow不为空，则查询该org下所有成果
            if(StringUtil.isNotBlank(org.getOrgFlow())){
                appraisal.setApplyOrgFlow(org.getOrgFlow());
                achAppraisalList = appraisalBiz.search(appraisal, null,appraisalFlowList);
            }
            //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
            if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())){
                achAppraisalList = appraisalBiz.search(appraisal, currOrgChildList,appraisalFlowList);
            }
        }
        Map<String, List<SrmAchAppraisalAuthor>> allAuthorMap = new HashMap<String, List<SrmAchAppraisalAuthor>>();
        List<SrmAchAppraisalAuthor> appraisalAuthorList = appraisalAuthorBiz.searchAuthorList(new SrmAchAppraisalAuthor());
        if(appraisalAuthorList != null && !appraisalAuthorList.isEmpty()){
            for(SrmAchAppraisalAuthor a : appraisalAuthorList){
                List<SrmAchAppraisalAuthor> authorList = allAuthorMap.get(a.getAppraisalFlow());
                if(authorList == null){
                    authorList = new ArrayList<SrmAchAppraisalAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getAppraisalFlow(), authorList);
            }
        }

        //过滤
        if(StringUtil.isNotBlank(author.getAuthorName())){
            appraisalList = new ArrayList<SrmAchAppraisal>();
            for(SrmAchAppraisal b : achAppraisalList){
                boolean addFlag = false;
                List<SrmAchAppraisalAuthor> authorByNameList = allAuthorMap.get(b.getAppraisalFlow());
                if(authorByNameList != null){
                    for(SrmAchAppraisalAuthor na : authorByNameList){
                        if(na.getAuthorName().equals(author.getAuthorName())){
                            addFlag = true;
                            break;
                        }
                    }
                }
                if(addFlag){
                    appraisalList.add(b);
                }
            }
        }else {
            appraisalList = achAppraisalList;
        }
        if (appraisalAuthorList != null && !appraisalAuthorList.isEmpty() && appraisalList != null) {
            for (SrmAchAppraisal achAppraisal : appraisalList) {
                //组织关联作者
                String authorName = "";
                AchAppraisalExportForm appraisalExportForm = new AchAppraisalExportForm();
                for (SrmAchAppraisalAuthor appraisalAuthor : appraisalAuthorList) {
                    if (achAppraisal.getAppraisalFlow().equals(appraisalAuthor.getAppraisalFlow())) {
                        authorName = authorName + " " + appraisalAuthor.getAuthorName();
                    }
                }
                appraisalExportForm.setSrmAchAppraisal(achAppraisal);
                appraisalExportForm.setAuthorName(authorName);
                searchList.add(appraisalExportForm);
            }
        }

        ExcleUtile.exportSimpleExcleByObjs(titles,searchList, response.getOutputStream());
        String fileName = "科研成果（鉴定）.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
