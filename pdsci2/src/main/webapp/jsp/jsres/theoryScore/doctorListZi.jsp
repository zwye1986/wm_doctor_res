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
    <div class="search_table" style="padding:0;width: 95%;margin: 5px auto;">
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

<%--

                <th>培训<br>年限</th>
				<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
					<th>${scoreYear}年<br>理论成绩</th>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
					<th>理论<br>成绩</th>
					<th>技能<br>成绩</th>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					<th>操作</th>
				</c:if>
--%>

            </tr>
             <c:forEach items="${doctorList}" var="doctor">
	             <tr>
	                <td>${doctor.sysUser.userName}</td>
	                <td>${doctor.theoryTestId}</td>
	                <td>${doctor.sysUser.idNo}</td>
	                <td>${doctor.sessionNumber}</td>
	                <td>${doctor.placeName}</td>
	                <td>${doctor.catSpeName}</td>
	                <td>${doctor.orgName}</td>
	                <td>${doctor.speName}</td>
					 <td >

							 <%--<input style="width: 30px;" class="inputText theoryScore" readOnly="true"  value="${doctor.resScore.theoryScore}" > </input>--%>
							 ${doctor.resScore.theoryScore eq '1'?'是':(doctor.resScore.theoryScore eq '0'?'否':(doctor.resScore.theoryScore eq '2'?'缺考':'-'))}

						<%-- <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							 &lt;%&ndash;<input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText theoryScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"&ndash;%&gt;
									&lt;%&ndash;onblur="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');"&ndash;%&gt;
									&lt;%&ndash;onchange="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" value="${doctor.resScore.theoryScore}" > </input>&ndash;%&gt;
							 <input class="theoryScore1" hidden value="${doctor.resScore.theoryScore}"/>
							 <select class="select" onchange="saveScore2('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');">
								 <option value="1" ${doctor.resScore.theoryScore eq '1'?'selected':''}>合格</option>
								 <option value="0" ${doctor.resScore.theoryScore eq '0'?'selected':''}>不合格</option>
								 <option value="2" ${doctor.resScore.theoryScore eq '2'?'selected':''}>缺考</option>
							 </select>
						 </c:if>--%>
						 <%--<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
							 &lt;%&ndash;<input style="width: 30px;" class="inputText theoryScore" readOnly="true"  value="${doctor.resScore.theoryScore}" > </input>&ndash;%&gt;
							 ${doctor.resScore.theoryScore eq '1'?'合格':(doctor.resScore.theoryScore eq '0'?'不合格':(doctor.resScore.theoryScore eq '2'?'缺考':'-'))}
						 </c:if>--%>
						<%--<input class="inputText theoryScore" type="text"onblur="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" onchange="saveScore('${doctor.resScore.scoreFlow}','theoryScore',this,'${roleFlag}');" style="width: 60px;" value=""/>--%>
					 </td>
					 <%--<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
						 <td <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL && not empty doctor.resScore.scorePhaseName}">title="${doctor.resScore.scorePhaseName}年份"</c:if>>
							 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">
								 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${doctor.resScore.scorePhaseName}');" >合格</a></c:if>
							 <c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">
								 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${doctor.resScore.scorePhaseName}');" >不合格</a></c:if>
							 <c:if test="${doctor.resScore.skillScore eq '2'}">
								 <a onclick="skillScoreDetail('${doctor.sysUser.userFlow}','${doctor.resScore.scoreFlow}','skillScore','${doctor.resScore.scorePhaseName}');" >缺考</a></c:if>
							 <c:if test="${empty doctor.resScore.skillScore}">-</c:if>
						 </td>
					 </c:if>--%>
					<%-- <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						 <td style="">
							 <div style="width: 100%;">
		          		<span>
							<!-- 培训基地 -->
				          	<a class="btn"  onclick="deleteScore('${doctor.resScore.scoreFlow}','${roleFlag}','${param.currentPage}');" style="margin-top: 5px;margin-bottom: 5px;">删除</a>
						</span>
							 </div>
						 </td>
					 </c:if>
--%>
	            </tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="12" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="width: 906px;margin: 15px auto;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
