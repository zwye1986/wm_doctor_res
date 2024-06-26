<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
	</jsp:include>

	<style type="text/css">
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		.processFlag{width: 50px;float: left;height: 100%;}
		.processDept{width: 120px;padding-left: 10px;}
		.processDept .dept{font-size: 15px;color:#3d91d5;margin: 5px 0px;}
		.processDept .schScore{color:#5A5A5A;margin: 10px 0px 5px 0px;;}
		.headNmae{width: 170px;color: #A3A3A3;margin-top: 5px;line-height: 25px;}
	</style>
	<script type="text/javascript">
		//选科数据
		var selDeptFlows = {};
		var selMonths = {};
		//页面初始化
		$(function(){
			//阻止事件冒泡
			$("[onclick]").click(function(e){
				e.stopPropagation();
			});
		});

		//轮转方案说明
		function openDetail(rotationName, rotationFlow){
			var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
			jboxOpen(url, rotationName, 800, 500);
		}
	</script>
</head>

<body>
<div class="mainright">
	<div class="content">
		<div style="width: 100%;height: 40px;">
			<table class="basic" style="width: 100%;margin-top: 10px;margin-bottom: 10px;background-color: white;position: relative;">
				<tr>
					<td id="titleTd">
						姓名：<font style="font-weight: bold;">${doctor.doctorName}</font>
						&#12288;&#12288;&#12288;&#12288;
						轮转方案：<font style="font-weight: bold;">${doctor.rotationName}</font>
						&#12288;&#12288;&#12288;&#12288;
						培训年级：<font style="font-weight: bold;"><c:out value="${doctor.sessionNumber}" default="无"/></font>
						&#12288;&#12288;&#12288;&#12288;
						轮转年限：<font style="font-weight: bold;"><c:out value="${doctor.trainingYears}" default="无"/></font>
					</td>
				</tr>
			</table>
		</div>
		<div id="selDept">
			<table class="xllist nofix" style="width:100%;margin-top: 10px;margin-bottom: 10px;text-align: center;">
				<tr>
					<th>标准科室</th>
					<th>转轮科室</th>
					<th>转轮时间</th>
					<th>轮转时长</th>
				</tr>
				<c:forEach items="${results}" var="result">
					<tr>
						<td >${result.standardDeptName}</td>
						<td >${result.schDeptName}</td>
						<td >${result.schStartDate}~${result.schEndDate}</td>
						<td >${result.schMonth}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty results}">
					<tr>
						<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
					</tr>
				</c:if>
			</table>
		</div>

	</div>
</div>
</body>
</html>