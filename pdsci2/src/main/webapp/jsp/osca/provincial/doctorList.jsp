<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        var yearJson = {"OneYear":"一年","TwoYear":"两年","ThreeYear":"三年"}
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/osca/provincial/doctorList'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input name="clinicalFlow" value="${param.clinicalFlow}" type="hidden">
        </form>
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>培训年限</th>
                <th>证件号码</th>
                <th>性别</th>
                <th>培训届别</th>
                <th>考核专业</th>
                <th>联系方式</th>
                <th>学员预约时间</th>
                <th>准考证号</th>
            </tr>
            <c:forEach items="${doctorList}" var="item" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${item['doctorName']}</td>
                    <td id="trainingYear${s.index+1}"></td>
                    <td>${item['idNo']}</td>
                    <td>${item['sexName']}</td>
                    <td>${item['sessionNumber']}</td>
                    <td>${item['speName']}</td>
                    <td>${item['userPhone']}</td>
                    <td>${item['appointTime']}</td>
                    <td>${item['ticketNumber']}</td>
                    <script>
                        $("#trainingYear${s.index+1}").text(yearJson['${item['trainingYears']}'])
                    </script>
                </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
