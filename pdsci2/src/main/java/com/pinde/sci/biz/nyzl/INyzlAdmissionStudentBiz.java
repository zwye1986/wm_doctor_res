package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlAdmissionStudent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INyzlAdmissionStudentBiz {
    //查询学生录取情况信息
    List<NyzlAdmissionStudent> searchAdmissionStudentList(NyzlAdmissionStudent admissionStudent);
    //保存缺额调剂信息
    int save(NyzlAdmissionStudent admissionStudent);
    //复试学员信息导入操作
    int importAdmissionStudent(MultipartFile file, String stuSignFlag);

    NyzlAdmissionStudent readAdmissionStudent(String recordFlow);
}
