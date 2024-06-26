package com.pinde.sci.ctrl.gyxjgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduCourseBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjEduCourseMajorBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.gyxjgl.XjglCourseTypeEnum;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseMajor;
import com.pinde.sci.model.mo.EduMajorCredit;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorListExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/gyxjgl/majorCourse")
public class GyXjglCourseMajorController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(GyXjglCourseMajorController.class);
	@Autowired
	private IGyXjEduCourseMajorBiz courseMajorBiz;
	@Autowired
	private IGyXjEduCourseBiz courseBiz;

	/**
	 * 查询出专业及其对应课程
	 * @param model
	 */
    @RequestMapping("/list")
	public String courseMajorList(EduCourseMajor courseMajor,Integer currentPage,HttpServletRequest request,Model model){
    	String year = courseMajor.getPlanYear();
    	if(StringUtil.isBlank(year)){
    		 year = DateUtil.transDateTime(DateUtil.getCurrDate(), "yyyy-MM-dd", "yyyy");
    		 courseMajor.setPlanYear(year);
    	}
    	model.addAttribute("year", year);
//    	PageHelper.startPage(currentPage, getPageSize(request));
//    	List<MajorCourseForm> formList= this.courseMajorBiz.searchIncludeCourseMajor(courseMajor,course);
//    	model.addAttribute("formList", formList);
    	
    	List<EduCourse> courseList = courseBiz.searchAllCourseList(new EduCourse(),null);
    	Map<String,EduCourse> courseMap = new HashMap<String, EduCourse>();
    	for(EduCourse temp : courseList){
    		courseMap.put(temp.getCourseFlow(), temp);
    	}
    	model.addAttribute("eduCourseMap", courseMap);
    	
    	
    	List<EduCourseMajor> courseMajorList = courseMajorBiz.searchCourseMajorList(courseMajor);
    	Map<String,Object> majorMap = new HashMap<String, Object>();
    	Map<String,Map<String,List<EduCourseMajor>>> yearCourseMajorMap = new HashMap<String, Map<String,List<EduCourseMajor>>>();
    	for(EduCourseMajor temp : courseMajorList){
    		Map<String,List<EduCourseMajor>> courseMajorMap  = yearCourseMajorMap.get(temp.getPlanYear());
    		if(courseMajorMap == null){
    			courseMajorMap = new HashMap<String, List<EduCourseMajor>>();
    		}
    		List<EduCourseMajor> list = courseMajorMap.get(temp.getMajorId());
    		if(list == null){
    			list = new ArrayList<EduCourseMajor>();
    		}
    		list.add(temp);
    		courseMajorMap.put(temp.getMajorId(), list);
    		yearCourseMajorMap.put(temp.getPlanYear(), courseMajorMap);
    		
    		majorMap.put(temp.getMajorId(), temp.getMajorName());
    		
    		EduCourse course = courseMap.get(temp.getCourseFlow());
    		if(course!=null){
	    		//总学时
	    		String _majorAllCoursePeriodStr = (String) majorMap.get(temp.getPlanYear()+"_"+temp.getMajorId()+"_majorAllCoursePeriod");
	    		if(_majorAllCoursePeriodStr == null){
	    			_majorAllCoursePeriodStr = "0.0";
	    		}
	    		Double majorAllCoursePeriod = Double.parseDouble(_majorAllCoursePeriodStr);
	    		majorAllCoursePeriod += Double.parseDouble(StringUtil.defaultIfEmpty(course.getCoursePeriod(), "0.0"));
	    		
	    		//总学分
	    		String _majorAllCourseCreditStr = (String) majorMap.get(temp.getPlanYear()+"_"+temp.getMajorId()+"_majorAllCourseCredit");
	    		if(_majorAllCourseCreditStr == null){
	    			_majorAllCourseCreditStr = "0.0";
	    		}
	    		Double majorAllCourseCredit = Double.parseDouble(_majorAllCourseCreditStr);
	    		majorAllCourseCredit += Double.parseDouble(StringUtil.defaultIfEmpty(course.getCourseCredit(), "0.0"));
	    		
	    		majorMap.put(temp.getPlanYear()+"_"+temp.getMajorId()+"_majorAllCoursePeriod", String.valueOf(majorAllCoursePeriod));
	    		majorMap.put(temp.getPlanYear()+"_"+temp.getMajorId()+"_majorAllCourseCredit", String.valueOf(majorAllCourseCredit));
    		}
    	}
    	model.addAttribute("yearCourseMajorMap", yearCourseMajorMap);
    	model.addAttribute("majorMap", majorMap);
    	
    	
    	List<String> yearList=this.courseMajorBiz.searchRecommendYear(year,null);
    	List<String> delYearList=this.courseMajorBiz.searchRecommendYear(null,GlobalConstant.FLAG_N);
    	model.addAttribute("delYearList", delYearList);
    	model.addAttribute("yearList", yearList);
		return "gyxjgl/plat/professMaintain";
	}
    
    @RequestMapping("/edit")
   	public String edit(String majorId,Model model){
   		return "gyxjgl/plat/professInfo";
   	}
    
    @RequestMapping("/delCourseMajor")
    @ResponseBody
   	public String delCourseMajor(EduCourseMajor courseMajor,Model model){
    	List<EduCourseMajor> courseMajorList=this.courseMajorBiz.searchCourseMajorListNoStatus(courseMajor);
    	if(courseMajorList!=null && !courseMajorList.isEmpty()){
			int result = 0;
			for (int i = 0; i < courseMajorList.size(); i++) {//因之前引用数据功能(现已遗弃)导致一个专业对应多个相同的课程
				result += courseMajorBiz.delete(courseMajorList.get(i));
			}
    		if(result >= GlobalConstant.ONE_LINE){
    			return GlobalConstant.DELETE_SUCCESSED;
    		}
    	}
   		return GlobalConstant.DELETE_FAIL;
   	}
    
    @RequestMapping("/loadCourseMajorTb")
   	public String loadCourseMajorTb(String majorId,String year,Model model){
    	if(StringUtil.isNotBlank(majorId)){
    		String majorName=DictTypeEnum.GyMajor.getDictNameById(majorId);
        	model.addAttribute("majorName", majorName);
        	Map<String,Object> courseMap=searchCourseByMajor(majorId,year);
        	model.addAttribute("courseMap", courseMap);
    	}
		Map<String,String> periodMap=new HashMap<>();//课程学时
		Map<String,String> creditMap=new HashMap<>();//课程学分
		List<XjEduCourseMajorExt> courseList=this.courseMajorBiz.searchCourseByMajor(majorId,year);
		if(courseList!=null&&courseList.size()>0){
			for (XjEduCourseMajorExt ec:courseList) {
				if(ec!=null&&ec.getCourse()!=null){
					periodMap.put(ec.getCourse().getCourseFlow(),ec.getCourse().getCoursePeriod());
					creditMap.put(ec.getCourse().getCourseFlow(),ec.getCourse().getCourseCredit());
				}
			}
		}
		model.addAttribute("periodMap",periodMap);
		model.addAttribute("creditMap",creditMap);
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			return "gyxjgl/plat/professInfoTbOfGz";//广州医科大学
		}
   		return "gyxjgl/plat/professInfoTb";
   	}
    
    @RequestMapping("/saveRecommendData")
    @ResponseBody
   	public String recommendData(EduCourseMajor courseMajor,String targetYear,Model model){
    	int result=this.courseMajorBiz.saveRecommendData(courseMajor,targetYear);
    	if(result==GlobalConstant.ONE_LINE){
    		return GlobalConstant.OPERATE_SUCCESSED;
    	}
   		return GlobalConstant.OPRE_FAIL;
   	}
    
    @RequestMapping("/delRecommendData")
    @ResponseBody
   	public String delRecommendData(EduCourseMajor courseMajor,Model model){
    	int result=this.courseMajorBiz.delRecommendData(courseMajor);
    	if(result==GlobalConstant.ONE_LINE){
    		return GlobalConstant.OPERATE_SUCCESSED;
    	}
   		return GlobalConstant.OPRE_FAIL;
   	}
    
    @RequestMapping("/save")
    @ResponseBody
   	public String saveCourseMajor(EduCourseMajor courseMajor){
    	List<EduCourseMajor> courseMajorList=this.courseMajorBiz.searchCourseMajorListNoStatus(courseMajor);
    	if(courseMajorList!=null && !courseMajorList.isEmpty()){
    		courseMajor.setRecordFlow(courseMajorList.get(0).getRecordFlow());
    		if(GlobalConstant.RECORD_STATUS_N.equals(courseMajorList.get(0).getRecordStatus())){
    			courseMajor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
    		}
    	}
    	if(StringUtil.isNotBlank(courseMajor.getMajorId())){
    		String majorName=DictTypeEnum.GyMajor.getDictNameById(courseMajor.getMajorId());
    		courseMajor.setMajorName(majorName);
    	}
    	if(StringUtil.isNotBlank(courseMajor.getCourseFlow())){
    		EduCourse course=this.courseBiz.readCourse(courseMajor.getCourseFlow());
    		if(course!=null){
    			courseMajor.setCourseName(course.getCourseName());
    		}
    	}
    	if(StringUtil.isNotBlank(courseMajor.getCourseTypeId())){
    		String courseTypeName=XjglCourseTypeEnum.getNameById(courseMajor.getCourseTypeId());
    		courseMajor.setCourseTypeName(courseTypeName);
    	}else{
    		courseMajor.setCourseTypeId("");
    		courseMajor.setCourseTypeName("");
    	}
    	courseMajor.setRecommendFlag(GlobalConstant.FLAG_N);
    	int result=this.courseMajorBiz.save(courseMajor);
    	if(result==GlobalConstant.ONE_LINE){
    		return GlobalConstant.SAVE_SUCCESSED;
    	}
   		return GlobalConstant.SAVE_FAIL;
   	}

	/**
	 * 导入课程
	 */
	@RequestMapping(value="/importCourseMajor")
	public String importCourse(String deptFlow){
		return "gyxjgl/plat/courseMajorImport";
	}

	/**
	 * 导入课程
	 */
	@RequestMapping(value="/importCourseMajorFromExcel")
	@ResponseBody
	public String importCourseMajorFromExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = courseMajorBiz.importMajorCoursesFromExcel(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;

	}


//---------------------------------------基础数据-----------------------------------------    
    @RequestMapping("/searchCourseJson")
    @ResponseBody
    public List<EduCourse> searchCourseJson(String majorId,String assumeOrgFlow,String year,String planYear){
    	List<EduCourse> courseList=this.courseMajorBiz.searchCourseNotIncludeMajor(majorId,assumeOrgFlow ,year,planYear);
   		return courseList;
   	}
	@RequestMapping("/searchCourseJson2")
	@ResponseBody
	public List<EduCourse> searchCourseJson2(String majorId,String year){
		List<EduCourse> courseList=this.courseMajorBiz.searchCourseAllMajor(majorId,year);
		return courseList;
	}
    @RequestMapping("/searchMajorJson")
    @ResponseBody
    public List<SysDict> searchMajorJson(){
    	Map<String,List<SysDict>>  sysListDictMap = DictTypeEnum.sysListDictMap;
    	List<SysDict> dictList=sysListDictMap.get("GyMajor");
   		return dictList;
   	}
    
    @RequestMapping("/searchCourseByMajor")
    @ResponseBody
    public Map<String,Object> searchCourseByMajor(String majorId,String year){
    	Map<String,Object> map=new HashMap<String, Object>();
    	EduCourseMajor courseMajor=new EduCourseMajor();
    	courseMajor.setMajorId(majorId);
    	courseMajor.setPlanYear(year);
		List<XjEduCourseMajorExt> courseMajorExtList = this.courseMajorBiz.searchCourseByMajor(majorId, year);
		List<EduCourse> degreeList = new ArrayList<EduCourse>();
		List<EduCourse> optionalList=new ArrayList<EduCourse>();
		List<EduCourse> optionalNeedList=new ArrayList<EduCourse>();
		List<EduCourse> publicList=new ArrayList<EduCourse>();
    	List<EduCourse> publicNeedList=new ArrayList<EduCourse>();
    	List<EduCourse> notIncludeList=new ArrayList<EduCourse>();
    	if(courseMajorExtList!=null && !courseMajorExtList.isEmpty()){
			for (XjEduCourseMajorExt courseMajorExt : courseMajorExtList) {
				if (XjglCourseTypeEnum.Degree.getId().equals(courseMajorExt.getCourseTypeId())) {
					degreeList.add(courseMajorExt.getCourse());
    		    }
    			else if(XjglCourseTypeEnum.Optional.getId().equals(courseMajorExt.getCourseTypeId())){
    			   optionalList.add(courseMajorExt.getCourse());
    		    }
				else if(XjglCourseTypeEnum.OptionalNeed.getId().equals(courseMajorExt.getCourseTypeId())){
					optionalNeedList.add(courseMajorExt.getCourse());
				}
				else if(XjglCourseTypeEnum.Public.getId().equals(courseMajorExt.getCourseTypeId())){
					publicList.add(courseMajorExt.getCourse());
				}
    		   	else if(XjglCourseTypeEnum.PublicNeed.getId().equals(courseMajorExt.getCourseTypeId())){
					publicNeedList.add(courseMajorExt.getCourse());
    		   	}
				else if(StringUtil.isBlank(courseMajorExt.getCourseTypeId())){
    			   notIncludeList.add(courseMajorExt.getCourse());
    			}
    	   }
    	}
    	map.put("degreeList", degreeList);
    	map.put("optionalList", optionalList);
		map.put("optionalNeedList", optionalNeedList);
		map.put("publicList", publicList);
    	map.put("publicNeedList", publicNeedList);
    	map.put("notIncludeList", notIncludeList);
   		return map;
   	}
    
    @RequestMapping("/testExport")
   	public void testExport(HttpServletRequest request,HttpServletResponse response){
    	String RealPath ="D:\\";
    	//String RealPath = request.getSession().getServletContext().getRealPath("/");
        File dir=new File(RealPath+PkUtil.getUUID()+"\\");
        dir.mkdirs();
        File outputExcel=null;
        for(int i=0;i<20;i++){
        	outputExcel=new File(dir+"test_"+i+".xls");
        	outputExcel.mkdir();
        }
    	
   	}

	@RequestMapping(value={"/doSave"},method = {RequestMethod.POST})
	@ResponseBody
	public String saveCourseMajorNew(String jsonData){
		XjEduCourseMajorListExt list = JSON.parseObject(jsonData,XjEduCourseMajorListExt.class);
		String majorId=list.getMajorId();
		String planYear=list.getPlanYear();
		List<EduCourseMajor> delList=list.getDelDatas();
		List<EduCourseMajor> addList=list.getAddDatas();
		if(delList!=null&&delList.size()>0){
			for (EduCourseMajor ecm:delList) {
				ecm.setMajorId(majorId);
				ecm.setPlanYear(planYear);
				List<EduCourseMajor> courseMajorList=this.courseMajorBiz.searchCourseMajorListNoStatus(ecm);
				if(courseMajorList!=null && !courseMajorList.isEmpty()){
					for (int i = 0; i < courseMajorList.size(); i++) {//因之前引用数据功能(现已遗弃)导致一个专业对应多个相同的课程
						courseMajorBiz.delete(courseMajorList.get(i));
					}
				}
			}
		}
		if(addList!=null&&addList.size()>0){
			for (EduCourseMajor ecm1:addList) {
				ecm1.setMajorId(majorId);
				ecm1.setPlanYear(planYear);
				List<EduCourseMajor> courseMajorList=this.courseMajorBiz.searchCourseMajorListNoStatus(ecm1);
				if(courseMajorList!=null && !courseMajorList.isEmpty()){
					ecm1.setRecordFlow(courseMajorList.get(0).getRecordFlow());
					if(GlobalConstant.RECORD_STATUS_N.equals(courseMajorList.get(0).getRecordStatus())){
						ecm1.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					}
				}
				if(StringUtil.isNotBlank(ecm1.getMajorId())){
					String majorName=DictTypeEnum.GyMajor.getDictNameById(ecm1.getMajorId());
					ecm1.setMajorName(majorName);
				}
				if(StringUtil.isNotBlank(ecm1.getCourseFlow())){
					EduCourse course=this.courseBiz.readCourse(ecm1.getCourseFlow());
					if(course!=null){
						ecm1.setCourseName(course.getCourseName());
					}
				}
				if(StringUtil.isNotBlank(ecm1.getCourseTypeId())){
					String courseTypeName=XjglCourseTypeEnum.getNameById(ecm1.getCourseTypeId());
					ecm1.setCourseTypeName(courseTypeName);
				}else{
					ecm1.setCourseTypeId("");
					ecm1.setCourseTypeName("");
				}
				ecm1.setRecommendFlag(GlobalConstant.FLAG_N);
				this.courseMajorBiz.save(ecm1);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 查询出专业学分要求
	 * @param model
	 */
	@RequestMapping("/majorCreditList")
	public String majorCreditList(EduMajorCredit eduMajorCredit, Integer currentPage, HttpServletRequest request, Model model){
		PageHelper.startPage(currentPage, getPageSize(request));
		List<EduMajorCredit> majorCreditList=courseMajorBiz.searchMajorCreditList(eduMajorCredit);
		model.addAttribute("majorCreditList",majorCreditList);
		return "gyxjgl/plat/majorCreditManage";
	}

	/**
	 * 保存/修改专业学分要求
	 * @param eduMajorCredit
	 * @return
     */
	@RequestMapping("/saveMajorCredit")
	@ResponseBody
	public String saveMajorCredit(EduMajorCredit eduMajorCredit){
		if(StringUtil.isBlank(eduMajorCredit.getRecordFlow())){//保存校验
			EduMajorCredit eduMajorCredit1=new EduMajorCredit();
			eduMajorCredit1.setMajorId(eduMajorCredit.getMajorId());
			eduMajorCredit1.setTrainTypeId(eduMajorCredit.getTrainTypeId());
			List<EduMajorCredit> checkList=courseMajorBiz.searchMajorCreditList(eduMajorCredit1);
			if(checkList!=null&&checkList.size()>0){
				return GlobalConstant.NOT_NULL;
			}
		}
		eduMajorCredit.setMajorName(DictTypeEnum.GyMajor.getDictNameById(eduMajorCredit.getMajorId()));
		eduMajorCredit.setTrainTypeName(DictTypeEnum.GyTrainType.getDictNameById(eduMajorCredit.getTrainTypeId()));
		int num=courseMajorBiz.saveMajorCredit(eduMajorCredit);
		if(num>0){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 展示修改专业学分要求
	 * @param recordFlow
	 */
	@RequestMapping("/editMajorCredit")
	public String editMajorCredit(String recordFlow,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			EduMajorCredit eduMajorCredit=new EduMajorCredit();
			eduMajorCredit.setRecordFlow(recordFlow);
			List<EduMajorCredit> majorCreditList=courseMajorBiz.searchMajorCreditList(eduMajorCredit);
			if(majorCreditList!=null&&majorCreditList.size()>0){
				model.addAttribute("majorCredit",majorCreditList.get(0));
			}
		}
		return "gyxjgl/plat/editMajorCredit";
	}
}
