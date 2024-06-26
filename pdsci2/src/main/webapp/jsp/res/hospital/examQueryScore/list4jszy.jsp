
		<table class="xllist">
			<tr>
				<th>姓名</th>
				<th>培训专业</th>
				<th>对应专业</th>
				<th>年级</th>
				<c:forEach items="${years}" var="year">
					<th>${year}年度</th>
				</c:forEach>
			</tr>
			<c:forEach items="${list}" var="b">
				<tr>
					<td>${b.sysUser.userName}</td>
					<td>${b.doctorCategoryName}</td>
					<td>${b.trainingSpeName}</td>
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
				<td colspan="99" >无记录！</td>
			</tr>
			</c:if>
		</table>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
      
