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
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
				<table class="xllist">
					<tr>
						<th width="7%">姓名</th>
						<th width="7%">类型</th>
						<th width="17%">详情</th>
						<th width="17%">时间</th>
						<th width="10%">轮转科室</th>
						<c:if test="${isSubmit}">
							<th width="10%">报送人</th>
						</c:if>
						<c:if test="${!isSubmit}">
							<th width="10%">带教老师</th>
							<th width="10%">科秘</th>
						</c:if>
					</tr>
					<c:forEach items="${kqList}" var="kq">
						<tr>
							<td>${kq.doctorName}</td>
							<td>${kq.qingpuKqTypeDetailName }</td>
							<td>${kq.doctorRemarks}</td>
							<td>
									${kq.startDate}~${kq.endDate}<br/>
								<font style="font-weight: bold;">
									(${kq.intervalDays}天)
								</font>
							</td>
							<td style="text-align: center; ">${kq.schDeptName}</td>
							<c:if test="${isSubmit}">
								<td>
									${kq.teacherName}${kq.headName}
								</td>
							</c:if>
							<c:if test="${!isSubmit}">
								<td>
									<a title="审核信息：${kq.teacherAuditInfo}">${kq.teacherName}</a>
								</td>
								<td>
									<a title="审核信息：${kq.headAuditInfo}">${kq.headName}</a>
								</td>
							</c:if>
						</tr>
					</c:forEach>
					<c:if test="${empty kqList}">
						<tr>
							<td colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
		</div>
	</div>
</div>
</body>
</html>