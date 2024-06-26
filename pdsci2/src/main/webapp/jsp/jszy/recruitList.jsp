	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
               	<th width="10%">序号</th>
                <th width="15%">姓名</th>
                <th width="20%">证件号</th>
                <th width="5%">性别</th>
                <th width="20%">培训专业</th>
                <th width="20%">对应专业</th>
<%--                 <c:if test="${param.source == 'hospital'}"> --%>
<!--                 	<th width="10%">审核类型</th> -->
<%--                 </c:if> --%>
				<c:if test="${sysCfgMap['jsres_is_hospital_audit'] != 'N'}">
	                <th width="10%">操作</th>
				</c:if>  
            </tr>
            <c:forEach items="${recruitList}" var="recruit" varStatus="a">
            	<tr>
<%-- 	             	<td><input type="checkbox" value="${recruit.doctorFlow}"></td> --%>
	               <td>${a.count}</td>
	                <td>${recruit.sysUser.userName}</td>
	                <td>${recruit.sysUser.idNo}</td>
	                <td>${recruit.sysUser.sexName}</td>
	                <td>${recruit.catSpeName}</td>
	                <td>${recruit.speName}</td>
	                <c:if test="${param.source == 'hospital'}">
	                	<td>培训信息</td>
	                </c:if>
	                <c:if test="${sysCfgMap['jsres_is_hospital_audit'] != 'N'}">
	                	<c:if test="${ recruit.sessionNumber eq sysCfgMap['jsres_local_sessionNumber'] }">
		                	<td><a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}');">审核</a></td>
						</c:if>
					</c:if>
					  <c:if test="${sysCfgMap['jsres_is_hospital_audit'] !=  'Y' or recruit.sessionNumber != sysCfgMap['jsres_local_sessionNumber']}">
	           		  	<td></td>
	           		  </c:if>
	            </tr>
            </c:forEach>
            <c:if test="${empty recruitList}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(recruitList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
