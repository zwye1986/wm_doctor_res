<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="basic_bootstrap" value="true" />
	<jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<style type="text/css">

	#boxHome .item:HOVER{background-color: #eee;}

	.selected a{
		padding: 0;
		background: white !important;
	}

	.base_info td a{
		color: black !important;
	}

	.div_table {
		padding: 5px 30px 0;
		margin-bottom: 0px;
	}

	.vertical-line {
		background-color: lightblue; /* 线的颜色 */
		margin: 0 auto; /* 居中显示 */
	}
</style>
<script type="text/javascript">
	function saveAuditCommonSzInfo(auditStatus, recordFlow) {
		var applicationAuditMessage = $("#applicationAuditMessage").val();
		if (auditStatus == 'HeadNotPassed' || auditStatus == 'NotPassed') {
			if (applicationAuditMessage == '') {
				jboxTip("审核不通过需要写原因！");
				return false;
			}
		}
		var url = "<s:url value='/jsres/statistic/saveAuditCommonSzInfo?applicationAuditStatus='/>" + auditStatus + "&applicationAuditMessage=" + applicationAuditMessage + "&recordFlow=" + recordFlow;
		jboxPost(url, null, function (resp) {
			jboxTip(resp);
			if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
				jboxTip("审核成功！");
				window.parent.toPage(1);
				jboxClose();
			}
		}, null, true);
	}


</script>
<body>
<div style="height: 760px;overflow-y: auto">
	<div class="div_table">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;申请信息</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info " style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">申请师资级别</th>
				<td style="text-align: center">${teacherTraining.applicationTeacherLevelId}</td>
				<th style="background-color: #f4f5f9; text-align: center"></th>
				<td style="text-align: center"></td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">申请说明</th>
				<td style="text-align: left" colspan="3">${teacherTraining.applicationMessage}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">申请附件</th>
				<td style="text-align: left" colspan="3">
					<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" width="80px" height="80px"/></a>
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;个人信息</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info " style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">姓名</th>
				<td style="text-align: center">${user.userName}</td>
				<th style="background-color: #f4f5f9; text-align: center">性别</th>
				<td style="text-align: center">${user.sexName}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">手机号码</th>
				<td style="text-align: center">${user.userPhone}</td>
				<th style="background-color: #f4f5f9; text-align: center">身份证号</th>
				<td style="text-align: center">${user.idNo}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">年龄</th>
				<td style="text-align: center">${age}</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;教育信息</h5>
		<table class="base_info" style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="18%" style="text-align: center">毕业院校</th>
				<th width="16%" style="text-align: center">毕业时间</th>
				<th width="12%" style="text-align: center">毕业专业</th>
				<th width="12%" style="text-align: center">学历</th>
				<th width="12%" style="text-align: center">学历附件</th>
				<th width="12%" style="text-align: center">学位</th>
				<th width="12%" style="text-align: center">学位附件</th>
			</tr>
			<c:forEach items="${educationInfoList}" var="educationInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="text-align: center">${status.index + 1}</td>
					<td style="text-align: center">${educationInfo.graduationSchool}</td>
					<td style="text-align: center">${educationInfo.graduationTime}</td>
					<td style="text-align: center">${educationInfo.graduationSpe}</td>
					<td style="text-align: center">${educationInfo.academicBackgroundName}</td>
					<td style="text-align: center">
					<span style="display:${!empty educationInfo.academicBackgroundFile?'':'none'} ">
                        <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicBackgroundFile}"
						   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;"><font color="#add8e6">查看</font></a>
                    </span>
					</td>
					<td style="text-align: center">${educationInfo.academicDegreeName}</td>
					<td style="text-align: center">
					<span style="display:${!empty educationInfo.academicDegreeFile?'':'none'} ">
                        <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicDegreeFile}"
						   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;"><font color="#add8e6">查看</font></a>
                    </span>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty educationInfoList}">
				<tr>
					<td style="text-align: center" colspan="8">无记录</td>
				</tr>
			</c:if>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;职称信息</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info " style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">师资级别</th>
				<td style="text-align: center">${user.teacherLevel}</td>
				<th style="background-color: #f4f5f9; text-align: center">职称</th>
				<td style="text-align: center">${professionalInfo.professionalTitleName}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">专业技术职务</th>
				<td style="text-align: center">${professionalInfo.technicalPositionName}</td>
				<th style="background-color: #f4f5f9; text-align: center">专业技术职务任职时间</th>
				<td style="text-align: center">${professionalInfo.technicalPositionTime}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">从事临床教学工作时间</th>
				<td style="text-align: center">${professionalInfo.clinicalTeachingTime}</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;师资培训记录</h5>
		<table class="base_info" style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="10%" style="text-align: center">培训年份</th>
				<th width="20%" style="text-align: center">培训单位</th>
				<th width="16%" style="text-align: center">培训专业</th>
				<th width="12%" style="text-align: center">证书等级</th>
				<th width="12%" style="text-align: center">证书编号</th>
				<th width="12%" style="text-align: center">证书取得时间</th>
				<th width="12%" style="text-align: center">证书附件</th>
			</tr>
			<c:forEach items="${trainingInfoList}" var="trainingInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="text-align: center">${status.index + 1}</td>
					<td style="text-align: center">${trainingInfo.trainingYear}</td>
					<td style="text-align: center">${trainingInfo.trainingUnit}</td>
					<td style="text-align: center">${trainingInfo.trainingSpeName}</td>
					<td style="text-align: center">${trainingInfo.certificateLevelName}</td>
					<td style="text-align: center">${trainingInfo.certificateNo}</td>
					<td style="text-align: center">${trainingInfo.certificateTime}</td>
					<td style="text-align: center">
					<span style="display:${!empty trainingInfo.certificateFile?'':'none'} ">
                        <a href="${sysCfgMap['upload_base_url']}/${trainingInfo.certificateFile}"
						   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;"><font color="#add8e6">查看</font></a>
                    </span>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty trainingInfoList}">
				<tr>
					<td style="text-align: center" colspan="8">无记录</td>
				</tr>
			</c:if>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;师资聘书记录</h5>
		<table class="base_info" style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="22%" style="text-align: center">聘书有效期</th>
				<th width="18%" style="text-align: center">开始时间</th>
				<th width="18%" style="text-align: center">结束时间</th>
				<th width="18%" style="text-align: center">聘书取得时间</th>
				<th width="18%" style="text-align: center">证书附件</th>
			</tr>
			<c:forEach items="${letterInfoList}" var="letterInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="text-align: center">${status.index + 1}</td>
					<td style="text-align: center">${letterInfo.letterPeriod}</td>
					<td style="text-align: center">${letterInfo.letterStartTime}</td>
					<td style="text-align: center">${letterInfo.letterEndTime}</td>
					<td style="text-align: center">${letterInfo.letterTime}</td>
					<td style="text-align: center">
					<span style="display:${!empty letterInfo.letterFile?'':'none'} ">
                        <a href="${sysCfgMap['upload_base_url']}/${letterInfo.letterFile}"
						   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;"><font color="#add8e6">查看</font></a>
                    </span>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty letterInfoList}">
				<tr>
					<td style="text-align: center" colspan="6">无记录</td>
				</tr>
			</c:if>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;审核备注</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info " style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<td style="text-align: left" colspan="4">
					<input style="width: 98%" value="" placeholder="请输入审核备注（若审核不通过，则必须填写）" name="applicationAuditMessage" id="applicationAuditMessage" />
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div style="text-align: center; margin-top: 10px;">
		<c:if test="${roleFlag eq 'head'}">
			<input type="button" onclick="saveAuditCommonSzInfo('HeadPassed', '${teacherTraining.recordFlow}');" class="btn_green" value="审核通过"/>
			<input type="button" onclick="saveAuditCommonSzInfo('HeadNotPassed', '${teacherTraining.recordFlow}');" class="btn_green" value="审核不通过"/>
		</c:if>
		<c:if test="${roleFlag eq 'local'}">
			<input type="button" onclick="saveAuditCommonSzInfo('Passed', '${teacherTraining.recordFlow}');" class="btn_green" value="审核通过"/>
			<input type="button" onclick="saveAuditCommonSzInfo('NotPassed', '${teacherTraining.recordFlow}');" class="btn_green" value="审核不通过"/>
		</c:if>
		<input type="button" class="btn_green" value="取&#12288;消" onclick="jboxClose();"/>
	</div>
</div>


