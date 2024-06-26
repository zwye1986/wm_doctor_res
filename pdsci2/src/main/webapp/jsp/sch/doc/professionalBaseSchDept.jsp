<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
</style>

<script type="text/javascript">
	function search(){
		if(false==$("#sysDeptForm").validationEngine("validate")){
			return ;
		}
		$("#searchForm").submit();
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	$(function(){
		changeView();
	});
	
	function heightFiexed(){
		var height = document.body.clientHeight-110;
		$("#tableContext").css("height",height+"px");	
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
		var url = "<s:url value='/sch/template/exportExcel/professionalBase'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}


</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div>
			<jsp:include page="/res/doc/newNoticeList">
				<jsp:param name="fromSch" value="Y"></jsp:param>
			</jsp:include>
		</div>
		<div class="clearfix">
			<div class="queryDiv">
				<form id="searchForm" action="<s:url value='/res/ProfessionalBase/schDept'/>" method="post">
				<div class="inputDiv">
					开始日期：
					<input name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" class="validate[required] qtext"/>
				</div>
				<div class="inputDiv">
					结束日期：
					<input name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}" class="validate[required] qtext"/>
				</div>
				<div class="inputDiv" style="max-width: 217px;min-width: 217px;">
					科&#12288;&#12288;室：
					<select style="width: 120px;" id="schDeptFlow" name="schDeptFlow" class="qselect">
						<option value="">请选择</option>
							<c:forEach items="${schDeptList}" var="schDept">
								<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>
							</c:forEach>
					</select>
				</div>
				<div class="inputDiv" style="text-align: left">
					&nbsp;
						<label>
						<input type="checkbox" id="viewBox" name="viewName" value="${GlobalConstant.FLAG_Y}" <c:if test="${GlobalConstant.FLAG_Y eq param.viewName}">checked</c:if> onchange="changeView();"/>
						查看姓名
					</label>
					&nbsp;
					<input type="button" class="search" value="查&#12288;询" onclick="search();"/>
					<input type="button" class="search" value="导&#12288;出" onclick="exportDoctor();" style="margin-left: 0"/>
				</div>
				</form>
			</div>

			<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic">
						<tr>
							<th style="width: 200px;min-width: 200px;max-width: 200px;">
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
					</table>
				</div>
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic" style="min-width: 200px;max-width: 200px;">
						<tr>
							<th style="width: 200px;width: 200px;min-width: 200px;">
								轮转科室
							</th>
						</tr>
						<c:forEach items="${schDeptList}" var="schDept">
							<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.schDeptFlow}">
							<tr>
								<td style="background: white;" class="toFiexdDept">${schDept.schDeptName}</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
				<table class="basic">
					<tr>
						<th style="min-width: 200px;max-width: 200px;">轮转科室</th>
					</tr>
				</table>
				</div>
				<table class="basic">
					<tr>
						<th style="width: 200px;min-width: 200px;">
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
					<c:forEach items="${schDeptList}" var="schDept">
						<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.schDeptFlow}">
						<tr>
							<td class="fixedBy">${schDept.schDeptName}</td>
							<c:forEach items="${titleDate}" var="title">
								<c:set var="key" value="${schDept.schDeptFlow}${title}"/>
								<td class="nameTitle" style="line-height: 18px;"
									<c:if test="${!empty resultListMap[key]}">
									title="
										<table>
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<tr>
													<td>${result.doctorName}</td>
<%-- 													(${result.schStartDate}~${result.schEndDate}) --%>
												</tr>
											</c:forEach>
										</table>
									"
									</c:if>
								>
									<div class="docCountView">
										${resultListMap[key].size()+0}
									</div>
									<c:if test="${!empty resultListMap[key]}">
										<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
											<div class="docNameView" style="display: none;width: 80px;min-width: 80px;word-wrap:break-word;text-align: left;">●${result.doctorName}</div>
										</c:forEach>
									</c:if>
								</td>
							</c:forEach>
						</tr>
						</c:if>
					</c:forEach>
					<c:if test="${empty schDeptList}">
						<tr>
							<td colspan="25" style="text-align: center">暂无医师信息！</td>
						</tr>
					</c:if>
				</table>
			</div>
	</div>
</div>
</div>
</body>
</html>