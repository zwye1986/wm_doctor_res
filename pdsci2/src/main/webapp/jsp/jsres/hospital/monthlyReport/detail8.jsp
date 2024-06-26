
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>科室</th>
                <th>带教老师</th>
                <th>用户名</th>
                <th>评价均分</th>
                <th>教学活动开展数</th>
                <th>带教人数</th>
                <th>需审核数</th>
                <th>已审核数</th>
                <th>审核比例</th>
            </tr>
             <c:forEach items="${resultMapList}" var="result">
	             <tr>
	                <td>${result.ORG_NAME}</td>
	                <td>${result.SCH_DEPT_NAME}</td>
	                <td>${result.TEACHER_USER_NAME}</td>
	                <td>${result.USER_CODE}</td>
	                <td>${result.TEACHER_SCORE}</td>
	                <td>${result.ACTIVITY_NUM}</td>
	                <td>${result.DOCTOR_NUM}</td>
	                <td>${result.REC_NUM}</td>
	                <td>${result.AUDIT_NUM}</td>
	                <td>${result.percent}</td>
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
      
