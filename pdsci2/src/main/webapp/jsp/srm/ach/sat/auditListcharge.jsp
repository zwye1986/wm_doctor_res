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
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function edit(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/edit/${scope}'/>?satFlow=" + satFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看科技信息", width, height);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/audit?satFlow='/>" + satFlow, "审核", 700, 400);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/sat/exportSatExcel/charge'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
        $(function(){
            initOrg();
        });

        function initOrg() {
            var datas = [];
            <c:forEach var="org" items="${firstGradeOrgList}">
            var d = {};

            d.id = "${org.orgFlow}";
            d.text = "${org.orgName}";
            datas.push(d);
            </c:forEach>
            var itemSelectFuntion = function () {
                $("#orgFlow").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/sat/auditList/charge"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;科技名称：
                    <input type="text" name="satName" class="xltext" value="${param.satName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;获奖日期：
                    <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="ctime"
                           style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>
                </div>
                <div class="searchDiv">
                    &#12288;申报单位：
                    <input id="trainOrg" class="xltext" name="orgName" type="text"
                           value="${param.orgName}" autocomplete="off"/>
                    <input id="orgFlow" name="orgFlow" class="input" value="${param.orgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    &#12288;作&#12288;&#12288;者：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </div>
                </p>
                <input type="hidden" id="currentPage" name="currentPage">
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th>科技名称</th>
                <th>科技作者</th>
                <th>获奖级别</th>
                <th>获奖等级</th>
                <th>获奖日期</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:forEach items="${satList}" var="sat">
                <tr>
                    <td>${sat.satName }</td>
                    <td width="20%">
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key==sat.satFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${sat.prizedGradeName}</td>
                    <td>${sat.prizedLevelName}</td>
                    <td>${sat.prizedDate}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="edit('${sat.satFlow}');">[查看]</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(satList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>
</body>
</html>