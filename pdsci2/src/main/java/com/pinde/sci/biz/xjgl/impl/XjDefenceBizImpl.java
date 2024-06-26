package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IXjDefenceBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.NydbCommitteeMemberMapper;
import com.pinde.sci.dao.base.NydbDefenceApplyMapper;
import com.pinde.sci.dao.base.NydbPaperTitleMapper;
import com.pinde.sci.dao.xjgl.XjDefenceExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.xjgl.XjAuthorInfoForm;
import com.pinde.sci.form.xjgl.XjAuthorOrgForm;
import com.pinde.sci.form.xjgl.XjPaperTitleExtForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.NydbDefenceApplyExample.Criteria;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjDefenceBizImpl implements IXjDefenceBiz {
	@Autowired
	private NydbDefenceApplyMapper applyMapper;
	@Autowired
	private NydbPaperTitleMapper titleMapper;
	@Autowired
	private NydbCommitteeMemberMapper memberMapper;
	@Autowired
	private XjDefenceExtMapper defenceExtMapper;
	@Autowired
	private IUserBiz userBiz;

	@Override
	public List<NydbDefenceApply> queryDefenceList(NydbDefenceApply defence) {
		NydbDefenceApplyExample example = new NydbDefenceApplyExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		setCriteria(defence,criteria);
		//导师
		if(StringUtil.isNotBlank(defence.getTutorFlow())){
			criteria.andTutorFlowEqualTo(defence.getTutorFlow());
		}
		//导师名称
		if(StringUtil.isNotBlank(defence.getTutorName())){
			criteria.andTutorNameLike("%"+defence.getTutorName()+"%");

		}
		if(StringUtil.isNotBlank(defence.getTwoTutorFlow()) || StringUtil.isNotBlank(defence.getTutorName())){
			setCriteria(defence,criteria2);
			if(StringUtil.isNotBlank(defence.getTwoTutorFlow())){
				criteria2.andTwoTutorFlowEqualTo(defence.getTwoTutorFlow());
			}else{
				criteria2.andTwoTutorNameLike("%"+defence.getTutorName()+"%");
			}
			example.or(criteria2);
		}
		return applyMapper.selectByExample(example);
	}
	private void setCriteria(NydbDefenceApply defence,Criteria criteria){
		//学生流水号
		if(StringUtil.isNotBlank(defence.getUserFlow())){
			criteria.andUserFlowEqualTo(defence.getUserFlow());
		}
		//学号
		if(StringUtil.isNotBlank(defence.getStuNo())){
			criteria.andStuNoLike("%"+defence.getStuNo()+"%");
		}
		//姓名
		if(StringUtil.isNotBlank(defence.getUserName())){
			criteria.andUserNameLike("%"+defence.getUserName()+"%");
		}
		//专业
		if(StringUtil.isNotBlank(defence.getMajorId())){
			criteria.andMajorIdEqualTo(defence.getMajorId());
		}
		//培养层次
		if(StringUtil.isNotBlank(defence.getTrainGradationId())){
			criteria.andTrainGradationIdEqualTo(defence.getTrainGradationId());
		}
		//培养类型
		if(StringUtil.isNotBlank(defence.getTrainCategoryId())){
			criteria.andTrainCategoryIdEqualTo(defence.getTrainCategoryId());
		}
		//答辩申请时间
		if(StringUtil.isNotBlank(defence.getDefenceTime())){
			criteria.andDefenceTimeEqualTo(defence.getDefenceTime());
		}
		//培养单位
		if(StringUtil.isNotBlank(defence.getPydwOrgFlow())){
			criteria.andPydwOrgFlowEqualTo(defence.getPydwOrgFlow());
		}
		//分委会
		if(StringUtil.isNotBlank(defence.getFwhOrgFlow())){
			criteria.andFwhOrgFlowEqualTo(defence.getFwhOrgFlow());
		}
		//保存/提交状态
		if(StringUtil.isNotBlank(defence.getStatusId())){
			criteria.andStatusIdEqualTo(defence.getStatusId());
		}
		//培养单位审核状态
		if(StringUtil.isNotBlank(defence.getPydwAuditId())){
			criteria.andPydwAuditIdEqualTo(defence.getPydwAuditId());
		}
		//是否具有正式答辩资格
		if(StringUtil.isNotBlank(defence.getFormalDefenceFlag())){
			criteria.andFormalDefenceFlagEqualTo(defence.getFormalDefenceFlag());
		}
		//答辩结果
		if(StringUtil.isNotBlank(defence.getDefenceResultFlag())){
			criteria.andDefenceResultFlagEqualTo(defence.getDefenceResultFlag());
		}
		//性别
		if(StringUtil.isNotBlank(defence.getSexId())){
			criteria.andSexIdEqualTo(defence.getSexId());
		}
		//盲审
		if(StringUtil.isNotBlank(defence.getAuditResultFlag())){
			criteria.andAuditResultFlagEqualTo(defence.getAuditResultFlag());
		}
		//学习形式
		if(StringUtil.isNotBlank(defence.getStudyFormId())){
			criteria.andStudyFormIdEqualTo(defence.getStudyFormId());
		}
		//学位补授
		if(StringUtil.isNotBlank(defence.getIsReplenish())){
			criteria.andIsReplenishEqualTo(defence.getIsReplenish());
		}else{
			criteria.andIsReplenishIsNull();
		}
		//学位补授时间
		if(StringUtil.isNotBlank(defence.getReplenishTime())){
			criteria.andReplenishTimeEqualTo(defence.getReplenishTime());
		}
	}

	@Override
	public NydbDefenceApply queryDefenceByFlow(String recordFlow) {
		return applyMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public Map<String, Object> queryBaseInfo(String userFlow) {
		return defenceExtMapper.queryBaseInfo(userFlow);
	}

	@Override
	public int saveDefence(NydbDefenceApply defence) {
		int num = 0;
		//提交后初始化审核状态
		if("Submit".equals(defence.getStatusId())){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("xjgl_audit_tutor"))){
				if(StringUtil.isNotBlank(defence.getTutorFlow())){
					defence.setTutorAuditId("Passing");
					defence.setTutorAuditName("待审核");
				}
				if(StringUtil.isNotBlank(defence.getTwoTutorFlow())){
					defence.setTwoTutorAuditId("Passing");
					defence.setTwoTutorAuditName("待审核");
				}
			}else{
				defence.setPydwAuditId("Passing");
				defence.setPydwAuditName("待审核");
			}
		}
		if(StringUtil.isNotBlank(defence.getRecordFlow())){
			GeneralMethod.setRecordInfo(defence,false);
			num = applyMapper.updateByPrimaryKeySelective(defence);
		}else{
			defence.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(defence,true);
			num = applyMapper.insertSelective(defence);
			if(StringUtil.isNotBlank(defence.getIsReplenish())&&"Y".equals(defence.getIsReplenish())){//获取学位论文旧数据
				Map<String,String> pMap=new HashMap<>();
				pMap.put("userFlow",defence.getUserFlow());
				List<Map<String,Object>> paperTitleList=queryPaperTitleList(pMap);
				if(paperTitleList!=null&&paperTitleList.size()>0){
					for (Map<String,Object> paperMap:paperTitleList) {
						if(StringUtil.isNotBlank((String)paperMap.get("recordFlow"))){
							NydbPaperTitle title=titleMapper.selectByPrimaryKey((String)paperMap.get("recordFlow"));
							if(title!=null){
								title.setRecordFlow(PkUtil.getUUID());
								title.setDefenceFlow(defence.getRecordFlow());
								GeneralMethod.setRecordInfo(title,true);
								titleMapper.insertSelective(title);
							}
						}
					}
				}

			}
		}
		return num;
	}

	@Override
	public int delDefence(String recordFlow) {
		return applyMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public List<Map<String, Object>> queryPaperTitleList(Map<String, String> params) {
		return defenceExtMapper.queryPaperTitleList(params);
	}

	@Override
	public int delTitle(String recordFlow) {
		return titleMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public NydbPaperTitle queryTitleByFlow(String recordFlow) {
		return titleMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public XjPaperTitleExtForm parseExtInfoXml(String content) {
		XjPaperTitleExtForm extInfo = null;
		if(StringUtil.isNotBlank(content)){
			try{
				extInfo = new XjPaperTitleExtForm();
				Document doc = DocumentHelper.parseText(content);
				Element root = doc.getRootElement();
				Element extInfoFormEle = root.element("xjPaperTitleExtForm");
				if(extInfoFormEle!=null){
					List<Element> extInfoAttrEles = extInfoFormEle.elements();
					if(extInfoAttrEles!=null && extInfoAttrEles.size()>0){
						for(Element attr : extInfoAttrEles){
							String attrName = attr.getName();
							String attrValue = attr.getText();
							setValue(extInfo,attrName,attrValue);
						}
					}
				}
				//共同第一作者
				Element coListEle = root.element("coAuthorList");
				if(coListEle!=null){
					List<Element> coEles = coListEle.elements();
					if(coEles!=null && coEles.size()>0){
						List<XjAuthorInfoForm> coList = new ArrayList<XjAuthorInfoForm>();
						for(Element coEle : coEles){
							XjAuthorInfoForm sub = new XjAuthorInfoForm();
							List<Element> coAttrEles = coEle.elements();
							if(coAttrEles!=null && coAttrEles.size()>0){
								for(Element attr : coAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(sub,attrName,attrValue);
								}
							}
							Element orgListEle = coEle.element("coAuthorOrgList");
							if(orgListEle!=null){
								List<Element> orgEles = orgListEle.elements();
								if(orgEles!=null && orgEles.size()>0){
									List<XjAuthorOrgForm> orgList = new ArrayList<XjAuthorOrgForm>();
									for(Element orgEle : orgEles){
										XjAuthorOrgForm orgSub = new XjAuthorOrgForm();
										List<Element> orgAttrEles = orgEle.elements();
										if(orgAttrEles!=null && orgAttrEles.size()>0){
											for(Element attr : orgAttrEles){
												String attrName = attr.getName();
												String attrValue = attr.getText();
												setValue(orgSub,attrName,attrValue);
											}
										}
										orgList.add(orgSub);
									}
									sub.setCoAuthorOrgList(orgList);
								}
							}
							coList.add(sub);
						}
						extInfo.setCoAuthorList(coList);
					}
				}
				//共同通讯作者
				Element messListEle = root.element("messAuthorList");
				if(messListEle!=null){
					List<Element> messEles = messListEle.elements();
					if(messEles!=null && messEles.size()>0){
						List<XjAuthorInfoForm> messList = new ArrayList<XjAuthorInfoForm>();
						for(Element messEle : messEles){
							XjAuthorInfoForm sub = new XjAuthorInfoForm();
							List<Element> messAttrEles = messEle.elements();
							if(messAttrEles!=null && messAttrEles.size()>0){
								for(Element attr : messAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(sub,attrName,attrValue);
								}
							}
							Element orgListEle = messEle.element("messAuthorOrgList");
							if(orgListEle!=null){
								List<Element> orgEles = orgListEle.elements();
								if(orgEles!=null && orgEles.size()>0){
									List<XjAuthorOrgForm> orgList = new ArrayList<XjAuthorOrgForm>();
									for(Element orgEle : orgEles){
										XjAuthorOrgForm orgSub = new XjAuthorOrgForm();
										List<Element> orgAttrEles = orgEle.elements();
										if(orgAttrEles!=null && orgAttrEles.size()>0){
											for(Element attr : orgAttrEles){
												String attrName = attr.getName();
												String attrValue = attr.getText();
												setValue(orgSub,attrName,attrValue);
											}
										}
										orgList.add(orgSub);
									}
									sub.setMessAuthorOrgList(orgList);
								}
							}
							messList.add(sub);
						}
						extInfo.setMessAuthorList(messList);
					}
				}
				//本人作者单位
				Element orgListEle = root.element("ownerOrgList");
				if(orgListEle!=null){
					List<Element> orgEles = orgListEle.elements();
					if(orgEles!=null && orgEles.size()>0){
						List<XjAuthorOrgForm> orgList = new ArrayList<XjAuthorOrgForm>();
						for(Element orgEle : orgEles){
							XjAuthorOrgForm sub = new XjAuthorOrgForm();
							List<Element> orgAttrEles = orgEle.elements();
							if(orgAttrEles!=null && orgAttrEles.size()>0){
								for(Element attr : orgAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(sub,attrName,attrValue);
								}
							}
							orgList.add(sub);
						}
						extInfo.setOwnerOrgList(orgList);
					}
				}
				//所在单位（第一作者）
				Element orgListEle2 = root.element("authorOrgList");
				if(orgListEle2!=null){
					List<Element> orgEles = orgListEle2.elements();
					if(orgEles!=null && orgEles.size()>0){
						List<XjAuthorOrgForm> orgList = new ArrayList<XjAuthorOrgForm>();
						for(Element orgEle : orgEles){
							XjAuthorOrgForm sub = new XjAuthorOrgForm();
							List<Element> orgAttrEles = orgEle.elements();
							if(orgAttrEles!=null && orgAttrEles.size()>0){
								for(Element attr : orgAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(sub,attrName,attrValue);
								}
							}
							orgList.add(sub);
						}
						extInfo.setAuthorOrgList(orgList);
					}
				}
				//通讯作者单位
				Element orgListEle3 = root.element("messAuthorOrgList");
				if(orgListEle3!=null){
					List<Element> orgEles = orgListEle3.elements();
					if(orgEles!=null && orgEles.size()>0){
						List<XjAuthorOrgForm> orgList = new ArrayList<XjAuthorOrgForm>();
						for(Element orgEle : orgEles){
							XjAuthorOrgForm sub = new XjAuthorOrgForm();
							List<Element> orgAttrEles = orgEle.elements();
							if(orgAttrEles!=null && orgAttrEles.size()>0){
								for(Element attr : orgAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(sub,attrName,attrValue);
								}
							}
							orgList.add(sub);
						}
						extInfo.setMessAuthorOrgList(orgList);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return extInfo;
	}

	//为对象自动复制
	private void setValue(Object obj,String attrName,String attrValue) {
		try {
			Class<?> objClass = obj.getClass();
			String firstLetter = attrName.substring(0, 1).toUpperCase();
			String methedName = "set" + firstLetter + attrName.substring(1);
			Method setMethod = objClass.getMethod(methedName, new Class[]{String.class});
			setMethod.invoke(obj, new Object[]{attrValue});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveTitle(NydbPaperTitle title, XjPaperTitleExtForm extInfo) {
		String content = getXmlFromExtInfo(extInfo);
		title.setPaperContent(content);
		if(StringUtil.isNotBlank(title.getRecordFlow())){
			GeneralMethod.setRecordInfo(title,false);
			return titleMapper.updateByPrimaryKeySelective(title);
		}else{
			title.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(title,true);
			return titleMapper.insertSelective(title);
		}
	}
	//将form对象封装为xml文本
	public String getXmlFromExtInfo(XjPaperTitleExtForm extInfo){
		String xmlBody = null;
		if(extInfo!=null){
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("paperTitleRoot");
			Element extInfoForm = root.addElement("xjPaperTitleExtForm");
			Map<String,String> filedMap = getClassFieldMap(extInfo);
			if(filedMap!=null && filedMap.size()>0){
				for(String key : filedMap.keySet()){
					Element item = extInfoForm.addElement(key);
					item.setText(filedMap.get(key));
				}
			}
			//共同第一作者
			List<XjAuthorInfoForm> coList = extInfo.getCoAuthorList();
			if(coList!=null && coList.size()>0){
				Element coListEle = root.addElement("coAuthorList");
				for(XjAuthorInfoForm co : coList){
					Element coEle = coListEle.addElement("xjAuthorInfoForm");
					Map<String,String> coFiledMap = getClassFieldMap(co);
					if(coFiledMap!=null && coFiledMap.size()>0){
						for(String key : coFiledMap.keySet()){
							Element item = coEle.addElement(key);
							item.setText(coFiledMap.get(key));
						}
					}
					List<XjAuthorOrgForm> orgList = co.getCoAuthorOrgList();
					if(orgList!=null && orgList.size()>0){
						Element coOrgListEle = coEle.addElement("coAuthorOrgList");
						for(XjAuthorOrgForm org : orgList){
							Element coOrgEle = coOrgListEle.addElement("xjAuthorOrgForm");
							Map<String,String> coOrgFiledMap = getClassFieldMap(org);
							if(coOrgFiledMap!=null && coOrgFiledMap.size()>0){
								for(String key : coOrgFiledMap.keySet()){
									Element item = coOrgEle.addElement(key);
									item.setText(coOrgFiledMap.get(key));
								}
							}
						}
					}
				}
			}
			//共同通讯作者
			List<XjAuthorInfoForm> messList = extInfo.getMessAuthorList();
			if(messList!=null && messList.size()>0){
				Element messListEle = root.addElement("messAuthorList");
				for(XjAuthorInfoForm mess : messList){
					Element messEle = messListEle.addElement("xjAuthorInfoForm");
					Map<String,String> messFiledMap = getClassFieldMap(mess);
					if(messFiledMap!=null && messFiledMap.size()>0){
						for(String key : messFiledMap.keySet()){
							Element item = messEle.addElement(key);
							item.setText(messFiledMap.get(key));
						}
					}
					List<XjAuthorOrgForm> orgList = mess.getMessAuthorOrgList();
					if(orgList!=null && orgList.size()>0){
						Element messOrgListEle = messEle.addElement("messAuthorOrgList");
						for(XjAuthorOrgForm org : orgList){
							Element messOrgEle = messOrgListEle.addElement("xjAuthorOrgForm");
							Map<String,String> messOrgFiledMap = getClassFieldMap(org);
							if(messOrgFiledMap!=null && messOrgFiledMap.size()>0){
								for(String key : messOrgFiledMap.keySet()){
									Element item = messOrgEle.addElement(key);
									item.setText(messOrgFiledMap.get(key));
								}
							}
						}
					}
				}
			}
			//本人作者单位
			List<XjAuthorOrgForm> orgList = extInfo.getOwnerOrgList();
			if(orgList!=null && orgList.size()>0){
				Element orgListEle = root.addElement("ownerOrgList");
				for(XjAuthorOrgForm org : orgList){
					Element orgEle = orgListEle.addElement("xjAuthorOrgForm");
					Map<String,String> orgFiledMap = getClassFieldMap(org);
					if(orgFiledMap!=null && orgFiledMap.size()>0){
						for(String key : orgFiledMap.keySet()){
							Element item = orgEle.addElement(key);
							item.setText(orgFiledMap.get(key));
						}
					}
				}
			}
			//所在单位（第一作者）
			List<XjAuthorOrgForm> orgList2 = extInfo.getAuthorOrgList();
			if(orgList2!=null && orgList2.size()>0){
				Element orgListEle = root.addElement("authorOrgList");
				for(XjAuthorOrgForm org : orgList2){
					Element orgEle = orgListEle.addElement("xjAuthorOrgForm");
					Map<String,String> orgFiledMap = getClassFieldMap(org);
					if(orgFiledMap!=null && orgFiledMap.size()>0){
						for(String key : orgFiledMap.keySet()){
							Element item = orgEle.addElement(key);
							item.setText(orgFiledMap.get(key));
						}
					}
				}
			}
			//通讯作者单位
			List<XjAuthorOrgForm> orgList3 = extInfo.getMessAuthorOrgList();
			if(orgList3!=null && orgList3.size()>0){
				Element orgListEle = root.addElement("messAuthorOrgList");
				for(XjAuthorOrgForm org : orgList3){
					Element orgEle = orgListEle.addElement("xjAuthorOrgForm");
					Map<String,String> orgFiledMap = getClassFieldMap(org);
					if(orgFiledMap!=null && orgFiledMap.size()>0){
						for(String key : orgFiledMap.keySet()){
							Element item = orgEle.addElement(key);
							item.setText(orgFiledMap.get(key));
						}
					}
				}
			}
			xmlBody=doc.asXML();
		}
		return xmlBody;
	}

	//获取属性名和值
	private Map<String,String> getClassFieldMap(Object obj){
		Map<String,String> filedMap = null;
		if(obj!=null){
			try{
				filedMap = new HashMap<String, String>();
				String stringClassName = String.class.getSimpleName();
				Class<?> objClass = obj.getClass();
				Field[] fileds = objClass.getDeclaredFields();
				for(Field f : fileds){
					String typeName = f.getType().getSimpleName();
					if(stringClassName.equals(typeName)){
						String attrName = f.getName();
						String firstLetter = attrName.substring(0,1).toUpperCase();
						String methedName = "get"+firstLetter+attrName.substring(1);
						Method getMethod = objClass.getMethod(methedName);
						String value = (String)getMethod.invoke(obj);
						filedMap.put(attrName,StringUtil.defaultString(value));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return filedMap;
	}

	@Override
	public List<NydbCommitteeMember> queryMemberList(String recordFlow) {
		NydbCommitteeMemberExample example = new NydbCommitteeMemberExample();
		example.createCriteria().andDefenceFlowEqualTo(recordFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME");
		return memberMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int saveSchedule(NydbDefenceApply defence, List<NydbCommitteeMember> memberList) {
		int count = applyMapper.updateByPrimaryKeySelective(defence);
		//获取此次维护且已存在的委员名单
		List<String> recordFlows = new ArrayList<>();
		for(int i=0; i<memberList.size(); i++){
			if(StringUtil.isNotBlank(memberList.get(i).getRecordFlow())){
				recordFlows.add(memberList.get(i).getRecordFlow());
			}
		}
		//删除不在此次维护的委员名单
		NydbCommitteeMemberExample example = new NydbCommitteeMemberExample();
		NydbCommitteeMemberExample.Criteria criteria = example.createCriteria().andDefenceFlowEqualTo(defence.getRecordFlow())
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(!recordFlows.isEmpty()){
			criteria.andRecordFlowNotIn(recordFlows);
		}
		memberMapper.deleteByExample(example);
		for(int i=0; i<memberList.size(); i++){
			String recordFlow = memberList.get(i).getRecordFlow();
			if(StringUtil.isBlank(recordFlow)){
				memberList.get(i).setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(memberList.get(i),true);
				count += memberMapper.insertSelective(memberList.get(i));
			}else{
				GeneralMethod.setRecordInfo(memberList.get(i),false);
				count += memberMapper.updateByPrimaryKeySelective(memberList.get(i));
			}
		}
		return count;
	}
	@Override
	public int importDefenceFromExcel(MultipartFile file) throws Exception {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			return parseExcel(wb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
		// EXCEL2003使用的是微软的文件系统
		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
			return new HSSFWorkbook(inS);
		}
		// EXCEL2007使用的是OOM文件格式
		if (POIXMLDocument.hasOOXMLHeader(inS)) {
			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
			return new XSSFWorkbook(OPCPackage.open(inS));
		}
		throw new IOException("不能解析的excel版本");
	}
	private int parseExcel(Workbook wb) throws Exception {
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try {
				sheet = wb.getSheetAt(0);
			} catch (Exception e) {
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR = sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for (int i = 0; i < cell_num; i++) {
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title.trim());
			}
			if (row_num < 1) {
				throw new RuntimeException("没有数据");
			}
			for (int i = 1; i <= row_num; i++) {
				Row r = sheet.getRow(i);
				if(null == r){
					continue;
				}
				SysUser sysUser = new SysUser();
				NydbDefenceApply defenceApply=new NydbDefenceApply();

				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if ("学号".equals(colnames.get(j))) {
						sysUser.setUserCode(value);
						defenceApply.setStuNo(value);
					} else if ("姓名".equals(colnames.get(j))) {
						defenceApply.setUserName(value);
					} else if ("答辩时间".equals(colnames.get(j))) {
						List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("DefenceTime");
						for (SysDict dict : dictList) {
							if (dict.getDictName().equals(value)) {
								defenceApply.setDefenceTime(value);
							}
						}
						if(StringUtil.isBlank(defenceApply.getDefenceTime())){
							throw new RuntimeException("第"+(count+1)+"行答辩时间和所维护的时间不匹配！");
						}
					} else if ("学位补授时间".equals(colnames.get(j))) {
						List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("ReplenishTime");
						for (SysDict dict : dictList) {
							if (dict.getDictName().equals(value)) {
								defenceApply.setReplenishTime(value);
							}
						}
						if(StringUtil.isBlank(defenceApply.getReplenishTime())){
							throw new RuntimeException("第"+(count+1)+"行学位补授时间和所维护的时间不匹配！");
						}else{
							defenceApply.setIsReplenish(GlobalConstant.RECORD_STATUS_Y);
						}
					} else if ("中文论文题目".equals(colnames.get(j))) {
						defenceApply.setPaperChiTitle(value);
					} else if ("英文论文题目".equals(colnames.get(j))) {
						defenceApply.setPaperEngTitle(value);
					}else if ("研究方向".equals(colnames.get(j))) {
						defenceApply.setResearchDirection(value);
					} else if ("关键字".equals(colnames.get(j))) {
						defenceApply.setKeyWord(value);
					} else if ("培养单位审核".equals(colnames.get(j))) {
						defenceApply.setPydwAuditId("Passing");
						defenceApply.setPydwAuditName("待审核");
						if("审核不通过".equals(value)){
							defenceApply.setPydwAuditId("UnPassed");
							defenceApply.setPydwAuditName("审核不通过");
						}
						if("审核通过".equals(value)){
							defenceApply.setPydwAuditId("Passed");
							defenceApply.setPydwAuditName("审核通过");
						}
					}
				}
				if (StringUtil.isNotBlank(sysUser.getUserCode())) {
					SysUser newUser=userBiz.findByUserCode(sysUser.getUserCode());
					if(newUser!=null){
						Map<String,Object> baseInfo = defenceExtMapper.queryBaseInfo(newUser.getUserFlow());
						if(baseInfo!=null){
							if(StringUtil.isNotBlank((String)baseInfo.get("userName"))&&((String)baseInfo.get("userName")).equals(defenceApply.getUserName())){
								defenceApply.setUserName((String)baseInfo.get("userName"));
							}else{
								throw new RuntimeException("第"+(count+1)+"行学号和姓名不匹配！");
							}
							defenceApply.setUserFlow((String)baseInfo.get("userFlow"));
							defenceApply.setSexId((String)baseInfo.get("sexId"));
							defenceApply.setSexName((String)baseInfo.get("sexName"));
							defenceApply.setUserPhone((String)baseInfo.get("userPhone"));
							defenceApply.setMajorId((String)baseInfo.get("majorId"));
							defenceApply.setMajorName((String)baseInfo.get("majorName"));
							defenceApply.setTrainGradationId((String)baseInfo.get("trainTypeId"));
							defenceApply.setTrainGradationName((String)baseInfo.get("trainTypeName"));
							defenceApply.setTrainCategoryId((String)baseInfo.get("trainCategoryId"));
							defenceApply.setTrainCategoryName((String)baseInfo.get("trainCategoryName"));
							defenceApply.setTutorFlow((String)baseInfo.get("firstTeacherFlow"));
							defenceApply.setTutorName((String)baseInfo.get("firstTeacher"));
							defenceApply.setTutorPhone((String)baseInfo.get("firstTeacherPhone"));
							defenceApply.setTwoTutorFlow((String)baseInfo.get("secondTeacherFlow"));
							defenceApply.setTwoTutorName((String)baseInfo.get("secondTeacher"));
							defenceApply.setTwoTutorPhone((String)baseInfo.get("secondTeacherPhone"));
							defenceApply.setPydwOrgFlow((String)baseInfo.get("orgFlow"));
							defenceApply.setPydwOrgName((String)baseInfo.get("orgName"));
							defenceApply.setFwhOrgFlow((String)baseInfo.get("deptFlow"));
							defenceApply.setFwhOrgName((String)baseInfo.get("deptName"));
							defenceApply.setStudyFormId((String)baseInfo.get("studyFormId"));
							defenceApply.setStudyFormName((String)baseInfo.get("studyFormName"));
							defenceApply.setStatusId("Submit");
							defenceApply.setStatusName("提交");
							defenceApply.setPaperAuditName("12121_"+baseInfo.get("majorId")+"_"+baseInfo.get("sid")+"_LW");
						}
					}else{
						throw new RuntimeException("第"+(count+1)+"行没有此学号的学生！");
					}
				}else{
					throw new RuntimeException("第"+(count+1)+"行学号有误！");
				}
				if(StringUtil.isNotBlank(defenceApply.getRecordFlow())){
					GeneralMethod.setRecordInfo(defenceApply,false);
					applyMapper.updateByPrimaryKeySelective(defenceApply);
				}else{
					defenceApply.setRecordFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(defenceApply,true);
					applyMapper.insertSelective(defenceApply);
				}
				count++;
			}
		}
		return count;
	}

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}
}
