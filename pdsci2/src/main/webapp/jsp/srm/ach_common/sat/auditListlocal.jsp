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

        function audit(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/audit?satFlow='/>" + satFlow, "审核", width, height);
        }

        function view(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/audit?satFlow='/>" + satFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看科技信息", width, height);
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

        /*$(function () {
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            var selectedFlow = "${param.deptFlow}";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    if (selectedFlow == n.deptFlow) {
                        $("#selectDept").append("<option selected='selected' value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    } else {
                        $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    }
                });
            }, null, false);
        });*/
        function expert() {
            var url = "<s:url value='/srm/ach/sat/exportSatExcel/${scope}'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
        /*function expert() {
            var url = "<s:url value='/srm/ach/manager/exportWXAchExcel/Sat'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));

            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }*/
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
            <form id="searchForm" action="<s:url value="/srm/ach/sat/auditList/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    年&#12288;&#12288;度：
                    <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="ctime"
                           style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>
                </div>
                <div class="searchDiv">
                    奖项名称：
                    <input type="text" name="satName" class="xltext" value="${param.satName}"/>
                </div>
                <div class="searchDiv">
                    获奖类别：
                    <select class="xlselect" name="prizedGradeId" style="width: 168px;">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumPrizedGradeList}">
                            <option value="${dict.dictId}"
                                    <c:if test="${param.prizedGradeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    获奖等级：
                    <select class="xlselect" name="prizedLevelId" style="width: 168px;">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumPrizedLevelList}">
                            <option value="${dict.dictId}"
                                    <c:if test="${param.prizedLevelId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <%--<div class="searchDiv">
                    支&#12288;&#12288;部：
                    <select name="branchId" class="xlselect" style="width: 168px;">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${param.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>--%>
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
                    姓&#12288;&#12288;名：
                    <input type="text" class="xltext" name="authorName" value="${param.authorName}"
                           style="width: 158px;"/>
                </div>
                <div class="searchDiv">
                    工&#12288;&#12288;号：
                    <input type="text" name="workCode" class="xltext" value="${param.workCode}"/>
                </div>
                <div class="searchDiv">
                    审核结果：
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
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <input class="search" type="button" value="导&#12288;出" onclick="expert()"/>
                </div>
                </p>
            </form>
            <form id="exportForm" method="post">
                <input type="hidden" id="jsondata" name="jsondata"/>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="6%">年度</th>
                <th width="10%">奖项类别</th>
                <th width="15%">完成者</th>
                <th width="10%">科室</th>
                <%--<th width="10%">支部</th>--%>
                <th width="20%">奖项名称</th>
                <th width="10%">奖励等级</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <c:if test="${empty satList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${satList}" var="sat" varStatus="satStatus">
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
                <td>${sat.deptName}</td>
                <%--<td>${sat.branchName}</td>--%>
                <td>${sat.satName}</td>
                <td>${sat.prizedLevelName}</td>
                <td>
                    <c:if test="${not (scope eq 'director') and (sat.operStatusId eq achStatusEnumSubmit.id) }">
                        <a href="javascript:void(0)" onclick="audit('${sat.satFlow}');">[审核]</a>&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${not (scope eq 'director') and (sat.operStatusId eq achStatusEnumFirstAudit.id) }">
                        <a href="javascript:void(0)" onclick="audit('${sat.satFlow}');">[编辑]</a>&nbsp;&nbsp;
                    </c:if>
                    <%--<c:if test="${sat.operStatusId eq achStatusEnumFirstAudit.id }">--%>
                        <a href="javascript:void(0)" onclick="view('${sat.satFlow}','look');">[查看]</a>
                    <%--</c:if>--%>
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