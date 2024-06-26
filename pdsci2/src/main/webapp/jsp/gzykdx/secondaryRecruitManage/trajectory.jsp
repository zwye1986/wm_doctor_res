<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
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
		var batchMap = {"0":"无","1":"第一批次","2":"第二批次","3":"第三批次","4":"第四批次","5":"第五批次","6":"第六批次",
			"7":"第七批次","8":"第八批次","9":"第九批次","10":"第十批次"}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<table class="xllist" style="width: 100%">
			<tr>
				<th>录取批次</th>
				<th>二级单位意见</th>
				<th>学校意见</th>
			</tr>
			<c:forEach items="${doctorTeacherRecruitBatches}" var="batch">
				<tr>
					<td id="batch${batch.recordFlow}"></td>
					<script>
						$("#batch${batch.recordFlow}").text(batchMap["${batch.recruitBatch}"]);
					</script>
					<td>${batch.auditStatusName}<br/>
						${batch.orgAuditMemo}
					</td>
					<td><c:if test="${batch.auditStatusId eq gzykdxAdmissionStatusEnumSchoolPassing.id}">
						${batch.schoolAuditStatusName}
					</c:if><br/>
						${batch.schoolAuditMemo}
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td><script>document.write(batchMap["${doctorTeacherRecruit.recruitBatch}"])</script></td>
					<td>${doctorTeacherRecruit.auditStatusName}<br/>${doctorTeacherRecruit.orgAuditMemo}
					</td>
					<td><c:if test="${doctorTeacherRecruit.auditStatusId eq gzykdxAdmissionStatusEnumSchoolPassing.id}">
						${doctorTeacherRecruit.schoolAuditStatusName}
					</c:if>
						<br/>${doctorTeacherRecruit.schoolAuditMemo}
					</td>
				</tr>
		</table>
		<div style="text-align: center;margin-top: 20px;">
		<input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search">
		</div>
	</div>
</div>
</body>
</html>