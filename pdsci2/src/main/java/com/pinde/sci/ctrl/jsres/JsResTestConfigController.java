package com.pinde.sci.ctrl.jsres;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.model.mo.ResTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/testConfig")
public class JsResTestConfigController extends GeneralController {
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;

    @RequestMapping("/testMain")
    public String testMain(Model model) {
        return "jsres/completeCourse/testConfig/testMain";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAll();
        //当前年设置考试次数
        String addFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if(null != resTestConfigs && resTestConfigs.size()>0){
            List<ResTestConfig> testList = resTestConfigs.stream().filter(testConfig -> DateUtil.getYear().equals(testConfig.getTestId().substring(0, 4))
                    && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(testConfig.getRecordStatus())).collect(Collectors.toList());
            if(testList.size()>=2){
                addFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("addFlag",addFlag);
        Map<String, Boolean> map = new HashMap<>();
        String currDateTime = DateUtil.getCurrDateTime2();
        for (ResTestConfig resTestConfig : resTestConfigs) {
            if (currDateTime.compareTo(resTestConfig.getTestEndTime()) > 0) {
                map.put(resTestConfig.getTestFlow(), true);
            } else {
                map.put(resTestConfig.getTestFlow(), false);
            }
        }
        model.addAttribute("map", map);
        model.addAttribute("resTestConfigs", resTestConfigs);
        return "jsres/completeCourse/testConfig/main";
    }

    @RequestMapping("/addTest")
    public String addTest(Model model, String testFlow, String flag, HttpServletRequest request) {
        String applyEndTime = "";
        if (StringUtil.isNotBlank(testFlow)) {
            ResTestConfig resTestConfig = resTestConfigBiz.findOne(testFlow);
            String[] split = resTestConfig.getCitysId().split(",");
            List<String> cityList = Arrays.asList(split);
            model.addAttribute("cityList", cityList);
            model.addAttribute("resTestConfig", resTestConfig);
            applyEndTime = resTestConfig.getApplyEndTime();
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        List<Map<String, String>> citys = resTestConfigBiz.getAllCitys();
        model.addAttribute("citys", citys);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAll();
        String testName = "";
        //当前年设置考试次数
        if(null != resTestConfigs && resTestConfigs.size()>0){
            List<ResTestConfig> testList = resTestConfigs.stream().filter(testConfig -> DateUtil.getYear().equals(testConfig.getTestId().substring(0, 4))
                    && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(testConfig.getRecordStatus())).collect(Collectors.toList());
            if(testList.size()==0){
                testName = DateUtil.getYear()+"年上半年考试";
            }else if(testList.size()==1){
                testName = DateUtil.getYear()+"年下半年考试";
            }
        }
        model.addAttribute("testName",testName);
        if(StringUtil.isBlank(testFlow)) {
            String date = DateUtil.getCurrDate();
            applyEndTime = date + " 23:59:59";
        }
        model.addAttribute("applyEndTime", applyEndTime);
        return "jsres/completeCourse/testConfig/addTest";
    }

    @RequestMapping("/insertTest")
    @ResponseBody
    public String insertTest(ResTestConfig resTestConfig) {
        if (resTestConfigBiz.checkTestExist(resTestConfig)) {
            if (resTestConfigBiz.insert(resTestConfig) == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            } else {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        } else {
            return "当前时间段有进行中的考试，请修改考试时间";
        }
    }

    @RequestMapping("/closeTest")
    @ResponseBody
    public String closeTest(String testFlow) {
        if (StringUtil.isNotBlank(testFlow)) {
            if (resTestConfigBiz.closeTest(testFlow) == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            } else {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
        } else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }
}
