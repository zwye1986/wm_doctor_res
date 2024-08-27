package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresSpeContrastPractice;
import com.pinde.sci.model.mo.JsresSpeContrastPracticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresSpeContrastPracticeMapper {
    int countByExample(JsresSpeContrastPracticeExample example);

    int deleteByExample(JsresSpeContrastPracticeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresSpeContrastPractice record);

    int insertSelective(JsresSpeContrastPractice record);

    List<JsresSpeContrastPractice> selectByExample(JsresSpeContrastPracticeExample example);

    JsresSpeContrastPractice selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresSpeContrastPractice record, @Param("example") JsresSpeContrastPracticeExample example);

    int updateByExample(@Param("record") JsresSpeContrastPractice record, @Param("example") JsresSpeContrastPracticeExample example);

    int updateByPrimaryKeySelective(JsresSpeContrastPractice record);

    int updateByPrimaryKey(JsresSpeContrastPractice record);
}