package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResLectureEvaDetail;

import java.util.List;
import java.util.Map;

public interface ResLectureEvaDetailExtMapper {


	int saveLectureEval(Map<String, Object> paramMap);


	List<ResLectureEvaDetail> searchUserEvalList(Map<String, String> param);
}
