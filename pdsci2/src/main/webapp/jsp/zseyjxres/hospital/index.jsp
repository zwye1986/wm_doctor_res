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
    jboxPostLoad("content","<s:url value='/zseyjxres/hospital/audit'/>",data,true);
}
/**
 * 学员录取
 */
function _recruitDoctor(status){
    var data="status="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/zseyjxres/hospital/_recruitDoctor'/>",data,true);
}
/**
 * 学员报到
 */
function recruitDoctor(){
    var data="status="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/zseyjxres/hospital/recruitDoctor'/>",data,true);
}

/**
 * 学员结业
 */
function graduationDoctor(status){
    var data="status="+status+"&currentPage=1";
    jboxPostLoad("content","<s:url value='/zseyjxres/hospital/graduationDoctor'/>",data,true);
}
/**
 * 外出进修登记
 */
function furtherStudyRegist(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/furtherStudyRegist?currentPage=1'/>",true);
}

/**
 * 外出进修查询
 */
function furtherStudyQuery(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/furtherStudyQuery?currentPage=1'/>",true);
}
/**
 * 评价指标维护
 */
function evaluationMaintenance(roleId) {
    if(!roleId){
        roleId = "${stuRoleIdDoctor.id}";
    }
    jboxLoad("content", "<s:url value='/zseyjxres/evaluation/evaluationMaintenance?roleId='/>" + roleId, false);
}

/**
 * 评价查询
 */
function evaluationQuery() {
    jboxLoad("content", "<s:url value='/zseyjxres/evaluation/evaluationSelect'/>", true);
}

/**
 * 招录概况
 */
 function recruitList(){
	 jboxLoad("content","<s:url value='/zseyjxres/hospital/orgRecruitInfo'/>",true);
}
/**
 * 学员查询
 */
 function queryStu(){
	 jboxLoad("content","<s:url value='/zseyjxres/hospital/queryStuMain'/>",true);
}
/**
 * 公告维护
 */
function notice(currentPage){
    if(!currentPage){
        currentPage = 1;
    }
    jboxLoad("content","<s:url value='/zseyjxres/hospital/noticemanage?currentPage='/>"+currentPage,true);
}


/**
 * 过程
 */
function resMain(){
    window.open("<s:url value='/zseyjxres/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");
}
/**
 * 科室维护
 */
function speMaintenance(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/speMaintenance?currentPage=1'/>",true);
}

/**
 * 科室账号维护
 */
function headMaintenance(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/headMaintenance?currentPage=1'/>",true);
}


function headInfo(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/headInfo?currentPage=1'/>",true);
}


/**
* 医院人员维护
*/
function staffManage(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/staffManage?currentPage=1'/>",true);
}

/**
 * 进修批次设置
 */
function batchSetting(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/searchSetting'/>",true);
}
/**
 * 国籍维护
 */
function nationalityMaintenance(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/national?currentPage=1'/>",true);
}

/**
 * 注册人员管理
 */
function registManage(){
    jboxLoad("content","<s:url value='/zseyjxres/hospital/registManage?currentPage=1'/>",true);
}
</script>
</head>
<body>
    <div style="overflow:auto;" id="indexBody">
        <div class="bd_bg">
            <div class="<%--yw--%>">
                <jsp:include page="/jsp/zseyjxres/head_hospital.jsp"/>
                <div class="body">
                    <div class="container">
                        <div class="content_side">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>对外进修管理
                                </dt>
                                <dd class="menu_item"><a onclick="audit('${regStatusEnumPassing.id}');">报名审核</a></dd>
                                <dd class="menu_item"><a onclick="_recruitDoctor('${stuStatusEnumPassed.id}');">学员录取</a></dd>
                                <dd class="menu_item"><a onclick="recruitDoctor('');">学员报到</a></dd>
                                <%--<dd class="menu_item"><a onclick="graduationDoctor('');">学员结业</a></dd>--%>
                            </dl>
                           <%-- <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>外出进修管理
                                </dt>
                                <dd class="menu_item"><a href="javascript:furtherStudyRegist();">外出进修登记</a></dd>
                                <dd class="menu_item"><a href="javascript:furtherStudyQuery();">外出进修查询</a></dd>
                            </dl>--%>
                            <%--<dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>评价管理
                                </dt>
                                <dd class="menu_item"><a href="javascript:evaluationMaintenance();">评价指标维护</a></dd>
                                <dd class="menu_item"><a href="javascript:evaluationQuery();">评价查询</a></dd>
                            </dl>--%>
                            <%--<dl class="menu">--%>
                                <%--<dt class="menu_title">--%>
                                    <%--<i class="icon_menu menu_statistics"></i>过程--%>
                                <%--</dt>--%>
                                <%--<dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>--%>
                            <%--</dl>--%>

                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_statistics"></i>统计查询
                                </dt>
                                <%--<dd class="menu_item"><a onclick="recruitList();">招录概况</a></dd>--%>
                                <dd class="menu_item"><a onclick="queryStu();">学员查询</a></dd>
                            </dl>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_setup"></i>设置
                                </dt>
                                <dd class="menu_item"><a onclick="notice();">公告维护</a></dd>
                                <%--<dd class="menu_item"><a onclick="speMaintenance();">科室</a></dd>--%>
                                <%--<dd class="menu_item"><a onclick="staffManage();">本院人员维护</a></dd>--%>
                                <dd class="menu_item"><a onclick="batchSetting();">进修批次设置</a></dd>
                                <dd class="menu_item"><a onclick="headInfo();">科室信息查询</a></dd>
                            <%--<dd class="menu_item"><a onclick="headMaintenance();">科主任账号</a></dd>--%>
                                <%--<dd class="menu_item"><a onclick="nationalityMaintenance();">国籍维护</a></dd>--%>
                                <%--<dd class="menu_item"><a onclick="registManage();">外院人员管理</a></dd>--%>
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/jsp/zseyjxres/foot.jsp" />
    </div>
</body>
</html>
