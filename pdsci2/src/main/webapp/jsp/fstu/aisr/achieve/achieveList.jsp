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
            jboxOpen("<s:url value='/fstu/aisr/achieve/edit'/>", "奖项信息", width, height);
        }
        function edit(achieveFlow) {
            jboxOpen("<s:url value='/fstu/aisr/achieve/edit'/>?achieveFlow=" + achieveFlow, "编辑奖项信息", width, height);
        }

        function submitAudit(achieveFlow) {
            var url = "<s:url value='/fstu/aisr/achieve/submitAudit?achieveFlow='/>" + achieveFlow;
            jboxConfirm("送审后无法修改，请确认", function () {
                jboxGet(url, null, function (resp) {
                    if (resp == "1") jboxTip("送审成功");
                    else jboxTip("送审失败");
                    window.location.href = "<s:url value='/fstu/aisr/achieve/list'/>";
                }, null, false);
            });
        }

        function view(achieveFlow) {
            jboxOpen("<s:url value='/fstu/aisr/achieve/edit'/>?achieveFlow=" + achieveFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看奖项信息", width, height);
        }
        function auditProcess(flow) {
            jboxOpen("<s:url value='/fstu/aisr/achieve/auditProcess?flow='/>" + flow, "操作记录", width, height);
        }

        function delachieve(achieveFlow) {
            var url = '<s:url value="/fstu/aisr/achieve/delete"/>?achieveFlow=' + achieveFlow;
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

        <%--function audit(){--%>
        <%--jboxStartLoading();--%>
        <%--jboxOpen("<s:url value='/srm/ach/thesis/audit'/>", "审核", width, height);--%>
        <%--}--%>

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        <%--function expert() {--%>
        <%--var url = "<s:url value='/srm/ach/achieve/exportachieveExcel/personal'/>";--%>
        <%--jboxSubmit($('#searchForm'), url, null, null);--%>
        <%--jboxEndLoading();--%>
        <%--}--%>
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/fstu/aisr/achieve/list"/>" method="post">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td>
                            &#12288;成果名称：
                            <input class="xltext" name="achieveName" type="text" value="${param.achieveName}"/>
                            &#12288;获奖日期：
                            <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="ctime"
                                   style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>

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
                <th>成果名称</th>
                <th>成果作者</th>
                <th>获奖级别</th>
                <th>获奖等级</th>
                <th>获奖日期</th>
                <!-- <th>创建时间</th> -->
                <th>审核状态</th>
                <th>操作记录</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${achieveList}" var="achieve">
                <tr>
                    <td>${achieve.achieveName}</td>
                    <td width="20%">
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key==achieve.achieveFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${achieve.prizedGradeName}</td>
                    <td>${achieve.prizedLevelName}</td>
                    <td>${achieve.prizedDate}</td>
                        <%-- <td>${pdfn:transDate(achieve.createTime) }</td> --%>
                    <td>${achieve.operStatusName}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${achieve.achieveFlow}')">[查看操作记录]</a>
                    </td>
                    <td width="15%">
                        <c:choose>
                            <c:when test="${(achieve.operStatusId eq proStatusEnumDraft.id)||(achieve.operStatusId eq proStatusEnumUnPassed.id)}">
                                <a href="javascript:void(0)" onclick="submitAudit('${achieve.achieveFlow}');">[送审]</a>
                                <a href="javascript:void(0)" onclick="edit('${achieve.achieveFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="delachieve('${achieve.achieveFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${achieve.achieveFlow}');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(achieveList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>

</body>
</html>