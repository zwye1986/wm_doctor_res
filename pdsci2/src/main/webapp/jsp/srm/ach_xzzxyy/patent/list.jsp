<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        $(document).ready(function () {
            $("td").css("font-size", "13px");
            $("th").css("font-size", "14px");
        });

        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function edit(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/patent/edit/${scope}?patentFlow='/>" + patentFlow, "编辑专利信息", width, height);
        }

        function add() {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/patent/edit/${scope}'/>", "编辑专利信息", width, height);
        }

        function delPatent(patentFlow) {
            jboxConfirm("确认删除？", function () {
                doDel(patentFlow);
            });
        }

        function doDel(patentFlow) {
            var url = "<s:url value='/srm/ach/patent/delete?patentFlow='/>" + patentFlow;
            jboxStartLoading();
            jboxGet(url, null, function () {
                search();
            }, null, true);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function submitAudit(patentFlow) {
            var url = "<s:url value='/srm/ach/patent/submitAudit?patentFlow='/>" + patentFlow;
            jboxConfirm("确认送审,送审后无法修改?", function () {
                jboxStartLoading();
                jboxGet(url, null, function (resp) {
                    if (resp == "1") {
                        jboxTip("送审成功");
                        window.location.href = "<s:url value='/srm/ach/patent/list/${scope}'/>";
                    }
                }, null, false);
            });
        }

        function view(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/patent/edit/${scope}?patentFlow='/>" + patentFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看专利信息", width, height);
        }

        function auditProcess(flow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/achProcess/auditProcess?flow='/>" + flow, "操作记录", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/patent/exportPatentExcel/personal'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/patent/list/${scope}"/>" method="post">
                <p>
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;专利名称：
                    <input type="text" class="xltext" name="patentName" value="${param.patentName }"/>
                    &#12288;申请日期：
                    <input class="xltext ctime" style="width:160px;" type="text" name="applyDate"
                           value="${param.applyDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                           readonly="readonly"/>
                    <input type="button" class="search" onclick="search()" value="查&#12288;询"/>
                    <input type="button" class="search" onclick="add()" value="新&#12288;增"/>
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </p>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">年度</th>
                <th width="4%">序号</th>
                <th width="9%">姓名</th>
                <th width="10%">科室</th>
                <th width="18%">专利名称</th>
                <th width="9%">专利号</th>
                <th width="9%">专利权人</th>
                <th width="7%">授权公告日</th>
                <th width="7%">类型</th>
                <th width="11%">审核状态</th>
                <th width="11%">操作</th>
            </tr>
            </thead>
            <c:if test="${empty patentList}">
                <tr>
                    <td colspan="11">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${patentList}" var="patent" varStatus="patentStatus">
                <tr style="height: 20px">
                    <td>${patent.authorizeYear}</td>
                    <td>${patentStatus.count}</td>
                    <td title="专利作者：<c:forEach items="${allAuthorMap}" var="entry">
                                <c:if test="${entry.key == patent.patentFlow}">
                                    <c:forEach items="${entry.value}" var="author">
                                        ${author.authorName}&nbsp;
                                    </c:forEach>
                                </c:if>
                            </c:forEach>">${patent.applyUserName}
                            <%--<c:forEach items="${allAuthorMap}" var="entry">
                                <c:if test="${entry.key == patent.patentFlow}">
                                    <c:forEach items="${entry.value}" var="author">
                                        ${author.authorName}&nbsp;
                                    </c:forEach>
                                </c:if>
                            </c:forEach>--%>
                    </td>
                    <td>${patent.deptName}</td>
                    <td>${patent.patentName}</td>
                    <td>${patent.authorizeCode}</td>
                    <td>${patent.patentee}</td>
                    <td>${patent.authorizeDate}</td>
                    <td>${patent.typeName}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${patent.patentFlow}');">${patent.operStatusName}</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${patent.operStatusId != achStatusEnumSubmit.id and patent.operStatusId != achStatusEnumFirstAudit.id}">
                                <c:if test="${patent.operStatusId eq achStatusEnumApply.id or patent.operStatusId eq achStatusEnumRollBack.id}">
                                    <a href="javascript:void(0)" onclick="submitAudit('${patent.patentFlow}');">[送审]</a>
                                </c:if>
                                <a href="javascript:void(0)" onclick="edit('${patent.patentFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="delPatent('${patent.patentFlow}');">[删除]</a>
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