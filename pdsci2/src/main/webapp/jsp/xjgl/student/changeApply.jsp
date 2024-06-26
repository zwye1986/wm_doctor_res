
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

<style type="text/css">
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
	.td{text-align: center;}
</style>
<script type="text/javascript">
	function editApply(userFlow,applyTypeId,recordFlow){
		var url =  "<s:url value='/xjgl/change/apply/editApply'/>?editFlag=Y&recordFlow="+recordFlow+"&applyTypeId="+applyTypeId+"&userFlow="+userFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,"申请编辑",1100,550);
	}
	function submit(recordFlow){
	jboxConfirm("确认提交?",function(){
	jboxPost("<s:url value='/xjgl/change/apply/submitApply'/>" , {"recordFlow":recordFlow} , function(resp){
		window.location.reload();
	} , null , true);
	});
}
	function look(applyTypeId,recordFlow){
		var url =  "<s:url value='/xjgl/change/apply/editApply'/>?lookFlag=Y&viewFlag=Y&applyTypeId="+applyTypeId+"&recordFlow="+recordFlow; 
		jboxOpen(url ,"查看申请",1100,550);
	}
	function Apply(userFlow){
		var url = "<s:url value='/xjgl/change/apply/editApply'/>?userFlow="+userFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,"申请编辑",1100,550);
	}
	function lookAudit(applyTypeId,recordFlow){
		var url =  "<s:url value='/xjgl/change/apply/editApply'/>?audit=Y&lookFlag=Y&auditFlag=Y&viewFlag=Y&applyTypeId="+applyTypeId+"&recordFlow="+recordFlow; 
		jboxOpen(url ,"查看申请",1100,550);
	}
	function print(recordFlow){
		jboxTip("打印中,请稍等...");
		var url = '<s:url value="/xjgl/change/apply/print"/>?recordFlow='+recordFlow;
		window.location.href = url;	
	}
	function search(){
		$("#changeFlag").val('${GlobalConstant.FLAG_Y}');
		$("#searchForm").submit();
	}

	function toPage(page){
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
			<form id="searchForm" method="post" action="<s:url value='/xjgl/change/apply/editApply'/>">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<input id="changeFlag" type="hidden" name="changeFlag" value=""/>
				<c:if test="${sessionScope.currWsId eq GlobalConstant.CMIS_WS_ID}">
					<table style="width: 100%;min-width: 1080px;margin: 10px 0px;border: none;">
						<tr>
							<td style="line-height: 260%;">
								申请类型：
								<select name="applyTypeId" style="width: 141px;">
									<option value="">全部</option>
									<c:forEach items="${userChangeApplyTypeEnumList}" var="applyType">
										<option value="${applyType.id}" ${param.applyTypeId eq applyType.id?'selected':''}>${applyType.name}</option>
									</c:forEach>
								</select>
								&#12288;申请时间：
								<input type="text" style="width: 137px;" name="applyTime" value="${param['applyTime']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly">
								&#12288;状&#12288;&#12288;态：
								<select name="applyStatus" style="width: 141px;">
									<option value="">全部</option>
									<c:forEach items="${userChangeApplyStatusEnumList}" var="applyStatus">
										<option value="${applyStatus.id}" ${param.applyStatus eq applyStatus.id?'selected':''}>${applyStatus.name}</option>
									</c:forEach>
								</select>
								&#12288;
								<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
								<input type="button" value="异动申请" class="search" onclick="Apply('${sessionScope.currUser.userFlow}');"/>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${sessionScope.currWsId ne GlobalConstant.CMIS_WS_ID}">
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">申请类型：</label>
							<select name="applyTypeId" class="qselect"  >
								<option value="">全部</option>
								<c:forEach items="${userChangeApplyTypeEnumList}" var="applyType">
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
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						</div>
						<div class="lastDiv">
							<input type="button" value="异动申请" class="searchInput" onclick="Apply('${sessionScope.currUser.userFlow}');"/>
						</div>
					</div>
				</c:if>
			</form>
			<div class="resultDiv">
				<table class="basic" width="100%">
					<%--<tr>--%>
						<%--<th colspan="12" ><span style="float: left;padding-left: 10px;padding-top: 10px">异动申请一览表 </span>--%>
							<%--<input type="button" value="异动申请  " class="search" onclick="Apply('${sessionScope.currUser.userFlow}');" style="float: right;margin-right:10px;margin-top: 7px;margin-bottom: 7px"></th>--%>
					<%--</tr>--%>
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
							<td style="text-align: center;padding: 0px;">${UserAuditTimeMap[edu.recordFlow]}</td>
							<td style="text-align: center;padding: 0px;">${userAuditPersonMap[edu.recordFlow]}</td>
							<td style="text-align: center;padding: 0px;">${edu.statusName}</td>
							<td style="text-align: center;padding: 0px;">
									<label <c:if test="${edu.statusId !=userChangeApplyStatusEnumSave.id}">style="display: none;"</c:if> >
										<a style="cursor: pointer;color: blue;" onclick="editApply('${edu.userFlow}','${edu.applyTypeId}','${edu.recordFlow}');">编辑</a>
										<a style="cursor: pointer;color: blue;"onclick="submit('${edu.recordFlow}');">提交</a>
									</label>
									<c:if test="${edu.statusId eq userChangeApplyStatusEnumSubmit.id}">
										<a style="cursor: pointer;color: blue;" onclick="look('${edu.applyTypeId}','${edu.recordFlow}');">查看</a>
										<a style="cursor: pointer;color: blue;"onclick="print('${edu.recordFlow}');">打印</a>
									</c:if>
									<c:if test="${edu.statusId eq userChangeApplyStatusEnumApprove.id ||edu.statusId eq userChangeApplyStatusEnumNotApprove.id}">
										<a style="cursor: pointer;color: blue;" onclick="lookAudit('${edu.applyTypeId}','${edu.recordFlow}');">查看</a>
										<a style="cursor: pointer;color: blue;"onclick="print('${edu.recordFlow}');">打印</a>
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
