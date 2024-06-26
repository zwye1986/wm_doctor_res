<script type="text/javascript">
    $(document).ready(function(){

    });
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
        <th>姓名</th>
        <th>角色</th>
        <th>专业基地</th>
        <th>总带教人数</th>
        <th>总带教次数</th>
        <th>教学活动总数</th>
        <th>教学活动补助</th>
        <th>带教补助</th>
        <th>补助合计</th>
    </tr>
    <c:forEach items="${userList}" var="b" varStatus="s">
        <tr>
            <c:set var="key" value="${b.userFlow}"></c:set>
            <td>${b.userName}</td>
            <td>${b.roleName}</td>
            <td>${speNameMap[key]}</td>
            <td>${pNumMap[key]}</td>
            <td>${tNumMap[key]}</td>
            <td>${atyNumMap[key] + ltNumMap[key]}</td>
            <td>${atyPayMap[key] + ltPayMap[key]}</td>
            <td>${payMap[key]}</td>
            <td>${atyPayMap[key] + ltPayMap[key] + payMap[key]}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty userList}">
        <tr>
            <td colspan="10" >无记录！</td>
        </tr>
    </c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>



