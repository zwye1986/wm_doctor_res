<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" width="100%" style="margin-bottom: 10px;margin-top: 10px;">
			<tr>
				<td>
					医师：${doctor.doctorName}
					&#12288;
					轮转方案：${doctor.rotationName}
					<c:if test="${not empty doctor.secondRotationName}">
						&#12288;
						二级轮转方案：${doctor.rotationName}
					</c:if>
				</td>
			</tr>
		</table>
		<table class="basic list" width="100%">
			<tr>
				<th width="25%">轮转科室</th>
				<th width="10%">轮转时长</th>
				<th width="35%">轮转时间</th>
				<th width="30%">轮转状态</th>
			</tr>
			<c:forEach items="${arrResultList}" var="result">
				<tr>
					<td>${result.schDeptName}</td>
					<td>${result.schMonth+0}${applicationScope[unitKey].name}</td>
					<td>${result.schStartDate} ~ ${result.schEndDate}</td>
					<c:set value="${resultMap[result.resultFlow].schFlag}" var="sch"></c:set>
					<c:set value="${resultMap[result.resultFlow].isCurrentFlag}" var="current"></c:set>
					<td>
						<c:if test="${sch eq 'N' && current eq 'N'}">未入科</c:if>
						<c:if test="${sch eq 'Y' && current eq 'N'}">已出科</c:if>
						<c:if test="${sch eq 'N' && current eq 'Y'}">轮转中</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty arrResultList}">
				<tr><td colspan="20" style="text-align: center">暂无轮转计划!</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>