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
<script>
$(document).ready(function(){
	heightFiexed();
});
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
}
function heightFiexed(){
	var height = document.body.clientHeight-110;
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
}
function searchInfo(){
	var catSpeId = $("#catSpeId").val();
	jboxLoad("content","<s:url value='/jsres/institute/statisticsAppUserForOrg'/>?catSpeId="+catSpeId+"",true);
}
function showView(orgFlow){
	var catSpeId = $("#catSpeId").val();
	jboxOpen("<s:url value='/jsres/institute/statisticsAppUser'/>?open=Y&catSpeId="+catSpeId+"&orgFlow="+orgFlow,"APP使用情况折线图",900,500);
}
</script>
<body>
<div class="main_hd">
    <h2 class="underline">APP使用情况统计</h2> 
</div>
<div class="search_table"style="margin-top: 20px">
	<form id="searchForm">
		<div style="margin-left: 30px;" >
			学员类型：
			<select name="catSpeId" id="catSpeId" class="select" onchange="searchInfo();" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;
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
					<td style="width: 30%"><label style="cursor: pointer;" onclick="showView('${org.orgFlow}');"><font color="blue">${org.orgName}</font></label></td>
					<td style="border-left: 1px solid #e3e3e3;width: 10%">${percentMap[org.orgFlow]}</td>
					<td colspan="2" style="border-bottom:0px;">
						<table border="0" cellpadding="0" cellspacing="0"  class="grid" <c:if test="${b.count == fn:length(sysOrgList)}">style="border-right: 0px;"</c:if>
							<c:if test="${b.count != fn:length(sysOrgList)}">style="border-right: 0px;border-bottom:0px;"</c:if>
						>
							<c:forEach items="${jointOrgListMap[org.orgFlow]}" var="jointOrg" varStatus="a">
								<tr style="border-right: 0px;">
									<td class="bo" <c:if test="${a.count == fn:length(jointOrgListMap[org.orgFlow]) }">style="border-bottom: 0px"</c:if>>
										<label style="cursor: pointer;" onclick="showView('${jointOrg.jointOrgFlow}');"><font color="blue">${jointOrg.jointOrgName}</font></label>
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