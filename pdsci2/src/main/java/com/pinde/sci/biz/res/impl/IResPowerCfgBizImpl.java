package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.ResPowerCfgMapper;
import com.pinde.sci.model.mo.ResPowerCfg;
import com.pinde.sci.model.mo.ResPowerCfgExample;
import com.pinde.core.model.SysUser;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
//@Transactional(rollbackFor=Exception.class)
public class IResPowerCfgBizImpl implements IResPowerCfgBiz{

    @Autowired
    private ResPowerCfgMapper resPowerCfgMapper;
    @Autowired
    private IUserBiz userBiz;

    @Override
    public List<ResPowerCfg> searchByCfgCode(String cfgCode){

        ResPowerCfgExample example = new ResPowerCfgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.createCriteria().andCfgValueEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.createCriteria().andCfgCodeEqualTo(cfgCode);
        return resPowerCfgMapper.selectByExample(example);
    }

    @Override
    public ResPowerCfg read(String cfgCode) {
        ResPowerCfg powerCfg =null;
        if(StringUtil.isNotBlank(cfgCode)){
            powerCfg =  resPowerCfgMapper.selectByPrimaryKey(cfgCode);
        }
        return powerCfg;
    }

    @Override
    public int updateDate(ResPowerCfg powerCfg){
        int count=0;
        if(powerCfg!=null){
            powerCfg.setModifyTime(DateUtil.getCurrDateTime());
            powerCfg.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            count = resPowerCfgMapper.updateByPrimaryKeySelective(powerCfg);
        }
        return count;
    }


    @Override
    public int add(ResPowerCfg cfg) {
        GeneralMethod.setRecordInfo(cfg, true);
        return resPowerCfgMapper.insert(cfg);
    }

    @Override
    public int mod(ResPowerCfg cfg) {
        GeneralMethod.setRecordInfo(cfg, false);
        return resPowerCfgMapper.updateByPrimaryKeySelective(cfg);
    }

    private int edit(ResPowerCfg cfg){
        int result = 0;
        if(cfg != null && StringUtil.isNotBlank(cfg.getCfgCode())){
            ResPowerCfg tempCfg =  resPowerCfgMapper.selectByPrimaryKey(cfg.getCfgCode());
            if(tempCfg == null){
                result = add(cfg);
            }else {
                result = mod(cfg);
            }
        }
        return  result;
    }

    @Override
    public ExcelUtile importPowerFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "idNo",
                    "userName",
                    "powerStartTime",
                    "powerEndTime"
            };
            return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String, Object>> datas = eu.getExcelDatas();
                    int count = 0;
                    String code = "0";
                    for (int i = 0; i < datas.size(); i++) {
                        Map<String, Object> data = datas.get(i);
                        ResPowerCfg webCfg = new ResPowerCfg();
                        webCfg.setCfgCode("res_doctor_web_" + data.get("userFlow"));
                        webCfg.setCfgValue(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        webCfg.setCfgDesc("是否开放学员web登录权限");
                        webCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        webCfg.setPowerStartTime((String) data.get("powerStartTime"));
                        webCfg.setPowerEndTime((String) data.get("powerEndTime"));
                        ResPowerCfg appCfg = new ResPowerCfg();
                        appCfg.setCfgCode("res_doctor_app_login_" + data.get("userFlow"));
//                        appCfg.setCfgValue(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        appCfg.setCfgDesc("是否开放学员app登录权限");
                        appCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                        appCfg.setPowerStartTime((String) data.get("powerStartTime"));
                        appCfg.setPowerEndTime((String) data.get("powerEndTime"));
                        count += edit(webCfg);
                        edit(appCfg);
                    }
                    eu.put("code", code);
                    eu.put("count", count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    String sheetName = (String) eu.get("SheetName");
                    if (!"Powers".equals(sheetName)) {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的模板！！");
                        return ExcelUtile.RETURN;
                    }
                    int rowNum = (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    boolean f = true;
                    String startTime = "";
                    String endTime = "";
                    String userFlow = "";
                    for (Object key1 : data.keySet()) {
                        String key = (String) key1;
                        String value = (String) data.get(key);
                        if ("idNo".equals(key)) {
                            if (StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            SysUser user = userBiz.findByIdNo(value);
                            if (user == null) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行身份证号为" + value + "用户不存在，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            } else {
                                userFlow = user.getUserFlow();
                            }
                        } else if ("powerStartTime".equals(key)) {
                            if (StringUtil.isNotBlank(value)) {
                                f = checkTime(value);
                                if (!f) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间格式不正确，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            startTime = value;
                        } else if ("powerEndTime".equals(key)) {
                            if (StringUtil.isNotBlank(value)) {
                                f = checkTime(value);
                                if (!f) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限结束时间格式不正确，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限结束时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            endTime = value;
                        }
                    }
                    if (StringUtil.isNotBlank(startTime) && StringUtil.isNotBlank(endTime) && startTime.compareTo(endTime) > 0) {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行权限开始时间大于权限结束时间，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if (StringUtil.isNotBlank(userFlow)) {
                        data.put("userFlow", userFlow);
                    } else {
                        f = false;
                    }
                    data.put("f", f);
                    return null;
                }

                private boolean checkTime(String time) {
                    boolean f = true;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(time);
                    } catch (ParseException e) {
                        f = false;
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
    public int saveList(List<ResPowerCfg> cfgList) {
        if(cfgList!=null&&cfgList.size()>0) {
            for (ResPowerCfg resPowerCfg : cfgList) {
                ResPowerCfg temp = this.read(resPowerCfg.getCfgCode());
                if (temp == null) {
                    this.add(resPowerCfg);
                } else {
                    this.mod(resPowerCfg);
                }
            }
            return cfgList.size();
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

    private static final Logger logger = LoggerFactory.getLogger(IResPowerCfgBizImpl.class);

}
