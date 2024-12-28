package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.sci.dao.JsresDeptConfigMapper;
import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.ResOrgCkxzMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResPowerCfgBizImpl implements IJsResPowerCfgBiz {


    private static final Logger logger = LoggerFactory.getLogger(JsResPowerCfgBizImpl.class);


    @Autowired
    private JsresPowerCfgMapper jsresPowerCfgMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private JsresDeptConfigMapper deptConfigMapper;
    @Autowired
    private ResOrgCkxzMapper resOrgCkxzMapper;

    @Override
    public JsresPowerCfg read(String cfgCode) {
        return jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
    }

    @Override
    public int add(JsresPowerCfg cfg) {
        GeneralMethod.setRecordInfo(cfg, true);
        return jsresPowerCfgMapper.insert(cfg);
    }

    @Override
    public int mod(JsresPowerCfg cfg) {
        GeneralMethod.setRecordInfo(cfg, false);
        return jsresPowerCfgMapper.updateByPrimaryKeySelective(cfg);
    }

    @Override
    public int saveList(List<JsresPowerCfg> cfgList) {
        if(cfgList!=null&&cfgList.size()>0) {
            for (JsresPowerCfg jsresPowerCfg : cfgList) {
                JsresPowerCfg temp = this.read(jsresPowerCfg.getCfgCode());
                if (temp == null) {
                    this.add(jsresPowerCfg);
                } else {
                    this.mod(jsresPowerCfg);
                }
            }
            return cfgList.size();
        }
        return 0;
    }

    @Override
    public int save(JsresPowerCfg cfg) {
        if(cfg!=null)
        {
            JsresPowerCfg temp = this.read(cfg.getCfgCode());
            if (temp == null) {
                return this.add(cfg);
            } else {
                return this.mod(cfg);
            }
        }
        return 0;
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

    @Override
    public void exportInfo(List<Map<String,Object>> list,List<String> docTypeList, HttpServletResponse response) throws IOException{
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
        List<String> titleList=new ArrayList<>();

        titleList.add("培训基地");
        titleList.add("年级");
        titleList.add("姓名");
        titleList.add("证件号码");
//        if(docTypeList.size() == 1 && "Graduate".equals(docTypeList.get(0))){
//            titleList.add("派送学校");
//        }
        titleList.add("人员类别");
        titleList.add("培训专业");
        titleList.add("是否开放app登录权限");
        titleList.add("是否开放学员付费菜单");
        titleList.add("是否开放学员出科考核表");
        titleList.add("是否开放结业理论模拟考核");
        titleList.add("是否开放学员培训手册");
        titleList.add("派送学校");
        String[] titles=new String[titleList.size()];
        titleList.toArray(titles);
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        rowDep.setHeightInPoints(20);
//        HSSFCell cellOne = rowDep.createCell(0);
//        cellOne.setCellStyle(styleCenter);
//        cellOne.setCellValue("学员信息表");

        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 10 * 50);//设置内容表格宽度
        }

//        HSSFCell cellTitle = null;
//        for (int i = 0; i < titles.length; i++) {
//            cellTitle = rowTwo.createCell(i);
//            cellTitle.setCellValue(titles[i]);
//            cellTitle.setCellStyle(styleCenter);
//            sheet.setColumnWidth(i, titles.length * 2 * 256);
//        }
        int rownum = 1;
        if(list!=null&&!list.isEmpty()){
            for(Map<String,Object> map : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue((String) map.get("orgName"));//培训基地
                cell.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(1);//年级
                cell2.setCellValue((String) map.get("sessionNumber"));
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(2);//姓名
                cell3.setCellValue((String)map.get("userName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(3);//证件号码
                cell4.setCellValue((String)map.get("idNo"));
                cell4.setCellStyle(styleCenter);
                int columnNum = 4;
//                if(docTypeList.size() == 1 && "Graduate".equals(docTypeList.get(0))){
//                    HSSFCell cell5 = rowDepts.createCell(columnNum);//证件号码
//                    cell5.setCellValue((String)map.get("workOrgName"));
//                    cell5.setCellStyle(styleCenter);
//                }
                HSSFCell cell61 = rowDepts.createCell(columnNum++);//人员类别
                cell61.setCellValue((String)map.get("doctorTypeName"));
                cell61.setCellStyle(styleCenter);
                HSSFCell cell62 = rowDepts.createCell(columnNum++);//培训专业
                cell62.setCellValue((String)map.get("trainingSpeName"));
                cell62.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(columnNum++);//app权限
                cell6.setCellValue((String)map.get("appLoginFlag"));
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(columnNum++);//是否开放学员付费菜单
                cell7.setCellValue((String)map.get("appMenuFlag"));
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(columnNum++);//是否开放学员出科考核表
                cell8.setCellValue((String)map.get("examFlag"));
                cell8.setCellStyle(styleCenter);
                HSSFCell cell9 = rowDepts.createCell(columnNum++);//
                cell9.setCellValue((String)map.get("graduationExamFlag"));
                cell9.setCellStyle(styleCenter);
                HSSFCell cell10 = rowDepts.createCell(columnNum++);//是否开放学员手册
                cell10.setCellValue((String)map.get("manualFlag"));
                cell10.setCellStyle(styleCenter);
                HSSFCell cell11 = rowDepts.createCell(columnNum);//派送学校
                String workOrgName = "";
                if(null != map.get("workOrgName")){
                    workOrgName = map.get("workOrgName").toString();
                }
                cell11.setCellValue(workOrgName);
                cell11.setCellStyle(styleCenter);
                rownum++;
            }
        }

        String fileName = "学员信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public int saveDeptConfig(JsresDeptConfig deptConfig) {
        if(StringUtil.isNotBlank(deptConfig.getCfgFlow())){
            GeneralMethod.setRecordInfo(deptConfig, false);
            return deptConfigMapper.updateByPrimaryKeySelective(deptConfig);
        }else{
            deptConfig.setCfgFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(deptConfig, true);
            return deptConfigMapper.insert(deptConfig);
        }
    }

    @Override
    public int saveCkxzConfig(ResOrgCkxz orgCkxz) {
        orgCkxz.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        orgCkxz.setOrgName(GlobalContext.getCurrentUser().getOrgName());

        ResOrgCkxzExample example = new ResOrgCkxzExample();
        ResOrgCkxzExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andOrgFlowEqualTo(orgCkxz.getOrgFlow())
                .andSessionGradeEqualTo(orgCkxz.getSessionGrade());
        List<ResOrgCkxz> list = resOrgCkxzMapper.selectByExample(example);
        if (StringUtil.isNotBlank(orgCkxz.getRecordFlow())){
            if (null != list && list.size()>0){
                ResOrgCkxz resOrgCkxz = list.get(0);
                if (!resOrgCkxz.getRecordFlow().equals(orgCkxz.getRecordFlow())){
                    return -1;
                }
            }
            GeneralMethod.setRecordInfo(orgCkxz, false);
            return resOrgCkxzMapper.updateByPrimaryKey(orgCkxz);
        }else {
            if (null != list && list.size()>0){
                return -1;
            }
            orgCkxz.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(orgCkxz, true);
            return resOrgCkxzMapper.insert(orgCkxz);
        }
    }

    @Override
    public List<JsresDeptConfig> searchDeptConfigs(String orgFlow) {
        JsresDeptConfigExample example = new JsresDeptConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("DEPT_FLOW DESC,CREATE_TIME DESC");
        return deptConfigMapper.selectByExample(example);
    }

    @Override
    public JsresDeptConfig searchDeptCfgByCfgFlow(String cfgFlow) {
        return deptConfigMapper.selectByPrimaryKey(cfgFlow);
    }

    @Override
    public JsresDeptConfig searchBaseDeptConfig(String orgFlow) {
        JsresDeptConfigExample example = new JsresDeptConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowIsNull();
        List<JsresDeptConfig> list = deptConfigMapper.selectByExample(example);
        if(null != list && list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public JsresDeptConfig searchDeptCfg(String orgFlow, String schDeptFlow) {
        JsresDeptConfigExample example = new JsresDeptConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowEqualTo(schDeptFlow);
        List<JsresDeptConfig> list = deptConfigMapper.selectByExample(example);
        if(null != list && list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int deleteCfg(String cfgCode) {
        return jsresPowerCfgMapper.deleteByPrimaryKey(cfgCode);
    }

    @Override
    public String saveCfgDoctorFlows(Map<String, String> param,List<String> doctorFlows) {
        if(null != doctorFlows && doctorFlows.size()>0){
            for (String doctorFlow:doctorFlows) {
                JsresPowerCfg cfg = new JsresPowerCfg();
                cfg.setCfgCode(param.get("cfg")+doctorFlow);
                cfg.setCfgValue(param.get("cfgValue"));
                cfg.setCfgDesc(param.get("cfgDesc"));
                cfg.setCheckStatusId("Passed");
                cfg.setCheckStatusName("审核通过");
                JsresPowerCfg jpc = jsresPowerCfgMapper.selectByPrimaryKey(cfg.getCfgCode());
                if (null == jpc){
                    this.add(cfg);
                }else{
                    this.mod(cfg);
                }
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @Override
    public ExcelUtile importTime(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "idNo",
                    "powerStartTime",
                    "powerEndTime"
            };
            return  ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String,Object> data=datas.get(i);
                        JsresPowerCfg cfg=new JsresPowerCfg();
                        cfg.setCfgCode("jsres_doctor_data_time_"+data.get("userFlow"));
                        cfg.setCfgValue(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        cfg.setCfgDesc("学员数据审核权限期限");
                        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        cfg.setPowerStartTime((String) data.get("powerStartTime"));
                        cfg.setPowerEndTime((String) data.get("powerEndTime"));
                        count+=save(cfg);
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if (!"ImportTime".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的模板！！");
                        return ExcelUtile.RETURN;
                    }
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    boolean f=true;
                    String startTime="";
                    String endTime="";
                    String userFlow="";
                    for (Object key1 : data.keySet()) {
                        String key = (String) key1;
                        String value = (String) data.get(key);
                        if ("idNo".equals(key)) {
                            if(StringUtil.isBlank(value))
                            {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            SysUser user = userBiz.findByIdNo(value);
                            if (user==null)
                            {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行身份证号为"+value+"用户不存在，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }else{
                                userFlow=user.getUserFlow();
                            }
                        } else if ("powerStartTime".equals(key)) {
                            if(StringUtil.isNotBlank(value))
                            {
                                f=checkTime(value);
                                if(!f){
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间格式不正确，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            }else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            startTime=value;
                        } else if ("powerEndTime".equals(key)) {
                            if(StringUtil.isNotBlank(value))
                            {
                                f=checkTime(value);
                                if(!f){
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限结束时间格式不正确，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            }else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限结束时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            endTime=value;
                        }
                    }
                    if(StringUtil.isNotBlank(startTime)&&StringUtil.isNotBlank(endTime)&&startTime.compareTo(endTime)>0)
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间大于权限结束时间，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isNotBlank(userFlow))
                    {
                        data.put("userFlow",userFlow);
                    }else{
                        f=false;
                    }
                    data.put("f",f);
                    return null;
                }

                private boolean checkTime(String time) {
                    boolean f=true;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(time);
                    } catch (ParseException e) {
                        f=false;
                    }
                    return f;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String saveCkshConfig(String[] mulDeptFlow) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //批量废止
        JsresPowerCfgExample example = new JsresPowerCfgExample();
        JsresPowerCfgExample.Criteria criteria = example.createCriteria();
        criteria.andCfgValueEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andCfgCodeLike("%jsres_cksh_"+orgFlow+"_%");
        jsresPowerCfgMapper.deleteByExample(example);

        //查询新增
        if (null!=mulDeptFlow){
            List<String> list = Arrays.asList(mulDeptFlow);
            JsresPowerCfg cfg = new JsresPowerCfg();
            cfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
            cfg.setCfgDesc("科主任审核学员技能出科成绩-科室限制");
            GeneralMethod.setRecordInfo(cfg,true);
            int count=0;
            for (String s : list) {
                cfg.setCfgCode("jsres_cksh_"+orgFlow+"_"+s);
                count = jsresPowerCfgMapper.insert(cfg)+count;
            }
            if (count==list.size()){
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @Override
    public List<JsresPowerCfg> selectLikeCode(String code) {
        JsresPowerCfgExample example = new JsresPowerCfgExample();
        JsresPowerCfgExample.Criteria criteria = example.createCriteria();
        criteria.andCfgValueEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andCfgCodeLike(code);
        return jsresPowerCfgMapper.selectByExample(example);
    }
}
