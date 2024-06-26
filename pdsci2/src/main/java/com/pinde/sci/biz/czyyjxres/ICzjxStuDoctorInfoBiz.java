package com.pinde.sci.biz.czyyjxres;

import com.pinde.sci.model.mo.StuDeptOfStaff;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface ICzjxStuDoctorInfoBiz {
    /**
     * sysUser有啥就存啥没有就存空
     *
     * @param sysUser
     * @return
     */
    int updateUserByExp(SysUser sysUser);

    /**
     * 保存人员基本信息和所分配的科室
     *
     * @param sysUser 待保存的人员信息
     * @param deptFlow 所分配的科室
     * @param currentUser 当前操作的人员
     * @return
     */
    int saveUserAndDeptInfo(SysUser sysUser, String[] deptFlow, SysUser currentUser);

    /**
     * 获取科室配置用于保证单个科室只能有一个教秘
     * @param speLst 科室信息
     * @param sysUser 包含机构流水号和人员流水号
     * @return
     */
    void searchDeptOfUser(List<SysDict> speLst, SysUser sysUser);

    /**
     * 本院人员维护用根据条件查询本院带教/教秘
     * 因为科室可以绑定多条所以注意deptFlow处理
     * @param paramMap
     * @return
     */
    List<Map<String,String>> searchUser(Map<String, String> paramMap);

    /**
     * 查询该人员绑定的科室
     * @param tempUser
     * @return
     */
    List<SysDict> searchDeptByUser(SysUser tempUser);

    /**
     * 根据分配科室条件查询科室分配详情表
     * @param stuDeptOfStaff
     * @return
     */
    List<StuDeptOfStaff> searchIfDeptHasSecretary(StuDeptOfStaff stuDeptOfStaff);

    /**
     * 删除人员需将科室配置信息一起删除
     * @param sysUser
     * @return
     */
    int removeUser(SysUser sysUser);

    /**
     * 查询该科室下是否有本院人员(排除教秘角色)
     * @param sysUser
     * @return
     */
    List<StuDeptOfStaff> searchUserBydept(SysUser sysUser);
    /**
     * 将没有分配该科室的人员移除(排除教秘角色)
     * @param paramMap
     * @return
     */
    void operUserByMap(Map<String, Object> paramMap);

    /**
     * 统计当年已报到的学员人数
     * @param year
     * @return
     */
    int countAdmitedStuUsers(String year);
}
