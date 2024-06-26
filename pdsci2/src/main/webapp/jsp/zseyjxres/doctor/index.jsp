<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['jx_top_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
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
                var url = "<s:url value='/zseyjxres/doctor/singup?&batchFlow='/>" + stuBatId;
                jboxLoad("content", url, true);
            } else {
                jboxTip("当前没有可以报名的批次！");
                var url = "<s:url value='/zseyjxres/doctor/singup'/>";
            }

        }
        /**
         * 结业鉴定
         */
        function graduationAppraisal() {
            var url = "<s:url value='/zseyjxres/doctor/graduationAppraisal/list'/>";
            jboxLoad("content", url, true);
        }

        /**
         * 评价管理
         */
        function evaluation(resp) {
            var roleId = resp;
            var url = "<s:url value='/zseyjxres/evaluation/evaluationManage?roleId='/>" + roleId;
            jboxLoad("content", url, true);
        }

        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            window.location.href = "<s:url value='/zseyjxres/doctor/home'/>?currentPage=" + page;
        }

        /**
         * 公告消息
         */
        function msgToPage(page) {
            var url = "<s:url value='/zseyjxres/doctor/msg'/>?currentPage=" + (page || "");
            jboxLoad("content", url, true);
        }

        /**
         * 学员确认录取操作
         */
        function openDialog(recruitFlow) {
            jboxOpenContent("<div style='text-align:center;'>" + '<span class="btn_green"  onclick="confirmRecruit(' + "'Y'" + ' , ' + "'" + recruitFlow + "'" + ');">确认录取</span>&nbsp;&nbsp;<span class="btn_red" onclick="confirmRecruit(' + "'N'" + ' , ' + "' " + recruitFlow + " '" + ')">放弃录取</span>' + "<div>", "是否确认录取?", 340, 40, true);
        }
        function confirmRecruit(confirmFlag, recruitFlow) {
            var requestData = {
                "recruitFlow": $.trim(recruitFlow),
                "confirmFlag": $.trim(confirmFlag)
            }
            var msg = "确认同意该基地录取操作?";
            if (confirmFlag == "N") {
                msg = "确认<font color='red'>拒绝并放弃</font>该基地的录取操作?";
            }
            jboxConfirm(msg, function (resp) {
                var url = "<s:url value='/sczyres/doctor/confirmRecruit'/>";
                jboxPost(url, requestData, function (resp) {
                    if (resp == "1") {
                        jboxTip("操作成功");
                        setTimeout(function () {
                            window.location.href = '<s:url value='/sczyres/doctor/home'/>';
                        }, 1000);
                    } else {
                        jboxTip("操作失败");
                    }
                }, null, false);
            });
            jboxClose();
        }

        <%--/**--%>
         <%--* 过程--%>
         <%--*/--%>
        <%--function resMain(){--%>
            <%--window.open("<s:url value='/gzzyjxres/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");--%>
        <%--}--%>
    </script>
    <style type="text/css">

    </style>
</head>
<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="<%--yw--%>">
            <jsp:include page="/jsp/zseyjxres/head.jsp">
                <jsp:param value="true" name="notice"/>
            </jsp:include>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>志愿填报
                            </dt>
                            <dd class="menu_item" id="main"><a onclick="singup('${batchFlow}');">网上报名</a></dd>
                        </dl>
                      <%--  <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>评价管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:evaluation('Secretary');">科室评价</a></dd>
                            <dd class="menu_item"><a href="javascript:evaluation('Teacher');">带教评价</a></dd>
                        </dl>--%>

                        <%--暂时隐藏，结业需求未定 --%>
                        <%--<c:if test="${showGraduation eq 'Y'}">
                            &lt;%&ndash;<dl class="menu">&ndash;%&gt;
                                &lt;%&ndash;<dt class="menu_title">&ndash;%&gt;
                                    &lt;%&ndash;<i class="icon_menu menu_statistics"></i>过程&ndash;%&gt;
                                &lt;%&ndash;</dt>&ndash;%&gt;
                                &lt;%&ndash;<dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>&ndash;%&gt;
                            &lt;%&ndash;</dl>&ndash;%&gt;
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>结业管理
                                </dt>
                                <dd class="menu_item" id=""><a onclick="graduationAppraisal();">进修鉴定</a></dd>
                            </dl>
                        </c:if>--%>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <a>
                                              <span class="tap_inner">
                                                <i class="message"></i>
                                                <em class="number" id="newMsg">${newMsg+0}</em>
                                                <strong class="title">新消息</strong>
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
                                                        <c:if test='${empty stuUser}'>未填写</c:if>
                                                        <c:if test='${not empty stuUser}'>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumPassing.name}'>
                                                                资料审核中
                                                                <p style="font-size: 12px ;text-align: center">
                                                                    审核需时约10~15个工作日，请耐心等待</p>
                                                            </c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumPassed.name}'>资料合格待录取
                                                                <p style="font-size: 12px;text-align: center">
                                                                    一周后查看录取信息</p>
                                                            </c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnPassed.name && stuUser.isBack ne "Y"}'>资料不合格</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnPassed.name && stuUser.isBack eq "Y"}'>资料不全</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumRecruitted.name}'>已被录取</c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumUnRecruitted.name}'>未被录取</c:if>
                                                        </c:if>
                                                    </em>
                                                    <strong class="title">报名信息</strong>
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
                                                              <c:when test="${stuUser.stuStatusId eq 'Recruitted'}">已录取
                                                                  <c:set var="flag" value="false"/>
                                                                  <c:forEach items="${speFormList}" var="speForm">
                                                                  <c:if test="${flag}">,</c:if>
                                                                  ${speForm.speName}
                                                                  <c:set var="flag" value="true"/>
                                                                  </c:forEach>
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Admited'}">已报到
                                                                  <c:set var="flag2" value="false"/>
                                                                  <c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag2}">,</c:if>
                                                                      ${speForm.speName}
                                                                      <c:set var="flag2" value="true"/>
                                                                  </c:forEach>
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'UnAdmitd'}">未报到
                                                                  <c:set var="flag3" value="false"/>
                                                                  <c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag3}">,</c:if>
                                                                      ${speForm.speName}
                                                                      <c:set var="flag3" value="true"/>
                                                                  </c:forEach>
                                                              </c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Graduation'}">结业</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'DelayGraduation'}">延期结业
                                                                  <c:set var="flag4" value="false"/>
                                                                  <c:forEach items="${speFormList}" var="speForm">
                                                                      <c:if test="${flag4}">,</c:if>
                                                                      ${speForm.speName}
                                                                      <c:set var="flag4" value="true"/>
                                                                  </c:forEach>
                                                              </c:when>
                                                          </c:choose>
                                                      </span>
                                                  </c:if>
                                                  <c:if test='${empty stuUser}'>
                                                      <span class="Volunteeer_none">
                                                          暂无报名信息
                                                      </span>
                                                  </c:if>
                                              </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <script>
                                //下载录取通知书
                                function printRecruit(resumeFlow) {
                                    jboxTip("打印中,请稍等...");
                                    var url = '<s:url value="/zseyjxres/doctor/recruitNotice?resumeFlow="/>' + resumeFlow;
                                    window.open(url);

                                }
                                //下载住宿介绍信
                                function printIntroduction(resumeFlow) {
                                    jboxTip("打印中,请稍等...");
                                    var url = '<s:url value="/zseyjxres/doctor/accommodation?resumeFlow="/>' + resumeFlow;
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
                                    <c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">
                                        <span>
                                            <a onclick="printRecruit('${stuUser.resumeFlow}')"
                                               style="color: #f60;text-decoration: underline">下载录取通知书</a>
                                        </span>
                                        <c:if test="${stuUser.isPublishAccommodation eq 'Y'}">
                                            <span>
                                            <a onclick="printIntroduction('${stuUser.resumeFlow}')"
                                               style="color: #f60;text-decoration: underline">下载住宿介绍信</a>
                                        </span>
                                        </c:if>
                                    </c:if>
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
        <jsp:include page="/jsp/service.jsp"></jsp:include>
    </c:if>
    <jsp:include page="/jsp/zseyjxres/foot.jsp"/>
</div>
</body>
</html>
