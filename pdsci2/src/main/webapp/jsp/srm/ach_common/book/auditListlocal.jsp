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

        function audit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/audit?bookFlow='/>" + bookFlow, "审核", width, height);
        }

        function view(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/audit?bookFlow='/>" + bookFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作信息", width, height);
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
            var url = "<s:url value='/srm/ach/manager/exportWXAchExcel/Book/${scope}'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
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
            <form id="searchForm" action="<s:url value="/srm/ach/book/auditList/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;年&#12288;&#12288;度：
                    <input class="xltext ctime" style="width: 158px;" type="text" name="publishDate"
                           value="${param.publishDate}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                </div>
                <div class="searchDiv">
                    &#12288;著作类别：
                    <select name="typeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumAchBookTypeList }" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${dict.dictId eq param.typeId}">selected="selected"</c:if> >${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${not (scope eq 'director')}">
                    <div class="searchDiv">
                        &#12288;科&#12288;&#12288;室：
                        <input id="trainDept" class="xltext" name="deptName" type="text"
                               value="${param.deptName}" autocomplete="off"/>
                        <input id="deptFlow" name="deptFlow" class="input" value="${param.deptFlow}" type="text"
                               hidden style="margin-left: 0px;"/>
                    </div>
                </c:if>
                <div class="searchDiv">
                    &#12288;支&#12288;&#12288;部：
                    <select name="branchId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${param.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;姓&#12288;&#12288;名：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;工&#12288;&#12288;号：
                    <input type="text" name="workCode" class="xltext" value="${param.workCode}"/>
                </div>
                <div class="searchDiv">
                    &#12288;著作名称：
                    <input type="text" name="bookName" class="xltext" value="${param.bookName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;审核状态：
                    <input type="radio" name="operStatusId" id="achStatusEnumAll"
                           <c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                    <input type="radio" name="operStatusId" id="achStatusEnum_Submit"
                           <c:if test="${empty param.operStatusId }">checked="checked"</c:if>
                           <c:if test="${param.operStatusId eq achStatusEnumSubmit.id}">checked="checked"</c:if>
                           value="${achStatusEnumSubmit.id }"/><label
                        for="achStatusEnum_Submit">待审核</label>
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
            <tr style="height: 20px">
                <th width="5%">年度</th>
                <th width="18%">著作名称</th>
                <th width="12%">参编作者</th>
                <th width="8%">科室</th>
                <th width="7%">支部</th>
                <th width="7%">著作类别</th>
                <th width="7%">出版单位</th>
                <th width="7%">出版地</th>
                <th width="7%">出版日期</th>
                <th width="10%">操作</th>
            </tr>
            <c:if test="${empty bookList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${bookList}" var="achBook" varStatus="achBookStatus">
                <tr>
                    <td>${fn:substring(achBook.publishDate, 0, 4)}</td>
                    <td>${achBook.bookName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == achBook.bookFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${achBook.deptName}</td>
                    <td>${achBook.branchName}</td>
                    <td>${achBook.typeName}</td>
                    <td>${achBook.publishOrg}</td>
                    <td>${achBook.pubPlaceName}</td>
                    <td>${achBook.publishDate}</td>
                    <td>
                        <c:if test="${not (scope eq 'director') and (achBook.operStatusId eq achStatusEnumSubmit.id) }">
                            <a href="javascript:void(0)" onclick="audit('${achBook.bookFlow}');">[审核]</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${not (scope eq 'director') and (achBook.operStatusId eq achStatusEnumFirstAudit.id) }">
                            <a href="javascript:void(0)"  onclick="audit('${achBook.bookFlow}');">[编辑]</a>&nbsp;&nbsp;
                        </c:if>
                            <%--<c:if test="${achBook.operStatusId eq achStatusEnumFirstAudit.id }">--%>
                            <a href="javascript:void(0)" onclick="view('${achBook.bookFlow}');">[查看]</a>
                        <%--</c:if>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(bookList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>
</body>
</html>