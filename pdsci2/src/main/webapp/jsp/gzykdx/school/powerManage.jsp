<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function toPage(){
            $("#searchForm").submit();
        }
        function settingOption(orgFlow){
            var url = "<s:url value='/gzykdx/school/settingOption?orgFlow='/>"+orgFlow;
            jboxOpen(url,"导师权限控制",600,360,true);
        }
        function changePower(obj){
            var powerFlag = $(obj).is(':checked')?"Y":"N";
            jboxPost("<s:url value='/gzykdx/school/changePower?target=org&powerFlag='/>"+powerFlag+"&targetFlow="+$(obj).val(), null, function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gzykdx/school/powerManage"/>" method="post">
            <div class="choseDivNewStyle">
                <span></span>机构名称：
                <select class="select" style="width:137px;" name="orgFlow">
                    <option value="">全部</option>
                    <c:forEach var="org" items="${applicationScope.sysOrgList}">
                        <c:if test="${org.isSecondFlag eq 'Y'}">
                            <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="width:25%;">序号</th>
                <th style="width:25%;">机构名称</th>
                <th style="width:25%;">机构申报权限</th>
                <th style="width:25%;">导师申报权限</th>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${info.orgName}</td>
                    <td><input type="checkbox" value="${info.orgFlow}" ${info.reportingAuthority eq 'Y'?'checked':''} onchange="changePower(this)"></td>
                    <td>
                        <a onclick="settingOption('${info.orgFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="[设置]"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>