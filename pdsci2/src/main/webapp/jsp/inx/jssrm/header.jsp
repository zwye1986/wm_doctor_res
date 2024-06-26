<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function toKYGL(userFlow){
	if(!userFlow){
    	jboxTip("请先登录！");
    	return false;
    }else{
    	var url = "<s:url value='/main/srm'/>"
    	window.location.href= url;
    }        
}
</script>

<div id="header">
<div class="header">
    <img src="<s:url value='/'/>jsp/inx/jssrm/images/logo.png"/>
    <div class="nav">
    	<ul id="navUl">
        	<li id="nav_" class="active"><a href="<s:url value='/inx/jssrm'/>">首&#12288;&#12288;页</a></li>
            <%-- <li id="nav_LM01"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=LM01'/>">新闻中心</a></li> --%>
            <li id="nav_LM03"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=LM03'/>">通知公告</a></li>
            <li><a href="javascript:void(0);" onclick="toKYGL('${sessionScope.currUser.userFlow}')">科研管理</a></li>
            <li id="nav_LM02"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=LM02'/>">下载中心</a></li> 
            <%-- <li id="nav_LM04"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=LM04'/>">政策法规</a></li> --%>
        </ul>
    </div>
   </div>
</div>