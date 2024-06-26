<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>

<script type="text/javascript">
	$(document).ready(function(){
		initCheck();

	});

	//修改时间
	function modifyDate(cfgCode){
		jboxOpen("<s:url value='/res/doctorCfg/showModifyDate'/>?cfgCode="+cfgCode, "修改权限时间",400,200);
	}
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>培训基地</th>
			<th>年级</th>
			<th>姓名</th>
			<th>登录名</th>
			<th>证件号码</th>
			<th>培训专业</th>
			<th>培养年限</th>
			<th>WEB登录权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="webLogin" onclick="choose('webLogin','webLogins','res_doctor_web_');"/>
			</th>
			<th>APP登录权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="appLogin" onclick="choose('appLogin','appLogins','res_doctor_app_login_');"/>
			</th>
			<th>权限期限(WEB/APP)
				<%--<br>--%>
				<%--<input type="hidden" name="qxqj" onclick="choose('qxqj','qxqjs','res_doctor_qxqj_');"/>--%>
			</th>
			<th>出科考试权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="outProcessExam" onclick="choose('outProcessExam','outProcessExams','res_doctor_ckks_');"/>
			</th>
			<th>结业理论模拟考核
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="outGraduationExam" onclick="choose('outGraduationExam','outGraduationExams','res_doctor_graduation_exam_');"/>
			</th>
			<th>培训手册
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="schManual" onclick="choose('schManual','schManuals','res_doctor_pxsc_');"/>
			</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.orgName }</td>
			<td>${userExt.sessionNumber }</td>
			<td>${userExt.userName }</td>
			<td>${userExt.userCode}</td>
			<td>${userExt.idNo }</td>
			<td>${userExt.trainingSpeName}</td>
			<td>${userExt.trainingYears}</td>
			<td>
				<c:set var="key" value="res_doctor_web_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="res_doctor_web_${userExt.userFlow}" name="webLogins" value="${userExt.userFlow}" ${pdfn:resPowerCfgMap(key).cfgValue == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','res_doctor_web_');"/>
			</td>
			<td>
				<c:set var="key2" value="res_doctor_app_login_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="res_doctor_app_login_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}"  ${pdfn:resPowerCfgMap(key).cfgValue != GlobalConstant.FLAG_Y?'disabled':''} ${pdfn:resPowerCfgMap(key2).cfgValue == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','res_doctor_app_login_');"/>
			</td>
			<td>
				<%--<c:set var="key3" value="res_doctor_qxqj_${userExt.userFlow}"/>--%>
				<%--<input type="text" value="${timeMap[userExt.userFlow].cfgCode}">--%>

					<a <%--href="javascript:modifyDate('${timeMap[userExt.userFlow].cfgCode}');"--%> id="res_doctor_qxqj" <c:if test="${pdfn:resPowerCfgMap(key).cfgValue ne GlobalConstant.FLAG_Y}">style="display: none"</c:if> class="parentA">
						<c:if test="${ (not empty timeMap[userExt.userFlow].powerStartTime) and (not empty timeMap[userExt.userFlow].powerEndTime)}">
							[${timeMap[userExt.userFlow].powerStartTime}&nbsp;至&nbsp;${timeMap[userExt.userFlow].powerEndTime}]
						</c:if>
					</a>

			</td>

			<td>
				<c:set var="key4" value="res_doctor_ckks_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="res_doctor_ckks_${userExt.userFlow}" name="outProcessExams" value="${userExt.userFlow}" ${pdfn:resPowerCfgMap(key4).cfgValue==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','res_doctor_ckks_');"/>
			</td>
			<td>
				<c:set var="key2" value="res_doctor_graduation_exam_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="res_doctor_graduation_exam_${userExt.userFlow}" name="outGraduationExams" value="${userExt.userFlow}" ${pdfn:resPowerCfgMap(key2).cfgValue==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','res_doctor_graduation_exam_');"/>
			</td>
			<td>
				<c:set var="key5" value="res_doctor_pxsc_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="res_doctor_pxsc_${userExt.userFlow}" name="schManuals" value="${userExt.userFlow}" ${pdfn:resPowerCfgMap(key5).cfgValue==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','res_doctor_pxsc_');"/>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr>
			<td colspan="8" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>