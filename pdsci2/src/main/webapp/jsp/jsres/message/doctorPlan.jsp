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

    // 分页
    function toPage(currentPage){
        getPlanList('${param.assignYear}',currentPage);
    }

    // 招录计划 切换年份 加载对应的招录计划
    function getPlanList(currentPage){
        var assignYear=$("#assignYear").val();
        var orgCode=$("#orgCode").val();
		var orgFlow =$("#orgFlow").find("option:selected").val();
        jboxLoad("content","<s:url value='/jsres/message/doctorPlan'/>?assignYear="+assignYear + "&orgFlow=" + orgFlow + "&orgCode=" + orgCode  + "&currentPage=" + currentPage,true);
    }

	// 详情
	function planInfo(orgFlow,assignYear) {
        var url= "<s:url value='/jsres/message/doctorPlanInfo'/>?orgFlow=" + orgFlow + "&assignYear=" + assignYear;
        var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='600px' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='" + url + "'></iframe>";
        jboxMessager(iframe, '查看详情', 900, 600);
	}

</script>
	<div class="main_hd">
		<h2 class="underline">
			学员报名
		</h2>
	</div>
  	<div class="main_bd" id="div_table_0">
	  	<div class="div_search" style="padding: 0px 40px;">
		  	<form id="searchForm" method="post">
				<table style="border-collapse:separate; border-spacing:0px 20px;">
			  		<tr>
					  	<td class="td_right">年&#12288;&#12288;份：</td>
					  	<td class="td_left">
						  	<input class="input" name="assignYear" id="assignYear" readonly="readonly" value="${param.assignYear}" type="text"/>
					  	</td>
						<th width="40px"></th>
						<td class="td_right">基&#12288;&#12288;地：</td>
						<td class="td_right">
							<select name="orgFlow" class="select" id="orgFlow" style="width: 161px">
								<option value="">全部</option>
								<c:forEach items="${orgs}" var="dict">
									<option value="${dict.orgFlow}"
											<c:if test="${dict.orgFlow eq orgFlow}">selected</c:if>>${dict.orgName}</option>
								</c:forEach>
							</select>
						</td>
						<th width="40px"></th>
						<td class="td_right">基地编码：</td>
						<td class="td_left">
							<input class="input" name="orgCode" id="orgCode" width="161px"  value="${orgCode}" type="text"/>
						</td>
						<td>
							<input class="btn_green" type="button" style="margin-left: 40px" value="查&#12288;询" onclick="getPlanList(1)"/>
						</td>
					</tr>
				</table>
		  	</form>
	  	</div>
		<div class="div_table" style="padding: 0px 30px;">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th>基地编码</th>
					<th style="text-align: center;width: 120px;">年份</th>
					<th style="text-align: center;">基地名称</th>
					<th style="text-align: center;">基地类型</th>
					<th style="text-align: center;width: 150px;">计划招录总数</th>
					<th style="text-align: center;width: 200px;">详情</th>
				</tr>
				<c:forEach items="${resultMapList}" var="result" varStatus="s">
					<c:if test="${result.ORG_NAME eq '东部战区总医院'}">
						<tr>
							<td>${result.ORG_CODE}</td>
							<td style="text-align: center;width: 120px;">${result.ASSIGN_YEAR}</td>
							<td style="text-align: center;">${result.ORG_NAME}</td>
							<td style="text-align: center;">${result.ORG_TYPE}</td>
							<td style="text-align: center;width: 150px;">${result.ASSIGN_PLAN_SUM}</td>
							<td style="text-align: center;width: 200px;">
								<a class="btn" href="javascript:void(0);" onclick="planInfo('${result.ORG_FLOW}','${result.ASSIGN_YEAR}');">详情</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:forEach items="${resultMapList}" var="result" varStatus="s">
					<c:if test="${result.ORG_NAME ne '东部战区总医院'}">
						<tr>
							<td>${result.ORG_CODE}</td>
							<td style="text-align: center;width: 120px;">${result.ASSIGN_YEAR}</td>
							<td style="text-align: center;">${result.ORG_NAME}</td>
							<td style="text-align: center;">${result.ORG_TYPE}</td>
							<td style="text-align: center;width: 150px;">${result.ASSIGN_PLAN_SUM}</td>
							<td style="text-align: center;width: 200px;">
								<a class="btn" href="javascript:void(0);" onclick="planInfo('${result.ORG_FLOW}','${result.ASSIGN_YEAR}');">详情</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${empty resultMapList}">
					<tr>
						<td colspan="6">无记录</td>
					</tr>
				</c:if>
			</table>
        </div>
		<div class="page" style="text-align: center;">
	  		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		  	<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
		  	<pd:pagination-jsres toPage="toPage"/>
		</div>
	</div>

