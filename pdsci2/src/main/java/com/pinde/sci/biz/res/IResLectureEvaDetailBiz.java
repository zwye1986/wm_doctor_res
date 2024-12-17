package com.pinde.sci.biz.res;

import com.pinde.core.model.LectureInfoTarget;
import com.pinde.sci.model.mo.ResLectureEvaDetail;

import java.util.List;
import java.util.Map;

public interface IResLectureEvaDetailBiz {
    /**
     * 根据讲座流水查找记录
     */
    List<ResLectureEvaDetail> SearchByLectureFlow(String lectureFlow);
    /**
     * 通过学员流水号和讲座流水号查询评价记录
     */
    List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow,String lectureFlow );
    /**
     * 保存一条评价记录
     */
    int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail);
    /**
     * 根据讲座流水获取所有的评价类
     */
    List<ResLectureEvaDetail> searchByLectureFlow(String lectureFlow);

    /**
     * @Department：研发部
     * @Description 查询讲座活动用户评价信息
     * @Author fengxf
     * @Date 2020/2/14
     */
    List<ResLectureEvaDetail> searchUserEvalList(Map<String,String> param);

    /**
     * @Department：研发部
     * @Description 查找指定讲座的评价指标
     * @Author Zjie
     * @Date 0005, 2020年11月5日
     */
    List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow);
}