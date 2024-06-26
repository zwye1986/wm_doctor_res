package com.pinde.sci.webservice;

import com.pinde.core.util.*;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.MD5Util;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.ZseyHrKqMonthMapper;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: machengshuang
 * Date: 17-10-18
 * Version: 1.0
 */
@WebService
@Component
public class ZseyDeptEmpService {
    private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);

    private static final String ZSEY_ORG_FLOW = "9f9a5bc7111a4670bda79f25c7c98efd";
    private static final String ZSEY_WEBSERVICE_KEY = "9F9A5BC7111A4677BDA79F25C7C98EFD";

    /**
     * 接收科室数据
     *
     * @param deptData
     * @return
     */
    public String synDeptInfo(String deptData) {
        try {
            logger.debug("deptData======="+deptData);
            if (StringUtil.isBlank(deptData)) {
                logger.debug("保存失败，数据为空！");
                return _getResMsg("000500", "保存失败，数据为空！", null);
            }

            Document doc = DocumentHelper.parseText(deptData);
            Element root = doc.getRootElement();
            Element extInfoFormEle = root.element("ACCESSTOKEN");
            String accessToken = extInfoFormEle.getText();
            logger.debug("accessToken="+accessToken);
            //验证token
            if (StringUtil.isBlank(accessToken) ) {
                logger.debug("保存失败，令牌为空！");
                return _getResMsg("000500", "保存失败，令牌为空！", null);
            }
//            String _token = MD5Util.MD5Encode(deptData + ZSEY_WEBSERVICE_KEY);
//            if (StringUtil.isBlank(accessToken) || ! accessToken.equals(_token)) {
//                logger.debug("保存失败，令牌不正确！");
//                return _getResMsg("000500", "保存失败，令牌不正确！", null);
//            }
            if (StringUtil.isBlank(accessToken) || ! accessToken.equals(ZSEY_WEBSERVICE_KEY)) {
                logger.debug("保存失败，令牌不正确！");
                return _getResMsg("000500", "保存失败，令牌不正确！", null);
            }

            Element requestData = root.element("REQUESTDATA");
            List<Element> dataList = requestData.elements();
            String deptID = null;
            String deptName = null;
            String pareID = null;
            String deptCode = null;
            for(Element e : dataList){
                if(e.getName().equals("DEPARTMENTID")){
                    deptID = e.getText();
                }else if(e.getName().equals("DEPARTMENTNAME")){
                    deptName = e.getText();
                }else if(e.getName().equals("PAREID")){
                    pareID = e.getText();
                }else if(e.getName().equals("DEPARTMENTNO")){
                    deptCode = e.getText();
                }
            }
            logger.debug("deptID="+deptID);
            logger.debug("deptName="+deptName);
            logger.debug("pareID="+pareID);
            logger.debug("deptCode="+deptCode);

            if (StringUtil.isBlank(deptID)) {
                logger.debug("保存失败，科室ID为空！");
                return _getResMsg("000500", "保存失败，科室ID为空！", null);
            } else if (StringUtil.isBlank(deptName)) {
                logger.debug("保存失败，科室名称为空！");
                return _getResMsg("000500", "保存失败，科室名称为空！", null);
            } else if (StringUtil.isBlank(deptCode)) {
                logger.debug("保存失败，科室编码为空！");
                return _getResMsg("000500", "保存失败，科室编码为空！", null);
            }

            int count;
//            if(! "1".equals(deptID) && StringUtil.isNotBlank(pareID) && ! "1".equals(pareID)) {//同步二级科室为轮转科室
//                SchDept sysDept = new SchDept();
//                SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
//                SysDeptExample sysDeptExample = new SysDeptExample();
//                sysDeptExample.createCriteria().andDeptIdEqualTo(pareID);
//                List<SysDept> deptList = deptMapper.selectByExample(sysDeptExample);
//                if(deptList!=null&&deptList.size()>0)
//                {
//                    sysDept.setDeptFlow(deptList.get(0).getDeptFlow());
//                    sysDept.setDeptName(deptList.get(0).getDeptName());
//                }else{
//                    sysDept.setDeptFlow(pareID);
//                }
//                sysDept.setDeptId(deptID);
//                sysDept.setDeptPid(pareID);
//                sysDept.setSchDeptName(deptName);
//                sysDept.setOrdinal(999);
//                sysDept.setOrgFlow(ZSEY_ORG_FLOW);
//
//                SchDeptMapper schDeptMapper = SpringUtil.getBean(SchDeptMapper.class);
//                SchDeptExample schDeptExample = new SchDeptExample();
//                schDeptExample.createCriteria().andDeptIdEqualTo(deptID);
//                List<SchDept> schDeptList = schDeptMapper.selectByExample(schDeptExample);
//                if (schDeptList == null || schDeptList.size() == 0) {
//                    sysDept.setSchDeptFlow(deptID);
//                    sysDept.setRecordStatus("N");
//                    sysDept.setCreateTime(DateUtil.getCurrDateTime());
//                    sysDept.setCreateUserFlow("zsey.webservice");
//                    sysDept.setModifyTime(DateUtil.getCurrDateTime());
//                    sysDept.setModifyUserFlow("zsey.webservice");
//                    count = schDeptMapper.insert(sysDept);
//                } else {
//                    String deptFlow = schDeptList.get(0).getSchDeptFlow();
//                    sysDept.setSchDeptFlow(deptFlow);
//                    sysDept.setModifyTime(DateUtil.getCurrDateTime());
//                    sysDept.setModifyUserFlow("zsey.webservice");
//                    count = schDeptMapper.updateByPrimaryKeySelective(sysDept);
//                }
//            }else
            {//同步一级科室为医院科室
                SysDept sysDept = new SysDept();
                sysDept.setDeptId(deptID);
                sysDept.setDeptName(deptName);
                sysDept.setDeptPid(pareID);
                sysDept.setDeptCode(deptCode);
                sysDept.setOrdinal(999);
                sysDept.setOrgFlow(ZSEY_ORG_FLOW);

                SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
                SysDeptExample sysDeptExample = new SysDeptExample();
                sysDeptExample.createCriteria().andDeptIdEqualTo(deptID);
                List<SysDept> deptList = deptMapper.selectByExample(sysDeptExample);
                if (deptList == null || deptList.size() == 0) {
                    sysDept.setDeptFlow(deptID);
                    sysDept.setRecordStatus("N");
                    sysDept.setCreateTime(DateUtil.getCurrDateTime());
                    sysDept.setCreateUserFlow("zsey.webservice");
                    sysDept.setModifyTime(DateUtil.getCurrDateTime());
                    sysDept.setModifyUserFlow("zsey.webservice");
                    count = deptMapper.insert(sysDept);
                } else {
                    String deptFlow = deptList.get(0).getDeptFlow();
                    sysDept.setDeptFlow(deptFlow);
                    sysDept.setModifyTime(DateUtil.getCurrDateTime());
                    sysDept.setModifyUserFlow("zsey.webservice");
                    count = deptMapper.updateByPrimaryKeySelective(sysDept);
                }
            }


            logger.debug("保存成功！"+count);
            return _getResMsg("000000", "保存成功", count+"");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000500", "保存失败，出现异常！"+e.getMessage(), null);
        }
    }

    /**
     * 组织返回数据
     * @param returnCode 000000	成功	          平台服务异常编码
                            000400	错误请求	      平台服务异常编码
                            000401	未授权	          平台服务异常编码
                            000404	回调的URL找不到	  平台服务异常编码
                            000500	内部错误	      平台服务异常编码
                            000503	无法获得服务	  平台服务异常编码
                            000600	远程系统错误	  推送响应状态编码(第三方系统接口返回)
                            000601	推送数据有误	  推送响应状态编码(第三方系统接口返回)
     * @param ReturnMSG
     * @param qty
     * @return
     */
    private String _getResMsg(String returnCode, String ReturnMSG, String qty){
        StringBuffer resXml = new StringBuffer();
        resXml.append("<PUSHRESPONSE>" +
                "      <RESPONSEDATA>" +
                "        <RETURNCODE>"+returnCode+"</RETURNCODE>" +
                "        <RETURNMSG>"+ReturnMSG+"</RETURNMSG>");
        if(qty != null) {
            resXml.append("<RESPONSE>" + qty + "</RESPONSE>");
        }
        resXml.append(" </RESPONSEDATA>" +
                  "  </PUSHRESPONSE>");
        return resXml.toString();
    }

    /**
     * 接收人员数据
     *
     * @param empdata
     * @return
     */
    public String synEmpInfo(String empdata) {
        try {
            logger.debug("empdata======="+empdata);
            if (StringUtil.isBlank(empdata)) {
                logger.debug("保存失败，人员数据为空！");
                return _getResMsg("000500", "保存失败，人员数据为空！", null);
            }

            Document doc = DocumentHelper.parseText(empdata);
            Element root = doc.getRootElement();
            Element extInfoFormEle = root.element("ACCESSTOKEN");
            String accessToken = extInfoFormEle.getText();
            logger.debug("accessToken="+accessToken);
            //验证token
            if (StringUtil.isBlank(accessToken) ) {
                logger.debug("保存失败，令牌为空！");
                return _getResMsg("000500", "保存失败，令牌为空！", null);
            }
            if (StringUtil.isBlank(accessToken) || ! accessToken.equals(ZSEY_WEBSERVICE_KEY)) {
                logger.debug("保存失败，令牌不正确！");
                return _getResMsg("000500", "保存失败，令牌不正确！", null);
            }

            Element requestData = root.element("REQUESTDATA");
            List<Element> dataList = requestData.elements();
            String userId = null; //员工编码(医院工号)
            String id_no = null;
            String userCode = null;
            String userName = null;
            String userBirthday = null;
            String educationId = null;
            String employeeAssortFlag = null;
            String employeeId = null;
            String deptId = null;
            String deptName = null;
            String deptCode = null;
            String onDutyFlag = null;
            String sexId = null;
            String newOrUpdate = null;
            for(Element e : dataList){
                if(e.getName().equals("HIP_STAFF_CODE")){
                    userId = e.getText();
                }else if(e.getName().equals("IDENTITYCARD")){
                    id_no = e.getText();
                }else if(e.getName().equals("LOGINNAME")){
                    userCode = e.getText();
                }else if(e.getName().equals("EMPLOYEENAME")){
                    userName = e.getText();
                }else if(e.getName().equals("BIRTHDAY")){
                    userBirthday = e.getText();
                }else if(e.getName().equals("EDUCATEDFLAG")){ //学历  0-未确定,1-小学,2-初中,3-职高,4-高中,5-中技,6-中专,7-专科,8-大学本科,9-研究生,10-硕士研究生,11-博士研究生
                    educationId = e.getText();
                }else if(e.getName().equals("EMPLOYEEASSORTFLAG")){ //员工类别标志: 1-管理科室,2-临时工,3-药库,4-药房,5-医生,6-护士,7-制剂室
                    employeeAssortFlag = e.getText();
                }else if(e.getName().equals("EMPLOYEEID")){
                    employeeId = e.getText();
                }else if(e.getName().equals("DEPARTMENTID")){
                    deptId = e.getText();
                }else if(e.getName().equals("DEPARTMENTNAME")){
                    deptName = e.getText();
                }else if(e.getName().equals("DEPARTMENTNO")){
                    deptCode = e.getText();
                }else if(e.getName().equals("ONDUTYFLAG")){ //在职标志：0-在职,1-离休,2-退休,3-离院
                    onDutyFlag = e.getText();
                }else if(e.getName().equals("SEXFLAG")){ // 性别：0-未知,1-男,2-女
                    sexId = e.getText();
                }else if(e.getName().equals("NEWORUPDATE")){ //新增或更新 1.新增，2更新，3删除
                    newOrUpdate = e.getText();
                }
            }

            logger.debug("userId="+userId);
            logger.debug("id_no="+id_no);
            logger.debug("userCode="+userCode);
            logger.debug("userName="+userName);
            logger.debug("userBirthday="+userBirthday);
            logger.debug("educationId="+educationId);
            logger.debug("employeeAssortFlag="+employeeAssortFlag);
            logger.debug("employeeId="+employeeId);
            logger.debug("deptId="+deptId);
            logger.debug("deptName="+deptName);
            logger.debug("onDutyFlag="+onDutyFlag);
            logger.debug("sexId="+sexId);
            logger.debug("newOrUpdate="+newOrUpdate);

            if (StringUtil.isBlank(userId)) {
                logger.debug("保存失败，员工编码(医院工号)为空！");
                return _getResMsg("000500", "保存失败，员工编码(医院工号)为空！", null);
            } else if (StringUtil.isBlank(employeeId)) {
                logger.debug("保存失败，员工ID为空！");
                return _getResMsg("000500", "保存失败，员工ID为空！", null);
            } else if (StringUtil.isBlank(id_no)) {
                logger.debug("保存失败，身份证为空！");
                return _getResMsg("000500", "保存失败，身份证为空！", null);
            } else if (StringUtil.isBlank(userCode)) {
                logger.debug("保存失败，登录名(loginName)为空！");
                return _getResMsg("000500", "保存失败，登录名(loginName)为空！", null);
            } else if (StringUtil.isBlank(userName)) {
                logger.debug("保存失败，员工名称为空！");
                return _getResMsg("000500", "保存失败，员工名称为空！", null);
            } else if (StringUtil.isBlank(deptId)) {
                logger.debug("保存失败，所属科室ID为空！");
                return _getResMsg("000500", "保存失败，所属科室ID为空！", null);
            } else if (StringUtil.isBlank(deptName)) {
                logger.debug("保存失败，所属科室名称为空！");
                return _getResMsg("000500", "保存失败，所属科室名称为空！", null);
            } else if (StringUtil.isBlank(newOrUpdate)) {
                logger.debug("保存失败，新增或更新标识为空！");
                return _getResMsg("000500", "保存失败，新增或更新标识为空！", null);
            }

            SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
            SysDeptExample deptExample = new SysDeptExample();
            deptExample.createCriteria().andDeptIdEqualTo(deptId);
            deptExample.setOrderByClause("record_status desc");
            List<SysDept> deptList = deptMapper.selectByExample(deptExample);
            if(deptList == null || deptList.size() < 1){
                logger.debug("保存失败，根据所属科室ID找不到科室数据！");
                return _getResMsg("000500", "保存失败，根据所属科室ID找不到科室数据！", null);
            }
            String deptFlow = deptList.get(0).getDeptFlow();

            //如果非一级科室，则找到一级科室  1:中山二院(pid为空)  pid=1的为一级科室
            if(! "1".equals(deptId) && StringUtil.isNotBlank(deptList.get(0).getDeptPid()) && ! "1".equals(deptList.get(0).getDeptPid())){
                deptExample = new SysDeptExample();
                deptExample.createCriteria().andDeptPidEqualTo(deptId);
                deptExample.setOrderByClause("record_status desc");
                deptList = deptMapper.selectByExample(deptExample);
                if(deptList != null && deptList.size() > 0){
                    deptFlow = deptList.get(0).getDeptFlow();
                }
            }
            SysUserMapper sysUserMapper = SpringUtil.getBean(SysUserMapper.class);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(employeeId);
            List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);

            SysUser sysUser = new SysUser();
            //删除人员
//            if("3".equals(newOrUpdate)){
//                if (sysUserList == null || sysUserList.size() == 0) {
//                    logger.debug("删除失败，根据人员employeeId找不到人员信息！");
//                    return _getResMsg("000500", "删除失败，根据人员employeeId找不到人员信息！", null);
//                }else{
//                    sysUser.setUserFlow(sysUserList.get(0).getUserFlow());
//                    sysUser.setRecordStatus("N");
//                    sysUserMapper.updateByPrimaryKey(sysUser);
//                }
//            }

            //验证用户名是否重复
            sysUserExample = new SysUserExample();
            SysUserExample.Criteria criteria = sysUserExample.createCriteria();
            if (sysUserList == null || sysUserList.size() == 0) {
                criteria.andUserCodeEqualTo(userCode);
            }else{
                criteria.andUserCodeEqualTo(userCode).andUserIdNotEqualTo(employeeId);
                sysUserExample.or(sysUserExample.createCriteria().andUserCodeEqualTo(userCode).andUserIdIsNull());
            }
            List<SysUser> sysUserByCodeList = sysUserMapper.selectByExample(sysUserExample);
            if(sysUserByCodeList != null && sysUserByCodeList.size() > 0){
                logger.debug("保存失败，用户名"+userCode+"重复！");
                return _getResMsg("000500", "保存失败，用户名"+userCode+"重复！", null);
            }

            sysUser.setUserId(employeeId);
            sysUser.setUserCode(userCode);
            sysUser.setUserName(userName);
            sysUser.setIdNo(id_no);
            sysUser.setDeptFlow(deptFlow);
            sysUser.setDeptName(deptName);
            sysUser.setOrgFlow(ZSEY_ORG_FLOW);
            if("1".equals(sexId)){  //性别：0-未知,1-男,2-女
                sysUser.setSexId("Man");
                sysUser.setSexName("男");
            }else if("2".equals(sexId)){
                sysUser.setSexId("Woman");
                sysUser.setSexName("女");
            }
            //学历  0-未确定,1-小学,2-初中,3-职高,4-高中,5-中技,6-中专,7-专科,8-大学本科,9-研究生,10-硕士研究生,11-博士研究生
            // educationId
            int count;
            if (sysUserList == null || sysUserList.size() == 0) {
                sysUser.setUserFlow(employeeId);
                sysUser.setUserPasswd(PasswordHelper.encryptPassword(employeeId, "123456"));
                sysUser.setRecordStatus("Y");
                sysUser.setCreateTime(DateUtil.getCurrDateTime());
                sysUser.setCreateUserFlow("zsey.webservice");
                sysUser.setModifyTime(DateUtil.getCurrDateTime());
                sysUser.setModifyUserFlow("zsey.webservice");
                count = sysUserMapper.insert(sysUser);
            } else {
                String userFlow = sysUserList.get(0).getUserFlow();
                sysUser.setUserFlow(userFlow);
                sysUser.setModifyTime(DateUtil.getCurrDateTime());
                sysUser.setModifyUserFlow("zsey.webservice");
                count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            }

            logger.debug("保存成功！"+count);
            return _getResMsg("000000", "保存成功", count+"");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000500", "保存失败，出现异常！"+e.getMessage(), null);
        }
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><RESULT><ACCESSTOKEN>9F9A5BC7111A4688DEA79F25C7C98EFD</ACCESSTOKEN><CODE>2000</CODE><MSG>传输成功</MSG><REQUESTDATA><RECORD><USERCODE>00982</USERCODE><USERNAME>刘颖琳</USERNAME><DEPTNAME>妇产科</DEPTNAME><INDATE>1989-06-29 00:00:00.0</INDATE><INDATE>1989-06-29 00:00:00.0</INDATE><IDNO>00982</IDNO><M01>00</M01><M02>00</M02><M03>00</M03><M04>00</M04><M05>00</M05><M06>00</M06><M07>00</M07><M08>00</M08><M09>00</M09><M10>00</M10><M11>00</M11><M12>00</M12><M13>00</M13><M14>00</M14><M15>00</M15><M16>00</M16><M17>00</M17><M18>00</M18><M19>00</M19><M20>00</M20><M21>00</M21><M22>00</M22><M23>00</M23><M24>00</M24><M25>00</M25><M26>00</M26><M27>00</M27><M28>00</M28><M29>00</M29><M30>00</M30><M31></M31><KQDATE>2017-09</KQDATE><KQDEPTNAME>妇产科围产专科</KQDEPTNAME><KQUSERCODE>83160</KQUSERCODE><KQUSERNAME>邓科文</KQUSERNAME></RECORD><RECORD><USERCODE>01015</USERCODE><USERNAME>方建培</USERNAME><DEPTNAME>儿科</DEPTNAME><INDATE>1989-07-01 00:00:00.0</INDATE><INDATE>1989-07-01 00:00:00.0</INDATE><IDNO>01015</IDNO><M01>00</M01><M02>00</M02><M03>00</M03><M04>00</M04><M05>00</M05><M06>00</M06><M07>00</M07><M08>00</M08><M09>00</M09><M10>00</M10><M11>00</M11><M12>00</M12><M13>00</M13><M14>00</M14><M15>15</M15><M16>15</M16><M17>15</M17><M18>15</M18><M19>15</M19><M20>15</M20><M21>15</M21><M22>00</M22><M23>00</M23><M24>00</M24><M25>00</M25><M26>00</M26><M27>00</M27><M28>00</M28><M29>00</M29><M30>00</M30><M31></M31><KQDATE>2017-09</KQDATE><KQDEPTNAME>儿科一区医生</KQDEPTNAME><KQUSERCODE>83524</KQUSERCODE><KQUSERNAME>阙丽萍</KQUSERNAME></RECORD></REQUESTDATA></RESULT>";
        new ZseyDeptEmpService().synHRR(xml);

    }

    /**
     * 接收考勤数据
     *
     * @param hrData
     * @return
     */
    public String synHRR(String hrData) {
        try {
            logger.debug("hrData======="+hrData);
            if (StringUtil.isBlank(hrData)) {
                logger.debug("保存失败，数据为空！");
                return _getResMsg("000500", "保存失败，数据为空！", null);
            }

            Document doc = DocumentHelper.parseText(hrData);
            Element root = doc.getRootElement();
            Element extInfoFormEle = root.element("ACCESSTOKEN");
            String accessToken = extInfoFormEle.getText();
            logger.debug("accessToken="+accessToken);
            //验证token
            if (StringUtil.isBlank(accessToken) ) {
                logger.debug("保存失败，令牌为空！");
                return _getResMsg("000500", "保存失败，令牌为空！", null);
            }
            if ( ! accessToken.equals("9F9A5BC7111A4688DEA79F25C7C98EFD")) {
                logger.debug("保存失败，令牌不正确！");
                return _getResMsg("000500", "保存失败，令牌不正确！", null);
            }

            List<ZseyHrKqMonthXml> zhkmList = new ArrayList<>();
            Element requestData = root.element("REQUESTDATA");
            List<Element> dataList = requestData.elements("RECORD");  //RECORD LIST
            if(null != dataList && !dataList.isEmpty()) {
                for (Element ele : dataList) {
                    ZseyHrKqMonthXml  zhkm = new ZseyHrKqMonthXml();
                    List<Element> elsList  = ele.elements();
                    if(null != elsList && !elsList.isEmpty()){
                        for(Element attr : elsList){
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            setValue(zhkm,attrName,attrValue);
                        }
                        zhkmList.add(zhkm);
                    }
                }
            }
            int count = 0;
            for(ZseyHrKqMonthXml zhkm : zhkmList){
                if (StringUtil.isBlank(zhkm.getUsercode())) {
                    logger.debug("保存失败，工号为空！");
                    return _getResMsg("000500", "保存失败，工号为空！", null);
                } else if (StringUtil.isBlank(zhkm.getUsername())) {
                    logger.debug("保存失败，姓名为空！");
                    return _getResMsg("000501", "保存失败，姓名为空！", null);
                }
                SysUserMapper userMapper = SpringUtil.getBean(SysUserMapper.class);
                ZseyHrKqMonthMapper monthMapper = SpringUtil.getBean(ZseyHrKqMonthMapper.class);
                SysUserExample example = new SysUserExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andUserCodeEqualTo(zhkm.getUsercode()).andUserNameEqualTo(zhkm.getUsername());
                List<SysUser> userList =userMapper.selectByExample(example);
                if(null != userList && !userList.isEmpty()){
                    ZseyHrKqMonthExample monthExample = new ZseyHrKqMonthExample();
                    monthExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                            .andUserCodeEqualTo(zhkm.getUsercode()).andKqDateEqualTo(zhkm.getKqdate());
                    List<ZseyHrKqMonth> montList = monthMapper.selectByExample(monthExample);
                    ZseyHrKqMonth month = new ZseyHrKqMonth();
                    month.setResType("1");
                    if(null != montList && !montList.isEmpty()){
                        month.setMonthFlow(montList.get(0).getMonthFlow());
                        month.setUserFlow(userList.get(0).getUserFlow());
                        copyValue(month,zhkm);
                        month.setModifyTime(DateUtil.getCurrentTime());
                        month.setModifyUserFlow("zsey.hr.ifs");
                        count += monthMapper.updateByPrimaryKeySelective(month);
                    }else{
                        month.setMonthFlow(PkUtil.getUUID());
                        month.setUserFlow(userList.get(0).getUserFlow());
                        copyValue(month,zhkm);
                        month.setCreateTime(DateUtil.getCurrentTime());
                        month.setCreateUserFlow("zsey.hr.ifs");
                        count += monthMapper.insertSelective(month);
                    }
                }else{
                    logger.debug("保存失败，工号"+zhkm.getUsercode()+"姓名"+zhkm.getUsername()+"不存在！");
                    return _getResMsg("000100", "保存失败，工号"+zhkm.getUsercode()+"姓名"+zhkm.getUsername()+"不存在！", null);
                }
            }
            logger.debug("保存成功！"+count);
            return _getResMsg("000000", "保存成功", count+"");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return _getResMsg("000500", "保存失败，出现异常！"+e.getMessage(), null);
        }
    }
    private ZseyHrKqMonth copyValue(ZseyHrKqMonth month,ZseyHrKqMonthXml zhkm){
        month.setUserCode(zhkm.getUsercode());
        month.setUserName(zhkm.getUsername());
        month.setDeptName(zhkm.getDeptname());
        month.setInDate(zhkm.getIndate());
        month.setIdNo(zhkm.getIdno());
        month.setM01(zhkm.getM01());
        month.setM02(zhkm.getM02());
        month.setM03(zhkm.getM03());
        month.setM04(zhkm.getM04());
        month.setM05(zhkm.getM05());
        month.setM06(zhkm.getM06());
        month.setM07(zhkm.getM07());
        month.setM08(zhkm.getM08());
        month.setM09(zhkm.getM09());
        month.setM10(zhkm.getM10());
        month.setM11(zhkm.getM11());
        month.setM12(zhkm.getM12());
        month.setM13(zhkm.getM13());
        month.setM14(zhkm.getM14());
        month.setM15(zhkm.getM15());
        month.setM16(zhkm.getM16());
        month.setM17(zhkm.getM17());
        month.setM18(zhkm.getM18());
        month.setM19(zhkm.getM19());
        month.setM20(zhkm.getM20());
        month.setM21(zhkm.getM21());
        month.setM22(zhkm.getM22());
        month.setM23(zhkm.getM23());
        month.setM24(zhkm.getM24());
        month.setM25(zhkm.getM25());
        month.setM26(zhkm.getM26());
        month.setM27(zhkm.getM27());
        month.setM28(zhkm.getM28());
        month.setM29(zhkm.getM29());
        month.setM30(zhkm.getM30());
        month.setM31(zhkm.getM31());
        month.setKqDate(zhkm.getKqdate());
        month.setKqDeptName(zhkm.getKqdeptname());
        month.setKqUserCode(zhkm.getKqusercode());
        month.setKqUserName(zhkm.getKqusername());
        return month;
    }
    private void setValue(Object obj,String attrName,String attrValue) {
        try {
            Class<?> objClass = obj.getClass();
            String methedName = "set" + attrName.substring(0, 1) + attrName.substring(1).toLowerCase();
            Method setMethod = objClass.getMethod(methedName, new Class[]{String.class});
            setMethod.invoke(obj, new Object[]{attrValue});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收科室数据
     *
     * @param deptData
     * @return
     */
    private String _synDeptInfo(String deptData, String token) {
        try {

            if (StringUtil.isBlank(deptData)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1001</code><msg>ERROR：数据为空</msg></result>";
            }else if (StringUtil.isBlank(token) ) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1002</code><msg>ERROR：令牌为空</msg></result>";
            }

            String _token = MD5Util.MD5Encode(deptData + ZSEY_WEBSERVICE_KEY);

            if (StringUtil.isBlank(token) || ! token.equals(_token)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1003</code><msg>ERROR：令牌不正确</msg></result>";
            }

            Map<String, String> map = XmlUtil.parseXmlStr(deptData);
            String deptID = map.get("DEPARTMENTID");
            String deptName = map.get("DEPARTMENTNAME");
            String pareID = map.get("PAREID");
            String deptCode = map.get("DEPARTMENTNO");
//            String spellCode = map.get("SPELLCODE");

            if (StringUtil.isBlank(deptID)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1004</code><msg>ERROR：科室ID为空</msg></result>";
            } else if (StringUtil.isBlank(deptName)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1005</code><msg>ERROR：科室名称为空</msg></result>";
            } else if (StringUtil.isBlank(deptCode)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1006</code><msg>ERROR：科室编码为空</msg></result>";
            }

            SysDept sysDept = new SysDept();
            sysDept.setDeptId(deptID);
            sysDept.setDeptName(deptName);
            sysDept.setDeptPid(pareID);
            sysDept.setDeptCode(deptCode);
//            sysDept.setDeptPinyin(spellCode);
            sysDept.setRecordStatus("Y");
            sysDept.setOrdinal(999);
            sysDept.setOrgFlow(ZSEY_ORG_FLOW);

            SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
            SysDeptExample sysDeptExample = new SysDeptExample();
            sysDeptExample.createCriteria().andDeptIdEqualTo(deptID);
            int count;
            List<SysDept> deptList = deptMapper.selectByExample(sysDeptExample);
            if (deptList == null || deptList.size() == 0) {
                sysDept.setDeptFlow(deptID);
                sysDept.setCreateTime(DateUtil.getCurrDateTime());
                sysDept.setCreateUserFlow("zsey.webservice");
                sysDept.setModifyTime(DateUtil.getCurrDateTime());
                sysDept.setModifyUserFlow("zsey.webservice");
                count = deptMapper.insert(sysDept);
            } else {
                String deptFlow = deptList.get(0).getDeptFlow();
                sysDept.setDeptFlow(deptFlow);
                sysDept.setModifyTime(DateUtil.getCurrDateTime());
                sysDept.setModifyUserFlow("zsey.webservice");
                count = deptMapper.updateByPrimaryKeySelective(sysDept);
            }

            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>2000</code><msg>成功保存" + count + "条数据</msg></result>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1999</code><msg>ERROR：保存失败，出现异常！</msg></result>";
        }
    }

    private String _synEmpInfo(String empdata, String token) {
        if (StringUtil.isBlank(empdata)) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1001</code><msg>ERROR：数据为空</msg></result>";
        }else if (StringUtil.isBlank(token) ) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1002</code><msg>ERROR：令牌为空</msg></result>";
        }

        if (StringUtil.isBlank(token) || !token.equals(MD5Util.MD5Encode(empdata + ZSEY_WEBSERVICE_KEY))) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1003</code><msg>ERROR：令牌不正确</msg></result>";
        }

        logger.debug(empdata);

        try {
            Map<String, String> map = XmlUtil.parseXmlStr(empdata);
            String userId = map.get("USEREID");
            String userCode = map.get("USERCODE");
            String userName = map.get("USERNAME");
            String orgId = map.get("ORGID");

            if (StringUtil.isBlank(userId)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1004</code><msg>ERROR：用户ID为空</msg></result>";
            } else if (StringUtil.isBlank(userName)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1005</code><msg>ERROR：用户名称为空</msg></result>";
            } else if (StringUtil.isBlank(userCode)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1006</code><msg>ERROR：用户编码为空</msg></result>";
            } else if (StringUtil.isBlank(orgId)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1007</code><msg>ERROR：科室ID为空</msg></result>";
            }

            SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
            SysDeptExample deptExample = new SysDeptExample();
            deptExample.createCriteria().andDeptIdEqualTo(orgId).andRecordStatusEqualTo("Y");
            List<SysDept> deptList = deptMapper.selectByExample(deptExample);
            if(deptList == null || deptList.size() < 1){
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1007</code><msg>ERROR：人员所属科室ID为空</msg></result>";
            }

            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysUser.setUserCode(userCode);
            sysUser.setUserName(userName);
            sysUser.setDeptFlow("");
            sysUser.setDeptName("");
            sysUser.setRecordStatus("Y");
            sysUser.setOrgFlow(ZSEY_ORG_FLOW);

            SysUserMapper sysUserMapper = SpringUtil.getBean(SysUserMapper.class);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(userId);
            int count;
            List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
            if (sysUserList == null || sysUserList.size() == 0) {
                sysUser.setUserFlow(userId);
                sysUser.setCreateTime(DateUtil.getCurrDateTime());
                sysUser.setCreateUserFlow("zsey.webservice");
                sysUser.setModifyTime(DateUtil.getCurrDateTime());
                sysUser.setModifyUserFlow("zsey.webservice");
                count = sysUserMapper.insert(sysUser);
            } else {
                String userFlow = sysUserList.get(0).getUserFlow();
                sysUser.setUserFlow(userFlow);
                sysUser.setModifyTime(DateUtil.getCurrDateTime());
                sysUser.setModifyUserFlow("zsey.webservice");
                count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            }

            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>2000</code><msg>成功保存" + count + "条数据</msg></result>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><code>1999</code><msg>ERROR：保存失败，出现异常！</msg></result>";
        }
    }
}