<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
	table.grid th,table.grid td{padding: 0;}
	.bo{width: 250px;}
</style>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
$(document).ready(function(){
	heightFiexed();
// 	$('#startTime').datepicker({
// 		startView: 1, 
// 		maxViewMode: 1,
// 		minViewMode:1,
// 		format:'yyyy-mm'
// 	});
// 	$('#endTime').datepicker({
// 		startView: 1, 
// 		maxViewMode: 1,
// 		minViewMode:1,
// 		format:'yyyy-mm'
// 	});
});
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
}
function heightFiexed(){
	var height = document.body.clientHeight-110;
// 	$("#tableContext").css("height",height+"px");	
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
}
function searchInfo(){
// 	var year1=parseInt($("#startTime").val().substring(0,4));
// 	var month1=parseInt($("#startTime").val().substring(5,7));
// 	var year2=parseInt($("#endTime").val().substring(0,4));
// 	var month2=parseInt($("#endTime").val().substring(5,7));
// 	if(year1 == null || year1 == undefined || year1 == '' || isNaN(year1)){
// 		jboxTip("开始时间不能为空");return;
// 	}
// 	if(year2 == null || year2 == undefined || year2 == '' || isNaN(year2)){
// 		jboxTip("结束时间不能为空");return;
// 	}
// 	if(year2>year1||(year1==year2&&month2>=month1)){
// 		var startTime=$("#startTime").val().substring(0,4)+$("#startTime").val().substring(5,7);
// 		var endTime=$("#endTime").val().substring(0,4)+$("#endTime").val().substring(5,7);
		var catSpeId = $("#catSpeId").val();
		var sessionNumber = $("#sessionNumber").val();
		jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>?catSpeId="+catSpeId+"&sessionNumber="+sessionNumber,true);
// 	}else{
// 		jboxTip("开始时间不能大于结束时间！");return;
// 	}
}
function showView(orgFlow,sessionNumber){
// 	var year1=parseInt($("#startTime").val().substring(0,4));
// 	var month1=parseInt($("#startTime").val().substring(5,7));
// 	var year2=parseInt($("#endTime").val().substring(0,4));
// 	var month2=parseInt($("#endTime").val().substring(5,7));
// 	if(year1 == null || year1 == undefined || year1 == '' || isNaN(year1)){
// 		jboxTip("开始时间不能为空");return;
// 	}
// 	if(year2 == null || year2 == undefined || year2 == '' || isNaN(year2)){
// 		jboxTip("结束时间不能为空");return;
// 	}
// 	if(year2>year1||(year1==year2&&month2>=month1)){
// 		var startTime=$("#startTime").val().substring(0,4)+$("#startTime").val().substring(5,7);
// 		var endTime=$("#endTime").val().substring(0,4)+$("#endTime").val().substring(5,7);
		var catSpeId = $("#catSpeId").val();
		jboxOpen("<s:url value='/jsres/statistic/statisticsAppUser'/>?open=Y&catSpeId="+catSpeId+"&orgFlow="+orgFlow+"&sessionNumber="+sessionNumber,"APP使用情况折线图",900,500);
// 	}else{
// 		jboxTip("开始时间不能大于结束时间！");return;
// 	}
}
</script>
<body>
<div class="main_hd">
    <h2 class="underline">APP使用情况统计</h2> 
</div>
<div class="search_table"style="margin-top: 20px">
	<form id="searchForm">
		<div style="margin-left: 30px;" >
<%-- 			开始时间：<input type="text" id="startTime" name="startTime"  <c:if test="${empty param.startTime }">value="2015-09"</c:if>value="${pdfn:transDateTimeForPattern(param.startTime,'yyyyMM','yyyy-MM')}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288; --%>
<%-- 			结束时间：<input type="text" id="endTime" name="endTime" <c:if test="${empty param.startTime }">value="${pdfn:getCurrDateTime('yyyy-MM')}"</c:if>value="${pdfn:transDateTimeForPattern(param.endTime,'yyyyMM','yyyy-MM')}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288; --%>
			学员类型：
			<select name="catSpeId" id="catSpeId" class="select" onchange="searchInfo();" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;
			学员届别：<input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;

	<!-- 			<input class="btn_green" type="button" value="查询" onclick="searchInfo();"/> -->
		</div>
	</form>
	<div id="tableContext" style="margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 30px;height: 700px" onscroll="tableFixed(this);">
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid"style="width: 700px;" >
				<tr>
					<th style="min-width: 410px;max-width:410px;"class="toFiexd"colspan="2">
						培训基地名称
					</th>
				    <th  style="min-width: 410px;max-width:410px;"class="fixed" colspan="2">
				    	协同医院
				    </th>
			    </tr>
			</table>
		</div>
		<table border="0" cellpadding="0" cellspacing="0" class="grid" style="border-bottom: 0px;width: 700px" >
			<tr>
				<th colspan="2"style="min-width: 410px;max-width:410px;">
					培训基地名称
				</th>
				<th colspan="2" style="border-bottom: 0px;min-width: 410px;max-width:410px;">
					协同医院
				</th>
			</tr>
			<c:forEach items="${sysOrgList}" var="org" varStatus="b">
				<tr>
					<td style="width: 30%"><label style="cursor: pointer;" onclick="showView('${org.orgFlow}','${sessionNumber}');"><font color="blue">${org.orgName}</font></label></td>
					<td style="border-left: 1px solid #e3e3e3;width: 10%">${percentMap[org.orgFlow]}</td>
					<td colspan="2" style="border-bottom:0px;">
						<table border="0" cellpadding="0" cellspacing="0"  class="grid" <c:if test="${b.count == fn:length(sysOrgList)}">style="border-right: 0px;"</c:if>
							<c:if test="${b.count != fn:length(sysOrgList)}">style="border-right: 0px;border-bottom:0px;"</c:if>
						>
							<c:forEach items="${jointOrgListMap[org.orgFlow]}" var="jointOrg" varStatus="a">
								<tr style="border-right: 0px;">
									<td class="bo" <c:if test="${a.count == fn:length(jointOrgListMap[org.orgFlow]) }">style="border-bottom: 0px"</c:if>>
										<label style="cursor: pointer;" onclick="showView('${jointOrg.jointOrgFlow}','${sessionNumber}');"><font color="blue">${jointOrg.jointOrgName}</font></label>
									</td>
									<c:set var="flow" value="${jointOrg.jointOrgFlow}"/>
									<td  <c:if test="${a.count == fn:length(jointOrgListMap[org.orgFlow]) }">style="border-bottom: 0px;border-left: 1px solid #e3e3e3;"</c:if>
										<c:if test="${a.count != fn:length(jointOrgListMap[org.orgFlow]) }">style="border-left: 1px solid #e3e3e3;"</c:if>									
									>${jointPercentMap[flow]}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty jointOrgListMap[org.orgFlow]}">
								<tr style="border-right: 0px;">
									<td class="bo" style="border-bottom: 0px">--</td>
									<td style="border-left: 1px solid #e3e3e3;border-bottom: 0px">--</td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>