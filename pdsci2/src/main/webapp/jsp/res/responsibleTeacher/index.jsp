﻿<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        function doctorInfoSearch() {
            jboxLoad("content", "<s:url value='/res/responsibleTeacher/doctorInfoList'/>", true);
        }

        function annualExamScore() {
            jboxLoad("content", "<s:url value='/res/responsibleTeacher/annualExamScore'/>", true);
        }

        function doctorTestOut() {
            jboxLoad("content", "<s:url value='/res/manager/doctorRecruit/doctorChuke'/>?role=responsibleTeacher", true);
        }

        function docWorkSearch(){
            jboxLoad("content","<s:url value='/res/responsibleTeacher/docWorkQuery'/>",true);
        }

        function cycle(data) {
            var docTypes = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if (docTypes == "") {
                docTypes += "docTypes=" + "${type.id}";
            } else {
                docTypes += "&docTypes=" + "${type.id}";
            }
            </c:forEach>
            var url = "<s:url value='/res/responsibleTeacher/cycle'/>?" + docTypes;
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
    </script>
    <style>
    </style>
</head>
<body>
<div id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }-${sessionScope.currUser.userName }</h2>
                        <div class="head_right">
                            <!--        引入切换角色功能 -->
                            <jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_function"></i>基地信息管理
                            </dt>
                            <dd class="menu_item"><a onclick="doctorInfoSearch();">学员信息查询</a></dd>
                            <dd class="menu_item"><a onclick="annualExamScore();">年度成绩查询</a></dd>
                            <dd class="menu_item"><a href="javascript:doctorTestOut();">出科成绩查询</a></dd>
                            <dd class="menu_item"><a href="javascript:docWorkSearch();">学员工作量查询</a></dd>
                            <dd class="menu_item"><a onclick="cycle(null);">学员轮转查询</a> </dd>

                        </dl>
                    </div>
                    <div class="col_main" id="content" style="height: 50%;">

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
            主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司
        </div>
    </div>

</div>
</div>
</body>
</html>
