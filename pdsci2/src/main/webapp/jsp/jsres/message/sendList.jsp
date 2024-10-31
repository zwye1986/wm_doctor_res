<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $('#assignYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
    });

	// 导入
	function impPlan(orgName, orgFlow, assignYear){
        jboxOpen("<s:url value='/jsres/message/importPlan'/>?orgFlow=" + orgFlow + "&assignYear=" + assignYear, orgName + assignYear + "年招录计划导入", 500, 200);
	}

	// 新增招录计划
	function add(){
        var url= "<s:url value='/jsres/message/addSend'/>";
        var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='600px' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='" + url + "'></iframe>";
        jboxMessager(iframe, '新增报送计划', 900, 600);
	}

	// 编辑
	function edit(orgFlow, assignYear,startTime,endTime,flag){
        var url= "<s:url value='/jsres/message/addSend'/>?orgFlowEdit=" + orgFlow + "&assignYearEdit=" + assignYear +"&sendStartTime="+startTime +"&sendEndTime="+endTime +"&flag="+flag;
        var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='600px' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='" + url + "'></iframe>";
        jboxMessager(iframe, '编辑报送计划', 900, 600);
	}

</script>

		<div class="div_search" style="padding: 0px 30px;">
			<form id="searchForm" method="post">
				<table style="border-collapse:separate; border-spacing:0px 20px;">
					<tr>
						<td class="td_right">年&#12288;&#12288;份：</td>
						<td class="td_left">
							<input class="input" name="assignYear" id="assignYear" readonly="readonly" value="${param.assignYear}" type="text" onchange="getSendList(this.value, 1)"/>
						</td>
						<td colspan="2">
							&#12288;<input type="button" class="btn_green" value="新&#12288;增" onclick="add()" />
						</td>
					</tr>
				</table>
			</form>
		</div>
        <div class="div_table" style="padding: 0px 30px;">
			<c:set var="sum" value="0"></c:set>
          	<table border="0" cellpadding="0" cellspacing="0" class="grid">
            	<tr>
              		<th style="text-align: center;width: 5%">序号</th>
              		<th style="text-align: center;width: 7%">年份</th>
			  		<th style="text-align: center;width: 20%">基地名称</th>
					<th style="text-align: center;width: 10%">基地类型</th>
					<th style="text-align: center;width: 20%">报送时间</th>
				  	<th style="text-align: center;width: 14%">计划报送总数</th>
				  	<th style="text-align: center;width: 14%">详情</th>
            	</tr>
            	<c:forEach items="${resultMapList}" var="result" varStatus="s">
            		<tr>
						<td>${s.index+1}</td>
						<td style="text-align: center;width: 120px;">${result.ASSIGN_YEAR}</td>
						<td style="text-align: center;">${result.ORG_NAME}</td>
						<td style="text-align: center;">${result.ORG_TYPE}</td>
						<td style="text-align: center;">
							<c:if test="${empty result.SEND_START_TIME}">
								无
							</c:if>
							<c:if test="${not empty result.SEND_START_TIME}">
								${result.SEND_START_TIME}~${result.SEND_END_TIME}
							</c:if>
						</td>
						<td style="text-align: center;width: 150px;">
							${result.SEND_PLAN_SUM}
							<c:set var="sum" value="${sum+result.SEND_PLAN_SUM}"></c:set>
						</td>
						<td style="text-align: center;width: 200px;">
							<a class="btn" href="javascript:void(0);"
							   onclick="edit('${result.ORG_FLOW}','${result.ASSIGN_YEAR}','${result.SEND_START_TIME}','${result.SEND_END_TIME}','edit');">编辑</a>&nbsp;
							<a class="btn" href="javascript:void(0);" onclick="impPlan('${result.ORG_NAME}','${result.ORG_FLOW}','${result.ASSIGN_YEAR}');">导入计划</a>
						</td>
            	</tr>
            </c:forEach>
            	<c:if test="${empty resultMapList}">
					<tr>
						<td colspan="7">无记录</td>
					</tr>
            	</c:if>
				<tr>
					<td style="text-align: right;color: red;" colspan="6">计划报送合计：${sum}人</td>
					<td></td>
				</tr>
          	</table>
        </div>
	  	<div class="page" style="text-align: right;">
		  	<input id="currentPage" type="hidden" name="currentPage" value=""/>
		  	<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
		  	<pd:pagination-jsres toPage="toPage"/>
	  	</div>
      
