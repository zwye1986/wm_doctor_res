<form id="appointForm" method="post">
	<input class="clinicalFlow" type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
	<input class="clinicalName" type="hidden" name="clinicalName" value="${clinicalName}"/>
	<input class="speName" type="hidden" name="speName" value="${speName}"/>
	<input class="year" type="hidden" name="year" value="${year}"/>
	<input class="isLocal" type="hidden" name="isLocal" value="${isLocal}"/>
	<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
	<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
		<tr>
			<td style="border:0px;">
				<span style=""></span>审核状态：
				<select name="auditStatusId" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${auditStatusEnumList}" var="status">
						<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
					</c:forEach>
				</select>
				<span style="padding-left:10px;"></span>姓名：
				<input type="text" name="appointDoctorName" value="${param.appointDoctorName}">
				<c:if test="${isLocal eq 'N'}">
				<span style="padding-left:10px;"></span>届别：
				<select name="sessionId" style="width:137px;" class="select">
					<option value="">全部</option>
					<option value="1" ${param.sessionId eq "1"?'selected':''}>应届</option>
					<option value="0" ${param.sessionId eq "0"?'selected':''}>往届</option>
				</select>
				</c:if>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage1(1)"/>
				<input type="button" class="search" value="审&#12288;核" onclick="auditOpt()"/>
				<input type="button" class="search" value="撤&#12288;销" onclick="backOpt()"/>
				<input type="button" class="search" value="导&#12288;出" onclick="expAppoint()"/>
				<input type="button" class="search" value="导&#12288;入" onclick="importExl()"/>
				<input type="button" class="search" value="导出准考证" onclick="expDocTickets()"/>
			</td>
		</tr>
	</table>
</form>
<table class="xllist" style="width:100%;">
	<tr>
		<th><input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号</th>
		<th>姓名</th>
		<th>证件号码</th>
		<th>性别</th>
		<th>培训届别</th>
		<th>培训基地</th>
		<th>考核专业</th>
		<th>联系方式</th>
		<th>学员预约时间</th>
		<th>状态</th>
	</tr>
	<c:forEach items="${appointList}" var="info" varStatus="i">
		<tr>
			<td><input type="checkbox" class="check" value="${info.recordFlow}" statusId="${info.auditStatusId}" signStatusId="${info.siginStatusId}" onclick="checkSingel(this)">&nbsp;${i.index + 1}</td>
			<td>${info.doctorName}</td>
			<td>${info.sysUser.idNo}</td>
			<td>${info.sysUser.sexName}</td>
			<td>${info.doctor.sessionNumber}</td>
			<td>${info.doctor.orgName}</td>
			<td>${info.skillAssess.speName}</td>
			<td>${info.sysUser.userPhone}</td>
			<td>${info.appointTime}</td>
			<td>${info.auditStatusName}</td>
		</tr>
	</c:forEach>
</table>
<div id="detail"></div>
<div style="float:right;margin-top:100px;">
	<c:set var="pageView" value="${pdfn:getPageView(appointList)}" scope="request"/>
	<pd:pagination toPage="toPage1"/>
</div>