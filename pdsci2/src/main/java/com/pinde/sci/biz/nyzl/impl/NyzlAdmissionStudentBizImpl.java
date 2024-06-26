package com.pinde.sci.biz.nyzl.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlAdmissionStudentBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.NyzlAdmissionStudentMapper;
import com.pinde.sci.enums.nyzl.NyzlStuSignEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.NyzlAdmissionStudent;
import com.pinde.sci.model.mo.NyzlAdmissionStudentExample;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysOrg;
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
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor=Exception.class)
public class NyzlAdmissionStudentBizImpl implements INyzlAdmissionStudentBiz{
    @Autowired
    private NyzlAdmissionStudentMapper admissionStudentMapper;
    @Autowired
    private IOrgBiz orgBiz;

    @Override
    public List<NyzlAdmissionStudent> searchAdmissionStudentList(NyzlAdmissionStudent admissionStudent) {
        NyzlAdmissionStudentExample example=new NyzlAdmissionStudentExample();
        NyzlAdmissionStudentExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(admissionStudent.getRecordFlow())){
            criteria.andRecordFlowEqualTo(admissionStudent.getRecordFlow());
        }
        if(StringUtil.isNotBlank(admissionStudent.getStuSign())){
            criteria.andStuSignEqualTo(admissionStudent.getStuSign());
        }
        if(StringUtil.isNotBlank(admissionStudent.getStuNo())){
            criteria.andStuNoEqualTo(admissionStudent.getStuNo());
        }
        if(StringUtil.isNotBlank(admissionStudent.getStuName())){
            criteria.andStuNameLike(admissionStudent.getStuName());
        }
        if(StringUtil.isNotBlank(admissionStudent.getAdmissionFlag())){
            criteria.andAdmissionFlagEqualTo(admissionStudent.getAdmissionFlag());
        }
        if(StringUtil.isNotBlank(admissionStudent.getSwapFlag())){
            criteria.andSwapFlagEqualTo(admissionStudent.getSwapFlag());
        }
        if(StringUtil.isNotBlank(admissionStudent.getSpeId())){
            criteria.andSpeIdEqualTo(admissionStudent.getSpeId());
        }
        if(StringUtil.isNotBlank(admissionStudent.getStuDirectionId())){
            criteria.andStuDirectionIdEqualTo(admissionStudent.getStuDirectionId());
        }
        if(StringUtil.isNotBlank(admissionStudent.getDegreeTypeId())){
            criteria.andDegreeTypeIdEqualTo(admissionStudent.getDegreeTypeId());
        }
        if(StringUtil.isNotBlank(admissionStudent.getEducationTypeId())){
            criteria.andEducationTypeIdEqualTo(admissionStudent.getEducationTypeId());
        }
        if(StringUtil.isNotBlank(admissionStudent.getRecruitYear())){
            criteria.andRecruitYearEqualTo(admissionStudent.getRecruitYear());
        }
        if(StringUtil.isNotBlank(admissionStudent.getOrgFlow())){
            criteria.andOrgFlowEqualTo(admissionStudent.getOrgFlow());
        }
        if(StringUtil.isNotBlank(admissionStudent.getFwhFlow())){
            criteria.andFwhFlowEqualTo(admissionStudent.getFwhFlow());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return admissionStudentMapper.selectByExample(example);
    }

    @Override
    public int save(NyzlAdmissionStudent admissionStudent) {
        if(StringUtil.isNotBlank(admissionStudent.getRecordFlow())){
            GeneralMethod.setRecordInfo(admissionStudent, false);
            return admissionStudentMapper.updateByPrimaryKeySelective(admissionStudent);
        }else{
            admissionStudent.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(admissionStudent, true);
            return admissionStudentMapper.insertSelective(admissionStudent);
        }
    }

    @Override
    public int importAdmissionStudent(MultipartFile file, String stuSignFlag) {InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
                return parseExcel1(wb,stuSignFlag);//夏令营导入
            }
            return parseExcel(wb,stuSignFlag);
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
    private int parseExcel(Workbook wb,String stuSignFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
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
            List<SysOrg> orgList=orgBiz.searchTrainOrgList();
            for(int i = 1;i < row_num; i++) {
                Row r = sheet.getRow(i);
                NyzlAdmissionStudent admissionStudent = new NyzlAdmissionStudent();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                admissionStudent.setStuSign(stuSignFlag);
                admissionStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                admissionStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
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
                        admissionStudent.setRecruitYear(value);
                        if(StringUtil.isBlank(admissionStudent.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }
                    else if ("考生编号".equals(currTitle)){
                        titles.add("考生编号");
                        admissionStudent.setStuNo(value);
                        if(StringUtil.isBlank(admissionStudent.getStuNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行考生编号不能为空！");
                        }
                        //查询旧数据
                        List<NyzlAdmissionStudent> tempList=searchAdmissionStudentList(admissionStudent);
                        if(tempList!=null&&tempList.size()>0&&tempList.get(0)!=null){
                            admissionStudent.setRecordFlow(tempList.get(0).getRecordFlow());
                        }
                    }else if ("姓名".equals(currTitle)){
                        titles.add("姓名");
                        admissionStudent.setStuName(value);
                        if(StringUtil.isBlank(admissionStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                        }
                    }else if ("博士类型".equals(currTitle)){
                        titles.add("博士类型");
                        admissionStudent.setEducationTypeName(value);
                        if(StringUtil.isBlank(admissionStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行博士类型不能为空！");
                        }
                        if("全日制".equals(value)){
                            admissionStudent.setEducationTypeId("1");
                        }
                        if("在职".equals(value)){
                            admissionStudent.setEducationTypeId("0");
                        }
                    }else if ("身份证号".equals(currTitle)){
                        titles.add("身份证号");
                        admissionStudent.setCardNo(value);
                        if(StringUtil.isBlank(admissionStudent.getCardNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                        }
                    }
                    else if ("录取专业代码".equals(currTitle)){
                        titles.add("录取专业代码");
                        admissionStudent.setSpeId(value);
//                        if(StringUtil.isNotBlank(value)){
//                            admissionStudent.setSpeName(DictTypeEnum.NyzlSpe.getDictNameById(value));
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行录取专业代码不能为空！");
//                        }
                    }else if ("录取专业名称".equals(currTitle)){
                        titles.add("录取专业名称");
//                        if(StringUtil.isNotBlank(value)){
//                            if(!value.equals(admissionStudent.getSpeName())){
//                                throw new Exception("导入失败！第"+ (count+2) +"行录取专业代码与录取专业名称不匹配！");
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行录取专业名称不能为空！");
//                        }
                        admissionStudent.setSpeName(value);
                    }else if ("初试成绩".equals(currTitle)){
                        titles.add("初试成绩");
                        if(checkGrade(value)){
                            admissionStudent.setTestGrade(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行初试成绩请输入正确的数字！");
                        }
                    }else if ("复试成绩".equals(currTitle)){
                        titles.add("复试成绩");
                        if(checkGrade(value)){
                            admissionStudent.setRetestGrade(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行复试成绩请输入正确的数字！");
                        }
                    }else if ("总成绩".equals(currTitle)){
                        titles.add("总成绩");
                        if(checkGrade(value)){
                            admissionStudent.setTotalGrade(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行总成绩请输入正确的数字！");
                        }
                    }else if ("导师所在单位".equals(currTitle)){
                        titles.add("导师所在单位");
                        admissionStudent.setTeaWorkUnit(value);
                    }else if ("导师姓名".equals(currTitle)){
                        titles.add("导师姓名");
                        admissionStudent.setTeaName(value);
                    }else if ("导师方向".equals(currTitle)){
                        titles.add("导师方向");
                        admissionStudent.setTeaDirectionName(value);
//                        List<SysDict> dictList= DictTypeEnum.NyzlDirection.getSysDictList();
//                        if(dictList!=null&&dictList.size()>0){
//                            for (SysDict sd:dictList) {
//                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
//                                    admissionStudent.setTeaDirectionId(sd.getDictId());
//                                }
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行方向字典未维护！");
//                        }
                    }else if ("报考方向".equals(currTitle)){
                        boolean directionFlag=true;
                        titles.add("报考方向");
                        admissionStudent.setStuDirectionName(value);
//                        List<SysDict> dictList= DictTypeEnum.NyzlDirection.getSysDictList();
//                        if(dictList!=null&&dictList.size()>0){
//                            for (SysDict sd:dictList) {
//                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
//                                    admissionStudent.setStuDirectionId(sd.getDictId());
//                                    directionFlag=false;
//                                }
//                            }
//                            if(directionFlag){
//                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考方向！");
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行方向字典未维护！");
//                        }
                    }else if ("复试小组入学总成绩排名".equals(currTitle)){
                        titles.add("复试小组入学总成绩排名");
                        if(checkNum(value)){
                            admissionStudent.setGroupRanking(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行复试小组入学总成绩排名请输入正确的数字！");
                        }
                    }else if ("是否录取(Y/N)".equals(currTitle)){
                        titles.add("是否录取(Y/N)");
                        admissionStudent.setAdmissionFlag(value);
                        if(StringUtil.isBlank(admissionStudent.getAdmissionFlag())){
                            throw new Exception("导入失败！第"+ (count+2) +"行是否录取不能为空！");
                        }
                    }else if ("学位类型".equals(currTitle)){
                        boolean degreeFlag=true;
                        titles.add("学位类型");
                        admissionStudent.setDegreeTypeName(value);
                        List<SysDict> dictList= DictTypeEnum.NyzlDegreeType.getSysDictList();
                        if(dictList!=null&&dictList.size()>0){
                            for (SysDict sd:dictList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
                                    admissionStudent.setDegreeTypeId(sd.getDictId());
                                    degreeFlag=false;
                                }
                            }
                            if(degreeFlag){
                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                            }
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行学位类型字典未维护！");
                        }
                    }else if ("是否调剂生(Y/N)".equals(currTitle)){
                        titles.add("是否调剂生(Y/N)");
                        admissionStudent.setSwapFlag(value);
                        if(StringUtil.isBlank(admissionStudent.getSwapFlag())){
                            throw new Exception("导入失败！第"+ (count+2) +"行是否调剂生不能为空！");
                        }
                    }else if ("复试批次".equals(currTitle)){
                        boolean degreeFlag=true;
                        titles.add("复试批次");
                        admissionStudent.setSwapBatchName(value);
                        if(StringUtil.isNotBlank(value)){
                            List<SysDict> dictList= DictTypeEnum.NyzlBatch.getSysDictList();
                            if(dictList!=null&&dictList.size()>0){
                                for (SysDict sd:dictList) {
                                    if(value.equals(sd.getDictName())){
                                        admissionStudent.setSwapBatchId(sd.getDictId());
                                        degreeFlag=false;
                                    }
                                }
                                if(degreeFlag){
                                    throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的复试批次！");
                                }
                            }else{
                                throw new Exception("导入失败！第"+ (count+2) +"行复试批次字典未维护！");
                            }
                        }
                    }else if ("培养单位".equals(currTitle)){
                        titles.add("培养单位");
                        admissionStudent.setOrgName(value);
                        if(orgList!=null&&orgList.size()>0){
                            for (SysOrg so:orgList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(so.getOrgName())){
                                    admissionStudent.setOrgFlow(so.getOrgFlow());
                                }
                            }
                            if(StringUtil.isBlank(admissionStudent.getOrgFlow())){
                                throw new Exception("导入失败！第"+ (count+2) +"行培养单位有误！");
                            }
                        }
                    }else if ("录取专业研究方向代码".equals(currTitle)){
                        titles.add("录取专业研究方向代码");
                        admissionStudent.setStuSpeDirectionId(value);
                    }else if ("录取专业研究方向".equals(currTitle)){
                        titles.add("录取专业研究方向");
                        admissionStudent.setStuSpeDirectionName(value);
                    }
                }
                save(admissionStudent);
                count ++;
            }
        }
        return count;

    }
    //夏令营导入
    private int parseExcel1(Workbook wb,String stuSignFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
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
            List<SysOrg> orgList=orgBiz.searchTrainOrgList();
            for(int i = 1;i < row_num; i++) {
                Row r = sheet.getRow(i);
                NyzlAdmissionStudent admissionStudent = new NyzlAdmissionStudent();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                admissionStudent.setStuSign(stuSignFlag);
                admissionStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                admissionStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
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
                        admissionStudent.setRecruitYear(value);
                        if(StringUtil.isBlank(admissionStudent.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }else if ("姓名".equals(currTitle)){
                        titles.add("姓名");
                        admissionStudent.setStuName(value);
                        if(StringUtil.isBlank(admissionStudent.getStuName())){
                            throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                        }
                    }else if ("身份证号".equals(currTitle)){
                        titles.add("身份证号");
                        admissionStudent.setCardNo(value);
                        if(StringUtil.isBlank(admissionStudent.getCardNo())){
                            throw new Exception("导入失败！第"+ (count+2) +"行身份证号不能为空！");
                        }
                        //查询旧数据
                        List<NyzlAdmissionStudent> tempList=searchAdmissionStudentList(admissionStudent);
                        if(tempList!=null&&tempList.size()>0&&tempList.get(0)!=null){
                            admissionStudent.setRecordFlow(tempList.get(0).getRecordFlow());
                        }
                    }
                    else if ("录取专业代码".equals(currTitle)){
                        titles.add("录取专业代码");
                        admissionStudent.setSpeId(value);
//                        if(StringUtil.isNotBlank(value)){
//                            admissionStudent.setSpeName(DictTypeEnum.NyzlSpe.getDictNameById(value));
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行录取专业代码不能为空！");
//                        }
                    }else if ("录取专业名称".equals(currTitle)){
                        titles.add("录取专业名称");
//                        if(StringUtil.isNotBlank(value)){
//                            if(!value.equals(admissionStudent.getSpeName())){
//                                throw new Exception("导入失败！第"+ (count+2) +"行录取专业代码与录取专业名称不匹配！");
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行录取专业名称不能为空！");
//                        }
                        admissionStudent.setSpeName(value);
                    }else if ("复试成绩".equals(currTitle)){
                        titles.add("复试成绩");
                        if(checkGrade(value)){
                            admissionStudent.setRetestGrade(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行复试成绩请输入正确的数字！");
                        }
                    }else if ("总成绩".equals(currTitle)){
                        titles.add("总成绩");
                        if(checkGrade(value)){
                            admissionStudent.setTotalGrade(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行总成绩请输入正确的数字！");
                        }
                    }else if ("导师所在单位".equals(currTitle)){
                        titles.add("导师所在单位");
                        admissionStudent.setTeaWorkUnit(value);
                    }else if ("导师姓名".equals(currTitle)){
                        titles.add("导师姓名");
                        admissionStudent.setTeaName(value);
                    }else if ("导师方向".equals(currTitle)){
                        titles.add("导师方向");
                        admissionStudent.setTeaDirectionName(value);
//                        List<SysDict> dictList= DictTypeEnum.NyzlDirection.getSysDictList();
//                        if(dictList!=null&&dictList.size()>0){
//                            for (SysDict sd:dictList) {
//                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
//                                    admissionStudent.setTeaDirectionId(sd.getDictId());
//                                }
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行方向字典未维护！");
//                        }
                    }else if ("报考方向".equals(currTitle)){
                        boolean directionFlag=true;
                        titles.add("报考方向");
                        admissionStudent.setStuDirectionName(value);
//                        List<SysDict> dictList= DictTypeEnum.NyzlDirection.getSysDictList();
//                        if(dictList!=null&&dictList.size()>0){
//                            for (SysDict sd:dictList) {
//                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
//                                    admissionStudent.setStuDirectionId(sd.getDictId());
//                                    directionFlag=false;
//                                }
//                            }
//                            if(directionFlag){
//                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的报考方向！");
//                            }
//                        }else{
//                            throw new Exception("导入失败！第"+ (count+2) +"行方向字典未维护！");
//                        }
                    }else if ("复试小组入学总成绩排名".equals(currTitle)){
                        titles.add("复试小组入学总成绩排名");
                        if(checkNum(value)){
                            admissionStudent.setGroupRanking(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行复试小组入学总成绩排名请输入正确的数字！");
                        }
                    }else if ("是否录取(Y/N)".equals(currTitle)){
                        titles.add("是否录取(Y/N)");
                        admissionStudent.setAdmissionFlag(value);
                        if(StringUtil.isBlank(admissionStudent.getAdmissionFlag())){
                            throw new Exception("导入失败！第"+ (count+2) +"行是否录取不能为空！");
                        }
                    }else if ("学位类型".equals(currTitle)){
                        boolean degreeFlag=true;
                        titles.add("学位类型");
                        admissionStudent.setDegreeTypeName(value);
                        List<SysDict> dictList= DictTypeEnum.NyzlDegreeType.getSysDictList();
                        if(dictList!=null&&dictList.size()>0){
                            for (SysDict sd:dictList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
                                    admissionStudent.setDegreeTypeId(sd.getDictId());
                                    degreeFlag=false;
                                }
                            }
                            if(degreeFlag){
                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                            }
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行学位类型字典未维护！");
                        }
                    }else if ("培养单位".equals(currTitle)){
                        titles.add("培养单位");
                        admissionStudent.setOrgName(value);
                        if(orgList!=null&&orgList.size()>0){
                            for (SysOrg so:orgList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(so.getOrgName())){
                                    admissionStudent.setOrgFlow(so.getOrgFlow());
                                }
                            }
                            if(StringUtil.isBlank(admissionStudent.getOrgFlow())){
                                throw new Exception("导入失败！第"+ (count+2) +"行培养单位有误！");
                            }
                        }
                    }else if ("录取专业研究方向代码".equals(currTitle)){
                        titles.add("录取专业研究方向代码");
                        admissionStudent.setStuSpeDirectionId(value);
                    }else if ("录取专业研究方向".equals(currTitle)){
                        titles.add("录取专业研究方向");
                        admissionStudent.setStuSpeDirectionName(value);
                    }
                }
                save(admissionStudent);
                count ++;
            }
        }
        return count;

    }

    @Override
    public NyzlAdmissionStudent readAdmissionStudent(String recordFlow) {
        return admissionStudentMapper.selectByPrimaryKey(recordFlow);
    }

    public boolean checkNum(String num){
        Boolean temp=false;
        Pattern pattern = Pattern.compile("^([1-9]\\d*|[0]{1,1})$");
        if(StringUtil.isNotBlank(num)){
            temp=pattern.matcher(num).matches();
        }
        return temp;
    }
    public boolean checkGrade(String num){
        Boolean temp=false;
        Pattern pattern = Pattern.compile("^[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)$");
        if(StringUtil.isNotBlank(num)){
            temp=pattern.matcher(num).matches();
        }
        return temp;
    }
}
