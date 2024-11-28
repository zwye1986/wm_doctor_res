package com.pinde.sci.biz.sys.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.ISpePracticeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.JsresSpeContrastPracticeMapper;
import com.pinde.sci.model.mo.JsresSpeContrastPractice;
import com.pinde.sci.model.mo.JsresSpeContrastPracticeExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SpePracticeBizImpl implements ISpePracticeBiz {

	private static Logger logger = LoggerFactory.getLogger(SpePracticeBizImpl.class);

	@Autowired
	private JsresSpeContrastPracticeMapper scpMapper;

	@Override
	public List<JsresSpeContrastPractice> getSpePracticeBySpeId(String trainingSpeId) {
		if(StringUtil.isNotBlank(trainingSpeId)){
			JsresSpeContrastPracticeExample example=new JsresSpeContrastPracticeExample();
            example.createCriteria().andSpeIdEqualTo(trainingSpeId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

			return scpMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public int save(JsresSpeContrastPractice s) {

		String recordFlow = s.getRecordFlow();
		String practiceId = s.getPracticeId();
		String speId = s.getSpeId();
		JsresSpeContrastPractice old = scpMapper.selectByPrimaryKey(recordFlow);
		if (old == null)
			old = getSpePracticeBySpeIdAndPracticeId(speId, practiceId);
		if (old != null) {
			s.setRecordFlow(old.getRecordFlow());
		}
		if (StringUtil.isNotBlank(s.getRecordFlow())) {
			GeneralMethod.setRecordInfo(s, false);
			return scpMapper.updateByPrimaryKeySelective(s);
		} else {//新增
			s.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(s, true);
			return scpMapper.insertSelective(s);
		}
	}

	private JsresSpeContrastPractice getSpePracticeBySpeIdAndPracticeId(String speId, String practiceId) {

		JsresSpeContrastPracticeExample example=new JsresSpeContrastPracticeExample();
		example.createCriteria().andSpeIdEqualTo(speId).andPracticeIdEqualTo(practiceId);
		List<JsresSpeContrastPractice> list=scpMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
