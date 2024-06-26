<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	function doSave() {
		if(false==$("#resRecForm").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value='/res/doc/check'/>";
		var data = $('#resRecForm').serialize();
		jboxPost(url, data, function() {
			window.parent.frames['mainIframe'].window.$("li a:first").click();
			jboxClose();
		},null,true);

	}
</script>
</head>
<body>	
<form id="resRecForm" action="<s:url value="/res/doc/check"/>"style="padding-left: 33px;height: 100px;" >
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<input type="hidden" name="recordFlow" value="${resInfo.recordFlow}">
				<table width="400px;" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th>省厅审核结果：</th>
						<td>
							<input id="agree"  name="auditStatusId" value="${resBaseStatusEnumGlobalPassed.id}" type="radio" class="validate[required]" checked>
							<label for="agree">同意</label>&#12288;&#12288;
							<input id="disAgree"  name="auditStatusId" value="${resBaseStatusEnumGlobalNotPassed.id}" type="radio" class="validate[required]">
							<label for="disAgree">不同意</label>&#12288;
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<th>省厅审核意见：</th>
						<td>
							<input class="xltext" name="auditOpinion" type="text"/>
						</td>
					</tr>
				</table>
				<div class="button" style="width: 400px;">
					<input type="button" value="确&#12288;认" class="search" onclick="doSave();" />
					<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>