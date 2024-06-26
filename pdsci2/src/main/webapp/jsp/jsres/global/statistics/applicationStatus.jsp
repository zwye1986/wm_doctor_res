<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">

</script>
<body>
	<table class="grid" style="width: 100%;margin-top: 15px">
		<tr>
			<th width="150px">姓名</th>
			<td style="text-align: left;">
				<input  type="text" class="select validate[required]" readonly value="${teacher.doctorName}"  style="text-align: left;width: 150px;"/>
			</td>
			<th width="150px">师资类型</th>
			<td style="text-align: left;">
				<input  type="text" class="select validate[required]" readonly value="${teacher.applicationTeacherLevelId == 'GeneralFaculty' ? '一般师资' : '骨干师资'}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th>申请说明</th>
			<td colspan="3">
				<textarea style="height: 50px;width: 630px" id="applicationMessage" class="validate[required]" name="applicationMessage" >${teacher.applicationMessage}</textarea>
			</td>
		</tr>
		<tr>
			<th width="150px">师资证明</th>
			<td colspan="3">
				<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" width="80px" height="80px"/></a>
			</td>
		</tr>
	</table>
<%--	<font size="3">审核结果</font>--%>
	<div i="title" style="font-weight: bold;cursor: default;font-size:14px; margin-top: 10px">审核结果</div>
	<table class="grid" style="width: 100%;margin-top: 15px">
		<tr>
			<th width="150px">审核状态</th>
			<td colspan="3" style="text-align: left;">
				<input  type="text" class="select validate[required]" readonly value="${teacher.applicationAuditStatus == 'HeadAuditing' ? '待科主任审核' : teacher.applicationAuditStatus == 'HeadNotPassed' ? '科主任审核不通过' : teacher.applicationAuditStatus == 'HeadPassed' ? '待基地审核' : teacher.applicationAuditStatus == 'Passed' ? '基地审核通过' : '基地审核不通过'}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<c:if test="${teacher.applicationAuditStatus == 'NotPassed' || teacher.applicationAuditStatus == 'HeadNotPassed'}">
			<tr>
				<th>审核意见</th>
				<td colspan="3">
					<textarea style="height: 50px;width: 630px" id="applicationAuditMessage" readonly class="validate[required]" name="applicationAuditMessage" >${teacher.applicationAuditMessage}</textarea>
				</td>
			</tr>
		</c:if>
	</table>
</body>

