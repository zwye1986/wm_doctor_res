package com.pinde.sci.ctrl.srm;

import com.pinde.sci.common.GeneralController;
import com.pinde.sci.ctrl.res.TeachingActivityJob;
import com.pinde.sci.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

/**
 * @创建人 zsq
 * @创建时间 2020/10/12
 * 描述
 */
@Controller
@RequestMapping("/sys/job")
public class JobQuartzController extends GeneralController {

    @Autowired
    private Job job;

    @Autowired
    private TeachingActivityJob teachingActivityJob;

    /***
     * 基地app
     * @return
     */
    @RequestMapping(value={"/yyAppJob"})
    public String localApp_InsertData() {
        try {
            job.localApp_InsertData();
            return "执行成功!";
        } catch (ParseException e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }

    /***
     * 基地轮转
     * @return
     */
    @RequestMapping(value={"/yyLunzhuanJob"})
    public String localDoctorLunZhuanDataInsertData() {
        try {
            job.localDoctorLunZhuanDataInsertData();
            return "执行成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }

    /***
     * 省厅app
     * @return
     */
    @RequestMapping(value={"/stAppJob"})
    public String shengshiApp() {
        try {
            teachingActivityJob.shengshiApp();
            return "执行成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }

    /***
     * 省厅轮转
     * @return
     */
    @RequestMapping(value={"/stLunzhangJob"})
    public String shengshiDoctorLUNZHUAN_FIND() {
        try {
            teachingActivityJob.shengshiDoctorLUNZHUAN_FIND();
            return "执行成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }

    /***
     * 省厅出科
     * @return
     */
    @RequestMapping(value={"/stChukeJob"})
    public String shengshiDoctorOutOffice() {
        try {
            teachingActivityJob.shengshiDoctorOutOffice();
            return "执行成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }

    /***
     * 省厅教学活动
     * @return
     */
    @RequestMapping(value={"/stJxhdJob"})
    public String shengshiTeachActive() {
        try {
            teachingActivityJob.shengshiTeachActive();
            return "执行成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行失败!";
        }
    }
}
