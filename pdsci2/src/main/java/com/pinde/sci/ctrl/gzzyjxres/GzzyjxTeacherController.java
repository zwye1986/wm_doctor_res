package com.pinde.sci.ctrl.gzzyjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.gzzyjxres.ExportStuUserInfo;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.StuUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gzzyjxres/teacher")
public class GzzyjxTeacherController extends GeneralController{

	@Autowired
	private IStuBatchBiz stuBatchBiz;
	@Autowired
	private IStuUserResumeBiz stuUserResumeBiz;

	@RequestMapping("/home")
	public String home( Model model){
		return "gzzyjxres/teacher/index";
	}

	@RequestMapping("/changeTea")
	public String changeTea(Integer currentPage ,String batchFlow,String userName,String teaName,String isSecretart,String isQuery, Model model,HttpServletRequest request){
		List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
		model.addAttribute("batchLst",batchLst);
		if(StringUtil.isBlank(batchFlow)&&StringUtil.isBlank(isQuery)){
			if(null != batchLst && batchLst.size() > 0){
				for (StuBatch obj : batchLst) {
					if("Y".equals(obj.getIsDefault())){//系统默认批次设置为"Y"，反之为"N"，不存在null
						batchFlow = obj.getBatchFlow();//当前系统默认 进修批次流水号
						break;
					}
				}
			}
		}
		SysUser user = GlobalContext.getCurrentUser();
		model.addAttribute("batchFlow",batchFlow);
		model.addAttribute("deptFlow" , user.getDeptFlow());//批次
		Map<String , Object> parMp = new HashMap<String, Object>();
		parMp.put("deptFlow" , user.getDeptFlow());//批次
		parMp.put("isSecretart" , isSecretart);//批次
		parMp.put("batchFlow" , batchFlow);//批次
		parMp.put("userName", userName);//用户名
		parMp.put("teaName",teaName);//进修专业
		if(currentPage==null)
			currentPage=1;
		PageHelper.startPage(currentPage,getPageSize(request));
		List<StuUserExt> stuUserLst = stuUserResumeBiz.teaQueryStuList(parMp);
		model.addAttribute("list",stuUserLst);
		return "gzzyjxres/secretaries/list";
	}

	/**
	 *
	 */
	@RequestMapping("/exportQueryStu")
	public void exportQueryStu(HttpServletResponse response ,String batchFlow,String userName,String isQuery,String teaName,String isSecretart ) throws Exception {

		if(StringUtil.isBlank(batchFlow)&&StringUtil.isBlank(isQuery)){
			List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
			if(null != batchLst && batchLst.size() > 0){
				for (StuBatch obj : batchLst) {
					if("Y".equals(obj.getIsDefault())){//系统默认批次设置为"Y"，反之为"N"，不存在null
						batchFlow = obj.getBatchFlow();//当前系统默认 进修批次流水号
						break;
					}
				}
			}
		}
		SysUser user = GlobalContext.getCurrentUser();
		Map<String , Object> parMp = new HashMap<String, Object>();
		parMp.put("deptFlow" , user.getDeptFlow());//批次
		parMp.put("isSecretart" , isSecretart);//批次
		parMp.put("batchFlow" , batchFlow);//批次
		parMp.put("userName", userName);//用户名
		parMp.put("teaName",teaName);//进修专业
		List<StuUserExt> stuUserLst = stuUserResumeBiz.teaQueryStuList(parMp);
		String fileName = "学员名单.xls";
		List<ExportStuUserInfo> dataList = new ArrayList<ExportStuUserInfo>();
		ExportStuUserInfo eui = null;
		for(StuUserExt ext:stuUserLst){
			eui = new ExportStuUserInfo();
			eui.setUserName(ext.getSysUser().getUserName());
			eui.setIdNo(ext.getSysUser().getIdNo());
			eui.setSchoolSpeName(ext.getSchoolSpeName());
			eui.setStuTimeName(ext.getStuTimeName());
			eui.setSpeName(ext.getSpeName());
			eui.setBatchRegDate(ext.getStuBatName());
			eui.setTeacherName(ext.getTeacherName());
			eui.setIsGraduation(StringUtil.isNotBlank(ext.getTeacherFlow())?"已分配":"未分配");
			dataList.add(eui);
		}
		String[] titles =  new String[]{
					"userName:姓名",
					"idNo:身份证号",
					"schoolSpeName:毕业专业",
					"stuTimeName:进修时间",
					"speName:进修专业",
					"batchRegDate:进修批次",
					"teacherName:带教老师",
					"isGraduation:状态"
			};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcle(titles, dataList, ExportStuUserInfo.class, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
}
