package com.pinde.sci.biz.pub.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyDoctorReductionBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.PubFileExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FileBizImpl implements IFileBiz {

	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private ResDoctorRecruitMapper recruitMapper;
	@Autowired
	private IJszyDoctorReductionBiz reductionBiz;

	@Override
	public PubFile readFile(String fileFlow) {
		return pubFileMapper.selectByPrimaryKey(fileFlow);
	}

	@Override
	public String addFile(MultipartFile mulFile , String userFlow) {
		String originalFileName =  mulFile.getOriginalFilename();
		String suffix =originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		PubFile file  = new PubFile();
		file.setFileFlow(PkUtil.getUUID());
		file.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		file.setCreateTime(DateUtil.getCurrDateTime());
		file.setCreateUserFlow(userFlow);
		file.setFileSuffix(suffix);
		if (mulFile != null) {
			file.setFileSize(new BigDecimal(mulFile.getSize()));
		}
		try {
			InputStream is = mulFile.getInputStream();
			  byte[] fileData = new byte[(int) mulFile.getSize()];
			    is.read(fileData);
			    file.setFileName(originalFileName);
			    file.setFileContent(fileData);
		} catch (IOException e) {
			throw new RuntimeException("文件上传异常");
		}
		pubFileMapper.insert(file);
		return file.getFileFlow();
	}

//	@Override
//	public List<PubFile> searchFile(PubFile file) {
//		PubFileExample docExample  = new PubFileExample();
//		Criteria crieria =  docExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(file.getFileName())){
//			crieria.andFileNameLike("%"+file.getFileName()+"%");
//		}
////		if(StringUtil.isNotBlank(file.getProjFlow())){
////			crieria.andProjFlowEqualTo(file.getProjFlow());
////		}
////		if(StringUtil.isNotBlank(file.getRecFlow())){
////			crieria.andRecFlowEqualTo(file.getRecFlow());
////		}
//		docExample.setOrderByClause("create_time desc");
//		return pubFileMapper.selectByExample(docExample);
//		
//	}

	@Override
	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
                /*文件是否存在*/
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
					fileName = URLEncoder.encode(fileName, "UTF-8");
					if(StringUtil.isNotBlank(file.getFileSuffix())&&StringUtil.isNotBlank(fileName) && !fileName.contains(file.getFileSuffix())) {
						fileName += "." + file.getFileSuffix();
					}
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
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	@Override
	public void downPubFile2(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
			/*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
				/*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+File.separator+file.getFilePath();
				File downLoadFile = new File(filePath);
				/*文件是否存在*/
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
					fileName = URLEncoder.encode(fileName, "UTF-8");
					if(StringUtil.isNotBlank(file.getFileSuffix())&&StringUtil.isNotBlank(fileName) && !fileName.contains(file.getFileSuffix())) {
						fileName += "." + file.getFileSuffix();
					}
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
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}


	@Override
	public List<PubFile> searchFile(List<String> fileFlows) {
		PubFileExample docExample  = new PubFileExample();
		Criteria crieria =  docExample.createCriteria();
		crieria.andFileFlowIn(fileFlows).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		docExample.setOrderByClause("CREATE_TIME");
		return this.pubFileMapper.selectByExample(docExample);

	}


	@Override
	public void down(PubFile file, final HttpServletResponse response) throws Exception{
        /*文件是否存在*/
        Boolean fileExists = false;
		if(file !=null){
            byte[] data=null;
            long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
            if("1".equals(file.getFileUpType()) && StringUtil.isNotBlank(file.getFilePath())){
                /*获取文件物理路径*/
                String filePath = InitConfig.getSysCfg("srm_apply_file") + file.getFilePath();

                File downLoadFile = new File(filePath);
                /*文件是否存在*/
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }else {
                data = file.getFileContent();
                if (data != null) {
                    dataLength = data.length;
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = file.getFileName();
                   // fileName = URLEncoder.encode(fileName, "UTF-8");
                    if (StringUtil.isNotBlank(file.getFileSuffix())) {
                        fileName += "." + file.getFileSuffix();
                    }else{
						String [] fileSuffixs = file.getFilePath().split("\\.");
						if(null != fileSuffixs && fileSuffixs.length>1){
							fileName += "." + fileSuffixs[fileSuffixs.length-1];
						}
					}
					fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1" );
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
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
	}

	@Override
	public void addFile(PubFile file) {
		if (file.getFileContent() != null) {
			file.setFileSize(new BigDecimal(file.getFileContent().length));
		}
		this.pubFileMapper.insert(file);
	}

	@Override
	public int editFile(PubFile file) {
		if(file!=null){
			if (file.getFileContent() != null) {
				file.setFileSize(new BigDecimal(file.getFileContent().length));
			}
			if(StringUtil.isNotBlank(file.getFileFlow())){//修改
				GeneralMethod.setRecordInfo(file, false);
				return this.pubFileMapper.updateByPrimaryKeySelective(file);
			}else{//新增
				file.setFileFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(file, true);
				return this.pubFileMapper.insertSelective(file);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveFile(PubFile file) {
		if (file.getFileContent() != null) {
			file.setFileSize(new BigDecimal(file.getFileContent().length));
		}
		GeneralMethod.setRecordInfo(file, true);
		return this.pubFileMapper.insertSelective(file);
	}

	/**
	　* 把一个文件转化为字节
	　* @param file
	　* @return byte[]
	　* @throws Exception
	　*/
	public static byte[] getByte(File file) throws Exception{
		if (file == null) {
            return null;
        }
        try {
        	int length = (int) file.length();
			if(length>Integer.MAX_VALUE) {	//当文件的长度超过了int的最大值
				System.out.println("this file is max ");
			}
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);
			byte[] b = new byte[length];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
	}


	@Override
	public void downFile(File file, final HttpServletResponse response) throws Exception{
	    byte[] data =  getByte(file);
	    int dataLength = 0;
	    if (data != null) {
	    	dataLength = data.length;
	    }
	    String fileName = file.getName();
	    fileName = URLEncoder.encode(fileName, "UTF-8");
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
	}

    /**
     * 删除附件
     *
     * @param fileFlows
     */
    @Override
    public int deleteFile(List<String> fileFlows) {
		int i = 0;
        if (!fileFlows.isEmpty()){
            boolean delSuccess = false;
        PubFile file = new PubFile();
        String basePath = InitConfig.getSysCfg("srm_apply_file");
        file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        PubFileExample docExample = new PubFileExample();
        Criteria crieria = docExample.createCriteria();
        crieria.andFileFlowIn(fileFlows).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        docExample.setOrderByClause("CREATE_TIME");
        List<PubFile> pubFileList = pubFileMapper.selectByExample(docExample);
            delFile(pubFileList);
            i = pubFileMapper.updateByExampleSelective(file, docExample);
        }
		return i;
    }
    @Override
    public int deleteAfterFile(String fileFlow) {
        PubFile file = readFile(fileFlow);
		if(file!=null)
		{
			file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            delAfterFile(file);
            return pubFileMapper.updateByPrimaryKeySelective(file);
        }
		return 0;
    }

    /**
     * 根据主表类型，流水号查找文件
     * @param productType
     * @param productFlow
     * @return
     */
    @Override
    public List<PubFile> findFileByTypeFlow(String productType, String productFlow) {
        PubFileExample example = new PubFileExample();
        if (productFlow != null) {
            Criteria crieria = example.createCriteria();
            crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProductFlowEqualTo(productFlow);
            if (productType != null) {
                crieria.andProductTypeEqualTo(productType);
            }
            example.setOrderByClause("CREATE_TIME");
        }
        return pubFileMapper.selectByExample(example);
    }
    @Override
    public List<PubFile> findFileByLikeTypeFlow(String productType, String productFlow) {
        PubFileExample example = new PubFileExample();
        if (productFlow != null) {
            Criteria crieria = example.createCriteria();
            crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProductFlowEqualTo(productFlow);
            if (productType != null) {
                crieria.andProductTypeLike(productType+"%");
            }
            example.setOrderByClause("CREATE_TIME");
        }
        return pubFileMapper.selectByExample(example);
    }
    /**
     * 根据主表类型，流水号删除文件
     * @param productType
     * @param productFlow
     * @return
     */
    @Override
    public void deleteFileByTypeFlow(String productType, String productFlow) {
        PubFileExample example = new PubFileExample();
        PubFile file = new PubFile();
        file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        if (productFlow != null) {
            Criteria crieria = example.createCriteria();
            crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProductFlowEqualTo(productFlow);
            if (productType != null) {
                crieria.andProductTypeEqualTo(productType);
            }
            List<PubFile> pubFileList = pubFileMapper.selectByExample(example);
            delFile(pubFileList);
            pubFileMapper.updateByExampleSelective(file, example);
        }
    }

	@Override
	public List<PubFile> searchByProductFlow(String productFlow) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(productFlow)){
			criteria.andProductFlowEqualTo(productFlow);
		}else{
			return new ArrayList<>();
		}
		return pubFileMapper.selectByExample(example);
	}

	@Override
	public PubFile readDocGraduationFile(PubFile search) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(search.getProductType())){
			criteria.andProductTypeEqualTo(search.getProductType());
		}
		if(StringUtil.isNotBlank(search.getCreateUserFlow())){
			criteria.andCreateUserFlowEqualTo(search.getCreateUserFlow());
		}
		List<PubFile> list=pubFileMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	/**
	 *
	 *
	 * @return
	 */
	@Override
	public List<PubFile> searchByProductFlowAndNotInFileFlows(String productFlow,List<String> fileFlows) {
		PubFileExample example = new PubFileExample();
		if (productFlow != null) {
			Criteria crieria = example.createCriteria();
			crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProductFlowEqualTo(productFlow);
			if (fileFlows != null && fileFlows.size()>0) {
				crieria.andFileFlowNotIn(fileFlows);
			}
			example.setOrderByClause("CREATE_TIME");
		}
		return pubFileMapper.selectByExample(example);
	}

	@Override
	public void saveFiles(List<PubFile> files) {
		if(files!=null&&files.size()>0)
		{
			for (PubFile pubFile : files) {
				if(StringUtil.isNotBlank(pubFile.getFileFlow()))
				{
					GeneralMethod.setRecordInfo(pubFile,false);
					pubFileMapper.updateByPrimaryKeySelective(pubFile);
				}else{
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile,true);
					pubFileMapper.insertSelective(pubFile);
				}

			}
		}
	}

	@Override
	public String addAfterFile(MultipartFile file, String fileType, String productFlow) {
		//保存附件
		PubFile pubFile = new PubFile();
		//取得当前上传文件的文件名称
		String oldFileName = file.getOriginalFilename();
		//如果名称不为“”,说明该文件存在，否则说明该文件不存在
		if (StringUtil.isNotBlank(oldFileName)) {
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + fileType + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			String originalFilename = "";
			originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			String filePath = File.separator + fileType + File.separator + dateString + File.separator + originalFilename;
			pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			pubFile.setFilePath(filePath);
			pubFile.setFileName(oldFileName);
			pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
			pubFile.setProductType(fileType);
			pubFile.setProductFlow(productFlow);
			int c=editFile(pubFile);
			if(c==1) {
				return pubFile.getFileFlow();
			}else{
				return "fail";
			}
		}
		return "fail";
	}

	@Override
	public String addDeptFile(MultipartFile file, String productFlow) {
		//保存附件
		PubFile pubFile = new PubFile();
		String fileType="resDeptFile";
		//取得当前上传文件的文件名称
		String oldFileName = file.getOriginalFilename();
		//如果名称不为“”,说明该文件存在，否则说明该文件不存在
		if (StringUtil.isNotBlank(oldFileName)) {
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + fileType + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			String originalFilename = "";
			originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			String filePath = File.separator + fileType + File.separator + dateString + File.separator + originalFilename;
			pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			pubFile.setFilePath(filePath);
			pubFile.setFileName(oldFileName);
			pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
			pubFile.setProductType(fileType);
			pubFile.setProductFlow(productFlow);
			pubFile.setFileFlow(productFlow);
			int c=0;
			PubFile old=readFile(productFlow);
			if(old!=null) {//修改
				GeneralMethod.setRecordInfo(pubFile, false);
				c= this.pubFileMapper.updateByPrimaryKeySelective(pubFile);
			}else{
				GeneralMethod.setRecordInfo(pubFile, true);
				c= this.pubFileMapper.insertSelective(pubFile);
			}
			if(c==1) {
				return pubFile.getFileFlow();
			}else{
				return "fail";
			}
		}
		return "fail";
	}

	@Override
	public List<PubFile> findFileByTypeFlows(String productType, List<String> productFlows) {
		PubFileExample example = new PubFileExample();
		PubFileExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(productFlows!=null&&productFlows.size()>0)
		{
			criteria.andProductFlowIn(productFlows);
		}
		if(StringUtil.isNotBlank(productType)){
			criteria.andProductTypeEqualTo(productType);
		}
		example.setOrderByClause("CREATE_TIME");
		return pubFileMapper.selectByExample(example);
	}

	@Override
	public PubFile readProductFile(String resultFlow, String fileType) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andProductTypeEqualTo(fileType);
		criteria.andProductFlowEqualTo(resultFlow);
		List<PubFile> list=pubFileMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PubFile> findFileByType(String productType,String productFlow,String testId) {
		PubFileExample example = new PubFileExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andProductTypeEqualTo(productType);
		if(StringUtil.isNotBlank(productFlow)){
			criteria.andProductFlowEqualTo(productFlow);
		}
		if(StringUtil.isNotBlank(testId)){
			criteria.andFileUpTypeEqualTo(testId);
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return pubFileMapper.selectByExampleWithBLOBs(example);
	}

	private void delFile(List<PubFile> pubFileList) {
        String basePath = InitConfig.getSysCfg("srm_apply_file");
        if (!pubFileList.isEmpty()) {
            for (PubFile pubFile : pubFileList) {
                if ("1".equals(pubFile.getFileUpType()) && StringUtil.isNotBlank(pubFile.getFilePath())) {
                    String filePath = basePath + pubFile.getFilePath();
                    FileUtil.deletefile(filePath);
                }
            }
        }
    }
	private void delAfterFile(PubFile pubFile) {
        String basePath = InitConfig.getSysCfg("upload_base_dir");
		if(pubFile!=null){
			String filePath = basePath + pubFile.getFilePath();
			FileUtil.deletefile(filePath);
		}
    }

	@Override
	public List<PubFile> searchByProductFlowAndTypeAndNotInFileFlows(String productFlow, List<String> fileFlows, String type) {
		PubFileExample example = new PubFileExample();
		if (productFlow != null) {
			Criteria crieria = example.createCriteria();
			crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
					andProductFlowEqualTo(productFlow)
			.andProductTypeEqualTo(type);
			if (fileFlows != null && fileFlows.size()>0) {
				crieria.andFileFlowNotIn(fileFlows);
			}
			example.setOrderByClause("CREATE_TIME");
			return pubFileMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<PubFile> findFileByDoctorFlow(String doctorFlow) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andDoctorStatusNameEqualTo("在培");
		List<ResDoctorRecruit> recruitList = recruitMapper.selectByExample(example);
		if(null != recruitList && !recruitList.isEmpty()){
			ResDoctorReduction reduction = reductionBiz.findReductionByRecruitFlow(recruitList.get(0).getRecruitFlow());
			if(null != reduction){
				return findFileByTypeFlow("Reduction", reduction.getRecordFlow());
			}
		}
		return null;
	}
}
