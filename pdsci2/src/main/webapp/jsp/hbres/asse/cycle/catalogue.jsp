
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
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
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
</style>
<script type="text/javascript">
	$(function(){
		if("${empty param.recTypeId}"=="true"){
			$("li:first").click();
		}
	});
	function setType(flag,f,obj){
		$(".selectTag").removeClass("selectTag");
		$(obj).addClass("selectTag");
		$("#recTypeId").val(flag);
		$("#f").val(f);
		dataChange();
	}
	function dataChange(){
		jboxTip("数据加载中");
		jboxLoad("tagContent","<s:url value='/hbres/doctor/doctorRecruit/catalogue/detail'/>?"+$("#searchForm").serialize(),false);
	}
</script>
</head>
<body style="overflow: auto">
	<div class="main_hd" >
	    <div class="title1 clearfix">
			<ul id="tags">
				<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
					<c:set var="enumList" value="${registryTypeEnumList}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
					<c:set var="enumList" value="${practicRegistryTypeEnumList}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
					<c:set var="enumList" value="${theoreticalRegistryTypeEnumList}"></c:set>
				</c:if>
				<c:forEach items="${enumList}" var="registryType" varStatus="status">
					<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
						<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
						<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
						<c:set value="theoretical_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>

					<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
					&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
						<li class="${param.recTypeId eq registryType.id?'selectTag':''}" onclick="setType('${registryType.id}','1',this);"><a>${registryType.name}</a></li>
					</c:if>
				</c:forEach>
				<%--<c:if test="${typeId ne jszyTCMPracticEnumTheoreticalStudy.id}">--%>
					<%--<c:forEach items="${afterRecTypeEnumList}" var="eu">--%>
						<%--<c:set var="key" value="res_${eu.id}_form_flag"/>--%>
						<%--<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[key]--%>
					<%--&&pdfn:findChineseOrWestern(user.medicineTypeId,eu.id)}">--%>
							<%--<li class="${param.recTypeId eq eu.id?'selectTag':''}" onclick="setType('${eu.id}','2',this);"><a>${eu.name}</a></li>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
				<%--</c:if>--%>
			</ul>

				<div id="tagContent">

				</div>
	    </div>
	</div>
	<div class="div_search">
		<form id="searchForm" method="post">
			<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}">
			<input type="hidden" id="f" name="f" value="${param.f}">
			<input type="hidden" id="userFlow" name="userFlow" value="${param.userFlow}">
			<input type="hidden" id="processFlow" name="processFlow" value="${param.processFlow}">
		</form>
	</div>

</body>
</html>