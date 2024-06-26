<script type="text/javascript">
    $(document).ready(function(){

    });
    function editPerformance(mainFlow,typeId,flag) {
        jboxOpen("<s:url value='/res/performanceManage/searchByMainFlow'/>?mainFlow="+mainFlow+"&typeId="+typeId+"&flag="+flag,"编辑信息", 600, 550);
    }
    function seach(mainFlow,typeId,flag) {
        jboxOpen("<s:url value='/res/performanceManage/searchByMainFlow'/>?mainFlow="+mainFlow+"&typeId="+typeId+"&flag="+flag,"查看信息", 600, 550);
    }
</script>

<style type="text/css">
    .bg{
        width: 60px;
        height: 16px;
        background: url(<s:url value='/jsp/res/activity/img/star_gray.png'/>);
        margin-left: 15px;
    }
    .img{
        width: 20px;
        height: 20px;
        margin-left: 5px;
    }
    .over{
        height:16px;
        background:url(<s:url value='/jsp/res/activity/img/star_org.png'/>) no-repeat;
    }
</style>

<table class="xllist">
    <tr>
        <th>有效时间</th>
        <th>带教标准</th>
        <th>教秘标准</th>
        <th>科主任标准</th>
        <th>专业基地主任</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="b" varStatus="s">
        <tr>
            <td>${b.startDate}~${b.endDate}</td>
            <td>${b.teaCost}</td>
            <td>${b.kmCost}</td>
            <td>${b.headCost}</td>
            <td>${b.speCost}</td>
            <td>
                [<a href="javascript:seach('${b.mainFlow}','${typeId}','search');" >查看</a>]
                <c:if test="${b.endDate ge pdfn:getCurrDateTime('yyyy-MM-dd')}">
                    [<a href="javascript:editPerformance('${b.mainFlow}','${typeId}','edit');" >编辑</a>]
                </c:if>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty list}">
        <tr>
            <td colspan="10" >无记录！</td>
        </tr>
    </c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>



