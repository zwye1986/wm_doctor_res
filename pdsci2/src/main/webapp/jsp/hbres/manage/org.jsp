<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function getOrgList(isY,id){
		org(isY,id);
	}
	
	$(function(){
		var li = $("#${param.id}");
		if(li.length>0){
			$(".tab_select").toggleClass("tab");
			$(".tab_select").toggleClass("tab_select");
			$(li).toggleClass("tab_select");
			$(li).toggleClass("tab");
		}
	});

    function toPage(page){
        if(page){
            jboxLoad("content","<s:url value='/hbres/singup/manage/orgCfg'/>?isCountry=${param.isCountry}&id=${param.id}&currentPage="+page,true);
        }
    }
</script>
      <div class="main_hd">
        <h2>基地维护</h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li id="all" class="tab_select" onclick="getOrgList('',this.id);"><a>全部</a></li>
            <li id="country" class="tab" onclick="getOrgList('${GlobalConstant.FLAG_Y}',this.id);"><a>国家基地</a></li>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0"  >
        <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th style="text-align: center;">序号</th>
              <th style="text-align: center;">基地编号</th>
              <th style="text-align: center;">基地名称</th>
              <th style="text-align: center;">类型</th>
            </tr>
            <c:forEach items="${orgList}" var="org" varStatus="s">
            	<tr>
            		<td style="text-align: center;">${s.index+1}</td>
            		<td style="text-align: center;">${org.orgCode}</td>
            		<td style="text-align: center;">${org.orgName}</td>
            		<td style="text-align: center;">${org.orgLevelName}</td>
            	</tr>
            </c:forEach>
            <c:if test="${empty orgList}">
            	<tr>
            		<td colspan="4">无记录</td>
            	</tr>
            </c:if>
          </table>
        </div>
          <div class="page" style="text-align: center;">
              <input id="currentPage" type="hidden" name="currentPage" value=""/>
              <c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"></c:set>
              <pd:pagination-jsres toPage="toPage"/>
          </div>
      </div>
      
