package com.pinde.sci.biz.res.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.INjResExamBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.impl.UserBizImpl;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.TjDocinfoMapper;
import com.pinde.sci.dao.base.TjExamInfoMapper;
import com.pinde.sci.dao.res.NjDocinfoExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.NjDocinfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class NjResExamBizImpl implements INjResExamBiz {
    @Autowired
    private TjDocinfoMapper docinfoMapper;

    @Autowired
    private NjDocinfoExtMapper docinfoExtMapper;

    @Autowired
    private TjExamInfoMapper examInfoMapper;

    @Autowired
    private UserBizImpl userBizImpl;

    @Autowired
    private ResDoctorBizImpl doctorBiz;

    @Autowired
    private IOrgBiz orgBiz;

    static Logger logger = Logger.getLogger(NjResExamBizImpl.class);

    @Override
    public List<NjDocinfoExt> searchNjDocinfoExt(TjDocinfo searchDocinfo) {
        return docinfoExtMapper.searchNjDocinfo(searchDocinfo);
    }

    private static int  sequenceNumber = 1;

    @Override
    public List<NjDocinfoExt> readNjDocinfo(TjDocinfo searchDocinfo,List<String> orgNames) {
        TjDocinfoExample tjDocinfoExample = new TjDocinfoExample();
        TjDocinfoExample.Criteria criteria = tjDocinfoExample.createCriteria();
        if(StringUtil.isNotEmpty(searchDocinfo.getIdNo())){
            criteria.andIdNoEqualTo(searchDocinfo.getIdNo());
        }
        if(StringUtil.isNotEmpty(searchDocinfo.getUserName())){
            criteria.andUserNameEqualTo(searchDocinfo.getUserName());
        }
        if(CollectionUtils.isNotEmpty(orgNames)){
            criteria.andOrgFlowIn(orgNames);
        }
        if(StringUtil.isNotEmpty(searchDocinfo.getSpeName())){
            criteria.andSpeNameEqualTo(searchDocinfo.getSpeName());
        }
        if(StringUtil.isNotEmpty(searchDocinfo.getCreateUserFlow())){
            criteria.andCreateUserFlowEqualTo(searchDocinfo.getCreateUserFlow());
        }
        criteria.andDocroleNotEqualTo("4");

        return docinfoExtMapper.selectByParam(tjDocinfoExample);
    }

    @Override
    public void editNjDocinfo(List<NjDocinfoExt> extList, TjExamInfo examInfo) {
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        if(CollectionUtils.isNotEmpty(extList)){
            for (NjDocinfoExt njDocinfo : extList) {
                if(StringUtils.isEmpty(njDocinfo.getTicketNum())){
                    //准考证生成 32+四位市级+四位专业+证件号后四位
                    String speCode = "";
                    String idNo = njDocinfo.getIdNo();
                    if(idNo.length() < 4){
                        continue;
                    }
                    String replace = idNo.substring(idNo.length()-4,idNo.length());
                    String cityCode = docinfo.getCityCode();
                    Map<String, String> speMap = InitConfig.getDictNameMap("DoctorTrainingSpe");
                    speMap.putAll(InitConfig.getDictNameMap("AssiGeneral"));
                    speMap.put("全科转岗","0998");
                    for (String key : speMap.keySet()) {
                        if (key.equals(njDocinfo.getSpeName())) {
                            speCode = speMap.get(key);
                            break;
                        }
                    }
                    if(StringUtil.isEmpty(cityCode) || StringUtil.isEmpty(idNo) || StringUtil.isEmpty(replace) || StringUtil.isEmpty(speCode)){
                        continue;
                    }
                    String resultTickNum = checkTickNum(generateUniqueExamAdmissionNumber(cityCode.substring(0,4),speCode));
                    njDocinfo.setTicketNum(resultTickNum);
                }
                njDocinfo.setAddress(examInfo.getExamAddress());
                njDocinfo.setRemark(examInfo.getRemark());
                njDocinfo.setSitephone(examInfo.getSitePhone());
                njDocinfo.setExamtime(examInfo.getExamTime());
                njDocinfo.setTitle(examInfo.getTitle());
                docinfoMapper.updateByPrimaryKeySelective(njDocinfo);
            }
        }
    }

    @Override
    public ExcelUtile importDocInfoExcel(MultipartFile file) {
        InputStream is = null;
        try{
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            Sheet sheet = wb.getSheetAt(0);
            if(sheet ==null){
               return null;
            }
            final String[] keys = {
                    "userName",
                    "idNo",
                    "speName",
                    "examAddress",
                    "examTime",
                    "userPhone",
                    "sitePhone",
                    "remark",
                    "title"
            };
            return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>(){
                @Override
                public void operExcelData(ExcelUtile eu) {
                    TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
                    List<HashMap> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++){
                        HashMap map = datas.get(i);
                        TjDocinfo tjDocinfo = new TjDocinfo();
                        tjDocinfo.setUserName((String)map.get("userName"));
                        tjDocinfo.setIdNo((String)map.get("idNo"));
                        tjDocinfo.setSpeName((String)map.get("speName"));
                        tjDocinfo.setUserPhone((String)map.get("userPhone"));
                        //匹配考点
//                        TjExamInfo tjExamInfo = new TjExamInfo();
//                        tjExamInfo.setSpeName(tjDocinfo.getSpeName());
//                        tjExamInfo.setCityCode(tjDocinfo.getCityCode()+"00");
//                        if(StringUtils.isNotEmpty(currInfo.getOrgFlow())){
//                            SysOrg sysOrg = orgBiz.readSysOrg(currInfo.getOrgFlow());
//                            if(null != sysOrg){
//                                tjExamInfo.setCreateUserFlow(currInfo.getUserFlow());
//                            }
//                        }
//                        List<TjExamInfo> tjExamInfos = selTjExamInfos(tjExamInfo);
//                        if(CollectionUtils.isNotEmpty(tjExamInfos)){
//                            TjExamInfo tjExamInfo1 = tjExamInfos.get(0);
                            //准考证生成 32+四位市级+四位专业+证件号后四位
                            String speCode = "";
                            String idNo = tjDocinfo.getIdNo();
                            if(idNo.length() < 4){
                                continue;
                            }
                            String replace = idNo.substring(idNo.length()-4,idNo.length());
                            String cityCode = currInfo.getCityCode();
                            Map<String, String> speMap = InitConfig.getDictNameMap("DoctorTrainingSpe");
                            speMap.putAll(InitConfig.getDictNameMap("AssiGeneral"));
                            speMap.put("全科转岗","0998");
                            for (String key : speMap.keySet()) {
                                if (key.equals(tjDocinfo.getSpeName())) {
                                    speCode = speMap.get(key);
                                    break;
                                }
                            }
                            if(StringUtil.isEmpty(cityCode) || StringUtil.isEmpty(idNo) || StringUtil.isEmpty(replace) || StringUtil.isEmpty(speCode)){
                                continue;
                            }
                            tjDocinfo.setTicketNum(generateUniqueExamAdmissionNumber(cityCode.substring(0,4),speCode));
                            tjDocinfo.setAddress((String)map.get("examAddress"));
                            tjDocinfo.setSpeCode(speCode);
                            tjDocinfo.setRemark((String)map.get("remark"));
                            tjDocinfo.setSitephone((String)map.get("sitePhone"));
                            tjDocinfo.setExamtime((String)map.get("examTime"));
                            tjDocinfo.setTitle((String)map.get("title"));
                            count += editDocInfo(tjDocinfo);
//                        }else{
//                            logger.error(JSON.toJSONString(tjDocinfo) + " 未分配考点，不能导入");
//                        }


                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap hashMap, ExcelUtile eu) {
                    return null;
                }
            });
        }catch(Exception e){
            logger.error("考生信息导入异常：",e);
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public ExcelUtile importExamUserExcel(MultipartFile file,String examFlow) {
        InputStream is = null;
        try{
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            Sheet sheet = wb.getSheetAt(0);
            if(sheet ==null){
                return null;
            }
            final String[] keys = {
                    "userName",
                    "idNo",
                    "speName",
                    "userPhone",
                    "cityCode"
            };
            return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>(){
                @Override
                public void operExcelData(ExcelUtile eu) {
                    TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
                    List<HashMap> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++){
                        HashMap map = datas.get(i);
                        TjDocinfo tjDocinfo = new TjDocinfo();
                        tjDocinfo.setUserName((String)map.get("userName"));
                        tjDocinfo.setIdNo((String)map.get("idNo"));
                        tjDocinfo.setSpeName((String)map.get("speName"));
                        tjDocinfo.setUserPhone((String)map.get("userPhone"));
                        tjDocinfo.setOrgName((String)map.get("orgName"));
                        tjDocinfo.setCityCode((String)map.get("cityCode"));
                        //匹配考点
                        TjExamInfo tjExamInfo = new TjExamInfo();
                        tjExamInfo.setRecordFlow(examFlow);
                        List<TjExamInfo> tjExamInfos = selTjExamInfos(tjExamInfo);
                        if(CollectionUtils.isNotEmpty(tjExamInfos)){
                            TjExamInfo tjExamInfo1 = tjExamInfos.get(0);
                            //准考证生成 32+四位市级+四位专业+证件号后四位
                            String speCode = "";
                            String idNo = tjDocinfo.getIdNo();
                            if(idNo.length() < 4){
                                continue;
                            }
                            String replace = idNo.substring(idNo.length()-4,idNo.length());
                            String cityCode = currInfo.getCityCode();
                            Map<String, String> speMap = InitConfig.getDictNameMap("DoctorTrainingSpe");
                            speMap.putAll(InitConfig.getDictNameMap("AssiGeneral"));
                            speMap.put("全科转岗","0998");
                            for (String key : speMap.keySet()) {
                                if (key.equals(tjDocinfo.getSpeName())) {
                                    speCode = speMap.get(key);
                                    break;
                                }
                            }
                            if(StringUtil.isEmpty(cityCode) || StringUtil.isEmpty(idNo) || StringUtil.isEmpty(replace) || StringUtil.isEmpty(speCode)){
                                continue;
                            }
                            if(StringUtils.isBlank(tjDocinfo.getTicketNum()))
                                tjDocinfo.setTicketNum(generateUniqueExamAdmissionNumber(cityCode.substring(0,4),speCode));
                            tjDocinfo.setAddress(tjExamInfo1.getExamAddress());
                            tjDocinfo.setSpeCode(speCode);
                            tjDocinfo.setRemark(tjExamInfo1.getRemark());
                            tjDocinfo.setSitephone(tjExamInfo1.getSitePhone());
                            tjDocinfo.setExamtime(tjExamInfo1.getExamTime());
                            tjDocinfo.setTitle(tjExamInfo1.getTitle());
                            count += editDocInfo(tjDocinfo);
                        }else{
                            logger.error(JSON.toJSONString(tjDocinfo) + " miss exam address,cannot import!!!");
                        }


                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap hashMap, ExcelUtile eu) {
                    return null;
                }
            });
        }catch(Exception e){
            logger.error("导入异常",e);
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public int editDocInfo(TjDocinfo docInfo) {
        TjDocinfo currInfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        if(StringUtil.isBlank(docInfo.getIdNo())){
            return 0;
        }
        docInfo.setIdNo(docInfo.getIdNo().toUpperCase());
        String userFlow = "";
        if(StringUtil.isBlank(docInfo.getUserFlow())){
            TjDocinfoExample example = new TjDocinfoExample();
            TjDocinfoExample.Criteria criteria = example.createCriteria();
            criteria.andIdNoEqualTo(docInfo.getIdNo());
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<TjDocinfo> tjDocinfos = docinfoMapper.selectByExample(example);
            if (tjDocinfos != null && tjDocinfos.size()> 0){
                userFlow = tjDocinfos.get(0).getUserFlow();
            }
            docInfo.setUserFlow(userFlow);
//            docInfo.setCityCode(currInfo.getCityCode());
        }

        if(!StringUtil.isNotBlank(docInfo.getUserFlow())){
            docInfo.setUserFlow(PkUtil.getUUID());
            docInfo.setCreateUserFlow(currInfo.getUserFlow());
            setRecordInfo(docInfo,true);
            return docinfoMapper.insertSelective(docInfo);
        }else {
            setRecordInfo(docInfo,false);
            return docinfoMapper.updateByPrimaryKeySelective(docInfo);
        }
    }

    @Override
    public TjDocinfo readDocInfo(String idNo) {
        TjDocinfoExample example = new TjDocinfoExample();
        TjDocinfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdNoEqualTo(idNo);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<TjDocinfo> tjDocinfos = docinfoMapper.selectByExample(example);
        if(tjDocinfos!= null && tjDocinfos.size() >0){
            return tjDocinfos.get(0);
        }
        return null;
    }

    @Override
    public List<TjExamInfo> selTjExamInfos(TjExamInfo examInfo) {
        TjExamInfoExample tjExamInfoExample = new TjExamInfoExample();
        TjExamInfoExample.Criteria criteria = tjExamInfoExample.createCriteria();
        if(StringUtil.isNotEmpty(examInfo.getCityCode())){
            criteria.andCityCodeEqualTo(examInfo.getCityCode());
        }
        if(StringUtil.isNotEmpty(examInfo.getExamAddress())){
            criteria.andExamAddressLike("%"+examInfo.getExamAddress()+"%");
        }
        if(StringUtil.isNotEmpty(examInfo.getSpeName())){
            criteria.andSpeNameEqualTo(examInfo.getSpeName());
        }
        if(StringUtil.isNotEmpty(examInfo.getOrgFlow())){
            criteria.andOrgFlowEqualTo(examInfo.getOrgFlow());
        }
        if(StringUtil.isNotEmpty(examInfo.getCreateUserFlow())){
            criteria.andCreateUserFlowEqualTo(examInfo.getCreateUserFlow());
        }
        if(StringUtil.isNotEmpty(examInfo.getRecordFlow())){
            criteria.andRecordFlowEqualTo(examInfo.getRecordFlow());
        }
        return examInfoMapper.selectByExample(tjExamInfoExample);
    }

    @Override
    public List<TjExamInfo> selTjExamInfoById(String recordFlow) {
        TjExamInfoExample tjExamInfo = new TjExamInfoExample();
        tjExamInfo.createCriteria().andRecordFlowEqualTo(recordFlow);
        return examInfoMapper.selectByExample(tjExamInfo);
    }

    @Override
    public int editExamInfo(TjExamInfo examInfo) {
        int i = 0;
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        if (StringUtil.isNotEmpty(examInfo.getRecordFlow())) {
            TjExamInfoExample tjExamInfoExample = new TjExamInfoExample();
            tjExamInfoExample.createCriteria().andRecordFlowEqualTo(examInfo.getRecordFlow());
            examInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            examInfo.setModifyTime(DateUtil.getCurrDateTime());
            examInfo.setModifyUserFlow(docinfo.getUserFlow());
            i=examInfoMapper.updateByExampleSelective(examInfo,tjExamInfoExample);
        }else{
            if(StringUtils.isNotEmpty(docinfo.getOrgFlow())){
                SysOrg sysOrg = orgBiz.readSysOrg(docinfo.getOrgFlow());
                if(null != sysOrg){
                    examInfo.setOrgFlow(sysOrg.getOrgFlow());
                }
            }
            examInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            examInfo.setCreateTime(DateUtil.getCurrDateTime());
            examInfo.setCreateUserFlow(docinfo.getUserFlow());
            examInfo.setRecordFlow(PkUtil.getUUID());
            examInfo.setUserFlow(docinfo.getUserFlow());
            examInfo.setCityCode(docinfo.getCityCode());
            i=examInfoMapper.insert(examInfo);
        }
        return i;
    }

    @Override
    public int setExamInfo(String userFlows,String recordFlow) {
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("docinfo");
        int count = 0;
        String[] split = userFlows.split(",");
        for (String s : split) {
            TjDocinfo tjDocinfo = docinfoMapper.selectByPrimaryKey(s);
            TjExamInfoExample tjExamInfoExample = new TjExamInfoExample();
            tjExamInfoExample.createCriteria().andRecordFlowEqualTo(recordFlow);
            List<TjExamInfo> tjExamInfos = examInfoMapper.selectByExample(tjExamInfoExample);
            TjExamInfo tjExamInfo = tjExamInfos.get(0);
            if(StringUtils.isEmpty(tjDocinfo.getTicketNum())){
                //准考证生成 32+四位市级+四位专业+证件号后四位
                String speCode = "";
                String idNo = tjDocinfo.getIdNo();
                if(idNo.length() < 4){
                    return 0;
                }
                String replace = idNo.substring(idNo.length()-4,idNo.length());
                String cityCode = docinfo.getCityCode();
                Map<String, String> speMap = InitConfig.getDictNameMap("DoctorTrainingSpe");
                speMap.putAll(InitConfig.getDictNameMap("AssiGeneral"));
                speMap.put("全科转岗","0998");
                for (String key : speMap.keySet()) {
                    if (key.equals(tjDocinfo.getSpeName())) {
                        speCode = speMap.get(key);
                        break;
                    }
                }
                if(StringUtil.isEmpty(cityCode) || StringUtil.isEmpty(idNo) || StringUtil.isEmpty(replace) || StringUtil.isEmpty(speCode)){
                    return 0;
                }
                String resultTickNum = checkTickNum(generateUniqueExamAdmissionNumber(cityCode.substring(0,4),speCode));
                tjDocinfo.setTicketNum(resultTickNum);
            }
            tjDocinfo.setAddress(tjExamInfo.getExamAddress());
            tjDocinfo.setRemark(tjExamInfo.getRemark());
            tjDocinfo.setSitephone(tjExamInfo.getSitePhone());
            tjDocinfo.setExamtime(tjExamInfo.getExamTime());
            tjDocinfo.setTitle(tjExamInfo.getTitle());
            count += docinfoMapper.updateByPrimaryKeySelective(tjDocinfo);
        }
        return count;
    }


    @Override
    public int delExamInfo(String recordFlow) {
        TjExamInfoExample tjExamInfoExample = new TjExamInfoExample();
        tjExamInfoExample.createCriteria().andRecordFlowEqualTo(recordFlow);
        return examInfoMapper.deleteByExample(tjExamInfoExample);
    }

    public int delDocInfo(String userFlow,String idNo) {
        TjDocinfoExample example = new TjDocinfoExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andIdNoEqualTo(idNo);
        return docinfoMapper.deleteByExample(example);
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

    private void setRecordInfo(TjDocinfo docInfo, boolean isAdd) {
        if(StringUtil.isNotEmpty(docInfo.getIdNo())){
            SysUser byIdNo = userBizImpl.findByIdNo(docInfo.getIdNo());
            if(null != byIdNo){
                ResDoctor byFlow = doctorBiz.findByFlow(byIdNo.getUserFlow());
                if(null != byFlow && StringUtils.isNotEmpty(byFlow.getOrgFlow())){
                    docInfo.setOrgFlow(byFlow.getOrgFlow());
                    docInfo.setOrgCode("20");
                    docInfo.setOrgName(byFlow.getOrgName());
                }else{
                    SysOrg sysOrg = orgBiz.readSysOrgByName(docInfo.getOrgName());
                    if(null != sysOrg){
                        docInfo.setOrgFlow(sysOrg.getOrgFlow());
                        docInfo.setOrgCode("20");
                        docInfo.setOrgName(sysOrg.getOrgName());
                    }else{
                        docInfo.setOrgFlow("12ab132b79cd4493b6f912d03f367ba0");
                        docInfo.setOrgCode("20");
                        docInfo.setOrgName("南京市卫计委");
                    }
                }

            }else{
                if(StringUtils.isNotEmpty(docInfo.getOrgName())){
                    SysOrg sysOrg = orgBiz.readSysOrgByName(docInfo.getOrgName());
                    if(null != sysOrg){
                        docInfo.setOrgFlow(sysOrg.getOrgFlow());
                        docInfo.setOrgCode("20");
                        docInfo.setOrgName(sysOrg.getOrgName());
                    }else {
                        docInfo.setOrgFlow("12ab132b79cd4493b6f912d03f367ba0");
                        docInfo.setOrgCode("20");
                        docInfo.setOrgName("南京市卫计委");
                    }
                }else{
                    docInfo.setOrgFlow("12ab132b79cd4493b6f912d03f367ba0");
                    docInfo.setOrgCode("20");
                    docInfo.setOrgName("南京市卫计委");
                }
            }
        }

        docInfo.setUserPassword(docInfo.getIdNo());
        docInfo.setDocrole("1");
        docInfo.setUserId(docInfo.getIdNo());
        docInfo.setPhonePath(docInfo.getIdNo()+".jpg");
        if (isAdd) {
            docInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            docInfo.setCreateTime(DateUtil.getCurrDateTime());
            //判断准考证是否重复
            String ticketNum = docInfo.getTicketNum();
            TjDocinfoExample example = new TjDocinfoExample();
            TjDocinfoExample.Criteria criteria = example.createCriteria();
            criteria.andTicketNumEqualTo(ticketNum);
            List<TjDocinfo> tjDocinfos = docinfoMapper.selectByExample(example);
            if(CollectionUtils.isNotEmpty(tjDocinfos)){
                String s = docinfoExtMapper.selectMaxTickNumber();
                if(s.contains("X")){
                    s = s.replace("X","9");
                }
                Long i = Long.parseLong(s);
                String ticketNum1 = docInfo.getTicketNum();
                docInfo.setTicketNum(i+1+ticketNum1.substring(ticketNum1.length() - 2,ticketNum1.length()));
            }
//            docInfo.setCreateUserFlow(docInfo.getUserFlow());
        }
        docInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        docInfo.setModifyTime(DateUtil.getCurrDateTime());
        docInfo.setModifyUserFlow(docInfo.getUserFlow());

    }

    @Override
    public void chargeExportList(List<NjDocinfoExt> extList, HttpServletResponse response) throws IOException {
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
        String[] titles =null;
        titles= new String[]{
                "序号",
                "考试地点",
                "姓名",
                "证件号码",
                "培训专业",
                "准考证号"
        };
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if(CollectionUtils.isNotEmpty(extList)){
            int i=1;
            for (NjDocinfoExt njDocinfoExt : extList) {
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(String.valueOf(i));
                cell.setCellStyle(styleCenter);
                HSSFCell cell01 = rowDepts.createCell(1);
                cell01.setCellValue(njDocinfoExt.getAddress());
                cell01.setCellStyle(styleCenter);
                HSSFCell cell1 = rowDepts.createCell(2);
                cell1.setCellValue(njDocinfoExt.getUserName());
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(3);
                cell2.setCellValue(njDocinfoExt.getIdNo());
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(4);
                cell3.setCellValue(njDocinfoExt.getSpeName());
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(5);
                cell4.setCellValue(njDocinfoExt.getTicketNum());
                cell4.setCellStyle(styleCenter);
                rownum++;
                i++;
            }
        }

        String fileName = "考点人员信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public void chargeExportList2(List<NjDocinfoExt> extList, HttpServletResponse response) throws IOException {
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
        String[] titles =null;
        titles= new String[]{
                "序号",
                "姓名",
                "证件号码",
                "培训专业",
                "准考证号",
                "考试地点",
                "考试时间",
                "考生联系电话",
                "考点联系电话",
                "准考证标题",
        };
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if(CollectionUtils.isNotEmpty(extList)){
            int i=1;
            for (NjDocinfoExt njDocinfoExt : extList) {
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(String.valueOf(i));
                cell.setCellStyle(styleCenter);
                HSSFCell cell01 = rowDepts.createCell(1);
                cell01.setCellValue(njDocinfoExt.getUserName());
                cell01.setCellStyle(styleCenter);
                HSSFCell cell1 = rowDepts.createCell(2);
                cell1.setCellValue(njDocinfoExt.getIdNo());
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(3);
                cell2.setCellValue(njDocinfoExt.getSpeName());
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(4);
                cell3.setCellValue(njDocinfoExt.getTicketNum());
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(5);
                cell4.setCellValue(njDocinfoExt.getAddress());
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(6);
                cell5.setCellValue(njDocinfoExt.getExamtime());
                cell5.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(7);
                cell6.setCellValue(njDocinfoExt.getUserPhone());
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(8);
                cell7.setCellValue(njDocinfoExt.getSitephone());
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(9);
                cell8.setCellValue(njDocinfoExt.getTitle());
                cell8.setCellStyle(styleCenter);
                rownum++;
                i++;
            }
        }

        String fileName = "考生信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    public String checkTickNum(String ticketNum){
        //判断准考证是否重复
        TjDocinfoExample example = new TjDocinfoExample();
        TjDocinfoExample.Criteria criteria = example.createCriteria();
        criteria.andTicketNumEqualTo(ticketNum);
        List<TjDocinfo> tjDocinfos = docinfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tjDocinfos)){
            String s = docinfoExtMapper.selectMaxTickNumber();
            if(s.contains("X")){
                s = s.replace("X","9");
            }
            Long i = Long.parseLong(s);
            ticketNum = i+1+ticketNum.substring(ticketNum.length() - 2,ticketNum.length());
        }
        return  ticketNum;
    }

    public static String generateUniqueExamAdmissionNumber(String cityCode, String majorCode) {
        String examAdmissionNumber = String.format("%s%s%s%04d", String.valueOf(LocalDate.now().getYear()).substring(2),cityCode, majorCode, sequenceNumber);
        sequenceNumber++;
        logger.info("generate a examAdmissionNumber :" + examAdmissionNumber) ;
        return examAdmissionNumber;
    }
}
