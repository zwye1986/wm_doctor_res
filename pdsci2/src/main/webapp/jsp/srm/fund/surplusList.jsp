<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        var height = (window.screen.height) * 0.5;

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            search();
        }

        function surplusFund(userFlow) {
            var width = $('#mainright').width();
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/surplus/surplusFund'/>?userFlow=" + userFlow, "项目结余经费管理", width, 500);
        }

        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });
    </script>
    <style type="text/css">
        a {
            color: #00a0ff;
        }

        a:hover {
            color: #ff6600
        }
    </style>
</head>
<body>

<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/surplus/surplusList/${sessionScope.projListScope}"/>"
                  method="post">
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="deptName" type="text"
                           value="${param.deptName}" autocomplete="off"/>
                    <input id="deptFlow" name="deptFlow" class="input" value="${param.deptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    &#12288;负责人：
                    <input type="text" name="userName" value="${param.userName}" class="xltext"/>
                </div>
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                </div>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="15%">负责人姓名</th>
                <th width="15%">个人科研账号</th>
                <th width="15%">所在科室</th>
                <th width="10%">已结题项目数</th>
                <th width="10%">项目总预算(元)</th>
                <th width="10%">实际到账(元)</th>
                <th width="10%">实际报销(元)</th>
                <th width="10%">剩余总经费(元)</th>
                <th width="20%">操&#12288;作</th>
            </tr>
            </thead>
            <c:forEach items="${surplusInfoList}" var="surplusInfo">
                <tr>
                    <td>${surplusInfo.userName}</td>
                    <td>${surplusInfo.accountNo}</td>
                    <td>${surplusInfo.deptName}</td>
                    <td>${surplusInfo.projCount}</td>
                    <td>${pdfn:transMultiply(surplusInfo.budgetAmount,10000)}</td>
                    <td>${pdfn:transMultiply(surplusInfo.realityAmount,10000)}</td>
                    <td>${pdfn:transMultiply(surplusInfo.yetPaymentAmount,10000)}</td>
                    <td>${pdfn:transMultiply(surplusInfo.realityBalance,10000)}</td>
                    <td>
                        <c:if test="${sessionScope.projListScope eq 'local'}">

                        </c:if>
                        <c:if test="${sessionScope.projListScope eq 'finance'}">
                        <a href="javascript:void(0)" onclick="surplusFund('${surplusInfo.userFlow}')">项目经费结转</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(surplusInfoList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>