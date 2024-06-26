package com.pinde.sci.biz.gzzyjxres;


import com.pinde.sci.model.mo.JxTuitionManagement;

import java.util.List;

public interface IJxTuitionManagementBiz {
    /**
     * 查询全部
     */
    List<JxTuitionManagement> listTuitionManagements();

    /**
     * 根据主键查询
     */
    JxTuitionManagement getTuition(String  recordFlow);

    /**
     * 根据费用项
     * @param costCategory
     * @return
     */
    List<JxTuitionManagement> getTuitionByCategory(String  costCategory);

    List<JxTuitionManagement> getTuitionByCategoryAndValue(String  costCategory,String costValue);

    /**
     * 保存学费
     */
    int saveTuition(JxTuitionManagement tuitionManagement);

    /**
     * 删除学费
     */
    int deleteTuition(JxTuitionManagement tuitionManagement);
}
