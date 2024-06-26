<!doctype html>
<html lang="zh-cn">
 <head>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
           pageEncoding="UTF-8"%>
  <%@include file="/jsp/common/doctype.jsp" %>
  <html>
  <head>
   <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="register" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="true"/>
   </jsp:include>
  <title>证书查询</title>
   <style>
		#main{display:block;
		position:relative;
		margin:auto;}
   </style>
 </head>
 <body>
  <img id="main" src="<s:url value='/jsp/inx/jsres/search/images/search_new.png'/>" width="100%" />
  <div class="footer_index">
   主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
  </div>
 </body>
</html>
