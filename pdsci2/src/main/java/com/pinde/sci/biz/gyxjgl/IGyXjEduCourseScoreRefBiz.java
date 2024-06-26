package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.model.mo.EduCourseScoreRef;

import java.util.List;

public interface IGyXjEduCourseScoreRefBiz {
    /**
     * 保存一条计分人员范围记录
     *
     * @param ref
     * @return
     */
    int saveScoreRef(EduCourseScoreRef ref);

    /**
     * 保存一组计分人员范围记录
     *
     * @param refList
     * @param courseFlow
     * @param refTypeId
     * @return
     */
    String saveScoreRefs(List<EduCourseScoreRef> refList, String courseFlow, String refTypeId);

    /**
     * 查询计分人员范围记录
     *
     * @param ref
     * @return
     */
    List<EduCourseScoreRef> searchScoreRefs(EduCourseScoreRef ref);
    /**
     * 读取一条计分人员范围记录
     * @param recordFlow
     * @return
     */
//	EduCourseScoreRef readScoreRef(String recordFlow);

    /**
     * 删除一条计分人员范围记录
     *
     * @param ref
     * @return
     */
    String deleteScoreRef(EduCourseScoreRef ref);

    /**
     * 删除一组计分人员范围记录
     *
     * @param refList
     * @return
     */
    String deleteScoreRefs(List<EduCourseScoreRef> refList);
}
