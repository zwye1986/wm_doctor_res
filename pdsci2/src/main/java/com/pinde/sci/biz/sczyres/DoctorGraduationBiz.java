package com.pinde.sci.biz.sczyres;

import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.form.sczyres.SingUpForm;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DoctorGraduationBiz {
    //结业申请基本信息保存和修改
    int saveBaseInfo(SysUser user, ResDoctor doctor, ExtInfoForm extInfoForm,String jsonData);
    //结业申请轮转信息保存
    int saveSchInfo(String jsonData);
    //编辑单条轮转信息
    int saveSingleSchInfo(ScresSchInfo scresSchInfo);
    //根据条件查询轮转信息
    List<ScresSchInfo> searchSchInfo(ScresSchInfo scresSchInfo);
    //编辑结业申请
    int saveApply(ScresGraduationApply apply);
    //根据主键查询结业申请
    ScresGraduationApply readApply(String recordFlow);
    //根据条件查询结业申请
    List<ScresGraduationApply> searchApply(ScresGraduationApply apply,List<String> orgFlows);
    //编辑结业申请
    int editApply(ScresGraduationApply apply);
    //结业_主基地查找申报的学员 本基地及协同基地
    List<ScresGraduationApply> searchAppliesMain(Map<String,Object> paramMap);
    //结业_中管局结业统计
    List<Map<String,Object>> graduationStatistics(Map<String,Object> paramMap);
    //结业_中管局准考证管理页面
    List<Map<String,Object>> ticketList(Map<String,Object> paramMap);
    //读取单个准考证详情
    ScresGraduationTicket readTicket(String recordFlow);
    //根据条件查询准考证
    List<ScresGraduationTicket> searchTicket(ScresGraduationTicket scresGraduationTicket);
    //编辑单个准考证
    int editTicket(ScresGraduationTicket ticket);
    //查询准考证重复
    List<ScresGraduationTicket> searchTicketNoRepeat(ScresGraduationTicket ticket);
    //管理开关
    int openPrint(String flag);
    //管理当前可用培训专业
    int changSpeAllow(String datas);
    //设置省厅默认是否审核学员减免
    int changAllowReduction(String data);
    //准考证导入
    int importTickets(MultipartFile file);
    //学员打印查询
    List<Map<String,Object>> searchDocrtor4Exl(Map<String,Object> paramMap);

    List<ScresGraduationApply> searchApplyMap(Map<String, Object> paramMap);

}
