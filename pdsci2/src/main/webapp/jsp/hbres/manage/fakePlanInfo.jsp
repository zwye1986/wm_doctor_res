<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	<%--$(function(){--%>
		<%--<c:set var="planNum" value="0"/>--%>
		<%--var speNum = 0;--%>
		<%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
			<%--<c:set var="planNum" value="${planNum + speAssignMap[dict.dictId].assignPlanOrg}"/>--%>
			<%--if(${speAssignMap[dict.dictId].assignPlanOrg>0}){--%>
				<%--speNum++--%>
			<%--}--%>
		<%--</c:forEach>--%>
		<%--$("#total").text('${planNum}');--%>
		<%--$("#speNum").text(speNum);--%>
	<%--});--%>
	function savePlan(orgFlow,speId,obj,plan){
        var a = Number($(obj).val());
        if (!(/(^[0-9]\d*$)/.test(a))) {
            jboxTip("不是整数");
            $(obj).val('').focus();
            return false;
        }
		var planOrg = parseInt($(obj).val());
		var plan = parseInt(plan);
		if(!plan) plan=0;
		if(planOrg>plan){
			jboxTip("不能大于省厅计划数量");
			$(obj).val('').focus();
			return false;
		}
        if(planOrg<0){
            jboxTip("不能小于0");
            $(obj).val('').focus();
            return false;
        }
		var data = {};
		data.recordFlow = $(obj).attr("recordFlow");
		data.orgFlow = orgFlow;
		data.speId = speId;
		data.assignPlanOrg = $(obj).val();
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
<style>
	.s1,.s2{
		display: inline-block;
		width:20px;
		height: 20px;
		vertical-align: text-top;
	}
	.s1{
		background: #acd9cb;
	}
	.s2{
		background: #e0f7e1;
	}
</style>
      <div class="main_hd">
        <h2 class="underline">
       		 基地：
			 	${sessionScope.currUser.orgName}
       		 &#12288;&#12288;
				<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
				<c:if test="${!isFree}">
       		 		<a id="operA" class="btn_green" href="javascript:goLabel();" style="background-color:#44b549;">编&#12288;辑</a>
				</c:if>
				<%--<a class="btn_green" href="javascript:goBack();" style="background-color:#44b549;">返&#12288;回</a>--%>
        </h2>
		 <div  style="text-align: right;margin-right: 100px;">
			 <%--计划招录专业：<span id="speNum"></span>&#12288;&#12288;--%>
			 <%--计划招录人员总数：<span id="total"></span>--%>
			 <span class="s1"></span>
			 <span>省厅计划招录人数</span>
			 <span class="s2"></span>
			 <span>基地公开招录人数(若未设置默认等同省厅计划人数)</span>
		 </div>
      </div>
      <div class="main_bd" id="div_table_0">
         <div class="search_table">
         	<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="i">
				<div class="recruit">
					<div class="recruit_top" style="width: 183px;">${dict.dictName}</div>
					<c:set value="${dict.dictId}" var="key"/>
					<div class="recruit_body" style="background: #acd9cb;">
						<label>${speAssignMap[key].assignPlan+0 }</label>
					</div>
					<div class="recruit_body">
						<input class="dataPut"  style="display: none;border: 1px #ccc solid;width: 30px;text-align: right; margin-top:20px;" type="text" oldValue="${speAssignMap[key].assignPlanOrg}" value="${speAssignMap[key].assignPlanOrg}" recordFlow="${speAssignMap[key].recordFlow}" onchange="savePlan('${orgFlow}','${dict.dictId}',this,'${speAssignMap[key].assignPlan }');$('#${key}').text(this.value);">
						<label class="dataPut" id="${key}">${speAssignMap[key].assignPlanOrg }</label>
					</div>
				</div>
			</c:forEach>
			 <div style="clear:both;"></div>
         </div>
      </div>
      
