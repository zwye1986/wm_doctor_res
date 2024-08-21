package com.pinde.sci.ctrl.sch;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/cfg")
public class SchCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(SchCfgController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResEnterOpenCfgBiz readResEnterOpenCfg;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private  ISchExamCfgBiz examCfgBiz;
	
	/**
	 * 轮转科室维护
	 * **/
	@RequestMapping(value = {"/deptcfg/{roleFlag}" }, method = RequestMethod.GET)
	public String deptcfg (@PathVariable String roleFlag,String orgFlow,Model model) throws Exception{
		model.addAttribute("roleFlag",roleFlag);
		setSessionAttribute("deptListScope",roleFlag);

		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);

		SysDept dept = new SysDept();
		dept.setOrgFlow(orgFlow);
//		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> deptList = deptBiz.searchDept(dept);
		if(deptList!=null && deptList.size()>0){
			model.addAttribute("deptList",deptList);
			Map<String,SysDept> deptMap = new HashMap<String, SysDept>();
			for(SysDept deptTemp : deptList){
				deptMap.put(deptTemp.getDeptFlow(),deptTemp);
			}
			model.addAttribute("deptMap",deptMap);
		}

		Map<String, List<SchDept>> schDeptCountMap = schDeptBiz.searchSchDeptMap(orgFlow);
		model.addAttribute("schDeptCountMap",schDeptCountMap);

		List<SchDeptRel> deptRelList = deptRelBiz.searchRelByOrg(orgFlow);
		if(deptRelList!=null && deptRelList.size()>0){
			Map<String,List<SchDeptRel>> deptRelMap = new HashMap<String, List<SchDeptRel>>();
			for(SchDeptRel deptRel : deptRelList){
				String key = deptRel.getSchDeptFlow();
				if(deptRelMap.get(key)==null){
					List<SchDeptRel> deptRelListTemp = new ArrayList<SchDeptRel>();
					deptRelListTemp.add(deptRel);
					deptRelMap.put(key, deptRelListTemp);
				}else{
					deptRelMap.get(key).add(deptRel);
				}
			}
			model.addAttribute("deptRelMap",deptRelMap);
		}

		List<SchAndStandardDeptCfg> cfgs=deptCfgBiz.getListByOrgFlow(orgFlow);
		if(cfgs!=null&&cfgs.size()>0)
		{
			Map<String,SchAndStandardDeptCfg> schStandarMap = new HashMap<>();
			for(SchAndStandardDeptCfg cfg:cfgs)
			{
				schStandarMap.put(cfg.getSchDeptFlow(),cfg);
			}
			model.addAttribute("schStandarMap",schStandarMap);
		}
		List<SchDeptExternalRel> deptExtRelList = deptExtRelBiz.searchSchDeptExtRel(orgFlow);
		if(deptExtRelList!=null && deptExtRelList.size()>0){
			Map<String,SchDeptExternalRel> deptExtRelMap = new HashMap<String, SchDeptExternalRel>();
			for(SchDeptExternalRel deptExtRel : deptExtRelList){
				deptExtRelMap.put(deptExtRel.getSchDeptFlow(),deptExtRel);
			}
			model.addAttribute("deptExtRelMap",deptExtRelMap);
		}
		return "sch/cfg/deptcfg";
	}

	/**
	 * 对轮转科室维护（/deptcfg/{roleFlag}）的优化功能
	 * @param roleFlag
	 * @param orgFlow
	 * @param model
	 * @param isRelCkkh 是否关联出科考核
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = {"/deptInfoCfg/{roleFlag}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String deptInfoCfg(@PathVariable String roleFlag, String orgFlow, Model model, Integer currentPage,
							  String isRelCkkh, SchDept schDept, HttpServletRequest request) throws Exception {
		model.addAttribute("roleFlag", roleFlag);

		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);

		schDept.setOrgFlow(orgFlow);
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dept",schDept);
		paramMap.put("isRelCkkh",isRelCkkh);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> deptList = deptBiz.searchDeptByExt(paramMap);
		if(deptList!=null && deptList.size()>0){
			model.addAttribute("deptList",deptList);
			Map<String,List<SchDeptRel>> schDeptRelMap = new HashMap<>();
			for(Map<String,String> deptTemp : deptList){
				String tempSchDeptFlow = deptTemp.get("schDeptFlow");
				if(StringUtil.isNotBlank(tempSchDeptFlow)){
					List<SchDeptRel> schDeptRels = deptRelBiz.searchRelBySchDept(tempSchDeptFlow);
					schDeptRelMap.put(tempSchDeptFlow,schDeptRels);
				}
				model.addAttribute("schDeptRelMap",schDeptRelMap);
			}
		}
		return "sch/cfg/deptInfoCfg";
	}


	@RequestMapping(value = "/searchDept")
	@ResponseBody
	public List<SysDept> searchDept(String orgFlow) {
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		SysDept searchDept = new SysDept();
		searchDept.setOrgFlow(orgFlow);
		searchDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> depts = deptBiz.searchDept(searchDept);
		return depts;
	}
	@RequestMapping(value = "/searchSchDept")
	@ResponseBody
	public List<SchDept> searchSchDept(String orgFlow) {
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		SchDept searchSchDept = new SchDept();
		searchSchDept.setOrgFlow(orgFlow);
		//searchSchDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SchDept> schDepts = schDeptBiz.searchSchDeptByExample(searchSchDept);
		return schDepts;
	}
	/**
	 * 加载报名时间配置信息
	 * @param model
	 * @return
     */
	@RequestMapping("/enterCfg")
	public String enterCfg(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		ResEnterOpenCfg resEnterOpenCfg = readResEnterOpenCfg.readResEnterOpenCfg(orgFlow);
		model.addAttribute("resEnterOpenCfg",resEnterOpenCfg);
		return "sch/cfg/enterCfg";
	}

	@RequestMapping("/saveEnterCfg")
	@ResponseBody
	public String saveEnterCfg(ResEnterOpenCfg enterOpenCfg){
		String orgFlow=enterOpenCfg.getOrgFlow();
		if(StringUtil.isBlank(enterOpenCfg.getOrgFlow()))
		{

			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
		enterOpenCfg.setOrgFlow(orgFlow);
		if(sysOrg!=null){
			enterOpenCfg.setOrgName(sysOrg.getOrgName());
		}
		ResEnterOpenCfg resEnterOpenCfg = readResEnterOpenCfg.readResEnterOpenCfg(orgFlow);
		if(resEnterOpenCfg!=null)
			enterOpenCfg.setCfgFlow(resEnterOpenCfg.getCfgFlow());
		int result = readResEnterOpenCfg.saveEnterOpenCfg(enterOpenCfg);
		if(result>GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/modifyCfg")
	public String modifyCfg(String schDeptFlow,String recordFlow,Model model){
		SchAndStandardDeptCfg cfg=deptCfgBiz.readByFlow(recordFlow);
		model.addAttribute("cfg",cfg);
		SchDept dept=schDeptBiz.readSchDept(schDeptFlow);
		model.addAttribute("dept",dept);
		List<SysDict> allDicts=new ArrayList<>();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.StandardDept.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
		if(sysDictList!=null&&sysDictList.size()>0) {
			allDicts.addAll(sysDictList);
			for (SysDict dict : sysDictList) {
				String typeId = dict.getDictTypeId() + "." + dict.getDictId();
				if (DictTypeEnum.StandardDept.getLevel() > 1) {
					sysDict.setDictTypeId(typeId);
					List<SysDict> sysDictSecondList = dictBiz.searchDictList(sysDict);
					if (sysDictSecondList != null && sysDictSecondList.size() > 0) {
						allDicts.addAll(sysDictSecondList);
						for (SysDict sDict : sysDictSecondList) {
							String tTypeId = typeId + "." + sDict.getDictId();
							sDict.setDictId(dict.getDictId()+"."+sDict.getDictId());
							sDict.setDictName(dict.getDictName()+"."+sDict.getDictName());
							if (DictTypeEnum.StandardDept.getLevel() > 2) {
								sysDict.setDictTypeId(tTypeId);
								List<SysDict> sysDictThirdList = dictBiz.searchDictList(sysDict);
								if (sysDictThirdList != null && sysDictThirdList.size() > 0) {
									allDicts.addAll(sysDictThirdList);
									for (SysDict tDict : sysDictThirdList) {
										tDict.setDictId(sDict.getDictId()+"."+tDict.getDictId());
										tDict.setDictName(sDict.getDictName()+"."+tDict.getDictName());
									}
								}
							}

						}
					}
				}
			}
		}
		model.addAttribute("allDicts",allDicts);
		return "sch/cfg/modifyCfg";
	}
	@RequestMapping(value = "/saveCfg")
	@ResponseBody
	public String saveCfg(String schDeptFlow,String recordFlow,String standardDeptId,Model model){
		SchAndStandardDeptCfg cfg=deptCfgBiz.readByFlow(recordFlow);
		if(cfg==null)
			cfg=new SchAndStandardDeptCfg();
		cfg.setStandardDeptId(standardDeptId);
		cfg.setStandardDeptName(DictTypeEnum.StandardDept.getDictNameById(standardDeptId));
		SchDept dept=schDeptBiz.readSchDept(schDeptFlow);
		cfg.setSchDeptFlow(schDeptFlow);
		cfg.setSchDeptName(dept.getSchDeptName());
		cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		SysOrg org=orgBiz.readSysOrg(dept.getOrgFlow());
		cfg.setOrgFlow(dept.getOrgFlow());
		cfg.setOrgName(org.getOrgName());
		int result = deptCfgBiz.save(cfg);
		if(result==0)
		{
			return  "保存失败！";
		}
		return "保存成功！";
	}
//	@RequestMapping(value = "/exportDept")
//	public void exportDept(String orgFlow,HttpServletResponse response, String deptFlow)throws Exception{
//		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
//		List<SchDept> datas=new ArrayList<>();
//		SysDept dept = new SysDept();
//		dept.setOrgFlow(orgFlow);
//		if(StringUtil.isNotBlank(deptFlow)){
//			dept.setDeptFlow(deptFlow);
//		}
//		//获取医院科室列表
//		List<SysDept> deptList = deptBiz.searchDept(dept);
//		if(deptList!=null && deptList.size()>0){
//			Map<String,SysDept> deptMap = new HashMap<String, SysDept>();
//			for(SysDept deptTemp : deptList){
//				deptMap.put(deptTemp.getDeptFlow(),deptTemp);
//			}
//		}
//
//		//获取轮转科室列表
//		Map<String, List<SchDept>> schDeptCountMap = schDeptBiz.searchSchDeptMap(orgFlow);
//
//		//获取标准科室列表
//		List<SchDeptRel> deptRelList = deptRelBiz.searchRelByOrg(orgFlow);
//		Map<String,List<SchDeptRel>> deptRelMap = new HashMap<String, List<SchDeptRel>>();
//		if(deptRelList!=null && deptRelList.size()>0){
//			for(SchDeptRel deptRel : deptRelList){
//				String key = deptRel.getSchDeptFlow();
//				if(deptRelMap.get(key)==null){
//					List<SchDeptRel> deptRelListTemp = new ArrayList<SchDeptRel>();
//					deptRelListTemp.add(deptRel);
//					deptRelMap.put(key, deptRelListTemp);
//				}else{
//					deptRelMap.get(key).add(deptRel);
//				}
//			}
//		}
//
//		//获取外院关联列表列表
//		List<SchDeptExternalRel> deptExtRelList = deptExtRelBiz.searchSchDeptExtRel(orgFlow);
//		Map<String,SchDeptExternalRel> deptExtRelMap = new HashMap<String, SchDeptExternalRel>();
//		if(deptExtRelList!=null && deptExtRelList.size()>0){
//			for(SchDeptExternalRel deptExtRel : deptExtRelList){
//				deptExtRelMap.put(deptExtRel.getSchDeptFlow(),deptExtRel);
//			}
//		}
//		String[] headLines = null;
//		headLines = new String[]{
//				"科室信息表"
//		};
//		//创建工作簿
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 为工作簿添加sheet
//		HSSFSheet sheet = wb.createSheet("sheet1");
//		//定义将用到的样式
//		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
//		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		styleCenter.setWrapText(true);
//		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
//		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		styleLeft.setWrapText(true);
//
//		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
//		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		styleLeft.setWrapText(true);
//		//列宽自适应
////		sheet.setDefaultColumnWidth((short)50);
//		HSSFRow rowDep = sheet.createRow(0);//第一行
//		rowDep.setHeightInPoints(20);
//		HSSFCell cellOne = rowDep.createCell(0);
//		cellOne.setCellStyle(styleCenter);
//		cellOne.setCellValue("科室信息表");
//
//		HSSFRow rowTwo = sheet.createRow(1);//第二行
//		String[] titles = new String[]{
//				"序号",
//				"医院科室",
//				"轮转科室",
//				"标准科室",
//				"容纳人数",
//				"是否对外"
//		};
//		HSSFCell cellTitle = null;
//		for (int i = 0; i < titles.length; i++) {
//			cellTitle = rowTwo.createCell(i);
//			cellTitle.setCellValue(titles[i]);
//			cellTitle.setCellStyle(styleCenter);
//			sheet.setColumnWidth(i, titles.length * 2 * 256);
//		}
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
//		//行数
//		int rowNum = 2;
//		//存放在excel中的行数据
//		String[] resultList = null;
//		//对当前机构医院科室循环
//		if (deptList != null && !deptList.isEmpty()) {
//			for (int i = 0; i < deptList.size(); i++) {
//				//根据医院科室的Flow取出轮转科室List
//				List<SchDept> tempSchDepts = schDeptCountMap.get(deptList.get(i).getDeptFlow());
//				//对相应的轮转科室循环
//				if(tempSchDepts != null && tempSchDepts.size()>0){
//					for(int j=0;j<tempSchDepts.size();j++, rowNum++){
//						HSSFRow rowFour = sheet.createRow(rowNum);//第二行
//						String deptName = "";
//						if(GlobalConstant.FLAG_Y.equals(tempSchDepts.get(j).getIsExternal())){
//							deptName = "*"+deptList.get(i).getDeptName()+"\n(外院名称："
//									 + deptExtRelMap.get(tempSchDepts.get(j).getSchDeptFlow()).getOrgName()
//									 + "\n医院科室：" +deptExtRelMap.get(tempSchDepts.get(j).getSchDeptFlow()).getRelDeptName()
//									 + "\n轮转科室：" +deptExtRelMap.get(tempSchDepts.get(j).getSchDeptFlow()).getRelSchDeptName()+
//									")";
//						}else {
//							deptName = deptList.get(i).getDeptName();
//						}
//						//标准科室名用逗号隔开
//						String standardDept = "";
//						//根据轮转科室的Flow取出标准科室List
//						List<SchDeptRel> tempSchDeptRel = deptRelMap.get(tempSchDepts.get(j).getSchDeptFlow());
//						//对相应的标准科室循环取出标准科室名
//						if(tempSchDeptRel != null&&tempSchDeptRel.size()>0){
//							for(SchDeptRel schrel:tempSchDeptRel){
//								standardDept += schrel.getStandardDeptName()+",";
//							}
//						}
//						String deptNum = tempSchDepts.get(j).getDeptNum() == null ? "0" :tempSchDepts.get(j).getDeptNum()+"";
//						String external = GlobalConstant.FLAG_Y.equals(tempSchDepts.get(j).getExternal()) ? "是":"否";
//						resultList = new String[]{
//								rowNum-1+"",
//								deptName,
//								tempSchDepts.get(j).getSchDeptName(),
//								standardDept,
//								deptNum,
//								external
//						};
//						for (int k = 0; k < titles.length; k++) {
//							HSSFCell cellFirst = rowFour.createCell(k);
//							cellFirst.setCellStyle(styleCenter);
//							cellFirst.setCellValue(new HSSFRichTextString(resultList[k]));
//						}
//					}
//				}
//
//			}
//		}
//		String fileName = "科室信息表.xls";
//		fileName = URLEncoder.encode(fileName, "UTF-8");
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//		response.setContentType("application/octet-stream;charset=UTF-8");
//		wb.write(response.getOutputStream());
//	}

	/**
	 * 导出科室信息重做
	 * @param orgFlow
	 * @param response
	 * @param schDept
	 * @param isRelCkkh
     * @throws Exception
     */
	@RequestMapping(value = "/exportDept")
	public void exportDept(String orgFlow,HttpServletResponse response, SchDept schDept,String isRelCkkh)throws Exception{
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		List<SchDept> datas=new ArrayList<>();

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dept",schDept);
		paramMap.put("isRelCkkh",isRelCkkh);
		List<Map<String,String>> deptList = deptBiz.searchDeptByExt(paramMap);
		Map<String,List<SchDeptRel>> schDeptRelMap = new HashMap<>();
		if(deptList!=null && deptList.size()>0){
			for(Map<String,String> deptTemp : deptList){
				String tempSchDeptFlow = deptTemp.get("schDeptFlow");
				if(StringUtil.isNotBlank(tempSchDeptFlow)){
					List<SchDeptRel> schDeptRels = deptRelBiz.searchRelBySchDept(tempSchDeptFlow);
					schDeptRelMap.put(tempSchDeptFlow,schDeptRels);
				}
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				"科室信息表"
		};
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleLeft.setWrapText(true);
		//列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("科室信息表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"序号",
				"实际轮转科室（二级）",
				"医院科室（一级）",
				"标准科室",
				"出科考核标准科室",
				"容纳人数",
				"对外开放"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		//行数
		int rowNum = 2;
		//存放在excel中的行数据
		String[] resultList = null;
		//对当前机构医院科室循环
		if (deptList != null && !deptList.isEmpty()) {
			for (int i = 0; i < deptList.size(); i++,rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);
				//根据医院科室的Flow取出轮转科室List
				List<SchDeptRel> tempSchDeptRels = schDeptRelMap.get(deptList.get(i).get("schDeptFlow"));
				//对相应的标准科室循环
				String standardDept = "";
				if(tempSchDeptRels != null&&tempSchDeptRels.size()>0){
					//取出标准科室名用逗号隔开
					for(SchDeptRel schrel:tempSchDeptRels){
						standardDept += schrel.getStandardDeptName()+",";
					}
					if(StringUtil.isNotBlank(standardDept)){
						standardDept.substring(0,standardDept.length()-2);
					}
				}
				String deptNum =deptList.get(i).get("deptNum");
				deptNum = deptNum == null ? "0" :deptNum+"";
				String external = GlobalConstant.FLAG_Y.equals(deptList.get(i).get("external")) ? "是":"否";
				resultList = new String[]{
						rowNum-1+"",
						deptList.get(i).get("schDeptName"),
						deptList.get(i).get("deptName"),
						standardDept,
						deptList.get(i).get("standardDeptName"),
						deptNum,
						external
				};
				for (int k = 0; k < titles.length; k++) {
					HSSFCell cellFirst = rowFour.createCell(k);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(new HSSFRichTextString(resultList[k]));
				}
			}
		}
		String fileName = "科室信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
	/**
	 * 科室导入
	 * @param model
	 * @param orgFlow
	 * @return
	 */
	@RequestMapping(value="/importDept")
	public String importUsers(Model model,String orgFlow){
		model.addAttribute("orgFlow",orgFlow);
		return "sch/cfg/importDept";
	}

	/**
	 * 科室导入
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importDeptFromExcel")
	@ResponseBody
	public String importDeptFromExcel(MultipartFile file,String orgFlow){
		if(file.getSize() > 0){
			try{
				int result = deptBiz.importDeptFromExcel(file,orgFlow);
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

	@RequestMapping(value = {"/deptList" }, method = RequestMethod.GET)
	public String deptList (String deptFlow,Model model) throws Exception{
		List<SchDept> schDeptList = schDeptBiz.searchSchDept(deptFlow);
		model.addAttribute("schDeptList",schDeptList);
		
		SysDept sysDept = deptBiz.readSysDept(deptFlow);
		model.addAttribute("sysDept",sysDept);
		
		return "sch/cfg/deptList";
	}
	
	/**
	 * 编辑部门
	 * **/
	@RequestMapping(value = {"/editDept" }, method = RequestMethod.GET)
	public String editDept (String schDeptFlow,String orgFlow,Model model) throws Exception{
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);

		SchDept schDept = new SchDept();
		if(StringUtil.isNotBlank(schDeptFlow)){
			schDept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("schDept",schDept);
			
			List<SchDeptRel> deptRelList = deptRelBiz.searchRelBySchDept(schDeptFlow);
			if(deptRelList!=null && deptRelList.size()>0){
				Map<String,SchDeptRel> deptRelMap = new HashMap<String, SchDeptRel>();
				for(SchDeptRel deptRel : deptRelList){
					deptRelMap.put(deptRel.getStandardDeptId(),deptRel);
				}
				model.addAttribute("deptRelMap",deptRelMap);
			}
			
			if(schDept!=null && GlobalConstant.FLAG_Y.equals(schDept.getIsExternal())){
				SchDeptExternalRel deptExtRel = deptExtRelBiz.readSchDeptExtRelBySchDept(schDeptFlow);
				model.addAttribute("deptExtRel",deptExtRel);
			}
		}
		
		if(StringUtil.isNotBlank(orgFlow)){
			SysDept sysdept = new SysDept();
			sysdept.setOrgFlow(orgFlow);
			List<SysDept> deptList = deptBiz.searchDept(sysdept);
			model.addAttribute("deptList",deptList);

			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			model.addAttribute("jointOrgList",jointOrgList);
		}

		if(StringUtil.isBlank(schDeptFlow) || null == schDept.getOrdinal()){
			int ordinal = schDeptBiz.searchMaxSchDeptOrdinal(orgFlow);
			model.addAttribute("ordinal",ordinal + 1);
		}

		return "sch/cfg/deptEdit";
	}
	
	@RequestMapping(value = "/saveDept",method={RequestMethod.POST})
	@ResponseBody
	public String saveDept(SchDept schDept, String[] standardDeptId, SchDeptExternalRel deptExtRel,String permissionAddSysDeptFlow) throws Exception {
		int result = 0;
		if(StringUtil.isNotBlank(permissionAddSysDeptFlow))
		{
			if (StringUtil.isBlank(schDept.getSchDeptFlow())) {
				//schDeptFlow为空则为新增
				SysDept permissionAddSysDept = deptBiz.readSysDept(permissionAddSysDeptFlow);

				String schDeptName = schDept.getSchDeptName();
				Boolean isSchDeptNameRepeat = false;
				//检查轮转科室名是否重复
				SchDept searchSchDept = new SchDept();
				searchSchDept.setSchDeptName(schDeptName);
				searchSchDept.setOrgFlow(schDept.getOrgFlow());
				searchSchDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SchDept> schDeptForNames = schDeptBiz.searchSchDeptByExample(searchSchDept);
				if (schDeptForNames != null && schDeptForNames.size() > 0) {
					isSchDeptNameRepeat = true;
				}
				//只要轮转科室重复---提示“轮转科室已存在，无法新增！”
				if (isSchDeptNameRepeat) {
					return "轮转科室已存在，无法新增！";
				}
				//医院科室重复，转科室不重复---新增轮转科室 (将库里的SysDept赋给SysDept)
				//医院科室不重复，轮转科室不重复---新增医院科室,新增轮转科室
				if (!isSchDeptNameRepeat) {
					schDept.setDeptFlow(permissionAddSysDept.getDeptFlow());
					schDept.setDeptName(permissionAddSysDept.getDeptName());
					result = saveDeptInfo(schDept, standardDeptId, deptExtRel);
				}
			} else {
				//schDeptFlow不为空则为编辑
				String schDeptFlow = schDept.getSchDeptFlow();
				SchDept oldeSchDept = schDeptBiz.readSchDept(schDeptFlow);
				SysDept permissionAddSysDept = deptBiz.readSysDept(permissionAddSysDeptFlow);
				String schDeptName = schDept.getSchDeptName();
				Boolean isSchDeptNameRepeat = false;
				//检查轮转科室名是否重复
				SchDept searchSchDept = new SchDept();
				searchSchDept.setSchDeptName(schDeptName);
				searchSchDept.setOrgFlow(schDept.getOrgFlow());
				List<SchDept> schDeptForNames = schDeptBiz.searchSchDeptByExample(searchSchDept);
				if (schDeptForNames != null && schDeptForNames.size() > 0) {
					SchDept tempSchDept = schDeptForNames.get(0);
					//如果查到重复名的轮转科室的流水号和编辑的该条记录的流水号不等则库中存在重复的轮转科室
					if (!tempSchDept.getSchDeptFlow().equals(oldeSchDept.getSchDeptFlow())) {
						isSchDeptNameRepeat = true;
					}
				}
				//只要轮转科室重复---提示“轮转科室已存在，无法编辑！”
				if (isSchDeptNameRepeat) {
					return "轮转科室已存在，无法编辑！";
				}
				//更新SchDept
				schDept.setDeptFlow(permissionAddSysDept.getDeptFlow());
				schDept.setDeptName(permissionAddSysDept.getDeptName());
				result = saveDeptInfo(schDept, standardDeptId, deptExtRel);
			}
			if (result != GlobalConstant.ZERO_LINE) {
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}else {//允许新增sys_dept
			if (null != schDept) {
				if (StringUtil.isBlank(schDept.getSchDeptFlow())) {
					//schDeptFlow为空则为新增
					String deptName = schDept.getDeptName();
					String schDeptName = schDept.getSchDeptName();
					Boolean isDeptNameRepeat = false;
					Boolean isSchDeptNameRepeat = false;
					//检查医院科室名是否重复
					List<SysDept> deptForNames = deptBiz.searchDeptByNameAndFlow(deptName, schDept.getOrgFlow());
					SysDept sysDept = new SysDept();
					if (deptForNames != null && deptForNames.size() > 0) {
						//医院科室重复将库里的SysDept赋给SysDept
						isDeptNameRepeat = true;
						sysDept = deptForNames.get(0);
					}
					sysDept.setDeptName(deptName);
					//检查轮转科室名是否重复
					SchDept searchSchDept = new SchDept();
					searchSchDept.setSchDeptName(schDeptName);
					searchSchDept.setOrgFlow(schDept.getOrgFlow());
					searchSchDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					List<SchDept> schDeptForNames = schDeptBiz.searchSchDeptByExample(searchSchDept);
					if (schDeptForNames != null && schDeptForNames.size() > 0) {
						isSchDeptNameRepeat = true;
					}
					//只要轮转科室重复---提示“轮转科室已存在，无法新增！”
					if (isSchDeptNameRepeat) {
						return "轮转科室已存在，无法新增！";
					}
					//医院科室重复，转科室不重复---新增轮转科室 (将库里的SysDept赋给SysDept)
					//医院科室不重复，轮转科室不重复---新增医院科室,新增轮转科室
					if ((isDeptNameRepeat && !isSchDeptNameRepeat)
							|| (!isDeptNameRepeat && !isSchDeptNameRepeat)) {
						result = saveDeptInfo(schDept, sysDept, standardDeptId, deptExtRel);
					}
				} else {
					//schDeptFlow不为空则为编辑
					String schDeptFlow = schDept.getSchDeptFlow();
					SchDept oldeSchDept = schDeptBiz.readSchDept(schDeptFlow);
					String deptName = schDept.getDeptName();
					String schDeptName = schDept.getSchDeptName();
					Boolean isDeptNameRepeat = false;
					Boolean isSchDeptNameRepeat = false;
					//检查医院科室名是否重复
					List<SysDept> deptForNames = deptBiz.searchDeptByNameAndFlow(deptName, schDept.getOrgFlow());
					SysDept sysDept = new SysDept();
					if (deptForNames != null && deptForNames.size() > 0) {
						SysDept tempSysDept = deptForNames.get(0);
						sysDept = tempSysDept;
						//如果查到重复名的医院科室的流水号和编辑的该条记录的流水号不等则库中存在重复的医院科室
						if (!tempSysDept.getDeptFlow().equals(oldeSchDept.getDeptFlow())) {
							isDeptNameRepeat = true;
						}
					}
					sysDept.setDeptName(deptName);
					//检查轮转科室名是否重复
					SchDept searchSchDept = new SchDept();
					searchSchDept.setSchDeptName(schDeptName);
					searchSchDept.setOrgFlow(schDept.getOrgFlow());
					List<SchDept> schDeptForNames = schDeptBiz.searchSchDeptByExample(searchSchDept);
					if (schDeptForNames != null && schDeptForNames.size() > 0) {
						SchDept tempSchDept = schDeptForNames.get(0);
						//如果查到重复名的轮转科室的流水号和编辑的该条记录的流水号不等则库中存在重复的轮转科室
						if (!tempSchDept.getSchDeptFlow().equals(oldeSchDept.getSchDeptFlow())) {
							isSchDeptNameRepeat = true;
						}
					}
					//只要轮转科室重复---提示“轮转科室已存在，无法编辑！”
					if (isSchDeptNameRepeat) {
						return "轮转科室已存在，无法编辑！";
					}
					//只要医院科室重复---提示“医院科室已存在，无法编辑！”
					if (isDeptNameRepeat) {
						return "医院科室已存在，无法编辑！";
					}
					//更新SysDept和SchDept
					result = saveDeptInfo(schDept, sysDept, standardDeptId, deptExtRel);
				}

				if (result != GlobalConstant.ZERO_LINE) {
					return GlobalConstant.SAVE_SUCCESSED;
				}
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	private int saveDeptInfo(SchDept schDept, String[] standardDeptId, SchDeptExternalRel deptExtRel) {
		int result = 0;
		String orgFlow = schDept.getOrgFlow();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		schDept.setOrgFlow(org.getOrgFlow());
		schDept.setOrgName(org.getOrgName());
		result = schDeptBiz.saveSchDeptAndRed(schDept, standardDeptId);
		return result;
	}

	private int saveDeptInfo(SchDept schDept,SysDept sysDept, String[] standardDeptId, SchDeptExternalRel deptExtRel) {
		int result = 0;
		String orgFlow = schDept.getOrgFlow();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		schDept.setOrgFlow(org.getOrgFlow());
		schDept.setOrgName(org.getOrgName());

		String deptFlow = sysDept.getDeptFlow();
		SysDept tempDept = null;
		if(StringUtil.isNotBlank(deptFlow)){
			tempDept = deptBiz.readSysDept(deptFlow);
		}else {
			tempDept = new SysDept();
			tempDept.setOrgFlow(org.getOrgFlow());
		}

		tempDept.setDeptName(sysDept.getDeptName());
		deptBiz.saveDept(tempDept);
		schDept.setDeptFlow(tempDept.getDeptFlow());
		result = schDeptBiz.saveSchDeptAndRed(schDept, standardDeptId);
//			if (GlobalConstant.FLAG_Y.equals(schDept.getIsExternal())) {
//				result = schDeptBiz.saveSchDeptAndRedAndExtRel(schDept, standardDeptId, deptExtRel);
//			} else {
//				result = schDeptBiz.saveSchDeptAndRed(schDept, standardDeptId);
//			}
		return result;
	}
	
	@RequestMapping(value = "/delDept",method={RequestMethod.POST})
	@ResponseBody
	public String delDept(SchDept dept,Model model) throws Exception{
		if(null != dept){
			//dept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = schDeptBiz.delSchDept(dept);
			if(result != GlobalConstant.ZERO_LINE){
				//删除关联记录
				examCfgBiz.deleteSchExamStandardDeptByDeptId(dept.getSchDeptFlow());
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	@RequestMapping(value = "/searchDeptListByOrg",method={RequestMethod.POST})
	@ResponseBody
	public List<SysDept> searchDeptListByOrg(String orgFlow){
		List<SysDept> deptList = new ArrayList<>();
		if(StringUtil.isNotBlank(orgFlow)){
			deptList = deptBiz.searchDeptByOrg(orgFlow);
		}
		return deptList;
	}
	
	@RequestMapping(value = "/searchSchDeptListByDept",method={RequestMethod.POST})
	@ResponseBody
	public List<SchDept> searchSchDeptListByDept(String deptFlow){
		List<SchDept> schDeptList = new ArrayList<>();
		if(StringUtil.isNotBlank(deptFlow)){
			schDeptList = schDeptBiz.searchSchExternalDeptListByDept(deptFlow);
		}
		return schDeptList;
	}
	
	/**
	 *  标准科室与轮转科室关系页面
	 * */
	@RequestMapping(value = {"/schDeptRelCfg" },method = RequestMethod.GET)
	public String schDeptRelCfg(String orgFlow,Model model){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if(StringUtil.isNotBlank(orgFlow)){
			List<SchDept> deptList = schDeptBiz.searchSchDeptList(orgFlow);
			model.addAttribute("deptList",deptList);
			
			List<SchDeptRel> deptRelList = deptRelBiz.searchRelByOrg(orgFlow);
			if(deptRelList!=null && deptRelList.size()>0){
				Map<String,Map<String,String>> deptRelMap = new HashMap<String,Map<String,String>>();
				for(SchDeptRel deptRel : deptRelList){
					String key = deptRel.getSchDeptFlow();
					if(deptRelMap.get(key)==null){
						Map<String,String> deptRelTempMap = new HashMap<String, String>();
						deptRelTempMap.put(deptRel.getStandardDeptId(),deptRel.getStandardDeptName());
						deptRelMap.put(key,deptRelTempMap);
					}else{
						deptRelMap.get(key).put(deptRel.getStandardDeptId(),deptRel.getStandardDeptName());
					}
				}
				model.addAttribute("deptRelMap",deptRelMap);
			}
		}
		return "sch/cfg/schDeptRelCfg";
	}
	
	/**
	 *  加载已关联科室
	 * */
	@RequestMapping(value = "/loadRelDept",method={RequestMethod.POST})
	@ResponseBody
	public List<SchDeptRel> loadRelDept(String schDeptFlow){
		List<SchDeptRel> deptRelList = null;
		if(StringUtil.isNotBlank(schDeptFlow)){
			deptRelList = deptRelBiz.searchRelBySchDept(schDeptFlow);
		}
		return deptRelList;
	}
	
	/**
	 *  保存关系
	 * */
	@RequestMapping(value = "/saveDeptRel",method={RequestMethod.POST})
	@ResponseBody
	public String saveDeptRel(SchDeptRel deptRel){
		if(deptRel!=null){
			String orgFlow = StringUtil.defaultIfEmpty(deptRel.getOrgFlow(),GlobalContext.getCurrentUser().getOrgFlow());
			if(StringUtil.isNotBlank(orgFlow)){
				SysOrg org = orgBiz.readSysOrg(orgFlow);
				if(org!=null){
					deptRel.setOrgFlow(orgFlow);
					deptRel.setOrgName(org.getOrgName());
				}
			}
			
			if(StringUtil.isNotBlank(deptRel.getSchDeptFlow())){
				SchDept dept = schDeptBiz.readSchDept(deptRel.getSchDeptFlow());
				if(dept!=null){
					deptRel.setSchDeptName(dept.getSchDeptName());
					deptRel.setDeptFlow(dept.getDeptFlow());
					deptRel.setDeptName(dept.getDeptName());
				}
			}
			if(StringUtil.isNotBlank(deptRel.getStandardDeptId())){
				deptRel.setStandardDeptName(DictTypeEnum.StandardDept.getDictNameById(deptRel.getStandardDeptId()));
			}
		}
		if(!StringUtil.isNotBlank(deptRel.getStandardDeptName()) && !GlobalConstant.RECORD_STATUS_N.equals(deptRel.getRecordStatus())){
			return GlobalConstant.FLAG_N;
		}
		deptRelBiz.editDeptRel(deptRel);
		return deptRel.getRecordFlow();
	}
	
	/**
	 *  部门与轮转科室1:1映射
	 * */
	@RequestMapping(value = "/mapTheDept",method={RequestMethod.POST})
	@ResponseBody
	public String mapTheDept(String orgFlow,String[] deptFlow){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if(deptFlow!=null && deptFlow.length>0){
			List<String> deptFlows = new ArrayList<String>();
			for(String flow : deptFlow){
				deptFlows.add(flow);
			}
			int result = schDeptBiz.mapDeptRel(orgFlow,deptFlows);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	@RequestMapping(value = "/getSchInUsedCount",method={RequestMethod.GET})
	@ResponseBody
	public int getSchInUsedCount(String schDeptFlow,String deptFlow){
		int result = 0;
		if(StringUtil.isNotBlank(schDeptFlow) || StringUtil.isNotBlank(deptFlow)){
			result = schRotationDeptBiz.getSchInUsedCount(schDeptFlow,deptFlow);
		}
		return result;
	}
}

