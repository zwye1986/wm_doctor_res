package com.pinde.sci.biz.exam.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.exam.ISubjectManageBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ExamBookMapper;
import com.pinde.sci.dao.base.ExamQuestionSubjectMapper;
import com.pinde.sci.dao.base.ExamSubjectMapper;
import com.pinde.sci.dao.exam.ExamBookExtMapper;
import com.pinde.sci.dao.exam.ExamSubjectExtMapper;
import com.pinde.sci.enums.exam.ExamBankTypeEnum;
import com.pinde.sci.enums.exam.SubjectTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ExamSubjectExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectManageBizImpl implements ISubjectManageBiz{

	private static ThreadLocal<Integer> countForTreeLocal = new ThreadLocal<Integer>();
	@Autowired
	private ExamSubjectMapper examSubjectMapper;
	@Autowired
	private ExamQuestionSubjectMapper examQuestionSubjectMapper;
	@Autowired
	private ExamBookExtMapper examBookExtMapper;
	@Autowired
	private ExamSubjectExtMapper examSubjectExtMapper;
	@Autowired
	private ExamBookMapper bookMapper;

	@Override
	public List<ExamSubject> search(ExamSubject subject) {
		ExamSubjectExample example = new ExamSubjectExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(subject.getRecordStatus())){
			criteria.andRecordStatusEqualTo(subject.getRecordStatus());
		}
		if(StringUtil.isNotBlank(subject.getSubjectParentFlow())){
			criteria.andSubjectParentFlowEqualTo(subject.getSubjectParentFlow());
		}
		if(StringUtil.isNotBlank(subject.getBankTypeId())){
			criteria.andBankTypeIdEqualTo(subject.getBankTypeId());
		}
		example.setOrderByClause("ORDINAL");
		return this.examSubjectMapper.selectByExample(example);
	}

	@Override
	public int countQuestNumOfSubject(String subjectFlow) {
		int count = 0;
		if(StringUtil.isNotBlank(subjectFlow)){
		    ExamQuestionSubjectExample example = new ExamQuestionSubjectExample();
		    example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		    count = examQuestionSubjectMapper.countByExample(example);
		}

		return count;
	}

	@Override
	public void saveOrder(String[] subjectFlows) {
		if(subjectFlows==null){
			return;
		}
		int i = 1;
		ExamSubject subject = null;
		for(String subjectFlow : subjectFlows){
			subject = new ExamSubject();
			subject.setSubjectFlow(subjectFlow);
			subject.setOrdinal(i++);
			GeneralMethod.setRecordInfo(subject, false);
			examSubjectMapper.updateByPrimaryKeySelective(subject);
		}
	}

	@Override
	public List<Map<String,Integer>> countQuestNumByQuestionTypeOfSubject(String subjectFlow) {
		return examSubjectExtMapper.countQuestNumByQuestionTypeOfSubject(subjectFlow);
	}

	@Override
	public List<ExamBook> searchBookBySubject(String subjectFlow) {
		return examBookExtMapper.searchBookBySubject(subjectFlow);
	}

	@Override
	public List<ExamBook> searchBookByQuestion(String questionFlow) {
		return examBookExtMapper.searchBookByQuestion(questionFlow);
	}

//	@Override
//	public List<ExamSubject> searchSubjectBySubject(String subjectFlow) {
//		return examSubjectExtMapper.searchSubjectBySubject(subjectFlow);
//	}

//	@Override
//	public List<Map<String, Integer>> countQuestNumBySubjectOfSubject(String subjectFlow) {
//		return examSubjectExtMapper.countQuestNumBySubjectOfSubject(subjectFlow);
//	}

	@Override
	public List<ExamSubject> searchSubjectByQuestion(String questionFlow) {
		return examSubjectExtMapper.searchSubjectByQuestion(questionFlow);
	}

	@Override
	public List<Map<String, Integer>> countQuestNumByBookOfSubject(String subjectFlow) {
		return examSubjectExtMapper.countQuestNumByBookOfSubject(subjectFlow);
	}

	@Override
	public void delQuestionSubject(String subjectFlow, String questionFlow) {
		ExamQuestionSubject update = new ExamQuestionSubject();
		update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(update, false);

		ExamQuestionSubjectExample example = new ExamQuestionSubjectExample();
		example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andQuestionFlowEqualTo(questionFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		examQuestionSubjectMapper.updateByExampleSelective(update, example);
	}

	@Override
	public void unRel(String subjectFlow) {
		ExamQuestionSubject update = new ExamQuestionSubject();
		update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(update, false);

		ExamQuestionSubjectExample example = new ExamQuestionSubjectExample();
		example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		examQuestionSubjectMapper.updateByExampleSelective(update, example);
	}

	@Override
	public ExamSubject read(String subjectFlow) {
		return examSubjectMapper.selectByPrimaryKey(subjectFlow);
	}

	@Override
	public void add(ExamSubject subject,ExamSubject subjectParent) {
		String subjectFlow = PkUtil.getUUID();
		subject.setSubjectFlow(subjectFlow);

		int subjectCodeInBankTypeCount = countSubjectCodeInBankType(subject.getSubjectFlow() , subject.getSubjectCode().trim() , subject.getBankTypeId());
		if(subjectCodeInBankTypeCount==0){
			subject.setSubjectCode(subject.getSubjectCode().trim());
			GeneralMethod.setRecordInfo(subject, true);
			examSubjectMapper.insertSelective(subject);

			ExamSubject updateParent = new ExamSubject();
			updateParent.setSubjectFlow(subjectParent.getSubjectFlow());
			updateParent.setLeafFlag(GlobalConstant.RECORD_STATUS_N);
			GeneralMethod.setRecordInfo(updateParent, false);
			examSubjectMapper.updateByPrimaryKeySelective(updateParent);
		}else{
			throw new RuntimeException("科目编号:"+subject.getSubjectCode()+"在"+ subject.getBankTypeName()+"已存在");
		}


	}

	@Override
	public void mod(ExamSubject subject) {
		ExamSubject exitSubject = this.read(subject.getSubjectFlow());
		int subjectCodeInBankTypeCount = countSubjectCodeInBankType(subject.getSubjectFlow() , subject.getSubjectCode().trim() , exitSubject.getBankTypeId());
		if(subjectCodeInBankTypeCount==0){
			GeneralMethod.setRecordInfo(subject, false);
			examSubjectMapper.updateByPrimaryKeySelective(subject);
		}else{
			throw new RuntimeException("科目编号:"+subject.getSubjectCode()+"在"+ exitSubject.getBankTypeName()+"已存在");
		}

	}

	@Override
	public void del(String subjectFlow) {
		ExamSubject update = new ExamSubject();
		update.setSubjectFlow(subjectFlow);
		update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		examSubjectMapper.updateByPrimaryKeySelective(update);

		unRel(subjectFlow);

		ExamSubjectExample example = new ExamSubjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubjectParentFlowEqualTo(subjectFlow);
		example.setOrderByClause("ORDINAL");
		List<ExamSubject> subExamSubjectList = examSubjectMapper.selectByExample(example);
		for(ExamSubject subSubject : subExamSubjectList){
			del(subSubject.getSubjectFlow());
		}
//		if(subExamSubjectList.size()==0){
//			return;
//		}
	}

	@Override
	public void setenabled(String enabledtype, String subjectFlow) {
		examSubjectExtMapper.setenabled(enabledtype, subjectFlow);
	}


	@Override
	public void merge(String[] subjectFlows) {
		String subjectFlow1 = subjectFlows[0];
		for(int i=1;i<subjectFlows.length;i++ ){
			String subjectFlow = subjectFlows[i];

			ExamSubject update = new ExamSubject();
			update.setSubjectFlow(subjectFlow);
			update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			examSubjectMapper.updateByPrimaryKeySelective(update);

			ExamQuestionSubjectExample exampleS = new ExamQuestionSubjectExample();
			exampleS.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

			List<ExamQuestionSubject> examQuestionSubjectList = examQuestionSubjectMapper.selectByExample(exampleS);
			for(ExamQuestionSubject qs : examQuestionSubjectList){
				//建立新的关系
				qs.setQuestionSubjectFlow(PkUtil.getUUID());
				qs.setSubjectFlow(subjectFlow1);
				qs.setSubjectName("");
				GeneralMethod.setRecordInfo(qs, true);
				qs.setModifyTime("");
				qs.setModifyUserFlow("");
				examQuestionSubjectMapper.insertSelective(qs);
			}

			//删除就关系
			unRel(subjectFlow);

			ExamSubjectExample example = new ExamSubjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andSubjectParentFlowEqualTo(subjectFlow);
			example.setOrderByClause("ORDINAL");
			List<ExamSubject> subExamSubjectList = examSubjectMapper.selectByExample(example);
			for(ExamSubject subSubject : subExamSubjectList){
				ExamSubject updateSub = new ExamSubject();
				updateSub.setSubjectFlow(subSubject.getSubjectFlow());
				updateSub.setSubjectParentFlow(subjectFlow1);
				examSubjectMapper.updateByPrimaryKeySelective(updateSub);
			}

		}

	}

	@Override
	public int countForTree(String subjectFlow) {
		countForTreeLocal.set(0);
		_countQuestNumOfSubjectTree(subjectFlow);
		return countForTreeLocal.get();
	}
	
	private void _countQuestNumOfSubjectTree(String subjectFlow) {
		countForTreeLocal.set(countForTreeLocal.get()+ countQuestNumOfSubject(subjectFlow));
		ExamSubjectExample example = new ExamSubjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubjectParentFlowEqualTo(subjectFlow);
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<ExamSubject> subList = examSubjectMapper.selectByExample(example);
		if(subList.size()==0){
			return;
		}
		for(ExamSubject sub : subList){
			_countQuestNumOfSubjectTree(sub.getSubjectFlow());
		}
	}

	@Override
	public void copy(String examBankType,String targetSubjectFlow, String sourceSubjectFlow) {
		ExamSubject source = this.read(sourceSubjectFlow);//获取被复制节点
		if(source!=null){

			ExamQuestionSubjectExample exampleT = new ExamQuestionSubjectExample();
			exampleT.createCriteria().andSubjectFlowEqualTo(targetSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<ExamQuestionSubject> examQuestionSubjectTargetList = examQuestionSubjectMapper.selectByExample(exampleT);
			List<String> questionCodeList = new ArrayList<String>();
			for(ExamQuestionSubject qs : examQuestionSubjectTargetList){
				questionCodeList.add(qs.getQuestionCode());
			}

			ExamSubject target = source;//复制节点
			ExamSubject parant=this.read(targetSubjectFlow);//父节点
			target.setSubjectParentFlow(targetSubjectFlow);

			String parantDepth = "1";
			String parantMemo = "";
			if(!examBankType.equals(targetSubjectFlow) && parant != null) {
				parantDepth = String.valueOf(Integer.valueOf(parant.getDepth()) + 1);
				parantMemo = parant.getMemo() + "_" + target.getSubjectName();
			}

			String newSubjectFlow = PkUtil.getUUID();
			target.setSubjectFlow(newSubjectFlow);
			
			target.setSourceSubjectFlow(sourceSubjectFlow);

			target.setBankTypeId(examBankType);
			target.setBankTypeName(ExamBankTypeEnum.getNameById(examBankType));

			target.setSubjectTypeId(SubjectTypeEnum.Copy.getId());
			target.setSubjectTypeName(SubjectTypeEnum.Copy.getName());
			//修改当前节点的深度与备注  深度为父节点深度+1  备注为父节点备注+当前节点科目名称
			target.setDepth(parantDepth);
			target.setMemo(parantMemo);

			GeneralMethod.setRecordInfo(target, true);
			examSubjectMapper.insertSelective(target);	
			
			ExamSubject updateParent = new ExamSubject();
			updateParent.setSubjectFlow(targetSubjectFlow);
			updateParent.setLeafFlag(GlobalConstant.RECORD_STATUS_N);
			examSubjectMapper.updateByPrimaryKeySelective(updateParent);
			
			//复制题目与科目的关系
			ExamQuestionSubjectExample exampleS = new ExamQuestionSubjectExample();
			exampleS.createCriteria().andSubjectFlowEqualTo(sourceSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			
			List<ExamQuestionSubject> examQuestionSubjectList = examQuestionSubjectMapper.selectByExample(exampleS);
			for(ExamQuestionSubject qs : examQuestionSubjectList){
				if(questionCodeList.contains(qs.getQuestionCode())){
					continue;
				}

				//建立新的关系
				qs.setQuestionSubjectFlow(PkUtil.getUUID());
				qs.setSubjectFlow(target.getSubjectFlow());
				qs.setSubjectName("");
				qs.setBookName("");
				qs.setQuestionSubjectCode("");
				GeneralMethod.setRecordInfo(qs, true);
				qs.setModifyTime("");
				qs.setModifyUserFlow("");
				examQuestionSubjectMapper.insertSelective(qs);

				questionCodeList.add(qs.getQuestionCode());
			}
			
			ExamSubjectExample example = new ExamSubjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andSubjectParentFlowEqualTo(sourceSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//			example.setOrderByClause("ORDINAL");
			List<ExamSubject> subExamSubjectList = examSubjectMapper.selectByExample(example);
			for(ExamSubject subSubject : subExamSubjectList){				
				copy(examBankType,newSubjectFlow, subSubject.getSubjectFlow());
			}
		}
	}

	@Override
	public void extract(String examBankType, String targetSubjectFlow,String sourceSubjectFlow) {

		ExamQuestionSubjectExample exampleT = new ExamQuestionSubjectExample();
		exampleT.createCriteria().andSubjectFlowEqualTo(targetSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<ExamQuestionSubject> examQuestionSubjectTargetList = examQuestionSubjectMapper.selectByExample(exampleT);
		List<String> questionCodeList = new ArrayList<>();
		for(ExamQuestionSubject qs : examQuestionSubjectTargetList){
			questionCodeList.add(qs.getQuestionCode());
		}
		
		ExamQuestionSubjectExample exampleS = new ExamQuestionSubjectExample();
		exampleS.createCriteria().andSubjectFlowEqualTo(sourceSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		
		List<ExamQuestionSubject> examQuestionSubjectSourceList = examQuestionSubjectMapper.selectByExample(exampleS);
		for(ExamQuestionSubject qs : examQuestionSubjectSourceList){
			if(questionCodeList.contains(qs.getQuestionCode())){
				continue;
			}
			//建立新的关系
			qs.setQuestionSubjectFlow(PkUtil.getUUID());
			qs.setSubjectFlow(targetSubjectFlow);
			qs.setSubjectName("");
			qs.setBookName("");
			qs.setQuestionSubjectCode("");
			GeneralMethod.setRecordInfo(qs, true);
			qs.setModifyTime("");
			qs.setModifyUserFlow("");
			examQuestionSubjectMapper.insertSelective(qs);
			
			questionCodeList.add(qs.getQuestionCode());
		}
		ExamSubjectExample example = new ExamSubjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubjectParentFlowEqualTo(sourceSubjectFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		example.setOrderByClause("ORDINAL");
		List<ExamSubject> subExamSubjectList = examSubjectMapper.selectByExample(example);
		for(ExamSubject subSubject : subExamSubjectList){				
			extract(examBankType,targetSubjectFlow, subSubject.getSubjectFlow());
		}
	}

	@Override
	public int countSubjectCodeInBankType(String subjectFlow , String subjectCode, String bankTypeId) {
		ExamSubjectExample example = new ExamSubjectExample();
		example.createCriteria()
		.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andBankTypeIdEqualTo(bankTypeId).andSubjectCodeEqualTo(subjectCode)
		.andSubjectFlowNotEqualTo(subjectFlow);
		return this.examSubjectMapper.countByExample(example);
	}
}
