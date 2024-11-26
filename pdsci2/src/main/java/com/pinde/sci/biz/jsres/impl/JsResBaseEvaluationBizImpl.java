package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseEvaluationBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresBaseEvaluationFileMapper;
import com.pinde.sci.dao.base.JsresBaseEvaluationMapper;
import com.pinde.sci.dao.base.JsresBaseEvaluationScoreMapper;
import com.pinde.sci.dao.jsres.JsresBaseEvaluationExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResBaseEvaluationBizImpl implements IJsResBaseEvaluationBiz{

    @Autowired
    private JsresBaseEvaluationMapper jsresBaseEvaluationMapper;
    @Autowired
    private JsresBaseEvaluationFileMapper jsresBaseEvaluationFileMapper;
    @Autowired
    private JsresBaseEvaluationScoreMapper jsresBaseEvaluationScoreMapper;
    @Autowired
    private JsresBaseEvaluationExtMapper jsresBaseEvaluationExtMapper;

    @Override
    public List<JsresBaseEvaluation> searchBaseEvaluationList(JsresBaseEvaluation jsresBaseEvaluation) {
        JsresBaseEvaluationExample example=new JsresBaseEvaluationExample();
        JsresBaseEvaluationExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (jsresBaseEvaluation != null)
        {
            if(StringUtil.isNotBlank(jsresBaseEvaluation.getOrgFlow())){
                criteria.andOrgFlowEqualTo(jsresBaseEvaluation.getOrgFlow());
            }
            if(StringUtil.isNotBlank(jsresBaseEvaluation.getRecordFlow())){
                criteria.andRecordFlowEqualTo(jsresBaseEvaluation.getRecordFlow());
            }
        }
        example.setOrderByClause("SPE_ALL_SCORE desc");
        return jsresBaseEvaluationMapper.selectByExample(example);
    }

    @Override
    public List<JsresBaseEvaluationFile> searchBaseEvaluationFileList(String evaluationFlow) {
        JsresBaseEvaluationFileExample fileExample = new JsresBaseEvaluationFileExample();
        JsresBaseEvaluationFileExample.Criteria criteria = fileExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(evaluationFlow)){
            criteria.andEvaluationRecordFlowEqualTo(evaluationFlow);
        }
        return jsresBaseEvaluationFileMapper.selectByExample(fileExample);
    }

    @Override
    public List<JsresBaseEvaluationScore> searchBaseEvaluationScoreList(String evaluationFlow) {
        JsresBaseEvaluationScoreExample scoreExample = new JsresBaseEvaluationScoreExample();
        JsresBaseEvaluationScoreExample.Criteria criteria = scoreExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(evaluationFlow)){
            criteria.andEvaluationRecordFlowEqualTo(evaluationFlow);
        }
        return jsresBaseEvaluationScoreMapper.selectByExample(scoreExample);
    }
    @Override
    public int saveExpertScoreByItem(String evaluationFlow,String itemId,String speScore) {

        JsresBaseEvaluationScore jsresBaseEvaluationScore = new JsresBaseEvaluationScore();
        jsresBaseEvaluationScore.setSpeScore(speScore);
        JsresBaseEvaluationScoreExample jsresBaseEvaluationScoreExample=new JsresBaseEvaluationScoreExample();
        JsresBaseEvaluationScoreExample.Criteria criteria = jsresBaseEvaluationScoreExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andEvaluationRecordFlowEqualTo(evaluationFlow);
        criteria.andItemIdEqualTo(itemId);
        return jsresBaseEvaluationScoreMapper.updateByExampleSelective(jsresBaseEvaluationScore,jsresBaseEvaluationScoreExample);
    }


    @Override
    public JsresBaseEvaluationScore searchBaseEvaluationScoreByItemId(JsresBaseEvaluationScore jsresBaseEvaluationScore) {
        JsresBaseEvaluationScoreExample scoreExample = new JsresBaseEvaluationScoreExample();
        JsresBaseEvaluationScoreExample.Criteria criteria = scoreExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getItemId())){
            criteria.andItemIdEqualTo(jsresBaseEvaluationScore.getItemId());
        }
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getEvaluationRecordFlow())){
            criteria.andEvaluationRecordFlowEqualTo(jsresBaseEvaluationScore.getEvaluationRecordFlow());
        }
        JsresBaseEvaluationScore score = null;
        if(jsresBaseEvaluationScoreMapper.selectByExample(scoreExample)!=null&&jsresBaseEvaluationScoreMapper.selectByExample(scoreExample).size()!=0){
            score = jsresBaseEvaluationScoreMapper.selectByExample(scoreExample).get(0);
        }
        return score;
    }

    @Override
    public void saveJsresBaseEvaluationScore(JsresBaseEvaluationScore jsresBaseEvaluationScore) {
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getRecordFlow())){
            GeneralMethod.setRecordInfo(jsresBaseEvaluationScore, false);
            jsresBaseEvaluationScoreMapper.updateByPrimaryKeySelective(jsresBaseEvaluationScore);
        }else{
            jsresBaseEvaluationScore.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(jsresBaseEvaluationScore, true);
            jsresBaseEvaluationScoreMapper.insert(jsresBaseEvaluationScore);
        }
    }


    @Override
    public List<JsresBaseEvaluationScore> searchBaseEvaluationScore(JsresBaseEvaluationScore jsresBaseEvaluationScore) {
        JsresBaseEvaluationScoreExample scoreExample = new JsresBaseEvaluationScoreExample();
        JsresBaseEvaluationScoreExample.Criteria criteria = scoreExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getEvaluationRecordFlow())){
            criteria.andEvaluationRecordFlowEqualTo(jsresBaseEvaluationScore.getEvaluationRecordFlow());
        }
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getPlanYear())){
            criteria.andPlanYearEqualTo(jsresBaseEvaluationScore.getPlanYear());
        }
        if(StringUtil.isNotBlank(jsresBaseEvaluationScore.getSpeUserFlow())){
            criteria.andSpeUserFlowEqualTo(jsresBaseEvaluationScore.getSpeUserFlow());
        }
        return jsresBaseEvaluationScoreMapper.selectByExample(scoreExample);
    }

    @Override
    public String checkImg(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))){
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))){
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
            return "请上传 "+InitConfig.getSysCfg("res_file_support_suffix")+"格式的文件";
        }
//        long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
//        if(file.getSize() > limitSize*1024*1024){
//            return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M！" ;
//        }
        return GlobalConstant.FLAG_Y;//可执行保存
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String orgFlow,String planYear,String itemId ){
        String path = GlobalConstant.FLAG_N;
        if(file.getSize() > 0){
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + folderName + File.separator+ orgFlow + File.separator+ planYear + File.separator+ itemId ;
            File fileDir = new File(newDir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //文件名
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String originalFilename = PkUtil.getGUID()+suffix;
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if(StringUtil.isNotBlank(oldFolderName)){
                try {
                    oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if(imgFile.exists()){
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/"+ orgFlow + "/"+ planYear + "/"+ itemId +"/"+ originalFilename;
        }

        return path;
    }

    @Override
    public void saveJsResBaseEvaluation(JsresBaseEvaluation jsresBaseEvaluation) {
        if(StringUtil.isNotBlank(jsresBaseEvaluation.getRecordFlow())){
            GeneralMethod.setRecordInfo(jsresBaseEvaluation, false);
            jsresBaseEvaluationMapper.updateByPrimaryKeySelective(jsresBaseEvaluation);
        }else{
            jsresBaseEvaluation.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(jsresBaseEvaluation, true);
            jsresBaseEvaluationMapper.insert(jsresBaseEvaluation);
        }
    }


    @Override
    public void saveJsresBaseEvaluationFile(JsresBaseEvaluationFile jsresBaseEvaluationFile) {
        if(StringUtil.isNotBlank(jsresBaseEvaluationFile.getRecordFlow())){
            GeneralMethod.setRecordInfo(jsresBaseEvaluationFile, false);
            jsresBaseEvaluationFileMapper.updateByPrimaryKeySelective(jsresBaseEvaluationFile);
        }else{
            jsresBaseEvaluationFile.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(jsresBaseEvaluationFile, true);
            jsresBaseEvaluationFileMapper.insert(jsresBaseEvaluationFile);
        }
    }

    @Override
    public JsresBaseEvaluationFile searchBaseEvaluationFileByRecordFlow(String recordFlow) {
        JsresBaseEvaluationFileExample fileExample = new JsresBaseEvaluationFileExample();
        JsresBaseEvaluationFileExample.Criteria criteria = fileExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recordFlow)){
            criteria.andRecordFlowEqualTo(recordFlow);
        }
        return jsresBaseEvaluationFileMapper.selectByExample(fileExample).get(0);
    }


    @Override
    public List<JsresBaseEvaluation> searchAllBaseEvaluation(Map<String, Object> map) {
        return jsresBaseEvaluationExtMapper.searchEvaluationExtList(map);
    }

    /**
     * 查询协同基地
     * @param jointOrgFlowList
     * @return
     */
    @Override
    public List<JsresBaseEvaluation> searchJointBaseEvaluation(List<String> jointOrgFlowList) {
        return jsresBaseEvaluationExtMapper.searchJointEvaluationExtList(jointOrgFlowList);
    }

}
