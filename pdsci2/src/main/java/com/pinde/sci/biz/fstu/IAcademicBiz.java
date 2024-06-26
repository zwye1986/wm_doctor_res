package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuAcademicActivity;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IAcademicBiz {
    //保存学术申请记录及保存文件
    int saveAcademicActivity(FstuAcademicActivity activity,String filePath);
    //保存上传文件至指定目录
    String saveFileToDirs(MultipartFile file, String folderName);
    //学术申请记录查询
    List<FstuAcademicActivity> searchAcademicActivity(FstuAcademicActivity activity,String typeFlag);
    //单条学术申请记录查询
    FstuAcademicActivity searchAcademicByFlow(String academicFlow);
    //查询上传的出差凭证文件
    PubFile seachUpFile(String academicFlow);
    //伪删除学术申请记录
    int delAcademicByFlow(String academicFlow);
    //修改学术申请状态(提交)
    int updateAcademicByFlow(String academicFlow);
    //学术审核同意/退回/保存操作
    int updateAcademicActivity(FstuAcademicActivity activity);
    //修改学术报销申请状态(提交)及记录学术总结
    int updateExpenseByFlow(String academicFlow,String academicSummary);
    /**
     * 根据条件筛选学术申请记录
     */
    List<FstuAcademicActivity> search(FstuAcademicActivity activity,String startingTime,String endingTime,List<String> deptFlows);
    /**
     * 取某一年各项学术费用的类别和合计
     */
    Map<String,Integer> calculation(String year,List<String> deptFlows);
    /**
     * 保存学术申请记录及保存文件
     */
    int saveAcademicAndFiles(FstuAcademicActivity activity,List<PubFile> fileList);
}
