<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true" />
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

	function toPage(page) {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(startDate>endDate){
			var startDate = $("#itemsDate").attr("startDate");
			var endDate = $("#itemsDate").attr("endDate");
			$("#startDate").val(startDate);
			$("#endDate").val(endDate);
			jboxTip("开始日期不能小于截止日期");
			return;
		}
		if(page){
			$("#currentPage").val(page);
		}
		$("#sysRecordsForm").submit();
	}
    function toPrint() {
        var url = "<s:url value='/res/manager/exportSysRecords'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#sysRecordsForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
				<form id="sysRecordsForm" method="post" action="<s:url value='/res/manager/showOrgSysRecords'/>">
					<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
					<input id="itemsDate" type="hidden" startDate="${param.startDate}" endDate="${param.endDate}" />
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select id="sessionNumber" name="sessionNumber"
									class="qselect">
								<option></option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
									<option value="${dict.dictId}" ${param.sessionNumber eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">开始日期：</label>
							<input class="qtext" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" />
						</div>
						<div class="inputDiv">
							<label class="qlable">结束日期：</label>
							<input class="qtext" id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}"/>
						</div>
						<div class="lastDiv" style="max-width: 180px;min-width: 180px;">
							<input type="button" class="search" onclick="toPage(1);" value="查&#12288;询"/>
							<input type="button" class="search" onclick="toPrint();" value="导&#12288;出"/>
						</div>
					</div>
				</form>
			<div style="width:100%;border: 0px solid #e3e3e3;overflow: auto;" class="basic">
				<table class="basic" width="100%">
					<colgroup>

					</colgroup>
					<tr style="font-weight: bold;">
						<th style="text-align: center;padding: 0px;width: 200px;min-width:200px;">姓名</th>
						<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">出科小结</th>
						<c:forEach items="${registryTypeEnumList}" var="registryType">
							<c:set var="res_registry_type_key" value="res_registry_type_${registryType.id }"/>
							<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_Y}">
								<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${registryType.name}</th>
							</c:if>
						</c:forEach>
						<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">带教评价</th>
						<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">科室评价</th>
						<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">考勤签到数</th>
					</tr>
					<c:forEach items="${records}" var="record">
						<tr>
							<td style="text-align: center;padding: 0px;width: 200px;min-width:200px;">${record['doctorName']}</td>
							<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${record['AfterSummary']}</td>
							<c:forEach items="${registryTypeEnumList}" var="registryType">
								<c:set var="res_registry_type_key" value="res_registry_type_${registryType.id }"/>
								<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_Y}">
									<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${record[registryType.id]}</td>
								</c:if>
							</c:forEach>
							<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${record['TeacherGrade']}</td>
							<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${record['DeptGrade']}</td>
							<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${record['siginNum']}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty records}">
						<tr><td style="text-align: center;" colspan="99">无记录</td></tr>
					</c:if>
				</table>
			</div>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(records)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>
</div>
</div>
</body>
</html>