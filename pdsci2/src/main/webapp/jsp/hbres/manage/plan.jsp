<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page){
		if(page){
			if('${fakeFlag}'=='Y'){
				fakePlanList('${param.assignYear}',page);
			}else {
				planList('${param.assignYear}',page);
			}
		}
	}

	function getPlanList(assignYear){
		if('${fakeFlag}'=='Y'){
			fakePlanList(assignYear,'');
		}else{
			planList(assignYear,'');
		}
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
	
	function planInfo(orgFlow,assignYear) {
		var data={
				"assignYear":assignYear,
				"orgFlow":orgFlow
			};
		jboxPostLoad("content","<s:url value='/hbres/singup/manage/planInfo'/>?source=${param.source}",data,true);
	}
	function fakePlanInfo(orgFlow,assignYear) {
		var data={
			"assignYear":assignYear,
			"orgFlow":orgFlow
		};
		jboxPostLoad("content","<s:url value='/hbres/singup/manage/fakePlanInfo'/>?source=${param.source}",data,true);
	}
	function impPlan(){
		jboxOpen("<s:url value='/jsp/hbres/manage/importPlan.jsp'/>", "招录计划导入", '600', '300');
	}
</script>

<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
      <div class="main_hd">
        <h2>
			住培<c:if test="${fakeFlag eq 'Y'}">公开</c:if>招录计划
        </h2>
        <div class="title_tab" id="toptab">
			&#12288;&#12288;&#12288;年&#12288;&#12288;份：
		  <select name="sessionNumber" class="select" style="width: 126px;" onchange="getPlanList(this.value)">
			  <option value="">全部</option>
			  <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
				  <option value="${dict.dictName}" ${param.assignYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
			  </c:forEach>
		  </select>
			  <%--&#12288;<font color="red">此表包含${sysCfgMap['res_reg_year']}年新进职工和行业人容量，不包含四证合一和特殊情况学员容量</font>--%>
        </div>
      </div>
      <div class="main_bd" id="div_table_0">
        <div class="div_table" style="padding-top: 20px;">
        	<div style="float: right;font-size: 16px;padding-bottom: 10px;">
	       	 	计划招录合计：${totalNum}人&#12288;
				<c:if test="${!isFree}">
					<c:if test="${GlobalConstant.USER_LIST_LOCAL != param.source}">
						<input type="button" value="招录计划导入" class="btn_green" onclick="impPlan();">
					</c:if>
				</c:if>
   			</div>
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th>序号</th>
              <th style="text-align: center;width: 120px;">年份</th>
              <th style="text-align: center;width: 120px;">基地编号</th>
              <th style="text-align: center;">基地名称</th>
              <th style="text-align: center;width: 150px;">计划招录总数</th>
              <th style="text-align: center;width: 200px;">详情</th>
            </tr>
            <c:forEach items="${resultMapList}" var="result" varStatus="s">
            	<tr>
            		<td>${s.index+1}</td>
            		<td style="text-align: center;width: 120px;">${result.ASSIGN_YEAR}</td>
            		<td style="text-align: center;width: 120px;">${result.ORG_CODE}</td>
            		<td style="text-align: center;">${result.ORG_NAME}</td>
            		<td style="text-align: center;width: 150px;">${result.ASSIGN_PLAN_SUM}</td>
            		<td style="text-align: center;width: 200px;">
						<c:if test="${fakeFlag ne 'Y'}">
            				<a class="btn" href="javascript:void(0);" onclick="planInfo('${result.ORG_FLOW}','${result.ASSIGN_YEAR}');">详情</a>
						</c:if>
						<c:if test="${fakeFlag eq 'Y'}">
							<a class="btn" href="javascript:void(0);" onclick="fakePlanInfo('${result.ORG_FLOW}','${result.ASSIGN_YEAR}');">公开详情</a>
						</c:if>
            		</td>
            	</tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
            	<tr>
            		<td colspan="10">无记录</td>
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
      
