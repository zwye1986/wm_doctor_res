package com.pinde.sci.biz.pub.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IEduResumeBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.form.pub.EduResumeForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tiger
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EduResumeBizImpl implements IEduResumeBiz {
	@Autowired
	private IPubUserResumeBiz pubResumeBiz;

	@Override
	public List<EduResumeForm> queryEduResumeList(PubUserResume resume) throws Exception {
		List<EduResumeForm> eduFormList = null;
		if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
			Document dom = DocumentHelper.parseText(resume.getUserResume());
			String xpath = "/rusume/education/record";
			List<Element> recordElements = dom.selectNodes(xpath);
			if(null != recordElements && !recordElements.isEmpty()){
				eduFormList = new ArrayList<EduResumeForm>();
				EduResumeForm form = null;
				for(Element e : recordElements){
					form = new EduResumeForm();
					form.setRecordId(e.attributeValue("recordId"));//id
					form.setStartDate(e.element("startDate").getTextTrim());
					form.setEndDate(e.element("endDate").getTextTrim());
					form.setCollege(e.element("college").getTextTrim());
					form.setMajor(e.element("major").getTextTrim());
					form.setEducation(e.element("education").getTextTrim());
					form.setDegree(e.element("degree").getTextTrim());
					eduFormList.add(form);
				}
			}
		}
		return eduFormList;
	}

	@Override
	public int saveEduResume(SysUser user, EduResumeForm form) throws Exception {
		if(null != user && null != form){
			Document dom ;
			Element root ;
			Element eduElement;

			String startDate = form.getStartDate();
			String endDate = form.getEndDate();
			String college = form.getCollege();
			String major = form.getMajor();
			String education = form.getEducation();
			String degree = form.getDegree();

			//获取个人信息
			PubUserResume resume = pubResumeBiz.readPubUserResume(user.getUserFlow());

			if(null != resume && null != resume.getUserResume()){//存在个人简历--》修改
				dom = DocumentHelper.parseText(resume.getUserResume());
				root = dom.getRootElement();

				//有无教育经历记录？无则创建一个education节点
				String xpath = "/rusume/education";
				eduElement = (Element)dom.selectSingleNode(xpath);
				if(null != eduElement){
					eduElement = root.element("education");

				}else{
					eduElement = root.addElement("education");
				}
			}else{//新增个人简历
				resume = new PubUserResume();

				dom = DocumentHelper.createDocument();
				root = dom.addElement("rusume");
				eduElement = root.addElement("education");
			}

			String xpath = "/rusume/education/record[@recordId = '" + form.getRecordId() + "']";
			Element recordElement = (Element)dom.selectSingleNode(xpath);
			//根据 record id存在 判断新增或修改 个人简历中的教育经历

			if(null != recordElement){
				recordElement.addAttribute("recordId",form.getRecordId());
				recordElement.element("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
				recordElement.element("endDate").setText(StringUtil.defaultIfEmpty(endDate, ""));
				recordElement.element("college").setText(StringUtil.defaultIfEmpty(college, ""));
				recordElement.element("major").setText(StringUtil.defaultIfEmpty(major, ""));
				recordElement.element("education").setText(StringUtil.defaultIfEmpty(education, ""));
				recordElement.element("degree").setText(StringUtil.defaultIfEmpty(degree, ""));
			}else{
				String recordId = PkUtil.getUUID();
				recordElement = eduElement.addElement("record").addAttribute("recordId", recordId);
				recordElement.addElement("startDate").setText(StringUtil.defaultIfEmpty(startDate, ""));
				recordElement.addElement("endDate").setText(StringUtil.defaultIfEmpty(endDate, ""));
				recordElement.addElement("college").setText(StringUtil.defaultIfEmpty(college, ""));
				recordElement.addElement("major").setText(StringUtil.defaultIfEmpty(major, ""));
				recordElement.addElement("education").setText(StringUtil.defaultIfEmpty(education, ""));
				recordElement.addElement("degree").setText(StringUtil.defaultIfEmpty(degree, ""));
			}
			//CLOB
			resume.setUserResume(dom.asXML());
			int result = pubResumeBiz.savePubUserResume(user, resume);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.ONE_LINE;
			}

		}
		return GlobalConstant.ZERO_LINE;
	}

	/**
	 * 获取一条教育经历
	 */
	@Override
	public EduResumeForm getEduResumeByRecordId(String userFlow, String recordId) throws Exception {
		EduResumeForm eduForm = null;
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
			PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
			if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
				Document dom = DocumentHelper.parseText(resume.getUserResume());
				String xpath = "/rusume/education/record[@recordId = '"+recordId+"']";
				Element e = (Element) dom.selectSingleNode(xpath);
				if(null != e){
					eduForm = new EduResumeForm();
					eduForm.setRecordId(e.attributeValue("recordId"));
					eduForm.setStartDate(e.element("startDate").getTextTrim());
					eduForm.setEndDate(e.element("endDate").getTextTrim());
					eduForm.setCollege(e.element("college").getTextTrim());
					eduForm.setMajor(e.element("major").getTextTrim());
					eduForm.setEducation(e.element("education").getTextTrim());
					eduForm.setDegree(e.element("degree").getTextTrim());
				}
			}
		}
		return eduForm;

	}

	/**
	 * 删除一条教育经历
	 */
	@Override
	public int delEduResumeByRecordId(String userFlow, String recordId) throws Exception {
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordId)){
			PubUserResume resume = pubResumeBiz.readPubUserResume(userFlow);
			if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
				Document dom = DocumentHelper.parseText(resume.getUserResume());
				String xpath = "/rusume/education/record[@recordId = '"+recordId+"']";

				//List list = dom.selectNodes(xpath);//

				Element e = (Element) dom.selectSingleNode(xpath);//用xpath查找对象
				if(null != e){
					/*List list = dom.selectNodes(xpath);// 用xpath查找对象

					//创建迭代器，用来查找要删除的节点,迭代器相当于指针，指向其下所有的节点
					Iterator iter = list.iterator();
					while (iter.hasNext()) {
						Element nextE = (Element) iter.next();
						e.remove(nextE);
					}*/
					Element parentE =  e.getParent();
					parentE.remove(e);
				}
				resume.setUserResume(dom.asXML());
				int result = pubResumeBiz.savePubUserResume(null, resume);
				if(result != GlobalConstant.ZERO_LINE){
					return GlobalConstant.ONE_LINE;
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

}
