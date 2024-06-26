<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
	function search(){
        jboxPostLoad("content", "<s:url value='/sczyres/manage/ticket'/>",$("#searchForm").serialize(), true);
	}
    function ticketDetail(recordFlow,doctorFlow,applyFlow){
        jboxOpen("<s:url value='/sczyres/manage/ticketDetail'/>?recordFlow="+recordFlow+"&userFlow="+doctorFlow+"&applyFlow="+applyFlow,"详情",1000,500);
    }
    function openPrint(flag){
        jboxPost("<s:url value='/sczyres/manage/openPrint'/>?flag="+flag,null,function(resp){
           if(resp==1){
               jboxTip(flag=='Y'?'已开启打印准考证':'已关闭打印准考证');
           }
        },null,false);
    }
    function importTickets(){
        jboxOpen("<s:url value='/jsp/sczyres/manage/importTickets.jsp'/>", "准考证导入", '600', '300');
    }
</script>
      <div class="main_hd">
        <h2>准考证管理列表</h2>
      </div>
      <div class="main_bd" id="div_table_0" >
         <form id="searchForm">
      	<div class="div_search">
            姓名：
            <input name="doctorName" class="input" value="${param.doctorName}" type="text"/>
            基地：
            <select name="orgFlow" class="select" >
                <option value="" >请选择</option>
                <c:forEach items="${hospitals}" var="hos">
                    <option value="${hos.orgFlow}" <c:if test='${param.orgFlow eq hos.orgFlow}'>selected="selected"</c:if>>${hos.orgName}</option>
                </c:forEach>
            </select>
            &#12288;
            <input type="button" value="查&#12288;询" class="btn_blue" onclick="search();">
            &#12288;
            <input type="button" value="导&#12288;入" class="btn_blue" onclick="importTickets();">
            <br/>
            <label><input type="radio" value="Y" name="a" ${cfg.cfgValue eq 'Y'?'checked':''} onchange="openPrint('Y')">开启学员打印</label>
            <label><input type="radio" value="N" name="a" ${cfg.cfgValue eq 'N'?'checked':''} onchange="openPrint('N')">关闭学员打印</label>
        </div>
         </form>
         <div class="div_table" style="padding-top: 0">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th>序号</th>
              <th>姓名</th>
              <th>结业年份</th>
              <th>身份证</th>
              <th>准考证</th>
              <th>培训基地</th>
              <th>更多</th>
            </tr>
            <c:forEach items="${resultMapList}" var="result" varStatus="s">
            <tr>
              <td>${s.index+1}</td>
              <td>${result.DOCTOR_NAME}</td>
              <td>${result.GRADUATION_YEAR}</td>
              <td>${result.ID_NO}</td>
              <td>${empty result.TICKET_NUMBER?'未分配':result.TICKET_NUMBER}</td>
              <td>${result.ORG_NAME}</td>
              <td><a onclick="ticketDetail('${result.RECORD_FLOW}','${result.DOCTOR_FLOW}','${result.APPLY_FLOW}')">详情</a></td>
              </td>
            </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
              <tr>
                  <td colspan="20">暂无记录</td>
              </tr>
            </c:if>
          </table>
        </div>
        <%--<div class="page" style="padding-right: 40px;">--%>
       	 <%--<input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>--%>
           <%--<c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>--%>
	  		 <%--<pd:pagination-sczyres toPage="toPage"/>	 --%>
        <%--</div>--%>
      </div>
      
