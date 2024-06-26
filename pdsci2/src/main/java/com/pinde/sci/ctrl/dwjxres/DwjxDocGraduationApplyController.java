package com.pinde.sci.ctrl.dwjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
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
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dwjxres/doctor/graduationAppraisal")
public class DwjxDocGraduationApplyController extends GeneralController{

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
	private IDocSingupBiz docSingupBiz;
	@Autowired
	private IFileBiz fileBiz;

	@RequestMapping("/list")
	public String list(Integer currentPage , Model model,HttpServletRequest request){
		String s;
		SysUser user = GlobalContext.getCurrentUser();
		if(currentPage==null)
			currentPage=1;
		PageHelper.startPage(currentPage,getPageSize(request));
		List<String> statues = new ArrayList<>();
		statues.add(StuStatusEnum.Admited.getId());
		statues.add(StuStatusEnum.Graduation.getId());
		statues.add(StuStatusEnum.DelayGraduation.getId());
		List<StuUserResume> list=stuUserResumeBiz.getPassStuUserLst(user.getUserFlow(),statues);
		model.addAttribute("list",list);
		//管理员附件
		Map<String,Object> adminFileMap=new HashMap<>();
		//学生上传结业鉴定
		Map<String,Object> stuFileMap=new HashMap<>();
		//学生结业评语
		Map<String,String> graduationMarkMap = new HashMap<>();

		if(list!=null&&list.size()>0)
		{
			for(StuUserResume sur:list)
			{
				if(StuStatusEnum.DelayGraduation.getId().equals(sur.getStuStatusId())){
					String resumeInfo = sur.getResumeInfo();
					ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resumeInfo);
					graduationMarkMap.put(sur.getResumeFlow(),extInfo.getGraduationMark());
				}
				List<PubFile> files=fileBiz.findFileByTypeFlow("AdminFlie",sur.getResumeFlow());
				if(files!=null&&files.size()>0)
				{
					adminFileMap.put(sur.getResumeFlow(),files.get(0));
				}
				List<PubFile> files2=fileBiz.findFileByTypeFlow("StuFlie",sur.getResumeFlow());
				if(files2!=null&&files2.size()>0)
				{
					stuFileMap.put(sur.getResumeFlow(),files2.get(0));
				}
			}
		}
		model.addAttribute("adminFileMap",adminFileMap);
		model.addAttribute("graduationMarkMap",graduationMarkMap);
		model.addAttribute("stuFileMap",stuFileMap);
		return "dwjxres/doctor/graduation/list";
	}

}
