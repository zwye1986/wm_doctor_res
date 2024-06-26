package com.pinde.sci.biz.xjgl;

import com.pinde.sci.model.mo.NydsDoctorPaper;
import com.pinde.sci.model.mo.NydsDoctorTopic;
import com.pinde.sci.model.mo.NydsOfficialDoctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IXjTutorBiz {
    //导师信息
    NydsOfficialDoctor queryTutorByFlow(String tutorFlow);
    //图片保存操作
    String uploadImg(String tutorFlow, String identifyFlag, MultipartFile file);
    //附件保存操作
    String uploadFile(String tutorFlow, MultipartFile file);
    //保存导师信息
    int saveTutor(String tabFlag, NydsOfficialDoctor tutor, NydsDoctorPaper paper, NydsDoctorTopic topic);
    //导师论文信息
    List<NydsDoctorPaper> queryPaperByFlow(String tutorFlow);
    //导师课题信息
    List<NydsDoctorTopic> queryTopicByFlow(String tutorFlow);
    //删除论文/课题信息
    int delPaperTopicByFlow(String tabFlag, String recordFlow);
    //提交申请操作
    int applyOption(String tutorFlow);
    //导师列表
    List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor);
    //根据Flow查论文信息
    NydsDoctorPaper queryPaperByRecordFlow(String recordFlow);
    //根据Flow查课题信息
    NydsDoctorTopic queryTopicByRecordFlow(String recordFlow);
    //审核操作
    int auditOption(String role,String statusId,NydsOfficialDoctor tutor);
    //退回，导师从新提交并审核
    int backAuditOption(String tutorFlow);
    //导师查询(params：额外筛选条件)
    List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor,Map<String,String> params);
    //导师信息导入操作
    int importTutorExcel(MultipartFile file) throws Exception;
    //删除导师信息
    int delTutorByFlow(String delTutorByFlow);
    //停用/启用导师
    int blockTutorByFlow(NydsOfficialDoctor doctor);
}
