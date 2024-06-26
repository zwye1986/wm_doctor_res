<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	table.grid th,table.grid td{padding: 0;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function heightFiexed(){
	var height = document.body.clientHeight-110;
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
}
function heightTh(){
	var height = document.body.clientHeight-110;
	//修正高度
	var toFixedTd = $(".toFiexd");
	$(".fixed").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
}

onresize = function(){
	heightFiexed();
	heightTh();
};
//页面加载完成
$(function(){
	heightFiexed();
	heightTh();
	if("${param.viewBox}"==""){
		changeView("${GlobalConstant.FLAG_Y}");
	}else{
		changeView("${param.viewBox}");
	}
// 	year();
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		<c:forEach items="${datas}" var="data"> 
			if("${data}"=="${type.id}"){
				$("#"+"${data}").attr("checked","checked");
			}
		</c:forEach>
		<c:if test="${empty datas}">
			$("#"+"${type.id}").attr("checked","checked");
		</c:if>
	</c:forEach>
});
function searchInfo(sessionNumber,trainTypeId){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年份不能为空！");
		return;
	}
	var trainTypeId=$("#trainTypeId").val();
	var data="";
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return;
	}
	var orgLevel=$("#orgLevel").val();
	var viewBox=$('input[name="viewName"]:checked').val();
	jboxLoad("content","<s:url value='/jszy/statistic/statisticJointOrg'/>?orgLevel="+orgLevel+"&viewBox="+viewBox+""+data+"&trainTypeId="+trainTypeId+"&sessionNumber="+sessionNumber,true);
}
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
	$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
}

//切换展示形式
function changeView(obj){
	if(obj=="${GlobalConstant.FLAG_Y}"){
		$(".tdClass").css("padding-top","10px");
		$(".tdClass").attr("valign","top");
		$(".docCountView").toggle(false);
		$(".docNameView").toggle(true);
	}else{
		$(".tdClass").css("padding-top","");
		$(".tdClass").removeAttr("valign");
		$(".docCountView").toggle(true);
		$(".docNameView").toggle(false);
	}
	if(obj=="${GlobalConstant.FLAG_Y}"){
		$("input[name=viewName]:eq(1)").attr("checked","checked");
	}else{
		$("input[name=viewName]:eq(0)").attr("checked","checked");
	}
	heightFiexed();
	heightTh();
}
</script>
<div class="main_hd">
    <h2 class="underline">协同基地信息统计</h2> 
</div>
<div class="main_bd">
	<div class="div_search">
		<table class="searchTable">
			<tr>
				<td class="td_left">年&#12288;&#12288;份：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
				</td>
				<td class="td_left">培训类别：</td>
				<td>
					<select name="trainTypeId" id="trainTypeId" class="select" style="width:107px;">
						<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="4">
					<label style="cursor: pointer;"><input type="radio" name="viewName" value="${GlobalConstant.FLAG_N }" onclick="changeView('${GlobalConstant.FLAG_N}');"/>查看协同基地</label>&nbsp;
					<label style="cursor: pointer;"><input type="radio"  name="viewName" value="${GlobalConstant.FLAG_Y }"  onclick="changeView('${GlobalConstant.FLAG_Y }');"/>查看协同基地各专业报名人数</label>
				</td>
			</tr>
			<tr>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${jszyResDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="4">
					<input class="btn_brown" type="button" value="查&#12288;询" onclick="searchInfo();"/>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding-bottom: 20px;">
		<div class="search_table">
			<!--表格  -->
			<div id="tableContext" style="overflow: auto;height: auto;max-height: 450px;" onscroll="tableFixed(this);">
				<!--第一次 -->
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid"style="width: auto;" >
						<tr>
							<th style="min-width: 110px;max-width: 110px;"class="toFiexd">
								协同医院
							</th>
							<c:forEach items="${sysOrgList}" var="sysOrg" >
								<th  style="width: 170px;min-width:170px;max-width: 170px"class="xiaoji fixed" title="${sysOrg.orgName}">${pdfn:cutString(sysOrg.orgName,12,true,3)}</th>
							</c:forEach>
						</tr>
					</table>
				</div>
				<!--第二次 -->
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid" style="min-width: 100px;max-width: 100px;border-right: 0px">
						<tr>
							<th style="width: 110px;min-width: 110px;max-width: 110px;"class="toFiexd">
								协同医院
							</th>
						</tr>
						<tr>
							<td style="background: white;" class="toFiexdDept">协同医院1</td>
						</tr>
						<tr>
							<td style="background: white;" class="toFiexdDept">协同医院2</td>
						</tr>
						<tr>
							<td style="background: white;" class="toFiexdDept">协同医院3</td>
						</tr>
					</table>
				</div>
				<!--第三次  -->
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
					<table class="grid" style="width: auto;border-right: 0px" >
						<tr>
							<th style="min-width: 110px;max-width: 110px;" class="toFiexd">协同医院</th>
						</tr>
					</table>
				</div>
				<table class="grid" style="width: auto;">
					<tr>
						<th style="width: 110px;min-width: 110px;max-width: 110px;"  class="toFiexd">
							协同医院
						</th>
						<c:forEach items="${sysOrgList}" var="org">
							<th style="width: 170px;min-width:170px;max-width:170px;"class="fixed" title="${org.orgName }">${pdfn:cutString(org.orgName,12,true,3)}</th>
						</c:forEach>
					</tr>
					<tr>
						<td class="fixedBy">协同医院1</td>
						<c:forEach items="${sysOrgList}" var="sysOrg">
							<td class="tdClass" style="width: 130px; padding-top:10px"valign="top">
								<label style="text-align: left;">
									<c:set var="flow" value="${sysOrg.orgFlow}${GlobalConstant.FLAG_Y}"/>
									<c:set var="mark" value="${jointOrgListMap[flow].jointOrgFlow}"/>
									<c:if test="${empty jointOrgListMap[flow]}">无</c:if>
									<c:if test="${!empty jointOrgListMap[flow]}">
										<span style="float: left;padding-left: 20px;">${jointOrgListMap[flow].jointOrgName}</span><br>
										<div class="docNameView" style="padding-left: 25px">
											<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
												<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
												${org.speName}:&nbsp;${jointOrgDocCountMap[speMark]+0}
												<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>
											</c:forEach>
											<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
											<br>
										</div>
									</c:if>
								</label>
							</td>
						</c:forEach>
					</tr>
					<tr>
						<td  class="fixedBy">协同医院2</td>
						<c:forEach items="${sysOrgList}" var="sysOrg">
							<td class="tdClass" style="width: 130px; padding-top: 10px" valign="top">
								<label style="text-align: left;">
									<c:set var="flow" value="${sysOrg.orgFlow}${GlobalConstant.FLAG_F}"/>
									<c:set var="mark" value="${jointOrgListMap[flow].jointOrgFlow}"/>
									<c:if test="${empty jointOrgListMap[flow]}">无</c:if>
									<c:if test="${!empty jointOrgListMap[flow]}">
										<span style="float: left;padding-left: 20px">${jointOrgListMap[flow].jointOrgName}</span><br>
										<div class="docNameView"  style="padding-left: 25px">
											<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
												<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
												${org.speName}:&nbsp;${jointOrgDocCountMap[speMark]+0}
												<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>
											</c:forEach>
											<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
											<br>
										</div>
									</c:if>
								</label>
							</td>
						</c:forEach>
					</tr>
					<tr>
						<td class="fixedBy">协同医院3</td>
						<c:forEach items="${sysOrgList}" var="sysOrg">
							<td class="tdClass" style="width: 130px;padding-top: 10px" valign="top">
								<label style="text-align: left;" >
									<c:set var="flow" value="${sysOrg.orgFlow}${GlobalConstant.FLAG_N}"/>
									<c:set var="mark" value="${jointOrgListMap[flow].jointOrgFlow}"/>
									<c:if test="${empty jointOrgListMap[flow]}">无</c:if>
									<c:if test="${!empty jointOrgListMap[flow]}">
										<span style="float: left;padding-left: 20px">${jointOrgListMap[flow].jointOrgName}</span><br>
										<div class="docNameView" style="padding-left: 25px">
											<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
												<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
												${org.speName}:&nbsp;${jointOrgDocCountMap[speMark]+0}
												<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>
											</c:forEach>
											<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
											<br>
										</div>
									</c:if>
								</label>
							</td>
						</c:forEach>
					</tr>
				</table>
			</div>

		</div>
	</div>
</div>
