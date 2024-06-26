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
        function editInfo(deviceFlow){
            var title = deviceFlow == ""?"新增":"编辑";
            var url = "<s:url value='/zsey/base/editDevices?deviceFlow='/>"+deviceFlow;
            jboxOpen(url, title,600,400);
        }
        function delInfo(deviceFlow){
            jboxConfirm("确认删除设备信息？", function(){
                var url = "<s:url value='/zsey/base/delDevices?deviceFlow='/>"+deviceFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function impInfo(){
            jboxOpen("<s:url value='/jsp/zsey/base/importDevices.jsp'/>", "导入",600,200);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/devicesList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>设备名称：
                <input type="text" name="deviceName" value="${param.deviceName}">
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="新&#12288;增" onclick="editInfo('')"/>
                    <input type="button" class="search" value="导&#12288;入" onclick="impInfo()"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>设备名称</th>
                <th>品牌</th>
                <th>规格型号</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${devicesList}" var="dev">
                <tr>
                    <td>${dev.deviceName}</td>
                    <td>${dev.brandName}</td>
                    <td>${dev.deviceMode}</td>
                    <td>${dev.deviceNumber}</td>
                    <td>
                        <a onclick="editInfo('${dev.deviceFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>
                        <a onclick="delInfo('${dev.deviceFlow}');" style="cursor:pointer;color:#4195c5;">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(devicesList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>