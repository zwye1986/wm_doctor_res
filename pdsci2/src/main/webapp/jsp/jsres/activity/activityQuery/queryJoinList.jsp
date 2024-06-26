<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<c:if test="${empty list}">
	<div class="search_table" style="width: 96%;padding: 0px 20px">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>姓名</th>
				<th>年级</th>
				<th>专业</th>
				<th>人员类型</th>
				<th>签到时间</th>
				<th>签退时间</th>
			</tr>
			<tr>
				<td colspan="6">无记录！</td>
			</tr>
		</table>
	</div>
</c:if>
<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="width: 96%;padding: 0px 20px">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
			<thead>
			<tr>
				<th width="15">姓名</th>
				<th width="15%">年级</th>
				<th width="15%">专业</th>
				<th width="15%">人员类型</th>
				<th width="20%">签到时间</th>
				<th width="20%">签退时间</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="s">
				<tr class="fixTrTd">
					<td>${s.doctorName}</td>
					<td>${s.sessionNumber}</td>
					<td>${s.trainingSpeName}</td>
					<td>${s.doctorTypeName}</td>
					<td>${s.siginTime==null?"-": s.siginTime}</td>
					<td>${s.siginTime2==null?"-": s.siginTime2}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

      
