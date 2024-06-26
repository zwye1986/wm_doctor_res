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
        function editInfo(projectFlow){
            var url = "<s:url value='/zsey/base/editProjectConfig?projectFlow='/>"+projectFlow;
            jboxOpen(url, projectFlow == ""?"新增":"编辑",600,400);
        }
        function delInfo(projectFlow){
            jboxConfirm("确认删除培训项目？", function(){
                var url = "<s:url value='/zsey/base/delProject?projectFlow='/>"+projectFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/projectList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>培训项目名称：
                <input type="text" name="projectName" value="${param.projectName}">
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="新&#12288;增" onclick="editInfo('')"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>培训项目名称</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${projectList}" var="pro">
                <tr>
                    <td>${pro.projectName}</td>
                    <td>
                        <a onclick="editInfo('${pro.projectFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>
                        <a onclick="delInfo('${pro.projectFlow}');" style="cursor:pointer;color:#4195c5;">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(projectList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>