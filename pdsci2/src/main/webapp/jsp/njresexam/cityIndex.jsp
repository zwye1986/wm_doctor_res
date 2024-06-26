<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script>

        function toPage(page) {
            $("#currentPage").val(page);
            jboxStartLoading();
            $("#paramForm").submit();
        }

        function shouye() {
            var url = "<s:url value='/res/njExam/index'/>";
            window.location.href = url;
        }

        $(function () {
            $("#indexBody").css("height", window.innerHeight + "px");
        });
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
        });

        function examAddress() {
            jboxLoad("content","<s:url value='/res/njExam/examInfo'/>",true);
            <%--var url = "<s:url value='/res/njExam/index'/>";--%>
            <%--window.location.href = url;--%>
        }

        function doctorInfo() {
            jboxLoad("content","<s:url value='/res/njExam/docInfo'/>",true);
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
                        <a onclick="shouye();">江苏省${pdfn:getCurrYear()}年结业技能考试准考证打印平台</a>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span
                                id="topUserName">${sessionScope.currUser.orgName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/njresexam/logout'/>">退出</a>
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
                                <i class="icon_menu menu_management"></i>结业考核管理
                            </dt>
                            <dd class="menu_item" id="xyxx"><a href="javascript:doctorInfo();">学员信息</a></dd>
                            <dd class="menu_item"><a href="javascript:examAddress();">考点信息维护</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="foot">
    <div class="foot_inner">
        主管单位：江苏省卫生健康委员会科教处 | 技术支持：南京品德网络信息技术有限公司
    </div>
</div>

</body>
</html>
