<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
});
</script>
<style>
    .inputScore{
        width: 30px;border: 1px solid #e7e7eb;
    }
</style>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="4%"/>
						<col width="6%"/>
						<col width="9%"/>
						<col width="13%"/>
					</c:if>

					<c:if test="${!(roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="5%"/>
						<col width="13%"/>
					</c:if>
	        	</colgroup>
            <tr>
                <th>姓名</th>
				<th>身份证号</th>
                <th>培训<br>基地</th>
                <th>培训<br>专业</th>
                <th>第一站</th>
				<th>第二站</th>
				<th>第三站</th>
				<th>第四站</th>
				<th>第五站</th>
				<th>第六站</th>
				<th>第七站</th>
				<th>第八站</th>
				<th>第九站</th>
				<th>总分</th>
				<th>是否<br>通过</th>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
				<th>操作</th>
			</c:if>
			</tr>
             <c:forEach items="${doctorList}" var="doctor">
				 <c:set var="extScore" value="${extScoreMap[doctor.resScore.scoreFlow]}"></c:set>
				 <c:set var="firstStationScore" value="${extScore.firstStationScore}"></c:set>
				 <c:set var="secondStationScore" value="${extScore.secondStationScore}"></c:set>
				 <c:set var="thirdStationScore" value="${extScore.thirdStationScore}"></c:set>
				 <c:set var="fourthStationScore" value="${extScore.fourthStationScore}"></c:set>
				 <c:set var="fifthStationScore" value="${extScore.fifthStationScore}"></c:set>
				 <c:set var="sixthStationScore" value="${extScore.sixthStationScore}"></c:set>
				 <c:set var="seventhStationScore" value="${extScore.seventhStationScore}"></c:set>
				 <c:set var="eighthStationScore" value="${extScore.eighthStationScore}"></c:set>
				 <c:set var="ninthStationScore" value="${extScore.ninthStationScore}"></c:set>
				 <c:set var="xiji1" value="${firstStationScore+secondStationScore+thirdStationScore}"></c:set>
				 <c:set var="xiji2" value="${fourthStationScore+fifthStationScore+sixthStationScore	 +seventhStationScore+eighthStationScore}"></c:set>
				 <c:set var="all" value="${firstStationScore+secondStationScore+thirdStationScore+fourthStationScore+fifthStationScore+sixthStationScore
				 +seventhStationScore+eighthStationScore+ninthStationScore }"></c:set>
				 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					 <tr>
						 <td>${doctor.sysUser.userName}</td>
						 <td>${doctor.sysUser.idNo}</td>
						 <td>${doctor.resDoctor.orgName}</td>
						 <td>${doctor.resDoctor.trainingSpeName}</td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','firstStationScore',this,'第一站','${roleFlag}');" value="${firstStationScore}" > </input>
							 <input class="skillScore1" hidden value="${firstStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','secondStationScore',this,'第二站','${roleFlag}');" value="${secondStationScore}" > </input>
							 <input class="skillScore1" hidden value="${secondStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','thirdStationScore',this,'第三站','${roleFlag}');" value="${thirdStationScore}" > </input>
							 <input class="skillScore1" hidden value="${thirdStationScore}"/>
						 </td>
						 <%--<td class="xiji1">${xiji1}</td>--%>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','fourthStationScore',this,'第四站','${roleFlag}');" value="${fourthStationScore}" > </input>
							 <input class="skillScore1" hidden value="${fourthStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','fifthStationScore',this,'第五站','${roleFlag}');" value="${fifthStationScore}" > </input>
							 <input class="skillScore1" hidden value="${fifthStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','sixthStationScore',this,'第六站','${roleFlag}');" value="${sixthStationScore}" > </input>
							 <input class="skillScore1" hidden value="${sixthStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','seventhStationScore',this,'第七站','${roleFlag}');" value="${seventhStationScore}" > </input>
							 <input class="skillScore1" hidden value="${seventhStationScore}"/>
						 </td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','eighthStationScore',this,'第八站','${roleFlag}');" value="${eighthStationScore}" > </input>
							 <input class="skillScore1" hidden value="${eighthStationScore}"/>
						 </td>
						 <%--<td class="xiji2">${xiji2}</td>--%>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText skillScore" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveScore('${doctor.resScore.scoreFlow}','ninthStationScore',this,'第九站','${roleFlag}');" value="${ninthStationScore}" > </input>
							 <input class="skillScore1" hidden value="${ninthStationScore}"/>
						 </td>
						 <td class="all"><fmt:formatNumber type="number" value="${all}" maxFractionDigits="2"/></td>
						 <td><input style="width: 30px;border: 1px solid #e7e7eb;" class="inputText isPass" readOnly="true" onclick="$(this).removeAttr('readOnly');"
									onblur="saveIsPassScore('${doctor.resScore.scoreFlow}','isPass',this,'是否通过','${roleFlag}');"
									value="<c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">是</c:if><c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">否</c:if>" > </input>
							 <input class="isPass1" hidden value="<c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">是</c:if><c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">否</c:if>"/>
						 </td>
						 <td style="">
							 <div style="width: 100px">
		          			<span>
							<!-- 培训基地 -->
				          	<a class="btn"  onclick="deleteSkillScore('${doctor.resScore.scoreFlow}','${roleFlag}');" style="margin-top: 5px;margin-bottom: 5px;">删除</a>
							</span>
							 </div>
						 </td>
					 </tr>
				 </c:if>
				 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
					 <tr>
						 <td>${doctor.sysUser.userName}</td>
						 <td>${doctor.sysUser.idNo}</td>
						 <td>${doctor.resDoctor.orgName}</td>
						 <td>${doctor.resDoctor.trainingSpeName}</td>
                         <td>${firstStationScore}</td>
                         <td>${secondStationScore}</td>
                         <td>${thirdStationScore}</td>
                             <%--<td>${xiji1}</td>--%>
                         <td>${fourthStationScore}</td>
                         <td>${fifthStationScore}</td>
                         <td>${sixthStationScore}</td>
                         <td>${seventhStationScore}</td>
                         <td>${eighthStationScore}</td>
                             <%--<td>${xiji2}</td>--%>
                         <td>${ninthStationScore}</td>
                         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${all}"/></td>
                         <td><c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">是</c:if><c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">否</c:if></td>
                     </tr>
				 </c:if>

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
      
