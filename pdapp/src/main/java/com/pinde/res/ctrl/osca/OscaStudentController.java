package com.pinde.res.ctrl.osca;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.CertificateTypeEnum;
import com.pinde.core.common.enums.UserSexEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IJsResBaseBiz;
import com.pinde.res.biz.osca.*;
import com.pinde.core.common.enums.osca.*;
import com.pinde.core.model.OscaSkillRoomExt;
import com.pinde.res.model.osca.mo.OscaSkillsAssessmentExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/osca/student")
public class OscaStudentController {
	private static Logger logger = LoggerFactory.getLogger(OscaStudentController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);

		return "res/osca/500";
    }
	@Autowired
	private IOscaAppBiz oscaAppBiz;
	@Autowired
	private IOscaStudentBiz oscaStudentBiz;

	@Autowired
	private IOscaBaseBiz baseBiz;
	@Autowired
	private IOscaDoctorOrderdeBiz oscaDoctorOrderdeBiz;
	@Autowired
	private IOscaDoctorScoreBiz oscaDoctorScoreBiz;
	@Autowired
	private IJsResBaseBiz resbaseBiz;
	@Autowired
	private IOscaDoctorRegistBiz oscaDoctorRegistBiz;
	@Autowired
	private IFileBiz fileBiz;



	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "/res/osca/student/version";
	}
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "/res/osca/student/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String doctorFlow,String stationFlow,String clinicalFlow , String roleId ,
						   String scoreFlow , String fromFlow ,
						   String codeInfo , HttpServletRequest request){
		HttpSession session = request.getSession();

		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("stationFlow", stationFlow);
		session.setAttribute("clinicalFlow", clinicalFlow);
		session.setAttribute("roleId", roleId);
		session.setAttribute("scoreFlow", scoreFlow);
		session.setAttribute("fromFlow", fromFlow);
		session.setAttribute("codeInfo", codeInfo);
		return "/res/osca/student/test";
	}

	@RequestMapping(value={"/regiest"},method={RequestMethod.POST})
	public String regiest( HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		String doctorRole = oscaAppBiz.getCfgCode("osca_doctor_role_flow");
		if(StringUtil.isBlank(doctorRole))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", "请联系系统管理员，配置学员角色");
			return "/res/osca/student/regiest";
		}
		model.addAttribute("types", CertificateTypeEnum.values());
		return "/res/osca/student/regiest";
	}
	@RequestMapping(value={"/saveRegister"},method={RequestMethod.POST})
	public synchronized String saveRegister( SysUser registerUser ,String reUserPasswd,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		String doctorRole = oscaAppBiz.getCfgCode("osca_doctor_role_flow");
		if(StringUtil.isBlank(doctorRole))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", "请联系系统管理员，配置学员角色");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(registerUser.getUserEmail()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入邮箱地址！");
			return "/res/osca/student/saveRegister";
		}
		boolean f = registerUser.getUserEmail().matches("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入正确的邮箱地址！");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(registerUser.getUserPhone()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入手机号！");
			return "/res/osca/student/saveRegister";
		}
		f = registerUser.getUserPhone().matches("^[1][345789]\\d{9}$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入正确的手机号！");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(registerUser.getUserPasswd()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入密码！");
			return "/res/osca/student/saveRegister";
		}
		f = registerUser.getUserPasswd().matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{6,20}$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "密码必须包括数字、字母，长度6－20！");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(reUserPasswd))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入确认密码！");
			return "/res/osca/student/saveRegister";
		}
		if(!reUserPasswd.equals(registerUser.getUserPasswd()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "两次密码输入不一致！");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(registerUser.getCretTypeId()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择证件类型！");
			return "/res/osca/student/saveRegister";
		}
		try{
			String cretTypeName=CertificateTypeEnum.getNameById(registerUser.getCretTypeId());
			registerUser.setCretTypeName(cretTypeName);
		}catch (Exception e)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择正确的证件类型！");
			return "/res/osca/student/saveRegister";
		}
		if(StringUtil.isBlank(registerUser.getIdNo()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入证件号码！");
			return "/res/osca/student/saveRegister";
		}
		if(registerUser.getIdNo().length()>30)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "证件号码的长度不得超过30个字符！");
			return "/res/osca/student/saveRegister";
		}
		//是否已注册
		String userEmail = registerUser.getUserEmail().trim();
		SysUser user = oscaAppBiz.findByUserEmail(userEmail);
		if(user != null){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该电子邮箱已经被注册！");
			return "/res/osca/student/saveRegister";
		}
		user = oscaAppBiz.findByUserCode(userEmail);
		if(user != null){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该电子邮箱已经被注册！");
			return "/res/osca/student/saveRegister";
		}
		//判断用户身份证号是否重复
		user = oscaAppBiz.findByIdNo(registerUser.getIdNo());
		if (user != null) {
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该证件号码已经被注册！");
			return "/res/osca/student/saveRegister";
		}
		//判断用户手机号是否重复
		user = oscaAppBiz.findByUserPhone(registerUser.getUserPhone());
		if (user != null) {
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该手机号已经被注册！");
			return "/res/osca/student/saveRegister";
		}
		int c =oscaAppBiz.saveOsceRegistUser(registerUser);
		if(c==0)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "注册失败！");
			return "/res/osca/student/saveRegister";
		}
		model.addAttribute("user",registerUser);
		return "/res/osca/student/saveRegister";
	}

	//base64字符串转化成图片
	public static boolean GenerateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) //图像数据为空
			return false;
		try {
			String newDir = savePath.substring(0,savePath.lastIndexOf("/"));
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			BASE64Decoder decoder = new BASE64Decoder();
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片//新生成的图片
			File imageFile = new File(savePath);
			OutputStream out = new FileOutputStream(imageFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@RequestMapping(value={"/saveCompleteInfo"},method={RequestMethod.POST})
	public String saveCompleteInfo( SysUser sysUser,ResDoctor doctor ,String userImg,String isNew,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(sysUser.getUserFlow()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "用户标识符为空！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(isNew))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "图片标识符为空！");
			return "/res/osca/success";
		}
		String doctorRole = oscaAppBiz.getCfgCode("osca_doctor_role_flow");
		if(StringUtil.isBlank(doctorRole))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", "请联系系统管理员，配置学员角色");
			return "/res/osca/success";
		}
		String baseDir=oscaAppBiz.getCfgCode("upload_base_dir");
		if(StringUtil.isBlank(baseDir))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系管理员，设置上传图片路径！");
			return "/res/osca/success";
		}
        List<SysUserRole> userRoles = oscaAppBiz.getSysUserRole(sysUser.getUserFlow(), com.pinde.core.common.GlobalConstant.OSCA_WS_ID);
		if(userRoles==null || userRoles.isEmpty()){
			model.addAttribute("resultId", "3010106");
			model.addAttribute("resultType", "用户未赋权，无权限使用！");
			return "/res/osca/success";
		}
		//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
		boolean isDoctor = false;
		for(SysUserRole sur : userRoles){
			String ur = sur.getRoleFlow();
			if(doctorRole.equals(ur)){
				isDoctor = true;
				break;
			}
		}
		if(!isDoctor)
		{
			model.addAttribute("resultId", "3010106");
			model.addAttribute("resultType", "用户不是学员，无权限使用！");
			return "/res/osca/success";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isNew)) {
			if (StringUtil.isBlank(userImg)) {
				model.addAttribute("resultId", "010000");
				model.addAttribute("resultType", "请上传头像图片");
				return "/res/osca/success";
			}
			String dateString = DateUtil.getCurrDate2();
			String imageUrl = File.separator + "userImages" + File.separator + dateString;

			String siginImageName = PkUtil.getUUID() + ".png";
			String url = "/userImages/" + dateString + "/" + siginImageName;
			sysUser.setUserHeadImg(url);
			boolean imgFlag = GenerateImage(userImg, baseDir + imageUrl + "/" + siginImageName);
			if (!imgFlag) {
				model.addAttribute("resultId", "010101");
				model.addAttribute("resultType", "头像图片格式错误！");
				return "/res/osca/success";
			}
		}
		if(StringUtil.isBlank(sysUser.getUserName()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入姓名！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(sysUser.getSexId()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择性别！");
			return "/res/osca/success";
		}
		if(!StringUtil.isEquals(sysUser.getSexId(),"Man")&&!StringUtil.isEquals(sysUser.getSexId(),"Woman"))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "性别只能是男或女！");
			return "/res/osca/success";
		}
		String sexName = UserSexEnum.getNameById(sysUser.getSexId());
		sysUser.setSexName(sexName);


		if(StringUtil.isBlank(sysUser.getCretTypeId()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择证件类型！");
			return "/res/osca/success";
		}
		try{
			String cretTypeName=CertificateTypeEnum.getNameById(sysUser.getCretTypeId());
			sysUser.setCretTypeName(cretTypeName);
		}catch (Exception e)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择正确的证件类型！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(sysUser.getIdNo()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入证件号码！");
			return "/res/osca/success";
		}
		if(sysUser.getIdNo().length()>30)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "证件号码的长度不得超过30个字符！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(sysUser.getUserEmail()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入邮箱地址！");
			return "/res/osca/success";
		}
		boolean f = sysUser.getUserEmail().matches("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入正确的邮箱地址！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(sysUser.getUserPhone()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入手机号！");
			return "/res/osca/success";
		}
		f = sysUser.getUserPhone().matches("^[1][345789]\\d{9}$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入正确的手机号！");
			return "/res/osca/success";
		}

		//是否已注册
		String userEmail = sysUser.getUserEmail().trim();
		SysUser user = oscaAppBiz.findByUserEmail(userEmail);
		if(user != null&&!user.getUserFlow().equals(sysUser.getUserFlow())){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该电子邮箱已经被注册！");
			return "/res/osca/success";
		}
		user = oscaAppBiz.findByUserCode(userEmail);
		if(user != null&&!user.getUserFlow().equals(sysUser.getUserFlow())){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该电子邮箱已经被注册！");
			return "/res/osca/success";
		}
		//判断用户身份证号是否重复
		user = oscaAppBiz.findByIdNo(sysUser.getIdNo());
		if (user != null&&!user.getUserFlow().equals(sysUser.getUserFlow())){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该证件号码已经被注册！");
			return "/res/osca/success";
		}
		//判断用户手机号是否重复
		user = oscaAppBiz.findByUserPhone(sysUser.getUserPhone());
		if (user != null&&!user.getUserFlow().equals(sysUser.getUserFlow())){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该手机号已经被注册！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(doctor.getTrainingTypeId()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择培训类型！");
			return "/res/osca/success";
		}

        List<SysDict> dicts = oscaAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId());
		List<SysDict> dicts2=null;
		if(dicts==null||dicts.size()<=0)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请联系系统管理员，维护培训类型！");
			return "/res/osca/success";
		}
		for(SysDict dict:dicts)
		{
			if(dict.getDictId().equals(doctor.getTrainingTypeId()))
			{
				doctor.setTrainingTypeName(dict.getDictName());
                dicts2 = oscaAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + dict.getDictId());
				break;
			}
		}
		if(StringUtil.isBlank(doctor.getTrainingTypeName()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "培训类型与系统字典不匹配！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(doctor.getTrainingSpeId()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择培训专业！");
			return "/res/osca/success";
		}
		if(dicts2==null||dicts2.size()<=0)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请联系系统管理员，维护培训专业！");
			return "/res/osca/success";
		}
		for(SysDict dict:dicts2)
		{
			if(dict.getDictId().equals(doctor.getTrainingSpeId()))
			{
				doctor.setTrainingSpeName(dict.getDictName());
				break;
			}
		}
		if(StringUtil.isBlank(doctor.getTrainingSpeName()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "培训专业与系统字典不匹配！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(doctor.getSessionNumber()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择年级！");
			return "/res/osca/success";
		}
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
		try
		{
			dateFormat2.parse(doctor.getSessionNumber());
		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "年级格式错误");
			return "/res/osca/success";
		}

		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgTypeId("Hospital");
		List<SysOrg> orgList = oscaAppBiz.queryAllSysOrg(searchOrg);
		if(orgList==null||orgList.size()<=0)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请联系系统管理员，维护培训基地！");
			return "/res/osca/success";
		}
		for(SysOrg org:orgList)
		{
			if(org.getOrgFlow().equals(sysUser.getOrgFlow()))
			{
				sysUser.setOrgName(org.getOrgName());
				break;
			}
		}
		if(StringUtil.isBlank(sysUser.getOrgName()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "培训基地与系统字典不匹配！");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(doctor.getTrainingYears()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择培养年限！");
			return "/res/osca/success";
		}
		Map<String,Integer> yearMap=new HashMap<>();
		yearMap.put("OneYear",1);
		yearMap.put("TwoYear",2);
		yearMap.put("ThreeYear",3);
		if(!StringUtil.isEquals(doctor.getTrainingYears(),"OneYear")&&
				!StringUtil.isEquals(doctor.getTrainingYears(),"TwoYear")&&
				!StringUtil.isEquals(doctor.getTrainingYears(),"ThreeYear"))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "培养年限只能是一年或两年或三年！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(doctor.getGraduationYear()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请选择结业年份！");
			return "/res/osca/success";
		}
		String graduationYear=Integer.valueOf(doctor.getSessionNumber())+yearMap.get(doctor.getTrainingYears())+"";
		if(!graduationYear.equals(doctor.getGraduationYear()))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "结业年份填写错误，应为年级+培养年限！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(doctor.getWorkOrgName()))
		{
			doctor.setWorkOrgName(" ");
		}
		int c =oscaAppBiz.completeOsceRegistUser(sysUser,doctor);
		if(c==0)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "注册失败！");
			return "/res/osca/success";
		}
		return "/res/osca/success";
	}
	@RequestMapping(value={"/completeInfo"},method={RequestMethod.POST})
	public  String completeInfo( String  userFlow ,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", "用户标识符为空！");
			return "/res/osca/student/completeInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/completeInfo";
		}
		model.addAttribute("user",user);
		ResDoctor doctor = oscaAppBiz.readResDoctor(user.getUserFlow());
		model.addAttribute("doctor",doctor);
		OscaDoctorRegist search = new OscaDoctorRegist();
		search.setDoctorFlow(user.getUserFlow());
		List<OscaDoctorRegist> regists = oscaDoctorRegistBiz.searchRegist(search);
		if(regists!=null&&regists.size()>0){
			model.addAttribute("regist",regists.get(0));
		}
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgTypeId("Hospital");
		List<SysOrg> orgList = oscaAppBiz.queryAllSysOrg(searchOrg);
		model.addAttribute("orgList",orgList);
		if(StringUtil.isNotBlank(user.getCretTypeId())) {
			String cretTypeName = CertificateTypeEnum.getNameById(user.getCretTypeId());
			user.setCretTypeName(cretTypeName);
		}
		model.addAttribute("types", CertificateTypeEnum.values());

        List<SysDict> dicts = oscaAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId());
		model.addAttribute("dicts", dicts);
		Map<String,List<SysDict>>dictsMap=new HashMap<>();
		if(dicts!=null&&dicts.size()>0)
		{
			for(SysDict dict:dicts)
			{
                List<SysDict> dicts2 = oscaAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + dict.getDictId());
				dictsMap.put(dict.getDictId(),dicts2);
			}
		}
		model.addAttribute("dictsMap", dictsMap);
		List<SysDict> years=new ArrayList<>();
		SysDict dict=new SysDict();
		dict.setDictId("OneYear");
		dict.setDictName("一年");
		years.add(dict);
		dict=new SysDict();
		dict.setDictId("TwoYear");
		dict.setDictName("二年");
		years.add(dict);
		dict=new SysDict();
		dict.setDictId("ThreeYear");
		dict.setDictName("三年");
		years.add(dict);
		model.addAttribute("years", years);

		String trainingYears="";

		Map<String,String> yearMap=new HashMap<>();
		yearMap.put("OneYear","一年");
		yearMap.put("TwoYear","两年");
		yearMap.put("ThreeYear","三年");
		if(doctor!=null) {
			trainingYears = yearMap.get(doctor.getTrainingYears());
		}
		model.addAttribute("trainingYears",trainingYears);
		return "/res/osca/student/completeInfo";
	}

	private String checkUser(SysUser user) {

		String doctorRole = oscaAppBiz.getCfgCode("osca_doctor_role_flow");
		if(StringUtil.isBlank(doctorRole))
		{
			return "请联系系统管理员，配置学员角色" ;
		}
		if(user==null)
		{
			return "用户不存在，无法使用！";
		}

        List<SysUserRole> userRoles = oscaAppBiz.getSysUserRole(user.getUserFlow(), com.pinde.core.common.GlobalConstant.OSCA_WS_ID);
		if(userRoles==null || userRoles.isEmpty()){
			return"用户未赋权，无权限使用！" ;
		}
		boolean isDoctor = false;
		//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
		for(SysUserRole sur : userRoles){
			String ur = sur.getRoleFlow();
			if(doctorRole.equals(ur)){
				isDoctor = true;
				break;
			}
		}
		if(!isDoctor)
		{
			return "用户不是学员角色，无权限使用！" ;
		}
		return null;
	}

	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public  String qrCode(String userFlow,
									  String roleId,
									  String funcTypeId,
									  String funcFlow,
									  String codeInfo,
									  HttpServletRequest request,
									  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011103");
			model.addAttribute("resultType", "功能类型为空");
			return "/res/osca/success";
		}

		if(StringUtil.isNotEquals("qrCode", funcTypeId)){
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");
			return "/res/osca/success";
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		transCodeInfo(paramMap, codeInfo);
		funcFlow=paramMap.get("funcFlow");
		if(StringUtil.isEquals(funcFlow, "queryQrCode")){//技能考核签到
			String recordFlow=paramMap.get("recordFlow");
			if(StringUtil.isNotBlank(recordFlow))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "无效二维码");
				return "/res/osca/success";
			}
			String clinicalFlow=paramMap.get("clinicalFlow");
			if(StringUtil.isBlank(clinicalFlow)){
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "技能考核信息无效");
				return "/res/osca/success";
			}
			OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
			if(skillsAssessment==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "技能考核信息不存在");
				return "/res/osca/success";
			}
			//查询预约信息
			OscaDoctorAssessment oscaDoctorAssessment=oscaAppBiz.getOscaDoctorAssessment(clinicalFlow,userFlow);
			if(oscaDoctorAssessment==null){
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "你未预约该场考核");
				return "/res/osca/success";
			}
			if(!AuditStatusEnum.Passed.getId().equals(oscaDoctorAssessment.getAuditStatusId())){
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "你的预约信息暂未审核通过");
				return "/res/osca/success";
			}
			if(SignStatusEnum.SignIn.getId().equals(oscaDoctorAssessment.getSiginStatusId())){
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "你已签到成功");
				return "/res/osca/success";
			}
			oscaDoctorAssessment.setSiginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			oscaDoctorAssessment.setSiginStatusId(SignStatusEnum.SignIn.getId());
			oscaDoctorAssessment.setSiginStatusName(SignStatusEnum.SignIn.getName());
			if(StringUtil.isNotBlank(oscaDoctorAssessment.getIsPass())&&DoctorScoreEnum.Miss.getId().equals(oscaDoctorAssessment.getIsPass())){
				oscaDoctorAssessment.setIsPass(DoctorScoreEnum.PendingEnter.getId());
				oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.PendingEnter.getName());
			}
			SysUser user=oscaAppBiz.readSysUser(userFlow);
			int count=oscaAppBiz.editOscaDoctorAssessment(oscaDoctorAssessment,user);
			if(count==0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "签到失败");
				return "/res/osca/success";
			}
			List<OscaSubjectStation> stations=oscaAppBiz.getOscaSubjectStations(skillsAssessment.getSubjectFlow());
			List<OscaSkillDocStation> docStations=new ArrayList<>();
			for(OscaSubjectStation station:stations)
			{
				OscaSkillDocStation docStation=oscaAppBiz.getDocSkillStation(station.getStationFlow(),user.getUserFlow(),clinicalFlow);
				if(docStation==null)
					docStation=new OscaSkillDocStation();
                if (!com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId().equals(docStation.getExamStatusId()) &&
                        !com.pinde.core.common.enums.ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId()))
				{
					docStation.setClinicalFlow(clinicalFlow);
					docStation.setClinicalName(skillsAssessment.getClinicalName());
					docStation.setStationFlow(station.getStationFlow());
					docStation.setStationName(station.getStationName());
					docStation.setDoctorFlow(userFlow);
					docStation.setDoctorName(user.getUserName());
					String date=DateUtil.getCurrDateTime2();
					docStation.setHoukaoTime(date);
					docStation.setWaitingTime(date);
                    docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                    docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                    docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					docStations.add(docStation);
				}
			}
			oscaAppBiz.saveDocSkillStations(docStations,user);

			return "/res/osca/success";
		}else if(StringUtil.isEquals(funcFlow, "osceDocJoinTea")) {//学员扫码考官

			String teaFlow=paramMap.get("teaFlow");
			if(StringUtil.isBlank(teaFlow))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "二维码错误");
				return "/res/osca/success";
			}

			OscaDoctorAssessment doctorAssessment=oscaStudentBiz.getAuditOscaDocInfo(userFlow);
			//如果有查询有几站并且查询有哪些房间及相应信息
			if(doctorAssessment==null)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "你暂未预约考核信息，无法参加考核！");
				return "/res/osca/success";
			}

			String clinicalFlow=doctorAssessment.getClinicalFlow();
			String doctorFlow=userFlow;
			paramMap.put("doctorFlow",doctorFlow);
			paramMap.put("clinicalFlow",clinicalFlow);
			paramMap.put("userFlow",teaFlow);
			paramMap.put("roleId",roleId);
			if(StringUtil.isNotBlank(clinicalFlow)&&StringUtil.isNotBlank(doctorFlow)){
				//考核信息
				OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
				if(skillsAssessment==null){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "考核信息不存在");
					return "/res/osca/success";
				}
				String sigin=doctorAssessment.getSiginStatusId();
				if(StringUtil.isBlank(sigin)|| SignStatusEnum.NoSignIn.getId().equals(sigin)){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "该考生尚未考核签到，无法进行考核，请学员联系管理员签到");
					return "/res/osca/success";
				}
				//学员信息
				SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
				if(docUser==null){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "用户信息不存在");
					return "/res/osca/success";
				}
				ResDoctor doctor=oscaAppBiz.readResDoctor(doctorFlow);
				if(doctor==null){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "医师信息不存在");
					return "/res/osca/success";
				}
				if(StringUtil.isBlank(doctorAssessment.getExamStartTime())||StringUtil.isBlank(doctorAssessment.getExamEndTime()))
				{
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "管理员未维护考核时间");
					return "/res/osca/success";
				}
				//查询当前考官可以考核学员哪些站点
				List<OscaSubjectStation> stations=oscaAppBiz.getTeaDocStation(paramMap);
				if(stations==null||stations.size()<=0){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "你不在该考官负责的考核范围内，无法参加考核哦，换一个考官扫一扫吧~");
					return "/res/osca/success";
				}
				//先获取考官是否已经扫过学员的二维码了
				OscaTeaScanDoc b=oscaAppBiz.getOscaTeaScanDoc(paramMap);
				if(b!=null)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你扫过码了，请等待考核！");
					return "/res/osca/success";
				}
				//记录在哪个考场扫码的
				String examRoomFlow="";
				for(int i=0;i<stations.size();i++)
				{
					OscaSubjectStation s=stations.get(i);
					Map<String,Object> bean=new HashMap<>();
					//查询当前站点考官可以考核学员的考场
					Map<String,String> param=new HashMap<>();
					param.put("userFlow",teaFlow);
					param.put("doctorFlow",doctorFlow);
					param.put("stationFlow",s.getStationFlow());
					param.put("clinicalFlow",clinicalFlow);
					//该站点考官可以考核的考场
					List<OscaSkillRoomTea> roomTeas=oscaAppBiz.getTeaRooms(param);
					OscaSkillRoomTea roomTea=null;
					if(roomTeas!=null&&roomTeas.size()>0){
						roomTea=roomTeas.get(0);
					}
					String roomRecordFlow=roomTea.getRoomRecordFlow();
					bean.put("roomRecordFlow",roomRecordFlow);
					bean.put("roomFlow",roomTea.getRoomFlow());
					bean.put("roomName",roomTea.getRoomName());
                    bean.put("examStatusId", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                    bean.put("examStatusName", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
					if(i==0){
						examRoomFlow=roomRecordFlow;
					}
					//当前站点信息学员排队的考场
					OscaSkillRoomDoc docStation=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
					if(docStation!=null){
						roomRecordFlow=docStation.getRoomRecordFlow();
                        if (com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId().equals(docStation.getExamStatusId())) {
							examRoomFlow = roomRecordFlow;
						}
					}
				}
				//看看考场
				OscaSkillRoom room=oscaAppBiz.getOscaskillRoomByFlow(examRoomFlow);
				//添加考官扫码学员的信息
				SysUser user=oscaAppBiz.readSysUser(teaFlow);
				b=new OscaTeaScanDoc();
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				b.setClinicalFlow(clinicalFlow);
				b.setClinicalName(skillsAssessment.getClinicalName());
				b.setDoctorFlow(doctorFlow);
				b.setDoctorName(docUser.getUserName());
				b.setExamTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
				b.setRoomRecordFlow(examRoomFlow);
				b.setRoomFlow(room.getRoomFlow());
				b.setRoomName(room.getRoomName());
				if(StringUtil.isBlank(b.getStatusId())) {
					b.setStatusId(ScanDocStatusEnum.StayAssessment.getId());
					b.setStatusName(ScanDocStatusEnum.StayAssessment.getName());
				}
				b.setPartnerFlow(teaFlow);
				b.setPartnerName(user.getUserName());
				b.setCodeInfo(doctorAssessment.getCodeInfo());//保存学员二维码信息
				int num=oscaAppBiz.editTeaScanDoc(b,user);

				return "/res/osca/success";
			}else {
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "无效二维码");
				return "/res/osca/success";
			}
		} else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "/res/osca/success";
		}
	}

	@RequestMapping(value={"/osca"},method={RequestMethod.POST})
	public String osca(String userFlow,String roleId,
					   HttpServletRequest request,
					   Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/osca";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/osca";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/osca";
		}
		//查询是否是有成功  预约考核信息
		OscaDoctorAssessment oscaDoctorAssessment=oscaStudentBiz.getAuditOscaDocInfo(userFlow);
		model.addAttribute("oscaDoctorAssessment",oscaDoctorAssessment);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
		//如果有查询有几站并且查询有哪些房间及相应信息
		if(oscaDoctorAssessment!=null)
		{
			OscaSkillsAssessment oscaSkillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(oscaDoctorAssessment.getClinicalFlow());
			model.addAttribute("oscaSkillsAssessment",oscaSkillsAssessment);
			if(oscaSkillsAssessment==null)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "考核信息不存在");
				return "/res/osca/student/osca";
			}
			String sigin=oscaDoctorAssessment.getSiginStatusId();
			if(StringUtil.isBlank(sigin)|| SignStatusEnum.NoSignIn.getId().equals(sigin)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "技能考核暂未签到，无法查看考核信息");
				return "/res/osca/student/osca";
			}
			OscaSubjectMain main=oscaStudentBiz.getOscaSubjectMain(oscaSkillsAssessment.getSubjectFlow());
			model.addAttribute("main",main);
			List<OscaSubjectStation> stations=oscaStudentBiz.getOscaSubjectStations(oscaSkillsAssessment.getSubjectFlow());
			if(stations!=null&&stations.size()>0)
			{
				model.addAttribute("stations",stations);
				Map<String,Object> stationRoomMap=new HashMap<>();
				for(OscaSubjectStation s:stations)
				{
					String key =s.getStationFlow();
					Map<String,Object> roomMap=new HashMap<>();
					//查看站点下是否有考场
					List<OscaSkillRoom> rooms=oscaStudentBiz.getRooms(s.getStationFlow(),oscaSkillsAssessment.getClinicalFlow());
					roomMap.put("rooms",rooms);
					roomMap.put("roomSize",rooms.size());
					if(rooms!=null&&rooms.size()>0) {
						//获取学员排队或已经考核的考场相应信息
						OscaSkillRoomExt roomExt = oscaStudentBiz.getDocRoom(userFlow, s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
						if (roomExt == null) {
							//站点考场排队人数最少的一个考场
							roomExt = oscaStudentBiz.getStationBestRoom(s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
						}else{
                            if (roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId()))
							{
                                canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
							}
						}
						roomMap.put("roomExt",roomExt);
					}
					stationRoomMap.put(key,roomMap);
				}
				model.addAttribute("stationRoomMap",stationRoomMap);
			}
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "暂无考核信息");
			return "/res/osca/student/osca";
		}
		model.addAttribute("canClick",canClick);
		return "/res/osca/student/osca";
	}

	@RequestMapping(value={"/lineUp"},method={RequestMethod.POST})
	public String lineUp(String userFlow,String roleId,
						 String roomRecordFlow,String clinicalFlow,String stationFlow,String waitingFlag,
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(roomRecordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考场流水号为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息流水号为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点流水号为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(waitingFlag)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排队标识符为空");
			return "/res/osca/success";
		}
        if (!waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && !waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_N))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排队状态只能是排队或取消排队");
			return "/res/osca/success";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/success";
		}
		//校验是否已经在其他站排队
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("roomRecordFlow",roomRecordFlow);
		param.put("clinicalFlow",clinicalFlow);
		param.put("stationFlow",stationFlow);
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
			//校验是否已经在排队 或正在考核中
			int count = oscaStudentBiz.checkIsWait(param);
			if(count>0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "你正在其他站点或考场排队，请取消后再进行排队！");
				return "/res/osca/success";
			}
		}else{
			//校验当前站点是否正在考核或已考核
			int count = oscaStudentBiz.checkIsAssess(param);
			if(count>0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "当前站点正在考核或已考核，不得取消排队！");
				return "/res/osca/success";
			}
		}
		SysUser user =oscaAppBiz.readSysUser(userFlow);
		//考场信息
		OscaSkillRoom room=oscaStudentBiz.getRoomByFlow(roomRecordFlow);
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		//站点信息
		OscaSubjectStation station=oscaStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
		//当前站点信息
		OscaSkillRoomDoc docStation=oscaStudentBiz.getOscaSkillRoomDocByDoc(param);
		if(docStation==null){
			docStation=new OscaSkillRoomDoc();
			docStation.setDoctorFlow(userFlow);
			docStation.setDoctorName(user.getUserName());
		}
		if(room!=null)
		{
			docStation.setRoomRecordFlow(room.getRecordFlow());
			docStation.setRoomFlow(room.getRoomFlow());
			docStation.setRoomName(room.getRoomName());
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "读取考场信息失败！");
			return "/res/osca/success";
		}
		if(skillsAssessment!=null){
			docStation.setClinicalFlow(skillsAssessment.getClinicalFlow());
			docStation.setClinicalName(skillsAssessment.getClinicalName());
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "读取考核信息失败！");
			return "/res/osca/success";
		}
		if(station!=null){
			docStation.setStationFlow(station.getStationFlow());
			docStation.setStationName(station.getStationName());
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "读取站点信息失败！");
			return "/res/osca/success";
		}
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getName());
			docStation.setWaitingTime(DateUtil.getCurrentTime());
		}else{
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
			docStation.setWaitingTime("");
		}
		int count = oscaStudentBiz.updateOscaSkillRoomDoc(docStation,user);
		if(count==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "操作失败！");
			return "/res/osca/success";
		}
		return "/res/osca/success";
	}
	@RequestMapping(value={"/stationRooms"},method={RequestMethod.POST})
	public String stationRooms(String userFlow,String roleId,String clinicalFlow,String stationFlow,
							   HttpServletRequest request,
							   Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/stationRooms";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/stationRooms";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息流水号为空");
			return "/res/osca/student/stationRooms";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点流水号为空");
			return "/res/osca/student/stationRooms";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/stationRooms";
		}
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("clinicalFlow",clinicalFlow);
		param.put("stationFlow",stationFlow);
		//当前站点信息 是否有考核或排阶段或已考核的考场信息
		//获取学员排队或已经考核的考场相应信息
		OscaSkillRoomExt roomExt = oscaStudentBiz.getDocRoom(userFlow,stationFlow, clinicalFlow);
		OscaSubjectStation station=oscaStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
		model.addAttribute("station",station);
		List<OscaSkillRoomDoc> skillRoomDocs=oscaStudentBiz.getDocAllStation(param);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
		if(skillRoomDocs!=null){
			for(OscaSkillRoomDoc rd:skillRoomDocs)
			{
                if (rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId()))
				{
                    canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
				}
			}
		}
		String roomRecordFlow="";
		if(roomExt!=null)
		{
			roomRecordFlow=roomExt.getRecordFlow();
            model.addAttribute("isSelect", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		model.addAttribute("canClick",canClick);
		param.put("roomRecordFlow",roomRecordFlow);
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		model.addAttribute("skillsAssessment",skillsAssessment);
		List<OscaSkillRoomExt> list = oscaStudentBiz.getStationAllRoom(param);
		model.addAttribute("list",list);
		model.addAttribute("roomExt",roomExt);
		return "/res/osca/student/stationRooms";
	}
//预约考核
	@RequestMapping(value={"/skillAssessList"},method = {RequestMethod.POST})
	public  String skillAssessList(String userFlow,String roleId,String searchStr,   Integer pageIndex,
							   Integer pageSize,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/skillAssessList";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/skillAssessList";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/skillAssessList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/student/skillAssessList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/student/skillAssessList";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/skillAssessList";
		}
		ResDoctor resDoctor=oscaAppBiz.readResDoctor(userFlow);
		if(resDoctor==null)
		{
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/skillAssessList";
		}

		List<String> speIdList=new ArrayList<>();
		List<String> speNameList=new ArrayList<>();
		String speId="";
		String orgFlow="";
		String graduationYear="";
		String trainingSpeName ="";
		if(resDoctor!=null){
			Map<String,Object> map=new HashMap<>();
			//一阶段学员
			if("WMFirst".equals(resDoctor.getTrainingTypeId())){
				trainingSpeName=resDoctor.getTrainingSpeName();
				switch(trainingSpeName)
				{
					case "医学检验科":
						speNameList.add("检验医学科");
						break;
					case "医学影像科":
						speNameList.add("放射科");
						speNameList.add("放射肿瘤科");
						speNameList.add("核医学科");
						speNameList.add("超声医学科");
						break;
					case "病理科":
						speNameList.add("临床病理科");
						break;
					case "皮肤性病科":
						speNameList.add("皮肤科");
						break;
					case "口腔科":
						speNameList.add("口腔全科");
						break;
					case "全科方向（西医）":
						speNameList.add("全科");
						break;
					case "助理全科":
						speNameList.add("全科");
						break;
					default:
						speNameList.add(trainingSpeName);
				}
				map.put("speNameList",speNameList);
				speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
				if(speNameList.size()==0){
					speIdList=new ArrayList<>();
				}
			}else if(StringUtil.isNotBlank(resDoctor.getTrainingSpeName())
					&&"助理全科".equals(resDoctor.getTrainingSpeName())){
				speNameList.add("全科");
				map.put("speNameList",speNameList);
				speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
			}else {
				speId=resDoctor.getTrainingSpeId();
			}
			if(speIdList.size()==0){
				speId=resDoctor.getTrainingSpeId();
			}
			orgFlow=resDoctor.getOrgFlow();
		}
        List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits = oscaDoctorOrderdeBiz.selectDoctorGraduationYear(userFlow);
		if(resDoctorRecruits!=null&&resDoctorRecruits.size()>0&&resDoctorRecruits.get(0)!=null){
			graduationYear=resDoctorRecruits.get(0).getGraduationYear();
		}
		if(StringUtil.isNotBlank(graduationYear)){
			graduationYear=graduationYear.substring(0,4);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("searchStr",searchStr);
		map.put("speId",speId);
		map.put("speIdList",speIdList);
		map.put("orgFlow",orgFlow);
		PageHelper.startPage(pageIndex,pageSize);
		List<OscaSkillsAssessmentExt> skillsAssessmentList=oscaDoctorOrderdeBiz.skillsAssessmentList(map);
		Map<String,OscaDoctorAssessment> odaMap=new HashMap<>();
		Map<String,List<OscaSkillsAssessmentTime>> timesMap=new HashMap<>();
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
			OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
			oscaDoctorAssessment.setDoctorFlow(userFlow);
			List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
			if(doctorList!=null&&doctorList.size()>0){
				for(OscaDoctorAssessment oda:doctorList)
				{
					odaMap.put(oda.getClinicalFlow(),oda);
				}
			}
			for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
				if(osae!=null){
					oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
					oscaDoctorAssessment.setDoctorFlow(null);
					int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
					int overplus=osae.getAppointNum()-doctorCount;
					osae.setOverplus(overplus+"");
					List<OscaSkillsAssessmentTime> times=baseBiz.getAssessmentTimes(osae.getClinicalFlow());
					timesMap.put(osae.getClinicalFlow(),times);
				}
			}
		}
//
//		OscaDoctorAssessment doctor=new OscaDoctorAssessment();
//		doctor.setDoctorFlow(userFlow);
//		List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(doctor);
//		OscaDoctorAssessment resultDoctor=null;
//		if(doctorList!=null&&doctorList.size()>0){
//			resultDoctor=doctorList.get(0);
//		}
//		Map<String, String> doctorMap=new HashMap<>();
//		doctorMap.put("doctorFlow",userFlow);
//		OscaSkillsAssessmentExt doctorAssessmentInfo=null;
//		List<OscaSkillsAssessmentExt> skillsAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessmentInfo(doctorMap);
//		if(skillsAssessments!=null&&skillsAssessments.size()>0){
//			doctorAssessmentInfo=skillsAssessments.get(0);
//		}
//		String clinicalFlow="";
//		String ticketNumber="";
//		List<OscaSkillsAssessmentExt> osaTemp1=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
//		if(osaTemp1!=null&&osaTemp1.size()>0){
//			if(osaTemp1.get(0)!=null&&osaTemp1.get(0).getOscaDoctorAssessment()!=null){
//				ticketNumber=osaTemp1.get(0).getOscaDoctorAssessment().getTicketNumber();
//			}
//		}
//		if(doctorAssessmentInfo!=null&&doctorAssessmentInfo.getOscaDoctorAssessment()!=null){
//			clinicalFlow=doctorAssessmentInfo.getClinicalFlow();
//		}
//		Map<String,Object> param = new HashMap<>();
//		param.put("clinicalFlow",clinicalFlow);
//		param.put("auditStatusId",AuditStatusEnum.Passed.getId());
//		param.put("signStatusId", SignStatusEnum.SignIn.getId());
//		param.put("ticketNumber", ticketNumber);
//		param.put("doctorFlow",userFlow);
//		List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
//		int doctorScore=0;
//		if(gradeList!=null&&gradeList.size()>0&&gradeList.get(0)!=null&&gradeList.get(0).get("EXAM_SCORE")!=null){
//			String examScore=gradeList.get(0).get("EXAM_SCORE").toString();
//			String[] scores=examScore.split(",");
//			for (int i=0;i<scores.length;i++){
//				if(scores[i]!=null&&!"*".equals(scores[i])){
//					doctorScore+=(int)(Double.parseDouble(scores[i])+0.5);
//				}
//			}
//		}
//		if(resDoctor==null||StringUtil.isBlank(resDoctor.getTrainingSpeId())|| StringUtil.isBlank(resDoctor.getTrainingSpeId())){
//			skillsAssessmentList=new ArrayList<>();
//		}
//		int lastGraduationYear=0;
//		if(StringUtil.isNotBlank(graduationYear)){
//			lastGraduationYear=Integer.parseInt(graduationYear)-1;
//		}
//		//取近两年结业考核理论成绩
//		List<ResScore> resScoreList=oscaDoctorOrderdeBiz.selectResScore(userFlow,graduationYear);
//		List<ResScore> resScoreList1=oscaDoctorOrderdeBiz.selectResScore(userFlow,lastGraduationYear+"");
//		if(StringUtil.isNotBlank(graduationYear)&&resScoreList!=null&&resScoreList.size()>0){
//			model.addAttribute("resScore",resScoreList.get(0));
//		}else{
//			model.addAttribute("resScore",new ResScore());
//		}
//		if(lastGraduationYear!=0&&resScoreList1!=null&&resScoreList1.size()>0){
//			model.addAttribute("resScore1",resScoreList1.get(0));
//		}else{
//			model.addAttribute("resScore1",new ResScore());
//		}
//
//		String hegeScore="60";
//		//根据年份从res_pass_score_cfg取数据
//		ResPassScoreCfg cfg = new ResPassScoreCfg();
//		cfg.setCfgYear(graduationYear);
//		ResPassScoreCfg resPassScoreCfg = resbaseBiz.readResPassScoreCfg(cfg);
//		if(resPassScoreCfg!=null){
//			hegeScore = resPassScoreCfg.getCfgPassScore();
//			if(StringUtil.isBlank(hegeScore)){
//				hegeScore="60";
//			}
//		}
//		model.addAttribute("hegeScore",hegeScore);
//		model.addAttribute("doctorScore",doctorScore+"");
//		model.addAttribute("doctorAssessmentInfo",doctorAssessmentInfo);
//		model.addAttribute("resultDoctor",resultDoctor);
//		model.addAttribute("skillsAssessments",skillsAssessments);
		model.addAttribute("graduationYear",graduationYear);
		if(resDoctor!=null){
			model.addAttribute("oscaStudentSubmit",resDoctor.getOscaStudentSubmit());//通过OSCE注册学员
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resDoctor.getOscaStudentSubmit())) {
				model.addAttribute("graduationYear",resDoctor.getGraduationYear());
			}
		}
//		model.addAttribute("isOsca",user.getIsOsca());

		model.addAttribute("timesMap",timesMap);
		model.addAttribute("odaMap",odaMap);
		model.addAttribute("skillsAssessmentList", skillsAssessmentList);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/student/skillAssessList";
	}
	//取消预约
	@RequestMapping(value={"/changeOrdered"},method={RequestMethod.POST})
	public String changeOrdered(String userFlow,String roleId,
								String recordFlow,
								HttpServletRequest request,
								Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(recordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "预约信息流水号为空");
			return "/res/osca/success";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/success";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/success";
		}
		//考核信息
		OscaDoctorAssessment skillsAssessment=oscaDoctorOrderdeBiz.selectDoctorAssessmentByRecordFlow(recordFlow);
		if(skillsAssessment==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "预约信息不存在");
			return "/res/osca/success";
		}
        skillsAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		skillsAssessment.setModifyUserFlow(user.getUserFlow());
		skillsAssessment.setModifyTime(DateUtil.getCurrDateTime());
		int count =	oscaDoctorOrderdeBiz.updateDoctorAssessment(skillsAssessment);
		if(count==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "取消失败！");
			return "/res/osca/success";
		}
		return "/res/osca/success";
	}
	//预约
	@RequestMapping(value={"/toOrdered"},method={RequestMethod.POST})
	public String toOrdered(String userFlow,String roleId,
								String clinicalFlow,
								HttpServletRequest request,
								Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息流水号为空");
			return "/res/osca/success";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/success";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/success";
		}
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "/res/osca/success";
		}

		ResDoctor doctor=oscaAppBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/success";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillsAssessment.getIsLocal()))//预约院内考核
		{
		}else{//预约结业
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getOscaStudentSubmit()))
			{
				String graduationYear=doctor.getGraduationYear();
				if(StringUtil.isBlank(graduationYear)||skillsAssessment.getClinicalYear().compareTo(graduationYear)<0)
				{
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "你的考核年份未到，无法预约技能考核！");
					return "/res/osca/success";
				}
			}else{//江苏西医学员只能每一年考核一次
				String graduationYear="";
                List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits = oscaDoctorOrderdeBiz.selectDoctorGraduationYear(userFlow);
				if(resDoctorRecruits!=null&&resDoctorRecruits.size()>0&&resDoctorRecruits.get(0)!=null){
					graduationYear=resDoctorRecruits.get(0).getGraduationYear();
				}
				if(StringUtil.isNotBlank(graduationYear)){
					graduationYear=graduationYear.substring(0,4);
				}
				if(StringUtil.isBlank(graduationYear)||skillsAssessment.getClinicalYear().compareTo(graduationYear)<0)
				{
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "你的考核年份未到，无法预约技能考核！");
					return "/res/osca/success";
				}
				if(skillsAssessment.getClinicalYear().compareTo(graduationYear)==0)
				{

					int lastGraduationYear=0;
					if(StringUtil.isNotBlank(graduationYear)){
						lastGraduationYear=Integer.parseInt(graduationYear)-1;
					}
					//取近两年结业考核理论成绩
					List<ResScore> resScoreList=oscaDoctorOrderdeBiz.selectResScore(userFlow,graduationYear);
					List<ResScore> resScoreList1=oscaDoctorOrderdeBiz.selectResScore(userFlow,lastGraduationYear+"");
					ResScore resScore=new ResScore();
					ResScore resScore1=new ResScore();
					if(StringUtil.isNotBlank(graduationYear)&&resScoreList!=null&&resScoreList.size()>0){
						resScore=resScoreList.get(0);
					}
					if(lastGraduationYear!=0&&resScoreList1!=null&&resScoreList1.size()>0){
						resScore1=resScoreList1.get(0);
					}
					String hegeScore="60";
					//根据年份从res_pass_score_cfg取数据
					ResPassScoreCfg cfg = new ResPassScoreCfg();
					cfg.setCfgYear(graduationYear);
					ResPassScoreCfg resPassScoreCfg = resbaseBiz.readResPassScoreCfg(cfg);
					if(resPassScoreCfg!=null){
						hegeScore = resPassScoreCfg.getCfgPassScore();
						if(StringUtil.isBlank(hegeScore)){
							hegeScore="60";
						}
					}
					String hegeScore1="60";
					//根据年份从res_pass_score_cfg取数据
					cfg.setCfgYear(lastGraduationYear+"");
					resPassScoreCfg = resbaseBiz.readResPassScoreCfg(cfg);
					if(resPassScoreCfg!=null){
						hegeScore1 = resPassScoreCfg.getCfgPassScore();
						if(StringUtil.isBlank(hegeScore1)){
							hegeScore1="60";
						}
					}
					if((hegeScore.compareTo(resScore.getTheoryScore()+"")>0||StringUtil.isBlank(resScore.getTheoryScore()+"")&&
							hegeScore1.compareTo(resScore1.getTheoryScore()+"")>0||StringUtil.isBlank(resScore1.getTheoryScore()+""))&&"2018".compareTo(graduationYear)>=0)
					{
						model.addAttribute("resultId", "3011101");
						model.addAttribute("resultType", "你的结业理论成绩未通过，无法预约技能考核！");
						return "/res/osca/success";
					}
				}

			}
			Map<String, String> doctorMap=new HashMap<>();
			doctorMap.put("doctorFlow",userFlow);
			doctorMap.put("clinicalYear",skillsAssessment.getClinicalYear());//预约的考核信息年份
//			doctorMap.put("orgFlow",skillsAssessment.getOrgFlow());//考核信息发布考点
			List<OscaSkillsAssessmentExt> osaList=oscaDoctorOrderdeBiz.selectAssessmentIsNotLocalOneYear(doctorMap);
			if(osaList!=null&&osaList.size()>0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "你本年度已经参加过一次结业考核！");
				return "/res/osca/success";
			}
		}
		//校验考核时间
		int temp=checkTimes(clinicalFlow,userFlow);
		if(temp>0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "该信息考核时间与已预约的考核时间重叠,请重新选择！");
			return "/res/osca/success";
		}
		OscaDoctorAssessment odaTemp=new OscaDoctorAssessment();
		odaTemp.setClinicalFlow(clinicalFlow);
		int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(odaTemp);
		int overplus=skillsAssessment.getAppointNum()-doctorCount;
		if(overplus>0){

			int count =	0;
			OscaDoctorAssessment oda=new OscaDoctorAssessment();
			oda.setClinicalFlow(clinicalFlow);
			oda.setDoctorFlow(userFlow);
			List<OscaDoctorAssessment> odaListTemp = oscaDoctorOrderdeBiz.selectDoctorAssessment(oda);
				OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
				if(odaListTemp!=null&&odaListTemp.size()>0){
					oscaDoctorAssessment=odaListTemp.get(0);
					if(AuditStatusEnum.Passing.getId().equals(oscaDoctorAssessment.getAuditStatusId()))
					{
						model.addAttribute("resultId", "3011101");
						model.addAttribute("resultType", "你预约过此考核信息！");
						return "/res/osca/success";
					}
					if(AuditStatusEnum.Passed.getId().equals(oscaDoctorAssessment.getAuditStatusId()))
					{
						model.addAttribute("resultId", "3011101");
						model.addAttribute("resultType", "此考核的预约信息已审核通过，不需要重复预约！");
						return "/res/osca/success";
					}
					oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
					oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
					oscaDoctorAssessment.setModifyTime(DateUtil.getCurrentTime());
					oscaDoctorAssessment.setModifyUserFlow(user.getUserFlow());
					count =oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
				}else{
					oscaDoctorAssessment.setRecordFlow(PkUtil.getUUID());
					oscaDoctorAssessment.setClinicalFlow(clinicalFlow);
					oscaDoctorAssessment.setClinicalName(skillsAssessment.getClinicalName());
					oscaDoctorAssessment.setClinicalYear(skillsAssessment.getClinicalYear());
					oscaDoctorAssessment.setDoctorFlow(user.getUserFlow());
					oscaDoctorAssessment.setDoctorName(user.getUserName());
					oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
					oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
					oscaDoctorAssessment.setCreateTime(DateUtil.getCurrentTime());
					oscaDoctorAssessment.setCreateUserFlow(user.getUserFlow());
					oscaDoctorAssessment.setModifyTime(DateUtil.getCurrentTime());
					oscaDoctorAssessment.setModifyUserFlow(user.getUserFlow());
                    oscaDoctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					oscaDoctorAssessment.setIsPass(DoctorScoreEnum.Miss.getId());
					oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.Miss.getName());
                    oscaDoctorAssessment.setIsAdminAudit(com.pinde.core.common.GlobalConstant.FLAG_N);
					count =oscaDoctorOrderdeBiz.insertDoctorAssessment(oscaDoctorAssessment);

				}
				if(count==0)
				{
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "预约失败！");
					return "/res/osca/success";
				}
				return "/res/osca/success";
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "该考核预约人数已满，请刷新列表，预约其他考核！");
			return "/res/osca/success";
		}
	}


	@RequestMapping(value={"/myAssessList"},method = {RequestMethod.POST})
	public  String myAssessList(String userFlow,String roleId, String searchStr,   Integer pageIndex,
								   Integer pageSize,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/myAssessList";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/myAssessList";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/myAssessList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/student/myAssessList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/student/myAssessList";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/myAssessList";
		}
		ResDoctor resDoctor=oscaAppBiz.readResDoctor(userFlow);
		if(resDoctor==null)
		{
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/myAssessList";
		}
		Map<String, String> map=new HashMap<>();
		map.put("doctorFlow",userFlow);
		map.put("searchStr",searchStr);
		map.put("orgFlow",resDoctor.getOrgFlow());
		PageHelper.startPage(pageIndex,pageSize);
		List<OscaSkillsAssessmentExt> skillsAssessmentList=oscaDoctorOrderdeBiz.selectDoctorAssessmentList(map);
		Map<String,OscaDoctorAssessment> odaMap=new HashMap<>();
		Map<String,List<OscaSkillsAssessmentTime>> timesMap=new HashMap<>();
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
			OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
			oscaDoctorAssessment.setDoctorFlow(userFlow);
			List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
			if(doctorList!=null&&doctorList.size()>0){
				for(OscaDoctorAssessment oda:doctorList)
				{
					odaMap.put(oda.getClinicalFlow(),oda);
				}
			}
			for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
				if(osae!=null){
					oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
					oscaDoctorAssessment.setDoctorFlow(null);
					int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
					int overplus=osae.getAppointNum()-doctorCount;
					osae.setOverplus(overplus+"");
					List<OscaSkillsAssessmentTime> times=baseBiz.getAssessmentTimes(osae.getClinicalFlow());
					timesMap.put(osae.getClinicalFlow(),times);
				}
			}
		}
		model.addAttribute("timesMap",timesMap);
		model.addAttribute("odaMap",odaMap);
		model.addAttribute("skillsAssessmentList", skillsAssessmentList);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/student/myAssessList";
	}
	@RequestMapping(value={"/myScores"},method = {RequestMethod.POST})
	public  String myScores(String userFlow,String roleId,   Integer pageIndex,
								   Integer pageSize,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/myScores";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/myScores";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/myScores";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/student/myScores";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/student/myScores";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/myScores";
		}
		ResDoctor resDoctor=oscaAppBiz.readResDoctor(userFlow);
		if(resDoctor==null)
		{
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/myScores";
		}
		Map<String,Object> paramMap2 = new HashMap<>();
		paramMap2.put("doctorFlow",userFlow);
		paramMap2.put("auditStatusId",AuditStatusEnum.Passed.getId());
		paramMap2.put("signStatusId", SignStatusEnum.SignIn.getId());
		paramMap2.put("orgFlow",resDoctor.getOrgFlow());
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> resultMapList = oscaDoctorScoreBiz.getSingleGrade(paramMap2);
		model.addAttribute("datas",resultMapList);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/student/myScores";
	}
	@RequestMapping(value={"/showTicket"},method={RequestMethod.POST})
	public String showTicket(String userFlow,String roleId,
							 String clinicalFlow,
							 HttpServletRequest request,
							 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/showTicket";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/showTicket";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息流水号为空");
			return "/res/osca/student/showTicket";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/showTicket";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/showTicket";
		}
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "/res/osca/student/showTicket";
		}

		ResDoctor doctor=oscaAppBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/showTicket";
		}
		OscaDoctorAssessment oda=oscaStudentBiz.getOscaDoctorAssessment(userFlow,clinicalFlow);
		if(oda==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "你未预约该考核信息，请刷新列表页面");
			return "/res/osca/student/showTicket";
		}
		if(!AuditStatusEnum.Passed.getId().equals(oda.getAuditStatusId()))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "你的预约信息未审核通过，请刷新列表页面");
			return "/res/osca/student/showTicket";
		}
		model.addAttribute("skillsAssessment",skillsAssessment);
		model.addAttribute("oda",oda);
		model.addAttribute("user",user);
		model.addAttribute("doctor",doctor);

		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "/res/osca/student/showTicket";
	}
	@RequestMapping(value={"/scoreDetail"},method={RequestMethod.POST})
	public String scoreDetail(String userFlow,String roleId,
							 String clinicalFlow,
							 HttpServletRequest request,
							 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/scoreDetail";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/scoreDetail";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息流水号为空");
			return "/res/osca/student/scoreDetail";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/scoreDetail";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/scoreDetail";
		}

		ResDoctor doctor=oscaAppBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/scoreDetail";
		}
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "/res/osca/student/scoreDetail";
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillsAssessment.getIsShow()))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核成绩暂未发布！");
			return "/res/osca/student/scoreDetail";
		}
		OscaDoctorAssessment oda=oscaStudentBiz.getOscaDoctorAssessment(userFlow,clinicalFlow);
		if(oda==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "你未预约该考核信息，请刷新列表页面");
			return "/res/osca/student/scoreDetail";
		}
		if(!AuditStatusEnum.Passed.getId().equals(oda.getAuditStatusId()))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "你的预约信息未审核通过，请刷新列表页面");
			return "/res/osca/student/scoreDetail";
		}
		if(!SignStatusEnum.SignIn.getId().equals(oda.getSiginStatusId()))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "你未参加该考核信息，请刷新列表页面");
			return "/res/osca/student/scoreDetail";
		}
		List<OscaSubjectStation> stations=oscaAppBiz.getOscaSubjectStations(skillsAssessment.getSubjectFlow());

		Map<String,Object> param=new HashMap<>();
		Map<String,OscaSkillRoomDoc> docRoomMap=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("clinicalFlow",clinicalFlow);
		int allScore=0;
		List<OscaSkillRoomDoc> skillRoomDocs=oscaStudentBiz.getDocAllStation(param);
		if(skillRoomDocs!=null){
			for(OscaSkillRoomDoc rd:skillRoomDocs)
			{
				docRoomMap.put(rd.getStationFlow(),rd);
				if(StringUtil.isNotBlank(rd.getExamScore()))
				{
					allScore+=Integer.valueOf(rd.getExamScore());
				}
			}
		}
		model.addAttribute("skillsAssessment",skillsAssessment);
		model.addAttribute("allScore",allScore);
		model.addAttribute("docRoomMap",docRoomMap);
		model.addAttribute("stations",stations);
		model.addAttribute("oda",oda);
		model.addAttribute("user",user);
		model.addAttribute("doctor",doctor);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "/res/osca/student/scoreDetail";
	}
	@RequestMapping(value={"/noticeList"})
	public String noticeList(String userFlow,Integer pageIndex,Integer pageSize,String noticeTitle,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/noticeList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "/res/osca/student/noticeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "/res/osca/student/noticeList";
		}
		InxInfo info = new InxInfo();
		info.setColumnId("jinengkaohe");
		PageHelper.startPage(pageIndex,pageSize);
		List<InxInfo> infos = this.oscaAppBiz.findNotice(info);
		model.addAttribute("infos",infos);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp//res/osca/student/showNotice/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/student/noticeList";
	}
	@RequestMapping(value={"/noticeDetail"})
	public String noticeDetail(String userFlow,String infoFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/noticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "公告标识符为空");
			return "/res/osca/student/noticeDetail";
		}
		InxInfo info=oscaAppBiz.readInxInfo(infoFlow);
		if(info==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "公告信息不存在");
			return "/res/osca/student/noticeDetail";
		}
		model.addAttribute("title",info.getInfoTitle());
		model.addAttribute("content",info.getInfoContent());
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/osca/student/showNotice/showNoticeDetail.jsp?infoFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/osca/student/showNotice/showNoticeDetail2.jsp?infoFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "/res/osca/student/noticeDetail";
	}
	@RequestMapping(value={"/showNoticeDetail"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showNoticeDetail(String infoFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		InxInfo info=oscaAppBiz.readInxInfo(infoFlow);
		if(info!=null)
		{
			map.put("title",""+info.getInfoTitle()+"");
			map.put("content",info.getInfoContent());
		}else{
			map.put("title","");
			map.put("content","");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value={"/ownInfo"},method={RequestMethod.POST})
	public String ownInfo(String userFlow,String roleId,
						  HttpServletRequest request,
						  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/student/ownInfo";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "/res/osca/student/ownInfo";
		}
		if(!roleId.equals("Student")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户与角色不符");
			return "/res/osca/student/ownInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);

		String msg=checkUser(user);
		if(StringUtil.isNotBlank(msg))
		{
			model.addAttribute("resultId", "010000");
			model.addAttribute("resultType", msg);
			return "/res/osca/student/ownInfo";
		}

		ResDoctor doctor=oscaAppBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "医师信息不存在");
			return "/res/osca/student/ownInfo";
		}
		String trainingYears="";

		if(StringUtil.isNotBlank(user.getCretTypeId())) {
			String cretTypeName = CertificateTypeEnum.getNameById(user.getCretTypeId());
			user.setCretTypeName(cretTypeName);
		}
		Map<String,String> yearMap=new HashMap<>();
		yearMap.put("OneYear","一年");
		yearMap.put("TwoYear","两年");
		yearMap.put("ThreeYear","三年");
		trainingYears=yearMap.get(doctor.getTrainingYears());
		model.addAttribute("trainingYears",trainingYears);
		model.addAttribute("user",user);
		model.addAttribute("doctor",doctor);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "/res/osca/student/ownInfo";
	}
	@RequestMapping(value={"/changePassword"},method={RequestMethod.POST})
	public String changePassword(String userFlow,String oldPass,String newPass,String reNewPass,
						  HttpServletRequest request,
						  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(oldPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入原密码！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(newPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入新密码！");
			return "/res/osca/success";
		}
		if(StringUtil.isBlank(reNewPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入确认密码！");
			return "/res/osca/success";
		}
		if(!reNewPass.equals(newPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "两次密码输入不一致！");
			return "/res/osca/success";
		}
		boolean f = newPass.matches("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{6,20}$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "密码必须包括数字、字母，长度6－20！");
			return "/res/osca/success";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "用户不存在！");
			return "/res/osca/success";
		}
        String newPassword = PasswordHelper.encryptPassword(user.getUserFlow(), oldPass);
		if(!user.getUserPasswd().equalsIgnoreCase(newPassword)){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "原密码输入不正确！");
			return "/res/osca/success";
		}
		newPassword=PasswordHelper.encryptPassword(user.getUserFlow(), newPass);
		user=new SysUser();
		user.setUserFlow(userFlow);
		user.setUserPasswd(newPassword);
		int c=oscaAppBiz.edit(user,user);
		if(c==0){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "修改密码失败！");
			return "/res/osca/success";
		}
		return "/res/osca/success";

	}
	@RequestMapping(value={"/checkPassword"},method={RequestMethod.POST})
	public String checkPassword(String userFlow,String oldPass,
						  HttpServletRequest request,
						  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(oldPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入密码！");
			return "/res/osca/success";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "用户不存在！");
			return "/res/osca/success";
		}
		if(!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), oldPass))){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "密码输入不正确！");
			return "/res/osca/success";
		}
		return "/res/osca/success";

	}
	@RequestMapping(value={"/changePhone"},method={RequestMethod.POST})
	public synchronized String changePhone(String userFlow,String oldPass,String userPhone,
						  HttpServletRequest request,
						  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(oldPass))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入密码！");
			return "/res/osca/success";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "用户不存在！");
			return "/res/osca/success";
		}
		if(!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), oldPass))){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "密码输入不正确！");
			return "/res/osca/success";
		}

		if(StringUtil.isBlank(userPhone))
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入手机号！");
			return "/res/osca/success";
		}
		boolean f = userPhone.matches("^[1][345789]\\d{9}$");
		if(!f)
		{
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "请输入正确的手机号！");
			return "/res/osca/success";
		}

		//判断用户手机号是否重复
		SysUser olduser = oscaAppBiz.findByUserPhone(userPhone);
		if (olduser != null) {
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "该手机号已经被注册！");
			return "/res/osca/success";
		}
		user=new SysUser();
		user.setUserFlow(userFlow);
		user.setUserPhone(userPhone);
		int c=oscaAppBiz.edit(user,user);
		if(c==0){
			model.addAttribute("resultId", "010101");
			model.addAttribute("resultType", "修改手机号失败！");
			return "/res/osca/success";
		}
		return "/res/osca/success";

	}

	private int checkTimes(String clinicalFlow, String userFlow) {


		int temp=0;
		List<OscaSkillsAssessmentTime> timeList=oscaDoctorOrderdeBiz.searchCheckTime(clinicalFlow);
		if(timeList!=null&&timeList.size()>0){
			for (OscaSkillsAssessmentTime t:timeList) {
				String examStartTime=t.getExamStartTime();
				String examEndTime=t.getExamEndTime();
				if(StringUtil.isNotBlank(examStartTime)&&StringUtil.isNotBlank(examEndTime)){
					Map<String, String> map=new HashMap<>();
					map.put("doctorFlow",userFlow);
					map.put("examStartTime",examStartTime);
					map.put("examEndTime",examEndTime);
					int count=oscaDoctorOrderdeBiz.countOrderedTime(map);
					if(count>0){
						temp++;
					}
				}
			}
		}
		return temp;
	}

	//解析二维码字符串为map
	private void transCodeInfo(Map<String,String> paramMap,String codeInfo){
		String[] params = StringUtil.split(codeInfo, "&");
		for(String paramStr : params){
			if(paramStr.indexOf("=")>0){
				String key = paramStr.substring(0, paramStr.indexOf("="));
				String value = paramStr.substring(paramStr.indexOf("=")+1, paramStr.length());
				paramMap.put(key, value);
			}
		}
	}
}

