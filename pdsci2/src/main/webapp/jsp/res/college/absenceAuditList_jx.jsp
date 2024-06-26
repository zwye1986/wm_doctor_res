
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
		<%--function save(absenceFlow, agreeFlag){--%>
		<%--var title = "同意";--%>
		<%--if("${GlobalConstant.FLAG_N}" == agreeFlag){--%>
		<%--title = "不同意";--%>
		<%--}--%>
		<%--jboxConfirm("确认"+ title +"请假? " ,  function(){--%>
		<%--jboxStartLoading();--%>
		<%--var url = "<s:url value='/res/teacher/saveAbsenceAudit'/>";--%>
		<%--var data = {--%>
		<%--absenceFlow : absenceFlow,--%>
		<%--<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER == resRoleScope}">--%>
		<%--teacherAgreeFlag:agreeFlag,--%>
		<%--</c:if>--%>
		<%--<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD == resRoleScope}">--%>
		<%--headAgreeFlag:agreeFlag,--%>
		<%--</c:if>--%>
		<%--<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER == resRoleScope}">--%>
		<%--managerAgreeFlag:agreeFlag--%>
		<%--</c:if>--%>
		<%--};--%>
		<%--jboxPost(url, data,--%>
		<%--function(resp){--%>
		<%--if("${GlobalConstant.SAVE_SUCCESSED}" == resp){--%>
		<%--window.location.reload(true);--%>
		<%--}--%>
		<%--}, null, true);--%>
		<%----%>
		<%--});--%>
		<%--}--%>
		function save(absenceFlow, agreeFlag){
			var url = "<s:url value='/res/teacher/saveAbsenceInfo'/>?absenceFlow="+absenceFlow+"&agreeFlag="+agreeFlag+"&resRoleScope="+'${resRoleScope}';
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
		function repealAbsence(absenceFlow){
			var url = "<s:url value='/res/doc/editAbsence'/>?resRoleScope=repealAbsenceAudit&absenceFlow=" + absenceFlow;
			jboxOpen(url, "销假申请", 900, 400);
		}
	</script>

</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/res/teacher/absenceAudit/${resRoleScope}'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
				<div class="queryDiv" style="min-width: 880px;max-width: 880px;">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">状&#12288;&#12288;态：</label>
						<select name="repealAbsence"  class="qselect">
							<option value="">全部</option>
							<option value="${GlobalConstant.FLAG_N}" <c:if test="${param.repealAbsence eq GlobalConstant.FLAG_N}">selected="selected"</c:if>>未销假</option>
							<option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.repealAbsence eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>>已销假</option>
						</select>
					</div>
					<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
						~
						<input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq resRoleScope}">
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
						<th width="5%">姓名</th>
						<th width="5%">请假类型</th>
						<th width="15%">请假事由</th>
						<th width="15%">请假时间</th>
						<th width="15%">轮转科室</th>
						<th width="5%">请假材料</th>
						<th width="10%">带教老师</th>
						<th width="10%">教学主任</th>
						<th width="10%">管理员</th>
						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq resRoleScope}">
							<th width="5%">销假状态</th>
							<th width="5%">操作</th>
						</c:if>
					</tr>
					<c:set var="emptyRecord" value="true"/>
					<c:forEach items="${doctorAbsenceList}" var="doctorAbsence">
						<c:set var="isDue" value="1"/>
						<c:set var="disagreeFlag" value="false"/>
						<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag or
							GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag or
							GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag
							}">
							<c:set var="disagreeFlag" value="true"/>
						</c:if>
						<c:set var="emptyRecord" value="false"/>
						<c:set var="repealFlag" value="false"/>
						<tr>
							<td>${doctorAbsence.doctorName}</td>
							<td>${doctorAbsence.absenceTypeName}</td>
							<td>${doctorAbsence.absenceReson}</td>
							<td>${doctorAbsence.startDate}~${doctorAbsence.endDate}<br/><font style="font-weight: bold;">(${doctorAbsence.intervalDay}天)</font></td>
							<td>${doctorAbsence.schDeptName}</td>
							<td><a id="down" href='<s:url value="/gzzyjxres/doctor/fileDown?fileFlow=${doctorAbsence.makingFile}"/>'>${fileMap[doctorAbsence.makingFile].fileName}</a></td>
							<td>
								<c:if test="${not empty doctorAbsence.teacherAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png' />" title="审核不通过"/>
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" title="审核通过"/>
									</c:if>
									<a title="审核信息：${doctorAbsence.teacherAuditInfo}">${doctorAbsence.teacherName}</a>
								</c:if>
								<c:if test="${empty doctorAbsence.teacherAgreeFlag and GlobalConstant.RES_ROLE_SCOPE_TEACHER ne resRoleScope}">
									<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${doctorAbsence.teacherName}
								</c:if>
								<c:if test="${isDue >= 0 && empty doctorAbsence.teacherAgreeFlag and
											GlobalConstant.RES_ROLE_SCOPE_TEACHER == resRoleScope}">
									<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
									<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
								</c:if>
							</td>
							<td>
								<c:if test="${doctorAbsence.intervalDay >= sysCfgMap['res_absence_head_day']}">
									<c:if test="${not empty doctorAbsence.headAgreeFlag}">
										<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" title="审核不通过"/>
										</c:if>
										<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.headAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" title="审核通过"/>
										</c:if>
										<a title="审核信息：${doctorAbsence.headAuditInfo}">${doctorAbsence.headName}</a>
									</c:if>
									<c:if test="${empty doctorAbsence.headAgreeFlag and GlobalConstant.RES_ROLE_SCOPE_HEAD ne resRoleScope}">
										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${doctorAbsence.headName}
									</c:if>
									<c:if test="${isDue >= 0 && empty doctorAbsence.headAgreeFlag and GlobalConstant.RES_ROLE_SCOPE_HEAD eq resRoleScope
										and doctorAbsence.teacherAgreeFlag eq 'Y'}">
										<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
										<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
									</c:if>
								</c:if>
							</td>
							<td>
								<c:if test="${doctorAbsence.intervalDay >= sysCfgMap['res_absence_manage_day']}">
									<c:if test="${not empty doctorAbsence.managerAgreeFlag}">
										<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" title="审核不通过"/>
										</c:if>
										<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.managerAgreeFlag}">
											<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" title="审核通过"/>
										</c:if>
										<a title="审核信息：${doctorAbsence.managerAuditInfo}">${doctorAbsence.managerName}</a>
									</c:if>
									<c:if test="${empty doctorAbsence.managerAgreeFlag and GlobalConstant.RES_ROLE_SCOPE_MANAGER ne resRoleScope}">
										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${doctorAbsence.managerName}
									</c:if>
									<c:if test="${isDue >= 0 && empty doctorAbsence.managerAgreeFlag && GlobalConstant.RES_ROLE_SCOPE_MANAGER == resRoleScope
										and doctorAbsence.headAgreeFlag eq 'Y'}">
										<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
										<a href="javascript:save('${doctorAbsence.absenceFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
									</c:if>
								</c:if>
							</td>
							<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER==resRoleScope}">
								<td>
									<c:if test="${'S' eq doctorAbsence.repealAbsence}">
										待审核
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.repealAbsence}">
										已销假
									</c:if>
									<c:if test="${'N'eq doctorAbsence.repealAbsence
											and !((empty doctorAbsence.teacherAgreeFlag) or
												(empty doctorAbsence.headAgreeFlag) or
												(empty doctorAbsence.managerAgreeFlag))
											and	!disagreeFlag}">
										未销假
									</c:if>
								</td>
								<td>
									<c:if test="${'S' eq doctorAbsence.repealAbsence && empty doctorAbsence.repealAbsenceDay}">
										<a href="javascript:repealAbsence('${doctorAbsence.absenceFlow}');">销假审核</a>
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
				<c:set var="pageView" value="${pdfn:getPageView(doctorAbsenceList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>