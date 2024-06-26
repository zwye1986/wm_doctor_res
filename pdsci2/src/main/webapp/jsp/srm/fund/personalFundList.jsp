<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
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

        function personalFund(userFlow) {
            var width = $('#mainright').width();
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/surplus/personalFund/${sessionScope.projListScope}'/>?userFlow=" + userFlow, "个人经费管理", width, 500);
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
        function surplusHistory(fundFlow) {
            /*var width = $('#mainright').width();*/
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/surplus/surplusHistory'/>?fundFlow=" + fundFlow, "经费结余记录", 650, 370);
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
            <form id="searchForm" action="<s:url value="/srm/surplus/personalList/${sessionScope.projListScope}"/>"
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
                <th width="15%">姓名</th>
                <th width="15%">所在科室</th>
                <th width="15%">科研账号</th>
                <th width="10%">个人经费总额(元)</th>
                <th width="10%">已报销金额(元)</th>
                <th width="10%">剩余金额(元)</th>
                <th width="20%">操&#12288;作</th>
            </tr>
            </thead>
            <c:forEach items="${fundInfoList}" var="fundInfo">
                <tr>
                    <td>${fundInfo.sysUser.userName}</td>
                    <td>${fundInfo.sysUser.deptName}</td>
                    <td>${fundInfo.accountNo}</td>
                    <td><a href="javascript:void(0)" onclick="surplusHistory('${fundInfo.fundFlow}')">${pdfn:transMultiply(fundInfo.realityAmount,10000)}</a></td>
                    <td>${pdfn:transMultiply(fundInfo.yetPaymentAmount,10000)}</td>
                    <td>${pdfn:transMultiply(fundInfo.realityBalance,10000)}</td>
                    <td>
                        <c:if test="${sessionScope.projListScope eq 'local'}">
                            <a href="javascript:void(0)" onclick="personalFund('${fundInfo.projFlow}')">[查询]</a>&#12288;
                        </c:if>
                        <c:if test="${sessionScope.projListScope eq 'finance'}">
                        <a href="javascript:void(0)" onclick="personalFund('${fundInfo.projFlow}')">[使用]</a>&#12288;
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(fundInfoList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>