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
		if(false==$("#sysDeptForm").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value='/sch/rotationCfg/saveArrangeCfg'/>";
		var data = $('#sysDeptForm').serialize();
		jboxPost(url, data, function() {
			window.parent.frames['mainIframe'].window.search();
			jboxClose();
		},null,true);
	
	}
</script>
</head>
<body>	
<form id="sysDeptForm">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="350px;" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th>年级：</th>
						<td>
							<c:if test="${canEdit eq 'Y'}">
								<input class="validate[required] input" type="text" name="sessionNumber"
									   value="${time.sessionNumber}" onClick="WdatePicker({dateFmt:'yyyy'})"
									   readonly="readonly" />
							</c:if>
							<c:if test="${canEdit ne 'Y'}">
								${time.sessionNumber}
							</c:if>
						</td>
					</tr>
					<tr>
						<th>轮转开始月份：</th>
						<td>
							<c:if test="${canEdit eq 'Y'}">
								<input class="validate[required] input" type="text" id="startDate" name="startDate"
									   value="${time.startDate}" onClick="WdatePicker({dateFmt:'MM-01'})"
									   readonly="readonly" />
							</c:if>
							<c:if test="${canEdit ne 'Y'}">
								${time.startDate}
							</c:if>
						</td>
					</tr>
				</table>
				<div class="button" style="width: 350px;">
					<input  type="hidden" name="orgFlow" value="${orgFlow}"/>
					<input  type="hidden" name="recordFlow" value="${time.recordFlow}"/>
					<c:if test="${canEdit eq 'Y'}">
						<input type="button" value="保&#12288;存" class="search" onclick="doSave();" />
					</c:if>
					<c:if test="${canEdit ne 'Y'}">
						当前年级已有学员安排排班，无法再次修改。
					</c:if>
					<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>