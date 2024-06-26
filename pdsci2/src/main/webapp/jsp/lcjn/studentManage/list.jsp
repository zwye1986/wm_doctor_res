<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function edit(userFlow){
            var title = userFlow == ""?"新增":"编辑";
            var url = "<s:url value='/lcjn/studentManage/edit?userFlow='/>"+userFlow;
            jboxOpen(url, title,600,500);
        }
        function resetCode(userFlow){
            jboxConfirm("确认密码重置为：123456？",function(){
                jboxPost("<s:url value='/lcjn/studentManage/save'/>?userFlow="+userFlow+"&resetCode=resetCode", null, function (resp) {
                    if(resp==1){
                        jboxTip("操作成功");
                    }
                },null,false);
            });
        }
        function importInfo(){
            jboxOpen("<s:url value='/jsp/lcjn/studentManage/importStudent.jsp'/>", "导入",600,200);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/lcjn/studentManage/list"/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="choseDivNewStyle">
            <table style="width: 100%;margin: 5px 0px;">
                <tr>
                    <td style="line-height:44px;">
                        <span style=""></span>用户名：
                            <input type="text" name="userCode" value="${param.userCode}">
                        <span style="padding-left:20px;"></span>姓名：
						<span>
                            <input type="text" name="userName" value="${param.userName}">
                        </span>
                        <span style="padding-left:20px;"></span>是否本院：
                        <label><input type="radio" name="isOwnerStu" value="Y"
                            <c:if test="${param.isOwnerStu eq 'Y'}">checked="checked"</c:if>
                        >是</label>&#12288;
                        <label><input type="radio" name="isOwnerStu" value="N"
                            <c:if test="${param.isOwnerStu eq 'N'}">checked="checked"</c:if>
                        >否</label>
                        <span style="padding-left:20px;"></span>
                        <input type="button" class="search" value="查&#12288;询" onclick="search()"/>
                        <input type="button" class="search" value="新&#12288;增" onclick="edit('')"/>
                        <input type="button" class="search" value="导&#12288;入" onclick="importInfo()"/>
                    </td>
                </tr>
            </table>
                </div>
        </form>
        <table class="xllist" style="width:100%;min-width: 999px;">
            <tr>
                <th style="min-width: 27px">序号</th>
                <th style="min-width: 92px">用户名</th>
                <th style="min-width: 90px">姓名</th>
                <th style="min-width: 27px">性别</th>
                <th style="min-width: 172px">身份证号</th>
                <th style="min-width: 92px">手机号码</th>
                <th style="min-width: 172px">邮箱</th>
                <th style="min-width: 53px">是否本院</th>
                <th style="min-width: 53px" >所在单位</th>
                <th style="min-width: 53px">科室</th>
                <th style="min-width: 54px">职称</th>
                <th style="min-width: 54px">培训专业</th>
                <th style="min-width: 103px">操作</th>
            </tr>
            <c:forEach items="${students}" var="user" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${user.userCode}</td>
                    <td>${user.userName}</td>
                    <td>${user.sexName}</td>
                    <td>${user.idNo}</td>
                    <td>${user.userPhone}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.isOwnerStu eq 'Y'?'是':'否'}</td>
                    <td nowrap>${user.orgName}</td>
                    <td nowrap>${user.deptName}</td>
                    <td nowrap>${user.titleName}</td>
                    <td>${user.lcjnSpeName}</td>
                    <td>
                        <a onclick="edit('${user.userFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>&nbsp;
                        <a onclick="resetCode('${user.userFlow}');" style="cursor:pointer;color:#4195c5;">重置密码</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(students)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>