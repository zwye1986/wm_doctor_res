<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
</script>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>

					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
						<col width="7%"/>
						<col width="8%"/>
						<col width="4%"/>
						<col width="7%"/>
						<col width="10%"/>
						<col width="10%" />
						<col width="8%"/>
						<col width="3%"/>
						<col width="10%"/>
						<col width="14%"/>
					</c:if>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
						<col width="7%"/>
						<col width="8%"/>
						<col width="4%"/>
						<col width="7%"/>
						<col width="10%"/>
						<col width="10%" />
						<col width="8%"/>
						<col width="3%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
					</c:if>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<col width="7%"/>
						<col width="6%"/>
						<col width="4%"/>
						<col width="7%"/>
						<col width="7%"/>
						<col width="10%" />
						<col width="8%"/>
						<col width="3%"/>
						<col width="7%"/>
						<col width="7%"/>
						<col width="10%"/>
					</c:if>

	        	</colgroup>
            <tr>
                <th>姓名</th>
                <th>证件号</th>
                <th>性别</th>
                <th>联系<br>方式</th>
                <th>培训<br>类别</th>
                <th>培训<br>基地</th>
                <th>培训<br>专业</th>
                <th>届别</th>
                <th>培养<br>年限</th>
				<th>${scoreYear}年<br>理论成绩</th>
				<th>${scoreYear}年<br>技能成绩</th>
            </tr>
             <c:forEach items="${doctorList}" var="doctor">
	             <tr>
	                <td>${doctor.sysUser.userName}</td>
	                <td>${doctor.sysUser.idNo}</td>
	                <td>${doctor.sysUser.sexName}</td>
	                <td>${doctor.sysUser.userPhone}</td>
	                <td>${doctor.catSpeName}</td>
	                <td>${doctor.orgName}</td>
	                <td>${doctor.speName}</td>
	                <td>${doctor.sessionNumber}</td>
	                <td>
	                  <c:set var="year" value="${doctor.trainYear}"/>  
	    				<c:forEach items="${jsResTrainYearEnumList}" var="dict">
	                		<c:if test="${dict.id eq year}">
	                		${dict.name}
	                		</c:if>
	    				</c:forEach>            
	                </td>
					 <td>
						<input style="width: 30px;" class="inputText theoryScore" readOnly="true"  value="${empty doctor.resScore.theoryScore ? '' : (doctor.resScore.theoryScore eq 0 ? '否' : '是')}" > </input>
					 </td>
					 <td>
						 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">
							 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${scoreYear}');" >是</a>
						 </c:if>
						 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">
							 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${scoreYear}');" >否</a>
						 </c:if>
					 </td>
	            </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="12" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
