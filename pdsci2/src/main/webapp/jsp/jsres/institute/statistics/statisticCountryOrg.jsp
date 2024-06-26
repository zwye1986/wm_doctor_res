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
//页面加载完成
$(function(){
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

	heightFiexed();
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
	jboxLoad("content","<s:url value='/jsres/institute/statisticCountryOrg'/>?orgLevel="+orgLevel+""+data+"&viewBox="+viewBox+"&trainTypeId="+trainTypeId+"&sessionNumber="+sessionNumber,true);
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
		$(".xiaoji").toggle(false);
		$(".org").toggle(true);
	}else{
		$(".docCountView").toggle(true);
		$(".docNameView").toggle(false);
		$(".kong").toggle(true);
		$(".zongji").toggle(true);
		$(".xiaoji").toggle(true);
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
	var url = "<s:url value='/jsres/institute/exportExcel'/>?"+data+"";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
</script>
<div class="main_hd">
    <h2 class="underline">医师信息统计</h2> 
</div>
	<div class="search_table clearfix">
		<form id="searchForm">
			<div style="margin-top: 15px;margin-left: 33px">
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
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>
				</select>&#12288;&nbsp;&nbsp;
				<label style="cursor: pointer;"><input type="radio" name="viewName" value="${GlobalConstant.FLAG_N }" onclick="changeView('${GlobalConstant.FLAG_N}');"/>查看医师数量</label>&nbsp;
				<label style="cursor: pointer;"><input type="radio"  name="viewName" value="${GlobalConstant.FLAG_Y }"  onclick="changeView('${GlobalConstant.FLAG_Y }');"/>查看基地专业关系</label>
				<br><br>
				年&#12288;&#12288;份：
				<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px" />
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
		<div id="tableContext" style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 30px;" onscroll="tableFixed(this);">
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
							<c:forEach items="${sysOrgList}" var="sysOrg" >
								  <th  style="width: 170px;min-width:170px;"class="fixedBy" title="${sysOrg.orgName}">${pdfn:cutString(sysOrg.orgName,12,true,3) }</th>
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
						</tr>
						<c:set var= "mk" value="dictTypeEnum${param.trainTypeId}List"/>
						<c:forEach items="${applicationScope[mk]}" var="dict">
						  <tr>
							<td style="height: 40px;background: white;"class="fix">
								${dict.dictName}
							</td>
							<td style="height: 40px;background: white;" class="fix">
								<label id="${dict.dictId}" class="xiaoji"></label>
							</td>
						  </tr>
						</c:forEach>
					<tr class="zongji">
						<td style="height: 40px;background: white;">总计</td>
						<td style="background: white;"><label id="all" style="background: white;"></label></td>	
					</tr>
					</table>
				</div>
		        <!--第三次  -->
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
					<table class="grid" style="width: auto;border-right: 0px" >
						<tr>
							<th style="min-width: 145px;max-width: 145px;" class="toFiexdDept">名称</th>
							<th style="min-width: 60px;max-width: 60px;" class="xiaoji toFiexdDept" >小计</th>
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
						<c:forEach items="${sysOrgList}" var="org">
							  <th style="width: 170px;min-width:170px;max-width:170px;" class="fixedBy" title="${org.orgName}">${pdfn:cutString(org.orgName,12,true,3) }</th>
						</c:forEach>
				    </tr>
				   	 	<c:set var ="mark" value="dictTypeEnum${param.trainTypeId}List"/>
				   		 <c:forEach items="${applicationScope[mark]}" var="dict">
						    <tr>
						    	<td></td>
						    	<td class="xiaoji"></td>
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
			   	 		<td style="background: white;"><label id="all"  style="margin-left: 40px;background: white;"></label></td>
				   	 <c:forEach items="${sysOrgList}" var="org">
			   	 		<td style="width: 170px;min-width:170px;max-width:170px;" >
			   	 			<label id="${org.orgFlow}"></label>
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
							(3) <img  style="vertical-align:middle; margin-right:10px;" src="<s:url value='/jsp/jsres/images/gou.gif'/>"/>
							代表有此专业。
							</label>
						
						</td>
					</tr>
					
				</table>
	</div>
