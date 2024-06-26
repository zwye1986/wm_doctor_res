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
	<jsp:param name="jquery_fullcalendar" value="true" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
	function editApply(userFlow,applyTypeId,recordFlow){
		var url =  "<s:url value='/gyxjgl/change/apply/editAwardApply'/>?editFlag=Y&recordFlow="+recordFlow+"&applyTypeId="+applyTypeId+"&userFlow="+userFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,"申请编辑",1100,550);
	}
	function submit(recordFlow){
		jboxConfirm("确认提交?",function(){
		jboxPost("<s:url value='/gyxjgl/change/apply/submitApply'/>" , {"recordFlow":recordFlow} , function(resp){
			window.location.reload();
		} , null , true);
		});
	}
	function apply(userFlow){
		var url = "<s:url value='/gyxjgl/change/apply/editAwardApply'/>?userFlow="+userFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,"申请编辑",1100,550);
	}
	function lookAudit(applyTypeId,recordFlow){
		var url =  "<s:url value='/gyxjgl/change/apply/editAwardApply'/>?audit=Y&lookFlag=Y&auditFlag=Y&viewFlag=Y&applyTypeId="+applyTypeId+"&recordFlow="+recordFlow;
		jboxOpen(url ,"查看申请",1100,550);
	}
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" method="post" action="<s:url value='/gyxjgl/change/apply/awardApply'/>">
				<input id="currentPage" type="hidden" name="currentPage"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">申请类型：</label>
						<select name="applyTypeId" class="qselect"  >
							<option value="">全部</option>
							<c:forEach items="${userAwardApplyTypeEnumList}" var="applyType">
								<option value="${applyType.id}" ${param.applyTypeId eq applyType.id?'selected':''}>${applyType.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">申请时间：</label>
						<input type="text"  class="qtext" name="applyTime" value="${param['applyTime']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly">
					</div>
					<div class="inputDiv">
						<label class="qlable">状&#12288;&#12288;态：</label>
						<select name="applyStatus" class="qselect"  >
							<option value="">全部</option>
							<c:forEach items="${userChangeApplyStatusEnumList}" var="applyStatus">
								<option value="${applyStatus.id}" ${param.applyStatus eq applyStatus.id?'selected':''}>${applyStatus.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage('1');"/>
					</div>
					<div class="lastDiv"><input type="button" value="申&#12288;请" class="searchInput" onclick="apply('');"/></div>
				</div>
			</form>
			<div class="resultDiv">
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;">序号</td>
						<td style="text-align: center;padding: 0px;">学员姓名</td>
						<td style="text-align: center;padding: 0px;">学号</td>
						<td style="text-align: center;padding: 0px;">专业</td>
						<td style="text-align: center;padding: 0px;">导师</td>
						<td style="text-align: center;padding: 0px;">培养单位</td>
						<td style="text-align: center;padding: 0px;">申请类型</td>
						<td style="text-align: center;padding: 0px;">申请时间</td>
						<td style="text-align: center;padding: 0px;">批准时间</td>
						<td style="text-align: center;padding: 0px;">批准人</td>
						<td style="text-align: center;padding: 0px;">状态</td>
						<td style="text-align: center;padding: 0px;">操作</td>
					</tr>
					<c:forEach items="${eduUserChangeApplies}" var="edu" varStatus="aa">
						<tr>
							<td style="text-align: center;padding: 0px;">${aa.count}</td>
							<td style="text-align: center;padding: 0px;">${sysUser.userName}</td>
							<td style="text-align: center;padding: 0px;">${user.sid}</td>
							<td style="text-align: center;padding: 0px;">${user.majorName}</td>
							<td style="text-align: center;padding: 0px;">${user.firstTeacher}<c:if test="${user.secondTeacher!=null}">,${user.secondTeacher}</c:if></td>
							<td style="text-align: center;padding: 0px;">${doctor.orgName}</td>
							<td style="text-align: center;padding: 0px;">${edu.applyTypeName}</td>
							<td style="text-align: center;padding: 0px;">${edu.applyTime}</td>
							<td style="text-align: center;padding: 0px;">${userAuditTimeMap[edu.recordFlow]}</td>
							<td style="text-align: center;padding: 0px;">${userAuditPersonMap[edu.recordFlow]}</td>
							<td style="text-align: center;padding: 0px;">${edu.statusName}</td>
							<td style="text-align: center;padding: 0px;">
								<c:set var="bool" value="${edu.statusId eq userChangeApplyStatusEnumSave.id}"/>
								<c:if test="${bool}">
									<a style="cursor: pointer;color: blue;" onclick="editApply('${edu.userFlow}','${edu.applyTypeId}','${edu.recordFlow}');">编辑</a>
									<a style="cursor: pointer;color: blue;"onclick="submit('${edu.recordFlow}');">提交</a>
								</c:if>
								<c:if test="${!bool}">
									<a style="cursor: pointer;color: blue;" onclick="lookAudit('${edu.applyTypeId}','${edu.recordFlow}');">查看</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty eduUserChangeApplies}">
						<td colspan="15" style="text-align: center;padding: 0px;">无记录！</td>
					</c:if>
				</table>
				<c:set var="pageView" value="${pdfn:getPageView(eduUserChangeApplies)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
		</div>

</div>
</div>
</body>
</html>
