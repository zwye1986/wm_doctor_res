<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	<%--$(function(){--%>
		<%--var li = $("#${param.assignYear}");--%>
		<%--if(li.length>0){--%>
			<%--$(".tab_select").toggleClass("tab");--%>
			<%--$(".tab_select").toggleClass("tab_select");--%>
			<%--$(li).toggleClass("tab_select");--%>
			<%--$(li).toggleClass("tab");--%>
		<%--}--%>
	<%--});--%>
	<%----%>
		function showMsg(orgFlow) {
		var url = "<s:url value='/sczyres/manage/graduationListOrg'/>?orgFlow="+orgFlow+"&role=charge";
		jboxLoad("content", url, true);
	}
	<%--function planInfo(orgFlow) {--%>
		<%--var data={--%>
				<%--"assignYear":"${sysCfgMap['res_reg_year']}",--%>
				<%--"orgFlow":orgFlow--%>
			<%--};--%>
		<%--jboxPostLoad("content","<s:url value='/sczyres/manage/planInfo'/>?source=${param.source}",data,true);--%>
	<%--}--%>
</script>
      <div class="main_hd">
        <h2>
       		 结业统计
        </h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li class="tab_select" onclick="graduationStatistics('${sysCfgMap['res_graduation_year']}');"><a>${sysCfgMap['res_graduation_year']}</a></li>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0">
        <div class="div_table" style="padding-top: 20px;">
        	<div style="float: right;font-size: 16px;padding-bottom: 10px;">
	       	   合计：${totalNum}
   			</div>
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th style="text-align: left;padding-left: 40px;">基地编号</th>
              <th style="text-align: left;padding-left: 40px;">基地名称</th>
              <th >结业学员总数</th>
              <th style="width: 150px;">详情</th>
            </tr>
            <c:forEach items="${resultMapList}" var="result">
            	<tr>
            		<td style="text-align: left;padding-left: 40px;width: 120px;">${result.ORG_CODE}</td>
            		<td style="text-align: left;padding-left: 20px;">${result.ORG_NAME}</td>
            		<td style="width: 120px;">${result.ORG_SUM}</td>
            		<td style="width: 200px;">
            		<a class="btn" onclick="showMsg('${result.ORG_FLOW}')">详情</a>
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
      </div>
