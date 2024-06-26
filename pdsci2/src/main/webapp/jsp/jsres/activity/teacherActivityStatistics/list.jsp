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
                <th>主讲人</th>
                <th>科室名称</th>
				<c:forEach items="${activityTypeEnumList}" var="a">
					<th>${a.name}</th>
				</c:forEach>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td>${b.userName}</td>
	                <td>${b.deptName}</td>
					 <c:forEach items="${activityTypeEnumList}" var="a">
						 <c:set var="key" value="${b.deptFlow}${b.userFlow}${a.id}"></c:set>
						 <c:set var="key2" value="${b.userFlow}${b.deptFlow}"></c:set>
						 <c:set var="map2" value="${map[key2]}"></c:set>
						 <td>${empty map2[key]?'0':map2[key]}</td>
					 </c:forEach>
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
      
