package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResLectureScanRegistBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.LectureInfoTargetMapper;
import com.pinde.sci.dao.base.ResLectureScanRegistMapper;
import com.pinde.sci.dao.res.LectureInfoTargetExtMapper;
import com.pinde.sci.dao.res.ResLectureInfoExtMapper;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResLectureScanRegistBizImpl implements IResLectureScanRegistBiz {
    @Autowired
    private ResLectureScanRegistMapper lectureScanRegistMapper;
    @Autowired
    private ResLectureInfoExtMapper lectureinfoExtMapper;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private LectureInfoTargetExtMapper lectureInfoTargetExtMapper;
    @Autowired
    private LectureInfoTargetMapper lectureInfoTargetMapper;


    @Override
    public List<Map<String, String>> searchIsScanNew(String lectureFlow, List<String> roles) {
        return lectureinfoExtMapper.searchIsScanNew(lectureFlow,roles);
    }

    @Override
    public List<Map<String, String>> searchRegistByLectureFlowNew(String lectureFlow, List<String> roles) {
        return lectureinfoExtMapper.searchRegistByLectureFlowNew(lectureFlow,roles);
    }
    @Override
    public List<ResLectureScanRegist> searchRegistByLectureFlow(String lectureFlow, List<String> roles) {
//        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
//        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andLectureFlowEqualTo(lectureFlow).andIsRegistIsNotNull()
//                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//        example.setOrderByClause("CREATE_TIME");
        return lectureinfoExtMapper.searchRegistByLectureFlow(lectureFlow,roles);
    }

    @Override
    public int edit(ResLectureScanRegist lectureScanRegist) {
        if(StringUtil.isNotBlank(lectureScanRegist.getRecordFlow())){
            GeneralMethod.setRecordInfo(lectureScanRegist,false);
            return lectureScanRegistMapper.updateByPrimaryKeySelective(lectureScanRegist);
        }else{
            lectureScanRegist.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(lectureScanRegist,true);
            return lectureScanRegistMapper.insertSelective(lectureScanRegist);
        }
    }

    @Override
    public ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow,String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andLectureFlowEqualTo(lectureFlow).andOperUserFlowEqualTo(userFlow);
        List<ResLectureScanRegist> lectureScanRegists = lectureScanRegistMapper.selectByExample(example);
        if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
            return lectureScanRegists.get(0);
        }else{
            return null;
        }

    }

    @Override
    public synchronized int editLectureScanRegist(String lectureFlow){
        int count=lectureinfoExtMapper.checkRegistNum(lectureFlow);
        if(count<=0)
        {
            if(count==0)
                count=-1;
            return count;
        }

        SysUser currUser = GlobalContext.getCurrentUser();
        ResLectureScanRegist lectureScanRegist =getRegistByFlow(currUser.getUserFlow(),lectureFlow);
        if(lectureScanRegist==null) {
            String userFlow = currUser.getUserFlow();
            lectureScanRegist = new ResLectureScanRegist();
            lectureScanRegist.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(lectureScanRegist, true);
            lectureScanRegist.setLectureFlow(lectureFlow);
            lectureScanRegist.setOperUserFlow(userFlow);
            ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
            if (doctor != null) {
                String session = doctor.getSessionNumber();
                String trainingTypeId = doctor.getTrainingTypeId();
                String trainingTypeName = doctor.getTrainingTypeName();
                String trainingSpeId = doctor.getTrainingSpeId();
                String trainingSpeName = doctor.getTrainingSpeName();
                if (StringUtil.isNotBlank(session)) {
                    lectureScanRegist.setSessionNumber(session);
                }
                if (StringUtil.isNotBlank(trainingTypeId)) {
                    lectureScanRegist.setTrainingTypeId(trainingTypeId);
                }
                if (StringUtil.isNotBlank(trainingTypeName)) {
                    lectureScanRegist.setTrainingTypeName(trainingTypeName);
                }
                if (StringUtil.isNotBlank(trainingSpeId)) {
                    lectureScanRegist.setTrainingSpeId(trainingSpeId);
                }
                if (StringUtil.isNotBlank(trainingSpeName)) {
                    lectureScanRegist.setTrainingSpeName(trainingSpeName);
                }
            }
            if (StringUtil.isNotBlank(currUser.getUserName())) {
                lectureScanRegist.setOperUserName(currUser.getUserName());
            }
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return lectureScanRegistMapper.insertSelective(lectureScanRegist);
        }
        else{
            GeneralMethod.setRecordInfo(lectureScanRegist, false);
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return lectureScanRegistMapper.updateByPrimaryKeySelective(lectureScanRegist);
        }

    }

    private ResLectureScanRegist getRegistByFlow(String userFlow, String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
        .andLectureFlowEqualTo(lectureFlow).andOperUserFlowEqualTo(userFlow);
        List<ResLectureScanRegist>list =lectureScanRegistMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int editLectureScanRegist(String lectureFlow, String limitNum){
        SysUser currUser = GlobalContext.getCurrentUser();
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andIsRegistEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andLectureFlowEqualTo(lectureFlow);
        if(StringUtil.isNotBlank(limitNum) && lectureScanRegistMapper.countByExample(example)>=Integer.valueOf(limitNum)){
            return 0;
        }else{
            ResLectureScanRegist lectureScanRegist = new ResLectureScanRegist();
            lectureScanRegist.setRecordFlow(PkUtil.getUUID());
            lectureScanRegist.setLectureFlow(lectureFlow);
            lectureScanRegist.setOperUserFlow(currUser.getUserFlow());
            lectureScanRegist.setOperUserName(currUser.getUserName());
            ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
            if(doctor!=null){
                lectureScanRegist.setSessionNumber(doctor.getSessionNumber());
                lectureScanRegist.setTrainingTypeId(doctor.getDoctorCategoryId());
                lectureScanRegist.setTrainingTypeName(doctor.getDoctorCategoryName());
                lectureScanRegist.setTrainingSpeId(doctor.getTrainingSpeId());
                lectureScanRegist.setTrainingSpeName(doctor.getTrainingSpeName());
            }
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
            GeneralMethod.setRecordInfo(lectureScanRegist,true);
            return lectureScanRegistMapper.insertSelective(lectureScanRegist);
        }
    }

    @Override
    public List<ResLectureScanRegist> searchByUserFLowAndRegist(String userFlow){
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andOperUserFlowEqualTo(userFlow).andIsRegistEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        ResLectureScanRegistExample.Criteria criteria2 = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                        andOperUserFlowEqualTo(userFlow).andIsRegistIsNull();
        ResLectureScanRegistExample.Criteria criteria3 = example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                andOperUserFlowEqualTo(userFlow).andIsScanEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        example.or(criteria2);
        example.or(criteria3);
        example.setOrderByClause("CREATE_TIME");
        return lectureScanRegistMapper.selectByExample(example);
    }

    @Override
    public List<ResLectureScanRegist> searchByLectureFlow(String lectureFlow) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andLectureFlowEqualTo(lectureFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME");
        return lectureScanRegistMapper.selectByExample(example);
    }
    @Override
    public List<ResLectureScanRegist> searchIsScan(String lectureFlow, List<String> roles) {

        return lectureinfoExtMapper.searchIsScan(lectureFlow,roles);
    }

    @Override
    public List<ResLectureScanRegist> queryScanRegistDocList(String lectureFlow, String flag) {
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andLectureFlowEqualTo(lectureFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if("scan".equals(flag)){
            criteria.andIsScanIsNotNull();
        }else{
            criteria.andIsRegistEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        return lectureScanRegistMapper.selectByExample(example);
    }

    @Override
    public ResLectureScanRegist readByFlow(String registFlow) {
        return lectureScanRegistMapper.selectByPrimaryKey(registFlow);
    }

    @Override
    public int saveRegist(ResLectureScanRegist regist) {
        if(regist!=null){
            if(StringUtil.isNotBlank(regist.getRecordFlow())){
                GeneralMethod.setRecordInfo(regist, false);
                return lectureScanRegistMapper.updateByPrimaryKeySelective(regist);
            }else{
                regist.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(regist, true);
                return lectureScanRegistMapper.insertSelective(regist);
            }
        }
        return 0;
    }

    @Override
    public Map<String, String> registImg(String registFlow, MultipartFile file, String fileAddress) {
        Map<String, String> map=new HashMap<String, String>();
        map.put("status", com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG);
        if(file!=null){
            List<String> mimeList = new ArrayList<String>();
            if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
                mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
                suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
            }

            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
                return  map;

            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M");
                return  map;
            }
            try {
				/*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
				/*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = InitConfig.getSysCfg("upload_base_url")+"/"+fileAddress+"/"+dateString+"/"+fileName;
                if(StringUtil.isNotBlank(registFlow)){
                    ResLectureScanRegist resRec = readByFlow(registFlow);
                    String content =resRec.getProofUrls();
                    Document document= DocumentHelper.parseText(content);
                    if (document!=null) {
                        Element element=document.getRootElement();
                        Element elem=element.addElement("image");
                        String imageFlow=PkUtil.getUUID();
                        elem.addAttribute("imageFlow",imageFlow);
                        Element imageUrl=elem.addElement("imageUrl");
                        Element thumbUrl=elem.addElement("thumbUrl");
                        imageUrl.setText(url);
                        thumbUrl.setText(url);
                        resRec.setProofUrls(document.asXML());
                        saveRegist(resRec);
                        map.put("url",url);
                        map.put("flow",imageFlow);
                    }
                }
                map.put("status", com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return map;
    }

    @Override
    public List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow) {
        return lectureinfoExtMapper.checkJoinList(lectureFlow, userFlow);
    }

    @Override
    public int delLectureScanRegist(String lectureFlow, String userFlow){
        ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andIsRegistEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andLectureFlowEqualTo(lectureFlow)
                .andOperUserFlowEqualTo(userFlow);
        List<ResLectureScanRegist> list=lectureScanRegistMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            int num=lectureScanRegistMapper.deleteByPrimaryKey(list.get(0).getRecordFlow());
            return num;
        }
        return 0;
    }
    /**
     * @param lectureFlow
     * @Department：研发部
     * @Description 查询讲座评价指标信息
     * @Author fengxf
     * @Date 2020/2/13
     */
    @Override
    public List<Map<String, Object>> searchLectureTargetEvalList(String lectureFlow, List<String> roles) {
        return lectureInfoTargetExtMapper.searchLectureTargetEvalList(lectureFlow, roles);
    }

    /**
     * @param lectureFlow
     * @Department：研发部
     * @Description 查询讲座活动管理的评价指标列表
     * @Author fengxf
     * @Date 2020/2/13
     */
    @Override
    public List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow) {
        LectureInfoTargetExample example = new LectureInfoTargetExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andLectureFlowEqualTo(lectureFlow);
        example.setOrderByClause("ORDINAL");
        return lectureInfoTargetMapper.selectByExample(example);
    }

    /**
     * @param recordFlow
     * @Department：研发部
     * @Description 根据主键查询当前用户报名签到信息
     * @Author fengxf
     * @Date 2020/2/13
     */
    @Override
    public ResLectureScanRegist getLectureScanRegistInfoByFlow(String recordFlow) {
        return lectureScanRegistMapper.selectByPrimaryKey(recordFlow);
    }

    private static Logger logger = LoggerFactory.getLogger(ResLectureScanRegist.class);


}
