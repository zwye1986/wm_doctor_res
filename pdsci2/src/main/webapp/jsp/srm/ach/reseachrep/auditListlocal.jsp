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

        function audit(reseachrepFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/reseachrep/audit?reseachrepFlow='/>" + reseachrepFlow, "审核", 950, 650);
        }

        function view(reseachrepFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/reseachrep/edit?reseachrepFlow='/>" + reseachrepFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看研究报告信息", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/reseachrep/exportReseachrepExcel/local'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/reseachrep/auditList/local"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;提交时间：
                    <input type="text" name="submitDate" value="${param.submitDate}" class="xltext ctime"
                           style="width: 158px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                </div>
                <div class="searchDiv">
                    &#12288;项目来源：
                    <select name="projSourceId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjSourceList }" var="dict">
                            <option
                                    <c:if test="${param.projSourceId eq dict.dictId }">selected="selected"</c:if>
                                    value="${dict.dictId }">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;报告题目：
                    <input type="text" name="repTitle" value="${param.repTitle}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    &#12288;作&#12288;&#12288;者：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;审核结果：
                    <input type="radio" name="operStatusId" id="achStatusEnumAll"
                           <c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                    <input type="radio" name="operStatusId" id="achStatusEnum_Submit"
                           <c:if test="${empty param.operStatusId }">checked="checked"</c:if>
                           <c:if test="${param.operStatusId eq achStatusEnumSubmit.id}">checked="checked"</c:if>
                           value="${achStatusEnumSubmit.id }"/><label
                        for="achStatusEnum_Submit">${achStatusEnumSubmit.name}</label>
                    <input type="radio" name="operStatusId" id="achStatusEnum_FirstAudit"
                           <c:if test="${param.operStatusId eq achStatusEnumFirstAudit.id }">checked="checked"</c:if>
                           value="${achStatusEnumFirstAudit.id }"/><label
                        for="achStatusEnum_FirstAudit">${achStatusEnumFirstAudit.name}</label>
                </div>
                <div class="searchDiv">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th>报告题目</th>
                <th>采纳对象</th>
                <th>所属作者</th>
                <th>提交时间</th>
                <th>项目来源</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:forEach items="${reseachreps}" var="rep">
                <tr>
                    <td>${rep.repTitle}</td>
                    <td>${rep.acceptObjectName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key==rep.reseachrepFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${pdfn:transDateTime(rep.submitDate)}</td>
                    <td>${rep.projSourceName}</td>
                    <td>${rep.operStatusName}</td>
                    <td>
                        <c:if test="${rep.operStatusId eq achStatusEnumSubmit.id }">
                            <a href="javascript:void(0)" onclick="audit('${rep.reseachrepFlow}');">[审核]</a>
                        </c:if>
                        <c:if test="${rep.operStatusId eq achStatusEnumFirstAudit.id }">
                            <a href="javascript:void(0)" onclick="view('${rep.reseachrepFlow}');">[查看]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%-- <p>
           <input type="hidden" id="currentPage" name="currentPage">
           <c:set var="pageView" value="${pdfn:getPageView2(reseachreps , 10)}" scope="request"></c:set>
           <pd:pagination toPage="toPage"/>
       </p> --%>
    </div>
</div>
</body>
</html>