<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	table.grid th,table.grid td{text-align: center;padding: 0;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	var sum=0;var totalCount=0;
	<c:set var= "mrk" value="dictTypeEnum${param.trainTypeId}List"/>
	<c:forEach items="${applicationScope[mrk]}" var="dict">
			<c:forEach items="${sysOrgList}" var="org">
			     var val=$("#"+"${org.orgFlow}"+"${dict.dictId}").text();
				if (val == null || val == undefined || val == '' || isNaN(val)){
					val=0;
				}
				 sum +=  parseFloat(val); 
			 </c:forEach>
			$("#"+"${dict.dictId}").text(sum);
			totalCount+=sum;
			sum=0;
		</c:forEach>
		$("#all").text(totalCount);
	var sum=0;var totalCount=0;
	<c:set var= "mrk" value="dictTypeEnum${param.trainTypeId}List"/>
		<c:forEach items="${applicationScope[mrk]}" var="dict">
			<c:forEach items="${sysOrgList}" var="org">
				var val=$("#"+"${org.orgFlow}"+"${dict.dictId}").text();
				if (!(val == null || val == undefined || val == '' || isNaN(val))){
					sum +=1;
				}
			</c:forEach>
			$("#"+"${dict.dictId}2").text(sum);
			totalCount+=sum;
			sum=0;
		</c:forEach>
	$("#all2").text(totalCount);
});
function heightFiexed(){
	// var height = document.body.clientHeight-110;
	var height = 500;
	$("#tableContext").css("height",height+"px");
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
//  	var width=$(".fix");
//  	$(".by").each(function(i){
// 		$(width[i]).height($(this).height());
// 	});
}

onresize = function(){
	heightFiexed();
};
//页面加载完成
$(function(){
	heightFiexed();
// 	var sessionNumber=$("#sessionNumber").val();
// 	if(sessionNumber==2015){
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumWMFirst.id}']").hide();
// 	}
	var sum=0;
	<c:forEach items="${sysOrgList}" var="org">
		<c:set var= "mak" value="dictTypeEnum${param.trainTypeId}List"/>
		<c:forEach items="${applicationScope[mak]}" var="dict">
		     var val=$("#"+"${org.orgFlow}"+"${dict.dictId}").text();
			if (val == null || val == undefined || val == '' || isNaN(val)){
				val=0;
			}
			 sum +=  parseFloat(val); 
		</c:forEach>
		$("#"+"${org.orgFlow}").text(sum);
		sum=0;
	</c:forEach>
	var sum=0;
	<c:forEach items="${sysOrgList}" var="org">
	<c:set var= "mak" value="dictTypeEnum${param.trainTypeId}List"/>
	<c:forEach items="${applicationScope[mak]}" var="dict">
	var val=$("#"+"${org.orgFlow}"+"${dict.dictId}").text();
	if (!(val == null || val == undefined || val == '' || isNaN(val))){
		sum += 1;
	}
	</c:forEach>
	$("#"+"${org.orgFlow}2").text(sum);
	sum=0;
	</c:forEach>
// 	returnYear();
	if("${param.viewBox}"==""){
		changeView("${GlobalConstant.FLAG_N}");
	}else{
		changeView("${param.viewBox}");
	}
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
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
	if(trainTypeId==""){
		jboxTip("培训类别不能为空！");
		return;
	}
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
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
	jboxLoad("content","<s:url value='/jsres/statistic/statisticCountryOrgAcc'/>?orgLevel="+orgLevel+""+data+"&viewBox="+viewBox+"&trainTypeId="+trainTypeId+"&sessionNumber="+sessionNumber,true);
}
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
	$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
}
function changeView(obj){
	if(obj=="${GlobalConstant.FLAG_Y}"){
		$(".docCountView").toggle(false);
		$(".docNameView").toggle(true);
		$(".kong").toggle(false);
		$(".zongji").toggle(false);
		$(".zongji2").toggle(true);
		$(".xiaoji").toggle(false);
		$(".xiaoji2").toggle(true);
		$(".org").toggle(true);
	}else{
		$(".docCountView").toggle(true);
		$(".docNameView").toggle(false);
		$(".kong").toggle(true);
		$(".zongji").toggle(true);
		$(".zongji2").toggle(false);
		$(".xiaoji").toggle(true);
		$(".xiaoji2").toggle(false);
		$(".org").toggle(false);
	}
	if(obj=="${GlobalConstant.FLAG_Y}"){
		$("input[name=viewName]:eq(1)").attr("checked","checked");
	}else{
		$("input[name=viewName]:eq(0)").attr("checked","checked");
	}
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年份不能为空！");
		return;
	}
	var trainTypeId=$("#trainTypeId").val();
	if(trainTypeId==""){
		jboxTip("培训类别不能为空！");
		return;
	}
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return;
	}
	jboxTip("导出中…………");
	var url = "<s:url value='/jsres/statistic/exportExcel'/>?"+data+"&"+$("#searchForm").serialize();
	window.location.href=url;

	jboxEndLoading();
}
</script>
<div class="main_hd">
    <h2 class="underline">学员信息统计</h2>
</div>
	<div class="search_table clearfix">
		<form id="searchForm">
			<div style="margin-top: 15px;margin-left: 10px">
				基地类型：
				<select name="orgLevel" id="orgLevel" class="select" style="width:107px;">
				 	<option value="">请选择</option>
					<c:forEach items="${orgLevelEnumList}" var="level">
						<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
						<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
						>${level.name}</option>
					</c:forEach>
				</select>&#12288;&nbsp;&nbsp;
				培训类别：
				 <select name="trainTypeId" id="trainTypeId" class="select" style="width:107px;">
					 <option value="AssiGeneral">助理全科</option>
				<%--	<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
				</select>&#12288;&nbsp;&nbsp;
				<label style="cursor: pointer;"><input type="radio" name="viewName" value="${GlobalConstant.FLAG_N }" onclick="changeView('${GlobalConstant.FLAG_N}');"/>查看医师数量</label>&nbsp;
				<label style="cursor: pointer;"><input type="radio"  name="viewName" value="${GlobalConstant.FLAG_Y }"  onclick="changeView('${GlobalConstant.FLAG_Y }');"/>查看基地专业关系</label>
				<br><br>
				年&#12288;&#12288;级：
				<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${sessionNumber}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px" />
				&#12288;
				人员类型：
					<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
						<label><input type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>
					</c:forEach>&nbsp;&nbsp;
				<input class="btn_green" type="button" value="查&#12288;询" onclick="searchInfo();"/>&#12288;&nbsp;&nbsp;
				<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>&#12288;&nbsp;&nbsp;
			</div>
		</form>
		<!--表格  -->
		<div id="tableContext" style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
			<!--第一次 -->
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid"style="width: auto;" >
						<tr>
							<th style="min-width: 145px;max-width: 145px;" class="toFiexdDept">
								名称
							</th>
							<th style="width: 60px;min-width: 60px;max-width: 60px;"class="xiaoji toFiexdDept">
								小计
							</th>
							<th style="width: 60px;min-width: 60px;max-width: 60px;"class="xiaoji2 toFiexdDept">
								小计
							</th>
							<c:forEach items="${sysOrgList}" var="sysOrg" >
								  <th  style="width: 170px;min-width:170px;"class="fixedBy" title="${sysOrg.orgName}">${pdfn:cutString(sysOrg.orgName,8,true,3) }</th>
							</c:forEach>
					    </tr>
					</table>
				</div>
			<!--第二次 -->
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid" style="min-width: 100px;max-width: 100px;border-right: 0px">
						<tr>
							<th style="min-width: 145px;max-width: 145px;"class="toFiexdDept">
								名称
							</th>
							<th style="width: 60px;width: 60px;min-width: 60px;"class="xiaoji toFiexdDept">
								小计
							</th>
							<th style="width: 60px;width: 60px;min-width: 60px;"class="xiaoji2 toFiexdDept">
								小计
							</th>
						</tr>
						<c:set var= "mk" value="dictTypeEnum${param.trainTypeId}List"/>
						<c:forEach items="${applicationScope[mk]}" var="dict">
						  <tr>
							<td style="height: 40px;background: white;"class="fix">
								${dict.dictName}
							</td>
							<td style="height: 40px;background: white;" class="fix">
								<label id="${dict.dictId}" class="xiaoji"></label>
								<label id="${dict.dictId}2" class="xiaoji2"></label>
							</td>
						  </tr>
						</c:forEach>
					<tr class="zongji">
						<td style="height: 40px;background: white;">总计</td>
						<td style="min-width: 60px;max-width: 60px;background: white;"><label id="all" style="background: white;"></label></td>
					</tr>
						<tr class="zongji2">
						<td style="height: 40px;background: white;">总计</td>
						<td style="min-width: 60px;max-width: 60px;background: white;"><label id="all2" style="background: white;"></label></td>
					</tr>
					</table>
				</div>
		        <!--第三次  -->
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
					<table class="grid" style="width: auto;border-right: 0px" >
						<tr>
							<th style="min-width: 145px;max-width: 145px;" class="toFiexdDept">名称</th>
							<th style="min-width: 60px;max-width: 60px;" class="xiaoji toFiexdDept" >小计</th>
							<th style="min-width: 60px;max-width: 60px;" class="xiaoji2 toFiexdDept" >小计</th>
						</tr>
					</table>
				</div>
				<table class="grid" style="width: auto;">
					<tr>
						<th style="width: 145px;min-width: 145px;max-width: 145px;"class="toFiexdDept">
							名称
						</th>
						<th style="width: 60px;min-width: 60px;max-width: 60px;" class="xiaoji toFiexdDept">
							小计
						</th>
						<th style="width: 60px;min-width: 60px;max-width: 60px;" class="xiaoji2 toFiexdDept">
							小计
						</th>
						<c:forEach items="${sysOrgList}" var="org">
							  <th style="width: 170px;min-width:170px;max-width:170px;" class="fixedBy" title="${org.orgName}">${pdfn:cutString(org.orgName,8,true,3) }</th>
						</c:forEach>
				    </tr>
				   	 	<c:set var ="mark" value="dictTypeEnum${param.trainTypeId}List"/>
				   		 <c:forEach items="${applicationScope[mark]}" var="dict">
						    <tr>
						    	<td></td>
						    	<td class="xiaoji"></td>
						    	<td class="xiaoji2"></td>
						    	<c:forEach items="${sysOrgList}" var="sysOrg">
						    		<td style="width: 170px;min-width:170px;max-width:170px;" class="by">
					    		    <c:set var="flow" value="${sysOrg.orgFlow}${param.trainTypeId}${dict.dictId}"/>
						    		<c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
						    			<div class="docCountView">
											<label id="${sysOrg.orgFlow}${dict.dictId}">
												${totalCountMap[flow]+0}
											</label>
											<c:if test="${totalCountMap[flow]+0 !=0 and joingCountMap[flow]+0 !=0}">
												<label>(${joingCountMap[flow]+0})</label>
											</c:if>
										</div>
										<div class="docNameView" style="display: none;">
											 <img  style="vertical-align:middle; margin-right:10px;" src="<s:url value='/jsp/jsres/images/gou.gif'/>"/>
										</div>
									</c:if>
									<div class="kong">
										<c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
										--
										</c:if>
									</div>
									</td>
						    	</c:forEach>
						    </tr>
				  	 	</c:forEach>
				   	 <!-- 总计 -->
			   	 	<tr class="zongji">
			   	 		<td></td>
			   	 		<td style="min-width: 60px;max-width: 60px;background: white;"><label id="all"  style="margin-left: 40px;background: white;"></label></td>
				   	 <c:forEach items="${sysOrgList}" var="org">
			   	 		<td style="width: 170px;min-width:170px;max-width:170px;" >
			   	 			<label id="${org.orgFlow}"></label>
			   	 		</td>
				   	 </c:forEach>
			   	 	</tr><!-- 总计 -->
			   	 	<tr class="zongji2">
			   	 		<td></td>
			   	 		<td style="min-width: 60px;max-width: 60px;background: white;"><label id="all2"  style="margin-left: 40px;background: white;"></label></td>
				   	 <c:forEach items="${sysOrgList}" var="org">
			   	 		<td style="width: 170px;min-width:170px;max-width:170px;" >
			   	 			<label id="${org.orgFlow}2"></label>
			   	 		</td>
				   	 </c:forEach>
			   	 	</tr>
				</table>
		</div>
			<table style="margin-left: 35px">
					<tr >
						<td colspan="${sysOrgList.size()}">
							<label>说明：<br>
							(1) "--" 代表这家基地没有此专业<br>
							(2) "XX(XX)"表示主基地和协同基地所有医师培训记录总数(协同基地医师记录总数)<br>
							(3) <img  style="vertical-align:middle; margin-right:10px; display: inline;" src="<s:url value='/jsp/jsres/images/gou.gif'/>"/>
							代表有此专业。
							</label>
						
						</td>
					</tr>
					
				</table>
	</div>

<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">
<script>
	$(function(){
//		chartsInfo();
	});
	function chartsInfo()
	{
		jboxLoad("chartsInfoDiv","<s:url value='/jsres/statistic/chartsInfo'/>?"+$("#chartsInfoSearch").serialize(),true);
	}
</script>
<div class="main_bd search_table clearfix" style="height:auto;">
	<ul>
		<li class="score_frame">
			<h1 style="background:#e7f5fc;">学员信息图表统计</h1>
			<div class="" style="margin-top: 15px;">
				<form id="chartsInfoSearch">
					<table cellspacing="8px">
						<tr>
							<td>&#12288;年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>
								<c:if test="${not empty param.sessionNumber }">value="${param.sessionNumber}"</c:if>
									   class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><input class="btn_green" type="button" value="查&#12288;询" onclick="chartsInfo();"/></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="index_table" id="chartsInfoDiv"style="height: auto;width:100%;margin-top: 50px;">
				<jsp:include page="chartsInfo.jsp" flush="true"></jsp:include>
			</div>
		</li>
	</ul>
</div>
</c:if>