package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.model.mo.EduLog;
import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.gyxjgl.XjEduUserExt;
import com.pinde.sci.model.gyxjgl.XjEduUserInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GyXjEduNoticeExtMapper {

    /**
     * 根据角色流水查找教学通知
     */
    List<EduTeachingnotice> searchByRoles(@Param(value="userFlow")String userFlow,@Param(value="infoTypeId")String infoTypeId);

    /**
     * 查询代办事项数
     * @return
     */
    Map<String,Object> searchAgencyThing();
}
