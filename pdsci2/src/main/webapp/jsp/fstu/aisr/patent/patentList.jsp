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
            jboxOpen("<s:url value='/fstu/aisr/patent/edit'/>", "项目信息", width, height);
        }
        function edit(patentFlow) {
            jboxOpen("<s:url value='/fstu/aisr/patent/edit'/>?patentFlow=" + patentFlow, "编辑项目信息", width, height);
        }

        function submitAudit(patentFlow) {
            var url = "<s:url value='/fstu/aisr/patent/submitAudit?patentFlow='/>" + patentFlow;
            jboxConfirm("送审后无法修改，请确认", function () {
                jboxGet(url, null, function (resp) {
                    if (resp == "1") jboxTip("送审成功");
                    else jboxTip("送审失败");
                    window.location.href = "<s:url value='/fstu/aisr/patent/list'/>";
                }, null, false);
            });
        }

        function view(patentFlow) {
            jboxOpen("<s:url value='/fstu/aisr/patent/edit'/>?patentFlow=" + patentFlow + "&editFlag=${GlobalConstant.FLAG_N}", "项目信息", width, height);
        }
        function auditProcess(flow) {
            jboxOpen("<s:url value='/fstu/aisr/patent/auditProcess?flow='/>" + flow, "操作记录", width, height);
        }

        function delpatent(patentFlow) {
            var url = '<s:url value="/fstu/aisr/patent/delete"/>?patentFlow=' + patentFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    window.parent.frames['mainIframe'].location.reload(true);
                }, null, true);
            });
        }
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

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/fstu/aisr/patent/list"/>" method="post">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td>
                            年&#12288;&#12288;份：
                            <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="ctime"
                                   style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>
                            &#12288;项目名称：
                            <input class="xltext" name="patentName" type="text" value="${param.patentName}"/>

                            <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                            <input class="search" type="button" value="新&#12288;增" onclick="add()"/>
                            <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                            <input type="hidden" id="currentPage" name="currentPage">
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <table class="xllist">
            <tr>
                <th>年&#12288;&#12288;份</th>
                <th>项目名称</th>
                <th>项目负责人</th>
                <%--<th>获奖级别</th>--%>
                <%--<th>获奖等级</th>--%>
                <%--<th>获奖日期</th>--%>
                <!-- <th>创建时间</th> -->
                <th>起止日期</th>
                <th>项目来源</th>
                <th>审核状态</th>
                <th>操作记录</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${patentList}" var="patent">
                <tr>
                    <td>${patent.prizedDate}</td>
                    <td>${patent.patentName}</td>
                    <td>${patent.patentChangeMan}</td>
                    <%--<td width="20%">--%>
                        <%--<c:forEach items="${allAuthorMap}" var="entry">--%>
                            <%--<c:if test="${entry.key==patent.patentFlow}">--%>
                                <%--<c:forEach items="${entry.value}" var="author">--%>
                                    <%--${author.authorName}&nbsp;--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</td>--%>
                    <%--<td>${patent.prizedGradeName}</td>--%>
                    <%--<td>${patent.prizedLevelName}</td>--%>
                    <%--<td>${patent.prizedDate}</td>--%>
                        <%-- <td>${pdfn:transDate(patent.createTime) }</td> --%>
                    <td>${patent.prizedStartDate}~${patent.prizedEndDate}</td>
                    <td>${patent.projSourceName}</td>
                    <td>${patent.operStatusName}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${patent.patentFlow}')">[查看操作记录]</a>
                    </td>
                    <td width="15%">
                        <c:choose>
                            <c:when test="${(patent.operStatusId eq proStatusEnumDraft.id)||(patent.operStatusId eq proStatusEnumUnPassed.id)}">
                                <a href="javascript:void(0)" onclick="submitAudit('${patent.patentFlow}');">[送审]</a>
                                <a href="javascript:void(0)" onclick="edit('${patent.patentFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="delpatent('${patent.patentFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${patent.patentFlow}');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(patentList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>

</body>
</html>