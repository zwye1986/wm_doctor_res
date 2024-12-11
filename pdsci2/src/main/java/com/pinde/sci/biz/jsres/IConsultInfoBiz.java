package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ConsultInfo;

import java.util.HashMap;
import java.util.List;

public interface IConsultInfoBiz {
    List<ConsultInfo> search(ConsultInfo consultInfo,String orderBy);

    Integer saveConsultInfo(ConsultInfo consultInfo);

    List<String> searchOrgCityNameList();

    Boolean deleteAll(List<String> list);
    Integer delete(String consultInfoFlow);

    ConsultInfo read(String consultInfoFlow);

    /**
     * 条件查询,根据consultQuestion的内容对问题/答案进行模糊查询
     * @param map
     * @return
     */
    List<ConsultInfo> search2(HashMap<String,String> map);

    /**
     * 根据userFlow查询我的问题
     * @param userFlow
     * @return
     */
    List<ConsultInfo> searchMyQuestion(String userFlow);

    /**
     * 增加一次访问量
     * @param consultInfoFlow
     */
    void detail(String consultInfoFlow);

}
