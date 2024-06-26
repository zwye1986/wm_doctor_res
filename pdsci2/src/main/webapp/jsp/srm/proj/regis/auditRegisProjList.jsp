<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
<script type="text/javascript">
    var height = (window.screen.height) * 0.7;
    var width = (window.screen.width) * 0.8;
    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }

    function audit(projFlow) {
        var url = "<s:url value='/srm/regis/proj/auditRegisProj?projFlow='/>" + projFlow;
        jboxStartLoading();
        jboxOpen(url, "项目审核", width, height);
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
    }

    function exportExcel() {
        var url = "<s:url value='/srm/regis/proj/exportRegisProjList/${scope}'/>";
        jboxSubmit($('#searchForm'), url, null, null);
        jboxEndLoading();
    }
    function initDept() {
        var datas = [];
        <c:forEach items="${deptList}" var="dept">
        var d = {};

        d.id = "${dept.deptFlow}";
        d.text = "${dept.deptName}";
        datas.push(d);
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#deptFlow").val(this.id);
        };
        $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
    }

    $(document).ready(function () {
        initDept();
    });

    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/regis/proj/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
    }
</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <%-- <form id="paramForm" action="<s:url value="/srm/proj/search/toExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
                   method="post">
                 <input type="hidden" name="projYear" value="${param.projYear}"/>
                 <input type="hidden" name="projTypeId" value="${param.projTypeId}"/>
                 <input type="hidden" name="projNo" value="${param.projNo}"/>
                 <input type="hidden" name="projStageId" value="${param.projStageId}"/>
                 <input type="hidden" name="projStatusId" value="${param.projStatusId}"/>
                 <input type="hidden" name="applyOrgFlow" value="${param.applyOrgFlow}"/>
                 <input type="hidden" name="chargeOrgFlow" value="${param.chargeOrgFlow}"/>
                 <input type="hidden" name="orgFlow" value="${param.orgFlow}"/>
                 <input type="hidden" name="projName" value="${param.projName}"/>
             </form>--%>
            <form id="searchForm" action="<s:url value="/srm/regis/proj/auditRegisProjList/${scope}"/>"
                  method="post">
                <p>
                <div class="searchDiv">
                    年&#12288;&#12288;度：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <c:if test="${not (scope eq 'director')}">
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                </c:if>
                <div class="searchDiv">
                    项目类别：
                    <select class="xlselect" name="projSubTypeId">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumWxeyProjTypeList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projSubTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    姓&#12288;&#12288;名：
                    <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    支&#12288;&#12288;部：
                    <select name="branchId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumWxeyBranchList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.branchId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    项目来源：
                    <select name="projDeclarerFlow" class="xlselect">
                        <option value="">请选择项目来源</option>
                        <c:forEach var="dict" items="${dictTypeEnumWxeyProjSourceList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projDeclarerFlow==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    审核状态：
                    <input type="radio" name="projStatusId" id="achStatusEnumAll"
                           <c:if test="${param.projStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                           value="${GlobalConstant.FLAG_Y}"/><label for="achStatusEnumAll">全部</label>
                    <input type="radio" name="projStatusId" id="aidProjStatusEnumNonAudit"
                           <c:if test="${empty param.projStatusId }">checked="checked"</c:if>
                           <c:if test="${param.projStatusId eq aidProjStatusEnumNonAudit.id}">checked="checked"</c:if>
                           value="${aidProjStatusEnumNonAudit.id }"/><label
                        for="aidProjStatusEnumNonAudit">${aidProjStatusEnumNonAudit.name}</label>
                    <input type="radio" name="projStatusId" id="aidProjStatusEnumPass"
                           <c:if test="${param.projStatusId eq aidProjStatusEnumPass.id }">checked="checked"</c:if>
                           value="${aidProjStatusEnumPass.id }"/><label
                        for="aidProjStatusEnumPass">${aidProjStatusEnumPass.name}</label>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <input type="button" class="search" onclick="exportExcel();" value="导&#12288;出">
                </div>
                </p>

            </form>
        </div>
        <form id="recForm" method="post">
            <table class="xllist">
                <thead>
                <tr>
                    <th width="5%">年度</th>
                    <th width="10%">项目编号</th>
                    <th width="20%">项目名称</th>
                    <th width="10%">项目负责人</th>
                    <th width="8%">科室</th>
                    <th width="8%">支部</th>
                    <th width="10%">起止时间</th>
                    <th width="8%">项目状态</th>
                    <th width="8%">审核意见</th>
                    <th width="8%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projList}" var="proj">
                    <tr>
                        <td><span>${proj.projYear }</span></td>
                        <td>${proj.projNo}</td>
                        <td><a style="color:blue"
                               href="<s:url value='/srm/regis/proj/view?projFlow=${proj.projFlow}&typeId=${proj.projTypeId}&auditFlag=Y'/>">${proj.projName}</a>
                        </td>
                        <td>${proj.applyUserName}</td>
                        <td>${proj.applyDeptName }</td>
                        <td>${proj.branchName }</td>
                        <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                        <td><a href="javascript:void(0)" onclick="auditList('${proj.projFlow}')">${proj.projStatusName }</a></td>
                        <td><a href="javascript:void(0)" onclick="auditList('${proj.projFlow}')">[查看意见]</a></td>
                        <td>
                            <a href="<s:url value='/srm/regis/proj/view?projFlow=${proj.projFlow}&typeId=${proj.projTypeId}&auditFlag=Y'/>">[查看]</a>&#12288;
                            <c:if test="${!(scope eq 'director') and (aidProjStatusEnumNonAudit.id eq proj.projStatusId)}">
                                <a href="javascript:void(0)" onclick="audit('${proj.projFlow}');">[审核]</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>


</html>