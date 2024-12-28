package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.JsresAttendanceDetailMapper;
import com.pinde.core.common.sci.dao.JsresAttendanceMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAttendanceBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.res.ResAttendanceExtMapper;
import com.pinde.sci.model.jsres.JsResAttendanceExt;
import com.pinde.sci.model.jszy.JszyResAttendanceExt;
import com.pinde.sci.model.res.ResAttendanceExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/8.
 */
@Service
public class ResAttendenceBizImpl implements IResAttendanceBiz {

    @Autowired
    private ResAttendanceExtMapper resAttendenceExtMapper;
    @Resource
    private JsresAttendanceMapper jsresAttendanceMapper;
    @Resource
    private JsresAttendanceDetailMapper jsresAttendanceDetailMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public List<JsResAttendanceExt> searchAttendanceList(Map<String, String> map) {

        return resAttendenceExtMapper.searchAttendanceList(map);
    }
    @Override
    public List<JsResAttendanceExt> searchAttendanceList2(Map<String, Object> map) {

        return resAttendenceExtMapper.searchAttendanceList2(map);
    }
    @Override
    public List<Map<String, String>> attendanceList2(Map<String, Object> map) {

        return resAttendenceExtMapper.attendanceList2(map);
    }
    @Override
    public List<Map<String, String>> attendanceList3(Map<String, Object> map) {

        return resAttendenceExtMapper.attendanceList3(map);
    }
    @Override
    public List<Map<String, String>> exportaAttendanceList2(Map<String, Object> map) {

        return resAttendenceExtMapper.exportaAttendanceList2(map);
    }
    @Override
    public List<Map<String, String>> exportaAttendanceList3(Map<String, Object> map) {

        return resAttendenceExtMapper.exportaAttendanceList3(map);
    }
    @Override
    public List<Map<String, String>> exportaAttendanceList4(Map<String, Object> map) {

        return resAttendenceExtMapper.exportaAttendanceList4(map);
    }
    @Override
    public List<Map<String, String>> exportResAttendanceList2(Map<String, Object> map) {

        return resAttendenceExtMapper.exportResAttendanceList2(map);
    }
    @Override
    public List<Map<String, String>> exportResAttendanceList3(Map<String, Object> map) {

        return resAttendenceExtMapper.exportResAttendanceList3(map);
    }

    @Override
    public List<JszyResAttendanceExt> searchJszyAttendanceList(Map<String, String> map) {
        return resAttendenceExtMapper.searchJszyAttendanceList(map);
    }

    @Override
    public List<ResAttendanceExt> searchResAttendanceList(Map<String, Object> map) {
        return resAttendenceExtMapper.searchResAttendanceList(map);
    }

    @Override
    public List<Map<String,String>> searchResAttendanceList2(Map<String, Object> map) {
        return resAttendenceExtMapper.searchResAttendanceList2(map);
    }

    @Override
    public void saveJsresAttendance(JsresAttendance jsresAttendance) {
        if(StringUtil.isNotBlank(jsresAttendance.getAttendanceFlow())){
            GeneralMethod.setRecordInfo(jsresAttendance, false);
            jsresAttendanceMapper.updateByPrimaryKeySelective(jsresAttendance);

        }else{
            jsresAttendance.setAttendanceFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(jsresAttendance, true);
            jsresAttendanceMapper.insertSelective(jsresAttendance);
        }
    }
    @Override
    public JsresAttendance getJsresAttendance(String nowDay, String userFlow) {
        JsresAttendanceExample example=new JsresAttendanceExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
        example.setOrderByClause("create_time desc");
        JsresAttendance bean=null;
        List<JsresAttendance> list=jsresAttendanceMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            bean=list.get(0);
        }
        return bean;
    }

    @Override
    public List<Map<String, String>> signinlist(Map<String, Object> map) {
        return resAttendenceExtMapper.signinlist(map);
    }


    @Override
    public List<JsresAttendanceDetail> searchJsresAttendanceDetailByAttendanceFlow(String jsResAttendanceFlow) {
        List<JsresAttendanceDetail> JsresAttendanceDetails =null;
        if(StringUtil.isNotBlank(jsResAttendanceFlow)){
            JsresAttendanceDetailExample example=new JsresAttendanceDetailExample();
            example.createCriteria().andAttendanceFlowEqualTo(jsResAttendanceFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            example.setOrderByClause("CREATE_TIME");
            JsresAttendanceDetails =jsresAttendanceDetailMapper.selectByExample(example);
        }
        return JsresAttendanceDetails;
    }

    @Override
    public List<JsresAttendanceDetail> searchJsresAttendanceDetailByAttendanceFlows(List<String> jsResAttendanceFlows) {
        List<JsresAttendanceDetail> JsresAttendanceDetails =null;
        if(jsResAttendanceFlows != null && jsResAttendanceFlows.size() > 0){
            JsresAttendanceDetailExample example=new JsresAttendanceDetailExample();
            example.createCriteria().andAttendanceFlowIn(jsResAttendanceFlows).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            example.setOrderByClause("CREATE_TIME");
            JsresAttendanceDetails =jsresAttendanceDetailMapper.selectByExample(example);
        }
        return JsresAttendanceDetails;
    }

    @Override
    public JsresAttendance searchJsresAttendanceByAttendanceFlow(String attendanceFlow) {
        JsresAttendance jsresAttendance =null;
        if(StringUtil.isNotBlank(attendanceFlow)){
            jsresAttendance =jsresAttendanceMapper.selectByPrimaryKey(attendanceFlow);
        }
        return jsresAttendance;
    }

    @Override
    public SysUser searchSysUserByUserFlow(String doctorFlow) {
        return sysUserMapper.selectByPrimaryKey(doctorFlow);
    }

    /****************************高******校******管******理******员******角******色************************************************/

    @Override
    public List<JsResAttendanceExt> searchAttendanceListForUni(Map<String, String> map) {
        if(map.get("workOrgId")==null&&map.get("workOrgName")==null){
            return null;
        }
        return resAttendenceExtMapper.searchAttendanceListForUni(map);
    }
}
