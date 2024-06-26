<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<style type="text/css">
	.selSysDept span{color: red;}
</style>

<script type="text/javascript">

	$(document).ready(function(){
		search(1);
	});

	function search(){
		jboxPostLoad("spePracticeDiv" ,"<s:url value='/sys/spePractice/list'/>" ,$("#doctorSearchForm").serialize() , true);
	}

	function changeTrainSpes(t){
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			$(this).clone().appendTo($("#trainingSpeId"));
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="doctorSearchForm" method="post">
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlabel">培训类别：</label>
					<select name="trainingTypeId" id="trainingTypeId" onchange="changeTrainSpes('1');"class="qselect" >
						<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlabel">培训专业：</label>
					<select name="trainingSpeId" id="trainingSpeId"class="qselect" >
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="lastDiv">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="search(1);"/>
				</div>
			</div>
		</form>
		<div id="spePracticeDiv" class="resultDiv">
			
		</div>
	</div>
	<div style="display: none;">
		<select id="WMFirst_select">
			<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="WMSecond_select">
			<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="AssiGeneral_select">
			<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
</body>
</html>