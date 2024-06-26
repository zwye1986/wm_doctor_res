<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="<s:url value='/jsp/jsres/hospital/monthlyReportNew/css/common.css'/>">
<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
<title>江苏西医住培平台统计报表</title>

<script>
    $(function(){
        jboxLoad("content0","<s:url value='/jsres/monthlyReportGlobalNew/content0'/>?monthDate=${currentMonth}",false);
    })

    function content0(obj){
        var text = $(obj).text();
        var monthDate = text.substr(0,4)+"-"+text.substr(5,2);
        jboxLoad("content0","<s:url value='/jsres/monthlyReportGlobalNew/content0'/>?monthDate="+monthDate,false);
    }
</script>
</head>
<body>
<div id="indexBody">
<div class="bd_bg">
<div class="yw">
<div class="head">
   <div class="head_inner">
     <h1 class="logo">
		 <a><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName}</h2>
	 </div>
   </div>
 </div>
 <div class="body">
   <div class="container">
	 <div class="col_main" id="content">
       <div class="content_main">
           <div class="content1 clearfix">
               <div class="tab1" id="tab">
                   <ul>
                       <c:forEach items="${resultMonths}" var="month" varStatus="s">
                           <li
                            <c:if test="${s.first}">class="choose" </c:if>
                           >
                               <a onclick="content0(this)">${month}</a>
                           </li>
                       </c:forEach>
                   </ul>
               </div>
               <div class="tab-content" id="content0">

               </div>
           </div>
       </div>
     </div>
   </div>
 </div>
</div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
  </c:if>
 <div class="foot">
   <div class="foot_inner">
       主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>

</div>
</div>
</body>
</html>
