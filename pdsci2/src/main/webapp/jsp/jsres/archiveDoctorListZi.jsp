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
	        		<col width="7%"/>
	        		<col width="10%"/>
	        		<col width="6%"/>
	        		<col width="8%"/>
	        		<col width="11%"/>
	        		<col width="11%" />
	        		<col width="11%"/>
	        		<col width="12%"/>
	        		<col width="10%"/>
	        		<col width="14%"/>
	        	</colgroup>
            <tr>
                <th>姓名</th>
                <th>证件号</th>
                <th>性别</th>
                <th>联系方式</th>
                <th>培训类别</th>
                
                <th>培训基地</th>
                <th>培训专业</th>
                <th>年级</th>
                <th>培训年限</th>
                <th>操作</th>
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
	          		<td style="text-align: center;">
	          		<div style="width: 100%;">
		          		<span>
		          			<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}','${doctor.archiveFlow}');">详情
							</a>
						</span>
					</div>
	          		</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
