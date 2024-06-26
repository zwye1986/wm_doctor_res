<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function toZYYS(userFlow , userName , roleFlow , orgCode , uri){
	        if(!userFlow){
	        	jboxTip("请先登录！");
	        	return false;
	        }else if(!roleFlow){
	        	jboxTip("您没有权限！");
	        	return false;
	        }else{
	        	var url = uri+"?userFlow="+userFlow+"&userName="+encodeURI(userName)+"&roleFlow="+roleFlow+"&orgCode="+orgCode;
	        	window.location.href= url;
	        }        
  	}
	
	$(document).ready(function(){
		$("#${param.id}").addClass("nav_a");
	});
</script>
<div class="header_box">
	<div class="header">
		<div class="wx_logo"><img src="<s:url value='/'/>jsp/inx/wxwsj/images/logo.png"></div>
       <!-- 
       <a class="houtai" href="<s:url value='/main/srm'/>">后台管理</a>
        --> 
        <a class="welcome">您好，欢迎来到无锡市卫生和计划生育委员会科教管理平台</a>
    </div>
</div>

<div class="nav">
	<a id="wzsy" href="<s:url value='/inx/wxwsj?id=wzsy'/>">网站首页</a>
    <a id="xwzx" href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM01&id=xwzx'/>">新闻中心</a>
    <a id="tzgg" href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM03&id=tzgg'/>">通知公告</a>
    <a id="kygl" href="<s:url value='/main/srm'/>">科研管理</a>
    <a id="zyys" href="javascript:void(0);" onclick="toZYYS('${sessionScope.currUser.userFlow}' , '${sessionScope.currUser.userName}', '${eduRoleFlow}' , '${sessionScope.currOrg.orgCode}' , '${sysCfgMap['srm_zyys_url']}');">住院医师</a>
    <a id="mlzy" href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM05&isWithBlobs=${GlobalConstant.FLAG_Y}&id=mlzy'/>">名医</a>
    <a id="zcfg" href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM04&id=zcfg'/>">政策法规</a>
    <a id="xzzx" href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM02&id=xzzx'/>">下载中心</a>
</div>
