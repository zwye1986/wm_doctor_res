<div class="div_search">
<form id="appointForm" method="post">
	<input class="clinicalFlow" type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
	<input class="clinicalName" type="hidden" name="clinicalName" value="${clinicalName}"/>
	<input class="speName" type="hidden" name="speName" value="${speName}"/>
	<input class="year" type="hidden" name="year" value="${year}"/>
	<input class="isLocal" type="hidden" name="isLocal" value="${isLocal}"/>
	<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
	<table class="searchTable">
		<tr>
			<td style="border:0px;">
				<span style=""></span>审核状态：
				<select name="auditStatusId" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${auditStatusEnumList}" var="status">
						<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
					</c:forEach>
				</select>&#12288;&#12288;
				<span style="padding-left:10px;"></span>姓名：
				<input type="text" class="input" name="appointDoctorName" value="${param.appointDoctorName}">
				<%--<span style="padding-left:20px;"></span>--%>
				<br/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="查&#12288;询" onclick="toPage1(1)"/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="审&#12288;核" onclick="auditOpt()"/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="撤&#12288;销" onclick="backOpt()"/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="导&#12288;出" onclick="exportAppoint()"/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="导&#12288;入" onclick="importExcel()"/>
				<input style="margin-top: 5px" type="button" class="btn_green" value="导出准考证" onclick="expDocTickets()"/>
			</td>
		</tr>
	</table>
</form>
</div>
<div style="padding: 0px 40px;">
<div class="main_bd clearfix" style="margin-top:20px;">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
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
		<c:if test="${empty appointList}">
			<tr>
				<td colspan="10">无记录！</td>
			</tr>
		</c:if>
		<c:if test="${not empty appointList}">
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
		</c:if>
	</table>
</div>
<%--<div id="detail"></div>--%>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(appointList)}" scope="request"/>
	<pd:pagination-jsres toPage="toPage1"/>
</div>
</div>
