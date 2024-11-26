package com.pinde.sci.biz.osca.impl;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaExaminerManageBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.OscaTeaInfoMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.osca.OscaExaminerMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaExaminerExt;
import com.pinde.sci.model.osca.OscaTypeSpeExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaExaminerManageBizImpl implements IOscaExaminerManageBiz{
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private SysOrgMapper sysOrgMapper;
    @Autowired
    private OscaExaminerMapper oscaExaminerMapper;
    @Autowired
    private OscaTeaInfoMapper teaInfoMapper;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IRoleBiz roleBiz;

    @Override
    public List<OscaExaminerExt> searchAllExam(Map<String, Object> map) {
        return oscaExaminerMapper.searchAllExam(map);
    }

    @Override
    public List<OscaTeaInfo> getOscaTeaInfo(String userFlow) {
        if(StringUtil.isNotBlank(userFlow)){
            OscaTeaInfoExample example= new OscaTeaInfoExample();
            OscaTeaInfoExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andUserFlowEqualTo(userFlow);
            return teaInfoMapper.selectByExample(example);
        }
        return null;
    }


    @Override
    public int updateExamAndUser(SysUser sysUser, OscaTeaInfo oscaTeaInfo, List<OscaTypeSpeExt> typeSpeList, SysUserRole userRole) {

        if(StringUtil.isNotBlank(sysUser.getUserFlow())){
            GeneralMethod.setRecordInfo(sysUser, false);
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }else{
            sysUser.setUserFlow(PkUtil.getUUID());
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), InitPasswordUtil.getInitPass()));
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            //考官标志
            sysUser.setIsExamTea(GlobalConstant.FLAG_Y);
            GeneralMethod.setRecordInfo(sysUser,true);
            sysUserMapper.insertSelective(sysUser);
        }
        if(userRole!=null)
        {
            userRole.setUserFlow(sysUser.getUserFlow());
            userRoleBiz.addSysUserRole(userRole);
        }
        if(null!=typeSpeList && typeSpeList.size()>0){

            //先判断考官表是否已存在
            List<OscaTeaInfo> teaInfos = getOscaTeaInfo(sysUser.getUserFlow());
                //存在则先删除，再插入
                if (null != teaInfos && teaInfos.size() > 0) {
                    OscaTeaInfoExample example = new OscaTeaInfoExample();
                    OscaTeaInfoExample.Criteria criteria = example.createCriteria();
                    criteria.andUserFlowEqualTo(sysUser.getUserFlow());

                    oscaTeaInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    teaInfoMapper.updateByExampleSelective(oscaTeaInfo,example);

                }
            //不存在，则直接循环插入
            for(OscaTypeSpeExt typeSpe:typeSpeList) {
                if (StringUtil.isBlank(typeSpe.getTrainingSpeId()) || StringUtil.isBlank(typeSpe.getTrainingTypeId())) {
                    continue;
                }
                oscaTeaInfo.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(oscaTeaInfo, true);
                oscaTeaInfo.setUserFlow(sysUser.getUserFlow());

                oscaTeaInfo.setTrainingTypeId(typeSpe.getTrainingTypeId());
                oscaTeaInfo.setTrainingTypeName(DictTypeEnum.OscaTrainingType.getDictNameById(typeSpe.getTrainingTypeId()));

                oscaTeaInfo.setTrainingSpeId(typeSpe.getTrainingSpeId());
                List<SysDict> dictList= dictBiz.searchDictListByDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+typeSpe.getTrainingTypeId());
                if(dictList!=null&&dictList.size()>0){
                    for (SysDict sd:dictList){
                        if(sd.getDictId().equals(typeSpe.getTrainingSpeId())){
                            oscaTeaInfo.setTrainingSpeName(sd.getDictName());
                            break;
                        }
                    }
                }
                teaInfoMapper.insertSelective(oscaTeaInfo);
            }
    }

        return GlobalConstant.ONE_LINE;
    }

    @Override
    public void importExamFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            parseExcel(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private void parseExcel(Workbook wb) {
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        int count=0;
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try {
                sheet = (HSSFSheet) wb.getSheetAt(0);
            } catch (Exception e) {
                sheet = (XSSFSheet) wb.getSheetAt(0);
            }

            int row_num = sheet.getLastRowNum();
            if(row_num<1){
                throw new RuntimeException("导入失败！导入数据为空!");
            }
            for (int i = 0; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                int cell_num = r.getLastCellNum();
                if (i == 0) {
                    colnames.add("用户名");
                    colnames.add("姓名");
                    colnames.add("性别");
                    colnames.add("考核人员类型");
                    colnames.add("考核专业");
                    colnames.add("职称");
                    colnames.add("联系方式");
                    colnames.add("所在单位");
                    colnames.add("所在考点");
                } else {
                    SysUser sysUser = new SysUser();

                    //存放考核人员类型、考核专业List
//                    List<Map<String,String>> typeSpeList = new ArrayList<>();
                    //存放考核人员类型、考核专业map
                    Map<String,String> typeSpeMap = new HashMap<>();
                    boolean flag = false;
                    boolean modifyFlag = true;
                    List<SysDict> dicts=null;
                    String type="",spe="";

                    for (int j = 0; j < cell_num; j++) {
                        String value = "";
                        Cell cell = r.getCell((short) j);
                        try {
                            cell.setCellType(CellType.STRING);
                            if (cell.getCellType() == CellType.STRING) {
                                value = r.getCell((short) j).getStringCellValue();
                            } else {
                                value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                            }
                        }catch(Exception e){
                            value="";
                        }
                        if ("用户名".equals(colnames.get(j))) {
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                flag = true;
                                break;
                            }
                            sysUser.setUserCode(value);
                        } else if ("姓名".equals(colnames.get(j))) {
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                flag = true;
                                break;
                            }
                            sysUser.setUserName(value);
                        } else if ("性别".equals(colnames.get(j))) {
                            sysUser.setSexName(value);
                            if(StringUtil.isNotBlank(value)){
                                if ("男".equals(value)) {
                                    sysUser.setSexId("Man");
                                }
                                if ("女".equals(value)){
                                    sysUser.setSexId("Woman");
                                }
                            }
                        }else if("考核人员类型".equals(colnames.get(j))){
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核人员类型不能为空!");
                            }
                            type = value;
                            //校验 人员类型和专业是否对应的存在
                            //1、先校验考核人员类型是否存在，根据名称反查
                            SysDict sysDict = new SysDict();
                            sysDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId());
                            sysDict.setDictName(type);
                            dicts = dictBiz.searchDictListByDictName(sysDict);

                            if(null==dicts || dicts.size()==0){
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核人员类型不存在!");
                            }

                        }else if("考核专业".equals(colnames.get(j))){
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核专业不能为空!");
                            }
                            spe = value;
                            //2、如果存在，则查出该人员类型对应的考核专业list
                            List<SysDict> dictList= dictBiz.searchDictListByDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+dicts.get(0).getDictId());
                            //3、校验导入的专业是否存在于list当中
                            if(null!=dictList && dictList.size()>0){
                                boolean isExist=false;
                                for(SysDict dict:dictList){
                                    if(spe.equals(dict.getDictName())){
                                        typeSpeMap.put(type,spe);
                                        isExist=true;
                                        break;
                                    }
                                }
                                if(!isExist){
                                    throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核专业不存在!");
                                }
                            }else {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，当前考核人员类型下没有考核专业!");
                            }

                        } else if ("职称".equals(colnames.get(j))) {
                            sysUser.setTitleName(value);
                        } else if ("联系方式".equals(colnames.get(j))) {
                            sysUser.setUserPhone(value);
                        } else if ("所在单位".equals(colnames.get(j))) {
                            sysUser.setWorkOrgName(value);
                        } else if ("所在考点".equals(colnames.get(j))) {
                            //首先判断是否为考点
                            if(StringUtil.isNotBlank(value)){
                                SysOrg sysOrg = new SysOrg();
                                sysOrg.setOrgName(value);
                                sysOrg.setIsExamOrg(GlobalConstant.FLAG_Y);
                                List<SysOrg> sysOrgs = orgBiz.searchSysOrgByName(sysOrg);
                                if (sysOrgs != null && sysOrgs.size() > 0) {
                                    sysUser.setOrgName(value);
                                    sysUser.setOrgFlow(sysOrgs.get(0).getOrgFlow());
                                }else{
                                    throw new RuntimeException("导入失败！第"+ (count+1) +"行，请填写正确的考点名称!");
                                }
                            }
                        }
                    }
                    if (flag) {
                        throw new RuntimeException("导入失败！第"+ (count+1) +"行，用户名/姓名不能为空!");
                    }
                    SysUser existUser=userBiz.findByUserCode(sysUser.getUserCode());
                    if(existUser!=null)
                    {
                        throw new RuntimeException("导入失败！第"+ (count+1) +"行，用户名已存在!");
                    }
                    if(modifyFlag){
                        SysUserRole userRole=new SysUserRole();
                        userRole.setRoleFlow(examTeaRole);
                        userRole.setWsId("osca");
                        //考官信息表
                        OscaTeaInfo oscaTeaInfo  = new OscaTeaInfo();

                        List<OscaTypeSpeExt> typeSpeList = new ArrayList<>();
                        OscaTypeSpeExt typeSpeExt = new OscaTypeSpeExt();

                        typeSpeExt.setTrainingTypeId(dicts.get(0).getDictId());
                        List<SysDict> dictList= dictBiz.searchDictListByDictTypeIdAndDictName(DictTypeEnum.OscaTrainingType.getId()+"."+dicts.get(0).getDictId(),spe);

                        typeSpeExt.setTrainingSpeId(dictList.get(0).getDictId());
                        typeSpeList.add(typeSpeExt);
                        updateExamAndUser(sysUser,oscaTeaInfo,typeSpeList, userRole);
                    }
                }
                count++;
            }
        }
    }

    private void parseExcel1(Workbook wb) {
        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        int count=0;
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try {
                sheet = (HSSFSheet) wb.getSheetAt(0);
            } catch (Exception e) {
                sheet = (XSSFSheet) wb.getSheetAt(0);
            }

            int row_num = sheet.getLastRowNum();
            if(row_num<1){
                throw new RuntimeException("导入失败！导入数据为空!");
            }
            int cell_num = 0;
            for (int i = 0; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                if (i == 0) {
                    cell_num = r.getLastCellNum();
                    colnames.add("用户名");
                    colnames.add("姓名");
                    colnames.add("性别");
                    colnames.add("考核人员类型");
                    colnames.add("考核专业");
                    colnames.add("职称");
                    colnames.add("联系方式");
                    colnames.add("所在单位");
//                    colnames.add("所在考点");
                } else {
                    SysUser sysUser = new SysUser();

                    sysUser.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                    sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//                    ResDoctor resDoctor = new ResDoctor();

                    //存放考核人员类型、考核专业List
//                    List<Map<String,String>> typeSpeList = new ArrayList<>();
                    //存放考核人员类型、考核专业map
                    Map<String,String> typeSpeMap = new HashMap<>();
                    boolean flag = false;
                    boolean modifyFlag = true;
                    List<SysDict> dicts=null;
                    String type="",spe="";

                    for (int j = 0; j < cell_num; j++) {
                        String value = "";
                        Cell cell = r.getCell((short) j);
                        try {
                            cell.setCellType(CellType.STRING);
                            if (cell.getCellType() == CellType.STRING) {
                                value = r.getCell((short) j).getStringCellValue();
                            } else {
                                value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                            }
                        }catch(Exception e){
                            value="";
                        }
                        if ("用户名".equals(colnames.get(j))) {
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                flag = true;
                                break;
                            }
                            sysUser.setUserCode(value);
                        } else if ("姓名".equals(colnames.get(j))) {
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                flag = true;
                                break;
                            }
                            sysUser.setUserName(value);
                        } else if ("性别".equals(colnames.get(j))) {
                            sysUser.setSexName(value);
                            if(StringUtil.isNotBlank(value)){
                                if ("男".equals(value)) {
                                    sysUser.setSexId("Man");
                                }
                                if ("女".equals(value)){
                                    sysUser.setSexId("Woman");
                                }
                            }
                        }else if("考核人员类型".equals(colnames.get(j))){
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核人员类型不能为空!");
                            }
                            type = value;
                            //校验 人员类型和专业是否对应的存在
                            //1、先校验考核人员类型是否存在，根据名称反查
                            SysDict sysDict = new SysDict();
                            sysDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId());
                            sysDict.setDictName(type);
                             dicts = dictBiz.searchDictListByDictName(sysDict);

                            if(null==dicts || dicts.size()==0){
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核人员类型不存在!");
                            }

                        }else if("考核专业".equals(colnames.get(j))){
                            System.err.print("123123123123");
                            if (value == null
                                    || value.trim()
                                    .length() == 0) {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核专业不能为空!");
                            }
                            spe = value;
                            //2、如果存在，则查出该人员类型对应的考核专业list
                            List<SysDict> dictList= dictBiz.searchDictListByDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+dicts.get(0).getDictId());
                            //3、校验导入的专业是否存在于list当中
                            if(null!=dictList && dictList.size()>0){
                                boolean isExist=false;
                                for(SysDict dict:dictList){
                                    if(spe.equals(dict.getDictName())){
                                        typeSpeMap.put(type,spe);
                                        isExist=true;
                                        break;
                                    }
                                }
                                if(!isExist){
                                    throw new RuntimeException("导入失败！第"+ (count+1) +"行，考核专业不存在!");
                                }
                            }else {
                                throw new RuntimeException("导入失败！第"+ (count+1) +"行，当前考核人员类型下没有考核专业!");
                            }

                        } else if ("职称".equals(colnames.get(j))) {
                            sysUser.setTitleName(value);
                        } else if ("联系方式".equals(colnames.get(j))) {
                            sysUser.setUserPhone(value);
                        } else if ("所在单位".equals(colnames.get(j))) {
                            sysUser.setWorkOrgName(value);
                        }
                    }
                    if (flag) {
                        throw new RuntimeException("导入失败！第"+ (count+1) +"行，用户名/姓名不能为空!");
                    }
//                    sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//                    SysOrg sysOrg1 = new SysOrg();
//                    sysOrg1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//                    sysOrg1.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//                    List<SysOrg> sysOrgs1 = orgBiz.searchSysOrg(sysOrg1);
//                    if (sysOrgs1 != null && sysOrgs1.size() > 0) {
//                        sysUser.setOrgName(sysOrgs1.get(0).getOrgName());
//                    }
                    //查询全部以验证

                    SysUser existUser=userBiz.findByUserCode(sysUser.getUserCode());
                    if(existUser!=null)
                    {
                        throw new RuntimeException("导入失败！第"+ (count+1) +"行，用户名已存在!");
                    }
                    if(modifyFlag){
                        SysUserRole userRole=new SysUserRole();
                        userRole.setRoleFlow(examTeaRole);
                        userRole.setWsId("osca");
                        //考官信息表
                        OscaTeaInfo oscaTeaInfo  = new OscaTeaInfo();

                        List<OscaTypeSpeExt> typeSpeList = new ArrayList<>();
                        OscaTypeSpeExt typeSpeExt = new OscaTypeSpeExt();
                        if(null!=dicts && dicts.size()>0){
                            typeSpeExt.setTrainingTypeId(dicts.get(0).getDictId());
                            List<SysDict> dictList= dictBiz.searchDictListByDictTypeIdAndDictName(DictTypeEnum.OscaTrainingType.getId()+"."+dicts.get(0).getDictId(),spe);

                            typeSpeExt.setTrainingSpeId(dictList.get(0).getDictId());
                            typeSpeList.add(typeSpeExt);
                        }

                        updateExamAndUser(sysUser,oscaTeaInfo,typeSpeList, userRole);

                    }
                }
                count++;
            }
        }
    }
    @Override
    public void importExamFromExcel1(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            parseExcel1(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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
    }
    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public List<SysUser> sysUserList(SysUser sysUser) {
        SysUserExample example=new SysUserExample();
        SysUserExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sysUser.getUserCode())){
            criteria.andUserCodeEqualTo(sysUser.getUserCode());
        }
        List<SysUser> userList=sysUserMapper.selectByExample(example);
        return userList;
    }
}
