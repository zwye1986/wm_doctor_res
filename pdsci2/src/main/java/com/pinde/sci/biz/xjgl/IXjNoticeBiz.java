package com.pinde.sci.biz.xjgl;

import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.InxInfo;

import java.util.List;

public interface IXjNoticeBiz {
    /**
     * 根据条件查询通知 根据创建时间倒序排序
     */
    List<EduTeachingnotice> findNotice(EduTeachingnotice teachingnotice);

    /**
     * 根据流水号查询通知
     */
    EduTeachingnotice findNoticByFlow(String flow);

    /**
     * 删除通知
     */
    int delNotice(String flow);

    int editNotice(EduTeachingnotice teachingnotice);

    List<EduTeachingnotice> searchAllNotice();

    /**
     * 根据角色流水号查询通知
     */
    List<EduTeachingnotice> searchByRoles(String sysRoleFlow);
}
