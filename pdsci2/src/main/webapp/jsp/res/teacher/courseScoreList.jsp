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
					<table class="basic" style="width: 100%;margin-top: 10px;">
						<tr>
							<td>
								姓名：
								<select name="doctorFlow" onchange="toSearch();" style="width: 100px;">
									<option/>
									<c:forEach items="${userList}" var="user">
										<option value="${user.userFlow}" <c:if test="${param.doctorFlow eq user.userFlow}">selected</c:if>>${user.userName}</option>
									</c:forEach>
								</select>
								&#12288;
								<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq param.roleFlag}">
								专业：
								<select name="trainingSpeId" onchange="toSearch();" style="width: 100px;">
									<option/>
									<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
										<option value="${training.dictId}" <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
									</c:forEach>
								</select>
								</c:if>
								&#12288;
								年度：
								<input type="text" name="year" value="${param.year}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});" style="width: 100px;" onchange="toSearch();"/>
								&#12288;
								<c:if test="${!(GlobalConstant.RES_ROLE_SCOPE_MANAGER eq param.roleFlag)}">
								<label>
									<input type="checkbox" name="isCurrentFlag" <c:if test="${GlobalConstant.FLAG_Y eq param.isCurrentFlag}">checked</c:if> value="${GlobalConstant.FLAG_Y}" onchange="toSearch();"/>
									轮转中学员
								</label>
								</c:if>
							</td>
						</tr>
					</table>
				</form>
				
				<c:set var="recFlows" value=""/>
				<c:forEach items="${userList}" var="user">
					<c:set var="rec" value="${viewListMap[user.userFlow]}"></c:set>
					<c:if test="${user.userFlow eq param.doctorFlow or (empty param.doctorFlow)}">
						<div class="ith" style="cursor: pointer;border: 1px solid #ccc;margin-top: 10px;padding: 10px;">
							<div style="float: right;">
								<c:if test="${(!empty rec.managerAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_MANAGER) || (!empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) || (!empty rec.headAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD) || (!empty rec.adminAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN)}">
									<a style="color: blue; cursor: pointer;" onclick="edit('${rec.recFlow}','${user.userFlow}')">查看</a>
								</c:if>
								<c:if test="${!((!empty rec.managerAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_MANAGER) || (!empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) || (!empty rec.headAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD) || (!empty rec.adminAuditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN))}">
									<a style="color: blue; cursor: pointer;" onclick="edit('${rec.recFlow}','${user.userFlow}')">审核</a>
								</c:if>
							</div>
							<div style="width: 95%;">
								<font style="width: 13%;display: inline-block;vertical-align: top;">
									姓名：${user.userName}
								</font>
							</div>
						</div>
					</c:if>
				</c:forEach>	
				<c:if test="${empty userList}">
					<div class="ith" style="border: 1px solid #ccc;margin-top: 10px;padding: 10px;text-align: center;">无记录</div>
				</c:if>
			</div>
		</div>
</body>
</html>