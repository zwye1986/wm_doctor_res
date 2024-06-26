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
<script type="text/javascript">

    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }
    function apply() {
        jboxStartLoading();
        window.location.href = "<s:url value='/srm/proj/add/addContract'/>";
    }

    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/mine/auditList?projFlow='/>" + projFlow, "审核信息", 800, 600);
    }
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
    }
    //编辑合同信息
    function editContractInfo(projFlow, recTypeId) {
        jboxConfirm("确认编辑合同信息？", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        });
    }

    function review(projFlow, recTypeId, recFlow) {
        var url = "";
        if (recFlow) {
            url = "<s:url value='/srm/proj/mine/prepareReview'/>?projFlow=" + projFlow + "&recFlow=" + recFlow;
        } else if (projFlow && recTypeId) {
            url = "<s:url value='/srm/proj/mine/prepareReview'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        }

        jboxConfirm("确认送审,送审后无法修改?", function () {
            jboxStartLoading();
            jboxGet(url, null, function (resp) {
                if (resp == "1") {
                    jboxTip("送审成功");
                    window.location.href = "<s:url value='/srm/proj/add/projContractList/${sessionScope.projCateScope}'/>";
                } else {
                    jboxTip("信息填写不完整，请填写后送审!");
                }

            }, null, false);
        });
    }

</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm"
                  action="<s:url value="/srm/proj/add/projContractList/${sessionScope.projCateScope}" />"
                  method="post">
                <div class="searchDiv">
                    重点专科类别：
                    <select name="projTypeId" class="xlselect">
                        <option value="">--请选择--</option>
                        <c:forEach var="dictEnuProfeType" items="${dictTypeEnumProfeTypeList}">
                            <option value="${dictEnuProfeType.dictId}"
                                    <c:if test='${param.projTypeId==dictEnuProfeType.dictId}'>selected="selected"</c:if>>${dictEnuProfeType.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    重点专科名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext" />
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                </div>
                <div class="searchDiv">
                    &#12288;&#12288;年&#12288;&#12288;度：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <input type="button" class="search" onclick="apply();" value="新增项目">
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th width="5%" rowspan="2">年份</th>
                <th width="20%" rowspan="2">专科名称</th>
                <th width="12%" rowspan="2">专科类型</th>
                <th width="13%" rowspan="2">起止时间</th>
                <th width="6%" rowspan="2">合同状态</th>
                <th width="5%" rowspan="2">审核意见</th>
                <th width="5%" rowspan="2">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${projList}" var="proj">
                <tr>
                    <td><span>${proj.projYear }</span></td>
                    <td><a style="color:blue" href="<s:url value='/srm/proj/mine/projView?projFlow=${proj.projFlow}'/>"
                           target="_blank">${proj.projName}</a></td>
                    <td>${proj.projTypeName }</td>
                    <td>&#12288;${proj.projStartTime}~<br/>${proj.projEndTime}</td>
                    <td>${proj.projStatusName }</td>
                    <td><a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a></td>
                    <td>
                        <c:choose>
                        <c:when test="${projStageEnumContract.id eq proj.projStageId and projContractStatusEnumApply.id eq proj.projStatusId}">
                            <a href="javascript:editContractInfo('${proj.projFlow}' , '${projRecTypeEnumContract.id}');">编辑</a>&#12288;
                            <a href="javascript:review('${proj.projFlow}' , '${projRecTypeEnumContract.id}' , '');">送审</a>
                        </c:when>
                        <c:otherwise>
                        <a href="<s:url value='/srm/proj/mine/projView?projFlow=${proj.projFlow}'/>" target="_blank">查看
                            </c:otherwise>
                            </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>
