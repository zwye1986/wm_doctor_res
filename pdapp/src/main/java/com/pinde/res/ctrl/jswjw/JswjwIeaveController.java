package com.pinde.res.ctrl.jswjw;

import com.pinde.core.common.enums.ResDoctorKqStatusEnum;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IIeaveAppBiz;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.core.model.PubFile;
import com.pinde.core.model.ResDoctorKq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Deacription
 * @Author shengl
 * @Date 2021-01-13 17:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/res/jswjw/leave")
public class JswjwIeaveController {

    @Autowired
    IIeaveAppBiz ieaveAppBiz;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private IFileBiz fileBiz;

    private static String successUrl = "res/jswjw/success";

    /**
     * @Author shengl
     * @Description // 请假列表
     * @Date 2021-01-14
     **/
    @RequestMapping(value = {"/leaveAuditList"}, method = {RequestMethod.POST})
    public String leaveAuditList(String userFlow, String statusId, Integer pageIndex, Integer pageSize, Model model, String roleId) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (!"Teacher".equals(roleId) && !"Head".equals(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符roleId错误");
            return successUrl;
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (!"Auditing".equals(statusId) && !"Audited".equals(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符错误只能是Auditing或Audited");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        //验证用户是否存在
        SysUser userinfo = userMapper.selectByPrimaryKey(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/teacher/leaveAuditList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }

        List<String> notStatus = new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        Map<String, Object> param = new HashMap<>();
        if ("Teacher".equals(roleId)) {
            param.put("teacherFlow", userFlow);
        } else {
            param.put("headFlow", userFlow);
        }
        param.put("roleFlag", roleId);
        param.put("statusId", statusId);
        param.put("orgFlow", userinfo.getOrgFlow());
        param.put("notStatus", notStatus);
        PageHelper.startPage(pageIndex, pageSize);
        List<ResDoctorKq> kqList = ieaveAppBiz.searchResDoctorKq(param);
        model.addAttribute("kqList", kqList);
        model.addAttribute("dataCount", PageHelper.total);
        int auditing = 0;
        int audited = 0;
        param.put("statusId", "");
        List<Map<String,Object>> countMap = ieaveAppBiz.queryResDoctorKqCount(param);
        if (countMap != null && countMap.size() >0) {
            for (Map<String,Object> map : countMap) {
                String type = (String)map.get("TYPE");
                int scount = Integer.parseInt(map.get("SCOUNT")+"");
                if("audited".equals(type)){
                    audited = scount;
                } else if("auditing".equals(type)){
                    auditing = scount;
                }
            }
        }
        model.addAttribute("audited", audited);
        model.addAttribute("auditing", auditing);
        return "res/jswjw/leave/leaveAuditList";
    }

    /**
     * @Author shengl
     * @Description // 查看详情
     * @Date 2021-01-14
     **/
    @RequestMapping(value = {"/showLeave"}, method = {RequestMethod.POST})
    public String showLeave(String userFlow, String recordFlow, Model model, String roleId) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        String leaveUrl = "res/jswjw/leave/showLeave";
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return leaveUrl;
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return leaveUrl;
        }
        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为错误");
            return leaveUrl;
        }
        model.addAttribute("kq", kq);
        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile", recordFlow);
        model.addAttribute("files", files);
        //获取访问路径前缀
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        if(userFlow.equals(kq.getTeacherFlow()) && !"-".equals(kq.getTeacherName())){
            return "res/jswjw/leave/showTeacherLeave";
        }
        return leaveUrl;
    }

    /**
     * @Author shengl
     * @Description // 审核
     * @Date 2021-01-14
     **/
    @RequestMapping(value = {"/auditLeave"}, method = {RequestMethod.POST})
    public String auditLeave(String userFlow, String recordFlow, String teacherAgreeFlag, String auditInfo, Model model, String roleId) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        boolean isTeacher = "Teacher".equals(roleId);
        boolean isHead = "Head".equals(roleId);
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (!isTeacher && !isHead) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符roleId错误");
            return successUrl;
        }
        //验证用户是否存在
        SysUser userinfo = userMapper.selectByPrimaryKey(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return successUrl;
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return successUrl;
        }
        if (StringUtil.isBlank(teacherAgreeFlag)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态为空");
            return successUrl;
        }
        if (isTeacher && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(teacherAgreeFlag) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(teacherAgreeFlag)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是Y通过或N不通过");
            return successUrl;
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(teacherAgreeFlag) && StringUtil.isBlank(auditInfo)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核信息为空");
            return successUrl;
        }
        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为错误");
            return successUrl;
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        if (isTeacher) {
            if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已审核，请刷新列表页");
                return successUrl;
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(teacherAgreeFlag)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
                if ("-".equals(kq.getHeadName())) {
                    if ("-".equals(kq.getManagerName())) {
                        kq.setAuditRoleNow("Pass");
                    }else {
                        kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                    }
                }else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD);
                    // 判断带教和科主任是否同一人审核，如果是审批同时通过
                    String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                    if(teacherFlow.equals(kq.getHeadFlow())){
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                        if ("-".equals(kq.getManagerName())) {
                            kq.setAuditRoleNow("Pass");
                        }else {
                            kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                        }
                        kq.setHeadAuditTime(time);
                        kq.setHeadAgreeFlag(teacherAgreeFlag);
                    }
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setTeacherAuditTime(time);
            kq.setTeacherAgreeFlag(teacherAgreeFlag);
            kq.setTeacherAuditInfo(auditInfo);

        } else if (isHead){
            if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已审核，请刷新列表页");
                return successUrl;
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(teacherAgreeFlag)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                if ("-".equals(kq.getManagerName())) {
                    kq.setAuditRoleNow("Pass");
                }else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setHeadAuditTime(time);
            kq.setHeadAgreeFlag(teacherAgreeFlag);
            kq.setHeadAuditInfo(auditInfo);
        }
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(userinfo.getUserFlow());
        int n = ieaveAppBiz.updateResDoctorKq(kq);
        if (n == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "审核失败");
            return successUrl;
        }
        return successUrl;
    }
}
