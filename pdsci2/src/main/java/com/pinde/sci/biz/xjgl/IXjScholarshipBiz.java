package com.pinde.sci.biz.xjgl;

import com.pinde.sci.form.xjgl.XjScholarshipApplyForm;
import com.pinde.sci.model.mo.NyjzScholarshipMain;

import java.util.List;
/**
 * @author suncg
 */
public interface IXjScholarshipBiz {

    /**
     * 奖助申请查询
     * @param recordFlow 流水号
     * @return NyjzScholarshipMain
     */
    NyjzScholarshipMain readScholarship(String recordFlow);
    /**
     * 奖助申请列表查询
     * @param main 奖助申请对象
     * @return List<NyjzScholarshipMain>
     */
    List<NyjzScholarshipMain> queryStuApplyList(NyjzScholarshipMain main);
    /**
     * 奖助申请保存
     * @param main 奖助申请对象
     * @return int
     */
    int saveApplyInfo(NyjzScholarshipMain main,XjScholarshipApplyForm scholarship);
    /**
     * 取消奖助申请
     * @param recordFlow 流水号
     * @return int
     */
    int delApplyInfo(String recordFlow);
    /**
     * 奖助申请审核操作
     * @param main 奖助申请对象
     * @return int
     */
    int updateAuditOption(NyjzScholarshipMain main);

    /**
     * 包装额外信息xml为form对象
     * @param content
     * @return
     */
    XjScholarshipApplyForm parseExtInfoXml(String content);

}
