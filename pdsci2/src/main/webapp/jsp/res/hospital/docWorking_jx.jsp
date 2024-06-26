<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<style>
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		table.basic th,table.basic td{ text-align: center;padding: 0; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${studentTypes}" var="data">
				$("#"+"${data}").attr("checked","checked");
			</c:forEach>
		});
		function detailSearch(userFlow){
			var url = "<s:url value='/res/manager/docWorkDetailSearch'/>?userFlow="+userFlow;
			jboxOpen(url, "详情", 960, 600, true);
		}
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
            var url = "<s:url value='/res/manager/exportDocWorking'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="clearfix" style="padding: 10px 0px;">
			<div class="queryDiv">
			<form id="searchForm" action="<s:url value='/res/manager/doc/docWorking'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<input type="hidden" name="role" value="${role}"/>
				<%--<div class="inputDiv">--%>
					<%--年&#12288;&#12288;级：--%>
					<%--<select name="sessionNumber" class="qselect">--%>
						<%--<option value="">全部</option>--%>
						<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
							<%--<option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
				<%--</div>--%>
				<div class="inputDiv">
					姓&#12288;&#12288;名：
					<input type="text" class="qtext" value="${param.docName}" name="docName">
				</div>
				<%--<div class="doctorTypeDiv">--%>
					<%--<div class="doctorTypeLabel">学员类型：</div>--%>
					<%--<div class="doctorTypeContent">--%>
						<%--<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">--%>
							<%--<input id="${dict.dictId}" type="checkbox" name="studentTypes" value="${dict.dictId}" <c:if test="${empty param.studentTypes}">checked</c:if>/>${dict.dictName}--%>
						<%--</c:forEach>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="inputDiv">
					证件号码：
					<input type="text" class="qtext" value="${param.cardNo}" name="cardNo">
				</div>
                <div class="inputDiv">
                    进修专业：
                    <select name="deptFlow" class="qselect" style="width: 157px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                        <option value="${SysDept.deptFlow}" ${param.deptFlow eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
				<div class="inputDiv" style="text-align: left;margin-left: 25px;">
					<input type="button" class="search" value="查&#12288;询" onclick="toPage();"/>
					<input type="button" class="search" value="导&#12288;出" onclick="toPrint();" style="margin-left: 0px"/>
				</div>
			</form>
		</div>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="basic" style="text-align: center">
				<tr style="height: 15px;max-height: 15px;">
					<th style="text-align: center" rowspan="2">姓名</th>
					<th style="text-align: center" rowspan="2">进修专业</th>
					<%--<th style="text-align: center" rowspan="2">年级</th>--%>
					<th style="text-align: center" colspan="5">培训数据</th>
					<th style="text-align: center" colspan="3">轮转科室</th>
					<th style="text-align: center" rowspan="2">详细信息</th>
				</tr>
				<tr style="height: 15px;max-height: 15px;">
					<th style="text-align: center;width: 80px;">要求数</th>
					<th style="text-align: center;width: 80px;">完成数</th>
					<th style="text-align: center;width: 80px;">完成比例</th>
					<th style="text-align: center;width: 80px;">审核数</th>
					<th style="text-align: center;width: 80px;">审核比例</th>
					<th style="text-align: center;width: 80px;">要求数</th>
					<th style="text-align: center;width: 80px;">已轮转</th>
					<th style="text-align: center;width: 80px;">已出科</th>
				</tr>
				<c:forEach items="${rltLst}" var="obj">
					<tr>
						<td style="padding-left: 0px;text-align: center">${obj.doctorName}</td>
						<td style="padding-left: 0px;text-align: center">${obj.speName}</td>
						<%--<td style="padding-left: 0px;text-align: center">${obj.sessionNumber}</td>--%>
						<td style="padding-left: 0px;text-align: center">${obj.reqNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.completeNum}</td>
						<td>
							<c:if test="${obj.reqNum == '0'}">
								-
							</c:if>
							<c:if test="${obj.reqNum ne '0'}">
								<fmt:formatNumber type="percent" value="${obj.completeNum/obj.reqNum}" maxFractionDigits="2"/>
							</c:if>
						</td>
						<td style="padding-left: 0px;text-align: center">${obj.checkNum}</td>
						<td>
							<c:if test="${obj.completeNum == '0'}">
								-
							</c:if>
							<c:if test="${obj.completeNum ne '0'}">
								<fmt:formatNumber type="percent" value="${obj.checkNum/obj.completeNum}" maxFractionDigits="2"/>
							</c:if>
						</td>
						<td style="padding-left: 0px;text-align: center">${obj.rotationNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.lunzhuanNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.chukeNum}</td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="detailSearch('${obj.doctorFlow}');" class="btn">查看</a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty rltLst}">
					<tr>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;" colspan="12">无数据记录！</td>
					</tr>
				</c:if>
			</table>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(rltLst)}" scope="request"></c:set>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>
</div>
</body>
</html>

