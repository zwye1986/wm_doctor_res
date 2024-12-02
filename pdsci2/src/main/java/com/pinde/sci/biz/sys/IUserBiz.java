package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.JsresUserBalcklist;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserDept;
import com.pinde.sci.model.mo.SysUserExample;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IUserBiz {

    List<SysUser> selectByNamesOrIdNo(List<String> userNameList,
                                      List<String> idNoList);

    int deleteUser(String userFlow);
    SysUser readSysUser(String sysUserFlow);
    SysUser findByIdNoAndCretType(SysUser sysUser);

    /**
     * 根据部门查用户
     * @param deptFlow
     * @return
     */
    List<SysUser> searcherUserByDeptFlow(String deptFlow);

    List<SysUser> readSysUserByExample(SysUserExample example);

    int addUser(SysUser user);

    int addTeaching(SysUser user);

    int insertUser(SysUser user);

    int saveUser(SysUser user);

    List<SysUser> searchUser(SysUser sysUser);

    List<SysUser> searchUserWithRole(Map<String,Object> paramMap);

    List<SysUser> searchUserWithRoleByJx(Map<String,Object> paramMap);
    List<SysUser> searchUserWithRoleByJx2(Map<String,Object> paramMap);

    SysUser findByUserCode(String userCode);

    SysUser findByIdNo(String idNo);

    //江苏中医，检查身份证号是否存在，及是否为黑名单
    SysUser findByNotBlackIdNo(String idNo);

    SysUser findByUserPhone(String userPhone);

    SysUser findByUserEmail(String userEmail);

    SysUser findByUserCodeNotSelf(String userFlow, String userCode);

    SysUser findByIdNoNotSelf(String userFlow, String idNo);

    /**
     * 根据证件类型和证件号查询不是指定用户的其他用户
     *
     * @param userFlow
     * @param idNo
     * @param cretTypeId
     * @return
     */
    SysUser findByIdNoAndCretTypeNotSelf(String userFlow, String idNo, String cretTypeId);

    SysUser findByUserPhoneNotSelf(String userFlow, String userPhone);

    SysUser findByUserEmailNotSelf(String userFlow, String userEmail);

    SysUser findByUserName(String userName);

    /**
     * 根据用户去修改密码
     *
     * @param sysUser
     * @return
     */
    int updateUser(SysUser sysUser);

    /**
     * 用于上级部门的查询 例如：主管部门
     *
     * @param sysUser
     * @param orgFlows
     * @return
     */
    List<SysUser> searchUserByOrgFlow(SysUser sysUser,
                                      List<String> orgFlows);

    void modifyUserByExample(SysUser user);

    /**
     * 根据用户flow集合查找用户
     *
     * @param
     * @return
     */

    List<SysUser> searchSysUserByuserFlows(List<String> userFlows);

    SysUser searcherUserByOrgFlow(String orgFlow);

    List<SysUser> searchSysUserByOrgFlows(List<String> orgFlows);

    /**
     * 用户注册审核
     *
     * @param user
     */
    void activateUser(SysUser user);

    List<SysUser> searchUserListByOrgFlow(Map<String, Object> paramMap);

    List<SysUser> searchSysUserByuserFlows(List<String> userFlows,
                                           String deptFlow);

    /**
     * 获取指定机构，指定角色的人员
     *
     * @param orgFlow
     * @param roleFlow
     * @return
     */
    List<SysUser> findUserByOrgFlowAndRoleFlow(String orgFlow, String roleFlow);




//	List<SysUser> findUserByRoleFlow(String orgFlow , String roleFlow);


//	List<SysUser> searchUserByStatus(SysUser user);

    List<SysUser> searchUserByRoleAndOrgFlows(String roleFlow,
                                              List<String> orgFlows);

    int saveUser(SysUser user, String roleFlow);

    /**
     * 获取拥有指定菜单的人员
     *
     * @param paramMap
     * @return
     */
    List<SysUser> searchUserByMenu(Map<String, Object> paramMap);

    void sendResetPassEmail(String userEmail, String userFlow);

    void authUserEmail(SysUser user);

    void addUserDept(SysUser user, List<String> deptFlows);

    List<SysUserDept> getUserDept(SysUser user);

    void disUserDept(SysUser user);

    SysUser findByUserCodeAndOrgFlow(String userCode, String orgFlow);

    List<SysUser> searchResManageUser(SysUser user, List<String> roleList,String roleFlow);

    List<SysUser> searchResManageUserByModeDept(SysUser user, String moreDept, List<String> roleList,String roleFlow);

    /**
     * 人员导入
     *
     * @param file
     * @return
     */
    int importUserFromExcel(MultipartFile file);
    /**
     * 人员导入
     *
     * @param file
     * @return
     */
    int importTeachingFromExcel(MultipartFile file,SysUser user);

    /**
     * 为指定用户保存头像
     *
     * @param userFlow
     * 
     * @param file
     * @return
     */
    String uploadImg(String userFlow, MultipartFile file);

    /**
     * 根据某个部门获取用户部门关系列表
     * @param deptFlow
     * @return
     */
//	List<SysUserDept> searchUserDeptByDept(String deptFlow);

    /**
     * 获取某个部门的指定角色用户(支持多部门)
     *
     * @param deptFlow
     * @param roleFlow
     * @return
     */
    List<SysUser> searchUserByDeptAndRole(String deptFlow, String roleFlow);

    /**
     * 筛选不在userFlows里的用户
     *
     * @param orgFlow
     * @param userFlows
     * @return
     */
    List<SysUser> searchUserNotInUserFlows(String orgFlow,
                                           List<String> userFlows);

    SysUser findByFlow(String userFlow);

    /**
     * 查询当前带教或科主任的待出科人员
     * @param process
     * @param user
     * @return
     */
//	List<SysUser> searchAfterAuditUser(ResDoctorSchProcess process, SysUser user);

    /**
     * 根据用户获取用户所有关联部门
     *
     * @param userFlow
     * @return
     */
    List<SysUserDept> searchUserDeptByUser(String userFlow);

    /**
     * 根据用户登录名获取user
     *
     * @param
     * @return
     */
//	List<SysUser> searchUserByUserCode(String userCode);

//	public String uploadImgWithWatermark(String userFlow, MultipartFile headImg);


    int importPerManageFromExcel(MultipartFile file, String[] roles);

    /**
     * 根据角色和deptFlow获取user
     *
     * @param deptFlow
     * @return
     */
    List<SysUser> teacherRoleCheckUser(String deptFlow, String role, String doctorName,String userFlow);

    List<SysUser> teacherRoleCheckUser2(String deptFlow, String role, String doctorName,String userFlow);

    /**
     * 根据rec和process的情况获取用户
     *
     * @param paramMap
     * @return
     */
    List<SysUser> getUserByRec(Map<String, Object> paramMap);


    void edit(SysUser sysUser);

    /**
     * 获取指定机构所有人
     */
    List<SysUser> getUserByOrg(String orgFlow);

    List<SysUser> getUserByRecForUni(Map<String, Object> paramMap);

    List<SysUser> searchResManageUserNotSelf(SysUser user, List<String> roleList, String userFlow, String isSelect, String examTeaRole);

    List<SysUser> searchResManageUserNotSelf2(SysUser user, List<String> roleList, String userFlow, String isSelect, String examTeaRole);
    /**
     * usercode like"%***%"
     */
    List<SysUser> searchSysUserByLikeCode(SysUser sysUser);

    List<SysUser> getUserByDeptFlows(List<String> deptFlows);

    List<SysUser> searchResManageUserByModeDept2(SysUser user, String moreDept, List<String> roleList1, String roleFlow, List<String> orgFlowList);

    List<SysUser> searchResManageUser2(SysUser user, List<String> roleList1, String roleFlow, List<String> orgFlowList);

    List<SysUser> readDeptTeachAndHead(String deptFlow, String teacherRoleFlow, String headRoleFlow, String trainTeacherRoleFlow);

    int importDiscAndResponFromExcel(MultipartFile file, String type);

    List<SysUser> searchOtherUserByDeptAndRole(String orgFlow, String deptFlow, String headRoleFlow);

    List<SysUser> readOrgTeas(String orgFlow, String teacherRoleFlow, String headRoleFlow, String secretaryRoleFlow, String trainTeacherRoleFlow);

    List<SysUser> readUserBySpe(String deptFlow, String professionalBaseAdminRoleFlow, String resTrainingSpeId, String orgFlow);

    List<SysUser> searchRecruitManagers(SysUser user, List<String> roleList);

    List<SysUser> selectByUserPhoneAndIsVerify(String userPhone);

    int saveRegisterUser(String userPhone,String code,String codeTime);

    SysUser selectByUserPhone(String userPhone);

    List<SysUser> checkPhoneIsVerify(String userPhone);

    int saveForgetPasswdUser(String userPhone, String code, String currDateTime);

    int saveAuthenSuccessUser(SysUser currentUser);

    int saveModifyUser(SysUser currentUser);

    SysUser findByUserEmailNew(String userEmail);

    SysUser findByUserCodeNew(String userEmail);

    SysUser loginByUserPhone(String userCode);

    int updateTeaSubmit(List<String> userFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateTeaNotSubmit(List<String> userFlowList);

    List<JsresUserBalcklist> selectBlacklistByIdNo(String idNo);

    /**
     * 用户只有一个角色权限，并且用户的权限与指定的权限是否相同
     * @param userFlow	用户流水号
     * @param roleFlow	指定角色的流水号
     * @return
     */
    boolean userISRole(String userFlow,String roleFlow);
}