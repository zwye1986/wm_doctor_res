package com.pinde.sci.ctrl.zseyjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxDocSingupBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.form.zseyjxres.SpeForm;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pinde.sci.form.zseyjxres.ExtInfoForm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zseyjxres/doctor/graduationAppraisal")
public class ZseyjxDocGraduationApplyController extends GeneralController{

	@Autowired
	private IStuUserResumeBiz stuUserResumeBiz;
	@Autowired
	private IZseyjxDocSingupBiz docSingupBiz;
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
		Map<String,List<SpeForm>> speFormMap = new HashMap<>(); //进修专业、时间

		if(list!=null&&list.size()>0)
		{
			ExtInfoForm extInfo = null;
			for(StuUserResume sur:list)
			{
				String resumeInfo = sur.getResumeInfo();
				extInfo = docSingupBiz.parseExtInfoXml(resumeInfo);
				speFormMap.put(sur.getResumeFlow(),extInfo.getSpeFormList());

				if(StuStatusEnum.DelayGraduation.getId().equals(sur.getStuStatusId())){
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
		model.addAttribute("speFormMap",speFormMap);
		model.addAttribute("adminFileMap",adminFileMap);
		model.addAttribute("graduationMarkMap",graduationMarkMap);
		model.addAttribute("stuFileMap",stuFileMap);
		return "zseyjxres/doctor/graduation/list";
	}
}
