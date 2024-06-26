<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){

	});
</script>
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>姓名</th>
				<th>人员类型</th>
				<th>培训类别</th>
				<th>培训专业</th>
				<th>年级</th>
				<c:forEach items="${years}" var="year">
					<th>${year}年度</th>
				</c:forEach>
			</tr>
			<c:forEach items="${list}" var="b">
				<tr>
					<td>${b.userName}</td>
					<td>${b.doctorTypeName}</td>
					<td>${b.catSpeName}</td>
					<td>${b.speName}</td>
					<td>${b.sessionNumber}</td>
					<c:forEach items="${years}" var="year">
						<c:set var="key" value="${year}${b.doctorFlow}${b.sessionNumber}"></c:set>
						<c:set var="schExam" value="${daMap[key]}"></c:set>
						<td>
							<c:if test="${not empty schExam.examScore }">
								<a href="javascript:downloadExamPaper('${schExam.recordFlow}');">${schExam.examScore }</a>
							</c:if>
							<c:if test="${empty schExam.examScore }">
								--
							</c:if>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
			<tr>
				<td colspan="7" >无记录！</td>
			</tr>
			</c:if>
		</table>
	</div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
