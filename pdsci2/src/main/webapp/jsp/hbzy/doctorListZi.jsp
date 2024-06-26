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
                
                <th>培训基地</th>
                <th>对应专业</th>
                <th>年级</th>
                <th>培养年限</th>
                <th style="min-width: 90px;">操作</th>
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
	    				<c:forEach items="${jszyResTrainYearEnumList}" var="dict">
	                		<c:if test="${dict.id eq year}">
	                		${dict.name}
	                		</c:if>
	    				</c:forEach>            
	                </td>
	          		<td style="text-align: center;">
	          		<div style="width: 100%;float:left;">
		          		<span>
		          			<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}','${zlFlag}');">详情
		          			<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL ||sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL && zlFlag != GlobalConstant.FLAG_Y}">
			          			<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
									<div style="display: none;width: 70px;" class="info">
										<table class="grid" style="background: white;margin-left:41px;margin-top:-11px;">
				              			 	<tr>
				              			 		<td>
									          		<a onclick="updateDoctorTrend('${doctor.recruitFlow}','${doctor.doctorFlow}');">更新</a>
								          		</td>
								          	</tr>
			                			</table>
									</div>
								</div>
							</c:if>
							</a>
							<!-- 培训基地 -->
							
				          	<%--<a class="btn"  onclick="InformationQuery('${doctor.doctorFlow}','${param.roleFlag}');" style="margin-top: 5px;margin-bottom: 5px;">培训信息</a>--%>
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
      
