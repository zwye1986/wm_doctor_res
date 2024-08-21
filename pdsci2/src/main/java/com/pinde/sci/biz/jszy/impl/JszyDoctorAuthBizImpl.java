package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyDoctorAuthBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.DoctorAuthMapper;
import com.pinde.sci.model.mo.DoctorAuth;
import com.pinde.sci.model.mo.DoctorAuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class JszyDoctorAuthBizImpl implements IJszyDoctorAuthBiz {

    @Autowired
    private DoctorAuthMapper doctorAuthMapper;
    @Override
    public int edit(DoctorAuth doctorAuth) {
        if(doctorAuth != null){
            if(StringUtil.isNotBlank(doctorAuth.getRecordFlow())){//修改
                GeneralMethod.setRecordInfo(doctorAuth, false);
                DoctorAuthExample example = new DoctorAuthExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andRecordFlowEqualTo(doctorAuth.getRecordFlow());
                return this.doctorAuthMapper.updateByExampleSelective(doctorAuth,example);
            }else{//新增
                doctorAuth.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(doctorAuth, true);
                return this.doctorAuthMapper.insertSelective(doctorAuth);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public DoctorAuth searchAuthByOperUserFlow(String operUserFlow) {
        DoctorAuthExample example = new DoctorAuthExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOperUserFlowEqualTo(operUserFlow);
        List<DoctorAuth> doctorAuths =  doctorAuthMapper.selectByExample(example);
        if(doctorAuths != null && doctorAuths.size() > 0){
            return doctorAuths.get(0);
        }
        return null;
    }

    @Override
    public List<DoctorAuth> searchAuthsByOperUserFlow(String operUserFlow) {
        if(StringUtil.isNotBlank(operUserFlow))
        {
            DoctorAuthExample example = new DoctorAuthExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andOperUserFlowEqualTo(operUserFlow);
            List<DoctorAuth> doctorAuths =  doctorAuthMapper.selectByExample(example);
            return doctorAuths;
        }
        return null;
    }

    @Override
    public Map<String, String> saveInfo(String recordFlow, MultipartFile file, String fileAddress) {
        Map<String, String> map=new HashMap<String, String>();
        map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
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
                map.put("error", GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
                return  map;

            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
                return  map;
            }
            try {
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+fileAddress+File.separator + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = fileAddress+"/"+dateString+"/"+fileName;
                if(StringUtil.isNotBlank(recordFlow)){
                    DoctorAuth resRec=readByFlow(recordFlow);
                    resRec.setImageUrl(url);
                    edit(resRec);
                    map.put("url",url);
                }
                map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);

                FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
                String localFilePath=fileDir+File.separator+fileName;
                String ftpDir= fileAddress+File.separator +dateString ;
                String ftpFileName=fileName;
                ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public DoctorAuth readByFlow(String recordFlow) {
        return doctorAuthMapper.selectByPrimaryKey(recordFlow);
    }
}
