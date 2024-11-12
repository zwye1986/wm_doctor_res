<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$(".leftNum").each(function(){
			var all = $(this).prev().prev().prev().prev().text();
			var a1 = $(this).prev().text();
			if(!a1) a1=0;
			if(all){
				var a2 = parseInt(all)-parseInt(a1);
				$(this).text(a2);
			}
		})
	});
	function recruitUsers(speId , graduatedId , key , batchOper , assignPlan  , confirmResult, retestFlag , admitFlag ,
						  page , order ,personType,adminSwapFlag,datas){
		var data={
				"speId":speId,
				"graduatedId":graduatedId,
				"key":key,
				"batchOper":batchOper,
				"assignPlan":assignPlan,
				"confirmResult":confirmResult,
				"retestFlag":retestFlag,
				"admitFlag":admitFlag,
				"currentPage":page,
				"order":order,
				"personType":personType,
				"adminSwapFlag":adminSwapFlag,
			};
			var ary = "";
		if(datas){
			for (var i=0; i<datas.length; i++){
				ary+=datas[i]+",";
			}
			ary=ary.substr(0, ary.length - 1);
		}
		jboxPostLoad("content","<s:url value='/hbres/singup/hospital/getdoctors'/>?datas="+ary,data,true);
	}
	function impOperExam(){
		jboxOpen("<s:url value='/jsp/hbres/hospital/importOperExam.jsp'/>", "复试成绩单导入", '600', '300');
	}
	function toPage(page){
		if(page){
			jboxLoad("content","<s:url value='/hbres/singup/hospital/main'/>?currentPage="+page,true);
		}
	}
</script>
<div class="main_hd">
	<h2 class="underline">招录管理</h2>
</div>
<div id="docCountInfo" class="search_table" style="margin-bottom:0;margin-top:20px;">
    <span class="tip_right"><em>●</em>计划招录人数：<strong>${totalAssignPlan}</strong>人</span>
    <span class="tip_right"><em>●</em>填报人数：<strong>${totalFillNum}</strong>人</span>
    <!-- 
    <span class="tip_right"><em>●</em>通知复试人数：<strong>${totalRetestNum}</strong>人</span>
    <span class="tip_right"><em>●</em>预录取人数：<strong>${totalRecruitNum}</strong>人</span>
     -->
    <span class="tip_right"><em>●</em>确认录取人数：<strong>${totalConfirmCount}</strong>人</span>
    <span class="tip_right"><em>●</em>省厅调剂人数：<strong>${adminSwapTotle}</strong>人</span><br>
    <span style="margin-top: 5px"><a href="<s:url value='/hbres/singup/hospital/exportdoctor/fill'/>" class="btn_green">导出填报学员</a></span>
	<span><a href="<s:url value='/hbres/singup/hospital/exportdoctor/retest'/>" class="btn_green">导出复试通知学员</a></span>
	<span><a href="<s:url value='/hbres/singup/hospital/exportdoctor/reutrnBack'/>" class="btn_green">导出退回学员</a></span>
	<span><a href="<s:url value='/hbres/singup/hospital/exportRecruit'/>" class="btn_green">导出录取学员</a></span>
	<span><a onclick="impOperExam()" class="btn_green">复试成绩单导入</a></span>
	<span><a href="<s:url value='/hbres/singup/hospital/exportdoctorHasResult'/>" class="btn_green">复试成绩已导名单</a></span>
</div>
<div class="main_bd" id="div_table_0" >
        <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th>序号</th>
              <th style="text-align: left;padding-left: 20px;">专业代码</th>
              <th style="text-align: left;padding-left: 20px;">专业基地名称</th>
              <th>累计总容量</th>
              <th>填报志愿人数</th>
              <th>通知复试人数</th>
              <!-- <th>预录取人数</th>-->
              <!-- <th>调剂人数</th> -->
              <th>确认录取人数</th>
              <th style="color: #00a0ff">剩余总容量</th>
              <th>省厅调剂人数</th>
              <th>操作</th>
            </tr>
            <c:forEach items="${doctorTrainingSpeList}" var="dict" varStatus="s">
            <c:set var="speId" value="${dict.dictId}"></c:set>
            <c:set var="assignPlan" value="${speAssignMap[speId].assignPlan}"></c:set>
            <%--<c:if test="${!empty assignPlan and assignPlan>0}">--%>
	            <tr>
	            	<td>${s.index+1}</td>
	            	<td style="text-align: left;padding-left: 20px;">${speId}</td>
	                <td style="text-align: left;padding-left: 20px;">${dict.dictName}</td>
	                <td>${assignPlan}</td>
	                <td>${recruitNumMap[speId]}</td>
	                <td>${retestNumMap[speId] }</td>
	                <!-- <td>${recruitYNumMap[speId] }</td> -->
	                <!-- <td>${swapCountMap[speId]}</td> -->
	                <td>${confirmCountMap[speId]}</td>
	                <td style="color: #00a0ff" class="leftNum"></td>
	                <td>${adminSwapMap[speId]}</td>
	                <td>
		                <a class="btn" onclick="recruitUsers('${speId}',null,null,null,'${assignPlan}','${confirmCountMap[speId]}','','','','','signStu');">人员名单</a>
	            	</td>
	            </tr>
            <%--</c:if>--%>
            </c:forEach>
          </table>
        </div>
	<div class="page" style="text-align: center;">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		<c:set var="pageView" value="${pdfn:getPageView(doctorTrainingSpeList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
      
