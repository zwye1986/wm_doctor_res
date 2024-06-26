
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>科室名称</th>
                <th>入科人数</th>
                <th>出科人数</th>
                <th>出科异常人数</th>
            </tr>
             <c:forEach items="${resultMapList}" var="result">
	             <tr>
	                <td>${result.ORG_NAME}</td>
	                <td>${result.SCH_DEPT_NAME}</td>
	                <td>${result.INNUM}</td>
	                <td>${result.OUTNUM}</td>
	                <td>${result.ERRORNUM}</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
