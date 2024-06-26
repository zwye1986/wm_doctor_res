<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(function(){
            $("#detail").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        });
        function toPage(page){
            jboxStartLoading();
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }

        function editInfo(userFlow){
            jboxOpen("<s:url value='/gyxjgl/partyBranch/editPartyMember'/>?userFlow="+userFlow,"编辑",500,300);
        }

        function showInfo(userFlow){
            jboxStartLoading();
            var url= "<s:url value='/gyxjgl/user/userCertificateInfo'/>?userFlow="+userFlow;
            jboxLoad("detail",url,false);
            $("#detail").rightSlideOpen();
        }

        function exportData(){
            var url = "<s:url value='/gyxjgl/partyBranch/exportPartyMembers'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/partyBranch/partyMemberList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                &#12288;学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width: 137px;"/>&#12288;
                姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;"/>&#12288;
                专&#12288;&#12288;业：<select style="width: 141px;" name="majorId">
                    <option/>
                    <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                        <option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>
                            [${major.dictId}]${major.dictName}</option>
                    </c:forEach>
                </select>
                <%--&#12288;--%>
                <%--是否转入：<select style="width: 141px;" name="isRelationInto">--%>
                    <%--<option/>--%>
                    <%--<option value="Y" ${param.isRelationInto eq 'Y'?'selected':''}>是</option>--%>
                    <%--<option value="N" ${param.isRelationInto eq 'N'?'selected':''}>否</option>--%>
                <%--</select>--%><br/>
                所属党支部：<select style="width: 141px;" name="partyBranchId">
                    <option/>
                    <c:forEach items="${dictTypeEnumGyXjPartyBranchList}" var="partyBranch">
                        <option value="${partyBranch.dictId}" ${param.partyBranchId eq partyBranch.dictId?'selected':''}>
                            ${partyBranch.dictName}</option>
                    </c:forEach>
                </select>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="exportData();" value="导&#12288;出"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:80px;">学号</td>
                <td style="width:80px;">姓名</td>
                <td style="width:120px;">导师</td>
                <td style="width:80px;">专业代码</td>
                <td style="width:120px;">专业名称</td>
                <td style="width:80px;">政治面貌</td>
                <td style="width:80px;">入党时间</td>
                <td style="width:120px;">所属党支部</td>
                <td style="width:80px;">是否转入</td>
                <td style="width:100px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.sid}</td>
                    <td>${info.sysUser.userName}</td>
                    <td>
                        <c:if test="${not empty info.firstTeacher and empty info.secondTeacher}">
                            ${info.firstTeacher}
                        </c:if>
                        <c:if test="${empty info.firstTeacher and not empty info.secondTeacher}">
                            ${info.secondTeacher}
                        </c:if>
                        <c:if test="${not empty info.firstTeacher and not empty info.secondTeacher}">
                            导师一：${info.firstTeacher}<br/>
                            导师二：${info.secondTeacher}
                        </c:if>
                    </td>
                    <td>${info.majorId}</td>
                    <td>${info.majorName}</td>
                    <td>${info.sysUser.politicsStatusName}</td>
                    <td>${info.modifyTime}</td>
                    <td>${info.sysUser.partyBranchName}</td>
                    <td>${empty info.modifyUserFlow?"":(info.modifyUserFlow eq 'Y'?'是':'否')}</td>
                    <td>
                        <a onclick="editInfo('${info.userFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                        <a onclick="showInfo('${info.userFlow}');" style="cursor:pointer;color:blue;">查看</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="detail" style="background: white;">
</div>
</body>
</html>