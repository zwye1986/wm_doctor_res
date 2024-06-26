	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th width="5%">序号</th>
				<th width="8%">姓名</th>
				<th width="10%">人员类型</th>
				<th width="12%">证件号</th>
				<th width="5%">性别</th>
				<th width="5%">年级</th>
				<th width="10%">是否重培</th>
				<th width="10%">培训专业</th>
				<th width="10%">状态</th>
				<c:if test="${param.doctorSignupFlag eq 'DoctorSend'}">
					<th width="12%">审核意见</th>
				</c:if>
				<th width="13%">操作</th>
            </tr>
            <c:forEach items="${recruitList}" var="recruit" varStatus="a">
            	<tr>
					<td>${a.count}</td>
					<td>${recruit.sysUser.userName}</td>
					<td>${recruit.resDoctor.doctorTypeName}</td>
					<td>${recruit.sysUser.idNo}</td>
					<td>${recruit.sysUser.sexName}</td>
					<td>${recruit.sessionNumber}</td>
	                <td>${recruit.isRetrain}</td>
					<td>${recruit.speName}</td>
					<td>${recruit.auditStatusName}</td>
					<c:if test="${param.doctorSignupFlag eq 'DoctorSend'}">
						<td>
							<c:if test="${not empty recruit.globalNotice}">${recruit.globalNotice}</c:if>
							<c:if test="${empty recruit.globalNotice}">${recruit.admitNotice}</c:if>
						</td>
					</c:if>
					<td>
						<%--maintenance 是否是运维角色（只能查看）--%>
						<c:if test="${ recruit.sessionNumber == sysCfgMap['jsres_local_sessionNumber'] and maintenance ne 'Y'}">
							<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','examine');">审核</a>
						</c:if>
						<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
					</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty recruitList}">
				<tr>
					<td colspan="20" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(recruitList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
