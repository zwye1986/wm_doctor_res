package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResAnnualCheckBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResAnnualAssessmentRecordMapper;
import com.pinde.sci.model.mo.ResAnnualAssessmentRecord;
import com.pinde.sci.model.mo.ResAnnualAssessmentRecordExample;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author william.li
 * @date 2017/12/29
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResAnnualCheckBizImpl implements IResAnnualCheckBiz{

    @Autowired
    private ResAnnualAssessmentRecordMapper recordMapper;

    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @Autowired
    private IUserBiz userBiz;

    @Autowired
    private IFileBiz fileBiz;

    @Override
    public List<ResAnnualAssessmentRecord> listAssessmentRecord(String userFlow) {
        ResAnnualAssessmentRecordExample example = new ResAnnualAssessmentRecordExample();
        ResAnnualAssessmentRecordExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(userFlow)){
            criteria.andUserFlowEqualTo(userFlow);
        }
        return recordMapper.selectByExample(example);
    }

    @Override
    public int saveAnnualCheck(ResAnnualAssessmentRecord assessmentRecord) {
        int count=0;
        if(StringUtil.isBlank(assessmentRecord.getAnnualAssessmentFlow())){
            assessmentRecord.setAnnualAssessmentFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(assessmentRecord,true);
            count = recordMapper.insertSelective(assessmentRecord);
        }else{
            GeneralMethod.setRecordInfo(assessmentRecord,false);
            count = recordMapper.updateByPrimaryKeySelective(assessmentRecord);
        }
        return count;
    }

    @Override
    public int updateByUserFlowAndYear(ResAnnualAssessmentRecord assessmentRecord) {
        int count=0;
        ResAnnualAssessmentRecordExample example = new ResAnnualAssessmentRecordExample();
        ResAnnualAssessmentRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserFlowEqualTo(assessmentRecord.getUserFlow());
        criteria.andAssessmentYearEqualTo(assessmentRecord.getAssessmentYear());

        List<ResAnnualAssessmentRecord> recordList = recordMapper.selectByExample(example);
        if(null!=recordList && recordList.size()>0){
            count = recordMapper.updateByExampleSelective(assessmentRecord,example);
        }else{
            count = saveAnnualCheck(assessmentRecord);
        }

        return count;
    }

    @Override
    public int deleteMatertialUrlAndFile(ResAnnualAssessmentRecord assessmentRecord) {
        int count=0;

        String oldFolderName = assessmentRecord.getMaterialUrl();
        //删除原文件
        if (StringUtil.isNotBlank(oldFolderName)) {
            try {
                oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                File imgFile = new File(oldFolderName);
                if (imgFile.exists()) {
                    imgFile.delete();
                }

                if(null!=assessmentRecord && StringUtil.isNotBlank(assessmentRecord.getAnnualAssessmentFlow())){
                    assessmentRecord.setMaterialName("");
                    assessmentRecord.setMaterialUrl("");
                    count = recordMapper.updateByPrimaryKeySelective(assessmentRecord);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("删除文件失败！");
            }
        }

        return count;
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (file.getSize() > 0) {
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + dateString;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/" + dateString + "/" + originalFilename;
        }

        return path;
    }



    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0){
            return String.valueOf((long) d);
        }else{
            return String.valueOf(d);
        }
    }
    private int parseCheckExcel(Workbook wb) throws Exception{
        //导入记录数
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num <= 1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                if(null == titleR.getCell(i)){
                    throw new Exception("导入文件首行列名异常！");
                }
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                SysUser user = new SysUser();
                ResDoctor resDoctor = new ResDoctor();
                ResAnnualAssessmentRecord assessmentRecord = new ResAnnualAssessmentRecord();

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

                    if("姓名".equals(currTitle)){
                        user.setUserName(value);
                        assessmentRecord.setUserName(value);
                    }
                    if("身份证号".equals(currTitle)){
                        user.setIdNo(value);
                    }
                    if("入培时间".equals(currTitle)){
                        resDoctor.setSessionNumber(value);
                    }
                    if("结业时间".equals(currTitle)){
                        resDoctor.setGraduationYear(value);
                    }
                    if("日常考核及出科考核".equals(currTitle)){
                        assessmentRecord.setDailyFinishScore(value);
                    }
                    if("培训手册填写".equals(currTitle)){
                        assessmentRecord.setTrainingManualScore(value);
                    }
                    if("专业理论成绩".equals(currTitle)){
                        assessmentRecord.setProfessionalTheoryScore(value);
                    }
                    if("技能考核名称".equals(currTitle)){
                        assessmentRecord.setSkillName(value);
                    }
                    if("技能成绩".equals(currTitle)){
                        assessmentRecord.setSkillScore(value);
                    }
                    if("公共技能成绩".equals(currTitle)){
                        assessmentRecord.setPublicSkillsScore(value);
                    }
                    if("备注".equals(currTitle)){
                        assessmentRecord.setMemo(value);
                    }

                }

                //总成绩计算公式：
                //日常考核及出科考核分数+培训手册填写分数+专业理论成绩分数*40%+技能考核成绩分数*40%+公共技能成绩分数*10%
                Double totalScore=0.0;

                if(StringUtil.isBlank(user.getIdNo())){
                    throw new Exception("导入失败！第"+ (count+1) +"行身份证号不能为空！");
                }

                if(StringUtil.isNotBlank(assessmentRecord.getDailyFinishScore())){
                    if(!"2".equals(assessmentRecord.getDailyFinishScore().trim()) && !"5".equals(assessmentRecord.getDailyFinishScore().trim())){
                        throw new Exception("导入失败！第"+ (count+1) +"行日常考核及出科考核分数不符合标准！");
                    }
                    totalScore += Double.valueOf(assessmentRecord.getDailyFinishScore());
                }
               if(StringUtil.isNotBlank(assessmentRecord.getTrainingManualScore())){
                   if(!"2".equals(assessmentRecord.getTrainingManualScore().trim()) && !"5".equals(assessmentRecord.getTrainingManualScore().trim())){
                       throw new Exception("导入失败！第"+ (count+1) +"行培训手册填写分数不符合标准！");
                   }
                   totalScore += Double.valueOf(assessmentRecord.getTrainingManualScore());
               }

               if(StringUtil.isNotBlank(assessmentRecord.getProfessionalTheoryScore().trim())){
                   if(Integer.parseInt(assessmentRecord.getProfessionalTheoryScore().trim())<0 && Integer.parseInt(assessmentRecord.getProfessionalTheoryScore().trim())>100){
                       throw new Exception("导入失败！第"+ (count+1) +"行专业理论成绩分数不符合标准！");
                   }
                   totalScore += Double.valueOf(assessmentRecord.getProfessionalTheoryScore())*0.4;
               }

               if(StringUtil.isNotBlank(assessmentRecord.getSkillScore())){
                   if(Integer.parseInt(assessmentRecord.getSkillScore().trim())<0 && Integer.parseInt(assessmentRecord.getSkillScore().trim())>100){
                       throw new Exception("导入失败！第"+ (count+1) +"行技能考核成绩分数不符合标准！");
                   }
                   totalScore += Double.valueOf(assessmentRecord.getSkillScore())*0.4;
               }
               if(StringUtil.isNotBlank(assessmentRecord.getPublicSkillsScore())){
                   if(Integer.parseInt(assessmentRecord.getPublicSkillsScore().trim())<0 && Integer.parseInt(assessmentRecord.getPublicSkillsScore().trim())>100){
                       throw new Exception("导入失败！第"+ (count+1) +"行公共技能成绩分数不符合标准！");
                   }
                   totalScore += Double.valueOf(assessmentRecord.getPublicSkillsScore())*0.1;
               }

                assessmentRecord.setApprovedTotalScore(String.valueOf(totalScore));


                //排除入培时间为2017年且入培年限为3年的学员和外院轮转人员
                //根据身份证号查询 userFlow
                SysUser sysUser = userBiz.findByIdNo(user.getIdNo());
                if(null==sysUser){
                    throw new Exception("导入失败！第"+ (count+1) +"行用户不存在！");
                }
                assessmentRecord.setUserFlow(sysUser.getUserFlow());

                //考核年度为系统当前年份
//                assessmentRecord.setAssessmentYear(DateUtil.getYear());
                //暂时改成2017年度
                assessmentRecord.setAssessmentYear("2017");

                Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("sessionNumber","2017");
                    paramMap.put("trainingYears","3");
                    paramMap.put("doctorTypeId","ExternalEntrust");
                    paramMap.put("doctorFlow",sysUser.getUserFlow());
                List<ResDoctor> doctorList = resDoctorBiz.searchResDoctorByAssessment(paramMap);
                if(null!=doctorList && doctorList.size()>0){
                    throw new Exception("导入失败！第"+ (count+1) +"行属于入培时间为2017年，且培训年限为3的学员或为外院人员！");
                }

                //先查询 成绩记录表中是否已经存在该学员数据
                List<ResAnnualAssessmentRecord> records = listAssessmentRecord(sysUser.getUserFlow());
                if(null!=records && records.size()>0){
                    if(StringUtil.isNotBlank(records.get(0).getAnnualAssessmentFlow())){
                        assessmentRecord.setAnnualAssessmentFlow(records.get(0).getAnnualAssessmentFlow());
                        GeneralMethod.setRecordInfo(assessmentRecord,false);
                        recordMapper.updateByPrimaryKeySelective(assessmentRecord);
                    }
                }else {
                    assessmentRecord.setAnnualAssessmentFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(assessmentRecord,true);
                    recordMapper.insertSelective(assessmentRecord);
                }

                count ++;
            }
        }
        return count;
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

    }

    @Override
    public int importCheckExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            byte[] fileData = new byte[(int) file.getSize()];
            is = file.getInputStream();
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseCheckExcel(wb);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            is.close();
        }
    }

}
