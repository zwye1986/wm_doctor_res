package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IGzzyyyAssessmentBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.GzzyyyAnnualAssessmentMapper;
import com.pinde.sci.dao.base.GzzyyyQuarterlyAssessmentMapper;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
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

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class GzzyyyAssessmentBizImpl implements IGzzyyyAssessmentBiz {
    @Autowired
    private GzzyyyAnnualAssessmentMapper annualAssessmentMapper;
    @Autowired
    private GzzyyyQuarterlyAssessmentMapper quarterlyAssessmentMapper;
    @Autowired
    private IUserBiz userBiz;

    @Override
    public int editAnnualAssessment(GzzyyyAnnualAssessment annualAssessment) {
        String recordFlow = annualAssessment.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(annualAssessment,false);
            return annualAssessmentMapper.updateByPrimaryKeySelective(annualAssessment);
        }else {
            annualAssessment.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(annualAssessment,true);
            return annualAssessmentMapper.insertSelective(annualAssessment);
        }
    }

    @Override
    public List<GzzyyyAnnualAssessment> searchAnnualAssessment(GzzyyyAnnualAssessment annualAssessment) {
        GzzyyyAnnualAssessmentExample example = new GzzyyyAnnualAssessmentExample();
        GzzyyyAnnualAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(annualAssessment!=null){
            if(StringUtil.isNotBlank(annualAssessment.getDoctorName())){
                criteria.andDoctorNameLike("%"+annualAssessment.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(annualAssessment.getUserCode())){
                criteria.andUserCodeLike("%"+annualAssessment.getUserCode()+"%");
            }
            if(StringUtil.isNotBlank(annualAssessment.getSessionNumber())){
                criteria.andSessionNumberEqualTo(annualAssessment.getSessionNumber());
            }
            if(StringUtil.isNotBlank(annualAssessment.getOrgFlow())){
                criteria.andOrgFlowEqualTo(annualAssessment.getOrgFlow());
            }
            if(StringUtil.isNotBlank(annualAssessment.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(annualAssessment.getDoctorFlow());
            }
        }
        return annualAssessmentMapper.selectByExample(example);
    }

    @Override
    public int importAnnualAssessment(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel(wb);
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

    private int parseExcel(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <=row_num; i++){
                Row r =  sheet.getRow(i);
                if(r==null){
                    throw new RuntimeException("导入失败！，表中不能有空行！");
                }
                SysUser current = GlobalContext.getCurrentUser();
                GzzyyyAnnualAssessment annualAssessment = new GzzyyyAnnualAssessment();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType().getCode() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }

					/*姓名 用户名 年级 理论1 技能1 理论2 技能2 理论3 技能3*/
                    if("用户名".equals(colnames.get(j))) {
                        SysUser currentUser = userBiz.findByUserCode(value);
                        if(currentUser==null||StringUtil.isBlank(currentUser.getOrgFlow())||(!current.getOrgFlow().equals(currentUser.getOrgFlow()))){
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，学员用户名有误！");
                        }else{
                            annualAssessment.setUserCode(currentUser.getUserCode());
                            annualAssessment.setDoctorFlow(currentUser.getUserFlow());
                            annualAssessment.setOrgFlow(currentUser.getOrgFlow());
                            annualAssessment.setDoctorName(currentUser.getUserName());
                        }
                    }else if("姓名".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            annualAssessment.setDoctorName(value);
                        }
                    }else if("年级".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^(19|20)\\d{2}$");
                            if (b) {
                                annualAssessment.setSessionNumber(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行，年级有误！");
                            }
                        }else {
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，年级不能为空！");
                        }
                    }else if("第一年理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setTheory1(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第一年理论成绩必须为数字！");
                            }
                        }
                    }else if("第一年技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setSkill1(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第一年技能成绩必须为数字！");
                            }
                        }
                    }else if("第二年理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setTheory2(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第二年理论成绩必须为数字！");
                            }
                        }
                    }else if("第二年技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setSkill2(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第二年技能成绩必须为数字！");
                            }
                        }
                    }else if("第三年理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setTheory3(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第三年理论成绩必须为数字！");
                            }
                        }
                    }else if("第三年技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                annualAssessment.setSkill3(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第三年技能成绩必须为数字！");
                            }
                        }
                    }
                }
                //执行保存
                GzzyyyAnnualAssessmentExample example = new GzzyyyAnnualAssessmentExample();
                example.createCriteria().andDoctorFlowEqualTo(annualAssessment.getDoctorFlow()).
                        andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                List<GzzyyyAnnualAssessment> old = annualAssessmentMapper.selectByExample(example);

                if(old!=null&&old.size()>0){
                    annualAssessment.setRecordFlow(old.get(0).getRecordFlow());
                }
                editAnnualAssessment(annualAssessment);
                count ++;
            }
        }
        return count;
    }

    @Override
    public int editQuarterlyAssessment(GzzyyyQuarterlyAssessment quarterlyAssessment) {
        String recordFlow = quarterlyAssessment.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(quarterlyAssessment,false);
            return quarterlyAssessmentMapper.updateByPrimaryKeySelective(quarterlyAssessment);
        }else {
            quarterlyAssessment.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(quarterlyAssessment,true);
            return quarterlyAssessmentMapper.insertSelective(quarterlyAssessment);
        }
    }

    @Override
    public List<GzzyyyQuarterlyAssessment> searchQuarterlyAssessment(GzzyyyQuarterlyAssessment quarterlyAssessment) {
        GzzyyyQuarterlyAssessmentExample example = new GzzyyyQuarterlyAssessmentExample();
        GzzyyyQuarterlyAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(quarterlyAssessment!=null){
            if(StringUtil.isNotBlank(quarterlyAssessment.getDoctorName())){
                criteria.andDoctorNameLike("%"+quarterlyAssessment.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(quarterlyAssessment.getUserCode())){
                criteria.andUserCodeLike("%"+quarterlyAssessment.getUserCode()+"%");
            }
            if(StringUtil.isNotBlank(quarterlyAssessment.getYear())){
                criteria.andYearEqualTo(quarterlyAssessment.getYear());
            }
            if(StringUtil.isNotBlank(quarterlyAssessment.getOrgFlow())){
                criteria.andOrgFlowEqualTo(quarterlyAssessment.getOrgFlow());
            }
            if(StringUtil.isNotBlank(quarterlyAssessment.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(quarterlyAssessment.getDoctorFlow());
            }
        }
        return quarterlyAssessmentMapper.selectByExample(example);
    }

    @Override
    public int importQuarterlyAssessment(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel2(wb);
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

    private int parseExcel2(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <=row_num; i++){
                Row r =  sheet.getRow(i);
                if(r==null){
                    throw new RuntimeException("导入失败！，表中不能有空行！");
                }
                SysUser current = GlobalContext.getCurrentUser();
                GzzyyyQuarterlyAssessment quarterlyAssessment = new GzzyyyQuarterlyAssessment();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType().getCode() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }

					/*姓名 用户名 年度 理论1 技能1 理论2 技能2 理论3 技能3 理论4 技能4*/
                    if("用户名".equals(colnames.get(j))) {
                        SysUser currentUser = userBiz.findByUserCode(value);
                        if(currentUser==null||StringUtil.isBlank(currentUser.getOrgFlow())||(!current.getOrgFlow().equals(currentUser.getOrgFlow()))){
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，学员用户名有误！");
                        }else{
                            quarterlyAssessment.setUserCode(currentUser.getUserCode());
                            quarterlyAssessment.setDoctorFlow(currentUser.getUserFlow());
                            quarterlyAssessment.setOrgFlow(currentUser.getOrgFlow());
                            quarterlyAssessment.setDoctorName(currentUser.getUserName());
                        }
                    }else if("姓名".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            quarterlyAssessment.setDoctorName(value);
                        }
                    }else if("年度".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^(19|20)\\d{2}$");
                            if (b) {
                                quarterlyAssessment.setYear(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行，年度有误！");
                            }
                        }else {
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，年度不能为空！");
                        }
                    }else if("第一季度理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setTheory1(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第一季度理论成绩必须为数字！");
                            }
                        }
                    }else if("第一季度技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setSkill1(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第一季度技能成绩必须为数字！");
                            }
                        }
                    }else if("第二季度理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setTheory2(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第二季度理论成绩必须为数字！");
                            }
                        }
                    }else if("第二季度技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setSkill2(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第二季度技能成绩必须为数字！");
                            }
                        }
                    }else if("第三季度理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setTheory3(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第三季度理论成绩必须为数字！");
                            }
                        }
                    }else if("第三季度技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setSkill3(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第三季度技能成绩必须为数字！");
                            }
                        }
                    }else if("第四季度理论成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setTheory4(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第四季度理论成绩必须为数字！");
                            }
                        }
                    }else if("第四季度技能成绩".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            Boolean b = value.matches("^[0-9]+([.]{1}[0-9]+){0,1}$");
                            if (b) {
                                quarterlyAssessment.setSkill4(value);
                            } else {
                                throw new RuntimeException("导入失败！，第" + (count+2) + "行第四季度技能成绩必须为数字！");
                            }
                        }
                    }
                }
                //执行保存
                GzzyyyQuarterlyAssessmentExample example = new GzzyyyQuarterlyAssessmentExample();
                example.createCriteria().andDoctorFlowEqualTo(quarterlyAssessment.getDoctorFlow()).andYearEqualTo(quarterlyAssessment.getYear()).
                        andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                List< GzzyyyQuarterlyAssessment> old = quarterlyAssessmentMapper.selectByExample(example);

                if(old!=null&&old.size()>0){
                    quarterlyAssessment.setRecordFlow(old.get(0).getRecordFlow());
                }
                editQuarterlyAssessment(quarterlyAssessment);
                count ++;
            }
        }
        return count;
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
}
