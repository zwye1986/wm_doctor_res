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
		<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
			var orgFlow=$("#orgFlow").val();
			if(orgFlow=="")
			{
				jboxInfo("请选择基地之后，再进行导出！");
				return false;
			}
		</c:if>
		var url = "<s:url value='/sch/template/exportExcel/${roleFlag}'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}

    function exportDoctor2(){
        <c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
        var orgFlow=$("#orgFlow").val();
        if(orgFlow=="")
        {
            jboxInfo("请选择基地之后，再进行导出！");
            return false;
        }
        </c:if>
        var url = "<s:url value='/sch/template/exportExcel2/${roleFlag}'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

	function searchOrgSchDept(orgFlow)
	{
		var url = "<s:url value='/sch/doc/getSchDept?orgFlow='/>"+orgFlow;
		var courseArray = new Array();
		jboxGetAsync(url,null,function(data){
			if(data){
				var html="<option></option>";
				for (var i = 0; i < data.length; i++) {
					var schDeptFlow=data[i].schDeptFlow;
					var schDeptName=data[i].schDeptName;
					html+="<option value='"+schDeptFlow+"'>"+schDeptName+"</option>";
				}
				$("#schDeptFlow").html(html);
			}
		},null,false);
		search();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<table class="basic" style="width: 100%">
				<tr>
					<td style="text-align: left;padding-left: 10px;">
						<form id="searchForm" action="<s:url value='/sch/doc/schDept/${roleFlag}'/>" method="post">
<!-- 						年份: -->
<%-- 						<input onchange="search();" style="margin-right: 0px;width: 200px" name="schYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" type="text" value="${empty param.schYear?pdfn:getCurrYear():param.schYear}" class="validate[required]"/> --%>
						开始日期：
						<input onchange="search();" style="margin-right: 0px;width: 120px" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" class="validate[required]"/>
						&#12288;
						结束日期：
						<input onchange="search();" style="margin-right: 0px;width: 120px" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}" class="validate[required]"/>
						&#12288;
						<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
						培训基地：
							<select id="orgFlow" name="orgFlow" class="validate[required] select" style="width: 120px;"onchange="searchOrgSchDept(this.value);">
								<option value="">请选择</option>
								<c:forEach items="${orgList}" var="org">
									<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</c:if>
						&#12288;
						科&#12288;室：<select style="width: 120px;" id="schDeptFlow" name="schDeptFlow" onchange="search();">
								<option value="">请选择</option>
								<c:forEach items="${schDeptList}" var="schDept">
									<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>
								</c:forEach>
						</select>
						
						&#12288;
						<label>
							<input type="checkbox" id="viewBox" name="viewName" value="${GlobalConstant.FLAG_Y}" <c:if test="${GlobalConstant.FLAG_Y eq param.viewName}">checked</c:if> onchange="changeView();"/>
							查看医师姓名
						</label>
						&#12288;&#12288;
						<input type="button" class="search" value="导&#12288;出" onclick="exportDoctor();"/>
                            <%--<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">--%>
                            &#12288;&#12288;
                            <input type="button" class="search" value="导出二" onclick="exportDoctor2();"/>
                            <%--</c:if>--%>
						</form>
						
					</td>
				</tr>
			</table>

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
</div>
</body>
</html>