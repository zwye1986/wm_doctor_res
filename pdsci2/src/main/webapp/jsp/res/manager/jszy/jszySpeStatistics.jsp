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
            var trainingTypeTds = $("td[trainingType]")
            var chineseMedicineSum = 0;
            var TCMGeneralSum = 0;

            trainingTypeTds.each(function (i, e) {
                var trainingType = $(e).attr("trainingType");
                var trainingTypeCount = $(e).attr("trainingTypeCount");
                if (trainingType == "${jszyTrainCategoryEnumChineseMedicine.id}") {
                    chineseMedicineSum += Number(trainingTypeCount);
                }
                if (trainingType == "${jszyTrainCategoryEnumTCMGeneral.id}") {
                    TCMGeneralSum += Number(trainingTypeCount);
                }

            });
            $("#chineseMedicineSum").text(chineseMedicineSum);
            $("#TCMGeneralSum").text(TCMGeneralSum);
            $("#zongji").text(chineseMedicineSum + TCMGeneralSum);
        }
        function exportExcel(){
            window.location.href = "<s:url value='/res/statistics/exportJszySpeStatistics'/>";
        }
    </script>
</head>
<body>
<div class="mainright" style="overflow: auto">
    <div class="content">
        <div class="clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/statistics/jszySpeStatistics'/>" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
                </form>
            </div>
            <table class="basic centerInfo" width="100%">
                <tr>
                    <th colspan="10" style="text-align: center;padding: 0px">在培人数&#12288;&#12288;&#12288;
                        <a href="javascript:exportExcel()" style="color:blue;">导出</a></th>
                </tr>
                <tr>
                    <th style="text-align: center;padding: 0px" rowspan="2">基地名称</th>
                    <th style="text-align: center;padding: 0px" colspan="4">中医</th>
                    <th style="text-align: center;padding: 0px" colspan="4">中医全科</th>

                    <th style="text-align: center;padding: 0px" rowspan="2">小计</th>
                </tr>
                <tr>
                    <c:forEach items="${sessionNumbers}" var="sessionNumber">
                        <th style="text-align: center;padding: 0px" >${sessionNumber}</th>
                    </c:forEach>
                    <c:forEach items="${sessionNumbers}" var="sessionNumber">
                        <th style="text-align: center;padding: 0px" >${sessionNumber}</th>
                    </c:forEach>
                </tr>
                <tr>

                </tr>
                <c:forEach items="${orgs}" var="org">
                    <c:set var="sum" value="0"></c:set>
                    <tr>
                        <td>${org.orgName}</td>
                        <c:forEach items="${sessionNumbers}" var="sessionNumber">
                            <c:set var="key1" value="${org.orgFlow}${jszyTrainCategoryEnumChineseMedicine.id}${sessionNumber}"></c:set>
                            <td trainingType="${jszyTrainCategoryEnumChineseMedicine.id}"
                                trainingTypeCount="${empty countInfoMap[key1] ? 0 : countInfoMap[key1]}">
                            ${empty countInfoMap[key1] ? 0 : countInfoMap[key1]}</td>

                            <c:set var="sum" value="${sum + countInfoMap[key1]}"></c:set>
                        </c:forEach>
                        <c:forEach items="${sessionNumbers}" var="sessionNumber">
                            <c:set var="key2" value="${org.orgFlow}${jszyTrainCategoryEnumTCMGeneral.id}${sessionNumber}"></c:set>
                            <td trainingType="${jszyTrainCategoryEnumTCMGeneral.id}"
                                trainingTypeCount="${empty countInfoMap[key2] ? 0 : countInfoMap[key2]}">
                                    ${empty countInfoMap[key2] ? 0 : countInfoMap[key2]}</td>
                            <c:set var="sum" value="${sum + countInfoMap[key2]}"></c:set>
                        </c:forEach>
                        <td>${sum}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>合计</td>
                    <td id="chineseMedicineSum" colspan="4"></td>
                    <td id="TCMGeneralSum" colspan="4"></td>
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

