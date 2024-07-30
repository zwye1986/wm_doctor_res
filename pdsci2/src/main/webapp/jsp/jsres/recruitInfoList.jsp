	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th width="5%">序号</th>
				<th width="8%">姓名</th>
				<th width="10%">人员类型</th>
				<th width="12%">证件号</th>
				<th width="5%">性别</th>
				<th width="5%">年级</th>
				<%--<th width="10%">培训类别</th>--%>
				<th width="10%">是否重培</th>
				<th width="10%">报考专业</th>
				<th width="10%">状态</th>
				<th width="13%">操作</th>
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
					<%--<td>${recruit.doctorStatusName}</td>--%>
	                <td>
						<c:if test="${recruit.reviewFlag eq 'OrgAuditing'}">
							待审核
						</c:if>
						<c:if test="${recruit.reviewFlag eq 'Passed'}">
							通过
						</c:if>
						<c:if test="${recruit.reviewFlag eq 'NotPassed'}">
							不通过
						</c:if>
					</td>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] != 'N'}">
						<c:if test="${ recruit.sessionNumber eq sysCfgMap['jsres_local_sessionNumber'] }">
							<c:choose>
								<c:when test="${recruit.doctorStatusId eq 'Passed' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
									<td>
										<c:if test="${recruit.recruitFlag ne 'Y'}">
											<a class="btn" onclick="auditRecruitInfo('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">重新审核</a>
										</c:if>
										<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
									</td>
								</c:when>
								<%--<c:when test="${recruit.doctorStatusId eq 'Auditing' and sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">--%>
								<c:when test="${sysCfgMap['jsres_is_check_start'] le currentDate and sysCfgMap['jsres_is_check_end'] ge currentDate}">
									<td>
										<c:if test="${isJointOrg eq 'Y' and recruit.doctorStatusId eq 'Auditing'}">
											<a class="btn" onclick="auditRecruitInfo('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">审核</a>
										</c:if>
<%--										// 不论是否选择协同基地培训，主基地均可审核--%>
										<c:if test="${isJointOrg eq 'N' and (recruit.doctorStatusId eq 'OrgAuditing' || recruit.doctorStatusId eq 'Auditing')}">
											<a class="btn" onclick="auditRecruitInfo('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">审核</a>
										</c:if>
										<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>
									</td>
								</c:when>
								<c:otherwise>
									<td><a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a></td>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:if>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] !=  'Y' or recruit.sessionNumber != sysCfgMap['jsres_local_sessionNumber']}">
						<td><a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a></td>
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
      
