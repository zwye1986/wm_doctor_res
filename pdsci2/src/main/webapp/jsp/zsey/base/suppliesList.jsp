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
        function addInfo(){
            jboxOpen("<s:url value='/jsp/zsey/base/addSupplies.jsp'/>","新增",600,400);
        }
        function inOutInfo(suppliesFlow,flag,suppliesName){
            jboxOpen("<s:url value='/zsey/base/editSupplies?suppliesFlow='/>"+suppliesFlow+"&flag="+flag+"&suppliesName="+encodeURI(encodeURI(suppliesName)),flag=='in'?"入库":"出库",600,400);
        }
        function batchInfo(suppliesFlow){
            var url = "<s:url value='/zsey/base/batchDetail?suppliesFlow='/>"+suppliesFlow;
            jboxOpen(url, "明细",660,400);
        }
        function delInfo(suppliesFlow){
            jboxConfirm("确认删除耗材信息？", function(){
                var url = "<s:url value='/zsey/base/delSupplies?suppliesFlow='/>"+suppliesFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function impInfo(){
            jboxOpen("<s:url value='/jsp/zsey/base/importSupplies.jsp'/>", "导入",600,200);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/suppliesList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>耗材名称：
                <input type="text" name="suppliesName" value="${param.suppliesName}">
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="新&#12288;增" onclick="addInfo('')"/>
                    <input type="button" class="search" value="导&#12288;入" onclick="impInfo('')"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th rowspan="2">耗材名称</th>
                <th colspan="2">库存量</th>
                <th rowspan="2" colspan="2">操作</th>
            </tr>
            <tr>
                <th>一次性</th>
                <th>可重复</th>
            </tr>
            <c:forEach items="${suppliesList}" var="sup">
                <tr>
                    <td>${sup.suppliesName}</td>
                    <td>${sup.oneNumber}</td>
                    <td>${sup.repeatNumber}</td>
                    <td>
                        <a onclick="inOutInfo('${sup.suppliesFlow}','in','${sup.suppliesName}');" style="cursor:pointer;color:#4195c5;">入库</a>
                        <a onclick="inOutInfo('${sup.suppliesFlow}','out','${sup.suppliesName}');" style="cursor:pointer;color:#4195c5;">出库</a>
                        <a onclick="batchInfo('${sup.suppliesFlow}');" style="cursor:pointer;color:#4195c5;">明细</a>
                        <a onclick="delInfo('${sup.suppliesFlow}');" style="cursor:pointer;color:#4195c5;">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(suppliesList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>