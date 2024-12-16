package com.pinde.sci.biz.lcjn;

import com.pinde.core.model.LcjnDoctorSigin;

import java.util.List;
import java.util.Map;

public interface ILcjnDoctorSignBiz {
    /**
     * 学员签到列表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectDoctorSignList(Map<String, String> map);

    /**
     * 某个学员的签到情况
     * @param doctorFlow
     * @return
     */
    List<LcjnDoctorSigin> searchDoctorSigns(String doctorFlow,String courseFlow);
}
