package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.JsresDoctorDeptDetailMapper;
import com.pinde.sci.dao.base.JsresGraduationApplyLogMapper;
import com.pinde.sci.dao.base.JsresGraduationApplyMapper;
import com.pinde.sci.dao.jsres.JsresGraduationApplyExtMapper;
import com.pinde.sci.dao.jsres.TempMapper;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SchRotationDeptAfterWithBLOBs;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czz on 2017/2/27.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class JsResGraduationApplyImpl implements IJsResGraduationApplyBiz {
    @Autowired
    private JsresGraduationApplyMapper graduationApplyMapper;
    @Autowired
    private JsresGraduationApplyLogMapper graduationApplyLogMapper;
    @Autowired
    private JsresDoctorDeptDetailMapper jsresDoctorDeptDetailMapper;
    @Autowired
    private JsresGraduationApplyExtMapper graduationApplyExtMapper;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;

    private static final Logger logger = LoggerFactory.getLogger(JsResGraduationApplyImpl.class);

    @Override
    public JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear) {
        JsresGraduationApplyExample example = new JsresGraduationApplyExample();
        JsresGraduationApplyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitFlow)){
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        if(StringUtil.isNotBlank(applyYear)){
            criteria.andApplyYearEqualTo(applyYear);
        }
        List<JsresGraduationApply> applys = graduationApplyMapper.selectByExample(example);
        if(applys != null && applys.size() > 0){
            return applys.get(0);
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
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<Map<String, Object>>   chargeQueryList(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryList(param);
    }
    @Override
    public List<Map<String, Object>> chargeQueryListForExport(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryListForExport(param);
    }

    @Override
    public List<Map<String, Object>> chargeQueryListForExport2(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryListForExport2(param);
    }

    @Override
    public void addOldInfoByApplyYear(String applyYear, String recruitFlow, String doctorFlow, String applyFlow, Map<String, String> practicingMap)  throws DocumentException {
        //学员出科考核表数据抽取
        selectAfter(applyYear,recruitFlow,doctorFlow);
        //更新学员相关证书信息
        practicingMap.put("applyFlow",applyFlow);
        practicingMap.put("doctorFlow",doctorFlow);
        tempMapper.updateRecruitAsseInfoByApplyYear2(practicingMap);
        //学员资格审查百分比
        setFourStep(applyYear,recruitFlow,doctorFlow,applyFlow);
//        //完成比例与审核比例
//        List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetailByNow(recruitFlow, doctorFlow);
//        if (details != null && details.size() > 0) {
//            int isShortY=0;
//            int isShortN=0;
//            int shortYCount=0;
//            int shortNCount=0;
//            double shortYCBSum=0;//完成比例
//            double shortNCBSum=0;
//            double shortYOCBSum=0;//补填比例
//            double shortNOCBSum=0;
//            double shortYICBSum=0;//正常比例
//            double shortNICBSum=0;
//            double shortYABSum=0;//审核 比例
//            double shortNABSum=0;
//            double avgComBi=0;//平均完成比例
//            double avgOutComBi=0;//平均补填比例
//            double avgInComBi=0;//平均正常比例
//            double avgAuditComBi=0;//平均审核比例
//            for (JsresDoctorDeptDetail d : details) {
//                if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(d.getIsShort())) {
//                    shortYCount++;
//                    shortYCBSum+=StringUtil.isBlank(d.getCompleteBi())?0:"-".equals(d.getCompleteBi())?0:Double.valueOf(d.getCompleteBi());
//                    shortYOCBSum+=StringUtil.isBlank(d.getOutCompleteBi())?0:"-".equals(d.getOutCompleteBi())?0:Double.valueOf(d.getOutCompleteBi());
//                    shortYICBSum+=StringUtil.isBlank(d.getInCompleteBi())?0:"-".equals(d.getInCompleteBi())?0:Double.valueOf(d.getInCompleteBi());
//                    shortYABSum+=StringUtil.isBlank(d.getAuditBi())?0:"-".equals(d.getAuditBi())?0:Double.valueOf(d.getAuditBi());
//                    if (isShortY == 0) {
//                        isShortY = 1;
//                    }
//                }
//                if(com.pinde.core.common.GlobalConstant.FLAG_N.equals(d.getIsShort())) {
//                    shortNCount++;
//                    shortNCBSum+=StringUtil.isBlank(d.getCompleteBi())?0:"-".equals(d.getCompleteBi())?0:Double.valueOf(d.getCompleteBi());
//                    shortNOCBSum+=StringUtil.isBlank(d.getOutCompleteBi())?0:"-".equals(d.getOutCompleteBi())?0:Double.valueOf(d.getOutCompleteBi());
//                    shortNICBSum+=StringUtil.isBlank(d.getInCompleteBi())?0:"-".equals(d.getInCompleteBi())?0:Double.valueOf(d.getInCompleteBi());
//                    shortNABSum+=StringUtil.isBlank(d.getAuditBi())?0:"-".equals(d.getAuditBi())?0:Double.valueOf(d.getAuditBi());
//                    if (isShortN == 0) {
//                        isShortN = 1;
//                    }
//                }
//                d.setApplyYear(applyYear);
//                addJsresDoctorDeptDetail(d);
//            }
//            //平均完成比例与平均审核比例
//            if((isShortY+isShortN)>1)
//            {
//                avgComBi=new BigDecimal(shortYCBSum/shortYCount).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgOutComBi=new BigDecimal(shortYOCBSum/shortYCount).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgInComBi=new BigDecimal(shortYICBSum/shortYCount).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgAuditComBi=new BigDecimal(shortYABSum/shortYCount).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//            }else{
//                avgComBi=new BigDecimal((shortYCBSum+shortNCBSum)/(shortYCount+shortNCount)).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgOutComBi=new BigDecimal((shortYOCBSum+shortNOCBSum)/(shortYCount+shortNCount)).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgInComBi=new BigDecimal((shortYICBSum+shortNICBSum)/(shortYCount+shortNCount)).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                avgAuditComBi=new BigDecimal((shortYABSum+shortNABSum)/(shortYCount+shortNCount)).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//            }
//            JsresGraduationApply apply=new JsresGraduationApply();
//            apply.setApplyFlow(applyFlow);
//            apply.setAvgComplete(avgComBi+"");
//            apply.setAvgOutComplete(avgOutComBi+"");
//            apply.setAvgInComplete(avgInComBi+"");
//            apply.setAvgAudit(avgAuditComBi+"");
//            editGraduationApply(apply);
//        }
    }

    @Override
    public int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String changeSpeId, String doctorFlow, String applyYear, Map<String, String> practicingMap) throws DocumentException {
        int i=editGraduationApply(jsresGraduationApply);
        if(i==1)
        {
            if(StringUtil.isNotBlank(changeSpeId))
            {
                ResDoctorRecruitWithBLOBs newRecruit=new ResDoctorRecruitWithBLOBs();
                newRecruit.setRecruitFlow(recruitFlow);
                newRecruit.setChangeSpeId(changeSpeId);
                newRecruit.setChangeSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(changeSpeId));
                doctorRecruitBiz.editDoctorRecruit(newRecruit);
            }
            //保存提交后的证书信息，百分比，出科考核表
            addOldInfoByApplyYear(applyYear,recruitFlow,doctorFlow,jsresGraduationApply.getApplyFlow(),practicingMap);
        }
        return i;
    }

    @Override
    public int updatePer(String applyFlow, String recruitFlow, String s, String doctorFlow, String applyYear) {
        setFourStep2(applyYear,recruitFlow,doctorFlow,applyFlow);
        return 1;
    }

    @Override
    public List<JsresGraduationInfo> queryGraduationInfoList(Map<String, Object> param) {
        return graduationApplyExtMapper.queryGraduationInfoListNew(param);
    }

    @Override
    public List<Map<String, Object>> queryGraduationInfoListExport(Map<String, Object> param) {
        return graduationApplyExtMapper.queryGraduationInfoListExport(param);
    }

    private void setFourStep(String applyYear, String recruitFlow, String doctorFlow, String applyFlow) {
        //1
        tempMapper.deleteDeptDetailByApplyYear(applyYear,doctorFlow);
        tempMapper.insetDeptDetailByApplyYear(applyYear,doctorFlow,recruitFlow);
        //2
        tempMapper.deleteDeptTempByRecruitFlow(recruitFlow);
        tempMapper.updateDeptTempByRecruitFlow(recruitFlow,applyYear);
        //3
        tempMapper.deleteDeptAvgTempByRecruitFlow(recruitFlow);
        tempMapper.updateDeptAvgTempByRecruitFlow(recruitFlow,applyYear);
        //4
        tempMapper.updateRecruitAvgTempByRecruitFlow(recruitFlow,applyFlow);
    }
    private void setFourStep2(String applyYear, String recruitFlow, String doctorFlow, String applyFlow) {
        //1
        tempMapper.updateDeptDetailPerByApplyYear(applyYear,doctorFlow,recruitFlow);
        //2
        tempMapper.updateDeptTempByRecruitFlow(recruitFlow,applyYear);
        //3
        tempMapper.updateDeptAvgPerTempByRecruitFlow(recruitFlow,applyYear);
        //4
        tempMapper.updateRecruitAvgPerTempByRecruitFlow(recruitFlow,applyFlow);
    }

    private void addJsresDoctorDeptDetail(JsresDoctorDeptDetail d) {
        JsresDoctorDeptDetail old=getJsresDoctorDeptDetail(d.getApplyYear(),d.getDoctorFlow(),d.getSchStandardDeptFlow());
        if(old==null) {
            jsresDoctorDeptDetailMapper.insert(d);
        }else{
            d.setRecordFlow(old.getRecordFlow());
            jsresDoctorDeptDetailMapper.updateByPrimaryKeySelective(d);
        }
    }

    private JsresDoctorDeptDetail getJsresDoctorDeptDetail(String applyYear, String doctorFlow, String schStandardDeptFlow) {
        JsresDoctorDeptDetailExample example=new JsresDoctorDeptDetailExample();
        example.createCriteria().andApplyYearEqualTo(applyYear).andDoctorFlowEqualTo(doctorFlow).andSchStandardDeptFlowEqualTo(schStandardDeptFlow);
        List<JsresDoctorDeptDetail> list=jsresDoctorDeptDetailMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    private void selectAfter(String applyYear, String recruitFlow, String doctorFlow) throws DocumentException {
        List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId( doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
        List<SchRotationDeptAfterWithBLOBs> afters=afterBiz.getAfterByDoctorFlow(doctorFlow,applyYear);
        Map<String,SchRotationDeptAfterWithBLOBs> map=new HashMap();
        if(afters!=null&&afters.size()>0)
        {
            for(SchRotationDeptAfterWithBLOBs a:afters)
            {
                map.put(a.getSchRotationDeptFlow()+a.getDoctorFlow(),a);
            }
        }
        if(recs!=null&&recs.size()>0)
        {
            for(ResSchProcessExpress rec:recs)
            {
                SchRotationDeptAfterWithBLOBs after=map.get(rec.getSchRotationDeptFlow()+rec.getOperUserFlow());
                if(after==null)
                    after=new SchRotationDeptAfterWithBLOBs();

                    String content = null == rec ? "" : rec.getRecContent();

                    StringBuffer imageUrls=new StringBuffer();
                    StringBuffer thumbUrls=new StringBuffer();
                        if (StringUtil.isNotBlank(content)) {
                            Document doc = DocumentHelper.parseText(content);
                            Element root = doc.getRootElement();
                            List<Element> imageEles = root.elements();
                            if (imageEles != null && imageEles.size() > 0) {
                                boolean isUpdate=false;
                                for (Element image : imageEles) {
                                    Element imageUrl = image.element("imageUrl");
                                    if(imageUrl!=null) {
                                        if(imageUrl.getTextTrim().indexOf("http://58.213.112.250:65486")>0||
                                                imageUrl.getTextTrim().indexOf("http://116.62.70.138")>0)
                                            isUpdate=true;
                                        String url=imageUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
                                                .replace("http://116.62.70.138", "http://js.ezhupei.com");
                                        imageUrls.append( url+ ",");
                                        imageUrl.setText(url);
                                    }
                                    Element thumbUrl = image.element("thumbUrl");
                                    if(thumbUrl!=null) {
                                        if(thumbUrl.getTextTrim().indexOf("http://58.213.112.250:65486")>0||
                                                thumbUrl.getTextTrim().indexOf("http://116.62.70.138")>0)
                                            isUpdate=true;
                                        String url = thumbUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
                                                .replace("http://116.62.70.138", "http://js.ezhupei.com");
                                        thumbUrls.append(url + ",");
                                        thumbUrl.setText(url);
                                    }
                                }
                                if(isUpdate) {
                                    rec.setRecContent(doc.asXML());
                                    expressBiz.edit(rec);
                                }
                            }
                        }

                    if(StringUtil.isNotBlank(imageUrls.toString()))
                        after.setImageUrls(imageUrls.substring(0,imageUrls.length()-1));
                    else
                        after.setImageUrls("");

                    if(StringUtil.isNotBlank(thumbUrls.toString()))
                        after.setThumbUrls(thumbUrls.substring(0,thumbUrls.length()-1));
                    else
                        after.setThumbUrls("");
                after.setApplyYear(applyYear);
                after.setRecordStatus(rec.getRecordStatus());
                after.setModfiyTime(rec.getModifyTime());
                after.setSchRotationDeptFlow(rec.getSchRotationDeptFlow());
                after.setDoctorFlow(rec.getOperUserFlow());
                afterBiz.edit(after);
            }
        }
    }

    @Override
    public List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyList(param);
    }

    @Override
    public List<Map<String,Object>> chargeQueryApplyList2(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyList2(param);
    }

    @Override
    public List<JsresGraduationApplyLog> getAuditLogsByApplyFlow(String applyFlow) {
        JsresGraduationApplyLogExample example=new JsresGraduationApplyLogExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
            HSSFRow row = sheet.createRow(0);
            HSSFCell hSSFCell0 = row.createCell(0);
            hSSFCell0.setCellValue("住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
            styleTwo.setFont(fontTwo);
            hSSFCell0.setCellStyle(styleThree);

            HSSFRow row1 = sheet.createRow(1);
            HSSFCell hSSFCell1 = row1.createCell(0);
            hSSFCell1.setCellValue("填报单位（公章）:                       填表人：                     联系电话：                              年       月        日");
            styleTwo.setFont(fontTwo);
            hSSFCell1.setCellStyle(styleThree);
        String[] titles =null;
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isWaitAudit)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 37));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)37));
               titles= new String[]{
                        "序号",
                        "地区",
                        "培训基地",
                        "是否协同",
                        "国家基地",
                        "工作单位",
                        "姓名",
                        "性别",
                        "证件类型",
                        "证件号码",
                        "民族",
                        "培训类别",
                        "培训专业",
                        "报考专业",
                        "培训起止时间",
                        "学历",
                        "学位类型",
                        "毕业学校",
                        "毕业时间",
                        "毕业专业",
                        "毕业证书编号",
                        "报考资格材料（三选一）",
                        "执业资格材料编码",
                        "执业范围",
                        "是否为军队人员",
                        "是否为西部支援住院医师",
                        "是否完成轮转培训计划",
                        "是否在上报的在培人员信息中",
                        "公共课考核",
                        "备注",
                        "数据填写平均比例",
                        "数据补填平均比例",
                        "数据审核平均比例",
                        "培养年限",
                        "轮转总时长",
                        "标准科室数",
                        "出科考核表上传数",
                        "出科考核表上传比例",
                        "执业类别",
                        "联系地址"
                };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 37));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)37));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "是否协同",
                    "国家基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "证件类型",
                    "证件号码",
                    "民族",
                    "培训类别",
                    "培训专业",
                    "报考专业",
                    "培训起止时间",
                    "学历",
                    "学位类型",
                    "毕业学校",
                    "毕业时间",
                    "毕业专业",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "执业资格材料编码",
                    "执业范围",
                    "是否为军队人员",
                    "是否为西部支援住院医师",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "数据填写平均比例",
                    "数据补填平均比例",
                    "数据审核平均比例",
                    "培养年限",
                    "轮转总时长",
                    "标准科室数",
                    "出科考核表上传数",
                    "出科考核表上传比例",
                    "执业类别",
                    "联系地址"
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
                if (sd.get("countryOrgFlow") == null || sd.get("orgFlow").equals(sd.get("countryOrgFlow"))) {
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
                HSSFCell cell30 = rowDepts.createCell(8);
                cell30.setCellValue((String)sd.get("cretTypeName"));
                cell30.setCellStyle(styleCenter);
                HSSFCell cell31 = rowDepts.createCell(10);
                cell31.setCellValue((String)sd.get("nationName"));
                cell31.setCellStyle(styleCenter);
                HSSFCell cell32 = rowDepts.createCell(11);
                cell32.setCellValue((String)sd.get("trainingTypeName"));
                cell32.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(12);
                cell7.setCellValue((String)sd.get("speName"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(13);
                cell8.setCellValue((String)sd.get("changeSpeName"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(14);
                cell9.setCellValue(sd.get("startDate") + "~" + sd.get("endDate"));
                cell9.setCellStyle(styleCenter);

                HSSFCell cell10 = rowDepts.createCell(15);
                cell10.setCellValue((String)sd.get("educationName"));
                cell10.setCellStyle(styleCenter);

                UserResumeExtInfoForm userResumeExt = null;
                try {
                    String doctorFlow = (String)sd.get("doctorFlow");
                    PubUserResume userResume = userResumeBiz.readPubUserResume(doctorFlow);
                    if(userResume != null){
                        userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
                    }
                } catch (DocumentException e) {
                    logger.error("", e);
                }
                String degreeCategoryName = (String)sd.get("degreeCategoryName");
                if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeTypeName())){
                    degreeCategoryName = userResumeExt.getDoctorDegreeTypeName();
                }else if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeTypeName())){
                    degreeCategoryName = userResumeExt.getMasterDegreeTypeName();
                }else if(StringUtil.isNotBlank(userResumeExt.getDegreeName())){
                    degreeCategoryName = userResumeExt.getDegreeName();
                }
                if("科学型".equals(degreeCategoryName)){
                    degreeCategoryName = "学术型";
                }
                HSSFCell cell33 = rowDepts.createCell(16);
                cell33.setCellValue(degreeCategoryName);
                cell33.setCellStyle(styleCenter);
                HSSFCell cell34 = rowDepts.createCell(17);
                cell34.setCellValue((String)sd.get("graduatedName"));
                cell34.setCellStyle(styleCenter);
                HSSFCell cell35 = rowDepts.createCell(18);
                cell35.setCellValue((String)sd.get("graduationTime"));
                cell35.setCellStyle(styleCenter);
                HSSFCell cell36 = rowDepts.createCell(19);
                cell36.setCellValue((String)sd.get("specialized"));
                cell36.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(20);
                cell11.setCellValue((String)sd.get("certificateNo"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(21);
                cell12.setCellValue((String)sd.get("qualificationMaterialName"));
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(22);
                cell13.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(23);
                cell14.setCellValue((String)sd.get("practicingScopeName"));
                cell14.setCellStyle(styleCenter);

                if (userResumeExt != null) {
                    // 是否军队人员
                    String armyType = userResumeExt.getArmyType();
                    String armyName = "否";
                    if (StringUtil.isNotBlank(armyType)) {
                        armyName = com.pinde.core.common.enums.ArmyTypeEnum.getNameById(armyType);
                    }
                    HSSFCell cell371 = rowDepts.createCell(24);
                    cell371.setCellValue(armyName);
                    cell371.setCellStyle(styleCenter);

                    String westernSupportResidents = "";
                    if (StringUtil.isNotBlank(userResumeExt.getWesternSupportResidents())) {
                        if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                            westernSupportResidents = "是";
                        } else if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                            westernSupportResidents = "否";
                        }
                    }
                    HSSFCell cell37 = rowDepts.createCell(25);
                    cell37.setCellValue(westernSupportResidents);
                    cell37.setCellStyle(styleCenter);
                }
                HSSFCell cell15 = rowDepts.createCell(26);
                cell15.setCellValue((String)sd.get("registeManua"));
                cell15.setCellStyle(styleCenter);
                HSSFCell cell16 = rowDepts.createCell(27);
                cell16.setCellValue("是");
                cell16.setCellStyle(styleCenter);
                HSSFCell cell17 = rowDepts.createCell(28);
                cell17.setCellValue((String)sd.get("isPass"));
                cell17.setCellStyle(styleCenter);
                HSSFCell cell18 = rowDepts.createCell(29);
                cell18.setCellValue((String) sd.get("auditReason"));
                cell18.setCellStyle(styleCenter);

               /* if(!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isWaitAudit)) {
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
                    String upNum=(String) sd.get("upNum");
                    HSSFCell cell27 = rowDepts.createCell(27);
                    cell27.setCellValue(allNum);
                    cell27.setCellStyle(styleCenter);
                    HSSFCell cell28 = rowDepts.createCell(28);
                    cell28.setCellValue(upNum);
                    cell28.setCellStyle(styleCenter);
                    String upPer="0%";
                    if(Integer.valueOf(allNum)>0&&Integer.valueOf(upNum)>0)
                    {
                        double per=new BigDecimal(Double.valueOf(upNum) / Double.valueOf(allNum)*100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                        if(per>100)
                        {
                            per=100;
                        }
                        upPer=per+"%";
                    }
                    HSSFCell cell29 = rowDepts.createCell(29);
                    cell29.setCellValue(upPer);
                    cell29.setCellStyle(styleCenter);
                }else {*/
                    HSSFCell cell19 = rowDepts.createCell(9);
                    cell19.setCellValue((String) sd.get("idNo"));
                    cell19.setCellStyle(styleCenter);
                    HSSFCell cell21 = rowDepts.createCell(30);
                cell21.setCellValue(sd.get("avgComplete") + "%");
                    cell21.setCellStyle(styleCenter);
                    HSSFCell cell23 = rowDepts.createCell(31);
                cell23.setCellValue(sd.get("avgOutComplete") + "%");
                    cell23.setCellStyle(styleCenter);
                    HSSFCell cell24 = rowDepts.createCell(32);
                cell24.setCellValue(sd.get("avgAudit") + "%");
                    cell24.setCellStyle(styleCenter);
                    HSSFCell cell25 = rowDepts.createCell(33);
                    cell25.setCellValue((String) sd.get("trainYearName"));
                    cell25.setCellStyle(styleCenter);
                    HSSFCell cell26 = rowDepts.createCell(34);
                    cell26.setCellValue((String) sd.get("allSchMonth"));
                    cell26.setCellStyle(styleCenter);
                    String allNum=(String) sd.get("allNum");
                    String upNum=(String) sd.get("upNum");
                    HSSFCell cell27 = rowDepts.createCell(35);
                    cell27.setCellValue(allNum);
                    cell27.setCellStyle(styleCenter);
                    HSSFCell cell28 = rowDepts.createCell(36);
                    cell28.setCellValue(upNum);
                    cell28.setCellStyle(styleCenter);
                    String upPer="0%";
                    if(Integer.valueOf(allNum)>0&&Integer.valueOf(upNum)>0)
                    {
                        double per = BigDecimal.valueOf(Double.valueOf(upNum) / Double.valueOf(allNum) * 100).setScale(0, RoundingMode.HALF_UP).doubleValue();
                        if(per>100)
                        {
                            per=100;
                        }
                        upPer=per+"%";
                    }
                    HSSFCell cell29 = rowDepts.createCell(37);
                    cell29.setCellValue(upPer);
                    cell29.setCellStyle(styleCenter);
                    if (userResumeExt != null) {
                        String practicingCategoryName = userResumeExt.getPracticingCategoryName();
                        HSSFCell cell38 = rowDepts.createCell(38);
                        cell38.setCellValue(practicingCategoryName);
                        cell38.setCellStyle(styleCenter);
                    }
                    HSSFCell cell39 = rowDepts.createCell(39);
                    cell39.setCellValue((String)sd.get("userAddress"));
                    cell39.setCellStyle(styleCenter);
//                }
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
    public void chargeExportListTwo(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("江苏省住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell hSSFCell1 = row1.createCell(0);
        hSSFCell1.setCellValue("填报单位（公章）:                       填表人：                     联系电话：                              年       月        日");
        styleTwo.setFont(fontTwo);
        hSSFCell1.setCellStyle(styleThree);
        String[] titles =null;
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isWaitAudit)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)18));
            titles= new String[]{
                    "序号",
                    "培训基地",
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
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "状态",
                    "证件号码",
                    "手机号码",
                    "人员类型"
            };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)18));
            titles= new String[]{
                    "序号",
                    "培训基地",
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
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "状态",
                    "证件号码",
                    "手机号码",
                    "人员类型"
            };
        }
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(2);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
//            sheet.setColumnWidth(i, titles[i].getBytes().length*2*256);
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
                HSSFCell cell1 = rowDepts.createCell(1);//培训基地
                cell1.setCellValue((String)sd.get("orgName"));
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(2);//国家基地
                String orgFlow = (String)sd.get("orgFlow");
                if(StringUtils.isNotBlank(orgFlow)){
                    List<ResJointOrg> resJointOrgs = jointOrgBiz.selectByJointOrgFlow(orgFlow);
                    if(CollectionUtils.isNotEmpty(resJointOrgs)){
                        cell2.setCellValue(resJointOrgs.get(0).getOrgName());//国家基地
                        cell2.setCellStyle(styleCenter);
                    }else{
                        cell2.setCellValue((String)sd.get("orgName"));
                        cell2.setCellStyle(styleCenter);
                    }
                }
                HSSFCell cell3 = rowDepts.createCell(3);//工作单位
                cell3.setCellValue((String)sd.get("workOrgName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(4);//姓名
                cell4.setCellValue((String)sd.get("userName"));
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(5);//性别
                cell5.setCellValue((String)sd.get("sexName"));
                cell5.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(6);//培训专业
                cell6.setCellValue((String)sd.get("speName"));
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(7);//培训起止时间
                cell7.setCellValue(sd.get("startDate") + "~" + sd.get("endDate"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(8);
                cell8.setCellValue((String)sd.get("educationName"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(9);
                cell9.setCellValue((String)sd.get("certificateNo"));
                cell9.setCellStyle(styleCenter);
                HSSFCell cell10 = rowDepts.createCell(10);
                cell10.setCellValue((String)sd.get("qualificationMaterialName"));
                cell10.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(11);
                cell11.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(12);
                cell12.setCellValue((String)sd.get("practicingScopeName"));
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(13);
                cell13.setCellValue((String)sd.get("registeManua"));
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(14);
                cell14.setCellValue("是");
                cell14.setCellStyle(styleCenter);
                HSSFCell cell15 = rowDepts.createCell(15);
                cell15.setCellValue((String)sd.get("isPass"));
                cell15.setCellStyle(styleCenter);
                HSSFCell cell16 = rowDepts.createCell(16);
                cell16.setCellValue((String) sd.get("auditReason"));
                cell16.setCellStyle(styleCenter);
                HSSFCell cell17 = rowDepts.createCell(17);
                cell17.setCellValue((String) sd.get("auditStatusName"));
                cell17.setCellStyle(styleCenter);
                HSSFCell cell18 = rowDepts.createCell(18);
                cell18.setCellValue((String) sd.get("idNo"));
                cell18.setCellStyle(styleCenter);
                HSSFCell cell19 = rowDepts.createCell(19);
                cell19.setCellValue((String) sd.get("userPhone"));
                cell19.setCellStyle(styleCenter);
                HSSFCell cell20 = rowDepts.createCell(20);
                cell20.setCellValue((String) sd.get("doctorTypeName"));
                cell20.setCellStyle(styleCenter);
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
    public void chargeExportList2(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit,String roleFlag) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);
        String[] titles = null;

        if("global".equals(roleFlag)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 11));
            titles = new String[]{
                    "序号",
                    "审核状态",
                    "姓名",
                    "培训基地",
                    "国家基地",
                    "报考专业",
                    "培训起止时间",
                    "地市",
                    "学历",
                    "毕业证书编号",
                    "报考资格材料编码",
                    "培训专业",
                    "执业范围"
            };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 5));
            titles = new String[]{
                    "序号",
                    "审核状态",
                    "姓名",
                    "培训基地",
                    "国家基地",
                    "报考专业",
                    "培训起止时间"
            };
        }

        //列宽自适应
        HSSFRow rowDep = sheet.createRow(1);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 2;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(String.valueOf(i));
                cell.setCellStyle(styleCenter);

                HSSFCell cell01 = rowDepts.createCell(1);
                cell01.setCellValue((String)sd.get("auditStatusName"));
                cell01.setCellStyle(styleCenter);

                HSSFCell cell2 = rowDepts.createCell(2);
                cell2.setCellValue((String)sd.get("userName"));
                cell2.setCellStyle(styleCenter);

                HSSFCell cell3 = rowDepts.createCell(3);//培训基地
                cell3.setCellValue((String)sd.get("orgName"));
                cell3.setCellStyle(styleCenter);

                HSSFCell cell4 = rowDepts.createCell(4);
                String orgFlow = (String)sd.get("orgFlow");
                if(StringUtils.isNotBlank(orgFlow)){
                    List<ResJointOrg> resJointOrgs = jointOrgBiz.selectByJointOrgFlow(orgFlow);
                    if(CollectionUtils.isNotEmpty(resJointOrgs)){
                        cell4.setCellValue(resJointOrgs.get(0).getOrgName());//国家基地
                        cell4.setCellStyle(styleCenter);
                    }else{
                        cell4.setCellValue((String)sd.get("orgName"));//国家基地
                        cell4.setCellStyle(styleCenter);
                    }
                }


                HSSFCell cell5 = rowDepts.createCell(5);
                cell5.setCellValue((String)sd.get("changeSpeName"));//国家基地
                cell5.setCellStyle(styleCenter);

                HSSFCell cell6 = rowDepts.createCell(6);
                cell6.setCellValue(sd.get("startDate") + "~" + sd.get("endDate"));
                cell6.setCellStyle(styleCenter);

                if("global".equals(roleFlag)) {
                    HSSFCell cell7 = rowDepts.createCell(7);//地区
                    cell7.setCellValue((String) sd.get("orgCityName"));
                    cell7.setCellStyle(styleCenter);

                    HSSFCell cell8 = rowDepts.createCell(8);
                    cell8.setCellValue((String) sd.get("educationName"));
                    cell8.setCellStyle(styleCenter);

                    HSSFCell cell9 = rowDepts.createCell(9);
                    cell9.setCellValue((String) sd.get("certificateNo"));
                    cell9.setCellStyle(styleCenter);

                    HSSFCell cell10 = rowDepts.createCell(10);
                    cell10.setCellValue((String) sd.get("qualificationMaterialCode"));
                    cell10.setCellStyle(styleCenter);

                    HSSFCell cell11 = rowDepts.createCell(11);
                    cell11.setCellValue((String) sd.get("speName"));
                    cell11.setCellStyle(styleCenter);

                    HSSFCell cell12 = rowDepts.createCell(12);
                    cell12.setCellValue((String) sd.get("practicingScopeName"));
                    cell12.setCellStyle(styleCenter);
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
    public void chargeExportList3(List<Map<String, Object>> list, HttpServletResponse response,String isWaitAudit,String roleFlag) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);

        String[] titles = null;

        if("global".equals(roleFlag)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            titles = new String[]{
                    "序号",
                    "培训基地",
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
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "状态",
                    "证件号码",
                    "手机号码",
                    "人员类型"
            };
        }else if("charge".equals(roleFlag)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            titles = new String[]{
                    "序号",
                    "培训基地",
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
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "状态",
                    "证件号码",
                    "手机号码",
                    "人员类型"
            };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 18));
            titles = new String[]{
                    "序号",
                    "培训基地",
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
                    "执业范围",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "状态",
                    "证件号码",
                    "手机号码",
                    "人员类型"
            };
        }

        //列宽自适应
        HSSFRow rowDep = sheet.createRow(1);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 2;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(String.valueOf(i));
                cell.setCellStyle(styleCenter);
                HSSFCell cell1 = rowDepts.createCell(1);//培训基地
                cell1.setCellValue((String)sd.get("orgName"));
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(2);//国家基地
                String orgFlow = (String)sd.get("orgFlow");
                if(StringUtils.isNotBlank(orgFlow)){
                    List<ResJointOrg> resJointOrgs = jointOrgBiz.selectByJointOrgFlow(orgFlow);
                    if(CollectionUtils.isNotEmpty(resJointOrgs)){
                        cell2.setCellValue(resJointOrgs.get(0).getOrgName());//国家基地
                        cell2.setCellStyle(styleCenter);
                    }else{
                        cell2.setCellValue((String)sd.get("orgName"));
                        cell2.setCellStyle(styleCenter);
                    }
                }
                HSSFCell cell3 = rowDepts.createCell(3);//工作单位
                cell3.setCellValue((String)sd.get("workOrgName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(4);//姓名
                cell4.setCellValue((String)sd.get("userName"));
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(5);//性别
                cell5.setCellValue((String)sd.get("sexName"));
                cell5.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(6);//培训专业
                cell6.setCellValue((String)sd.get("speName"));
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(7);//培训起止时间
                cell7.setCellValue(sd.get("startDate") + "~" + sd.get("endDate"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(8);
                cell8.setCellValue((String)sd.get("educationName"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(9);
                cell9.setCellValue((String)sd.get("certificateNo"));
                cell9.setCellStyle(styleCenter);
                HSSFCell cell10 = rowDepts.createCell(10);
                cell10.setCellValue((String)sd.get("qualificationMaterialName"));
                cell10.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(11);
                cell11.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(12);
                cell12.setCellValue((String)sd.get("practicingScopeName"));
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(13);
                cell13.setCellValue((String)sd.get("registeManua"));
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(14);
                cell14.setCellValue("是");
                cell14.setCellStyle(styleCenter);
                HSSFCell cell15 = rowDepts.createCell(15);
                cell15.setCellValue((String)sd.get("isPass"));
                cell15.setCellStyle(styleCenter);
                HSSFCell cell16 = rowDepts.createCell(16);
                cell16.setCellValue((String) sd.get("auditReason"));
                cell16.setCellStyle(styleCenter);
                HSSFCell cell17 = rowDepts.createCell(17);
                cell17.setCellValue((String) sd.get("auditStatusName"));
                cell17.setCellStyle(styleCenter);
                HSSFCell cell18 = rowDepts.createCell(18);
                cell18.setCellValue((String) sd.get("idNo"));
                cell18.setCellStyle(styleCenter);
                HSSFCell cell19 = rowDepts.createCell(19);
                cell19.setCellValue((String) sd.get("userPhone"));
                cell19.setCellStyle(styleCenter);
                HSSFCell cell20 = rowDepts.createCell(20);
                cell20.setCellValue((String) sd.get("doctorTypeName"));
                cell20.setCellStyle(styleCenter);
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
    public void chargeExportListNew(List<Map<String,Object>> list, HttpServletResponse response,String isWaitAudit) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell hSSFCell1 = row1.createCell(0);
        hSSFCell1.setCellValue("填报单位（公章）:                       填表人：                     联系电话：                              年       月        日");
        styleTwo.setFont(fontTwo);
        hSSFCell1.setCellStyle(styleThree);
        String[] titles =null;
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isWaitAudit)) {
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 37));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)37));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "是否协同",
                    "国家基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "证件类型",
                    "证件号码",
                    "民族",
                    "培训类别",
                    "培训专业",
                    "报考专业",
                    "培训起止时间",
                    "学历",
                    "学位类型",
                    "毕业学校",
                    "毕业时间",
                    "毕业专业",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "执业资格材料编码",
                    "执业范围",
                    "是否为军队人员",
                    "是否为西部支援住院医师",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "数据填写平均比例",
                    "数据补填平均比例",
                    "数据审核平均比例",
                    "培养年限",
                    "轮转总时长",
                    "标准科室数",
                    "出科考核表上传数",
                    "出科考核表上传比例",
                    "执业类别",
                    "联系地址"
            };
        }else{
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 37));
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, (short)1, 0, (short)37));
            titles= new String[]{
                    "序号",
                    "地区",
                    "培训基地",
                    "是否协同",
                    "国家基地",
                    "工作单位",
                    "姓名",
                    "性别",
                    "证件类型",
                    "证件号码",
                    "民族",
                    "培训类别",
                    "培训专业",
                    "报考专业",
                    "培训起止时间",
                    "学历",
                    "学位类型",
                    "毕业学校",
                    "毕业时间",
                    "毕业专业",
                    "毕业证书编号",
                    "报考资格材料（三选一）",
                    "执业资格材料编码",
                    "执业范围",
                    "是否为军队人员",
                    "是否为西部支援住院医师",
                    "是否完成轮转培训计划",
                    "是否在上报的在培人员信息中",
                    "公共课考核",
                    "备注",
                    "数据填写平均比例",
                    "数据补填平均比例",
                    "数据审核平均比例",
                    "培养年限",
                    "轮转总时长",
                    "标准科室数",
                    "出科考核表上传数",
                    "出科考核表上传比例",
                    "执业类别",
                    "联系地址"
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
                if (sd.get("countryOrgFlow") == null || sd.get("orgFlow").equals(sd.get("countryOrgFlow"))) {
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
                HSSFCell cell30 = rowDepts.createCell(8);
                cell30.setCellValue((String)sd.get("cretTypeName"));
                cell30.setCellStyle(styleCenter);
                HSSFCell cell31 = rowDepts.createCell(10);
                cell31.setCellValue((String)sd.get("nationName"));
                cell31.setCellStyle(styleCenter);
                HSSFCell cell32 = rowDepts.createCell(11);
                cell32.setCellValue((String)sd.get("trainingTypeName"));
                cell32.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(12);
                cell7.setCellValue((String)sd.get("speName"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(13);
                cell8.setCellValue((String)sd.get("changeSpeName"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(14);
                cell9.setCellValue(sd.get("startDate") + "~" + sd.get("endDate"));
                cell9.setCellStyle(styleCenter);

                HSSFCell cell10 = rowDepts.createCell(15);
                cell10.setCellValue((String)sd.get("educationName"));
                cell10.setCellStyle(styleCenter);

                UserResumeExtInfoForm userResumeExt = null;
                try {
                    String doctorFlow = (String)sd.get("doctorFlow");
                    PubUserResume userResume = userResumeBiz.readPubUserResume(doctorFlow);
                    if(userResume != null){
                        userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
                    }
                } catch (DocumentException e) {
                    logger.error("", e);
                }
                String degreeCategoryName = (String)sd.get("degreeCategoryName");
                if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeTypeName())){
                    degreeCategoryName = userResumeExt.getDoctorDegreeTypeName();
                }else if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeTypeName())){
                    degreeCategoryName = userResumeExt.getMasterDegreeTypeName();
                }else if(StringUtil.isNotBlank(userResumeExt.getDegreeName())){
                    degreeCategoryName = userResumeExt.getDegreeName();
                }
                if("科学型".equals(degreeCategoryName)){
                    degreeCategoryName = "学术型";
                }
                HSSFCell cell33 = rowDepts.createCell(16);
                cell33.setCellValue(degreeCategoryName);
                cell33.setCellStyle(styleCenter);
                HSSFCell cell34 = rowDepts.createCell(17);
                cell34.setCellValue((String)sd.get("graduatedName"));
                cell34.setCellStyle(styleCenter);
                HSSFCell cell35 = rowDepts.createCell(18);
                cell35.setCellValue((String)sd.get("graduationTime"));
                cell35.setCellStyle(styleCenter);
                HSSFCell cell36 = rowDepts.createCell(19);
                cell36.setCellValue((String)sd.get("specialized"));
                cell36.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(20);
                cell11.setCellValue((String)sd.get("certificateNo"));
                cell11.setCellStyle(styleCenter);
                HSSFCell cell12 = rowDepts.createCell(21);
                cell12.setCellValue((String)sd.get("qualificationMaterialName"));
                cell12.setCellStyle(styleCenter);
                HSSFCell cell13 = rowDepts.createCell(22);
                cell13.setCellValue((String)sd.get("qualificationMaterialCode"));
                cell13.setCellStyle(styleCenter);
                HSSFCell cell14 = rowDepts.createCell(23);
                cell14.setCellValue((String)sd.get("practicingScopeName"));
                cell14.setCellStyle(styleCenter);

                if (userResumeExt != null) {
                    // 是否军队人员
                    String armyType = userResumeExt.getArmyType();
                    String armyName = "否";
                    if (StringUtil.isNotBlank(armyType)) {
                        armyName = com.pinde.core.common.enums.ArmyTypeEnum.getNameById(armyType);
                    }
                    HSSFCell cell371 = rowDepts.createCell(24);
                    cell371.setCellValue(armyName);
                    cell371.setCellStyle(styleCenter);

                    String westernSupportResidents = "";
                    if (StringUtil.isNotBlank(userResumeExt.getWesternSupportResidents())) {
                        if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                            westernSupportResidents = "是";
                        } else if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                            westernSupportResidents = "否";
                        }
                    }
                    HSSFCell cell37 = rowDepts.createCell(25);
                    cell37.setCellValue(westernSupportResidents);
                    cell37.setCellStyle(styleCenter);
                }
                HSSFCell cell15 = rowDepts.createCell(26);
                cell15.setCellValue((String)sd.get("registeManua"));
                cell15.setCellStyle(styleCenter);
                HSSFCell cell16 = rowDepts.createCell(27);
                cell16.setCellValue("是");
                cell16.setCellStyle(styleCenter);
                HSSFCell cell17 = rowDepts.createCell(28);
                cell17.setCellValue((String)sd.get("isPass"));
                cell17.setCellStyle(styleCenter);
                HSSFCell cell18 = rowDepts.createCell(29);
                cell18.setCellValue((String) sd.get("auditReason"));
                cell18.setCellStyle(styleCenter);


                HSSFCell cell19 = rowDepts.createCell(9);
                cell19.setCellValue((String) sd.get("idNo"));
                cell19.setCellStyle(styleCenter);
                HSSFCell cell21 = rowDepts.createCell(30);
                cell21.setCellValue(sd.get("avgComplete") + "%");
                cell21.setCellStyle(styleCenter);
                HSSFCell cell23 = rowDepts.createCell(31);
                cell23.setCellValue(sd.get("avgOutComplete") + "%");
                cell23.setCellStyle(styleCenter);
                HSSFCell cell24 = rowDepts.createCell(32);
                cell24.setCellValue(sd.get("avgAudit") + "%");
                cell24.setCellStyle(styleCenter);
                HSSFCell cell25 = rowDepts.createCell(33);
                cell25.setCellValue((String) sd.get("trainYearName"));
                cell25.setCellStyle(styleCenter);
                HSSFCell cell26 = rowDepts.createCell(34);
                cell26.setCellValue((String) sd.get("allSchMonth"));
                cell26.setCellStyle(styleCenter);
                String allNum=(String) sd.get("allNum");
                String upNum=(String) sd.get("upNum");
                HSSFCell cell27 = rowDepts.createCell(35);
                cell27.setCellValue(allNum);
                cell27.setCellStyle(styleCenter);
                HSSFCell cell28 = rowDepts.createCell(36);
                cell28.setCellValue(upNum);
                cell28.setCellStyle(styleCenter);
                String upPer="0%";
                if(Integer.valueOf(allNum)>0&&Integer.valueOf(upNum)>0)
                {
                    double per = BigDecimal.valueOf(Double.valueOf(upNum) / Double.valueOf(allNum) * 100).setScale(0, RoundingMode.HALF_UP).doubleValue();
                    if(per>100)
                    {
                        per=100;
                    }
                    upPer=per+"%";
                }
                HSSFCell cell29 = rowDepts.createCell(37);
                cell29.setCellValue(upPer);
                cell29.setCellStyle(styleCenter);
                if (userResumeExt != null) {
                    String practicingCategoryName = userResumeExt.getPracticingCategoryName();
                    HSSFCell cell38 = rowDepts.createCell(38);
                    cell38.setCellValue(practicingCategoryName);
                    cell38.setCellStyle(styleCenter);
                }
                HSSFCell cell39 = rowDepts.createCell(39);
                cell39.setCellValue((String)sd.get("userAddress"));
                cell39.setCellStyle(styleCenter);
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
    public List<Map<String, Object>> chargeQueryApplyListNew(Map<String, Object> param) {
        return graduationApplyExtMapper.chargeQueryApplyListNew(param);
    }

    @Override
    public int saveBatchAudit(List<JsresGraduationApply> applies, List<JsresGraduationApplyLog> logs) {
        for(JsresGraduationApply a:applies)
           saveApply(a);
        for(JsresGraduationApplyLog a:logs)
           saveApplyLog(a);
        return 1;
    }

    @Override
    public void exportCountryList(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("国家基地上传导入数据考核名单(省厅审核通过)");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 36));
        String[] titles = new String[]{
                "考区",
                "考点",
                "姓名",
                "性别",
                "证件类型",
                "证件编号",
                "确认证件编号",
                "出生日期",
                "民族",
                "联系地址",
                "邮政编码",
                "报考级别",
                "报考专业",
                "人员类型",
                "是否为军队人员",
                "本次考试是否为初次报考",
                "是否为西部支援住院医师",
                "医师资格取得时间",
                "执业类别",
                "现有学历",
                "现有学位",
                "学位类型",
                "毕业学校",
                "毕业时间",
                "毕业专业",
                "毕业专业备注",
                "毕业证书编号",
                "单位名称（全称）",
                "单位级别",
                "参加工作时间",
                "工作年限",
                "进入基地时间",
                "培训专业",
                "培训专业备注",
                "是否在协同单位完成培训",
                "所在协同单位",
                "医师资格取得时间"
        };

        //列宽自适应
        HSSFRow rowDep = sheet.createRow(1);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 2;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//考区
                cell.setCellValue("");
                cell.setCellStyle(styleCenter);

                HSSFCell cell01 = rowDepts.createCell(1);
                cell01.setCellValue("");
                cell01.setCellStyle(styleCenter);

                HSSFCell cell2 = rowDepts.createCell(2);
                cell2.setCellValue((String)sd.get("userName"));
                cell2.setCellStyle(styleCenter);

                HSSFCell cell3 = rowDepts.createCell(3);
                cell3.setCellValue((String)sd.get("sexName"));
                cell3.setCellStyle(styleCenter);

                HSSFCell cell4 = rowDepts.createCell(4);
                cell4.setCellValue((String)sd.get("cretTypeName"));
                cell4.setCellStyle(styleCenter);

                HSSFCell cell5 = rowDepts.createCell(5);
                cell5.setCellValue((String)sd.get("idNo"));
                cell5.setCellStyle(styleCenter);

                HSSFCell cell6 = rowDepts.createCell(6);
                cell6.setCellValue((String)sd.get("idNo"));
                cell6.setCellStyle(styleCenter);

                HSSFCell cell7 = rowDepts.createCell(7);
                cell7.setCellValue((String)sd.get("userBirthday"));
                cell7.setCellStyle(styleCenter);

                HSSFCell cell8 = rowDepts.createCell(8);
                cell8.setCellValue((String)sd.get("nationName"));
                cell8.setCellStyle(styleCenter);

                HSSFCell cell9 = rowDepts.createCell(9);
                cell9.setCellValue("");
                cell9.setCellStyle(styleCenter);

                HSSFCell cell10 = rowDepts.createCell(10);
                cell10.setCellValue("");
                cell10.setCellStyle(styleCenter);

                HSSFCell cell11 = rowDepts.createCell(11);
                cell11.setCellValue((String)sd.get("trainingTypeName"));
                cell11.setCellStyle(styleCenter);

                HSSFCell cell12 = rowDepts.createCell(12);
                cell12.setCellValue((String)sd.get("changeSpeName"));
                cell12.setCellStyle(styleCenter);

                HSSFCell cell13 = rowDepts.createCell(13);
                String doctorType = (String)sd.get("doctorTypeId");
                if("Company".equals(doctorType)){
                    cell13.setCellValue("本单位住院医师");
                }else if("CompanyEntrust".equals(doctorType)){
                    cell13.setCellValue("外单位委托培养住院医师");
                }else if("Social".equals(doctorType)){
                    cell13.setCellValue("面向社会招收住院医师");
                }else if("Graduate".equals(doctorType)){
                    cell13.setCellValue("全日制硕士专业研究生");
                }else{
                    cell13.setCellValue("其他人员");
                }
                cell13.setCellStyle(styleCenter);

                UserResumeExtInfoForm userResumeExt = null;
                try {
                    String doctorFlow = (String)sd.get("doctorFlow");
                    PubUserResume userResume = userResumeBiz.readPubUserResume(doctorFlow);
                    if(userResume != null){
                        userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
                    }
                } catch (DocumentException e) {
                    logger.error("", e);
                }
                if(null != userResumeExt){
                    String armyType = userResumeExt.getArmyType();
                    String armyName = "否";
                    if (StringUtil.isNotBlank(armyType)) {
                        armyName = com.pinde.core.common.enums.ArmyTypeEnum.getNameById(armyType);
                    }
                    HSSFCell cell14 = rowDepts.createCell(14);
                    cell14.setCellValue(armyName);
                    cell14.setCellStyle(styleCenter);

                    String westernSupportResidents = "";
                    if (StringUtil.isNotBlank(userResumeExt.getWesternSupportResidents())) {
                        if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                            westernSupportResidents = "是";
                        } else if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                            westernSupportResidents = "否";
                        }
                    }
                    HSSFCell cell16 = rowDepts.createCell(16);
                    cell16.setCellValue(westernSupportResidents);
                    cell16.setCellStyle(styleCenter);

                    String haveQualificationCertificateTime = "";
                    if (StringUtil.isNotBlank(userResumeExt.getHaveQualificationCertificateTime())) {
                        haveQualificationCertificateTime = userResumeExt.getHaveQualificationCertificateTime().substring(0,7);
                    }
                    HSSFCell cell17 = rowDepts.createCell(17);
                    cell17.setCellValue(haveQualificationCertificateTime);
                    cell17.setCellStyle(styleCenter);

                    HSSFCell cell36 = rowDepts.createCell(36);
                    cell36.setCellValue(haveQualificationCertificateTime);
                    cell36.setCellStyle(styleCenter);

                    String practicingCategoryName = "";//执业类别
                    if (StringUtil.isNotBlank(userResumeExt.getPracticingCategoryName())) {
                        practicingCategoryName = userResumeExt.getPracticingCategoryName();
                    }
                    HSSFCell cell18 = rowDepts.createCell(18);
                    cell18.setCellValue(practicingCategoryName);
                    cell18.setCellStyle(styleCenter);

                    String educationName = (String)sd.get("educationName");
                    if("大专".equals(educationName)){
                        educationName = "专科";
                    }else if("本科".equals(educationName)){
                        educationName = "本科";
                    }else if("博士研究生".equals(educationName) || "硕士研究生".equals(educationName)){
                        educationName = "研究生";
                    }else{
                        educationName = "中专";
                    }
                    HSSFCell cell19 = rowDepts.createCell(19);//学历
                    cell19.setCellValue(educationName);
                    cell19.setCellStyle(styleCenter);

                    HSSFCell cell20 = rowDepts.createCell(20); //学位
                    String degreeCategoryName = (String)sd.get("degreeCategoryName");
                    if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeTypeName())){//博士
                        degreeCategoryName = userResumeExt.getDoctorDegreeTypeName();
                        cell20.setCellValue("博士");
                    }else if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeTypeName())){//硕士
                        degreeCategoryName = userResumeExt.getMasterDegreeTypeName();
                        cell20.setCellValue("硕士");
                    }else if(StringUtil.isNotBlank(userResumeExt.getDegreeName())){
                        degreeCategoryName = userResumeExt.getDegreeName();
                        cell20.setCellValue("学士");
                    }else{
                        cell20.setCellValue("无");
                    }
                    cell20.setCellStyle(styleCenter);

                    if(!"科学型".equals(degreeCategoryName) && !"专业型".equals(degreeCategoryName)){
                        degreeCategoryName = "无";
                    }
                    HSSFCell cell21 = rowDepts.createCell(21);//学位类型
                    cell21.setCellValue(degreeCategoryName);
                    cell21.setCellStyle(styleCenter);
                }

                HSSFCell cell15 = rowDepts.createCell(15);
                cell15.setCellValue((String)sd.get("signupStatus"));
                cell15.setCellStyle(styleCenter);

                HSSFCell cell22 = rowDepts.createCell(22);
                cell22.setCellValue((String)sd.get("graduatedName"));
                cell22.setCellStyle(styleCenter);

                HSSFCell cell23 = rowDepts.createCell(23);
                cell23.setCellValue((String)sd.get("graduationTime"));
                cell23.setCellStyle(styleCenter);

                HSSFCell cell24 = rowDepts.createCell(24);
                cell24.setCellValue((String)sd.get("specialized"));
                cell24.setCellStyle(styleCenter);

                HSSFCell cell25 = rowDepts.createCell(25);
                cell25.setCellValue("");
                cell25.setCellStyle(styleCenter);

                HSSFCell cell26 = rowDepts.createCell(26);//毕业证书编号
                cell26.setCellValue("");
                cell26.setCellStyle(styleCenter);

                HSSFCell cell27 = rowDepts.createCell(27);
                cell27.setCellValue("");
                cell27.setCellStyle(styleCenter);

                HSSFCell cell28 = rowDepts.createCell(28);//单位名称
                cell28.setCellValue("");
                cell28.setCellStyle(styleCenter);

                HSSFCell cell29 = rowDepts.createCell(29);
                cell29.setCellValue("");
                cell29.setCellStyle(styleCenter);

                HSSFCell cell30 = rowDepts.createCell(30);
                cell30.setCellValue("");
                cell30.setCellStyle(styleCenter);

                HSSFCell cell31 = rowDepts.createCell(31);//进入基地时间
                cell31.setCellValue((String)sd.get("startDate"));
                cell31.setCellStyle(styleCenter);

                HSSFCell cell32 = rowDepts.createCell(32);
                cell32.setCellValue((String)sd.get("speName"));
                cell32.setCellStyle(styleCenter);

                HSSFCell cell33 = rowDepts.createCell(33);
                cell33.setCellValue("");
                cell33.setCellStyle(styleCenter);

                HSSFCell cell34 = rowDepts.createCell(34);
                cell34.setCellValue((String)sd.get("isJointOrg"));
                cell34.setCellStyle(styleCenter);

                HSSFCell cell35 = rowDepts.createCell(35);
                cell35.setCellValue("");
                cell35.setCellStyle(styleCenter);

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
