	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th width="7%">姓名</th>
				<th width="9%">人员类型</th>
				<th width="10%">证件号</th>
				<th width="5%">性别</th>
				<th width="5%">届别</th>
				<%--<th width="10%">培训类别</th>--%>
				<th width="9%">是否重培</th>
				<th width="9%">培训专业</th>
				<th width="9%">招录途径</th>
				<th width="9%">状态</th>
				<th width="10%">审核意见</th>
				<th width="13%">操作</th>
            </tr>
            <c:forEach items="${recruitList}" var="recruit" varStatus="a">
            	<tr>
					<td>${recruit.sysUser.userName}</td>
					<td>${recruit.resDoctor.doctorTypeName}</td>
					<td>${recruit.sysUser.idNo}</td>
					<td>${recruit.sysUser.sexName}</td>
					<td>${recruit.sessionNumber}</td>
					<%--<td>${recruit.catSpeName}</td>--%>
					<td>${recruit.isRetrain}</td>
					<td>${recruit.speName}</td>
					<td>
						<c:if test="${not empty recruit.signupWay and  recruit.signupWay eq 'DoctorSignup'}">
							报名
						</c:if>
						<c:if test="${not empty recruit.signupWay and  recruit.signupWay eq 'DoctorSend'}">
							报送
						</c:if>
					</td>
					<td>${recruit.auditStatusName}</td>
					<td>${recruit.admitNotice}</td>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] != 'N'}">
						<c:if test="${ recruit.sessionNumber == sysCfgMap['jsres_local_sessionNumber'] }">
							<c:choose>
								<c:when test="${recruit.auditStatusId eq 'Passed' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
									<td>
										<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
									</td>
								</c:when>
								<c:when test="${recruit.auditStatusId eq 'Auditing' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
									<td>
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
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] !=  'Y' or recruit.sessionNumber != sysCfgMap['jsres_local_sessionNumber']}">
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
      
