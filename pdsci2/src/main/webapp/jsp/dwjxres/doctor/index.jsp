<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['jx_top_name']}</title>
    <jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
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
                var url = "<s:url value='/dwjxres/doctor/singup?&batchFlow='/>" + stuBatId;
                jboxLoad("content", url, true);
            } else {
                jboxTip("当前没有可以报名的批次！");
                var url = "<s:url value='/dwjxres/doctor/singup'/>";
            }

        }
        /**
         * 结业鉴定
         */
        function graduationAppraisal() {
            var url = "<s:url value='/dwjxres/doctor/graduationAppraisal/list'/>";
            jboxLoad("content", url, true);
        }

        /**
         * 评价管理
         */
        function evaluation(resp) {
            var roleId = resp;
            var url = "<s:url value='/evaluation/manage/evaluationManage?roleId='/>" + roleId;
            jboxLoad("content", url, true);
        }

        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            window.location.href = "<s:url value='/dwjxres/doctor/home'/>?currentPage=" + page;
        }

        /**
         * 公告消息
         */
        function msgToPage(page) {
            var url = "<s:url value='/sczyres/doctor/msg'/>?currentPage=" + (page || "");
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
    </script>
    <style type="text/css">

    </style>
</head>
<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <jsp:include page="/jsp/dwjxres/head.jsp">
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
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>评价管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:evaluation('Secretary');">科室评价</a></dd>
                            <dd class="menu_item"><a href="javascript:evaluation('Teacher');">带教评价</a></dd>
                        </dl>
                        <c:if test="${showGraduation eq 'Y'}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>结业管理
                                </dt>
                                <dd class="menu_item" id=""><a onclick="graduationAppraisal();">进修鉴定</a></dd>
                            </dl>
                        </c:if>
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
                                                                    一周内查看资料审核结果</p>
                                                            </c:if>
                                                            <c:if test='${stuUser.stuStatusName eq stuStatusEnumPassed.name}'>资料合格待录取
                                                                <p style="font-size: 12px;text-align: center">
                                                                    开网后次月中旬查看录取通知</p>
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
                                                      <span class="Volunteeer_none">
                                                          <c:choose>
                                                              <%--<c:when test="${stuUser.stuStatusId eq 'Passed'}">待录取</c:when>--%>
                                                              <c:when test="${stuUser.stuStatusId eq 'Recruitted'}">已录取（${stuUser.speName}）</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Admited'}">已报到（${stuUser.speName}）</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'UnAdmitd'}">未报到（${stuUser.speName}）</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'Graduation'}">结业</c:when>
                                                              <c:when test="${stuUser.stuStatusId eq 'DelayGraduation'}">延期结业（${stuUser.speName}）</c:when>
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
                                //打印录取通知书
                                function printRecruit(resumeFlow, templeteName) {
                                    jboxTip("打印中,请稍等...");
                                    var url = '<s:url value="/dwjxres/doctor/recruitNotice?resumeFlow="/>' + resumeFlow + "&templeteName=" + templeteName;
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
                                        <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                            <span>
                                                <a onclick="printRecruit('${stuUser.resumeFlow}','${sysCfgMap['jx_templete_name']}')"
                                                   style="color: #4897d3;text-decoration: underline">下载录取通知书</a>
                                            </span>
                                        </c:if>
                                        <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                            <span>
                                                <a onclick="printRecruit('${stuUser.resumeFlow}','${sysCfgMap['jx_templete_name']}')"
                                                   style="color: #f60;text-decoration: underline">下载录取通知书</a>
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
    <jsp:include page="/jsp/dwjxres/foot.jsp"/>
</div>
</body>
</html>
