package com.pinde.sci.ctrl.lcjn;


import com.pinde.core.model.LcjnCourseInfo;
import com.pinde.core.model.LcjnCourseSkill;
import com.pinde.core.model.LcjnCourseSupplies;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnCostBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.core.model.LcjnSkillCfgDetail;
import com.pinde.core.model.LcjnSupplies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lcjn/cost")
public class LcjnCostController extends GeneralController {
	@Autowired
	private ILcjnCostBiz costBiz;

	@RequestMapping("/list")
	public String list(String courseFlow, Model model, Integer currentPage, HttpServletRequest request){
		//技能总表
		PageHelper.startPage(currentPage, getPageSize(request));
		List<LcjnCourseSkill> courseSkills = costBiz.searchByCourseFlow(courseFlow);
		model.addAttribute("courseSkills",courseSkills);
		//其他耗材
		Map<String,List<LcjnCourseSupplies>> suppliyMap = new HashMap<>();
		Map<String,String> priceMap = new HashMap<>();
		if(courseSkills!=null&&courseSkills.size()>0){
			for(LcjnCourseSkill courseSkill:courseSkills){
				String skillFlow = courseSkill.getSkillFlow();
				List<LcjnCourseSupplies> courseSupplies = costBiz.searchSuppliesBySkillFlowAndCourse(skillFlow,courseFlow);
				suppliyMap.put(skillFlow,courseSupplies);
				if(courseSupplies!=null&courseSupplies.size()>0){
					for(LcjnCourseSupplies courseSupplies1:courseSupplies){
						String supplyFlow = courseSupplies1.getRecordFlow();
						String id = courseSupplies1.getDictId();
						List<LcjnSupplies> supplies = costBiz.searchById(id);
						if(supplies!=null&&supplies.size()>0) {
							priceMap.put(supplyFlow,supplies.get(0).getSuppliesPrice());
						}
					}
				}
			}
		}
		model.addAttribute("priceMap",priceMap);
		model.addAttribute("suppliyMap",suppliyMap);
		//人数
		int num = costBiz.countNum(courseFlow);
		model.addAttribute("num",num);
		//耗材列表
		List<LcjnSupplies> supplyList = new ArrayList<>();
		List<String> flowList = new ArrayList<>();
		Map<String,LcjnSkillCfgDetail> materialMap = new HashMap<>();
		Map<String,String> priceMap2 = new HashMap<>();
		if(courseSkills!=null&&courseSkills.size()>0){
			for(LcjnCourseSkill courseSkill:courseSkills){
				String skillFlow = courseSkill.getSkillFlow();
				List<LcjnSkillCfgDetail> skillCfgDetails = costBiz.searchCfgBySkillFlow(skillFlow);
				if(skillCfgDetails!=null&&skillCfgDetails.size()>0){
					for(LcjnSkillCfgDetail skillCfgDetail:skillCfgDetails){
						String materiaFlow = skillCfgDetail.getMateriaFlow();
						if(StringUtil.isNotBlank(materiaFlow)){
							LcjnSupplies supply = costBiz.readSupply(materiaFlow);
							if(supply!=null){
								String price = supply.getSuppliesPrice();
								priceMap2.put(skillFlow+materiaFlow,price);
								if(!flowList.contains(supply.getSuppliesFlow())){
									flowList.add(supply.getSuppliesFlow());
									supplyList.add(supply);
								}
							}
							materialMap.put(skillFlow+materiaFlow,skillCfgDetail);
						}
					}
				}
			}
		}
		model.addAttribute("supplyList2",supplyList);
		model.addAttribute("materialMap",materialMap);
		model.addAttribute("priceMap2",priceMap2);
		return "lcjn/cost/list";
	}

	//打开添加或编辑其他耗材页面
	@RequestMapping("/editOtherSupply")
	public String editOtherSupply(String recordFlow,String courseFlow,Model model){
		LcjnCourseSupplies courseSupply = costBiz.readOtherSupply(recordFlow);
		model.addAttribute("courseSupply",courseSupply);
		//技能列表
		List<LcjnCourseSkill> courseSkills = costBiz.searchByCourseFlow(courseFlow);
		model.addAttribute("courseSkills",courseSkills);
		//耗材列表
		Map<String,String> priceMap = new HashMap<>();
		List<LcjnSupplies> suppliesList = costBiz.searchSupplyList();
		model.addAttribute("suppliesList",suppliesList);
		if(suppliesList!=null&&suppliesList.size()>0){
			for(LcjnSupplies supplies:suppliesList){
				String id = supplies.getDictId();
				if(StringUtil.isNotBlank(id)){
					List<LcjnSupplies> lcjnSupplies = costBiz.searchById(id);
					if(lcjnSupplies!=null&&lcjnSupplies.size()>0){
						LcjnSupplies lcjnSupplies1 = lcjnSupplies.get(0);
						String price = lcjnSupplies1.getSuppliesPrice();
						priceMap.put(lcjnSupplies1.getDictId(),price);
					}
				}
			}
		}
		model.addAttribute("priceMap",priceMap);
		return "lcjn/cost/editOtherSupply";
	}

	//添加或编辑其他耗材
	@RequestMapping("/saveOtherSupply")
	@ResponseBody
	public int saveOtherSupply(LcjnCourseSupplies courseSupplies){
		if(courseSupplies!=null){
			String courseFlow = courseSupplies.getCourseFlow();
			LcjnCourseInfo courseInfo = costBiz.readCourse(courseFlow);
			if(courseInfo!=null){
				String courseName = courseInfo.getCourseName();
				courseSupplies.setCourseName(courseName);
			}
			String dictId = courseSupplies.getDictId();
//			String dictName = com.pinde.core.common.enums.DictTypeEnum.SkillMaterial.getDictNameById(dictId);
//			courseSupplies.setDictName(dictName);
			if(StringUtil.isBlank(courseSupplies.getRecordFlow())){//合并相同耗材
				List<LcjnCourseSupplies> oldSupplyList = costBiz.searchSuppliesById(dictId,courseSupplies.getSkillFlow(),courseFlow);
				if(oldSupplyList.size()>0){
					String recordFlow = oldSupplyList.get(0).getRecordFlow();
					String oldUseNum = oldSupplyList.get(0).getUseNum();
					courseSupplies.setRecordFlow(recordFlow);
					String useNum = courseSupplies.getUseNum();
					useNum = String.valueOf(Integer.parseInt(useNum)+Integer.parseInt(oldUseNum));
					courseSupplies.setUseNum(useNum);
				}
			}
			int result = costBiz.editOtherSupply(courseSupplies);
			return result;
		}
		return 0;
	}

	//课程成本统计
	@RequestMapping("/courseCostList")
	public String courseCostList(LcjnCourseInfo courseInfo, String startTime, String endTime,
								 		Integer currentPage,HttpServletRequest request,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> courseMapList = costBiz.searchCourseInfo(courseInfo,startTime,endTime);
		model.addAttribute("courseMapList",courseMapList);
		//其他耗材
		Map<String,List<LcjnCourseSupplies>> suppliyMap = new HashMap<>();
		Map<String,String> priceMap = new HashMap<>();
		Map<String,Integer> numMap = new HashMap<>();
		if(courseMapList!=null&&courseMapList.size()>0){
			for(Map<String,Object> courseMap:courseMapList){
				String courseFlow = (String)courseMap.get("COURSE_FLOW");
				List<LcjnCourseSupplies> courseSupplies = costBiz.searchSuppliesByCourseFlow(courseFlow);
				suppliyMap.put(courseFlow,courseSupplies);
				if(courseSupplies!=null&courseSupplies.size()>0){
					for(LcjnCourseSupplies courseSupplies1:courseSupplies){
						String supplyFlow = courseSupplies1.getRecordFlow();
						String id = courseSupplies1.getDictId();
						List<LcjnSupplies> supplies = costBiz.searchById(id);
						if(supplies!=null&&supplies.size()>0) {
							priceMap.put(supplyFlow,supplies.get(0).getSuppliesPrice());
						}
					}
				}
				//人数
				int num = costBiz.countNum(courseFlow);
				numMap.put(courseFlow,num);
			}
		}
		model.addAttribute("priceMap",priceMap);
		model.addAttribute("suppliyMap",suppliyMap);
		model.addAttribute("numMap",numMap);
		//耗材列表
		List<LcjnSupplies> supplyList = new ArrayList<>();
		List<String> flowList = new ArrayList<>();
		Map<String,LcjnSkillCfgDetail> materialMap = new HashMap<>();
		Map<String,String> priceMap2 = new HashMap<>();
		if(courseMapList!=null&&courseMapList.size()>0){
			for(Map<String,Object> courseMap:courseMapList){
				String courseFlow = (String)courseMap.get("COURSE_FLOW");
				List<LcjnSkillCfgDetail> skillCfgDetails = costBiz.searchCfgByCourseFlow(courseFlow);
				if(skillCfgDetails!=null&&skillCfgDetails.size()>0){
					for(LcjnSkillCfgDetail skillCfgDetail:skillCfgDetails){
						String materiaFlow = skillCfgDetail.getMateriaFlow();
						if(StringUtil.isNotBlank(materiaFlow)){
							LcjnSupplies supply = costBiz.readSupply(materiaFlow);
							if(supply!=null){
								String price = supply.getSuppliesPrice();
								priceMap2.put(courseFlow+materiaFlow,price);
								if(!flowList.contains(supply.getSuppliesFlow())){
									flowList.add(supply.getSuppliesFlow());
									supplyList.add(supply);
								}
							}
							materialMap.put(courseFlow+materiaFlow,skillCfgDetail);
						}
					}
				}
			}
		}
		model.addAttribute("supplyList2",supplyList);
		model.addAttribute("materialMap",materialMap);
		model.addAttribute("priceMap2",priceMap2);
		//课程总数，人员总数，以及总费用
		List<Map<String,Object>> courseMapList2 = costBiz.searchCourseInfo(courseInfo,startTime,endTime);
		model.addAttribute("courseNum",courseMapList2.size());
		int peopleNum = 0;
		double costNum = 0.00;
		if(courseMapList2!=null&&courseMapList2.size()>0) {
			for (Map<String, Object> courseMap : courseMapList2) {
				String courseFlow = (String)courseMap.get("COURSE_FLOW");
				int num = costBiz.countNum(courseFlow);
				peopleNum+=num;//人数
				//费用
				List<LcjnCourseSupplies> courseSupplies = costBiz.searchSuppliesByCourseFlow(courseFlow);
				if(courseSupplies!=null&courseSupplies.size()>0){
					for(LcjnCourseSupplies courseSupplies1:courseSupplies){
						String useNum = courseSupplies1.getUseNum();
						String id = courseSupplies1.getDictId();
						List<LcjnSupplies> supplies = costBiz.searchById(id);
						if(supplies!=null&&supplies.size()>0) {
							costNum+=Double.parseDouble(StringUtil.defaultIfEmpty(supplies.get(0).getSuppliesPrice(), "0.00"))
									*Double.parseDouble(StringUtil.defaultIfEmpty(useNum, "0.00"))*(num==0?0:1);
						}
					}
				}
				List<LcjnSkillCfgDetail> skillCfgDetails = costBiz.searchCfgByCourseFlow(courseFlow);
				if(skillCfgDetails!=null&&skillCfgDetails.size()>0){
					for(LcjnSkillCfgDetail skillCfgDetail:skillCfgDetails){
						String useNum = skillCfgDetail.getUseNum();
						String materiaFlow = skillCfgDetail.getMateriaFlow();
						if(StringUtil.isNotBlank(materiaFlow)){
							LcjnSupplies supply = costBiz.readSupply(materiaFlow);
							String price = "";
							if(supply!=null){
								price = supply.getSuppliesPrice();
								costNum += Double.parseDouble(StringUtil.defaultIfEmpty(price, "0.00")) * Double.parseDouble(StringUtil.defaultIfEmpty(useNum, "0.00")) * num;
							}
						}
					}
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		model.addAttribute("costNum",df.format(costNum));
		model.addAttribute("peopleNum",peopleNum);
		return "lcjn/cost/courseCostList";
	}
}