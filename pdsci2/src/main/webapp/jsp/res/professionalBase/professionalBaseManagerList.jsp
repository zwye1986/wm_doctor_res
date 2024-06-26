<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
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
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
function addUser() {
	jboxOpen("<s:url value='/sys/user/editProfessionalBaseManagerNew'/>","新增用户信息", 900, 350);
}
function edit(userFlow) {
	jboxOpen("<s:url value='/sys/user/editProfessionalBaseManagerNew?userFlow='/>"+ userFlow,"编辑用户信息", 900, 400);
}
function deptCfg() {
	jboxOpen("<s:url value='/res/ProfessionalBase/deptsCfgNew'/>","专业科室配置", 900, 400);
}
function evalCfg() {
	jboxOpen("<s:url value='/res/ProfessionalBase/evalCfg'/>","专业基地评估配置", 900, 400);
}
function resetPasswd(userFlow){
	jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
		var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			//searchUser();			
		});		
	});
}

function activate(userFlow){
	jboxConfirm("确认解锁该用户吗？",function () {
		var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			setTimeout(function () {
			 searchUser();
			},1500);
		});
	});
}
function lock(userFlow){
	jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！",function () {
		var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			setTimeout(function () {
			  searchUser();
			},1500);
		});
	});
}
function searchUser(){
	jboxStartLoading();
	var url = "<s:url value="/res/ProfessionalBase/professionalBaseManagerList" />";
	jboxPostLoad("content", url, $("#searchForm").serialize(), false);
}
function searchDept(orgFlow,deptFlow) {
	if(orgFlow==""){
		return;
	}
	var url = "<s:url value='/sys/user/getDept'/>?orgFlow="+orgFlow+"&deptFlow="+deptFlow;
	jboxGet(url,null,function(resp){
		$("#deptSelectId").html(resp);
	},null,false);
}
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	searchUser();
}
function exportExl() {
    var url = "<s:url value='/res/ProfessionalBase/exportProfessionalBaseManagerList'/>";
    jboxTip("导出中…………");
    jboxExp($("#searchForm"), url);
}
/**
*模糊查询加载
*/
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		/* clickActive:function(flow){
			$("#studyUserFlow").val(flow).change();
		} */ 
	});
});

</script>
</head>
<div class="main_hd">
	<h2 class="underline">专业基地管理</h2>
</div>
<div class="main_bd" id="div_table_0">
	<div class="div_search" style="padding-left: 40px;">
				 <form id="searchForm" action="<s:url value="/res/ProfessionalBase/professionalBaseManagerList" />" method="post">
					 <input id="currentPage" type="hidden" name="currentPage" value=""/>
					 <table style="width: 100%;">
						 <tr>
							 <td style="text-align: right">专业基地：</td>
							 <td>
								 <input id="orgSel" class="toggleView input" type="text" name="resTrainingSpeName" value="${param.resTrainingSpeName}" autocomplete="off"/>
								 <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:100px;text-align: left;">
									 <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
										 <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
											 <c:if test="${spe.dictId ne '50'}">
												 <p class="item" flow="${spe.dictId}" value="${spe.dictName}" style="height: 25px;padding-left: 10px;">${spe.dictName}</p>
											 </c:if>
										 </c:forEach>
									 </div>
								 </div>
							 </td>
							 <td style="text-align: right">负&ensp;责&ensp;人：</td>
							 <td>
								 <input type="text" name="userName" value="${param.userName}"  class="input"/>
							 </td>
							 <td style="text-align: right">用户状态：</td>
							 <td>
								 <input id="all" name="statusId" type="radio" value="" onclick="searchUser();"
										<c:if test='${empty param.statusId}'>checked="checked"</c:if>>
								 <label for="all">全部</label>&#12288;
								 <input id="${userStatusEnumActivated.id}" name="statusId" type="radio" value="${userStatusEnumActivated.id}" onclick="searchUser();"
										<c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
								 <label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
								 <input id="${userStatusEnumLocked.id}" name="statusId" type="radio" value="${userStatusEnumLocked.id}" onclick="searchUser();"
										<c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
								 <label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>
							 </td>
						 </tr>
						 <tr>
							 <td style="text-align: right">登&ensp;录&ensp;名：</td>
							 <td>
								 <input type="text" name="userCode" value="${param.userCode}" class="input"/>
							 </td>
							 <td colspan="4">
								 <input type="button" class="btn_green" onclick="searchUser();" value="查&#12288;询">
								 <input type="button" class="btn_green" onclick="addUser();" value="新&#12288;增">
								 <input type="button" class="btn_green" onclick="deptCfg();" value="科室配置">
								 <input type="button" class="btn_green" onclick="exportExl();" value="导&#12288;出">
							 </td>
						 </tr>
					 </table>
				</form>
			</div>
	<div class="div_search" style="padding-left: 40px;">
	<table class="grid">
		<tr>
			<th>负责人</th>
			<th>登录名</th>
			<th>性别</th>
			<th>手机号码</th>
			<th>职务</th>
			<th>专业基地</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${sysUserList}" var="user">
			<tr>
				<td>${user.userName}</td>
				<td>${user.userCode}</td>
				<td>${user.sexName}</td>
				<td>${user.userPhone}</td>
				<td>${user.postName}</td>
				<td>${user.resTrainingSpeName}</td>
				<td>${user.statusDesc}</td>
				<td>
					<c:if test="${user.statusId==userStatusEnumActivated.id}">
						[<a href="javascript:edit('${user.userFlow}');" >编辑</a>] |
						[<a href="javascript:resetPasswd('${user.userFlow}');" >重置密码</a>] |
						[<a href="javascript:lock('${user.userFlow}');" >停用</a>]
					</c:if>
					<c:if test="${user.statusId==userStatusEnumLocked.id}">
						[<a href="javascript:activate('${user.userFlow}');" >启用</a>]
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty sysUserList}">
			<tr>
				<td colspan="99">无记录</td>
			</tr>
		</c:if>
	</table>
	</div>
	<div class="page" style="padding-right: 40px;height: 32px;">
		<c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
</html>