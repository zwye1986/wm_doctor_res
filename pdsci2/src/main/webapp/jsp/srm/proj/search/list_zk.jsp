<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">
    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }
    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/mine/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
    }
    /*function audit(projFlow){
     $("#recForm").attr("action","<s:url value='/srm/proj/search/recList?projFlow='/>"+projFlow);
     jboxStartLoading();
     $("#recForm").submit();
     }*/

    //加载申请单位
    function loadApplyOrg() {
        //清空
        var org = $('#org');
        org.html('<option value="">请选择</option>');
        var chargeOrgFlow = $('#chargeOrg').val();
        if (!chargeOrgFlow) {
            return;
        }
        var url = "<s:url value='/sys/org/loadApplyOrg'/>?orgFlow=" + chargeOrgFlow;
        jboxStartLoading();
        jboxGet(url, null, function (data) {
            $.each(data, function (i, n) {
                org.append('<option value="' + n.orgFlow + '">' + n.orgName + '</option>');
            });
        }, null, false);
    }
</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm"
                  action="<s:url value="/srm/proj/search/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
                  method="post">
                <div class="searchDiv">
                    年&#12288;&#12288;份：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <div class="searchDiv">
                    专科类型：
                    <select class="xlselect" name="projTypeId">
                        <option value="">--请选择--</option>
                        <c:forEach var="dictEnuProfeType" items="${dictTypeEnumProfeTypeList}">
                            <option value="${dictEnuProfeType.dictId}"
                                    <c:if test='${param.projTypeId==dictEnuProfeType.dictId}'>selected="selected"</c:if>>${dictEnuProfeType.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test='${sessionScope.projListScope=="charge"}'>
                    <div class="searchDiv">
                        申报单位：
                        <select name="applyOrgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${firstGradeOrgList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${param.applyOrgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>

                <c:if test='${sessionScope.projListScope=="global" }'>
                    <div class="searchDiv">
                        主管部门：
                        <select id="chargeOrg" name="chargeOrgFlow" onchange="loadApplyOrg();" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="chargeOrg" items="${chargeOrgList}">
                                <option value="${chargeOrg.orgFlow}"
                                        <c:if test="${param.chargeOrgFlow==chargeOrg.orgFlow}">selected</c:if>>${chargeOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        申报单位：
                        <select id="org" name="applyOrgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${param.applyOrgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <div class="searchDiv">
                    专科名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext" />
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                </div>
            </form>
        </div>
        <form id="recForm" method="post">
            <table class="xllist">
                <thead>
                <tr>
                    <th width="5%">年份</th>
                    <th width="10%">专科类别</th>
                    <th width="20%">专科名称</th>
                    <th width="10%">起止时间</th>
                    <th width="10%">专科负责人</th>
                    <th width="10%">当前状态</th>
                    <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                        <th width="20%">申报单位</th>
                    </c:if>
                    <th width="10%">审核意见</th>
                    <%--<th width="5%" >操作</th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projList}" var="proj">
                    <%--<c:choose>
                        <c:when test="${proj.projStageId == projStageEnumApply.id and  proj.projStatusId == projApplyStatusEnumApply.id }"></c:when>
                        <c:otherwise>--%>
                    <tr>
                        <td><span>${proj.projYear }</span></td>
                        <td>${proj.projTypeName }</td>
                        <td><a style="color:blue"
                               href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                               target="_blank">${proj.projName}</a></td>
                        <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                        <td>${proj.applyUserName}</td>
                        <td>${proj.projStatusName }</td>
                        <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                            <td>${proj.applyOrgName }</td>
                        </c:if>
                        <td>[<a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a>]</td>
                            <%--<td>
                              [<a href="javascript:void(0)" onclick="audit('${proj.projFlow}');">进入</a>]
                            </td>--%>
                    </tr>
                    <%-- </c:otherwise>
                 </c:choose>--%>
                </c:forEach>
                <c:if test="${empty projList}">
                    <tr>
                        <td colspan="11">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>


</html>