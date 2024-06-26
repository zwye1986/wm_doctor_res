package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.JsresSpeContrastPractice;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserDept;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ISpePracticeBiz {

    List<JsresSpeContrastPractice> getSpePracticeBySpeId(String trainingSpeId);

    int save(JsresSpeContrastPractice speContrastPractice);
}