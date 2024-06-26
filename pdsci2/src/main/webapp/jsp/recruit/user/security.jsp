
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
<script type="text/javascript">
function modPasswd(userFlow) {
	jboxOpen("<s:url value='/sys/user/modPasswd?userFlow='/>"
			+ userFlow, "修改密码", 500, 300);
}
</script>
<style>
.ss3{text-align: right;padding-right: 80px;width: 210px;}
.ss3 a{color: #2f8cef}
</style>
</head>
<body>

<form id="sysUserForm" style="height: 100px;" >
<div class="content">
	<div class="title1 clearfix">		
		<table border="0" cellspacing="0" cellpadding="0" class="safe">
  			<tr>
   			 <td>
    			<div id="safe">
    				<div class="ss1">登录密码：</div>
   					<div class="ss3"><a href="javascript:modPasswd('${sessionScope.currUser.userFlow}')">修改</a></div></div>
    				<div class="ss2">互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</div>
    			</div>
    		 </td>
  			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>