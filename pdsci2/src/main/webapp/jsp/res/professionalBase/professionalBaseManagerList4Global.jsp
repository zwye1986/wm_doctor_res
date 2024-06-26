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
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
function addUser() {
	jboxOpen("<s:url value='/sys/user/editProfessionalBaseManager'/>","新增用户信息", 900, 350);
}
function edit(userFlow) {
	jboxOpen("<s:url value='/sys/user/editProfessionalBaseManager?userFlow='/>"+ userFlow,"编辑用户信息", 900, 400);
}
function deptCfg() {
	jboxOpen("<s:url value='/res/ProfessionalBase/deptsCfg'/>","专业科室配置", 900, 400);
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
			searchUser();			
		});
	});
}
function lock(userFlow){
	jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！",function () {
		var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			searchUser();			
		});		
	});
}
function searchUser(){
	jboxStartLoading();
	$("#searchForm").submit();
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
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				 <form id="searchForm" action="<s:url value="/res/ProfessionalBase/professionalBaseManagerList" />" method="post">
					 <input id="currentPage" type="hidden" name="currentPage" value=""/>
					 <input type="hidden" name="role" value="${role}"/>
					 <div class="queryDiv" style="max-width: 880px;min-width: 880px;">
						 <div class="inputDiv">
							 <label class="qlable">专业基地：</label>
							 <input id="orgSel" class="toggleView qtext" type="text" name="resTrainingSpeName" value="${param.resTrainingSpeName}" autocomplete="off"/>
							 <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:100px;text-align: left;">
								 <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
									 <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
										 <p class="item" flow="${spe.dictId}" value="${spe.dictName}" style="height: 25px;padding-left: 10px;">${spe.dictName}</p>
									 </c:forEach>
								 </div>
							 </div>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">负&ensp;责&ensp;人：</label>
							 <input type="text" name="userName" value="${param.userName}"  class="qtext "/>
						 </div>
						 <div class="inputDiv" style="max-width: 288px;min-width: 272px;">
							 <label class="qlable">用户状态：</label>
							 <input id="all" name="statusId" type="radio" value="" onclick="searchUser();"
									<c:if test='${empty param.statusId}'>checked="checked"</c:if>>
							 <label for="all">全部</label>&#12288;
							 <input id="${userStatusEnumActivated.id}" name="statusId" type="radio" value="${userStatusEnumActivated.id}" onclick="searchUser();"
									<c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
							 <label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
							 <input id="${userStatusEnumLocked.id}" name="statusId" type="radio" value="${userStatusEnumLocked.id}" onclick="searchUser();"
									<c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
							 <label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">培训基地：</label>
							 <select name="orgFlow" class="qselect" >
								 <option value="">全部</option>
								 <c:forEach items="${orgs}" var="org">
									 <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								 </c:forEach>
							 </select>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">
								 邮&#12288;&#12288;箱：
							 </label>
							 <input type="text" name="userEmail" value="${param.userEmail}" class="qtext"/>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">登&ensp;录&ensp;名：</label>
							 <input type="text" name="userCode" value="${param.userCode}"  class="qtext "/>
						 </div>
					 </div>
					 <div class="funcDiv">
						<input type="button" class="searchInput" onclick="searchUser();" value="查&#12288;询">
						<input type="button" class="searchInput" onclick="exportExl();" value="导&#12288;出">
					 </div>
				</form>
			</div>
	<table class="xllist" style="min-width: 999px;">
		<tr>
			<th>负责人</th>
			<th>登录名</th>
			<th>性别</th>
			<th>手机号码</th>
			<th>职务</th>
			<th>邮箱</th>
			<th>专业基地</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${sysUserList}" var="user">
			<tr>
				<td>${user.userName}</td>
				<td>${user.userCode}</td>
				<td>${user.sexName}</td>
				<td>${user.userPhone}</td>
				<td>${user.postName}</td>
				<td>${user.userEmail}</td>
				<td>${user.resTrainingSpeName}</td>
				<td>${user.statusDesc}</td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
	<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>