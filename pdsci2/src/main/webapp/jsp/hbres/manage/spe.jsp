<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
    function toPage(page){
        if(page){
            jboxLoad("content","<s:url value='/hbres/singup/spe'/>?currentPage="+page,true);
        }
    }
</script>
      <div class="main_hd">
        <h2>专业维护</h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li class="tab_select"><a>培训专业</a></li>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0" >
        <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th  style="text-align: center;">序号</th>
              <th  style="text-align: center;">专业代码</th>
              <th  style="text-align: center;">基地专业名称</th>
            </tr>
            <c:forEach items="${doctorTrainingSpeList}" var="dict" varStatus="s">
            <tr>
              <td style="text-align: center;">${s.index+1}</td>
              <td style="text-align: center;">${dict.dictId}</td>
              <td style="text-align: center;">${dict.dictName}</td>
            </tr>
            </c:forEach>
          </table>
      </div>
          <div class="page" style="text-align: center;">
              <input id="currentPage" type="hidden" name="currentPage" value=""/>
              <c:set var="pageView" value="${pdfn:getPageView(doctorTrainingSpeList)}" scope="request"></c:set>
              <pd:pagination-jsres toPage="toPage"/>
          </div>
      </div>
      
