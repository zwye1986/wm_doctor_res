<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){

	});
	function searchScore(doctorFlow,userName,speName) {
        var url = "<s:url value='/jsres/graduation/searchScore?doctorFlow='/>"+doctorFlow+"&userName="+userName+"&speName="+speName;
        jboxOpen(url, "模拟考核详情",1330,500);
    }
</script>
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>姓名</th>
				<%--<th>身份证号码</th>--%>
				<th>人员类型</th>
				<th>培训类别</th>
				<th>培训专业</th>
				<th>年级</th>
				<th>最高分</th>
				<th>考核次数</th>
				<th>合格率</th>
			</tr>
			<c:forEach items="${list}" var="b">
				<tr>
					<td>${b.userName}</td>
					<%--<td>${b.idNo}</td>--%>
					<td>${b.doctorTypeName}</td>
					<td>${b.catSpeName}</td>
					<td>${b.speName}</td>
					<td>${b.sessionNumber}</td>
					<td>${b.theoryScore}</td>
					<td><a href="javascript:void(0);" onclick="searchScore('${b.doctorFlow}','${b.userName}','${b.speName}')">${b.totalNum}</a></td>
					<td>
						<c:forEach items="${map}" var="item">
							<c:if test="${b.doctorFlow == item.key}">
								${item.value}
							</c:if>
							<%--<fmt:formatNumber type="percent" value="${b.qualifiedNum/b.totalNum}" />--%>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
			<tr>
				<td colspan="8" >无记录！</td>
			</tr>
			</c:if>
		</table>
	</div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
