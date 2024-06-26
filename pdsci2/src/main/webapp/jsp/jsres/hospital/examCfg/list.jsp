<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	function lookScore(arrangeFlow,trainingSpeName,examStartTime,examEndTime,examNumber,examDuration) {
		/*	var url = '<s:url value="/res/test/lookScore"/>?arrangeFlow='+arrangeFlow+"&trainingSpeName="+trainingSpeName
				+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime+"&examNumber="+examNumber+"&examDuration="+examDuration;*/
		$.ajax({
			url: "<s:url value='/res/test/lookScore'/>",
			type: "get",
			data: {
				"arrangeFlow": arrangeFlow,
				"trainingSpeName": trainingSpeName,
				"examStartTime": examStartTime,
				"examEndTime": examEndTime,
				"examNumber": examNumber,
				"examDuration": examDuration},
			dataType: "json",
			success: function (res) {
				window.open(res);
			}
		});
	}
</script>
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>培训类别</th>
				<th>培训专业</th>
				<th>年级</th>
				<th>年度</th>
				<th>考试模式</th>
				<th>考核方式</th>
				<th>考试时长(分钟)</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="b">
				<tr>
					<td>${b.trainingTypeName}</td>
					<td>${b.trainingSpeName}</td>
					<td>${b.sessionNumber}</td>
					<td>${b.assessmentYear}</td>
					<td>
						<c:if test="${b.exampaperType eq '1'}">
							随机试卷
						</c:if>
						<c:if test="${b.exampaperType eq '2'}">
							固定试卷
						</c:if>
					</td>
					<td>
						<c:if test="${b.isApp eq 'Y'}">
							APP端
						</c:if>
						<c:if test="${b.isWeb eq 'Y'}">
							<c:if test="${b.isApp eq 'Y'}">
								/
							</c:if>WEB端
						</c:if>
					</td>
                    <td>${b.examDuration}</td>
					<td>
						<c:if test="${b.isOpen eq 'Y'}">
							<a href="javascript:openInfo('${b.arrangeFlow}','N');" class="btn">关闭</a>
						</c:if>
						<c:if test="${b.isOpen eq 'N'}">
							<a href="javascript:openInfo('${b.arrangeFlow}','Y');" class="btn">开放</a>
						</c:if>
						<a href="javascript:edit('${b.arrangeFlow}');" class="btn">编辑</a>
						<a href="javascript:del('${b.arrangeFlow}');" class="btn">删除</a>
						<c:if test="${b.exampaperType eq '2'}">
							<a href="javascript:lookScore('${b.arrangeFlow}','${b.trainingSpeName}','${b.examStartTime}','${b.examEndTime}','${b.examNumber}','${b.examDuration}');" class="btn">查看试卷</a>
						</c:if>
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
      
