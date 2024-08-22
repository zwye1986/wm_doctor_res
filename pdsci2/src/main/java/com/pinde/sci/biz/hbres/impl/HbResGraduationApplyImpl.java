package com.pinde.sci.biz.hbres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresGraduationApplyLogMapper;
import com.pinde.sci.dao.base.JsresGraduationApplyMapper;
import com.pinde.sci.dao.base.JsresGraduationAttachmentMapper;
import com.pinde.sci.dao.hbres.HbresGraduationApplyExtMapper;
import com.pinde.sci.dao.hbres.HbresTempMapper;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by czz on 2017/2/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HbResGraduationApplyImpl implements IHbResGraduationApplyBiz {
    @Autowired
    private JsresGraduationApplyMapper graduationApplyMapper;
    @Autowired
    private JsresGraduationApplyLogMapper graduationApplyLogMapper;
    @Autowired
    private HbresGraduationApplyExtMapper graduationApplyExtMapper;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private HbresTempMapper tempMapper;
    @Autowired
    private JsresGraduationAttachmentMapper attachmentMapper;
    @Override
    public JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear) {
        JsresGraduationApplyExample example = new JsresGraduationApplyExample();
        JsresGraduationApplyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitFlow)){
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        if(StringUtil.isNotBlank(applyYear)){
            criteria.andApplyYearEqualTo(applyYear);
        }
        List<JsresGraduationApply> applys = graduationApplyMapper.selectByExample(example);
        if(applys != null && applys.size() > 0){
            return graduationApplyMapper.selectByExample(example).get(0);
        }else {
            return null;
        }
    }

    @Override
    public int editGraduationApply(JsresGraduationApply jsresGraduationApply) {
        if(jsresGraduationApply!=null){
            if(StringUtil.isNotBlank(jsresGraduationApply.getApplyFlow())){//修改
                GeneralMethod.setRecordInfo(jsresGraduationApply, false);
                return this.graduationApplyMapper.updateByPrimaryKeySelective(jsresGraduationApply);
            }else{//新增
                jsresGraduationApply.setApplyFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(jsresGraduationApply, true);
                return this.graduationApplyMapper.insertSelective(jsresGraduationApply);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<Map<String, Object>> chargeQueryListForExport(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryListForExport(param);
    }

    @Override
    public void addOldInfoByApplyYear(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, Map<String, String> practicingMap, List<String> resultFlows)  throws DocumentException {
        //更新学员相关证书信息
        practicingMap.put("applyFlow",applyFlow);
        practicingMap.put("doctorFlow",doctorFlow);
        tempMapper.updateRecruitAsseInfoByApplyYear2(practicingMap);
        //学员资格审查百分比
        setFourStep(applyYear,recruitFlow,doctorFlow,applyFlow,resultFlows);
    }

    @Override
    public int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String doctorFlow, String applyYear, Map<String, String> practicingMap, List<String> resultFlows) throws DocumentException {
        int i=editGraduationApply(jsresGraduationApply);
        if(i==1)
        {
            //保存提交后的证书信息，百分比，出科考核表
            addOldInfoByApplyYear(applyYear,recruitFlow,doctorFlow,jsresGraduationApply.getApplyFlow(),practicingMap,resultFlows);
        }
        return i;
    }
    private void setFourStep(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, List<String> resultFlows) {
        //1
        tempMapper.deleteDeptDetailByApplyYear(applyYear,doctorFlow);
        if(resultFlows!=null&&resultFlows.size()>0)
            tempMapper.insetDeptDetailByApplyYear(applyYear,doctorFlow,recruitFlow,resultFlows);
        //3
        tempMapper.deleteDeptAvgTempByRecruitFlow(recruitFlow);
        tempMapper.updateDeptAvgTempByRecruitFlow(recruitFlow,applyYear);
        //4
        tempMapper.updateRecruitAvgTempByRecruitFlow(recruitFlow,applyFlow);
    }
    @Override
    public List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyList(param);
    }
    @Override
    public List<JsresGraduationApplyLog> getAuditLogsByApplyFlow(String applyFlow) {
        JsresGraduationApplyLogExample example=new JsresGraduationApplyLogExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andApplyFlowEqualTo(applyFlow);
        example.setOrderByClause("create_time desc");
        return graduationApplyLogMapper.selectByExample(example);
    }

    @Override
    public JsresGraduationApply readByFlow(String applyFlow) {
        return graduationApplyMapper.selectByPrimaryKey(applyFlow);
    }

    @Override
    public int saveChargeAudit(JsresGraduationApply apply, JsresGraduationApplyLog log) {
        saveApply(apply);
        saveApplyLog(log);
        return 1;
    }

    @Override
    public void chargeExportList(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //表头
            HSSFRow row = sheet.createRow(0);
            HSSFCell hSSFCell0 = row.createCell(0);
            hSSFCell0.setCellValue("湖北省住院医师规范化培训结业理论统考资格审核名册");
            styleTwo.setFont(fontTwo);
            hSSFCell0.setCellStyle(styleThree);

            HSSFRow row1 = sheet.createRow(1);
            HSSFCell hSSFCell1 = row1.createCell(0);
            hSSFCell1.setCellValue("填报单位（公章）:                       填表人：                     联系电话：                              年       月        日");
            styleTwo.setFont(fontTwo);
            hSSFCell1.setCellStyle(styleThree);
        String[] titles =null;
        if("Y".equals(isWaitAudit)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 30));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)30));
               titles= new String[]{
                        "序号",
                        "地区",
                        "培训基地",
                        "是否协同",
                        "国家基地",
                        "工作单位",
                        "姓名",
                        "性别",
                        "培训专业",
                        "培训起止时间",
                        "学历",
                        "毕业证书编号",
                        "报考资格材料（三选一）",
                        "报考资格材料编码",
                        "报考资格取得时间",
                        "执业范围",
                        "是否完成轮转培训计划",
                        "是否在上报的在培人员信息中",
                        "",
                        "备注",
                        "证件号码",
                        "数据填写平均比例",
                        "数据补填平均比例",
                        "数据审核平均比例",
                        "培养年限",
                        "轮转总时长",
                        "标准科室数"
                };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 29));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)29));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "是否协同",
                    "国家基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "培训专业",
                    "培训起止时间",
                    "学历",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "报考资格材料编码",
                    "报告资格取得时间",
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "",
                    "备注",
                    "状态",
                    "证件号码",
                    "数据填写平均比例",
                    "数据补填平均比例",
                    "数据审核平均比例",
                    "培养年限",
                    "轮转总时长",
                    "标准科室数"
            };
        }
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(2);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 3;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(String.valueOf(i));
                cell.setCellStyle(styleCenter);
                HSSFCell cell01 = rowDepts.createCell(1);//地区
                cell01.setCellValue((String)sd.get("orgCityName"));
                cell01.setCellStyle(styleCenter);
                HSSFCell cell1 = rowDepts.createCell(2);//培训基地
                cell1.setCellValue((String)sd.get("orgName"));
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(3);//是否协同
                if(sd.get("countryOrgFlow") == null || ((String)sd.get("orgFlow")).equals((String)sd.get("countryOrgFlow"))){
                    cell2.setCellValue("否");
                    cell2.setCellStyle(styleCenter);
                }else {
                    cell2.setCellValue("是");
                    cell2.setCellStyle(styleCenter);
                }

                HSSFCell cell3 = rowDepts.createCell(4);//国家基地
                cell3.setCellValue((String)sd.get("countryOrgName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(5);//工作单位
                cell4.setCellValue((String)sd.get("workOrgName"));
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(6);
                cell5.setCellValue((String)sd.get("userName"));
                cell5.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(7);
                cell6.setCellValue((String)sd.get("sexName"));
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(8);
                cell7.setCellValue((String)sd.get("speName"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(9);
                cell8.setCellValue((String)sd.get("startDate")+"~"+(String)sd.get("endDate"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(10);
                cell9.setCellValue((String)sd.get("educationName"));
                cell9.setCellStyle(styleCenter);
                HSSFCell cell10 = rowDepts.createCell(11);
                cell10.setCellValue((String)sd.get("certificateNo"));
                cell10.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(12);
                cell11.setCellValue((String)sd.get("qualificationMaterialName"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(13);
                cell12.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(14);
                cell13.setCellValue((String)sd.get("qualificationMaterialTime"));
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(15);
                cell14.setCellValue((String)sd.get("practicingScopeName"));
                cell14.setCellStyle(styleCenter);
                HSSFCell cell15 = rowDepts.createCell(16);
                cell15.setCellValue((String)sd.get("registeManua"));
                cell15.setCellStyle(styleCenter);
                HSSFCell cell16 = rowDepts.createCell(17);
                cell16.setCellValue("是");
                cell16.setCellStyle(styleCenter);
                HSSFCell cell17 = rowDepts.createCell(18);
                cell17.setCellValue((String)sd.get("isPass"));
                cell17.setCellStyle(styleCenter);
                HSSFCell cell18 = rowDepts.createCell(19);
                cell18.setCellValue((String) sd.get("auditReason"));
                cell18.setCellStyle(styleCenter);

                if(!"Y".equals(isWaitAudit)) {
                    HSSFCell cell19 = rowDepts.createCell(20);
                    cell19.setCellValue((String) sd.get("auditStatusName"));
                    cell19.setCellStyle(styleCenter);
                    HSSFCell cell20 = rowDepts.createCell(21);
                    cell20.setCellValue((String) sd.get("idNo"));
                    cell20.setCellStyle(styleCenter);
                    HSSFCell cell21 = rowDepts.createCell(22);
                    cell21.setCellValue((String) sd.get("avgComplete")+"%");
                    cell21.setCellStyle(styleCenter);
                    HSSFCell cell23 = rowDepts.createCell(23);
                    cell23.setCellValue((String) sd.get("avgOutComplete")+"%");
                    cell23.setCellStyle(styleCenter);
                    HSSFCell cell24 = rowDepts.createCell(24);
                    cell24.setCellValue((String) sd.get("avgAudit")+"%");
                    cell24.setCellStyle(styleCenter);
                    HSSFCell cell25 = rowDepts.createCell(25);
                    cell25.setCellValue((String) sd.get("trainYearName"));
                    cell25.setCellStyle(styleCenter);
                    HSSFCell cell26 = rowDepts.createCell(26);
                    cell26.setCellValue((String) sd.get("allSchMonth"));
                    cell26.setCellStyle(styleCenter);
                    String allNum=(String) sd.get("allNum");
                    HSSFCell cell27 = rowDepts.createCell(27);
                    cell27.setCellValue(allNum);
                    cell27.setCellStyle(styleCenter);
                }else {
                    HSSFCell cell19 = rowDepts.createCell(20);
                    cell19.setCellValue((String) sd.get("idNo"));
                    cell19.setCellStyle(styleCenter);
                    HSSFCell cell21 = rowDepts.createCell(21);
                    cell21.setCellValue((String) sd.get("avgComplete")+"%");
                    cell21.setCellStyle(styleCenter);
                    HSSFCell cell23 = rowDepts.createCell(22);
                    cell23.setCellValue((String) sd.get("avgOutComplete")+"%");
                    cell23.setCellStyle(styleCenter);
                    HSSFCell cell24 = rowDepts.createCell(23);
                    cell24.setCellValue((String) sd.get("avgAudit")+"%");
                    cell24.setCellStyle(styleCenter);
                    HSSFCell cell25 = rowDepts.createCell(24);
                    cell25.setCellValue((String) sd.get("trainYearName"));
                    cell25.setCellStyle(styleCenter);
                    HSSFCell cell26 = rowDepts.createCell(25);
                    cell26.setCellValue((String) sd.get("allSchMonth"));
                    cell26.setCellStyle(styleCenter);
                    String allNum=(String) sd.get("allNum");
                    HSSFCell cell27 = rowDepts.createCell(26);
                    cell27.setCellValue(allNum);
                    cell27.setCellStyle(styleCenter);
                }
                rownum++;
                i++;
            }
        }

        String fileName = "考核资格审查表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public int saveBatchAudit(List<JsresGraduationApply> applies, List<JsresGraduationApplyLog> logs) {
        for(JsresGraduationApply a:applies)
           saveApply(a);
        for(JsresGraduationApplyLog a:logs)
           saveApplyLog(a);
        return 1;
    }

    private int saveApplyLog(JsresGraduationApplyLog log) {
        if(StringUtil.isNotBlank(log.getRecordFlow()))
        {
            GeneralMethod.setRecordInfo(log,false);
            return graduationApplyLogMapper.updateByPrimaryKeySelective(log);
        }else{
            log.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(log,true);
            return graduationApplyLogMapper.insertSelective(log);
        }
    }

    private int saveApply(JsresGraduationApply apply) {
        if(StringUtil.isNotBlank(apply.getApplyFlow()))
        {
            GeneralMethod.setRecordInfo(apply,false);
            return graduationApplyMapper.updateByPrimaryKeySelective(apply);
        }else{
            apply.setApplyFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(apply,true);
            return graduationApplyMapper.insertSelective(apply);
        }
    }

    @Override
    public int editAttachment(JsresGraduationAttachment attachment) {
        if(StringUtil.isNotBlank(attachment.getRecordFlow())){
            GeneralMethod.setRecordInfo(attachment,false);
            return attachmentMapper.updateByPrimaryKeySelective(attachment);
        }else{
            attachment.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(attachment,true);
            return attachmentMapper.insertSelective(attachment);
        }
    }

    @Override
    public List<JsresGraduationAttachment> searchAttachments(JsresGraduationAttachment attachment) {
        JsresGraduationAttachmentExample example = new JsresGraduationAttachmentExample();
        JsresGraduationAttachmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(attachment!=null){
            if(StringUtil.isNotBlank(attachment.getGraduationYear())){
                criteria.andGraduationYearEqualTo(attachment.getGraduationYear());
            }
            if(StringUtil.isNotBlank(attachment.getOrgFlow())){
                criteria.andOrgFlowEqualTo(attachment.getOrgFlow());
            }
        }
        return attachmentMapper.selectByExample(example);
    }

    @Override
    public Map<String, String> graduationImgUpload(MultipartFile file) {
        Map<String, String> map=new HashMap<String, String>();
        map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
        if(file!=null){
            List<String> mimeList = new ArrayList<String>();
            if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
                mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
                suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
            }

            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                map.put("error", GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
                return  map;
            }

            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
                return  map;
            }
            try {
				/*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"graduationScoresImg"+File.separator + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
				/*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = InitConfig.getSysCfg("upload_base_url")+"/"+"graduationScoresImg"+"/"+dateString+"/"+fileName;

                SysUser user = GlobalContext.getCurrentUser();
                String orgFlow = user.getOrgFlow();
                String year = DateUtil.getYear();
                JsresGraduationAttachment save = new JsresGraduationAttachment();
                String recordFlow=PkUtil.getUUID();
                save.setRecordFlow(recordFlow);
                save.setImgPath(url);
                save.setOrgFlow(orgFlow);
                save.setGraduationYear(year);
                GeneralMethod.setRecordInfo(save,true);
                attachmentMapper.insertSelective(save);
                map.put("url",url);
                map.put("flow",recordFlow);

                map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
