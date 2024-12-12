package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.enums.BaseStatusEnum;
import com.pinde.core.common.sci.dao.SysOrgMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgSpeAssignBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.model.jsres.OrgSpeListVo;
import com.pinde.sci.model.mo.JsresSign;
import com.pinde.sci.model.mo.JsresSignExample;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitExample;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResOrgSpeAssign;
import com.pinde.sci.model.mo.ResOrgSpeAssignExample;
import com.pinde.sci.model.mo.ResOrgSpeExample;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
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
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResOrgSpeAssignBizImpl implements IResOrgSpeAssignBiz {
    @Autowired
    private ResOrgSpeAssignMapper speAssignMapper;
    @Autowired
    private ResOrgSpeMapper speMapper;
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ResDoctorRecruitMapper recruitMapper;
    @Autowired
    private ResDoctorRecruitExtMapper recruitExtMapper;
    @Autowired
    private ResDoctorMapper resDoctorMapper;
    @Autowired
    private JsresSignMapper signMapper;
    @Autowired
    private JsresDoctorSpeMapper doctorSpeMapper;

    private static Logger logger = LoggerFactory.getLogger(ResOrgSpeAssignBizImpl.class);


    @Override
    public List<ResOrgSpeAssign> searchSpeAssign(String assignYear) {
        ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
        ResOrgSpeAssignExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(assignYear)) {
            criteria.andAssignYearEqualTo(assignYear);
        }
        return speAssignMapper.selectByExample(example);
    }

//	@Override
//	public int editSpeAssign(ResOrgSpeAssign speAssign){
//		if(speAssign!=null){
//			if(StringUtil.isNotBlank(speAssign.getRecordFlow())){
//				GeneralMethod.setRecordInfo(speAssign, false);
//				return speAssignMapper.updateByPrimaryKeySelective(speAssign);
//			}else{
//				speAssign.setRecordFlow(PkUtil.getUUID());
//				GeneralMethod.setRecordInfo(speAssign, true);
//				return speAssignMapper.insertSelective(speAssign);
//			}
//		}
//		return com.pinde.core.common.GlobalConstant.ZERO_LINE;
//	}

    @Override
    public int editSpeAssignUnSelective(ResOrgSpeAssign speAssign) {
        speAssign.setRecordFlow(null);
        if (speAssign != null) {
            String assignYear = InitConfig.getSysCfg("res_reg_year");
            String orgFlow = speAssign.getOrgFlow();
            String speId = speAssign.getSpeId();
            ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowEqualTo(orgFlow)
                    .andSpeIdEqualTo(speId)
                    .andAssignYearEqualTo(assignYear);
            int count = this.speAssignMapper.countByExample(example);
            if (count == 0) {
                speAssign.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(speAssign, true);
                return speAssignMapper.insert(speAssign);
            } else {
                GeneralMethod.setRecordInfo(speAssign, false);
                return speAssignMapper.updateByExampleSelective(speAssign, example);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public String queryOrgSpePlanFlow(ResOrgSpeAssign speAssign) {
        if (speAssign != null) {
            ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andOrgFlowEqualTo(speAssign.getOrgFlow())
                    .andSpeIdEqualTo(speAssign.getSpeId())
                    .andAssignYearEqualTo(speAssign.getAssignYear());
            int count = this.speAssignMapper.countByExample(example);
            if (count == 0) {
                String recordFlow = PkUtil.getUUID();
                speAssign.setRecordFlow(recordFlow);
                GeneralMethod.setRecordInfo(speAssign, true);
                speAssignMapper.insert(speAssign);
                return recordFlow;
            } else {
                GeneralMethod.setRecordInfo(speAssign, false);
                speAssignMapper.updateByExampleSelective(speAssign, example);
                return speAssign.getRecordFlow();
            }
        }
        return null;
    }

    @Override
    public List<ResOrgSpeAssign> searchSpeAssign(String orgFlow,
                                                 String assignYear) {
        ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow).andAssignYearEqualTo(assignYear);
        return this.speAssignMapper.selectByExample(example);
    }

    public ResOrgSpeAssign searchSpeAssign(String orgFlow, String assignYear, String speId) {
        ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow)
                .andAssignYearEqualTo(assignYear)
                .andSpeIdEqualTo(speId);
        List<ResOrgSpeAssign> spes = speAssignMapper.selectByExample(example);
        if (spes.size() == 1) {
            return spes.get(0);
        }
        return null;
    }

    @Override
    public ResOrgSpeAssign findSpeAssignByFlow(String flow) {
        return this.speAssignMapper.selectByPrimaryKey(flow);
    }

    @Override
    public List<ResOrgSpeAssign> searchSpeAssignBySpeIdAndYear(String speId, String year) {
        ResOrgSpeAssignExample example = new ResOrgSpeAssignExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSpeIdEqualTo(speId)
                .andAssignYearEqualTo(year);
        return this.speAssignMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> importExcel(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        byte[] fileData = new byte[(int) file.getSize()];
        is.read(fileData);
        Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
        return parseExcel(wb);
    }

    private static Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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

    private Map<String, Object> parseExcel(Workbook wb) {
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
            if (row_num < 1) {
                throw new RuntimeException("没有数据！");
            }
            Row titleR = sheet.getRow(0);//获取表头
            int cell_num = titleR.getLastCellNum();//获取表头单元格数
            List<String> colnames = new ArrayList<>();
            for (int i = 0; i < cell_num; i++) {
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            String p1 = "基地编号和名称不能为空！";
            String p2 = "该基地不存在！";
            int succCount = 0, failCount = 0;//导入成功，失败记录数
            List<Integer> lineList = new ArrayList<Integer>();//失败记录行号
            Map<Integer, String> problemsMap = new HashMap<>();//key：行号，val：失败原因
            Map<String, Object> returnDataMap = new HashMap<String, Object>();//返回信息
            //数据行开始遍历
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                String value = "";
                String orgCode = "";
                String orgName = "";
                String flag = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
                ResOrgSpeAssign speAssign = new ResOrgSpeAssign();
                for (int j = 0; j < 2; j++) {//处理基地信息
                    Cell cell = r.getCell(j);
                    if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (r.getCell((short) j).getCellType().getCode() == 1) {
                        value = r.getCell((short) j).getStringCellValue();
                    } else {
                        value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                    }
                    if ("基地编号".equals(colnames.get(j))) {
                        orgCode = value;
                    } else if ("基地名称".equals(colnames.get(j))) {
                        orgName = value;
                    }
                }
                if (StringUtil.isBlank(orgCode) || StringUtil.isBlank(orgName)) {
                    flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                    problemsMap.put(i + 1, p1);
                } else {
                    SysOrgExample example = new SysOrgExample();
                    example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                            .andOrgCodeEqualTo(orgCode).andOrgNameEqualTo(orgName);
                    List<SysOrg> orgList = orgMapper.selectByExample(example);
                    if (null != orgList && orgList.size() > 0) {
                        speAssign.setOrgFlow(orgList.get(0).getOrgFlow());//存在该基地，复制基地流水号
                    } else {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, p2);
                    }
                }
                speAssign.setAssignYear(InitConfig.getSysCfg("res_reg_year"));
                if (StringUtil.isNotBlank(speAssign.getOrgFlow())) {//处理基地各专业信息
                    int total = 0;
                    for (int j = 2; j < colnames.size(); j++) {
                        Cell cell = r.getCell(j);
                        if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                            continue;
                        }
                        if (r.getCell((short) j).getCellType().getCode() == 1) {
                            value = r.getCell((short) j).getStringCellValue();
                        } else {
                            value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                        }
                        Map<String, String> speMap = InitConfig.getDictNameMap("DoctorTrainingSpe");//key：专业name，val：专业id
                        for (String key : speMap.keySet()) {
                            if (key.equals(colnames.get(j))) {
                                speAssign.setSpeId(speMap.get(key));
                                speAssign.setSpeName(key);
                                speAssign.setAssignPlan(new BigDecimal(value));
                                total += editSpeAssignUnSelective(speAssign);//导入计划招录人数
                                break;
                            }
                        }
                    }
                    if (total > 0) {
                        succCount++;
                    }
                }
                if (com.pinde.core.common.GlobalConstant.UPLOAD_FAIL.equals(flag)) {
                    failCount++;//失败的数
                    lineList.add(i + 1);
                    continue;
                }
            }
            returnDataMap.put("succCount", succCount);
            returnDataMap.put("failCount", failCount);
            returnDataMap.put("lineList", lineList);
            returnDataMap.put("problemsMap", problemsMap);
            return returnDataMap;
        }
        return null;
    }

    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D) {
            return String.valueOf((long) d);
        } else {
            return String.valueOf(d);
        }
    }

    @Override
    public Map<String, Object> impOperExcel(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        byte[] fileData = new byte[(int) file.getSize()];
        is.read(fileData);
        Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
        return parseOperExcel(wb);
    }

    @Override
    public Map<String, Object> impInterviewExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseOperExcel(wb);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("", e);
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public Map<String, Object> impSkillExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseOperExcel(wb);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("", e);
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public Map<String, Object> impGradeExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseOperExcel(wb);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("", e);
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private Map<String, Object> parseOperExcel(Workbook wb) {
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
            if (row_num < 1) {
                throw new RuntimeException("没有数据！");
            }
            Row titleR = sheet.getRow(0);//获取表头
            int cell_num = titleR.getLastCellNum();//获取表头单元格数
            List<String> colnames = new ArrayList<>();
            for (int i = 0; i < cell_num; i++) {
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            String p1 = "姓名和身份证号不能为空！";
            String p2 = "该学员不存在！";
            String p3 = "专业基地名称不能为空！";
            String p5 = "该学员无该专业志愿记录，请检查信息是否正确！";
            String p4 = "面试和操作成绩不能为空！";
            String p6 = "该学员招录状态为已录取，不可修改成绩！";

            String examResultFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
            String interviewExam = com.pinde.core.common.GlobalConstant.FLAG_N;
            String skillExam = com.pinde.core.common.GlobalConstant.FLAG_N;
            int succCount = 0, failCount = 0;//导入成功，失败记录数
            List<Integer> lineList = new ArrayList<Integer>();//失败记录行号
            Map<Integer, String> problemsMap = new HashMap<>();//key：行号，val：失败原因
            Map<String, Object> returnDataMap = new HashMap<String, Object>();//返回信息
            //数据行开始遍历
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                if (r == null) {
                    throw new RuntimeException("上传文件有误，请上传正确的文件");
                }
                String value = "";
                String userName = "";
                String idNo = "";
                String flag = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
                ResDoctorRecruitWithBLOBs record = new ResDoctorRecruitWithBLOBs();
                ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
                recruitExample.setOrderByClause("CREATE_TIME DESC");
                ResDoctorRecruitExample.Criteria criteria = recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSessionNumberEqualTo(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))
                        .andDoctorStatusIdNotEqualTo("NotPassed");
                for (int j = 0; j < 3; j++) {//处理学员信息
                    Cell cell = r.getCell(j);
                    if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (r.getCell((short) j).getCellType().getCode() == 1) {
                        value = r.getCell((short) j).getStringCellValue();
                    } else {
                        value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                    }
                    if ("姓名".equals(colnames.get(j))) {
                        userName = value;
                    } else if ("身份证号".equals(colnames.get(j))) {
                        idNo = value;
                    }
                }
                if (StringUtil.isBlank(userName) || StringUtil.isBlank(idNo)) {
                    flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                    problemsMap.put(i + 1, p1);
                } else {
                    SysUserExample example = new SysUserExample();
                    example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                            .andUserNameEqualTo(userName).andIdNoEqualTo(idNo);
                    List<SysUser> userList = userMapper.selectByExample(example);
                    if (null != userList && userList.size() > 0) {
                        criteria.andDoctorFlowEqualTo(userList.get(0).getUserFlow());//存在该学员，复制学员流水号
                    } else {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, p2);
                    }
                }
                String speName = "";
                if (flag.equals(com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED)) {
                    criteria.andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
                    for (int j = 2; j < colnames.size(); j++) {
                        Cell cell = r.getCell(j);
                        if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                            continue;
                        }
                        if (r.getCell((short) j).getCellType().getCode() == 1) {
                            value = r.getCell((short) j).getStringCellValue();
                        } else {
                            value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                        }
                        if ("培训专业".equals(colnames.get(j))) {
                            speName = value;
                            criteria.andSpeNameEqualTo(value);
                        } else if ("笔试成绩".equals(colnames.get(j))) {
                            examResultFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            if (isNumericzidai(value)) {
                                record.setExamResult(new BigDecimal(value));
                            }
                            record.setExamStatusId(BaseStatusEnum.Auditing.getId());
                            record.setExamStatusName(BaseStatusEnum.Auditing.getName());
                        } else if ("面试成绩".equals(colnames.get(j))) {
                            interviewExam = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            if (isNumericzidai(value)) {
                                record.setAuditionResult(new BigDecimal(value));
                            }
                            record.setAuditionStatusId(BaseStatusEnum.Auditing.getId());
                            record.setAuditionStatusName(BaseStatusEnum.Auditing.getName());
                        } else if ("操作成绩".equals(colnames.get(j))) {
                            skillExam = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            if (isNumericzidai(value)) {
                                record.setOperResult(new BigDecimal(value));
                            }
                            record.setOperStatusId(BaseStatusEnum.Auditing.getId());
                            record.setOperStatusName(BaseStatusEnum.Auditing.getName());
                        }
                    }
                    if (StringUtil.isBlank(speName)) {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, p3);
                    } else if (record.getExamResult() == null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(examResultFlag)) {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, "笔试成绩数值错误");
                    } else if (record.getAuditionResult() == null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(interviewExam)) {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, "面试成绩数值错误");
                    } else if (record.getOperResult() == null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillExam)) {
                        flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i + 1, "操作成绩数值错误");
                    } else {
                        List<ResDoctorRecruit> recruitList = recruitMapper.selectByExample(recruitExample);
                        if (null != recruitList && recruitList.size() > 0) {
                            ResDoctorRecruit recruitTemp = recruitList.get(0);

                            BigDecimal examResult = recruitTemp.getExamResult();
                            BigDecimal auditionResult = recruitTemp.getAuditionResult();
                            BigDecimal operResult = recruitTemp.getOperResult();
                            BigDecimal totalResult = new BigDecimal(0);

                            //计算总分=笔试分 + 面试分 + 操作技能分
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(examResultFlag)) {
                                totalResult = totalResult.add(record.getExamResult());
                            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(interviewExam)) {
                                totalResult = totalResult.add(record.getAuditionResult());
                            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillExam)) {
                                totalResult = totalResult.add(record.getOperResult());
                            }
                            if (null != examResult && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(examResultFlag)) {
                                totalResult = totalResult.add(examResult);
                            }
                            if (null != auditionResult && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(interviewExam)) {
                                totalResult = totalResult.add(auditionResult);
                            }
                            if (null != operResult && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillExam)) {
                                totalResult = totalResult.add(operResult);
                            }
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitTemp.getRecruitFlag())) {//该学员已录取，不可修改成绩
                                flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                                problemsMap.put(i + 1, p6);
                            } else {
                                record.setTotleResult(totalResult);
                                record.setRecruitFlow(recruitList.get(0).getRecruitFlow());
                                succCount += recruitMapper.updateByPrimaryKeySelective(record);
                            }
                        } else {
                            flag = com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                            problemsMap.put(i + 1, p5);
                        }
                    }
                }
                if (com.pinde.core.common.GlobalConstant.UPLOAD_FAIL.equals(flag)) {
                    failCount++;//失败的数
                    lineList.add(i + 1);
                    continue;
                }
            }
            returnDataMap.put("succCount", succCount);
            returnDataMap.put("failCount", failCount);
            returnDataMap.put("lineList", lineList);
            returnDataMap.put("problemsMap", problemsMap);
            return returnDataMap;
        }
        return null;
    }

    private boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getRecruitInfo(Map<String, Object> paramMap) {
        return recruitExtMapper.getHBRecruitInfo(paramMap);
    }

    @Override
    public List<Map<String, Object>> getJSRecruitInfo(Map<String, Object> paramMap) {
        return recruitExtMapper.getJSRecruitInfo(paramMap);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description 查询基地招录计划信息
     * @Author fengxf
     * @Date 2020/6/5
     */
    @Override
    public List<Map<String, Object>> searchAssignInfoList(Map<String, Object> param) {
        return recruitExtMapper.searchAssignInfoList(param);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public List<Map<String, String>> searchOrgInfoList(Map<String, String> param) {
        return recruitExtMapper.searchOrgInfoList(param);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description 查询基地下的住院医师和助理全科的专业信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public List<Map<String, String>> searchAssignOrgSpeList(Map<String, String> param) {
        return recruitExtMapper.searchAssignOrgSpeList(param);
    }

    @Override
    public List<Map<String, String>> searchAssignOrgSpeListNew(Map<String, String> param) {
        return recruitExtMapper.searchAssignOrgSpeListNew(param);
    }

    @Override
    public ResOrgSpe findOrgSpe(String orgFlow, String assignYear, String speId) {
        String[] status = {"open", "stop"};
        ResOrgSpeExample orgSpeExample = new ResOrgSpeExample();
        orgSpeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSessionYearEqualTo(assignYear).andStatusIn(Arrays.asList(status)).andOrgFlowEqualTo(orgFlow).andSpeIdEqualTo(speId);
        List<ResOrgSpe> orgSpeList = speMapper.selectByExample(orgSpeExample);
        if (CollectionUtils.isNotEmpty(orgSpeList)) {
            return orgSpeList.get(0);
        }
        return null;
    }

    @Override
    public List<ResOrgSpe> findOrgSpeByExample(ResOrgSpeExample example) {
        return speMapper.selectByExample(example);
    }

    @Override
    public List<OrgSpeListVo> searchAssignOrgSpeList2(String orgFlow, String assignYear) {
        return recruitExtMapper.searchAssignOrgSpeList2(orgFlow, assignYear);
    }


    /**
     * @param param
     * @Department：研发部
     * @Description 保存基地专业信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public int insertOrgSpeAssign(Map<String, String> param) {
        SysUser currUser = GlobalContext.getCurrentUser();
        // 清空当前基地下招生年份为空的招生计划信息再插入
        param.put("modifyUserFlow", currUser.getUserFlow());
        param.put("modifyTime", DateUtil.getCurrDateTime());
        param.put("recordStatus", com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        recruitExtMapper.delOrgSpeAssign(param);
        param.put("createUserFlow", currUser.getUserFlow());
        param.put("createTime", DateUtil.getCurrDateTime());
        param.put("recordStatus", com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return recruitExtMapper.insertOrgSpeAssign(param);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description 保存招生计划专业简介信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public String updateAssignSpeDesc(Map<String, String> param) {
        SysUser currUser = GlobalContext.getCurrentUser();
        param.put("modifyUserFlow", currUser.getUserFlow());
        param.put("modifyTime", DateUtil.getCurrDateTime());
        int count = recruitExtMapper.updateAssignSpeDesc(param);
        if (count == 0) {
            return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
    }

    /**
     * @param assignFlow
     * @Department：研发部
     * @Description 查询招生计划信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public ResOrgSpeAssign getResOrgSpeAssignInfo(String assignFlow) {
        return speAssignMapper.selectByPrimaryKey(assignFlow);
    }

    /**
     * @param param
     * @Department：研发部
     * @Description 批量更新招生计划信息
     * @Author fengxf
     * @Date 2020/6/6
     */
    @Override
    public String updateAssignInfo(Map<String, Object> param) {
        int count = 0;
        // 查询当前年份的基地招生计划是否已存在 assignYearEdit 不为空表示编辑页面 不用查询是否已存在
        if (StringUtil.isBlank(String.valueOf(param.get("assignYearEdit")))) {
            count = recruitExtMapper.countAssignInfoByParam(param);
            if (count > 0) {
                return String.valueOf(param.get("assignYear")) + "年的报送计划已存在";
            }
        }
        SysUser currUser = GlobalContext.getCurrentUser();
//		param.put("modifyUserFlow", currUser.getUserFlow());
//		param.put("modifyTime", DateUtil.getCurrDateTime());
        for (Map<String, String> map : (List<Map>) param.get("assignList")) {
            map.put("modifyUserFlow", currUser.getUserFlow());
            map.put("modifyTime", DateUtil.getCurrDateTime());
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals((String) param.get("isJointOrg"))) {
                map.put("auditStatusId", "Passed");
                map.put("auditStatusName", "审核通过");
            } else if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals((String) param.get("isJointOrg"))) {
                map.put("auditStatusId", "Passing");
                map.put("auditStatusName", "待审核");
            }
            map.put("isShown", com.pinde.core.common.GlobalConstant.FLAG_Y);
            recruitExtMapper.updateAssignInfo(map);
            count++;
        }
        if (count == 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * @param param
     * @param file
     * @Department：研发部
     * @Description 基地导入招生计划
     * @Author fengxf
     * @Date 2020/6/7
     */
    @Override
    public int importAssignInfo(Map<String, String> param, MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        byte[] fileData = new byte[(int) file.getSize()];
        is.read(fileData);
        Workbook wb = createAssignWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
        return parseExcelToAssign(wb, param);
    }

    /**
     * @Department：研发部
     * @Description 判断学员是否可以报名
     * @Author fengxf
     * @Date 2020/6/7
     */
    @Override
    public String doctorSignupFlag() {
        SysUser sysUser = GlobalContext.getCurrentUser();
        // 查询医师信息
        ResDoctor resDoctor = resDoctorMapper.selectByPrimaryKey(sysUser.getUserFlow());
        if (null == resDoctor) {
            return "请先完善报考信息";
        }
//		if(StringUtil.isBlank(resDoctor.getDoctorStatusId())){
//			return "请先完善报考信息";
//		}
        // 查询招录信息
        List<ResDoctorRecruit> doctorRecruitList = recruitExtMapper.getDoctorRecruitInfo(sysUser.getUserFlow());
        if (null != doctorRecruitList && 0 < doctorRecruitList.size()) {
            ResDoctorRecruit doctorRecruit = doctorRecruitList.get(0);
            // 招录表 待审核 或 招录表医师状态审核通过    审核通过且 医师表 医师状态为在培的
            if ("Auditing".equals(doctorRecruit.getDoctorStatusId()) || "OrgAuditing".equals(doctorRecruit.getDoctorStatusId())
                    || "WaitGlobalPass".equals(doctorRecruit.getDoctorStatusId()) || ("Passed".equals(doctorRecruit.getDoctorStatusId()))
                    || ("Passed".equals(doctorRecruit.getAuditStatusId()) && "20".equals(resDoctor.getDoctorStatusId()))) {
                return "当前在" + doctorRecruit.getOrgName() + "已有报考记录";
            }
        }
        return "";
    }

    /**
     * @Department：研发部
     * @Description 判断学员是否可以报名
     * @Author fengxf
     * @Date 2020/6/7
     */
    @Override
    public String doctorSignupFlag2() {
        SysUser sysUser = GlobalContext.getCurrentUser();
        // 查询医师信息
        ResDoctor resDoctor = resDoctorMapper.selectByPrimaryKey(sysUser.getUserFlow());
        if (null == resDoctor) {
            return "请先完善报考信息";
        }
//		if(StringUtil.isBlank(resDoctor.getDoctorStatusId())){
//			return "请先完善报考信息";
//		}
        // 查询招录信息
        List<ResDoctorRecruit> doctorRecruitList = recruitExtMapper.getDoctorRecruitInfo(sysUser.getUserFlow());
        if (null != doctorRecruitList && 0 < doctorRecruitList.size()) {
            ResDoctorRecruit doctorRecruit = doctorRecruitList.get(0);
            // 招录表 待审核 或 招录表医师状态审核通过    审核通过且 医师表 医师状态为在培的
            if ("Auditing".equals(doctorRecruit.getDoctorStatusId()) || ("Passed".equals(doctorRecruit.getDoctorStatusId()))
                    || ("Passed".equals(doctorRecruit.getAuditStatusId()) && "20".equals(resDoctor.getDoctorStatusId()))) {
                return "当前在" + doctorRecruit.getOrgName() + "已有报考记录";
            }
        }
        return "";
    }

    @Override
    public String updateAssignAudit(Map<String, Object> param) {
        int result = recruitExtMapper.updateAssignAudit(param);
        if (result > 0) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
    }

    @Override
    public int updateOrgSpeByFlow(Map<String, Object> param) {
        return recruitExtMapper.updateOrgSpeByFlow(param);
    }

    @Override
    public List<Map<String, Object>> searchAssignInfoListNew(Map<String, Object> paramMap) {
        return recruitExtMapper.searchAssignInfoListNew(paramMap);
    }

    @Override
    public List<JsresDoctorSpe> searchDoctorSpe() {
        JsresDoctorSpeExample example = new JsresDoctorSpeExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return doctorSpeMapper.selectByExample(example);
    }

    @Override
    public List<JsresSign> searchSignListByParam(Map<String, Object> param) {
        JsresSignExample example = new JsresSignExample();
        JsresSignExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdNotEqualTo("NotUse");
        if (StringUtil.isNotBlank((String) param.get("orgFlow"))) {
            criteria.andOrgFlowEqualTo((String) param.get("orgFlow"));
        }
        if (StringUtil.isNotBlank((String) param.get("auditStatusId"))) {
            criteria.andAuditStatusIdEqualTo((String) param.get("auditStatusId"));
        }
        return signMapper.selectByExample(example);
    }

    @Override
    public List<JsresSign> searchSignListByOrgFlow(String orgFlow, String useStatusId) {
        JsresSignExample example = new JsresSignExample();
        JsresSignExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(useStatusId)) {
            criteria.andUseStatusIdEqualTo(useStatusId);
        }
        return signMapper.selectByExample(example);
    }

    @Override
    public List<JsresSign> searchSignListByOrgFlowNew(String orgFlow, String useStatusId, String sessionNumber) {
        JsresSignExample example = new JsresSignExample();
        JsresSignExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(useStatusId)) {
            criteria.andUseStatusIdEqualTo(useStatusId);
        }
        if (StringUtil.isNotBlank(sessionNumber)) {
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        return signMapper.selectByExample(example);
    }


    @Override
    public String saveJsresSign(JsresSign sign) {
        if (StringUtil.isBlank(sign.getSignFlow())) {
            sign.setSignFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(sign, true);
            int count = signMapper.insert(sign);
            if (count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        } else {
            GeneralMethod.setRecordInfo(sign, false);
            int count = signMapper.updateByPrimaryKeySelective(sign);
            if (count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @Override
    public List<JsresSign> searchSignList(JsresSign sign) {
        JsresSignExample example = new JsresSignExample();
        JsresSignExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(sign.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(sign.getOrgFlow());
        }
        if (StringUtil.isNotBlank(sign.getAuditStatusId())) {
            criteria.andAuditStatusIdEqualTo(sign.getAuditStatusId());
        }
        return signMapper.selectByExample(example);
    }

    @Override
    public JsresSign searchSignByPrimaryKey(String signFlow) {
        return signMapper.selectByPrimaryKey(signFlow);
    }

    @Override
    public List<JsresSign> searchSignListUnPassed(String orgFlow) {
        JsresSignExample example = new JsresSignExample();
        JsresSignExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdNotEqualTo("UnPassed");
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        return signMapper.selectByExample(example);
    }

    @Override
    public String doctorSignupFlagNew() {
        SysUser sysUser = GlobalContext.getCurrentUser();
        // 查询医师信息
        ResDoctor resDoctor = resDoctorMapper.selectByPrimaryKey(sysUser.getUserFlow());
        if (null == resDoctor) {
            return "请先完善报考信息";
        }
        // 查询招录信息
        ResDoctorRecruitExample doctorRecruitExample = new ResDoctorRecruitExample();
        doctorRecruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDoctorFlowEqualTo(sysUser.getUserFlow()).andDoctorStatusIdNotIn(Arrays.asList("21", "23", "24"));
        doctorRecruitExample.setOrderByClause("create_time desc");
        List<ResDoctorRecruit> doctorRecruitList = this.recruitMapper.selectByExample(doctorRecruitExample);
        if (null != doctorRecruitList && 0 < doctorRecruitList.size()) {
            ResDoctorRecruit doctorRecruit = doctorRecruitList.get(0);
            //有报送记录只能走报送通道
            if ("DoctorSend".equals(doctorRecruit.getSignupWay())) {
                return "当前在" + doctorRecruit.getOrgName() + "已有报送记录";
            } else {
                if ("Auditing".equals(doctorRecruit.getDoctorStatusId()) || "OrgAuditing".equals(doctorRecruit.getDoctorStatusId()) || ("Passed".equals(doctorRecruit.getDoctorStatusId()))
                        || ("Passed".equals(doctorRecruit.getAuditStatusId()) && "20".equals(resDoctor.getDoctorStatusId()))
                        || "20".equals(doctorRecruit.getDoctorStatusId())) {
                    return "当前在" + doctorRecruit.getOrgName() + "已有报名记录";
                }
            }
        }
        return "";
    }

    // 解析excel
    private static Workbook createAssignWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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
            logger.error("", e);
            throw new IOException("不能解析的excel版本");
        }

    }

    // 读取数据
    private int parseExcelToAssign(Workbook wb, Map<String, String> param) {
        // 导入成功条数
        int succCount = 0;
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
            if (row_num < 1) {
                throw new RuntimeException("没有数据！");
            }
            Row titleR = sheet.getRow(0);//获取表头
            int cell_num = titleR.getLastCellNum();//获取表头单元格数
            List<String> colnames = new ArrayList<>();
            for (int i = 0; i < cell_num; i++) {
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            List<Map<String, Object>> assignList = new ArrayList<>();
            //数据行开始遍历
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                if(r.getCell(0).getStringCellValue() == null || r.getCell(0).getStringCellValue().length() == 0) {
                    continue;
                }
                String value = "";
                String flag = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
                Map<String, Object> map = new HashMap<>();
                for (int j = 0; j < colnames.size(); j++) {
                    Cell cell = r.getCell(j);
                    if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (r.getCell((short) j).getCellType().getCode() == 1) {
                        value = r.getCell((short) j).getStringCellValue();
                    } else {
                        value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                    }
                    if ("招生专业名称*".equals(colnames.get(j))) {
                        map.put("speName", value);
                    } else if ("专业简介".equals(colnames.get(j))) {
                        map.put("speDesc", value);
                    } else if ("招收人数".equals(colnames.get(j))) {
                        map.put("assignPlan", value);
                    } else if ("报考专业要求".equals(colnames.get(j))) {
                        map.put("graduateSpe", value);
                    } else if ("学历学位要求".equals(colnames.get(j))) {
                        map.put("education", value);
                    }
                }
                if (StringUtil.isBlank(String.valueOf(map.get("speName")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，招生专业名称为空！");
                }
                //招收人数整数校验
                boolean isInt = Pattern.compile("^0|([1-9][0-9]{0,4})$").matcher(String.valueOf(map.get("assignPlan"))).matches();
                if (!isInt) {
                    throw new RuntimeException("导入失败！第" + i + "行，招录人数应为0-10000之间任意整数！");
                }
                if (Integer.parseInt(String.valueOf(map.get("assignPlan"))) > 10000 || Integer.parseInt(String.valueOf(map.get("assignPlan"))) < 0) {
                    throw new RuntimeException("导入失败！第" + i + "行，招录人数应为0-10000之间任意整数！");
                }
                if (String.valueOf(map.get("graduateSpe")).getBytes().length > 40) {
                    throw new RuntimeException("导入失败！第" + i + "行，招录报考要求过长，请控制在50字内！");
                }
                if (String.valueOf(map.get("education")).getBytes().length > 40) {
                    throw new RuntimeException("导入失败！第" + i + "行，学历学位要求过长，请控制在20字内！");
                }
                // 查询专业信息是否存在
                map.put("orgFlow", param.get("orgFlow"));
                map.put("assignYear", param.get("assignYear"));
                int count = recruitExtMapper.countAssignInfoByParam(map);
                if (count == 0) {
                    throw new RuntimeException("导入失败！第" + i + "行，招生专业不存在，请先新增招生计划信息！");
                }
                assignList.add(map);
            }
            if (null != assignList && 0 < assignList.size()) {
                SysUser sysUser = GlobalContext.getCurrentUser();
                for (Map<String, Object> assignMap : assignList) {
                    assignMap.put("orgFlow", param.get("orgFlow"));
                    assignMap.put("assignYear", param.get("assignYear"));
                    assignMap.put("modifyUserFlow", sysUser.getUserFlow());
                    assignMap.put("modifyTime", DateUtil.getCurrDateTime());
                    recruitExtMapper.updateAssignInfoByParam(assignMap);
                    succCount++;
                }
            }
        }
        return succCount;
    }

    @Override
    public String updateSendInfo(Map<String, Object> param) {
        int count = 0;
        // 查询当前年份的基地招生计划是否已存在 assignYearEdit 不为空表示编辑页面 不用查询是否已存在
        if (StringUtil.isBlank(String.valueOf(param.get("assignYearEdit")))) {
            count = recruitExtMapper.countSendInfoByParam(param);
            if (count > 0) {
                return String.valueOf(param.get("assignYear")) + "年的报送计划已存在";
            }
        }
        SysUser currUser = GlobalContext.getCurrentUser();
//		param.put("modifyUserFlow", currUser.getUserFlow());
//		param.put("modifyTime", DateUtil.getCurrDateTime());
        for (Map<String, String> map : (List<Map>) param.get("assignList")) {
            map.put("modifyUserFlow", currUser.getUserFlow());
            map.put("modifyTime", DateUtil.getCurrDateTime());
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals((String) param.get("isJointOrg"))) {
                map.put("auditStatusId", "Passed");
                map.put("auditStatusName", "审核通过");
            } else if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals((String) param.get("isJointOrg"))) {
                map.put("auditStatusId", "Passing");
                map.put("auditStatusName", "待审核");
            }
            map.put("isShown", com.pinde.core.common.GlobalConstant.FLAG_Y);
            recruitExtMapper.updateAssignInfo(map);
            count++;
        }
        if (count == 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

}
