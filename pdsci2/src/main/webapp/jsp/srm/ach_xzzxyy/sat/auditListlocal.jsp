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

        function audit(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/audit?satFlow='/>" + satFlow, "审核", width, height);
        }

        function view(satFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/sat/edit/${scope}?satFlow='/>" + satFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看科技信息", width, height);
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
            var url = "<s:url value='/srm/ach/sat/exportSatExcel/local'/>";
            jboxSubmit($('#searchForm'), url, null, null);
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

        function expert() {
            var url = "<s:url value='/srm/ach/manager/exportAchExcel/Sat'/>";
            var jsondata = $('#searchForm').serializeJson();
            $("#jsondata").val(JSON.stringify(jsondata));

            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/sat/auditList/${scope}"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;奖项名称：
                    <input type="text" name="satName" class="xltext" value="${param.satName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;获奖年份：
                    <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="ctime"
                           style="width: 158px;" name="prizedDate" type="text" value="${param.prizedDate}"/>
                </div>
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <select name="deptFlow" class="xlselect" id="selectDept">
                        <option value=""></option>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;&#12288;&#12288;作者：
                    <input type="text" class="xltext" name="authorName" value="${param.authorName}"/>
                </div>
                <%--&#12288;项目来源：
                <select name="projSourceId" class="xlselect">
                   <option value="">请选择</option>
                   <c:forEach items="${dictTypeEnumProjSourceList }" var="dict">
                   <option <c:if test="${param.projSourceId eq dict.dictId }">selected="selected"</c:if> value="${dict.dictId }" >${dict.dictName }</option>
                   </c:forEach>
               </select>--%>
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
            <thead>
            <tr>
                <th width="6%">年度</th>
                <th width="4%">序号</th>
                <th width="10%">姓名</th>
                <th width="10%">科室</th>
                <th width="20%">课题名称</th>
                <th width="10%">奖项名称</th>
                <th width="7%">等级</th>
                <th width="8%">类别</th>
                <th width="10%">审核状态</th>
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
                <td>${satStatus.count}</td>
                <td title="作者：<c:forEach items="${allAuthorMap}" var="entry">
				     <c:if test="${entry.key==sat.satFlow}">
				     	<c:forEach items="${entry.value}" var="author">
				    	 ${author.authorName}&nbsp;
				    	</c:forEach>
				     </c:if>
			     </c:forEach>">${sat.applyUserName}</td>
                <td>${sat.deptName}</td>
                <td>${sat.subjectName}</td>
                <td>${sat.satName}</td>
                <td>${sat.prizedLevelName}</td>
                <td>${sat.prizedGradeName}</td>
                <td>
                    <a href="javascript:void(0);" onclick="auditProcess('${sat.satFlow}')">${sat.operStatusName}</a>
                </td>
                <td>
                    <c:if test="${sat.operStatusId eq achStatusEnumSubmit.id }">
                        <a href="javascript:void(0)" onclick="audit('${sat.satFlow}');">[审核]</a>
                    </c:if>
                    <c:if test="${not (scope eq 'director') and (sat.operStatusId eq achStatusEnumFirstAudit.id) }">
                        <a href="javascript:void(0)" onclick="audit('${sat.satFlow}');">[编辑]</a>&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${sat.operStatusId eq achStatusEnumFirstAudit.id }">
                        <a href="javascript:void(0)" onclick="view('${sat.satFlow}','look');">[查看]</a>
                    </c:if>
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