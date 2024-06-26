package com.pinde.sci.biz.czyyjxres;

import com.pinde.sci.model.mo.StuFurtherStudyRegist;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

public interface ICzjxStuFurtherStudyRegistBiz {

    int saveRegist(StuFurtherStudyRegist stuFurtherStudyRegist);

    List<StuFurtherStudyRegist> searchRegist(StuFurtherStudyRegist stuFurtherStudyRegist);

    StuFurtherStudyRegist readRegist(String recordFlow);

    List<SysUser> readUserByEqDeptNameAndUserName(String userName, String deptName);
}
