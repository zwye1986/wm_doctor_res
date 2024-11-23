package com.pinde.sci.biz.lcjn.impl;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnSuppliesAndAssetsBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.LcjnFixedAssetsMapper;
import com.pinde.sci.dao.base.LcjnSuppliesBatchMapper;
import com.pinde.sci.dao.base.LcjnSuppliesMapper;
import com.pinde.sci.dao.lcjn.LcjnSuppliesAndAssetsExtMapper;
import com.pinde.sci.enums.lcjn.LcjnFixedAssetsStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnSuppliesAndAssetsBizImpl implements ILcjnSuppliesAndAssetsBiz{
    @Autowired
    private LcjnSuppliesAndAssetsExtMapper suppliesAndAssetsExtMapper;
    @Autowired
    private LcjnFixedAssetsMapper fixedAssetsMapper;
    @Autowired
    private LcjnSuppliesMapper suppliesMapper;
    @Autowired
    private LcjnSuppliesBatchMapper suppliesBatchMapper;

    @Override
    public List<Map<String, Object>> selectFixedAssetsList(String dictName, String statusId) {
        Map<String, String> map=new HashMap<>();
        map.put("dictName",dictName);
        map.put("statusId",statusId);
        map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return suppliesAndAssetsExtMapper.queryFixedAssetsList(map);
    }

    @Override
    public int editFixedAsset(LcjnFixedAssets lcjnFixedAssets) {
        if(StringUtil.isNotBlank(lcjnFixedAssets.getFixedFlow())){
//            lcjnFixedAssets.setDictName(DictTypeEnum.SkileFixedAssets.getDictNameById(lcjnFixedAssets.getDictId()));
            lcjnFixedAssets.setStatusName(LcjnFixedAssetsStatusEnum.getNameById(lcjnFixedAssets.getStatusId()));
            GeneralMethod.setRecordInfo(lcjnFixedAssets, false);
            return fixedAssetsMapper.updateByPrimaryKeySelective(lcjnFixedAssets);
        }else{
            lcjnFixedAssets.setFixedFlow(PkUtil.getUUID());
//            lcjnFixedAssets.setDictName(DictTypeEnum.SkileFixedAssets.getDictNameById(lcjnFixedAssets.getDictId()));
            lcjnFixedAssets.setStatusName(LcjnFixedAssetsStatusEnum.getNameById(lcjnFixedAssets.getStatusId()));
            GeneralMethod.setRecordInfo(lcjnFixedAssets, true);
            return fixedAssetsMapper.insert(lcjnFixedAssets);
        }
    }

    @Override
    public LcjnFixedAssets selectByFixedFlow(String fixedFlow) {
        return fixedAssetsMapper.selectByPrimaryKey(fixedFlow);
    }

    @Override
    public int deleteByFixedFlow(String fixedFlow) {
        LcjnFixedAssets fixedAssets=new LcjnFixedAssets();
        fixedAssets.setFixedFlow(fixedFlow);
        fixedAssets.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(fixedAssets, false);
        return fixedAssetsMapper.updateByPrimaryKeySelective(fixedAssets);
    }

    @Override
    public List<Map<String, Object>> selectSuppliesList(String dictName,String suppliesFlow) {
        Map<String, String> map=new HashMap<>();
        map.put("dictName",dictName);
        map.put("suppliesFlow",suppliesFlow);
        map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return suppliesAndAssetsExtMapper.querySuppliesList(map);
    }

    @Override
    public List<LcjnSupplies> selectByDictId(String dictId) {
        LcjnSuppliesExample example=new LcjnSuppliesExample();
        LcjnSuppliesExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDictIdEqualTo(dictId).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        return suppliesMapper.selectByExample(example);
    }

    @Override
    public int addSupplies(LcjnSupplies supplies) {
        List<SysDict> distList= DictTypeEnum.SkillMaterial.getSysDictList();
        if(distList!=null&&distList.size()>0){
            for(int k=0;k<distList.size();k++){
                if(distList.get(k)!=null&&GlobalContext.getCurrentUser().getOrgFlow()!=null
                        &&supplies.getDictId().equals(distList.get(k).getDictId())&&GlobalContext.getCurrentUser().getOrgFlow()
                        .equals(distList.get(k).getOrgFlow())){
                    supplies.setDictName(distList.get(k).getDictName());
                }
            }
        }
        GeneralMethod.setRecordInfo(supplies, true);
        return suppliesMapper.insert(supplies);
    }

    @Override
    public int addSupliesBatch(LcjnSuppliesBatch suppliesBatch) {
        suppliesBatch.setStockFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(suppliesBatch, true);
        return suppliesBatchMapper.insert(suppliesBatch);
    }

    @Override
    public int deleteSuppliesInfo(String suppliesFlow) {
        int num=0;
        LcjnSupplies supplies=new LcjnSupplies();
        supplies.setSuppliesFlow(suppliesFlow);
        supplies.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(supplies, false);
        suppliesMapper.updateByPrimaryKeySelective(supplies);
        LcjnSuppliesBatch suppliesBatch=new LcjnSuppliesBatch();
        suppliesBatch.setSuppliesFlow(suppliesFlow);
        suppliesBatch.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(suppliesBatch, false);
        LcjnSuppliesBatchExample example=new LcjnSuppliesBatchExample();
        LcjnSuppliesBatchExample.Criteria criteria=example.createCriteria();
        criteria.andSuppliesFlowEqualTo(suppliesFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        num=suppliesBatchMapper.updateByExampleSelective(suppliesBatch,example);
        return num;
    }

    @Override
    public List<LcjnSuppliesBatch> selectSuppliesBatch(String suppliesFlow) {
        LcjnSuppliesBatchExample example=new LcjnSuppliesBatchExample();
        LcjnSuppliesBatchExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSuppliesFlowEqualTo(suppliesFlow);
        example.setOrderByClause("ADD_TIME DESC");
        return suppliesBatchMapper.selectByExample(example);
    }

    @Override
    public void importAssetsFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            parseExcel(wb);
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
    @Override
    public void importSuppliesFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            parseExcel2(wb);
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
    private void parseExcel(Workbook wb) {
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
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
            for (int i = 0; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                int cell_num = r.getLastCellNum();
                if (i == 0) {
                    colnames.add("资产名称");
                    colnames.add("单价（元）");
                    colnames.add("资产编号");
                    colnames.add("资产状态");
                } else {
                    LcjnFixedAssets fixedAssets=new LcjnFixedAssets();
                    boolean flag = false;
                    boolean nameFlag=false;
                    boolean statusFlag=false;
                    for (int j = 0; j < cell_num; j++) {
                        String value = "";
                        Cell cell = r.getCell((short) j);
                        cell.setCellType(CellType.STRING);
                        if (cell.getCellType() == CellType.STRING) {
                            value = r.getCell((short) j).getStringCellValue();
                        } else {
                            value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                        }
                        if ("资产名称".equals(colnames.get(j))) {
                            if (value == null|| value.trim().length() == 0) {
                                flag = true;
                                break;
                            }
                            fixedAssets.setDictName(value);
                            List<SysDict> distList=DictTypeEnum.SkileFixedAssets.getSysDictList();
                            int temp=0;
                            if(distList!=null&&distList.size()>0){
                                for(int k=0;k<distList.size();k++){
                                    if(distList.get(k)!=null&&orgFlow!=null&&value.equals(distList.get(k).getDictName())&&orgFlow.equals(distList.get(k).getOrgFlow())){
                                        fixedAssets.setDictId(distList.get(k).getDictId());
                                        temp+=1;
                                    }
                                }
                            }
                            if(temp==0){
                                nameFlag = true;
                                break;
                            }

                        } else if ("单价（元）".equals(colnames.get(j))) {
                            fixedAssets.setFixedPrice(value);
                        } else if ("资产编号".equals(colnames.get(j))) {
                            fixedAssets.setFixedCode(value);
                        } else if ("资产状态".equals(colnames.get(j))) {
                            fixedAssets.setStatusName(value);
                            if (StringUtil.isNotBlank(value)) {
                                if(value.equals("正常")){
                                    fixedAssets.setStatusId("Normal");
                                }else if(value.equals("损坏")){
                                    fixedAssets.setStatusId("Damage");
                                }else if(value.equals("维修")){
                                    fixedAssets.setStatusId("Maintenance");
                                }else {
                                    statusFlag = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (flag) {
                        throw new RuntimeException("导入失败!资产名称不能为空!");
                    }
                    if (nameFlag) {
                        throw new RuntimeException("导入失败!资产名称填写有误，请参照导入模板填写说明!");
                    }
                    if (statusFlag) {
                        throw new RuntimeException("导入失败!资产状态填写有误，请参照导入模板填写说明!");
                    }
                    //查询全部以验证

                    fixedAssets.setFixedFlow(PkUtil.getUUID());
                    fixedAssets.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                    fixedAssets.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                    GeneralMethod.setRecordInfo(fixedAssets, true);
                    fixedAssetsMapper.insert(fixedAssets);
                }
            }
        }
    }

    private void parseExcel2(Workbook wb) {
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
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
            for (int i = 0; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                int cell_num = r.getLastCellNum();
                if (i == 0) {
                    colnames.add("耗材名称");
                    colnames.add("单价（元）");
                    colnames.add("维护数量");
                } else {
                    LcjnSupplies supplies = new LcjnSupplies();
                    supplies.setOrgFlow(orgFlow);
                    supplies.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                    LcjnSuppliesBatch suppliesBatch = new LcjnSuppliesBatch();
                    suppliesBatch.setStockFlow(PkUtil.getUUID());
                    suppliesBatch.setAddTime(DateUtil.getCurrDate());
                    boolean flag = false;
                    boolean nameFlag=false;
                    for (int j = 0; j < cell_num; j++) {
                        String value = "";
                        Cell cell = r.getCell((short) j);
                        cell.setCellType(CellType.STRING);
                        if (cell.getCellType() == CellType.STRING) {
                            value = r.getCell((short) j).getStringCellValue();
                        } else {
                            value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                        }
                        if ("耗材名称".equals(colnames.get(j))) {
                            if (value == null|| value.trim().length() == 0) {
                                flag = true;
                                break;
                            }
                            supplies.setDictName(value);
                            List<SysDict> distList=DictTypeEnum.SkillMaterial.getSysDictList();
                            int temp=0;
                            if(distList!=null&&distList.size()>0){
                                for(int k=0;k<distList.size();k++){
                                    if(orgFlow!=null&&value.equals(distList.get(k).getDictName())&&orgFlow.equals(distList.get(k).getOrgFlow())){
                                        supplies.setDictId(distList.get(k).getDictId());
                                        temp+=1;
                                    }
                                }
                            }
                            if(temp==0){
                                nameFlag = true;
                                break;
                            }

                        } else if ("单价（元）".equals(colnames.get(j))) {
                            supplies.setSuppliesPrice(value);
                        } else if ("维护数量".equals(colnames.get(j))) {
                            suppliesBatch.setStockNum(value);
                        }
                    }
                    if (flag) {
                        throw new RuntimeException("导入失败!耗材名称不能为空!");
                    }
                    if (nameFlag) {
                        throw new RuntimeException("导入失败!耗材名称填写有误，请参照导入模板填写说明!");
                    }
                    //查询全部以验证
                    LcjnSuppliesExample lse = new LcjnSuppliesExample();
                    lse.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                            .andDictIdEqualTo(supplies.getDictId()).andDictNameEqualTo(supplies.getDictName());
                    List<LcjnSupplies> suppliesList = suppliesMapper.selectByExample(lse);
                    if(suppliesList.size() > 0){
                        //已存在耗材，此时维护批次
                        suppliesBatch.setSuppliesFlow(suppliesList.get(0).getSuppliesFlow());
                    }else{
                        //新增耗材
                        String flow = PkUtil.getUUID();
                        supplies.setSuppliesFlow(flow);
                        GeneralMethod.setRecordInfo(supplies,true);
                        suppliesMapper.insertSelective(supplies);
                        suppliesBatch.setSuppliesFlow(flow);
                    }
                    GeneralMethod.setRecordInfo(suppliesBatch,true);
                    suppliesBatchMapper.insertSelective(suppliesBatch);
                }
            }
        }
    }
}
