package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IAbroadApplyBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.NygoAbroadApplyMapper;
import com.pinde.sci.dao.base.NygoAbroadScheduleMapper;
import com.pinde.sci.form.xjgl.XjAbroadFamilyForm;
import com.pinde.sci.model.mo.NygoAbroadApply;
import com.pinde.sci.model.mo.NygoAbroadApplyExample;
import com.pinde.sci.model.mo.NygoAbroadSchedule;
import com.pinde.sci.model.mo.NygoAbroadScheduleExample;
import com.pinde.sci.model.xjgl.XjAbroadApplyExt;
import com.pinde.sci.model.xjgl.XjQuestionDetailExt;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/4/13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AbroadApplyBizImpl implements IAbroadApplyBiz {
    @Autowired
    private NygoAbroadApplyMapper abroadApplyMapper;
    @Autowired
    private NygoAbroadScheduleMapper scheduleMapper;
    @Override
    public List<NygoAbroadApply> searchApplyList(NygoAbroadApply abroadApply) {
        NygoAbroadApplyExample example=new NygoAbroadApplyExample();
        NygoAbroadApplyExample.Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(abroadApply.getRecordFlow())) {
            criteria.andRecordFlowEqualTo(abroadApply.getRecordFlow());
        }
        if(StringUtil.isNotBlank(abroadApply.getUserFlow())){
            criteria.andUserFlowEqualTo(abroadApply.getUserFlow());
        }
        if(StringUtil.isNotBlank(abroadApply.getUserName())){
            criteria.andUserNameLike("%"+abroadApply.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(abroadApply.getStuNo())){
            criteria.andStuNoLike("%"+abroadApply.getStuNo()+"%");
        }
        if(StringUtil.isNotBlank(abroadApply.getTutorFlow())){
            criteria.andTutorFlowEqualTo(abroadApply.getTutorFlow());
        }
        if(StringUtil.isNotBlank(abroadApply.getPydwOrgFlow())){
            criteria.andPydwOrgFlowEqualTo(abroadApply.getPydwOrgFlow());
        }
        if(StringUtil.isNotBlank(abroadApply.getFwhOrgFlow())){
            criteria.andFwhOrgFlowEqualTo(abroadApply.getFwhOrgFlow());
        }
        if(StringUtil.isNotBlank(abroadApply.getStuLevelId())){
            criteria.andStuLevelIdEqualTo(abroadApply.getStuLevelId());
        }
        if(StringUtil.isNotBlank(abroadApply.getMajorId())){
            criteria.andMajorIdEqualTo(abroadApply.getMajorId());
        }
        if(StringUtil.isNotBlank(abroadApply.getInSchoolDate())){
            criteria.andInSchoolDateEqualTo(abroadApply.getInSchoolDate());
        }
        if(StringUtil.isNotBlank(abroadApply.getGoAbroadId())){
            criteria.andGoAbroadIdEqualTo(abroadApply.getGoAbroadId());
        }
        if(StringUtil.isNotBlank(abroadApply.getBeginDate())){
            criteria.andBeginDateGreaterThanOrEqualTo(abroadApply.getBeginDate());
        }
        if(StringUtil.isNotBlank(abroadApply.getEndDate())){
            criteria.andEndDateLessThanOrEqualTo(abroadApply.getEndDate());
        }
        if(StringUtil.isNotBlank(abroadApply.getPeriod())){
            criteria.andPeriodEqualTo(abroadApply.getPeriod());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return abroadApplyMapper.selectByExample(example);
    }

    @Override
    public NygoAbroadApply searchApplyByFlow(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            return abroadApplyMapper.selectByPrimaryKey(recordFlow);
        }
        return null;
    }

    @Override
    public int saveAbroadApply(NygoAbroadApply abroadApply) {
        if (StringUtil.isNotBlank(abroadApply.getRecordFlow())) {
            GeneralMethod.setRecordInfo(abroadApply, false);
            return abroadApplyMapper.updateByPrimaryKeySelective(abroadApply);
        } else {
            abroadApply.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(abroadApply, true);
            return abroadApplyMapper.insert(abroadApply);
        }
    }

    @Override
    public int delAbroadApply(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            NygoAbroadScheduleExample example=new NygoAbroadScheduleExample();
            NygoAbroadScheduleExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andApplyFlowEqualTo(recordFlow);
            scheduleMapper.deleteByExample(example);
            return abroadApplyMapper.deleteByPrimaryKey(recordFlow);
        }
        return 0;
    }

    @Override
    public int saveSheet(NygoAbroadApply abroadApply, List<XjAbroadFamilyForm> formList) {
        int num=0;
        if(StringUtil.isNotBlank(abroadApply.getRecordFlow())){
            String content=getFamilyFormXml(formList);
            abroadApply.setFamilyMember(content);
            num=saveAbroadApply(abroadApply);
        }
        return num;
    }

    @Override
    public XjAbroadApplyExt parseAbroadFamilyXml(String content) {
        XjAbroadApplyExt form = null;
        if(StringUtil.isNotBlank(content)){
            try{
                form = new XjAbroadApplyExt();
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
//                Element famListEle = root.element("xjAbroadFamilyForm");
                if(null != root){
                    List<Element> famEles = root.elements();
                    if(null != famEles && famEles.size()>0){
                        List<XjAbroadFamilyForm> famList = new ArrayList<XjAbroadFamilyForm>();
                        for(Element famEle : famEles){
                            XjAbroadFamilyForm fam = new XjAbroadFamilyForm();
                            List<Element> famAttrEles = famEle.elements();
                            if(null != famAttrEles && famAttrEles.size()>0){
                                for(Element attr : famAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(fam,attrName,attrValue);
                                }
                            }
                            famList.add(fam);
                        }
                        form.setFamilyFormList(famList);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return form;
    }

    @Override
    public String uploadInvitationFile(String recordFlow,String invitationType,MultipartFile file) {
        if(file!=null){
            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if(!(".pdf".equals(suffix)||".PDF".equals(suffix))){
                return "只能上传PDF文件！";
            }
//            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
//            if(file.getSize()>limitSize*1024*1024){
//                return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
//            }
            try {
				/*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"abroadApplyFiles"+File.separator + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
				/*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "abroadApplyFiles/"+dateString+"/"+fileName;
                if(StringUtil.isNotBlank(recordFlow)){
                    NygoAbroadApply abroadApply = new NygoAbroadApply();
                    abroadApply.setRecordFlow(recordFlow);
                    if("Ch".equals(invitationType)){
                        abroadApply.setChiInvitationUrl(url);
                    }else if("En".equals(invitationType)){
                        abroadApply.setInvitationUrl(url);
                    }else if("Contract".equals(invitationType)){
                        abroadApply.setProjContractUrl(url);
                    }else if("Money".equals(invitationType)){
                        abroadApply.setProjMoneyUrl(url);
                    }
                    saveAbroadApply(abroadApply);
                }
                FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
                String localFilePath=fileDir+File.separator+fileName;
                String ftpDir= "abroadApplyFiles"+File.separator +dateString ;
                String ftpFileName=fileName;
                ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
                return "success:"+url;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @Override
    public int delAbroadSchedule(String applyFlow) {
        NygoAbroadScheduleExample example=new NygoAbroadScheduleExample();
        NygoAbroadScheduleExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(applyFlow)){
            criteria.andApplyFlowEqualTo(applyFlow);
            return scheduleMapper.deleteByExample(example);
        }
        return 0;
    }

    @Override
    public int saveAbroadSchedule(NygoAbroadSchedule abroadSchedule) {
        if (StringUtil.isNotBlank(abroadSchedule.getRecordFlow())) {
            GeneralMethod.setRecordInfo(abroadSchedule, false);
            return scheduleMapper.updateByPrimaryKeySelective(abroadSchedule);
        } else {
            abroadSchedule.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(abroadSchedule, true);
            return scheduleMapper.insert(abroadSchedule);
        }
    }

    @Override
    public List<NygoAbroadSchedule> searchScheduleList(String applyFlow) {
        NygoAbroadScheduleExample example=new NygoAbroadScheduleExample();
        NygoAbroadScheduleExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME");
        if(StringUtil.isNotBlank(applyFlow)){
            criteria.andApplyFlowEqualTo(applyFlow);
            return scheduleMapper.selectByExample(example);
        }
        return new ArrayList<>();
    }

    @Override
    public NygoAbroadSchedule searchScheduleByFlow(String recordFlow) {
        return scheduleMapper.selectByPrimaryKey(recordFlow);
    }

    //将form对象封装为xml文本
    public String getFamilyFormXml(List<XjAbroadFamilyForm> formList){
        String xmlBody = null;
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xjAbroadFamilyFormList");
        if(null != formList && formList.size()>0){
            for(XjAbroadFamilyForm ff : formList){
                Element familyForm = root.addElement("xjAbroadFamilyForm");
                Map<String,String> ffMap = getClassFieldMap(ff);
                if(ffMap!=null && ffMap.size()>0){
                    for(String key : ffMap.keySet()){
                        Element item = familyForm.addElement(key);
                        item.setText(ffMap.get(key));
                    }
                }
            }
        }
        xmlBody=doc.asXML();
        return xmlBody;
    }
    //获取属性名和值
    private Map<String,String> getClassFieldMap(Object obj){
        Map<String,String> filedMap = null;
        if(obj!=null){
            try{
                filedMap = new HashMap<String, String>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for(Field f : fileds){
                    String typeName = f.getType().getSimpleName();
                    if(stringClassName.equals(typeName)){
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0,1).toUpperCase();
                        String methedName = "get"+firstLetter+attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String)getMethod.invoke(obj);
                        filedMap.put(attrName,StringUtil.defaultString(value));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return filedMap;
    }
    //为对象自动复制
    private void setValue(Object obj,String attrName,String attrValue) {
        try {
            Class<?> objClass = obj.getClass();
            String firstLetter = attrName.substring(0, 1).toUpperCase();
            String methedName = "set" + firstLetter + attrName.substring(1);
            Method setMethod = objClass.getMethod(methedName, new Class[]{String.class});
            setMethod.invoke(obj, new Object[]{attrValue});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
