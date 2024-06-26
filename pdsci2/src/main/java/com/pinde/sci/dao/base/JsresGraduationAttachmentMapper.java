package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresGraduationAttachment;
import com.pinde.sci.model.mo.JsresGraduationAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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