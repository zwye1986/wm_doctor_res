<script type="text/javascript">
$(document).ready(function(){

});
</script>

<style type="text/css">
	.bg{
		width: 60px;
		height: 16px;
		background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
		margin-left: 15px;
	}
	.img{
		width: 20px;
		height: 20px;
		margin-left: 5px;
	}
	.over{
		height:16px;
		background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
	}
</style>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>科室名称</th>
				<c:forEach items="${activityTypeEnumList}" var="a">
					<th>${a.name}</th>
				</c:forEach>
				<th>轮转人数</th>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td>${b.deptName}</td>
					 <c:forEach items="${activityTypeEnumList}" var="a">
						 <c:set var="key" value="${b.deptFlow}${a.id}"></c:set>
						 <c:set var="map2" value="${map[b.deptFlow]}"></c:set>
						 <td>${empty map2[key]?'0':map2[key]}</td>
					 </c:forEach>
					 <td>${empty countMap[b.deptFlow]?'0':countMap[b.deptFlow]}</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty list}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>

