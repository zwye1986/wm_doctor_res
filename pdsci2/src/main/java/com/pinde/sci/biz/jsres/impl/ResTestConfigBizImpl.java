package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResTestConfigMapper;
import com.pinde.sci.model.mo.ResTestConfig;
import com.pinde.sci.model.mo.ResTestConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResTestConfigBizImpl implements IResTestConfigBiz {
    @Autowired
    private ResTestConfigMapper resTestConfigMapper;

    //查询所有的考试
    @Override
    public List<ResTestConfig> findAll() {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME DESC");
        return resTestConfigMapper.selectByExample(example);
    }

    //获取江苏省所有城市
    @Override
    public List<Map<String, String>> getAllCitys() {
        List<Map<String, String>> citys = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityId", "320100");
        city.put("cityName", "南京市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320200");
        city.put("cityName", "无锡市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320300");
        city.put("cityName", "徐州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320400");
        city.put("cityName", "常州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320500");
        city.put("cityName", "苏州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320600");
        city.put("cityName", "南通市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320700");
        city.put("cityName", "连云港市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320800");
        city.put("cityName", "淮安市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320900");
        city.put("cityName", "盐城市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321000");
        city.put("cityName", "扬州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321100");
        city.put("cityName", "镇江市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321200");
        city.put("cityName", "泰州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321300");
        city.put("cityName", "宿迁市");
        citys.add(city);
        return citys;
    }

    //获取江苏省所有城市
    @Override
    public List<Map<String, String>> getAllCitysNew() {
        List<Map<String, String>> citys = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityId", "120100");
        city.put("cityName", "市辖区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120101");
        city.put("cityName", "和平区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120102");
        city.put("cityName", "河东区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120103");
        city.put("cityName", "河西区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120104");
        city.put("cityName", "南开区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120105");
        city.put("cityName", "河北区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120106");
        city.put("cityName", "红桥区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120110");
        city.put("cityName", "东丽区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120111");
        city.put("cityName", "西青区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120112");
        city.put("cityName", "津南区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120113");
        city.put("cityName", "北辰区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120114");
        city.put("cityName", "武清区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120115");
        city.put("cityName", "宝坻区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120116");
        city.put("cityName", "滨海新区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120117");
        city.put("cityName", "宁河区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120118");
        city.put("cityName", "静海区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120119");
        city.put("cityName", "蓟州区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120200");
        city.put("cityName", "辖县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120221");
        city.put("cityName", "宁河县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120223");
        city.put("cityName", "静海县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120225");
        city.put("cityName", "蓟县");
        citys.add(city);
        return citys;
    }

    //插入一条记录
    @Override
    public int insert(ResTestConfig resTestConfig) {
        if (StringUtil.isNotBlank(resTestConfig.getTestFlow())) {
            GeneralMethod.setRecordInfo(resTestConfig, false);
            ResTestConfigExample example = new ResTestConfigExample();
            example.createCriteria().andTestFlowEqualTo(resTestConfig.getTestFlow());
            return resTestConfigMapper.updateByExampleSelective(resTestConfig, example);
        } else {
            resTestConfig.setTestFlow(PkUtil.getUUID());
            ResTestConfigExample example = new ResTestConfigExample();
            example.setOrderByClause("TEST_ID DESC");
            //查询出所有的考试
            List<ResTestConfig> resTestConfigs = resTestConfigMapper.selectByExample(example);
            if (resTestConfigs.size() > 0) {
                //判断今年有没有已经新增的考试
                if (DateUtil.getYear().equals(resTestConfigs.get(0).getTestId().substring(0, 4))) {
                    //如果有那么考试编号+1
                    resTestConfig.setTestId(String.valueOf(Integer.valueOf(resTestConfigs.get(0).getTestId()) + 1));
                } else {
                    //如果没有那么考试编号为当年年份拼接上01
                    resTestConfig.setTestId(DateUtil.getYear() + "01");
                }
            } else {
                resTestConfig.setTestId(DateUtil.getYear() + "01");
            }
            GeneralMethod.setRecordInfo(resTestConfig, true);
            return resTestConfigMapper.insert(resTestConfig);
        }
    }

    //校验当前时间段有没有进行中的考试
    @Override
    public Boolean checkTestExist(ResTestConfig resTestConfig) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        //获取出所有非关闭的考试
        List<ResTestConfig> resTestConfigs = resTestConfigMapper.selectByExample(example);
        if (StringUtil.isNotBlank(resTestConfig.getTestFlow())) {
            //如果是编辑当前这条考试信息，排除当前这条记录不考虑（如果不排除的话编辑这条考试记录时间会与自己冲突）
            resTestConfigs = resTestConfigs.stream().filter(resTest -> !resTestConfig.getTestFlow().equals(resTest.getTestFlow())).collect(Collectors.toList());
        }
        //新增的考试时间和库里已存在的考试时间重叠的集合
        List<ResTestConfig> list = resTestConfigs.stream().filter(resTest -> (resTestConfig.getApplyStartTime().compareTo(resTest.getApplyStartTime()) >= 0
                && resTestConfig.getApplyStartTime().compareTo(resTest.getTestEndTime()) <= 0
        ) || (resTestConfig.getTestEndTime().compareTo(resTest.getApplyStartTime()) >= 0 &&
                resTestConfig.getTestEndTime().compareTo(resTest.getTestEndTime()) <= 0)).collect(Collectors.toList());
        List<ResTestConfig> collect = resTestConfigs.stream().filter(resTest -> resTestConfig.getApplyStartTime().compareTo(resTest.getApplyStartTime()) <= 0
                && resTestConfig.getTestEndTime().compareTo(resTest.getTestEndTime()) >= 0).collect(Collectors.toList());
        if (list.size() == 0 && collect.size() == 0) {
            return true;
        }
        return false;
    }
    //根据唯一标识获取考试信息
    @Override
    public ResTestConfig findOne(String testFlow) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andTestFlowEqualTo(testFlow);
        List<ResTestConfig> resTestConfigs = resTestConfigMapper.selectByExample(example);
        if (resTestConfigs.size() > 0) {
            return resTestConfigs.get(0);
        }
        return new ResTestConfig();
    }
    //关闭当前的考试
    @Override
    public int closeTest(String testFlow) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andTestFlowEqualTo(testFlow);
        List<ResTestConfig> resTestConfigs = resTestConfigMapper.selectByExample(example);
        if (resTestConfigs.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigs.get(0);
            resTestConfig.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            GeneralMethod.setRecordInfo(resTestConfig, false);
            ResTestConfigExample resTestConfigExample = new ResTestConfigExample();
            resTestConfigExample.createCriteria().andTestFlowEqualTo(resTestConfig.getTestFlow());
            return resTestConfigMapper.updateByExampleSelective(resTestConfig, example);
        }
        return 0;
    }
    //获取当前城市在当前时间存在的考试
    @Override
    public List<ResTestConfig> findEffective(String currDateTime, String orgCityId) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andApplyEndTimeGreaterThanOrEqualTo(currDateTime).andCitysIdLike("%" + orgCityId + "%");
        return resTestConfigMapper.selectByExample(example);
    }
    //获取所有非关闭的考试
    @Override
    public List<ResTestConfig> findAllEffective() {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return resTestConfigMapper.selectByExample(example);
    }
    //获取基地审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findLocalEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andLocalAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andLocalAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }
    //获取市局审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findChargeEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andChargeAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andChargeAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }
    //获取省厅审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResTestConfig> findGlobalEffective(String currDateTime) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    /**
     * @param currDateTime
     * @param applyTime
     * @param orgCityId
     * @Department：研发部
     * @Description 查询结业申请创建时间内的考试时间没有结束的考试信息
     * @Author fengxf
     * @Date 2020/6/16
     */
    @Override
    public List<ResTestConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(applyTime)
                .andApplyEndTimeGreaterThanOrEqualTo(applyTime).andCitysIdLike("%" + orgCityId + "%")
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return resTestConfigMapper.selectByExample(example);
    }

    @Override
    public List<ResTestConfig> findTestConfigByCurrYear(String year) {
        ResTestConfigExample example = new ResTestConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andTestIdLike(year + "%");
        return resTestConfigMapper.selectByExample(example);
    }
}
