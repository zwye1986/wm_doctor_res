
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style type="text/css">
	table.xllist a{color: blue;cursor: pointer;}
</style>
<script type="text/javascript">

	function save(recordFlow, agreeFlag){
		var url = "<s:url value='/res/doctorSignin/saveKqInfo'/>?recordFlow="+recordFlow+"&agreeFlag="+agreeFlag+"&roleFlag="+'${roleFlag}';
		jboxOpen(url,"审核意见",500,300,true);
	}
	
	function search(){
		jboxStartLoading();
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value='/res/doctorSignin/leaveAuditList/${roleFlag}'/>" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">请假类型：</label>
							<select name="typeId" class="qselect">
								<option value="">请选择</option>
								<c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
									<option value="${type.dictId}" <c:if test="${param.typeId eq type.dictId}">selected="selected"</c:if> >${type.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
							<label class="qlable">请假时间：</label>
							<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
							~
							<input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 435px;max-width: 400px;">
							学员类型：
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input type="checkbox" name="doctorTypeIdArys" value="${type.dictId}"
										${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
								</c:forEach>
						</div>
						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER==resRoleScope}">
							<div class="inputDiv">
								<label class="qlable">轮转科室：</label>
								<select name="schDeptFlow" class="qselect">
									<option value="">全部</option>
									<c:forEach items="${schDeptList}" var="schDetp">
										<option value="${schDetp.schDeptFlow}" <c:if test="${param.schDeptFlow eq schDetp.schDeptFlow}">selected="selected"</c:if>>${schDetp.schDeptName}</option>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<div class="lastDiv">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						</div>
					</div>

				</form>
				<div style="float: right;margin: 0px auto 10px auto">
					<span>
					<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;待审核&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>"/>&nbsp;审核通过&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/>&nbsp;审核不通过&#12288;
					</span>
				</div>
				<div class="resultDiv">
				<table class="xllist">
					<tr>
						<th width="4%">姓名</th>
						<th width="5%">请假类型</th>
						<th width="15%">请假事由</th>
						<th width="12%">请假时间</th>
						<th width="10%">轮转科室</th>
						<th width="9%">请假材料</th>
						<th width="10%">带教老师</th>
						<th width="10%">科主任</th>
						<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
							<th width="10%">导师</th>
							<th width="10%">管理员</th>
						</c:if>
					</tr>
					<c:set var="emptyRecord" value="true"/>
					<c:forEach items="${kqList}" var="kq">
						<c:set var="emptyRecord" value="false"/>
						<c:set var="disagreeFlag" value="false"/>
						<tr>
							<td>${kq.doctorName}</td>
							<td>${kq.typeName}</td>
							<td>${kq.doctorRemarks}</td>
							<td>
								${kq.startDate}~${kq.endDate}<br/>
								<font style="font-weight: bold;">
									(${kq.intervalDays}天)
								</font>
							</td>
							<td>${kq.schDeptName}</td>
							<td>
								<c:forEach items="${fileMap[kq.recordFlow]}" var="f">
									<a href="${sysCfgMap['upload_base_url']}/${f.filePath}" target="_blank">${f.fileName}</a>
									<br/>
								</c:forEach>
							</td>
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
									<c:if test="${empty kq.teacherAgreeFlag and 'teacher' ne roleFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.teacherName}
									</c:if>
									<c:if test="${empty kq.teacherAgreeFlag and 'teacher' eq roleFlag}">
										<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
										<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
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
												请等待带教审核
											</c:if>
										</c:if>
										<c:if test="${audit and 'head' eq roleFlag}">
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
											<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
										</c:if>
										<c:if test="${audit and 'head' ne roleFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.headName}
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

					<c:if test="${emptyRecord}">
						<tr>
							<td style="text-align: center" colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div class="resultDiv">
		             <c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
		        </div>
			</div>
		</div>
	</div>
</body>
</html>