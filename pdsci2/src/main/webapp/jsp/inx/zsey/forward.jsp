<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script type="text/javascript">
function countDown(secs,surl){     
	 var jumpTo = document.getElementById('jumpTo');
	 jumpTo.innerHTML=secs;  
	 if(--secs>0){     
	     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
	     }     
	 else{       
	     location.href=surl;     
	     }     
	 }   
</script>
<style>
html{overflow:hidden;}
</style>
</head>

<body id="indexBody" style="overflow-y:hidden;">
  <div class="header_bg">
    <div class="header_inner">
      <div class="header_logo">
        <img src="<s:url value='/jsp/hbres/images/basic/logo.png'/>" />
      </div>
    </div>
  </div>
  
  <div class="wrapper" style="overflow: hidden;">
    <div class="news_contain">
      <img src="<s:url value='/jsp/hbres/images/basic/Upgrade.png'/>" style="float:left; margin-top:100px;" />
      <div class="Upgrade" style="text-align: center;vertical-align: middle;margin-top: 200px;">
        <h1>系统将于
	    <span id="jumpTo">5</span>秒后自动跳转到
	    &#12288;<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/inx/hbres" title="湖北省住院医师规范化培训招录系统">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/inx/hbres</a>
	    <br/><br/>为方便您下次登录，请重新收藏新地址
        <script type="text/javascript">countDown(5,'${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/inx/hbres');</script>  
        </h1>
      </div>
    </div>
  </div>
    
  
  <div class="footer">
  	技术支持：
     <a style="color: #fff" href="http://www.njpdxx.com/" target="_blank">南京品德网络信息技术有限公司</a>
  </div>

</body>
</html>
