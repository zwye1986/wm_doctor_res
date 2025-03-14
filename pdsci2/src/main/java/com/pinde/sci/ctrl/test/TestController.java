package com.pinde.sci.ctrl.test;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.JsresDoctorDeptDetail;
import com.pinde.sci.biz.sch.impl.SchArrangeResultBizImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private SchArrangeResultBizImpl schArrangeResultBiz;
    @RequestMapping("/getJsresDoctorDeptDetail")
    public List<JsresDoctorDeptDetail> test(String recruitFlow, String doctorFlow, String applyYear, String rotationFlow){
        List<JsresDoctorDeptDetail> jsresDoctorDeptDetails = schArrangeResultBiz.deptDoctorAllWorkDetailByNow_new(recruitFlow, doctorFlow, applyYear, rotationFlow);
        return jsresDoctorDeptDetails;
    }
}
