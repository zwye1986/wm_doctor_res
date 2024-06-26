<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
	function search(){
		$("#headScoreForm").submit();
	}
	
	$(function(){
		$(".formatDouble").each(function(){
			$(this).text(parseFloat($(this).text()).toFixed(1));
		});
	});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="headScoreForm" action ="<s:url value='/res/teacher/headScore'/>" >
				<div class="queryDiv">
					<%--<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>
						<input type="text" class="qtext" value="${sessionScope.currUser.deptName}" readonly="readonly">
					</div>--%>
					<div class="inputDiv">
						<label class="qlable">科主任：</label>
						<input type="text" class="qtext" value="${sessionScope.currUser.userName}" readonly="readonly">
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
						</select>
					</div>
					<div class="qcheckboxDiv">
						&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y || param.sessionNumber == null?"checked":""} />
							当前轮转科室
						</label>
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
				</div>
			</form>
		</div>
		
		<table class="xllist">
			<tr style="display: none;"><td></td></tr>
			<thead>
				<tr>
					<th style="text-align: left;padding-left: 20px;width: 600px;max-width: 600px;min-width: 600px;" >评价指标</th>
					<c:forEach items="${recExtList}" var="recExt">
						<th style="width: 80px;max-width: 80px;min-width: 80px;">${recExt.operUserName}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:set value="0" var="count"/>
				<c:forEach items="${titleFormList}" var="title">
					<tr>
						<th style="text-align: left;padding-left: 20px;" colspan="${recExtList.size()+1}">${title.name}</th>
					</tr>
					<c:forEach items="${title.itemList}" var="item">
						<tr>
							<td style="text-align: left;padding-left: 20px;">${item.name}</td>
							<c:forEach items="${recExtList}" var="recExt">
								<c:set value="${recExt.schDeptFlow}${recExt.operUserFlow}" var="key"/>
								<td>${scoreMap[key][item.id]['score']}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</c:forEach>
				<tr style="background-color: #FCF1C0">
					<td style="text-align: left;padding-left: 20px;">总分</td>
					<c:forEach items="${recExtList}" var="recExt">
						<c:set value="${recExt.schDeptFlow}${recExt.operUserFlow}" var="key"/>
						<td>
							<font class="formatDouble">${scoreMap[key]['totalScore']+0}</font>
						</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>