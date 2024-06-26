<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function searchProj() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(projFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/proj/complete/audit'/>?projFlow=" + projFlow, "审核", 900, 600);

        }

        function auditList(projFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/proj/mine/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
        }

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
            jboxGet(url, null, function (data) {
                $.each(data, function (i, n) {
                    org.append('<option value="' + n.orgFlow + '">' + n.orgName + '</option>');
                });
            }, null, false);
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchProj();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm"
                  action="<s:url value="/srm/proj/complete/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?recTypeId=${param.recTypeId}" />"
                  method="post">
                <div class="searchDiv">
                    年&#12288;&#12288;份：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <div class="searchDiv">
                    人才类型：
                    <select class="xlselect" name="projTypeId">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumTalentTypeList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    人才编号：&nbsp;<input name="projNo" value="${param.projNo}" class="xltext"/>
                </div>
                <c:if test='${sessionScope.projListScope=="charge"}'>
                    <div class="searchDiv">
                        申报单位：
                        <select name="applyOrgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgList}">
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
                        <select id="org" name="orgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${param.orgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <div class="searchDiv">
                    人才名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                </div>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">年份</th>
                <th width="10%">人才类别</th>
                <th>人才名称</th>
                <th width="10%">起止时间</th>
                <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                    <th width="15%">申报单位</th>
                </c:if>
                <th width="10%">申报人</th>
                <th width="10%">当前阶段</th>
                <th width="8%">审核意见</th>
                <th width="8%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty projList}">
                <tr>
                    <td colspan="11">无记录</td>
                </tr>
            </c:if>
            <c:forEach items="${projList}" var="proj">
                <%-- <td>[<a href="javascript:edit('${proj.projFlow}');">编辑</a>]&#12288;|&#12288;[<a
                    href="javascript:delProj('${proj.projFlow}');">删除</a>]&#12288;|&#12288;<a
                    href="projectAuditInfo.html" target="_BLACK">审核</a></td> --%>
                <tr>
                    <td><span>${proj.projYear }</span></td>
                    <td>${proj.projTypeName }</td>
                    <td><a style="color: blue"
                           href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                           target="_blank">${proj.projName}</a></td>
                    <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                    <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                        <td>${proj.applyOrgName }</td>
                    </c:if>
                    <td>${proj.applyUserName}</td>
                    <td>${proj.projStageName }</td>
                    <td>[<a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a>]</td>
                    <td>[<a href="javascript:audit('${proj.projFlow}');">审核</a>]</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>