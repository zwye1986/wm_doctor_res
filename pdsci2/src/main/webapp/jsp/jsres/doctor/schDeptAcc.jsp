<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="queryFont" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
</style>
<script type="text/javascript">
	function toPage(page) {
		search(page);
	}


	function search(page){
		if (!page) {
			page = 1;
		}
		$("#currentPage").val(page);

		if(false == $("#searchForm").validationEngine("validate")){
			return ;
		}
		if($('#startDate').val() >= $('#endDate').val()){
			jboxTip("开始日期必须小于结束日期！");
			return;
		}
		var data = "";
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		if ($("#" + "${type.id}").attr("checked")) {
			data += "&datas=" + $("#" + "${type.id}").val();
		}
		</c:forEach>
		if ("${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}" == "true") {
			if (data == "") {
				jboxTip("请选择人员类型！");
				return false;
			}
		}
		jboxPost("<s:url value='/jsres/base/doc/schDeptAcc'/>?" + data ,$("#searchForm").serialize(),function(resp){
			$('#content').html(resp);
		},null,false);
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	$(function(){
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		<c:forEach items="${datas}" var="data">
		if ("${data}" == "${type.id}") {
			$("#" + "${data}").attr("checked", "checked");
		}
		</c:forEach>
		<c:if test="${empty datas}">
		$("#" + "${type.id}").attr("checked", "checked");
		</c:if>
		</c:forEach>
		$('#startDate,#endDate').datepicker();
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		changeView();
	});

	function heightFiexed(){
		var height = document.body.clientHeight-110;
		//$("#tableContext").css("height",height+"px");
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}
	
	onresize = function(){
		heightFiexed();
	};
	
	//切换展示形式
	function changeView(){
		var viewBox = $("#viewBox")[0];
		if(viewBox){
			$(".docCountView").toggle(!viewBox.checked);
			$(".docNameView").toggle(viewBox.checked);
		}
		heightFiexed();
	}
	function exportDoctor(){
		if(false == $("#searchForm").validationEngine("validate")){
			return ;
		}
		if($('#startDate').val() >= $('#endDate').val()){
			jboxTip("开始日期必须小于结束日期！");
			return;
		}
		var data = "";
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		if ($("#" + "${type.id}").attr("checked")) {
			data += "&datas=" + $("#" + "${type.id}").val();
		}
		</c:forEach>
		if ("${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}" == "true") {
			if (data == "") {
				jboxTip("请选择人员类型！");
				return false;
			}
		}
		var url = "<s:url value='/jsres/base/template/exportExcelAcc'/>?" + data;
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
</head>
<body>
	<div class="main_hd">
		<h2 class="underline">
			科室轮转查询
		</h2>
	</div>
	<div class="div_table">
		<div class="div_search" style="padding: 0px;margin-bottom: 10px;">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<table class="searchTable">
				<tr>
					<td class="td_left ">开始日期：</td>
					<td><input type="text" id="startDate" name="startDate" value="${empty param.startDate?startDate:param.startDate}"  class="validate[required] input" readonly="readonly" /></td>
					<td class="td_left ">结束日期：</td>
					<td><input type="text" id="endDate" name="endDate" value="${empty param.endDate?endDate:param.endDate}"  class="validate[required] input" readonly="readonly" /></td>
					<td class="td_left ">科&#12288;&#12288;室：</td>
					<td>
						<select  name="schDeptFlow"  class="select">
							<option></option>
							<c:forEach items="${allDeptList}" var="schDept">
								<option value="${schDept.deptFlow}" ${param.schDeptFlow eq schDept.deptFlow?'selected':''}>${schDept.deptName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left ">年&#12288;&#12288;级：</td>
					<td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"  readonly="readonly" /></td>
				</tr>
				<tr>
					<td class="td_left ">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}" value="${type.id}"
										  class="docType"/>${type.name}&nbsp;
							</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
					<td colspan="4">
						&#12288;<label>
							<input type="checkbox" id="viewBox" name="viewName" value="${GlobalConstant.FLAG_Y}" <c:if test="${GlobalConstant.FLAG_Y eq param.viewName}">checked</c:if> onchange="changeView();"/>
								查看医师姓名
						</label>
						<input type="button" class="btn_green" value="查&#12288;询" onclick="search(1);"/>
						<input type="button" class="btn_green" value="导&#12288;出" onclick="exportDoctor();"/>
					</td>
				</tr>
			</table>
			</form>
		</div>
		<div style="width:965px;">
			<div id="tableContext" style="width:100%;overflow: auto;" onscroll="tableFixed(this);">
				<%--<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">--%>
					<%--<table class="grid"style="width:0px;">--%>
						<%--<tr>--%>
							<%--<th style="width: 300px;min-width: 300px;max-width: 300px;">--%>
								<%--轮转科室--%>
							<%--</th>--%>
							<%--<c:forEach items="${titleDate}" var="title">--%>
								<%--<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">--%>
									<%--<c:set var="year" value="${title.substring(0,4)}"/>--%>
									<%--<c:set var="week" value="${title.substring(5)}"/>--%>
									<%--<c:set var="title" value="${year}年<br/>${week}周"/>--%>
								<%--</c:if>--%>
								<%--<th style="width: 80px;min-width: 80px;">${title}</th>--%>
							<%--</c:forEach>--%>
						<%--</tr>--%>
					<%--</table>--%>
				<%--</div>--%>
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid" style="min-width: 300px;max-width: 300px;width:0px;">
						<tr>
							<th style="width: 300px;width: 300px;min-width: 300px;">
								轮转科室
							</th>
						</tr>
						<c:forEach items="${deptList}" var="schDept">
								<tr>
									<td style=" min-width:300px;background: white;" class="toFiexdDept">${schDept.deptName}</td>
								</tr>
						</c:forEach>
					</table>
				</div>
				<div id="topTitle" style="width: 0px;line-height:normal;overflow: visible;position: relative;height: 0px;">
					<table class="grid" style="width:0px;">
						<tr>
							<th style="min-width: 300px;max-width: 300px;">轮转科室</th>
						</tr>
					</table>
				</div>
				<table class="grid" style="width:0px;">
					<tr>
						<th style="width: 300px;min-width: 300px;">
							轮转科室
						</th>
						<c:forEach items="${titleDate}" var="title">
							<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
								<c:set var="year" value="${title.substring(0,4)}"/>
								<c:set var="week" value="${title.substring(5)}"/>
								<c:set var="title" value="${year}年<br/>${week}周"/>
							</c:if>
							<th style="width: 80px;min-width: 80px;">${title}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${deptList}" var="schDept">
						<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.deptFlow}">
							<tr>
								<td style="width: 10%" class="fixedBy">${schDept.deptName}</td>
								<c:forEach items="${titleDate}" var="title">
									<c:set var="key" value="${schDept.deptFlow}${title}"/>
									<td class="nameTitle" style="line-height: 18px;" <c:if test="${!empty resultListMap[key]}">
										title="<table><c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
										<tr><td>${result.doctorName}</td></tr></c:forEach></table>"</c:if>
									>
										<div class="docCountView">
												${resultListMap[key].size()+0}
										</div>
										<c:if test="${!empty resultListMap[key]}">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<div class="docNameView" style="display: none;">${result.doctorName}</div>
											</c:forEach>
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(deptList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</body>
</html>