<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/font.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="true"/>
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
	<style type="text/css">
		.xuanze {
			background-color: #D0E3F2;
		}

		.noteItems {
			border-bottom: 1px solid #D0E3F2;
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
		function editTypicalCase() {
			$("#casesRecordFlow").val("");
			var myDate = new Date();
			$("#notice").hide();
			$("#operateBtn").html($("#operate").html());
			$("#operateBtn").show();
			$("#editTip").hide();
			$("#typicalCaseForm").empty();
			$("#typicalCaseForm").html($("#addTypicalCases").html());
			$("td input[type='text']").removeAttr("readOnly");
			$("#birthDate").attr("readOnly",true);
//			$("td input[type='text']").attr("style","width: 100%; height: 100%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom: 1px solid #58ACFA;");
			$("td input[type='text']").attr("style","width: 100%; height: 100%;border:0px");
		}
		$(function () {
			if(${'Apply' eq seeFlag or 'UnQualified' eq seeFlag}){
//				$("td input[type='text']").attr("style","width: 100%; height: 100%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom: 1px solid #58ACFA;");
				$("td input[type='text']").attr("style","width: 100%; height: 100%;border:0px");
			}else{
				$("td input[type='text']").attr("style","width: 100%; height: 100%;border:0px");
			}
//			border-style: solid; border-width: 0
//			$("td input[type='text']").addClass("inputText");
//			$("td input[type='text']").attr("text-align","left");
			if(${'PendingAudit' eq seeFlag||'Qualified' eq seeFlag}){
				$("td input[type='text']").attr("readOnly","readOnly");
			}
			var roleId=$("#roleId").val();
			if("teacher"==roleId){
				$("#addCase").hide();
				$("#submitCase").hide();
			}else{
				$("#shenhe").hide();
				$("#shenheBack").hide();
			}
			if("see"==roleId||"adminSee"==roleId){
				$("#addCase").hide();
				$("#submitCase").hide();
//				$("#resubmitCase").hide();
				$("#shenhe").hide();
				$("#shenheBack").hide();
				$("td input[type='text']").attr("readOnly","readOnly");
//				$("#back").hide();
			}
			//鼠标的移入移出
			$(".noteItems").mouseover(function () {
				$(this).addClass("xuanze");
			}).mouseout(function () {
				$(this).removeClass("xuanze");
			});
		});

		function deleteTypicalCase(recordFlow){
			jboxConfirm("确认移除该记录吗？",function () {
				var url = "<s:url value='/res/typicalCases/removeTypicalCase'/>?recordFlow="+recordFlow;
				jboxGet(url,null,function(){
					$("#casesRecordFlow").val("");
					refreshInfo();
				}, null, true);
			});
		}
		function checkTypicalCase(recordFlow,exp){
			var auditContent =$("#auditContent").val();
			if(""==auditContent.trim()){
				jboxTip("未填写批阅意见无法保存！");
				return;
			}
            var msg="";
            if(exp == "1"){
                msg ="确认审核通过该记录吗？"
            }else if(exp == "0"){
                msg ="确认审核不通过该记录吗？"
            }
			jboxConfirm(msg,function () {
				var url = "<s:url value='/res/typicalCases/adminTypicalCase'/>?recordFlow="+recordFlow+"&statuId="+exp;
				var data = $('#typicalCaseForm').serialize();
				jboxPost(url,data,function(){
					refreshInfo();
				}, null, true);
			});
		}
		function saveTypicalCase() {
			var peopleName=$("td input[name='peopleName']");
			if(peopleName[0].value.trim().length<=0){
				jboxTip("患者姓名不能为空！");
				return;
			}
			var birthDate=$("td input[name='birthDate']");
			if(birthDate[0].value.trim().length<=0){
				jboxTip("出生日期不能为空！");
				return;
			}
			var visitDate=$("td input[name='visitDate']");
			if(visitDate[0].value.trim().length<=0){
				jboxTip("就诊日期不能为空！");
				return;
			}
			var solarTerms=$("td input[name='solarTerms']");
			if(solarTerms[0].value.trim().length<=0){
				jboxTip("发病节气不能为空！");
				return;
			}
			var mainSuit=$("td input[name='mainSuit']");
			if(mainSuit[0].value.trim().length<=0){
				jboxTip("主诉不能为空！");
				return;
			}
			var recordFlow = $("#casesRecordFlow").val();
			var currentDate="${pdfn:getCurrDate()}";
			if(birthDate[0].value>currentDate){
				jboxTip("出生日期不能晚于当前日期！");
				return;
			}
			if(visitDate[0].value>currentDate){
				jboxTip("就诊日期不能晚于当前日期！");
				return;
			}
			if(visitDate[0].value<birthDate[0].value){
				jboxTip("就诊日期不能早于出生日期！");
				return;
			}
			var jsonData={};
			var fileFlows=[];
			$("input[name='fileFlows']").each(function(){
				var fileFlow=$(this).val();
				if(fileFlow)
					fileFlows.push(fileFlow);
			});
			jsonData.fileFlows=fileFlows;
			jboxConfirm("确认保存？" , function() {
				var url = "<s:url value='/res/typicalCases/editTypicalCase'/>?recordFlow="+recordFlow + "&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
				var data = $('#typicalCaseForm').serialize();
//				jboxPost(url, data, function (resp) {
//					refreshInfo();
//				}, null, true);
				jboxSubmit($("#typicalCaseForm"), url, function () {
					refreshInfo();
				}, null, true);
			});
		}
		function submitTypicalCase() {
			var peopleName=$("td input[name='peopleName']");
			if(peopleName[0].value.trim().length<=0){
				jboxTip("患者姓名不能为空！");
				return;
			}
			var birthDate=$("td input[name='birthDate']");
			if(birthDate[0].value.trim().length<=0){
				jboxTip("出生日期不能为空！");
				return;
			}
			var visitDate=$("td input[name='visitDate']");
			if(visitDate[0].value.trim().length<=0){
				jboxTip("就诊日期不能为空！");
				return;
			}
			var solarTerms=$("td input[name='solarTerms']");
			if(solarTerms[0].value.trim().length<=0){
				jboxTip("发病节气不能为空！");
				return;
			}
			var mainSuit=$("td input[name='mainSuit']");
			if(mainSuit[0].value.trim().length<=0){
				jboxTip("主诉不能为空！");
				return;
			}
			var recordFlow = $("#casesRecordFlow").val();
			var currentDate="${pdfn:getCurrDate()}";
			if(birthDate[0].value>currentDate){
				jboxTip("出生日期不能晚于当前日期！");
				return;
			}
			if(visitDate[0].value>currentDate){
				jboxTip("就诊日期不能晚于当前日期！");
				return;
			}
			if(visitDate[0].value<birthDate[0].value){
				jboxTip("就诊日期不能早于出生日期！");
				return;
			}
			var jsonData={};
			var fileFlows=[];
			$("input[name='fileFlows']").each(function(){
				var fileFlow=$(this).val();
				if(fileFlow)
					fileFlows.push(fileFlow);
			});
			jsonData.fileFlows=fileFlows;
			jboxConfirm("确认提交？" , function() {
				var url = "<s:url value='/res/typicalCases/subTypicalCase'/>?recordFlow="+recordFlow + "&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
				var data = $('#typicalCaseForm').serialize();
				jboxSubmit($("#typicalCaseForm"), url, function () {
					refreshInfo();
				}, null, true);
			});
		}
		function showDetail(caseFlow){
			var url = "<s:url value='/res/typicalCases/showTypicalCases'/>?caseFlow="+caseFlow;
			var roleId=$("#roleId").val();
			if("teacher"==roleId){
				var doctorFlow=$("#doctorFlow").val();
				var url ="<s:url value='/res/typicalCases/showTypicalCases'/>?caseFlow="+caseFlow+"&roleId=teacher"+"&doctorFlow="+doctorFlow;
			}
			if("globalSee"==roleId){
				var currentPage= $("#currentPage").val();
				var doctorFlow= $("#doctorFlow").val();
				var roleId=$("#roleId").val();
				url ="<s:url value='/res/typicalCases/showTypicalCases'/>?doctorFlow="+doctorFlow+"&roleId="+roleId+"&currentPage="+currentPage+"&caseFlow="+caseFlow;
			}
			location.href=url;
		}
		function refreshInfo(){
			var casesRecordFlow=$("#casesRecordFlow").val();
			var url ="<s:url value='/res/typicalCases/showTypicalCases'/>?caseFlow="+casesRecordFlow;
			var roleId=$("#roleId").val();
			if("teacher"==roleId){
				var currentPage= $("#currentPage").val();
				var doctorFlow= $("#doctorFlow").val();
				var url ="<s:url value='/res/typicalCases/showTypicalCases'/>?doctorFlow="+doctorFlow+"&roleId=teacher&currentPage="+currentPage;
			}
			if("see"==roleId||"adminSee"==roleId){
				var teacherCurrentPage= $("#teacherCurrentPage").val();
				var currentPage= $("#currentPage").val();
				var doctorFlow= $("#doctorFlow").val();
				url ="<s:url value='/res/typicalCases/showTypicalCases'/>?teacherCurrentPage="+teacherCurrentPage+"&doctorFlow="+doctorFlow+"&roleId="+roleId;
			}
			window.location.href= url;
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
		function agreeRecordBatch(exp){
			var url = "<s:url value='/res/typicalCases/agreeRecordBatch/${roleId}?doctorFlow=${doctorFlow}'/>";
			jboxConfirm("确认审核通过该学员所有待审核跟师医案？" , function() {
				jboxPost(url,null,function(resp){
					jboxTip(resp);
					setTimeout(function(){
						location.reload();
					},1000);
				}, null, false);
			});
		}
        function exportDocx(flow) {
            var url = '<s:url value="/res/typicalCases/exportDoc"/>?caseFlow='+flow;
            jboxTip("打印中,请稍等...");
            window.location.href = url;
        }
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="margin-top: 10px;width: 100%;">
			<div style="width: 30%;margin-bottom: 40px; float: left">
				<div class="index_title" style="height: 50px;width: 100%;">
					<input id="roleId" type="hidden" value="${roleId}"/>
					<input id="currentPage" type="hidden" value="${currentPage}"/>
					<input id="teacherCurrentPage" type="hidden" value="${teacherCurrentPage}"/>
					<input id="doctorFlow" type="hidden" value="${doctorFlow}"/>
					<p>
						<span style="float: left;margin-top: 10px;">跟师医案:</span>
					</p>
				</div>
				<div style="height: 800px;width: 100%;margin-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
					<c:if test="${empty resTypicalCasesList}">
						<p id="" style="margin-top: 30px;color: #BABABA;font-size:18px;">
							<c:choose>
								<c:when test="${'teacher'eq roleId}">&#12288;暂无待审核跟师医案</c:when>
								<c:otherwise>&#12288;还没有创建任何跟师医案哦</c:otherwise>
							</c:choose>
						</p>
					</c:if>
					<c:forEach items="${resTypicalCasesList}" var="cases">
						<div class="noteItems" style="width: 100%;padding-bottom: 10px;padding-top: 10px;
							<c:if test="${resTypicalCases.recordFlow eq cases.recordFlow}">background: #D0E3F2;</c:if>"
							 onclick="showDetail('${cases.recordFlow}');">
							<p>患者姓名：${cases.peopleName}&#12288;&#12288;</p>
							<p>创建时间：${pdfn:transDateTime(cases.createTime)}
								<br/>状态：
								<c:choose>
									<c:when test="${'PendingAudit' eq cases.auditStatusId}"><span style="color:#428BFF;">${cases.auditStatusName}</span></c:when>
									<c:when test="${'Apply' eq cases.auditStatusId or 'UnQualified' eq cases.auditStatusId}"><span style="color:#428BFF;">${cases.auditStatusName}</span></c:when>
									<c:when test="${'Qualified' eq cases.auditStatusId}"><span style="color:#00CC33;">${cases.auditStatusName}</c:when>
									<c:when test="${'UnQualified' eq cases.auditStatusId}"><span style="color:#FF2F5D;">${cases.auditStatusName}</c:when>
									<c:otherwise>${cases.auditStatusName}</c:otherwise>
								</c:choose>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>

			<div style="width: 65%;margin-left: 20px;margin-bottom: 40px; float: left">
				<div style="height: 50px;width: 100%;position: relative;">
					<input id="casesRecordFlow" type="hidden" value="${resTypicalCases.recordFlow}"/>
					<input id="seeFlag" type="hidden" value="${seeFlag}"/>
					<div style="float: left;min-width: 360px;">
						<p id="operateBtn" style="float: left;<c:if test='${empty resTypicalCasesList}'>display: none;</c:if>">
							<p style="position: absolute;top: 0;right: 0;">
								<input class="btn_blue" type="button" onclick="exportDocx('${resTypicalCases.recordFlow}')" value="打&#12288;印"/>
							</p>
						</p>
						<c:if test="${empty roleId}">
							<p id="editTip" class="red" style="<c:if test="${resTypicalCases.auditStatusId eq 'Apply' or 'UnQualified' eq resTypicalCases.auditStatusId}">display: none;</c:if> min-width: 500px;padding-top: 3px;">
								（每年收集整理10份师承指导老师典型临床病案）
							</p>
						</c:if>
					</div>
				</div>
				<div style="height: 800px;width: 100%;margin-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
					<c:if test="${empty resTypicalCasesList}">
						<div id="notice" style="margin-top: 30px;color: #BABABA;font-size:18px;">
							<c:choose>
								<c:when test="${'teacher'eq roleId}">&#12288;暂无待审核跟师医案</c:when>
								<c:otherwise>&#12288;还没有创建任何跟师医案哦</c:otherwise>
							</c:choose>
						</div>
					</c:if>
					<form id="typicalCaseForm" method="post" enctype="multipart/form-data">
						<div id="typicalCaseInfos" style="<c:if test="${empty resTypicalCasesList}">display: none;</c:if>margin-bottom: 20px">
							<table class="basic" width="90%" style="margin-left: 30px" >
								<colgroup>
									<col width="17%"/>
									<col width="17%"/>
									<col width="16%"/>
									<col width="17%"/>
									<col width="16%"/>
									<col width="17%"/>
								</colgroup>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>患者姓名：</td>
									<td title="${resTypicalCases.peopleName}">
										<input type="text" name="peopleName" value="${resTypicalCases.peopleName}"/>
									</td>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>性别：</td>
									<td>
										<c:choose>
											<c:when test="${'Apply' eq seeFlag or 'UnQualified' eq seeFlag}">
												<input id="Man"  name="sexId" value="Man" <c:if test="${'Man' eq resTypicalCases.sexId}">checked="checked"</c:if> type="radio">
												<label for="Man">男</label>
												<input id="Woman"  name="sexId" value="Woman" <c:if test="${'Woman' eq resTypicalCases.sexId}">checked="checked"</c:if> type="radio">
												<label for="Woman">女</label>
											</c:when>
											<c:otherwise>${resTypicalCases.sexName}</c:otherwise>
										</c:choose>
									</td>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>出生日期：</td>
									<td>
										<c:choose>
											<c:when test="${'Apply' eq seeFlag or 'UnQualified' eq seeFlag}"><input type="text" name="birthDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="${resTypicalCases.birthDate}"></c:when>
											<c:otherwise><input type="text" name="birthDate"  value="${resTypicalCases.birthDate}"></c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>就诊日期：</td>
									<td>
										<c:choose>
											<c:when test="${'Apply' eq seeFlag or 'UnQualified' eq seeFlag}"><input type="text" name="visitDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="${resTypicalCases.visitDate}"></c:when>
											<c:otherwise><input type="text" name="visitDate" value="${resTypicalCases.visitDate}"/></c:otherwise>
										</c:choose>
									</td>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>初诊、复诊：</td>
									<td>
										<c:choose>
											<c:when test="${'Apply' eq seeFlag or 'UnQualified' eq seeFlag}">
												<input id="chuzhen"  name="visitActionId" value="chuzhen" <c:if test="${'chuzhen' eq resTypicalCases.visitActionId}">checked="checked"</c:if>  type="radio">
												<label for="chuzhen">初诊</label>
												<input id="fuzhen"  name="visitActionId" value="fuzhen" <c:if test="${'fuzhen' eq resTypicalCases.visitActionId}">checked="checked"</c:if> type="radio">
												<label for="fuzhen">复诊</label>
											</c:when>
											<c:otherwise>${resTypicalCases.visitActionName}</c:otherwise>
										</c:choose>
									</td>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>发病节气：</td>
									<td title="${resTypicalCases.solarTerms}">
										<input type="text" name="solarTerms"
											   value="${resTypicalCases.solarTerms}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>主诉：</td>
									<td colspan="5" title="${resTypicalCases.mainSuit}">
										<input type="text" name="mainSuit"
											   value="${resTypicalCases.mainSuit}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">现病史：</td>
									<td colspan="5" title="${resTypicalCases.presentDiseaseHistory}">
										<input type="text" name="presentDiseaseHistory"
											   value="${resTypicalCases.presentDiseaseHistory}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">既往史：</td>
									<td colspan="5" title="${resTypicalCases.previousDiseaseHistory}">
										<input type="text" name="previousDiseaseHistory"
											   value="${resTypicalCases.previousDiseaseHistory}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">过敏史：</td>
									<td colspan="5" title="${resTypicalCases.allergicHistory}">
										<input type="text" name="allergicHistory"
											   value="${resTypicalCases.allergicHistory}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">体格检查：</td>
									<td colspan="5" title="${resTypicalCases.physicalExamination}">
										<input type="text" name="physicalExamination"
											   value="${resTypicalCases.physicalExamination}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">辅助检查：</td>
									<td colspan="5" title="${resTypicalCases.accessoryExamination}">
										<input type="text" name="accessoryExamination"
											   value="${resTypicalCases.accessoryExamination}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">中医诊断：</td>
									<td colspan="5" title="${resTypicalCases.tcmDiagnosis}">
										<input type="text" name="tcmDiagnosis"
											   value="${resTypicalCases.tcmDiagnosis}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">证候诊断：</td>
									<td colspan="5" title="${resTypicalCases.syndromeDiagnosis}">
										<input type="text" name="syndromeDiagnosis"
											   value="${resTypicalCases.syndromeDiagnosis}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">西医诊断：</td>
									<td colspan="5" title="${resTypicalCases.westernDiagnosis}">
										<input type="text" name="westernDiagnosis"
											   value="${resTypicalCases.westernDiagnosis}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">治法：</td>
									<td colspan="5" title="${resTypicalCases.therapy}">
										<input type="text" name="therapy"
											   value="${resTypicalCases.therapy}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">处方：</td>
									<td colspan="5" title="${resTypicalCases.prescription}">
										<input type="text" name="prescription"
											   value="${resTypicalCases.prescription}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">复诊：</td>
									<td colspan="5" title="${resTypicalCases.returnVisit}">
										<input type="text" name="returnVisit"
											   value="${resTypicalCases.returnVisit}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">${(empty sysCfgMap['zy_jdya_lzsb'])?'临证随笔或心得':sysCfgMap['zy_jdya_lzsb']}：</td>
									<td colspan="5" title="${resTypicalCases.experienceContent}">
										<input type="text" name="experienceContent"
											   value="${resTypicalCases.experienceContent}"/>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">签名：</td>
									<td colspan="5">
										<p style="float: right;margin-right: 30px">
											<span>跟师规培学员签名：${resTypicalCases.doctorName}&#12288;&#12288;&#12288;&#12288; ${signTime}</span>
										</p>
									</td>
								</tr>
								<tr>
									<td style="height: 36px;text-align: center;padding-left: 0px">附件：</td>
									<td colspan="5">
										<c:if test="${resTypicalCases.auditStatusId eq 'Apply' or 'UnQualified' eq resTypicalCases.auditStatusId or 'Qualified' eq resTypicalCases.auditStatusId}">
											<table style="max-width: 480px;width: 90%;float: left;margin-left: 5px;margin-bottom: 10px;margin-top: 10px;" class="filesTable" id="filesTable">
												<tr>
													<td style="text-align: left;padding-left: 10px;">
														附件名&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
													</td>
												</tr>
												<c:forEach items="${discipleFiles}" var="discipleFile">
													<tr>
														<td style="text-align: left;padding-left: 10px;">
															<input hidden name="fileFlows" value="${discipleFile.fileFlow}">
															<a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
														</td>
													</tr>
												</c:forEach>
											</table>
										</c:if>
										<c:if test="${!(resTypicalCases.auditStatusId eq 'Apply' or 'UnQualified' eq resTypicalCases.auditStatusId or
										'Qualified' eq resTypicalCases.auditStatusId) and not empty discipleFiles}">
											<table style="max-width: 480px;width: 90%;float: left;margin-left: 5px;margin-bottom: 10px;margin-top: 10px;" class="filesTable" id="filesTable">
												<tr>
													<td style="text-align: left;padding-left: 10px;">
														附件名&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
													</td>
												</tr>
												<c:forEach items="${discipleFiles}" var="discipleFile">
													<tr>
														<td style="text-align: left;padding-left: 10px;">
															<input hidden name="fileFlows" value="${discipleFile.fileFlow}">
															<a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
														</td>
													</tr>
												</c:forEach>
											</table>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="6">
										师承指导老师批阅意见：（要有针对性和指导性，能体现指导老师的学术和水平）
                                    <textarea id="auditContent"
											<c:if test="${'teacher'!= roleId}">readonly="readonly"</c:if>
											class="xltxtarea" style="height: 250px" name="auditContent"
											value="">${resTypicalCases.auditContent}</textarea>
										<p style="float: right;margin-right: 30px;">
											<span>师承签名：
												<c:if test="${'teacher'!= roleId}">
													<c:choose>
														<c:when test="${'Apply' eq seeFlag}"><span style="color: red">该医案暂未提交</span></c:when>
														<c:when test="${'PendingAudit' eq seeFlag}"><span style="color: red">师承老师暂未审核</span></c:when>
														<c:when test="${'Qualified' eq seeFlag}">${pdfn:getSingnPhoto(resTypicalCases.teacherFlow)}</br>&#12288;&#12288;${auditTime}</c:when>
														<c:when test="${'UnQualified' eq seeFlag}">${pdfn:getSingnPhoto(resTypicalCases.teacherFlow)}</br>&#12288;&#12288;${auditTime}</c:when>
														<c:otherwise></c:otherwise>
													</c:choose>
												</c:if>
												<c:if test="${'teacher'eq roleId}">
													<c:set var="teaFlow" value="${resTypicalCases.teacherFlow}"/>
													${pdfn:getSingnPhoto(teaFlow)}</br>
													<span>&#12288;&#12288;${currDate}</span>
												</c:if>
											</span>
										</p>
									</td>
								</tr>
							</table>
							<span style="color: #FF0000;margin-left: 30px;">Tip:本日期需与跟师 记录相对应</span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;">
		<div id="operate" style="margin-bottom: 20px">
			<p id="operateBtn2" style="float: left;">
				<input class="btn_blue" type="button" value="保存并阅读" class="search" onclick="saveTypicalCase();"/>
				&#12288;
				<input class="btn_blue" type="button" value="提交" class="search" onclick="submitTypicalCase();"/>
				（提交后指导老师才可审核）
			</p>
		</div>
	</div>
	<div style="display: none;">
		<div id="addTypicalCases" style="margin-bottom: 20px">
			<table class="basic"  width="90%" style="margin-left: 30px" >
				<colgroup>
					<col width="17%"/>
					<col width="17%"/>
					<col width="16%"/>
					<col width="17%"/>
					<col width="16%"/>
					<col width="17%"/>
				</colgroup>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px" ><font name="requrid" color="red">*</font>患者姓名：</td>
					<td>
						<input type="text" name="peopleName" value=""/>
					</td>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>性别：</td>
					<td>
						<%--<input  type="text" name="sexName" value=""/>--%>
						<input id="Man"  name="sexId" value="Man" checked="checked" type="radio">
						<label for="Man">男</label>
						<input id="Woman"  name="sexId" value="Woman" type="radio">
						<label for="Woman">女</label>
					</td>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>出生日期：</td>
					<td>
						<input id="birthDate" type="text" name="birthDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="${pdfn:getCurrDate()}">
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>就诊日期：</td>
					<td>
						<input id="visitDate" type="text" name="visitDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="${pdfn:getCurrDate()}">
					</td>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>初诊、复诊：</td>
					<td>
						<%--<input type="text" name="visitActionName"--%>
							   <%--value=""/>--%>
						<input id="chuzhen"  name="visitActionId" value="chuzhen" checked="checked" type="radio">
						<label for="chuzhen">初诊</label>
						<input id="fuzhen"  name="visitActionId" value="fuzhen" type="radio">
						<label for="fuzhen">复诊</label>
					</td>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>发病节气：</td>
					<td>
						<input type="text" name="solarTerms"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px"><font name="requrid" color="red">*</font>主诉：</td>
					<td colspan="5">
						<input type="text" name="mainSuit"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">现病史：</td>
					<td colspan="5">
						<input type="text" name="presentDiseaseHistory"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">既往史：</td>
					<td colspan="5">
						<input type="text" name="previousDiseaseHistory"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">过敏史：</td>
					<td colspan="5">
						<input type="text" name="allergicHistory"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">体格检查：</td>
					<td colspan="5">
						<input type="text" name="physicalExamination"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">辅助检查：</td>
					<td colspan="5">
						<input type="text" name="accessoryExamination"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">中医诊断：</td>
					<td colspan="5">
						<input type="text" name="tcmDiagnosis"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">证候诊断：</td>
					<td colspan="5">
						<input type="text" name="syndromeDiagnosis"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">西医诊断：</td>
					<td colspan="5">
						<input type="text" name="westernDiagnosis"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">治法：</td>
					<td colspan="5">
						<input type="text" name="therapy"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">处方：</td>
					<td colspan="5">
						<input type="text" name="prescription"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">复诊：</td>
					<td colspan="5">
						<input type="text" name="returnVisit"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">${(empty sysCfgMap['zy_jdya_lzsb'])?'临证随笔或心得':sysCfgMap['zy_jdya_lzsb']}：</td>
					<td colspan="5">
						<input type="text" name="experienceContent"
							   value=""/>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">签名：</td>
					<td colspan="5">
						<p style="float: right;margin-right: 30px">
							<span>跟师规培学员签名：${sysUser.userName}&#12288;&#12288;&#12288;&#12288; ${currDate}</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="height: 36px;text-align: center;padding-left: 0px">附件：</td>
					<td colspan="5">
						<table style="max-width: 480px;width: 90%;float: left;margin-left: 5px;margin-bottom: 10px;margin-top: 10px;" class="filesTable" id="filesTable">
							<tr>
								<td style="text-align: left;padding-left: 10px;">
									附件名&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
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
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						师承指导老师批阅意见：（要有针对性和指导性，能体现指导老师的学术和水平）
                                    <textarea  class="xltxtarea" style="height: 250px" name="auditContent" readonly="readonly"
											value=""></textarea>
						<p style="float: right;margin-right: 30px;">
							<span>师承签名：</span><input class="inputText" name="teacherName"
													 value=""
													 readonly="readonly"/><br/>
							&#12288;&#12288;&#12288;&#12288;&#12288;<input class="inputText" name="auditTime"
																   value=""
																   readonly="readonly"/>
						</p>
					</td>
				</tr>
			</table>
			<span style="color: #FF0000;margin-left: 30px;">注：本日期需与跟师记录相对应</span>
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