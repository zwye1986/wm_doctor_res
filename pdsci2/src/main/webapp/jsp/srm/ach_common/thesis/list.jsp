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
        var width = (window.screen.width) * 0.7;
        var height = (window.screen.height) * 0.7;
        function add() {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/thesis/edit/${scope}'/>", "编辑论文信息", width, height);
        }

        function delThesis(thesisFlow) {
            url = "<s:url value='/srm/ach/thesis/delete?thesisFlow='/>" + thesisFlow;
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

        function edit(thesisFlow) {
            jboxOpen("<s:url value='/srm/ach/thesis/edit/${scope}?thesisFlow='/>" + thesisFlow, "编辑论文信息", width, height);
        }

        function submitAudit(thesisFlow) {
            var url = "<s:url value='/srm/ach/thesis/submitAudit?thesisFlow='/>" + thesisFlow;
            jboxConfirm("确认送审,送审后无法修改?", function () {
                jboxGet(url, null, function (resp) {
                    if (resp == "1") {
                        jboxTip("送审成功");
                        window.location.href = "<s:url value='/srm/ach/thesis/list/${scope}'/>";
                    }
                }, null, false);
            });
        }

        function view(thesisFlow) {
            jboxOpen("<s:url value='/srm/ach/thesis/edit/${scope}?thesisFlow='/>" + thesisFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看论文信息", width, height);
        }

        function auditProcess(flow) {
            jboxOpen("<s:url value='/srm/achProcess/auditProcess?flow='/>" + flow + "&local=WXDERMYY", "操作记录", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/thesis/exportThesisExcel/personal'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/thesis/list/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    发表/出版日期：
                    <input class="xltext ctime" type="text" name="publishDate"
                           value="${param.publishDate}" onClick="WdatePicker({dateFmt:'yyyy'})" class="ctime"
                           readonly="readonly"/>
                </div>
                <div class="searchDiv">
                    论文题目：
                    <input type="text" name="thesisName" value="${param.thesisName}" class="xltext"/>
                </div>
                <%--<div class="searchDiv">
                    审核状态：
                    <input type="radio" name="operStatusId" id="achStatusEnum_FirstAudit"
                           <c:if test="${param.operStatusId eq achStatusEnumFirstAudit.id }">checked="checked"</c:if>
                           value="${achStatusEnumFirstAudit.id }"/><label
                        for="achStatusEnum_FirstAudit">审核通过</label>&#12288;
                    <input type="radio" name="operStatusId" id="achStatusEnum_Submit"
                           <c:if test="${param.operStatusId eq achStatusEnumSubmit.id}">checked="checked"</c:if>
                           value="${achStatusEnumSubmit.id }"/><label
                        for="achStatusEnum_Submit">待审核</label>&#12288;
                    <input type="radio" name="operStatusId" id="achStatusEnumAll"
                           <c:if test="${empty param.operStatusId }">checked="checked"</c:if>
                           <c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>&#12288;
                </div>--%>

                <div class="searchDiv">
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <input type="button" class="search" onclick="add();" value="新&#12288;增">
                    <input class="search" type="button" value="导&#12288;出" onclick="expert()"/>
                    <input type="hidden" id="currentPage" name="currentPage">
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="6%">年度</th>
                <th width="10%">姓名</th>
                <th width="14%">论文题目</th>
                <th width="10%">杂志名称</th>
                <th width="14%">杂志年卷页</th>
                <th width="8%">刊物类型</th>
                <th width="12%">备注</th>
                <th width="8%">审核状态</th>
                <th width="8%">审核意见</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <c:if test="${empty thesisList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${thesisList}" var="thesis" varStatus="thesisStatus">
                <tr>
                    <td>${thesis.publishDate}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key==thesis.thesisFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${thesis.thesisName }</td>
                    <td>${thesis.publishJour }</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty thesis.jourYear || (thesis.volumeCode) || (thesis.pageNoRange)}">
                                ${thesis.jourCode}
                            </c:when>
                            <c:otherwise>
                                ${thesis.jourYear}，${thesis.volumeCode}（${thesis.jourCode}）：${thesis.pageNoRange}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${thesis.jourTypeName}</td>
                    <td>${thesis.remark}</td>
                    <td>
                        <c:choose>
                            <c:when test="${thesis.operStatusName eq achStatusEnumFirstAudit.name}">审核通过</c:when>
                            <c:when test="${thesis.operStatusName eq achStatusEnumFirstBack.name}">审核不通过</c:when>
                            <c:otherwise>${thesis.operStatusName}</c:otherwise>
                        </c:choose></a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${thesis.thesisFlow}')">查看</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${thesis.operStatusId != achStatusEnumSubmit.id and thesis.operStatusId != achStatusEnumFirstAudit.id}">
                                <c:if test="${thesis.operStatusId eq achStatusEnumApply.id or thesis.operStatusId eq achStatusEnumRollBack.id}">
                                    <a href="javascript:void(0)" onclick="submitAudit('${thesis.thesisFlow}');">[送审]</a>
                                </c:if>
                                <a href="javascript:void(0)" onclick="edit('${thesis.thesisFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="delThesis('${thesis.thesisFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${thesis.thesisFlow}');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView(thesisList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>
</body>
</html>