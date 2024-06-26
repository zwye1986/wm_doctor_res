<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="basic" value="true"/>
</jsp:include>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/platform/monthlyReport/css/common.css'/>">
<%--<script type="text/javascript" src="<s:url value='/jsp/res/platform/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>--%>
<%--<script type="text/javascript" src="<s:url value='/jsp/res/platform/monthlyReport/js/common-web.js'/>"></script>--%>

<%--<link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>--%>
<%--<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<%--<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<%--<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<title>江苏西医住培平台统计报表</title>

<script>
    $(function(){
        jboxLoad("content0","<s:url value='/res/monthlyReportGlobal/content0'/>?monthDate=${currentMonth}",false);

        $("#tab a").on({
            click:function(){
                if(!$(this).parent("li").hasClass("choose")){
                    $("#tab a").parent("li").removeClass("choose");
                    $("#tab a").parent("li").removeAttr("style");
                    $(this).removeAttr("style");
                    $(this).parent("li").addClass("choose");
                }else{

                }
            },
            mouseover:function(){
                if(!$(this).parent("li").hasClass("choose")){
                    $(this).parent('li').css({
                        "margin-left":"10px",
                        "background":"#DAE9FF",
                    });
                    $(this).css("color","#597EF7")
                }

            },
            mouseout:function(){
                if(!$(this).parent("li").hasClass("choose")){
                    $(this).parent('li').css({
                        "margin-left":"15px",
                        "background":"#ddd"
                    });
                    $(this).css("color","#ffffff")
                }
            }
        })
    })

    function content0(obj){
        var text = $(obj).text();
        var monthDate = text.substr(0,4)+"-"+text.substr(5,2);
        jboxLoad("content0","<s:url value='/res/monthlyReportGlobal/content0'/>?monthDate="+monthDate,false);
    }
</script>
</head>
<body>
<%--<div id="indexBody">--%>
<%--<div class="bd_bg">--%>
<%--<div class="yw">--%>
<%--<div class="head">--%>
   <%--<div class="head_inner">--%>
     <%--<h1 class="logo">--%>
     <%--</h1>--%>
     <%--<div class="account">--%>
       <%--<h2 class="head_right">${sessionScope.currUser.orgName}</h2>--%>
	 <%--</div>--%>
   <%--</div>--%>
 <%--</div>--%>
 <%--<div class="body">--%>
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
 <%--</div>--%>
<%--</div>--%>
  <%--<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">--%>
		<%--<jsp:include page="/jsp/service.jsp"></jsp:include>--%>
  <%--</c:if>--%>
 <%--<div class="foot">--%>
   <%--<div class="foot_inner">--%>
       <%--主管单位：江苏省卫生和计划生育委员会科教处   |  技术支持：南京品德网络信息技术有限公司--%>
   <%--</div>--%>
 <%--</div>--%>

<%--</div>--%>
<%--</div>--%>
</body>
</html>
