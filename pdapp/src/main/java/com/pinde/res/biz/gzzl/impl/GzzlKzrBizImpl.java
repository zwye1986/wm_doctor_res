package com.pinde.res.biz.gzzl.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzl.IGzzlKzrBiz;
import com.pinde.res.dao.jswjw.ext.ResDoctorSchProcessExtMapper;
import com.pinde.res.dao.jswjw.ext.SysUserExtMapper;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.stdp.RecStatusEnum;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzzlKzrBizImpl implements IGzzlKzrBiz {

	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private ResDoctorSchProcessMapper resDoctorProcessMapper;
	@Autowired
	private SchDeptMapper schDeptMapper;
	@Autowired
	private ResSchProcessExpressMapper expressMapper;
	@Autowired
	private ResRecMapper resRecMapper;

	@Override
	public List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.hbresDoctorSchProcessHead(schArrangeResultMap);
	}

	@Override
	public List<SysUser> teacherRoleCheckUser(String deptFlow, String role,String userName, String userFlow) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sysDeptFlow", deptFlow);
		map.put("roleFlow", role);
		map.put("userName", userName);
		map.put("userFlow", userFlow);
		List<SysUser> sysUserList=userExtMapper.teacherRoleCheckUser(map);
		return sysUserList;
	}

	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> paramMap) {
		return resDoctorProcessExtMapper.searchProcessByDoctorNew(paramMap);
	}

	@Override
	public List<SysUser> searchSysUserByuserFlows(List<String> userFlows) {
		if(userFlows != null && !userFlows.isEmpty()){
			SysUserExample sysUserExample = new SysUserExample();
			sysUserExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			return sysUserMapper.selectByExample(sysUserExample);
		}
		return null;
	}

	@Override
	public List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows) {
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public Map<String, String> parseRecContent(String content) {
		Map<String,String> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, String>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				Element afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_MANAGER+ResRecTypeEnum.AfterEvaluation.getId());
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_HEAD+ResRecTypeEnum.AfterEvaluation.getId());
				}
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_TEACHER+ ResRecTypeEnum.AfterEvaluation.getId());
				}
				List<Element> elements = null;
				if(afterEvaluation!=null){
					elements = afterEvaluation.elements();
				}else{
					elements = rootElement.elements();
				}
				for(Element element : elements){
					List<Node> valueNodes = element.selectNodes("value");
					if(valueNodes != null && !valueNodes.isEmpty()){
						String value = "";
						for(Node node : valueNodes){
							if(StringUtil.isNotBlank(value)){
								value+=",";
							}
							value += node.getText();
						}
						formDataMap.put(element.getName(), value);
					}else {
						String isSelect = element.attributeValue("id");
						if(StringUtil.isNotBlank(isSelect)){
							formDataMap.put(element.getName()+"_id",isSelect);
							formDataMap.put(element.getName(),element.getText());
						}else{
							formDataMap.put(element.getName(), element.getText());
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	@Override
	public ResScore getScoreByProcess(String processFlow) {
		if(StringUtil.isNotBlank(processFlow)){
			ResScoreExample example=new ResScoreExample();
			ResScoreExample.Criteria criteria= example.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andProcessFlowEqualTo(processFlow);
			List<ResScore> scores = scoreMapper.selectByExample(example);
			if(scores==null || scores.isEmpty()){
				return null;
			}
			return scores.get(0);
		}
		return null;
	}

	@Override
	public ResDoctorSchProcess read(String processFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
		}
		return process;
	}

	@Override
	public SchDept readSchDept(String schDeptFlow) {
		return schDeptMapper.selectByPrimaryKey(schDeptFlow);
	}

	@Override
	public int schProcessStudentDistinctQuery(String deptFlow, String userFlow,String isOpen) {
		return resDoctorProcessExtMapper.hbresSchProcessStudent(deptFlow,userFlow,isOpen);
	}

	@Override
	public void auditDate(String userFlow, String dataFlow) {
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(dataFlow)){
			ResSchProcessExpress rec = new ResSchProcessExpress();
			rec.setRecFlow(dataFlow);
			rec.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
			rec.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
			rec.setHeadAuditUserFlow(userFlow);
			SysUser user = sysUserMapper.selectByPrimaryKey(userFlow);
			if(user!=null){
				rec.setHeadAuditUserName(user.getUserName());
			}
			rec.setHeadAuditTime(DateUtil.getCurrDateTime());
			expressMapper.updateByPrimaryKeySelective(rec);
		}
	}

	@Override
	public ResDoctorSchProcess searchByResultFlow(String resultFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> processList = this.resDoctorProcessMapper.selectByExample(example);
			if(processList!=null&&!processList.isEmpty()){
				process = processList.get(0);
			}
		}
		return process;
	}

	@Override
	public ResRec queryResRec(String processFlow, String operUserFlow, String recTypeId) {
		ResRec rec=null;
		ResRecExample example = new ResRecExample();
		ResRecExample.Criteria create=example.createCriteria();
		create.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(processFlow)) {
			create.andProcessFlowEqualTo(processFlow);
		}
		if (StringUtil.isNotBlank(operUserFlow)) {
			create.andOperUserFlowEqualTo(operUserFlow);
		}
		if (StringUtil.isNotBlank(recTypeId)) {
			create.andRecTypeIdEqualTo(recTypeId);
		}
		List<ResRec> list = resRecMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >0) {
			rec = list.get(0);
		}
		return rec;
	}
}
  