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
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(thesisFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/thesis/audit?thesisFlow='/>" + thesisFlow, "审核", width, height);
        }

        function view(thesisFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/thesis/audit?thesisFlow='/>" + thesisFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看论文信息", width, height);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }

       /* function expert() {
            var url = "<s:url value='/srm/ach/manager/exportWXAchExcel/Thesis'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }*/
        function expert() {
            var url = "<s:url value='/srm/ach/thesis/exportThesisExcel/${scope}'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
        function auditProcess(flow) {
            jboxOpen("<s:url value='/srm/achProcess/auditProcess?flow='/>" + flow, "操作记录", width, height);
        }
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/thesis/auditList/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    年&#12288;&#12288;度：
                    <input class="xltext ctime" type="text" name="publishDate" value="${param.publishDate }"
                           style="width: 157px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                </div>
                <div class="searchDiv">
                    项目来源：
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
                    刊物类型：
                    <select name="jourTypeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumJournalTypeList }" var="dict">
                            <option
                                    <c:if test="${param.jourTypeId eq dict.dictId }">selected="selected"</c:if>
                                    value="${dict.dictId }">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    姓&#12288;&#12288;名：
                    <input type="text" name="authorName" value="${param.authorName }" class="xltext"
                           style="width: 157px;"/>
                </div>
                <div class="searchDiv">
                    工&#12288;&#12288;号：
                    <input type="text" name="workCode" class="xltext" value="${param.workCode}"/>
                </div>
                <c:if test="${not (scope eq 'director')}">
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="deptName" type="text"
                           value="${param.deptName}" autocomplete="off"/>
                    <input id="deptFlow" name="deptFlow" class="input" value="${param.deptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                </c:if>
                <div class="searchDiv">
                    支&#12288;&#12288;部：
                    <select name="branchId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${param.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    审核状态：
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
                        for="achStatusEnum_FirstAudit">审核通过</label>
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <input class="search" type="button" value="导&#12288;出" onclick="expert()"/>
                </div>
                </p>
                <input type="hidden" id="currentPage" name="currentPage">
            </form>
            <form id="exportForm" method="post">
                <input type="hidden" id="jsondata" name="jsondata"/>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">年度</th>
                <th width="12%">姓名</th>
                <th width="6%">科室</th>
                <th width="6%">支部</th>
                <th width="17%">论文题目</th>
                <th width="8%">项目来源</th>
                <th width="10%">杂志名称</th>
                <th width="13%">杂志年卷页</th>
                <th width="8%">刊物类型</th>
                <th width="9%">备注</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <c:if test="${not empty thesisList}">
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
                        <td>${thesis.deptName}</td>
                        <td>${thesis.branchName }</td>
                        <td>${thesis.thesisName }</td>
                        <td>${thesis.projSourceName }</td>
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
                            <c:if test="${(thesis.operStatusId eq achStatusEnumSubmit.id) and (not (scope eq 'director')) }">
                                <a href="javascript:void(0)" onclick="audit('${thesis.thesisFlow}');">[审核]</a>&nbsp;&nbsp;
                            </c:if>
                            <c:if test="${not (scope eq 'director') and (thesis.operStatusId eq achStatusEnumFirstAudit.id) }">
                                <a href="javascript:void(0)" onclick="audit('${thesis.thesisFlow}');">[编辑]</a>&nbsp;&nbsp;
                            </c:if>
                            <%--<c:if test="${thesis.operStatusId eq achStatusEnumFirstAudit.id }">--%>
                                <a href="javascript:void(0)"  onclick="view('${thesis.thesisFlow}');">[查看]</a>
                            <%--</c:if>--%>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <p>
            <%--<c:if test="${pageFlag eq 'Y' and not empty thesisList}">--%>
            <c:set var="pageView" value="${pdfn:getPageView2(thesisList , 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
            <%--</c:if>--%>
        </p>
    </div>
</div>
</body>
</html>