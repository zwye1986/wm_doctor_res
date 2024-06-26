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
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/fstu/book/audit?bookFlow='/>" + bookFlow, "审核", 950, 650);
        }

        function view(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/fstu/book/edit?bookFlow='/>" + bookFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作信息", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        <%--function expert(){--%>
        <%--var url = "<s:url value='/fstu/book/exportBookExcel/local'/>";--%>
        <%--jboxSubmit($('#searchForm'),url,null,null);--%>
        <%--jboxEndLoading();--%>
        <%--}--%>
        $(function () {
            var url = "<s:url value='/fstu/thesis/searchDept'/>";
            var selectedFlow = "${param.deptFlow}";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    if (selectedFlow == n.deptFlow) {
                        $("#selectDept").append("<option selected='selected' value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    } else {
                        $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    }
                });
            }, null, false);
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/fstu/book/auditList/deptSch"/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td>
                            <div class="searchDiv">
                                &#12288;著作名称：
                                <input type="text" name="bookName" class="xltext" value="${param.bookName }"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;出版日期：
                                <input class="xltext ctime" type="text" name="publishDate" value="${param.publishDate}"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;参编作者：
                                <input type="text" name="authorName" class="xltext" value="${param.authorName}"
                                       placeholder="输入参编作者全称查询"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;著作类别：
                                <select name="typeId" class="xlselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumFstuBookTypeList }" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${dict.dictId eq param.typeId}">selected="selected"</c:if> >${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                &#12288;发 布 人 ：
                                <input type="text" class="xltext" name="applyUserName" value="${param.applyUserName}"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;发布部门：
                                <input type="text" class="xltext" name="deptName" value="${param.deptName}"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;审核结果：
                                <input type="radio" name="operStatusId" id="achStatusEnumAll"
                                       <c:if test="${empty param.operStatusId }">checked="checked"</c:if>
                                       <c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                       value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                                <input type="radio" name="operStatusId" id="achStatusEnum_Submit"
                                       <c:if test="${param.operStatusId eq proStatusEnumApply.id}">checked="checked"</c:if>
                                       value="${proStatusEnumApply.id }"/><label
                                    for="achStatusEnum_Submit">${proStatusEnumApply.name}</label>
                                <input type="radio" name="operStatusId" id="achStatusEnum_FirstAudit"
                                       <c:if test="${param.operStatusId eq proStatusEnumPassed.id }">checked="checked"</c:if>
                                       value="${proStatusEnumPassed.id }"/><label
                                    for="achStatusEnum_FirstAudit">${proStatusEnumPassed.name}</label>
                            </div>
                            <div class="searchDiv">
                                &#12288;
                                <input type="button" class="search" onclick="search();" value="查&#12288;询">
                                <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <table class="xllist">
            <tr style="height: 20px">
                <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                    <th>序号</th>
                </c:if>
                <th>著作名称</th>
                <th>参编作者</th>
                <th>出版日期</th>
                <th>出版单位</th>
                <th>出版地</th>
                <th>发布人</th>
                <th>发布部门</th>
                <th>项目来源</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${bookList}" var="book" varStatus="bookList">
                <tr>
                    <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                        <td>${bookList.count}</td>
                    </c:if>
                    <td>${book.bookName }</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == book.bookFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${book.publishDate}</td>
                    <td>${book.publishOrg}</td>
                    <td>${book.pubPlaceName}</td>
                    <td>${book.applyUserName}</td>
                    <td>${book.deptName}</td>
                    <td>${book.projSourceName}</td>
                    <td>${book.operStatusName}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="view('${book.bookFlow}');">[查看]</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:if test="${pageFlag eq 'Y'}">
                <c:set var="pageView" value="${pdfn:getPageView2(bookList, 10)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </c:if>
        </p>
    </div>
</div>
</body>
</html>