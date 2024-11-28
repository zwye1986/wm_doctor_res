package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.util.*;
import com.pinde.sci.biz.jszy.IJszyResBaseBiz;
import com.pinde.sci.biz.jszy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jszy.JszyResBaseExtMapper;
import com.pinde.sci.form.jszy.JszyBaseExtInfoForm;
import com.pinde.sci.form.jszy.JszyBaseInfoForm;
import com.pinde.sci.form.jszy.JszyCountryOrgExtInfoForm;
import com.pinde.sci.model.jszy.JszyResBaseExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResBaseBizImpl implements IJszyResBaseBiz {

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;
	@Autowired
	private JszyResBaseExtMapper resBaseExtMapper;
	@Autowired
	private IJszyResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private ResPassScoreCfgMapper resPassScoreCfgMapper;
	@Autowired
	private CountryOrgInfoMapper coiMapper;
	@Autowired
	private AttachedUnitInfoMapper auiMapper;
	/**
	 * 保存基地的基本信息
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	@Override
	public int saveBaseInfo(String flag, JszyBaseInfoForm baseInfoForm, String index) throws Exception {
		SysOrg sysOrg = baseInfoForm.getSysOrg();
		if(sysOrg!=null){
			orgBiz.update(sysOrg);
		}
		ResBase resBase=readBase(sysOrg.getOrgFlow());
//		baseInfoForm.getResBase();
		String xml= null;
		JszyBaseExtInfoForm baseExtInfo = null ;
		if(resBase != null){
			xml=resBase.getBaseInfo();
			baseExtInfo =JaxbUtil.converyToJavaBean(xml, JszyBaseExtInfoForm.class);
		}else{
			resBase=baseInfoForm.getResBase();
			resBase.setOrgName(baseInfoForm.getSysOrg().getOrgName());
			baseExtInfo=new JszyBaseExtInfoForm();
		}
		if(baseInfoForm!=null){
            if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(flag)) {
				baseExtInfo.setEducationInfo(baseInfoForm.getEducationInfo());
            } else if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(flag)) {
				baseExtInfo.setOrganizationManage(baseInfoForm.getOrganizationManage());
            } else if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(flag)) {
				baseExtInfo.setSupportCondition(baseInfoForm.getSupportCondition());
            } else if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(flag)) {
				baseExtInfo.setBasicInfo(baseInfoForm.getBasicInfo());
			}
		}
		xml=JaxbUtil.convertToXml(baseExtInfo);
		resBase.setBaseInfo(xml);
        resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
		if(StringUtil.isNotBlank(resBase.getResApprovalNumberId())){
			resBase.setResApprovalNumberId(resBase.getResApprovalNumberId());
            resBase.setResApprovalNumberName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.ResidentBaseApproveNum, resBase.getResApprovalNumberId()));
		}
		if(StringUtil.isNotBlank(resBase.getGpsApprovalNumberId())){
			resBase.setGpsApprovalNumberId(resBase.getGpsApprovalNumberId());
            resBase.setGpsApprovalNumberName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.GeneralBaseApproNum, resBase.getGpsApprovalNumberId()));
		}
		if(StringUtil.isNotBlank(resBase.getBaseGradeId())){
			resBase.setBaseGradeId(resBase.getBaseGradeId());
            resBase.setBaseGradeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseLevel, resBase.getBaseGradeId()));
		}
		if(StringUtil.isNotBlank(resBase.getBaseTypeId())){
			resBase.setBaseTypeId(resBase.getBaseTypeId());
            resBase.setBaseTypeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseType, resBase.getBaseTypeId()));
		}
		if(StringUtil.isNotBlank(resBase.getBasePropertyId())){
			resBase.setBasePropertyId(resBase.getBasePropertyId());
            resBase.setBasePropertyName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BasProperty, resBase.getBasePropertyId()));
		}
		return saveResBase(resBase);
  	}
	
	@Override
	public ResBase readBase(String orgFlow) {
		ResBase resBase = null;
		if(StringUtil.isNotBlank(orgFlow)){
			resBase = resBaseMapper.selectByPrimaryKey(orgFlow);
		}
		return  resBase ;
	}
	
	@Override
	public int  saveTrainSpe(List<ResOrgSpe> saveList, String trainCategoryType, String orgFlow, String orgName) {
		ResOrgSpe exitSpe=new ResOrgSpe();
		exitSpe.setOrgFlow(orgFlow);
		List<ResOrgSpe> exitSpeList = resOrgSpeBiz.searchResOrgSpeList(exitSpe, trainCategoryType);
		Map<String, ResOrgSpe> deleteMap = new HashMap<String, ResOrgSpe>();
		List<ResOrgSpe> oldStatusYList = new ArrayList<ResOrgSpe>();
		List<ResOrgSpe> oldStatusNList = new ArrayList<ResOrgSpe>();
		if(exitSpeList != null && !exitSpeList.isEmpty()){
				for(ResOrgSpe  s: exitSpeList){
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(s.getRecordStatus())) {
						deleteMap.put(s.getOrgSpeFlow(), s );
						oldStatusYList.add(s);
					}else{
						oldStatusNList.add(s);
					}
				}
		}
		if(saveList != null && !saveList.isEmpty()){
			for(ResOrgSpe s: saveList){
				boolean addFlag = true;
				if(oldStatusYList!= null && !oldStatusYList.isEmpty()){
					for(ResOrgSpe oldResOrgSpe: oldStatusYList){
						if(s.getSpeTypeId().equals(oldResOrgSpe.getSpeTypeId()) &&  s.getSpeId().equals(oldResOrgSpe.getSpeId()) && orgFlow.equals(oldResOrgSpe.getOrgFlow()) ){
							addFlag = false;
							if(deleteMap.size()>0){
								deleteMap.remove(oldResOrgSpe.getOrgSpeFlow());
							}
							break;
						}
					}
				}
				if(addFlag){
					ResOrgSpe addOrgSpe = new ResOrgSpe();
					addOrgSpe.setSpeId(s.getSpeId());
					addOrgSpe.setSpeName(s.getSpeName());
					addOrgSpe.setSpeTypeId(s.getSpeTypeId());
					addOrgSpe.setSpeTypeName(s.getSpeTypeName());
					addOrgSpe.setOrgFlow(orgFlow);
					addOrgSpe.setOrgName(orgName);
					if(oldStatusNList != null && !oldStatusNList.isEmpty()){
						for(ResOrgSpe N :oldStatusNList){
							if(s.getSpeTypeId().equals(N.getSpeTypeId()) &&  s.getSpeId().equals(N.getSpeId()) && orgFlow.equals(N.getOrgFlow()) ){
								addFlag = false;
                                N.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
								resOrgSpeBiz.saveResOrgSpe(N);
								break;
							}
						}
						if(addFlag){//新增
							resOrgSpeBiz.saveResOrgSpe(addOrgSpe);
						}
					}else{//新增
						resOrgSpeBiz.saveResOrgSpe(addOrgSpe);
					}
				}
			}
		}
		//删除原先的
		if(deleteMap.size()>0){
			for(Entry<String, ResOrgSpe> entry : deleteMap.entrySet()){
				ResOrgSpe delOrgSpe = entry.getValue();
                delOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				resOrgSpeBiz.saveResOrgSpe(delOrgSpe);
			}
		}
		ResBase resBase=readBase(orgFlow);
		if (resBase!=null) {
            resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
            resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
			saveResBase(resBase);
		}
        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}
	@Override
	public List<JszyResBaseExt> searchResBaseExtList(Map<String, Object> paramMap) {
		return resBaseExtMapper.searchResBaseExtList(paramMap);
	}
	
	@Override
	public int saveResBase(ResBase resBase) {
		if(StringUtil.isNotBlank(resBase.getOrgFlow())){
			GeneralMethod.setRecordInfo(resBase, false);
			return resBaseMapper.updateByPrimaryKeySelective(resBase);
		}else{
			SysUser sysUser =GlobalContext.getCurrentUser();
			resBase.setOrgFlow(sysUser.getOrgFlow());
			GeneralMethod.setRecordInfo(resBase, true);
			return resBaseMapper.insert(resBase);
		}
	}

    @Override
    public int savePassScoreCfg(ResPassScoreCfg resPassScoreCfg){
        ResPassScoreCfg scoreCfg = readResPassScoreCfg(resPassScoreCfg);
        if(scoreCfg!=null){
            GeneralMethod.setRecordInfo(resPassScoreCfg, false);
            return resPassScoreCfgMapper.updateByPrimaryKeySelective(resPassScoreCfg);
        }else{
            GeneralMethod.setRecordInfo(resPassScoreCfg, true);
            return resPassScoreCfgMapper.insert(resPassScoreCfg);
        }
    }
    @Override
    public ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg){
        ResPassScoreCfg passScoreCfg=null;
        if(StringUtil.isNotBlank(resPassScoreCfg.getCfgYear())){
            passScoreCfg = resPassScoreCfgMapper.selectByPrimaryKey(resPassScoreCfg.getCfgYear());
        }
        return passScoreCfg;
    }
    @Override
    public List<ResPassScoreCfg> readCfgs(ResPassScoreCfg resPassScoreCfg){
        ResPassScoreCfgExample example = new ResPassScoreCfgExample();
        if(StringUtil.isNotBlank(resPassScoreCfg.getCfgYear())){
            example.createCriteria().andCfgYearEqualTo(resPassScoreCfg.getCfgYear());
        }else{
            example.createCriteria().andCfgYearLike("%");
        }
        example.setOrderByClause("CFG_YEAR desc");
        return resPassScoreCfgMapper.selectByExample(example);
    }
    @Override
    public int delScoreConf(String cfgYear){
        int count=0;
        if(StringUtil.isNotBlank(cfgYear)){
            count = resPassScoreCfgMapper.deleteByPrimaryKey(cfgYear);
        }
        return count;
    }

	@Override
	public CountryOrgInfo queryCountryOrgInfo(String orgFlow) {
		return coiMapper.selectByPrimaryKey(orgFlow);
	}

	@Override
	public List<JszyCountryOrgExtInfoForm> parseXmlToBean(String content) {
		if(StringUtil.isNotBlank(content)){
			try{
				List<JszyCountryOrgExtInfoForm> deptList = new ArrayList<JszyCountryOrgExtInfoForm>();
				Document doc = DocumentHelper.parseText(content);
				Element root = doc.getRootElement();
				List<Element> deptEles = root.elements("deptList");
				if(deptEles!=null && deptEles.size()>0){
					for(Element deptEle : deptEles){
						JszyCountryOrgExtInfoForm dept = new JszyCountryOrgExtInfoForm();
						List<Element> deptAttrEles = deptEle.elements();
						if(deptAttrEles!=null && deptAttrEles.size()>0){
							for(Element attr : deptAttrEles){
								String attrName = attr.getName();
								String attrValue = attr.getText();
								setValue(dept,attrName,attrValue);
							}
						}
						deptList.add(dept);
					}
					return deptList;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
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
	public String parseBeanToXml(List<JszyCountryOrgExtInfoForm> deptList) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("deptListForm");
		if(null != deptList && deptList.size()>0){
			for(JszyCountryOrgExtInfoForm dept : deptList){
				Element deptListEle = root.addElement("deptList");
				Map<String,String> deptFiledMap = getClassFieldMap(dept);
				if(deptFiledMap!=null && deptFiledMap.size()>0){
					for(String key : deptFiledMap.keySet()){
						Element item = deptListEle.addElement(key);
						item.setText(deptFiledMap.get(key));
					}
				}
			}
		}
		return doc.asXML();
	}
	/**
	 * 获取属性名和值
	 * @return
	 */
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
	public int saveCountryOrgInfo(CountryOrgInfo orgInfo) {
		if(StringUtil.isBlank(orgInfo.getOrgFlow())){
			GeneralMethod.setRecordInfo(orgInfo,true);
			orgInfo.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			return coiMapper.insertSelective(orgInfo);
		}else{
			GeneralMethod.setRecordInfo(orgInfo,false);
			return coiMapper.updateByPrimaryKeySelective(orgInfo);
		}
	}

	@Override
	public List<AttachedUnitInfo> queryUnitInfoList(String orgFlow) {
		AttachedUnitInfoExample example = new AttachedUnitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgInfoFlowEqualTo(orgFlow);
		return auiMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public AttachedUnitInfo queryUnitInfoByFlow(String recordFlow) {
		return auiMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int saveJointOrgInfo(AttachedUnitInfo unitInfo) {
		AttachedUnitInfo exit = auiMapper.selectByPrimaryKey(unitInfo.getRecordFlow());
		if(null != exit){
			GeneralMethod.setRecordInfo(unitInfo,false);
			return auiMapper.updateByPrimaryKeySelective(unitInfo);
		}else{
			GeneralMethod.setRecordInfo(unitInfo,true);
			unitInfo.setOrgInfoFlow(GlobalContext.getCurrentUser().getOrgFlow());
			return auiMapper.insertSelective(unitInfo);
		}
	}

	@Override
	public int delJointOrgInfo(String recordFlow) {
		return auiMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public String uploadFile(String recordFlow, String unitTypeId, MultipartFile file) {
		if(file!=null){
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(".pdf".equals(suffix)||".PDF".equals(suffix))){
				return "只能上传PDF文件！";
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"baseInfoFiles"+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = "baseInfoFiles/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(recordFlow)){
					AttachedUnitInfo unitInfo = auiMapper.selectByPrimaryKey(recordFlow);
					if(null !=unitInfo){
						GeneralMethod.setRecordInfo(unitInfo,false);
						unitInfo.setPdfUrl(url);
						auiMapper.updateByPrimaryKeySelective(unitInfo);
					}else{
						unitInfo = new AttachedUnitInfo();
						GeneralMethod.setRecordInfo(unitInfo,true);
						unitInfo.setOrgInfoFlow(GlobalContext.getCurrentUser().getOrgFlow());
						unitInfo.setUnitTypeId(unitTypeId);
						unitInfo.setRecordFlow(recordFlow);
						unitInfo.setPdfUrl(url);
						auiMapper.insertSelective(unitInfo);
					}

				}
				FtpHelperUtil ftpHelperUtil = new FtpHelperUtil();
				String localFilePath=fileDir+File.separator+fileName;
				String ftpDir= "baseInfoFiles"+File.separator +dateString ;
				String ftpFileName=fileName;
				ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
				return "success:"+url;
			} catch (Exception e) {
				e.printStackTrace();
                return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
}
