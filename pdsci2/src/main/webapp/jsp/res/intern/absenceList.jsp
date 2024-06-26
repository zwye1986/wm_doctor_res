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
function editAbsence(absenceFlow){
	var title = "新增";
	if(absenceFlow){
		title = "修改";
	}
	var url = "<s:url value='/res/doc/editAbsence'/>?resRoleScope=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}&absenceFlow=" + absenceFlow;
	jboxOpen(url, title+"请假", 900, 400);
}

function delAbsence(absenceFlow){
	jboxConfirm("确认删除请假? " ,  function(){
		jboxStartLoading();
		var url = "<s:url value='/res/doc/delAbsence'/>?absenceFlow=" + absenceFlow;
		jboxPost(url, null,
			function(resp){
				if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
					window.location.reload(true);
				}
			}, null, true);
	});
}

function repealAbsence(absenceFlow){
	var url = "<s:url value='/res/doc/editAbsence'/>?resRoleScope=repealAbsence&absenceFlow=" + absenceFlow;
	jboxOpen(url, "销假申请", 900, 400);
}

function search(){
	var form = $("#searchForm");
	form.submit();
}

function toPage(page){
	$("#currentPage").val(page);
	jboxStartLoading();
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
			<form id="searchForm" action="<s:url value="/res/doc/absenceList"/>" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">请假类型：</label>
						<select name="absenceTypeId" class="qselect">
							<option value="">请选择</option>
							<c:forEach var="absenceType" items="${absenceTypeEnumList}">
								<c:if test="${absenceType.id ne absenceTypeEnumAbsenteeism.id or GlobalConstant.FLAG_Y eq isRegister}">
									<option value="${absenceType.id}" <c:if test="${param.absenceTypeId eq absenceType.id}">selected="selected"</c:if> >${absenceType.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qselect" name="doctorName" value="${param.doctorName}">
					</div>

					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
					<div class="lastDiv">
						<c:if test="${!(resDoctor.doctorTypeId eq 'Company' || resDoctor.doctorTypeId eq 'Social')}">
							<input type="button" value="新&#12288;增" class="searchInput" onclick="editAbsence('');"/>
						</c:if>
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
					<th width="7%">姓名</th>
					<th width="7%">人员类型</th>
					<th width="7%">请假类型</th>
					<th width="17%">请假事由</th>
					<th width="17%">请假时间</th>
					<th width="10%">轮转科室</th>
					<th width="10%">教学主任</th>
					<th width="10%">专业基地主任</th>
					<th width="10%">继续教育科</th>
					<th width="6%">销假状态</th>
					<th width="6%">操作</th>
				</tr>
				<c:forEach items="${doctorAbsenceList}" var="doctorAbsence">
					<%--<c:set var="isDue" value="${doctorAbsence.endDate.compareTo(pdfn:getCurrDate())}"/>--%>
					<c:set var="disagreeFlag" value="false"/>
					<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag or 
						GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag or 
						GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag
						}">
						<c:set var="disagreeFlag" value="true"/>
					</c:if>
					<c:set var="repealFlag" value="false"/>
					<c:if test="${'N'eq doctorAbsence.repealAbsence
						and !((empty doctorAbsence.teacherAgreeFlag) or
							(empty doctorAbsence.headAgreeFlag) or
							(empty doctorAbsence.managerAgreeFlag))
						and	!disagreeFlag}">
						<c:set var="repealFlag" value="true"/>
					</c:if>
					<tr>
						<td>${doctorAbsence.doctorName}</td>
						<td>${doctorAbsence.doctorTypeName}</td>
						<td>${doctorAbsence.absenceTypeName}</td>
						<td>${doctorAbsence.absenceReson}</td>
						<td>
							${doctorAbsence.startDate}~${doctorAbsence.endDate}<br/>
							<font style="font-weight: bold;">
								(${doctorAbsence.intervalDay}天)
							</font>
						</td>
						<td style="text-align: center; ">${doctorAbsence.schDeptName}</td>
						<td>
							<c:if test="${not empty doctorAbsence.teacherAgreeFlag}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.teacherAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
								</c:if>
								<a title="审核信息：${doctorAbsence.teacherAuditInfo}">${doctorAbsence.teacherName}</a>
							</c:if>
							<c:if test="${empty doctorAbsence.teacherAgreeFlag and
							 		!disagreeFlag}">
							 	<%--<c:choose>--%>
									<%--<c:when test="${isDue < 0 }">已过期</c:when>--%>
									<%--<c:otherwise>--%>

										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${doctorAbsence.teacherName}

									<%--</c:otherwise>--%>
								<%--</c:choose>--%>
							</c:if>
						</td>
						<td>
							<c:if test="${not empty doctorAbsence.headAgreeFlag}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.headAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
								</c:if>
								<a title="审核信息：${doctorAbsence.headAuditInfo}">${doctorAbsence.headName}</a>
							</c:if>
							<c:if test="${empty doctorAbsence.headAgreeFlag and
										 !disagreeFlag}">
								<%--<c:choose>--%>
									<%--<c:when test="${isDue < 0 }">已过期</c:when>--%>
									<%--<c:otherwise>--%>

										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${doctorAbsence.headName}

									<%--</c:otherwise>--%>
								<%--</c:choose>--%>
							</c:if>
						</td>
						<td>
							<c:if test="${not empty doctorAbsence.managerAgreeFlag}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.managerAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
								</c:if>
								<a title="审核信息：${doctorAbsence.managerAuditInfo}">${doctorAbsence.managerName}</a>
							</c:if>
							<c:if test="${empty doctorAbsence.managerAgreeFlag and
										 !disagreeFlag}">
								<%--<c:choose>--%>
									<%--<c:when test="${isDue<0}">已过期</c:when>--%>
									<%--<c:otherwise>--%>

										<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${doctorAbsence.managerName}

									<%--</c:otherwise>--%>
								<%--</c:choose>--%>
							</c:if>
						</td>
						<td>
							<c:if test="${'S' eq doctorAbsence.repealAbsence}">
								待审核
							</c:if>
							<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.repealAbsence}">
								已销假
							</c:if>
							<c:if test="${repealFlag}">
								未销假	
							</c:if>
						</td>
						<td>
						<c:if test="${repealFlag}">
							<a href="javascript:repealAbsence('${doctorAbsence.absenceFlow}');">销假</a>
						</c:if>
						<c:if test="${(empty doctorAbsence.teacherAgreeFlag) and (empty doctorAbsence.headAgreeFlag) and (empty doctorAbsence.managerAgreeFlag) and (doctorAbsence.doctorTypeId ne 'Graduate')}">
							<a href="javascript:editAbsence('${doctorAbsence.absenceFlow}');">编辑</a> |
							<a href="javascript:delAbsence('${doctorAbsence.absenceFlow}');">删除</a>
						</c:if>
						</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty doctorAbsenceList}">
					<tr>
						<td colspan="20">无记录</td>
					</tr>
				</c:if>
			</table>
			</form>
			<p>
			   	<c:set var="pageView" value="${pdfn:getPageView2(doctorAbsenceList, 10)}" scope="request"/> 
				<pd:pagination toPage="toPage"/>
			</p>
			</div>
		</div>
	</div>
</body>
</html>