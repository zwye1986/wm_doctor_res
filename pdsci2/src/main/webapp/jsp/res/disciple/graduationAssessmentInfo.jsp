<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	String.prototype.htmlFormartById = function(data){
		var html = this;
		for(var key in data){
			var reg = new RegExp('\\{'+key+'\\}','g');
			html = html.replace(reg,data[key]);
		}
		return html;
	};
</script>
<style type="text/css">
	.inputTime {
		-moz-border-bottom-colors: none;
		-moz-border-left-colors: none;
		-moz-border-right-colors: none;
		-moz-border-top-colors: none;
		border-color: -moz-use-text-color -moz-use-text-color #ccc;
		border-image: none;
		border-style: none none solid;
		border-width: 0 0 1px;
		text-align: center;
		width: 60px;
	}
	.table {
		border: 1px solid #e3e3e3;
	}
	.table tr:nth-child(2n) {
		background-color: #fcfcfc;
		transition: all 0.125s ease-in-out 0s;
	}
	.table tr:hover {
		background: #fbf8e9 none repeat scroll 0 0;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
	}
</style>
<script type="text/javascript">
	function downFile(fileFlow)
	{
		var url='<s:url value="/res/disciple/down?fileFlow="/>'+fileFlow;
		window.location.href=url;
	}
	function downImg(recordFlow)
	{
		var url='<s:url value="/res/disciple/downImg?recordFlow="/>'+recordFlow;
		window.location.href=url;
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="100%" height="100%" style="margin-top: 20px">
			<table class="table" width="100%">
				<tr style="height: 58px">
					<th style="text-align: center; "><span style="font-size: 25px;">跟师学习结业考核情况记录表</span></th>
				</tr>
			</table>
			<br>
			<table class="table" width="100%">
				<form id="saveForm">
					<input type="text" class=" inputText" value="${ext.recordFlow}" name="recordFlow" hidden style="margin-left: 20px;">
					<input type="text" class=" inputText" id="statusId" name="statusId" hidden style="margin-left: 20px;">
				<tr style="height: 54px">
					<td style="text-align: right; width:25%"><span style="font-size: 16px;margin-right: 34px">规培学员姓名：</span></td>
					<td style="text-align: left;width:25%">
						<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.doctorName}</lable>
						<input type="text" class=" inputText" value="${ext.doctorName}" name="doctorName" hidden style="margin-left: 20px;">
						<input type="text" class=" inputText" value="${ext.doctorFlow}" name="doctorFlow" hidden style="margin-left: 20px;">
					</td>
					<td style="text-align: right; width:25%"><span style="font-size: 16px;margin-right: 34px">师承指导老师姓名：</span></td>
					<td style="text-align: left;width:25%">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.teacherName}</lable>
							<input type="text" class=" inputText" value="${ext.teacherName}" name="teacherName" hidden style="margin-left: 20px;">
							<input type="text" class=" inputText" value="${ext.teacherFlow}" name="teacherFlow" hidden style="margin-left: 20px;">
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师时间：</span></td>
					<td style="text-align: left;" colspan="3">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
								自${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','dd')}日
								至${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','dd')}日
							</lable>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师天数：</span></td>
					<td style="text-align: left;">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.dayCount}</lable>
							<input type="text" class=" inputText" value="${ext.dayCount}" name="dayCount" hidden style="margin-left: 20px;">
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师笔记份数：</span></td>
					<td style="text-align: left;">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.noteCount}</lable>
							<input type="text" class=" inputText" value="${ext.noteCount}" name="noteCount" hidden style="margin-left: 20px;">
					</td>
				</tr>
					<tr style="height: 54px">
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师心得份数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.experienceCount}</lable>
								<input type="text" class=" inputText" value="${ext.experienceCount}" name="experienceCount" hidden style="margin-left: 20px;">
						</td>
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">学习中医经典部数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.tcmCount}</lable>
								<input type="text" class=" inputText" value="${ext.tcmCount}" name="tcmCount" hidden style="margin-left: 20px;">
						</td>
					</tr>
					<tr style="height: 54px">
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">经典学习体会份数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.xxthCount}</lable>
								<input type="text" class=" inputText" value="${ext.xxthCount}" name="xxthCount" hidden style="margin-left: 20px;">
						</td>
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师医案份数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.typicalCasesCount}</lable>
								<input type="text" class=" inputText" value="${ext.typicalCasesCount}" name="typicalCasesCount" hidden style="margin-left: 20px;">
						</td>
					</tr>
					<tr style="height: 54px">
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">年度考核合格次数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.annualHege}</lable>
								<input type="text" class=" inputText" value="${ext.annualHege}" name="annualHege" hidden style="margin-left: 20px;">
						</td>
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">年度考核不合格次数：</span></td>
						<td style="text-align: left;">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.annualNotHege}</lable>
								<input type="text" class=" inputText" value="${ext.annualNotHege}" name="annualNotHege" hidden style="margin-left: 20px;">
						</td>
					</tr>
					<tr style="height: 54px">
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">结业论文题目：</span></td>
						<td style="text-align: left;" colspan="3">
							<c:if test="${empty ext.graduationThesisTitle}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">尚未上传结业论文</lable>
							</c:if>
							<c:if test="${not empty ext.graduationThesisTitle}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText"><a href="javascript:void()" onclick="downFile('${ext.fileFlow}')">${ext.graduationThesisTitle}</a></lable>
								<input type="text" class=" inputText" value="${ext.graduationThesisTitle}" name="graduationThesisTitle" hidden style="margin-left: 20px;">
							</c:if>
						</td>
					</tr>
					<tr style="height: 54px">
						<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师学习考核打分表：</span></td>
						<td style="text-align: left;" colspan="3">
							<c:if test="${not empty ext.assessmentImgUrl}">
								<a style="font-size: 16px;margin-left: 20px;" href="${sysCfgMap['upload_base_url']}/${ext.assessmentImgUrl }" target="_blank">查看</a>
								&#12288;&#12288;
								<a style="font-size: 16px;margin-left: 20px;" onclick="downImg('${ext.recordFlow}')" >下载</a>
							</c:if>
							<c:if test="${empty ext.assessmentImgUrl}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">尚未打分，暂时无法查看</lable>
							</c:if>
							<input type="text" class=" inputText" value="${ext.assessmentImgUrl}" id="assessmentImgUrl" name="assessmentImgUrl" hidden style="margin-left: 20px;">
						</td>
					</tr>
				<tr>
					<td style="text-align: center;width: 100%; " colspan="4">
						<textarea  style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="experienceContent" id="experienceContent"
								 readonly
								  placeholder="自我鉴定（简述执行跟师学习计划情况，三年来师承学习所取得的成绩和存在的问题等）：">${ext.experienceContent}</textarea>
						<div style="font-size: 16px;width: 90%;text-align: right;margin-right:10%">
								<p>跟师规培学员签名：<input class="inputText" type="text" readonly  value="${ext.doctorName}"></p>
								<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','yyyy')}">年
								<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','MM')}">月
								<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','dd')}">日</p>
								<input hidden name="experienceSignTime" value="${ext.experienceSignTime}">
						</div>
					</td>
				</tr>
					<tr>
						<td style="text-align: center;width: 100%; " colspan="4">
						<textarea  style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="auditContent" id="auditContent" readonly
								  placeholder="指导老师意见（对跟师规培学员进行总体评价，重点评价跟师学习态度、主要成绩、结业论文质量，并明确是否是同意跟师规培学员参加结业考核）：">${ext.auditContent}</textarea>
							<div style="font-size: 16px;width: 90%;text-align: right;margin-right:10%">
									<p>指导老师签名：${pdfn:getSingnPhoto(ext.auditUserFlow)}</p>
									<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','yyyy')}">年
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','MM')}">月
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','dd')}">日</p>
									<input hidden name="auditTime" value="${ext.auditTime}">
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;width: 100%; " colspan="4">
						<textarea class="" style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="adminContent" id="adminContent" readonly
								  placeholder="单位考核意见：（包括：对跟师规培学员职业道德、工作态度、劳动纪律、跟师效果等进行综合评价，并按照跟师学习考核打分表对结业考核是否提出明确意见。）">${ext.adminContent}</textarea>
							<div style="font-size: 16px;width: 80%;text-align: left;margin-left: 10%">
									<font style="">负责人（签名）：${pdfn:getSingnPhoto(ext.adminUserFlow)}</font>
							</div>
							<div style="font-size: 16px;width: 80%;text-align: right;margin-left: 10%">
								<font style="">
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','yyyy')}">年
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','MM')}">月
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','dd')}">日（公章）
								</font>
								<input hidden name="adminTime" value="${ext.adminTime}">
							</div>
						</td>
					</tr>
				</form>
				<tr style="height: 54px">
					<td colspan="6">
						<input type="button"  value="关&#12288;闭" class="search" style="width: 70px"  onclick="jboxClose();" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>