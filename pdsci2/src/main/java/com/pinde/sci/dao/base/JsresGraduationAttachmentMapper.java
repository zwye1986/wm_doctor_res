package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresGraduationAttachment;
import com.pinde.core.model.JsresGraduationAttachmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresGraduationAttachmentMapper {
    int countByExample(JsresGraduationAttachmentExample example);

    int deleteByExample(JsresGraduationAttachmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresGraduationAttachment record);

    int insertSelective(JsresGraduationAttachment record);

    List<JsresGraduationAttachment> selectByExample(JsresGraduationAttachmentExample example);

    JsresGraduationAttachment selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresGraduationAttachment record, @Param("example") JsresGraduationAttachmentExample example);

    int updateByExample(@Param("record") JsresGraduationAttachment record, @Param("example") JsresGraduationAttachmentExample example);

    int updateByPrimaryKeySelective(JsresGraduationAttachment record);

    int updateByPrimaryKey(JsresGraduationAttachment record);
}