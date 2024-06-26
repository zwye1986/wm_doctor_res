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
    <script type="text/javascript">
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function add() {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/edit/${scope}'/>", "著作信息", width, height);
        }

        function edit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/edit/${scope}'/>?bookFlow=" + bookFlow, "著作信息", width, height);
        }

        function deleBook(bookFlow) {
            var url = '<s:url value="/srm/ach/book/delete"/>?bookFlow=' + bookFlow;
            jboxConfirm("确认删除？", function () {
                jboxStartLoading();
                jboxGet(url, null, function () {
                    window.parent.frames['mainIframe'].location.reload(true);
                }, null, true);
            });
        }

        function submitAudit(bookFlow) {
            var url = "<s:url value='/srm/ach/book/submitAudit?bookFlow='/>" + bookFlow;
            jboxConfirm("确认送审,送审后无法修改?", function () {
                jboxStartLoading();
                jboxGet(url, null, function (resp) {
                    if (resp == "1") {
                        jboxTip("送审成功");
                        window.location.href = "<s:url value='/srm/ach/book/list/${scope}'/>";
                    }
                }, null, false);
            });
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function view(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/edit/${scope}?bookFlow='/>" + bookFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作信息", width, height);
        }

        function auditProcess(flow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/achProcess/auditProcess?flow='/>" + flow + "&local=WXDERMYY", "操作记录", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/manager/exportWXAchExcel/Book/personal'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/book/list/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    著作名称：
                    <input class="xltext" name="bookName" type="text" value="${param.bookName}"/>
                </div>
                <div class="searchDiv">
                    出版日期：
                    <input onClick="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 160px;" class="xlname ctime"
                           name="publishDate" type="text" value="${param.publishDate}"/>
                </div>
                <div class="searchDiv">
                    审核结果：
                    <input type="radio" name="operStatusId" id="achStatusEnum_FirstAudit"
                           <c:if test="${param.operStatusId eq achStatusEnumFirstAudit.id }">checked="checked"</c:if>
                           value="${achStatusEnumFirstAudit.id }"/><label
                        for="achStatusEnum_FirstAudit">审核通过</label>&#12288;
                    <input type="radio" name="operStatusId" id="achStatusEnum_Submit"
                           <c:if test="${param.operStatusId eq achStatusEnumRollBack.id}">checked="checked"</c:if>
                           value="${achStatusEnumRollBack.id }"/><label
                        for="achStatusEnum_Submit">审核不通过</label>&#12288;
                    <input type="radio" name="operStatusId" id="achStatusEnumAll"
                           <c:if test="${empty param.operStatusId }">checked="checked"</c:if>
                           <c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="all"/><label for="achStatusEnumAll">全部</label>
                </div>
                <div class="searchDiv">
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                    <input class="search" type="button" value="新&#12288;增" onclick="add()"/>
                    <input class="search" type="button" value="导&#12288;出" onclick="expert()"/>
                    <input type="hidden" id="currentPage" name="currentPage">
                </div>
                </p>
            </form>
            <form id="exportForm" method="post">
                <input type="hidden" id="jsondata" name="jsondata"/>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="15%">著作名称</th>
                <th width="15%">参编作者</th>
                <th width="10%">著作类别</th>
                <th width="15%">出版单位</th>
                <th width="7%">出版地</th>
                <th width="8%">出版日期</th>
                <th width="8%">审核状态</th>
                <th width="8%">审核意见</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <c:forEach items="${achBookList}" var="achBook" varStatus="achBookStatus">
                <tr>
                    <td>${achBook.bookName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == achBook.bookFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${achBook.typeName}</td>
                    <td>${achBook.publishOrg}</td>
                    <td>${achBook.pubPlaceName}</td>
                    <td>${achBook.publishDate}</td>
                    <td><a href="javascript:void(0);" onclick="auditProcess('${achBook.bookFlow}')">
                        <c:choose>
                            <c:when test="${achBook.operStatusName eq achStatusEnumFirstAudit.name}">审核通过</c:when>
                            <c:when test="${achBook.operStatusName eq achStatusEnumFirstBack.name}">审核不通过</c:when>
                            <c:otherwise>${achBook.operStatusName}</c:otherwise>
                        </c:choose>
                    </a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${achBook.bookFlow}')">查看</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${achBook.operStatusId != achStatusEnumSubmit.id and achBook.operStatusId != achStatusEnumFirstAudit.id}">
                                <c:if test="${achBook.operStatusId eq achStatusEnumApply.id or achBook.operStatusId eq achStatusEnumRollBack.id}">
                                    <a href="javascript:void(0)" onclick="submitAudit('${achBook.bookFlow}');">[送审]</a>
                                </c:if>
                                <a href="javascript:void(0)" onclick="edit('${achBook.bookFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="deleBook('${achBook.bookFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${achBook.bookFlow}','look');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(achBookList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>
</body>
</html>