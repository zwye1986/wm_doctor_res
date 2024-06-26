<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
		table.xllist a{color: blue;}
	</style>
	<script type="text/javascript">
		function editKq(recordFlow){
			var title = "新增";
			if(recordFlow){
				title = "修改";
			}
			var url = "<s:url value='/res/doctorSignin/editLeave'/>?roleFlag=doctor&recordFlow=" + recordFlow;
			jboxOpen(url, title+"请假", 900, 400);
		}

		function delKq(recordFlow){
			jboxConfirm("确认删除请假? " ,  function(){
				jboxStartLoading();
				var url = "<s:url value='/res/doctorSignin/delKq'/>?recordFlow=" + recordFlow;
				jboxPost(url, null,
						function(resp){
							if("1" == resp){
								jboxTip("操作成功");
								search();
							}
						}, null, false);
			});
		}
		function revokeKq(recordFlow){
			jboxConfirm("确认撤销请假? " ,  function(){
				jboxStartLoading();
				var url = "<s:url value='/res/doctorSignin/revokeKq'/>?recordFlow=" + recordFlow;
				jboxPost(url, null,
						function(resp){
							if("1" == resp){
								jboxTip("操作成功");
								search();
							}
						}, null, false);
			});
		}

		function search(){
			var form = $("#searchForm");
			form.submit();
		}

		function toPage(page){
			if (page) {
				$("#currentPage").val(page);
			}
			search();
		}
		function calculateDay(){
			var startDate = $("input[name='startDate']").val();
			var endDate = $("input[name='endDate']").val();
			if("" == startDate || "" == endDate){
				return;
			}
			if(endDate < startDate){
				jboxTip("结束日期不能早于开始日期！");
				return;
			}
		}
	</script>

</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doctorSignin/leaveList"/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv" style="max-width: 450px;">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" style="width: 120px;" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"/>
						~ <input type="text" class="qtime" style="width: 120px;" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  readonly="readonly"/>
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
					<div class="inputDiv" style="text-align: left">&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						<input type="button" value="新&#12288;增" class="search" onclick="editKq('');"/>
					</div>

				</div>
				<div style="float: right;margin: 0px auto 10px auto">
					<span>
					<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;待审核&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>"/>&nbsp;审核通过&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/>&nbsp;审核不通过&#12288;
					</span>
				</div>
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
						<th width="10%">操作</th>
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
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' }">
												<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName ne '-' and kq.headName ne '-'  }">
												<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
													<c:if test="${empty kq.headAgreeFlag}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
												</c:if>
												<c:if test="${empty kq.teacherAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${audit}">

												<c:if test="${kq.auditStatusId eq 'Revoke'}">
													${kq.tutorName}
												</c:if>
												<c:if test="${kq.auditStatusId ne 'Revoke'}">
													<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.tutorName}
												</c:if>
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
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName eq '-' and kq.headName ne '-'  and kq.tutorName eq '-'}">
												<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  and kq.tutorName ne '-'}">
												<c:if test="${not empty kq.tutorAgreeFlag and kq.tutorAgreeFlag eq 'Y'}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
												<c:if test="${empty kq.tutorAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
												<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
														<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
															<c:set var="audit" value="true"></c:set>
														</c:if>
														<c:if test="${empty kq.tutorAgreeFlag}">
															<c:set var="audit" value="true"></c:set>
														</c:if>
													</c:if>
													<c:if test="${empty kq.headAgreeFlag}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
												</c:if>
												<c:if test="${empty kq.teacherAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName eq '-'  }">
												<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
													<c:if test="${empty kq.headAgreeFlag}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
												</c:if>
												<c:if test="${empty kq.teacherAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName ne '-'  }">
												<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
													<c:if test="${empty kq.tutorAgreeFlag}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
												</c:if>
												<c:if test="${empty kq.teacherAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
												<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
													<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
													<c:if test="${empty kq.tutorAgreeFlag}">
														<c:set var="audit" value="true"></c:set>
													</c:if>
												</c:if>
												<c:if test="${empty kq.headAgreeFlag}">
													<c:set var="audit" value="true"></c:set>
												</c:if>
											</c:if>
											<c:if test="${audit}">
												<c:if test="${kq.auditStatusId eq 'Revoke'}">
													${kq.managerName}
												</c:if>
												<c:if test="${kq.auditStatusId ne 'Revoke'}">
													<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.managerName}
												</c:if>
											</c:if>
										</c:if>
									</c:if>
									<c:if test="${kq.managerName eq '-'}">
										${kq.managerName}
									</c:if>
								</td>
							</c:if>
							<td>
								<c:if test="${kq.auditStatusId eq 'Auditing'}">
									<a href="javascript:revokeKq('${kq.recordFlow}');">撤销</a> |
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a>
								</c:if>
								<c:if test="${kq.auditStatusId eq 'Revoke'}">
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty kqList}">
						<tr>
							<td colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
			</form>
			<p>
				<c:set var="pageView" value="${pdfn:getPageView2(kqList, 10)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>