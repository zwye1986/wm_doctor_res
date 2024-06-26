<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
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
						<%--<col width="7%"/>--%>
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
						<%--<col width="7%"/>--%>
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
						<%--<col width="7%"/>--%>
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
                <%--<th>培训<br>类别</th>--%>
                <th>培训<br>基地</th>
                <th>培训<br>专业</th>
                <th>年级</th>
                <th>培训<br>年限</th>
				<th>${scoreYear}年<br>理论成绩</th>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
					<th>${scoreYear}年<br>技能成绩</th>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					<th>操作</th>
				</c:if>

            </tr>
             <c:forEach items="${doctorList}" var="doctor">
	             <tr>
	                <td>${doctor.sysUser.userName}</td>
	                <td>${doctor.sysUser.idNo}</td>
	                <td>${doctor.sysUser.sexName}</td>
	                <td>${doctor.sysUser.userPhone}</td>
	                <%--<td>${doctor.speName}</td>--%>
	                <td>${doctor.orgName}</td>
	                <td>${doctor.catSpeName}</td>
	                <td>${doctor.sessionNumber}</td>
	                <td>
	                  <c:set var="year" value="${doctor.trainYear}"/>
	    				<c:forEach items="${jszyResTrainYearEnumList}" var="dict">
	                		<c:if test="${dict.id eq year}">
	                		${dict.name}
	                		</c:if>
	    				</c:forEach>            
	                </td>
					 <td>
						 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
							 <%--<input style="width: 30px;" class="inputText theoryScore" readOnly="true"  value="${doctor.resScore.theoryScore}" > </input>--%>
							 ${doctor.resScore.theoryScore eq '1'?'合格':(doctor.resScore.theoryScore eq '0'?'不合格':(doctor.resScore.theoryScore eq '2'?'缺考':''))}
						 </c:if>
						 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							 <%--<input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText theoryScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"--%>
									<%--onblur="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');"--%>
									<%--onchange="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" value="${doctor.resScore.theoryScore}" > </input>--%>
							 <select class="select" onchange="saveScore2('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');">
								 <option value="1" ${doctor.resScore.theoryScore eq '1'?'selected':''}>合格</option>
								 <option value="0" ${doctor.resScore.theoryScore eq '0'?'selected':''}>不合格</option>
								 <option value="2" ${doctor.resScore.theoryScore eq '2'?'selected':''}>缺考</option>
							 </select>
							 <%--<input class="theoryScore1" hidden value="${doctor.resScore.theoryScore}"/>--%>
						 </c:if>
						 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
							 ${doctor.resScore.theoryScore eq '1'?'合格':(doctor.resScore.theoryScore eq '0'?'不合格':(doctor.resScore.theoryScore eq '2'?'缺考':''))}
						 </c:if>
						<%--<input class="inputText theoryScore" type="text"onblur="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" onchange="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" style="width: 60px;" value=""/>--%>
					 </td>
					 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
						 <td>
							 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">
							 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${scoreYear}');" >合格</a></c:if>
							 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">
								 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${scoreYear}');" >不合格</a></c:if>
							 <c:if test="${doctor.resScore.skillScore eq '2'}">
								 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${scoreYear}');" >缺考</a>
							 </c:if>
						 </td>
					 </c:if>
					 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						 <td style="">
							 <div style="width: 100px;">
		          		<span>
							<!-- 培训基地 -->
				          	<a class="btn"  onclick="deleteScore('${doctor.resScore.scoreFlow}','${roleFlag}','${param.currentPage}');" style="margin-top: 5px;margin-bottom: 5px;">删除</a>
						</span>
							 </div>
						 </td>
					 </c:if>

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
      
