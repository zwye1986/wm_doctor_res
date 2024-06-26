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
                <th>专业基地</th>
				<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
					<th>${a.dictName}</th>
				</c:forEach>
				<th>合计</th>
				<th>活动数量排名</th>
				<th>活动评分排名</th>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
					 <td>${b.value}</td>
					 <c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
						 <c:set var="key1" value="${b.key}${a.dictId}"></c:set>
						 <c:set var="map2" value="${map[b.key]}"></c:set>
						 <td>${empty map2[key1]?'0':map2[key1]}</td>
					 </c:forEach>

					 <c:set var="k" value="${b.key}"></c:set>
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
        <c:set var="pageView" value="${pdfn:getPageView(speDeptList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>


		<%--<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 --%>

      
