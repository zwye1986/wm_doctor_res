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
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_mask" value="true"/>
	</jsp:include>

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
	</style>
	<script type="text/javascript">

		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			jboxPostLoad("clinicalList" ,"<s:url value='/eval/evalOrgQuery/showOrgClinicalList'/>" ,$("#clinicalForm").serialize() , true);
		}
		function toPage(page) {
			search();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<form  id="clinicalForm" method="post">
			<input type="hidden" id="evalYear" name="evalYear" value="${param.evalYear}"/>
			<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>

			<div class="queryDiv" style="min-width: 1000px;">
				<div class="inputDiv">
					<label class="qlable">专业基地：</label>
					<select name="speId" class="qselect">
						<option value=""></option>
						<c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
						<c:forEach items="${applicationScope[dictName] }" var="dict">
							<option value="${dict.dictId }">${dict.dictName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="lastDiv">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
				</div>
			</div>
		</form>
		<div class="resultDiv" style="min-width: 1000px;max-height: 420px;overflow: auto;" id="clinicalList">

		</div>
	</div>
</div>

</body>
</html>