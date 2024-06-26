package com.pinde.sci.ctrl.srm;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SrmGradeItemMapper;
import com.pinde.sci.dao.srm.SrmExpertGroupExtMapper;
import com.pinde.sci.enums.srm.EvaluationStatusEnum;
import com.pinde.sci.enums.srm.ExpertScoreResultEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmExpertGroupProjExt;
import com.pinde.sci.model.srm.SrmExpertProjExt;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/srm/expert/proj")
public class ExpertProjController extends GeneralController{

	@Autowired
	private IExpertProjBiz expertProjBiz;
	@Autowired
	private IGradeItemBiz gradeItemBiz;
	@Autowired
	private IExpertGroupProjBiz expertGroupProjBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IExpertBiz expertBiz;
	@Autowired
	private SrmGradeItemMapper gradeItemMapper;
	
	/**
	 * 专家评审默认进入页面(目前只显示网评)
	 * @param userFlow
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/confirm")
	public String confirm(String userFlow , Model model,HttpServletRequest request){
		SrmExpertProjExt srmExpertProjExt = new SrmExpertProjExt();
		srmExpertProjExt.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		srmExpertProjExt.setRecordStatus(GlobalConstant.FLAG_Y);
		srmExpertProjExt.setAgreeFlag(GlobalConstant.NULL);//表示没有点过同意按钮的
		SrmExpertGroupProjExt srmExpertGroupProjExt = new SrmExpertGroupProjExt();
		srmExpertGroupProjExt.setEvalStatusId(EvaluationStatusEnum.Submit.getId());//评审设置为提交的状态 专家评审才能看到
		srmExpertProjExt.setSrmExpertGroupProjExt(srmExpertGroupProjExt );
		
//		HashMap<String,Object> map=new HashMap<String, Object>();
//		SrmExpertGroup expertGroup=new SrmExpertGroup();
		//评审项目通知
		List<SrmExpertProjExt> expertProjList = this.expertProjBiz.searchExpertProj(srmExpertProjExt);
//		for(SrmExpertProjExt list:expertProjList){
//				//如果评审方式是网络评审
//				if(EvaluationWayEnum.NetWorkWay.getId().equals(list.getSrmExpertGroupProjExt().getEvaluationWayId())){
//					map.put(list.getProjFlow(), list);
//				}
//				//如果评审方式是会议评审
//				else{
//					expertGroup = experGroupBiz.readSrmExpertGroup(list.getSrmExpertGroupProjExt().getGroupFlow());
//					map.put(list.getProjFlow(), expertGroup);
//				}
//		}
//		model.addAttribute("map", map);
		model.addAttribute("expertProjList" , expertProjList);
		
		SrmExpert expert = expertBiz.readExpert(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("expert", expert);
		return "srm/expert/proj/confirm";
	}
	
	/**
	 * 专家是否同意评审该项目
	 * @param expertProjFlow
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveConfirm",method={RequestMethod.POST})
	public @ResponseBody String saveConfirm( String expertProjFlow , String flag , Model model){
		SrmExpertProj expertProj = expertProjBiz.read(expertProjFlow);
		expertProj.setAgreeFlag(flag);
		expertProjBiz.modifyByFlow(expertProj);
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	/**
	 * 评审时间过期 就隐藏
	 * @param recordFlow
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/hide",method={RequestMethod.POST})
	public @ResponseBody String hide(String recordFlow, Model model,HttpServletRequest request){
		SrmExpertProj expertProj = expertProjBiz.read(recordFlow);
		expertProjBiz.saveForHide(expertProj);
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	/**
	 * 评审项目列表
	 * @param scoreType
	 * @param pjName
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public String list( String scoreType,String pjName, Model model,HttpServletRequest request){
		SrmExpertProjExt srmExpertProjExt = new SrmExpertProjExt();
		srmExpertProjExt.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		srmExpertProjExt.setRecordStatus(GlobalConstant.FLAG_Y);
		srmExpertProjExt.setAgreeFlag(GlobalConstant.FLAG_Y);//表示没有点过同意按钮的
		if(StringUtil.isNotBlank(scoreType)){
			if(scoreType.equals(GlobalConstant.FLAG_Y)){
				srmExpertProjExt.setScoreTotal(GlobalConstant.NOT_NULL);
			}else if(scoreType.equals(GlobalConstant.FLAG_N)){
				srmExpertProjExt.setScoreTotal(GlobalConstant.NULL);
			}
		} 
		if(StringUtil.isNotBlank(pjName)){
			srmExpertProjExt.setProjName("%"+pjName+"%");
		}
		SrmExpertGroupProjExt srmExpertGroupProjExt = new SrmExpertGroupProjExt();
		srmExpertGroupProjExt.setEvalStatusId(EvaluationStatusEnum.Submit.getId());//评审设置为提交的状态 专家评审才能看到
		srmExpertProjExt.setSrmExpertGroupProjExt(srmExpertGroupProjExt );
		//评审项目通知
		List<SrmExpertProjExt> expertProjList = this.expertProjBiz.searchExpertProj(srmExpertProjExt);
		model.addAttribute("expertProjList",expertProjList);
		return "srm/expert/proj/list";
	}
	
	/**
	 * 专家打分
	 * @param expertProjFlow
	 * @param model
	 * @return
	 * @throws DocumentException 
	 */
	@RequestMapping(value="/score",method={RequestMethod.GET})
	public String score(String expertProjFlow , Model model) throws DocumentException{
		//评分记录保存对象
		SrmExpertProj expertProj = expertProjBiz.read(expertProjFlow);
		model.addAttribute("expertProj", expertProj);
		
		SrmExpertProjEval groupProj = expertGroupProjBiz.searchSrmExpertGroupProjByFlow(expertProj.getEvalSetFlow());
		
		if(StringUtil.isNotBlank(groupProj.getSchemeFlow())){
			SrmGradeItem item = new SrmGradeItem();
			item.setSchemeFlow(groupProj.getSchemeFlow());
			List<SrmGradeItem> gradeItem = gradeItemBiz.searchGradeItem(item);
			model.addAttribute("gradeItem",gradeItem);
			model.addAttribute("schemeFlow",groupProj.getSchemeFlow());
		}
		//评分值
		Map<String,String> scoreItemMap = new HashMap<String, String>();
		String scoreItem = expertProj.getScoreItem();
		if(StringUtil.isNotBlank(scoreItem)){
			Document document = DocumentHelper.parseText(scoreItem);
			List<Element> eleList = document.selectNodes("/scoreItem/score");
			for(Element ele :eleList){
				if("2".equals(ele.attributeValue("itemType"))){//二级表单
					Node node = ele.selectSingleNode("/scoreItem/score[@itemFlow='"+ele.attributeValue("itemFlow")+"']/secScoreItem/scoreSum");
					if(null != node && StringUtil.isNotBlank(node.getText())){
						Double disScore = Double.valueOf(node.getText()) / Double.valueOf(ele.attributeValue("jzScore"));
						scoreItemMap.put(ele.attributeValue("itemFlow"), String.format("%.2f", disScore));
					}
				}else{
					scoreItemMap.put(ele.attributeValue("itemFlow"), ele.getText());
				}
			}
			model.addAttribute("scoreItemMap", scoreItemMap);
		}
		return "srm/expert/proj/score";
	}
	
	/**
	 * 保存分数
	 * @param paramObj
	 * @param schemeFlow
	 * @param status 提交 还是 保存
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saveScore",method={RequestMethod.POST})
	@ResponseBody
	public String saveScore(SrmExpertProj paramObj,String schemeFlow, String status , Model model,HttpServletRequest req) throws DocumentException{
		SrmExpertProj expertProj = expertProjBiz.read(paramObj.getExpertProjFlow());
		/*if(paramObj.getScoreResultId().equals(ExpertScoreResultEnum.Agree.getId())){
			expertProj.setScoreResultId(ExpertScoreResultEnum.Agree.getId()); 
			expertProj.setScoreResultName(ExpertScoreResultEnum.Agree.getName()); 
		}else if(paramObj.getScoreResultId().equals(ExpertScoreResultEnum.NotAgree.getId())){
			expertProj.setScoreResultId(ExpertScoreResultEnum.NotAgree.getId()); 
			expertProj.setScoreResultName(ExpertScoreResultEnum.NotAgree.getName());
		}else if(paramObj.getScoreResultId().equals(ExpertScoreResultEnum.FirstAgree.getId())){
            expertProj.setScoreResultId(ExpertScoreResultEnum.FirstAgree.getId());
            expertProj.setScoreResultName(ExpertScoreResultEnum.FirstAgree.getName());
        }*/
		if(StringUtil.isNotBlank(paramObj.getScoreResultId())){
			expertProj.setScoreResultId(paramObj.getScoreResultId());
			expertProj.setScoreResultName(DictTypeEnum.ExpertScoreResult.getDictNameById(paramObj.getScoreResultId()));
		}
		expertProj.setScoreTotal(paramObj.getScoreTotal());
		expertProj.setExpertOpinion(paramObj.getExpertOpinion());
		expertProj.setEvalStatusId(status);
		expertProj.setEvalStatusName(EvaluationStatusEnum.getNameById(status));
		
		//评分方案列表 ，用于项目切换评分方案需求
		SrmGradeItem item = new SrmGradeItem();
		item.setSchemeFlow(schemeFlow);
		List<SrmGradeItem> gradeItem = gradeItemBiz.searchGradeItem(item);
		
		Document document = DocumentHelper.createDocument();
		//建立根节点
		Element rootElement = document.addElement("scoreItem");
		for(SrmGradeItem temp : gradeItem){
			if("2".equals(temp.getItemType())){//二级表单
                if(StringUtil.isNotBlank(expertProj.getScoreItem())) {
                    Document doc = DocumentHelper.parseText(expertProj.getScoreItem());
                    Element secondEle = (Element) doc.selectSingleNode("/scoreItem/score[@itemFlow='" + temp.getItemFlow() + "']");
                    Element scoreEle = rootElement.addElement("score");
                    scoreEle.addAttribute("itemType", "2");
                    scoreEle.addAttribute("itemFlow", temp.getItemFlow());
                    if (null != secondEle) {//已存在二级表单评分信息
                        scoreEle.addAttribute("jzScore", secondEle.attributeValue("jzScore"));
                        scoreEle.appendContent((Element) secondEle.clone());//克隆secondEle节点下的内容
                    }
                }else{
                    return "请填写评分表单";
                }
			}else{
				Element scoreEle =  rootElement.addElement("score");
				scoreEle.addAttribute("itemFlow",temp.getItemFlow());
				scoreEle.setText(StringUtil.toHtml(req.getParameter(temp.getItemFlow())));
			}
		}
		expertProj.setScoreItem(document.asXML());
		expertProjBiz.modifyByFlow(expertProj) ;
		return GlobalConstant.SAVE_SUCCESSED;
	}

	//二级表单评分信息
	@RequestMapping(value="/secondForm",method={RequestMethod.GET})
	public String score(String expertProjFlow,String itemFlow,String formId,Model model) throws DocumentException{
		model.addAttribute("expertProjFlow",expertProjFlow);
		model.addAttribute("itemFlow",itemFlow);
		SrmExpertProj expertProj = expertProjBiz.read(expertProjFlow);
		if(null != expertProj && StringUtil.isNotBlank(expertProj.getScoreItem())) {
			Map<String, Object> map = new HashMap<>();//表单评分要素分值
			Map<String, Object> map2 = new HashMap<>();//表单评分要素备注
			Document doc = DocumentHelper.parseText(expertProj.getScoreItem());
			Element secScoreItem = (Element)doc.selectSingleNode("/scoreItem/score[@itemFlow='"+itemFlow+"']/secScoreItem");
			if(null != secScoreItem){
				List<Element> eleLst = secScoreItem.elements();
				if (null != eleLst && eleLst.size() > 0) {
					for (Element attr : eleLst) {
						map.put(attr.getName(), attr.getText());
						map2.put(attr.getName(), attr.attributeValue("remark"));
					}
					model.addAttribute("dataMap", map);
					model.addAttribute("remarkMap", map2);
				}
			}
		}
		if("101".equals(formId)) {//学科带头人 评分表
			return "srm/expert/form/subjectHeader";
		}else if("102".equals(formId)){//学科发展潜力 评分表
			return "srm/expert/form/subjectDevelopment";
		}else if("103".equals(formId)){//领军人才学术水平 评分表
			return "srm/expert/form/leaderAcademicLevel";
		}else if("104".equals(formId)){//团队发展潜力 评分表
			return "srm/expert/form/teamDevelopment";
		}else if("105".equals(formId)){//已有业绩与发展潜力 评分表
			return "srm/expert/form/exitAchievementAndDevelopment";
		}else if("106".equals(formId)){//个人业绩与发展潜力 评分表
			return "srm/expert/form/achievementAndDevelopment";
		}else if("107".equals(formId)){//（重点学科）学科带头人 评分表
			return "srm/expert/form/pointSubjectHeader";
		}else if("108".equals(formId)){//（重点学科）学科发展潜力 评分表
			return "srm/expert/form/pointSubjectDevelopment";
		}else{
			return "";
		}
	}

	//保存二级表单评分信息
	@RequestMapping(value="/saveSecondScore",method={RequestMethod.POST})
	@ResponseBody
	public String saveSecondScore(String expertProjFlow,String itemFlow,String jzScore,String [] score,String [] remark) throws DocumentException{
		SrmExpertProj expertProj = expertProjBiz.read(expertProjFlow);
		if(null != expertProj){
			if(StringUtil.isNotBlank(expertProj.getScoreItem())){//已评分过
				Document document = DocumentHelper.parseText(expertProj.getScoreItem());
				Element secondEle = (Element) document.selectSingleNode("/scoreItem/score[@itemFlow='"+itemFlow+"']");
				if(null != secondEle){//修改
					secondEle.remove((Element)secondEle.selectSingleNode("/scoreItem/score[@itemFlow='"+itemFlow+"']/secScoreItem"));
					Element secRootElement = secondEle.addElement("secScoreItem");
					for(int i=0;i<score.length;i++){
						Element scoreEle;
						if(i == score.length -1){
							scoreEle = secRootElement.addElement("scoreSum");
						}else{
							scoreEle = secRootElement.addElement("score"+(i+1));
							scoreEle.addAttribute("remark",remark[i]);//备注
						}
						scoreEle.setText(score[i]);
					}
					expertProj.setScoreItem(document.asXML());
				}else{//新增
					Element scoreEle =  ((Element) document.selectSingleNode("/scoreItem")).addElement("score");
					scoreEle.addAttribute("itemFlow",itemFlow);
					scoreEle.addAttribute("itemType","2");
					scoreEle.addAttribute("jzScore",jzScore);
					Element secondeElement = scoreEle.addElement("secScoreItem");
					for(int i=0;i<score.length;i++){
						Element secScoreEle = null;
						if(i == score.length -1){
							secScoreEle = secondeElement.addElement("scoreSum");
						}else{
							secScoreEle = secondeElement.addElement("score"+(i+1));
							secScoreEle.addAttribute("remark",remark[i]);//备注
						}
						secScoreEle.setText(score[i]);
					}
					expertProj.setScoreItem(document.asXML());
				}
			}else{//首次评分
				Document document = DocumentHelper.createDocument();
				Element rootElement = document.addElement("scoreItem");
				Element scoreEle =  rootElement.addElement("score");
				scoreEle.addAttribute("itemFlow",itemFlow);
				scoreEle.addAttribute("itemType","2");
				scoreEle.addAttribute("jzScore",jzScore);
				Element secondeElement = scoreEle.addElement("secScoreItem");
				for(int i=0;i<score.length;i++){
					Element secScoreEle = null;
					if(i == score.length -1){
						secScoreEle = secondeElement.addElement("scoreSum");
					}else{
						secScoreEle = secondeElement.addElement("score"+(i+1));
						secScoreEle.addAttribute("remark",remark[i]);//备注
					}
					secScoreEle.setText(score[i]);
				}
				expertProj.setScoreItem(document.asXML());
			}
			expertProjBiz.modifyByFlow(expertProj) ;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	/**
	 * 完善专家信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/finishInfo",method={RequestMethod.GET})
	public String finishInfo(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			model.addAttribute("user", user);
			SrmExpert expert = expertBiz.readExpert(userFlow);
			model.addAttribute("expert", expert);
		}
		return "srm/expert/finishInfo";
	}
}
