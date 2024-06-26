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
	// 点击报名 展示详细的报考信息
	function editDoctorRecruit(speId){
		var doctorFlow = $("#doctorFlow").val();
		var speId = speId;
		var orgFlow = $("#orgFlow").val();
		var assignYear = $("#assignYear").val();
		if(doctorFlow == ""){
			jboxTip("请先完善基本信息！");
			return false;
		}
		var url = "<s:url value='/jsres/message/editDoctorRecruit'/>?openType=open&speId="+
				speId+"&orgFlow="+orgFlow+"&assignYear="+assignYear;
		var title = "学员报名";
		jboxOpen(url, title, 900, 500);
		speId = '';
	}

	function getDoctorRecruit(recruitFlow, doctorFlow){
		var url = "<s:url value='/jsres/doctor/getDoctorRecruit?recruitFlow='/>"+recruitFlow + "&doctorFlow=" + doctorFlow;
		jboxLoad("doctorContent", url, true);
	}


</script>
<div class="main_hd">
	<div  style="text-align: right;margin-right: 100px;">
		计划招录专业：<span id="speNum"></span>&#12288;&#12288;
		计划招录人员总数：<span id="total"></span>
	</div>
</div>
<div class="main_bd" id="div_table_0">
	<input type="text" id="orgFlow" hidden="hidden" value="${orgFlow}">
	<input type="text" id="assignYear" hidden="hidden" value="${assignYear}">
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
				<td style="text-align: center;width: 120px;">${speAssign.speName}</td>
				<!--专业简介-->
				<td style="text-align: center;width: 120px;" id="speId" name="speId">${speAssign.speDesc}</td>
				<!--招收人数-->
				<td style="text-align: center;">${speAssign.assignPlan}</td>
				<!--毕业专业(招收要求)-->
				<td style="text-align: center;width: 120px;">
					${speAssign.graduateSpe}
				</td>
				<!--学历学位(招收要求)-->
				<td style="text-align: center;width: 120px;">
						${speAssign.education}
				</td>
				<td style="text-align: center;width: 120px;">
					<a class="btn" href="javascript:void(0);"
					   onclick="editDoctorRecruit('${speAssign.speId}');">去报名</a>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty speAssignList}">
			<tr>
				<td colspan="10">无记录</td>
			</tr>
		</c:if>
	</table>
</div>
      
