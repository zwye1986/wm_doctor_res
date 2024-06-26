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

        function audit(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/fstu/aisr/patent/audit?patentFlow='/>" + patentFlow, "审核", 950, 650);
        }

        function view(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/fstu/aisr/patent/edit?patentFlow='/>" + patentFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看专利信息", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        <%--function expert() {--%>
        <%--var url = "<s:url value='/srm/ach/patent/exportpatentExcel/local'/>";--%>
        <%--jboxSubmit($('#searchForm'), url, null, null);--%>
        <%--jboxEndLoading();--%>
        <%--}--%>
        <%--$(function(){--%>
        <%--var url = "<s:url value='/srm/ach/topic/searchDept'/>";--%>
        <%--var selectedFlow="${param.deptFlow}";--%>
        <%--jboxPost(url,null,function(resp){--%>
        <%--$.each(resp,function(i,n){--%>
        <%--if(selectedFlow == n.deptFlow){--%>
        <%--$("#selectDept").append("<option selected='selected' value='"+ n.deptFlow +"'>"+ n.deptName +"</option>");--%>
        <%--}else {--%>
        <%--$("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");--%>
        <%--}--%>
        <%--});--%>
        <%--},null,false);--%>
        <%--});--%>
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/fstu/aisr/patent/auditList/${role}"/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td>
                            <div class="searchDiv">
                                &#12288;年&#12288;&#12288;份：
                                <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="ctime"
                                       style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>
                            </div>
                            <div class="searchDiv">
                                项目负责人：
                                <input class="xltext" name="patentChangeMan" type="text" value="${param.patentChangeMan}"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;项目名称：
                                <input type="text" name="patentName" class="xltext" value="${param.patentName}"/>
                            </div>
                            <div class="searchDiv">
                                &#12288;项目来源：
                                <select name="projSourceId" class="xlselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumProSourceList }" var="dict">
                                        <option
                                                <c:if test="${param.projSourceId eq dict.dictId }">selected="selected"</c:if>
                                                value="${dict.dictId }">${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                &#12288;审核结果：
                                <input type="radio" name="operStatusId" id="all"
                                       <c:if test="${(param.operStatusId eq 'all')||((empty param.operStatusId)&&(role eq 'admin'))}">checked="checked"</c:if>
                                       value="all"/><label for="all">全部</label>
                                <input type="radio" name="operStatusId" id="apply"
                                       <c:if test="${(param.operStatusId eq proStatusEnumApply.id )||((empty param.operStatusId)&&(role eq 'adminAudit'))}">checked="checked"
                                       </c:if>value="${proStatusEnumApply.id }"/><label
                                    for="apply">${proStatusEnumApply.name}</label>
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
            <thead>
            <tr>
                <th>年&#12288;&#12288;份</th>
                <th>项目名称</th>
                <th>项目负责人</th>
                <th>起止日期</th>
                <th>项目来源</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:forEach items="${patentList}" var="patent" varStatus="patentNum">
                <tr>
                    <td>${patent.prizedDate}</td>
                    <td>${patent.patentName}</td>
                    <td>${patent.patentChangeMan}</td>
                    <td>${patent.prizedStartDate}~${patent.prizedEndDate}</td>
                    <td>${patent.projSourceName}</td>
                    <td>${patent.operStatusName}</td>
                    <td>
                        <c:if test="${patent.operStatusId eq proStatusEnumApply.id && (role eq 'adminAudit')}">
                            <a href="javascript:void(0)" onclick="audit('${patent.patentFlow}');">[审核]</a>
                        </c:if>
                        <c:if test="${patent.operStatusId eq proStatusEnumPassed.id || role eq 'deptMaster' || role eq 'admin'}">
                            <a href="javascript:void(0)" onclick="view('${patent.patentFlow}');">[查看]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:if test="${pageFlag eq 'Y'}">
                <c:set var="pageView" value="${pdfn:getPageView2(patentList, 10)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </c:if>
        </p>
    </div>
</div>

</body>
</html>