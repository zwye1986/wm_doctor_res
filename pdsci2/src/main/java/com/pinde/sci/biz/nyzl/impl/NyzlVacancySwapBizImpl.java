package com.pinde.sci.biz.nyzl.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlVacancySwapBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.NyzlVacancySwapMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.NyzlVacancySwap;
import com.pinde.sci.model.mo.NyzlVacancySwapExample;
import com.pinde.sci.model.mo.SysDict;
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
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor=Exception.class)
public class NyzlVacancySwapBizImpl implements INyzlVacancySwapBiz{
    @Autowired
    private NyzlVacancySwapMapper vacancySwapMapper;


    @Override
    public int save(NyzlVacancySwap vacancySwap) {
        if(StringUtil.isNotBlank(vacancySwap.getRecordFlow())){
            GeneralMethod.setRecordInfo(vacancySwap, false);
            return vacancySwapMapper.updateByPrimaryKeySelective(vacancySwap);
        }else{
            vacancySwap.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(vacancySwap, true);
            return vacancySwapMapper.insertSelective(vacancySwap);
        }
    }

    @Override
    public List<NyzlVacancySwap> searchVacancySwapList(NyzlVacancySwap vacancySwap) {
        NyzlVacancySwapExample example=new NyzlVacancySwapExample();
        NyzlVacancySwapExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(vacancySwap.getRecordFlow())){
            criteria.andRecordFlowEqualTo(vacancySwap.getRecordFlow());
        }
        if(StringUtil.isNotBlank(vacancySwap.getRecruitYear())){
            criteria.andRecruitYearEqualTo(vacancySwap.getRecruitYear());
        }
        if(StringUtil.isNotBlank(vacancySwap.getStuSign())){
            criteria.andStuSignEqualTo(vacancySwap.getStuSign());
        }
        if(StringUtil.isNotBlank(vacancySwap.getSpeId())){
            criteria.andSpeIdEqualTo(vacancySwap.getSpeId());
        }
        if(StringUtil.isNotBlank(vacancySwap.getDirectionId())){
            criteria.andDirectionIdEqualTo(vacancySwap.getDirectionId());
        }
        if(StringUtil.isNotBlank(vacancySwap.getDegreeTypeId())){
            criteria.andDegreeTypeIdEqualTo(vacancySwap.getDegreeTypeId());
        }
        if(StringUtil.isNotBlank(vacancySwap.getOrgFlow())){
            criteria.andOrgFlowEqualTo(vacancySwap.getOrgFlow());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return vacancySwapMapper.selectByExample(example);
    }

    @Override
    public int importVacancySwap(MultipartFile file, String stuSignFlag) {
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
            for(int i = 1;i < row_num; i++) {
                Row r = sheet.getRow(i);
                NyzlVacancySwap vacancySwap = new NyzlVacancySwap();//新建javaBean
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                vacancySwap.setStuSign(stuSignFlag);
                vacancySwap.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                vacancySwap.setOrgName(GlobalContext.getCurrentUser().getOrgName());
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
                        vacancySwap.setRecruitYear(value);
                        if(StringUtil.isBlank(vacancySwap.getRecruitYear())){
                            throw new Exception("导入失败！第"+ (count+2) +"行年份不能为空！");
                        }
                    }
                    else if ("专业代码".equals(currTitle)){
                        titles.add("专业代码");
                        vacancySwap.setSpeId(value);
                        if(StringUtil.isBlank(vacancySwap.getSpeId())){
                            throw new Exception("导入失败！第"+ (count+2) +"行专业代码不能为空！");
                        }
                    }
                    else if ("学位类型".equals(currTitle)){
                        boolean degreeFlag=true;
                        titles.add("学位类型");
                        vacancySwap.setDegreeTypeName(value);
                        List<SysDict> dictList= DictTypeEnum.NyzlDegreeType.getSysDictList();
                        if(dictList!=null&&dictList.size()>0){
                            for (SysDict sd:dictList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
                                    vacancySwap.setDegreeTypeId(sd.getDictId());
                                    degreeFlag=false;
                                }
                            }
                            if(degreeFlag){
                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位类型！");
                            }
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行学位类型字典未维护！");
                        }
                    }
                    else if ("专业名称".equals(currTitle)){
                        titles.add("专业名称");
                        vacancySwap.setSpeName(value);
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+2) +"行专业名称不能为空！");
                        }else if(StringUtil.isNotBlank(vacancySwap.getSpeId())){
                            if(!value.equals(DictTypeEnum.NyzlSpe.getDictNameById(vacancySwap.getSpeId()))){
                                throw new Exception("导入失败！第"+ (count+2) +"行专业名称与专业代码不匹配！");
                            }
                        }
                    }
                    else if ("方向".equals(currTitle)){
                        boolean directionFlag=true;
                        titles.add("方向");
                        vacancySwap.setDirectionName(value);
                        List<SysDict> dictList= DictTypeEnum.NyzlDirection.getSysDictList();
                        if(dictList!=null&&dictList.size()>0){
                            for (SysDict sd:dictList) {
                                if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
                                    vacancySwap.setDirectionId(sd.getDictId());
                                    directionFlag=false;
                                }
                            }
                            if(directionFlag){
                                throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的方向！");
                            }
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行方向字典未维护！");
                        }
                    }else if ("上线人数".equals(currTitle)){
                        titles.add("上线人数");
                        if(checkNum(value)){
                            vacancySwap.setFullNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行上线人数请输入正确的数字！");
                        }
                    }else if ("缺额人数".equals(currTitle)){
                        titles.add("缺额人数");
                        if(checkNum(value)){
                            vacancySwap.setVacancyNum(value);
                        }else{
                            throw new Exception("导入失败！第"+ (count+2) +"行缺额人数请输入正确的数字！");
                        }
                    }else if ("接收调剂考生条件（初试成绩）".equals(currTitle)){
                        titles.add("接收调剂考生条件（初试成绩）");
                        vacancySwap.setBasciConditions(value);
                    }else if ("接收调剂考生条件（其他要求）".equals(currTitle)){
                        titles.add("接收调剂考生条件（其他要求）");
                        vacancySwap.setOtherConditions(value);
                    }
                }
                //查询旧数据
                List<NyzlVacancySwap> tempList=searchVacancySwapList(vacancySwap);
                if(tempList!=null&&tempList.size()>0){
                    vacancySwap.setRecordFlow(tempList.get(0).getRecordFlow());
                }
                save(vacancySwap);
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
