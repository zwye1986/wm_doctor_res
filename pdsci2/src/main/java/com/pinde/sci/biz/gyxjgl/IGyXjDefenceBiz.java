package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.form.xjgl.XjPaperTitleExtForm;
import com.pinde.sci.model.mo.NydbCommitteeMember;
import com.pinde.sci.model.mo.NydbDefenceApply;
import com.pinde.sci.model.mo.NydbPaperTitle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGyXjDefenceBiz {

    //答辩申请信息查询
    List<NydbDefenceApply> queryDefenceList(NydbDefenceApply defence);
    //根据流水号答辩申请查询
    NydbDefenceApply queryDefenceByFlow(String recordFlow);
    //查询学员基本信息
    Map<String,Object> queryBaseInfo(String userFlow);
    //答辩申请 保存操作
    int saveDefence(NydbDefenceApply defence);
    //答辩申请 删除操作
    int delDefence(String recordFlow);
    //查询论文期刊题目
    List<Map<String,Object>> queryPaperTitleList(Map<String, String> params);
    //论文期刊题目 删除操作
    int delTitle(String recordFlow);
    //根据流水号论文期刊题目查询
    NydbPaperTitle queryTitleByFlow(String recordFlow);
    //包装额外信息xml为form对象
    XjPaperTitleExtForm parseExtInfoXml(String content);
    //保存 论文期刊题目信息
    int saveTitle(NydbPaperTitle title, XjPaperTitleExtForm extInfo);
    //作为学位申请论文 isReplenish 学位补授标识Y
    int degreePaperOption(String recordFlow);
    //答辩日程委员人员信息
    List<NydbCommitteeMember> queryMemberList(String recordFlow);
    //答辩日程信息保存操作
    int saveSchedule(NydbDefenceApply defence, List<NydbCommitteeMember> memberList);
    int importDefenceFromExcel(MultipartFile file) throws Exception;
    //查询学生对应专业学分要求
    Map<String,Object> searchMajorCredit(String userFlow);
}
