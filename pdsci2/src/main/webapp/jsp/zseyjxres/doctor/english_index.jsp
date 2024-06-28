<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['jx_top_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres_en.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
        });
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

        onresize = function () {
            setBodyHeight();
        }

        /**
         * 网上报名
         */
        function singup(stuBatId) {
            if (stuBatId) {
                var url = "<s:url value='/zseyjxres/doctor/singup_en?&batchFlow='/>" + stuBatId;
                jboxLoad("content", url, true);
            } else {
                jboxTip("There are no batches to sign up for!");
                var url = "<s:url value='/zseyjxres/doctor/singup_en'/>";
            }

        }


        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            window.location.href = "<s:url value='/zseyjxres/doctor/home_en'/>?currentPage=" + page;
        }

        /**
         * 公告消息
         */
        function msgToPage(page) {
            var url = "<s:url value='/zseyjxres/doctor/msg'/>?currentPage=" + (page || "");
            jboxLoad("content", url, true);
        }

    </script>
    <style type="text/css">

    </style>
</head>
<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="<%--yw--%>">
            <jsp:include page="/jsp/zseyjxres/english_head.jsp" flush="true">
                <jsp:param value="true" name="notice"/>
            </jsp:include>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>Voluntary reporting
                            </dt>
                            <dd class="menu_item"  id="main"><a style="padding: 0 0 0 35px" onclick="singup('${batchFlow}');">on-line registration</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content" style="width: 900px">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <a>
                                              <span class="tap_inner">
                                                <i class="message"></i>
                                                <em class="number" id="newMsg">${newMsg+0}</em>
                                                <strong class="title">New messages</strong>
                                              </span>
                                            </a>
                                        </li>
                                        <li class="index_item index_blue">
                                            <a href="javascript:void(0);" style="cursor: pointer;"
                                                    <c:if test='${not empty stuUser}'> onclick="singup('${stuUser.stuBatId}');</c:if>
                                                    ">
                                                  <span class="tap_inner tab_second">
                                                    <i class="people"></i>
                                                    <em class="number" style="font-size: 25px;">
                                                        <c:if test='${empty stuUser}'>Unfilled</c:if>
                                                        <c:if test='${not empty stuUser}'>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumPassing.name}'>
                                                                Data audit
                                                                <p style="font-size: 12px ;text-align: center">
                                                                    The audit will take about 10~15 working days. Please be patient</p>
                                                            </c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumPassed.name}'>Data is eligible for admission
                                                                <p style="font-size: 12px;text-align: center">
                                                                    Check the admissions information in a week</p>
                                                            </c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnPassed.name && stuUser.isBack ne "Y"}'>Data failure</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnPassed.name && stuUser.isBack eq "Y"}'>Incomplete data</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumRecruitted.name}'>Have been accepted</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnRecruitted.name}'>Not accepted</c:if>
                                                        </c:if>
                                                    </em>
                                                    <strong class="title">Registration information</strong>
                                                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="index_tap top_right">
                                    <ul class="inner">
                                        <li class="index_item index_green">
                                            <a href="javascript:;">
                                              <span class="tap_inner Volunteer">
                                                  <c:if test='${not empty stuUser}'>
                                                      <c:set var="speFormList" value="${extInfo.speFormList}"></c:set>

                                                      <span class="Volunteeer_none">
                                                          <c:choose>
                                                              <c:when test="${stuUser.stuStatusId eq 'Recruitted'}">Accepted
                                                                  (<c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag}">,</c:if>
                                                                      ${speForm.speId}<%--(${speForm.stuTimeId})--%>
                                                                      <c:set var="flag" value="true" scope="request"/>
                                                                  </c:forEach>)
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Admited'}">Already reported
                                                                  <c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag2}">,</c:if>
                                                                      ${speForm.speId}
                                                                      <c:set var="flag2" value="true" scope="request"/>
                                                                  </c:forEach>
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'UnAdmitd'}">Not reported
                                                                  (<c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag3}">,</c:if>
                                                                      ${speForm.speId}<%--(${speForm.stuTimeId})--%>
                                                                      <c:set var="flag3" value="true" scope="request"/>
                                                                  </c:forEach>)
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Graduation'}">Graduation</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'DelayGraduation'}">Delay in completion
                                                                  (<c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag4}">,</c:if>
                                                                      ${speForm.speId}<%--(${speForm.stuTimeId})--%>
                                                                      <c:set var="flag4" value="true" scope="request"/>
                                                                  </c:forEach>)
                                                              </c:when>
                                                          </c:choose>
                                                      </span>
                                                  </c:if>
                                                  <c:if test='${empty stuUser}'>
                                                      <span class="Volunteeer_none">
                                                          No Registration information
                                                      </span>
                                                  </c:if>
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
                                    Notification Center
                                    <%--<c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">
                                        <span>
                                            <a onclick="printRecruit('${stuUser.resumeFlow}')"
                                               style="color: #f60;text-decoration: underline">下载录取通知书</a>
                                        </span>
                                    </c:if>--%>
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
                                                            <c:set var="msg1" value="Your application material audit result: registration information has been returned, please re fill."></c:set>
                                                            <c:if test="${msg.msgTitle eq '您的报名材料审核结果：报名信息已被退回，请重新填写。'}">
                                                                ${msg1}
                                                            </c:if>

                                                            <c:set var="msg2" value="Your registration material audit result: registration does not pass"></c:set>
                                                            <c:if test="${msg.msgTitle eq '您的报名材料审核结果：报名审核不通过'}">
                                                                ${msg2}
                                                            </c:if>

                                                            <c:set var="msg3" value="Your registration materials audit results: application for approval"></c:set>
                                                            <c:if test="${msg.msgTitle eq '您的报名材料审核结果：报名审核通过'}">
                                                                ${msg3}
                                                            </c:if>
                                                            <c:set var="msg4" value="Your registration materials audit results: have been admitted"></c:set>
                                                            <c:if test="${msg.msgTitle eq '您的报名材料审核结果：已录取'}">
                                                                ${msg4}
                                                            </c:if>

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
                                                    No notice!
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
                                    <pd:pagination-sczyres toPage="toPage"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
        <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
    </c:if>
    <jsp:include page="/jsp/zseyjxres/foot.jsp" flush="true"/>
</div>
</body>
</html>
