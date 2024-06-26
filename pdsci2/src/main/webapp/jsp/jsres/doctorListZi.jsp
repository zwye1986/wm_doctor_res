<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
function doctorInfo(doctorFlow){
	var url = "<s:url value='/jsres/doctor/editDoctorInfo?viewFlag=Y&openType=open&userFlow='/>"+ doctorFlow+"&hideApprove=hideApprove&currentPage=${param.currentPage}";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'学员信息', 1050, 600, null, true);
}
function responsibleTeacherCfg(doctorFlow) {
	jboxOpen("<s:url value='/res/responsibleTeacher/responsibleTeacherCfg'/>?doctorFlow=" + doctorFlow + "&currentPage=${currentPage}", "责任导师", 900, 400, false);
}
</script>
<style>
	.searchTable .td_left {
		word-wrap: break-word;
		width: 6em;
		height: auto;
		line-height: auto;
		text-align: left;
	}
</style>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
				<col width="10%" />
				<col width="7%" />
				<col width="9%" />
				<col width="19%" />
				<col width="12%" />
				<col width="9%" />
				<col width="9%" />
				<%--<col width="10%" />--%>
				<c:if test="${not empty sessionScope.userListScope  && sessionScope.userListScope eq 'university'}">
					<col width="15%" />
				</c:if>
				<col width="20%" />
	        	</colgroup>
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>培训类别</th>
                <th>培训基地</th>
                <th>培训专业</th>
                <th>年级</th>
                <th>培训年限</th>
				<th>是否重培</th>
				<c:if test="${not empty sessionScope.userListScope  && sessionScope.userListScope eq 'university'}">
					<th>院校</th>
				</c:if>
				<%--<th>责任老师</th>--%>
                <th>操作</th>
            </tr>
             <c:forEach items="${doctorList}" var="doctor">
	             <tr>
	                <td title="手机号码：${doctor.sysUser.userPhone}">${doctor.sysUser.userName}</td>
	                <td>${doctor.sysUser.sexName}</td>
	                <td>${doctor.catSpeName}</td>
					<td>
						<c:if test="${empty doctor.jointOrgFlow}">${doctor.orgName}</c:if>
						<c:if test="${!empty doctor.jointOrgFlow}">${doctor.jointOrgName}</c:if>
					</td>
	                <%--<td>${doctor.orgName}</td>--%>
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
						 <c:if test="${doctor.isRetrain eq 'Y'}">是</c:if>
						 <c:if test="${doctor.isRetrain eq 'N'}">否</c:if>
					 </td>
					 <c:if test="${not empty sessionScope.userListScope  && sessionScope.userListScope eq 'university'}">
						 <td>
							 ${doctor.resDoctor.school}
						 </td>
					 </c:if>
					 <%--<td>--%>
						 <%--<c:choose>--%>
							 <%--<c:when test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">--%>
								 <%--<a style="color: blue;cursor: pointer" onclick="responsibleTeacherCfg('${doctor.doctorFlow}')">--%>
									 <%--<c:if test="${ empty teaMap[doctor.doctorFlow]}">请选择</c:if>--%>
									 <%--<c:forEach items="${teaMap[doctor.doctorFlow]}" var="item" varStatus="s">--%>
										 <%--<c:if test="${s.index+1 <  teaMap[doctor.doctorFlow].size()}">--%>
											 <%--${item.responsibleteacherName},--%>
										 <%--</c:if>--%>
										 <%--<c:if test="${s.index+1 ==  teaMap[doctor.doctorFlow].size()}">--%>
											 <%--${item.responsibleteacherName}--%>
										 <%--</c:if>--%>
									 <%--</c:forEach>--%>
								 <%--</a>--%>
							 <%--</c:when>--%>
							 <%--<c:otherwise>--%>
								 <%--<c:if test="${ empty teaMap[doctor.doctorFlow]}">暂无</c:if>--%>
								 <%--<c:forEach items="${teaMap[doctor.doctorFlow]}" var="item" varStatus="s">--%>
									 <%--<c:if test="${s.index+1 <  teaMap[doctor.doctorFlow].size()}">--%>
										 <%--${item.responsibleteacherName},--%>
									 <%--</c:if>--%>
									 <%--<c:if test="${s.index+1 ==  teaMap[doctor.doctorFlow].size()}">--%>
										 <%--${item.responsibleteacherName}--%>
									 <%--</c:if>--%>
								 <%--</c:forEach>--%>
							 <%--</c:otherwise>--%>
						 <%--</c:choose>--%>
					 <%--</td>--%>
	          		<td style="text-align: left;">
<%--						<div style="width: 100%;float:left;">--%>
<%--								<span>--%>
<%--									<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}');">详情--%>
<%--									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL ||sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL}">--%>
<%--										<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">--%>
<%--											<div style="display: none;width: 70px;" class="info">--%>
<%--												<table class="grid" style="background: white;margin-left:41px;margin-top:-11px;">--%>
<%--													<tr>--%>
<%--														<td>--%>
<%--															<a onclick="updateDoctorTrend('${doctor.recruitFlow}','${doctor.doctorFlow}');">更新</a>--%>
<%--														</td>--%>
<%--													</tr>--%>
<%--												</table>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--									</c:if>--%>
<%--									</a>--%>
<%--									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL && doctor.orgFlow eq mainOrgFlow}" >--%>
<%--										<a class="btn" onclick="doctorInfo('${doctor.doctorFlow}');">编辑</a>--%>
<%--									</c:if>--%>
<%--									<!-- 培训基地 -->--%>

<%--									<a class="btn"  onclick="InformationQuery('${doctor.doctorFlow}','${param.roleFlag}','${yearStr}');" style="margin-top: 5px;margin-bottom: 5px;white-space:nowrap;">培训信息</a>--%>
<%--								</span>--%>
<%--						</div>--%>
	 					<!-- 禅道需求2958 2019年及以前届别基地无操作按钮权限 -->
						<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL}">
							<div style="width: 100%;float:left;">
								<span>
									<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}');">详&#12288;&#12288;情
									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL}">
										<a class="btn"  onclick="updateDoctorTrend('${doctor.recruitFlow}','${doctor.doctorFlow}');">更&#12288;&#12288;新</a>
									</c:if>
									</a>
									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL && doctor.orgFlow eq mainOrgFlow}" >
										<a class="btn" onclick="doctorInfo('${doctor.doctorFlow}');">编&#12288;&#12288;辑</a>
									</c:if>
									<!-- 培训基地 -->

									<a class="btn"  onclick="InformationQuery('${doctor.doctorFlow}','${param.roleFlag}','${yearStr}');" style="white-space:nowrap;">培训信息</a>
								</span>
							</div>
						</c:if>
						<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">
							<div style="width: 100%;float:left;">
								<span>
									<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}');">详&#12288;&#12288;情
									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">
										<a  class="btn" onclick="updateDoctorTrend('${doctor.recruitFlow}','${doctor.doctorFlow}');">更&#12288;&#12288;新</a>
									</c:if>
									</a>
									<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL && doctor.orgFlow eq mainOrgFlow}" >
										<a class="btn" onclick="doctorInfo('${doctor.doctorFlow}');">编&#12288;&#12288;辑</a>
									</c:if>
									<!-- 培训基地 -->

									<a class="btn"  onclick="InformationQuery('${doctor.doctorFlow}','${param.roleFlag}','${yearStr}');" style="white-space:nowrap;">培训信息</a>
								</span>
							</div>
						</c:if>
						<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_UNIVERSITY}">
							<div style="width: 100%;float:left;">
								<span>
									<a class="btn show" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}');">详&#12288;&#12288;情</a>
									<a class="btn"  onclick="InformationQuery('${doctor.doctorFlow}','${param.roleFlag}','${yearStr}');" style="white-space:nowrap;">培训信息</a>
								</span>
							</div>
						</c:if>
	          		</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="99" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
