<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$("li").click(function(){
			$(".tab_select").addClass("tab");
			$(".tab_select").removeClass("tab_select");
			$(this).removeClass("tab");
			$(this).addClass("tab_select");
			var itemId = $(this).attr("itemId");
			search(itemId)
		});
		<c:if test="${empty param.regYear}">
		$("[name='sessionNumber'] option[value=${currentYear}]").attr("selected","selected");
		</c:if>
	});
	function search(itemId){
		jboxPostLoad("content","<s:url value='/hbres/singup/planStatistics'/>","itemId="+itemId+"&recruitYear="+$("#recruitYear").val(),true);
	}
	function exportStatistics(){
		location.href="<s:url value='/hbres/singup/exportStatistics?recruitYear='/>"+$("#recruitYear").val();
	}
	function toPage(page){
		if(page){
			var recruitYear = $("#recruitYear").val();
			if(!recruitYear){
				recruitYear='';
			}
			jboxPostLoad("content","<s:url value='/hbres/singup/planStatistics'/>","itemId=${param.itemId}&recruitYear="+recruitYear+"&currentPage="+page,true);
		}
	}
</script>

      <div class="main_hd">
        <h2>招录统计</h2>
        <div class="title_tab" id="toptab">
          <ul>
			  <li class="tab${param.itemId eq 'zltj'?'_select':''}" itemId="zltj"><a>招录信息统计</a></li>
			  <li class="tab${param.itemId eq 'zlxq'?'_select':''}" itemId="zlxq"><a>招录信息详情</a></li>
          </ul>
        </div>
      </div>
<div class="main_bd" >
	<div class="div_search">
		招录年份：
		<select name="sessionNumber" class="select" style="width: 126px;" onchange="search('${param.itemId}')" id="recruitYear" >
			<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
				<c:if test="${dict.dictName ge '2017'}">
					<option value="${dict.dictName}" ${param.recruitYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
				</c:if>
			</c:forEach>
		</select>
		<c:if test="${param.itemId eq 'zlxq'}">
			<input type="button" class="btn_green" value="导&#12288;出" onclick="exportStatistics()">
		</c:if>
	</div>
	<div class="div_table">
		<c:if test="${param.itemId eq 'zltj'}">
			<table class="grid">
				<tr>
					<td style="width:18%;text-align:left;padding-left:60px;">注册报名人数</td>
					<td style="width:32%;">${planMap.ZCBM_NUM}</td>
					<td style="width:60%;text-align:left;">说明：指系统内注册并提交报名信息学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">审核通过人数</td>
					<td>${planMap.SHTG_NUM}</td>
					<td style="text-align:left;">说明：指省厅审核通过的报名学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">参加笔试人数</td>
					<td>${planMap.CJBS_NUM}</td>
					<td style="text-align:left;">说明：指需选择考点参加笔试的学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">笔试通过人数</td>
					<td>${planMap.BSTG_NUM}</td>
					<td style="text-align:left;">说明：指满足填报志愿条件的学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">填报志愿人数</td>
					<td>${planMap.TBZY_NUM}</td>
					<td style="text-align:left;">说明：指实际已填报志愿的学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">未填报志愿人数</td>
					<td>${planMap.BSTG_NUM-planMap.TBZY_NUM}</td>
					<td style="text-align:left;">说明：笔试通过人数-填报志愿人数</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">复试通知人数</td>
					<td>${planMap.FSTZ_NUM}</td>
					<td style="text-align:left;">说明：指培训基地已发布的复试通知的学员数量</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">录取人数</td>
					<td>${planMap.LQ_NUM}</td>
					<td style="text-align:left;">说明：指学员最终确认录取的人数+省厅调剂且培训基地确认录取</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">省厅调剂人数</td>
					<td>${planMap.STTJ_NUM}</td>
					<td style="text-align:left;">说明：指省厅操作调剂到培训基地学员</td>
				</tr>
				<tr>
					<td style="text-align:left;padding-left:60px;">确认报到人数</td>
					<td>${planMap.REGISTER_NUM}</td>
					<td style="text-align:left;">说明：指基地最终确认报到的学员人数</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${param.itemId eq 'zlxq'}">
			<table class="grid">
				<tr>
					<th>培训基地代码</th>
					<th>培训基地名称</th>
					<th>计划招收人数</th>
					<th>填报志愿人数</th>
					<th>通知复试人数</th>
					<th>确认录取人数</th>
					<th>省厅调剂人数</th>
					<th>确认报到人数</th>
				</tr>
				<c:forEach items="${orgMap}" var="org">
					<tr>
						<td>${org.ORG_CODE}</td>
						<td>${org.ORG_NAME}</td>
						<td>${org.ASSIGN_PLAN}</td>
						<td>${org.TBZY_NUM}</td>
						<td>${org.TZFS_NUM}</td>
						<td>${org.QRLQ_NUM}</td>
						<td>${org.STTJ_NUM}</td>
						<td>${org.REGISTER_NUM}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<div class="page" style="text-align: center;">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		<c:set var="pageView" value="${pdfn:getPageView(orgMap)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>