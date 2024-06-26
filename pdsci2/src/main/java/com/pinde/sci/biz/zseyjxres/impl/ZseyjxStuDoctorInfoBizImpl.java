package com.pinde.sci.biz.zseyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxStuDoctorInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.StuDeptOfStaffMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.res.StuUserResumeExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.StuDeptOfStaff;
import com.pinde.sci.model.mo.StuDeptOfStaffExample;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pinde.sci.dao.gzzyjxres.GzStuUserExtMapper;
import com.pinde.sci.enums.gzzyjxres.StuRoleEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZseyjxStuDoctorInfoBizImpl implements IZseyjxStuDoctorInfoBiz {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private StuDeptOfStaffMapper stuDeptOfStaffMapper;
    @Autowired
    private StuUserResumeExtMapper stuUserResumeExtMapper;
    @Autowired
    private GzStuUserExtMapper gzStuUserExtMapper;

    @Override
    public int updateUserByExp(SysUser sysUser) {
        if (StringUtil.isNotBlank(sysUser.getUserFlow())) {
            GeneralMethod.setRecordInfo(sysUser, false);
            return sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int saveUserAndDeptInfo(SysUser sysUser, String[] deptFlow, SysUser currentUser) {
        int count;
        count = userBiz.saveUser(sysUser);
        //配置所属科室
        if (deptFlow != null && deptFlow.length > 0) {
            String userFlow = sysUser.getUserFlow();
            //根据人员userFlow查询科室配置表并将record_status设为N；
            StuDeptOfStaffExample example = new StuDeptOfStaffExample();
            example.createCriteria().andUserFlowEqualTo(userFlow);
            List<StuDeptOfStaff> deptsForStatus = stuDeptOfStaffMapper.selectByExample(example);
            if (deptsForStatus != null && deptsForStatus.size() > 0) {
                for (StuDeptOfStaff tempDept : deptsForStatus) {
                    tempDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    GeneralMethod.setRecordInfo(tempDept, false);
                    stuDeptOfStaffMapper.updateByPrimaryKey(tempDept);
                }
            }
            //对deptFlow循环
            for (String tempDeptFlow : deptFlow) {
                StuDeptOfStaff stuDeptOfStaff = null;
                String deptName = DictTypeEnum.getDictName(DictTypeEnum.DwjxSpe, tempDeptFlow);
                example.clear();
                //查询数据库中已有的考试范围科室Id（不区分状态）若有则改状态为Y
                example.createCriteria().andUserFlowEqualTo(sysUser.getUserFlow())
                        .andDeptFlowEqualTo(tempDeptFlow)
                        .andOrgFlowEqualTo(currentUser.getOrgFlow());
                List<StuDeptOfStaff> depts = stuDeptOfStaffMapper.selectByExample(example);
                if (depts != null && depts.size() > 0) {
                    stuDeptOfStaff = depts.get(0);
                    stuDeptOfStaff.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    stuDeptOfStaff.setDeptFlow(tempDeptFlow);
                    stuDeptOfStaff.setDeptName(deptName);
                    stuDeptOfStaff.setUserRole(sysUser.getIsOrgAdmin());
                    GeneralMethod.setRecordInfo(stuDeptOfStaff, false);
                    stuDeptOfStaffMapper.updateByPrimaryKey(stuDeptOfStaff);
                } else {
                    stuDeptOfStaff = new StuDeptOfStaff();
                    stuDeptOfStaff.setRecordFlow(PkUtil.getUUID());
                    stuDeptOfStaff.setUserFlow(sysUser.getUserFlow());
                    stuDeptOfStaff.setOrgFlow(currentUser.getOrgFlow());
                    stuDeptOfStaff.setOrgName(currentUser.getOrgName());
                    stuDeptOfStaff.setDeptFlow(tempDeptFlow);
                    stuDeptOfStaff.setDeptName(deptName);
                    stuDeptOfStaff.setUserRole(sysUser.getIsOrgAdmin());
                    GeneralMethod.setRecordInfo(stuDeptOfStaff, true);
                    stuDeptOfStaffMapper.insert(stuDeptOfStaff);
                }
            }
        }
        return count;
    }

    @Override
    public void searchDeptOfUser(List<SysDict> speLst, SysUser sysUser) {
        if (speLst != null && speLst.size() > 0) {
            for (Iterator iter = speLst.iterator(); iter.hasNext(); ) {
                SysDict tempDict = (SysDict) iter.next();
                StuDeptOfStaffExample example = new StuDeptOfStaffExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andDeptFlowEqualTo(tempDict.getDictId())
                        .andUserRoleEqualTo(StuRoleEnum.Secretary.getId())
                        .andOrgFlowEqualTo(sysUser.getOrgFlow());
                List<StuDeptOfStaff> depts = stuDeptOfStaffMapper.selectByExample(example);
                if (depts != null && depts.size() > 0) {
                    //如果userFlow不为空则判断userFlow是否为depts中配置的userFlow
                    if(StringUtil.isBlank(sysUser.getUserFlow())
                            || !sysUser.getUserFlow().equals(depts.get(0).getUserFlow()) ){
                        iter.remove();
                    }
                }
            }
        }
    }

    @Override
    public List<Map<String, String>> searchUser(Map<String, String> paramMap) {
        return stuUserResumeExtMapper.searchUserForOwner(paramMap);
    }

    @Override
    public List<SysDict> searchDeptByUser(SysUser sysUser) {
        StuDeptOfStaffExample example = new StuDeptOfStaffExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(sysUser.getUserFlow());
        List<StuDeptOfStaff> depts = stuDeptOfStaffMapper.selectByExample(example);
        List<SysDict> deptFowUser = new ArrayList<>();
        if (depts != null && depts.size() > 0) {
            for (int i = 0; i < depts.size(); i++) {
                SysDict tempDept = new SysDict();
                tempDept.setDictId(depts.get(i).getDeptFlow());
                tempDept.setDictName(depts.get(i).getDeptName());
                deptFowUser.add(tempDept);
            }
        }
        return deptFowUser;
    }

    @Override
    public List<StuDeptOfStaff> searchIfDeptHasSecretary(StuDeptOfStaff stuDeptOfStaff) {
        StuDeptOfStaffExample example = new StuDeptOfStaffExample();
        StuDeptOfStaffExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(stuDeptOfStaff.getDeptFlow())){
            criteria.andDeptFlowEqualTo(stuDeptOfStaff.getDeptFlow());
        }
        if(StringUtil.isNotBlank(stuDeptOfStaff.getUserRole())){
            criteria.andDeptFlowEqualTo(stuDeptOfStaff.getUserRole());
        }
        if(StringUtil.isNotBlank(stuDeptOfStaff.getUserFlow())){
            criteria.andUserFlowEqualTo(stuDeptOfStaff.getUserFlow());
        }
        return stuDeptOfStaffMapper.selectByExample(example);
    }

    @Override
    public int removeUser(SysUser sysUser) {
        StuDeptOfStaff stuDeptOfStaff = new StuDeptOfStaff();
        stuDeptOfStaff.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        StuDeptOfStaffExample example = new StuDeptOfStaffExample();
        StuDeptOfStaffExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sysUser.getUserFlow())){
            criteria.andUserFlowEqualTo(sysUser.getUserFlow());
        }
        stuDeptOfStaffMapper.updateByExampleSelective(stuDeptOfStaff,example);
        sysUser.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public List<StuDeptOfStaff> searchUserBydept(SysUser sysUser) {
        StuDeptOfStaffExample example = new StuDeptOfStaffExample();
        StuDeptOfStaffExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(sysUser.getDeptName())) {
            criteria.andDeptNameLike("%" + sysUser.getDeptName() + "%");
        }
        if (StringUtil.isNotBlank(sysUser.getUserFlow())) {
            criteria.andUserFlowEqualTo(sysUser.getUserFlow());
        }
        return stuDeptOfStaffMapper.selectByExample(example);
    }

    @Override
    public void operUserByMap(Map<String, Object> paramMap) {
        SysUser sysUser = (SysUser) paramMap.get("sysUser");
        List<SysUser> userList = (List<SysUser>) paramMap.get("userList");

        if (userList != null && userList.size() > 0) {
            for(int i = 0; i < userList.size(); i++){
                SysUser tempUser = userList.get(i);
                if (sysUser != null) {
                    tempUser.setDeptName(sysUser.getDeptName());
                }
                List<StuDeptOfStaff> userdeptList = searchUserBydept(tempUser);
                if (userdeptList == null || userdeptList.size() < 1) {
                    userList.remove(i);
                    i--;
                }
            }
        }

    }

    @Override
    public int countAdmitedStuUsers(String year) {
        return gzStuUserExtMapper.countAdmitedStuUsers(year);
    }
}
