package com.pinde.sci.common.ueditor;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.InitConfig;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet("/kindeditor/upload")
public class KindeditorUpload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uploadBaseDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"));
		String uploadBaseUrl = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"));
		//设置Response响应的编码
		resp.setContentType("text/html; charset=UTF-8");
		//获取一个Response的Write对象
		PrintWriter writer = resp.getWriter();
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2");
		//最大文件大小
		long maxSize = 10 * 1024 * 1024;
		//判断是否是一个文件
		if (!ServletFileUpload.isMultipartContent(req)) {
			writer.println(getError("请选择文件。"));
			return;
		}
		String dirName = req.getParameter("dir");
		if (StringUtil.isBlank(dirName)) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			writer.println(getError("目录名不正确。"));
			return;
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile file = multipartRequest.getFile("imgFile");
		if(file.getSize() > 0){
			// 检查文件大小
			if (file.getSize() > maxSize) {
				writer.println(getError("文件大小不能超过10M。"));
				return;
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			//检查扩展名
			String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
				writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return;
			}
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = uploadBaseDir + File.separator + dirName + File.separator + dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			String newFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, newFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			// 重写后的文件路径
			String path = dirName + File.separator + dateString + File.separator + newFilename;
			// 发送给KE
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", uploadBaseUrl + File.separator + path);
			obj.put("title", originalFilename);
			writer.println(obj.toJSONString());
			//将writer对象中的内容输出
			writer.flush();
			//关闭writer对象
			writer.close();
		}
	}

	//一个私有的方法，用于响应错误信息
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}