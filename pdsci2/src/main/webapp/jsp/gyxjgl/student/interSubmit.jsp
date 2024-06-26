
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
</script>
</head>
<body>
	<div style="text-align: center;font-size: 16px;margin-bottom: 10px;">您当前总共选修课程5门，总学时156，总学分13分,</div>
	<div style="text-align: center;font-size: 16px;margin-bottom: 15px;"><span style="color: red;">确认</span>提交后将无法再进行修改，点击<span style="color: red;">取消</span>返回选课页面</div>
	<div style="text-align: center;"><input type="button" class="search" value="确认" onclick="jboxClose();"/>
		<input type="button" class="search" value="取消" onclick="jboxClose();"/>
	</div>
</body>
</html>