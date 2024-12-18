package com.pinde.sci.biz.res;

import com.pinde.core.model.LectureInfoTarget;
import com.pinde.core.model.ResLectureInfo;
import com.pinde.core.model.ResLectureScanRegist;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResLectureScanRegistBiz {
    /**
     * 根据userFlow和lectureFlow读取一条报名记录
     */
    ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow,String lectureFlow);
    /**
     * 生成一条报名记录
     */
    int editLectureScanRegist(String lectureFlow);
    int editLectureScanRegist(String lectureFlow,String limitNum);
    /**
     * 查询某个学生报过名或扫过码的记录
     */
    List<ResLectureScanRegist> searchByUserFLowAndRegist(String userFlow);
    /**
     * 查询某个讲座的报名扫码记录
     */
    List<ResLectureScanRegist> searchByLectureFlow(String lectureFlow);
    /**
     * 查询某个讲座的报名记录
     */
    List<ResLectureScanRegist> searchRegistByLectureFlow(String lectureFlow, List<String> roles);
    /**
     * 查询某一讲座所有已扫码的报名信息
     */
    List<ResLectureScanRegist> searchIsScan(String lectureFlow, List<String> roles);
    /**
     * 编辑
     */
    int edit(ResLectureScanRegist lectureScanRegist);
    //产品已扫码/已报名的人员名单（专题讲座）
    List<ResLectureScanRegist> queryScanRegistDocList(String lectureFlow,String flag);

    ResLectureScanRegist readByFlow(String registFlow);

    int saveRegist(ResLectureScanRegist regist);

    Map<String,String> registImg(String registFlow, MultipartFile checkFile, String fileAddress);

    List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow);

    int delLectureScanRegist(String lectureFlow, String userFlow);


    /**
     * @Department：研发部
     * @Description 查询讲座评价指标信息
     * @Author fengxf
     * @Date 2020/2/13
     */
    List<Map<String,Object>> searchLectureTargetEvalList(String lectureFlow, List<String> roles);

    /**
     * @Department：研发部
     * @Description 查询讲座活动管理的评价指标列表
     * @Author fengxf
     * @Date 2020/2/13
     */
    List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow);

    /**
     * @Department：研发部
     * @Description 根据主键查询当前用户报名签到信息
     * @Author fengxf
     * @Date 2020/2/13
     */
    ResLectureScanRegist getLectureScanRegistInfoByFlow(String recordFlow);


    /**
     * 查询某一讲座所有已扫码的报名信息包含人员类型
     */
    List<Map<String, String>> searchIsScanNew(String lectureFlow, List<String> roles);

    /**
     * 查询某个讲座的报名记录包含人员类型
     */
    List<Map<String, String>> searchRegistByLectureFlowNew(String lectureFlow, List<String> roles);
}