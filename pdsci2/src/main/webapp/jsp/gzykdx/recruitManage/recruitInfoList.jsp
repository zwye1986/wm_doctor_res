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
		function detail(orgFlow,param){
			jboxOpen("<s:url value='/gzykdx/recruit/recruitDetail'/>?orgFlow="+orgFlow+"&degreeTypeId="+param+"&year=${param.year}", "录取详情",1000,600);
		}
		function detail2(orgFlow,param){
			jboxOpen("<s:url value='/gzykdx/recruit/vacanciesDetail'/>?orgFlow="+orgFlow+"&degreeTypeId="+param+"&year=${param.year}", "缺额详情",1000,600);
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/recruit/recruitInfoList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="year" value="${(empty param.year)?thisYear:param.year}" class="xltext"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"	>
							机构名称：
							<select class="xlselect" name="org" style="height: 27px;">
								<option></option>
								<c:forEach items="${orgList}" var="org">
									<option value="${org.orgFlow}"
											<c:if test="${org.orgFlow eq param.org}">selected</c:if>
									>${org.orgName}</option>
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
				<th>机构名称</th>
				<th>学术学位招生指标数</th>
				<th>学术学位已招指标数</th>
				<th>学术学位招生缺额数</th>
				<th>专业学位招生指标数</th>
				<th>专业学位已招指标数</th>
				<th>专业学位招生缺额数</th>
			</tr>
			<c:forEach items="${recruitInfoList}" var="info">
				<c:set var="key" value="${info['ORG_FLOW']}"></c:set>
				<tr>
					<td>${info['RECRUIT_YEAR']}</td>
					<td>${info['ORG_NAME']}</td>
					<td>${info['ACADEMIC_NUM']}</td>
					<td><a onclick="detail('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumAcademicType.id}')" style="cursor: pointer">
							${academicSumMap[key]}
					</a></td>
					<td><a onclick="detail2('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumAcademicType.id}')" style="cursor: pointer">
							${info['ACADEMIC_NUM']-academicSumMap[key]}
							</a></td>
					<td>${info['SPECIALIZED_NUM']}</td>
					<td><a onclick="detail('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumProfessionalType.id}')" style="cursor: pointer">
							${specializedSumMap[key]}
					</a></td>
					<td><a onclick="detail2('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumProfessionalType.id}')" style="cursor: pointer">
							${info['SPECIALIZED_NUM']-specializedSumMap[key]}
							</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty recruitInfoList}">
				<tr>
					<td colspan="8">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(recruitInfoList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>

</body>
</html>