
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_qrcode" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<style>
		.xlselect{margin-right: 0px;}
		.xltext{margin-right: 0px;}
	</style>
	<script type="text/javascript">

		function save(){
			jboxConfirm("确认预约？", function () {
				jboxPost("<s:url value='/study/doctor/saveSubject'/>",$("#submitForm").serialize(),function(resp){
					if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
						window.parent.frames['mainIframe'].location.reload();//刷新页面
						jboxClose();
					}
				},null,true);

			})
		}

	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="submitForm">
			<input type="hidden" name="subjectFlow" value="${studySubject.subjectFlow}">
			<div id="dataTable">
				<table class="basic" style="width: 100%;margin: 10px 0px;">
					<tr>
						<td style="text-align:right;">课程名称：</td>
						<td>
							<input type="text" readonly="readonly" class="validate[required] xltext" name="subjectName" value="${studySubject.subjectName}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">课程年份：</td>
						<td>
							<input type="text" class="validate[required] select xltext" readonly="readonly"  name="subjectYear" value="${studySubject.subjectYear}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">课程类型：</td>
						<td>
							<%--<input type="text" class="validate[required] select xltext" readonly="readonly"  name="subjectYear" value="${studySubject.subjectType}"/>--%>
							<c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
								<c:if test="${dict.dictId eq studySubject.subjectType}">
									<input type="text" class="validate[required] select xltext" readonly="readonly"  name="subjectType" value="${dict.dictName}"/>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">预约开放时间：</td>
						<td>
							<input type="text" class="validate[required] xlselect" readonly="readonly"  name="subjectStartTime" value="${studySubject.subjectStartTime}"/>
								— <input type="text" class="validate[required] xlselect" readonly="readonly"   name="subjectEndTime" value="${studySubject.subjectEndTime}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">预约人员容量：</td>
						<td>
							<input type="text" readonly="readonly" class="validate[required,custom[number]] xltext" name="reservationsNum" value="${studySubject.reservationsNum}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">课程说明：</td>
						<td>
							<textarea name="subjectExplain" readonly="readonly" style="width: 98%">${studySubject.subjectExplain}</textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: center;margin-top:20px;">
				<c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="预&#12288;约" id="saveBtn"/></c:if>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();" id="closeBtn"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>
