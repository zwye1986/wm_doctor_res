<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['jx_top_name']}</title>
<jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
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
	window.location.href="<s:url value='/zseyjxres/hospital/home'/>?currentPage="+$("#currentPage").val();
}
/**
 * 报名审核
 */
function audit(status){
    var data="status="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/zseyjxres/head/audit'/>",data,true);
}

/**
 * 学员录取
 */
function _recruitDoctor(status){
    var data="status="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/zseyjxres/head/_recruitDoctor'/>",data,true);
}

</script>
</head>
<body>
    <div style="overflow:auto;" id="indexBody">
        <div class="bd_bg">
            <div class="<%--yw--%>">
                <jsp:include page="/jsp/zseyjxres/head_head.jsp" flush="true"/>
                <div class="body">
                    <div class="container">
                        <div class="content_side">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>对外进修管理
                                </dt>
                                <dd class="menu_item"><a onclick="audit('${regStatusEnumHeadPassing.id}');">报名审核</a></dd>
                                <%--<dd class="menu_item"><a onclick="_recruitDoctor('${stuStatusEnumPassed.id}');">学员录取</a></dd>--%>
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

                                <script>
                                    //打印录取通知书
                                    function printRecruit(resumeFlow) {
                                        jboxTip("打印中,请稍等...");
                                        var url = '<s:url value="/zseyjxres/doctor/recruitNotice?resumeFlow="/>' + resumeFlow;
                                        window.location.href = url;
                                    }
                                    function viewMsg(dl, msgFlow) {
                                        var isSelect = dl.className.indexOf("msgselect") < 0;
                                        $(".msgselect").removeClass("msgselect").find("dd").addClass("none");
                                        if (isSelect) {
                                            $(dl).addClass("msgselect").find("dd").removeClass("none");
                                        }
                                        if (dl.className.indexOf("readed") < 0) {
                                            jboxPost("<s:url value='/sczyres/doctor/readMsg'/>?msgFlow=" + msgFlow + "&receiveFlag=${GlobalConstant.FLAG_Y}", null, function (resp) {
                                                if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                                                    $(dl).addClass("readed");
                                                    var msgNum = parseInt($("#newMsg").text());
                                                    $("#newMsg").text(--msgNum);
                                                }
                                            }, null, false);
                                        }
                                        ;
                                    }
                                </script>
                                <div class="index_form" style="margin-bottom: 10px;">
                                    <h3>
                                        通知中心
                                        <%--<c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">--%>
                                        <%--<span>--%>
                                            <%--<a onclick="printRecruit('${stuUser.resumeFlow}')"--%>
                                               <%--style="color: #f60;text-decoration: underline">下载录取通知书</a>--%>
                                        <%--</span>--%>
                                        <%--</c:if>--%>
                                    </h3>

                                    <div class="main_bd">
                                        <div id="notification" class="notificationCenterPage">
                                            <c:forEach items="${msgList}" var="msg">
                                                <dl class="notify_item <c:if test="${msg.receiveFlag eq GlobalConstant.FLAG_Y}"> readed</c:if>"
                                                    onclick="viewMsg(this,'${msg.msgFlow}');">
                                                    <dt>
                                                        <a class="notify_title_wrapper">
                                                        <span class="notify_status">
                                                            <span class="notify_time">
                                                                    ${pdfn:transDate(msg.msgTime)}
                                                            </span>
                                                            <i class="noticearrow"></i>
                                                        </span>
                                                        <span class="notify_title">
                                                            <i class="dot">●</i>
                                                            ${msg.msgTitle}
                                                        </span>
                                                        </a>
                                                    </dt>
                                                    <dd class="none">
                                                            ${msg.msgContent}
                                                    </dd>
                                                </dl>
                                            </c:forEach>
                                            <c:if test="${empty msgList}">
                                                <dl class="notify_item" style="text-align: left;padding-left: 50px;">
                                                    <dt>
                                                        暂无通知！
                                                    </dt>
                                                </dl>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty msgList}">
                                    <div class="page" style="padding-right: 40px;">
                                        <input id="currentPage" type="hidden" name="currentPage" value=""/>
                                        <c:set var="pageView" value="${pdfn:getPageView(msgList)}" scope="request"></c:set>
                                        <pd:pagination-sczyres toPage="toPage"/>
                                    </div>
                                </c:if>

                               <%-- <div class="index_form">
                                    <h3>系统公告</h3>
                                    <ul class="form_main">
                                        <c:forEach items="${messages}" var="msg">
                                            <li>
                                                <a>
                                                    <strong><a href="<s:url value='/inx/zseyjxres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle }</a>
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
                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/jsp/zseyjxres/foot.jsp"  flush="true"/>
    </div>
</body>
</html>
