package com.pinde.sci.biz.lcjn.impl;

import com.pinde.core.common.enums.LcjnAuditStatusEnum;
import com.pinde.core.common.enums.LcjnDoctorScoreEnum;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnDoctorOrderInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.LcjnCourseInfoMapper;
import com.pinde.sci.dao.base.LcjnCourseTimeMapper;
import com.pinde.sci.dao.base.LcjnDoctorCourseMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.lcjn.LcjnDoctorTrainExtMapper;
import com.pinde.sci.model.mo.*;
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
public class LcjnDoctorOrderInfoBizImpl implements ILcjnDoctorOrderInfoBiz{

    @Autowired
    private LcjnDoctorTrainExtMapper lcjnDoctorTrainExtMapper;
    @Autowired
    private LcjnDoctorCourseMapper lcjnDoctorCourseMapper;
    @Autowired
    private LcjnCourseTimeMapper lcjnCourseTimeMapper;
    @Autowired
    private LcjnCourseInfoMapper lcjnCourseInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<Map<String, Object>> selectDoctorOrderInfoList(Map<String, String> map) {
        return lcjnDoctorTrainExtMapper.queryDoctorOrderInfoList(map);
    }

    @Override
    public int batchAuditInfo(String recordFlow, String auditStatusId, String reason) {
        LcjnDoctorCourse lcjnDoctorCourse=new LcjnDoctorCourse();
        lcjnDoctorCourse.setRecordFlow(recordFlow);
        lcjnDoctorCourse.setAuditStatusId(auditStatusId);
        lcjnDoctorCourse.setAuditStatusName(LcjnAuditStatusEnum.getNameById(auditStatusId));
        if(StringUtil.isNotBlank(reason)){
            lcjnDoctorCourse.setReason(reason);
        }
        return lcjnDoctorCourseMapper.updateByPrimaryKeySelective(lcjnDoctorCourse);
    }

    @Override
    public List<Map<String, Object>> selectLocalDoctors(String orgFlow) {
        Map<String, String> map=new HashMap<>();
        map.put("orgFlow",orgFlow);
        return lcjnDoctorTrainExtMapper.queryLocalDoctors(map);
    }

    @Override
    public int countOrderNum(String courseFlow) {
        int count=0;
        List<String> auditStatus=new ArrayList<>();
        auditStatus.add(LcjnAuditStatusEnum.Passed.getId());
        auditStatus.add(LcjnAuditStatusEnum.Passing.getId());
        LcjnDoctorCourseExample example=new LcjnDoctorCourseExample();
        LcjnDoctorCourseExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow)
                .andAuditStatusIdIn(auditStatus);
        int num=lcjnDoctorCourseMapper.countByExample(example);
        LcjnCourseInfo lcjnCourseInfo=lcjnCourseInfoMapper.selectByPrimaryKey(courseFlow);
        if(lcjnCourseInfo!=null){
            count=Integer.parseInt(lcjnCourseInfo.getCoursePeopleNum())-num;
        }
        return count;
    }

    @Override
    public int countOrderTime(String doctorFlow, String courseFlow) {
        Map<String, String> map=new HashMap<>();
        map.put("doctorFlow",doctorFlow);
        map.put("courseFlow",courseFlow);
        return lcjnDoctorTrainExtMapper.countOrderTime(map);
    }

    @Override
    public List<String> selectOrderAndPassing(String doctorFlow, String courseFlow, String trainStartTime, String trainEndTime) {
        Map<String, String> map=new HashMap<>();
        map.put("doctorFlow",doctorFlow);
        map.put("courseFlow",courseFlow);
        map.put("trainStartTime",trainStartTime);
        map.put("trainEndTime",trainEndTime);
        return lcjnDoctorTrainExtMapper.queryOrderTimeAndPassing(map);
    }

    @Override
    public List<LcjnCourseTime> selectCourseTime(String courseFlow) {
        LcjnCourseTimeExample example=new LcjnCourseTimeExample();
        LcjnCourseTimeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
        return lcjnCourseTimeMapper.selectByExample(example);
    }

    @Override
    public int updateDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse,List<String> courseFlows) {
        LcjnDoctorCourseExample example = new LcjnDoctorCourseExample();
        LcjnDoctorCourseExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(lcjnDoctorCourse.getDoctorFlow());
        if(StringUtil.isNotBlank(lcjnDoctorCourse.getCourseFlow())){
            criteria.andCourseFlowEqualTo(lcjnDoctorCourse.getCourseFlow());
        }
        if(courseFlows!=null&&courseFlows.size()>0){
            criteria.andCourseFlowIn(courseFlows);
        }
        return lcjnDoctorCourseMapper.updateByExampleSelective(lcjnDoctorCourse,example);
    }

    @Override
    public List<LcjnDoctorCourse> selectDoctorCourse(String courseFlow, String doctorFlow) {
        LcjnDoctorCourseExample example=new LcjnDoctorCourseExample();
        LcjnDoctorCourseExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(doctorFlow);
        return lcjnDoctorCourseMapper.selectByExample(example);
    }

    @Override
    public void addDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse) {
        lcjnDoctorCourseMapper.insertSelective(lcjnDoctorCourse);
    }

    @Override
    public List<Map<String, Object>> selectDoctorScoreList(String courseFlow, String doctorName, String enteringStatusId,String doctorFlow) {
        Map<String, String> map=new HashMap<>();
        map.put("courseFlow",courseFlow);
        map.put("userName",doctorName);
        map.put("enteringStatusId",enteringStatusId);
        map.put("doctorFlow",doctorFlow);
        return lcjnDoctorTrainExtMapper.queryDoctorScoreList(map);
    }

    @Override
    public int editDoctorCourse(LcjnDoctorCourse lcjnDoctorCourse) {
        return lcjnDoctorCourseMapper.updateByPrimaryKeySelective(lcjnDoctorCourse);
    }

    @Override
    public int editCourseInfo(LcjnCourseInfo lcjnCourseInfo) {
        return lcjnCourseInfoMapper.updateByPrimaryKeySelective(lcjnCourseInfo);
    }

    @Override
    public void importScoreFromExcel(MultipartFile file,String courseFlow) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            parseExcel(wb,courseFlow);
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
    private void parseExcel(Workbook wb,String courseFlow) {

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
                    colnames.add("成绩");
                } else {
                    String userFlow="";
                    SysUser user=new SysUser();
                    LcjnDoctorCourse doctorCourse=new LcjnDoctorCourse();
                    boolean flag = false;
                    for (int j = 0; j < cell_num; j++) {
                        try{
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
                                if (value == null|| value.trim().length() == 0) {
                                    flag = true;
                                    break;
                                }
                                user.setUserCode(value);
                            } else if ("姓名".equals(colnames.get(j))) {
                                if (value == null|| value.trim().length() == 0) {
                                    flag = true;
                                    break;
                                }
                                user.setUserName(value);
                            }else if ("成绩".equals(colnames.get(j))) {
                                doctorCourse.setExamScore(value);
                            }
                        }catch (IndexOutOfBoundsException e){
                            throw new RuntimeException("导入失败！导入模板有误!");
                        }
                    }
                    if (flag) {
                        throw new RuntimeException("用户名/姓名不能为空!");
                    }
                    if(StringUtil.isNotBlank(user.getUserCode())&&StringUtil.isNotBlank(user.getUserName())){
                        SysUserExample example=new SysUserExample();
                        SysUserExample.Criteria criteria=example.createCriteria();
                        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserCodeEqualTo(user.getUserCode()).andUserNameEqualTo(user.getUserName());
                        List<SysUser> userList=sysUserMapper.selectByExample(example);
                        if(userList!=null&&userList.size()>0&&userList.get(0)!=null){
                            userFlow=userList.get(0).getUserFlow();
                            if(StringUtil.isNotBlank(doctorCourse.getExamScore())&&StringUtil.isNotBlank(doctorCourse.getExamScore().trim())){
                                String examScore=doctorCourse.getExamScore();
                                doctorCourse.setDoctorFlow(userFlow);
                                doctorCourse.setDoctorName(userList.get(0).getUserName());
                                doctorCourse.setCourseFlow(courseFlow);
                                LcjnDoctorCourseExample example1=new LcjnDoctorCourseExample();
                                LcjnDoctorCourseExample.Criteria criteria1=example1.createCriteria();
                                criteria1.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow).andDoctorFlowEqualTo(userFlow);
                                List<LcjnDoctorCourse> courseList=lcjnDoctorCourseMapper.selectByExample(example1);
                                if(courseList!=null&&courseList.size()>0&&courseList.get(0)!=null){
                                    doctorCourse=courseList.get(0);
                                    doctorCourse.setExamScore(examScore);
                                    doctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.HasBeenEntered.getId());
                                    doctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.HasBeenEntered.getName());
                                    GeneralMethod.setRecordInfo(doctorCourse, false);
                                    lcjnDoctorCourseMapper.updateByPrimaryKeySelective(doctorCourse);
                                }else{
                                    throw new RuntimeException("导入失败！用户名:"+user.getUserCode()+"/姓名:"+user.getUserName()+"尚未预约此次培训！");
                                }
                            }else {
                                throw new RuntimeException("导入失败！用户名:"+user.getUserCode()+"/姓名:"+user.getUserName()+"成绩不能为空!");
                            }
                        }else{
                            throw new RuntimeException("导入失败！ 用户名:"+user.getUserCode()+"/姓名:"+user.getUserName()+"不存在!");
                        }
                    }
                    //查询全部以验证

                }
            }
        }
    }
}
