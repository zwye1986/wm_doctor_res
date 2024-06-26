package com.pinde.sci.biz.hbzy.impl;


import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyResOrgSpeBiz;
import com.pinde.sci.biz.hbzy.IJszyResBaseBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.base.ResOrgSpeMapper;
import com.pinde.sci.dao.base.ResPassScoreCfgMapper;
import com.pinde.sci.dao.hbzy.HbzyResBaseExtMapper;
import com.pinde.sci.enums.hbzy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.hbzy.JszyBaseExtInfoForm;
import com.pinde.sci.form.hbzy.JszyBaseInfoForm;
import com.pinde.sci.model.hbzy.JszyResBaseExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor=Exception.class)
public class HbzyResBaseBizImpl implements IJszyResBaseBiz {

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;
	@Autowired
	private HbzyResBaseExtMapper resBaseExtMapper;
	@Autowired
	private IJszyResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private ResPassScoreCfgMapper resPassScoreCfgMapper;
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
			if(GlobalConstant.TEACH_CONDITION.equals(flag)){
				baseExtInfo.setEducationInfo(baseInfoForm.getEducationInfo());
			}else  if (GlobalConstant.ORG_MANAGE.equals(flag)){
				baseExtInfo.setOrganizationManage(baseInfoForm.getOrganizationManage());
			}else if(GlobalConstant.SUPPORT_CONDITION.equals(flag)){
				baseExtInfo.setSupportCondition(baseInfoForm.getSupportCondition());
			}else if(GlobalConstant.BASIC_INFO.equals(flag)){
				baseExtInfo.setBasicInfo(baseInfoForm.getBasicInfo());
			}
		}
		xml=JaxbUtil.convertToXml(baseExtInfo);
		resBase.setBaseInfo(xml);
		resBase.setBaseStatusId(JszyResDoctorAuditStatusEnum.NotSubmit.getId());
		resBase.setBaseStatusName(JszyResDoctorAuditStatusEnum.NotSubmit.getName());
		if(StringUtil.isNotBlank(resBase.getResApprovalNumberId())){
			resBase.setResApprovalNumberId(resBase.getResApprovalNumberId());
			resBase.setResApprovalNumberName(DictTypeEnum.getDictName(DictTypeEnum.ResidentBaseApproveNum, resBase.getResApprovalNumberId()));
		}
		if(StringUtil.isNotBlank(resBase.getGpsApprovalNumberId())){
			resBase.setGpsApprovalNumberId(resBase.getGpsApprovalNumberId());
			resBase.setGpsApprovalNumberName(DictTypeEnum.getDictName(DictTypeEnum.GeneralBaseApproNum, resBase.getGpsApprovalNumberId()));
		}
		if(StringUtil.isNotBlank(resBase.getBaseGradeId())){
			resBase.setBaseGradeId(resBase.getBaseGradeId());
			resBase.setBaseGradeName(DictTypeEnum.getDictName(DictTypeEnum.BaseLevel, resBase.getBaseGradeId()));
		}
		if(StringUtil.isNotBlank(resBase.getBaseTypeId())){
			resBase.setBaseTypeId(resBase.getBaseTypeId());
			resBase.setBaseTypeName(DictTypeEnum.getDictName(DictTypeEnum.BaseType, resBase.getBaseTypeId()));
		}
		if(StringUtil.isNotBlank(resBase.getBasePropertyId())){
			resBase.setBasePropertyId(resBase.getBasePropertyId());
			resBase.setBasePropertyName(DictTypeEnum.getDictName(DictTypeEnum.BasProperty, resBase.getBasePropertyId()));
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
					if(GlobalConstant.RECORD_STATUS_Y.equals(s.getRecordStatus())){
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
								N.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
				delOrgSpe.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				resOrgSpeBiz.saveResOrgSpe(delOrgSpe);
			}
		}
		ResBase resBase=readBase(orgFlow);
		if (resBase!=null) {
			resBase.setBaseStatusId(JszyResDoctorAuditStatusEnum.NotSubmit.getId());
			resBase.setBaseStatusName(JszyResDoctorAuditStatusEnum.NotSubmit.getName());
			saveResBase(resBase);
		}
		return GlobalConstant.ONE_LINE;
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
}
