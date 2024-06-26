package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IDgdhyyActivityBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.DgdhyyActivityMapper;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

/**
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DgdhyyActivityBizImpl implements IDgdhyyActivityBiz {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private DgdhyyActivityMapper dgdhyyActivityMapper;

    @Override
    public int editDgdhyyActivity(DgdhyyActivity dgdhyyActivity) {
        String recordFlow = dgdhyyActivity.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(dgdhyyActivity,false);
            return dgdhyyActivityMapper.updateByPrimaryKeySelective(dgdhyyActivity);
        }else {
            dgdhyyActivity.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(dgdhyyActivity,true);
            return dgdhyyActivityMapper.insertSelective(dgdhyyActivity);
        }
    }

    @Override
    public List<DgdhyyActivity> searchDgdhyyActivity(DgdhyyActivity dgdhyyActivity) {
        DgdhyyActivityExample example = new DgdhyyActivityExample();
        DgdhyyActivityExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(dgdhyyActivity!=null){
            if(StringUtil.isNotBlank(dgdhyyActivity.getUserName())){
                criteria.andUserNameLike("%"+dgdhyyActivity.getUserName()+"%");
            }
            if(StringUtil.isNotBlank(dgdhyyActivity.getUserCode())){
                criteria.andUserCodeLike("%"+dgdhyyActivity.getUserCode()+"%");
            }
            if(StringUtil.isNotBlank(dgdhyyActivity.getActivityName())){
                criteria.andActivityNameLike("%"+dgdhyyActivity.getActivityName()+"%");
            }
            if(StringUtil.isNotBlank(dgdhyyActivity.getOrgFlow())){
                criteria.andOrgFlowEqualTo(dgdhyyActivity.getOrgFlow());
            }
            if(StringUtil.isNotBlank(dgdhyyActivity.getUserFlow())){
                criteria.andUserFlowEqualTo(dgdhyyActivity.getUserFlow());
            }
        }
        return dgdhyyActivityMapper.selectByExample(example);
    }

    @Override
    public int importDgdhyyActivity(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel(wb);
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

    private int parseExcel(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <=row_num; i++){
                Row r =  sheet.getRow(i);
                if(r==null){
                    throw new RuntimeException("导入失败！，表中不能有空行！");
                }
                SysUser current = GlobalContext.getCurrentUser();
                DgdhyyActivity dgdhyyActivity = new DgdhyyActivity();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }

                    /*参加人姓名 参加人用户名 活动名称 开始时间 结束时间 活动形式 所在科室 主讲人 联系方式 活动地点 备注*/
                    if("参加人用户名".equals(colnames.get(j))) {
                        SysUser currentUser = userBiz.findByUserCode(value);
                        if(currentUser==null||StringUtil.isBlank(currentUser.getOrgFlow())||(!current.getOrgFlow().equals(currentUser.getOrgFlow()))){
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，用户名有误！");
                        }else{
                            dgdhyyActivity.setUserCode(currentUser.getUserCode());
                            dgdhyyActivity.setUserFlow(currentUser.getUserFlow());
                            dgdhyyActivity.setOrgFlow(currentUser.getOrgFlow());
                            dgdhyyActivity.setUserName(currentUser.getUserName());
                        }
                    }else if("参加人姓名".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setUserName(value);
                        }
                    }else if("活动名称".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setActivityName (value);
                        }else {
                            throw new RuntimeException("导入失败！，第" + (count+2) + "行，活动名称不能为空！");
                        }
                    }else if("开始时间".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setStartTime(value);
                        }
                    }else if("结束时间".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setEndTime(value);
                        }
                    }else if("活动形式".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setActivityForm(value);
                        }
                    }else if("所在科室".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setDeptName(value);
                        }
                    }else if("主讲人".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setActivitySpeaker(value);
                        }
                    }else if("联系方式".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setPhoneNumber(value);
                        }
                    }else if("活动地点".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setActivityPlace(value);
                        }
                    }else if("备注".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)) {
                            dgdhyyActivity.setRemarks(value);
                        }
                    }
                }
                //执行保存
                DgdhyyActivityExample example = new DgdhyyActivityExample();
                example.createCriteria().andUserFlowEqualTo(dgdhyyActivity.getUserFlow()).
                        andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andActivityNameEqualTo(dgdhyyActivity.getActivityName());
                List<DgdhyyActivity> old = dgdhyyActivityMapper.selectByExample(example);

                if(old!=null&&old.size()>0){
                    dgdhyyActivity.setRecordFlow(old.get(0).getRecordFlow());
                }
                editDgdhyyActivity(dgdhyyActivity);
                count ++;
            }
        }
        return count;
    }

    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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
        throw new IOException("不能解析的excel版本");
    }
    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
}
