<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>安全中心</title>
<c:choose>
<c:when test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.source }">
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
</c:when>
<c:otherwise>
	<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
</c:otherwise>
</c:choose>
<script>
$(document).ready(function(){
	phoneAccFirst();
});

function phoneAccFirst(){
	reloadContent("<s:url value='/hbres/singup/user/phoneAccFirst?userFlow=${param.userFlow}'/>");
}

function reloadContent(url){
	jboxStartLoading();
	jboxLoad("mainContent",url,true);
	jboxEndLoading();
}
</script>
</head>
<body>
<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.source }">
<div class="main_wrap">
    <div class="user-contain">
</c:if>
        <div class="main_hd">
          <h2>修改手机号</h2>
        </div>
        <div class="main_bd">
          <div class="flowsteps" id="icn">
		  <ol class="num4">
	        <li class="first current" id="step1">
	          <span><i>1</i><em>验证身份</em></span>
	        </li>
	        <li id="step2">
	          <span><i>2</i><em>修改手机</em></span>
	        </li>
	        <li id="step3">
	          <span><i>3</i><em>发送手机验证码</em></span>
	        </li>
	        <li class="last" id="step4">
	          <span><i>4</i><em>完成</em></span>
	        </li>
	      </ol>
		</div>
		<div id="mainContent" style="padding-top: 10px">
	
		</div>
        </div>
<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.source }">
<div class="main_wrap">
    <div class="user-contain">
</c:if>
</body>
</html>
