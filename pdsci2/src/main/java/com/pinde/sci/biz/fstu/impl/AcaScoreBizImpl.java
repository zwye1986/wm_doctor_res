package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IAcaScoreBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.fstu.FstuScoreExtMapper;
import com.pinde.sci.enums.fstu.AcademicAndScoreEnum;
import com.pinde.sci.enums.fstu.FstuFactorTypeEnum;
import com.pinde.sci.enums.fstu.FstuInfoStatusEnum;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class AcaScoreBizImpl implements IAcaScoreBiz {

    @Autowired
    private FstuScoreConfigMapper configMapper;
    @Autowired
    private FstuScoreMainMapper scoreMapper;
    @Autowired
    private PubFileMapper fileMapper;
    @Autowired
    private FstuScoreExtMapper scoreExtMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private FstuUserScoreMapper userScoreMapper;
    @Autowired
    private FstuUserStudyMapper userStudyMapper;

    @Override
    public List<FstuScoreConfig> searchScoreCfgList(FstuScoreConfig cfg) {
        FstuScoreConfigExample example = new FstuScoreConfigExample();
        FstuScoreConfigExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (null != cfg) {
            if (StringUtil.isNotBlank(cfg.getScoreTypeId())) {
                criteria.andScoreTypeIdEqualTo(cfg.getScoreTypeId());
            }
            if (StringUtil.isNotBlank(cfg.getProjTypeId())) {
                criteria.andProjTypeIdEqualTo(cfg.getProjTypeId());
            }
            if (StringUtil.isNotBlank(cfg.getScoreItemId())) {
                criteria.andScoreItemIdEqualTo(cfg.getScoreItemId());
            }
            if (StringUtil.isNotBlank(cfg.getExecutionId())) {
                criteria.andExecutionIdEqualTo(cfg.getExecutionId());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return configMapper.selectByExample(example);
    }

    @Override
    public FstuScoreConfig searchScoreCfgByFlow(String recordFlow) {
        return configMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int saveScoreCfg(FstuScoreConfig cfg) {
        if (StringUtil.isNotBlank(cfg.getRecordFlow())) {
            GeneralMethod.setRecordInfo(cfg, false);
            return configMapper.updateByPrimaryKeySelective(cfg);
        } else {
            FstuScoreConfigExample example = new FstuScoreConfigExample();
            FstuScoreConfigExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if (StringUtil.isNotBlank(cfg.getScoreTypeId())) {//学分类别
                criteria.andScoreTypeIdEqualTo(cfg.getScoreTypeId());
            }
            if (StringUtil.isNotBlank(cfg.getProjTypeId())) {//项目大类
                criteria.andProjTypeIdEqualTo(cfg.getProjTypeId());
            }
            if (StringUtil.isNotBlank(cfg.getScoreItemId())) {//评分项
                criteria.andScoreItemIdEqualTo(cfg.getScoreItemId());
            }
            if (StringUtil.isNotBlank(cfg.getExecutionId())) {//完成情况
                criteria.andExecutionIdEqualTo(cfg.getExecutionId());
            }
            int count = configMapper.countByExample(example);
            if (count <= 0) {
                cfg.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(cfg, true);
                return configMapper.insert(cfg);
            } else {//同种评分层级关系不可维护多次
                return -1;
            }
        }
    }

    @Override
    public int delConfigByFlow(String recordFlow) {
        FstuScoreConfig cfg = new FstuScoreConfig();
        cfg.setRecordFlow(recordFlow);
        cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return configMapper.updateByPrimaryKeySelective(cfg);
    }

    @Override
    public List<FstuScoreMain> searchScoreList(FstuScoreMain score, String roleFlag) {
        FstuScoreMainExample example = new FstuScoreMainExample();
        FstuScoreMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (null != score) {
            if (StringUtil.isNotBlank(score.getUserFlow())) {
                criteria.andUserFlowEqualTo(score.getUserFlow());
            }
            if (StringUtil.isNotBlank(score.getSessionNumber())) {
                criteria.andSessionNumberEqualTo(score.getSessionNumber());
            }
            if (StringUtil.isNotBlank(score.getFirstScoreTypeId())) {
                criteria.andFirstScoreTypeIdEqualTo(score.getFirstScoreTypeId());
            }
            if (StringUtil.isNotBlank(score.getFirstProjTypeId())) {
                criteria.andFirstProjTypeIdEqualTo(score.getFirstProjTypeId());
            }
            if (StringUtil.isNotBlank(score.getFirstScoreItemId())) {
                criteria.andFirstScoreItemIdEqualTo(score.getFirstScoreItemId());
            }
            if (StringUtil.isNotBlank(score.getFirstExecutionId())) {
                criteria.andFirstExecutionIdEqualTo(score.getFirstExecutionId());
            }
            if (StringUtil.isNotBlank(score.getMyScore())) {
                criteria.andMyScoreEqualTo(score.getMyScore());
            }
            if (StringUtil.isNotBlank(score.getUserCode())) {
                criteria.andUserCodeLike("%" + score.getUserCode() + "%");
            }
            if (StringUtil.isNotBlank(score.getUserName())) {
                criteria.andUserNameLike("%" + score.getUserName() + "%");
            }
            if (StringUtil.isNotBlank(score.getUserDeptName())) {
                criteria.andUserDeptNameLike("%" + score.getUserDeptName() + "%");
            }
            List<String> arg = new ArrayList<>();
            if (StringUtil.isNotBlank(score.getAuditStatusId())) {//我的学分申请提交后
                if ("ALL".equals(score.getAuditStatusId())) {//全部记录
                    arg.add(AcademicAndScoreEnum.ScorePassing.getId());//待审核
                    arg.add(AcademicAndScoreEnum.ScorePassed.getId());//审核通过
                    arg.add(AcademicAndScoreEnum.ScoreBacked.getId());//退回
                    if("header".equals(roleFlag)){
                        String deptFlow = GlobalContext.getCurrentUser().getDeptFlow();
                        if(StringUtil.isBlank(deptFlow)){
//                            criteria.andUserDeptFlowIsNull();
                        }else{
                            criteria.andUserDeptFlowEqualTo(deptFlow);
                        }
                        criteria.andHeaderAuditStatusIdIn (arg);
                    }else{
                        criteria.andAuditStatusIdIn(arg);
                    }
                } else if ("Y".equals(score.getAuditStatusId())) {//已审核
                    arg.add(AcademicAndScoreEnum.ScorePassed.getId());
                    arg.add(AcademicAndScoreEnum.ScoreBacked.getId());
                    if("header".equals(roleFlag)){
                        criteria.andUserDeptFlowEqualTo(GlobalContext.getCurrentUser().getDeptFlow());
                        criteria.andHeaderAuditStatusIdIn (arg);
                    }else{
                        criteria.andAuditStatusIdIn(arg);
                    }
                } else if ("N".equals(score.getAuditStatusId())) {//未审核
                    arg.add(AcademicAndScoreEnum.ScorePassing.getId());
                    if("header".equals(roleFlag)){
                        criteria.andUserDeptFlowEqualTo(GlobalContext.getCurrentUser().getDeptFlow());
                        criteria.andHeaderAuditStatusIdIn (arg);
                    }else{
                        criteria.andAuditStatusIdIn(arg);
                    }
                }
            }
        }
        example.setOrderByClause("CREATE_TIME DESC,SESSION_NUMBER DESC");
        return scoreMapper.selectByExample(example);
    }

    @Override
    public FstuScoreMain searchScoreByFlow(String recordFlow) {
        return scoreMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int saveScore(FstuScoreMain score, List<String> urlLst) {
        FstuScoreConfigExample example = new FstuScoreConfigExample();
        FstuScoreConfigExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(score.getFirstScoreTypeId())) {//学分类别
            criteria.andScoreTypeIdEqualTo(score.getFirstScoreTypeId());
        }
        if (StringUtil.isNotBlank(score.getFirstProjTypeId())) {//项目大类
            criteria.andProjTypeIdEqualTo(score.getFirstProjTypeId());
        }
        if (StringUtil.isNotBlank(score.getFirstScoreItemId())) {//评分项
            criteria.andScoreItemIdEqualTo(score.getFirstScoreItemId());
        }
        if (StringUtil.isNotBlank(score.getFirstExecutionId())) {//完成情况
            criteria.andExecutionIdEqualTo(score.getFirstExecutionId());
        }
        int count = configMapper.countByExample(example);
        if (count > 0) {//申请我的学分必须已维护好评分层级关系
            if (StringUtil.isNotBlank(score.getRecordFlow())) {
                GeneralMethod.setRecordInfo(score, false);
                if (null != urlLst && urlLst.size() > 0) {
                    saveFileInfo(score.getRecordFlow(), urlLst);
                }
                return scoreMapper.updateByPrimaryKeySelective(score);
            } else {
                score.setRecordFlow(PkUtil.getUUID());
                score.setAuditStatusId(AcademicAndScoreEnum.ScoreAdd.getId());
                score.setAuditStatusName(AcademicAndScoreEnum.ScoreAdd.getName());
                GeneralMethod.setRecordInfo(score, true);
                if (null != urlLst && urlLst.size() > 0) {
                    saveFileInfo(score.getRecordFlow(), urlLst);
                }
                return scoreMapper.insert(score);
            }
        }
        return 0;
    }

    public int saveFileInfo(String recordFlow, List<String> urlLst) {
        PubFileExample example = new PubFileExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andProductFlowEqualTo(recordFlow);
        fileMapper.deleteByExample(example);//保存文件时先做删除
        int count = 0;
        for (int i = 0; i < urlLst.size(); i++) {
            String filePath = urlLst.get(i);
            PubFile pf = new PubFile();
            pf.setFileFlow(PkUtil.getUUID());
            pf.setFileName(filePath.substring(filePath.lastIndexOf('/') + 1));
            pf.setFilePath(filePath);
            pf.setProductFlow(recordFlow);
            GeneralMethod.setRecordInfo(pf, true);
            count += fileMapper.insert(pf);
        }
        return count;
    }

    @Override
    public int delScoreByFlow(String recordFlow) {
        FstuScoreMain score = new FstuScoreMain();
        score.setRecordFlow(recordFlow);
        score.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return scoreMapper.updateByPrimaryKeySelective(score);
    }

    @Override
    public int submitScoreByFlow(String recordFlow,String roleFlag) {
        FstuScoreMain score = new FstuScoreMain();
        score.setRecordFlow(recordFlow);
        if(StringUtil.isNotBlank(roleFlag)){
            if("header".equals(roleFlag)){
                //科主任取消审核 恢复成待审核
                score.setAuditStatusId("");
                score.setAuditStatusName("");
                score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
            }else{
                //管理员取消审核
                score.setAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
                score.setAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
            }
        }else{
            //提交变为待审核
//            score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
//            score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
            //20190108成都中医药大学继教修改需求（取消科主任审核）
            score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScorePassed.getId());
            score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScorePassed.getName());
            score.setAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
            score.setAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
        }
        return scoreMapper.updateByPrimaryKeySelective(score);
    }

    @Override
    public int auditScore(String recordFlow, String auditStatusId,String roleFlag) {
        FstuScoreMain score = new FstuScoreMain();
        score.setRecordFlow(recordFlow);
        if("header".equals(roleFlag)){
            if("Passed".equals(auditStatusId)){//审核通过进入下一级审核
                score.setHeaderAuditStatusId(auditStatusId);
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScorePassed.getName());
                score.setAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
                score.setAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
            }else{//退回重新提交
                score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScoreAdd.getId());
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScoreAdd.getName());
            }
        }else{
            if("Passed".equals(auditStatusId)){
                score.setAuditStatusId(auditStatusId);
                score.setAuditStatusName(AcademicAndScoreEnum.ScorePassed.getName());
            }else{//退回科主任重新审核
                score.setAuditStatusId("");
                score.setAuditStatusName("");
                //20190108成都中医药大学继教修改需求（取消科主任审核）
//                score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
//                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
                score.setHeaderAuditStatusId(AcademicAndScoreEnum.ScoreAdd.getId());
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScoreAdd.getName());
            }

        }
        score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        return scoreMapper.updateByPrimaryKeySelective(score);
    }

    @Override
    public List<PubFile> queryFileList(String recordFlow) {
        PubFileExample example = new PubFileExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andProductFlowEqualTo(recordFlow);
        return fileMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> scoreMains(Map<String, String> paramMap) {
        return scoreExtMapper.searchScoreList(paramMap);
    }

    @Override
    public Map<String, Double> calculation(String year, String firstScoreTypeId) {
        FstuScoreMainExample example = new FstuScoreMainExample();
        FstuScoreMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdEqualTo("Passed");
        if (StringUtil.isNotBlank(year)) {
            criteria.andSessionNumberEqualTo(year);
        }
        if (StringUtil.isNotBlank(firstScoreTypeId)) {
            criteria.andFirstScoreTypeIdEqualTo(firstScoreTypeId);
        }
        example.setOrderByClause("FIRST_PROJ_TYPE_NAME");
        List<FstuScoreMain> scoreMains = scoreMapper.selectByExample(example);
        Map<String, Double> resultMap = new HashMap<>();
        if (scoreMains.size() > 0 && scoreMains != null) {
            double scoreSum = 0;
            for (FstuScoreMain scoreMain : scoreMains) {
                String firstProjTypeName = scoreMain.getFirstProjTypeName();
                if (resultMap.get(firstProjTypeName) == null) {
                    if (StringUtil.isNotBlank(scoreMain.getMyScore())) {
                        scoreSum = Double.parseDouble(scoreMain.getMyScore());
                    }
                    resultMap.put(firstProjTypeName, scoreSum);
                } else {
                    if (StringUtil.isNotBlank(scoreMain.getMyScore())) {
                        scoreSum = Double.parseDouble(scoreMain.getMyScore());
                    }
                    resultMap.put(firstProjTypeName, resultMap.get(firstProjTypeName) + scoreSum);
                }
            }
        }
        return resultMap;
    }

    @Override
    public int importScoreFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb);
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

    private int parseExcel(Workbook wb) {
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try {
                sheet = wb.getSheetAt(0);
            } catch (Exception e) {
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR = sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for (int i = 0; i < cell_num; i++) {
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                FstuScoreMain scoreMain = new FstuScoreMain();
                FstuScoreConfig scoreConfig = new FstuScoreConfig();
                String sessionNumber;
                String userName;
                String userCode = "";
                String firstScoreTypeName;
                String firstProjTypeName;
                String scoreName;
                String firstScoreItemName;
                String firstExecutionName;
                String myScore;
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }

					/* 年份	学员姓名	学员登录名  学分类别 	项目大类	名称	评分项	完成情况	分值*/
                    if ("年份".equals(colnames.get(j))) {
                        sessionNumber = value;
                        scoreMain.setSessionNumber(sessionNumber);
                    } else if ("学员姓名".equals(colnames.get(j))) {
                        userName = value;
                        scoreMain.setUserName(userName);
                    } else if ("学员登录名".equals(colnames.get(j))) {
                        userCode = value;
                    } else if ("学分类别".equals(colnames.get(j))) {
                        firstScoreTypeName = value;
                        scoreConfig.setScoreTypeName(firstScoreTypeName);
                        scoreMain.setFirstScoreTypeName(firstScoreTypeName);
                    } else if ("项目大类".equals(colnames.get(j))) {
                        firstProjTypeName = value;
                        scoreConfig.setProjTypeName(firstProjTypeName);
                        scoreMain.setFirstProjTypeName(firstProjTypeName);
                    } else if ("名称".equals(colnames.get(j))) {
                        scoreName = value;
                        scoreMain.setScoreName(scoreName);
                    } else if ("评分项".equals(colnames.get(j))) {
                        firstScoreItemName = value;
                        scoreConfig.setScoreItemName(firstScoreItemName);
                        scoreMain.setFirstScoreItemName(firstScoreItemName);
                    } else if ("完成情况".equals(colnames.get(j))) {
                        firstExecutionName = value;
                        scoreConfig.setExecutionName(firstExecutionName);
                        scoreMain.setFirstExecutionName(firstExecutionName);
                    } else if ("分值".equals(colnames.get(j))) {
                        myScore = value;
                        scoreMain.setMyScore(myScore);
                        scoreMain.setFirstScore(myScore);
                    }
                }
                FstuScoreConfigExample example = new FstuScoreConfigExample();
                FstuScoreConfigExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                if (null != scoreConfig) {
                    if (StringUtil.isNotBlank(scoreConfig.getScoreTypeName())) {
                        criteria.andScoreTypeNameEqualTo(scoreConfig.getScoreTypeName());
                    }
                    if (StringUtil.isNotBlank(scoreConfig.getProjTypeName())) {
                        criteria.andProjTypeNameEqualTo(scoreConfig.getProjTypeName());
                    }
                    if (StringUtil.isNotBlank(scoreConfig.getScoreItemName())) {
                        criteria.andScoreItemNameEqualTo(scoreConfig.getScoreItemName());
                    }
                    if (StringUtil.isNotBlank(scoreConfig.getExecutionName())) {
                        criteria.andExecutionNameEqualTo(scoreConfig.getExecutionName());
                    }
                }
                example.setOrderByClause("CREATE_TIME DESC");
                List<FstuScoreConfig> scoreConfigList = configMapper.selectByExample(example);
                FstuScoreConfig config;
                if (null != scoreConfigList && scoreConfigList.size() > 0) {
                    config = scoreConfigList.get(0);
                    scoreMain.setFirstProjTypeId(config.getProjTypeId());
                    scoreMain.setFirstExecutionId(config.getExecutionId());
                    scoreMain.setFirstScoreItemId(config.getScoreItemId());
                    scoreMain.setFirstScoreTypeId(config.getScoreTypeId());
                    scoreMain.setConfigRecordFlow(config.getRecordFlow());
                    scoreMain.setFirstMaxScore(config.getMaxScore());
                } else {
                    throw new RuntimeException("导入失败！，第" + count + 2 + "行，需配置对应学分配置项！");
                }
                if (StringUtil.isNotBlank(userCode)) {
                    SysUser sysUser = new SysUser();
                    sysUser.setUserCode(userCode);
                    List<SysUser> userList = userBiz.searchUser(sysUser);
                    if (null != userList && userList.size() > 0) {
                        scoreMain.setUserCode(userCode);
                        scoreMain.setUserFlow(userList.get(0).getUserFlow());
                        scoreMain.setUserDeptFlow(userList.get(0).getDeptFlow());
                        scoreMain.setUserDeptName(userList.get(0).getDeptName());
                        scoreMain.setUserName(userList.get(0).getUserName());
                    } else {
                        throw new RuntimeException("导入失败！，第" + count + 2 + "行，没有找到当前用户！");
                    }
                } else {
                    throw new RuntimeException("导入失败！，第" + count + 2 + "行，登录名不能为空！");
                }
                scoreMain.setRecordFlow(PkUtil.getUUID());
                scoreMain.setAuditStatusId(AcademicAndScoreEnum.ScorePassed.getId());
                scoreMain.setAuditStatusName(AcademicAndScoreEnum.ScorePassed.getName());
                GeneralMethod.setRecordInfo(scoreMain, true);
                //执行保存
                int result = scoreMapper.insert(scoreMain);
                if (result != 1) {
                    throw new RuntimeException("导入失败！，第" + (count + 2) + "行！");
                }
                count++;
            }
        }
        return count;
    }

    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public int saveScoreAndFile(FstuScoreMain score, List<PubFile> fileList) {
        int result = 0;
        if (score != null) {
            if (StringUtil.isNotBlank(score.getRecordFlow())) {
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScoreAdd.getId().equals(score.getHeaderAuditStatusId())?AcademicAndScoreEnum.ScoreAdd.getName():AcademicAndScoreEnum.ScorePassed.getName());
                if(AcademicAndScoreEnum.ScorePassed.getId().equals(score.getHeaderAuditStatusId())){
                    score.setAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
                    score.setAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
                }
                GeneralMethod.setRecordInfo(score, false);
                result = scoreMapper.updateByPrimaryKeySelective(score);
            } else {
                score.setRecordFlow(PkUtil.getUUID());
                score.setHeaderAuditStatusName(AcademicAndScoreEnum.ScoreAdd.getId().equals(score.getHeaderAuditStatusId())?AcademicAndScoreEnum.ScoreAdd.getName():AcademicAndScoreEnum.ScorePassed.getName());
                if(AcademicAndScoreEnum.ScorePassed.getId().equals(score.getHeaderAuditStatusId())){
                    score.setAuditStatusId(AcademicAndScoreEnum.ScorePassing.getId());
                    score.setAuditStatusName(AcademicAndScoreEnum.ScorePassing.getName());
                }
                GeneralMethod.setRecordInfo(score, true);
                result = scoreMapper.insertSelective(score);
            }
        }
        //操作附件
        for (PubFile pubFile : fileList) {
            //操作附件
            if (null != pubFile) {
                pubFile.setProductFlow(score.getRecordFlow());
                if (StringUtil.isNotBlank(pubFile.getFileFlow())) {
                    GeneralMethod.setRecordInfo(pubFile, false);
                    pubFileMapper.updateByPrimaryKeySelective(pubFile);
                } else {
                    pubFile.setFileFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(pubFile, true);
                    pubFileMapper.insertSelective(pubFile);
                }
            }
        }
        return result;
    }
    @Override
    public List<FstuUserScore> searchScoreList(FstuUserScore score) {
        FstuUserScoreExample example = new FstuUserScoreExample();
        FstuUserScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (null != score) {
            if (StringUtil.isNotBlank(score.getSessionNumber())) {
                criteria.andSessionNumberEqualTo(score.getSessionNumber());
            }
            if (StringUtil.isNotBlank(score.getScoreType())) {
                criteria.andScoreTypeEqualTo(score.getScoreType());
            }
            if (StringUtil.isNotBlank(score.getSubjectId())) {
                criteria.andSubjectIdEqualTo(score.getSubjectId());
            }
            if (StringUtil.isNotBlank(score.getSubjectName())) {
                criteria.andSubjectNameEqualTo(score.getSubjectName());
            }
            if (StringUtil.isNotBlank(score.getUserName())) {
                criteria.andUserNameLike("%" + score.getUserName() + "%");
            }
            if (StringUtil.isNotBlank(score.getScoreTarget())) {
                criteria.andScoreTargetEqualTo(score.getScoreTarget());
            }
            if (StringUtil.isNotBlank(score.getOrgFlow())) {
                criteria.andOrgFlowEqualTo(score.getOrgFlow());
            }
            if (StringUtil.isNotBlank(score.getTitleId())) {
                criteria.andTitleIdEqualTo(score.getTitleId());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return userScoreMapper.selectByExample(example);
    }

    @Override
    public int saveUserScore(FstuUserScore score) {
        FstuUserScore userScore = userScoreMapper.selectByPrimaryKey(score.getUserFlow());
        if(null != userScore){
            GeneralMethod.setRecordInfo(score,false);
            return userScoreMapper.updateByPrimaryKeySelective(score);
        }else{
            GeneralMethod.setRecordInfo(score,true);
            return userScoreMapper.insertSelective(score);
        }
    }

    @Override
    public FstuUserScore searchUserScoreByFlow(String userFlow) {
        return userScoreMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public List<SysUser> searchUserNotInScore(String orgFlow,String flag) {
        return scoreExtMapper.searchUserNotInScore(orgFlow,flag);
    }

    @Override
    public int saveUserStudy(FstuUserStudy study) {
        FstuUserStudy userStudy = userStudyMapper.selectByPrimaryKey(study.getUserFlow());
        if(null != userStudy){
            GeneralMethod.setRecordInfo(study,false);
            return userStudyMapper.updateByPrimaryKeySelective(study);
        }else{
            GeneralMethod.setRecordInfo(study,true);
            return userStudyMapper.insertSelective(study);
        }
    }

    @Override
    public List<FstuUserStudy> searchStudyList(FstuUserStudy study) {
        FstuUserStudyExample example = new FstuUserStudyExample();
        FstuUserStudyExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (null != study) {
            if (StringUtil.isNotBlank(study.getUserName())) {
                criteria.andUserNameLike("%" + study.getUserName() + "%");
            }
            if (StringUtil.isNotBlank(study.getDeptFlow())) {
                criteria.andDeptFlowEqualTo(study.getDeptFlow());
            }
            if (StringUtil.isNotBlank(study.getOrgFlow())) {
                criteria.andOrgFlowEqualTo(study.getOrgFlow());
            }
            if (StringUtil.isNotBlank(study.getStudyCourse())) {
                criteria.andStudyCourseLike("%" + study.getStudyCourse() + "%");
            }
            if (StringUtil.isNotBlank(study.getStudyMonth())) {
                criteria.andStudyMonthEqualTo(study.getStudyMonth());
            }
            if ("stFlag".equals(study.getAuditStatusId())) {
                List<String> statusList = new ArrayList<>();
                statusList.add("Passing");
                statusList.add("Passed");
                statusList.add("UnPassed");
                statusList.add("Backed");
                criteria.andAuditStatusIdIn(statusList);
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return userStudyMapper.selectByExample(example);
    }

    @Override
    public FstuUserStudy searchUserStudyByFlow(String userFlow) {
        return userStudyMapper.selectByPrimaryKey(userFlow);
    }
}
