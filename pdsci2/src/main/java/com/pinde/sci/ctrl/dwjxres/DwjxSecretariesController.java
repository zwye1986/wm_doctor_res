package com.pinde.sci.ctrl.dwjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
import com.pinde.sci.biz.dwjxres.IStuDoctorInfoBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.dwjxres.StuRoleEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.form.dwjxres.ExportStuUserInfo;
import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.SingUpForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dwjxres/secretaries")
public class DwjxSecretariesController extends GeneralController{

	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IStuBatchBiz stuBatchBiz;
	@Autowired
	private IStuUserResumeBiz stuUserResumeBiz;
	@Autowired
	private IStuDoctorInfoBiz stuDoctorInfoBiz;

	@RequestMapping("/home")
	public String home( Model model){

		return "dwjxres/secretaries/index";
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
		//一个科秘可以分配多个科室
		StuDeptOfStaff stuDeptOfStaff4search = new StuDeptOfStaff();
		stuDeptOfStaff4search.setUserFlow(user.getUserFlow());
		stuDeptOfStaff4search.setUserRole(StuRoleEnum.Secretary.getId());
		List<StuDeptOfStaff> stuDeptOfStaffs = stuDoctorInfoBiz.searchIfDeptHasSecretary(stuDeptOfStaff4search);
		List<String> deptFlows = new ArrayList<>();
		if (stuDeptOfStaffs != null && stuDeptOfStaffs.size() > 0) {
			for (StuDeptOfStaff tempDept : stuDeptOfStaffs) {
				deptFlows.add(tempDept.getDeptFlow());
			}
		}
		parMp.put("deptFlows" , deptFlows);//批次
		parMp.put("isSecretart" , isSecretart);//批次
		parMp.put("batchFlow" , batchFlow);//批次
		parMp.put("userName", userName);//用户名
		parMp.put("teaName",teaName);//进修专业
		if(currentPage==null)
			currentPage=1;
		PageHelper.startPage(currentPage,getPageSize(request));
		List<StuUserExt> stuUserLst = stuUserResumeBiz.teaQueryStuList(parMp);
		model.addAttribute("list",stuUserLst);
		return "dwjxres/secretaries/list";
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
		//一个科秘可以分配多个科室
		StuDeptOfStaff stuDeptOfStaff4search = new StuDeptOfStaff();
		stuDeptOfStaff4search.setUserFlow(user.getUserFlow());
		stuDeptOfStaff4search.setUserRole(StuRoleEnum.Secretary.getId());
		List<StuDeptOfStaff> stuDeptOfStaffs = stuDoctorInfoBiz.searchIfDeptHasSecretary(stuDeptOfStaff4search);
		List<String> deptFlows = new ArrayList<>();
		if (stuDeptOfStaffs != null && stuDeptOfStaffs.size() > 0) {
			for (StuDeptOfStaff tempDept : stuDeptOfStaffs) {
				deptFlows.add(tempDept.getDeptFlow());
			}
		}
		parMp.put("deptFlows" , deptFlows);//批次
		parMp.put("isSecretart" , isSecretart);//批次
		parMp.put("batchFlow" , batchFlow);//批次
		parMp.put("userName", userName);//用户名
		parMp.put("teaName",teaName);//进修专业
		List<StuUserExt> stuUserExts = stuUserResumeBiz.teaQueryStuList(parMp);
		List<StuUserExt> stuUserLst = stuUserExts;
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
