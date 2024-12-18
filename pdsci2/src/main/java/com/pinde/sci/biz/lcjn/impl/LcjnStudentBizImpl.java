package com.pinde.sci.biz.lcjn.impl;


import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserExample;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnStudentBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.dao.lcjn.LcjnBaseManagerExtMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnStudentBizImpl implements ILcjnStudentBiz {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private LcjnBaseManagerExtMapper baseManagerExtMapper;


    @Override
    public List<SysUser> searchStudent(Map<String,String> paramMap) {
        return baseManagerExtMapper.getStudents(paramMap);
    }

    @Override
    public int editStudent(SysUser user) {
        String userCode = user.getUserCode();
        String userFlow = user.getUserFlow();
        if(StringUtil.isNotBlank(userCode)){
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserCodeEqualTo(user.getUserCode());
            if(StringUtil.isNotBlank(userFlow)){
                criteria.andUserFlowNotEqualTo(userFlow);
            }
            int result =  userMapper.countByExample(example);
            if(0!=result){
                return -1;
            }
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(user.getIsOwnerStu())) {
            user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if(StringUtil.isNotBlank(userFlow)){
            GeneralMethod.setRecordInfo(user,false);
            return userMapper.updateByPrimaryKeySelective(user);
        }else{
            user.setUserFlow(PkUtil.getUUID());
            user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
            user.setStatusId(UserStatusEnum.Activated.getId());
            GeneralMethod.setRecordInfo(user,true);
            //插入学员角色
            SysUserRole userRole = new SysUserRole();
            userRole.setRecordFlow(PkUtil.getUUID());
            userRole.setWsId("lcjn");
            userRole.setUserFlow(user.getUserFlow());
            userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            userRole.setAuthTime(DateUtil.getCurrDateTime());
            userRole.setAuthUserFlow(user.getUserFlow());
            userRole.setCreateTime(DateUtil.getCurrDateTime());
            userRole.setCreateUserFlow(user.getUserFlow());
            userRole.setModifyTime(DateUtil.getCurrDateTime());
            userRole.setModifyUserFlow(user.getUserFlow());
            SysCfg cfg = cfgBiz.read("lcjn_doctor_role_flow");
            String doctorRole ="";
            if (null != cfg) {
                doctorRole = cfg.getCfgValue();
                if(!StringUtil.isNotBlank(doctorRole)){
                    doctorRole = cfg.getCfgBigValue();
                }
            }
            if(StringUtil.isNotBlank(doctorRole)){
                userRole.setRoleFlow(doctorRole);
            }else{
                return -2;
            }
            userRoleMapper.insertSelective(userRole);
            //插入完毕
            return userMapper.insertSelective(user);
        }
    }

    @Override
    public int importStudentExcel(MultipartFile file){
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    private Workbook createCommonWorkbook(InputStream inS) throws Exception {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
        //        // EXCEL2003使用的是微软的文件系统
//        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//            return new HSSFWorkbook(inS);
//        }
//        // EXCEL2007使用的是OOM文件格式
//        if (POIXMLDocument.hasOOXMLHeader(inS)) {
//            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
//            return new XSSFWorkbook(OPCPackage.open(inS));
//        }
        try{
            return WorkbookFactory.create(inS);
        }catch (Exception e) {
            throw new IOException("不能解析的excel版本");
        }
//        throw new Exception("不能解析的excel版本");
    }
    private int parseExcel(Workbook wb) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num==1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                SysUser user = new SysUser();
                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType().getCode() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("*用户名".equals(currTitle)){
                        user.setUserCode(value);
                    }
                    if("*姓名".equals(currTitle)){
                        user.setUserName(value);
                    }
                    if("*性别".equals(currTitle)){
                        if("男".equals(value)){
                            user.setSexId("Man");
                        }else if("女".equals(value)){
                            user.setSexId("Woman");
                        }
                        user.setSexName(value);
                    }
                    if("*身份证号".equals(currTitle)){
                        user.setIdNo(value);
                    }
                    if("*手机号码".equals(currTitle)){
                        user.setUserPhone(value);
                    }
                    if("*邮箱".equals(currTitle)){
                        user.setUserEmail(value);
                    }
                    if("*是否本院".equals(currTitle)){
                        if("是".equals(value)){
                            user.setIsOwnerStu(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }else if("否".equals(value)){
                            user.setIsOwnerStu(com.pinde.core.common.GlobalConstant.FLAG_N);
                        }
                    }
                    if("所在单位".equals(currTitle)){
                        user.setOrgName(value);
                    }
                    if("科室".equals(currTitle)){
                        user.setOrgName(value);
                    }
                    if("职称".equals(currTitle)){
                        user.setTitleId(InitConfig.getDictNameMap("UserTitle").get(value));
                        user.setTitleName(value);
                    }
                    if("培训专业".equals(currTitle)){
                        user.setLcjnSpeId(InitConfig.getDictNameMap("LcjnSpe").get(value));
                        user.setLcjnSpeName(value);
                    }
                }
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(user.getIsOwnerStu())) {
                    user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                }
                String regEx = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$";
                if(StringUtil.isBlank(user.getUserCode())){
                    throw new Exception("导入失败！第"+ (count+2) +"行用户名不能为空！");
                }else{
                    if(!user.getUserCode().matches(regEx)){
                        throw new Exception("导入失败！第"+ (count+2) +"行用户名必须为手机号码！");
                    }
                }
                if(StringUtil.isBlank(user.getUserName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                }
                if(StringUtil.isBlank(user.getSexName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                }
                if(StringUtil.isBlank(user.getIdNo())){
                    throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                }
                String isIDCard1="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
                String isIDCard2="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
                if(!(user.getIdNo().matches(isIDCard1)||user.getIdNo().matches(isIDCard2))){
                    throw new Exception("导入失败！第"+ (count+2) +"行身份证号有误！");
                }
                if(StringUtil.isBlank(user.getUserPhone())){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码不能为空！");
                }
                if(!user.getUserPhone().matches(regEx)){
                    throw new Exception("导入失败！第"+ (count+2) +"行手机号码有误！");
                }
                if(StringUtil.isBlank(user.getUserEmail())){
                    throw new Exception("导入失败！第"+ (count+2) +"行邮箱不能为空！");
                }
//                String reg4 = "^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$";
                String reg4 = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
                if(!user.getUserEmail().matches(reg4)){
                    throw new Exception("导入失败！第"+ (count+2) +"行邮箱有误！");
                }
                if(StringUtil.isBlank(user.getIsOwnerStu())){
                    throw new Exception("导入失败！第"+ (count+2) +"行是否本院不能为空！");
                }
                SysUserExample example = new SysUserExample();
                example.createCriteria().andUserCodeEqualTo(user.getUserCode());
                int num = userMapper.countByExample(example);
                if(num <= 0){//新增
                    user.setUserFlow(PkUtil.getUUID());
                    user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
                    user.setStatusId(UserStatusEnum.Activated.getId());
                    GeneralMethod.setRecordInfo(user,true);
                    //插入学员角色
                    SysUserRole userRole = new SysUserRole();
                    userRole.setRecordFlow(PkUtil.getUUID());
                    userRole.setWsId("lcjn");
                    userRole.setUserFlow(user.getUserFlow());
                    userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    userRole.setAuthTime(DateUtil.getCurrDateTime());
                    userRole.setAuthUserFlow(user.getUserFlow());
                    userRole.setCreateTime(DateUtil.getCurrDateTime());
                    userRole.setCreateUserFlow(user.getUserFlow());
                    userRole.setModifyTime(DateUtil.getCurrDateTime());
                    userRole.setModifyUserFlow(user.getUserFlow());
                    SysCfg cfg = cfgBiz.read("lcjn_doctor_role_flow");
                    String doctorRole ="";
                    if (null != cfg) {
                        doctorRole = cfg.getCfgValue();
                        if(!StringUtil.isNotBlank(doctorRole)){
                            doctorRole = cfg.getCfgBigValue();
                        }
                    }
                    if(StringUtil.isNotBlank(doctorRole)){
                        userRole.setRoleFlow(doctorRole);
                    }else{
                        throw new Exception("导入失败！请配置学员角色！");
                    }
                    userRoleMapper.insertSelective(userRole);
                    //插入完毕
                    userMapper.insertSelective(user);
                }else{//修改
                    userMapper.updateByExampleSelective(user,example);
                }
                count ++;
            }
        }
        return count;
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
}
