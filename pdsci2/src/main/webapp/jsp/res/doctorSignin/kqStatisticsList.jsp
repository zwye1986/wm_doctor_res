<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="false" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
	<style type="text/css">
		table.xllist a{color: blue;}
	</style>
	<script type="text/javascript">

		function search(){
			var form = $("#searchForm");
			form.submit();
		}

		function toPage(page){
			if (page) {
				$("#currentPage").val(page);
			}
			search();
		}
		function calculateDay(){
			var startDate = $("input[name='startDate']").val();
			var endDate = $("input[name='endDate']").val();
			if("" == startDate || "" == endDate){
				return;
			}
			if(endDate < startDate){
				jboxTip("结束日期不能早于开始日期！");
				return;
			}
		}
		function detail(doctorFlow,type){
			var startDate = $("input[name='startDate']").val();
			var endDate = $("input[name='endDate']").val();
			var url= '<s:url value="/res/doctorSignin/kqStatisticsDetail"/>?doctorFlow='+doctorFlow+"&typeId="+type+
					"&startDate="+startDate+"&endDate="+endDate;
			jboxOpen(url,'详情',800,600,true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doctorSignin/kqStatisticsList"/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">学员姓名：</label>
						<input class="qtext" id="doctorName" name="doctorName" value="${param.doctorName}" type="text"/>
					</div>
					<div class="inputDiv" style="max-width: 450px;">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" style="width: 120px;" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"/>
						~ <input type="text" class="qtime" style="width: 120px;" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv" style="text-align: left">&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
					</div>
				</div>
				<table class="xllist">
					<tr>
						<th>学员姓名</th>
						<th>培训专业</th>
						<th>参培年份</th>
						<c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
							<th>${type.dictName}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${resultMapList}" var="result">
						<tr>
							<td>${result.USER_NAME}</td>
							<td>${result.TRAINING_SPE_NAME}</td>
							<td>${result.SESSION_NUMBER}</td>
							<c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
								<c:set var="kqType" value="${fn:toUpperCase(type.dictId)}"/>
								<td><a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','${type.dictId}')">${result[kqType]}</a></td>
							</c:forEach>
						</tr>
					</c:forEach>
					<c:if test="${empty resultMapList}">
						<tr>
							<td colspan="99">无记录</td>
						</tr>
					</c:if>
				</table>
			</form>
			<p>
				<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>