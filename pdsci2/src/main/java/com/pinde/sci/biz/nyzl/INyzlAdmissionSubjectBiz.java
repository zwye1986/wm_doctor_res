package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlAdmissionSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface INyzlAdmissionSubjectBiz {
    //查询学科录取情况
    List<NyzlAdmissionSubject> searchAdmissionSubjectList(NyzlAdmissionSubject admissionSubject);
    //保存学科录取情况
    int save(NyzlAdmissionSubject admissionSubject);
    //学科录取情况导入操作
    int importAdmissionSubject(MultipartFile file, String stuSignFlag);
    //统计指标数
    Map<String,String> countAdmissionSubject(String recruitYear,String stuSignFlag,String orgFlow);
}
