package com.pinde.sci.biz.res;


import com.pinde.sci.model.mo.ResDiscipleTeacherInfo;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface IResDiscipleTeacherInfoBiz {

    ResDiscipleTeacherInfo readResDiscipleTeacherInfo(String userFlow);

    int savaResDiscipleTeacherInfo(ResDiscipleTeacherInfo bean);

    /**
     * 根据主键查询老师简况表
     * @param infoFlow
     * @return
     */
    ResDiscipleTeacherInfo readResDiscipleTeacherInfoByFlow(String infoFlow);
}
