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

        function audit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/audit?bookFlow='/>" + bookFlow, "审核", 950, height);
        }

        function view(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/edit/${scope}?bookFlow='/>" + bookFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作信息", width, height);
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
            var url = "<s:url value='/srm/ach/manager/exportAchExcel/Book'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }
        $(function () {
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
                    &#12288;著作名称：
                    <input type="text" name="bookName" class="xltext" value="${param.bookName }"/>
                </div>
                <div class="searchDiv">
                    &#12288;出版年份：
                    <input class="xltext ctime" style="width: 158px;" type="text" name="publishDate"
                           value="${param.publishDate}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                </div>
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <select name="deptFlow" class="xlselect" id="selectDept">
                        <option value=""></option>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;参编作者：
                    <input type="text" class="xltext" name="authorName" value="${param.authorName}"/>
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
                <th width="5%">序号</th>
                <th width="10%">姓名</th>
                <th width="8%">科室</th>
                <th width="7%">编委会</th>
                <th width="20%">著作名称</th>
                <th width="5%">出版社</th>
                <th width="5%">出版号</th>
                <th width="5%">字数（万）</th>
                <th width="10%">审核状态</th>
                <th width="15%">操作</th>
            </tr>
            <c:if test="${empty bookList}">
                <tr>
                    <td colspan="11">无记录！</td>
                </tr>
            </c:if>
            <c:forEach items="${bookList}" var="achBook" varStatus="achBookStatus">
                <tr>
                    <td>${achBook.publishDate}</td>
                    <td>${achBookStatus.count}</td>
                    <td title="专著作者：<c:forEach items="${allAuthorMap}" var="entry">
		      		<c:if test="${entry.key == achBook.bookFlow}">
		      			<c:forEach items="${entry.value}" var="author">
		      				${author.authorName}&nbsp;
		      			</c:forEach>
		      		</c:if>
		      	</c:forEach>">${achBook.applyUserName}</td>
                    <td>${achBook.deptName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == achBook.bookFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    <c:if test="${achBook.applyUserName == author.authorName}">${author.writeTypeName}</c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${achBook.bookName}</td>
                    <td>${achBook.publishOrg}</td>
                    <td>${achBook.isbnCode}</td>
                    <td>${achBook.wordCount}</td>
                    <td><a href="javascript:void(0);"
                           onclick="auditProcess('${achBook.bookFlow}')">${achBook.operStatusName}</a></td>
                    <td>
                        <c:if test="${achBook.operStatusId eq achStatusEnumSubmit.id }">
                            <a href="javascript:void(0)" onclick="audit('${achBook.bookFlow}');">[审核]</a>
                        </c:if>
                        <c:if test="${not (scope eq 'director') and (achBook.operStatusId eq achStatusEnumFirstAudit.id) }">
                            <a href="javascript:void(0)"  onclick="audit('${achBook.bookFlow}');">[编辑]</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${achBook.operStatusId eq achStatusEnumFirstAudit.id }">
                            <a href="javascript:void(0)" onclick="view('${achBook.bookFlow}');">[查看]</a>
                        </c:if>
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