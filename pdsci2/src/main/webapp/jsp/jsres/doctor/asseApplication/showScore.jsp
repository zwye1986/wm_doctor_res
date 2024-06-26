<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<body style="overflow: auto;">
<div class="main_hd">

	<div id="div1" class="div_table" style="width: 90%;">
		<h4 >公共科目成绩</h4>
		<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
			<tbody>
			<c:if test="${isAssiGeneral eq 'N'}">
				<tr>
					<td style="text-align: center;">卫生法律和法规</td>
					<td style="text-align: center;">循证医学</td>
					<td style="text-align: center;">临床思维与人际沟通</td>
					<td style="text-align: center;">重点传染病防治知识</td>
					<td style="text-align: center;">是否合格</td>
				</tr>
				<tr>
					<td style=" text-align: center;">
							${extScore.lawScore}
					</td>
					<td style=" text-align: center;">
							${extScore.medicineScore}
					</td>
					<td style=" text-align: center;">
							${extScore.clinicalScore}
					</td>
					<td style=" text-align: center;">
							${extScore.ckScore}
					</td>
					<td style=" text-align: center;">
						<c:if test="${score.skillScore eq GlobalConstant.PASS}">合格</c:if>
						<c:if test="${score.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>
					</td>
				</tr>
			</c:if>
			<c:if test="${isAssiGeneral eq 'Y'}">
				<tr>
					<th style="text-align: center;">全科医学及相关理论知识考核成绩</th>
					<th style="text-align: center;">是否合格</th>
				</tr>
				<tr>
					<td style="text-align: center;" >
							${score.theoryScore}
					</td>
					<td style=" text-align: center;">
						<c:if test="${score.skillScore eq GlobalConstant.PASS}">合格</c:if>
						<c:if test="${score.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>
					</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<h4>附件材料</h4>
		<div>
			<c:if test="${not empty file.filePath}">
				<a id="imgUrlA2" href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">
					<img src="${sysCfgMap['upload_base_url']}/${file.filePath}" width="700" height="300">
				</a>
			</c:if>
			<c:if test="${ empty file.filePath}">
				暂未上传
			</c:if>
		</div>
	</div>
</div>
</body>
</html>