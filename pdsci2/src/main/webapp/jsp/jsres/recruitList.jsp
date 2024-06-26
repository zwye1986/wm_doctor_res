	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th width="5%">序号</th>
				<th width="8%">姓名</th>
				<th width="10%">人员类型</th>
				<th width="10%">证件号</th>
				<th width="5%">性别</th>
				<th width="5%">年级</th>
				<%--<th width="10%">培训类别</th>--%>
				<th width="8%">是否重培</th>
				<th width="9%">培训专业</th>
				<th width="8%">招录途径</th>
				<th width="10%">状态</th>
				<th width="12%">审核意见</th>
				<th width="10%">操作</th>
            </tr>
            <c:forEach items="${recruitList}" var="recruit" varStatus="a">
            	<tr>
<%-- 	             	<td><input type="checkbox" value="${recruit.doctorFlow}"></td> --%>
					<td>${a.count}</td>
					<td>${recruit.sysUser.userName}</td>
					<td>${recruit.resDoctor.doctorTypeName}</td>
					<td>${recruit.sysUser.idNo}</td>
					<td>${recruit.sysUser.sexName}</td>
					<td>${recruit.sessionNumber}</td>
					<%--<td>${recruit.catSpeName}</td>--%>
	                <td>${recruit.isRetrain}</td>
					<td>${recruit.speName}</td>
					<td>
						<c:if test="${recruit.signupWay eq 'DoctorSend'}">报送</c:if>
						<c:if test="${recruit.signupWay eq 'DoctorSignup'}">招录</c:if>
					</td>
					<td>${recruit.auditStatusName}</td>
					<td>
						<c:if test="${empty recruit.globalNotice}">${recruit.admitNotice}</c:if>
						<c:if test="${not empty recruit.globalNotice}">${recruit.globalNotice}</c:if>
					</td>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] != 'N'}">--%>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit_register'] != 'N'}">
						<c:if test="${ recruit.sessionNumber == sysCfgMap['jsres_local_sessionNumber'] }">
							<c:if test="${isJointOrg eq 'Y'}">
								<c:choose>
									<c:when test="${recruit.jointOrgAudit eq 'Passed' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','reExamine');">重审</a>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:when>
									<c:when test="${recruit.jointOrgAudit eq 'Auditing' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','examine');">审核</a>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${isJointOrg eq 'N'}">
								<c:choose>
									<c:when test="${recruit.orgAudit eq 'Passed' and recruit.catSpeId eq 'DoctorTrainingSpe'
										and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','reExamine');">重审</a>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:when>
									<c:when test="${recruit.orgAudit eq 'Auditing' and recruit.jointOrgAudit eq 'Passed'
										and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','examine');">审核</a>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:if>
					</c:if>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] !=  'Y' or recruit.sessionNumber != sysCfgMap['jsres_local_sessionNumber']}">--%>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit_register'] !=  'Y' or recruit.sessionNumber != sysCfgMap['jsres_local_sessionNumber']}">
						<td>
							<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
						</td>
					</c:if>
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
      
