package com.pinde.sci.biz.jsres;

import com.pinde.core.model.JsresExamSignup;
import com.pinde.core.model.ResSkillTimeConfig;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResSkillTimeConfigBiz {
    //查询所有的考试
    List<ResSkillTimeConfig> findAll();

    //获取江苏省所有城市
    List<Map<String, String>> getAllCitys();

    //获取天津市所有区
    List<Map<String, String>> getAllCitysNew();

    //插入一条记录
    int insert(ResSkillTimeConfig skillTimeConfig);

    //校验当前时间段有没有进行中的考试
    Boolean checkTestExist(ResSkillTimeConfig skillTimeConfig);

    //根据唯一标识获取考试信息
    ResSkillTimeConfig findOne(String testFlow);

    //关闭当前的考试
    int closeTest(String skillTimeFlow);

    //获取当前城市在当前时间存在的考试
    List<ResSkillTimeConfig> findEffective(String currDateTime, String orgCityId);

    //获取所有非关闭的考试
    List<ResSkillTimeConfig> findAllEffective();

    //获取基地审核时间包含当前时间的所有非关闭的考试
    List<ResSkillTimeConfig> findLocalEffective(String currDateTime);

    //获取市局审核时间包含当前时间的所有非关闭的考试
    List<ResSkillTimeConfig> findChargeEffective(String currDateTime);

    //获取省厅审核时间包含当前时间的所有非关闭的考试
    List<ResSkillTimeConfig> findGlobalEffective(String currDateTime);

    /**
     * @Department：研发部
     * @Description 查询结业申请创建时间内的考试时间没有结束的考试信息
     * @Author fengxf
     * @Date 2020/6/16
     */
    List<ResSkillTimeConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId);

    ResSkillTimeConfig findOneByCurrDate(String currTime);

    int importDocSkillExcel(MultipartFile file,String cityId,String testId);

    JsresExamSignup findExamSignup(JsresExamSignup examSignup);

}
