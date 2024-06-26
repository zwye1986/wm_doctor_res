
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
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
		.btn {
			display: inline-block;
			overflow: visible;
			padding: 0 22px;
			height: 30px;
			line-height: 30px;
			vertical-align: middle;
			text-align: center;
			text-decoration: none;
			border-radius: 3px;
			-moz-border-radius: 3px;
			-webkit-border-radius: 3px;
			font-size: 14px;
			border-width: 1px;
			border-style: solid;
			cursor: pointer;
		}
	</style>
	<script type="text/javascript">
		function search(){
			$("#searchForm").submit();
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function toPrint() {
			var url = "<s:url value='/res/sxs/expoertResSxsScore/${scope}'/>";
			jboxTip("导出中…………");
			jboxSubmit($("#searchForm"), url, null, null, false);
			jboxEndLoading();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div class="queryDiv">
			<form id="searchForm" method="post" action="<s:url value='/res/sxs/schScore/${scope}'/>">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<c:if test="${scope eq 'global'}">
						<div class="inputDiv">
						实习基地：
						<select name="orgFlow"  id="orgFlow" class="qselect">
							<option value="">请选择</option>
							<c:forEach items="${orgList}" var="org">
								<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>
						</div>
					</c:if>
					<%--<div class="inputDiv">--%>
						<%--轮转科室：--%>
						<%--<select name="schDeptFlow" class="qselect">--%>
							<%--<option value="">请选择</option>--%>
							<%--<c:forEach items="${deptList}" var="dept">--%>
								<%--<option value="${dept.schDeptFlow}" ${param.schDeptFlow eq dept.schDeptFlow?'selected':''}>${dept.schDeptName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</div>--%>
					<div class="inputDiv">
						姓&#12288;&#12288;名：
						<input type="text" name="doctorName" value="${param.doctorName}" onchange="search();" class="qtext">
					</div>
					<div class="inputDiv">
						年&#12288;&#12288;级：
						<select name="sessionNumber" class="qselect">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv" style="min-width: 185px;max-width: 185px">
						<input type="button" class="search" value="查&#12288;询" onclick="search();"/>
						<input type="button" class="search" value="导&#12288;出" onclick="toPrint();"/>
					</div>
			</form>
			</div>
			<c:if test="${(empty orgFlow) and (scope eq GlobalConstant.USER_LIST_GLOBAL)}">
				<div style="text-align: center;height: 30px;float: left;width: 100%" class="basic">
					请选择实习基地
				</div>
			</c:if>
			<c:if test="${!((empty orgFlow) and (scope eq GlobalConstant.USER_LIST_GLOBAL))}">
				<div style="width:100%;border: 0px solid #e3e3e3;overflow: auto;" class="basic">
					<table class="basic" width="100%">
						<tr style="font-weight: bold;">
							<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">姓名</th>
							<c:forEach items="${deptList}" var="schDept">
								<c:if test="${(not empty param.schDeptFlow) and (schDept.schDeptFlow eq param.schDeptFlow) }">
									<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${schDept.schDeptName}</th>
								</c:if>
								<c:if test="${empty param.schDeptFlow}">
									<th style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${schDept.schDeptName}</th>
								</c:if>
							</c:forEach>
						</tr>
						<c:forEach items="${doctorList}" var="doctor">
							<tr>
								<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">${doctor.sysUser.userName}</td>
								<c:forEach items="${deptList}" var="schDept">
									<c:set var="key" value="${doctor.doctorFlow}${schDept.schDeptFlow}"></c:set>
									<c:set var="process" value="${resultMap[key]}"></c:set>
									<c:if test="${not empty param.schDeptFlow and (schDept.schDeptFlow eq param.schDeptFlow) }">
										<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">
											<c:if test="${empty process or (empty process.schScore)}">暂无</c:if>
											<c:if test="${!(empty process or (empty process.schScore))}">${process.schScore}</c:if>
										</td>
									</c:if>
									<c:if test="${ empty param.schDeptFlow }">
										<td style="text-align: center;padding: 0px;width: 100px;min-width:100px;">
											<c:if test="${empty process or (empty process.schScore)}">暂无</c:if>
											<c:if test="${!(empty process or (empty process.schScore))}">${process.schScore}</c:if>
										</td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>

						<c:if test="${empty doctorList}">
							<tr><td style="text-align: center;" colspan="${1+fn:length(deptList)}">无学员信息</td></tr>
						</c:if>
					</table>
				</div>
				<div>
					<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>