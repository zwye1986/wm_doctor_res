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
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			jboxPostLoad("userList" ,"<s:url value='/sys/gateuser/userList'/>" ,$("#doctorSearchForm").serialize() , true);
		}
		function clearOrgFlow(){
			$("#orgFlow").val("");
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

		function add(userFlow) {
			var msg="编辑用户信息";
			if(userFlow=="")
			{
				msg="新增用户信息";
			}
			jboxOpen("<s:url value='/sys/gateuser/edit?userFlow='/>"+ userFlow,msg, 400, 400);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="doctorSearchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlable">用&nbsp;户&nbsp;名：</label>
					<input type="text" name="userCode" class="qtext"  >
				</div>
				<div class="qcheckboxDiv" style="min-width: 350px;padding-left: 23px;">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
					<input type="button" value="新&#12288;增" class="searchInput" onclick="add('');"/>
				</div>
			</div>
		</form>
		<div id="userList" class="resultDiv">

		</div>
	</div>
</div>
</body>
</html>