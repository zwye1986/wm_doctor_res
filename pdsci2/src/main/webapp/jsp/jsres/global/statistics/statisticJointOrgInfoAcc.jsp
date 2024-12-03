<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
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
// 	$("#tableContext").css("height",height+"px");	
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
}
function heightTh(){
	var height = document.body.clientHeight-110;
// 	$("#tableContext").css("height",height+"px");	
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
		changeView("${GlobalConstant.FLAG_N}");
	}else{
		changeView("${param.viewBox}");
	}
// 	year();
	<c:forEach items="${resDocTypeEnumList}" var="type">
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
	<c:forEach items="${resDocTypeEnumList}" var="type">
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
	jboxLoad("content","<s:url value='/jsres/statistic/statisticJointOrgAcc'/>?orgLevel="+orgLevel+"&viewBox="+viewBox+""+data+"&trainTypeId="+trainTypeId+"&sessionNumber="+sessionNumber,true);
}
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
	$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
}
// function year(){
// 	var sessionNumber=$("#sessionNumber").val();
// 	if(sessionNumber<2015){
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumWMFirst.id}']").show();
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumDoctorTrainingSpe.id}']").hide();
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumWMFirst.id}']").attr("selected","selected");
// 	}
// 	if(sessionNumber>2015||sessionNumber==2015){
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumWMFirst.id}']").hide();
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumDoctorTrainingSpe.id}']").show();
// 		$("select[name=trainTypeId] option[value = '${trainCategoryEnumDoctorTrainingSpe.id}']").attr("selected","selected");
// 	}
// }
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
	function showNum(vari){
		jboxOpenContent($(vari).parent().next().next().html(),$(vari).text()+"各专业报名人数",500,300);
	}
</script>
<div class="main_hd">
    <h2 class="underline">协同基地信息统计</h2> 
</div>
	<div class="search_table clearfix">
			<div style="margin-top: 15px;margin-left: 10px">
				基地类型：
				<select name="orgLevel" id="orgLevel" class="select" style="width:107px;">
				 	<option value="">请选择</option>
					<c:forEach items="${orgLevelEnumList}" var="level">
						<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
						<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
						>${level.name}</option>
					</c:forEach>
				</select>&nbsp;&#12288;
				培训类别：
				 <select name="trainTypeId" id="trainTypeId" class="select" style="width:107px;">
					 <option value="AssiGeneral">助理全科</option>
					<%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
				</select>&#12288;
				人员类型：
				<c:forEach items="${resDocTypeEnumList}" var="type">
					<label><input type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>
				</c:forEach>
				<br><br>
				年&#12288;&#12288;份：
				<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${sessionNumber}"</c:if>
					   <c:if test="${not empty param.sessionNumber }"> value="${param.sessionNumber}"</c:if>
					   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
				<input class="btn_green" type="button" value="查&#12288;询" onclick="searchInfo();"/>&#12288;
			</div>
		<!--表格  -->
		<div id="tableContext" style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
			<!--第一次 -->
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="grid"style="width: auto;" >
						<tr>
							<th style="min-width: 110px;max-width: 110px;"class="toFiexd">
								协同医院
							</th>
							<c:forEach items="${sysOrgList}" var="sysOrg" >
								  <th  style="width: 170px;min-width:170px;max-width: 170px"class="xiaoji fixed" title="${sysOrg.orgName}">${pdfn:cutString(sysOrg.orgName,8,true,3)}</th>
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
						<tr>
						  <td style="background: white;" class="toFiexdDept">人数总计</td>
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
						<th style="width: 130px;min-width: 130px;max-width: 110px;"  class="toFiexd">
							协同医院
						</th>
						<c:forEach items="${sysOrgList}" var="org">
							  <th style="width: 170px;min-width:170px;max-width:170px;"class="fixed" title="${org.orgName }">${pdfn:cutString(org.orgName,8,true,3)}</th>
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
												<c:set var="countJoint" value="0"></c:set>
												<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
													<c:set var="countJoinKey" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
													<c:set var="countJoint" value="${jointOrgDocCountMap[countJoinKey]+countJoint}"></c:set>
												</c:forEach>
						    					<span><a href="javascript:void(0);" onclick="showNum(this);">${jointOrgListMap[flow].jointOrgName}(${countJoint})</a></span><br>
						    					<div class="docNameView" style="padding-left: 25px;">
													<c:if test="${not empty jointOrgSpeMap[mark]}">
														<table class="grid" width="auto">
															<tr><th>专业</th><th>人数</th></tr>
															<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
																<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
																<tr><td>${org.speName}</td><td>${jointOrgDocCountMap[speMark]+0}</td></tr>
																<%--${org.speName}:&nbsp;${jointOrgDocCountMap[speMark]+0}--%>
																<%--<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>--%>
															</c:forEach>
														</table>
													</c:if>
					    							<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
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
												<c:set var="countJoint" value="0"></c:set>
												<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
													<c:set var="countJoinKey" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
													<c:set var="countJoint" value="${jointOrgDocCountMap[countJoinKey]+countJoint}"></c:set>
												</c:forEach>
						    					<span><a href="javascript:void(0);" onclick="showNum(this);">${jointOrgListMap[flow].jointOrgName}(${countJoint})</a></span><br>
						    					<div class="docNameView"  style="padding-left: 25px;">
													<c:if test="${not empty jointOrgSpeMap[mark]}">
														<table class="grid" width="auto">
															<tr><th>专业</th><th>人数</th></tr>
															<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
																<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
																<tr><td>${org.speName}</td><td>${jointOrgDocCountMap[speMark]+0}</td></tr>
																<%--<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>--%>
															</c:forEach>
														</table>
													</c:if>
					    							<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
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
												<c:set var="countJoint" value="0"></c:set>
												<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
													<c:set var="countJoinKey" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
													<c:set var="countJoint" value="${jointOrgDocCountMap[countJoinKey]+countJoint}"></c:set>
												</c:forEach>
						    					<span><a href="javascript:void(0);" onclick="showNum(this);">${jointOrgListMap[flow].jointOrgName}(${countJoint})</a></span><br>
						    					<div class="docNameView" style="padding-left: 25px;">
													<c:if test="${not empty jointOrgSpeMap[mark]}">
														<table class="grid" width="auto">
															<tr><th>专业</th><th>人数</th></tr>
															<c:forEach items="${jointOrgSpeMap[mark]}" var="org" varStatus="num">
																<c:set var="speMark" value="${jointOrgListMap[flow].jointOrgFlow}${org.speTypeId}${org.speId}"/>
																<tr><td>${org.speName}</td><td>${jointOrgDocCountMap[speMark]+0}</td></tr>
																<%--<c:if test="${fn:length(jointOrgSpeMap[mark]) != num.count}"><br></c:if>--%>
															</c:forEach>
														</table>
													</c:if>
					    							<c:if test="${empty jointOrgSpeMap[mark]}">无</c:if>
					    							<br>
						    					</div>
						    				</c:if>
						    			</label>
						    		</td>
						    	</c:forEach>
					    </tr>
						<tr>
							<td class="fixedBy">人数总计</td>
							<c:forEach items="${sysOrgList}" var="sysOrg">
								<td class="tdClass" style="width: 130px;padding-top: 10px" valign="top">
									${empty orgJointDocSumMap[sysOrg.orgFlow] ? 0 : orgJointDocSumMap[sysOrg.orgFlow]}
								</td>
							</c:forEach>
						</tr>
				</table>
		</div>
	</div>
