
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>学员姓名</th>
                <th>年级</th>
                <th>专业</th>
                <th>轮转科室</th>
                <th>轮转时间</th>
                <th>带教老师</th>
                <th>科主任</th>
                <th>教学活动</th>
                <th>填写数</th>
                <th>已审核数</th>
                <th>轮转状态</th>
            </tr>
             <c:forEach items="${resultMapList}" var="result">
	             <tr>
	                <td>${result.ORG_NAME}</td>
	                <td>${result.DOCTOR_NAME}</td>
	                <td>${result.SESSION_NUMBER}</td>
	                <td>${result.TRAINING_SPE_NAME}</td>
	                <td>${result.SCH_DEPT_NAME}</td>
	                <td>${result.SCH_START_DATE}-${result.SCH_END_DATE}</td>
	                <td>${result.TEACHER_USER_NAME}</td>
	                <td>${result.HEAD_USER_NAME}</td>
	                <td>${result.ACTIVITY_NUM}</td>
	                <td>${result.REC_NUM}</td>
	                <td>${result.AUDIT_NUM}</td>
	                <td>${result.SCH_STATUS_NAME}</td>
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
      
