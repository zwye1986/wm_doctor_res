<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function getPlanList(assignYear){
		planList(assignYear);
	}
	
	$(function(){
		var li = $("#${param.assignYear}");
		if(li.length>0){
			$(".tab_select").toggleClass("tab");
			$(".tab_select").toggleClass("tab_select");
			$(li).toggleClass("tab_select");
			$(li).toggleClass("tab");
		}

		$(".leftNum").each(function(){
			var all = $(this).prev().prev().text();
			if(all){
				var a1 = $(this).prev().text();
				var left = parseInt(all)-parseInt(a1);
				$(this).text(left);
			}
		})
	});
	
	function showMsg(orgFlow) {
		var url = "<s:url value='/sczyres/manage/showDocRecruit'/>?orgFlow="+orgFlow+"&assignYear=${param.assignYear}";
		jboxLoad("content", url, true);
	}
	function planInfo(orgFlow) {
		var data={
				"assignYear":"${sysCfgMap['res_reg_year']}",
				"orgFlow":orgFlow
			};
		jboxPostLoad("content","<s:url value='/sczyres/manage/planInfo'/>?source=${param.source}",data,true);
	}
</script>
      <div class="main_hd">
        <h2>
       		 住培招录计划
        </h2>
        <div class="title_tab" id="toptab">
          <ul>
            <%--<li class="tab_select" onclick="getPlanList('${sysCfgMap['res_reg_year']}');"><a>${sysCfgMap['res_reg_year']}</a></li>--%>
			  &#12288;&#12288;&#12288;年&#12288;&#12288;份：
			  <select name="sessionNumber" class="select" style="width: 126px;" onchange="getPlanList(this.value)">
				  <option value="">全部</option>
				  <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
					  <option value="${dict.dictName}" ${param.assignYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
				  </c:forEach>
			  </select>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0">
        <div class="div_table" style="padding-top: 20px;">
        	<div style="float: right;font-size: 16px;padding-bottom: 10px;">
	       	   计划招录合计：${totalNum} 
   			</div>
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th style="width: 120px;">基地编号</th>
              <th style="width: 200px;">基地名称</th>
              <th >指标总数</th>
              <th >指标录取数</th>
              <th >指标剩余数</th>
              <th >录取总数</th>
              <th >详情</th>
            </tr>
            <c:forEach items="${orgList}" var="org">
            <c:if test="${GlobalConstant.USER_LIST_LOCAL !=param.source or org.orgFlow eq param.orgFlow }">
            	<tr>
            		<td>${org.orgCode}</td>
            		<td>${org.orgName}</td>
            		<td style="width: 120px;">${speAssignNumMap[org.orgFlow] }</td>
            		<td style="width: 120px;">${recruitCountMap[org.orgFlow] }</td>
            		<td style="width: 120px;" class="leftNum"></td>
            		<td style="width: 120px;">${recruitCountAllMap[org.orgFlow] }</td>
            		<td style="width: 200px;">
            		<a class="btn" onclick="showMsg('${org.orgFlow}')">详情</a>
            		<%--<a class="btn" onclick="planInfo('${org.orgFlow}')">计划招录</a>--%>
            		</td>
            	</tr>
            </c:if>
            </c:forEach>
            <c:if test="${empty orgList}">
            	<tr>
            		<td colspan="4">无记录</td>
            	</tr>
            </c:if>
          </table>
        </div>
      </div>
