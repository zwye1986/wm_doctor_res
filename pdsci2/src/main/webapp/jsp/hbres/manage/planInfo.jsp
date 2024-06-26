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
</script>
      <div class="main_hd">
        <h2 class="underline">
       		 基地：
       		<c:choose>
			<c:when test="${GlobalConstant.USER_LIST_LOCAL eq param.source }">
			 	${sessionScope.currUser.orgName}
			</c:when>
			<c:otherwise>
				<select class="xlselect" name="orgFlow" onchange="planInfo(this.value,${param.assignYear});" style="border:1px solid #d6d7d9; height:34px; line-height:30px; width:260px; outline:none;font-family: microsoft yahei;">
				    <option value="">请选择</option>
					    <c:forEach var="org" items="${orgList}">
							<option value="${org.orgFlow}" <c:if test='${org.orgFlow == orgFlow}'>selected="selected"</c:if>>${org.orgName}</option>
						</c:forEach>
				</select>
			</c:otherwise>
			</c:choose>
			<c:if test="${GlobalConstant.USER_LIST_LOCAL != param.source }">
       		 &#12288;&#12288;
				<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
				<c:if test="${!isFree}">
       		 		<a id="operA" class="btn_green" href="javascript:goLabel();" style="background-color:#44b549;">编&#12288;辑</a>
				</c:if>
				<a class="btn_green" href="javascript:goBack();" style="background-color:#44b549;">返&#12288;回</a>
       		 </c:if>
        </h2>
		 <div  style="text-align: right;margin-right: 100px;">
			 计划招录专业：<span id="speNum"></span>&#12288;&#12288;
			 计划招录人员总数：<span id="total"></span>
		 </div>
      </div>
      <div class="main_bd" id="div_table_0">
         <div class="search_table">
         	<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="i">
				<div class="recruit">
					<div class="recruit_top">${dict.dictName}</div>
					<div class="recruit_body">
						<c:set value="${dict.dictId}" var="key"/>
						<input class="dataPut"  style="display: none;border: 1px #ccc solid;width: 30px;text-align: right; margin-top:20px;" type="text" oldValue="${speAssignMap[key].assignPlan}" value="${speAssignMap[key].assignPlan+0}" recordFlow="${speAssignMap[key].recordFlow}" onchange="savePlan('${orgFlow}','${dict.dictId}',this);$('#${key}').text(this.value);">
						<label class="dataPut" id="${key}">${speAssignMap[key].assignPlan+0 }</label>
					</div>
				</div>
			</c:forEach>
         </div>
      </div>
      
