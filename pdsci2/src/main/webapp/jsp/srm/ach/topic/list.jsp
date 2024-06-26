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
        function edit(topicFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/topic/edit?topicFlow='/>" + topicFlow, "编辑课题信息", width, height);
        }

        function add() {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/topic/edit'/>", "编辑课题信息", width, height);
        }

        function deltopic(topicFlow) {
            jboxConfirm("确认删除？", function () {
                doDel(topicFlow);
            });
        }

        function doDel(topicFlow) {
            var url = "<s:url value='/srm/ach/topic/delete?topicFlow='/>" + topicFlow;
            jboxStartLoading();
            jboxGet(url, null, function () {
                search();
            }, null, true);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function submitAudit(topicFlow) {
            var url = "<s:url value='/srm/ach/topic/submitAudit?topicFlow='/>" + topicFlow;
            jboxConfirm("确认送审,送审后无法修改?", function () {
                jboxStartLoading();
                jboxGet(url, null, function (resp) {
                    if (resp == "1") {
                        jboxTip("送审成功");
                        window.location.href = "<s:url value='/srm/ach/topic/list'/>";
                    }
                }, null, false);
            });
        }

        function view(topicFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/topic/edit?topicFlow='/>" + topicFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看课题信息", width, height);
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
            var url = "<s:url value='/srm/ach/topic/exporttopicExcel/personal'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/topic/list"/>" method="post">
                <p>
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;课题名称：
                    <input type="text" class="xltext" name="topicName" value="${param.topicName }"/>
                    &#12288;课题开始时间日期：
                    <input class="xltext ctime" style="width:160px;" type="text" name="topicStartTime"
                           value="${param.topicStartTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
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
                <th width="10%">年度</th>
                <th width="20%">课题名称</th>
                <th width="15%">课题申请人</th>
                <th width="10%">科室</th>
                <th width="10%">主管部门</th>
                <th width="10%">审核状态</th>
                <th width="10%">操作记录</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <c:forEach items="${topicList}" var="topic">
                <tr style="height: 20px">
                    <td>${fn:substring(topic.topicStartTime, 0, 4)}</td>
                    <td>${topic.topicName}</td>
                    <td>${topic.applyUserName}</td>
                    <td>${topic.deptName}</td>
                    <td>${topic.chargeOrg}</td>
                    <td>${topic.operStatusName}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="auditProcess('${topic.topicFlow}');">[查看操作记录]</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${topic.operStatusId != achStatusEnumSubmit.id and topic.operStatusId != achStatusEnumFirstAudit.id}">
                                <c:if test="${topic.operStatusId eq achStatusEnumApply.id or topic.operStatusId eq achStatusEnumRollBack.id}">
                                    <a href="javascript:void(0)" onclick="submitAudit('${topic.topicFlow}');">[送审]</a>
                                </c:if>
                                <a href="javascript:void(0)" onclick="edit('${topic.topicFlow}');">[编辑]</a>
                                <a href="javascript:void(0)" onclick="deltopic('${topic.topicFlow}');">[删除]</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="view('${topic.topicFlow}');">[查看]</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(topicList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>
</body>
</html>