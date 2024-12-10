package com.pinde.sci.biz.osca;


import com.pinde.sci.model.mo.OscaTeaInfo;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.osca.OscaExaminerExt;
import com.pinde.sci.model.osca.OscaTypeSpeExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IOscaExaminerManageBiz {

    /**
     * 查询user所有考官
     * @return
     */
    List<OscaExaminerExt> searchAllExam(Map<String, Object> map);


    List<OscaTeaInfo> getOscaTeaInfo(String userFlow);


    /**
     * 修改考官
     * @param sysUser
     * @param userRole
     * @return
     */
    int updateExamAndUser(SysUser sysUser, OscaTeaInfo oscaTeaInfo, List<OscaTypeSpeExt> typeSpeList, SysUserRole userRole);


    void importExamFromExcel(MultipartFile file);

    void importExamFromExcel1(MultipartFile file);

    List<SysUser> sysUserList(SysUser sysUser);
}
