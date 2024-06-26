<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
</style>

<script type="text/javascript">
	function search(){
		if(false==$("#sysDeptForm").validationEngine("validate")){
			return ;
		}
		if($("#orgFlow option:selected").val()==""){
			jboxTip("请选择一个培训基地");
			return;
		}
		$("#searchForm").submit();
	}
	function exportDeptInfo(){
		jboxExpLoading($("#searchForm"),"<s:url value='/sch/arrangeResult/exportDeptStaticts'/>");
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	$(function(){
		changeView();
		heightFiexed();
	});
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
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
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/sch/arrangeResult/deptStaticts'/>" method="post">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>
						<select id="schDeptFlow" name="schDeptFlow" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${schDeptList}" var="schDept">
								<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">开始时间：</label>
						<input name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" class="qtext validate[required]"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">结束时间：</label>
						<input name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}" class="qtext validate[required]"/>
					</div>
					<div class="inputDiv" style="min-width: 128px;max-width: 128px;">
						<input type="checkbox" id="viewBox" name="viewName" value="${GlobalConstant.FLAG_Y}" <c:if test="${GlobalConstant.FLAG_Y eq param.viewName}">checked</c:if> onclick="changeView();"/>
						查看医师姓名
					</div>
					<div class="lastDiv" style="min-width: 182px;max-width: 182px;">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>&#12288;
						<input type="button" value="导&#12288;出" class="searchInput" onclick="exportDeptInfo();"/>
					</div>
				</div>
			</form>
						
			<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic">
						<tr>
							<th style="width: 200px;min-width: 200px;max-width: 200px;">
								轮转科室
							</th>
							<c:forEach items="${titleDate}" var="title">
								<th style="width: 180px;min-width: 180px;">${title}</th>
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
							<th style="width: 180px;min-width: 180px;">${title}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${schDeptList}" var="schDept">
						<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.schDeptFlow}">
						<tr>
							<td class="fixedBy">${schDept.schDeptName}</td>
							<c:forEach items="${titleDate}" var="title">
								<c:set var="key" value="${schDept.schDeptFlow}${title}"/>
								<td class="nameTitle" style="line-height: 18px;">
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
				</table>
			</div>
	</div>
</div>
</div>
</body>
</html>