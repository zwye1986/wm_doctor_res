package com.pinde.sci.dao.xjgl;

import com.pinde.sci.model.mo.EduLog;
import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import com.pinde.sci.model.xjgl.XjEduUserInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XjEduNoticeExtMapper {

    /**
     * 根据角色流水查找教学通知
     */
    List<EduTeachingnotice> searchByRoles(@Param(value="userFlow")String userFlow);
}
