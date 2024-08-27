package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.res.StuUserExt;

import java.util.List;
import java.util.Map;

public interface IStuUserResumeBiz {
    //根据stu_User_Resume表key查询记录
    StuUserResume getStuUserByKey(String resumeFlow);

    List<StuUserExt> searchUser(Map<String, Object> mp);
}
