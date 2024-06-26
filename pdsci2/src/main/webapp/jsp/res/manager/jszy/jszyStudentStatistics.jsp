<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .centerInfo td {
            text-align: center;
            padding: 0px;
        }
    </style>
    <script>
        $(document).ready(function () {
            init();
        });
        function toPage(page) {
            var currentPage;
            if (page != undefined) {
                currentPage = page;
            }
            $("#currentPage").val(currentPage);
            $("#searchForm").submit();
        }
        function init() {
            var sessionNumberTds = $("td[sessionNumber]")
            var sessionNumber1 = "${sessionNumbers.get(0)}";
            var sessionNumber2 = "${sessionNumbers.get(1)}";
            var sessionNumber3 = "${sessionNumbers.get(2)}";
            var sessionNumber4 = "${sessionNumbers.get(3)}";
            var sessionNumber1Sum = 0;
            var sessionNumber2Sum = 0;
            var sessionNumber3Sum = 0;
            var sessionNumber4Sum = 0;

            sessionNumberTds.each(function (i, e) {
                var sessionNumber = $(e).attr("sessionNumber");
                var sessionNumberCount = $(e).attr("sessionNumberCount");
                if (sessionNumber1 == sessionNumber) {
                    sessionNumber1Sum += Number(sessionNumberCount);
                }
                if (sessionNumber2 == sessionNumber) {
                    sessionNumber2Sum += Number(sessionNumberCount);
                }
                if (sessionNumber3 == sessionNumber) {
                    sessionNumber3Sum += Number(sessionNumberCount);
                }
                if (sessionNumber4 == sessionNumber) {
                    sessionNumber4Sum += Number(sessionNumberCount);
                }
            });
            $("#" + sessionNumber1 + "Sum").text(sessionNumber1Sum);
            $("#" + sessionNumber2 + "Sum").text(sessionNumber2Sum);
            $("#" + sessionNumber3 + "Sum").text(sessionNumber3Sum);
            $("#" + sessionNumber4 + "Sum").text(sessionNumber4Sum);
            $("#zongji").text(sessionNumber1Sum + sessionNumber2Sum + sessionNumber3Sum + sessionNumber4Sum);
        }
        function showOrgDoctorType(count, orgFlow) {
            if (count == "0") {
                jboxTip("该基地暂无学员!");
                return;
            }
            var url = "<s:url value='/res/statistics/showOrgDoctorType'/>?orgFlow=" + orgFlow;
            jboxOpen(url, "人员详情", 500, 300, true);
        }
        function exportExcel(){
            window.location.href = "<s:url value='/res/statistics/exportJszyStudentStatistics'/>";
        }
    </script>
</head>
<body>
<div class="mainright" style="overflow: auto">
    <div class="content">
        <div class="clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/statistics/jszyStudentStatistics'/>" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
                </form>
            </div>
            <table class="basic centerInfo" width="100%">
                <tr>
                    <th colspan="6" style="text-align: center;padding: 0px">在培人数&#12288;&#12288;&#12288;
                        <a href="javascript:exportExcel()" style="color:blue;">导出</a></th>
                </tr>
                <tr>
                    <th style="text-align: center;padding: 0px">基地名称</th>
                    <c:forEach items="${sessionNumbers}" var="sessionNumber">
                        <th style="text-align: center;padding: 0px">${sessionNumber}</th>
                    </c:forEach>
                    <th style="text-align: center;padding: 0px">小计</th>
                </tr>
                <c:forEach items="${orgs}" var="org">
                    <c:set var="sum" value="0"></c:set>
                    <tr>
                        <td>${org.orgName}</td>
                        <c:forEach items="${sessionNumbers}" var="sessionNumber">
                            <c:set var="key" value="${org.orgFlow}${sessionNumber}"></c:set>
                            <td sessionNumber="${sessionNumber}"
                                sessionNumberCount="${empty countInfoMap[key] ? 0 : countInfoMap[key]}">${empty countInfoMap[key] ? 0 : countInfoMap[key]}</td>
                            <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                        </c:forEach>
                        <td>
                            <a style="color: blue;cursor: pointer;"
                               onclick="showOrgDoctorType('${sum}','${org.orgFlow}');">${sum}</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>合计</td>
                    <c:forEach items="${sessionNumbers}" var="sessionNumber">
                        <td id="${sessionNumber}Sum"></td>
                    </c:forEach>
                    <td id="zongji"></td>
                </tr>
            </table>
            <div style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(orgs)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

