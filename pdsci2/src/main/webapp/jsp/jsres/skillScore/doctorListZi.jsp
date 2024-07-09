<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
</script>
    <div class="search_table" style="padding: 0;width: 95%;margin: 5px auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="12%"/>
				<col width="12%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="11%"/>
				<col width="10%" />
				<col width="12%"/>
				<col width="9%"/>
				<col width="9%"/>
			</colgroup>
            <tr>
				<th>姓名</th>
				<th>考试编号</th>
				<th>证件号</th>
				<th>年级</th>
				<th>地市</th>
				<th>培训类别</th>
				<th>培训基地</th>
				<th>培训专业</th>
				<th>是否合格</th>
			</tr>
             <c:forEach items="${doctorList}" var="doctor">
				 <tr>
				 <td>${doctor.sysUser.userName}</td>
				 <td>${doctor.skillTestId}</td>
				 <td>${doctor.sysUser.idNo}</td>
				 <td>${doctor.sessionNumber}</td>
				 <td>${doctor.placeName}</td>
				 <td>${doctor.catSpeName}</td>
				 <td>${doctor.orgName}</td>
				 <td>${doctor.speName}</td>
				 <td >
						 ${doctor.resScore.skillScore eq '1'?'是':(doctor.resScore.skillScore eq '0'?'否':(doctor.resScore.skillScore eq '2'?'缺考':'-'))}
				 </td>
				 </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="18" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="width: 906px;margin: 15px auto;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
