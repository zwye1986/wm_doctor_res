<form id="signForm" method="post">
	<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
	<input type="hidden" name="clinicalName" value="${clinicalName}"/>
	<input type="hidden" name="speName" value="${speName}"/>
	<input id="currentPage2" type="hidden" name="currentPage2" value="${currentPage2}"/>
	<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
		<tr>
			<td style="border:0px;">
				<span style=""></span>是否签到：
				<select name="siginStatusId" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${signStatusEnumList}" var="status">
						<option value="${status.id}" ${param.siginStatusId eq status.id ?'selected':''}>${status.name}</option>
					</c:forEach>
				</select>
				<span style="padding-left:10px;"></span>姓名：
				<input type="text" name="signDoctorName" value="${param.signDoctorName}" style="width:137px;">
				<span style="padding-left:10px;"></span>考核时间：
				<select name="recrodFlow" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${timeList}" var="time">
						<option value="${time.recrodFlow}" ${param.recrodFlow eq time.recrodFlow?'selected':''}>${time.examStartTime}~${time.examEndTime}</option>
					</c:forEach>
				</select>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage2(1)"/>
				<input type="button" class="search" value="导&#12288;出" onclick="expSign()"/>
			</td>
		</tr>
	</table>
</form>
<table class="xllist" style="width:100%;">
	<tr>
		<th>序号</th>
		<th>姓名</th>
		<th>证件号码</th>
		<th>准考证号</th>
		<th>培训届别</th>
		<th>考核专业</th>
		<th>签到时间</th>
		<th>状态</th>
	</tr>
	<c:forEach items="${signList}" var="info" varStatus="i">
		<tr>
			<td>${i.index + 1}</td>
			<td>${info.doctorName}</td>
			<td>${info.sysUser.idNo}</td>
			<td>${info.ticketNumber}</td>
			<td>${info.doctor.sessionNumber}</td>
			<td>${info.skillAssess.speName}</td>
			<td>${info.siginTime}</td>
			<td><a onclick="signOpt('${info.recordFlow}','${info.siginStatusId}');" style="cursor:pointer;color:blue;"><c:out value="${info.siginStatusName}"/></a></td>
		</tr>
	</c:forEach>
</table>
<div style="float:right;margin-top:100px;">
	<c:set var="pageView" value="${pdfn:getPageView(signList)}" scope="request"/>
	<pd:pagination toPage="toPage2"/>
</div>