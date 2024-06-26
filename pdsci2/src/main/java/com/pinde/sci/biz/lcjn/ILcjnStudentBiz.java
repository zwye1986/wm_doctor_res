package com.pinde.sci.biz.lcjn;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ILcjnStudentBiz {
    //查询学员信息
    List<SysUser> searchStudent(Map<String,String> paramMap);
    //编辑学员信息
    int editStudent(SysUser user);
    //学员信息导入操作
    int importStudentExcel(MultipartFile file);
}
