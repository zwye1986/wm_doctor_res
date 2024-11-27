package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresUserBalcklistMapper;
import com.pinde.sci.dao.jsres.JsResUserBalckListExtMapper;
import com.pinde.sci.model.mo.JsresUserBalcklist;
import com.pinde.sci.model.mo.JsresUserBalcklistExample;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * Created by pdkj on 2016/8/31.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResUserBlackBizImpl implements IJsResUserBlackBiz {

    @Autowired
    private JsresUserBalcklistMapper jsresUserBalcklistMapper;
    @Autowired
    private JsResUserBalckListExtMapper jsResUserBalckListExtMapper;

    /**
     * 根据条件查询
     * @param jsresUserBalcklist,operUserFlows,orgFlowList
     * @param userFlowList
     * @param orgFlowList
     * @return
     */
    @Override
    public List<JsresUserBalcklist> searchInfo(JsresUserBalcklist jsresUserBalcklist,List<String> userFlowList,List<String> orgFlowList){
        JsresUserBalcklistExample example = new JsresUserBalcklistExample();
        JsresUserBalcklistExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        if(StringUtil.isNotBlank(jsresUserBalcklist.getSessionNumber())){
            criteria.andSessionNumberEqualTo(jsresUserBalcklist.getSessionNumber());
        }
        if (StringUtil.isNotBlank(jsresUserBalcklist.getIdNo())){
            criteria.andIdNoEqualTo(jsresUserBalcklist.getIdNo());
        }
        if (StringUtil.isNotBlank(jsresUserBalcklist.getCretTypeId())){
            criteria.andCretTypeIdEqualTo(jsresUserBalcklist.getCretTypeId());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            criteria.andOrgFlowEqualTo(jsresUserBalcklist.getOrgFlow());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getUserName())){
            criteria.andUserNameLike("%"+jsresUserBalcklist.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getTrainingSpeName())){
            criteria.andTrainingSpeNameLike("%"+jsresUserBalcklist.getTrainingSpeName()+"%");
        }
        if(orgFlowList!=null && !orgFlowList.isEmpty()){
            criteria.andOrgFlowIn(orgFlowList);
        }
        example.setOrderByClause("create_time desc");
        return jsresUserBalcklistMapper.selectByExample(example);
    }

    @Override
    public List<JsresUserBalcklist> searchInfo2(JsresUserBalcklist jsresUserBalcklist,List<String> userFlowList,
                                                List<String> orgFlowList,List<String> sessionNumbers,String auditStatusId){
        JsresUserBalcklistExample example = new JsresUserBalcklistExample();
        JsresUserBalcklistExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        if(StringUtil.isNotBlank(jsresUserBalcklist.getSessionNumber())){
            criteria.andSessionNumberEqualTo(jsresUserBalcklist.getSessionNumber());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getIdNo())){
            criteria.andIdNoEqualTo(jsresUserBalcklist.getIdNo());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            criteria.andOrgFlowEqualTo(jsresUserBalcklist.getOrgFlow());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getUserName())){
            criteria.andUserNameLike("%"+jsresUserBalcklist.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getTrainingSpeName())){
            criteria.andTrainingSpeNameLike("%"+jsresUserBalcklist.getTrainingSpeName()+"%");
        }
        if(orgFlowList!=null && !orgFlowList.isEmpty()){
            criteria.andOrgFlowIn(orgFlowList);
        }
        if(sessionNumbers!=null && !sessionNumbers.isEmpty()){
            criteria.andSessionNumberIn(sessionNumbers);
        }
        if(StringUtil.isNotBlank(auditStatusId)){
            if("Passed".equals(auditStatusId)){
                criteria.andAuditStatusIdEqualTo(auditStatusId);
            }else{
                criteria.andAuditStatusIdNotEqualTo("Passed");
            }
        }
        example.setOrderByClause("create_time desc");
        return jsresUserBalcklistMapper.selectByExample(example);
    }

    @Override
    public Map<Object, Object> paraseBlackInfo(List<JsresUserBalcklist> blackLists, String sessionNumber, String speName) throws DocumentException{
        List<JsresUserBalcklist> newRecList = new ArrayList<JsresUserBalcklist>();
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        Map<Object, Object> countMap = new HashMap<Object, Object>();
        if (blackLists != null && !blackLists.isEmpty()) {
            for (JsresUserBalcklist black : blackLists) {
                String result = GlobalConstant.FLAG_Y;
                if (StringUtil.isNotBlank(speName) && StringUtil.isNotBlank(black.getTrainingSpeName())) {
                    if (!black.getTrainingSpeName().equals(speName)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (GlobalConstant.FLAG_Y.equals(result)) {
                    String key = black.getRecordFlow();

                    newRecList.add(black);
                    countMap.put(black.getUserFlow(), GlobalConstant.FLAG_F);
                }
            }
            backInfoMap.put(GlobalConstant.FLAG_F, countMap);
            backInfoMap.put(GlobalConstant.FLAG_Y, newRecList);
        }
        return backInfoMap;
    }

    /**
     * 保存、更新黑名单返回主键
     * @param black
     * @return
     */
    @Override
    public int saveBlack(JsresUserBalcklist black) {
        if(StringUtil.isNotBlank(black.getRecordFlow())){
            GeneralMethod.setRecordInfo(black, false);
            int ret = jsresUserBalcklistMapper.updateByPrimaryKeySelective(black);
            return ret;
        }else{
            black.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(black, true);
            int ret = jsresUserBalcklistMapper.insert(black);
            return ret;
        }
    }

    /**
     * 根据用户ID查询黑名单
     * @param userIdNo
     * @return
     */
    @Override
    public JsresUserBalcklist searchInfoByIdNo(String userIdNo) {
        JsresUserBalcklist jsresUserBalcklist = jsResUserBalckListExtMapper.selectByIdNo(userIdNo);
        return jsresUserBalcklist;
    }

    @Override
    public JsresUserBalcklist readUserBalcklist(String recordFlow) {
        return jsresUserBalcklistMapper.selectByPrimaryKey(recordFlow);
    }


    @Override
    public String checkImg(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))) {
            mimeList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))) {
            suffixList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
            return "请上传 " + InitConfig.getSysCfg("res_file_support_suffix") + "格式的文件";
        }
        return GlobalConstant.FLAG_Y;//可执行保存
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String planYear) {
        String path = GlobalConstant.FLAG_N;
        if (file.getSize() > 0) {
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + planYear + File.separator;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String originalFilename = PkUtil.getGUID() + suffix;
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (com.pinde.core.util.StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName+ "/" + planYear + "/" + "/" + originalFilename;
        }
        return path;
    }
}
