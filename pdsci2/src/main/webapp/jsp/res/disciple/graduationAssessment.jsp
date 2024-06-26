<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
	.filesTable{
		border: 1px solid #e7e7eb;
		border-collapse: collapse;
		color: #686868;
		border-spacing: 0 0;
		width: 100%;
		text-align: center;
	}
	.filesTable td{
		text-align: center;
		padding-left: 0px;
	}
</style>
<script type="text/javascript">
	$(function(){

	});
	function showext(){
		var src="<s:url value="/res/graduation/main/student"></s:url>";
		window.location.href= src;
	}
	function refresh()
	{
		window.location.reload(true);
	}
	function downImg(recordFlow)
	{
		var url='<s:url value="/res/disciple/downImg?recordFlow="/>'+recordFlow;
		window.location.href=url;
	}
	function downFile(fileFlow)
	{
		var url='<s:url value="/res/disciple/down?fileFlow="/>'+fileFlow;
		window.location.href=url;
	}
	function uploadImage(){
		$.ajaxFileUpload({
			url:"<s:url value='/res/graduation/docScordImg'/>?recordFlow=${ext.recordFlow}",
			secureuri:false,
			fileElementId:'imageFile',
			dataType: 'json',
			success: function (data, status){
				if(data.indexOf("success")==-1){
					jboxTip(data);
				}else{
					jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
					refresh();
				}
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
				$("#imageFile").val("");
			}
		});
	}
	function save(statusId)
	{
		var jsonData={};
		var fileFlows=[];
		$("input[name='fileFlows']").each(function(){
			var fileFlow=$(this).val();
			if(fileFlow)
				fileFlows.push(fileFlow);
		});
		jsonData.fileFlows=fileFlows;
		if("${role}"=="student")
		{
			var tent=$("#experienceContent").val();
			if(!tent){
				jboxTip("自我鉴定未填写，无法保存！");
				return false;
			}
		}
		if("${role}"=="teacher")
		{
			var tent=$("#auditContent").val();
			if(!tent){
				jboxTip("审核意见未填写，无法保存！");
				return false;
			}
		}
		if("${role}"=="admin")
		{
//			var assessmentImgUrl=$("#assessmentImgUrl").val();
//			if(!assessmentImgUrl){
//				jboxTip("请上传学员结业考核打分表，否则无法保存！");
//				return false;
//			}
			var tent=$("#adminContent").val();
			if(!tent){
				jboxTip("审核意见未填写，无法保存！");
				return false;
			}
		}
		$("#statusId").val(statusId);
		var msg = "确认保存？";
		if('studentSub' == statusId){
			msg = "确认提交？";
		}
		jboxConfirm(msg,function(){
			var url = "<s:url value='/res/graduation/save/${role}'/>" + "?jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
			jboxSubmit($('#saveForm'), url, function (resp) {
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
					if("${role}"=="student")
					{
						showext();
					}
					if("${role}"=="teacher")
					{
						window.parent.frames['mainIframe'].window.search();
					}
					if("${role}"=="admin")
					{
						window.parent.frames['mainIframe'].window.search();
					}
				}
			}, null, true);
			<%--jboxPost(url,$('#saveForm').serialize(),function(resp){--%>
				<%--if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {--%>
					<%--if("${role}"=="student")--%>
					<%--{--%>
						<%--showext();--%>
					<%--}--%>
					<%--if("${role}"=="teacher")--%>
					<%--{--%>
						<%--window.parent.frames['mainIframe'].window.search();--%>
					<%--}--%>
					<%--if("${role}"=="admin")--%>
					<%--{--%>
						<%--window.parent.frames['mainIframe'].window.search();--%>
					<%--}--%>
				<%--}--%>
			<%--},null,true);--%>
		});

	}
	function addFile(){
		$('#filesTable').append($("#fileTableFormat tr:eq(0)").clone());
	}
	function moveTr(obj){
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
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
			<%--管理员是否审核通过--%>
			<c:set var="isShowSelect" value="${ext.auditStatusId eq discipleStatusEnumAdminAudit.id}"></c:set>
			<%--是否可以提交--%>
			<c:set var="canSave" value="${ext.auditStatusId != discipleStatusEnumAdminAudit.id and ext.auditStatusId !=discipleStatusEnumDiscipleAudit.id
			and ext.auditStatusId !=discipleStatusEnumSubmit.id	}"></c:set>
            <c:set var="canSaveAppendix" value="${ext.auditStatusId eq discipleStatusEnumAdminAudit.id }"></c:set>
			<form id="saveForm" method="post" enctype="multipart/form-data">
			<table class="basic" width="100%">
				<input type="text" class=" inputText" value="${ext.recordFlow}" name="recordFlow" hidden style="margin-left: 20px;">
				<input type="text" class=" inputText" id="statusId" name="statusId" hidden style="margin-left: 20px;">

				<tr style="height: 54px">
					<td style="text-align: right; width:25%"><span style="font-size: 16px;margin-right: 34px">规培学员姓名</span></td>
					<td style="text-align: left;width:25%">
						<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.doctorName}</lable>
						<input type="text" class=" inputText" value="${ext.doctorName}" name="doctorName" hidden style="margin-left: 20px;">
						<input type="text" class=" inputText" value="${ext.doctorFlow}" name="doctorFlow" hidden style="margin-left: 20px;">
					</td>
					<td style="text-align: right; width:25%"><span style="font-size: 16px;margin-right: 34px">师承指导老师姓名：</span></td>
					<td style="text-align: left;width:25%">
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.discipleTeacherName}</lable>
							<input type="text" class=" inputText" value="${ext.discipleTeacherName}" name="teacherName" hidden style="margin-left: 20px;">
							<input type="text" class=" inputText" value="${ext.discipleTeacherFlow}" name="teacherFlow" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.teacherName}</lable>
							<input type="text" class=" inputText" value="${ext.teacherName}" name="teacherName" hidden style="margin-left: 20px;">
							<input type="text" class=" inputText" value="${ext.teacherFlow}" name="teacherFlow" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师时间</span></td>
					<td style="text-align: left;" colspan="3">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
								自${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.studyStartDate,'yyyy-MM-dd','dd')}日
								至${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.studyEndDate,'yyyy-MM-dd','dd')}日
							</lable>
							<input type="text" class=" inputText" value="${ext.studyStartDate}" name="studyStartDate" hidden style="margin-left: 20px;">
							<input type="text" class=" inputText" value="${ext.studyEndDate}" name="studyEndDate" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
								自${pdfn:transDateTimeForPattern(ext.discipleStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.discipleStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.discipleStartDate,'yyyy-MM-dd','dd')}日
								至${pdfn:transDateTimeForPattern(ext.discipleEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(ext.discipleEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(ext.discipleEndDate,'yyyy-MM-dd','dd')}日
							</lable>
							<input type="text" class=" inputText" value="${ext.discipleStartDate}" name="studyStartDate" hidden style="margin-left: 20px;">
							<input type="text" class=" inputText" value="${ext.discipleEndDate}" name="studyEndDate" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师天数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.dayCount}</lable>
							<input type="text" class=" inputText" value="${ext.dayCount}" name="dayCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.daySelectCount}</lable>
							<input type="text" class=" inputText" value="${ext.daySelectCount}" name="dayCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师笔记份数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.noteCount}</lable>
							<input type="text" class=" inputText" value="${ext.noteCount}" name="noteCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.noteSelectCount}</lable>
							<input type="text" class=" inputText" value="${ext.noteSelectCount}" name="noteCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师心得份数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.experienceCount}</lable>
							<input type="text" class=" inputText" value="${ext.experienceCount}" name="experienceCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.experienceSelectCount}</lable>
							<input type="text" class=" inputText" value="${ext.experienceSelectCount}" name="experienceCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">学习中医经典部数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.tcmCount}</lable>
							<input type="text" class=" inputText" value="${ext.tcmCount}" name="tcmCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.bookStudyCount}</lable>
							<input type="text" class=" inputText" value="${ext.bookStudyCount}" name="tcmCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">经典学习体会份数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.xxthCount}</lable>
							<input type="text" class=" inputText" value="${ext.xxthCount}" name="xxthCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.bookexpSelectCount}</lable>
							<input type="text" class=" inputText" value="${ext.bookexpSelectCount}" name="xxthCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">撰写跟师医案份数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.typicalCasesCount}</lable>
							<input type="text" class=" inputText" value="${ext.typicalCasesCount}" name="typicalCasesCount" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.casesCount}</lable>
							<input type="text" class=" inputText" value="${ext.casesCount}" name="typicalCasesCount" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">年度考核合格次数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.annualHege}</lable>
							<input type="text" class=" inputText" value="${ext.annualHege}" name="annualHege" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.yearHegeCount}</lable>
							<input type="text" class=" inputText" value="${ext.yearHegeCount}" name="annualHege" hidden style="margin-left: 20px;">
						</c:if>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">年度考核不合格次数</span></td>
					<td style="text-align: left;">
						<c:if test="${isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.annualNotHege}</lable>
							<input type="text" class=" inputText" value="${ext.annualNotHege}" name="annualNotHege" hidden style="margin-left: 20px;">
						</c:if>
						<c:if test="${!isShowSelect}">
							<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">${ext.yearBuhegeCount}</lable>
							<input type="text" class=" inputText" value="${ext.yearBuhegeCount}" name="annualNotHege" hidden style="margin-left: 20px;">
						</c:if>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">结业论文题目</span></td>
					<td style="text-align: left;" colspan="3">
						<c:if test="${isShowSelect}">
							<c:if test="${empty ext.graduationThesisTitle}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">尚未上传结业论文</lable>
							</c:if>
							<c:if test="${not empty ext.graduationThesisTitle}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText"><a href="javascript:void()" onclick="downFile('${ext.fileFlow}')">${ext.graduationThesisTitle}</a></lable>
								<input type="text" class=" inputText" value="${ext.graduationThesisTitle}" name="graduationThesisTitle" hidden style="margin-left: 20px;">
							</c:if>
						</c:if>
						<c:if test="${!isShowSelect}">
							<c:if test="${empty ext.fileName}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">尚未上传结业论文</lable>
							</c:if>
							<c:if test="${not empty ext.fileName}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText"><a href="javascript:void()" onclick="downFile('${ext.fileFlow}')">${ext.fileName}</a></lable>
								<input type="text" class=" inputText" value="${ext.fileName}" name="graduationThesisTitle" hidden style="margin-left: 20px;">
							</c:if>
						</c:if>

					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">跟师学习考核打分表</span></td>
					<td style="text-align: left;" colspan="3">
						<c:if test="${not empty ext.assessmentImgUrl}">

							<c:if test="${role =='admin'}">
								<a style="font-size: 16px;margin-left: 20px;" onclick="$('#imageFile').click();">上传</a>
								<input type="file" id="imageFile" accept="${sysCfgMap['inx_image_support_mime']}"  name="graduationFile" style="display: none" onchange="uploadImage();"/>
								<a style="font-size: 16px;margin-left: 20px;" href="<s:url value='/jsp/res/disciple/admin/scord.docx'/>">下载</a>
							</c:if>
							<a style="font-size: 16px;margin-left: 20px;" href="${sysCfgMap['upload_base_url']}/${ext.assessmentImgUrl }" target="_blank">查看</a>
							<c:if test="${role !='admin'}">
								<a style="font-size: 16px;margin-left: 20px;" onclick="downImg('${ext.recordFlow}')" >下载</a>
							</c:if>
						</c:if>
						<c:if test="${empty ext.assessmentImgUrl}">
							<c:if test="${role =='admin'and !isShowSelect}">
								<a style="font-size: 16px;margin-left: 20px;" onclick="$('#imageFile').click();">上传</a>
								<input type="file" id="imageFile" accept="${sysCfgMap['inx_image_support_mime']}"  name="graduationFile" style="display: none" onchange="uploadImage();"/>
								<a style="font-size: 16px;margin-left: 20px;" href="<s:url value='/jsp/res/disciple/admin/scord.docx'/>">下载</a>
							</c:if>
							<c:if test="${role !='admin'}">
								<lable style="font-size: 16px;margin-left: 20px;height: 24px;width: 250px;" class=" inputText">尚未打分，暂时无法查看</lable>
							</c:if>
						</c:if>
						<input type="text" class=" inputText" value="${ext.assessmentImgUrl}" id="assessmentImgUrl" name="assessmentImgUrl" hidden style="margin-left: 20px;">
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 100%; " colspan="4">
						<textarea  style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="experienceContent" id="experienceContent"
								  <c:if test="${isShowSelect or (role !='student')}">readonly</c:if>
								  <c:if test="${!(isShowSelect or (role !='student'))}">class="validate[required]"</c:if>
								  placeholder="自我鉴定（简述执行跟师学习计划情况，三年来师承学习所取得的成绩和存在的问题等）：">${ext.experienceContent}</textarea>
						<div style="font-size: 16px;width: 90%;text-align: right;margin-right:10%">
							<c:if test="${role !='student'}">
								<p>跟师规培学员签名：<input class="inputText" type="text" readonly  value="${ext.doctorName}"></p>
								<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','yyyy')}">年
								<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','MM')}">月
								<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','dd')}">日</p>
								<input hidden name="experienceSignTime" value="${ext.experienceSignTime}">
								<table style="max-width: 395px;float: left;margin-left: 11%;margin-bottom: 20px;margin-top: -50px;" class="filesTable" id="filesTable">
									<tr>
										<td style="text-align: left;padding-left: 10px;">
											附件名
										</td>
									</tr>
									<c:forEach items="${discipleFiles}" var="discipleFile">
										<tr>
											<td style="text-align: left;padding-left: 10px;">
												<a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<c:if test="${role eq 'student'}">
								<c:if test="${!canSaveAppendix and (isShowSelect or !canSave)}">
									<p>跟师规培学员签名：<input class="inputText" type="text" readonly  value="${ext.doctorName}"></p>
									<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','yyyy')}">年
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','MM')}">月
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.experienceSignTime,'yyyy-MM-dd','dd')}">日</p>
									<input hidden name="experienceSignTime" value="${ext.experienceSignTime}">
									<table style="max-width: 395px;float: left;margin-left: 11%;margin-bottom: 20px;margin-top: -50px;" class="filesTable" id="filesTable">
										<tr>
											<td style="text-align: left;padding-left: 10px;">
												附件名
											</td>
										</tr>
										<c:forEach items="${discipleFiles}" var="discipleFile">
											<tr>
												<td style="text-align: left;padding-left: 10px;">
													<a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
												</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${canSave or canSaveAppendix}">
									<p>跟师规培学员签名：<input class="inputText" type="text" readonly  value="${ext.doctorName}"></p>
									<p><input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("yyyy")}">年
									<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("MM")}">月
									<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("dd")}">日</p>
									<input hidden name="experienceSignTime" value="${pdfn:getCurrDate()}">
									<table style="max-width: 395px;float: left;margin-left: 11%;margin-bottom: 20px;margin-top: -50px;" class="filesTable" id="filesTable">
										<tr>
											<td style="text-align: left;padding-left: 10px;">
												附件名
												<span>
													<font color="#999" title="${applicationScope.sysCfgMap["inx_image_support_suffix"]}">&#12288;&#12288;仅支持图片格式 </font>
													&#12288;
													<img class="opBtn" title="新增"
														 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
														 style="cursor: pointer;" onclick="addFile();"/>
												</span>
											</td>
											<td width="75px">操作</td>
										</tr>
										<c:forEach items="${discipleFiles}" var="discipleFile">
											<tr>
												<td style="text-align: left;padding-left: 10px;">
													<input hidden name="fileFlows" value="${discipleFile.fileFlow}">
													<a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
												</td>
												<td width="75px">
													<img class='opBtn' title='删除' style='cursor: pointer;'
														 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
												</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 100%; " colspan="4">
					<textarea  style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="auditContent" id="auditContent"
							  <c:if test="${isShowSelect or role !='teacher'}">readonly</c:if>
							  <c:if test="${!(isShowSelect or (role !='teacher'))}">class="validate[required]"</c:if>
							  placeholder="指导老师意见（对跟师规培学员进行总体评价，重点评价跟师学习态度、主要成绩、结业论文质量，并明确是否同意跟师规培学员参加结业考核）：">${ext.auditContent}</textarea>
						<div style="font-size: 16px;width: 90%;text-align: right;margin-right:10%">
							<c:if test="${role !='teacher'}">
								<p>指导老师签名：<c:set var="img" value="${pdfn:getSingnPhoto(ext.auditUserFlow)}"></c:set>
								<c:if test="${not empty img}">
									${img}
								</c:if>
								<c:if test="${empty img}">
									<input class="inputText" type="text" readonly/>
								</c:if>
								</p>
								<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','yyyy')}">年
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','MM')}">月
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','dd')}">日</p>
								<input hidden name="auditTime" value="${ext.auditTime}">
							</c:if>
							<c:if test="${role eq 'teacher'}">
								<c:if test="${isShowSelect}">
									<p>指导老师签名：${pdfn:getSingnPhoto(ext.auditUserFlow)}</p>
									<p><input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','yyyy')}">年
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','MM')}">月
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.auditTime,'yyyy-MM-dd','dd')}">日</p>
									<input hidden name="auditTime" value="${ext.auditTime}">
								</c:if>
								<c:if test="${!isShowSelect}">
									<p>指导老师签名：${pdfn:getSingnPhoto(sessionScope.currUser.userFlow)}</p>
									<p><input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("yyyy")}">年
										<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("MM")}">月
										<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("dd")}">日</p>
									<input hidden name="auditTime" value="${pdfn:getCurrDate()}">
								</c:if>
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 100%; " colspan="4">
					<textarea class="" style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="adminContent" id="adminContent"
							  <c:if test="${isShowSelect or role !='admin'}">readonly</c:if>
							  <c:if test="${!(isShowSelect or (role !='admin'))}">class="validate[required]"</c:if>
							  placeholder="单位考核意见：（包括：对跟师规培学员职业道德、工作态度、劳动纪律、跟师效果等进行综合评价，并按照跟师学习考核打分表对结业考核是否合格提出明确意见。）">${ext.adminContent}</textarea>
						<div style="font-size: 16px;width: 80%;text-align: right;margin-left: 10%">
							<c:if test="${role !='admin'}">
								<font style="margin-right: 10px;">负责人（签名）：<c:set var="img" value="${pdfn:getSingnPhoto(ext.adminUserFlow)}"></c:set>
									<c:if test="${not empty img}">
										${img}
									</c:if>
									<c:if test="${empty img}">
										<input class="inputText" type="text" readonly/>
									</c:if>
								</font>
							</c:if>
							<c:if test="${role eq 'admin'}">
								<c:if test="${isShowSelect}">
									<font style="margin-right: 10px;">负责人（签名）：${pdfn:getSingnPhoto(ext.adminUserFlow)}</font>
								</c:if>
								<c:if test="${!isShowSelect}">
									<font style="margin-right: 10px;">负责人（签名）：${pdfn:getSingnPhoto(sessionScope.currUser.userFlow)}</font>
								</c:if>
							</c:if>
						</div>
						<div style="font-size: 16px;width: 80%;text-align: right;margin-left: 10%">
							<c:if test="${role !='admin'}">
								<font style="">
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','yyyy')}">年
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','MM')}">月
									<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','dd')}">日（公章）
								</font>
								<input hidden name="adminTime" value="${ext.adminTime}">
							</c:if>
							<c:if test="${role eq 'admin'}">
								<c:if test="${isShowSelect}">
									<font style="width:20%;">
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','yyyy')}">年
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','MM')}">月
										<input class="inputTime" type="text" readonly  value="${pdfn:transDateTimeForPattern(ext.adminTime,'yyyy-MM-dd','dd')}">日（公章）</font>
									<input hidden name="adminTime" value="${ext.adminTime}">
								</c:if>
								<c:if test="${!isShowSelect}">
									<font style="width:20%;">
										<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("yyyy")}">年
										<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("MM")}">月
										<input class="inputTime" type="text" readonly  value="${pdfn:getCurrDateTime("dd")}">日（公章）</font>
									<input hidden name="adminTime" value="${pdfn:getCurrDate()}">
								</c:if>
							</c:if>
						</div>
					</td>
				</tr>

				<tr style="height: 54px">
					<td colspan="6" style="text-align: center;">
						<c:if test="${(role =='student') and canSave}">
							<input type="button"  value="保&#12288;存" class="search" onclick="save('studentSave');" />&#12288;
							<input type="button"  value="提&#12288;交" class="search" onclick="save('studentSub');" />
							（提交后指导老师才可审核）
						</c:if>
                        <c:if test="${(role =='student') and canSaveAppendix}">
                            <input type="button"  value="保&#12288;存" class="search" onclick="save('studentSave');" />&#12288;
                        </c:if>
						<c:if test="${(role =='teacher') and !isShowSelect}">
							<input type="button"  value="审核通过" class="search" style=""  onclick="save('tearcherAudit');" />
							<input type="button"  value="审核不通过" class="search" style=""  onclick="save('tearcherBack');" />
						</c:if>
						<c:if test="${(role =='admin') and !isShowSelect}">
							<input type="button"  value="审核通过" class="search" style=""  onclick="save('adminAudit');" />
							<input type="button"  value="审核不通过" class="search" style=""  onclick="save('adminBack');" />
						</c:if>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</div>
</div>
<%--附件模板--%>
<table class="filesTable" id="fileTableFormat" style="display: none" style="width: 100%">
	<tr>
		<td style="text-align: left;padding-left: 10px;">
			<input type='file' name='files' class='validate[required]' multiple='multiple' style="max-width: 200px;"
				   accept="${applicationScope.sysCfgMap["inx_image_support_suffix"]}"/>
		</td>
		<td>
			<img class='opBtn' title='删除' style='cursor: pointer;'
				 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
		</td>
	</tr>
</table>
</body>
</html>