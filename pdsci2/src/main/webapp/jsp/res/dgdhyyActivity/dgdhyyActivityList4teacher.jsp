<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
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
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function search(){
            $("#searchForm").submit();
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class=" clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/dgdhyyActivity/dgdhyyActivityList4teacher'/>" method="post">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        活动名称：
                        <input type="text" name="activityName" class="qtext" value="${param.activityName}"/>
                    </div>
                    <div class="lastDiv" style="min-width: 182px;max-width: 182px;">
                        &#12288;<input class="search" type="button" value="查&#12288;询" onclick="search();"/>
                    </div>
                </form>
            </div>
            <form id="scoreForm">
            <table class="xllist">
                <tr>
                    <th>参加人姓名</th>
                    <th>参加人用户名</th>
                    <th>活动名称</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>活动形式</th>
                    <th>所在科室</th>
                    <th>主讲人</th>
                    <th>联系方式</th>
                    <th>活动地点</th>
                    <th>备注</th>
                </tr>
                <c:forEach items="${dgdhyyActivityList}" var="dgdhyyActivity" varStatus="s">
                    <tr>
                        <td>${dgdhyyActivity.userName}</td>
                        <td>${dgdhyyActivity.userCode}</td>
                        <td title="${dgdhyyActivity.activityName}">${pdfn:cutString(dgdhyyActivity.activityName,10,true,3)}</td>
                        <td>${dgdhyyActivity.startTime}</td>
                        <td>${dgdhyyActivity.endTime}</td>
                        <td>${dgdhyyActivity.activityForm}</td>
                        <td>${dgdhyyActivity.deptName}</td>
                        <td>${dgdhyyActivity.activitySpeaker}</td>
                        <td>${dgdhyyActivity.phoneNumber}</td>
                        <td title="${dgdhyyActivity.activityPlace}">${pdfn:cutString(dgdhyyActivity.activityPlace,10,true,3)}</td>
                        <td title="${dgdhyyActivity.remarks}">${pdfn:cutString(dgdhyyActivity.remarks,10,true,3)}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dgdhyyActivityList}">
                    <tr>
                        <td colspan="20" style="text-align: center">无记录！</td>
                    </tr>
                </c:if>
            </table>
            </form>
            <div class="page" style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(dgdhyyActivityList)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
