<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            <%--if ("${recordCount !=0}" == "true") {--%>
                var result = 0;
                if(("${jszyResDocTypeEnumCompany.id}"=="${doctor.doctorTypeId}"||"${jszyResDocTypeEnumCompanyEntrust.id}"=="${doctor.doctorTypeId}")
                        &&(""=="${doctor.workOrgId}"||""=="${doctor.workOrgName}")){
                    result=1;
                    <%--if("${doctor.workOrgId}"==""){--%>
                    result=1;
                    // 			}
                    <%--if("${doctor.workOrgName}"==""){--%>
    //			}
                }
                <%--if ("${jszyResDocTypeEnumCompany.id}" == "${doctor.doctorTypeId}") {--%>
                    <%--if ("${doctor.workOrgName}" == "") {--%>
                        <%--result = 1;--%>
                    <%--}--%>
                <%--}--%>
                <%--if ("${jszyResDocTypeEnumGraduate.id}" == "${doctor.doctorTypeId}") {--%>
                    <%--if ("${GlobalConstant.FLAG_N}" == "${school}") {--%>
                        <%--result = 1;--%>
                    <%--}--%>
                <%--}--%>
                <%--if ("${GlobalConstant.FLAG_N}" == "${benkeResult}") {--%>
                    <%--result = 1;--%>
                <%--}--%>
                <%--if ("${GlobalConstant.FLAG_N}" == "${result}") {--%>
                    <%--result = 1;--%>
                <%--}--%>
                <%--if ("${doctor.isYearGraduate}" == "") {--%>
                    <%--result = 1;--%>
                <%--}--%>
                if(result==1){
                   // jboxOpen("<s:url value='/hbzy/doctor/saveForLack'/>",null,650,380);
                }
            <%--}--%>

        });
        /* 培训信息 */
        function main(recruitFlow) {
            var url = "<s:url value='/hbzy/doctor/main'/>";
            if (recruitFlow != "") {
                url = url + "?recruitFlow=" + recruitFlow;
            }
            jboxLoad("content", url, true);
        }
        function selectMenu(menuId, subMenuId) {
            $("#" + menuId).find("a").click();
            $(".select").removeClass("select");
            $("#" + menuId).find("a").addClass("select");
            $("#subMenuId").val(subMenuId);
        }
        function msg() {
            var url = "<s:url value='/inx/jsres/allNotice'/>?flag=Y";
            jboxLoad("content", url, false);
        }
        function setBodyHeight() {
            if (navigator.appName.indexOf("Microsoft Internet Explorer") > -1) {//处理ie浏览器placeholder和password的不兼容问题
                if (navigator.appVersion.indexOf("MSIE 7.0") > -1) {
                    $("#indexBody").css("height", window.innerHeight + "px");
                } else if (navigator.appVersion.indexOf("MSIE 8.0") > -1) {
                    $("#indexBody").css("height", document.documentElement.offsetHeight + "px");
                } else {
                    $("#indexBody").css("height", window.innerHeight + "px");
                }
            } else {
                $("#indexBody").css("height", window.innerHeight + "px");
            }
        }
        function shouye() {
            var url = "<s:url value='/hbzy/doctor/index'/>";
            window.location.href = url;
        }
        onresize = function () {
            setBodyHeight();
        }
        function toPage(page) {
            var currentPage;
            if (page != undefined) {
                currentPage = page;
            }
            var url = "<s:url value='/hbzy/doctor/index'/>?currentPage=" + currentPage;
            window.location.href = url;
        }

        function resMain() {
            window.open("<s:url value='/hbzy/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");
        }
        function trainRegister() {
            jboxLoad("content", "<s:url value='/hbzy/doctor/trainRegister'/>", true);
        }
        function reductionApplyMain() {
            jboxLoad("content", "<s:url value='/hbzy/reduction/reductionApplyMain'/>", true);
        }
        //安全中心
        function accounts(){
            jboxLoad("content", "<s:url value='/hbzy/manage/accounts'/>", true);
        }
        function doctorBaseInfo(userFlow){
            jboxLoad("content", "<s:url value='/hbzy/doctor/doctorInfo?userFlow='/>" + userFlow, true);
        }
        function certificateManage() {
            jboxLoad("content", "<s:url value='/hbzy/graduationManager/doctorMain'/>", true);
        }
        function graduationRegistrate() {
            jboxLoad("content", "<s:url value='/hbzy/asse/graduationRegistrate'/>", true);
        }
        /*成绩查询*/
        function owenScore(){
            jboxLoad("content","<s:url value='/hbzy/doctor/owenScore'/>",true);
        }
    </script>
    <style>
        body {
            overflow: hidden;
        }
    </style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">

            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <img onclick="shouye();" src="<s:url value='/jsp/inx/hbzy/img/hbzy_head.png'/>" style="margin-top: 30px;"/>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span
                                id="topUserName">${sessionScope.currUser.userName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a onclick="msg();">公告</a>&#12288;
                            <a href="<s:url value='/inx/hbzy/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <input type="hidden" id="subMenuId" value=""/>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>医师个人信息管理
                            </dt>
                            <dd class="menu_item"><a onclick="doctorBaseInfo('${sessionScope.currUser.userFlow}');">基本信息</a></dd>
                            <dd class="menu_item"><a onclick="main('');">培训信息</a></dd>
                            <dd class="menu_item"><a onclick="reductionApplyMain();">减免申请</a></dd>
                            <dd class="menu_item"><a onclick="trainRegister();">诚信声明</a></dd>
                            <%--默认不开过程--%>
                            <c:set value="N" var="openGuocheng"></c:set>
                            <%--后台配置是否校验权限时间--%>
                            <c:set value="${pdfn:getSysCfg('res_permit_open_doc')}" var="isPermitOpen"></c:set>
                            <%--开通web权限的key--%>
                            <c:set value="res_doctor_web_${currUser.userFlow}" var="key"/>
                            <%--当前的登录时间--%>
                            <c:set value="${pdfn:getCurrDate()}" var="loginDate"/>
                            <%--如果校验权限时间，并且开通web权限，并且登录时间大于权限开始时间，并且登录时间小于权限结束时间，则开通过程--%>
                            <c:if test="${isPermitOpen eq GlobalConstant.RECORD_STATUS_Y
                            and (pdfn:resPowerCfgMap(key).cfgValue eq GlobalConstant.RECORD_STATUS_Y)
							and (pdfn:resPowerCfgMap(key).powerStartTime <= loginDate)
							and (pdfn:resPowerCfgMap(key).powerEndTime >= loginDate)}">
                                <c:set value="Y" var="openGuocheng"></c:set>
                            </c:if>
                            <%--如果不校验校验权限时间，并且开通web权限，则开通过程--%>
                            <c:if test="${isPermitOpen ne GlobalConstant.RECORD_STATUS_Y
                            and (pdfn:resPowerCfgMap(key).cfgValue eq GlobalConstant.RECORD_STATUS_Y)}">
                                <c:set value="Y" var="openGuocheng"></c:set>
                            </c:if>
                            <c:if test="${openGuocheng eq GlobalConstant.RECORD_STATUS_Y}">
                                <c:if test="${not empty doctor.orgFlow}">
                                    <dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>
                                </c:if>
                                <%--<c:choose>--%>
                                <%--<c:when test="${doctor.doctorTypeId eq jszyResDocTypeEnumGraduate.id and schoolPerm}">--%>
                                <%--<dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${doctor.doctorTypeId != jszyResDocTypeEnumGraduate.id}">--%>
                                <%--<dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>--%>
                                <%--</c:when>--%>
                                <%--</c:choose>--%>
                            </c:if>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>结业管理
                            </dt>
                            <dd class="menu_item"><a onclick="graduationRegistrate();">理论考核报名</a></dd>
                            <dd class="menu_item"><a onclick="certificateManage();">证书信息查询</a></dd>
                            <dd class="menu_item" id="main"><a onclick="owenScore('');">结业成绩查询</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>设置
                            </dt>
                            <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <a href="#">
                  <span class="tap_inner">
                    <i class="message"></i>
                     <em class="number" id="newMsg">${newMsg+0}</em>
                    <strong class="title">新消息</strong>
                  </span>
                                            </a>
                                        </li>
                                        <li class="index_item index_blue">
                                            <a href="javascript:selectMenu('main','doctorInfo');">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number" style="font-size: 37px;">${recordCount+0}</em>
                    <strong class="title">培训记录</strong>
                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="index_tap top_right">
                                    <ul class="inner">
                                        <li class="index_item index_green">
                                            <a href="#">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">
                        ${doctor.doctorStatusName}
                        <c:if test="${ empty doctor.doctorStatusName}">
                            <span class="number">无</span>
                        </c:if>
                    </em>
                    <strong class="title">医师状态</strong>
                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <script>
                                function viewMsg(dl, msgFlow) {
                                    var isSelect = dl.className.indexOf("msgselect") < 0;
                                    $(".msgselect").removeClass("msgselect").find("dd").addClass("none");
                                    if (isSelect) {
                                        $(dl).addClass("msgselect").find("dd").removeClass("none");
                                    }
                                    if (dl.className.indexOf("readed") < 0) {
                                        jboxPost("<s:url value='/jsres/doctor/readMsg'/>?msgFlow=" + msgFlow + "&receiveFlag=${GlobalConstant.FLAG_Y}", null, function (resp) {
                                            if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                                                $(dl).addClass("readed");
                                                var msgNum = parseInt($("#newMsg").text());
                                                var num = --msgNum;
                                                if (num < 0) {
                                                    num = 0;
                                                }
                                                $("#newMsg").text(num);
                                            }
                                        }, null, false);
                                    }
                                    ;
                                }
                            </script>
                            <div class="index_form" style="margin-bottom: 10px;">
                                <h3>通知中心</h3>
                                <div class="main_bd">
                                    <div id="notification" class="notificationCenterPage">
                                        <c:forEach items="${msgList}" var="msg">
                                            <dl class="notify_item <c:if test="${msg.receiveFlag eq GlobalConstant.FLAG_Y}"> readed</c:if>"
                                                onclick="viewMsg(this,'${msg.msgFlow}');" style="lin">
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
                            <c:if test="${!empty msgList}">
                                <div class="page" style="padding-right: 40px;">
                                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                                    <c:set var="pageView" value="${pdfn:getPageView(msgList)}" scope="request"></c:set>
                                    <pd:pagination-jsres toPage="toPage"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>
<div class="foot">
    <div class="foot_inner">
        主管单位：湖北省中医药局 | 技术支持：南京品德网络信息技术有限公司
    </div>
</div>

</div>

</body>
</html>
