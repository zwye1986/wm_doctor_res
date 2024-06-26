package com.pinde.sci.biz.nyzl.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.IRetestStudentBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.NyzlRetestStudentMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class RetestStudentBizImpl implements IRetestStudentBiz{
    @Autowired
    private NyzlRetestStudentMapper retestStudentMapper;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;


    @Override
    public List<NyzlRetestStudent> searchRetestStudentList(NyzlRetestStudent retestStudent) {
        NyzlRetestStudentExample example=new NyzlRetestStudentExample();
        NyzlRetestStudentExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(retestStudent.getStuNo())){
            criteria.andStuNoLike("%"+retestStudent.getStuNo()+"%");
        }
        if(StringUtil.isNotBlank(retestStudent.getStuName())){
            criteria.andStuNameLike("%"+retestStudent.getStuName()+"%");
        }
        if(StringUtil.isNotBlank(retestStudent.getEducationTypeId())){
            criteria.andEducationTypeIdEqualTo(retestStudent.getEducationTypeId());
        }
        if(StringUtil.isNotBlank(retestStudent.getRegisterStatusId())){
            criteria.andRegisterStatusIdEqualTo(retestStudent.getRegisterStatusId());
        }
        if(StringUtil.isNotBlank(retestStudent.getSpeId())){
            criteria.andSpeIdEqualTo(retestStudent.getSpeId());
        }
        if(StringUtil.isNotBlank(retestStudent.getSpeName())){
            criteria.andSpeNameLike("%"+retestStudent.getSpeName()+"%");
        }
        if(StringUtil.isNotBlank(retestStudent.getDirectionId())){
            criteria.andDirectionIdEqualTo(retestStudent.getDirectionId());
        }
        if(StringUtil.isNotBlank(retestStudent.getDirectionName())){
            criteria.andDirectionNameLike("%"+retestStudent.getDirectionName()+"%");
        }
        if(StringUtil.isNotBlank(retestStudent.getDegreeTypeId())){
            criteria.andDegreeTypeIdEqualTo(retestStudent.getDegreeTypeId());
        }
        if(StringUtil.isNotBlank(retestStudent.getRecruitYear())){
            criteria.andRecruitYearEqualTo(retestStudent.getRecruitYear());
        }
        if(StringUtil.isNotBlank(retestStudent.getSwapBatchId())){
            criteria.andSwapBatchIdEqualTo(retestStudent.getSwapBatchId());
        }
        if(StringUtil.isNotBlank(retestStudent.getOrgFlow())){
            criteria.andOrgFlowEqualTo(retestStudent.getOrgFlow());
        }
        if(StringUtil.isNotBlank(retestStudent.getFwhFlow())){
            criteria.andFwhFlowEqualTo(retestStudent.getFwhFlow());
        }
        if(StringUtil.isNotBlank(retestStudent.getStuSign())){
            criteria.andStuSignEqualTo(retestStudent.getStuSign());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return retestStudentMapper.selectByExample(example);
    }

    @Override
    public NyzlRetestStudent searchStudentByRecordFlow(String recordFlow) {
        return retestStudentMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int save(NyzlRetestStudent retestStudent) {
        if(StringUtil.isNotBlank(retestStudent.getRecordFlow())){
            GeneralMethod.setRecordInfo(retestStudent, false);
            return retestStudentMapper.updateByPrimaryKeySelective(retestStudent);
        }else{
            retestStudent.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(retestStudent, true);
            return retestStudentMapper.insertSelective(retestStudent);
        }
    }

    @Override
    public int importRetestStudent(MultipartFile file,String educationTypeId,String swapBatchId,String stuSignFlag,String adminFlag) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            if("0".equals(educationTypeId)){
                return parseExcel1(wb,educationTypeId,swapBatchId,stuSignFlag,adminFlag);
            }
            return parseExcel(wb,educationTypeId,swapBatchId,stuSignFlag,adminFlag);
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
        // EXCEL2003使用的是微软的文件系统
        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
            return new HSSFWorkbook(inS);
        }
        // EXCEL2007使用的是OOM文件格式
        if (POIXMLDocument.hasOOXMLHeader(inS)) {
            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
            return new XSSFWorkbook(OPCPackage.open(inS));
        }
        throw new Exception("不能解析的excel版本");
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
    private int parseExcel(Workbook wb,String educationTypeId,String swapBatchId,String stuSignFlag,String adminFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num<=1){
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
                Row r = sheet.getRow(i);
                NyzlRetestStudent retestStudent = new NyzlRetestStudent();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                retestStudent.setEducationTypeId(educationTypeId);
                retestStudent.setEducationTypeName("1".equals(educationTypeId)?"全日制":"在职");
                retestStudent.setSwapBatchId(swapBatchId);
                retestStudent.setSwapBatchName(DictTypeEnum.NyzlBatch.getDictNameById(swapBatchId));
                retestStudent.setStuSign(stuSignFlag);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("年份".equals(currTitle)){
                        titles.add("年份");
                        retestStudent.setRecruitYear(value);
                        if(StringUtil.isBlank(retestStudent.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }
                    if("姓名".equals(currTitle)){
                        titles.add("姓名");
                        retestStudent.setStuName(value);
                        if(StringUtil.isBlank(retestStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                        }
                    }
                    else if ("性别".equals(currTitle)){
                        titles.add("性别");
                        retestStudent.setSexName(value);
                        if("男".equals(value)){
                            retestStudent.setSexId("Man");
                        }
                        if("女".equals(value)){
                            retestStudent.setSexId("Woman");
                        }
                        if(StringUtil.isBlank(retestStudent.getSexName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                        }
                    }
                    else if ("考生编号".equals(currTitle)){
                        titles.add("考生编号");
                        retestStudent.setStuNo(value);
                        if(StringUtil.isBlank(retestStudent.getStuNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行考生编号不能为空！");
                        }
                    }
                    else if ("身份证号".equals(currTitle)){
                        titles.add("身份证号");
                        retestStudent.setCardNo(value);
                        if(StringUtil.isBlank(retestStudent.getCardNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                        }
                    }
                    else if ("移动电话".equals(currTitle)){
                        titles.add("移动电话");
                        retestStudent.setContactPhone(value);
                    }
                    else if ("电子信箱".equals(currTitle)){
                        titles.add("电子信箱");
                        retestStudent.setContactEmail(value);
                    }
                    else if ("毕业单位".equals(currTitle)){
                        titles.add("毕业单位");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+2) +"行毕业单位不能为空！");
                        }
                        retestStudent.setGraduationUnit(value);
                    }
                    else if ("毕业专业名称".equals(currTitle)){
                        titles.add("毕业专业名称");
                        retestStudent.setGraduationSpeName(value);
                    }
                    else if ("方向".equals(currTitle)){
                        titles.add("方向");
                        retestStudent.setDirectionName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            List<SysDict> dictList=DictTypeEnum.NyzlDirection.getSysDictList();
//                            if(dictList!=null&&dictList.size()>0){
//                                for (SysDict sd:dictList){
//                                    if(value.equals(sd.getDictName())){
//                                        retestStudent.setDirectionId(sd.getDictId());
//                                    }
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getDirectionId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的方向！");
//                        }
                    }
                    else if ("一级学科名称".equals(currTitle)){
                        titles.add("一级学科名称");
                        retestStudent.setSubjectOne(value);
                    }else if ("报考专业代码".equals(currTitle)){
                        titles.add("报考专业代码");
                        retestStudent.setSpeId(value);
//                        if(StringUtil.isBlank(retestStudent.getSpeId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业代码不能为空！");
//                        }
                    }
                    else if ("报考专业名称".equals(currTitle)){
                        titles.add("报考专业名称");
                        retestStudent.setSpeName(value);
//                        if(StringUtil.isBlank(value)){
//                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业名称不能为空！");
//                        }else if(StringUtil.isNotBlank(retestStudent.getSpeId())){
//                            if(!value.equals(DictTypeEnum.NyzlSpe.getDictNameById(retestStudent.getSpeId()))){
//                                throw new Exception("导入失败！第"+ (count+2) +"行报考专业名称与报考专业代码不匹配！");
//                            }
//                        }
                    }
                    else if ("学位类型".equals(currTitle)){
                        titles.add("学位类型");
                        retestStudent.setDegreeTypeName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlDegreeType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setDegreeTypeId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getDegreeTypeId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                        }
                    }
                    else if ("报考类别".equals(currTitle)){
                        titles.add("报考类别");
                        retestStudent.setApplicationCategoryName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlApplicationType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setApplicationCategoryId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getApplicationCategoryId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考类别！");
                        }
                    }
                    else if ("外国语名称".equals(currTitle)){
                        titles.add("外国语名称");
                        retestStudent.setForeignLanguage(value);
                    }
                    else if ("外国语".equals(currTitle)){
                        titles.add("外国语");
                        retestStudent.setForeignLanguageGrade(value);
                    }
                    else if ("业务课1名称".equals(currTitle)){
                        titles.add("业务课1名称");
                        retestStudent.setBusinessOne(value);
                    }
                    else if ("业务课1".equals(currTitle)){
                        titles.add("业务课1");
                        retestStudent.setBusinessOneGrade(value);
                    }
                    else if ("业务课2名称".equals(currTitle)){
                        titles.add("业务课2名称");
                        retestStudent.setBusinessTwo(value);
                    }
                    else if ("业务课2".equals(currTitle)){
                        titles.add("业务课2");
                        retestStudent.setBusinessTwoGrade(value);
                    }
                    else if ("总分".equals(currTitle)){
                        titles.add("总分");
                        retestStudent.setStuGrade(value);
                    }
                    else if ("全校1".equals(currTitle)){
                        titles.add("全校1");
                        retestStudent.setSchoolRanking(value);
                    }
                    else if ("全院2".equals(currTitle)){
                        titles.add("全院2");
                        retestStudent.setCollegeRankging(value);
                    }else if ("状态".equals(currTitle)){
                        titles.add("状态");
                        retestStudent.setRegisterStatusName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            for(NyzlRegisterStatusEnum str:NyzlRegisterStatusEnum.values()){
//                                if(value.equals(str.getName())){
//                                    retestStudent.setRegisterStatusId(str.getId());
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getRegisterStatusId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的状态！");
//                        }
                    }else if ("分委会".equals(currTitle)){
                        titles.add("分委会");
                        retestStudent.setFwhName(value);
                        if(StringUtil.isNotBlank(value)&&deptList!=null&&deptList.size()>0){
                            for (SysDept sd:deptList){
                                if(value.equals(sd.getDeptName())){
                                    retestStudent.setFwhFlow(sd.getDeptFlow());
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getFwhFlow())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的分委会！");
                        }
                    }
                }
                if("fwh".equals(adminFlag)){
                    retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                    retestStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
                }
                NyzlRetestStudent retestStudent1=new NyzlRetestStudent();
                retestStudent1.setRecruitYear(retestStudent.getRecruitYear());
                retestStudent1.setStuNo(retestStudent.getStuNo());
                retestStudent1.setStuSign(stuSignFlag);
                retestStudent1.setFwhFlow(retestStudent.getFwhFlow());
//              retestStudent1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                List<NyzlRetestStudent> listTemp=searchRetestStudentList(retestStudent1);
                if(listTemp!=null&&listTemp.size()>0&&listTemp.get(0)!=null){
                    retestStudent.setRecordFlow(listTemp.get(0).getRecordFlow());
                }
//                retestStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//                retestStudent.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                save(retestStudent);
                count ++;
            }
        }
        return count;
    }
    private int parseExcel1(Workbook wb,String educationTypeId,String swapBatchId,String stuSignFlag,String adminFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num<=1){
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
                Row r = sheet.getRow(i);
                NyzlRetestStudent retestStudent = new NyzlRetestStudent();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                retestStudent.setEducationTypeId(educationTypeId);
                retestStudent.setEducationTypeName("1".equals(educationTypeId)?"全日制":"在职");
                retestStudent.setSwapBatchId(swapBatchId);
                retestStudent.setSwapBatchName(DictTypeEnum.NyzlBatch.getDictNameById(swapBatchId));
                retestStudent.setStuSign(stuSignFlag);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("年份".equals(currTitle)){
                        titles.add("年份");
                        retestStudent.setRecruitYear(value);
                        if(StringUtil.isBlank(retestStudent.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }else if ("考生编号".equals(currTitle)){
                        titles.add("考生编号");
                        retestStudent.setStuNo(value);
                        if(StringUtil.isBlank(retestStudent.getStuNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行考生编号不能为空！");
                        }
                    }if("姓名".equals(currTitle)){
                        titles.add("姓名");
                        retestStudent.setStuName(value);
                        if(StringUtil.isBlank(retestStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                        }
                    }
                    else if ("证件号码".equals(currTitle)){
                        titles.add("证件号码");
                        retestStudent.setCardNo(value);
                        if(StringUtil.isBlank(retestStudent.getCardNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行证件号码不能为空！");
                        }
                    }else if ("出生日期".equals(currTitle)){
                        titles.add("出生日期");
                        retestStudent.setStuBirthday(value);
                        if(StringUtil.isBlank(retestStudent.getStuBirthday())){
                            throw new Exception("导入失败！第"+ (count+2) +"行出生日期不能为空！");
                        }
                    }else if ("英语标准分".equals(currTitle)){
                        titles.add("英语标准分");
                        retestStudent.setForeignLanguageGrade(value);
                    }
                    else if ("合格证编号".equals(currTitle)){
                        titles.add("合格证编号");
                        retestStudent.setCertificateNo(value);
                    }else if ("报考专业代码".equals(currTitle)){
                        titles.add("报考专业代码");
                        retestStudent.setSpeId(value);
                        if(StringUtil.isBlank(retestStudent.getSpeId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业代码不能为空！");
                        }
                    }
                    else if ("报考专业信息".equals(currTitle)){
                        titles.add("报考专业信息");
                        retestStudent.setSpeName(value);
//                        if(StringUtil.isBlank(value)){
//                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业信息不能为空！");
//                        }else if(StringUtil.isNotBlank(retestStudent.getSpeId())){
//                            if(!value.equals(DictTypeEnum.NyzlSpe.getDictNameById(retestStudent.getSpeId()))){
//                                throw new Exception("导入失败！第"+ (count+2) +"行报考专业信息与报考专业代码不匹配！");
//                            }
//                        }
                    }else if ("学位类型".equals(currTitle)){
                        titles.add("学位类型");
                        retestStudent.setDegreeTypeName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlDegreeType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setDegreeTypeId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getDegreeTypeId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                        }
                    }
                    else if ("意向报考导师".equals(currTitle)){
                        titles.add("意向报考导师");
                        retestStudent.setIntentionTeacherName(value);
                    }else if ("性别".equals(currTitle)){
                        titles.add("性别");
                        retestStudent.setSexName(value);
                        if("男".equals(value)){
                            retestStudent.setSexId("Man");
                        }
                        if("女".equals(value)){
                            retestStudent.setSexId("Woman");
                        }
                        if(StringUtil.isBlank(retestStudent.getSexName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                        }
                    }
                    else if ("硕士毕业单位".equals(currTitle)){
                        titles.add("硕士毕业单位");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+2) +"行硕士毕业单位不能为空！");
                        }
                        retestStudent.setGraduationUnit(value);
                    }
                    else if ("方向".equals(currTitle)){
                        titles.add("方向");
                        retestStudent.setDirectionName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            List<SysDict> dictList=DictTypeEnum.NyzlDirection.getSysDictList();
//                            if(dictList!=null&&dictList.size()>0){
//                                for (SysDict sd:dictList){
//                                    if(value.equals(sd.getDictName())){
//                                        retestStudent.setDirectionId(sd.getDictId());
//                                    }
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getDirectionId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的方向！");
//                        }
                    }else if ("报考类别".equals(currTitle)){
                        titles.add("报考类别");
                        retestStudent.setApplicationCategoryName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlApplicationType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setApplicationCategoryId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getApplicationCategoryId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考类别！");
                        }
                    }
                    else if ("联系方式".equals(currTitle)){
                        titles.add("联系方式");
                        retestStudent.setContactPhone(value);
                    }else if ("状态".equals(currTitle)){
                        titles.add("状态");
                        retestStudent.setRegisterStatusName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            for(NyzlRegisterStatusEnum str:NyzlRegisterStatusEnum.values()){
//                                if(value.equals(str.getName())){
//                                    retestStudent.setRegisterStatusId(str.getId());
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getRegisterStatusId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的状态！");
//                        }
                    }else if ("分委会".equals(currTitle)){
                        titles.add("分委会");
                        retestStudent.setFwhName(value);
                        if(StringUtil.isNotBlank(value)&&deptList!=null&&deptList.size()>0){
                            for (SysDept sd:deptList){
                                if(value.equals(sd.getDeptName())){
                                    retestStudent.setFwhFlow(sd.getDeptFlow());
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getFwhFlow())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的分委会！");
                        }
                    }
                }
                if("fwh".equals(adminFlag)){
                    retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                    retestStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
                }
                NyzlRetestStudent retestStudent1=new NyzlRetestStudent();
                retestStudent1.setRecruitYear(retestStudent.getRecruitYear());
                retestStudent1.setStuNo(retestStudent.getStuNo());
                retestStudent1.setStuSign(stuSignFlag);
                retestStudent1.setFwhFlow(retestStudent.getFwhFlow());
//              retestStudent1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                List<NyzlRetestStudent> listTemp=searchRetestStudentList(retestStudent1);
                if(listTemp!=null&&listTemp.size()>0&&listTemp.get(0)!=null){
                    retestStudent.setRecordFlow(listTemp.get(0).getRecordFlow());
                }
//                retestStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//                retestStudent.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                save(retestStudent);
                count ++;
            }
        }
        return count;
    }

    @Override
    public int importStuInfo(MultipartFile file, String stuSignFlag,String adminFlag) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcelTotal(wb,stuSignFlag,adminFlag);
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
    private int parseExcelTotal(Workbook wb,String stuSignFlag,String adminFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num<=1){
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
                Row r = sheet.getRow(i);
                NyzlRetestStudent retestStudent = new NyzlRetestStudent();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                retestStudent.setStuSign(stuSignFlag);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("年份".equals(currTitle)){
                        titles.add("年份");
                        retestStudent.setRecruitYear(value);
                        if(StringUtil.isBlank(retestStudent.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }else if ("考生编号".equals(currTitle)){
                        titles.add("考生编号");
                        retestStudent.setStuNo(value);
                        if(StringUtil.isBlank(retestStudent.getStuNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行考生编号不能为空！");
                        }
                    }if("姓名".equals(currTitle)){
                        titles.add("姓名");
                        retestStudent.setStuName(value);
                        if(StringUtil.isBlank(retestStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                        }
                    }else if ("性别".equals(currTitle)){
                        titles.add("性别");
                        retestStudent.setSexName(value);
                        if("男".equals(value)){
                            retestStudent.setSexId("Man");
                        }
                        if("女".equals(value)){
                            retestStudent.setSexId("Woman");
                        }
                        if(StringUtil.isBlank(retestStudent.getSexName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
                        }
                    }else if ("身份证号".equals(currTitle)){
                        titles.add("身份证号");
                        retestStudent.setCardNo(value);
                        if(StringUtil.isBlank(retestStudent.getCardNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                        }
                    }
                    else if ("电子信箱".equals(currTitle)){
                        titles.add("电子信箱");
                        retestStudent.setContactEmail(value);
                    }
                    else if ("移动电话".equals(currTitle)){
                        titles.add("移动电话");
                        retestStudent.setContactPhone(value);
                    }
                    else if ("毕业单位".equals(currTitle)){
                        titles.add("毕业单位");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+2) +"行硕士毕业单位不能为空！");
                        }
                        retestStudent.setGraduationUnit(value);
                    }
                    else if ("联系方式".equals(currTitle)){
                        titles.add("联系方式");
                        retestStudent.setContactPhone(value);
                    }else if ("报考专业代码".equals(currTitle)){
                        titles.add("报考专业代码");
                        retestStudent.setSpeId(value);
//                        if(StringUtil.isBlank(retestStudent.getSpeId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业代码不能为空！");
//                        }
                    }
                    else if ("报考专业名称".equals(currTitle)){
                        titles.add("报考专业名称");
                        retestStudent.setSpeName(value);
//                        if(StringUtil.isBlank(value)){
//                            throw new Exception("导入失败！第"+ (count+2) +"行报考专业名称不能为空！");
//                        }else if(StringUtil.isNotBlank(retestStudent.getSpeId())){
//                            if(!value.equals(DictTypeEnum.NyzlSpe.getDictNameById(retestStudent.getSpeId()))){
//                                throw new Exception("导入失败！第"+ (count+2) +"行报考专业名称与报考专业代码不匹配！");
//                            }
//                        }
                    }
                    else if ("方向".equals(currTitle)){
                        titles.add("方向");
                        retestStudent.setDirectionName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            List<SysDict> dictList=DictTypeEnum.NyzlDirection.getSysDictList();
//                            if(dictList!=null&&dictList.size()>0){
//                                for (SysDict sd:dictList){
//                                    if(value.equals(sd.getDictName())){
//                                        retestStudent.setDirectionId(sd.getDictId());
//                                    }
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getDirectionId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的方向！");
//                        }
                    }
                    else if ("报考专业方向".equals(currTitle)){
                        titles.add("报考专业方向");
                        retestStudent.setDirectionName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            List<SysDict> dictList=DictTypeEnum.NyzlDirection.getSysDictList();
//                            if(dictList!=null&&dictList.size()>0){
//                                for (SysDict sd:dictList){
//                                    if(value.equals(sd.getDictName())){
//                                        retestStudent.setDirectionId(sd.getDictId());
//                                    }
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getDirectionId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考专业方向！");
//                        }
                    }
                    else if ("学位类型".equals(currTitle)){
                        titles.add("学位类型");
                        retestStudent.setDegreeTypeName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlDegreeType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setDegreeTypeId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getDegreeTypeId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                        }
                    }
                    else if ("报考类别".equals(currTitle)){
                        titles.add("报考类别");
                        retestStudent.setApplicationCategoryName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlApplicationType.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setApplicationCategoryId(sd.getDictId());
                                    }
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getApplicationCategoryId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考类别！");
                        }
                    }
                    else if ("毕业专业名称".equals(currTitle)){
                        titles.add("毕业专业名称");
                        retestStudent.setGraduationSpeName(value);
                    }
                    else if ("毕业院校类型".equals(currTitle)){
                        titles.add("毕业院校类型");
                        retestStudent.setInstitutionTypeName(value);
                    }
                    else if ("一级学科名称".equals(currTitle)){
                        titles.add("一级学科名称");
                        retestStudent.setSubjectOne(value);
                    }
                    else if ("政治理论名称".equals(currTitle)){
                        titles.add("政治理论名称");
                        retestStudent.setPoliticalTheory(value);
                    }
                    else if ("政治理论".equals(currTitle)){
                        titles.add("政治理论");
                        retestStudent.setPoliticalTheoryGrade(value);
                    }
                    else if ("总分".equals(currTitle)){
                        titles.add("总分");
                        retestStudent.setStuGrade(value);
                    }
                    else if ("外国语名称".equals(currTitle)){
                        titles.add("外国语名称");
                        retestStudent.setForeignLanguage(value);
                    }
                    else if ("外国语".equals(currTitle)){
                        titles.add("外国语");
                        retestStudent.setForeignLanguageGrade(value);
                    }
                    else if ("业务课1名称".equals(currTitle)){
                        titles.add("业务课1名称");
                        retestStudent.setBusinessOne(value);
                    }
                    else if ("业务课1".equals(currTitle)){
                        titles.add("业务课1");
                        retestStudent.setBusinessOneGrade(value);
                    }
                    else if ("业务课2名称".equals(currTitle)){
                        titles.add("业务课2名称");
                        retestStudent.setBusinessTwo(value);
                    }
                    else if ("业务课2".equals(currTitle)){
                        titles.add("业务课2");
                        retestStudent.setBusinessTwoGrade(value);
                    }
                    else if ("全校排名".equals(currTitle)){
                        titles.add("全校排名");
                        retestStudent.setSchoolRanking(value);
                    }
                    else if ("全院排名".equals(currTitle)){
                        titles.add("全院排名");
                        retestStudent.setCollegeRankging(value);
                    }
                    else if ("六级成绩".equals(currTitle)){
                        titles.add("六级成绩");
                        retestStudent.setSixGrade(value);
                    }
                    else if ("年级排名百分比".equals(currTitle)){
                        titles.add("年级排名百分比");
                        retestStudent.setGradeRankingPercentage(value);
                    }
                    else if ("状态".equals(currTitle)){
                        titles.add("状态");
                        retestStudent.setRegisterStatusName(value);
//                        if(StringUtil.isNotBlank(value)){
//                            for(NyzlRegisterStatusEnum str:NyzlRegisterStatusEnum.values()){
//                                if(value.equals(str.getName())){
//                                    retestStudent.setRegisterStatusId(str.getId());
//                                }
//                            }
//                        }
//                        if(StringUtil.isBlank(retestStudent.getRegisterStatusId())){
//                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的状态！");
//                        }
                    }else if ("复试批次".equals(currTitle)){
                        titles.add("复试批次");
                        retestStudent.setSwapBatchName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList=DictTypeEnum.NyzlBatch.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList){
                                    if(value.equals(sd.getDictName())){
                                        retestStudent.setSwapBatchId(sd.getDictId());
                                    }
                                }
                            }
                            if(StringUtil.isBlank(retestStudent.getSwapBatchId())){
                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的复试批次！");
                            }
                        }
                    }else if ("分委会".equals(currTitle)){
                        titles.add("分委会");
                        retestStudent.setFwhName(value);
                        if(StringUtil.isNotBlank(value)&&deptList!=null&&deptList.size()>0){
                            for (SysDept sd:deptList){
                                if(value.equals(sd.getDeptName())){
                                    retestStudent.setFwhFlow(sd.getDeptFlow());
                                }
                            }
                        }
                        if(StringUtil.isBlank(retestStudent.getFwhFlow())){
                            throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的分委会！");
                        }
                    }
                }
                if("fwh".equals(adminFlag)){
                    retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                    retestStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
                }
                NyzlRetestStudent retestStudent1=new NyzlRetestStudent();
                retestStudent1.setRecruitYear(retestStudent.getRecruitYear());
                retestStudent1.setStuNo(retestStudent.getStuNo());
                retestStudent1.setStuSign(stuSignFlag);
                retestStudent1.setFwhFlow(retestStudent.getFwhFlow());
//              retestStudent1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                List<NyzlRetestStudent> listTemp=searchRetestStudentList(retestStudent1);
                if(listTemp!=null&&listTemp.size()>0&&listTemp.get(0)!=null){
                    retestStudent.setRecordFlow(listTemp.get(0).getRecordFlow());
                }
                save(retestStudent);
                count ++;
            }
        }
        return count;
    }
}
