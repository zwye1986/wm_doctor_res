<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	<%--function toPage(page) {--%>
		<%--if(page!=undefined){--%>
			<%--$("#currentPage").val(page);			--%>
		<%--}--%>
		<%--audit('${statusId}',$("#key").val() , $("#currentPage").val());--%>
	<%--} --%>

	function search(){
        jboxPostLoad("content", "<s:url value='/sczyres/manage/graduationListOrg'/>",$("#searchForm").serialize(), true);
	}

    function detail(doctorFlow){
        jboxOpen("<s:url value='/sczyres/hosptial/detail'/>?doctorFlow="+doctorFlow,"学员详情",1000,550);
    }

    <%--function exportExl(){--%>
        <%--var url = "<s:url value='/sczyres/manage/exportExl'/>";--%>
        <%--jboxTip("导出中…………");--%>
        <%--jboxExp($("#searchForm"),url);--%>
    <%--}--%>
</script>
      <div class="main_hd">
        <h2>基地结业人员情况表</h2>
      </div>
      <div class="main_bd" id="div_table_0" >
         <form id="searchForm">
         <input name="role" value="${param.role}" type="hidden">
      	<div class="div_search">
        <c:if test="${param.role eq 'charge'}">
            基地：
            <select name="orgFlow" class="select" >
                <option value="" >请选择</option>
                <c:forEach items="${hospitals}" var="hos">
                    <option value="${hos.orgFlow}" <c:if test='${param.orgFlow eq hos.orgFlow}'>selected="selected"</c:if>>${hos.orgName}</option>
                </c:forEach>
            </select>
            &#12288;
            <input type="button" value="查&#12288;询" class="btn_blue" onclick="search();">
        </c:if>
        </div>
         </form>
         <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th>序号</th>
              <th>姓名</th>
              <th>结业年份</th>
              <th>身份证</th>
                <c:if test="${param.role eq 'charge'}">
                    <th>培训基地</th>
                </c:if>
              <th>学历</th>
              <th>专业</th>
              <th>更多</th>
            </tr>
            <c:forEach items="${graduationApplies}" var="apply" varStatus="s">
            <tr>
              <td>${s.index+1}</td>
              <td>${apply.doctorName}</td>
              <td>${apply.graduationYear}</td>
              <td>${apply.idNo}</td>
                <c:if test="${param.role eq 'charge'}">
                    <td>${apply.orgName}</td>
                </c:if>
              <td>${apply.educationName}</td>
              <td>${apply.trainingSpeName}</td>
              <td><a onclick="detail('${apply.doctorFlow}')">详情</a></td>
              </td>
            </tr>
            </c:forEach>
            <c:if test="${empty graduationApplies}">
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
      
