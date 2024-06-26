
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>科室</th>
                <th>活动开展数</th>
                <th>活动参加数</th>
                <th>小讲课</th>
                <th>死亡病例讨论</th>
                <th>教学查房</th>
                <th>疑难病例讨论</th>
                <th>危重症病例讨论</th>
            </tr>
             <c:forEach items="${resultMapList}" var="result">
	             <tr>
	                <td>${result.ORG_NAME}</td>
	                <td>${result.DEPT_NAME}</td>
	                <td>${result.ACTIVTIY_NUM}</td>
	                <td>${result.JOIN_NUM}</td>
	                <td>${empty result.XJK?'0':result.XJK}</td>
	                <td>${empty result.SWBL?'0':result.SWBL}</td>
	                <td>${empty result.JXCF?'0':result.JXCF}</td>
	                <td>${empty result.YNBL?'0':result.YNBL}</td>
	                <td>${empty result.WZBL?'0':result.WZBL}</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
				<tr>
					<td colspan="20" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
