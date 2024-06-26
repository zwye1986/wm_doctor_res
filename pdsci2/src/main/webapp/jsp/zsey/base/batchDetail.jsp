<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function selectTag(selfObj,type) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("input[name='batchType']").val(type);
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/zsey/base/batchDetail'/>" method="post">
            <input type="hidden" name="suppliesFlow" value="${param.suppliesFlow}">
            <input type="hidden" name="batchType" value="${param.batchType}">
            <input type="hidden" id="currentPage" name="currentPage">
        </form>
        <div>
            <ul id="tags" style="margin-left: 0px;">
                <li ${empty param.batchType || param.batchType eq 'IN'?"class='selectTag'":""}>
                    <a onclick="selectTag(this,'IN')" href="javascript:;">入库</a>
                </li>
                <li ${param.batchType eq 'OUT'?"class='selectTag'":""}>
                    <a onclick="selectTag(this,'OUT')" href="javascript:;">出库</a>
                </li>
            </ul>
        </div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>日期</th>
                <th>一次性量</th>
                <th>可重复量</th>
                <c:if test="${param.batchType eq 'OUT'}">
                    <th>使用部门</th>
                </c:if>
            </tr>
            <c:forEach items="${batchList}" var="bat">
                <tr>
                    <td>${bat.batchDate}</td>
                    <td>${bat.oneNumber}</td>
                    <td>${bat.repeatNumber}</td>
                    <c:if test="${param.batchType eq 'OUT'}">
                        <td>${bat.deptName}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(batchList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>