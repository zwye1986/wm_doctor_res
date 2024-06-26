package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ExamResults;

import java.math.BigDecimal;
import java.util.List;

public interface IExamResultsBiz {

	ExamResults getByProcessFlowAndScore(String processFlow, BigDecimal theoryScore);

	List<ExamResults> getByProcessFlow(String processFlow);

	ExamResults getByFlow(String resultId);
}
