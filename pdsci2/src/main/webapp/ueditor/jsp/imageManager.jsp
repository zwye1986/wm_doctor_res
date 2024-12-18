<%@page import="com.pinde.core.util.StringUtil" %>
<%@page import="com.pinde.sci.common.InitConfig"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.io.File" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.lang.System" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Iterator" %>
<%
    //仅做示例用，请自行修改
	String path = "images";
	String imgStr ="";
	String realpath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+"\\"+path;
	List<File> files = getFiles(realpath, new java.util.ArrayList());
	for(File file :files ){
		imgStr+=file.getPath().replace(StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")),"")+"ue_separate_ue";
	}
	if(imgStr!=""){
        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
    }
	out.print(imgStr);	
	System.err.println(imgStr);	
%>
<%!
public List getFiles(String realpath, List files) {
	
	File realFile = new File(realpath);
	if (realFile.isDirectory()) {
		File[] subfiles = realFile.listFiles();
		for(File file :subfiles ){
			if(file.isDirectory()){
				getFiles(file.getAbsolutePath(),files);
			}else{
				if(!getFileType(file.getName()).equals("")) {
					files.add(file);
				}
			}
		}
	}
	return files;
}

public String getRealPath(HttpServletRequest request,String path){
	ServletContext application = request.getSession().getServletContext();
	String str = application.getRealPath(request.getServletPath());
	return new File(str).getParent();
}

public String getFileType(String fileName){
	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	Iterator<String> type = Arrays.asList(fileType).iterator();
	while(type.hasNext()){
		String t = type.next();
		if(fileName.toLowerCase().endsWith(t)){
			return t;
		}
	}
	return "";
}
%>