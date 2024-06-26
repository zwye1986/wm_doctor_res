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
            jboxOpen("<s:url value='/srm/ach/sat/edit/${scope}'/>", "奖项信息", width, height);
        }
        function edit(satFlow) {
            jboxOpen("<s:url value='/srm/ach/sat/edit/${scope}'/>?satFlow=" + satFlow, "编辑奖项信息", width, height);
        }

        function submitAudit(satFlow) {
            var url = "<s:url value='/srm/ach/sat/submitAudit?satFlow='/>" + satFlow;
            jboxConfirm("确认送审,送审后无法修改?", function () {
                jboxGet(url, null, function (resp) {
                    jboxTip("送审成功");
                    window.location.href = "<s:url value='/srm/ach/sat/list/${scope}'/>";
                }, null, false);
            });
        }

        function view(satFlow) {
            jboxOpen("<s:url value='/srm/ach/sat/edit/${scope}'/>?satFlow=" + satFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看奖项信息", width, height);
        }
        function auditProcess(flow) {
            jboxOpen("<s:url value='/srm/achProcess/auditProcess?flow='/>" + flow + "&local=WXDERMYY", "操作记录", width, height);
        }

        function delSat(satFlow) {
            var url = '<s:url value="/srm/ach/sat/delete"/>?satFlow=' + satFlow;
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
        function expert() {
            var url = "<s:url value='/srm/ach/sat/exportSatExcel/personal'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/sat/list/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                奖项名称：
                    <input class="xltext" name="satName" type="text" value="${param.satName}"/>
                </div>
                <div class="searchDiv">
                    奖项类别：
                    <select class="xlselect" name=prizedGradeId style="width: 168px;">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumPrizedGradeList}">
                            <option value="${dict.dictId}"
                                    <c:if test="${param.prizedGradeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <%--<div class="searchDiv">
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
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                </div>--%>
                <div class="searchDiv">
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                    <input class="search" type="button" value="新&#12288;增" onclick="add()"/>
                    <input class="search" type="button" value="导&#12288;出" onclick="expert()"/>
                    <input type="hidden" id="currentPage" name="currentPage">
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <tr>
                <th width="6%">年度</th>
                <th width="10%">奖项类别</th>
                <th width="15%">完成者</th>
                <th width="20%">奖项名称</th>
                <th width="10%">奖励等级</th>
                <th width="10%">审核状态</th>
                <th width="10%">审核意见</th>
                <th width="15%">操作</th>
            </tr>
            <c:if test="${empty satList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${satList}" var="sat" varStatus="satStatus">
                <tr>
                    <td>${sat.prizedDate}</td>
                    <td>${sat.prizedGradeName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key==sat.satFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${sat.satName}</td>
                    <td>${sat.prizedLevelName}</td>
                    <td>
                            <c:choose>
                                <c:when test="${sat.operStatusName eq achStatusEnumFirstAudit.name}">审核通过</c:when>
                                <c:when test="${sat.operStatusName eq achStatusEnumFirstBack.name}">审核不通过</c:when>
                                <c:otherwise>${sat.operStatusName}</c:otherwise>
                            </c:choose>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${sat.satFlow}')">查看</a>
                    </td>
                    <td width="15%">
                        <c:choose>
                            <c:when test="${sat.operStatusId != achStatusEnumSubmit.id and sat.operStatusId != achStatusEnumFirstAudit.id}">
                                <c:if test="${sat.operStatusId eq achStatusEnumApply.id or sat.operStatusId eq achStatusEnumRollBack.id}">
                                    <a href="javascript:void(0)" onclick="submitAudit('${sat.satFlow}');">[送审]</a>
                                </c:if>
                                <a href="javascript:void(0)" onclick="edit('${sat.satFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="delSat('${sat.satFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${sat.satFlow}');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
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