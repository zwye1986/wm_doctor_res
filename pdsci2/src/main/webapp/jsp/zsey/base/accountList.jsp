<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function editInfo(userFlow){
            var title = userFlow == ""?"新增":"编辑";
            var url = "<s:url value='/zsey/base/editAccount?userFlow='/>"+userFlow;
            jboxOpen(url, title,600,400);
        }
        function startInfo(userFlow,recordStatus){
            var optName = recordStatus == 'Y'?"停用":"启用";
            jboxConfirm("确认"+optName+"？", function(){
                var url = "<s:url value='/zsey/base/accountOpt?userFlow='/>"+userFlow+"&recordStatus="+recordStatus;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function resetPwdInfo(userFlow){
            jboxConfirm("确认还原密码为：${pdfn:getInitPass()} 吗？",function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
                jboxGet(url,null,function(){});
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/accountList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>用户名：
                <input type="text" name="userCode" value="${param.userCode}">
                <span style="padding-left:20px;"></span>姓名：
                <input type="text" name="userName" value="${param.userName}">
                <span style="padding-left:20px;"></span>所在科室：
                <select name="deptFlow" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach var="dept" items="${deptList}">
                        <option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>角色：
                <select name="roleFlow" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach var="role" items="${roleList}">
                        <option value="${role.roleFlow}" ${param.roleFlow eq role.roleFlow?'selected':''}>${role.roleName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="新&#12288;增" onclick="editInfo('')"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>姓名</th>
                <th>所在科室</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${dataList}" var="acc" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${acc.userCode}</td>
                    <td>${acc.userName}</td>
                    <td>${acc.deptName}</td>
                    <td>${acc.roleName}</td>
                    <td>
                        <a onclick="editInfo('${acc.userFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>
                        <a onclick="resetPwdInfo('${acc.userFlow}');" style="cursor:pointer;color:#4195c5;">还原密码</a>
                        <a onclick="startInfo('${acc.userFlow}','${acc.recordStatus}');" style="cursor:pointer;color:#4195c5;">${acc.recordStatus eq 'Y'?'停用':'启用'}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>