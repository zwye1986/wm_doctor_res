package com.pinde.res.biz.eval.impl;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.res.ctrl.eval.EvalResultBean;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.res.dao.eval.ext.EvalAppMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SysUserExample.Criteria;
import com.pinde.sci.util.FtpHelperUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class EvalAppBizImpl implements IEvalAppBiz {

	@Resource
	private EvalAppMapper evalAppMapper;
	@Autowired
	private ExpertEvalResultMapper resultMapper;
	@Autowired
	private ExpertEvalCfgMapper evalCfgMapper;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private SysOrgMapper orgMapper;
	//根据用户名获取用户信息
	@Override
	public SysUser getUserByCode(String userCode)
	{
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysUser> sysUserList = userMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;

	}

	@Override
	public SysUser readSysUser(String userFlow){
		return userMapper.selectByPrimaryKey(userFlow);
	}

	//获取用户拥有的角色列表
	@Override
	public List<SysUserRole> getSysUserRole(String userFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andUserFlowEqualTo(userFlow);
		return userRoleMapper.selectByExample(example);
	}

	//获取系统配置信息
	@Override
	public String getCfgByCode(String code){
		if(StringUtil.isNotBlank(code)){
			String v= GlobalUtil.getLocalCfgMap().get(code);
			if(StringUtil.isNotBlank(v))
				return v;
			SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
			if(cfg!=null){
				String val = cfg.getCfgValue();
				if(!StringUtil.isNotBlank(val)){
					val = cfg.getCfgBigValue();
				}
				return val;
			}
		}
		return null;
	}

	@Override
	public List<SysOrg> getExpertEvalOrg(String evalYear, String userFlow) {
		return evalAppMapper.getExpertEvalOrg(evalYear,userFlow);
	}

	@Override
	public ExpertEvalCfg getExpertBaseCfg(String evalYear) {
		if(StringUtil.isNotBlank(evalYear))
		{
			ExpertEvalCfgExample example=new ExpertEvalCfgExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andEvalYearEqualTo(evalYear).andParentCfgFlowIsNull().andIsPublishEqualTo(GlobalConstant.FLAG_Y);
			List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<ExpertEvalCfg> getExpertCfgChildrens(String cfgFlow, String evalYear, String userFlow, String orgFlow) {
		return evalAppMapper.getExpertCfgChildrens(cfgFlow,evalYear,userFlow,orgFlow);
	}

	@Override
	public ExpertEvalResult getOrgCfgEvalReustl(String evalYear, String orgFlow, String cfgFlow) {
		ExpertEvalResultExample example=new ExpertEvalResultExample();
		ExpertEvalResultExample.Criteria criteria=example.createCriteria().andEvalYearEqualTo(evalYear)
				.andOrgFlowEqualTo(orgFlow).andCfgFlowEqualTo(cfgFlow);
		List<ExpertEvalResult> list=resultMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public ExpertEvalCfg readCfgByFlow(String cfgFlow) {
		return evalCfgMapper.selectByPrimaryKey(cfgFlow);
	}

	@Override
	public Map<String,Object> parseContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					String name=element.getName();
					if("scores".equals(name))
					{
						List<Map<String,String>> scores=new ArrayList<>();
						List<Element> subs = element.elements();
						for(Element sub:subs) {
							Map<String, String> score = new HashMap<>();
							List<DefaultAttribute> attrs=sub.attributes();
							for(DefaultAttribute attr:attrs)
							{
								score.put(attr.getName(),attr.getStringValue());
							}
							score.put(sub.getName(),sub.getTextTrim());
							if(score.size()>0)
								scores.add(score);
						}
						formDataMap.put(element.getName(), scores);
					}else{
						formDataMap.put(element.getName(), element.getTextTrim());
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	@Override
	public int saveCfgResultByFile(FileForm form, String evalScore, String evalYear, String userFlow, String orgFlow, String cfgFlow) {

		ExpertEvalCfg evalCfg = readCfgByFlow(cfgFlow);
		String baseDirs = getCfgByCode("upload_base_dir");
		String filePath = baseDirs + File.separator + "evalFiles";
		MultipartFile uploadFile = form.getUploadFile();
		System.err.println("=====filename:" + uploadFile.getOriginalFilename());
		String dirUrl =	filePath +=File.separator +evalYear+"Result"+File.separator + orgFlow+ File.separator +
						evalCfg.getFilePath().substring(evalCfg.getFilePath().indexOf(evalYear)+evalYear.length());
		File dir = new File(dirUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dirUrl);
		try {
			uploadFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}


		FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
		String localFilePath=filePath;
		String ftpFileName=localFilePath.substring(localFilePath.lastIndexOf(File.separator)+1);
		String ftpDir=localFilePath.substring(baseDirs.length()+1,localFilePath.lastIndexOf(File.separator));
		ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);

		ExpertEvalResult result = getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
		if(result==null)
			result= new ExpertEvalResult();
		result.setFilePath(evalCfg.getFilePath());
		result.setIsExpert(evalCfg.getIsExpert());
		result.setIsFile(evalCfg.getIsFile());
		result.setCfgFlow(evalCfg.getCfgFlow());
		result.setCfgName(evalCfg.getCfgName());
		result.setParentCfgFlow(evalCfg.getParentCfgFlow());
		result.setEvalYear(evalCfg.getEvalYear());
		result.setTypeId(evalCfg.getTypeId());
		result.setLevelId(evalCfg.getLevelId());
		result.setOrdinal(evalCfg.getOrdinal());
		result.setOrgFlow(orgFlow);
		SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
		if(org!=null)
			result.setOrgName(org.getOrgName());
		result.setExpertUserFlow(userFlow);
		SysUser user=userMapper.selectByPrimaryKey(userFlow);

		ExpertEvalCfg pCfg = readCfgByFlow(evalCfg.getParentCfgFlow());
		if(pCfg!=null&&StringUtil.isNotBlank(pCfg.getSpeId()))
		{
			result.setSpeId(pCfg.getSpeId());
			result.setSpeName(pCfg.getSpeName());
		}
		result.setExpertUserName(user.getUserName());
		result.setEvalDate(DateUtil.getCurrDate());
		result.setEvalScore(evalScore);
		return saveCfgResult(result,user);
	}
	@Override
	public int saveCfgResult(ExpertEvalResult result, SysUser user) {
		if(StringUtil.isBlank(result.getResultFlow()))
		{
			result.setResultFlow(PkUtil.getUUID());
			result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			result.setModifyTime(DateUtil.getCurrDateTime());
			result.setModifyUserFlow(user.getUserFlow());
			result.setCreateTime(DateUtil.getCurrDateTime());
			result.setCreateUserFlow(user.getUserFlow());
			return  resultMapper.insertSelective(result);
		}else{
			result.setModifyTime(DateUtil.getCurrDateTime());
			result.setModifyUserFlow(user.getUserFlow());
			return  resultMapper.updateByPrimaryKeyWithBLOBs(result);
		}
	}

	@Override
	public int saveCfgResultNotFile(EvalResultBean resultBean, String evalYear, String userFlow, String orgFlow, String cfgFlow) throws DocumentException {
		ExpertEvalCfg evalCfg = readCfgByFlow(cfgFlow);
		ExpertEvalResult result = getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
		String oldContent="";
		if(result==null)
			result= new ExpertEvalResult();
		if(result!=null)
		{
			oldContent=result.getEvalContent();
		}
		result.setFilePath(evalCfg.getFilePath());
		result.setIsExpert(evalCfg.getIsExpert());
		result.setIsFile(evalCfg.getIsFile());
		result.setCfgFlow(evalCfg.getCfgFlow());
		result.setCfgName(evalCfg.getCfgName());
		result.setParentCfgFlow(evalCfg.getParentCfgFlow());
		result.setEvalYear(evalCfg.getEvalYear());
		result.setTypeId(evalCfg.getTypeId());
		result.setLevelId(evalCfg.getLevelId());
		result.setOrdinal(evalCfg.getOrdinal());
		result.setOrgFlow(orgFlow);
		SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
		if(org!=null)
			result.setOrgName(org.getOrgName());
		result.setExpertUserFlow(userFlow);
		SysUser user=userMapper.selectByPrimaryKey(userFlow);

		ExpertEvalCfg pCfg = readCfgByFlow(evalCfg.getParentCfgFlow());
		if(pCfg!=null&&StringUtil.isNotBlank(pCfg.getSpeId()))
		{
			result.setSpeId(pCfg.getSpeId());
			result.setSpeName(pCfg.getSpeName());
		}
		result.setExpertUserName(user.getUserName());
		result.setEvalDate(DateUtil.getCurrDate());
		result.setBaseScore(resultBean.getBaseScore());
		String newContent= null;
		try {
			newContent = getContent(oldContent,resultBean);
		} catch (DocumentException e) {
			newContent="";
		}
		result.setEvalScore(resultBean.getEvalScore());
		result.setEvalContent(newContent);
		result.setIsOverAll(GlobalConstant.FLAG_Y);
		return saveCfgResult(result,user);
	}

	@Override
	public int saveCfgResultNotFile2(HttpServletRequest request, String evalYear, String userFlow, String orgFlow, String cfgFlow) {
		ExpertEvalCfg evalCfg = readCfgByFlow(cfgFlow);
		ExpertEvalResult result = getOrgCfgEvalReustl(evalYear, orgFlow, evalCfg.getCfgFlow());
		String oldContent="";
		if(result==null)
			result= new ExpertEvalResult();
		if(result!=null)
		{
			oldContent=result.getEvalContent();
		}
		result.setFilePath(evalCfg.getFilePath());
		result.setIsExpert(evalCfg.getIsExpert());
		result.setIsFile(evalCfg.getIsFile());
		result.setCfgFlow(evalCfg.getCfgFlow());
		result.setCfgName(evalCfg.getCfgName());
		result.setParentCfgFlow(evalCfg.getParentCfgFlow());
		result.setEvalYear(evalCfg.getEvalYear());
		result.setTypeId(evalCfg.getTypeId());
		result.setLevelId(evalCfg.getLevelId());
		result.setOrdinal(evalCfg.getOrdinal());
		result.setOrgFlow(orgFlow);
		SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
		if(org!=null)
			result.setOrgName(org.getOrgName());
		result.setExpertUserFlow(userFlow);
		SysUser user=userMapper.selectByPrimaryKey(userFlow);

		ExpertEvalCfg pCfg = readCfgByFlow(evalCfg.getParentCfgFlow());
		if(pCfg!=null&&StringUtil.isNotBlank(pCfg.getSpeId()))
		{
			result.setSpeId(pCfg.getSpeId());
			result.setSpeName(pCfg.getSpeName());
		}
		result.setExpertUserName(user.getUserName());
		result.setEvalDate(DateUtil.getCurrDate());
		String newContent= null;
		try {
			newContent = getContent2(request);
		} catch (DocumentException e) {
			newContent="";
		}
		result.setEvalContent(newContent);

		return saveCfgResult(result,user);
	}

	private String getContent2(HttpServletRequest request) throws DocumentException {
		Map<String,String[]> paramsMap = request.getParameterMap();
		if(paramsMap!=null && !paramsMap.isEmpty()) {
			//创建xmldom对象和根节点
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("EvalResult");

			for (String key : paramsMap.keySet()) {
				String[] vs = paramsMap.get(key);
				String vss = "";
				String idCsVv = request.getParameter(key + "_name");
				if (vs != null && vs.length > 0) {
					vss = vs[0];
				}
//				try {
//					if (idCsVv != null)
//						idCsVv = URLDecoder.decode(idCsVv, "UTF-8");
//					if (vss != null) vss = URLDecoder.decode(vss, "UTF-8");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
				//开始创建xml子节点
				Element currEle = root.addElement(key);
				if (idCsVv == null) {
					currEle.setText(vss);
				} else {
					currEle.addAttribute("id", vss);
					currEle.setText(idCsVv);
				}
			}
			return doc.asXML();
		}
		return "";
	}

	private String getContent(String oldContent, EvalResultBean resultBean) throws DocumentException {

		String newContent="";
		if(StringUtil.isNotBlank(oldContent))
		{
			Map<String, Object> map=getBeanAttrVal(resultBean);
			Document document = DocumentHelper.parseText(oldContent);
			Element rootElement = document.getRootElement();
			for(String key:map.keySet())
			{
				Object v=map.get(key);
				if(v!=null) {
					if ("scores".equals(key)) {
						List<EvalResultBean.ScoresBean> scores= (List<EvalResultBean.ScoresBean>) v;
						if(scores!=null)
						{
							Element ss=rootElement.element(key);
							List<Element> elements=ss.elements();

							for(EvalResultBean.ScoresBean score:scores)
							{
								Map<String, Object> smap=getBeanAttrVal(score);
								if(elements!=null)
								{
									boolean f=false;
									Element s=null;
									for(Element element:elements)
									{
										if(element.attributeValue("ordinal").equals(smap.get("ordinal")))
										{
											if(StringUtil.isNotBlank(String.valueOf("score"))) {
												f=true;
												s=element;
												break;
											}
										}
									}
									if(!f)
									{
										s = ss.addElement("score");
									}
									for(String skey:smap.keySet()) {
										Object sv=smap.get(skey);
										if(sv==null)
											sv="";
										if(skey!="score")
										{
											s.addAttribute(skey,String.valueOf(sv));
										}else {
											if(StringUtil.isNotBlank(String.valueOf(sv))) {
												s.setText(String.valueOf(sv));
											}
										}
									}
								}else{
									Element s = ss.addElement("score");
									for(String skey:smap.keySet()) {
										Object sv=smap.get(skey);
										if(sv==null)
											sv="";
										if(skey!="score")
										{
											s.addAttribute(skey,String.valueOf(sv));
										}else {
											if(StringUtil.isNotBlank(String.valueOf(sv))) {
												s.setText(String.valueOf(sv));
											}
										}
									}
								}
							}
						}
					} else {
						Element e=rootElement.element(key);
						if(e==null)
							e=rootElement.addElement(key);
						e.setText(String.valueOf(v));
					}
				}
			}
			newContent= document.asXML();
		}else{
			Map<String, Object> map=getBeanAttrVal(resultBean);
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("EvalResult");
			for(String key:map.keySet())
			{
				Object v=map.get(key);
				if(v!=null) {
					if ("scores".equals(key)) {
						List<EvalResultBean.ScoresBean> scores= (List<EvalResultBean.ScoresBean>) v;
						if(scores!=null)
						{
							Element ss=rootElement.addElement(key);
							for(EvalResultBean.ScoresBean score:scores)
							{
								Map<String, Object> smap=getBeanAttrVal(score);
								Element s = ss.addElement("score");
								for(String skey:smap.keySet()) {
									Object sv=smap.get(skey);
									if(sv==null)
										sv="";
									if(skey!="score")
									{
										s.addAttribute(skey,String.valueOf(sv));
									}else {
										if(StringUtil.isNotBlank(String.valueOf(sv))) {
											s.setText(String.valueOf(sv));
										}
									}
								}
							}
						}
					} else {
						Element e=rootElement.addElement(key);
						e.setText(String.valueOf(v));
					}
				}
			}
			newContent= document.asXML();
		}
		if(StringUtil.isNotBlank(newContent))
		{
			Document document = DocumentHelper.parseText(newContent);
			Element rootElement = document.getRootElement();
			Map<String,Object> map= parseContent(newContent);
			if(map!=null&&map.size()>0)
			{
				List<Map<String,String>> scores= (List<Map<String, String>>) map.get("scores");
				if(scores!=null&&scores.size()>0)
				{
					int evalScore=0;
					int evalScore1=0;
					int evalScore2=0;
					int i=0;
					for(Map<String,String> s:scores)
					{
						String score=s.get("score");
						if(StringUtil.isNotBlank(score)) {
							evalScore += Integer.valueOf(score);
							if("1".equals(resultBean.getType()))
							{
								if(i<=33)
									evalScore1 += Integer.valueOf(score);
								else
									evalScore2 += Integer.valueOf(score);
							}
						}
						i++;
					}
					if("1".equals(resultBean.getType())) {
						Element e1 = rootElement.element("evalScore1");
						if(e1!=null)
							e1.setText(evalScore1+"");
						Element e2 = rootElement.element("evalScore2");
						if(e2!=null)
							e2.setText(evalScore2+"");
					}
					Element e1 = rootElement.element("evalScore");
					if(e1!=null)
						e1.setText(evalScore+"");
					resultBean.setEvalScore(evalScore+"");
				}
			}
			newContent=document.asXML();
		}
		return newContent;
	}
	public Map<String, Object> getBeanAttrVal(Object resultBean)
	{
		Field[] field = resultBean.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < field.length; j++) {     //遍历所有属性
			String name = field[j].getName();    //获取属性的名字
			String key =name;
			name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
			Method m = null;
			Object value=null;
			try {
				m = resultBean.getClass().getMethod("get" + name);
				value = m.invoke(resultBean);    //调用getter方法获取属性值
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		}
		return map;
	}
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		EvalResultBean resultBean = new EvalResultBean();
		Field[] field = resultBean.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < field.length; j++) {     //遍历所有属性
			String name = field[j].getName();    //获取属性的名字
			name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
			System.err.println(name);
			Method m = resultBean.getClass().getMethod("get" + name);
			Object value = m.invoke(resultBean);    //调用getter方法获取属性值
			if (value != null) {
				//System.out.println("attribute value:" + value);
			}
			map.put(name.toLowerCase(), value);
		}
		System.err.println(JSON.toJSONString(map));
	}
}
  