package com.pinde.sci.biz.dwjxres;

import com.pinde.sci.model.dwjxres.StuEvalCfgExt;
import com.pinde.sci.model.dwjxres.StuEvalCfgItemExt;
import com.pinde.sci.model.dwjxres.StuEvalCfgTitleExt;
import com.pinde.sci.model.mo.ResDoctorProcessEvalConfig;
import com.pinde.sci.model.mo.ResDoctorSchProcessEval;
import com.pinde.sci.model.mo.ResDoctorSchProcessEvalWithBLOBs;
import com.pinde.sci.model.res.StuUserExt;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * 用于操作与考核指标相关的
 * Created by pdkj on 2017/5/22.
 */
public interface IResDoctorEvaluationBiz {
    //获取对应角色考评指标
    ResDoctorProcessEvalConfig readEvalConfig(String roleId);

    //保存考评项目
    int saveEvalConfig(String configFlow, StuEvalCfgTitleExt title, String roleId) throws DocumentException;

    //删除评分项目
    int deleteTitle(String configFlow, String id) throws DocumentException;

    //删除评分指标
    int deleteItem(String configFlow, String id) throws DocumentException;

    //保存考评指标
    int modifyItem(String configFlow, StuEvalCfgItemExt title) throws DocumentException;

    //保存所有评分项目考评指标
    int saveFormItemList(StuEvalCfgExt form) throws DocumentException;

    //保存学员评价数据
    int saveForm(Map<String, Object> param);

    //查询学员评价
    ResDoctorSchProcessEvalWithBLOBs readProcessEvalByFlow(String recordFlow);

    /**
     * 根据processFlow查询评价
     *
     * @param resumeFlow          processsFlow
     * @param kaoherenFlowList    考核人流水号
     * @param beikaoherenFlowList 被考核人流水号
     * @return
     */
    List<ResDoctorSchProcessEval> queryEvalListByFlow(String resumeFlow, List<String> kaoherenFlowList, List<String> beikaoherenFlowList);
    /**
     * 根据ResDoctorSchProcessEval属性查询评价
     *
     * @param  schProcessEval
     * @return
     */
    List<ResDoctorSchProcessEval> searchByItems(ResDoctorSchProcessEval schProcessEval);

    /**
     * 用于教秘查询评价学员
     * @param parMp
     * @return
     */
    List<StuUserExt> searchStuUserForDept(Map<String, Object> parMp);
    /**
     * 用于带教查询评价学员
     * @param parMp
     * @return
     */
    List<StuUserExt> searchStuUserForTeacher(Map<String, Object> parMp);
}
