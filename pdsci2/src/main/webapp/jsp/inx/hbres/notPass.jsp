<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function goLogin(){
		location.href="<s:url value='/inx/hbres'/>";
	}
	
	function reedit(){
		location.href="<s:url value='/inx/hbres/reedit'/>?userFlow=${doctor.doctorFlow}";
	}
</script>
</head>

<body>

<div class="yw">

   <jsp:include page="/jsp/hbres/head.jsp" flush="true">
     <jsp:param value="/inx/hbres" name="indexUrl"/>
     <jsp:param value="true" name="notShowAccount"/>
 </jsp:include>
  
  <div class="content">
      <div class="notPass">
      
         <div class="notPass_left">
   	 	 <img src="<s:url value='/jsp/hbres/images/notPass.jpg'/>"/>
   	 	 </div>
   	 	 <div class="notPass_right">
           <p>
              <img src="<s:url value='/jsp/hbres/images/info.png'/>"/>审核未通过原因：<br/>
	         &#12288;&#12288;&#12288;&#12288; ${doctor.disactiveReason}
           </p>
          	 
           <div>
          	 <input type="button" value="返回首页" class="button  button-blue" style="cursor: pointer;" onclick="goLogin();"/>
         	 <c:if test="${GlobalConstant.FLAG_Y eq doctor.reeditFlag}">
         		<input type="button" value="重新填写" class="button  button-blue" style="cursor: pointer;" onclick="reedit();"/>
         	 </c:if>
           </div>
         </div>
         
      </div>
          
  </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>
<jsp:include page="/jsp/hbres/foot.jsp"  flush="true"/>
</body>
</html>
