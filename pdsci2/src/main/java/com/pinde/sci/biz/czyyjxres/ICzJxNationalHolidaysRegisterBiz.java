package com.pinde.sci.biz.czyyjxres;



import com.pinde.sci.model.mo.JxNationalHolidaysRegister;

import java.util.List;

public interface ICzJxNationalHolidaysRegisterBiz {
    /**
     * 查询全部
     */
    List<JxNationalHolidaysRegister> listNationalHolidays();

    /**
     * 根据主键查询
     */
    JxNationalHolidaysRegister getHoliday(String recordFlow);


    /**
     * 保存
     */
    int saveHoliday(JxNationalHolidaysRegister nationalHolidays);

    /**
     * 删除
     */
    int deleteHoliday(JxNationalHolidaysRegister nationalHolidays);
}
