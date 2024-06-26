<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
function getInfo(doctorFlow){
	jboxOpen("<s:url value='/sczyres/doctor/getsingupinfofordialog'/>?userFlow="+doctorFlow,"人员信息",1000,550);
}
</script>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
	        		<col width="7%"/>
	        		<col width="15%"/>
	        		<col width="5%"/>
	        		<col width="9%"/>
	        		<col width="10%"/>
	        		
	        		<col width="12%" />
	        		<col width="20%"/>
	        		<col width="5%"/>
	        		<col width="9%"/>
	        		<col width="8%"/>
	        	</colgroup>
            <tr>
                <th>姓名</th>
                <th>证件号</th>
                <th>性别</th>
                <th>联系方式</th>
                <th>培训专业</th>
                <th>对应专业</th>
                <th>培训基地</th>
                <th>年级</th>
                <th>培养年限</th>
                <th style="min-width: 90px;">操作</th>
            </tr>
             <c:forEach items="${doctorList}" var="doctor">
	             <tr>
	                <td>${doctor.USER_NAME}</td>
	                <td>${doctor.ID_NO}</td>
	                <td>${doctor.SEX_NAME}</td>
	                <td>${doctor.USER_PHONE}</td>
	                <td>${doctor.CAT_SPE_NAME}</td>
	                <td>${doctor.SPE_NAME}</td>
	                <td>${doctor.ORG_NAME}</td>
	                <td>${doctor.SESSION_NUMBER}</td>
	                <td>${doctor.TRAINING_YEARS}</td>
	          		<td style="text-align: center;">
	          		<div style="width: 100%;float:left;">
		          		<span>
		          			<a class="btn show" onclick="getInfo('${doctor.DOCTOR_FLOW}')">详情
		          			<%--<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL ||sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL}"> --%>
			          			<%--<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">--%>
									<%--<div style="display: none;width: 70px;" class="info">--%>
										<%--<table class="grid" style="background: white;margin-left:41px;margin-top:-11px;">--%>
				              			 	<%--<tr>--%>
				              			 		<%--<td>--%>
									          		<%--<a onclick="updateDoctorTrend('${doctor.recruitFlow}','${doctor.doctorFlow}');">更新</a>--%>
								          		<%--</td>--%>
								          	<%--</tr>--%>
			                			<%--</table>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</c:if>--%>
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
      
