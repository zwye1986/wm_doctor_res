package com.pinde.sci.biz.recruit.impl;

import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.biz.recruit.IRecruitInterviewInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.RecruitInfoMapper;
import com.pinde.sci.dao.base.RecruitInterviewInfoMapper;
import com.pinde.sci.dao.recruit.RecruitInfoExtMapper;
import com.pinde.sci.enums.recruit.RecruitOperEnum;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoLog;
import com.pinde.sci.model.mo.RecruitInterviewInfo;
import com.pinde.sci.model.mo.RecruitInterviewInfoExample;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecruitInterviewInfoBizImpl implements IRecruitInterviewInfoBiz {

    @Autowired
    private RecruitInfoMapper recruitInfoMapper;

    @Autowired
    private RecruitInterviewInfoMapper recruitInterviewInfoMapper;

    @Autowired
    private RecruitInfoExtMapper recruitInfoExtMapper;
    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;
    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    @Override
    public Boolean isQualifyInterview(String recruitFlow) {
        RecruitInterviewInfo recruitInterviewInfo = recruitInterviewInfoMapper.selectByPrimaryKey(recruitFlow);
        if (recruitInterviewInfo == null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean sendInterview(RecruitInterviewInfo recruitInterviewInfo) {

        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecruitFlow(recruitInterviewInfo.getRecruitFlow());
        recruitInfo.setInterviewFlag(GlobalConstant.FLAG_Y);
        int c=recruitInfoBiz.saveRecruitInfo(recruitInfo);
        if (c == 1){
            saveInterviewInfo(recruitInterviewInfo);
            RecruitInfoLog log=new RecruitInfoLog();
            log.setRecruitFlow(recruitInterviewInfo.getRecruitFlow());
            log.setOperTypeId(RecruitOperEnum.InterView.getId());
            log.setOperTypeName(RecruitOperEnum.InterView.getName());
            log.setAuditStatusId(RecruitOperEnum.InterView.getId());
            log.setAuditStatusName(RecruitOperEnum.InterView.getName());
            recruitInfoLogBiz.saveRecruitLog(log);
            return true;
        }else {
            return false;
        }
    }

    private void saveInterviewInfo(RecruitInterviewInfo recruitInterviewInfo) {
        RecruitInterviewInfo old=readByFlow(recruitInterviewInfo.getRecruitFlow());
        if(old==null)
        {
            GeneralMethod.setRecordInfo(recruitInterviewInfo,true);
            recruitInterviewInfoMapper.insertSelective(recruitInterviewInfo);
        }else{
            GeneralMethod.setRecordInfo(recruitInterviewInfo,false);
            recruitInterviewInfoMapper.updateByPrimaryKeySelective(recruitInterviewInfo);
        }
    }

    @Override
    public Boolean sendAllInterview(List<RecruitInfoExt> recruitInfoExts) {
        if(recruitInfoExts!=null)
        {
            for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
            {

                RecruitInterviewInfo recruitInterviewInfo = new RecruitInterviewInfo();
                recruitInterviewInfo.setRecruitFlow(recruitInfoExt.getRecruitFlow());
                recruitInterviewInfo.setOrgFlow(recruitInfoExt.getOrgFlow());
                recruitInterviewInfo.setDoctorFlow(recruitInfoExt.getDoctorFlow());
                recruitInterviewInfo.setInterviewStartTime(recruitInfoExt.getRecruitExamInfo().getInterviewStartTime());
                recruitInterviewInfo.setInterviewEndTime(recruitInfoExt.getRecruitExamInfo().getInterviewEndTime());
                recruitInterviewInfo.setInterviewAddress(recruitInfoExt.getRecruitExamInfo().getInterviewAddress());
                recruitInterviewInfo.setInterviewDemo(recruitInfoExt.getRecruitExamInfo().getInterviewDemo());

                sendInterview(recruitInterviewInfo);
            }
            return true;
        }
        return  false;
    }

    @Override
    public Integer importInterviewFromExcel(MultipartFile file, String type) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseDiscAndResponExcel(wb,type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public RecruitInterviewInfo searchByExample(String recruitFlow, String orgFlow) {
        RecruitInterviewInfoExample example = new RecruitInterviewInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecruitFlowEqualTo(recruitFlow)
                .andOrgFlowEqualTo(orgFlow);
        List<RecruitInterviewInfo> recruitInterviewInfos = recruitInterviewInfoMapper.selectByExample(example);
        if (recruitInterviewInfos != null && recruitInterviewInfos.size() > 0){
            return recruitInterviewInfos.get(0);
        }else {
            return null;
        }
    }

    @Override
    public RecruitInterviewInfo readByFlow(String recruitFlow) {
        return recruitInterviewInfoMapper.selectByPrimaryKey(recruitFlow);
    }

    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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
        throw new IOException("不能解析的excel版本");
    }

    private int parseDiscAndResponExcel(Workbook wb, String type) {
        Sheet sheet = wb.getSheetAt(0);
        List<String> colnames = new ArrayList<String>();
        Map<String,RecruitInterviewInfo> map = new HashMap<String,RecruitInterviewInfo>();
        int row_num = 0;
        if (sheet != null){
            //获取行数
            row_num = sheet.getLastRowNum() + 1;
            //获取表头
            Row titleR = sheet.getRow(0);
            //获取表头单元格数
//            int cell_num = titleR.getLastCellNum();
            String title = "";
            for (int i = 0; i < 4; i++) {
                Cell cell = titleR.getCell(i);
                if (cell == null) {
                    throw new RuntimeException("导入失败！第" + (i + 1) + "列，表头不能为空！");
                } else {
                    title = cell.getStringCellValue();
                }
                colnames.add(title);
            }
            if (row_num <= 1){
                return -1;
            }
            for (int i = 1 ;i < row_num ;i++){
                Row row = sheet.getRow(i);
                String idNo = "";
                String recruitFlow = "";
                RecruitInterviewInfo recruitInterviewInfo = new RecruitInterviewInfo();
                if (row != null){
                    Cell cell = row.getCell(0);
                    if (cell.getCellType() == 1){
                        idNo = cell.getStringCellValue();
                        List<RecruitInfo> recruitInfos = null;
                        try {
                            recruitInfos = recruitInfoExtMapper.selectRecruitInfoByIdNo(idNo);
                        } catch (Exception e) {
                            throw new RuntimeException("导入失败!！第" + (i + 1) + "行，考生证件号有误!");
                        }
                        if (recruitInfos != null&& recruitInfos.size() > 0){
                            RecruitInfo recruitInfo = recruitInfos.get(0);
                            recruitFlow = recruitInfo.getRecruitFlow();
                            String examIsPass = recruitInfo.getExamIsPass();
                            String interviewFlag = recruitInfo.getInterviewFlag();
                            String recordStatus = recruitInfo.getRecordStatus();
                            Boolean qualifyInterview = isQualifyInterview(recruitFlow);
                            if (!qualifyInterview || !GlobalConstant.RECORD_STATUS_Y.equals(examIsPass) || !GlobalConstant.RECORD_STATUS_N.equals(interviewFlag) || !GlobalConstant.RECORD_STATUS_Y.equals(recordStatus)){
                                throw new RuntimeException("导入失败!！第" + (i + 1) + "行，考生面试状态有误!");
                            }else {
                                recruitInterviewInfo.setRecruitFlow(recruitFlow);
                                recruitInterviewInfo.setOrgFlow(recruitInfo.getOrgFlow());
                                recruitInterviewInfo.setDoctorFlow(recruitInfo.getDoctorFlow());
                            }
                        }else {
                            throw new RuntimeException("导入失败！第" + (i + 1) + "行，考生信息有误！");
                        }
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell1 = row.getCell(1);
                    if (cell1.getCellType() == 1){
                        recruitInterviewInfo.setInterviewStartTime(cell1.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell2 = row.getCell(2);
                    if (cell2.getCellType() == 1){
                        recruitInterviewInfo.setInterviewEndTime(cell1.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell3 = row.getCell(3);
                    if (cell3.getCellType() == 1){
                        recruitInterviewInfo.setInterviewAddress(cell2.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell4 = row.getCell(4);
                    if (cell4.getCellType() == 1){
                        recruitInterviewInfo.setInterviewDemo(cell1.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                }
                map.put(recruitFlow,recruitInterviewInfo);
            }
        }else{
            return -1;
        }
        Set<String> strings = map.keySet();
        for (String recruitFlow :strings) {
            RecruitInterviewInfo recruitInterviewInfo = map.get(recruitFlow);
            sendInterview(recruitInterviewInfo);
        }
        return row_num - 1;
    }


}
