<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="login" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
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
	<script type="text/javascript">
		function search() {
			var url = "<s:url value='/res/njExam/searchDocInfoBySpe'/>";
			jboxPostLoad("doctorContent", url, $("#paramForm").serialize(), false);
		}
		function toPage(page) {
			$("#currentPage").val(page);
			jboxStartLoading();
			$("#paramForm").submit();
		}

		function exportInfo(){
			var url = "<s:url value='/res/njExam/exportInfo'/>";
			jboxTip("导出中…………");
			jboxExp($("#paramForm"), url);
			jboxEndLoading();
		}
	</script>
	<style>
		body {
			overflow: hidden;
		}
	</style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
	<div class="bd_bg">
		<div class="yw">
			<div class="body">
				<div class="container">
						<div class="main_hd" id="doctorContent">
							<form id="paramForm" action="<s:url value="/res/njExam/searchDocInfoBySpe"/>" method="post">
								<h2>姓名<input class="input" type="text" name="userName" value="${docinfo.userName}">&#12288;
									证件号<input class="input" type="text" name="idNo" value="${docinfo.idNo}">&#12288;
									<input type="hidden" id="currentPage" name="currentPage"/>
									<input type="hidden" id="speName" name="speName" value="${speName}"/>
									<input class="btn_green" type="button"
										   onclick="search();" value="查询">
									<input class="btn_green" type="button"
										   onclick="exportInfo();" value="导出">
								</h2>
							</form>
							<div class="doctorContent">
								<div class="search_table" id="baseInfo">
									<table border="0" cellpadding="0" cellspacing="0" class="base_info">
										<colgroup>
											<col width="25%"/>
											<col width="25%"/>
											<col width="25%"/>
											<col width="25%"/>
										</colgroup>
										<tr>
											<th style="text-align:center;">姓名</th>
											<th style="text-align:center;">证件号码</th>
											<th style="text-align:center;">培训专业</th>
											<th style="text-align:center;">准考证号</th>
										</tr>
										<c:forEach items="${extList}" var="ext" varStatus="extStatus">
											<tr>
												<td>${ext.userName}</td>
												<td>${ext.idNo}</td>
												<td>${ext.speName}</td>
												<td>${ext.ticketNum}</td>
											</tr>
										</c:forEach>
										<c:if test="${empty extList}">
											<tr>
												<td colspan="5">无记录！</td>
											</tr>
										</c:if>
									</table>
								</div>
								<div class="page" style="padding-right: 40px;">
									<c:set var="pageView" value="${pdfn:getPageView(extList)}" scope="request"></c:set>
									<pd:pagination-jszy toPage="toPage"/>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
