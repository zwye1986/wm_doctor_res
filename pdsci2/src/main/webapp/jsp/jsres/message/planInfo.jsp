<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(function(){
		<c:set var="planNum" value="0"/>
		var speNum = 0;
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<c:set var="planNum" value="${planNum + speAssignMap[dict.dictId].assignPlan}"/>
			if(${speAssignMap[dict.dictId].assignPlan>0}){
				speNum++
			}
		</c:forEach>
		$("#total").text('${planNum}');
		$("#speNum").text(speNum);
	});
	function savePlan(orgFlow,speId,obj){
		var data = {};
		data.recordFlow = $(obj).attr("recordFlow");
		data.orgFlow = orgFlow;
		data.speId = speId;
		data.assignPlan = $(obj).val();
		data.assignYear = "${param.assignYear}";
		jboxPost("<s:url value='/hbres/singup/manage/savePlan'/>",data,
			function(resp){//传回基地专业招录计划流水号
				if(resp != "${GlobalConstant.SAVE_FAIL}"){
					$(obj).attr("recordFlow",resp);
					var afterNum;
					if($(obj).attr("oldValue") != "" && !isNaN($(obj).attr("oldValue"))){
						afterNum = parseInt($("#total").text()) - parseInt($(obj).attr("oldValue")) + parseInt($(obj).val());
						$(obj).attr("oldValue",$(obj).val());
					}else{
						afterNum = parseInt($("#total").text()) + parseInt($(obj).val());
					}
					$("#total").text(afterNum);
				}
			},null,false);
	}
	
	function goLabel(){
		if ($("#operA").html().indexOf("编") >= 0) {
			$("#operA").html("视&#12288;图");
		} else {
			$("#operA").html("编&#12288;辑");
		}
		$(".dataPut").toggle();
	}
	function goBack(){
		jboxLoad("content","<s:url value='/hbres/singup/manage/plan'/>?assignYear=${sysCfgMap['res_reg_year']}",true);
	}
	//加载专业简介
	function speAssignInfo(speId){
		var data = {};
		data.speId = speId;
		jboxLoad("content","<s:url value='/hbres/singup/manage/plan'/>?assignYear=${sysCfgMap['res_reg_year']}",true);
	}
</script>
<div class="main_hd">
 <div  style="text-align: right;margin-right: 100px;">
	 计划招录专业：<span id="speNum"></span>&#12288;&#12288;
	 计划招录人员总数：<span id="total"></span>
 </div>
</div>
<div class="main_bd" id="div_table_0">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<tr>
			<th>序号</th>
			<th style="text-align: center;width: 120px;">招收专业</th>
			<th style="text-align: center;width: 120px;">专业简介</th>
			<th style="text-align: center;width: 120px;">招收人数</th>
			<th style="text-align: center;">毕业专业</th>
			<th style="text-align: center;width: 150px;">学历学位</th>
			<th style="text-align: center;width: 200px;">操作</th>
		</tr>
		<c:forEach items="${speAssignList}" var="speAssign" varStatus="s">
			<tr>
				<td>${s.index+1}</td>
				<td style="text-align: center;width: 120px;">
					${speAssign.speName}
				</td>
				<td style="text-align: center;width: 120px;">
					<a class="btn" href="javascript:void(0);" onclick="speAssignInfo('${speAssign.speId}');">专业简介</a>
				</td>
				<!--招收人数-->
				<td style="text-align: center;">${speAssign.assignPlan}</td>
				<!--毕业专业-->
				<td style="text-align: center;width: 120px;">
					<!-- Button trigger modal -->
					<button type="button" id="">
						请点击输入
					</button>
				</td>
				<!--学历学位-->
				<td style="text-align: center;width: 150px;">
						<input type="text" id="acadeDegree" value="" ></td>
				<td style="text-align: center;width: 200px;">
					<a class="btn" href="javascript:void(0);"
					   onclick="doctorSignup('${speAssignMap[key].recordFlow}','${orgFlow}','${dict.dictId}');">去报名</a>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty speAssignList}">
			<tr>
				<td colspan="10">无记录</td>
			</tr>
		</c:if>
	</table>
	<div class="page" style="text-align: center;">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		<c:set var="pageView" value="${pdfn:getPageView(speAssignList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>

      
