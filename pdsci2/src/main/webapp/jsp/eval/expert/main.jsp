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
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			jboxPostLoad("userList" ,"<s:url value='/eval/expert/list'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

		function resetPasswd(userFlow){
			jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
				var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
				jboxGet(url,null,function(){
					//searchUser();
				});
			});
		}

		function add(userFlow) {
			if(userFlow==""){
				jboxOpen("<s:url value='/eval/expert/edit'/>?userFlow="+userFlow,"新增专家信息", 900, 400);
			}else{
				jboxOpen("<s:url value='/eval/expert/edit'/>?userFlow="+userFlow,"编辑专家信息", 900, 400);
			}
		}
		function activate(userFlow){
			jboxConfirm("确认启用该用户吗？",function () {
				var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
				jboxGet(url,null,function(resp){
					if(resp=="重置成功！") {
						resp="启用成功！";
					}
					jboxTip(resp);
					search();
				},null,false);
			});
		}
		function lock(userFlow){
			jboxConfirm("确认停用该用户吗？停用后该用户将不能登录系统！",function () {
				var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
				jboxGet(url,null,function(resp){
					if(resp=="锁定成功！") {
						resp="停用成功！";
					}
					jboxTip(resp);
					search();
				},null,false);
			});
		}

		//导出
		function exportExpert(){
			var url="<s:url value='/eval/expert/exportExpert'/>";
			jboxTip("导出中…………");
			jboxExp($("#searchForm"), url);
			jboxEndLoading();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<div class="queryDiv">
		<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<div class="inputDiv">
				<label class="qlable">姓&#12288;&#12288;名：</label>
				<input type="text"  name="userName" autocomplete="off" class="qtext"/>
			</div>
			<div class="inputDiv" style="min-width: 300px;max-width: 300px;">
				<label class="qlable">用户状态：</label>
				<input id="all" name="statusId" type="radio" value="" checked="checked">
				<label for="all">全部</label>&#12288;
				<input id="${userStatusEnumActivated.id}" name="statusId" type="radio" value="${userStatusEnumActivated.id}"
					   <c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
				<label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
				<input id="${userStatusEnumLocked.id}" name="statusId" type="radio" value="${userStatusEnumLocked.id}"
					   <c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
				<label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>&#12288;
			</div>
			<div class="lastDiv">
				<input type="button" class="searchInput"  onclick="toPage(1);" value="查&#12288;询">
			</div>
		</form>
		</div>
		<div class="funcDiv">
			<input type="button" class="searchInput"  onclick="add('');" value="新&#12288;增">
			<input type="button" class="searchInput"  onclick="exportExpert();" value="导&#12288;出">
		</div>
		<div id="userList" class="resultDiv">

		</div>
	</div>
</div>

</body>
</html>