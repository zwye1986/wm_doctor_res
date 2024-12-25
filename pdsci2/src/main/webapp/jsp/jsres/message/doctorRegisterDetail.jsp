<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="true"/>
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
	<script src="<s:url value='/js/jsres/screenshot/html2canvas.min.js'/>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
<script type="text/javascript"
			src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
			src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">

	function detailOperation(operationType,recruitFlow,speId){
		var msg = "确认报到吗？";
		var url = "<s:url value='/jsres/message/doRegister'/>";
		var data = {};
		data.operationType = operationType;
		data.recruitFlow = recruitFlow;
        data.speId = speId;
		top.jboxConfirm(msg,function(){
			top.jboxPost(url, data, function(resp){
				top.jboxEndLoading();
				// window.parent.main(resp);
				top.jboxTip(resp);
				window.parent.jboxCloseMessager();
				window.parent.getPlanList('',1);
				setTimeout(function(){
					jboxCloseMessager();
				}, 1000);
			}, null, false);
		},function(){
			top.jboxEndLoading();
			$("#saveBtn").removeAttr("disabled");
		});
	}

	// 点击报到
	function editDoctorReport(recruitFlow, doctorFlow){
		var url = "<s:url value='/jsres/doctor/editDoctorRecruit'/>?recruitFlow="+recruitFlow +"&doctorFlow=" + doctorFlow +"&doctorStatus=report";
		var title = "学员报到";
		jboxOpen(url, title, 900, 500);
	}


</script>
</head>
<body>
<iframe style="display:none" id="printDivIframe" name="printDivIframe">
	<div id="printDoc">
	</div>
</iframe>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div id="tagContent" >
				<form id="saveForm" style="text-align: center">
					<table width="100%" cellpadding="0" cellspacing="0" class="basic">
						<colgroup>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
						</colgroup>
						<tr>
							<td>姓名：</td>
							<td>${currUser.userName}</td>
							<td>专业排名：</td>
							<%--<td>${recruitExt.rankNum}</td>--%>
							<c:set var="key" value="${recruit.recruitFlow}${recruit.doctorFlow}"></c:set>
							<td>${rankNumMap[key]}</td>
						</tr>
						<tr>
							<td>报考基地：</td>
							<td>${recruit.orgName}</td>
							<td>报考专业：</td>
							<td>${recruit.speName}</td>
						</tr>
						<tr>

							<td>笔试成绩：</td>
							<td>${recruit.examResult}</td>
							<td>面试成绩：</td>
							<td>${recruit.auditionResult}</td>
						</tr>
						<tr>
							<td>操作成绩：</td>
							<td>${recruit.operResult}</td>
							<td>总成绩：</td>
							<td>${recruit.totleResult}</td>
						</tr>
						<tr>
							<td>是否确认报到：</td>
							<td>
								<c:if test="${recruit.confirmFlag eq 'Y'}">
									是
								</c:if>
								<c:if test="${recruit.confirmFlag ne 'Y'}">
									否
								</c:if>
							</td>
							<td>是否录取：</td>
							<td>
								<c:if test="${recruit.recruitFlag eq 'Y'}">
									是
								</c:if>
								<c:if test="${recruit.recruitFlag ne 'Y'}">
									否
								</c:if>
							</td>
						</tr>
					</table>
					<div class="button" style="width: 730px;">
						<c:if test="${recruit.recruitFlag eq 'Y' and recruit.confirmFlag ne 'Y'}">
							<input type="button" id="saveBtn" class="btn_green"
								   onclick="editDoctorReport('${recruit.recruitFlow}','${recruit.doctorFlow}');" value="报到"/>&nbsp;
						</c:if>
						<input type="button" class="btn_green" onclick="jboxCloseMessager();" value="关闭"/>&nbsp;
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>