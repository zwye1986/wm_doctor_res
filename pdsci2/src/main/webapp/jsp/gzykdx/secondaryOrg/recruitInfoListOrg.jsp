<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
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
		function search(){
			$("#serchForm").submit();
		}
		function toPage(page){
			if(page){
				$("#currentPage").val(page);
				search();
			}
		}
		function detail(orgFlow,param,researchAreaId,speId){
			jboxOpen("<s:url value='/gzykdx/orgAudit/recruitDetail'/>?orgFlow="+orgFlow+"&degreeTypeId="+param+"&year=${param.year}"+"&researchAreaId="+researchAreaId+"&speId="+speId, "复试考生信息",1000,600);
		}
		function detail2(orgFlow,param,researchAreaId,speId){
			jboxOpen("<s:url value='/gzykdx/orgAudit/vacanciesDetail'/>?orgFlow="+orgFlow+"&degreeTypeId="+param+"&year=${param.year}"+"&researchAreaId="+researchAreaId+"&speId="+speId, "缺额详情",1000,600);
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/orgAudit/vacanciesInfoList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="recruitYear" value="${(empty param.recruitYear)?recruitYear:param.recruitYear}" class="xltext"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"	style="width:137px;">
							专业名称：
							<select name="speId" style="width:137px;" class="xlselect">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
									<option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
								</c:forEach>
							</select>
							研究方向：
							<input type="text" name="researchDirection" value="${param.researchDirection}" style="width:137px;" class="xltext"/>
						<%--<select name="researchDirectionId" class="xlselect" style="width:137px;">--%>
								<%--<option value="">请选择</option>--%>
								<%--<c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">--%>
									<%--<option--%>
											<%--<c:if test="${param.researchDirectionId eq dict.dictId }">selected="selected"</c:if>--%>
											<%--value="${dict.dictId }">${dict.dictName }</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
							学位类型：
							<select name="degreeTypeId" class="xlselect" style="width:137px;">
								<option value="">请选择</option>
								<c:forEach items="${gzykdxDegreeTypeEnumList }" var="dict">
									<option
											<c:if test="${param.degreeTypeId eq dict.id }">selected="selected"</c:if>
											value="${dict.id }">${dict.name }</option>
								</c:forEach>
							</select>
							<input type="button" value="查&#12288;询" class="search" onclick="search()">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th>年份</th>
				<th>专业代码</th>
				<th>学位类型</th>
				<th>专业名称</th>
				<th style="max-width: 200px;">研究方向</th>
				<th>计划指标</th>
				<th>上线人数</th>
				<th>缺额人数</th>
			</tr>
			<c:forEach items="${vacanciesInfoList}" var="info">
				<tr>
					<td>${info['RECRUIT_YEAR']}</td>
					<td>${info['SPE_ID']}</td>
					<td>${info['DEGREE_TYPE_NAME']}</td>
					<td>${info['SPE_NAME']}</td>
					<td style="max-width: 200px;">${info['RESEARCH_AREA_NAME']}</td>
					<td>${info['PLANQTY']}</td>
					<td><a onclick="detail('${info['ORG_FLOW']}','${info['DEGREE_TYPE_ID']}','${info['RESEARCH_AREA_ID']}','${info['SPE_ID']}')" style="cursor: pointer">
							${info['USEQTY']}</a></td>
					<td><a onclick="detail2('${info['ORG_FLOW']}','${info['DEGREE_TYPE_ID']}','${info['RESEARCH_AREA_ID']}','${info['SPE_ID']}')" style="cursor: pointer">
							${info['PLANQTY']-info['USEQTY']}</a>
							</td>
				</tr>
			</c:forEach>
			<c:if test="${empty vacanciesInfoList}">
				<tr>
					<td colspan="8">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<c:if test="${not empty vacanciesInfoList}">
			<c:set var="pageView" value="${pdfn:getPageView2(vacanciesInfoList , 10)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</c:if>
	</div>
</div>

</body>
</html>