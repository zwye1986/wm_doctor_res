
	<div class="search_table" style="width: 100%;<c:if test="${param.role ne 'doctor'}">
			height: 450px;overflow: auto;
	</c:if> box-sizing: border-box;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>培训专业</th>
				<th>试卷名称</th>
				<th>考试时间</th>
				<th>交卷时间</th>
				<th>试卷得分</th>
				<c:if test="${param.role eq 'doctor'}">
					<th>操作</th>
				</c:if>
				<c:if test="${param.role ne 'doctor'}">
					<th>及格分</th>
				</c:if>

			</tr>
			<c:forEach items="${graduationExamList}" var="b" varStatus="list">
				<tr>
					<td>${list.index+1}</td>
					<td>${user.userName}</td>
					<td>${doctor.trainingSpeName}</td>
					<td>${b.soluName}</td>
					<td>${b.examTime}</td>
					<td>${b.submitTime}</td>
					<td>${b.theoryScore}</td>
					<c:if test="${param.role eq 'doctor'}">
						<td><a style="cursor: pointer"	onclick="showErrorInfo('${b.resultsId}');">查看错题</a></td>
					</c:if>
					<c:if test="${param.role ne 'doctor'}">
						<td>${b.examPassScore}</td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${empty graduationExamList}">
			<tr>
				<td colspan="8" >无记录！</td>
			</tr>
			</c:if>
		</table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(graduationExamList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage2"/>
	</div>
      
