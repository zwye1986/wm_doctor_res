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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function edit(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/patent/edit/${scope}?patentFlow='/>" + patentFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看专利信息", width, height);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(patentFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/patent/audit?patentFlow='/>" + patentFlow, "审核", 700, 400);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/patent/exportPatentExcel/charge'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
        $(function(){
            initOrg();
        });

        function initOrg() {
            var datas = [];
            <c:forEach var="org" items="${firstGradeOrgList}">
            var d = {};

            d.id = "${org.orgFlow}";
            d.text = "${org.orgName}";
            datas.push(d);
            </c:forEach>
            var itemSelectFuntion = function () {
                $("#orgFlow").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/patent/auditList/charge"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;专利名称：
                    <input type="text" class="xltext" name="patentName" value="${param.patentName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;申请日期：
                    <input class="xltext ctime" style="width:157px;" type="text" name="applyDate"
                           value="${param.applyDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly">
                </div>
                <div class="searchDiv">
                    &#12288;专利类型：
                    <select name="typeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumPatentTypeList}">
                            <option
                                    <c:if test="${param.typeId eq dict.dictId}">selected="selected"</c:if>
                                    value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;申报单位：
                    <input id="trainOrg" class="xltext" name="orgName" type="text"
                           value="${param.orgName}" autocomplete="off"/>
                    <input id="orgFlow" name="orgFlow" class="input" value="${param.orgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    &#12288;专利作者：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;<input type="button" class="search" onclick="search()" value="查&#12288;询"/>
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <tr style="height: 20px">
                <th>专利名称</th>
                <th>申报单位</th>
                <th>专利发明（设计）人</th>
                <th>专利类型</th>
                <th>专利状态</th>
                <th>申请日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${patentList}" var="patent">
                <c:if test="${patent.operStatusId eq achStatusEnumFirstAudit.id}">
                    <tr>
                        <td>${patent.patentName }</td>
                        <td>${patent.applyOrgName }</td>
                        <td>
                            <c:forEach items="${allAuthorMap}" var="entry">
                                <c:if test="${entry.key == patent.patentFlow}">
                                    <c:forEach items="${entry.value}" var="author">
                                        ${author.authorName}&nbsp;
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${patent.typeName }</td>
                        <td>${patent.statusName }</td>
                        <td>${patent.applyDate }</td>
                        <td>
                            <a href="javascript:void(0)" onclick="edit('${patent.patentFlow}');">[查看]</a>
                        </td>
                    </tr>
                </c:if>
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