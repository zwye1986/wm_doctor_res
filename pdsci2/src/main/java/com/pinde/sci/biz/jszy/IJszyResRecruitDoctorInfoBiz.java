package com.pinde.sci.biz.jszy;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJszyResRecruitDoctorInfoBiz {

    /**
     * 中医浮窗展示二级专业的招录人数
     * @return
     */
    List<Map<String,Object>>  zltjSecondSpe(Map<String,Object> param) ;

}
