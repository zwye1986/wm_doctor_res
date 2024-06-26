package com.pinde.sci.ctrl.res;

import java.text.ParseException;

/**
 * @创建人 zsq
 * @创建时间 2020/10/12
 * 描述
 */
public interface TeachingActivityJob {

    //app使用
    void shengshiApp();
    //轮转
    void shengshiDoctorLUNZHUAN_FIND();
    //出科情况
    void shengshiDoctorOutOffice();
    //教学活动
    void shengshiTeachActive() throws ParseException;
}
