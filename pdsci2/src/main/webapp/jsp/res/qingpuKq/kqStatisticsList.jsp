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
			var url= '<s:url value="/res/qingpuKq/kqStatisticsDetail"/>?doctorFlow='+doctorFlow+"&type="+type+
					"&startDate="+startDate+"&endDate="+endDate;
			jboxOpen(url,'详情',800,600,true);
		}
		function exportExl(){
			if(${empty resultMapList}){
				jboxTip("当前无记录!");
				return;
			}
			var url = "<s:url value='/res/qingpuKq/kqStatisticsListExport'/>";
			jboxTip("导出中…………");
			jboxExp($("#searchForm"),url);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/qingpuKq/kqStatisticsList"/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<%--<div class="inputDiv">--%>
						<%--<label class="qlable">所属科室：</label>--%>
						<%--<select name="deptFlow" class="qselect">--%>
							<%--<option value="">全部</option>--%>
							<%--<c:forEach items="${deptList}" var="dept">--%>
								<%--<option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</div>--%>
					<div class="inputDiv" style="margin-left: 20px;">
						<label class="qlable">起止时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						参培年份：
						<select name="sessionNumber" class="qselect" >
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select name="trainingSpeId" class="qselect" >
							<option  value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">学员姓名：</label>
						<input type="text" class="qselect" name="doctorName" value="${param.doctorName}">
					</div>
					<div class="inputDiv" style="text-align: left">&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						<input type="button" value="导&#12288;出" class="search" onclick="exportExl();"/>
					</div>
				</div>
				<table class="xllist">
					<tr>
						<th width="10%">学员姓名</th>
						<th width="10%">培训专业</th>
						<th width="10%">参培年份</th>
						<th width="10%">请假次数</th>
						<th width="10%">申诉次数</th>
						<th width="10%">违纪次数</th>
						<th width="10%">迟到次数</th>
					</tr>
					<c:forEach items="${resultMapList}" var="result">
						<tr>
							<td>${result.USER_NAME}</td>
							<td>${result.TRAINING_SPE_NAME}</td>
							<td>${result.SESSION_NUMBER}</td>
							<td><a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','Leave')">${result.LEAVE}</a></td>
							<td><a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','Appeal')">${result.APPEAL}</a></td>
							<td><a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','Submit')">${result.SUBMIT}</a></td>
							<td><a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','Late')">${result.LATE}</a></td>
						</tr>
					</c:forEach>
					<c:if test="${empty resultMapList}">
						<tr>
							<td colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
			</form>
			<p>
				<c:set var="pageView" value="${pdfn:getPageView2(resultMapList, 10)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>