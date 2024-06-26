<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function toZYYS(userFlow,resFlag){
	        if(!userFlow){
	        	jboxTip("请先登录！");
	        	return false;
	        }else if(resFlag=="false"){
	        	jboxTip("您没有权限！");
	        	return false;
	        }else{
	        	var url = "<s:url value='/main/res'/>"
	        	window.location.href= url;
	        }        
  	}
	
	$(document).ready(function(){
		$("#${param.id}").addClass("checkedNav");
	});
	function toKYGL(userFlow,srmFlag){
		if(!userFlow){
        	jboxTip("请先登录！");
        	return false;
		}else if(srmFlag=="false"){
			jboxTip("您没有权限！");
			return false;
        }else{
        	var url = "<s:url value='/main/srm'/>"
        	window.location.href= url;
        }        
	}
	function toJXJY(userFlow,fstuFlag){
		if(!userFlow){
        	jboxTip("请先登录！");
        	return false;
		}else if(fstuFlag=="false"){
			jboxTip("您没有权限！");
			return false;
        }else{
        	var url = "<s:url value='/main/fstu'/>"
        	window.location.href= url;
        }        
	}
</script>

<div class="top">
	<img src="<s:url value='/'/>jsp/inx/yhwsj/images/logo.png" class="fl">
	<a class="fr">您好，欢迎来到余杭区卫计人事管理平台！</a>
</div>
<%-- 权限标识 --%>
<c:set var="resFlag" value='false'/>
<c:set var="srmFlag" value='false'/>
<c:set var="fstuFlag" value='false'/>
<%-- 住培系统权限 --%>
<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_global_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_charge_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_admin_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_manager_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_head_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_secretary_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_teacher_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_tutor_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['res_doctor_role_flow'] ,sessionScope.currRoleList)}">
	<c:set var="resFlag" value='true'/>
</c:if>
<%-- 科研系统权限 --%>
<c:if test="${pdfn:contain(applicationScope.sysCfgMap['srm_global_manager_role'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['srm_local_manager_role'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['srm_project_leader_role'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['srm_expert_reviewer_role'] ,sessionScope.currRoleList)}">
	<c:set var="srmFlag" value='true'/>
</c:if>
<%-- 继教系统权限 --%>
<c:if test="${pdfn:contain(applicationScope.sysCfgMap['fstu_global_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['fstu_local_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['fstu_head_role_flow'] ,sessionScope.currRoleList) ||
		pdfn:contain(applicationScope.sysCfgMap['fstu_doctor_role_flow'] ,sessionScope.currRoleList)}">
	<c:set var="fstuFlag" value='true'/>
</c:if>
<div class="nav">
	<ul id="navUl">
    	<li id="wzsy"><a class="no_01" href="<s:url value='/inx/yhwsj?id=wzsy'/>">网站首页</a></li>
        <li id="tzgg"><a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM03&id=tzgg'/>">通知公告</a></li>
        <li id="xzzx"><a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM02&id=xzzx'/>">下载中心</a></li>
        <li id="kygl"><a href="javascript:void(0);" onclick="toKYGL('${sessionScope.currUser.userFlow}','${srmFlag}')">科研管理</a></li>
        <li id="zyys"><a href="javascript:void(0);" onclick="toZYYS('${sessionScope.currUser.userFlow}','${resFlag}')">住院医师</a></li>
        <li id="jxjy"><a href="javascript:void(0);" onclick="toJXJY('${sessionScope.currUser.userFlow}','${fstuFlag}')">继续教育</a></li>
        <li id="rsgl"><a href="#">人事管理</a></li>
        <li id="zxks"><a class="no_02" href="http://192.26.23.23:8023/Login.aspx" target="_blank">在线考试</a></li>
     </ul>
</div>
