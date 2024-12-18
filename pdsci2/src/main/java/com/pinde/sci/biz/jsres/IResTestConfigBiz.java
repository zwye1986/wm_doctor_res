package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResTestConfig;

import java.util.List;
import java.util.Map;

public interface IResTestConfigBiz {
    //查询所有的考试
    List<ResTestConfig> findAll();

    //获取江苏省所有城市
    List<Map<String, String>> getAllCitys();

    //获取天津市所有区
    List<Map<String, String>> getAllCitysNew();

    //插入一条记录
    int insert(ResTestConfig resTestConfig);

    //校验当前时间段有没有进行中的考试
    Boolean checkTestExist(ResTestConfig resTestConfig);

    //根据唯一标识获取考试信息
    ResTestConfig findOne(String testFlow);

    //关闭当前的考试
    int closeTest(String testFlow);

    //获取当前城市在当前时间存在的考试
    List<ResTestConfig> findEffective(String currDateTime, String orgCityId);

    //获取所有非关闭的考试
    List<ResTestConfig> findAllEffective();

    //获取基地审核时间包含当前时间的所有非关闭的考试
    List<ResTestConfig> findLocalEffective(String currDateTime);

    //获取市局审核时间包含当前时间的所有非关闭的考试
    List<ResTestConfig> findChargeEffective(String currDateTime);

    //获取省厅审核时间包含当前时间的所有非关闭的考试
    List<ResTestConfig> findGlobalEffective(String currDateTime);

    /**
     * @Department：研发部
     * @Description 查询结业申请创建时间内的考试时间没有结束的考试信息
     * @Author fengxf
     * @Date 2020/6/16
     */
    List<ResTestConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId);

    List<ResTestConfig> findTestConfigByCurrYear(String year);
}
