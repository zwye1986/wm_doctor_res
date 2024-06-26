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
                <th>科室名称</th>
				<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
					<th>${a.dictName}</th>
				</c:forEach>
				<th>合计</th>
				<th>活动数量排名</th>
				<th>活动评分排名</th>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td>${b.deptName}</td>
					 <c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
						 <c:set var="key" value="${b.deptFlow}${a.dictId}"></c:set>
						 <c:set var="map2" value="${map[b.deptFlow]}"></c:set>
						 <td>${empty map2[key]?'0':map2[key]}</td>
					 </c:forEach>

					 <c:set var="k" value="${b.deptFlow}"></c:set>
					 <c:if test="${empty numMap[k]}">
						 <td>0</td>
						 <td>0</td>
					 </c:if>
					 <c:if test="${not empty numMap[k]}">
						 <c:forEach items="${numMap[k]}" var="item">
						 <td>${empty item.key?'0':item.key}</td>
						 <td>${empty item.value?'0':item.value}</td>
						 </c:forEach>
					 </c:if>
					 <td>${empty scoreMap[k]?'0':scoreMap[k]}</td>
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


		<%--<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 --%>

      
