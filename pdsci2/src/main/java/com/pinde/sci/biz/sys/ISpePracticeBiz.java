package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.JsresSpeContrastPractice;

import java.util.List;

public interface ISpePracticeBiz {

    List<JsresSpeContrastPractice> getSpePracticeBySpeId(String trainingSpeId);

    int save(JsresSpeContrastPractice speContrastPractice);
}