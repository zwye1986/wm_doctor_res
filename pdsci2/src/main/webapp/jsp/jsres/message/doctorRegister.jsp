<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
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
	function getPlanList(assignYear, currentPage){
		if(!currentPage){
			currentPage = 1;
		}
		jboxLoad("content","<s:url value='/jsres/message/doctorRegister'/>?assignYear="+assignYear + "&currentPage=" + currentPage,true);
	}
	$(function(){
		var li = $("#${param.assignYear}");
		if(li.length>0){
			$(".tab_select").toggleClass("tab");
			$(".tab_select").toggleClass("tab_select");
			$(li).toggleClass("tab_select");
			$(li).toggleClass("tab");
		}
	});
	// 点击报到按钮 进行报到确认操作  根据招录流水号来操作
	function doRegister(recruitFlow) {
		var data = {};
		data.recruitFlow = recruitFlow;
		jboxPost("<s:url value='/jsres/message/doRegister'/>",data,function(resp){
			jboxTip(resp);
			jboxEndLoading();
			//重新加载一次列表请求，刷新列表
			jboxLoad("content", "<s:url value='/jsres/message/doctorRegister'/>", true);
		},null,false);
	}
	// 查看报考详细信息
	function detailRegister(recruitFlow) {
		<%--var data = {};--%>
		<%--jboxOpen("<s:url value='/jsres/message/detailRegister?recruitFlow='/>"+recruitFlow,msg, 900, 500);--%>
		var url= "<s:url value='/jsres/message/detailRegister'/>?recruitFlow=" + recruitFlow;
		var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='600px' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='" + url + "'></iframe>";
		jboxMessager(iframe, '查看报考详细信息', 800, 600);
	}
	//打印
	function printDoc(doctorFlow,recruitFlow,orgName)
	{
		var url = "<s:url value='/jsres/message/printDoc?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" +
				doctorFlow +"&recruitFlow="+recruitFlow+"&orgName="+orgName;
		var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='800px' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='" + url + "'></iframe>";
		jboxMessager(iframe, '打印个人信息', 900, 800);
		// document.getElementById('printDivIframe').src=url;
	}
</script>

<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
<div class="main_hd">
	<h2 class="underline">
		报考记录
	</h2>
</div>
<div class="main_bd" id="div_table_0">
	<div class="div_search" style="padding: 0px 40px;">
		<form id="searchForm" method="post">
			<table style="border-collapse:separate; border-spacing:0px 20px;">
				<tr>
					<td class="td_right">年&#12288;&#12288;份：</td>
					<td class="td_left">
						<input class="input" name="assignYear" id="assignYear" readonly="readonly" value="${param.assignYear}" type="text" onchange="getPlanList(this.value, 1)"/>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div class="div_table" style="padding-top: 20px;">
	  <table border="0" cellpadding="0" cellspacing="0" class="grid">
		  <colgroup>
			  <col width="5%"/>
			  <col width="8%"/>
			  <col width="7%"/>
			  <col width="15%"/>
			  <col width="10%"/>
			  <col width="8%"/>
			  <col width="8%"/>
			  <col width="8%"/>
			  <col width="8%"/>
			  <col width="10%"/>
			  <col width="10%"/>
		  </colgroup>
		<tr>
			<th>序号</th>
			<th style="text-align: center;width: 120px;">姓名</th>
			<th style="text-align: center;width: 120px;">年份</th>
			<th style="text-align: center;">基地名称</th>
			<th style="text-align: center;">报考专业</th>
			<th style="text-align: center;width: 120px;">笔试成绩</th>
			<th style="text-align: center;width: 120px;">面试成绩</th>
			<th style="text-align: center;width: 120px;">操作技能</th>
			<th style="text-align: center;width: 150px;">审核状态</th>
			<th style="text-align: center;width: 150px;">报到状态</th>
			<th style="text-align: center;width: 200px;">详情</th>
		</tr>
		<c:forEach items="${recruitList}" var="result" varStatus="s">
			<tr>
				<td>${s.index+1}</td>
				<td style="text-align: center;width: 120px;">${userName}</td>
				<td style="text-align: center;width: 120px;">${result.recruitYear}</td>
				<td style="text-align: center;width: 120px;">${result.orgName}</td>
				<td style="text-align: center;width: 120px;">${result.speName}</td>
				<td style="text-align: center;">${result.examResult}</td>
				<td style="text-align: center;">${result.auditionResult}</td>
				<td style="text-align: center;">${result.operResult}</td>
				<td style="text-align: center;">
					<c:if test="${jsres_is_train eq 'N' and result.auditStatusId eq 'WaitGlobalPass'}">
						基地审核通过
					</c:if>
					<c:if test="${jsres_is_train ne 'N'  or result.auditStatusId  ne 'WaitGlobalPass'}">
						${result.auditStatusName}
					</c:if>
				</td>
				<td style="text-align: center;width: 150px;">
					<c:if test="${result.confirmFlag ne 'Y'}">
						<span>未报到</span>
					</c:if>
					<c:if test="${result.confirmFlag eq 'Y'}">
						<span>已报到</span>
					</c:if>
				</td>
				<td style="text-align: center;width: 200px;">
					<a class="btn" href="javascript:void(0);" onclick="detailRegister('${result.recruitFlow}');">详情</a>
					<a class="btn" href="javascript:void(0);" onclick="printDoc('${currUser.userFlow}','${result.recruitFlow}','${result.orgName}');">打印</a>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty recruitList}">
			<tr>
				<td colspan="10">无记录</td>
			</tr>
		</c:if>
	  </table>
	</div>
	  <div class="page" style="text-align: center;">
		  <input id="currentPage" type="hidden" name="currentPage" value=""/>
		  <c:set var="pageView" value="${pdfn:getPageView(recruitList)}" scope="request"></c:set>
		  <pd:pagination-jsres toPage="toPage"/>
	  </div>
  </div>
      
