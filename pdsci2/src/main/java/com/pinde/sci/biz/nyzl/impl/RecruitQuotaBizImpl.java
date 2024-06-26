package com.pinde.sci.biz.nyzl.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.IRecruitQuotaBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.NyzlRecruitQuotaMapper;
import com.pinde.sci.dao.nyzl.RecruitQuotaExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.nyzl.NyzlOrgRecruitQuotaExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor=Exception.class)
public class RecruitQuotaBizImpl implements IRecruitQuotaBiz{
    @Autowired
    private NyzlRecruitQuotaMapper recruitQuotaMapper;
    @Autowired
    private RecruitQuotaExtMapper recruitQuotaExtMapper;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Override
    public NyzlRecruitQuota searchRecruitQuotaByRecordFlow(String recordFlow) {
        return recruitQuotaMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int save(NyzlRecruitQuota recruitQuota) {
        if(StringUtil.isNotBlank(recruitQuota.getRecordFlow())){
            GeneralMethod.setRecordInfo(recruitQuota, false);
            return recruitQuotaMapper.updateByPrimaryKeySelective(recruitQuota);
        }else{
            recruitQuota.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitQuota, true);
            return recruitQuotaMapper.insertSelective(recruitQuota);
        }
    }

    @Override
    public List<NyzlRecruitQuota> searchRecruitQuotaList(NyzlRecruitQuota recruitQuota) {
        NyzlRecruitQuotaExample example=new NyzlRecruitQuotaExample();
        NyzlRecruitQuotaExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitQuota.getRecordFlow())){
            criteria.andRecordFlowEqualTo(recruitQuota.getRecordFlow());
        }
        if(StringUtil.isNotBlank(recruitQuota.getRecruitYear())){
            criteria.andRecruitYearEqualTo(recruitQuota.getRecruitYear());
        }
        if(StringUtil.isNotBlank(recruitQuota.getOrgFlow())){
            criteria.andOrgFlowEqualTo(recruitQuota.getOrgFlow());
        }
        if(StringUtil.isNotBlank(recruitQuota.getStuSign())){
            criteria.andStuSignEqualTo(recruitQuota.getStuSign());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return recruitQuotaMapper.selectByExample(example);
    }

    @Override
    public List<NyzlOrgRecruitQuotaExt> searchOrgList(SysOrg sysOrg) {
        return recruitQuotaExtMapper.queryOrgList(sysOrg);
    }

    @Override
    public List<Map<String, String>> searchFwhList(Map<String, String> map) {
        return recruitQuotaExtMapper.queryFwhList(map);
    }

    @Override
    public Map<String, String> countRecruitQuota(String recruitYear,String stuSignFlag,String fwhFlow) {
        Map<String ,String> map=new HashMap<>();
        map.put("recruitYear",recruitYear);
        map.put("stuSign",stuSignFlag);
        map.put("fwhFlow",fwhFlow);
        return recruitQuotaExtMapper.countRecruitQuota(map);
    }

    @Override
    public int importRecruitQuota(MultipartFile file, String stuSignFlag) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb,stuSignFlag);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    private Workbook createCommonWorkbook(InputStream inS) throws Exception {
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
        throw new Exception("不能解析的excel版本");
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
    private int parseExcel(Workbook wb,String stuSignFlag) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num<=1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
            List<SysDept> deptList=new ArrayList<>();
            if(org!=null){
                deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
            }
            for(int i = 1;i < row_num; i++) {
                Row r = sheet.getRow(i);
                NyzlRecruitQuota recruitQuota = new NyzlRecruitQuota();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                recruitQuota.setStuSign(stuSignFlag);
                recruitQuota.setPublisherName(GlobalContext.getCurrentUser().getUserName());
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("年份".equals(currTitle)){
                        titles.add("年份");
                        recruitQuota.setRecruitYear(value);
                        if(StringUtil.isBlank(recruitQuota.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }
                    else if ("分委会".equals(currTitle)){
                        boolean fwhFlag=true;
                        titles.add("分委会");
                        recruitQuota.setFwhName(value);
                        if(deptList!=null&&deptList.size()>0){
                            for (SysDept dept:deptList) {
                                if(dept!=null&&dept.getDeptName().equals(value)){
                                    recruitQuota.setFwhFlow(dept.getDeptFlow());
                                    fwhFlag=false;
                                }
                            }
                        }
                        if(fwhFlag){
                            throw new Exception("导入失败！第"+ (count+2) +"行分委会有误！");
                        }
                        List<NyzlRecruitQuota> nrqList=searchRecruitQuotaList(recruitQuota);
                        if(nrqList!=null&&nrqList.size()>0){
                            recruitQuota=nrqList.get(0);
                        }
                    }
                    else if ("统考学术型指标".equals(currTitle)){
                        titles.add("统考学术型指标");
                        if(checkNum(value)){
                            recruitQuota.setTkAcademicNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行统考学术型指标请输入正确的数字！");
                        }
                    }
                    else if ("统考专业型指标".equals(currTitle)){
                        titles.add("统考专业型指标");
                        if(checkNum(value)){
                            recruitQuota.setTkSpecialNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行统考专业型指标请输入正确的数字！");
                        }
                    }
                    else if ("推免生数（学术型）".equals(currTitle)){
                        titles.add("推免生数（学术型）");
                        if(checkNum(value)){
                            recruitQuota.setTmsAcademicNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行推免生数（学术型）请输入正确的数字！");
                        }
                    }
                    else if ("推免生数（专业型）".equals(currTitle)){
                        titles.add("推免生数（专业型）");
                        if(checkNum(value)){
                            recruitQuota.setTmsSpecialNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行推免生数（专业型）请输入正确的数字！");
                        }
                    }
                }
                save(recruitQuota);
                count ++;
            }
        }
        return count;

    }

    public boolean checkNum(String num){
        Boolean temp=false;
        Pattern pattern = Pattern.compile("^([1-9]\\d*|[0]{1,1})$");
        if(StringUtil.isNotBlank(num)){
            temp=pattern.matcher(num).matches();
        }
        return temp;
    }
}
