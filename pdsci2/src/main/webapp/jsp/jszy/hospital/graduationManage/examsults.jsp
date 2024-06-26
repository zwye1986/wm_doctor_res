
	<div class="search_table" style="width: 100%;<c:if test="${param.role ne 'doctor'}">
			height: 450px;overflow: auto;
	</c:if> box-sizing: border-box;">
		<table border="0" cellpadding="0" cellspacing="0" class="basic" style="width:100%;border: 1px solid #e3e3e3;">
			<tr>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">序号</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">姓名</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">培训专业</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">对应专业</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">试卷名称</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">考试时间</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">交卷时间</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">试卷得分</th>
				<c:if test="${param.role eq 'doctor'}">
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">操作</th>
				</c:if>
				<c:if test="${param.role ne 'doctor'}">
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">及格分</th>
				</c:if>

			</tr>
			<c:forEach items="${graduationExamList}" var="b" varStatus="list">
				<tr>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${list.index+1}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${user.userName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.doctorCategoryName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.trainingSpeName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${b.soluName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${b.examTime}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${b.submitTime}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${b.theoryScore}</td>
					<c:if test="${param.role eq 'doctor'}">
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;"><a style="cursor: pointer"	onclick="showErrorInfo('${b.resultsId}');">查看错题</a></td>
					</c:if>
					<c:if test="${param.role ne 'doctor'}">
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${b.examPassScore}</td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${empty graduationExamList}">
			<tr>
				<td colspan="9" style="text-align: center">无记录！</td>
			</tr>
			</c:if>
		</table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(graduationExamList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage3"/>
	</div>
      
