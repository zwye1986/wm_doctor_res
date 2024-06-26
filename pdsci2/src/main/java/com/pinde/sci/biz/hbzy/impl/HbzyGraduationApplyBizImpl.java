package com.pinde.sci.biz.hbzy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyGraduationApplyBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JsresGraduationApplyLogMapper;
import com.pinde.sci.dao.base.JsresGraduationApplyMapper;
import com.pinde.sci.dao.hbzy.HbzyGraduationApplyExtMapper;
import com.pinde.sci.enums.hbzy.JszyResAsseAuditListEnum;
import com.pinde.sci.model.mo.JsresGraduationApply;
import com.pinde.sci.model.mo.JsresGraduationApplyExample;
import com.pinde.sci.model.mo.JsresGraduationApplyLog;
import com.pinde.sci.model.mo.JsresGraduationApplyLogExample;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2018/3/9.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HbzyGraduationApplyBizImpl implements IJszyGraduationApplyBiz{
    @Autowired
    private JsresGraduationApplyMapper graduationApplyMapper;
    @Autowired
    private JsresGraduationApplyLogMapper graduationApplyLogMapper;
    @Autowired
    private HbzyGraduationApplyExtMapper graduationApplyExtMapper;

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
    public List<JsresGraduationApply> searchByRecruitFlows(List<String> recruitFlows, String applyYear) {
        JsresGraduationApplyExample example = new JsresGraduationApplyExample();
        JsresGraduationApplyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(recruitFlows != null && recruitFlows.size() > 0){
            criteria.andRecruitFlowIn(recruitFlows);
        }
        if(StringUtil.isNotBlank(applyYear)){
            criteria.andApplyYearEqualTo(applyYear);
        }
        List<JsresGraduationApply> applys = graduationApplyMapper.selectByExample(example);
        if(applys != null && applys.size() > 0){
            return graduationApplyMapper.selectByExample(example);
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
    public List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyList(param);
    }

    @Override
    public List<Map<String, Object>> chargeQueryListForExport(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryListForExport(param);
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
    public void chargeExportList(List<Map<String, Object>> list, HttpServletResponse response, String isWaitAudit, String roleFlag) throws IOException {
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
        hSSFCell0.setCellValue("江苏省住院医师规范化培训（中医）理论省统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell hSSFCell1 = row1.createCell(0);
        hSSFCell1.setCellValue("填报单位（公章）:                       填表人：                     联系电话：                              年       月        日");
        styleTwo.setFont(fontTwo);
        hSSFCell1.setCellStyle(styleThree);
        String[] titles =null;
        if("Y".equals(isWaitAudit)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 17));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short) 1, 0, (short) 17));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "培训专业",
                    "培养年限",
                    "培训起止时间",
                    "学历",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "执业资格材料编码",
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "备注",
                    "证件号码"
            };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short) 1, 0, (short) 18));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "培训专业",
                    "培养年限",
                    "培训起止时间",
                    "学历",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "执业资格材料编码",
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "备注",
                    "状态",
                    "证件号码"
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
                HSSFCell cell2 = rowDepts.createCell(3);//工作单位
                cell2.setCellValue((String)sd.get("workOrgName"));
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(4);//姓名
                cell3.setCellValue((String)sd.get("userName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(5);//性别
                cell4.setCellValue((String)sd.get("sexName"));
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(6);//培训专业
                cell5.setCellValue((String)sd.get("catSpeName"));
                cell5.setCellStyle(styleCenter);
                HSSFCell cell5B = rowDepts.createCell(7);//培训年限
                cell5B.setCellValue((String)sd.get("trainYear"));
                cell5B.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(8);//培训起止时间
                String timeRange = (String) sd.get("startDate") + "~" + (String) sd.get("endDate");
                cell6.setCellValue(timeRange);
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(9);//学历
                cell7.setCellValue((String)sd.get("educationName"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(10);//毕业证书编号
                cell8.setCellValue((String)sd.get("certificateNo"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(11);//报考资格材料（三选一）
                cell9.setCellValue((String)sd.get("qualificationMaterialName"));
                cell9.setCellStyle(styleCenter);
                HSSFCell cell10 = rowDepts.createCell(12);//执教资格材料编码
                cell10.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell10.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(13);//执业范围
                cell11.setCellValue((String)sd.get("practicingScopeName"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(14);//是否完成轮转培训计划
                cell12.setCellValue("是");
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(15);//是否在上报的在培人员信息中
                cell13.setCellValue("是");
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(16);//备注
                if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    if(((String)sd.get("auditStatusId")).equals(JszyResAsseAuditListEnum.LocalNotPassed.getId())){
                        cell14.setCellValue((String)sd.get("localReason"));
                    }else {
                        cell14.setCellValue((String)sd.get("globalReason"));
                    }
                }else{
                    if(((String)sd.get("auditStatusId")).equals(JszyResAsseAuditListEnum.GlobalNotPassed.getId())){
                        cell14.setCellValue((String)sd.get("globalReason"));
                    }else {
                        cell14.setCellValue((String)sd.get("localReason"));
                    }
                }
                cell14.setCellStyle(styleCenter);

                if(!"Y".equals(isWaitAudit)) {
                    HSSFCell cell16= rowDepts.createCell(17);//状态
                    cell16.setCellValue((String) sd.get("auditStatusName"));
                    cell16.setCellStyle(styleCenter);
                    HSSFCell cell15 = rowDepts.createCell(18);//证件号码
                    cell15.setCellValue((String)sd.get("idNo"));
                    cell15.setCellStyle(styleCenter);
                }else {
                    HSSFCell cell15 = rowDepts.createCell(17);//证件号码
                    cell15.setCellValue((String)sd.get("idNo"));
                    cell15.setCellStyle(styleCenter);
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
}
