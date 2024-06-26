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
        <table class="xllist" >
            <tr>
                <th>主讲人</th>
                <th>基地名称</th>
                <th>科室名称</th>
				<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
					<th>${a.dictName}</th>
				</c:forEach>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td>${b.userName}</td>
	                <td>${b.orgName}</td>
	                <td>${b.deptName}</td>
					 <c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
						 <c:set var="key" value="${b.deptFlow}${b.userFlow}${a.dictId}"></c:set>
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
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
      
