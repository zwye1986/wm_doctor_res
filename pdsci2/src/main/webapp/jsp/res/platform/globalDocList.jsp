<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
	<style type="text/css">
		.title{font-weight: 400;font-style: normal;font-size: 16px;letter-spacing: 2px;background: none;}
	</style>
<script type="text/javascript">
	function showHiddenInfo(exp){
		$(exp).parent().nextAll().toggle();
		$(exp).parents('tr').hide();
	}
	function hiddenInfo(exp){
		$(exp).parents('table').find('.showInfo').toggle();
		$(exp).parents('tr').hide();
	}
</script>
</head>
<body>
<div class="mainright">
		<table class="xllist" style="width:100%;">
			<colgroup>
					<col width="26%"/>
					<col width="14%"/>
					<col width="14%"/>
					<col width="14%"/>
					<col width="14%"/>
					<col width="18%"/>
			</colgroup>
			<tr>
				<c:if test="${seeFlag eq 'searchDoctorByJd'}">
					<th>培训基地</th>
				</c:if>
				<c:if test="${seeFlag eq 'searchDoctorBySpe'}">
					<th>专业</th>
				</c:if>
				<c:forEach items="${doctorTypeDicts}" var="docType">
					<th>${docType.dictName}</th>
				</c:forEach>
				<th>总人数</th>
			</tr>
			<c:forEach items="${jdDoctorMaps}" var="map" varStatus="num">
				<c:if test="${num.count<9}">
					<tr>
						<c:if test="${seeFlag eq 'searchDoctorByJd'}">
							<td>${map["orgName"]}</td>
						</c:if>
						<c:if test="${seeFlag eq 'searchDoctorBySpe'}">
							<td>${map["speName"]}</td>
						</c:if>
						<c:forEach items="${doctorTypeDicts}" var="docType">
							<td>${empty map[docType.dictId]?0:map[docType.dictId]}</td>
						</c:forEach>
						<td>${empty map["sumCount"]?0:map["sumCount"]}</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${jdDoctorMaps.size()>8}">
				<tr class="showInfo"><td  style="cursor: pointer" colspan="5" onclick="showHiddenInfo(this);">剩余${jdDoctorMaps.size()-8}条数据未展示，点击立即查看</td></tr>
			</c:if>
			<c:forEach items="${jdDoctorMaps}" var="map" varStatus="num">
				<c:if test="${num.count>8}">
					<tr class="showInfo" style="display: none;">
						<c:if test="${seeFlag eq 'searchDoctorByJd'}">
							<td>${map["orgName"]}</td>
						</c:if>
						<c:if test="${seeFlag eq 'searchDoctorBySpe'}">
							<td>${map["speName"]}</td>
						</c:if>
						<c:forEach items="${doctorTypeDicts}" var="docType">
							<td>${empty map[docType.dictId]?0:map[docType.dictId]}</td>
						</c:forEach>
						<td>${empty map["sumCount"]?0:map["sumCount"]}</td>
					</tr>
				</c:if>
			</c:forEach>
			<tr class="showInfo" style="display: none;" ><td  style="cursor: pointer" colspan="5" onclick="hiddenInfo(this);">收起</td></tr>
			<c:if test="${empty jdDoctorMaps}">
				<tr><td colspan="5" style="text-align: center;">无记录</td></tr>
			</c:if>
		</table>
</div>
</body>
</html>