package com.pinde.res.ctrl.upload;


import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysCfg;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.core.common.sci.dao.SysCfgMapper;
import com.pinde.sci.util.PicZoom;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/res/uploadFile")
public class UploadFileController {
	private static Logger logger = LoggerFactory.getLogger(UploadFileController.class);


	@Resource
	private SysCfgMapper cfgMapper;

	@RequestMapping(value={"/addImage"})
	public void addImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model){
		if(!(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData()))){
			SysCfg cfg = cfgMapper.selectByPrimaryKey("upload_base_dir");
			if(null!=null&&StringUtil.isNotBlank(cfg.getCfgValue())) {
				String dateString = DateUtil.getCurrDate2();
				String newDir = cfg.getCfgValue() + File.separator + "summary" + File.separator + dateString;
				String fileName = form.getFileName();
				String preffix = fileName.substring(0, fileName.lastIndexOf("."));
				String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
				File fileDir = new File(newDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				try {
					String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(), "");
					byte[] data = null;
					if (StringUtil.isNotBlank(imgDataString)) {
						BASE64Decoder decoder = new BASE64Decoder();
						// Base64解码
						data = decoder.decodeBuffer(imgDataString);
						for (int i = 0; i < data.length; ++i) {
							if (data[i] < 0) {// 调整异常数据
								data[i] += 256;
							}
						}
					}
					if (data == null) {
						data = form.getUploadFile().getBytes();
					}
					File imageFile = new File(fileDir, fileName);
					if (form.getUploadFile() != null) {
						form.getUploadFile().transferTo(imageFile);
					} else {
						FileOutputStream fos = new FileOutputStream(imageFile);
						fos.write(data);
						fos.flush();
						fos.close();
					}
					//处理缩略图...
					String thumbFileName = preffix + "_thumb" + suffix;
					File thumbFile = new File(fileDir, thumbFileName);
					FileOutputStream fos = new FileOutputStream(thumbFile);
					//调用PicZoom类的静态方法zoom对原始图像进行缩放。
					BufferedImage buffImg = PicZoom.zoom(data);
					//创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
					JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos);
					//编码BufferedImage对象到JPEG数据输出流。
					jpgEncoder.encode(buffImg);
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
                    logger.error("", e);
				} catch (IOException e) {
                    logger.error("", e);
				}
			}
		}
	}


	@RequestMapping(value={"/addFile"},method={RequestMethod.POST})
	@ResponseBody
	public String addFile(FileForm form, HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String,String> map=new HashMap<>();
		map.put("resultId", "200");
		map.put("resultType", "交易成功");
		if(form.getUploadFile()==null){
			map.put("resultId", "31703");
			map.put("resultType", "上传文件不能为空");
		}
		MultipartFile uploadFile=form.getUploadFile();
		System.err.println("=====filename:"+uploadFile.getOriginalFilename());
		String dirUrl="e:/uploadFile/";
		File dir=new File(dirUrl);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		File file=new File(dirUrl+uploadFile.getOriginalFilename());
		try {
			uploadFile.transferTo(file);
		} catch (IOException e) {
            logger.error("", e);
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value={"/downFile"})
	public void downFile( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		/*文件是否存在*/
		Boolean fileExists = false;
		String dirUrl="e:/uploadFile/";
		String filePath=dirUrl+"/22222.xlsx";
		File downLoadFile=new File(filePath);
			byte[] data=null;
			long dataLength = 0;
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			if(fileExists) {
				try {
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"22222.xlsx\"");
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
	}


}

