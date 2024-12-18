package com.pinde.sci.biz.res;

import com.pinde.core.model.ResEvaluationCfg;
import com.pinde.core.model.SysDept;
import com.pinde.sci.form.res.ResEvaluationCfgForm;
import com.pinde.sci.form.res.ResEvaluationCfgItemForm;
import com.pinde.sci.form.res.ResEvaluationCfgTitleForm;
import com.pinde.sci.form.res.ResEvaluationDeptExt;

import java.util.List;

public interface IResEvaluationCfgBiz {

    /**
     * 保存
     * @param evaluationCfg
     * @return
     */
    int saveEvaluationCfg(ResEvaluationCfg evaluationCfg);

    /**
     * 保存表单关联科室
     * @return
     */
    int saveEvaluationDept(String cfgFlow,List<String> depts );

    /**
     * 获取一条记录
     * @param cfgFlow
     * @return
     */
    ResEvaluationCfg readResEvaluationCfg(String cfgFlow);


    /**
     * 获取一条记录
     * @param cfgFlow
     * @return
     */
    ResEvaluationCfg readResEvaluationCfgByDept(String deptFlow);
    /**
     * 查询
     * @param evaluationCfg
     * @return
     */
    List<ResEvaluationCfg> searchEvaluationCfgList(ResEvaluationCfg evaluationCfg);

    /**
     * 编辑考核指标标题
     * @param titleForm
     * @return
     * @throws Exception
     */
    int editEvaluationCfgTitle(ResEvaluationCfg evaluationCfg, ResEvaluationCfgTitleForm titleForm) throws Exception;

    /**
     * 删除考核指标标题
     * @param cfgFlow
     * @param id
     * @return
     * @throws Exception
     */
    int deleteTitle(String cfgFlow, String id) throws Exception;



    /**
     * 保存考核指标列表
     * @param form
     * @return
     * @throws Exception
     */
    int saveEvaluationItemList(ResEvaluationCfgForm form) throws Exception;

    /**
     * 修改考核指标
     * @param cfgFlow
     * @param itemForm
     * @return
     * @throws Exception
     */
    int modifyItem(String cfgFlow, ResEvaluationCfgItemForm itemForm) throws Exception;

    /**
     * 删除考核指标
     * @param cfgFlow
     * @param id
     * @return
     * @throws Exception
     */
    int deleteItem(String cfgFlow, String id) throws Exception;

    /**
     * 直接获取配置内的内容
     * @param cfgCodeId
     * @return
     */
    List<ResEvaluationCfgTitleForm> getParsedGrade(String cfgCodeId);


    String  getNextCodeId(String orgFlow, String id);

    List<ResEvaluationDeptExt> readEvaluationDeptList(String cfgFlow);

    List<SysDept> getDepts(String cfgFlow);

    int delEvaluation(String cfgFlow);

    ResEvaluationCfg read(String cfgFlow);
}
