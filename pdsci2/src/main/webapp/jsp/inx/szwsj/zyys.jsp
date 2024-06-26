<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
</head>

<body>
<div id="register">
<div id="logo"><img src="<s:url value='zyys.png' />" /></div>
<div style="height:80px"></div>
<div id="station">
 	<ul>
 	<li>		
		<a href="http://localhost:9000/Default.aspx?UserID=admin&PassWord=123456&Type=Admin&RoleID=30"/>
			系统管理员(后台)
		</a>
	</li>
 	<li>		
		<a href="http://localhost:9000/Default.aspx?UserID=admin&PassWord=123456&Type=Website&RoleID=30"/>
			系统管理员（前台）
		</a>
	</li>
 	<li>		
		<a href="http://localhost:9000/Default.aspx?UserID=A0114&PassWord=123456&Type=Website&RoleID=14"/>
			科主任
		</a>
	</li>
 	<li>		
		<a href="http://localhost:9000/Default.aspx?UserID=A0087&PassWord=123456&Type=Website&RoleID=6"/>
			带教老师
		</a>
	</li>
 	<li>		
		<a href="http://localhost:9000/Default.aspx?UserID=20121457&PassWord=123456&Type=Website&RoleID=1"/>
			住院医师
		</a>
	</li>
    </ul>
</div>
<div id="footer">技术支持：南京品德信息技术有限公司<br />Copyright © 2001- 2014 Character Technology, Inc. All rights reserved.</div>
</div>
</body>
</html>