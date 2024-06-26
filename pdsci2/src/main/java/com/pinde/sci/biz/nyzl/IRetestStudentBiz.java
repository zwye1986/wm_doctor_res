package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlRetestStudent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRetestStudentBiz {
    //查询复试人员信息
    List<NyzlRetestStudent> searchRetestStudentList(NyzlRetestStudent retestStudent);
    //根据recordFlow查询
    NyzlRetestStudent searchStudentByRecordFlow(String recordFlow);
    //保存复试人员信息
    int save(NyzlRetestStudent retestStudent);
    //复试学员信息导入操作
    int importRetestStudent(MultipartFile file,String educationTypeId,String swapBatchId,String stuSignFlag,String adminFlag);
    //考生信息导入（硕士生、夏令营、推免生）
    int importStuInfo(MultipartFile file,String stuSignFlag,String adminFlag);
}
