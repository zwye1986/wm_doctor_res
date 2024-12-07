package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.recruit.IRecruitAdmitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.RecruitAdmitInfoMapper;
import com.pinde.sci.dao.base.RecruitInfoMapper;
import com.pinde.sci.dao.recruit.RecruitInfoExtMapper;
import com.pinde.core.common.enums.recruit.RecruitOperEnum;
import com.pinde.sci.model.mo.RecruitAdmitInfo;
import com.pinde.sci.model.mo.RecruitAdmitInfoExample;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoLog;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitAdmitInfoBizImpl implements IRecruitAdmitInfoBiz {

    @Autowired
    private RecruitAdmitInfoMapper recruitAdmitInfoMapper;

    @Autowired
    private RecruitInfoMapper recruitInfoMapper;

    @Autowired
    private RecruitInfoExtMapper recruitInfoExtMapper;
    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;
    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    @Override
        public Boolean isQualifyAdmit(String recruitFlow) {
        RecruitAdmitInfo recruitAdmitInfo = recruitAdmitInfoMapper.selectByPrimaryKey(recruitFlow);
        if (recruitAdmitInfo == null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean sendAdmit(RecruitAdmitInfo recruitAdmitInfo ) {

        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecruitFlow(recruitAdmitInfo.getRecruitFlow());
        recruitInfo.setAdmitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        int c=recruitInfoBiz.saveRecruitInfo(recruitInfo);
        if (c == 1){
            saveAdmitInfo(recruitAdmitInfo);
            RecruitInfoLog log=new RecruitInfoLog();
            log.setRecruitFlow(recruitAdmitInfo.getRecruitFlow());
            log.setOperTypeId(RecruitOperEnum.Admit.getId());
            log.setOperTypeName(RecruitOperEnum.Admit.getName());
            log.setAuditStatusId(RecruitOperEnum.Admit.getId());
            log.setAuditStatusName(RecruitOperEnum.Admit.getName());
            recruitInfoLogBiz.saveRecruitLog(log);
            return true;
        }else {
            return false;
        }
    }

    private void saveAdmitInfo(RecruitAdmitInfo recruitAdmitInfo) {

        RecruitAdmitInfo old=readByFlow(recruitAdmitInfo.getRecruitFlow());
        if(old==null)
        {
            GeneralMethod.setRecordInfo(recruitAdmitInfo,true);
            recruitAdmitInfoMapper.insertSelective(recruitAdmitInfo);
        }else{
            GeneralMethod.setRecordInfo(recruitAdmitInfo,false);
            recruitAdmitInfoMapper.updateByPrimaryKeySelective(recruitAdmitInfo);
        }
    }

    @Override
    public Boolean sendAdmitAll(List<RecruitInfoExt> recruitInfoExts,RecruitAdmitInfo recruitAdmitInfo ) {
        if(recruitInfoExts!=null)
        {
            for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
            {

                recruitAdmitInfo.setRecruitFlow(recruitInfoExt.getRecruitFlow());
                recruitAdmitInfo.setOrgFlow(recruitInfoExt.getOrgFlow());
                recruitAdmitInfo.setDoctorFlow(recruitInfoExt.getDoctorFlow());

                sendAdmit(recruitAdmitInfo);
            }
            return true;
        }
        return  false;
    }

    @Override
    public Integer importAdmitFromExcel(MultipartFile file, String type) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseDiscAndResponExcel(wb,type);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e.getMessage());
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }
    }

    @Override
    public RecruitAdmitInfo searchByExample(String recruitFlow, String orgFlow) {
        RecruitAdmitInfoExample example = new RecruitAdmitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecruitFlowEqualTo(recruitFlow)
                .andOrgFlowEqualTo(orgFlow);
        List<RecruitAdmitInfo> recruitInterviewInfos = recruitAdmitInfoMapper.selectByExample(example);
        if (recruitInterviewInfos != null && recruitInterviewInfos.size() > 0){
            return recruitInterviewInfos.get(0);
        }else {
            return null;
        }
    }

    @Override
    public RecruitAdmitInfo readByFlow(String recruitFlow) {
        return recruitAdmitInfoMapper.selectByPrimaryKey(recruitFlow);
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

    private int parseDiscAndResponExcel(Workbook wb, String type) {
        Sheet sheet = wb.getSheetAt(0);
        List<String> colnames = new ArrayList<String>();
        Map<String,RecruitAdmitInfo> map = new HashMap<String,RecruitAdmitInfo>();
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
                RecruitAdmitInfo recruitAdmitInfo = new RecruitAdmitInfo();
                if (row != null){
                    Cell cell = row.getCell(0);
                    if (cell.getCellType().getCode() == 1){
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

                            String interviewIsPass = recruitInfo.getInterviewIsPass();
                            String admitFlag = recruitInfo.getAdmitFlag();
                            String recordStatus = recruitInfo.getRecordStatus();
                            Boolean qualifyInterview = isQualifyAdmit(recruitFlow);
                            if (!qualifyInterview || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(interviewIsPass) || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(admitFlag) || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(recordStatus)) {
                                throw new RuntimeException("导入失败!！第" + (i + 1) + "行，考生招录状态有误!");
                            }else {
                                recruitAdmitInfo.setRecruitFlow(recruitFlow);
                                recruitAdmitInfo.setOrgFlow(recruitInfo.getOrgFlow());
                                recruitAdmitInfo.setDoctorFlow(recruitInfo.getDoctorFlow());
                            }
                        }else {
                            throw new RuntimeException("导入失败！第" + (i + 1) + "行，考生信息有误！");
                        }
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell1 = row.getCell(1);
                    if (cell1.getCellType().getCode() == 1){
                        recruitAdmitInfo.setAdmitTime(cell1.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell2 = row.getCell(2);
                    if (cell2.getCellType().getCode() == 1){
                        recruitAdmitInfo.setAdmitAddress(cell2.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                    Cell cell3 = row.getCell(3);
                    if (cell3.getCellType().getCode() == 1){
                        recruitAdmitInfo.setAdmitDemo(cell1.getStringCellValue());
                    }else {
                        throw new RuntimeException("导入失败！第" + (i + 1) + "行，请以文本格式导入！");
                    }
                }
                map.put(recruitFlow,recruitAdmitInfo);
            }
        }else{
            return -1;
        }
        Set<String> strings = map.keySet();
        for (String recruitFlow :strings) {
            RecruitAdmitInfo recruitAdmitInfo = map.get(recruitFlow);
            sendAdmit(recruitAdmitInfo);
        }
        return row_num - 1;
    }

    private static Logger logger = LoggerFactory.getLogger(RecruitAdmitInfoBizImpl.class);


}
