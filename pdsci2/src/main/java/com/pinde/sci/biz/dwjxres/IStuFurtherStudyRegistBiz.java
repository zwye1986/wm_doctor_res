package com.pinde.sci.biz.dwjxres;

import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.SingUpForm;
import com.pinde.sci.model.mo.StuFurtherStudyRegist;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface IStuFurtherStudyRegistBiz {

    int saveRegist(StuFurtherStudyRegist stuFurtherStudyRegist);

    List<StuFurtherStudyRegist> searchRegist(StuFurtherStudyRegist stuFurtherStudyRegist);

    StuFurtherStudyRegist readRegist(String recordFlow);

    List<SysUser> readUserByEqDeptNameAndUserName(String userName, String deptName);
}
