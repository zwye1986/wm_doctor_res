package com.pinde.sci.biz.czyyjxres;


import com.pinde.sci.model.mo.JxWeekendsRegister;

import java.util.List;

public interface ICzJxWeekendsRegisterBiz {
    /**
     * 查询全部
     */
    List<JxWeekendsRegister> listWeekends();

    /**
     * 根据主键查询
     */
    JxWeekendsRegister getWeekend(String recordFlow);

    List<JxWeekendsRegister> getWeekendByDate(String weekendDate);

    /**
     * 保存
     */
    int saveWeekend(JxWeekendsRegister weekendsRegister);

    /**
     * 删除
     */
    int deleteWeekend(JxWeekendsRegister weekendsRegister);
}
