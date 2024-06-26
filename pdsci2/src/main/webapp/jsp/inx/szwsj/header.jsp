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
	<p>
		<c:if test="${not empty sessionScope.currUser}">
		</c:if>
	</p>
	<div id="banner">
	   <form action="#" method="post">
	   <div id="logo" style="float: right;">
		   <!--  <input name="search" type="text" class="xltext" value="${param.search}"/>-->
		   <img src="<s:url value='/'/>jsp/inx/szwsj/images/portal/custer.png" style="padding-top: 0px;"/>
	   </div>
	   </form>
	</div>
	<div id="guide">
		<ul>
	       <li id="sy"><a href="<s:url value='/inx/szwsj?id=sy'/>">首页</a></li>
		   <li id="xwzx"><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM01&id=xwzx'/>">新闻中心</a></li>
		   <li id="tzgg"><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM03&id=tzgg'/>">通知公告</a></li>
	       <li><a href="<s:url value='/main/srm'/>">科研管理</a></li>
	       <li><a href="<s:url value='/main/srm'/>">学科与人才</a></li>
	       <!-- 和教育对接 -->
	       <c:set var="eduRoleFlow" value=""></c:set>
	       <!-- 市局 -->
	       <c:if test='${pdfn:contain("cb4df7a9ed90429ea36a664fed52338e" ,sessionScope.currRoleList)}'>
	           <c:set var="eduRoleFlow" value="cb4df7a9ed90429ea36a664fed52338e"></c:set>
	       </c:if>
	       <!-- 医院 -->
	       <c:if test='${pdfn:contain("fabbc4dceb914a4f80e54aa65fed3aa3" ,sessionScope.currRoleList)}'>
	           <c:set var="eduRoleFlow" value="fabbc4dceb914a4f80e54aa65fed3aa3"></c:set>
	       </c:if>
	       <li><a href="javascript:void(0);" onclick="toZYYS('${sessionScope.currUser.userFlow}' , '${sessionScope.currUser.userName}', '${eduRoleFlow}' , '${sessionScope.currOrg.orgCode}' , '${sysCfgMap['srm_zyys_url']}');">住院医师</a></li>
	       <li id="jxjy"><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM05&id=jxjy'/>">继续教育</a></li>
	       <li id="zcfg"><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM04&id=zcfg'/>">政策法规</a></li>
	       <li id="xzzx"><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM02&id=xzzx'/>">下载中心</a></li>
	    </ul>
	</div>