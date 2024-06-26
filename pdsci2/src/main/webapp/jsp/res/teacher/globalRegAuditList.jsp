<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<script type="text/javascript">
	function toSearch(){
		$(".selectTag").click();
	}
	function edit(flow,operUserFlow){
		var url="<s:url value='/res/rec/showRegistryForm'/>?roleFlag=${param.roleFlag}&type=open&recTypeId=${param.recTypeId}&schDeptFlow=${doctor.deptFlow}&recFlow="+flow+"&operUserFlow="+operUserFlow;
		jboxOpen(url,"${recTypeName}",1000,500);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<form id="searchForm">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<select name="doctorFlow" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${userList}" var="user">
								<option value="${user.userFlow}" <c:if test="${param.doctorFlow eq user.userFlow}">selected</c:if>>${user.userName}</option>
							</c:forEach>
						</select>
					</div>
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq param.roleFlag}">
					<div class="inputDiv">
						<label class="qlable">专&#12288;&#12288;业：</label>
						<select name="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
								<option value="${training.dictId}" <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
							</c:forEach>
						</select>
					</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;度：</label>
						<input type="text"  class="qtext" name="year" value="${param.year}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});" />
					</div>
					<c:if test="${!(GlobalConstant.RES_ROLE_SCOPE_MANAGER eq param.roleFlag)}">
					<div class="qcheckboxDiv">
						&#12288;
						<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" <c:if test="${GlobalConstant.FLAG_Y eq param.isCurrentFlag}">checked</c:if> value="${GlobalConstant.FLAG_Y}"/>
							轮转中学员
						</label>
						<input type="button"  class="searchInput"  value="查&#12288;询"  onclick="toSearch()" />
					</div>
					</c:if>
				</div>
			</form>

			<div class="resultDiv">
				<c:set var="recFlows" value=""/>
				<c:if test="${not empty recList}">
					<c:forEach items="${recList}" var="rec">
					<c:if test="${!recFlows.contains(rec.recFlow)}">
						<table class="xllist" width="100%">
						<tr>
							<td>
								<%--<c:if test="${(!empty rec.managerAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_MANAGER) || (!empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) || (!empty rec.headAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD) || (!empty rec.adminAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN)}">--%>
									<a style="color: blue; cursor: pointer;" onclick="edit('${rec.recFlow}','${rec.operUserFlow}')">查看</a>
								<%--</c:if>--%>
							</td>
							<td>
								<%--<c:if test="${!((!empty rec.managerAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_MANAGER) || (!empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) || (!empty rec.headAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD) || (!empty rec.adminAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN))}">--%>
									<a style="color: blue; cursor: pointer;" onclick="edit('${rec.recFlow}','${rec.operUserFlow}')">审核</a>
								<%--</c:if>--%>
							</td>
						</tr>
						<tr>
							<td>姓名：${rec.operUserName}</td>
							<td>
								<c:if test="${param.recTypeId eq globalRecTypeEnumAnnualActivity.id}">
									<font style="width: 12%;display: inline-block;vertical-align: top;">
										状态：
										<c:if test="${empty rec.adminAuditStatusId}">
											未审核
										</c:if>
										<c:if test="${rec.adminAuditStatusId eq recStatusEnumAdminAuditY.id}">
											审核通过
										</c:if>
										<c:if test="${rec.adminAuditStatusId eq recStatusEnumAdminAuditN.id}">
											审核不通过
										</c:if>
									</font>
								</c:if>
							</td>
						</tr>
						<tr>
							<c:forEach items="${viewListMap[rec.recFlow]}" var="viewInfo">
							<td>
									${viewInfo.title}：${viewInfo.value}
							</td>
							</c:forEach>
						</tr>

					</table>
					<c:set var="recFlows" value="${recFlows},${rec.recFlow}"/>
					</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${empty recList}">
					<table class="xllist" width="100%">
						<tr><td colspan="33">无记录</td></tr>
					</table>
				</c:if>



			</div>



		</div>
	</div>
</body>
</html>