package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlTeacherQuota;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INyzlTeacherQuotaBiz {
    //查询缺额调剂信息
    List<NyzlTeacherQuota> searchTeacherQuotaList(NyzlTeacherQuota teacherQuota);
    //保存缺额调剂信息
    int save(NyzlTeacherQuota teacherQuota);
    //复试学员信息导入操作
    int importTeacherQuota(MultipartFile file, String stuSignFlag);
}
