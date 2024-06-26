<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">
    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
    }

    function addProj() {
        window.location.href = "<s:url value='/srm/regis/proj/add'/>";
    }

    function delpubProj(projFlow) {
        url = "<s:url value='/srm/regis/proj/delete?projFlow='/>" + projFlow;
        jboxConfirm("确认删除？", function () {
            jboxStartLoading();
            jboxGet(url, null, function () {
                window.parent.frames['mainIframe'].location.reload(true);
            }, null, true);

        });
    }

    function submitAudit(projFlow) {
        var msg = "<span style='color:red;'>送审后无法修改</span> ,";
        var url = "<s:url value='/srm/regis/proj/submitAudit?projFlow='/>" + projFlow;
        jboxConfirm(msg + "确认送审?", function () {
            jboxStartLoading();
            jboxGet(url, null, function (resp) {
                if (resp == "1") {
                    jboxTip("送审成功");
                    window.location.href = "<s:url value='/srm/regis/proj/list'/>";
                }
            }, null, false);
        });
    }
    function exportExcel() {
        var url = "<s:url value='/srm/regis/proj/exportRegisProjList/personal'/>";
        jboxSubmit($('#searchForm'), url, null, null);
        jboxEndLoading();
    }

    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/regis/proj/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
    }
</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/regis/proj/list"/>"
                  method="post">
                <p>
                <div class="searchDiv">
                    年&#12288;&#12288;度：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <div class="searchDiv">
                    项目编号：
                    <input type="text" name="projNo" value="${param.projNo}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    项目名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext"/>
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                </div>
                <div class="searchDiv">
                    审核状态：
                    <input type="radio" name="projStatusId" id="achStatusEnumAll"
                           <c:if test="${param.projStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                    <input type="radio" name="projStatusId" id="aidProjStatusEnumNonSubmit"
                           <c:if test="${empty param.projStatusId }">checked="checked"</c:if>
                           <c:if test="${param.projStatusId eq aidProjStatusEnumNonSubmit.id}">checked="checked"</c:if>
                           value="${aidProjStatusEnumNonSubmit.id }"/><label
                        for="aidProjStatusEnumNonSubmit">${aidProjStatusEnumNonSubmit.name}</label>
                    <input type="radio" name="projStatusId" id="aidProjStatusEnumPass"
                           <c:if test="${param.projStatusId eq aidProjStatusEnumPass.id }">checked="checked"</c:if>
                           value="${aidProjStatusEnumPass.id }"/><label
                        for="aidProjStatusEnumPass">${aidProjStatusEnumPass.name}</label>
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <input type="button" class="search" onclick="addProj();" value="添&#12288;加">
                    <input type="button" class="search" onclick="exportExcel();" value="导&#12288;出">
                </div>
                </p>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">年度</th>
                <th width="10%">项目编号</th>
                <th width="20%">项目名称</th>
                <th width="9%">科室</th>
                <th width="9%">支部</th>
                <th width="15%">项目时间</th>
                <th width="9%">审核状态</th>
                <th width="8%">审核意见</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pubProjList}" var="pubProj">
                <tr>
                    <td><span>${pubProj.projYear }</span></td>
                    <td>${pubProj.projNo }</td>
                    <td>${pubProj.projName}</td>
                    <td>${pubProj.applyDeptName}</td>
                    <td>${pubProj.branchName }</td>
                    <td>&#12288;${pubProj.projStartTime}~${pubProj.projEndTime}</td>
                    <td><a href="javascript:void(0)" onclick="auditList('${pubProj.projFlow}')">${pubProj.projStatusName}</a></td>
                    <td><a href="javascript:void(0)" onclick="auditList('${pubProj.projFlow}')">[查看意见]</a></td>
                    <td>
                        <a href="<s:url value='/srm/regis/proj/view?projFlow=${pubProj.projFlow}&typeId=${pubProj.projTypeId}'/>">[查看]</a>
                        <c:if test="${aidProjStatusEnumNonSubmit.id eq pubProj.projStatusId}">
                            <a href="javascript:void(0)" onclick="submitAudit('${pubProj.projFlow}');">[送审]</a>
                            <a href="<s:url value='/srm/regis/proj/edit?projFlow=${pubProj.projFlow}&typeId=${pubProj.projTypeId}'/>">[编辑]</a>
                            <a href="javascript:void(0)" onclick="delpubProj('${pubProj.projFlow}');">[删除]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty pubProjList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(pubProjList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>