<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="font" value="true" />
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
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${studentTypes}" var="data">
			$("#"+"${data}").attr("checked","checked");
			</c:forEach>
			heightFiexed();
		});
		function tableFixed(div){
			$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
			$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
		}
		function heightFiexed(){

			//修正高度
			var maxheight=-1;
			var headTrTh=$(".headTrTh");
			$(headTrTh).each(function(i){

				$(headTrTh[i]).find(".fixedBy").each(function(){
					if($(this).height()>maxheight) maxheight=$(this).height();
				});
			});
			if(maxheight!=-1) {
				$(".toFiexdDept").each(function(){
					$(this).height(maxheight);
				});
			}
			var fixTrTd = $(".fixTrTd");
			var fixTrTh = $(".fixTrTh");
			$(fixTrTd).each(function(i)
			{
				var maxheight=-1;
				$(fixTrTd[i]).find(".by").each(function(){
					if($(this).height()>maxheight) maxheight=$(this).height();
				});
				if(maxheight!=-1) {
					$(fixTrTh[i]).find(".fix").each(function(){
						$(this).height(maxheight);
					});
				}
			});
		}

		onresize = function(){
			heightFiexed();
		};
		function search(page) {
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();
			if(startDate==""||startDate==undefined)
			{
				jboxTip("开始时间不得为空！！");
				return false;
			}
			if(endDate==""||endDate==undefined)
			{
				jboxTip("结束时间不得为空！！");
				return false;
			}
			if(startDate!=""&&endDate!=""&&endDate<startDate)
			{
				jboxTip("开始时间不得大于结束时间！！");
				return false;
			}
			jboxStartLoading();
			if (!page) {
				page = 1;
			}
			$("#currentPage").val(page);
			jboxPostLoad("content","<s:url value='/res/manager/doctorRecruit/doctorChuke'/>?role=responsibleTeacher",$("#searchForm").serialize(),true);
		}
		function toPage(page) {
			search(page);
		}
		function resultsDetails(processFlow,schDeptName){
			var url = "<s:url value='/res/manager/doctorRecruit/resultsDetails'/>?processFlow="+processFlow;
			var height = (window.screen.height * 0.7);
			jboxOpen(url,schDeptName+" 轮转详细信息",1000,height,true)
		}
		function exportInfo()
		{
			var url = "<s:url value='/res/manager/exportCkScore'/>";
			jboxExp($("#searchForm"),url);
		}
	</script>
</head>
<div class="main_hd">
	<h2 class="underline">出科成绩查询</h2>
</div>
<body>
<div style="padding: 10px 40px;">
	<form id="searchForm" action="<s:url value='/res/manager/doctorRecruit/doctorChuke'/>" method="post">
		<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
		<input type="hidden" name="role" value="${role}">
		<table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId" class="select" style="margin: 0 5px;width: 124px">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
							<option value="${spe.dictId}"
									<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
							>${spe.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<select name="sessionNumber" class="select" style="margin: 0 5px;width: 124px">
						<option value="all" ${sessionNumber eq 'all'?'selected':''}>全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
							<option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>&nbsp;
				</td>
				<td class="td_left">时间区间：</td>
				<td colspan="3">
					<input class="input" name="startDate" id="startDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"
						   value="${empty param.startDate?startDate:param.startDate}" class="input" style="width: 118px;" />~<input class="input" name="endDate" id="endDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
																																	value="${empty param.endDate?endDate:param.endDate}" class="input" style="width: 118px;" />
				</td>
			</tr>
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" style="width: 118px;"  class="input" value="${param.userName}"/>
				</td>
				<td class="td_left">学员类型：</td>
				<td colspan="6">
					<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
						<input style="margin: 0 5px"  id="${dict.dictId}" type="checkbox" name="studentTypes" value="${dict.dictId}" <c:if test="${empty param.studentTypes}">checked</c:if>/>${dict.dictName}
					</c:forEach>
					<input class="btn_green" type="button" value="查&#12288;询" onclick="search(1);" style="margin-left: 10px"/>&#12288;
				</td>
			</tr>
		</table>
	</form>

	<!--表格  -->
	<div id="tableContext" class="basic" style="width:100%;border: 0px solid #e3e3e3;overflow: auto;" onscroll="tableFixed(this);">
		<!--第一次 -->
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid"style="width: auto;" >
				<tr class="headTrTh">
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="toFiexdDept">姓名</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;" class="toFiexdDept">专业</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="toFiexdDept">年级</th>
					<c:forEach items="${titleDate}" var="title">
						<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
							<c:set var="year" value="${title.substring(0,4)}"/>
							<c:set var="week" value="${title.substring(5)}"/>
							<c:set var="title" value="${year}年<br/>${week}周"/>
						</c:if>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width:auto;min-width: 100px;" class="fixedBy">${title}</th>
					</c:forEach>
				</tr>
			</table>
		</div>
		<!--第二次 -->
		<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid" style="width: auto;">
				<tr class="headTrTh">
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="toFiexdDept">姓名</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;" class="toFiexdDept">专业</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="toFiexdDept">年级</th>
				</tr>
				<c:forEach items="${docCycleList}" var="docCycle">
					<tr class="fixTrTh">
						<td style="height: 40px;background: white; text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="fix">${docCycle.userName}</td>
						<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;" class="fix">${docCycle.trainingSpeName}</td>
						<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;" class="fix">${docCycle.sessionNumber}</td>

					</tr>
				</c:forEach>
				<c:if test="${empty docCycleList}">
					<tr class="fixTrTh">
						<td class="fix" colspan="10" style="background: white;border: 1px solid #e3e3e3;border-right: 0px">无记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
		<!--第三次  -->
		<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
			<table class="grid" style="width: auto;" >
				<tr class="headTrTh">
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;"class="toFiexdDept">姓名</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;"class="toFiexdDept">专业</th>
					<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;"class="toFiexdDept">年级</th>
				</tr>
			</table>
		</div>
		<table border="0"  style="width: auto;" cellpadding="0" cellspacing="0" class="grid">
			<tr class="headTrTh">
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;"class="toFiexdDept">姓名</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;"class="toFiexdDept">专业</th>
				<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;"class="toFiexdDept">年级</th>
				<c:forEach items="${titleDate}" var="title">
					<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
						<c:set var="year" value="${title.substring(0,4)}"/>
						<c:set var="week" value="${title.substring(5)}"/>
						<c:set var="title" value="${year}年<br/>${week}周"/>
					</c:if>
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width:auto;min-width: 100px;" class="fixedBy">${title}</th>
				</c:forEach>
			</tr>
			<c:forEach items="${docCycleList}" var="docCycle">
				<tr class="fixTrTd">
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 80px;"  >${docCycle.userName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 80px;min-width: 100px;" >${docCycle.trainingSpeName}</td>
					<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;" >${docCycle.sessionNumber}</td>
					<c:forEach items="${titleDate}" var="title">
						<c:set var="key" value="${docCycle.doctorFlow}${title}"/>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;" class="by">
							<c:if test="${!empty resultListMap[key]}">
								<table style="width: 100%;border: 0px solid #e3e3e3;text-align: center;">
									<tr style="border: 0px solid #e3e3e3;text-align: center;">
										<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
										<c:set var="color" value="${recMap[result.resultFlow]}"></c:set>
										<c:set var="lastCount" value="${(status.count % 2) }"></c:set>
										<td onclick="resultsDetails('${processFlowMap[result.resultFlow]}','${result.schDeptName}','${result.schStartDate}','${result.schEndDate}');"
											style="border: 0px solid #e3e3e3;text-align: center;padding-left: 0px;padding-right: 0px;font-color:black;
											<c:if test="${ lastCount eq '0'}">
													border-left: 1px solid #e3e3e3;
													</c:if>">
											<a style="color: blue;cursor: pointer;">${result.schDeptName}</a>
										</td>
										<c:if test="${ lastCount eq '0'}">
									</tr><tr style="border: 0px solid #e3e3e3;text-align: center;">
									</c:if>
									</c:forEach>
								</tr>
								</table>
							</c:if>
							<c:if test="${empty resultListMap[key]}">
								--
							</c:if>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			<c:if test="${empty docCycleList}">
				<tr>
					<td colspan="20">无记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
	<br>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(docCycleList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
</body>
</html>

