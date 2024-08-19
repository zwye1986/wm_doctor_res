<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
</script>
    <div class="search_table" >
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
						<col width="6%"/>
						<col width="11%"/>
						<col width="11%"/>
						<col width="13%"/>
						<col width="6%"/>
						<col width="5%"/>
						<col width="7%"/>
						<col width="6%"/>
						<col width="6%"/>
						<col width="6%"/>
						<col width="6%"/>
						<col width="6%"/>
						<col width="11%"/>
					</c:if>
					<c:if test="${ !(roleFlag eq GlobalConstant.USER_LIST_CHARGE)}">
						<col width="8%"/>
						<col width="5%"/>
						<col width="13%"/>
						<col width="9%"/>
						<col width="12%"/>
						<col width="8%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
					</c:if>
	        	</colgroup>
            <tr>
                <th>姓名</th>
				<th>证件号</th>
				<th>年级</th>
				<th>培训<br>类别</th>
                <th>培训<br>基地</th>
                <th>培训<br>专业</th>
				<th>卫生法律和法规</th>
				<th>循证<br>医学</th>
				<th>临床思维与人际沟通</th>
				<th>重点传染病防治知识</th>
				<th>公共科目考试</th>
			</tr>
             <c:forEach items="${doctorList}" var="doctor">
				 <c:set var="extScore" value="${extScoreMap[doctor.resScore.scoreFlow]}"></c:set>
				 <c:set var="lawScore" value="${extScore.lawScore}"></c:set>
				 <c:set var="medicineScore" value="${extScore.medicineScore}"></c:set>
				 <c:set var="clinicalScore" value="${extScore.clinicalScore}"></c:set>
				 <c:set var="ckScore" value="${extScore.ckScore}"></c:set>
					 <tr>
						 <td>${doctor.sysUser.userName}</td>
						 <td>${doctor.sysUser.idNo}</td>
						 <td>${doctor.sessionNumber}</td>
						 <td>${doctor.catSpeName}</td>
						 <td>${doctor.orgName}</td>
						 <td>${doctor.speName}</td>
						 <td>${lawScore}</td>
						 <td>${medicineScore}</td>
						 <td>${clinicalScore}</td>
						 <td>${ckScore}</td>

						 <td><c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">合格</c:if><c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if></td>
					 </tr>

            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="18" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
