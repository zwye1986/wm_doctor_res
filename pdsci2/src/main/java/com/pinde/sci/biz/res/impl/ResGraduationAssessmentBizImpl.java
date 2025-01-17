package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResGraduationAssessmentMapper;
import com.pinde.core.model.PubFile;
import com.pinde.core.model.ResGraduationAssessmentWithBLOBs;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResGraduationAssessmentBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.res.ResGraduationAssessmentExtMapper;
import com.pinde.core.model.ResGraduationAssessmentExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResGraduationAssessmentBizImpl implements IResGraduationAssessmentBiz {
    @Autowired
    private ResGraduationAssessmentMapper graduationAssessmentMapper;
    @Autowired
    private ResGraduationAssessmentExtMapper graduationAssessmentExtMapper;

    @Autowired
    private IFileBiz pubFileBiz;

    private static final Logger logger = LoggerFactory.getLogger(ResGraduationAssessmentBizImpl.class);

    @Override
    public ResGraduationAssessmentExt getDocGraduationAssessment(String userFlow) {
        return graduationAssessmentExtMapper.getDocGraduationAssessment(userFlow);
    }

    @Override
    public int save(ResGraduationAssessmentWithBLOBs bean) {
        if(StringUtil.isBlank(bean.getRecordFlow())) {
            GeneralMethod.setRecordInfo(bean, true);
            bean.setRecordFlow(PkUtil.getUUID());
            return graduationAssessmentMapper.insert(bean);
        }else{
            GeneralMethod.setRecordInfo(bean, false);
            return graduationAssessmentMapper.updateByPrimaryKeySelective(bean);
        }
    }

    @Override
    public String checkFile(MultipartFile uploadFile) {
        List<String> mimeList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("jszy_file_support_mime")))){
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("jszy_file_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("jszy_file_support_suffix")))){
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("jszy_file_support_suffix").toLowerCase()).split(","));
        }
        String fileType = uploadFile.getContentType();//MIME类型;
        String fileName = uploadFile.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
            return "请上传 "+InitConfig.getSysCfg("jszy_file_support_suffix")+"格式的文件";
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
    }

    @Override
    public String saveFileToDirs(String fileFlow, String productType, MultipartFile file, SysUser user) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
        if(file.getSize() > 0){
            PubFile search=pubFileBiz.readFile(fileFlow);
            if(search==null) {
                search =new PubFile();
                search.setCreateUserFlow(user.getUserFlow());
                search.setProductType("GRADUATION_FILE");
                search=pubFileBiz.readDocGraduationFile(search);
            }
            String oldFolderName="";
            if(search!=null)
            {
                oldFolderName=search.getFilePath();
            }
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + "graduationFile" + File.separator+ user.getUserFlow() ;
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
                logger.error("", e);
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
                    logger.error("", e);
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path =  "graduationFile"+ "/"+ user.getUserFlow() + "/"+ originalFilename;
            if(search==null){
                search=new PubFile();
            }
            search.setProductType(productType);
            search.setFileUpType("1");
            search.setFileSuffix(suffix);
            search.setFilePath(path);
            search.setFileName(fileName);
            search.setFileRemark(user.getUserName()+"结业论文");
            int count=pubFileBiz.editFile(search);
            if(count==0){
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            }else{
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return path;
    }

    @Override
    public String uploadImg(String recordFlow, MultipartFile file) {
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
            String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();//后缀名
            if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                return "只支持上传"+InitConfig.getSysCfg("inx_image_support_suffix")+"！";
            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if(file.getSize()>limitSize*1024*1024*2){
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize * 2 + "M";
            }
            try {
				/*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"graduationScoreImage"+File.separator  + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
				/*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "graduationScoreImage/"+dateString+"/"+fileName;
                if(StringUtil.isNotBlank(recordFlow)){
                    ResGraduationAssessmentWithBLOBs resGraduationAssessment = new ResGraduationAssessmentWithBLOBs();
                    resGraduationAssessment.setRecordFlow(recordFlow);
                    resGraduationAssessment.setAssessmentImgUrl(url);
                    save(resGraduationAssessment);
                }
                return "success:"+url;
            } catch (Exception e) {
                logger.error("", e);
                return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    @Override
    public ResGraduationAssessmentWithBLOBs getGraduationAssessmentWithBlobByFlow(String recordFlow) {
        return graduationAssessmentMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public void down(PubFile file, HttpServletResponse response) throws Exception{
        /*文件是否存在*/
        Boolean fileExists = false;
        if(file !=null){
            byte[] data=null;
            long dataLength = 0;
            /*如果文件相对路径不为空*/
            if( StringUtil.isNotBlank(file.getFilePath())){
                /*获取文件物理路径*/
                String filePath = InitConfig.getSysCfg("upload_base_dir") + File.separator + file.getFilePath();

                File downLoadFile = new File(filePath);
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = file.getFileName();
                    fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    fileExists = false;
                }
            }
        }else {
            fileExists = false;
        }
        if(!fileExists){
            /*设置页面编码为UTF-8*/
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes(StandardCharsets.UTF_8));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }
    @Override
    public void downImg(ResGraduationAssessmentWithBLOBs ext, HttpServletResponse response) throws Exception{
        /*文件是否存在*/
        Boolean fileExists = false;
        if(ext !=null){
            byte[] data=null;
            long dataLength = 0;
            String sux=".jpg";
            /*如果文件相对路径不为空*/
            if( StringUtil.isNotBlank(ext.getAssessmentImgUrl())){
                /*获取文件物理路径*/
                String filePath = InitConfig.getSysCfg("upload_base_dir") + File.separator +ext.getAssessmentImgUrl();
                sux=ext.getAssessmentImgUrl().substring(ext.getAssessmentImgUrl().lastIndexOf("."));
                File downLoadFile = new File(filePath);
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = "结业考核打分表";
                    fileName = URLEncoder.encode(fileName, "UTF-8")+sux;
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    fileExists = false;
                }
            }
        }else {
            fileExists = false;
        }
        if(!fileExists){
            /*设置页面编码为UTF-8*/
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现结业考核打分表文件,点击返回上一页</a>".getBytes(StandardCharsets.UTF_8));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }
}
