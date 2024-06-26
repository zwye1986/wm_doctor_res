package com.pinde.sci.biz.res;



import com.pinde.sci.model.mo.GzzyyyAnnualAssessment;
import com.pinde.sci.model.mo.GzzyyyQuarterlyAssessment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface IGzzyyyAssessmentBiz {
    //编辑年度考核记录
    int editAnnualAssessment(GzzyyyAnnualAssessment annualAssessment);
    //查询年度考核记录
    List<GzzyyyAnnualAssessment> searchAnnualAssessment(GzzyyyAnnualAssessment annualAssessment);
    //年度考核导入
    int importAnnualAssessment(MultipartFile file);
    //编辑季度考核记录
    int editQuarterlyAssessment(GzzyyyQuarterlyAssessment quarterlyAssessment);
    //查询季度考核记录
    List<GzzyyyQuarterlyAssessment> searchQuarterlyAssessment(GzzyyyQuarterlyAssessment quarterlyAssessment);
    //季度考核导入
    int importQuarterlyAssessment(MultipartFile file);
}
