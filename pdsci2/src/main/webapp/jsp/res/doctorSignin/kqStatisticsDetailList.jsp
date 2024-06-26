<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
	</jsp:include>
	<style type="text/css">
		table.xllist a{color: blue;}
	</style>
	<script>
		function search(){
			var form = $("#myForm");
			form.submit();
		}

		function toPage2(page){
			if (page) {
				$("#currentPage2").val(page);
			}
			search();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<input type="hidden" name="currentPage2" id="currentPage2">
			<input type="hidden" name="startDate" value="${param.startDate}">
			<input type="hidden" name="endDate" value="${param.endDate}">
			<input type="hidden" name="doctorFlow" value="${param.doctorFlow}">
			<input type="hidden" name="typeId" value="${param.typeId}">
		</form>
		<div class="title1 clearfix">
			<table class="xllist">
				<tr>
					<th width="5%">姓名</th>
					<th width="5%">请假类型</th>
					<th width="15%">请假事由</th>
					<th width="15%">请假时间</th>
					<th width="10%">轮转科室</th>
					<th width="10%">状态</th>
					<th width="10%">带教老师</th>
					<th width="10%">科主任</th>
					<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
						<th width="10%">导师</th>
						<th width="10%">管理员</th>
					</c:if>
				</tr>
				<c:forEach items="${kqList}" var="kq">
					<tr>
						<td>${kq.doctorName}</td>
						<td>${kq.typeName }</td>
						<td>${kq.doctorRemarks}</td>
						<td>
								${kq.startDate}~${kq.endDate}<br/>
							<font style="font-weight: bold;">
								(${kq.intervalDays}天)
							</font>
						</td>
						<td style="text-align: center; ">${kq.schDeptName}</td>
						<td>${kq.auditStatusName}</td>
						<td>
							<c:if test="${kq.teacherName ne '-'}">
								<c:if test="${not empty kq.teacherAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
									</c:if>
									<a title="审核信息：${kq.teacherAuditInfo}">${kq.teacherName}</a>
								</c:if>
								<c:if test="${empty kq.teacherAgreeFlag}">

									<c:if test="${kq.auditStatusId eq 'Revoke'}">
										${kq.teacherName}
									</c:if>
									<c:if test="${kq.auditStatusId ne 'Revoke'}">
										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.teacherName}
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${kq.teacherName eq '-'}">
								${kq.teacherName}
							</c:if>
						</td>
						<td>
							<c:if test="${kq.headName ne '-'}">
								<c:if test="${not empty kq.headAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.headAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.headAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
									</c:if>
									<a title="审核信息：${kq.headAuditInfo}">${kq.headName}</a>
								</c:if>
								<c:if test="${empty kq.headAgreeFlag}">
									<c:set var="audit" value="false"></c:set>
									<c:if test="${kq.teacherName eq '-'}">
										<c:set var="audit" value="true"></c:set>
									</c:if>
									<c:if test="${kq.teacherName ne '-'}">
										<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
											<c:set var="audit" value="true"></c:set>
										</c:if>
										<c:if test="${empty kq.teacherAgreeFlag}">
											<c:set var="audit" value="true"></c:set>
										</c:if>
									</c:if>
									<c:if test="${audit}">

										<c:if test="${kq.auditStatusId eq 'Revoke'}">
											${kq.headName}
										</c:if>
										<c:if test="${kq.auditStatusId ne 'Revoke'}">
											<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.headName}
										</c:if>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${kq.headName eq '-'}">
								${kq.headName}
							</c:if>
						</td>
						<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
							<td>
								<c:if test="${kq.tutorName ne '-'}">
									<c:if test="${not empty kq.tutorAgreeFlag}">
										<c:if test="${GlobalConstant.FLAG_N eq kq.tutorAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
										</c:if>
										<c:if test="${GlobalConstant.FLAG_Y eq kq.tutorAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
										</c:if>
										<a title="审核信息：${kq.tutorAuditInfo}">${kq.tutorName}</a>
									</c:if>
									<c:if test="${empty kq.tutorAgreeFlag}">
										<c:set var="audit" value="false"></c:set>
										<c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  }">
											<c:set var="audit" value="true"></c:set>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' }">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:set var="audit" value="true"></c:set>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' }">
											<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
												<c:set var="audit" value="true"></c:set>
											</c:if>
											<c:if test="${empty kq.headAgreeFlag}">
												请等待主任审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName ne '-'  }">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													请等待主任审核
												</c:if>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${audit and 'tutor' eq roleFlag}">
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
										</c:if>
										<c:if test="${audit and 'tutor' ne roleFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.tutorName}
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${kq.tutorName eq '-'}">
									${kq.tutorName}
								</c:if>
							</td>
							<td>
								<c:if test="${kq.managerName ne '-'}">
									<c:if test="${not empty kq.managerAgreeFlag}">
										<c:if test="${GlobalConstant.FLAG_N eq kq.managerAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
										</c:if>
										<c:if test="${GlobalConstant.FLAG_Y eq kq.managerAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
										</c:if>
										<a title="审核信息：${kq.managerAuditInfo}">${kq.managerName}</a>
									</c:if>
									<c:if test="${empty kq.managerAgreeFlag}">
										<c:set var="audit" value="false"></c:set>
										<c:if test="${kq.teacherName eq '-' and kq.headName eq '-' and kq.tutorName eq '-'  }">
											<c:set var="audit" value="true"></c:set>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName eq '-'}">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:set var="audit" value="true"></c:set>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName eq '-' and kq.headName ne '-'  and kq.tutorName eq '-'}">
											<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
												<c:set var="audit" value="true"></c:set>
											</c:if>
											<c:if test="${empty kq.headAgreeFlag}">
												请等待主任审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  and kq.tutorName ne '-'}">
											<c:if test="${not empty kq.tutorAgreeFlag and kq.tutorAgreeFlag eq 'Y'}">
												<c:set var="audit" value="true"></c:set>
											</c:if>
											<c:if test="${empty kq.tutorAgreeFlag}">
												请等待导师审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
													<c:if test="${empty kq.tutorAgreeFlag}">
														请等待导师审核
													</c:if>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													请等待主任审核
												</c:if>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName eq '-'  }">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													请等待主任审核
												</c:if>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName ne '-'  }">
											<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
												<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.tutorAgreeFlag}">
													请等待导师审核
												</c:if>
											</c:if>
											<c:if test="${empty kq.teacherAgreeFlag}">
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
											<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
												<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.tutorAgreeFlag}">
													请等待导师审核
												</c:if>
											</c:if>
											<c:if test="${empty kq.headAgreeFlag}">
												请等待主任审核
											</c:if>
										</c:if>

										<c:if test="${audit  and 'manager' eq roleFlag}">
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
										</c:if>
										<c:if test="${audit and 'manager' ne roleFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.managerName}
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${kq.managerName eq '-'}">
									${kq.managerName}
								</c:if>
							</td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${empty kqList}">
					<tr>
						<td colspan="20">无记录</td>
					</tr>
				</c:if>
			</table>
			<p>
				<c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"/>
				<pd:pagination toPage="toPage2"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>