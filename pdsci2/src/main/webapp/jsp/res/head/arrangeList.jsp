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
        <jsp:param name="jquery_fullcalendar" value="false" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
    </jsp:include>
    <style>
        .doctorTypeDiv {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
        .doctorTypeLabel {
            border: 0px;
            float: left;
            width: 96px;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
        .doctorTypeContent {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
    </style>
    <script type="text/javascript">

        function search(page) {
            var startDate=$("#schStartDate").val();
            var endDate=$("#schEndDate").val();

            if(startDate!=""&&endDate!=""&&endDate<startDate)
            {
                jboxTip("开始时间不得大于结束时间！！");
                return false;
            }
            jboxStartLoading();
            if (!page) {
                page = 1;
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function toPage(page) {
            search(page);
        }



        //计划安排
        function inDept(doctorFlow,doctorName,deptFlow,deptName,schStartDate,schEndDate){

            var url = "<s:url value='/res/head/inDept?doctorFlow='/>"+doctorFlow+"&deptFlow="+deptFlow+"&doctorName="+encodeURI(encodeURI(doctorName))+"&deptName="+encodeURI(encodeURI(deptName))+"&schStartDate="+schStartDate+"&schEndDate="+schEndDate;
            window.location.href = url;
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/head/arrange'/>" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
                    <div class="inputDiv" style="max-width: 280px;min-width: 280px;">
                      时间区间：
                        <input class="qtext" name="schStartDate" id="schStartDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"
                               value="${empty param.schStartDate?schStartDate:param.schStartDate}" class="input" style="width: 80px;"/>~<input class="qtext" name="schEndDate" id="schEndDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty param.schEndDate?endDate:param.schEndDate}" class="input" style="width: 80px;"/>
                    </div>
                    <div class="inputDiv">
                        姓&#12288;&#12288;名：
                        <input type="text" name="doctorName" class="qtext" value="${param.doctorName}"/>
                    </div>
                    <div class="inputDiv" style="text-align: left;padding-left: 23px;">
                        <input class="search" type="button" value="查&#12288;询" onclick="search(1);"/>&#12288;
                    </div>
                </form>
            </div>
            <table class="xllist">
                <thead>
                <tr>
                    <th width="15%">姓名</th>
                    <th width="30%">进修专业</th>
                    <th width="20%">开始时间</th>
                    <th width="20%">结束时间</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${arrangeList}" var="arrange">
                    <tr>
                        <td>${arrange.doctorName}</td>
                        <td>${arrange.deptName}</td>
                        <td>${arrange.schStartDate}</td>
                        <td>${arrange.schEndDate}</td>
                        <td><a style="cursor: pointer;color: #00A8FF"
                               onclick="inDept('${arrange.doctorFlow}','${arrange.doctorName}',
                                       '${arrange.deptFlow}','${arrange.deptName}','${arrange.schStartDate}','${arrange.schEndDate}')">计划安排</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <c:set var="pageView" value="${pdfn:getPageView(arrangeList)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

