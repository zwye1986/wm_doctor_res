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

<div class="div_table">
	<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;申请记录</h5>
	<table border="0" cellspacing="0" cellpadding="0" class="base_info ">
		<tr>
			<th width="6%" style="text-align: center">序号</th>
			<th width="22%" style="text-align: center">申请师资级别</th>
			<th width="18%" style="text-align: center">申请时间</th>
			<th width="18%" style="text-align: center">审核进度</th>
			<th width="18%" style="text-align: center">审核备注</th>
			<th width="18%" style="text-align: center">审核时间</th>
		</tr>
		<c:forEach items="${teacherTrainingList}" var="teacherTraining" varStatus="status">
			<tr style="height:20px ">
				<td style="text-align: center">${status.index + 1}</td>
				<td style="text-align: center">${teacherTraining.applicationTeacherLevelId}</td>
				<td style="text-align: center">${teacherTraining.createTime}</td>
				<td style="text-align: center">${teacherTraining.applicationAuditStatus == 'HeadAuditing' ? '待科主任审核' : teacherTraining.applicationAuditStatus == 'HeadNotPassed' ? '科主任审核不通过' : teacherTraining.applicationAuditStatus == 'HeadPassed' ? '待基地审核' : teacherTraining.applicationAuditStatus == 'Passed' ? '基地审核通过' : '基地审核不通过'}</td>
				<td style="text-align: center">${teacherTraining.applicationAuditMessage}</td>
				<c:if test="${teacherTraining.applicationAuditStatus ne 'HeadAuditing' and teacherTraining.applicationAuditStatus ne 'HeadPassed'}">
					<td style="text-align: center">${teacherTraining.modifyTime}</td>
				</c:if>
				<c:if test="${teacherTraining.applicationAuditStatus eq 'HeadAuditing' or teacherTraining.applicationAuditStatus eq 'HeadPassed'}">
					<td style="text-align: center">--</td>
				</c:if>
			</tr>
		</c:forEach>
		<c:if test="${empty teacherTrainingList}">
			<tr>
				<td style="text-align: center" colspan="6">无记录</td>
			</tr>
		</c:if>
	</table>
</div>

























<%--<body>--%>
<%--	<table class="grid" style="width: 100%;margin-top: 15px">--%>
<%--		<tr>--%>
<%--			<th width="150px">姓名</th>--%>
<%--			<td style="text-align: left;">--%>
<%--				<input  type="text" class="select validate[required]" readonly value="${teacher.doctorName}"  style="text-align: left;width: 150px;"/>--%>
<%--			</td>--%>
<%--			<th width="150px">师资类型</th>--%>
<%--			<td style="text-align: left;">--%>
<%--				<input  type="text" class="select validate[required]" readonly value="${teacher.applicationTeacherLevelId == 'GeneralFaculty' ? '一般师资' : '骨干师资'}"  style="text-align: left;width: 150px;"/>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<th>申请说明</th>--%>
<%--			<td colspan="3">--%>
<%--				<textarea style="height: 50px;width: 630px" id="applicationMessage" class="validate[required]" name="applicationMessage" >${teacher.applicationMessage}</textarea>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<th width="150px">师资证明</th>--%>
<%--			<td colspan="3">--%>
<%--				<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" width="80px" height="80px"/></a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</table>--%>

<%--	<div i="title" style="font-weight: bold;cursor: default;font-size:14px; margin-top: 10px">审核结果</div>--%>
<%--	<table class="grid" style="width: 100%;margin-top: 15px">--%>
<%--		<tr>--%>
<%--			<th width="150px">审核状态</th>--%>
<%--			<td colspan="3" style="text-align: left;">--%>
<%--				<input  type="text" class="select validate[required]" readonly value="${teacher.applicationAuditStatus == 'HeadAuditing' ? '待科主任审核' : teacher.applicationAuditStatus == 'HeadNotPassed' ? '科主任审核不通过' : teacher.applicationAuditStatus == 'HeadPassed' ? '待基地审核' : teacher.applicationAuditStatus == 'Passed' ? '基地审核通过' : '基地审核不通过'}"  style="text-align: left;width: 150px;"/>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<c:if test="${teacher.applicationAuditStatus == 'NotPassed' || teacher.applicationAuditStatus == 'HeadNotPassed'}">--%>
<%--			<tr>--%>
<%--				<th>审核意见</th>--%>
<%--				<td colspan="3">--%>
<%--					<textarea style="height: 50px;width: 630px" id="applicationAuditMessage" readonly class="validate[required]" name="applicationAuditMessage" >${teacher.applicationAuditMessage}</textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--		</c:if>--%>
<%--	</table>--%>
<%--</body>--%>

