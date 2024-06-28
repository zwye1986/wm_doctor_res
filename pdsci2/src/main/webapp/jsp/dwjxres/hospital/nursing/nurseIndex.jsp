<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['jx_top_name']}</title>
<jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
$(document).ready(function(){
	$(".menu_item a").click(function(){
		$(".select").removeClass("select");
		$(this).addClass("select");
	});
	setBodyHeight();
});

function setBodyHeight(){
	if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
		if(navigator.appVersion.indexOf("MSIE 7.0")>-1){
			$("#indexBody").css("height",window.innerHeight+"px");
		}else if(navigator.appVersion.indexOf("MSIE 8.0")>-1){
			$("#indexBody").css("height",document.documentElement.offsetHeight+"px");
		}else{
			$("#indexBody").css("height",window.innerHeight+"px");
		}
    } else {
    	$("#indexBody").css("height",window.innerHeight+"px");
    }
}

onresize=function(){
	setBodyHeight();
}
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);
	}
	window.location.href="<s:url value='/dwjxres/nursing/home'/>?currentPage="+$("#currentPage").val();
}
/**
 * 报名审核
 */
function audit(status){
    var data="nursingStatusId="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/dwjxres/nursing/audit'/>",data,true);
}

/**
 * 学员查询
 */
 function queryStu(){
	 jboxLoad("content","<s:url value='/dwjxres/nursing/queryNurseMain'/>",true);
}

</script>
</head>
<body>
    <div style="overflow:auto;" id="indexBody">
        <div class="bd_bg">
            <div class="yw">
                <div class="head">
                    <div class="head_inner">
                        <h1 class="logo">
                            <a href="<s:url value='/dwjxres/nursing/home'/>">${sysCfgMap['jx_top_name']}</a>
                        </h1>
                        <c:if test="${not param.notShowAccount}">
                            <div class="account">
                                <h2 style="text-align: right;">您好，${!empty sessionScope.currUser.userName?sessionScope.currUser.userName:sessionScope.currUser.userCode }</h2>
                                <div class="head_right">
                                    <a href="<s:url value='/dwjxres/nursing/home'/>">首页</a>&#12288;
                                    <c:if test='${param.notice}'>
                                        <a onclick="msgToPage();">公告</a>&#12288;
                                    </c:if>

                                    <a href="<s:url value='/inx/dwjxres/logout?flag='/>${sysCfgMap['jx_templete_name']}">退出</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="body">
                    <div class="container">
                        <div class="content_side">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>对外进修管理
                                </dt>
                                <dd class="menu_item"><a onclick="audit('${regStatusEnumPassing.id}');">报名审核</a></dd>
                            </dl>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_statistics"></i>统计查询
                                </dt>
                                <dd class="menu_item"><a onclick="queryStu();">学员查询</a></dd>
                            </dl>
                        </div>
                        <div class="col_main" id="content">
                            <div class="content_main">
                                <div class="index_show">
                                    <div class="index_tap top_left">
                                        <ul class="inner">
                                            <li class="index_item index_blue">
                                                <a href="javascript:;" onclick="audit('${stuStatusEnumPassing.id}')">
                                            <span class="tap_inner">
                                                <i class="message"></i>
                                                <em class="number">${passingCount}</em>
                                                <strong  class="title">待审核</strong>
                                            </span>
                                                </a>
                                            </li>
                                            <li class="index_item index_blue">
                                                <a href="javascript:;" style="cursor: default;">
                                        <span class="tap_inner tab_second">
                                        <i class="people"></i>
                                        <em class="number">${singupCount}</em>
                                        <strong  class="title">已报名</strong>
                                        </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="index_tap top_right">
                                        <ul class="inner">
                                            <li class="index_item index_green">
                                                <a href="javascript:;" onclick="audit('${stuStatusEnumPassed.id}')">
                                            <span class="tap_inner tab_third">
                                                <i class="crowd"></i>
                                                <em class="number">${passedCount}</em>
                                                <strong  class="title">审核通过</strong>
                                            </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="index_form">
                                    <h3>系统公告</h3>
                                    <ul class="form_main">
                                        <c:forEach items="${messages}" var="msg">
                                            <li>
                                                <a>
                                                    <strong><a href="<s:url value='/inx/dwjxres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle }</a>
                                                        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.infoTime))<=7}">
                                                            <i class="new1"></i>
                                                        </c:if>
                                                    </strong>
                                                    <span>${pdfn:transDate(msg.infoTime)}</span>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="page" style="padding-right: 40px;">
                                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                                    <c:set var="pageView" value="${pdfn:getPageView(messages)}" scope="request"></c:set>
                                    <pd:pagination-dwjxres toPage="toPage"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/jsp/dwjxres/foot.jsp"  flush="true"/>
    </div>
</body>
</html>
