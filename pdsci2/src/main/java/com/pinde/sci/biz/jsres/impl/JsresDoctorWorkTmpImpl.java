package com.pinde.sci.biz.jsres.impl;

import com.pinde.sci.biz.jsres.IJsresDoctorWorkTmpBiz;
import com.pinde.sci.dao.jsres.JsresDoctorWorkTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @创建人 zsq
 * @创建时间 2020/7/23
 * 描述
 */
@Service
@Component
public class JsresDoctorWorkTmpImpl implements IJsresDoctorWorkTmpBiz {

    @Autowired
    private JsresDoctorWorkTmp tmp;

//    @Scheduled(cron = "0 0 0 */1 * ?") // todo moved
//    @Scheduled(cron = "0 */1 * * * ?")
    @Override
    public void startKaoQinQuartz() {
        tmp.InStartKaoQinQuartz();
    }
}
