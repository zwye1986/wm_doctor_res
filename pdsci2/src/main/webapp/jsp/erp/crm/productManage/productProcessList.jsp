
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="true"/>
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
		<jsp:param name="jquery_mask" value="true"/>
	</jsp:include>
		</head>
<body>

<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<thead>
			<tr>
				<th>跟进内容</th>
				<th>跟进时间</th>
				<th>跟进人</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty processList }">
		<tr>
			<td colspan="8">无记录</td>
		</tr>
		</c:if>
		<c:forEach items="${processList }" var="manage">
			<tr>
				<td>${manage.processContent }</td>
				<td>${manage.processTime }</td>
				<td>${manage.userName }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		</div>
	</div>
</div>
</body>