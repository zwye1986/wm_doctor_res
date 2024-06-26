<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
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
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        $(document).ready(function () {
            $("td").css("font-size", "13px");
            $("th").css("font-size", "14px");
        });


        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/topic/exporttopicExcel/personal'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }

        $(function(){

        })

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/sum/collectHarvest"/>" method="post">
                <p>
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;姓&#12288;&#12288;名：
                    <input type="text" class="xltext" name="name" value="${param.name }"/>
                    &#12288;年&#12288;&#12288;度：
                    <input class="xltext ctime" style="width:160px;" type="text" name="time"
                           value="${param.name }" onClick="WdatePicker({dateFmt:'yyyy'})"
                           readonly="readonly"/>

                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </p>
                <p>
                    &#12288;科&#12288;&#12288;室：
                    <select name="deptFlow" class="xlselect" id = "selectDept">
                        <option value="">请选择</option>
                    </select>
                    <input type="button" class="search" onclick="search()" value="查&#12288;询"/>
                </p>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th rowspan="2">年度</th>
                <th rowspan="2">序号</th>
                <th rowspan="2">姓名</th>
                <th rowspan="2">科室</th>
                <th colspan="4">论文</th>
                <th colspan="2">专著</th>
                <th colspan="5">课题</th>
                <th colspan="3">奖项</th>
                <th colspan="2">专利</th>
            </tr>
            <tr>
                <th>总数</th>
                <th>SCI</th>
                <th>中华</th>
                <th>统计源</th>
                <th>总数</th>
                <th>主编/副主编/编委</th>
                <th>总数</th>
                <th>国家级</th>
                <th>省部级</th>
                <th>市厅级</th>
                <th>其他</th>
                <th>省部级</th>
                <th>市厅级</th>
                <th>其他</th>
                <th>实用</th>
                <th>发明</th>
            </tr>
            </thead>
            <tbody id="sumForm">
            <c:forEach items="${sumFormList}" var="sumForm" varStatus="sumFormNum">
                <tr style="height: 20px">
                    <td>${sumForm.year}</td>
                    <td>${sumFormNum.count}</td>
                    <td>${sumForm.name}</td>
                    <td>${sumForm.dept}</td>
                    <td>${fn:length(sumForm.achThesisList)}</td>
                    <td>${sumForm.thesisSciSum}</td>
                    <td>${sumForm.thesisChSum}</td>
                    <td>${sumForm.thesisTjSum}</td>
                    <td>${fn:length(sumForm.achBookList)}</td>
                    <td>${sumForm.bookAuthorSum}</td>
                    <td>${fn:length(sumForm.achTopicList)}</td>
                    <td>${sumForm.topicChSum}</td>
                    <td>${sumForm.topicProvSum}</td>
                    <td>${sumForm.topicCitySum}</td>
                    <td>${sumForm.topicOthSum}</td>
                    <td>${sumForm.satProvSum}</td>
                    <td>${sumForm.satCitySum}</td>
                    <td>${sumForm.satOthSum}</td>
                    <td>${sumForm.patentPracSum}</td>
                    <td>${sumForm.patentInventSum}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
        <p>
            <%--<c:set var="pageView" value="${pdfn:getPageView2(topicList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>--%>
        </p>
    </div>
</div>
</body>
</html>