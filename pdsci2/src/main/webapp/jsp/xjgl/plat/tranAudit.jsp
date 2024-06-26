<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<script type="text/javascript">
	function search(){
		var currentPage=$("#currentPage").val();
		if(currentPage==undefined || currentPage ==""){
			$("#currentPage").val(1)
		}
		$("#searchForm").submit();
	}
	function look(recordFlow,applyTypeId,userFlow){
		var url =  "<s:url value='/xjgl/change/apply/editApply'/>?userFlow="+userFlow+"&audit=Y&lookFlag=Y&auditFlag=Y&viewFlag=Y&applyTypeId="+applyTypeId+"&recordFlow="+recordFlow; 
		jboxOpen(url ,"查看申请",1100,550);
	}
	function audit(recordFlow,applyTypeId,userFlow){
		var url =  "<s:url value='/xjgl/change/apply/editApply'/>?userFlow="+userFlow+"&viewFlag=Y&auditFlag=Y&applyTypeId="+applyTypeId+"&recordFlow="+recordFlow; 
		jboxOpen(url ,"审核",1100,550);
	}
	function toPage(page){
		if(page!=undefined){
			$("#currentPage").val(page);			
		}else{
			$("#currentPage").val(1);
		}
		jboxStartLoading();
		search();
	}
	function print(recordFlow){
		jboxTip("打印中,请稍等...");
		var url = '<s:url value="/xjgl/change/apply/print"/>?recordFlow='+recordFlow;
		window.location.href = url;	
	}
	function exportExcel(){
		var applyTypeId=$("#applyTypeId").val();
		var trainOrgId=$("#trainOrgId").val();
		if(applyTypeId== undefined){
			applyTypeId="";
		}
		if(trainOrgId== undefined){
			trainOrgId="";
		}
		var url = "<s:url value='/xjgl/change/apply/exportExcel'/>?trainOrgId="+trainOrgId+"&applyTypeId="+applyTypeId;
		jboxTip("导出中…………");
		window.location.href = url;	
		jboxEndLoading();
	}
	function uploadForm(){
		var url =  "<s:url value='/xjgl/change/apply/uploadForm'/>";
		jboxOpen(url ,"上传表格",900,550);
	}
	function uploadInstruction(){
		var url = "<s:url value='/xjgl/change/apply/uploadInstruction'/>";
		jboxOpen(url ,"上传说明编辑",660,360);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/xjgl/change/apply/findChangeInfo"/>" method="post">
		<table style="width: 100%;margin: 10px 0px;border: none;">
						<tr>
						<td style="border: none;line-height: 260%;">
					&nbsp;申请类型：<select  id="applyTypeId" name="applyTypeId" style="width: 141px;">
								<option value="">请选择</option>
								<c:forEach items="${userChangeApplyTypeEnumList}" var="apply">
									<option value="${apply.id}"<c:if test="${apply.id eq param.applyTypeId}">selected="selected"</c:if>>${apply.name}</option>
								</c:forEach>
						</select>
						&nbsp;
					学位分委员会：<select  id="trainOrgId" name="trainOrgId"style="width: 141px;">
								<option value="">请选择</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptFlow}"<c:if test="${dept.deptFlow eq param.trainOrgId}">selected="selected"</c:if>>${dept.deptName}</option>
								</c:forEach>
						</select>
						&nbsp;
						<input type="button" value="查&#12288;询" class="search" onclick="search();">
						<br/>
						<input type="button" value="导&#12288;出" class="search" onclick="exportExcel();">
						&nbsp;
						<input type="button" value="上传表格" class="search" onclick="uploadForm()">
						&nbsp;
						<input type="button" value="上传说明编辑" class="search" onclick="uploadInstruction()">
					</td>
					</tr>
		</table>
	
		<table class="basic" style="width:100%;">
			<colgroup>
				<col width="5%"/>
				<col width="13%"/>
				<col width="5%"/>
				<col width="7%"/>
				<col width="5%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="7%"/>
				<col width="7%"/>
				<col width="8%"/>
				<col width="5%"/>
				<col width="8%"/>
			</colgroup>
			<tr style="font-weight: bold;">
				<td style="text-align: center;padding-left: 0px;">入学年级</td>
				<td style="text-align: center;padding-left: 0px;">班级</td>
				<td style="text-align: center;padding-left: 0px;">学号</td>
				<td style="text-align: center;padding-left: 0px;">姓名</td>
				<td style="text-align: center;padding-left: 0px;">性别</td>
				<td style="text-align: center;padding-left: 0px;">学位分委员会</td>
				<td style="text-align: center;padding-left: 0px;">专业名称</td>
				<td style="text-align: center;padding-left: 0px;">申请类型</td>
				<td style="text-align: center;padding-left: 0px;">申请日期</td>
				<td style="text-align: center;padding-left: 0px;">批准时间</td>
				<td style="text-align: center;padding-left: 0px;">状态</td>
				<td style="text-align: center;padding-left: 0px;">操作</td>
			</tr>
			<c:forEach items="${changeExtList}" var="change">
			<tr>
				<td style="text-align: center;padding-left: 0px;">${change.eduUser.period}</td>
				<td style="text-align: center;padding-left: 0px;">${change.eduUser.className}</td>
				<td style="text-align: center;padding-left: 0px;">${change.eduUser.sid}</td>
				<td style="text-align: center;padding-left: 0px;">${change.sysUser.userName}</td>
				<td style="text-align: center;padding-left: 0px;">${change.sysUser.sexName}</td>
				<td style="text-align: center;padding-left: 0px;">${change.eduUser.trainOrgName}</td>
				<td style="text-align: center;padding-left: 0px;">[${change.eduUser.majorId}]${change.eduUser.majorName}</td>
				<td style="text-align: center;padding-left: 0px;">${change.applyTypeName}</td>
				<td style="text-align: center;padding-left: 0px;">${change.applyTime}</td>
				<td style="text-align: center;padding-left: 0px;">${extMap[change.recordFlow]}</td>
				<td style="text-align: center;padding-left: 0px;">${change.statusName}</td>
				<td style="text-align: center;padding-left: 0px;">
					<c:if test="${change.statusId eq userChangeApplyStatusEnumApprove.id or change.statusId eq userChangeApplyStatusEnumNotApprove.id }">
						<a onclick="look('${change.recordFlow}','${change.applyTypeId}','${change.userFlow }');" style="cursor: pointer; color: blue;">查看</a>&nbsp;
						<a onclick="print('${change.recordFlow}');" style="cursor: pointer; color: blue;">打印</a>&nbsp;
					</c:if>
					<c:if test="${change.statusId eq userChangeApplyStatusEnumSubmit.id }">
						<a onclick="audit('${change.recordFlow}','${change.applyTypeId}','${change.userFlow }');" style="cursor: pointer; color: blue;">审核</a>&#12288;&#12288;&#12288;
					</c:if>
				</td>
				</tr>
			</c:forEach>
			<c:if test="${empty changeExtList}">
				<td colspan="12">无记录！</td>
			</c:if>
		</table>
		<div>
			<input name="currentPage" id="currentPage" type="hidden" value="">
	       	<c:set var="pageView" value="${pdfn:getPageView(changeExtList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	    </div>
</form>
	</div>
</div>
</body>
</html>